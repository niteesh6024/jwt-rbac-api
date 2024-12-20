package com.coding_sphere.jwt_rbac_api.controller;

import com.coding_sphere.jwt_rbac_api.payload.request.LoginRequest;
import com.coding_sphere.jwt_rbac_api.payload.request.SignupRequest;
import com.coding_sphere.jwt_rbac_api.payload.response.MessageResponse;
import com.coding_sphere.jwt_rbac_api.payload.response.UserInfoResponse;
import com.coding_sphere.jwt_rbac_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Operation(summary = "User Login", description = "Authenticate a user and return a JWT token")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            UserInfoResponse userInfoResponse = userService.loginUser(loginRequest);
            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.SET_COOKIE,
                            userInfoResponse.getJwtKey())
                    .body(userInfoResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "User Registration", description = "Register a new user with a username and password")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try{
            userService.createUser(signUpRequest);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),null, HttpStatus.BAD_REQUEST);
        }
    }
}
