package com.chafikhicham.service;

import com.chafikhicham.dataaccess.RestaurantRepository;
import com.chafikhicham.dataaccess.entities.Customer;
import com.chafikhicham.dataaccess.entities.Restaurant;
import com.chafikhicham.dataaccess.entities.VisitRegistration;
import com.chafikhicham.exceptions.ResourceAlreadyExistsException;
import com.chafikhicham.exceptions.ResourceNotFoundException;
import com.chafikhicham.rest.dto.RestaurantDTO;
import com.chafikhicham.rest.dto.VisitRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.chafikhicham.exceptions.ResourceAlreadyExistsException.errorMessageRestaurant;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VisitRegistrationService visitRegistrationService;

    public List<RestaurantDTO> getAll() {
        List<Restaurant> restaurants = new ArrayList<>();
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();

        restaurantRepository.findAll().forEach(restaurant -> restaurants.add(restaurant));
        restaurants.forEach(restaurant -> restaurantDTOS.add(mapToRestaurantDTO(restaurant)));

        return restaurantDTOS;
    }

    public RestaurantDTO getRestaurantDTOById(Long restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);

        return mapToRestaurantDTO(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Restaurant restaurant = null;
        Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);

        if (opt.isPresent()) {
            restaurant = opt.get();
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurantId);
        }

        return restaurant;
    }

    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurantRep = restaurantRepository.findByNameAndAddressAndEmail(
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getEmail());

        if (restaurantRep != null) {
            throw new ResourceAlreadyExistsException(
                    String.format(
                            errorMessageRestaurant,
                            restaurantDTO.getName(),
                            restaurantDTO.getAddress(),
                            restaurantDTO.getEmail()));
        }

        Restaurant restaurant = mapToRestaurant(restaurantDTO);
        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public Restaurant updateRestaurant(Long restaurantId, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = getRestaurantById(restaurantId);

        restaurant.setName(restaurantDTO.getName())
                .setAddress(restaurantDTO.getAddress())
                .setEmail(restaurantDTO.getEmail());

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    public void deleteRestaurantById(Long restaurantId) {
        getRestaurantById(restaurantId);
        List<VisitRegistration> visitRegistrations = visitRegistrationService.getByRestaurantId(restaurantId);
        visitRegistrationService.deleteVisitRegistrations(visitRegistrations);
        restaurantRepository.deleteById(restaurantId);
    }

    public void addCustomerAsVisitor(Long restaurantId, Long customerId) {
        Restaurant restaurant = this.getRestaurantById(restaurantId);
        Customer customer = customerService.getCustomerById(customerId);

        VisitRegistration visitRegistration = visitRegistrationService.createVisitRegistration();
        visitRegistration.setCustomer(customer);
        visitRegistration.setRestaurant(restaurant);
        visitRegistrationService.updateVisitRegistration(visitRegistration);
    }


    private RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {
        List<VisitRegistrationDTO> visitRegistrations = new ArrayList<>();
        restaurant.getVisitRegistrations().forEach(visitRegistration -> visitRegistrations.add(visitRegistrationService.mapToVisitRegistrationDTO(visitRegistration)));

        return new RestaurantDTO()
                .setName(restaurant.getName())
                .setAddress(restaurant.getName())
                .setEmail(restaurant.getEmail());
    }

    private Restaurant mapToRestaurant(RestaurantDTO restaurantDTO) {
        return new Restaurant()
                .setName(restaurantDTO.getName())
                .setAddress(restaurantDTO.getAddress())
                .setEmail(restaurantDTO.getEmail());
    }

    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public VisitRegistrationService getVisitRegistrationService() {
        return visitRegistrationService;
    }

    public void setVisitRegistrationService(VisitRegistrationService visitRegistrationService) {
        this.visitRegistrationService = visitRegistrationService;
    }
}
