package org.andres_k.web.app.core.scenes.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.andres_k.web.app.core.scenes.ENode;
import org.andres_k.web.app.core.scenes.SceneManager;
import org.andres_k.web.app.utils.tools.Console;

public class RegisterCtrl {
    @FXML
    private TextField email;
    @FXML
    private TextField pseudo;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField paswordVerif;

    @FXML
    public void onSubmit(ActionEvent event) {
        Console.log("salut" + email.getText());
        SceneManager.get().switchScene(ENode.MAIN);
    }
}
