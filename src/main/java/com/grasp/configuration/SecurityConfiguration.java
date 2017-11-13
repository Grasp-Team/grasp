package com.grasp.configuration;

import com.grasp.security.APIAuthenticaticationEntryPoint;
import com.grasp.security.EhcacheSecurityContextRepo;
import com.grasp.security.GraspAuthenticationProvider;
import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class SecurityConfiguration {

    private UserService userService;

    @Autowired
    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }


    @Bean("apiAuthenticationEntryPoint")
    public AuthenticationEntryPoint apiAuthenticaticationEntryPoint() throws Exception {
        return new APIAuthenticaticationEntryPoint();
    }

    @Bean("apiSecurityContextRepository")
    public SecurityContextRepository ehcacheSecurityContextRepo() throws Exception {
        return new EhcacheSecurityContextRepo();
    }

    @Bean("authenticationProvider")
    public AuthenticationProvider authenticationProvider() throws Exception {
        return new GraspAuthenticationProvider(userService);
    }

}
