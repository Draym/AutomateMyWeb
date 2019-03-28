package org.andres_k.web.app.core.http.models.items.templates;

import org.andres_k.web.app.core.http.models.UserProperty;

import java.util.ArrayList;
import java.util.List;

public class Template extends UserProperty<Template> {
    private List<TemplateElement> elements = new ArrayList<>();
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
