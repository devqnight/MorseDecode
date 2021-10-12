package com.lp.morse;

import java.io.IOException;

import com.lp.exceptions.MorseBusinessException;
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

    
    /** 
     * @throws IOException
     */
    public static void reloadTranslator() throws IOException{
        instance = null;
        instance = new Translator();
    }

    
    /** 
     * @return Translator
     * @throws IOException
     */
    public static Translator getTranslator() throws IOException{
        if(instance == null){
            instance = new Translator();
        }
        return instance;
    } 

    
    /** 
     * @param data
     * @return String
     * @throws MorseBusinessException
     */
    public String toText(String data) throws MorseBusinessException{
        String result="";
        result = TranslateToText.toText(this.tree, data);
        return result;
    }

    /**
     * 
     * @param data
     * @return
     * @throws MorseBusinessException
     */
    public String toMorse(String data) throws MorseBusinessException{
        String result="";
        result = TranslateToMorse.toMorse(this.codes, data);
        return result;
    }
}
