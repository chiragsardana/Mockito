package com.chirag.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.repository.CityRepository;
import com.chirag.assignment.repository.LocalityRepository;

@DataJpaTest
// JPA-specific test
// provides some auto-configuration for testing with Spring Data JPA.



// @AutoConfigureTestDatabase(replace = Replace.NONE)
// @TestPropertySource("classpath:application-test.properties")
public class LocalityRepositoryTest {
    // Provided or given -> When there is an execution -> answer or output

    @Autowired
    private LocalityRepository localityRepository;
    @Autowired
    private CityRepository cityRepository;

    Locality locality;

    @Test
    // mark the individual test methods 
    public void testFindByCityId_ExistingCityId() {
        // Arrange
        Long cityId = 1L;

        // Create and save test data
        City city = new City(cityId, "City A");
        cityRepository.save(city); // Save the city object to the database

        City city1 = new City(2L, "City B");
        cityRepository.save(city1);

        localityRepository.saveAll(Arrays.asList(
                new Locality(1L, "Locality 1", city),
                new Locality(2L, "Locality 2", city),
                new Locality(3L, "Locality 3", city),
                new Locality(4L, "Locality 4", city1) // Adding a locality with a different cityId
        ));

        // Act
        List<Locality> localities = localityRepository.findByCityId(cityId);

        // Assert
        assertEquals(3, localities.size()); // Assuming 3 localities are associated with cityId 1
        assertNotNull(localities);

    }

    @Test
    public void testFindByCityId_NonExistingCityId() {
        // Arrange
        Long cityId = 10L; // Assuming cityId 10 does not exist

        // Act
        List<Locality> localities = localityRepository.findByCityId(cityId);

        // Assert
        assertNotNull(localities);
        assertTrue(localities.isEmpty());
    }

    @Test
    public void testFindByCityId_NullCityId() {
        // Arrange
        Long cityId = null;

        // Act
        List<Locality> localities = localityRepository.findByCityId(cityId);

        // Assert
        assertNotNull(localities);
        assertTrue(localities.isEmpty());
    }
}
