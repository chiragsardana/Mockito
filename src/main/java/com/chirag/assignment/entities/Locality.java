package com.chirag.assignment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
// class is mapped to a database entity
public class Locality {
    @Id
    // primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto-incrementing value
    private Long id;


    private String name;

    @ManyToOne
    // many-to-one relationship with the City entity.
    // multiple instances of Locality can be associated with a single instance of City.
    @JoinColumn(name = "city_id")
    // specify the join column name in the database table for the city association.
    private City city;


    public Locality() {
    }

    public Locality(Long id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
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

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", city='" + getCity() + "'" +
            "}";
    }

}
