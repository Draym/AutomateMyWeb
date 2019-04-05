package org.andres_k.web.app.utils.data.cache;

import org.andres_k.web.app.core.http.models.items.jobs.Script;

public class ScriptCache extends ACache<Script> {
    @Override
    protected int find(Script item) {
        return 0;
    }

    @Override
    protected void persist() {

    }
}
