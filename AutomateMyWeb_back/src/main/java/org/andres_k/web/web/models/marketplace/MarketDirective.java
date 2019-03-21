package org.andres_k.web.web.models.marketplace;


import org.andres_k.web.web.models.UserProperty;
import org.andres_k.web.web.models.items.jobs.Directive;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "MarketDirective")
@Table(name = "market_directives")
public class MarketDirective extends UserProperty {
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "image_url")
    private String imageURL;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "directive_id")
    private Directive directive;
    @NotNull
    @Column(name = "version")
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
