package com.chafikhicham.rest.controller;

import com.chafikhicham.dataaccess.entities.Restaurant;
import com.chafikhicham.rest.dto.RestaurantDTO;
import com.chafikhicham.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("${rest.version.v1}/restaurants")
public class RestaurantRestController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity createRestaurant(@RequestBody RestaurantDTO restaurantDTO, UriComponentsBuilder uriBuilder) {
        Restaurant restaurant = restaurantService.createRestaurant(restaurantDTO);

        UriComponents uriComponents = uriBuilder.path("/rest/v1/restaurants/{restaurantId}").buildAndExpand(restaurant.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Object> getRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantDTOById(restaurantId);
        return ResponseEntity.ok(restaurantDTO);

    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Object> updateRestaurant(@PathVariable("restaurantId") Long restaurantId, @RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantService.updateRestaurant(restaurantId, restaurantDTO);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.deleteRestaurantById(restaurantId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{restaurantId}/customers/{customerId}")
    public ResponseEntity addVisit(@PathVariable("restaurantId") Long restaurantId, @PathVariable("customerId") Long customerId) {
        restaurantService.addCustomerAsVisitor(restaurantId, customerId);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
