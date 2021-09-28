package com.lp.controller;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseDaoException;
import com.lp.morse.Translator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
            }
        }
    }

    public void onClickAddCode(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add code");
        dialog.setHeaderText("Add a "+(this.pendingCode.length()>1?"character":"code")+" for this "+(this.pendingCode.length()>1?"code":"character")+" : "+this.pendingCode);

        ButtonType saveCodeBtn = new ButtonType("Add Code");
        dialog.getDialogPane().getButtonTypes().addAll(saveCodeBtn,ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField letter = new TextField();
        letter.setPromptText("Letter");
        TextField code = new TextField();
        code.setPromptText("Code");

        grid.add(new Label("Letter: "), 0, 0);
        grid.add(letter, 1, 0);
        grid.add(new Label("Code: "), 0, 1);
        grid.add(code, 1, 1);

        /* if(){

        } */

        dialog.getDialogPane().setContent(grid);

        /* if(){

        } */

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == saveCodeBtn){
                return new Pair<>(letter.getText(),code.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
    }

    private void disableBtnAddCode(){
        this.btnAddCode.setDisable(true);
    }

    public void manageTextInput() {
        this.btnTranslate.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnClear.setDisable(txtToTranslate.getText().trim().length() == 0);
        this.btnSave.setDisable(txtTranslated.getText().trim().length() == 0 || txtFileName.getText().trim().length() == 0);
    }
}
