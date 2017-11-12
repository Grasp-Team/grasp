package com.grasp.security.model;

import com.grasp.security.jwt.JWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class APIAuthenticationToken implements CredentialsContainer, Authentication  {

    private static final String TOKEN_ISSUER = "Grasp";
    private static final long DEFAULT_TOKEN_EXPIRATION = 60000;

    private Set<GrantedAuthority> authorities = new HashSet<>();
    private String name;
    private String principal;
    private String jwt;
    private UserRole userRole;

    public APIAuthenticationToken(UserAuthenticationResponse user) {
        this.authorities.add(new SimpleGrantedAuthority(user.getUserRole().toString()));
        this.name = user.getFirstName() + " " + user.getLastName();
        this.principal = user.getUserName();
        this.jwt = JWT.createJwt(user.getUserName(), TOKEN_ISSUER, user.getUserRole().toString(), DEFAULT_TOKEN_EXPIRATION);
        this.userRole = user.getUserRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return this.userRole.toString();
    }

    @Override
    public Object getDetails() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void eraseCredentials() {
    }
}
