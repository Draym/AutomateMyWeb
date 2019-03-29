package org.andres_k.web.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.andres_k.web.app.core.scenes.ENode;
import org.andres_k.web.app.core.scenes.SceneManager;
import org.andres_k.web.app.utils.data.Configs;
import org.andres_k.web.app.utils.data.UserProfile;

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
        UserProfile.get().init();
        SceneManager.get().init(primaryStage);

        if (UserProfile.get().isValid())
            SceneManager.get().switchScene(ENode.MAIN);
        else
            SceneManager.get().switchScene(ENode.LOGIN);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
