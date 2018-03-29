/**
 * 
 */
package jp.uclab.RemoveDuplicatesItems;

/**
 * @author mangohero1985
 * @create-time     Jul 2, 2013   3:52:16 PM   
 */
import java.util.LinkedList;
import java.util.ListIterator;

 
public class  OrderedList<T> extends LinkedList {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static <T extends Comparable<? super T>>
	void insertOrder(LinkedList<T>orderedList,T item){
		ListIterator<T> iter=orderedList.listIterator();
		
		while(iter.hasNext()){
			T t=(T)iter.next();
			if(t.compareTo(item)==1){
				iter.previous();
				iter.add(item);
				break;
			}
		}
		if(!iter.hasNext())
			iter.add(item);
		
	}
}
