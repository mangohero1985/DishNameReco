/**
 * 
 */
package jp.uclab;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jp.uclab.RemoveDuplicatesItems.TestWord;
import jp.uclab.formatAbstract.MenuNameExtraction;
import jp.uclab.formatAbstract.MenuSplit;
import jp.uclab.formatAbstract.SearchTweets;
import jp.uclab.io.IOhandle;
import jp.uclab.pruning.MenuNameNumberCount;
import jp.uclab.pruning.MenuRemoveDuplicats;
import jp.uclab.pruning.PruningFewerThanN;
import jp.uclab.pruning.UpdateNewResultToFormerFile;

/**
 * @author mangohero1985
 * @create-time     Jul 2, 2013   12:22:52 PM   
 */
public class ProcessingTestMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String TwitterSearchKeyWord="メニューは -RT";//"メニューは"
		String ProcessingKeyword="メニューは";
		String[] Keys = {"GeneralNoun","NounTail","VerbJiLiTu","ProperNoun"};
		Integer[] Times = {19,500,150,100};
		String verb ="を食べ";
		String additionalNoun = "料理";
		String filePath ="/Users/mangohero1985/Desktop/twitter/twitterTemporarybyMenu/2013-8-8/";
		String sourceFileName="MenuSplitSource.txt";
		
		//将要存储的文件路径传给SearchTwitter
		SearchTweets searchTweets = new SearchTweets();
		searchTweets.Search(new IOhandle().FileWriter(filePath+sourceFileName),TwitterSearchKeyWord);
		//名词一般设置19,名词词尾设置500,动词自立设置150,专有名词设置100
		int n1 =Times[0];
		String key1 = Keys[0];
		proceesingMethod(verb,additionalNoun,n1,key1,filePath,sourceFileName,ProcessingKeyword);
		int n2 =Times[1];
		String key2 = Keys[1];
		proceesingMethod(verb,additionalNoun,n2,key2,filePath,sourceFileName,ProcessingKeyword);
		int n3 =Times[2];
		String key3 = Keys[2];
		proceesingMethod(verb,additionalNoun,n3,key3,filePath,sourceFileName,ProcessingKeyword);
		int n4 =Times[3];
		String key4 = Keys[3];
		proceesingMethod(verb,additionalNoun,n4,key4,filePath,sourceFileName,ProcessingKeyword);
	}

	/**
	 * @throws IOException
	 */
	private static void proceesingMethod(String verb,String additionalNoun,int n,String key,String filePath,String sourceFileName,String ProcessingKeyword) throws IOException {
		//设置检索条件
		String Verb = verb;
		String AdditionalNoun = additionalNoun;
		String Key = key;
		
		//设定检索结果下限
		int N = n;
		
		//设置要处理的三种词性和分别要设置的最低限词频;
		Map<String, String> PartOfSpeech = new LinkedHashMap<String, String>();
		PartOfSpeech.put("GeneralNoun", "名詞,一般");
		PartOfSpeech.put("NounTail", "名詞,接尾");
		PartOfSpeech.put("VerbJiLiTu", "動詞,自立");
		PartOfSpeech.put("ProperNoun", "名詞,固有名詞");
		
		//是用twiiterSearch的搜索结果进行处理得到只含"メーニュは"和它之后的文本的文件
		String SourceInputPath = filePath + sourceFileName;
		String ResultOutputPath = filePath+ "MenuSplitResult.txt";
		MenuSplit menuSplit = new MenuSplit();
		menuSplit.LatterLeftOfMenu(new IOhandle().FileReader(SourceInputPath), new IOhandle().FileWriter(ResultOutputPath),ProcessingKeyword);
		
		//用上一步的结果,对文本文件进行相关词性规则方面的抽取,得到更纯净的数据
		String extractionInput = filePath + "MenuSplitResult.txt";
		String extractionOutput = filePath + "NameExtractionResult.txt";
		MenuNameExtraction menuNameExtraction = new MenuNameExtraction();
		menuNameExtraction.MenuNameExtractionByPattern(new IOhandle().FileReader(extractionInput), new IOhandle().FileWriter(extractionOutput));
		
		//用上一步得到的结果,去除重复的Menu词条,只留下词性为"名詞,一般"/"名詞,接尾"/"動詞,自立"的结果.
		String MenuRemoveDuplicatsInputPath = filePath + "NameExtractionResult.txt";
		String MenuRemoveDuplicatsOutPath = filePath + "NameRemoveDuplicateResultSpliteBy"+Key+".txt";
		MenuRemoveDuplicats menuRemoveDuplicats = new MenuRemoveDuplicats();
		menuRemoveDuplicats.RemoveDuplicates(new IOhandle().FileReader(MenuRemoveDuplicatsInputPath), new IOhandle().FileWriter(MenuRemoveDuplicatsOutPath), PartOfSpeech.get(Key).toString());
		
		//用上部得到的结果,除去重复的词条
	    String TestWordInputPath =filePath + "NameRemoveDuplicateResultSpliteBy"+Key+".txt";
	    String TestWordOutPath = filePath + "TestWord"+Key+".txt";
	    TestWord testWord = new TestWord();
	    testWord.TestWordRemoveDuplicatesItems(TestWordInputPath, TestWordOutPath);
	    
	    //用上面得到的结果使用rakuten搜索引擎进行处理,设置搜索规则
	    String MenuNameNumberCountInputPath = filePath + "TestWord"+Key+".txt";
		String MenuNameNumberCountOutputPath = filePath + "MenuNameNumberCountSplitBy"+Key+".txt";
		MenuNameNumberCount menuNameNumberCount = new MenuNameNumberCount();
		menuNameNumberCount.DenoteZeroName(new IOhandle().FileReader(MenuNameNumberCountInputPath), new IOhandle().FileWriter(MenuNameNumberCountOutputPath),Verb,AdditionalNoun);
	    
		//用上面得到的结果,移除统计条目小于N的items
		String PruningFewerThanNInputPath =filePath + "MenuNameNumberCountSplitBy"+Key+".txt";
		String PruningFewerThanNOutputPaht = filePath + "ResultAboutAll"+Key+".txt";
		PruningFewerThanN pruningFewerThanN = new PruningFewerThanN();
		pruningFewerThanN.deleteFewerThanFive(new IOhandle().FileReader(PruningFewerThanNInputPath), new IOhandle().FileWriter(PruningFewerThanNOutputPaht), N);
		
		//追加新收集的结果到以前的累积的文件
		String UpdateNewResultToFormerFileInputPath = filePath + "ResultAboutAll"+Key+".txt";
		String UpdateNewResultToFormerFileOutputPath = "/Users/mangohero1985/Desktop/twitter/twitterTemporarybyMenu/19-500-150-100/"+"ResultAboutAll"+Key+"-19-500-150-100.txt";
		UpdateNewResultToFormerFile updateNewResultToFormerFile = new UpdateNewResultToFormerFile();
		updateNewResultToFormerFile.update(new IOhandle().FileReader(UpdateNewResultToFormerFileInputPath), new IOhandle().FileWriterContinue(UpdateNewResultToFormerFileOutputPath));
		
		//移除每次累积中重复的条目
		String RemoveDuplicateForAllResultInputPath= "/Users/mangohero1985/Desktop/twitter/twitterTemporarybyMenu/19-500-150-100/"+"ResultAboutAll"+Key+"-19-500-150-100.txt";
		String RemoveDuplicateForAllResultOutputPath ="/Users/mangohero1985/Desktop/twitter/combinationResult/19-500-150-100(menu)/ResultAboutAll"+Key+"-19-500-150-100.txt";
		TestWord testWordRemoveDuplicateForAllResult = new TestWord();
		testWordRemoveDuplicateForAllResult.TestWordRemoveDuplicatesItems(RemoveDuplicateForAllResultInputPath, RemoveDuplicateForAllResultOutputPath);
	}

}
