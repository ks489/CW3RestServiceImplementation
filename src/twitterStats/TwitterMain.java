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
			int numOfFollowers = tHost.getNumberOfFollowers(user);
			
			//Second Test
			int tweetCount = tHost.getNumberOfTweets(user);
			String result = uHost.submitNumberOfTweets(PASSCODE, userName, tweetCount);
			System.out.println("First result is " + result);
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
