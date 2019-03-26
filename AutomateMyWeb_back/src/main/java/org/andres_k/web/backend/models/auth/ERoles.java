package org.andres_k.web.backend.models.auth;

public enum ERoles {
    USER(1L),
    DEV(2L),
    ADMIN(3L);

    private Long value;

    ERoles(Long value) {
        this.value = value;
    }

    public Long get(){
        return this.value;
    }
}
