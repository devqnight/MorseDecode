package com.lp.morse;

import java.io.IOException;

import com.lp.tools.CodeList;
import com.lp.tools.Reader;

public class MorseTree {

    private static MorseNode tree;

    
    /** 
     * Creates MorseNode original point, and calls the function to fill it
     * @throws IOException
     */
    public static void createTree() throws IOException{
        CodeList originList = Reader.getCodes();
        tree = new MorseNode();
        createBranch(originList);
    }

    
    /** 
     * reloads the tree by deleting it, will be called by translator later
     * @throws IOException
     */
    public static void reloadTree() throws IOException{
        tree = null;
    }

    
    /** 
     * Singleton to return whole morse tree
     * @return MorseNode : morse tree
     * @throws IOException
     */
    public static MorseNode getTree() throws IOException{
        if(tree == null){
            createTree();
        }
        return tree;
    }

    
    /** 
     * Creates a branch of the binary tree of the morse code
     * @param node : original code in chained list
     */
    private static void createBranch(CodeList node){
        while(node != null){
            int lengthOfCode = node.getCode().getCode().length();
            
            MorseNode codeNode = tree;
            for(int i=0; i < lengthOfCode; i++){
                char code = node.getCode().getCode().charAt(i);
                if(code == '.'){
                    if(codeNode.getLeft() == null){
                        MorseNode newLeft = new MorseNode();
                        codeNode.setLeft(newLeft);
                        if(i == lengthOfCode-1){
                            codeNode.getLeft().setMorseCode(node.getCode());
                        } else {
                            codeNode = newLeft;
                        }
                    } else {
                        if(i == lengthOfCode-1){
                            codeNode.getLeft().setMorseCode(node.getCode());
                        }
                        codeNode = codeNode.getLeft();
                    }
                }
                if(code == '-'){
                    if(codeNode.getRight() == null){
                        MorseNode newRight = new MorseNode();
                        codeNode.setRight(newRight);
                        if(i == lengthOfCode-1){
                            codeNode.getRight().setMorseCode(node.getCode());
                        } else {
                            codeNode = newRight;
                        }
                    } else {
                        if(i == lengthOfCode-1){
                            codeNode.getRight().setMorseCode(node.getCode());
                        }
                        codeNode = codeNode.getRight();
                    }
                }
            }
            node = node.getNext();
        }
    }
}
