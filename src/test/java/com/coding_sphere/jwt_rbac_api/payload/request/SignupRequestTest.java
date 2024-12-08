package com.coding_sphere.jwt_rbac_api.payload.request;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestTest {

    @Test
    void testSettersAndGetters() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setEmail("test@example.com");
        signupRequest.setPassword("testpassword");

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        signupRequest.setRole(roles);

        assertEquals("testuser", signupRequest.getUsername());
        assertEquals("test@example.com", signupRequest.getEmail());
        assertEquals("testpassword", signupRequest.getPassword());
        assertEquals(1, signupRequest.getRoles().size());
        assertTrue(signupRequest.getRoles().contains("ROLE_USER"));
    }
}
