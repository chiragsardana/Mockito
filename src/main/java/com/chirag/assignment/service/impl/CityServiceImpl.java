package com.chirag.assignment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chirag.assignment.entities.City;
import com.chirag.assignment.exception.NoCityFoundException;
import com.chirag.assignment.repository.CityRepository;
import com.chirag.assignment.service.CityService;

@Service
// service component and can be autowired into other classes.
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    
    // constructor injection
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // creates a new City instance, sets the city name, 
    // saves and returns the saved city.
    @Override
    public City addCity(String cityName) {
        City city = new City();
        city.setName(cityName);
        
        City savedCity = cityRepository.save(city);
        System.out.println("This is the City Name"+ cityName+" and the city "+
            "object before sending to repository"+city+" and saved City"+savedCity);
        return savedCity;
    }
    // deletes a city by its ID
    @Override
    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }
    // retrieves a city by its ID
    //  If the city is not found, it throws a custom exception NoCityFoundException
    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new NoCityFoundException("City not found"));
    }

    // retrieves all cities
    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

}
