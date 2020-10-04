package com.chafikhicham.dataaccess.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VisitRegistration {
    @Id
    @GeneratedValue
    private int id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public VisitRegistration setId(int id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public VisitRegistration setDate(Date date) {
        this.date = date;
        return this;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public VisitRegistration setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
