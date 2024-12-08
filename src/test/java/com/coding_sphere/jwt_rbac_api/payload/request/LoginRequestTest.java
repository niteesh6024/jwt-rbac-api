package com.coding_sphere.jwt_rbac_api.payload.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void testSettersAndGetters() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        assertEquals("testuser", loginRequest.getUsername());
        assertEquals("testpassword", loginRequest.getPassword());
    }
}
