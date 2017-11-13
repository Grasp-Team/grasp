package com.grasp.controller;

import com.grasp.exception.ControllerException;
import com.grasp.model.entity.User;
import com.grasp.model.dto.AuthenticationDTO;
import com.grasp.model.dto.JwtDTO;
import com.grasp.model.dto.UserDTO;
import com.grasp.model.util.EntityConverter;
import com.grasp.security.jwt.JWT;
import com.grasp.service.UserService;
import io.jsonwebtoken.Claims;
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
@RequestMapping("/authenticate")
public class UserAuthenticationController {

    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    private UserService userService;
    private EntityConverter entityConverter;

    @Autowired
    public UserAuthenticationController(AuthenticationManager authenticationManager,
                                        SecurityContextRepository securityContextRepository, UserService userService, EntityConverter entityConverter) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
        this.userService = userService;
        this.entityConverter = entityConverter;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> getUserByJWT(@RequestBody JwtDTO jwtDTO) {

        String token = jwtDTO.getApiToken();
        User user;

        try {
            Claims claims = JWT.parseJwt(token);

            user = userService.getByEmail(claims.getSubject());

            if (user == null) {
                throw new ControllerException(HttpStatus.NOT_FOUND,
                        "ERROR: Unable to find user: " + claims.getSubject());
            }

            // should also check if exists in cache - but it probably will since we're storing so many

        } catch (RuntimeException e) {
            throw new ControllerException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR during parsing of JWT");
        }

        return new ResponseEntity<>(entityConverter.convertToDTO(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<JwtDTO> authentication(@RequestBody AuthenticationDTO authenticationDTO,
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

            return new ResponseEntity<>(new JwtDTO((String) result.getDetails()), HttpStatus.OK);

        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
