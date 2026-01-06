package com.eventmanagement.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
	String email = SecurityUtil.getLoggedInUserEmail();

    private SecurityUtil() {
        // private constructor to prevent object creation
    }

    public static String getLoggedInUserEmail() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public static String getLoggedInUserRole() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();
    }
}
