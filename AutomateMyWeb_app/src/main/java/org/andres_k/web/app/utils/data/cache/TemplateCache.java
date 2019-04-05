package org.andres_k.web.app.utils.data.cache;

import org.andres_k.web.app.core.http.models.items.templates.Template;

public class TemplateCache extends ACache<Template> {

    @Override
    protected int find(Template item) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i).getName().equals(item.getName())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void persist() {

    }
}
