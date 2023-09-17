package com.chirag.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.chirag.assignment.entities.User;
import com.chirag.assignment.exception.NoUserFoundException;
import com.chirag.assignment.repository.UserRepository;
import com.chirag.assignment.service.UserService;
import com.chirag.assignment.service.impl.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
    @Mock
    // create mock object
    private UserRepository userRepository;

    private UserService userService;


    @BeforeEach
    // executed before each test method.
    public void setup() {
        MockitoAnnotations.openMocks(this);
        //  initialize the mocks
        userService = new UserServiceImpl(userRepository);
    }

    @AfterEach
    // executed after each test method.
    void tearDown() throws Exception {

    }

    @Test
    // tests the addUser() method of the UserService
    public void testAddUser() {
        System.out.println("Test User");
        // Create a user for testing

        User user = new User();
        user.setId(1L);
        user.setName("Chirag Sardana");

        // Mock the userRepository.save() method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the addUser() method
        User result = userService.addUser(user);

        // Verify the userRepository.save() method was called once
        verify(userRepository, times(1)).save(user);

        // Assert the returned user is the same as the mocked user
        assertEquals(user, result);
    }

    @Test
    //  tests the deleteUser() method of the UserService
    public void testDeleteUser() {
        // Mock the userRepository.deleteById() method
        doNothing().when(userRepository).deleteById(1L);

        // Call the deleteUser() method
        userService.deleteUser(1L);

        // Verify the userRepository.deleteById() method was called once
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    // tests the updateUser() method of the UserService
    public void testUpdateUser() {

        System.out.println("Test Updaye User");
        // Create a user for testing
        User user = new User();
        user.setId(1L);
        user.setName("Chirag Sardana");

        // Mock the userRepository.save() method
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the updateUser() method
        User result = userService.updateUser(user);

        // Verify the userRepository.save() method was called once
        verify(userRepository, times(1)).save(user);

        // Assert the returned user is the same as the mocked user
        assertEquals(user, result);
    }

    @Test
    // tests the getUser() method of the UserService when the user exists.
    public void testGetUser_existingUser() {
        // Create a user for testing
        User user = new User();
        user.setId(1L);
        user.setName("Chirag Sardana");

        // Mock the userRepository.findById() method
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        // Using Optional in this way allows you to handle cases where 
        // the findById() method may or may not find a matching user.

        
        // Call the getUser() method
        User result = userService.getUser(1L);

        // Verify the userRepository.findById() method was called once
        verify(userRepository, times(1)).findById(1L);

        // Assert the returned user is the same as the mocked user
        assertEquals(user, result);
    }

    @Test
    //  tests the getUser() method of the UserService when the user does not exist.
    public void testGetUser_nonExistingUser() {
        // Mock the userRepository.findById() method to return an empty Optional
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Call the getUser() method and assert that it throws an Custom Exception
        assertThrows(NoUserFoundException.class, () -> userService.getUser(1L));

        // Verify the userRepository.findById() method was called once
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    // tests the getAllUsers() method of the UserService
    public void testGetAllUsers() {
        System.out.println("Get All Users");
        // Create a list of users for testing
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Chirag Sardana");
        userList.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setName("Dheeraj");
        userList.add(user2);

        // Mock the userRepository.findAll() method
        when(userRepository.findAll()).thenReturn(userList);

        // Call the getAllUsers() method
        List<User> result = userService.getAllUsers();

        // Verify the userRepository.findAll() method was called once
        verify(userRepository, times(1)).findAll();

        // Assert the returned list of users is the same as the mocked list
        assertEquals(userList, result);
    }

}
