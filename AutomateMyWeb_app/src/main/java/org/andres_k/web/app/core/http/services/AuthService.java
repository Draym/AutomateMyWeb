package org.andres_k.web.app.core.http.services;

import org.andres_k.web.app.core.http.HttpResponse;
import org.andres_k.web.app.core.http.HttpUtils;
import org.andres_k.web.app.core.http.models.auth.User;
import org.andres_k.web.app.core.http.models.auth.custom.AuthHandler;
import org.andres_k.web.app.core.http.models.auth.custom.TokenResponse;
import org.andres_k.web.app.utils.data.UserProfile;
import org.andres_k.web.app.utils.tools.TJson;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    public static void login(AuthHandler user, boolean save) throws Exception {
        user.setOrigin("app");
        HttpResponse response = HttpUtils.POST("auth/login", TJson.toString(user, false));

        if (response.hasError)
            throw new Exception(response.error);

        UserProfile.get().init((TokenResponse) response.result);
        if(save)
            UserProfile.get().save();
    }

    public static void register(User user) throws Exception {
        HttpResponse response = HttpUtils.POST("auth/register", TJson.toString(user, false));

        if (response.hasError)
            throw new Exception(response.error);
    }

    public static TokenResponse loginByToken(String token) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        HttpResponse response = HttpUtils.GET("auth/token/login", params);

        if (response.hasError)
            throw new Exception(response.error);

        return (TokenResponse) response.result;
    }
}
