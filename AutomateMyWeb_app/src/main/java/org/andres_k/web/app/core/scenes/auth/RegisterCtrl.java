package org.andres_k.web.app.core.scenes.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.andres_k.web.app.core.http.models.auth.User;
import org.andres_k.web.app.core.http.services.AuthService;
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
    private PasswordField passwordVerify;
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

        if (!this.password.getText().equals(this.passwordVerify.getText())) {
            this.printError("The verification password does't match with the password.");
            return;
        }

        User user = new User();
        user.setEmail(this.email.getText());
        user.setPassword(this.password.getText());
        user.setPseudo(this.pseudo.getText());

        try {
            AuthService.register(user);
            SceneManager.get().switchScene(ENode.LOGIN);
        } catch (Exception ex) {
            this.printError(ex.getMessage());
        }
    }

    private void printError(String error) {
        Console.log_err(error);
        this.errorBloc.setVisible(true);
        this.errorMessage.setText(error);
    }
}
