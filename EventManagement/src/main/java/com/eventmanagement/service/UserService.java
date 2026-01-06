package com.eventmanagement.service;

import java.util.List;

import com.eventmanagement.model.User;

public interface UserService {

    User registerUser(User user);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User updateUserRole(Long userId, String role);

    void deactivateUser(Long userId);
    void activateUser(Long userId);

	long countAdmins();

	void deleteUserById(Long id);
}

