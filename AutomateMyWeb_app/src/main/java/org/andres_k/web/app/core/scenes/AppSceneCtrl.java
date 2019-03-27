package org.andres_k.web.app.core.scenes;

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
        Console.log("HAHAHA");
        try {
            Node root = FxFactory.createNode(ENode.LOGIN);
            this.panel.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
