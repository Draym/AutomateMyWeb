package org.andres_k.web.app.core.http.models.items.templates;

import org.andres_k.web.app.core.http.models.items.elements.WebElement;

public class TemplateElement {
    private Long id;
    private WebElement element;
    private Long templateId;

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

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof TemplateElement))return false;
        TemplateElement otherMyClass = (TemplateElement)other;

        return this.getElement().getName().equals(otherMyClass.element.getName());
    }
}