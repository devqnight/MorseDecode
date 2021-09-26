package com.lp.morse;

import java.io.IOException;

import com.lp.tools.Code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMorseObjects {
    private MorseNode testTree;

    @Before
    public void initTestMorseObjects() throws IOException{
        this.testTree = MorseTree.getTree();
    }

    /**
     * MorseTree TESTS
     */

    @Test
    public void testCreateTree() throws IOException{
        Assert.assertNotNull(MorseTree.getTree());
    }

    /**
     * MorseNode TESTS
     */

    @Test
    public void testCreateNode(){
        Assert.assertNotNull(new MorseNode());
    }

    @Test
    public void testSetLeft(){
       MorseNode testNode = this.testTree;
       MorseNode testLeftNode = new MorseNode();
       testNode.setLeft(testLeftNode);
       Assert.assertEquals(testNode.getLeft(), testLeftNode);
    }

    @Test
    public void testSetRight(){
       MorseNode testNode = this.testTree;
       MorseNode testRightNode = new MorseNode();
       testNode.setRight(testRightNode);
       Assert.assertEquals(testNode.getRight(), testRightNode);
    }

    @Test
    public void testSetLetter(){
        MorseNode testNode = new MorseNode();
        testNode.setLetter("A");
        Assert.assertEquals(testNode.getLetter(), "A");
    }

    @Test
    public void testSetCode(){
        MorseNode testNode = new MorseNode();
        testNode.setCode(".-");
        Assert.assertEquals(testNode.getCode(), ".-");
    }

    @Test
    public void testSetMorseCode(){
        MorseNode testNode = new MorseNode();
        Code code = new Code("A", ".-");
        testNode.setMorseCode(code);
        Assert.assertEquals(testNode.getMorseCode(), code);
    }

    @Test
    public void testSetMorseCodeStrings(){
        MorseNode testNode = new MorseNode();
        testNode.setMorseCode("A",".-");
        Assert.assertEquals(testNode.getMorseCode().toString(), new Code("A",".-").toString());
    }
}
