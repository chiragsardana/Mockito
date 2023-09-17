package com.chirag.assignment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chirag.assignment.controller.UserController;
import com.chirag.assignment.dto.UserRequestDto;
import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.entities.User;
import com.chirag.assignment.service.CityService;
import com.chirag.assignment.service.LocalityService;
import com.chirag.assignment.service.UserService;

@SpringBootTest
public class UserControllerTest {
    private MockMvc mockMvc;
    // allows you to perform HTTP requests and verify the responses
    // testing Spring MVC controllers.

    @Mock
    // Creates a mock object
    private UserService userService;

    @Mock
    // Creates a mock object
    private CityService cityService;

    @Mock
    // Creates a mock object
    private LocalityService localityService;

    private UserController userController;

    @BeforeEach
    // method should be executed before each test method.
    public void setUp() {
        userController = new UserController(userService, cityService, localityService);
        
        // Build standalone MockMvc instance for UserController
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
    }


    @Test
    // Tests the addUser method of the UserController
    public void testAddUser() throws Exception {

        // Create a UserRequestDto object with test data
        UserRequestDto request = new UserRequestDto();
        request.setName("Chirag Sardana");
        request.setEmail("Chiragsardana12@gmail.com");
        request.setCityId(1L);
        request.setLocalityId(1L);


        // Create City and Locality objects with test data
        City city = new City();
        city.setId(1L);
        city.setName("City");

        Locality locality = new Locality();
        locality.setId(1L);
        locality.setName("Locality");


        // Create a User object with test data
        User user = new User();
        user.setId(1L);
        user.setName("Chirag Sardana");
        user.setEmail("Chiragsardana12@gmail.com");
        user.setCity(city);
        user.setLocality(locality);


        // Mock the behavior of cityService, localityService, and userService
        when(cityService.getCityById(anyLong())).thenReturn(city);
        when(localityService.getLocalityById(anyLong())).thenReturn(locality);
        when(userService.addUser(any(User.class))).thenReturn(user);


        
        // Perform an HTTP POST request to "/api/users" and validate the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType("application/json")
                .content("{\"name\":\"Chirag Sardana\",\"email\":\"Chiragsardana12@gmail.com\",\"cityId\":1,\"localityId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Chirag Sardana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Chiragsardana12@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.name").value("City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locality.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locality.name").value("Locality"));

        // Verify that the methods were called on the mock objects
        verify(cityService, times(1)).getCityById(anyLong());
        verify(localityService, times(1)).getLocalityById(anyLong());
        verify(userService, times(1)).addUser(any(User.class));
    }

    @Test
    public void testDeleteUser() throws Exception {
        // Perform an HTTP Delete request to "/api/users/{userId}" and validate the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/{userId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Verify that the methods were called on the mock objects
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    public void testUpdateUser() throws Exception {

        // Create a UserRequestDto object with test data
        UserRequestDto request = new UserRequestDto();
        request.setName("Chirag Sardana");
        request.setEmail("Chiragsardana12@gmail.com");
        request.setCityId(1L);
        request.setLocalityId(1L);


        // Create City and Locality objects with test data
        City city = new City();
        city.setId(1L);
        city.setName("City");

        Locality locality = new Locality();
        locality.setId(1L);
        locality.setName("Locality");


        // Create existing and updated User objects with test data
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Chirag Sardana 1");
        existingUser.setEmail("Chiragsardanasrs@gmail.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setName("Chirag Sardana");
        updatedUser.setEmail("Chiragsardana12@gmail.com");
        updatedUser.setCity(city);
        updatedUser.setLocality(locality);

        // Mock the behavior of cityService, localityService, and userService
        when(userService.getUser(anyLong())).thenReturn(existingUser);
        when(cityService.getCityById(anyLong())).thenReturn(city);
        when(localityService.getLocalityById(anyLong())).thenReturn(locality);
        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);

        // Perform an HTTP PUT request to "/api/users/{userId}" and validate the response
        mockMvc.perform(MockMvcRequestBuilders.put("/api/users/{userId}", 1L)
                .contentType("application/json")
                .content("{\"name\":\"Chirag Sardana\",\"email\":\"Chiragsardana12@gmail.com\",\"cityId\":1,\"localityId\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Chirag Sardana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Chiragsardana12@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city.name").value("City"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locality.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.locality.name").value("Locality"));

        
        // Verify that the methods were called on the mock objects
        verify(userService, times(1)).getUser(anyLong());
        verify(cityService, times(1)).getCityById(anyLong());
        verify(localityService, times(1)).getLocalityById(anyLong());
        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Chirag Sardana");
        user.setEmail("Chiragsardana12@gmail.com");

        // Mock the behavior of userService
        when(userService.getUser(anyLong())).thenReturn(user);

        // Perform an HTTP GET request to "/api/users/{userId}" and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{userId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Chirag Sardana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("Chiragsardana12@gmail.com"));

        // Verify that the methods were called on the mock objects
        verify(userService, times(1)).getUser(anyLong());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        
        // Create User objects with test data
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Chirag Sardana");
        user1.setEmail("Chiragsardana12@gmail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Dheeraj");
        user2.setEmail("Dheeraj@gmail.com");

        List<User> users = Arrays.asList(user1, user2);
        // Mock the behavior of userService
        when(userService.getAllUsers()).thenReturn(users);

        // Perform an HTTP GET request to "/api/users" and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Chirag Sardana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("Chiragsardana12@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Dheeraj"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("Dheeraj@gmail.com"));
        
        
        // Verify that the methods were called on the mock objects
        verify(userService, times(1)).getAllUsers();
    }
}
