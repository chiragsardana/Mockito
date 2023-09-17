package com.chirag.assignment.service;

import java.util.List;

import com.chirag.assignment.entities.User;

public interface UserService {
    public User addUser(User user);
    public void deleteUser(Long userId);
    public User updateUser(User user);
    public User getUser(Long userId);
    public List<User> getAllUsers();
}
