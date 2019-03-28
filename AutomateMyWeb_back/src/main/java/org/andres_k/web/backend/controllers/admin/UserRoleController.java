package org.andres_k.web.backend.controllers.admin;

import org.andres_k.web.backend.config.HttpResponse;
import org.andres_k.web.backend.config.Restricted;
import org.andres_k.web.backend.models.auth.ERoles;
import org.andres_k.web.backend.services.UserService;
import org.andres_k.web.backend.utils.tools.TJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Restricted(tokenRequired = false, roles = ERoles.ADMIN)
@Controller("AdminUserRoleController")
@RequestMapping(value = "/admin")
public class UserRoleController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/role/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUserRole(@RequestParam Long userId, @RequestParam Long roleId) {
        HttpResponse response = new HttpResponse();

        try {
            this.userService.deleteRole(userId, roleId);
            response.addResult(true);
        } catch (Exception ex) {
            response.addError("Error deleting the user:" + ex.toString());
        }
        return TJson.toString(response);
    }
}
