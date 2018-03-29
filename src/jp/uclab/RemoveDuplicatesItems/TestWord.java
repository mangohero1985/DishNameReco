/**
 * 
 */
package jp.uclab.RemoveDuplicatesItems;

/**
 * @author mangohero1985
 * @create-time     Jul 2, 2013   3:49:53 PM   
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.TreeSet;



public class TestWord {
	public void TestWordRemoveDuplicatesItems(String InputPath,String OutputPath) throws IOException {		
		//OrderedList<WordFreq> list=new OrderedList<WordFreq>();
		//分词并且只留下"名词一般"的name
//		MenuNameSplit menuNameSplit = new MenuNameSplit();
//		String InputPath= "/Users/mangohero1985/Desktop/twitterSearchResult/SpamingFewerThanFiveOutput.txt";
//		String outputPath ="/Users/mangohero1985/Desktop/twitterSearchResult/generalNounFromSpamingFewerThanFive.txt";
//		menuNameSplit.menuSplit(InputPath, outputPath);
		
		LinkedList<WordFreq> orderedList = new LinkedList<WordFreq>();
		String input = InputPath;
		try {
			Scanner fileIn = new Scanner(new FileReader(input));
			fileIn.useDelimiter("[\t\r\n., ]+");
			while (fileIn.hasNext()) {
				//read
				String str = fileIn.next().trim();
				WordFreq wf = new WordFreq(str);
				ListIterator<WordFreq> iter = search(orderedList, wf);
				if (iter != null) {
					//exists
					((WordFreq) iter.previous()).increment();
					//update the freq!!! 
					/* iter.set(wf);
					 iter=null;*/
				} else {
					//add it to the list
					OrderedList.insertOrder(orderedList, wf);
				}
			}
			fileIn.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//display ,order by word
		displayResults(orderedList,OutputPath);
		//desc order by freq
		displayOrderedResults(orderedList,OutputPath);
	}

	/**
	 * ����Ƶ�ʸߵ�����
	 * @param <T>
	 * @param list
	 * @throws IOException 
	 */
	private static <T extends WordFreq> void displayOrderedResults(Collection<T> list,String OutputPath) throws IOException {
		final TreeSet<T> set = new TreeSet<T>(
				new Comparator<T>() {
					public int compare(T o1, T o2) {
						// TODO Auto-generated method stub
						if (o1.getFreq() == o2.getFreq())
							return o1.equals2(o2);
						else
							return o2.getFreq() > o1.getFreq() ? 1 : -1;
					}
				});

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			set.add((T) iter.next());
		}
		System.out.println("-----------order by freq begin---------------");
		displayResults(set,OutputPath);
		System.out.println("-----------order by freq end-----------------");
	}

	private static <T> void displayResults(Collection<T> list,String OutputPath) throws IOException {
		String outPathString = OutputPath;
		FileWriter fw = new FileWriter(outPathString);
		BufferedWriter bw = new BufferedWriter(fw);
		
		Iterator<T> iter = list.iterator();
		int count = 1;
		while (iter.hasNext()) {
			T item = (T) iter.next();
			String[] Split = item.toString().split("\\(");
		    bw.write(Split[0]);
		    bw.newLine();
		    bw.flush();
			System.out.printf("%-14s", item);
			if (count % 4 == 0)
				System.out.println();
			count++;
		}
		System.out.println();
		System.out.println("TestWord处理完成");
		bw.close();
	}

	/**
	 * ��ѯ��ǰ�����Ƿ��ڼ�����
	 * 
	 * @param list
	 * @param target
	 * @return
	 */
	public static <T> ListIterator<T> search(LinkedList<T> list, T target) {
		ListIterator<T> iter = list.listIterator();
		while (iter.hasNext()) {
			T item = (T) iter.next();
			if (target.equals(item)) {
				return iter;
			}
		}
		return null;
	}

}
