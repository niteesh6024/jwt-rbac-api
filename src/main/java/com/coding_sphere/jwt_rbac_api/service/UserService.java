package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.User;
import com.coding_sphere.jwt_rbac_api.payload.request.LoginRequest;
import com.coding_sphere.jwt_rbac_api.payload.request.SignupRequest;
import com.coding_sphere.jwt_rbac_api.payload.response.UserInfoResponse;

public interface UserService {

    User createUser(SignupRequest signupRequest) throws Exception;
    UserInfoResponse loginUser(LoginRequest loginRequest) throws Exception;

}
