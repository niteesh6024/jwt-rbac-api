package com.coding_sphere.jwt_rbac_api.controller;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api")
public class ProjectController {

    private List<User> user =  new ArrayList<>();
    private List<Project> projects =  new ArrayList<>();

    @GetMapping("/projects")
    private ResponseEntity<?> getprojects(){
        return new ResponseEntity<String>("succc",null, HttpStatus.OK);
    }

    @PostMapping("/projects")
    private ResponseEntity<?> createProjects(@RequestBody Project project){
        project.setId(UUID.randomUUID().toString());
        project.setUser(null);
        projects.add(project);
        return new ResponseEntity<List<Project>>(projects,null, HttpStatus.OK);
    }

}
