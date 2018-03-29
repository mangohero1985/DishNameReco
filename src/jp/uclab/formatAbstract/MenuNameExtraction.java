/**
 * 
 */
package jp.uclab.formatAbstract;

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
 * @create-time Jul 2, 2013 12:29:29 PM
 */
public class MenuNameExtraction {

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


	public void MenuNameExtractionByPattern(BufferedReader br, BufferedWriter bw) throws IOException {
		Tagger tagger = new Tagger();
		String readString;

		while ((readString = br.readLine()) != null) {
			//System.out.println(readString);
			tagger.parse(readString);
			Node node = tagger.parseToNode(readString);
			// System.out.println(node.getSurface()+" "+node.getFeature());
			ArrayList<String> nameFeatureArrayList = new ArrayList<String>();
			for (; node != null; node = node.getNext()) {
				// System.out.println(node.getSurface()+" "+node.getFeature());
				if (node.getFeature().contains("名詞,一般") || node.getFeature().contains("助詞,連体化") || node.getFeature().contains("動詞,自立") || node.getFeature().contains("接頭詞,名詞接続")
						|| node.getFeature().contains("名詞,接尾")|| node.getFeature().contains("名詞,固有名詞")) {
					if (nameFeatureArrayList.isEmpty()) nameFeatureArrayList.add(node.getSurface());
					else {
						if (node.getPrev().getFeature().contains("名詞,一般") || node.getPrev().getFeature().contains("助詞,連体化") || node.getPrev().getFeature().contains("動詞,自立")
								|| node.getPrev().getFeature().contains("接頭詞,名詞接続") || node.getPrev().getFeature().contains("名詞,接尾,一般")|| node.getPrev().getFeature().contains("名詞,固有名詞")) {
							nameFeatureArrayList.add(node.getSurface());

						}
						else {

							nameFeatureArrayList.clear();
						}
					}
				}
				else {
					if (nameFeatureArrayList.size() >= 2 && !nameFeatureArrayList.get(0).contains("の")) {
						String NameArray=nameFeatureArrayList.get(0).toString();
						for(int k = 1; k<nameFeatureArrayList.size();k++){
							NameArray=NameArray.concat(nameFeatureArrayList.get(k).toString());
						}
						//System.out.println(NameArray);
						bw.write(NameArray);
						bw.newLine();
						bw.flush();
						
					}
					nameFeatureArrayList.clear();
				}

			}

		}
		System.out.println("MenuNameExtraction处理完成");
		  bw.close();
	      br.close();
	}
}
