package org.andres_k.web.web.models.items.templates;

import org.andres_k.web.web.models.items.elements.WebElement;
import org.andres_k.web.web.models.items.jobs.Script;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TemplateElement")
@Table(name = "template_scripts")
public class TemplateElement {
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "element_id")
    private WebElement element;

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

    public WebElement getElement() {
        return this.element;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}