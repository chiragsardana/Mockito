package com.chirag.assignment;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chirag.assignment.controller.GeoController;
import com.chirag.assignment.dto.LocalityRequestDto;
import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.service.CityService;
import com.chirag.assignment.service.LocalityService;

@SpringBootTest
public class GeoControllerTest {
    @Mock
    // create a mock object
    private CityService cityService;

    @Mock
    // create a mock object
    private LocalityService localityService;

    private GeoController geoController;

    //  to test our Spring controllers
    private MockMvc mockMvc;
    

    @BeforeEach
    public void setup() {

        geoController = new GeoController(cityService, localityService);
        mockMvc = MockMvcBuilders.standaloneSetup(geoController).build();
        // set up your controller instance
    }

    @Test
    // Test for adding a city
    public void testAddCity() throws Exception {
        // Create a test city object
        System.out.println("Test Add City Controller");
        City city = new City();
        city.setId(1L);
        city.setName("Test City");

        // Mock the behavior of cityService.addCity() method to return the test city
        Mockito.when(cityService.addCity(Mockito.anyString())).thenReturn(city);

        // Perform a POST request to the "/api/cities" endpoint with the test city data
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cities")
                .content("Test City")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test City")));
    }

    @Test
    // Test for deleting a city
    public void testDeleteCity() throws Exception {

        // Perform a DELETE request to the "/api/cities/{cityId}" endpoint with the city
        // ID
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cities/{cityId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the cityService.deleteCity() method is called once with any long
        // value
        Mockito.verify(cityService, Mockito.times(1)).deleteCity(Mockito.anyLong());
    }

    @Test
    // Test for adding a locality
    public void testAddLocality() throws Exception {
        System.out.println("Test Add Locality Controller");
        // Create a test locality request DTO
        LocalityRequestDto requestDto = new LocalityRequestDto();
        requestDto.setLocalityName("Test Locality");
        requestDto.setCityId(1L);

        // Create a test locality object
        Locality locality = new Locality();
        locality.setId(1L);
        locality.setName("Test Locality");

        // Mock the behavior of localityService.addLocality() method to return the test
        // locality
        Mockito.when(localityService.addLocality(Mockito.anyString(), Mockito.anyLong())).thenReturn(locality);
        // Perform a POST request to the "/api/localities" endpoint with the test
        // locality data
        mockMvc.perform(MockMvcRequestBuilders.post("/api/localities")
                .content("{\"localityName\":\"Test Locality\", \"cityId\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test Locality")));

    }

    @Test
    // Test for deleting a locality
    public void testDeleteLocality() throws Exception {
        // Perform a DELETE request to the "/api/localities/{localityId}" endpoint with
        // the locality ID
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/localities/{localityId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    // Test for retrieving all localities
    public void testGetAllLocalities() throws Exception {

        // Create a list of test localities
        List<Locality> localities = new ArrayList<>();
        Locality locality1 = new Locality();
        locality1.setId(1L);
        locality1.setName("Locality 1");
        Locality locality2 = new Locality();
        locality2.setId(2L);
        locality2.setName("Locality 2");
        localities.add(locality1);
        localities.add(locality2);

        // Mock the behavior of localityService.getAllLocalities() method to return the
        // test localities
        Mockito.when(localityService.getAllLocalities()).thenReturn(localities);

        // Perform a GET request to the "/api/localities" endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/localities"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Locality 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Locality 2")));

    }

    @Test
    // Test for retrieving a specific locality
    public void testGetLocality() throws Exception {
        
        
        // Create a test locality object
        Locality locality = new Locality();
        locality.setId(1L);
        locality.setName("Test Locality");


        // Mock the behavior of localityService.getLocalityById() method to return the test locality
        Mockito.when(localityService.getLocalityById(Mockito.anyLong())).thenReturn(locality);
        
        
        // Perform a GET request to the "/api/localities/{localityId}" endpoint with the locality ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/localities/{localityId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test Locality")));


        // Verify that the localityService.getLocalityById() method is called once with any long value
        Mockito.verify(localityService, Mockito.times(1)).getLocalityById(Mockito.anyLong());
    }

    @Test
    // Test for retrieving a specific city
    public void testGetCity() throws Exception {

        // Create a test city object
        City city = new City();
        city.setId(1L);
        city.setName("Test City");
        
        
        // Mock the behavior of cityService.getCityById() method to return the test city
        Mockito.when(cityService.getCityById(Mockito.anyLong())).thenReturn(city);

        // Perform a GET request to the "/api/cities/{cityId}" endpoint with the city ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities/{cityId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test City")));

    }

    @Test
    // Test for retrieving all cities
    public void testGetAllCities() throws Exception {


        // Create a list of test cities
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setId(1L);
        city1.setName("City 1");
        City city2 = new City();
        city2.setId(2L);
        city2.setName("City 2");
        cities.add(city1);
        cities.add(city2);



        // Mock the behavior of cityService.getAllCities() method to return the test cities
        Mockito.when(cityService.getAllCities()).thenReturn(cities);


        // Perform a GET request to the "/api/cities" endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cities"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("City 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("City 2")));

    }

    @Test
    // Test for retrieving all localities by city ID
    public void testGetAllLocalitiesByCityId() throws Exception {

        // Create a list of test localities
        List<Locality> localities = new ArrayList<>();
        Locality locality1 = new Locality();
        locality1.setId(1L);
        locality1.setName("Locality 1");
        Locality locality2 = new Locality();
        locality2.setId(2L);
        locality2.setName("Locality 2");
        localities.add(locality1);
        localities.add(locality2);


        // Mock the behavior of localityService.getAllLocalitiesByCityId() method to return the test localities
        Mockito.when(localityService.getAllLocalitiesByCityId(Mockito.anyLong())).thenReturn(localities);


        // Perform a GET request to the "/api/localities/cities/{cityId}" endpoint with the city ID
        mockMvc.perform(MockMvcRequestBuilders.get("/api/localities/cities/{cityId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Locality 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Locality 2")));

    }

}
