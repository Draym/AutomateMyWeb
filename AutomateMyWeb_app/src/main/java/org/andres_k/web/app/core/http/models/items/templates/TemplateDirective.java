package org.andres_k.web.app.core.http.models.items.templates;

import org.andres_k.web.app.core.http.models.items.jobs.Directive;

public class TemplateDirective {
    private Long id;
    private Directive directive;
    private Template template;

    public Long getId() {
        return this.id;
    }

    public Template getTemplate() {
        return this.template;
    }

    public Directive getDirective() {
        return this.directive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }
}
