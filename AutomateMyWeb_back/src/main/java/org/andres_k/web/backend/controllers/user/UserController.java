package org.andres_k.web.backend.controllers.user;

import org.andres_k.web.backend.models.auth.Token;
import org.andres_k.web.backend.models.auth.TokenRepository;
import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.services.TokenService;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestParam String token, @RequestBody Long userId) {
        HttpResponse response = new HttpResponse();

        try {
            Token tokenValue = this.tokenService.getToken(token);
            this.userService.deleteUser(userId, tokenValue);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the user:" + ex.toString());
        }
        return TJson.toString(response);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestParam String token, @RequestBody User user) {
        HttpResponse response = new HttpResponse();

        try {
            Token tokenValue = this.tokenService.getToken(token);
            User newUser = this.userService.updateUser(user, tokenValue);
            response.addResult(newUser);
        } catch (Exception ex) {
            response.addError("Error updating the user:" + ex.toString());
        }
        return TJson.toString(response);
    }
}
