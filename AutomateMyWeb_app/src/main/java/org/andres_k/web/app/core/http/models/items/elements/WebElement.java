package org.andres_k.web.app.core.http.models.items.elements;

import org.andres_k.web.app.core.http.models.UserProperty;

public class WebElement extends UserProperty {

    private String cssSelector;

    public String getCssSelector() {
        return this.cssSelector;
    }

    public void setCssSelector(String cssSelector) {
        this.cssSelector = cssSelector;
    }
}
