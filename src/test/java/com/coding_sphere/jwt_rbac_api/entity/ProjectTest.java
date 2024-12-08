package com.coding_sphere.jwt_rbac_api.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testProjectConstructorAndGetters() {
        Project project = new Project("1", "Project Name", "Project Description");

        assertEquals("1", project.getId());
        assertEquals("Project Name", project.getName());
        assertEquals("Project Description", project.getDescription());
    }

    @Test
    void testSetters() {
        Project project = new Project("1", "Project Name", "Project Description");

        project.setId("2");
        project.setName("Updated Name");
        project.setDescription("Updated Description");

        assertEquals("2", project.getId());
        assertEquals("Updated Name", project.getName());
        assertEquals("Updated Description", project.getDescription());
    }
}
