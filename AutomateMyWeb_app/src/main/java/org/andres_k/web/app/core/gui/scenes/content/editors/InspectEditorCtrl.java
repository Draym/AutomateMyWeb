package org.andres_k.web.app.core.gui.scenes.content.editors;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.andres_k.web.app.core.http.models.items.templates.Template;
import org.andres_k.web.app.core.http.models.items.templates.TemplateElement;
import org.andres_k.web.app.core.scriptLogic.driver.Browser;
import org.andres_k.web.app.core.scriptLogic.driver.EDriver;
import org.andres_k.web.app.core.scriptLogic.driver.WebDriverFactory;
import org.andres_k.web.app.utils.data.UserAuth;
import org.andres_k.web.app.utils.data.UserData;
import org.andres_k.web.app.utils.exception.AppException;
import org.andres_k.web.app.utils.tools.Console;
import org.andres_k.web.app.utils.tools.TJavaFX;
import org.andres_k.web.app.utils.tools.TSelenium;
import org.andres_k.web.app.utils.tools.TString;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class InspectEditorCtrl {
    @FXML
    private TextField elementName;
    @FXML
    private TextField elementCss;
    @FXML
    private TextField websiteURL;
    @FXML
    private TextField templateField;
    @FXML
    private Pane paneContent;
    @FXML
    private TableView<TemplateElement> tblGenElements;
    @FXML
    private TableColumn<TemplateElement, String> colGenElemName;
    @FXML
    private TableColumn<TemplateElement, String> colGenElemCss;
    @FXML
    private TableColumn<TemplateElement, String> colElementBtn;
    @FXML
    private TableView<TemplateElement> tblElements;
    @FXML
    private TableColumn<TemplateElement, String> colElementName;
    @FXML
    private TableColumn<TemplateElement, String> colElementCss;
    @FXML
    private ComboBox<EDriver> cboxDrivers;
    @FXML
    private ComboBox<String> cboxTemplates;
    @FXML
    private Button btnAddTemplate;
    @FXML
    private Button btnSaveTemplate;
    @FXML
    private Button btnOpenBrowser;
    @FXML
    private ImageView tblGenLoading;

    private Browser browser;
    private Template currentTemplate;
    private TemplateElement currentElement;

    @FXML
    public void initialize() {
        this.hideAddTemplate();
        this.paneContent.setVisible(false);
        this.tblElements.setEditable(true);
        this.tblGenLoading.setVisible(false);

        this.cboxDrivers.getItems().removeAll(this.cboxDrivers.getItems());
        this.cboxDrivers.getItems().addAll(EDriver.CHROME, EDriver.FIREFOX);
        this.cboxDrivers.getSelectionModel().select(EDriver.CHROME);

        this.cboxTemplates.getItems().setAll(UserData.get().getTemplates().getKeys());

        this.colElementName.setCellValueFactory(param -> TJavaFX.toObservable(param.getValue().getElement().getName()));
        this.colElementName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.colElementName.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).getElement().setName(event.getNewValue())
        );
        this.colElementCss.setCellValueFactory(param -> TJavaFX.toObservable(param.getValue().getElement().getCssSelector()));
        this.colElementCss.setCellFactory(TextFieldTableCell.forTableColumn());
        this.colElementCss.setOnEditCommit(
                event -> event.getTableView().getItems().get(
                        event.getTablePosition().getRow()).getElement().setCssSelector(event.getNewValue())
        );

        this.tblGenElements.setRowFactory(row -> new TableRow<TemplateElement>() {
            @Override
            public void updateItem(TemplateElement item, boolean empty) {
                super.updateItem(item, empty);
                setStyle("");
                if (tblElements != null && tblElements.getItems().contains(item))
                    setStyle("-fx-background-color: yellow");

                for (int i = 0; i < getChildren().size(); i++) {
                    ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);
                }

            }
        });

        this.tblGenElements.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        this.colGenElemName.setCellValueFactory(param -> TJavaFX.toObservable(param.getValue().getElement().getName()));
        this.colGenElemCss.setCellValueFactory(param -> TJavaFX.toObservable(param.getValue().getElement().getCssSelector()));
    }

    @FXML
    public void selectRowElement() {
        if (this.tblElements.getSelectionModel() == null)
            return;
        int index = this.tblElements.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Console.log("row element selected");
            this.currentElement = this.tblElements.getItems().get(index);
        }
    }

    @FXML
    void selectNewElement() {
        this.currentElement = null;
        if (this.tblElements.getSelectionModel() == null)
            return;
        Console.log("new element selected");
        this.tblElements.getSelectionModel().clearSelection();
    }

    @FXML
    public void changeTemplate() {
        Console.log("change template");
        String key = this.cboxTemplates.getSelectionModel().getSelectedItem();
        try {
            this.currentTemplate = UserData.get().getTemplates().get(key);
        } catch (AppException e) {
            e.printError();
        }
        if (this.currentTemplate == null) {
            Console.log("wait: currentTemplate is null");
            return;
        }
        this.tblElements.setItems(TJavaFX.toObservable(this.currentTemplate.getElements()));
        this.tblElements.refresh();
        this.paneContent.setVisible(true);
    }

    @FXML
    public void showAddTemplate() {
        this.templateField.setVisible(true);
        this.btnSaveTemplate.setVisible(true);
        this.btnAddTemplate.setVisible(false);
    }

    @FXML
    public void hideAddTemplate() {
        this.templateField.setText("");
        this.templateField.setVisible(false);
        this.btnSaveTemplate.setVisible(false);
        this.btnAddTemplate.setVisible(true);
    }

    @FXML
    public void createTemplate() {
        Console.log("save template");
        if (!this.verifyTemplateName(this.templateField.getText()))
            return;
        Template template = new Template();
        template.setName(this.templateField.getText());
        this.cboxTemplates.getItems().add(template.getName());

        UserData.get().getTemplates().save(template);

        SingleSelectionModel<String> selectionModel = this.cboxTemplates.getSelectionModel();
        selectionModel.select(template.getName());


        this.hideAddTemplate();
    }

    @FXML
    public void tbl1KeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            this.deleteSelectedInTable(this.tblElements);
        }
    }

    @FXML
    public void tbl2KeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            this.deleteSelectedInTable(this.tblGenElements);
        }
    }

    private void deleteSelectedInTable(TableView<TemplateElement> table) {
        if (table.getSelectionModel() == null)
            return;
        List<Integer> positions = table.getSelectionModel().getSelectedCells().stream().map(TablePosition::getRow).collect(Collectors.toList());
        int i = 0;
        for (Integer pos : positions) {
            table.getItems().remove(pos - i);
            ++i;
        }
    }

    private boolean verifyTemplateName(String value) {
        return !TString.isNullOrEmpty(value);
    }

    @FXML
    public void pushChanges() {
        UserData.get().push(UserData.DataType.TEMPLATE);
    }

    public void saveCurrentTemplate() {
        UserData.get().save(this.currentTemplate);
    }

    @FXML
    public void addLine() {
        Console.log("add Line");
        if (this.currentTemplate == null) {
            Console.log_err("Please select a template first");
            return;
        }
        if (TString.isNullOrEmpty(this.elementName.getText()) || TString.isNullOrEmpty(this.elementCss.getText())) {
            Console.log_err("The field are incorrect.");
            return;
        }
        org.andres_k.web.app.core.http.models.items.elements.WebElement model = new org.andres_k.web.app.core.http.models.items.elements.WebElement();

        model.setName(this.elementName.getText());
        model.setCssSelector(this.elementCss.getText());

        TemplateElement element = new TemplateElement();
        element.setElement(model);
        element.setTemplateId(this.currentTemplate.getId());
        if (this.tblElements.getItems().contains(element)) {
            return;
        }
        this.currentTemplate.addElement(element);
        this.saveCurrentTemplate();
        this.tblElements.getItems().add(element);
        this.tblElements.refresh();

        this.elementName.setText("");
        this.elementCss.setText("");
    }

    @FXML
    public void openWebBrowser(ActionEvent event) {
        if (TString.isNullOrEmpty(this.websiteURL.getText())) {
            Console.log_err("The URL is invalid.");
            return;
        }
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setOnFinished(e -> launchWebDriver());
        rotateTransition.play();
    }

    @FXML
    public void saveGeneratedElems() {
        if (this.currentTemplate == null)
            return;
        for (int i = 0; i < this.tblGenElements.getItems().size(); ++i) {
            if (!this.tblElements.getItems().contains(this.tblGenElements.getItems().get(i))) {
                this.tblElements.getItems().add(this.tblGenElements.getItems().get(i));
                this.currentTemplate.addElement(this.tblGenElements.getItems().get(i));
                this.tblElements.refresh();
                this.tblGenElements.getItems().remove(i);
                --i;
            }
        }
        this.saveCurrentTemplate();
    }

    @FXML
    public void autoGenerateElems() {
        if (this.browser == null)
            return;

        this.tblGenLoading.setVisible(true);
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        List<WebElement> its = TSelenium.getAllUI(browser.driver);
                        List<TemplateElement> genElems = new ArrayList<>();

                        for (WebElement it : its) {

                            org.andres_k.web.app.core.http.models.items.elements.WebElement model = new org.andres_k.web.app.core.http.models.items.elements.WebElement();

                            model.setName(TSelenium.getName(it));
                            model.setCssSelector(TSelenium.getIdentifier(it));

                            TemplateElement element = new TemplateElement();
                            element.setElement(model);
                            genElems.add(element);
                        }

                        Platform.runLater(() -> {
                            tblGenElements.setItems(TJavaFX.toObservable(genElems));
                            tblGenElements.refresh();
                        });

                        tblGenLoading.setVisible(false);
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    private void launchWebDriver() {
        this.btnOpenBrowser.setDisable(true);
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Console.log("start");
                        AtomicBoolean run = new AtomicBoolean(true);
                        final CountDownLatch latch = new CountDownLatch(1);
                        browser = WebDriverFactory.get(cboxDrivers.getValue());

                        browser.goTo(websiteURL.getText());
                        browser.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

                        while (run.get()) {
                            try {
                                WebElement element = browser.driver.switchTo().activeElement();

                                if (element != null) {
                                    String result = TSelenium.getIdentifier(element);
                                    if ((currentElement != null && TString.isEqual(currentElement.getElement().getCssSelector(), result))
                                            || TString.isEqual(elementCss.getText(), result)) {
                                        continue;
                                    }
                                    Platform.runLater(() -> {
                                        try {
                                            if (currentElement != null) {
                                                currentElement.getElement().setCssSelector(result);
                                                saveCurrentTemplate();
                                                tblElements.refresh();
                                            } else {
                                                Console.log("field update");
                                                elementCss.setText(result);
                                            }
                                        } catch (Exception e) {
                                            latch.countDown();
                                            run.set(false);
                                            Console.log("Closing browser1.");
                                        }
                                    });
                                }
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (Exception e) {
                                latch.countDown();
                                run.set(false);
                                Console.log("Closing browser2.");
                            }
                        }
                        browser.stop();
                        btnOpenBrowser.setDisable(false);
                        latch.await();
                        Console.log("end");
                        return null;
                    }
                };
            }
        };
        service.start();
    }
}
