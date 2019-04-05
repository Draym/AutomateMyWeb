package org.andres_k.web.app.core.scriptLogic.driver;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.andres_k.web.app.utils.tools.TFiles;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {

    public static Browser get(EDriver driver) {

        if (driver == EDriver.FIREFOX) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            System.setProperty("webdriver.gecko.driver", TFiles.getResource("web_drivers/geckodriver/geckodriver.exe").getPath());
            return new Browser(new FirefoxDriver(firefoxOptions));
        } else if (driver == EDriver.FIREFOX_GHOST) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--headless");
            System.setProperty("webdriver.gecko.driver", TFiles.getResource("web_drivers/geckodriver/geckodriver.exe").getPath());
            return new Browser(new FirefoxDriver(firefoxOptions));
        } else if (driver == EDriver.CHROME) {
            ChromeOptions chromeOptions = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", TFiles.getResource("web_drivers/chromedriver_win32/chromedriver.exe").getPath());
            return new Browser(new ChromeDriver(chromeOptions));
        } else if (driver == EDriver.CHROME_GHOST) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            System.setProperty("webdriver.chrome.driver", TFiles.getResource("web_drivers/chromedriver_win32/chromedriver.exe").getPath());
            return new Browser(new ChromeDriver(chromeOptions));
        }
        throw new NotFoundException();
    }
}
