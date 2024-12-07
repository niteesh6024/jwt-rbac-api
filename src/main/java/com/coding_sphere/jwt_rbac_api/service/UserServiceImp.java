package com.coding_sphere.jwt_rbac_api.service;


import com.coding_sphere.jwt_rbac_api.entity.User;
import com.coding_sphere.jwt_rbac_api.repoistory.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

        private UserRepository userRepository;

        public UserServiceImp(UserRepository userRepository){
            this.userRepository = userRepository;
        }

        public User createUser(User user) throws Exception{
            return userRepository.save(user);
        }
}
