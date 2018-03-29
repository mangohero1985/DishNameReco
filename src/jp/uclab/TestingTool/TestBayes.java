/**
 * 
 */
package jp.uclab.TestingTool;

import java.io.IOException;

import jp.uclab.io.IOhandle;

/**
 * @author mangohero1985
 * @create-time     Aug 6, 2013   2:07:23 PM   
 */
public class TestBayes {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String InputPath = "/Users/mangohero1985/Desktop/twitter/combinationResult/19-500-150-100/conbination-19-500-150-100.txt";
		String OutputPath = "/Users/mangohero1985/Desktop/twitter/testResult/AllTestBayes.txt";
		String AdditionWord = "料理";
		
		RemoveNoiseByBeyas removeNoiseByBeyas = new RemoveNoiseByBeyas();
		removeNoiseByBeyas.Remove(new IOhandle().FileReader(InputPath), new IOhandle().FileWriterContinue(OutputPath), AdditionWord);
		

	}

}
