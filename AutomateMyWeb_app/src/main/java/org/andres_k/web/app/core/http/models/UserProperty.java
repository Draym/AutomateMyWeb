package org.andres_k.web.app.core.http.models;

public abstract class UserProperty<T extends UserProperty> {
    protected Long id;
    protected Long userId;
    protected Long ownerId;
    protected String name;

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof UserProperty))return false;
        UserProperty otherMyClass = (UserProperty)other;

        return this.getName().equals(otherMyClass.getName());
    }
}
