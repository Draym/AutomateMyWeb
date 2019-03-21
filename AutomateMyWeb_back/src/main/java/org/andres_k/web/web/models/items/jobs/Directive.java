package org.andres_k.web.web.models.items.jobs;

import org.andres_k.web.web.models.UserProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Directive")
@Table(name = "directives")
public class Directive extends UserProperty {
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "value")
    private String value;

    public String getDescription() {
        return this.description;
    }
    public String getValue() {
        return this.value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
