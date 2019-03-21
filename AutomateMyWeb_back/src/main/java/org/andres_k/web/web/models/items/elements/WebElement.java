package org.andres_k.web.web.models.items.elements;

import org.andres_k.web.web.models.UserProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "WebElement")
@Table(name = "web_elements")
public class WebElement extends UserProperty {

    @NotNull
    @Column(name = "css_selector")
    private String cssSelector;

    public String getCssSelector() {
        return this.cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }
}
