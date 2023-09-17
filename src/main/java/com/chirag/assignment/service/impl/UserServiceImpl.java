package com.chirag.assignment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chirag.assignment.entities.User;
import com.chirag.assignment.exception.NoUserFoundException;
import com.chirag.assignment.repository.UserRepository;
import com.chirag.assignment.service.UserService;

@Service
// service component and can be autowired into other classes.
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    // constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // saves a user and return the saved user
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
    // deletes a user by its ID
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    // updates a user and return the updated user
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // retrieves a user by its ID 
    // If the user is not found, it throws a custom exception NoUserFoundException
    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoUserFoundException("User not found"));
    }
    // retrieves all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
}
