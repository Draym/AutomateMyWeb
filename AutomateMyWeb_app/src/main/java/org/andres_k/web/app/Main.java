package org.andres_k.web.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.andres_k.web.app.core.scenes.Login;

public class Main extends Application {

    @Override
    public void init() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Login());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
