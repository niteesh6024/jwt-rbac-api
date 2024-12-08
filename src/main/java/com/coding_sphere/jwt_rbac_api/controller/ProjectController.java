package com.coding_sphere.jwt_rbac_api.controller;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    private ResponseEntity<?> creatprojects(@RequestBody Project project) {
        try {
            return new ResponseEntity<Project>(projectService.createProject(project), null, HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<String>(exception.getMessage(),null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
//    private ResponseEntity<?> updateproject(@RequestParam(required = true) String projectId,
//                                            @RequestParam String name,
//                                            @RequestParam String description){
    private ResponseEntity<?> updateproject(@RequestBody Project project){
        try{
            projectService.updateProject(project);
            return new ResponseEntity<String>("Successfully updated",null,HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<String>(exception.getMessage(),null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping()
    private ResponseEntity<?> getProjectDetailsByProjectId(@RequestParam(required = true) String projectId){
        try{
            projectService.deleteProject(projectId);
            return new ResponseEntity<String>("Successfully deleted",null,HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity<String>(exception.getMessage(),null, HttpStatus.BAD_REQUEST);
        }
    }

}
