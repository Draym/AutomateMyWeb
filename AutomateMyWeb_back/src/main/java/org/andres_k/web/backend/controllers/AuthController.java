package org.andres_k.web.backend.controllers;

import org.andres_k.web.backend.models.auth.custom.AuthHandler;
import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.services.AuthService;
import org.andres_k.web.backend.services.TokenService;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody AuthHandler auth) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.authService.login(auth.getEmail(), auth.getPassword());
            response.addResult(this.tokenService.createToken(user, auth.getOrigin()));
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody User user) {
        HttpResponse response = new HttpResponse();

        try {
            this.userService.createUser(user);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
            ex.printStackTrace();
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/auth/validate", method = RequestMethod.GET)
    @ResponseBody
    public String validate(@RequestParam String identifier) {
        HttpResponse response = new HttpResponse();

        try {
            this.authService.validateAccount(identifier);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error validating the account:" + ex.toString());
        }
        return TJson.toString(response);
    }
}
