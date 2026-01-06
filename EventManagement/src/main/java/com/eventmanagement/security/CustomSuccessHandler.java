package com.eventmanagement.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {

        String role = AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                                     .iterator().next();

        switch (role) {
            case "ADMIN":
                response.sendRedirect("/admin/dashboard");
                break;

            case "COORDINATOR":
                response.sendRedirect("/coordinator/dashboard");
                break;

            case "STUDENT":
                response.sendRedirect("/student/dashboard");
                break;

            default:
                response.sendRedirect("/");
        }
    }
}
