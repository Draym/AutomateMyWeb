package org.andres_k.web.backend.controllers;

import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.services.AuthService;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.config.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse login(@RequestBody String email, @RequestBody String password) {
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
    public HttpResponse register(@RequestBody User user) {
        HttpResponse response = new HttpResponse();

        try {
            User newUser = this.userService.createUser(user);

            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/auth/validate", method = RequestMethod.GET)
    @ResponseBody
    public HttpResponse validate(@RequestParam String identifier) {
        HttpResponse response = new HttpResponse();

        try {
            this.authService.validateAccount(identifier);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error validating the account:" + ex.toString());
        }
        return response;
    }
}
