package com.chafikhicham.dataaccess.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "restaurant", uniqueConstraints={@UniqueConstraint(columnNames = {"name" , "address", "email"})})
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String email;

    @OneToMany(mappedBy = "restaurant")
    private Set<VisitRegistration> visitRegistrations;

    public Long getId() {
        return id;
    }

    public Restaurant setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Restaurant setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Restaurant setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<VisitRegistration> getVisitRegistrations() {
        return visitRegistrations;
    }

    public void setVisitRegistrations(Set<VisitRegistration> visitRegistrations) {
        this.visitRegistrations = visitRegistrations;
    }
}
