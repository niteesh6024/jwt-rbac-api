//package com.coding_sphere.jwt_rbac_api.controller;
//
//import com.coding_sphere.jwt_rbac_api.entity.Project;
//import com.coding_sphere.jwt_rbac_api.service.ProjectService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/projects")
//public class TestController {
//
//    ProjectService projectService;
//
//    public TestController(ProjectService projectService){
//        this.projectService=projectService;
//    }
//
//    @GetMapping()
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public ResponseEntity<?> userAccess() {
//        return new ResponseEntity<List<Project>>(projectService.getProjects(),null, HttpStatus.OK);
//    }
//
//    @PostMapping()
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    private ResponseEntity<?> createProjects(@RequestBody Project project){
//        project.setId(UUID.randomUUID().toString());
//        return new ResponseEntity<Project>(projectService.createProject(project),null, HttpStatus.OK);
//    }
//}