package org.andres_k.web.backend.services;

import org.andres_k.web.backend.models.auth.*;
import org.andres_k.web.backend.utils.managers.EmailManager;
import org.andres_k.web.backend.utils.managers.PasswordManager;
import org.andres_k.web.backend.utils.tools.TRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserActivationRepository userActivationRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user) throws Exception {
        if (this.userRepository.existsUserByEmail(user.getEmail()))
            throw new Exception("The email '" + user.getEmail() + "' is already used.");
        else if (this.userRepository.existsUserByPseudo(user.getPseudo()))
            throw new Exception("The pseudo '" + user.getPseudo() + "' is already used.");
        else {
            // update user
            user.setPassword(PasswordManager.hashPassword(user.getPassword()));
            user.setEnabled(0);
            user.setDate(new Date());
            User newUser = this.userRepository.save(user);

            // create user role USER
            UserRole userRole = new UserRole();
            userRole.setUserId(newUser.getId());
            Optional<Role> role = this.roleRepository.findById(ERoles.USER.get());
            if (!role.isPresent())
                throw new EntityNotFoundException("Cannot find the default user role.");
            userRole.setRole(role.get());
            this.userRoleRepository.save(userRole);

            // create UserActivation
            UserActivation userActivation = new UserActivation();
            userActivation.setDate(new Date());
            userActivation.setUserId(newUser.getId());
            userActivation.setIdentifier(TRandomString.get().generate());
            this.userActivationRepository.save(userActivation);

            // send verification email
            EmailManager.get().sendVerification(newUser, userActivation.getIdentifier());

            return newUser;
        }
    }

    public User updateUser(User newUser) throws Exception {
        if (newUser.getId() == null)
            throw new NullPointerException("The user's id is missing.");
        Optional<User> optUser = this.userRepository.findById(newUser.getId());
        User user;
        if (!optUser.isPresent())
            throw new EntityNotFoundException("No user found for the given id");
        else
            user = optUser.get();
        if (!user.getEmail().equals(newUser.getEmail()) && this.userRepository.existsUserByEmail(newUser.getEmail()))
            throw new Exception("The email '" + newUser.getEmail() + "' is already used.");
        else if (!user.getPseudo().equals(newUser.getPseudo()) && this.userRepository.existsUserByPseudo(newUser.getPseudo()))
            throw new Exception("The pseudo '" + newUser.getPseudo() + "' is already used.");
        else {
            user.copy(newUser);
            return this.userRepository.save(user);
        }
    }

    public void deleteUser(Long id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent())
            throw new EntityNotFoundException("Cannot find user [id=" + id + "]");
        this.userRepository.delete(user.get());
        this.userRoleRepository.deleteAllByUserId(id);
        this.userActivationRepository.deleteAllByUserId(id);
    }

    public void deleteRole(Long userId, Long roleId) {
        this.userRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
    }

}
