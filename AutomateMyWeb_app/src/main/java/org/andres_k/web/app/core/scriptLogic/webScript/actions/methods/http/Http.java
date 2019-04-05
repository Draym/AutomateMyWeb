package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.http;

import org.andres_k.web.app.core.scriptLogic.webScript.actions.Action;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.ActionMethod;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.browser.Browser;
import org.andres_k.web.app.utils.storage.Pair;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Http extends Action {
    @Override
    protected void createMethods() {
        this.methods.add(WebIdsData.get().http_get);
    }

    @Override
    public ActionMethod getMethod(String id) {
        if (TString.isEqual(id, WebIdsData.get().http_get))
            return new Browser.CloseTab();
        return null;
    }

    /**
     * GET
     */
    public static class Get extends ActionMethod {

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {

        }

        @Override
        protected void createParams() {

        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            return result;
        }
    }
}
