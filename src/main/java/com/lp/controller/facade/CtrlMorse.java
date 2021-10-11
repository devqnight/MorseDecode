package com.lp.controller.facade;

import com.lp.controller.actions.DialogAddActions;
import com.lp.controller.actions.MorseActions;
import com.lp.controller.enums.TextTypes;
import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseDaoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CtrlMorse implements Initializable {

    private Stage vue;

    @FXML
    private TextArea txtAreaToTranslate;
    @FXML
    private TextArea txtAreaTranslated;
    @FXML
    private TextField txtFieldFileName;
    @FXML
    private Label lblWritingWarning;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnAddCode;

    private String pendingCode;

    final FileChooser fileChooser = new FileChooser();

    public void setVue(Stage stage) {
        this.vue = stage;
    }

    // Initialize the main stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnTranslate.setDisable(true);
        this.btnSave.setDisable(true);
        this.btnClear.setDisable(true);

        this.txtAreaToTranslate.textProperty().addListener((observable, oldValue, newValue) -> {
            listenerTranslationButtons(newValue);
        });
        
        this.txtAreaTranslated.textProperty().addListener((observable, oldValue, newValue) -> {
            listenerSaveButtons(newValue);
        });
    }  

    //LISTENERS
    private boolean stringIsEmpty(String res){
        return res.trim().length() == 0;
    }

    private void listenerTranslationButtons(String value){
        boolean isEmpty = stringIsEmpty(value);
        this.btnClear.setDisable(isEmpty && stringIsEmpty(this.txtAreaTranslated.getText()));
        this.btnTranslate.setDisable(isEmpty);
        if(isEmpty){
            toggleAddCode(true);
        }
    }

    private void listenerSaveButtons(String value){
        this.btnSave.setDisable(stringIsEmpty(value));
        this.lblWritingWarning.setText(stringIsEmpty(value)? "" : this.lblWritingWarning.getText());
    }

    // Clear all the fields
    public void clearFields() {
        this.txtFieldFileName.setText("");
        this.txtAreaToTranslate.setText("");
        this.txtAreaTranslated.setText("");
        this.lblWritingWarning.setText("");
        this.btnClear.setDisable(true);
        toggleAddCode(true);
    }

    public void onClickTranslate() {
        String txt = this.txtAreaToTranslate.getText();
        try {
            this.displayOnZone(MorseActions.translate(txt), txtAreaTranslated, TextTypes.TEXT_T);
        } catch (Exception e) {
            this.handleTranslateError(e, this.txtAreaTranslated);
        }
    }

    public void onClickSave(){
        try {
            if(MorseActions.saveToFile(txtFieldFileName.getText(),this.txtAreaTranslated.getText()) == 0)
            this.displayOnZone("File saved !", lblWritingWarning, TextTypes.SUCCESS_T);
        } catch (IOException | MorseDaoException e) {
            this.displayOnZone(e.getMessage(), lblWritingWarning, TextTypes.ERROR_T);
        }
    }

    public void onClickOpenFileChooser() throws IOException{
        File file = fileChooser.showOpenDialog(this.vue);
        if(file != null){
            try {
                DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
                this.displayOnZone(dao.getTextFromFile(file.getPath()), this.txtAreaToTranslate, TextTypes.TEXT_T);
            } catch (Exception e) {
                this.displayOnZone(e.getMessage(), this.lblWritingWarning, TextTypes.ERROR_T);
            }
        }
    }

    public void onClickAddCode() throws IOException{
        Optional<Pair<String,String>> result = CtrlDialogAddCode.getDialog(this.pendingCode);

        result.ifPresent(newCode -> {
            try {
                DialogAddActions.addCodeToList(newCode);
                DialogAddActions.reload();
            } catch (IOException e) {
                e.printStackTrace();
            }
            toggleAddCode(true);
        });
    }

    private void toggleAddCode(Boolean toggle){
        this.btnAddCode.setDisable(toggle);
        this.btnAddCode.setOpacity(toggle ? 0 : 100);
    }

    private void handleTranslateError(Exception e, Control control){
        this.pendingCode = e.getMessage();
        String result = (pendingCode.length()>1 ? "word ":"character ") 
                        +pendingCode
                        + " does not exist in morse code...";
        this.toggleAddCode(false);
        displayOnZone(result, control, TextTypes.ERROR_T);
    }

    private void displayOnZone(String msg, Control control, TextTypes type){
        control.setStyle("-fx-text-fill: "+type.getColor()+";");
        if(control instanceof TextInputControl){
            ((TextInputControl)control).setText(msg);
        } else if(control instanceof Labeled){
            ((Labeled)control).setText(msg);
        }
    }
}
