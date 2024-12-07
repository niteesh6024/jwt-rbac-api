package com.coding_sphere.jwt_rbac_api.payload.response;

import java.util.List;

public class UserInfoResponse {
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private String jwtKey;

    public UserInfoResponse(String id, String username, String email, List<String> roles, String jwtKey) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.jwtKey = jwtKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }
}
