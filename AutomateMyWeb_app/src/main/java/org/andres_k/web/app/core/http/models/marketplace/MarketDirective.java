package org.andres_k.web.app.core.http.models.marketplace;

import org.andres_k.web.app.core.http.models.UserProperty;
import org.andres_k.web.app.core.http.models.items.jobs.Directive;

public class MarketDirective extends UserProperty {
    private String description;
    private String imageURL;
    private Directive directive;
    private Long version;

    public String getDescription() {
        return this.description;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public Directive getDirective() {
        return this.directive;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
