package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserDetailServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // start page
    @RequestMapping("/")
    public String showAllUsers(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("newUser", new User());
        model.addAttribute("roleList", userService.getAllRoles());
        model.addAttribute("rolesList", user.getRoles());
        model.addAttribute("userList", userService.getAllUsers());
        return "admin";
    }

    // add new user
    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute User newUser,
                          @RequestParam(value = "roleBox", required = false) Long[] roleBox) {
        Set<Role> rolesSet = new HashSet<>();
        if (roleBox != null) {
            for (long i : roleBox) {
                rolesSet.add(userService.getRoleById(i));
            }
        }
        newUser.setRoles(rolesSet);
        userService.saveUser(newUser);
        return "redirect:/admin/";
    }


    //update user

    @PutMapping("updateUser/{id}")
    public String updateUser(@ModelAttribute User editUser,
                             @RequestParam(value = "roleBox", required = false) Long[] roleBox) {
        Set<Role> rolesSet = new HashSet<>();

        if (roleBox != null) {
            for (long i : roleBox) {
                rolesSet.add(userService.getRoleById(i));
            }
        }
        editUser.setRoles(rolesSet);
        userService.saveAndFlush(editUser);
        return "redirect:/admin/";
    }


    //delete user
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }

}