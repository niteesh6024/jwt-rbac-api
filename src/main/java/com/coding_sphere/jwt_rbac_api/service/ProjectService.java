package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);
    List<Project> getProjects();
}
