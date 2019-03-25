package org.andres_k.web.backend.models.items.elements;

import org.andres_k.web.backend.models.UserProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "WebElement")
@Table(name = "job_elements")
public class WebElement extends UserProperty<WebElement> {

    @NotNull
    @Column(name = "css_selector")
    private String cssSelector;

    public String getCssSelector() {
        return this.cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }

    @Override
    public void copy(WebElement object) {
        super.copy(object);
        if (object.cssSelector != null)
            this.setCssSelector(object.cssSelector);
    }
}
