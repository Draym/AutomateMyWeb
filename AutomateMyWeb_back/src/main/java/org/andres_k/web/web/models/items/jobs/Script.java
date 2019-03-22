package org.andres_k.web.web.models.items.jobs;

import org.andres_k.web.web.models.UserProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Script")
@Table(name = "job_scripts")
public class Script extends UserProperty<Script> {
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
    public void copy(Script object) {
        super.copy(object);
        if (object.value != null)
            this.setValue(object.value);
        if (object.description != null)
            this.setDescription(object.description);
    }
}
