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
    @Column(name = "template_id")
    private Long templateId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directive_id")
    private Directive directive;

    public Long getId() {
        return this.id;
    }


    public Directive getDirective() {
        return this.directive;
    }

    public Long getTemplateId() {
        return this.templateId;
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
