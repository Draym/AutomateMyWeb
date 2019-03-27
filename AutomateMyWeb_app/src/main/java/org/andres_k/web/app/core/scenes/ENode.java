package org.andres_k.web.app.core.scenes;

public enum ENode {
    LOGIN("views/auth/login.fxml"),
    REGISTER("views/auth/register.fxml"),
    MAIN("views/appScene.fxml");

    private String path;

    ENode(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }


}
