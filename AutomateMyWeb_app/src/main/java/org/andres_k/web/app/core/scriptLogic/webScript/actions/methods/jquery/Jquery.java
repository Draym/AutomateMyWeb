package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.jquery;

import org.andres_k.web.app.core.scriptLogic.webScript.actions.Action;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.ActionMethod;
import org.andres_k.web.app.utils.storage.Pair;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jquery extends Action {
    @Override
    protected void createMethods() {
        this.methods.add(WebIdsData.get().jquery_script);
    }

    @Override
    public ActionMethod getMethod(String id) {
        if (TString.isEqual(id, WebIdsData.get().jquery_script))
            return new Script();
        return null;
    }

    /**
     * Job
     ***/
    public static class Script extends ActionMethod {

        private final String web_inputScript = "jquery_script";

        private void executeScript(SearchContext container, String script) {
            ((JavascriptExecutor)container).executeScript(script);
        }

        @Override
        protected void createParams() {
            this.blocParams.put(this.web_inputScript, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            SearchContext target = this.getNewContext(driver, this.blocParams, globalParams);

            this.executeScript(target, this.blocParams.get(this.web_inputScript));
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            return new ArrayList<>();
        }
    }
}
