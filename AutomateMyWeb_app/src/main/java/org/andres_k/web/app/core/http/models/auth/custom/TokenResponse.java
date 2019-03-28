package org.andres_k.web.app.core.http.models.auth.custom;


import org.andres_k.web.app.core.http.models.auth.User;

public class TokenResponse {
    private String token;
    private User user;

    public String getToken() {
        return this.token;
    }

    public User getUser() {
        return this.user;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
