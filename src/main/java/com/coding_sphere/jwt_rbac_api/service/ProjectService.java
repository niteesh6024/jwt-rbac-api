package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project) throws Exception;
    List<Project> getProjects();
    void deleteProject(String id) throws Exception;
    Project updateProject(Project project) throws Exception;
}
