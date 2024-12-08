package com.coding_sphere.jwt_rbac_api.security;

import com.coding_sphere.jwt_rbac_api.entity.ERole;
import com.coding_sphere.jwt_rbac_api.entity.Role;
import com.coding_sphere.jwt_rbac_api.repoistory.RoleRepository;
import com.coding_sphere.jwt_rbac_api.security.jwt.AuthEntryPointJwt;
import com.coding_sphere.jwt_rbac_api.security.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Optional;

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth ->
          auth.requestMatchers("/api/auth/**","/v3/api-docs/**", "/swagger-ui.html","/swagger-ui/**").permitAll()
                  .requestMatchers(HttpMethod.GET, "/api/project/**").hasAnyRole("USER", "ADMIN")
                  .requestMatchers(HttpMethod.POST, "/api/project/**").hasRole("ADMIN")
                  .requestMatchers(HttpMethod.PUT, "/api/project/**").hasRole("ADMIN")
                  .requestMatchers(HttpMethod.DELETE, "/api/project/**").hasRole("ADMIN")
              .anyRequest().authenticated()
        );

    http.authenticationProvider(authenticationProvider());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

    @Bean
    CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {
            for (ERole roleName : ERole.values()) {
                Optional<Role> existingRole = roleRepository.findByName(roleName);
                if (existingRole.isEmpty()) {
                    Role role = new Role(roleName);
                    roleRepository.save(role);
                }
            }
        };
    }
}
