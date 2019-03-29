package org.andres_k.web.backend.models.auth.custom;

import org.andres_k.web.backend.models.auth.Token;
import org.andres_k.web.backend.models.auth.User;

public class TokenResponse {
    private Token token;
    private User user;

    public Token getToken() {
        return this.token;
    }

    public User getUser() {
        return this.user;
    }

    public void setToken(Token token) {
        this.token = token;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
