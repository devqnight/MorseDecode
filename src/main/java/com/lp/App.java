package com.lp;

import com.lp.controller.CtrlMorse;
import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.morse.MorseNode;
import com.lp.morse.MorseTree;
import com.lp.morse.Translator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

//import com.lp.tools.Reader;

public class App /*extends Application*/ {

/*    @Override
    public void start(Stage primaryStage){
        try {

            final URL fxmlURL = getClass().getResource("/main/java/com/lp/view/morseView.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            final VBox node = (VBox)fxmlLoader.load();
            Scene scene = new Scene(node);
            primaryStage.setX(100);
            primaryStage.setY(100);

            primaryStage.setTitle("Morse Translator");
            primaryStage.setScene(scene);
            ((CtrlMorse)fxmlLoader.getController()).setVue(primaryStage);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try {
            /* Reader rd = new Reader();

            System.out.println(rd.getCodes()); */
            Translator tl = Translator.getTranslator();
            /* MorseNode tree = MorseTree.getTree();
            tree.displayTree(); */
            String msg = "hello world";
            msg = msg.toUpperCase();
            String resultMorse = tl.toMorse(msg);
            System.out.println(msg + " = " + resultMorse);
            String resultText = tl.toText(resultMorse);
            boolean res = resultText.equals(msg);
            System.out.println(msg + " = " + resultMorse + " => " + resultText + " :==> " + res);
            DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
            dao.writeTextToFile("test.txt",resultMorse);
        } catch(Exception ioe){
            ioe.printStackTrace();
            System.out.println(ioe + " : "+ ioe.getMessage());
        }
    }
}
