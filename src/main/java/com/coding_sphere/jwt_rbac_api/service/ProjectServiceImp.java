package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.repoistory.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{
    @Autowired
    ProjectRepository projectRepoistory;

    public Project createProject(Project project)throws Exception{
        if(projectRepoistory.existsByName(project.getName())){
            throw new Exception("Error: project name already exists!");
        }
        return projectRepoistory.save(project);
    }

    public List<Project> getProjects(){
        return projectRepoistory.findAll();
    }

    @Override
    public void deleteProject(String id) throws Exception {
        Optional<Project> project=projectRepoistory.findById(id);
        if(project.isEmpty()){
            throw new Exception("Error: project does not exists with the id!");
        }
        projectRepoistory.deleteById(id);
    }

    public Project updateProject(Project newproject) throws Exception{
        System.out.println(newproject.getId());
        if(newproject.getId()==null){
            throw new Exception("Error: please give project id!");
        }

        Optional<Project> project=projectRepoistory.findById(newproject.getId());
        if(project.isEmpty()){
            throw new Exception("Error: project does not exists with the id!");
        }

        Project existingProject=project.get();
        if(!newproject.getName().equals(existingProject.getName()) &&
                projectRepoistory.existsByName(newproject.getName())){
            throw new Exception("Error: project name already exists!");
        }
        return projectRepoistory.save(newproject);
    }
}

