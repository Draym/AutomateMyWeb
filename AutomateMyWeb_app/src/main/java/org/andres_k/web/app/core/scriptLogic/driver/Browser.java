package org.andres_k.web.app.core.scriptLogic.driver;

import org.openqa.selenium.WebDriver;

public class Browser {
    public WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo(String url) {
        this.driver.get(url);
    }

    public void stop() {
        this.log();
        driver.quit();
    }

    public void log() {
    }
}
