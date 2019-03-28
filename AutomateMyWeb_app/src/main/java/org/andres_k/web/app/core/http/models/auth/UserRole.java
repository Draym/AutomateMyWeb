package org.andres_k.web.app.core.http.models.auth;

public class UserRole {
    private Long id;
    private Long userId;
    private Role role;

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}