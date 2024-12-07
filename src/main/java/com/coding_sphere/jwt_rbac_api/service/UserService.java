package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.User;

public interface UserService {

    User createUser(User user) throws Exception;
}
