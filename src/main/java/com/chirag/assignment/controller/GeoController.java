package com.chirag.assignment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chirag.assignment.dto.LocalityRequestDto;
import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.service.CityService;
import com.chirag.assignment.service.LocalityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// requests from any origin and any headers.
@RestController
// RESTful controller -> handles web requests and returns JSON responses.
@RequestMapping("/api")
// base URL path
public class GeoController {
    private final CityService cityService;
    private final LocalityService localityService;


    // Constructor injection for dependencies
    public GeoController(CityService cityService, LocalityService localityService) {
        this.cityService = cityService;
        this.localityService = localityService;
    }

    // add a new city
    @PostMapping("/cities")
    public City addCity(@RequestBody String cityName) {
        System.out.println(cityName+" is the Name of City");
        return cityService.addCity(cityName);
    }
    // delete a city based on the provided cityId
    @DeleteMapping("/cities/{cityId}")
    public void deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
    }

    // add a new locality
    @PostMapping("/localities")
    public Locality addLocality(@RequestBody LocalityRequestDto request) {
        return localityService.addLocality(request.getLocalityName(), request.getCityId());
    }
    // used to delete a locality based on the provided localityId
    @DeleteMapping("/localities/{localityId}")
    public void deleteLocality(@PathVariable Long localityId) {
        localityService.deleteLocality(localityId);
    }
    // list of all localities.
    @GetMapping("/localities")
    public List<Locality> getAllLocalities() {
        return localityService.getAllLocalities();
    }
    // eturns the Locality object based on the provided localityId
    @GetMapping("/localities/{localityId}")
    public Locality getLocality(@PathVariable Long localityId) {
        return localityService.getLocalityById(localityId);
    }
    // returns the City object based on the provided cityId
    @GetMapping("/cities/{cityId}")
    public City getCity(@PathVariable Long cityId) {
        return cityService.getCityById(cityId);
    }
    // list of all cities.
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    // list of all localities for the provided cityId
    @GetMapping("/localities/cities/{cityId}")
    public List<Locality> getAllLocalitiesByCityId(@PathVariable Long cityId) {
        return localityService.getAllLocalitiesByCityId(cityId);
    }

}
