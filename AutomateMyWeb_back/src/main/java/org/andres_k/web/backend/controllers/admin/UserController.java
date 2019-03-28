package org.andres_k.web.backend.controllers.admin;

import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.config.Restricted;
import org.andres_k.web.backend.models.auth.ERoles;
import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Restricted(tokenRequired = false, roles = ERoles.ADMIN)
@Controller("AdminUserController")
@RequestMapping(value = "/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestBody Long userId) {
        HttpResponse response = new HttpResponse();

        try {
            this.userService.deleteUser(userId);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the user:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody User user) {
        HttpResponse response = new HttpResponse();

        try {
            User newUser = this.userService.updateUser(user);
            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error updating the user:" + ex.toString());
        }
        return TJson.toString(response);
    }
}
