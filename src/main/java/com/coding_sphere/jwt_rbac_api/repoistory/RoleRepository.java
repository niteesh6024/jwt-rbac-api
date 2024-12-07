package com.coding_sphere.jwt_rbac_api.repoistory;

import com.coding_sphere.jwt_rbac_api.entity.ERole;
import com.coding_sphere.jwt_rbac_api.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
