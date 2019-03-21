package org.andres_k.web.web.models.items.templates;

import org.andres_k.web.web.models.UserProperty;
import org.andres_k.web.web.models.items.jobs.Directive;
import org.andres_k.web.web.models.items.elements.WebElement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Template")
@Table(name = "templates")
public class Template extends UserProperty {
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
}
