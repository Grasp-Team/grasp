package com.grasp.controller;

import com.grasp.model.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class UserAuthenticationController {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;

    @Autowired
    public UserAuthenticationController(AuthenticationManager authenticationManager,
                                        SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> authentication(@RequestBody AuthenticationDTO authenticationDTO,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {

        System.out.println("Authenticating : " + authenticationDTO.getUsername());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationDTO.getUsername(), authenticationDTO.getPassword());

        try {

            Authentication result = authenticationManager.authenticate(authenticationToken);

            if (result == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            System.out.println("Authenticating: " + result.getDetails());

            SecurityContextHolder.getContext().setAuthentication(result);
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);

            return new ResponseEntity<>((String) result.getDetails(), HttpStatus.OK);

        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
