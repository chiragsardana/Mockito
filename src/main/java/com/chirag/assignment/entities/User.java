package com.chirag.assignment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// class is mapped to a database entity.
public class User {
    @Id
    // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto-incrementing value
    private Long id;
    private String name;
    private String email;

    @ManyToOne
    // many-to-one relationship with the City entity.
    @JoinColumn(name = "city_id")
    // specify the join column names in the database tables for the associations.
    private City city;


    @ManyToOne
    // many-to-one relationship with the Locality entity.
    @JoinColumn(name = "locality_id")
    // specify the join column names in the database tables for the associations.
    private Locality locality;


    public User() {
    }

    public User(Long id, String name, String email, City city, Locality locality) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.locality = locality;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Locality getLocality() {
        return this.locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", city='" + getCity() + "'" +
            ", locality='" + getLocality() + "'" +
            "}";
    }

}
