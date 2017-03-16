package twitterStats;

import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterMain {
	private final static String PASSCODE = "ks4686";
	public static void main(String args[]){
		UniversityHost uHost = new UniversityHost();
		String userName = uHost.getUserName(PASSCODE);
		TwitterHost tHost = new TwitterHost();
		//tHost.getNumberOfFollowers(userName);
		try {
			User user = tHost.getUserDetails(userName);
			
			//First Test
			/*int numOfFollowers = tHost.getNumberOfFollowers(user);
			String followerResult = uHost.submitNumberOfFollowers(PASSCODE, userName, numOfFollowers);
			System.out.println("Number of Followers Result : " + followerResult);*/
			
			//Second Test
			/*int tweetCount = tHost.getNumberOfTweets(user);
			String tweetCountResult = uHost.submitNumberOfTweets(PASSCODE, userName, tweetCount);
			System.out.println("Number of Tweets Result : " + tweetCountResult);*/
			
			//Third Test
			/*int reTweetCount = tHost.getNumberOfRetweets(user);
			String reTweetResult = uHost.submitNumberOfRetweets(PASSCODE, userName, reTweetCount);
			System.out.println("Number of ReTweets Result : " + reTweetResult);*/
			
			//Fourth Test
			String mostFollowedUser = tHost.getMostActiveFollower1(user);
			String mostFollowed = uHost.submitMostActiveFollower(PASSCODE, userName, mostFollowedUser);
			System.out.println("Most Active Follower Result : " + mostFollowed);
			
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
