package org.andres_k.web.app.core.scriptLogic.webScript.bloc.operators;

import org.andres_k.web.app.core.scriptLogic.webScript.bloc.JobBloc;
import org.andres_k.web.app.core.scriptLogic.webScript.scripting.ConditionScript;
import org.andres_k.web.app.utils.storage.Pair;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpCondition extends JobBloc {
    private List<Pair<Condition, JobBloc>> opIf;
    private Pair<Condition, JobBloc> opElse;

    public OpCondition() {
        super();
    }

    public OpCondition(String id) {
        super(id);
    }

    private boolean checkCondition(Condition condition, Map<String, Object> globalParams) {

        return ConditionScript.executeCondition(condition.value, globalParams);
    }

    @Override
    public void run(WebDriver driver, Map<String, Object> globalParams) {

        for (Pair<Condition, JobBloc> bloc : opIf) {
            if (this.checkCondition(bloc.key, globalParams)) {
                bloc.value.run(driver, globalParams);
                globalParams.put(this.name, true);
                break;
            }
        }
        if (!globalParams.containsKey(this.name) && this.opElse != null && this.checkCondition(this.opElse.key, globalParams)) {
            this.opElse.value.run(driver, globalParams);
            globalParams.put(this.name, true);
        }
        globalParams.put(this.name, false);
    }

    @Override
    protected void createParams() {
    }

    @Override
    public List<Pair<String, Class>> getResultIds() {
        List<Pair<String, Class>> result = new ArrayList<>();
        result.add(new Pair<>(this.name, Boolean.class));
        return result;
    }
}
