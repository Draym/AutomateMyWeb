package org.andres_k.web.app.core.scenes.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.andres_k.web.app.core.http.models.auth.custom.AuthHandler;
import org.andres_k.web.app.core.http.services.AuthService;
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
    private CheckBox autoLogin;

    @FXML
    protected void initialize() {
        this.errorBloc.setVisible(false);
    }

    @FXML
    public void onSubmit(ActionEvent event) {
        AuthHandler user = new AuthHandler();
        user.setEmail(this.email.getText());
        user.setPassword(this.password.getText());

        try {
            AuthService.login(user, autoLogin.isSelected());
            SceneManager.get().switchScene(ENode.MAIN);
        } catch (Exception ex) {
            this.printError(ex.getMessage());
        }
    }

    @FXML
    public void createAccount(ActionEvent event) {
        SceneManager.get().switchScene(ENode.REGISTER);
    }

    private void printError(String error) {
        Console.log_err(error);
        this.errorBloc.setVisible(true);
        this.errorMessage.setText(error);
    }
}
