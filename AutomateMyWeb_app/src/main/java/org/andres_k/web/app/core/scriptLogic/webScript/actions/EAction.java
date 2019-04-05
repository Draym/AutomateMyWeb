package org.andres_k.web.app.core.scriptLogic.webScript.actions;

public enum EAction {
    INPUT("input"),
    SEARCH("search"),
    CLICK("click"),
    JQUERY("jquery");

    public String id;

    EAction(String id) {
        this.id = id;
    }


}
