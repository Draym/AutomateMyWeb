package org.andres_k.web.backend.services;

import org.andres_k.web.backend.models.auth.*;
import org.andres_k.web.backend.utils.managers.PasswordManager;
import org.andres_k.web.backend.utils.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserActivationRepository userActivationRepository;
    @Autowired
    private TokenRepository tokenRepository;

    public User login(String email, String password) throws Exception {
        User user = this.userRepository.findByEmail(email);

        if (user == null)
            throw new EntityNotFoundException("Cannot find user [email=" + email + "]");
        PasswordManager.verifyPassword(password, user.getPassword());
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
        token.setValue(TokenManager.generate());
        token.setExpired(false);
        return this.tokenRepository.save(token);
    }

    public void validateAccount(String identifier) {
        Optional<UserActivation> userActivation = this.userActivationRepository.findByIdentifier(identifier);

        if (!userActivation.isPresent())
            throw new EntityNotFoundException("The identifier link {" + identifier + "} is invalid.");

        Optional<User> user = this.userRepository.findById(userActivation.get().getUserId());

        if (!user.isPresent())
            throw new EntityNotFoundException("No user has been found for the identifier link {" + identifier + "}.");

        user.get().setEnabled(1);
        this.userRepository.save(user.get());
    }
}
