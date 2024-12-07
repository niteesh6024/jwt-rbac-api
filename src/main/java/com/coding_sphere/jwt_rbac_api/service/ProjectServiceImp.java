package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.repoistory.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService{
    @Autowired
    ProjectRepository projectRepoistory;

    public Project createProject(Project project){
        return projectRepoistory.save(project);
    }

    public List<Project> getProjects(){
        return projectRepoistory.findAll();
    }
}
