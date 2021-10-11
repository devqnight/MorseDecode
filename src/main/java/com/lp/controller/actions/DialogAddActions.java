package com.lp.controller.actions;

import java.io.IOException;

import com.lp.morse.MorseTree;
import com.lp.morse.Translator;
import com.lp.tools.Reader;

import javafx.util.Pair;

public class DialogAddActions {
    public static void addCodeToList(Pair<String,String> newCode) throws IOException{
        String newLine = "\n"+newCode.getKey() + " " + newCode.getValue();
        Reader.addCodeToMorse(newLine);
    }
    public static void reload() throws IOException{
        Reader.reloadCodes();
        MorseTree.reloadTree();
        Translator.reloadTranslator();
    }
}
