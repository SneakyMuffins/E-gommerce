package com.egommerce.demo.interceptor;

import com.egommerce.demo.annotation.RequireAuthorization;
import com.egommerce.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Authorization interceptor
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public AuthorizationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod method) {
            RequireAuthorization requireAuthorization = method.getMethodAnnotation(RequireAuthorization.class);

            if (requireAuthorization != null) {
                String token = request.getHeader("Authorization");

                try {
                    if (!userService.authorizeUserAccessFromToken(token)) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    return false;
                }
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
