package com.lp.dao.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import com.lp.exceptions.MorseDaoException;
import com.lp.interfaces.IDaoMorse;
import com.lp.tools.Reader;

public class DaoMorse implements IDaoMorse{
    private static DaoMorse instance;

    private DaoMorse(){}

    
    /** 
     * singleton dao morse
     * @return DaoMorse
     */
    public static DaoMorse getDaoMorseInstance(){
        if(instance == null){
            instance = new DaoMorse();
        }
        return instance;
    }

    
    /** 
     * Reads text from a file ==> used to a file to translate
     * @param name : name of the file
     * @return String : content of file as a String
     * @throws IOException
     */
    public String getTextFromFile(String name) throws IOException{
        String sb = "";
        BufferedReader rd = Reader.getReader(name);
        String st;
        while((st = rd.readLine()) != null){
            if(sb == ""){
                sb = st;
            } else {
                sb += "\n" + st;
            }
        }
        rd.close();
        return sb;
    }

    
    /** 
     * Creates new file and adds the translation in it
     * @param name : name of file to create
     * @param file : content of the file
     * @return int : result of function --> returns 0 for action done
     * @throws IOException
     * @throws MorseDaoException
     */
    public int writeTextToFile(String name, String file) throws IOException, MorseDaoException{
        String dest = "translations/"+name;
        
        FileWriter myWriter = new FileWriter(dest);//Reader.getFileInJar(dest));
        myWriter.write(file);
        myWriter.close();
        return 0;
    }

    
}
