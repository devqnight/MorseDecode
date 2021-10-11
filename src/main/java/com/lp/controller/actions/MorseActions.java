package com.lp.controller.actions;

import java.io.File;
import java.io.IOException;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseBusinessException;
import com.lp.exceptions.MorseDaoException;
import com.lp.morse.Translator;
import com.lp.tools.Tools;

public class MorseActions {

    public static String translate(String textString) throws MorseBusinessException, IOException{
        String result ="";
        switch(Tools.getTools().translateWhich(textString)){
            case "String":
                result = Translator.getTranslator().toText(textString);
                break;
            case "Morse":
                result = Translator.getTranslator().toMorse(textString);
                break;
            } 
                
        return result;
    }
    
    public static int saveToFile(String fileName, String text) throws IOException, MorseDaoException {
        if(fileName.trim().length() == 0){
            throw new MorseDaoException("No file name provided !");
        }
        DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
        if(!fileName.endsWith(".txt")){
            fileName += ".txt";
        }
        if ((new File("translations/"+fileName)).exists()){
            throw new MorseDaoException("File "+fileName+" already exists...");
        }
        dao.writeTextToFile(fileName, text);
        return 0;
    }
}
