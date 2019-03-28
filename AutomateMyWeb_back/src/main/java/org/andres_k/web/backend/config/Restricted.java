package org.andres_k.web.backend.config;

import org.andres_k.web.backend.models.auth.ERoles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Restricted {
    public boolean tokenRequired() default true;
    ERoles[] roles() default ERoles.USER;
}
