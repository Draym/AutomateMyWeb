package org.andres_k.web.app.core.http.models.marketplace;

import org.andres_k.web.app.core.http.models.UserProperty;
import org.andres_k.web.app.core.http.models.items.jobs.Script;

public class MarketScript extends UserProperty {
    private String description;
    private String imageURL;
    private Script script;
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
