package org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.browser;

import org.andres_k.web.app.core.scriptLogic.webScript.actions.Action;
import org.andres_k.web.app.core.scriptLogic.webScript.actions.methods.ActionMethod;
import org.andres_k.web.app.utils.storage.Pair;
import org.andres_k.web.app.utils.storage.WebIdsData;
import org.andres_k.web.app.utils.tools.Console;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Browser extends Action {

    @Override
    protected void createMethods() {
        this.methods.add(WebIdsData.get().browser_closeTab);
        this.methods.add(WebIdsData.get().browser_waitTime);
    }

    @Override
    public ActionMethod getMethod(String id) {
        if (TString.isEqual(id, WebIdsData.get().browser_closeTab))
            return new CloseTab();
        else if (TString.isEqual(id, WebIdsData.get().browser_waitTime))
            return new CloseTab();
        return null;
    }

    /**
     * CloseTab
     ***/
    public static class CloseTab extends ActionMethod {
        private final String tabOpen_index = "open tabIndex";
        private final String tabClose_index = "close tabIndex";

        private void closeTab(WebDriver driver, Integer open, Integer close) {
            ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
            if (tabs2.size() < 2) {
                throw new NotFoundException("[Browser.CloseTab] can't close -> " + tabs2.size() + "tab open");
            }
            driver.switchTo().window(tabs2.get(close));
            driver.close();
            driver.switchTo().window(tabs2.get(open));
        }

        @Override
        public void createParams() {
            this.blocParams.put(this.tabOpen_index, "");
            this.blocParams.put(this.tabClose_index, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            this.closeTab(driver, Integer.parseInt(this.blocParams.get(this.tabOpen_index)), Integer.parseInt(this.blocParams.get(this.tabClose_index)));
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            return new ArrayList<>();
        }
    }

    /**
     * WaitTime
     ***/
    public static class WaitTime extends ActionMethod {
        private final String milliseconds = "milliseconds";

        private void pause(WebDriver driver, Integer seconds) {
            driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.MILLISECONDS);
        }

        @Override
        public void createParams() {
            this.blocParams.put(this.milliseconds, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {
            try {
                this.pause(driver, Integer.parseInt(this.blocParams.get(this.milliseconds)));
            } catch (Exception ex) {
                ex.printStackTrace();
                Console.log_err(ex.getMessage());
            }
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            return new ArrayList<>();
        }
    }

    /**
     * WaitElement
     ***/
    public static class WaitElement extends ActionMethod {
        private final String timeout = "timeout";
        private final String pollEvery = "pollEvery";
        private final String web_inputCSS = "css_selector";


        private WebElement waitElement(WebDriver driver, Integer timeout, Integer pollEvery, String css) {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(pollEvery))
                    .ignoring(NoSuchElementException.class);

            return wait.until(driver1 -> driver1.findElement(By.cssSelector(css)));
        }

        @Override
        public void createParams() {
            this.blocParams.put(this.timeout, "");
            this.blocParams.put(this.pollEvery, "");
            this.blocParams.put(this.web_inputCSS, "");
        }

        @Override
        public void run(WebDriver driver, Map<String, Object> globalParams) {

            WebElement result = this.waitElement(driver
                    , Integer.parseInt(this.blocParams.get(this.timeout))
                    , Integer.parseInt(this.blocParams.get(this.pollEvery))
                    , this.blocParams.get(this.web_inputCSS));

            globalParams.put(this.name, result);
        }

        @Override
        public List<Pair<String, Class>> getResultIds() {
            List<Pair<String, Class>> result = new ArrayList<>();
            result.add(new Pair<>(this.name, WebElement.class));
            return result;
        }
    }
}
