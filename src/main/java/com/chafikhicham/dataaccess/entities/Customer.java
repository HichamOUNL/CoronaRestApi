package com.chafikhicham.dataaccess.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer", uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "lastname", "phoneNumber"})})
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private boolean infected;
    /*@OneToMany
    private Set<Visit> visit;*/

    @OneToMany(mappedBy = "restaurant")
    private Set<VisitRegistration> visitRegistrations;

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public boolean isInfected() {
        return infected;
    }

    public Customer setInfected(boolean infected) {
        this.infected = infected;
        return this;
    }

    public Set<VisitRegistration> getVisitRegistrations() {
        return visitRegistrations;
    }

    public void setVisitRegistrations(Set<VisitRegistration> visitRegistrations) {
        this.visitRegistrations = visitRegistrations;
    }
}
