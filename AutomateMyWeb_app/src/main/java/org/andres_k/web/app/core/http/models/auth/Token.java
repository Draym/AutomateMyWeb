package org.andres_k.web.app.core.http.models.auth;

import java.util.Date;

public class Token {
    private Long id;
    private Long userId;
    private boolean expired;
    private String value;
    private Date date;
    private String origin;

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public boolean isExpired() {
        return this.expired;
    }

    public String getValue() {
        return value;
    }

    public Date getDate() {
        return this.date;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
