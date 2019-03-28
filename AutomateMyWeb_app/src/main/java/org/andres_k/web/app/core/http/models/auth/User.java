package org.andres_k.web.app.core.http.models.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {
    private Long id;
    private Integer enabled;
    private String email;
    private String pseudo;
    private String password;
    private Date date;

    public Long getId() {
        return this.id;
    }

    public Integer getEnabled() {
        return this.enabled;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getDate() {
        return this.date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
