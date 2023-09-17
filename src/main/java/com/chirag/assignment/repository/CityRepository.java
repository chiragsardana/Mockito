package com.chirag.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chirag.assignment.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{
    //  provides CRUD (Create, Read, Update, Delete) operations for the Locality entity.
}
