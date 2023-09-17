package com.chirag.assignment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chirag.assignment.dto.UserRequestDto;
import com.chirag.assignment.entities.City;
import com.chirag.assignment.entities.Locality;
import com.chirag.assignment.entities.User;
import com.chirag.assignment.service.CityService;
import com.chirag.assignment.service.LocalityService;
import com.chirag.assignment.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
// requests from any origin and any headers.
@RestController
// RESTful controller -> handles web requests and returns JSON responses.
@RequestMapping("/api/users")
// base URL path
public class UserController {
    private final UserService userService;
    private final CityService cityService;
    private final LocalityService localityService;
    
    // Constructor injection for dependencies
    public UserController(UserService userService, CityService cityService, LocalityService localityService) {
        this.userService = userService;
        this.cityService = cityService;
        this.localityService = localityService;
    }
    // add a new user.
    @PostMapping
    public User addUser(@RequestBody UserRequestDto request) {
        City city = cityService.getCityById(request.getCityId());
        Locality locality = localityService.getLocalityById(request.getLocalityId());

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setCity(city);
        user.setLocality(locality);

        return userService.addUser(user);
    }
    // delete a user based on the provided userId
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
    // update an existing user based on the provided userId
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserRequestDto request) {
        User existingUser = userService.getUser(userId);
        City city = cityService.getCityById(request.getCityId());
        Locality locality = localityService.getLocalityById(request.getLocalityId());

        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setCity(city);
        existingUser.setLocality(locality);

        return userService.updateUser(existingUser);
    }
    // returns the User object based on the provided userId
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
    //  list of all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
