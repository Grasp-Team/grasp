package com.grasp.security;

import com.grasp.security.model.APIAuthenticationToken;
import com.grasp.security.model.UserAuthenticationRequest;
import com.grasp.security.model.UserAuthenticationResponse;
import com.grasp.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class GraspAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public GraspAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserAuthenticationResponse response = userService.authenticate(
                new UserAuthenticationRequest(authentication.getName(), (String) authentication.getCredentials()));

        System.out.println("firstName: " + response.getFirstName());
        System.out.println("lastName: " + response.getLastName());
        System.out.println("role: " + response.getUserRole());
        System.out.println("username: " + response.getUserName());

        if (response.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(new APIAuthenticationToken(response));

            return SecurityContextHolder.getContext().getAuthentication();
        }

        throw new AuthenticationServiceException("Username " + authentication.getName() + "could not be authenticated");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (authentication != null) && authentication == UsernamePasswordAuthenticationToken.class;
    }
}
