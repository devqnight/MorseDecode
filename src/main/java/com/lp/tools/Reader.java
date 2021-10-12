package com.lp.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import com.lp.StartApp;

public class Reader {
    private static File text;

    private static CodeList codeList;

    
    /** 
     * @return CodeList
     * @throws IOException
     */
    public static CodeList getCodes() throws IOException{
        if(codeList == null){
            readCodes();
        }
        return codeList;
    }

    
    /** 
     * @throws IOException
     */
    // This function reads a .txt file and add all the translations to the code list

    private static void readCodes() throws IOException{
        File text = new File("codes/codes.txt");//getFileInJar("../codes/codes.txt");
        BufferedReader reader = new BufferedReader(new FileReader(text));
        String st;
        while( (st = reader.readLine()) != null){
            Code newcode = new Code(st.substring(0,st.indexOf(' ')).trim() , st.substring(st.indexOf(' '),st.length()).trim());
            addCode(newcode);
        }
        reader.close();
    }

    
    /** 
     * @param nc
     */
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

    
    /** 
     * @param newCode
     * @throws IOException
     */
    // Add a new letter/number translation to the current translation file
    
    public static void addCodeToMorse(String newCode) throws IOException{
        String dest = "codes/codes.txt";

        FileWriter fw = new FileWriter(dest,true);//getFileInJar(dest), true);
        fw.append(newCode);
        fw.close();
    }

    public static void reloadCodes(){
        codeList = null;
    }
    
    
    /** 
     * @param name
     * @return BufferedReader
     * @throws IOException
     */
    public static BufferedReader getReader(String name) throws IOException{
        String dest = "";
        if(text != null){
            text = null;
        }
        text = new File(dest+name);//getFileInJar(dest+name);
        BufferedReader reader = new BufferedReader(new FileReader(text));
        return reader;
    }

    
    /** 
     * @param resourcePath
     * @return File
     * @throws IOException
     */
    public static File getFileInJar(String resourcePath) throws IOException{
        InputStream is = StartApp.class.getResourceAsStream(resourcePath);
        if (is == null) {
            System.out.println("is null");
        }
        File tempFile = File.createTempFile(String.valueOf(is.hashCode()),"");
        tempFile.deleteOnExit();

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            //copy stream
            byte[] buffer = new byte[1024];
            int bytesRead;
        
            while ((bytesRead = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }
}
