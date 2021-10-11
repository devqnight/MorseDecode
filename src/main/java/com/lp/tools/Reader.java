package com.lp.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reader {
    private static File text;

    private static CodeList codeList;

    public static CodeList getCodes() throws IOException{
        if(codeList == null){
            readCodes();
        }
        return codeList;
    }

    // This function reads a .txt file and add all the translations to the code list

    private static void readCodes() throws IOException{
        File text = new File("src/main/java/com/lp/tools/codes.txt");
        BufferedReader reader = new BufferedReader(new FileReader(text));
        String st;
        while( (st = reader.readLine()) != null){
            Code newcode = new Code(st.substring(0,st.indexOf(' ')).trim() , st.substring(st.indexOf(' '),st.length()).trim());
            addCode(newcode);
        }
        reader.close();
    }

    // Add a letter and its morse translation to the current code list

    private static void addCode(Code nc){
        CodeList temp1, temp2;

        if(codeList == null){
            temp1 = new CodeList(nc,null);
            codeList = temp1;
        } else {
            for(temp1 = codeList; temp1.getNext() != null; temp1 = temp1.getNext());
            temp2 = new CodeList(nc, null);
            temp1.setNext(temp2);
        }
    }

    // Add a new letter/number translation to the current translation file
    
    public static void addCodeToMorse(String newCode) throws IOException{
        String dest = "src/main/java/com/lp/tools/codes.txt";

        FileWriter fw = new FileWriter(dest, true);
        fw.append(newCode);
        fw.close();
    }

    public static void reloadCodes(){
        codeList = null;
    }
    
    public static BufferedReader getReader(String name) throws IOException{
        String dest = "";
        if(text != null){
            text = null;
        }
        text = new File(dest+name);
        BufferedReader reader = new BufferedReader(new FileReader(text));
        return reader;
    }
}
