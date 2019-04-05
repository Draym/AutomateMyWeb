package org.andres_k.web.app.utils.data.cache;

import org.andres_k.web.app.core.http.models.items.jobs.Directive;

public class DirectiveCache extends ACache<Directive> {
    @Override
    protected int find(Directive item) {
        return 0;
    }

    @Override
    protected void persist() {

    }
}
