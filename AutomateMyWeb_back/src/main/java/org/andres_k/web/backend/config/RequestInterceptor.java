package org.andres_k.web.backend.config;

import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.models.auth.UserRole;
import org.andres_k.web.backend.models.auth.UserRoleRepository;
import org.andres_k.web.backend.services.AuthService;
import org.andres_k.web.backend.services.TokenService;
import org.andres_k.web.backend.services.UserRoleService;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.utils.managers.EndpointManager;
import org.andres_k.web.backend.utils.tools.Console;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    AuthService authService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> parameters = request.getParameterMap();
        String method = request.getMethod();
        String endpoint = request.getRequestURI();

        Console.log("api requested: " + endpoint);
        Console.log("method: " + method);
        Console.log("params: " + TJson.toString(parameters, false));

        Restricted restriction = EndpointManager.getRestriction(endpoint);

        if (restriction == null || !restriction.tokenRequired())
            return true;
        if (!parameters.containsKey("token") || !(parameters.get("token").length > 0))
            return false;

        String token = parameters.get("token")[0];
        if (!this.tokenService.verifyValidity(token))
            return false;

        User user = this.userService.getUserByToken(token);

        return this.userRoleService.isUserAllowed(user, Arrays.asList(restriction.roles()));
    }
}