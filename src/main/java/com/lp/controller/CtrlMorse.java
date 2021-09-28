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

import java.io.File;
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

    /* public void onClickTranslate() {
        try {
            Translator tl = Translator.getTranslator();
            String msg = txtToTranslate.getText();
            String resultMorse = tl.toMorse(msg);
            txtTranslated.setText(resultMorse);
        } catch(Exception e){
            txtTranslated.setText(e.getMessage());
        }

    } */

    private String translateWhich(String input){
        if(!isText(input)){
            return "String";
        } else {
            return "Morse";
        }
    }

    private boolean isText(String input){
        return input.contains("a") || input.contains("A")
             ||input.contains("b") || input.contains("B")
             ||input.contains("c") || input.contains("C")
             ||input.contains("d") || input.contains("D")
             ||input.contains("e") || input.contains("E")
             ||input.contains("f") || input.contains("F")
             ||input.contains("g") || input.contains("G")
             ||input.contains("h") || input.contains("H")
             ||input.contains("i") || input.contains("I")
             ||input.contains("j") || input.contains("J")
             ||input.contains("k") || input.contains("K")
             ||input.contains("l") || input.contains("L")
             ||input.contains("m") || input.contains("M")
             ||input.contains("n") || input.contains("N")
             ||input.contains("o") || input.contains("O")
             ||input.contains("p") || input.contains("P")
             ||input.contains("q") || input.contains("Q")
             ||input.contains("r") || input.contains("R")
             ||input.contains("s") || input.contains("S")
             ||input.contains("t") || input.contains("T")
             ||input.contains("u") || input.contains("U")
             ||input.contains("v") || input.contains("V")
             ||input.contains("w") || input.contains("W")
             ||input.contains("x") || input.contains("X")
             ||input.contains("y") || input.contains("Y")
             ||input.contains("z") || input.contains("Z");
    }

    public void onClickTranslate() {
        String txt = this.txtToTranslate.getText();
        String result ="";
        try {
            switch(translateWhich(txt)){
                case "String":
                    result = Translator.getTranslator().toText(txt);
                    break;
                case "Morse":
                    result = Translator.getTranslator().toMorse(txt);
                    break;
            }
        } catch (Exception e) {
            result = e.getMessage();
        }
        
        this.txtTranslated.setText(result);
    }

    public void onClickSave(){
        try {
            DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
            String fileName = txtFileName.getText();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            File temp = new File("translations/"+fileName);
            boolean exists = temp.exists();
            if(exists){
                throw new MorseDaoException("File "+fileName+" already exists...");
            }
            dao.writeTextToFile(fileName,this.txtTranslated.getText());
            lblWritingWarning.setText("Save completed !");
        } catch (MorseDaoException e) {
            lblWritingWarning.setText(e.getMessage());
            return;
        } catch (IOException e) {
            lblWritingWarning.setText(e.getMessage());
            return;
        }
    }

    public void manageTextInput() {
        this.btnTranslate.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnClear.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnSave.setDisable(txtTranslated.getText().trim().length() == 0 || txtFileName.getText().trim().length() == 0);
    }
}
