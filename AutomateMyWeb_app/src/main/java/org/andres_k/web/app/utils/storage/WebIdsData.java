package org.andres_k.web.app.utils.storage;

public class WebIdsData {

    /** LOGIN **/
    public final String btn_loginAds;
    public final String btn_loginTab;
    public final String input_loginUsername;
    public final String input_loginPassword;
    public final String btn_loginSubmit;

    /** SERVER **/
    public final String btn_authPlay;
    public final String btn_launchServerAccount;
    public final String search_serverAccountsContainer;
    public final String search_serverAccounts;

    /** BUILD **/
    public final String btn_buildLaunch;
    public final String input_buildQuantity;

    /** FLEET **/
    public final String btn_fleetContinue;
    public final String btn_fleetPosG;
    public final String btn_fleetPosS;
    public final String btn_fleetPosP;
    public final String btn_fleetTypeP;
    public final String btn_fleetTypeM;
    public final String btn_fleetTypeR;
    public final String search_fleetSpeed;
    public final String input_fleetMetal;
    public final String input_fleetCristal;
    public final String input_fleetDeuterium;
    public final String input_fleetAllResources;
    public final String btn_fleetLaunch;

    /** MOVE_TYPE **/
    public final String btn_moveEXPEDITION;
    public final String btn_moveCOLONISER;
    public final String btn_moveRECYCLER;
    public final String btn_moveTRANSPORTER;
    public final String btn_moveSTATIONNER;
    public final String btn_moveESPIONNER;
    public final String btn_moveSTATIONNER_AMIS;
    public final String btn_moveATTAQUER;
    public final String btn_moveATTAQUE_GROUPE;
    public final String btn_moveDETRUIRE;

    /** UI **/
    public final String web_driver;
    public final String click_firstByCss;
    public final String click_firstByCssAndValue;
    public final String input_firstByCss;
    public final String jquery_script;
    public final String search_firstByCss;
    public final String browser_closeTab;
    public final String browser_waitTime;
    public final String get_firstByCss;
    public final String http_get;
    public final String http_post;
    public final String http_put;
    public final String http_delete;

    /**
     * SINGLETON
     **/
    private static WebIdsData instance;

    private WebIdsData() {

        this.btn_buildLaunch = "div.openX_interstitial";
        this.input_buildQuantity = "input#number.amount_input";
        this.btn_authPlay = "button.button.button-primary.button-md";
        this.btn_launchServerAccount = "button.btn.btn-primary";
        this.search_serverAccountsContainer = "section#myAccounts";
        this.search_serverAccounts = "div.rt-tr-group";
        this.btn_loginAds = "a#ui-name-1";
        this.btn_loginTab = "a#ui-name-1";
        this.input_loginUsername = "input#usernameLogin";
        this.input_loginPassword = "input#passwordLogin";
        this.btn_loginSubmit = "input#loginSubmit";
        this.btn_fleetContinue = "a#continue.on";
        this.btn_fleetPosG = "input#galaxy.galaxy.hideNumberSpin";
        this.btn_fleetPosS = "input#system.system.hideNumberSpin";
        this.btn_fleetPosP = "input#position.planet.hideNumberSpin";
        this.btn_fleetTypeP = "a#pbutton";
        this.btn_fleetTypeM = "a#mbutton";
        this.btn_fleetTypeR = "a#dbutton";
        this.search_fleetSpeed = "div#speedLinks";
        this.input_fleetMetal = "input#metal";
        this.input_fleetCristal = "input#cristal";
        this.input_fleetDeuterium = "input#deuterium";
        this.input_fleetAllResources = "a#allresources";
        this.btn_fleetLaunch = "a#start";
        this.btn_moveEXPEDITION = "a#missionButton15";
        this.btn_moveCOLONISER = "a#missionButton7";
        this.btn_moveRECYCLER = "a#missionButton8";
        this.btn_moveTRANSPORTER = "";
        this.btn_moveSTATIONNER = "";
        this.btn_moveESPIONNER = "";
        this.btn_moveSTATIONNER_AMIS = "";
        this.btn_moveATTAQUER = "";
        this.btn_moveATTAQUE_GROUPE = "";
        this.btn_moveDETRUIRE = "";

        this.web_driver = "@driver";
        this.click_firstByCss = "FirstByCss";
        this.click_firstByCssAndValue = "FirstByCssAndValue";
        this.input_firstByCss = "FirstByCss";
        this.jquery_script = "executeScript";
        this.search_firstByCss = "FirstByCss";
        this.browser_closeTab = "CloseTab";
        this.browser_waitTime = "WaitTime";
        this.get_firstByCss = "FirstByCss";
        this.http_get = "httpGET";
        this.http_post = "httpPOST";
        this.http_put = "httpPUT";
        this.http_delete = "httpDELETE";
    }

    public static WebIdsData get() {
        if (instance == null) {
            instance = new WebIdsData();
        }
        return instance;
    }
}
