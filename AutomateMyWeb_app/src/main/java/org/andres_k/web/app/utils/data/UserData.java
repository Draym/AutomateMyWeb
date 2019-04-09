package org.andres_k.web.app.utils.data;

import org.andres_k.web.app.core.http.HttpUtils;
import org.andres_k.web.app.core.http.models.items.jobs.Directive;
import org.andres_k.web.app.core.http.models.items.jobs.Script;
import org.andres_k.web.app.core.http.models.items.templates.Template;
import org.andres_k.web.app.utils.data.cache.CacheManager;
import org.andres_k.web.app.utils.tools.TJson;

import java.io.IOException;

public class UserData {
    public boolean isOffline;
    private CacheManager<Template> templates;
    private CacheManager<Directive> directives;
    private CacheManager<Script> scripts;


    public void saveTemplate(Template data) {
        this.templates.save(data);
        this.push("", data);
    }

    public void saveDirective(Directive data) {
        this.directives.save(data);
        this.push("", data);
    }

    public void saveScript(Script data) {
        this.scripts.save(data);
        this.push("", data);
    }

    private void push(String endpoint, Object data) {
        if (!this.isOffline) {
            try {
                HttpUtils.POST("/" + endpoint, TJson.toString(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * SINGLETON
     **/
    private static UserData instance;

    private UserData() {
        this.isOffline = false;
    }

    public static UserData get() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
}
