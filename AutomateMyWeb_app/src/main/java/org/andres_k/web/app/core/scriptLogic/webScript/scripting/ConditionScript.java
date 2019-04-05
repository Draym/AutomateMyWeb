package org.andres_k.web.app.core.scriptLogic.webScript.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class ConditionScript {

    public static boolean executeCondition(String condition, Map<String, Object> globalParams) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");

        boolean result = false;

        for (Map.Entry<String, Object> entry : globalParams.entrySet()) {
            engine.put(entry.getKey(), entry.getValue());
        }
        try {
            engine.eval("var result = " + condition + ";");
            result = (boolean) engine.get("result");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }
}
