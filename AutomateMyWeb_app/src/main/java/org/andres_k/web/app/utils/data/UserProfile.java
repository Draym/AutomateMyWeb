package org.andres_k.web.app.utils.data;

import org.andres_k.web.app.core.http.models.auth.Token;
import org.andres_k.web.app.core.http.models.auth.User;
import org.andres_k.web.app.core.http.models.auth.custom.TokenResponse;
import org.andres_k.web.app.core.http.services.AuthService;

public class UserProfile {
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
        Configs.get().addProperty(EProperty.TOKEN, this.token.getValue());
    }

    private void setupFromFiles() {
        if (!Configs.get().hasProperty(EProperty.TOKEN))
            return;
        String token = Configs.get().getProperty(EProperty.TOKEN);

        try {
            TokenResponse response = AuthService.loginByToken(token);
            this.init(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValid() {
        return this.user != null && this.token != null;
    }

    /**
     * SINGLETON
     **/
    private static UserProfile instance;

    private UserProfile() {
    }

    public static UserProfile get() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }
}
