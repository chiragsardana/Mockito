package com.chirag.assignment.service;

import java.util.List;

import com.chirag.assignment.entities.Locality;

public interface LocalityService {
    // add a new locality
    public Locality addLocality(String localityName, Long cityId);
    // delete a locality
    public void deleteLocality(Long localityId);
    // get locality by id
    public Locality getLocalityById(Long localityId);
    // all locality entities
    public List<Locality> getAllLocalities();
    // all localities of particular city
    public List<Locality> getAllLocalitiesByCityId(Long cityId);
}
