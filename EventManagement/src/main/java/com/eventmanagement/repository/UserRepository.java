package com.eventmanagement.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventmanagement.model.Role;
import com.eventmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    long countByRole(Role role);
    
}

