package org.andres_k.web.app.core.scriptLogic.webScript.bloc;

import org.andres_k.web.app.utils.storage.Pair;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JobBloc extends Directive {

    protected List<Directive> tasks;

    public JobBloc() {
        super();
    }
    public JobBloc(String id) {
        super(id);
    }

    @Override
    public void init() {
        super.init();
        this.tasks = new ArrayList<>();
    }

    @Override
    public void run(WebDriver driver, Map<String, Object> globalParams) {
        for (Directive task : this.tasks) {
            task.run(driver, globalParams);
        }
    }

    @Override
    protected void createParams() {
        // NO USAGE
    }

    @Override
    public List<Pair<String, Class>> getResultIds() {
        List<Pair<String, Class>> result = new ArrayList<>();

        for(Directive task : tasks) {
            result.addAll(task.getResultIds());
        }
        return result;
    }

    public void addTask(Directive task) {
        this.tasks.add(task);
    }

    public void setTasks(List<Directive> tasks) {
        this.tasks = tasks;
    }

    public List<Directive> getTasks(){
        return this.tasks;
    }
}
