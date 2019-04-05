package org.andres_k.web.app.core.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.andres_k.web.app.utils.tools.Console;

import java.io.IOException;

public class AppSceneCtrl {

    @FXML
    private BorderPane panel;
    @FXML
    private HBox header;
    @FXML
    private HBox footer;


    @FXML
    protected void initialize()
    {
        try {
            this.panel.setCenter(SceneManager.get().loadNode(ENode.INSPECT_EDITOR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToInspector(ActionEvent event) {
        try {
            this.panel.setCenter(SceneManager.get().loadNode(ENode.INSPECT_EDITOR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToScriptEditor(ActionEvent event) {
        try {
            this.panel.setCenter(SceneManager.get().loadNode(ENode.SCRIPT_EDITOR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
