package com.lp.morse;


import com.lp.tools.CodeList;

public class TranslateToText {
    public static String toText(CodeList codes, MorseNode tree,String morse){
        String text ="";

        String[] words = morse.split(" ");

        for(String word : words){
            if(word.equals("/")){
                text += " ";
            } else {
                text += toChar(tree, word, 0);
            }
        }
        return text.trim();
    }

    private static String toChar(MorseNode tree, String word, int index){
        if(index == word.length()){
            return tree.getLetter();
        } else if(word.charAt(index) == '.'){
            return toChar(tree.getLeft(), word, index+1);
        } else {
            return toChar(tree.getRight(), word, index+1);
        }
    }
}
