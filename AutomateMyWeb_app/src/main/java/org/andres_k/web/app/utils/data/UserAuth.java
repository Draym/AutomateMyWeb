package org.andres_k.web.app.utils.data;

import org.andres_k.web.app.core.http.models.auth.Token;
import org.andres_k.web.app.core.http.models.auth.User;
import org.andres_k.web.app.core.http.models.auth.custom.TokenResponse;
import org.andres_k.web.app.core.http.services.AuthService;
import org.andres_k.web.app.utils.exception.AppException;

public class UserAuth {
    public User user;
    public Token token;

    public void init() {
        this.setupFromFiles();
    }

    public void init(TokenResponse response) {
        this.user = response.getUser();
        this.token = response.getToken();
    }

    public void save() {
        try {
            Configs.get().addProperty(ETProperty.TOKEN, this.token.getValue());
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    private void setupFromFiles() {
        if (!Configs.get().hasProperty(ETProperty.TOKEN))
            return;
        String token = Configs.get().getProperty(ETProperty.TOKEN);

        try {
            TokenResponse response = AuthService.loginByToken(token);
            this.init(response);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    public boolean isValid() {
        return this.user != null && this.token != null;
    }

    /**
     * SINGLETON
     **/
    private static UserAuth instance;

    private UserAuth() {
    }

    public static UserAuth get() {
        if (instance == null) {
            instance = new UserAuth();
        }
        return instance;
    }
}
