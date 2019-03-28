package org.andres_k.web.backend.services;

import org.andres_k.web.backend.models.auth.ERoles;
import org.andres_k.web.backend.models.auth.User;
import org.andres_k.web.backend.models.auth.UserRole;
import org.andres_k.web.backend.models.auth.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public boolean isUserAllowed(User user, List<ERoles> allowed) {

        List<UserRole> userRoles = this.userRoleRepository.getAllByUserId(user.getId());

        for (ERoles role : allowed) {
            for (UserRole userRole : userRoles) {
                if (role.isEquals(userRole.getRole()))
                    return true;
            }
        }
        return false;
    }
}
