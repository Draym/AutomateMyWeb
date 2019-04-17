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

    public enum DataType {
        TEMPLATE(""),
        DIRECTIVE(""),
        SCRIPT("");

        public String endpoint;

        DataType(String endpoint){
            this.endpoint = endpoint;
        }
    }

    public void save(Template data) {
        this.templates.save(data);
    }

    public void save(Directive data) {
        this.directives.save(data);
    }

    public void save(Script data) {
        this.scripts.save(data);
    }

    public void push(DataType type) {
        if (!this.isOffline) {
            try {
                String json;

                if (type == DataType.TEMPLATE) {
                    json = TJson.toString(this.templates.getChanges());
                }
                else if (type == DataType.DIRECTIVE) {
                    json = TJson.toString(this.directives.getChanges());
                }
                else if (type == DataType.SCRIPT) {
                    json = TJson.toString(this.scripts.getChanges());
                } else
                    return;

                HttpUtils.POST(type.endpoint, json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CacheManager<Template> getTemplates() {
        return this.templates;
    }
    public CacheManager<Directive> getDirectives() {
        return this.directives;
    }
    public CacheManager<Script> getScripts() {
        return this.scripts;
    }


    /**
     * SINGLETON
     **/
    private static UserData instance;

    private UserData() {
        this.isOffline = false;
        this.templates = new CacheManager<>(ECProperty.CACHE_TEMPLATE, Template.class);
        this.directives = new CacheManager<>(ECProperty.CACHE_DIRECTIVE, Directive.class);
        this.scripts = new CacheManager<>(ECProperty.CACHE_SCRIPT, Script.class);
    }

    public static UserData get() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }
}
