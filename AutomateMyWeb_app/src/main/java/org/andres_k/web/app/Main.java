package org.andres_k.web.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.andres_k.web.app.core.scenes.ENode;
import org.andres_k.web.app.core.scenes.SceneManager;

public class Main extends Application {

    @Override
    public void init() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void start(Stage primaryStage) {
        SceneManager.get().init(primaryStage);

        SceneManager.get().switchScene(ENode.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
