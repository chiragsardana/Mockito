package com.chirag.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.chirag.assignment.entities.City;
import com.chirag.assignment.repository.CityRepository;
import com.chirag.assignment.service.CityService;
import com.chirag.assignment.service.impl.CityServiceImpl;

@SpringBootTest
public class CityServiceTest {

    @Mock
    //  create a mock object of the CityRepository.
    private CityRepository cityRepository;

    private CityService cityService;

    City city, city1, city2;

    List<City> cities;

    @BeforeEach
    // executed before each test method.
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // initialize the annotated mocks.
        cityService = new CityServiceImpl(cityRepository);
        city = new City(1L, "Sirsa");
        city1 = new City(2L, "Delhi");
        city2 = new City(3L, "Gurugram");
        cities = Arrays.asList(city1, city2);
    }

    @AfterEach
    void tearDown() throws Exception {
        
    }

    @Test
    void testAddCity() {
        mock(City.class);
        mock(CityRepository.class);

        when(cityRepository.save(Mockito.any(City.class))).thenReturn(city);
        // mocks the behavior of the cityRepository.save() method to return the city object 

        City savedCity = cityService.addCity(city.getName());
        System.out.println("Error" + " " + savedCity + " and " + city);
        assertNotNull(savedCity);
        // verifies that the returned savedCity is not null
        assertEquals(city.getName(), savedCity.getName());
        // and has the same name as the original city object.
    }

    @Test
    void testGetCityById() {
        Long cityId = 1L;
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));
        // mocks the behavior of the cityRepository.findById() method to return an
        // Optional containing the city object when called with the specified cityId

        City retrievedCity = cityService.getCityById(cityId);

        System.out.println(retrievedCity + " is the Retrieved city");

        assertNotNull(retrievedCity);
        // Verifying the retrieved City object is not null
        assertEquals(cityId, retrievedCity.getId());
        // and id is same
        assertEquals(city.getName(), retrievedCity.getName());
        // and the city name also
    }

    @Test
    void testGetAllCities() {
        

        when(cityRepository.findAll()).thenReturn(cities);
        // mocks the behavior of the cityRepository.findAll() method to return the cities list
        List<City> retrievedCities = cityService.getAllCities();

        System.out.println("The Retrieved Cities is "+retrievedCities);

        assertNotNull(retrievedCities);
        // verifies that the retrieved list of cities is not null,
        assertEquals(cities.size(), retrievedCities.size());
        // equal size
        assertEquals(cities.get(0).getName(), retrievedCities.get(0).getName());
        // name of the city at index 0
        assertEquals(cities.get(1).getName(), retrievedCities.get(1).getName());
        // name of the index at index 1
    }

    @Test
    void testDeleteCity() {
        Long cityId = 1L;

        cityService.deleteCity(cityId);
        // calls the deleteCity() method with the specified cityId

        System.out.println("Delete Service " + cityId);

        // Verify that the deleteById() method was called once with the correct cityId
        verify(cityRepository, times(1)).deleteById(cityId);

    }

}
