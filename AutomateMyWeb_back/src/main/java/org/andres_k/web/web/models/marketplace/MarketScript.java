package org.andres_k.web.web.models.marketplace;


import org.andres_k.web.web.models.UserProperty;
import org.andres_k.web.web.models.items.jobs.Script;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "MarketScript")
@Table(name = "market_scripts")
public class MarketScript extends UserProperty {
    @NotNull
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "image_url")
    private String imageURL;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "script_id")
    private Script script;
    @NotNull
    @Column(name = "version")
    private Long version;

    public String getDescription() {
        return this.description;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public Script getScript() {
        return this.script;
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

    public void setScript(Script script) {
        this.script = script;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
