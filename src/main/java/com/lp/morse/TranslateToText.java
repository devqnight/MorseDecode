package com.lp.morse;


import com.lp.exceptions.MorseBusinessException;

public class TranslateToText {
    public static String toText(MorseNode tree,String morse) throws MorseBusinessException{
        String text ="";

        String[] words = morse.split(" ");

        for(String word : words){
            String[] res = newLineChar(word, text);
            word = res[0];
            text = res [1];
            if(word.equals("/")){
                text += " ";
            } else {
                text += toChar(tree, word, 0);
            }
        }
        return text.trim();
    }

    private static String[] newLineChar(String word, String text){
        while(word.charAt(0)=='\n'){
            text += "\n";
            word = word.substring(1, word.length());
        }
        String[] res =  {word, text};
        return res;
    }

    private static String toChar(MorseNode tree, String word, int index) throws MorseBusinessException{
        if(index == word.length()){
            return tree.getLetter();
        } else if(word.charAt(index) == '.'){
            if(tree.getLeft() == null) throw new MorseBusinessException(word);
            return toChar(tree.getLeft(), word, index+1);
        } else if(word.charAt(index) == '-'){
            if(tree.getRight() == null) throw new MorseBusinessException(word);
            return toChar(tree.getRight(), word, index+1);
        } else {
            throw new MorseBusinessException(String.valueOf(word.charAt(index)));
        }
    }
}
