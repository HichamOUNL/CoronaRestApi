package com.chafikhicham.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public static final String errorMessageRestaurant = "Restaurant already exists with name: %s, address: %s, email: %s";
    public static final String errorMessageCustomer = "Customer already exists with name: %s, lastName: %s, phoneNumber: %s";

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
