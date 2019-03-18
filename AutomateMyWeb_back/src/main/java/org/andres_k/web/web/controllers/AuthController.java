package org.andres_k.web.web.controllers;

import org.andres_k.web.web.models.auth.User;
import org.andres_k.web.web.models.auth.UserRepository;
import org.andres_k.web.web.models.services.UserService;
import org.andres_k.web.web.utils.HttpResponse;
import org.andres_k.web.web.utils.PasswordStorage;
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


    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse createPerson(String email, String password) {
        HttpResponse response = new HttpResponse();

        try {
            User user = this.userRepository.findByEmail(email);

            if (user == null)
                throw new Exception("Cannot find user [email=" + email + "]");
            if (!PasswordStorage.verifyPassword(password, user.getPassword()))
                throw new Exception("The password is incorrect");
            response.addResult(user);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse createPerson(User user) {
        HttpResponse response = new HttpResponse();

        try {
            User newUser = this.userService.updateUser(user);
            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error creating the user:" + ex.toString());
        }
        return response;
    }
}
