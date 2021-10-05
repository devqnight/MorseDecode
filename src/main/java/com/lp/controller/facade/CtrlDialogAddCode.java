package com.lp.controller.facade;

import java.io.IOException;
import java.util.Optional;

import com.lp.tools.Tools;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class CtrlDialogAddCode {
    
    private static void initFields(TextField field, String pendingCode, Dialog<Pair<String,String>> dialog, ButtonType saveCodeBtn){
        field.setText(pendingCode);
        field.setEditable(false);
    }

    public static Optional<Pair<String,String>> getDialog(String pendingCode) throws IOException{
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add code");
        dialog.setHeaderText("Add a "+(pendingCode.length()>1?"character":"code")+" for this "+(pendingCode.length()>1?"code":"character")+" : "+pendingCode);

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

        String type;

        type = (Tools.getTools().translateWhich(pendingCode) == "String" 
        && Tools.getTools().isMorse(pendingCode)
        ) ? "String" : "Morse";
        
        if(type == "String"){
            initFields(code, pendingCode, dialog, saveCodeBtn);
            Node saveCode = dialog.getDialogPane().lookupButton(saveCodeBtn);
            saveCode.setDisable(true);
            code.textProperty().addListener((observable, oldValue, newValue) -> {
                saveCode.setDisable(newValue.trim().isEmpty());
            });
        } else {
            initFields(letter, pendingCode, dialog, saveCodeBtn);
            Node saveCode = dialog.getDialogPane().lookupButton(saveCodeBtn);
            saveCode.setDisable(true);
            letter.textProperty().addListener((observable, oldValue, newValue) -> {
                saveCode.setDisable(newValue.trim().isEmpty());
            });
        }

        dialog.getDialogPane().setContent(grid);

        if(type == "String"){
            Platform.runLater(() -> letter.requestFocus());
        } else {
            Platform.runLater(() -> code.requestFocus());
        }

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == saveCodeBtn){
                return new Pair<>(letter.getText(),code.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        /* result.ifPresent(newCode -> {
            if(type)
        }); */
        return result;
    }

}
