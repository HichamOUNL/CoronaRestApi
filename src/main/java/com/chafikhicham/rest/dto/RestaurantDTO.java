package com.chafikhicham.rest.dto;

import java.util.List;

public class RestaurantDTO {
    private String name;
    private String address;
    private String email;
    //private List<VisitRegistrationDTO> visitRegistrations;

    public String getName() {
        return name;
    }

    public RestaurantDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RestaurantDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    /*public List<VisitRegistrationDTO> getVisitRegistrations() {
        return visitRegistrations;
    }

    public RestaurantDTO setVisitRegistrations(List<VisitRegistrationDTO> visitRegistrations) {
        this.visitRegistrations = visitRegistrations;
        return this;
    }*/
}
