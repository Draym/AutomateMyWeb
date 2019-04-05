package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods;

import org.andres_k.web.app.core.scriptLogic.webScript.bloc.Directive;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public abstract class ActionMethod extends Directive {

    public ActionMethod() {
        super();
    }

    protected SearchContext getNewContext(WebDriver driver, Map<String, String> blocParams, Map<String, Object> globalParams) {
        if (!blocParams.containsKey(WebIdsData.get().web_driver))
            return driver;
        String id = blocParams.get(WebIdsData.get().web_driver);
        return (SearchContext) globalParams.get(id);
    }
}
