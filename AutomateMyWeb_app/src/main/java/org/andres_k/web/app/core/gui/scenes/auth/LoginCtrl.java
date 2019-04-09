package org.andres_k.web.app.core.gui.scenes.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.andres_k.web.app.core.http.models.auth.custom.AuthHandler;
import org.andres_k.web.app.core.http.services.AuthService;
import org.andres_k.web.app.core.gui.scenes.ENode;
import org.andres_k.web.app.core.gui.scenes.SceneManager;
import org.andres_k.web.app.utils.data.UserData;
import org.andres_k.web.app.utils.exception.AppException;

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
            SceneManager.get().loadScene(ENode.MAIN);
        } catch (AppException ex) {
            ex.guiPrintError(this.errorBloc, this.errorMessage);
        }
    }

    @FXML
    public void offlineMode() {
        UserData.get().isOffline = true;
        try {
            SceneManager.get().loadScene(ENode.MAIN);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createAccount() {
        try {
            SceneManager.get().loadScene(ENode.REGISTER);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }
}
