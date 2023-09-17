package com.chirag.assignment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.exception.NoCityFoundException;
import com.chirag.assignment.exception.NoLocalityFoundException;
import com.chirag.assignment.repository.CityRepository;
import com.chirag.assignment.repository.LocalityRepository;
import com.chirag.assignment.service.LocalityService;

@Service
// service component and can be autowired into other classes.
public class LocalityServiceImpl implements LocalityService{
    private final LocalityRepository localityRepository;
    private final CityRepository cityRepository;

    // constructor injection
    public LocalityServiceImpl(LocalityRepository localityRepository, CityRepository cityRepository) {
        this.localityRepository = localityRepository;
        this.cityRepository = cityRepository;
    }



    // creates a new Locality instance, sets the locality name, finds the city by its ID 
    // using the city repository, sets the city for the locality, saves it using the 
    // locality repository, and returns the saved locality.
    @Override
    public Locality addLocality(String localityName, Long cityId) {
        Locality locality = new Locality();
        locality.setName(localityName);
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new NoCityFoundException("City not found"));
        locality.setCity(city);
        return localityRepository.save(locality);
    }
    // deletes a locality by its ID
    @Override
    public void deleteLocality(Long localityId) {
        localityRepository.deleteById(localityId);
    }
    //  locality by its ID
    // If the locality is not found, it throws a custom exception 
    @Override
    public Locality getLocalityById(Long localityId) {
        return localityRepository.findById(localityId)
                .orElseThrow(() -> new NoLocalityFoundException("Locality not found"));
    }
    // retrieves all localities 
    @Override
    public List<Locality> getAllLocalities() {
        return localityRepository.findAll();
    }
    // retrieves all localities associated with a specific city ID
    @Override
    public List<Locality> getAllLocalitiesByCityId(Long cityId) {
        return localityRepository.findByCityId(cityId);
    }
    
}
