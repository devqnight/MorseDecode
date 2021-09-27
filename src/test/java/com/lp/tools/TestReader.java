package com.lp.tools;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TestReader {
    
    @Test
    public void testGetCodes() throws IOException{
        Assert.assertEquals(Reader.getCodes().getClass(), CodeList.class);
    }

    @Test
    public void testGetCodesExists() throws IOException{
        Assert.assertNotNull(Reader.getCodes());
    }

    @Test
    public void testGetReader() throws IOException{
        Assert.assertEquals(Reader.getReader("decode/text.txt").getClass(), BufferedReader.class);
    }

    @Test
    public void testGetReaderExists() throws IOException{
        Assert.assertNotNull(Reader.getReader("decode/text.txt"));
    }

    @Test(expected = IOException.class)
    public void testGetReaderFileNotExists() throws IOException{
        Assert.assertNotNull(Reader.getReader("decode/texte.txt"));
    }
}