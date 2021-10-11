package com.lp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

import com.lp.controller.facade.CtrlMorse;

//import com.lp.tools.Reader;

public class App extends Application {

    @Override
    public void start(Stage primaryStage){
        try {

            final URL fxmlURL = getClass().getResource("/view/morseView.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node node = fxmlLoader.load();
            Scene scene = new Scene((VBox) node);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Morse Translator");
            CtrlMorse controller = (CtrlMorse)fxmlLoader.getController();
            controller.setVue(primaryStage);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            
            launch(args);
        } catch(Exception ioe){
            ioe.printStackTrace();
            System.out.println(ioe + " : "+ ioe.getMessage());
        }
    }
}
