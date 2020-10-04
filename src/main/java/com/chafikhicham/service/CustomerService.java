package com.chafikhicham.service;

import com.chafikhicham.dataaccess.CustomerRepository;
import com.chafikhicham.dataaccess.entities.Customer;
import com.chafikhicham.dataaccess.entities.VisitRegistration;
import com.chafikhicham.exceptions.ResourceAlreadyExistsException;
import com.chafikhicham.exceptions.ResourceNotFoundException;
import com.chafikhicham.rest.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.chafikhicham.exceptions.ResourceAlreadyExistsException.errorMessageCustomer;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VisitRegistrationService visitRegistrationService;


    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> customers.add(customer));
        return customers;
    }

    public CustomerDTO getCustomerDTOById(Long customerId) {
        Customer customer = getCustomerById(customerId);
        CustomerDTO customerDTO = null;
        if (customer != null) {
            customerDTO = mapToCustomerDTO(customer);
        }
        return customerDTO;
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customerRep = customerRepository.findByNameAndLastNameAndPhoneNumber(
                customerDTO.getName(),
                customerDTO.getLastName(),
                customerDTO.getPhoneNumber());

        if (customerRep != null) {
            throw new ResourceAlreadyExistsException(String.format(errorMessageCustomer, customerRep.getName(), customerRep.getLastName(), customerRep.getPhoneNumber()));
        }

        Customer customer = mapToCustomer(customerDTO);
        customerRepository.save(customer);
        return customer;
    }

    public Customer updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId).get();

        if (!customer.isInfected() && customerDTO.isInfected()) {
            //todo create new record to send all visitors a notification
        }

        customer.setName(customerDTO.getName())
                .setLastName(customerDTO.getLastName())
                .setPhoneNumber(customerDTO.getLastName())
                .setInfected(customerDTO.isInfected());

        customerRepository.save(customer);

        return customer;
    }

    public void deleteCustomerById(Long customerId) {
        List<VisitRegistration> visitRegistrations = visitRegistrationService.getByCustomerId(customerId);
        visitRegistrationService.deleteVisitRegistrations(visitRegistrations);
        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerById(Long customerId) {
        Customer customer = null;
        Optional<Customer> opt = customerRepository.findById(customerId);

        if (opt.isPresent()) {
            customer = opt.get();
        } else {
            throw new ResourceNotFoundException("Customer not found with id: " + customerId);
        }

        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        customerRepository.save(customer);

        return customer;
    }

    private CustomerDTO mapToCustomerDTO(Customer customer) {
        return new CustomerDTO()
                .setName(customer.getName())
                .setLastName(customer.getLastName())
                .setPhoneNumber(customer.getLastName())
                .setInfected(customer.isInfected());
    }

    private Customer mapToCustomer(CustomerDTO customerDTO) {
        return new Customer()
                .setName(customerDTO.getName())
                .setLastName(customerDTO.getLastName())
                .setPhoneNumber(customerDTO.getPhoneNumber())
                .setInfected(customerDTO.isInfected());
    }

}
