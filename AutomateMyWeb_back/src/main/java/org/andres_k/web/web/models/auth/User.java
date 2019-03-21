package org.andres_k.web.web.models.auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name="enabled")
    private int enabled;
    @NotNull
    @Column(name="email")
    private String email;
    @NotNull
    @Column(name="pseudo")
    private String pseudo;
    @NotNull
    @Column(name="password")
    private String password;

    public Long getId() {
        return this.id;
    }

    public int getEnabled() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(int enabled) {
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
}
