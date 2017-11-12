package com.grasp.security;

import com.grasp.security.jwt.JWT;
import com.grasp.security.model.APIAuthenticationToken;
import io.jsonwebtoken.Claims;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class EhcacheSecurityContextRepo implements SecurityContextRepository {

    private final static String API_TOKEN = "API-TOKEN";
    private CacheManager cacheManager = CacheManager.getInstance();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        if (containsContext(requestResponseHolder.getRequest())) {

            String token = extractToken(requestResponseHolder.getRequest());

            return (SecurityContext) getCache().get(token).getObjectValue();
        }

        return SecurityContextHolder.createEmptyContext();
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication instanceof APIAuthenticationToken) {
            String token = (String) authentication.getDetails();


            if (token != null) {
                System.out.println("Saving Token: " + token);
                getCache().put(new Element(token, context));
            }
        }
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return this.getCache().get(extractToken(request)) != null;
    }

    private Cache getCache() {
        return cacheManager.getCache("SSSC");
    }

    private String extractToken(HttpServletRequest request) {
        return request.getHeader(API_TOKEN);
    }
}
