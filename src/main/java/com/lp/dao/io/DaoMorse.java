package com.lp.dao.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import com.lp.interfaces.IDaoMorse;
import com.lp.tools.Reader;

public class DaoMorse implements IDaoMorse{
    private static DaoMorse instance;

    private DaoMorse(){}

    public static DaoMorse getDaoMorseInstance(){
        if(instance == null){
            instance = new DaoMorse();
        }
        return instance;
    }

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

    public String writeTextToFile(String name, String file) throws IOException{
        String dest = "translations/"+name;
        FileWriter myWriter = new FileWriter(dest);
        myWriter.write(file);
        myWriter.close();
        return "Successfully wrote to the file : "+name;
    }

    
}
