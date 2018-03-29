/**
 * 
 */
package jp.uclab.formatAbstract;

import twitter4j.*;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Timer;

/**
 * @author mangohero1985
 * @create-time     Jul 31, 2013   9:11:51 PM   
 */
public class SearchTweets {

	public void Search(BufferedWriter bw,String TwitterSearchKeyWord) throws IOException{
		
		Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(TwitterSearchKeyWord);//搜索的结果可以在这里更改
            QueryResult result;
            //设置为90的意义是,15分钟内发送90次请求(API的Search请求是每15分钟180次),90次差不多正好返回一天的twitters.
            int i =90;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getCreatedAt() +tweet.getUser().getScreenName() + " - " + tweet.getText());
                    bw.write("@" + tweet.getUser().getScreenName() + " - " + tweet.getCreatedAt() + " - " + tweet.getText());
                    bw.flush();
                    bw.newLine();
                    
                }
                i--;
            } while ((query = result.nextQuery()) != null&&i>0);
            bw.close();
           // System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
}
