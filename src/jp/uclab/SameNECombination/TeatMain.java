/**
 * 
 */
package jp.uclab.SameNECombination;

import java.io.IOException;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time     Feb 26, 2014   11:13:21 AM   
 */
public class TeatMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String inputPath = "/Users/mangohero1985/Desktop/CRF++MenuReco/RepeatedNECombination/NEWithoutNoisefinal.txt";
		String outputPath = "/Users/mangohero1985/Desktop/CRF++MenuReco/RepeatedNECombination/TransformIntoHiragana.txt";
		
		TransIntoHira transIntoHira = new TransIntoHira();
		transIntoHira.Transform(new IOhandle().FileReader(inputPath), new IOhandle().FileWriter(outputPath));
	}

}
