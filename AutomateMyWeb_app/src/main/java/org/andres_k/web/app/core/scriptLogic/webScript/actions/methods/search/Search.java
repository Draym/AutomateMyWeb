package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.search;

import org.andres_k.web.app.core.scriptLogic.webScript.actions.Action;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.ActionMethod;
import org.andres_k.web.app.utils.storage.Pair;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search extends Action {

    @Override
    protected void createMethods() {
        this.methods.add(WebIdsData.get().search_firstByCss);
    }

    @Override
    public ActionMethod getMethod(String id) {
        if (TString.isEqual(id, WebIdsData.get().search_firstByCss))
            return new FirstByCss();
        return null;
    }
    /**
     * FirstByCs
     ***/
    public static class FirstByCss extends ActionMethod {
        private final String web_inputCSS = "css_selector";

        private WebElement firstByCss(SearchContext container, String css) {
            WebElement element = container.findElement(By.cssSelector(css));

            if (element == null)
                throw new NotFoundException("[Search.FirstByCss] element {" + css + "} has not been found in " + container.toString());
            return element;
        }

        @Override
        protected void createParams() {
            this.blocParams.put(this.web_inputCSS, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            SearchContext target = this.getNewContext(driver, this.blocParams, globalParams);
            WebElement result = this.firstByCss(target, this.blocParams.get(this.web_inputCSS));
            globalParams.put(this.name, result);
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            result.add(new Pair<>(this.name, WebElement.class));
            return result;
        }
    }
    /**
     * FirstByCssAndValue
     ***/
    public static class FirstByCssAndValue extends ActionMethod {
        private final String web_inputCSS = "css_selector";
        private final String web_inputValue = "value";

        private List<WebElement> allByCss(SearchContext container, String css) {
            return container.findElements(By.cssSelector(css));
        }

        private WebElement firstByValue(List<WebElement> elements, String value) {
            for (WebElement element : elements) {
                if (TString.contains(element.getText(), value)) {
                    return element;
                }
            }
            throw new NotFoundException("[Search.FirstByValue] element {" + value + "} has not been found");
        }

        @Override
        protected void createParams() {
            this.blocParams.put(this.web_inputCSS, "");
            this.blocParams.put(this.web_inputValue, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            SearchContext target = this.getNewContext(driver, this.blocParams, globalParams);

            List<WebElement> resultList = this.allByCss(target, this.blocParams.get(this.web_inputCSS));
            WebElement result = this.firstByValue(resultList, this.blocParams.get(this.web_inputValue));

            globalParams.put(this.name, result);
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            result.add(new Pair<>(this.name, WebElement.class));
            return result;
        }
    }

    /**
     * AllByCss
     ***/
    public class AllByCss extends ActionMethod {
        private final String web_inputCSS = "css_selector";

        private List<WebElement> allByCss(SearchContext container, String css) {
            return container.findElements(By.cssSelector(css));
        }

        @Override
        protected void createParams() {
            this.blocParams.put(this.web_inputCSS, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            SearchContext target = this.getNewContext(driver, this.blocParams, globalParams);
            List<WebElement> result = this.allByCss(target, this.blocParams.get(this.web_inputCSS));
            globalParams.put(this.name, result);
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            result.add(new Pair<>(this.name, List.class));
            return result;
        }
    }
}
