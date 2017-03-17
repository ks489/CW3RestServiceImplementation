package twitterStats;

import twitter4j.TwitterException;
import twitter4j.User;

/**
 * 
 * @author Kyle Spindler
 * @version 1.0
 * Date : 17/03/2017
 * Description : An application that will get twitter stats from Twitter API and send those
 *               details to a University API and verify weather the stats match.
 */
public class TwitterMain {
	
	//University passcode
	private final static String PASSCODE = "ks4686";
	
	public static void main(String args[]){
		UniversityHost uHost = new UniversityHost();
		String userName = uHost.getUserName(PASSCODE);
		TwitterHost tHost = new TwitterHost();
		try {
			User user = tHost.getUserDetails(userName);
			
			//First Test
			int numOfFollowers = tHost.getNumberOfFollowers(user);
			String followerResult = uHost.submitNumberOfFollowers(PASSCODE, userName, numOfFollowers);
			System.out.println("Number of Followers Result : " + followerResult);
			
			//Second Test
			int tweetCount = tHost.getNumberOfTweets(user);
			String tweetCountResult = uHost.submitNumberOfTweets(PASSCODE, userName, tweetCount);
			System.out.println("Number of Tweets Result : " + tweetCountResult);
			
			//Third Test
			int reTweetCount = tHost.getNumberOfRetweets(user);
			String reTweetResult = uHost.submitNumberOfRetweets(PASSCODE, userName, reTweetCount);
			System.out.println("Number of ReTweets Result : " + reTweetResult);
			
			//Fourth Test
			String mostFollowedUser = tHost.getMostActiveFollower(user);
			String mostFollowed = uHost.submitMostActiveFollower(PASSCODE, userName, mostFollowedUser);
			System.out.println("Most Active Follower Result : " + mostFollowed);
			
			System.out.println("Summary");
			System.out.println("-------");
			System.out.println("Number of followers - " + followerResult);
			System.out.println("Number of tweets - " + tweetCountResult);
			System.out.println("Number of retweets - " + reTweetResult);
			System.out.println("Most active follower - " + mostFollowed);
			System.out.println("-------");
			System.out.println("Application end");
			
		} catch (TwitterException e) {
			System.out.println("Ooops an error occured");
			e.printStackTrace();
		}		
	}
}
