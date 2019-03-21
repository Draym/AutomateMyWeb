package org.andres_k.web.web.controllers;

import org.andres_k.web.web.models.auth.User;
import org.andres_k.web.web.models.auth.UserRepository;
import org.andres_k.web.web.services.AuthService;
import org.andres_k.web.web.services.UserService;
import org.andres_k.web.web.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse login(String email, String password) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.login(email, password);
            response.addResult(user);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse register(User user) {
        HttpResponse response = new HttpResponse();

        try {
            User newUser = this.userService.createUser(user);

            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
        }
        return response;
    }
}
