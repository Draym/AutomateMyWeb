package org.andres_k.web.app.core.scriptLogic.webScript.bloc;

import org.andres_k.web.app.utils.storage.Pair;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Directive {
    public String name;
    /**
     * blocParams can only contains String or Integer
     */
    public Map<String, String> blocParams;

    public Directive() {
        this.init();
    }
    public Directive(String name) {
        this.name = name;
        this.init();
    }

    protected void init() {
        this.blocParams = new HashMap<>();
        this.createParams();
    }
    public abstract void run(WebDriver driver, Map<String, Object> globalParams);
    protected abstract void createParams();
    public abstract List<Pair<String, Class>> getResultIds();
}
