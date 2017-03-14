package twitterStats;

import twitter4j.*;

import twitter4j.*;

import twitter4j.auth.AccessToken;

import twitter4j.auth.AccessToken;

import java.io.IOException;

public class TwitterHost {
	public static void main(String[] args) throws IOException, TwitterException {

        //Type Your Twitter Consumer Key

       String consumerKey = "...............";

        //Type Your Twitter Consumer Secret

       String consumerSecret = ".................";

        //Type Your Twitter Access Token

       String accessToken = "................";

        //Type Your Twitter Access Token Secret

       String accessTokenSecret = ".....................";

        //Create thread-safe factory

       TwitterFactory twitterFactory = new TwitterFactory();

       //Create new Twitter instance

       Twitter twitter = twitterFactory.getInstance();

      //setup OAuth Consumer Credentials

       twitter.setOAuthConsumer(consumerKey, consumerSecret);

        //setup OAuth Access Token

       twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        String userName = "UoLInformatics";

        //get the most recent 200 tweets from the given user name. 

        //Note 200 is the maximum number of tweets get per 1 call. 

        //check Twitter API documentation for more detail

        ResponseList<Status> status1 =twitter.getUserTimeline(userName, new Paging(1,200));

        // print the tweet text

        for (Status tweets :status1){

            System.out.println(tweets.getText());

        }

        

  }
}
