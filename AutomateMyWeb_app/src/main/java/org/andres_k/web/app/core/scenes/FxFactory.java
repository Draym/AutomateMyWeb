package org.andres_k.web.app.core.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.andres_k.web.app.utils.tools.TFiles;

import java.io.IOException;

public class FxFactory {
    public static Scene createScene(ENode id) throws IOException {
        Parent root = (Parent) createNode(id);
        return new Scene(root);
    }

    public static Node createNode(ENode id) throws IOException {
        return FXMLLoader.load(TFiles.getResource(id.getPath()));
    }
}
