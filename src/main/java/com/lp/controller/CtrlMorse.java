package com.lp.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMorse implements Initializable, ChangeListener<String> {

    private Stage vue;
    //private Etudiant modele;

    @FXML
    private TextArea txtToTranslate;
    @FXML
    private TextArea txtTranslated;
    @FXML
    private TextField txtFileName;
    @FXML
    private Label lblWritingWarning;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnSave;

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String t, String t1) {
        this.manageTextInput();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnTranslate.setDisable(true);
        this.btnSave.setDisable(true);

        this.txtToTranslate.textProperty().addListener(this);
        this.txtTranslated.textProperty().addListener(this);
        this.txtFileName.textProperty().addListener(this);
    }

    public void setVue(Stage stage) {
        this.vue = stage;
    }

    private void videChamps() {

        this.txtFileName.setText("");
        this.txtToTranslate.setText("");
        this.txtTranslated.setText("");
        this.lblWritingWarning.setText("");
    }

    public void onClickTranslate() {
        this.txtTranslated.setText(this.txtToTranslate.getText());
    }

    public void manageTextInput() {
        this.btnTranslate.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnSave.setDisable(txtTranslated.getText().trim().length() == 0 || txtFileName.getText().trim().length() == 0);
    }
}
