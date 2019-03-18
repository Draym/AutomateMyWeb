package org.andres_k.web.web.models.services;

import javassist.NotFoundException;
import org.andres_k.web.web.models.auth.User;
import org.andres_k.web.web.models.auth.UserRepository;
import org.andres_k.web.web.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User updateUser(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new Exception("The email '" + user.getEmail() + "' is already used.");
        else if (userRepository.findByPseudo(user.getPseudo()) != null)
            throw new Exception("The pseudo '" + user.getPseudo() + "' is already used.");
        else {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public void deleteUser(Long id) throws Exception {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent())
            throw new NotFoundException("Cannot find user [id=" + id + "]");
        this.userRepository.delete(user.get());
    }

}
