package com.coding_sphere.jwt_rbac_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testRoleConstructorAndGetters() {
        Role role = new Role(ERole.ROLE_ADMIN);

        assertNull(role.getId());
        assertEquals(ERole.ROLE_ADMIN, role.getName());
    }

    @Test
    void testSetters() {
        Role role = new Role();
        role.setId("456");
        role.setName(ERole.ROLE_USER);

        assertEquals("456", role.getId());
        assertEquals(ERole.ROLE_USER, role.getName());
    }
}
