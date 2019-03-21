package org.andres_k.web.web.models.auth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Token")
@Table(name = "tokens")
public class Token {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name="user_id")
    private Long userId;
    @NotNull
    @Column(name="expired")
    private boolean expired;
    @NotNull
    @Column(name="value")
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
