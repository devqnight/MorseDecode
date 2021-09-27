package com.lp.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMorse implements Initializable, ChangeListener<String> {

    private Stage vue;
    //private Etudiant modele;

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String t, String t1) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setVue(Stage stage) {
        this.vue = stage;
    }
}
