package org.andres_k.web.app.core.gui.scenes;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.tools.Console;
import org.andres_k.web.app.utils.tools.TFiles;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private Map<ENode, Node> nodes;
    private Stage mainWindow;
    private boolean isInit;

    public void init(Stage primaryStage) {
        this.mainWindow = primaryStage;
        this.nodes = new HashMap<>();
        this.mainWindow.setTitle("Wigmo");
        this.mainWindow.getIcons().add(new Image(TFiles.getResourceAsStream("images/icon.jpg")));
        this.isInit = true;
    }

    public void loadScene(ENode id) throws AppException {
        Node root = loadNode(id);
        if (root == null)
            throw new NullPointerException("SceneManager: cannot open the scene " + id);
        this.mainWindow.setScene(new Scene((Parent) root));
        this.mainWindow.show();
    }

    public Node loadNode(ENode id) throws AppException {
        if (!this.isInit)
            throw new NullPointerException("Please init SceneManager before use it.");

        if (!this.nodes.containsKey(id))
            this.nodes.put(id, FxFactory.createNode(id));
        return this.nodes.get(id);

    }

    public Stage getMainWindow() {
        return this.mainWindow;
    }

    /**
     * SINGLETON
     **/
    private static SceneManager instance;

    private SceneManager() {
        this.isInit = false;
    }

    public static SceneManager get() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
}
