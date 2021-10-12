package com.lp.tools;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestReader {
    
    
    /** 
     * @throws IOException
     */
    @Test
    public void testGetCodes() throws IOException{
        Assert.assertEquals(Reader.getCodes().getClass(), CodeList.class);
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testGetCodesExists() throws IOException{
        Assert.assertNotNull(Reader.getCodes());
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testGetReader() throws IOException{
        Assert.assertEquals(Reader.getReader("to_translate/text.txt").getClass(), BufferedReader.class);
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testGetReaderExists() throws IOException{
        Assert.assertNotNull(Reader.getReader("to_translate/text.txt"));
    }

    
    /** 
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void testGetReaderFileNotExists() throws IOException{
        Assert.assertNotNull(Reader.getReader("decode/texte.txt"));
    }
}
