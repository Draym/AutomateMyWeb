package org.andres_k.web.app.core.http.models.items.templates;

import org.andres_k.web.app.core.http.models.items.jobs.Directive;

public class TemplateDirective {
    private Long id;
    private Directive directive;
    private Long templateId;

    public Long getId() {
        return this.id;
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public Directive getDirective() {
        return this.directive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }
}
