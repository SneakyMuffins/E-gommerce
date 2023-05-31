package com.egommerce.demo.interceptor;

import com.egommerce.demo.annotation.AdminOnly;
import com.egommerce.demo.model.User.User;
import com.egommerce.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminOnly interceptor
 */
@Component
public class AdminOnlyInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public AdminOnlyInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        AdminOnly adminOnly = ((HandlerMethod) handler).getMethodAnnotation(AdminOnly.class);

        if (adminOnly != null) {
            User authenticatedUser = userService.getAuthenticatedUser(request);
            if (authenticatedUser == null || !userService.isAdminUser(authenticatedUser)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Implement the postHandle method if needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        // Implement the afterCompletion method if needed
    }
}
