package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.get;

import org.andres_k.web.app.core.scriptLogic.webScript.actions.Action;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.ActionMethod;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.click.Click;
import org.andres_k.web.app.utils.storage.Pair;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Get extends Action {
    @Override
    protected void createMethods() {
        this.methods.add(WebIdsData.get().get_firstByCss);
    }

    @Override
    public ActionMethod getMethod(String id) {

        if (TString.isEqual(id, WebIdsData.get().get_firstByCss))
            return new Click.FirstByCss();
        return null;
    }

    /**
     * FirstByCss
     */
    public static class FirstByCss extends ActionMethod {
        private final String web_inputCSS = "css_selector";

        private String firstByCss(SearchContext container, String css) {
            WebElement element = container.findElement(By.cssSelector(css));
            if (element == null)
                throw new NotFoundException("[Get.FirstByCss] element {" + css + "} has not been found in " + container.toString());
            return element.getText();
        }

        @Override
        public void createParams() {
            this.blocParams.put(this.web_inputCSS, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            SearchContext target = this.getNewContext(driver, this.blocParams, globalParams);

            String result = this.firstByCss(target, this.blocParams.get(this.web_inputCSS));

            globalParams.put(this.name, result);
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            result.add(new Pair<>(this.name, String.class));
            return result;
        }
    }
}
