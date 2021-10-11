package com.lp.controller.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.exceptions.MorseBusinessException;
import com.lp.exceptions.MorseDaoException;
import com.lp.morse.Translator;
import com.lp.tools.Reader;
import com.lp.tools.Tools;

public class MorseActions {

    // function used on click of the 'translate' button
    // in : String text
    // out : String result

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
    
    // function used to save a translation into a file 
    // in : String filename , String text 
    // out : void
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

    //resets the morse code to the basic code
    public static void resetMorseCode() throws IOException {
        File code = new File("codes/codes.txt");//Reader.getFileInJar("/codes/codes.txt");
        if(code.delete()){
            File rstCode = new File("codes/codes.txt");
            File ogCode = new File("codes/codes copy.txt");
            copyContent(ogCode,rstCode);
            DialogAddActions.reload();
        }
    }

    private static void copyContent(File og, File dest) throws IOException{
        FileInputStream in = new FileInputStream(og);
        FileOutputStream out = new FileOutputStream(dest);
        try{
            int n;

            while ((n = in.read()) != -1){
                out.write(n);
            }
        }finally{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }
}
