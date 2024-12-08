package com.coding_sphere.jwt_rbac_api.service;

import com.coding_sphere.jwt_rbac_api.entity.ERole;
import com.coding_sphere.jwt_rbac_api.entity.Role;
import com.coding_sphere.jwt_rbac_api.entity.User;
import com.coding_sphere.jwt_rbac_api.payload.request.LoginRequest;
import com.coding_sphere.jwt_rbac_api.payload.request.SignupRequest;
import com.coding_sphere.jwt_rbac_api.payload.response.UserInfoResponse;
import com.coding_sphere.jwt_rbac_api.repoistory.RoleRepository;
import com.coding_sphere.jwt_rbac_api.repoistory.UserRepository;
import com.coding_sphere.jwt_rbac_api.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coding_sphere.jwt_rbac_api.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{
        @Autowired
        private UserRepository userRepository;
        @Autowired
        PasswordEncoder encoder;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        JwtUtils jwtUtils;

        public User createUser(SignupRequest signUpRequest) throws Exception{

            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                throw new Exception("Error: Username is already taken!");
            }

            if (userRepository.existsByEmail(signUpRequest.getEmail())) {
                throw new Exception("Error: Email is already in use!");
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
            return userRepository.save(user);
        }

        public UserInfoResponse loginUser(LoginRequest loginRequest) throws Exception {
            try {
                // Authenticate the user
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Generate JWT token
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

                ResponseCookie jwtToken = jwtUtils.generateJwtCookie(userDetails);

                // Extract user details and roles
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .collect(Collectors.toList());

                // Return user info response
                return new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles,
                        jwtToken.toString());
            } catch (Exception e) {
                throw new Exception("Invalid username or password", e);
            }
        }
}
