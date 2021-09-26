package com.lp.morse;

import java.io.IOException;

import com.lp.exceptions.MorseBusinessException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTranslator {
    private Translator testTranslator;

    @Before
    public void initTestTranslator() throws IOException{
        this.testTranslator = Translator.getTranslator();
    }

    @Test
    public void testGetTranslator() throws IOException{
        Assert.assertNotNull(Translator.getTranslator());
    }

    @Test
    public void testToTextHelloWorld() throws MorseBusinessException{
        Assert
            .assertEquals(
                testTranslator
                    .toText(".... . .-.. .-.. --- / .-- --- .-. .-.. -..")
                    , "HELLO WORLD");
    }

    @Test(expected = MorseBusinessException.class)
    public void testToTextHelloWorldFail() throws MorseBusinessException{
        Assert
            .assertEquals(
                testTranslator
                    .toText(".?... . .-.. .-.. --- / .-- --- .-. .-.. -..")
                    , "HELLO WORLD");
    }

    @Test
    public void testToMorseHelloWorld() throws MorseBusinessException{
        Assert
            .assertEquals(
                testTranslator
                    .toMorse("HELLO WORLD")
                    , ".... . .-.. .-.. --- / .-- --- .-. .-.. -..");
    }

    @Test(expected = MorseBusinessException.class)
    public void testToMorseHelloWorldFail() throws MorseBusinessException{
        Assert
            .assertEquals(
                testTranslator
                    .toMorse("HELLO WORLD!")
                    , ".... . .-.. .-.. --- / .-- --- .-. .-.. -..");
    }
}
