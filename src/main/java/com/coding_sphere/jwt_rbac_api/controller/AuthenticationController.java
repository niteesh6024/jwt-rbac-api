package com.coding_sphere.jwt_rbac_api.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.coding_sphere.jwt_rbac_api.entity.ERole;
import com.coding_sphere.jwt_rbac_api.entity.Project;
import com.coding_sphere.jwt_rbac_api.entity.Role;
import com.coding_sphere.jwt_rbac_api.entity.User;
import com.coding_sphere.jwt_rbac_api.payload.request.LoginRequest;
import com.coding_sphere.jwt_rbac_api.payload.request.SignupRequest;
import com.coding_sphere.jwt_rbac_api.payload.response.MessageResponse;
import com.coding_sphere.jwt_rbac_api.payload.response.UserInfoResponse;
import com.coding_sphere.jwt_rbac_api.repoistory.ProjectRepository;
import com.coding_sphere.jwt_rbac_api.repoistory.RoleRepository;
import com.coding_sphere.jwt_rbac_api.repoistory.UserRepository;
import com.coding_sphere.jwt_rbac_api.security.UserDetailsImpl;
import com.coding_sphere.jwt_rbac_api.security.jwt.JwtUtils;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found. niteesh"));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
//
//package com.coding_sphere.jwt_rbac_api.controller;
//
//import com.coding_sphere.jwt_rbac_api.entity.Project;
//import com.coding_sphere.jwt_rbac_api.repoistory.ProjectRepository;
//import com.coding_sphere.jwt_rbac_api.service.ProjectService;
//import com.coding_sphere.jwt_rbac_api.service.ProjectServiceImp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//        import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/projects")
//public class ProjectController {
//    @Autowired
//    ProjectRepository projectRepository;
//
//    @GetMapping()
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    private ResponseEntity<?> getprojects(){
//        System.out.println("niteesh");
//        return new ResponseEntity<List<Project>>(projectRepository.findAll(),null, HttpStatus.OK);
//    }
//
//    @PostMapping()
//    @PreAuthorize("hasRole('ADMIN')")
//    private ResponseEntity<?> createProjects(@RequestBody Project project){
//        project.setId(UUID.randomUUID().toString());
//        return new ResponseEntity<Project>(projectRepository.save(project),null, HttpStatus.OK);
//    }
//
//}
