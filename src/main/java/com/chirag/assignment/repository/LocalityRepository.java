package com.chirag.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chirag.assignment.entities.Locality;

public interface LocalityRepository extends JpaRepository<Locality, Long> {
    //  provides CRUD (Create, Read, Update, Delete) operations for the Locality entity.
    List<Locality> findByCityId(Long cityId);
    // list of Locality entities based on the provided cityId.
}
