package org.andres_k.web.backend.models.items.jobs;

import org.andres_k.web.backend.models.UserProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Directive")
@Table(name = "job_directives")
public class Directive extends UserProperty<Directive> {
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

    @Override
    public void copy(Directive object) {
        super.copy(object);
        if (object.value != null)
            this.setValue(object.value);
        if (object.description != null)
            this.setDescription(object.description);
    }
}
