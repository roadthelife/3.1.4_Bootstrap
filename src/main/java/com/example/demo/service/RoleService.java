package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);
    List<Role> getAllRoles();
}
