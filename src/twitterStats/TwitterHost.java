package twitterStats;

import twitter4j.*;

import twitter4j.*;

import twitter4j.auth.AccessToken;

import twitter4j.auth.AccessToken;

import java.io.IOException;

public class TwitterHost {
	
	private final static String CONSUMER_KEY = "0Jq1pqgM0GNfSkgtMQPyrvadl";
	private final static String CONSUMER_SECRET = "dRd1hH5nnGpMd4tMZnE6KuIqOdBsbtfPJH2qfRlBPLeILrSgZI";
	private final static String ACCESS_TOKEN = "61495742-GIbmeqRshY5fCYOQMHVrnOORKc8zLeDMNZkUUvRUg";
	private final static String ACCESS_TOKEN_SECRET = "9UsiI8xzIrgbZFd35rpw9a1frXFfZlzoq7zqzwxi7VjA0";
	
	private TwitterFactory twitterFactory;
	private Twitter twitter;

	public TwitterHost(){
		twitterFactory = new TwitterFactory();
        twitter = twitterFactory.getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));
	}
	
	public User getUserDetails(String userName) throws TwitterException{
		User user = twitter.showUser(userName);
		return user;
	}
	
	public String getNumberOfFollowers(String userName){
		//userName = "UoLInformatics";
		
		/*ResponseList<Status> status1;
		try {
			status1 = twitter.getUserTimeline(userName, new Paging(1,200));
			for (Status tweets :status1){
	            System.out.println(tweets.getText());
	        }
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

        
		return "";
	}
	
	public int getNumberOfTweets(User user) throws TwitterException{
		ResponseList<Status> status1;
		try {
			status1 = twitter.getUserTimeline(user.getId(), new Paging(1,3600));
			return status1.size();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getNumberOfRetweets(){
		return "";
	}
	
	public String getMostActiveFollower(){
		return "";
	}
	
}
