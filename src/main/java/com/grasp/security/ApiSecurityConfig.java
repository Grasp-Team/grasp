package com.grasp.security;

import com.grasp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public ApiSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/user/authenticate", "/user/signup").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean("apiAuthenticationEntryPoint")
    public AuthenticationEntryPoint apiAuthenticaticationEntryPoint() throws Exception{
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