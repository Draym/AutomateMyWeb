package org.andres_k.web.app.core.http.models.auth;

public class Role {
    private Long id;
    private String value;

    public Long getId() {
        return this.id;
    }

    public String getValue() {
        return this.value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
