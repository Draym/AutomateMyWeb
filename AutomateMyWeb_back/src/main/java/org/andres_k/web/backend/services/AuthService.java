package org.andres_k.web.backend.services;

import org.andres_k.web.backend.models.auth.*;
import org.andres_k.web.backend.models.auth.custom.TokenResponse;
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
        if (user.getEnabled() == 0)
            throw new SecurityException("Please verify your email address.");
        if (user.getEnabled() == -1)
            throw new SecurityException("Your account has been ban.");
        return user;
    }

    public TokenResponse loginByToken(String token) throws Exception {
        TokenResponse result = new TokenResponse();

        Optional<Token> optToken = this.tokenRepository.findByValue(token);

        if (!optToken.isPresent())
            throw new EntityNotFoundException("The token {" + token + "} has not been found.");
        Optional<User> optUser = this.userRepository.findById(optToken.get().getUserId());
        if (!optUser.isPresent())
            throw new EntityNotFoundException("The user {" + optToken.get().getUserId() + "} has not been found.");
        result.setUser(optUser.get());
        result.setToken(optToken.get());
        return result;
    }

    public void validateAccount(String identifier) {
        Optional<UserActivation> optUserActivation = this.userActivationRepository.findByIdentifier(identifier);

        if (!optUserActivation.isPresent())
            throw new EntityNotFoundException("The identifier link {" + identifier + "} is invalid.");

        Optional<User> optUser = this.userRepository.findById(optUserActivation.get().getUserId());

        if (!optUser.isPresent())
            throw new EntityNotFoundException("No user has been found for the identifier link {" + identifier + "}.");

        optUser.get().setEnabled(1);
        this.userRepository.save(optUser.get());
    }
}
