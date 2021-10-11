package com.lp.controller.facade;

import com.lp.controller.actions.DialogAddActions;
import com.lp.controller.actions.MorseActions;
import com.lp.controller.enums.TextTypes;
import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseDaoException;
import com.lp.tools.Reader;

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
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnAddCode;
    @FXML
    private Button btnResetMorseCode;
    @FXML
    private Label lblTitleMorseCode;
    @FXML
    private TextArea txtAreaMorseCode;

    private String pendingCode;

    public void setVue(Stage stage) {
        this.vue = stage;
    }

    //init functions
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.btnSave.setDisable(true);
        this.btnClear.setDisable(true);

        initMorseCodeList();

        this.txtAreaToTranslate.textProperty().addListener((observable, oldValue, newValue) -> {
            onChangeTranslate(newValue);
            listenerTranslationButtons(newValue);
        });
        
        this.txtAreaTranslated.textProperty().addListener((observable, oldValue, newValue) -> {
            listenerSaveButtons(newValue);
        });
    }
    
    //gets the morse code and displays it on a text area to the left of the window
    private void initMorseCodeList(){
        try {
            displayOnZone(Reader.getCodes().toString(), this.txtAreaMorseCode, TextTypes.TEXT_T);
        } catch (IOException e1) {
            displayOnZone(e1.getMessage(), this.txtAreaMorseCode, TextTypes.ERROR_T);
        }
    }

    //LISTENERS
    private boolean stringIsEmpty(String res){
        return res.trim().length() == 0;
    }

    private void listenerTranslationButtons(String value){
        boolean isEmpty = stringIsEmpty(value);
        this.btnClear.setDisable(isEmpty && stringIsEmpty(this.txtAreaTranslated.getText()));
        if(isEmpty){
            toggleAddCode(true);
        }
    }

    //listener for the save button and the status label
    private void listenerSaveButtons(String value){
        boolean isPending = this.pendingCode == null;
        this.btnSave.setDisable(stringIsEmpty(value)|| !isPending); 
        this.lblWritingWarning.setText(stringIsEmpty(value)? "" : this.lblWritingWarning.getText());
    }

    //functions
    public void clearFields() {
        this.txtFieldFileName.setText("");
        this.txtAreaToTranslate.setText("");
        this.txtAreaTranslated.setText("");
        this.lblWritingWarning.setText("");
        this.btnClear.setDisable(true);
        toggleAddCode(true);
    }

    private void onChangeTranslate(String newValue){
        try {
            if(newValue.trim().length() > 0){
                this.displayOnZone(MorseActions.translate(newValue), txtAreaTranslated, TextTypes.TEXT_T);
            } else {
                this.clearFields();
            }
        } catch (Exception e) {
            this.btnSave.setDisable(true);
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

    //opens a dialog box to choose a file to immediately open in the interface
    public void onClickOpenFileChooser() throws IOException{
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("to_translate/"));
        File file = fc.showOpenDialog(this.vue);
        if(file != null){
            try {
                DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
                this.displayOnZone(dao.getTextFromFile(file.getPath()), this.txtAreaToTranslate, TextTypes.TEXT_T);
            } catch (Exception e) {
                this.displayOnZone(e.getMessage(), this.lblWritingWarning, TextTypes.ERROR_T);
            }
        }
    }

    //opens a dialog box to add a code to the morse code when a character is entered and is not recognised
    public void onClickAddCode() throws IOException{
        Optional<Pair<String,String>> result = CtrlDialogAddCode.getDialog(this.pendingCode);

        result.ifPresent(newCode -> {
            try {
                DialogAddActions.addCodeToList(newCode);
                DialogAddActions.reload();
                onChangeTranslate(this.txtAreaToTranslate.getText());
                initMorseCodeList();
            } catch (IOException e) {
                e.printStackTrace();
            }
            toggleAddCode(true);
        });
    }

    public void onClickResetMorseCode() throws IOException{
        MorseActions.resetMorseCode();
        initMorseCodeList();
        onChangeTranslate(this.txtAreaToTranslate.getText());
    }

    //display controls
    private void toggleAddCode(Boolean toggle){
        this.btnAddCode.setDisable(toggle);
        this.btnAddCode.setOpacity(toggle ? 0 : 100);
    }

    //handles errors when translating
    private void handleTranslateError(Exception e, Control control){
        this.pendingCode = e.getMessage();
        String result = (pendingCode.length()>1 ? "word ":"character ") 
                        +pendingCode
                        + " does not exist in morse code...";
        this.toggleAddCode(false);
        displayOnZone(result, control, TextTypes.ERROR_T);
    }

    //displays text in the control zone passed as argument, and applies the color according to the text type
    private void displayOnZone(String msg, Control control, TextTypes type){
        control.setStyle("-fx-text-fill: "+type.getColor()+";");
        if(control instanceof TextInputControl){
            ((TextInputControl)control).setText(msg);
        } else if(control instanceof Labeled){
            ((Labeled)control).setText(msg);
        }
    }
}