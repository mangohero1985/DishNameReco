/**
 * 
 */
package jp.uclab.pruning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mangohero1985
 * @create-time     Jul 2, 2013   4:55:46 PM   
 */
public class PruningFewerThanN {

	public void deleteFewerThanFive(BufferedReader br, BufferedWriter bw, int N) throws IOException {

		String readString;

		while ((readString = br.readLine()) != null) {

			String[] splitReadStrings = readString.split("&");
			String menuNameAndCount = splitReadStrings[0];

			String[] spliteMenuNameAndCount = menuNameAndCount.split(" ");
			int count = Integer.parseInt(spliteMenuNameAndCount[1]);
			if (count <= N) continue;
			System.out.println(spliteMenuNameAndCount[0]);
			bw.write(spliteMenuNameAndCount[0]);
			bw.newLine();
			bw.flush();
		}
		System.out.println("PruningFewerThanN处理完成");
		bw.close();
		br.close();
	}
}
