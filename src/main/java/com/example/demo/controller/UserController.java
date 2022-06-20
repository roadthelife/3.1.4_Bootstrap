package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserDetailServiceImpl userService;

    @Autowired
    public UserController(UserDetailServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    @GetMapping("/user")
    public String showAllUsers(Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("rolesList", user.getRoles());
        model.addAttribute("user", user);
        return "user";
    }
}
