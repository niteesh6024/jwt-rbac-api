package com.coding_sphere.jwt_rbac_api.repoistory;

import com.coding_sphere.jwt_rbac_api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
