package org.andres_k.web.app.utils.tools;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TJavaFX {
    public final static List<String> actionComponents = new ArrayList<String>() {
        {
            add("a");
            add("input");
            add("button");
        }
    };
    public final static List<String> dataComponents = new ArrayList<String>() {
        {
            add("span");
            add("p");
        }
    };



    public static<T> ObservableList<T> toObservable(List<T> values) {
        return FXCollections.observableArrayList(values);
    }

    public static<T> ObservableValue<T> toObservable(T value) {
        return new SimpleObjectProperty<>(value);
    }

    public static String getIdentifier(WebElement element) {
        String className = element.getAttribute("class");
        String id = element.getAttribute("id");
        String type = element.getTagName();

        return type + (id != null ? "#" + id : "." + className);
    }

    public static String getName(WebElement element) {
        String className = element.getAttribute("class");
        String id = element.getAttribute("id");
        String text = element.getText();

        return (id != null ? id : (text != null ? text : className));
    }

    public static List<WebElement> getAll(WebDriver driver) {
        return driver.findElements(By.xpath("//*[@id]"));
    }

    public static List<WebElement> getAllUI(WebDriver driver) {
        List<WebElement> elements = getAll(driver);
        List<WebElement> result = new ArrayList<>();

        for (WebElement it : elements) {

            String id = TJavaFX.getIdentifier(it);

            if (hisUserAction(it) || hisData(it)) {
                result.add(it);

                Console.log(id + " ....  " + it.getText());
            }

        }
        return result;
    }

    public static boolean hisUserAction(WebElement element) {
        if (element.getAttribute("href") != null)
            return true;
        for (String action : actionComponents){
            if (TString.isEqual(element.getTagName(), action))
            return true;
        }
        return false;
    }

    public static boolean hisData(WebElement element) {
        if (!hisUserAction(element)) {
            for (String action : dataComponents){
                if (TString.isEqual(element.getTagName(), action))
                    if (element.getAttribute("id") != null && element.getText() != null)
                        return true;
            }
        }
        return false;
    }
}
