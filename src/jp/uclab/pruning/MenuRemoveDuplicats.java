/**
 * 
 */
package jp.uclab.pruning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.chasen.mecab.Node;
import org.chasen.mecab.Tagger;

/**
 * @author mangohero1985
 * @create-time Jul 2, 2013 1:48:36 PM
 */
public class MenuRemoveDuplicats {

	static {
		try {
//			File f = new File("/Users/mangohero1985/Documents/japanese-morphology/mecab-java-0.995/libMeCab.so");
//			System.load(f.toString());
			 System.loadLibrary("MeCab");
		}
		catch (UnsatisfiedLinkError e) {
			System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
			System.exit(1);
		}
	}

	public void RemoveDuplicates(BufferedReader br, BufferedWriter bw,String PartOfSpeech) throws IOException {

		String readString;
		ArrayList<String> menuNameArrayList = new ArrayList<String>();

		Tagger tagger = new Tagger();

		while ((readString = br.readLine()) != null) {
			int j = 0;
			if (menuNameArrayList.size() == 0) {
				menuNameArrayList.add(readString);
				continue;
			}
			else {
				for (int i = 0; i < menuNameArrayList.size(); i++) {
					if (menuNameArrayList.get(i).contains(readString)) j = 1;
				}
				if (j == 0) {
					menuNameArrayList.add(readString);
				}
			}
		}
		for (int k = 0; k < menuNameArrayList.size(); k++) {
			//System.out.println(menuNameArrayList.get(k));
			Node node = tagger.parseToNode(menuNameArrayList.get(k));
			tagger.parse(menuNameArrayList.get(k));
			for (; node != null; node = node.getNext()) {
				int NounCount = 0;
				if (node.getFeature().contains("BOS/EOS")) continue;
	
				if (node.getFeature().contains(PartOfSpeech)) {
					NounCount++;
				}
				if (NounCount != 0) {
					bw.write(node.getSurface());
					bw.flush();
					bw.newLine();
				}

			}

		}
		System.out.println("RemoveDuplicates处理完成");
		bw.close();
		br.close();
	}
}
