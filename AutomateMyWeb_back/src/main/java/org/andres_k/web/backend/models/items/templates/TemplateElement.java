package org.andres_k.web.backend.models.items.templates;

import org.andres_k.web.backend.models.items.elements.WebElement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "TemplateElement")
@Table(name = "template_elements")
public class TemplateElement {
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
    @JoinColumn(name = "element_id")
    private WebElement element;


    public Long getId() {
        return this.id;
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public WebElement getElement() {
        return this.element;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
}