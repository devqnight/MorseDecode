package com.lp.tools;

import java.io.IOException;

import com.lp.exceptions.MorseBusinessException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCodeClasses {
    private CodeList testList;

    
    /** 
     * @throws IOException
     */
    @Before
    public void initCodeClasses() throws IOException{
        this.testList = Reader.getCodes();
    }


    /**
     * TESTS FOR CODE CLASS
     */
    @Test
    public void testCreateCode(){
        Assert.assertEquals(new Code("A", ".-").toString(), this.testList.getCode().toString());
    }

    @Test
    public void testGetCodeCode(){
        Assert.assertEquals(this.testList.getCode().getCode(), ".-");
    }

    @Test
    public void testGetCodeLetter(){
        Assert.assertEquals(this.testList.getCode().getLetter(), "A");
    }

    @Test
    public void testSetCodeCode(){
        Code testCode = new Code("A", ".-");
        testCode.setCode("--");
        Assert.assertEquals(testCode.getCode(), "--");
    }

    @Test
    public void testSetCodeLetter(){
        Code testCode = new Code("A", ".-");
        testCode.setLetter("B");
        Assert.assertEquals(testCode.getLetter(), "B");
    }

    @Test
    public void testToStringCode(){
        Code testCode = new Code("A", ".-");
        Assert.assertEquals(testCode.toString(), "A => .-");
    }

    /**
     * TEST FOR CODELIST CLASS
     */
    @Test 
    public void testDecode() throws IOException{
        this.testList = null;
        this.testList = Reader.getCodes();
        Assert.assertEquals(this.testList.deCode(".-"), "A");
        Assert.assertEquals(this.testList.deCode("-..."), "B");
        Assert.assertEquals(this.testList.deCode("-.-."), "C");
        Assert.assertEquals(this.testList.deCode("-.."), "D");
        Assert.assertEquals(this.testList.deCode("."), "E");
        Assert.assertEquals(this.testList.deCode("..-."), "F");
        Assert.assertEquals(this.testList.deCode("--."), "G");
        Assert.assertEquals(this.testList.deCode("...."), "H");
        Assert.assertEquals(this.testList.deCode(".."), "I");
        Assert.assertEquals(this.testList.deCode(".---"), "J");
        Assert.assertEquals(this.testList.deCode("-.-"), "K");
        Assert.assertEquals(this.testList.deCode(".-.."), "L");
        Assert.assertEquals(this.testList.deCode("--"), "M");
        Assert.assertEquals(this.testList.deCode("-."), "N");
        Assert.assertEquals(this.testList.deCode("---"), "O");
        Assert.assertEquals(this.testList.deCode(".--."), "P");
        Assert.assertEquals(this.testList.deCode("--.-"), "Q");
        Assert.assertEquals(this.testList.deCode(".-."), "R");
        Assert.assertEquals(this.testList.deCode("..."), "S");
        Assert.assertEquals(this.testList.deCode("-"), "T");
        Assert.assertEquals(this.testList.deCode("..-"), "U");
        Assert.assertEquals(this.testList.deCode("...-"), "V");
        Assert.assertEquals(this.testList.deCode(".--"), "W");
        Assert.assertEquals(this.testList.deCode("-..-"), "X");
        Assert.assertEquals(this.testList.deCode("-.--"), "Y");
        Assert.assertEquals(this.testList.deCode("--.."), "Z");
    }

    @Test
    public void testIsIn(){
        Assert.assertEquals(this.testList.isIn("A"), ".-");
        Assert.assertEquals(this.testList.isIn("B"), "-...");
        Assert.assertEquals(this.testList.isIn("C"), "-.-.");
        Assert.assertEquals(this.testList.isIn("D"), "-..");
        Assert.assertEquals(this.testList.isIn("E"), ".");
        Assert.assertEquals(this.testList.isIn("F"), "..-.");
        Assert.assertEquals(this.testList.isIn("G"), "--.");
        Assert.assertEquals(this.testList.isIn("H"), "....");
        Assert.assertEquals(this.testList.isIn("I"), "..");
        Assert.assertEquals(this.testList.isIn("J"), ".---");
        Assert.assertEquals(this.testList.isIn("K"), "-.-");
        Assert.assertEquals(this.testList.isIn("L"), ".-..");
        Assert.assertEquals(this.testList.isIn("M"), "--");
        Assert.assertEquals(this.testList.isIn("N"), "-.");
        Assert.assertEquals(this.testList.isIn("O"), "---");
        Assert.assertEquals(this.testList.isIn("P"), ".--.");
        Assert.assertEquals(this.testList.isIn("Q"), "--.-");
        Assert.assertEquals(this.testList.isIn("R"), ".-.");
        Assert.assertEquals(this.testList.isIn("S"), "...");
        Assert.assertEquals(this.testList.isIn("T"), "-");
        Assert.assertEquals(this.testList.isIn("U"), "..-");
        Assert.assertEquals(this.testList.isIn("V"), "...-");
        Assert.assertEquals(this.testList.isIn("W"), ".--");
        Assert.assertEquals(this.testList.isIn("X"), "-..-");
        Assert.assertEquals(this.testList.isIn("Y"), "-.--");
        Assert.assertEquals(this.testList.isIn("Z"), "--..");
    }

    @Test
    public void testSetNext(){
        Code testCode = new Code("A", ".-");
        CodeList test = new CodeList(testCode, null);
        CodeList testNext = new CodeList(new Code("B", "-..."), null);
        test.setNext(testNext);
        Assert.assertEquals(test.getNext(),testNext);
    }

    @Test
    public void testToStringList(){
        Code testCode = new Code("A", ".-");
        CodeList test = new CodeList(testCode, null);
        Assert.assertEquals(test.toString(), " "+testCode.getLetter()+" => "+testCode.getCode() +"  \n" +"");
    }

    
    /** 
     * @throws MorseBusinessException
     */
    @Test
    public void testDeleteElement() throws MorseBusinessException{
        testList.addCode(new Code("0","---."));
        testList.deleteCode("0");
        Assert.assertEquals("(/)",testList.isIn("0"));
    }

    
    /** 
     * @throws MorseBusinessException
     */
    @Test(expected = MorseBusinessException.class)
    public void testDeleteElementFail() throws MorseBusinessException{
        testList.deleteCode("/");
    }

    @After
    public void destroy(){
        this.testList = null;
    }
}
