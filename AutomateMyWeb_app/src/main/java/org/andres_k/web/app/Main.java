package org.andres_k.web.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.andres_k.web.app.core.scenes.ENode;
import org.andres_k.web.app.core.scenes.SceneManager;
import org.andres_k.web.app.utils.data.Configs;
import org.andres_k.web.app.utils.data.UserAuth;

public class Main extends Application {

    @Override
    public void init() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void start(Stage primaryStage) {
        Configs.get().init();
        UserAuth.get().init();
        SceneManager.get().init(primaryStage);

        if (UserAuth.get().isValid())
            SceneManager.get().loadScene(ENode.MAIN);
        else
            SceneManager.get().loadScene(ENode.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
