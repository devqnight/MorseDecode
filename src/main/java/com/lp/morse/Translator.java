package com.lp.morse;

import java.io.IOException;

import com.lp.tools.CodeList;
import com.lp.tools.Reader;

public class Translator {
    private static Translator instance;

    private CodeList codes;
    private MorseNode tree;

    public Translator() throws IOException{
        this.codes = Reader.getCodes();
        this.tree = MorseTree.getTree();
    }

    public static Translator getTranslator() throws IOException{
        if(instance == null){
            instance = new Translator();
        }
        return instance;
    } 

    public String toText(String data){
        String result="";
        result = TranslateToText.toText(this.codes, this.tree, data);
        return result;
    }

    public String toMorse(String data){
        String result="";
        result = TranslateToMorse.toMorse(this.codes, data);
        return result;
    }
}
