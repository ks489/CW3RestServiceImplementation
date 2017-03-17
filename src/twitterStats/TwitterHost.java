package twitterStats;

import twitter4j.*;

import twitter4j.*;

import twitter4j.auth.AccessToken;

import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Kyle Spindler
 * @version 1.0
 * Date : 17/03/2017
 * Description : Integration into Twitter's API where it gets various twitter stats.
 *               
 */
public class TwitterHost {
	
	//Twitter credential details
	private final static String CONSUMER_KEY = "0Jq1pqgM0GNfSkgtMQPyrvadl";
	private final static String CONSUMER_SECRET = "dRd1hH5nnGpMd4tMZnE6KuIqOdBsbtfPJH2qfRlBPLeILrSgZI";
	private final static String ACCESS_TOKEN = "61495742-GIbmeqRshY5fCYOQMHVrnOORKc8zLeDMNZkUUvRUg";
	private final static String ACCESS_TOKEN_SECRET = "9UsiI8xzIrgbZFd35rpw9a1frXFfZlzoq7zqzwxi7VjA0";
	
	private TwitterFactory twitterFactory;
	private Twitter twitter;

	/**
	 * Initialize the twitter authorization and credentials
	 */
	public TwitterHost(){
		twitterFactory = new TwitterFactory();
        twitter = twitterFactory.getInstance();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET));
	}
	
	/**
	 * This method gets the User object from twitter
	 * @param  User's screen name
	 * @return User details
	 * @throws TwitterException
	 */
	public User getUserDetails(String userName) throws TwitterException{
		System.out.println("Twitter call - Getting User Detail...");
		User user = twitter.showUser(userName);
		System.out.println("Returning Detail - " + user.getScreenName());
		return user;
	}
	
	/**
	 * This method gets the number of followers for a user
	 * @param  Twitter's User. The user account you want to see the number of followers
	 * @return The number of followers
	 * @throws TwitterException
	 */
	public int getNumberOfFollowers(User user) throws TwitterException{
		System.out.println("Twitter call - Getting Number of Followers...");
		int count = user.getFollowersCount();
		System.out.println("Returning Detail - " + count);
		return count;
	}
	
	/**
	 * This method gets the number of tweets for a user
	 * @param  Twitter's User. The user account you want to see the number of tweets
	 * @return The number of tweets
	 * @throws TwitterException
	 */
	public int getNumberOfTweets(User user) throws TwitterException{
		System.out.println("Twitter call - Getting Number of Tweets...");		
		ResponseList<Status> status = twitter.getUserTimeline(user.getId(), new Paging(1,3600));			
		System.out.println("Returning Detail - " + status.size());
		return status.size();
	}
	
	/**
	 * This method gets the number of retweets for a user
	 * @param  Twitter's User. The user account you want to see the number of retweets
	 * @return The number of retweets
	 * @throws TwitterException
	 */
	public int getNumberOfRetweets(User user) throws TwitterException{
		System.out.println("Twitter call - Getting Number of Retweets...");
		
		//Another way of getting retweets for a user
		ResponseList<Status> responseList = twitter.getRetweets(user.getId());
		
		//Another way of getting retweets for a user - we return this one
		int pageNo = 1;
		List statuses = new ArrayList<Status>();
		int totalRetweets = 0;
		while(true){
			int size = statuses.size();
			Paging page = new Paging(pageNo++, 100);
			ResponseList<Status> statusList = twitter.getUserTimeline(user.getId(), page);
			for (Status statusItem : statusList)
	        {
				if(statusItem.isRetweet())
					totalRetweets++;			
	        }

	        if (statuses.size() == size)
	          break;
		}
		
		System.out.println("Returning Detail - " + totalRetweets);
		return totalRetweets;
	}
	
	/**
	 * This method gets the most active follower for a certain user
	 * @param  Twitter's User. The user account you want to see the retweets
	 * @return The most active follower's screen name
	 * @throws TwitterException
	 */
	public String getMostActiveFollower(User user) throws TwitterException{
		System.out.println("Twitter call - Getting Most Active Follower...");
		
		Map<User, Integer> userMap = getRetweeters(user);	
		
		int topNumber = 0;
		User topUser = null;

		for(User userKey : userMap.keySet()){
			Integer intNumber = userMap.get(userKey);
			if(intNumber > topNumber){
				topNumber = intNumber;
				topUser = userKey;
			}else if(intNumber == topNumber){
				System.out.println("THERE IS A TIE OOOOPS");
			}
		}

        System.out.println("Returning Detail - " + topUser.getScreenName());
        return topUser.getScreenName();
	}
	
	/**
	 * This method will get all retweets from a users timeline and the user
	 * who retweeted the user's tweet
	 * @param  Twitter's User. The user's account you want to view the retweets
	 * @return A map with the User who retweeted and the number of retweets the user did
	 */
	public Map<User, Integer> getRetweeters(User user) {
        ArrayList<User> names = new ArrayList<User>();
        Map<User, Integer> userMap = new TreeMap<User, Integer>();
        try {
            for (Status status : twitter.getUserTimeline(user.getId(), new Paging(1, 200))) {
                if (status.getText().startsWith("RT")) {
                    // skip
                } else if (status.getRetweetCount() > 0) {

                    waitUntilICanMakeAnotherCall();

                    for (Status rt : twitter.getRetweets(status.getId())) {
                        names.add(rt.getUser());
                        if(!userMap.containsKey(rt.getUser())){
    						userMap.put(rt.getUser(), 1);    						
    					}else{
    						userMap.put(rt.getUser(), userMap.get(rt.getUser()) + 1);
    					}
                    }
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMap;
    }
	
	/**
	 * This method will sleep according to the rate limiter provided by twitter.
	 * This is to avoid getting a ban from twitter.
	 * @throws TwitterException
	 * @throws InterruptedException
	 */
    protected void waitUntilICanMakeAnotherCall() throws TwitterException, InterruptedException {
    	{
    		System.out.print("...");
            Map<String, RateLimitStatus> temp = twitter.getRateLimitStatus();
            RateLimitStatus temp2 = temp.get("/statuses/retweets/:id");
            if (temp2.getRemaining() == 0) {
                Thread.sleep((temp2.getSecondsUntilReset() + 5) * 1000);
                return;
            }
            int secondstosleep = 1 + temp2.getSecondsUntilReset() / temp2.getRemaining();
            Thread.sleep(secondstosleep * 1000);
        }
    }
	
}
