package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user);

    void deleteUserById(long id);

    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    void saveRole(Role role);

    List<Role> getAllRoles();

    void saveAndFlush(User user);
}
