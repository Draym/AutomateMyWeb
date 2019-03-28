package org.andres_k.web.app.core.http.models.items.jobs;

import org.andres_k.web.app.core.http.models.UserProperty;

public class Directive extends UserProperty<Directive> {
    private String description;
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
