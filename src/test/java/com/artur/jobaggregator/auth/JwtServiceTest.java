package com.artur.jobaggregator.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        ReflectionTestUtils.setField(jwtService, "secret", "abcdefghijklmnoprstkdkadadwooadlawaldaldald");
        ReflectionTestUtils.setField(jwtService, "lifetime", 3600000);

        jwtService.init();
    }

    @Test
    void validateToken_validToken_returnsTrue() {
        String email = "email";

        String token = jwtService.generateToken(email);
        assertTrue(jwtService.validateToken(token));
    }

    @Test
    void validateToken_invalidToken_returnsFalse() {
        String token = "garbage_token";
        assertFalse(jwtService.validateToken(token));
    }

    @Test
    void getEmailFromToken_returnsEmail() {
        String email = "email";

        String token = jwtService.generateToken(email);
        assertEquals(email, jwtService.getEmailFromToken(token));
    }
}