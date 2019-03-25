package org.andres_k.web.backend.models.items.templates;

import org.andres_k.web.backend.models.UserProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Template")
@Table(name = "templates")
public class Template extends UserProperty<Template> {
    @OneToMany(
            mappedBy = "element",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TemplateElement> elements = new ArrayList<>();

    @OneToMany(
            mappedBy = "directive",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TemplateDirective> directives = new ArrayList<>();

    public List<TemplateElement> getElements() {
        return this.elements;
    }

    public List<TemplateDirective> getDirectives() {
        return this.directives;
    }

    public void setScriptIds(List<TemplateElement> elements) {
        this.elements = elements;
    }

    public void setDirectives(List<TemplateDirective> directives) {
        this.directives = directives;
    }

    @Override
    public void copy(Template object) {
        super.copy(object);
        // DO NOT IMPLEMENT template/update
        // DO template/element/add
        // DO template/directive/add
    }
}
