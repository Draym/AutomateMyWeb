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

    public static<T> ObservableList<T> toObservable(List<T> values) {
        return FXCollections.observableArrayList(values);
    }

    public static<T> ObservableValue<T> toObservable(T value) {
        return new SimpleObjectProperty<>(value);
    }


}
