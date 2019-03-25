package org.andres_k.web.app.utils.data;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.andres_k.web.app.utils.tools.FileTools;

import java.io.IOException;

public class RootFactory {
    public static Parent create(String path) throws IOException {
        return FXMLLoader.load(FileTools.getResource(path));
    }
}
