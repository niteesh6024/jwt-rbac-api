package com.coding_sphere.jwt_rbac_api.payload.response;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoResponseTest {

    @Test
    void testConstructorAndGetters() {
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        UserInfoResponse userInfoResponse = new UserInfoResponse("1", "testuser", "test@example.com", roles, "jwtToken");

        assertEquals("1", userInfoResponse.getId());
        assertEquals("testuser", userInfoResponse.getUsername());
        assertEquals("test@example.com", userInfoResponse.getEmail());
        assertEquals(roles, userInfoResponse.getRoles());
        assertEquals("jwtToken", userInfoResponse.getJwtKey());
    }
}
