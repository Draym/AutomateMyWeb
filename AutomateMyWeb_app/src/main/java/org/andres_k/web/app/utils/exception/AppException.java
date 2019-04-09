package org.andres_k.web.app.utils.exception;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.andres_k.web.app.core.gui.components.Toaster;
import org.andres_k.web.app.core.gui.scenes.SceneManager;
import org.andres_k.web.app.utils.tools.Console;

public class AppException extends Exception {
    public EError error;

    public AppException(EError error, Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = error;
    }

    public AppException(EError error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public void printError() {
        Console.log_err(this.getMessage());
        this.printStackTrace();
    }

    public void guiAlertError() {
        AppException.guiAlert(this.error.getValue());
    }

    public void guiPrintError(Pane panel, Text message) {
        Console.log_err(this.getMessage());
        panel.setVisible(true);
        message.setText(error.getValue());
    }

    public static void guiAlert(String message) {
        Toaster.create(SceneManager.get().getMainWindow(), message, 10, 2, 2);
    }
}
