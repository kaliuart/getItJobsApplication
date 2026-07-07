package com.artur.jobaggregator.project.service;

import com.artur.jobaggregator.project.auth.JwtService;
import com.artur.jobaggregator.project.dto.LoginRequest;
import com.artur.jobaggregator.project.dto.RegisterRequest;
import com.artur.jobaggregator.project.entity.UserEntity;
import com.artur.jobaggregator.project.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String loginUser(LoginRequest loginRequest) {
        Optional<UserEntity> existing = userRepository.findByEmail(loginRequest.getEmail());

        if (existing.isPresent()) {
            UserEntity user = existing.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

                return jwtService.generateToken(user.getEmail());
            }
            else {
                throw new RuntimeException("invalid password");
            }
        }
        else throw  new RuntimeException("invalid email");

    }

    public void registerUser(RegisterRequest registerRequest) {
        UserEntity userEntity = new UserEntity();

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new DataIntegrityViolationException("User with that email already exists");
        }
        else {
            userEntity.setEmail(registerRequest.getEmail());
            userEntity.setName(registerRequest.getName());
            userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            userRepository.save(userEntity);
        }
    }

    public void logoutUser() {

    }
}
