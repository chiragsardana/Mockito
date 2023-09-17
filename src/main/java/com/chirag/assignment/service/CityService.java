package com.chirag.assignment.service;

import java.util.List;

import com.chirag.assignment.entities.City;

public interface CityService {
    // add a new City
    public City addCity(String cityName);
    // delete City
    public void deleteCity(Long cityId);
    // get city by id
    public City getCityById(Long cityId);
    // all City entities
    public List<City> getAllCities();
}
