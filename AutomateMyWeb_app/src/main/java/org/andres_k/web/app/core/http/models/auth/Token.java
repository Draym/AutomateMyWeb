package org.andres_k.web.app.core.http.models.auth;

public class Token {
    private Long id;
    private Long userId;
    private boolean expired;
    private String value;

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
}
