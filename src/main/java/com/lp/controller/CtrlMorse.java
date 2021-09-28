package com.lp.controller;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseDaoException;
import com.lp.morse.MorseNode;
import com.lp.morse.MorseTree;
import com.lp.morse.Translator;
import com.lp.tools.Reader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
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
    private Button btnClear;
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
        this.btnClear.setDisable(true);

        this.txtToTranslate.textProperty().addListener(this);
        this.txtTranslated.textProperty().addListener(this);
        this.txtFileName.textProperty().addListener(this);
        this.lblWritingWarning.textProperty().addListener(this);
    }

    public void setVue(Stage stage) {
        this.vue = stage;
    }

    public void clearFields() {

        this.txtFileName.setText("");
        this.txtToTranslate.setText("");
        this.txtTranslated.setText("");
        this.lblWritingWarning.setText("");
    }

    public void onClickTranslate() {
        try {
            Translator tl = Translator.getTranslator();
            String msg = txtToTranslate.getText();
            String resultMorse = tl.toMorse(msg);
            txtTranslated.setText(resultMorse);
        } catch(Exception e){
            txtTranslated.setText(e.getMessage());
        }

    }

    public void onClickSave(){
        try {
            DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
            String fileName = txtFileName.getText();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            dao.writeTextToFile(fileName,this.txtTranslated.getText());
            lblWritingWarning.setText("Save completed !");
        } catch (MorseDaoException e) {
            lblWritingWarning.setText(e.getMessage());
        } catch (IOException e) {
            lblWritingWarning.setText(e.getMessage());
        }
    }

    public void manageTextInput() {
        this.btnTranslate.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnClear.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnSave.setDisable(txtTranslated.getText().trim().length() == 0 || txtFileName.getText().trim().length() == 0);
    }
}
