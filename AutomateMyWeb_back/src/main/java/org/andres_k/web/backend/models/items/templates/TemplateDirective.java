package org.andres_k.web.backend.models.items.templates;

import org.andres_k.web.backend.models.items.jobs.Directive;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TemplateDirective")
@Table(name = "template_directives")
public class TemplateDirective {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directive_id")
    private Directive directive;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
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
