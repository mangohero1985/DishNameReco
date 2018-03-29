/**
 * 
 */
package jp.uclab.SameNECombination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import jp.uclab.io.*;
/**
 * @author mangohero1985
 * @create-time     Feb 26, 2014   4:21:58 PM   
 */
public class spliteMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String inputString = "/Users/mangohero1985/Desktop/CRF++MenuReco/RepeatedNECombination/TransformIntoHiragana.txt";
		String outputString = "/Users/mangohero1985/Desktop/CRF++MenuReco/RepeatedNECombination/Hiragana.txt";
		
        BufferedReader br = new IOhandle().FileReader(inputString);
        BufferedWriter bw = new IOhandle().FileWriter(outputString);
        
        String readStr = null;
        while((readStr= br.readLine())!=null){
        	
        	String[] StrSplite = readStr.split("\t");
        	bw.write(StrSplite[1]);
        	bw.newLine();
        	bw.flush();
        }
       br.close();
       bw.close();
	}

}
