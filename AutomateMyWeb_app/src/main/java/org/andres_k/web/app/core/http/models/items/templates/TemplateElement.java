package org.andres_k.web.app.core.http.models.items.templates;

import org.andres_k.web.app.core.http.models.items.elements.WebElement;

public class TemplateElement {
    private Long id;
    private WebElement element;
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