package org.andres_k.web.app.core.http.services;

import org.andres_k.web.app.core.http.HttpResponse;
import org.andres_k.web.app.core.http.HttpUtils;
import org.andres_k.web.app.core.http.models.auth.User;
import org.andres_k.web.app.core.http.models.auth.custom.AuthHandler;
import org.andres_k.web.app.core.http.models.auth.custom.TokenResponse;
import org.andres_k.web.app.utils.data.UserAuth;
import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.exception.EError;
import org.andres_k.web.app.utils.tools.TJson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    public static void login(AuthHandler user, boolean save) throws AppException {
        user.setOrigin("app");
        HttpResponse response;
        try {
            response = HttpUtils.POST("auth/login", TJson.toString(user, false));
        } catch (IOException e) {
            throw new AppException(EError.HTTP_ERROR, e);
        }

        if (response.hasError)
            throw new AppException(EError.HTTP_ERROR, response.error, new Throwable());

        UserAuth.get().init((TokenResponse) response.result);
        if(save)
            UserAuth.get().save();
    }

    public static void register(User user) throws AppException {
        HttpResponse response;
        try {
            response = HttpUtils.POST("auth/register", TJson.toString(user, false));
        } catch (IOException e) {
            throw new AppException(EError.HTTP_ERROR, e);
        }

        if (response.hasError)
            throw new AppException(EError.HTTP_ERROR, response.error, new Throwable());
    }

    public static TokenResponse loginByToken(String token) throws AppException {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        HttpResponse response;
        try {
            response = HttpUtils.GET("auth/token/login", params);
        } catch (IOException e) {
            throw new AppException(EError.HTTP_ERROR, e);
        }

        if (response.hasError)
            throw new AppException(EError.HTTP_ERROR, response.error, new Throwable());

        return (TokenResponse) response.result;
    }
}
