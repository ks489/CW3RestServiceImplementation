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
	
	public static void main(String[] args) throws IOException, TwitterException {
		
        try{
        	TwitterFactory twitterFactory = new TwitterFactory();

            //Create new Twitter instance

            Twitter twitter = twitterFactory.getInstance();

           //setup OAuth Consumer Credentials

            twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

             //setup OAuth Access Token

            twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));

             String userName = "UoLInformatics";

             //get the most recent 200 tweets from the given user name. 

             //Note 200 is the maximum number of tweets get per 1 call. 

             //check Twitter API documentation for more detail

             ResponseList<Status> status1 =twitter.getUserTimeline(userName, new Paging(1,200));

             // print the tweet text

             for (Status tweets :status1){

                 System.out.println(tweets.getText());

             }
        }catch(Exception e){
        	System.out.println(e.toString());
        }
  }
}
