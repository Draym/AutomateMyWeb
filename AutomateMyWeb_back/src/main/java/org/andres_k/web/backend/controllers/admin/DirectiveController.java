package org.andres_k.web.backend.controllers.admin;

import org.andres_k.web.backend.config.Restricted;
import org.andres_k.web.backend.models.auth.ERoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Restricted(tokenRequired = false, roles = ERoles.ADMIN)
@Controller("AdminDirectiveController")
@RequestMapping(value = "/admin")
public class DirectiveController {
}
