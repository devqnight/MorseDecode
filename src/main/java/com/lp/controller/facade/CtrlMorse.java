package com.lp.controller.facade;

import com.lp.controller.actions.MorseActions;
import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseDaoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CtrlMorse implements Initializable, ChangeListener<String> {

    private Stage vue;

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
    @FXML
    private Button btnOpenFile;
    @FXML
    private Button btnAddCode;

    private String pendingCode;

    final FileChooser fileChooser = new FileChooser();

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
        this.disableBtnAddCode();
    }

    public void onClickTranslate() {
        String txt = this.txtToTranslate.getText();
        String result = "";
        try {
            result = MorseActions.translate(txt);
        } catch (Exception e) {
            this.pendingCode = e.getMessage();
            result = (pendingCode.length()>1 ? "word ":"character ") +pendingCode+ " does not exist in morse code...";
            this.btnAddCode.setDisable(false);
            this.btnAddCode.setOpacity(100);
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
            lblWritingWarning.setTextFill(Color.GREEN);
        } catch (MorseDaoException e) {
            lblWritingWarning.setText(e.getMessage());
        } catch (IOException e) {
            lblWritingWarning.setText(e.getMessage());
        }
    }

    public void onClickOpenFileChooser() throws IOException{
        File file = fileChooser.showOpenDialog(this.vue);
        if(file != null){
            try {
                DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
                this.txtToTranslate.setText(dao.getTextFromFile(file.getPath()));
            } catch (Exception e) {
                lblWritingWarning.setText(e.getMessage());
                lblWritingWarning.setTextFill(Color.RED);
            }
        }
    }

    public void onClickAddCode() throws IOException{
        Optional<Pair<String,String>> result = CtrlDialogAddCode.getDialog(this.pendingCode);

        result.ifPresent(newCode -> {
            System.out.println("code: "+newCode.getValue()+", letter: "+newCode.getKey());
            disableBtnAddCode();
        });
    }

    private void disableBtnAddCode(){
        this.btnAddCode.setDisable(true);
        this.btnAddCode.setOpacity(0);
    }

    public void manageTextInput() {
        boolean ToTranslateIsEmpty = txtToTranslate.getText().trim().length() == 0;
        boolean TranslatedIsEmpty = txtTranslated.getText().trim().length() == 0;
        boolean FileNameIsEmpty = txtFileName.getText().trim().length() == 0;

        this.btnTranslate.setDisable(ToTranslateIsEmpty);
        this.btnClear.setDisable(ToTranslateIsEmpty);
        this.btnSave.setDisable(TranslatedIsEmpty || FileNameIsEmpty);
        if(ToTranslateIsEmpty){
            disableBtnAddCode();
        }
    }
}
