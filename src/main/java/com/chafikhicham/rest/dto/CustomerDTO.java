package com.chafikhicham.rest.dto;

public class CustomerDTO {
    private String name;
    private String lastName;
    private String phoneNumber;
    private boolean infected;

    public String getName() {
        return name;
    }

    public CustomerDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isInfected() {
        return infected;
    }

    public CustomerDTO setInfected(boolean infected) {
        this.infected = infected;
        return this;
    }
}
