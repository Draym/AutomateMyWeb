package org.andres_k.web.app.core.scenes.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.andres_k.web.app.core.scenes.ENode;
import org.andres_k.web.app.core.scenes.SceneManager;
import org.andres_k.web.app.utils.tools.Console;

public class LoginCtrl {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Text errorMessage;
    @FXML
    private Pane errorBloc;

    @FXML
    protected void initialize() {

        this.errorBloc.setVisible(false);
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        if (this.email.getText().equals("aa")) {
            this.errorBloc.setVisible(true);
            this.errorMessage.setText("Wrong email address");
            return;
        }
        SceneManager.get().switchScene(ENode.MAIN);
    }

    @FXML
    public void createAccount(ActionEvent event) {
        SceneManager.get().switchScene(ENode.REGISTER);
    }
}
