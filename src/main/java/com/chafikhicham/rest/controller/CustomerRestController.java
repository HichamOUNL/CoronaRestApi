package com.chafikhicham.rest.controller;

import com.chafikhicham.dataaccess.entities.Customer;
import com.chafikhicham.rest.dto.CustomerDTO;
import com.chafikhicham.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("${rest.version.v1}/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody CustomerDTO customerDTO, UriComponentsBuilder uriBuilder) {
        Customer customer = customerService.createCustomer(customerDTO);

        UriComponents uriComponents = uriBuilder.path("/rest/v1/customers/{customerId}").buildAndExpand(customer.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable("customerId") Long customerId) {
        CustomerDTO customerDTO = customerService.getCustomerDTOById(customerId);

        return ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerId, customerDTO);

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
