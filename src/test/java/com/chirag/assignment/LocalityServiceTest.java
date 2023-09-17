package com.chirag.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.repository.CityRepository;
import com.chirag.assignment.repository.LocalityRepository;
import com.chirag.assignment.service.LocalityService;
import com.chirag.assignment.service.impl.LocalityServiceImpl;

@SpringBootTest
public class LocalityServiceTest {
    @Mock
    // create mock object
    private LocalityRepository localityRepository;

    @Mock
    // create mock object
    private CityRepository cityRepository;

    private LocalityService localityService;

    @BeforeEach
    // executed before each test method.
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //  initialize the mocks
        localityService = new LocalityServiceImpl(localityRepository, cityRepository);
    }

    @AfterEach
    // executed after each test method.
    void tearDown() throws Exception {

    }

    @Test
    // tests the addLocality() method of the service
    public void testAddLocality() {
        System.out.println("Add Locality Test");
        // mock(City.class);
        // mock(Locality.class);
        // mock(CityRepository.class);
        // mock(LocalityRepository.class);

        // Mock data
        String localityName = "Test Locality";
        Long cityId = 1L;

        City city = new City();
        city.setId(cityId);
        city.setName("Test City");

        Locality locality = new Locality();
        locality.setName(localityName);
        locality.setCity(city);

        // Mock behavior
        when(cityRepository.findById(cityId)).thenReturn(Optional.of(city));
        when(localityRepository.save(any(Locality.class))).thenReturn(locality);
        // mock behavior for the cityRepository.findById() and localityRepository.save()


        // Perform the method call
        Locality result = localityService.addLocality(localityName, cityId);

        // Verify the interactions and assertions
        verify(cityRepository, times(1)).findById(cityId);
        verify(localityRepository, times(1)).save(any(Locality.class));
        assertEquals(localityName, result.getName());
        assertEquals(cityId, result.getCity().getId());
    }

    @Test
    // tests the deleteLocality() method of the service.
    public void testDeleteLocality() {
        System.out.println("Delete Locality Test");
        // Mock data
        Long localityId = 1L;

        // Perform the method call
        localityService.deleteLocality(localityId);

        // Verify the interaction
        verify(localityRepository, times(1)).deleteById(localityId);
        // verifies that the localityRepository.deleteById() method is 
        // called with the correct locality ID.
    }

    @Test
    // tests the getLocalityById() method of the service.
    public void testGetLocalityById() {
        // Mock data
        Long localityId = 1L;

        Locality locality = new Locality();
        locality.setId(localityId);
        locality.setName("Test Locality");

        // Mock behavior
        when(localityRepository.findById(localityId)).thenReturn(Optional.of(locality));
        // mock behavior for the localityRepository.findById()

        // Perform the method call
        Locality result = localityService.getLocalityById(localityId);

        // Verify the interactions and assertions
        verify(localityRepository, times(1)).findById(localityId);
        assertEquals(localityId, result.getId());
    }

    @Test
    //  tests the getAllLocalities() method of the service.
    public void testGetAllLocalities() {
        // Mock data
        Locality locality1 = new Locality();
        locality1.setId(1L);
        locality1.setName("Locality 1");

        Locality locality2 = new Locality();
        locality2.setId(2L);
        locality2.setName("Locality 2");

        List<Locality> localities = new ArrayList<>();
        localities.add(locality1);
        localities.add(locality2);

        // Mock behavior
        when(localityRepository.findAll()).thenReturn(localities);
        // mock behavior for the localityRepository.findAll()

        // Perform the method call
        List<Locality> result = localityService.getAllLocalities();

        // Verify the interactions and assertions
        verify(localityRepository, times(1)).findAll();
        assertEquals(2, result.size());
    }

    @Test
    // tests the getAllLocalitiesByCityId() method of the service.
    public void testGetAllLocalitiesByCityId() {
        // Mock data
        Long cityId = 1L;
        City city = new City(cityId, "Jaipur");

        Locality locality1 = new Locality();
        locality1.setId(1L);
        locality1.setName("Locality 1");
        locality1.setCity(city);

        Locality locality2 = new Locality();
        locality2.setId(2L);
        locality2.setName("Locality 2");
        locality2.setCity(city);

        List<Locality> localities = new ArrayList<>();
        localities.add(locality1);
        localities.add(locality2);

        // Mock behavior
        when(localityRepository.findByCityId(cityId)).thenReturn(localities);
        // mock behavior for the localityRepository.findByCityId()

        // Perform the method call
        List<Locality> result = localityService.getAllLocalitiesByCityId(cityId);

        // Verify the interactions and assertions
        verify(localityRepository, times(1)).findByCityId(cityId);
        assertEquals(2, result.size());
    }

}
