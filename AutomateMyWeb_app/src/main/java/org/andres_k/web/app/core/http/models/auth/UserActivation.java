package org.andres_k.web.app.core.http.models.auth;

import java.util.Date;

public class UserActivation {
    private Long id;
    private Long userId;
    private String identifier;
    private Date date;


    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Date getDate() {
        return this.date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
