package org.andres_k.web.app.core.scenes;

import javafx.scene.Scene;
import org.andres_k.web.app.utils.data.RootFactory;
import org.andres_k.web.app.utils.data.SceneData;

import java.io.IOException;

public class Login extends Scene {
    public Login() throws IOException {
        super(RootFactory.create(SceneData.login_root));
    }
}
