package org.andres_k.web.app.core.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.andres_k.web.app.utils.tools.Console;

public class LoginController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    public void onLogin(ActionEvent event) {
        Console.log("salut" + email.getText());
    }
}
