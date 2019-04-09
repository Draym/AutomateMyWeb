package org.andres_k.web.app.core.gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.exception.EError;
import org.andres_k.web.app.utils.tools.TFiles;

import java.io.IOException;

public class FxFactory {
    public static Scene createScene(ENode id) throws AppException {
        Parent root = (Parent) createNode(id);
        return new Scene(root);
    }

    public static Node createNode(ENode id) throws AppException {
        try {
            return FXMLLoader.load(TFiles.getResource(id.getPath()));
        } catch (IOException e) {
            throw new AppException(EError.FXFACTORY_CREATE_NODE, e);
        }
    }
}
