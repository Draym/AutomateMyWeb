package org.andres_k.web.backend.controllers;

import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.utils.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteUser(@RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            this.userService.deleteUser(id);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the user:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @ResponseBody
    public HttpResponse update(@RequestBody User user) {
        HttpResponse response = new HttpResponse();

        try {
            User newUser = this.userService.updateUser(user);
            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error updating the user:" + ex.toString());
        }
        return response;
    }

    @RequestMapping(value = "/user/role/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public HttpResponse deleteUserRole(@RequestBody Long id) {
        HttpResponse response = new HttpResponse();

        try {
            this.userService.deleteUser(id);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the user:" + ex.toString());
        }
        return response;
    }
}
