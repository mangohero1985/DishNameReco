/**
 * 
 */
package jp.uclab.TestingTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author mangohero1985
 * @create-time Jul 30, 2013 12:19:59 PM
 */
public class RetrivalNumSorting {

	/**
	 * @throws Exception
	 * @throws
	 * @分别给三种词性的文件按照"なんにたべ"检索的数字进行排序
	 */
	public static void main(String[] args) throws Exception {
		String inPath = "/Users/mangohero1985/Desktop/twitter/testResult/AllTestBayes.txt";
		String outPath = "/Users/mangohero1985/Desktop/twitter/testResult/AllTestBayesSorting.txt";
		String readString = null;

		FileReader fr = new FileReader(inPath);
		BufferedReader br = new BufferedReader(fr);

		FileWriter fw = new FileWriter(outPath);
		BufferedWriter bw = new BufferedWriter(fw);

		Map<String,Double > keyfreqs = new HashMap<String, Double>();

		while ((readString = br.readLine()) != null) {

			String[] splitReadStrings = readString.split("&");
			String menuNameAndCount = splitReadStrings[0];

			String[] spliteMenuNameAndCount = menuNameAndCount.split(" ");
			keyfreqs.put(spliteMenuNameAndCount[0], Double.parseDouble(spliteMenuNameAndCount[1]));

		}
		//对map按照value进行排序
		
		ArrayList<Entry<String, Double>> l = new ArrayList<Entry<String, Double>>(keyfreqs.entrySet());

		Collections.sort(l, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				Double v1 = o1.getValue();
				Double v2 = o2.getValue();
				return v1.compareTo(v2);
			}
		});

		for (Entry<String, Double> e : l) {
			System.out.println(e.getKey() + " " + e.getValue());
		bw.write(e.getKey() + " " + e.getValue());
		bw.newLine();
		bw.flush();
		}

		System.out.println("处理完成");
		bw.close();
		br.close();

	}

}
