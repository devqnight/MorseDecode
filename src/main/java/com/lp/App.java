package com.lp;

import com.lp.dao.enums.ETypeDao;
import com.lp.dao.factory.DaoFactory;
import com.lp.dao.io.DaoMorse;
import com.lp.morse.MorseNode;
import com.lp.morse.MorseTree;
import com.lp.morse.Translator;

//import com.lp.tools.Reader;

public class App {

    public static void main(String[] args) {
        try {
            /* Reader rd = new Reader();

            System.out.println(rd.getCodes()); */
            Translator tl = Translator.getTranslator();
            /* MorseNode tree = MorseTree.getTree();
            tree.displayTree(); */
            /* String msg = "hello world";
            msg = msg.toUpperCase();
            String resultMorse = tl.toMorse(msg);
            System.out.println(msg + " = " + resultMorse);
            String resultText = tl.toText(resultMorse);
            boolean res = resultText.equals(msg);
            System.out.println(msg + " = " + resultMorse + " => " + resultText + " :==> " + res);
            DaoMorse dao = (DaoMorse) DaoFactory.getDaoFactory(ETypeDao.IO).getDaoMorse();
            dao.writeTextToFile("test.txt",resultMorse); */

            System.out.println(tl.toText(".... . .-.. .-.. --- / .-- --- .-. .-.. -.."));
        } catch(Exception ioe){
            ioe.printStackTrace();
            System.out.println(ioe + " : "+ ioe.getMessage());
        }
    }
}
