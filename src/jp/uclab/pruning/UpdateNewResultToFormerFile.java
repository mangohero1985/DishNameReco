/**
 * 
 */
package jp.uclab.pruning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.httpclient.RedirectException;

/**
 * @author mangohero1985
 * @create-time     Jul 31, 2013   9:48:56 PM   
 */
public class UpdateNewResultToFormerFile {
	
	public void update(BufferedReader br, BufferedWriter bw) throws IOException{
		
		String ReadString;
		
		while((ReadString=br.readLine())!=null){
			
			bw.write(ReadString);
			bw.newLine();
			bw.flush();
			
		}
		
		bw.close();
		br.close();
	}

}
