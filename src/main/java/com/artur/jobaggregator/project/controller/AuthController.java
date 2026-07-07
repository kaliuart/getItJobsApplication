package com.artur.jobaggregator.project.controller;

import com.artur.jobaggregator.project.auth.AuthResponse;
import com.artur.jobaggregator.project.dto.LoginRequest;
import com.artur.jobaggregator.project.dto.RegisterRequest;
import com.artur.jobaggregator.project.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/auth/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);

        return new AuthResponse(token);
    }

    @PostMapping("/auth/register")
    public void registration(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
    }

    @PostMapping("/auth/logout")
    public void logout(){
        userService.logoutUser();
    }




}
