package org.andres_k.web.app.core.gui.scenes;

public enum ENode {
    LOGIN("views/auth/login.fxml"),
    REGISTER("views/auth/register.fxml"),
    MAIN("views/appScene.fxml"),
    SCRIPT_EDITOR("views/content/editors/scriptEditor.fxml"),
    INSPECT_EDITOR("views/content/editors/inspectEditor.fxml");

    private String path;

    ENode(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }


}
