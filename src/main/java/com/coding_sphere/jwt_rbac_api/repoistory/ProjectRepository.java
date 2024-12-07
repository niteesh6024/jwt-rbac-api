package com.coding_sphere.jwt_rbac_api.repoistory;

import com.coding_sphere.jwt_rbac_api.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}

