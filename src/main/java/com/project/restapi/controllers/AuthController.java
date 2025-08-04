package com.project.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.restapi.entities.UserEntity;
import com.project.restapi.repositories.UserRepository;
import com.project.restapi.security.JwtUtil;
import com.project.restapi.services.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private UserRepository userRepo;
    @Autowired private UserDetailsServiceImpl userService;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody UserEntity user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isBlank()) {
            user.setRoles("ROLE_USER");
        }
        userRepo.save(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        try{
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return "Invalid credentials!";
        }
    }
}
