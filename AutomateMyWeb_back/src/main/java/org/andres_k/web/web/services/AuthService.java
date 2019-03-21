package org.andres_k.web.web.services;

import org.andres_k.web.web.models.auth.*;
import org.andres_k.web.web.utils.PasswordStorage;
import org.andres_k.web.web.utils.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private TokenRepository tokenRepository;

    public User login(String email, String password) throws Exception {
        User user = this.userRepository.findByEmail(email);

        if (user == null)
            throw new EntityNotFoundException("Cannot find user [email=" + email + "]");
        if (!PasswordStorage.verifyPassword(password, user.getPassword()))
            throw new Exception("The password is incorrect");
        return user;
    }

    public User getUserByToken(String value) {
        Token token = this.retrieveToken(value);

        if (token == null)
            throw new EntityNotFoundException("The token {" + value + "} doesn't exist.");
        if (token.isExpired())
            throw new NullPointerException("The token {" + value + "} has expired.");
        return this.userRepository.findById(token.getUserId()).orElse(null);
    }

    public Token retrieveToken(String value) {
        return this.tokenRepository.getByValue(value);
    }

    public Token generateToken(User user) {
        Token token = new Token();

        token.setUserId(user.getId());
        token.setValue(RandomString.get().generate());
        token.setExpired(false);
        return this.tokenRepository.save(token);
    }
}
