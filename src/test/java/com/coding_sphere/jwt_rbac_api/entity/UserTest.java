package com.coding_sphere.jwt_rbac_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User("username", "user@example.com", "password");

        assertEquals("username", user.getUsername());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setId("123");
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("securepassword");

        assertEquals("123", user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("securepassword", user.getPassword());
    }

    @Test
    void testRolesManagement() {
        User user = new User();
        Role role = new Role(ERole.ROLE_USER);

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
    }
}
