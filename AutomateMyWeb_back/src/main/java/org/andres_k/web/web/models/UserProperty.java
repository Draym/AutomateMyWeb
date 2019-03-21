package org.andres_k.web.web.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "UserProperty")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserProperty {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "user_id")
    private Long userId;
    @NotNull
    @Column(name = "owner_id")
    private Long ownerId;
    @NotNull
    @Column(name = "name")
    private String name;

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
}
