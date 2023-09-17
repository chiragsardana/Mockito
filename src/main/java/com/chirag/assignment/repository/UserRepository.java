package com.chirag.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chirag.assignment.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    //  provides CRUD (Create, Read, Update, Delete) operations for the Locality entity.
}
