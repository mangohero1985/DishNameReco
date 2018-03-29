/**
 * 
 */
package jp.uclab.formatAbstract;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mangohero1985
 * @create-time Jul 2, 2013 12:28:59 PM
 */
public class MenuSplit {

	public void LatterLeftOfMenu(BufferedReader br, BufferedWriter bw,String ProcessingKeyword) throws IOException {

		String readsString = null;
		int i = 1;
		String dunhao = new String("、");
		while ((readsString = br.readLine()) != null) {
			if (readsString.startsWith("@")) i = 1;
			if (i == 0) {
				//System.out.println("メニューは" + readsString);
				bw.write(ProcessingKeyword + readsString);
				bw.newLine();
				bw.flush();
			}
			else if (readsString.contains(ProcessingKeyword)) {
				String[] substring = readsString.split(ProcessingKeyword);
				if (substring.length > 1) {
					if (substring[1].equals(dunhao)) i = 0;
					else {
						//System.out.println("メニューは" + substring[1]);
						bw.write(ProcessingKeyword + substring[1]);
						bw.newLine();
						bw.flush();
					}
				}

				else i = 0;

			}
			else continue;

		}
	  System.out.println("MenuSplit处理完成");
      bw.close();
      br.close();
	}
}
