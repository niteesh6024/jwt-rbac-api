package com.coding_sphere.jwt_rbac_api.controller;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping()
    private ResponseEntity<?> getprojects(){
        return new ResponseEntity<List<Project>>(projectService.getProjects(),null, HttpStatus.OK);
    }
    @PostMapping()
    private ResponseEntity<?> creatprojects(@RequestBody Project project){
        return new ResponseEntity<Project>(projectService.createProject(project),null, HttpStatus.OK);
    }
}
