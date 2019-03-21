package org.andres_k.web.web.services;

import org.andres_k.web.web.models.auth.*;
import org.andres_k.web.web.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User createUser(User user) throws Exception {
        if (this.userRepository.findByEmail(user.getEmail()) != null)
            throw new Exception("The email '" + user.getEmail() + "' is already used.");
        else if (this.userRepository.findByPseudo(user.getPseudo()) != null)
            throw new Exception("The pseudo '" + user.getPseudo() + "' is already used.");
        else {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            user.setEnabled(0);
            UserRole role = new UserRole();
            role.setUserId(user.getId());
            role.setRole(this.roleRepository.getOne(0L));
            this.userRoleRepository.save(role);
            return this.userRepository.save(user);
        }
    }

    public void deleteUser(Long id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent())
            throw new EntityNotFoundException("Cannot find user [id=" + id + "]");
        this.userRepository.delete(user.get());
        this.userRoleRepository.deleteAllByUserId(id);
    }

}
