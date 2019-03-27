package org.andres_k.web.app.core.scenes;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.andres_k.web.app.utils.tools.Console;
import org.andres_k.web.app.utils.tools.TFiles;

import java.io.IOException;

public class SceneManager {
    private Stage mainWindow;
    private boolean isInit;

    public void init(Stage primaryStage) {
        this.mainWindow = primaryStage;

        this.mainWindow.setTitle("Wigmo");
        this.mainWindow.getIcons().add(new Image(TFiles.getResourceAsStream("images/icon.jpg")));
        this.isInit = true;
    }

    public void switchScene(ENode id)  {
        if (!this.isInit)
            throw new NullPointerException("Please init SceneManager before use it.");
        try {
            this.mainWindow.setScene(FxFactory.createScene(id));
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();

            Console.log("SceneManager: cannot open the scene " + id);
        }
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
