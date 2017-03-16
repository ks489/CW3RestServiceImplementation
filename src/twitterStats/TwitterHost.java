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
	
	public int getNumberOfFollowers(User user) throws TwitterException{
		
		int count = user.getFollowersCount();
		return count;
	}
	
	public int getNumberOfTweets(User user) throws TwitterException{
		ResponseList<Status> status1;
		
		try {
			status1 = twitter.getUserTimeline(user.getId(), new Paging(1,3600));
			//return status1.size();
			int singleTotal = 0;
			int singleTotal1 = 0;
			
			int pageNo = 1;
			List statuses = new ArrayList();
			
			while(true){
				int size = statuses.size();
				Paging page = new Paging(pageNo++, 100);
		        statuses.addAll(twitter.getUserTimeline(user.getId(), page));
		        ResponseList<Status> statusList = twitter.getUserTimeline(user.getId(), page);
				for (Status statusItem : statusList)
		        {
					if(!statusItem.isRetweet())
						singleTotal++;	
					if(!statusItem.isRetweeted())
						singleTotal1++;

		        }
		        if (statuses.size() == size)
		          break;
			}
			System.out.println(status1.size());
			System.out.println(statuses.size());
			System.out.println(singleTotal);
			System.out.println(singleTotal1);
			//return statuses.size();
			return singleTotal1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getNumberOfRetweets(User user) throws TwitterException{
		
		ResponseList<Status> responseList = twitter.getRetweets(user.getId());
		
		int pageNo = 1;
		List statuses = new ArrayList<Status>();
		int singleTotal = 0;
		while(true){
			int size = statuses.size();
			Paging page = new Paging(pageNo++, 100);
			ResponseList<Status> statusList = twitter.getUserTimeline(user.getId(), page);
			for (Status statusItem : statusList)
	        {
				if(statusItem.isRetweet())
					singleTotal++;			
				
	        }

	        if (statuses.size() == size)
	          break;
		}
		
		
		/*ResponseList<Status> statusList = twitter.getUserTimeline(user.getId(), new Paging(1,3600));
		int total = 0;
		int singleTotal = 0;
		for (Status statusItem : statusList)
        {
			if(statusItem.isRetweet())
				singleTotal++;
			
			if(statusItem.isRetweeted()){
				//statusItem.
			}
			//System.out.println("Tweet Id : " + statusItem.getId() + ", retweet count: " + statusItem.getRetweetCount());
			total = total + statusItem.getRetweetCount();
			
        }*/
		
		//System.out.println(total);
		System.out.println(singleTotal);
		return singleTotal;
	}
	
	public String getMostActiveFollower(User user) throws TwitterException{
		ResponseList<Status> responseList = twitter.getRetweets(user.getId());

		Map<User, Integer> userMap = new TreeMap<User, Integer>();
		Map<Long, Integer> userMap1 = new TreeMap<Long, Integer>();
		int pageNo = 1;
		List statuses = new ArrayList<Status>();
		int singleTotal = 0;
		while(true){
			int size = statuses.size();
			Paging page = new Paging(pageNo++, 100);
			ResponseList<Status> statusList = twitter.getUserTimeline(user.getId(), page);

			for (Status statusItem : statusList)
	        {

				if(statusItem.isRetweeted()){
					IDs ids = twitter.getRetweeterIds(statusItem.getId(), -1);
					if(ids.getIDs().length > 0){
						long[] idArray = ids.getIDs();
						for(long l : idArray){
							if(!userMap1.containsKey(l)){
								userMap1.put(l, 1);
							}else{
								userMap1.put(l, userMap1.get(l) + 1);
							}
						}
					}
					if(statusItem.isRetweeted()){
						statusItem.getCurrentUserRetweetId();
					}
				}
				
				
								
				Status status = statusItem.getRetweetedStatus();
				if(status != null){
					User curUser = status.getUser();
					
					//curUser.getName();
					//System.out.println(curUser.getName());
					if(!userMap.containsKey(curUser)){
						userMap.put(curUser, 1);
					}else{
						userMap.put(curUser, userMap.get(curUser) + 1);
					}
				}
				
								
				//System.out.println("Retweet user id is " + statusItem.getCurrentUserRetweetId());
				//System.out.println("User id " + statusItem.getUser());
				
	        }
			
			
			for(User userKey : userMap.keySet()){
				System.out.println("1key, " + userKey.getName() + " value " + userMap.get(userKey));
			}
			
			for(Long userKey : userMap1.keySet()){
				System.out.println("2key, " + userKey + " value " + userMap.get(userKey));
			}

	        if (statuses.size() == size)
	          break;
		}
		return "";
	}
	public String getMostActiveFollower1(User user) throws TwitterException{
		System.out.println("Starting");
		Map<User, Integer> userMap = getRetweeters(user);		
		int topNumber = 0;
		User topUser = null;
		System.out.println("#################");
		for(User userKey : userMap.keySet()){
			//Integer intNumber = userMap.get(userKey);
			System.out.println("1key, " + userKey.getName() + " value " + userMap.get(userKey));			
		}
		System.out.println("#################");
		
		for(User userKey : userMap.keySet()){
			Integer intNumber = userMap.get(userKey);
			System.out.println("1key, " + userKey.getName() + " value " + intNumber);
			if(intNumber > topNumber){
				topNumber = intNumber;
				topUser = userKey;
			}else if(intNumber == topNumber){
				System.out.println("THERE IS A TIE OOOOPS");
			}
		}

        System.out.println("Complete");
        return topUser.getScreenName();
		//return "";
	}
	
	public Map<User, Integer> getRetweeters(User user) {
        ArrayList<User> names = new ArrayList<User>();
        Map<User, Integer> userMap = new TreeMap<User, Integer>();
        try {
            for (Status status : twitter.getUserTimeline(user.getId(), new Paging(1, 200))) {
                //System.out.println(status.getText());
                if (status.getText().startsWith("RT")) {
                    // skip
                } else if (status.getRetweetCount() > 0) {
                    // Because I don't want to breach
                    // Twitter's rate limits
                    // okay the below has been added to keep within the rate
                    // limited.
                    waitUntilICanMakeAnotherCall();
                    // end keeping within rate limit code.
                    for (Status rt : twitter.getRetweets(status.getId())) {
                        names.add(rt.getUser());
                        if(!userMap.containsKey(rt.getUser())){
    						userMap.put(rt.getUser(), 1);
    						System.out.println("Added First");
    					}else{
    						userMap.put(rt.getUser(), userMap.get(rt.getUser()) + 1);
    						System.out.println("Added Second");
    						System.out.println("Increase " + userMap.get(rt.getUser()) + 1);
    					}
                        System.out.println("---"+rt.getUser().getScreenName());
                    }
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMap;
        //return names.toArray(new User[names.size()]);
    }
	
	/**
     * @throws TwitterException
     * @throws InterruptedException
     */
    protected void waitUntilICanMakeAnotherCall() throws TwitterException, InterruptedException {
        {
            Map<String, RateLimitStatus> temp = twitter.getRateLimitStatus();

            RateLimitStatus temp2 = temp.get("/statuses/retweets/:id");
            //System.out.println(temp2.getRemaining());
            if (temp2.getRemaining() == 0) {
                Thread.sleep((temp2.getSecondsUntilReset() + 5) * 1000);
                return;
            }
            //System.out.println(temp2.getSecondsUntilReset());
            int secondstosleep =1+ temp2.getSecondsUntilReset() / temp2.getRemaining();
            //System.out.println(secondstosleep);
            //System.out.println("sleeping");
            Thread.sleep(secondstosleep * 1000);
        }
    }
	
}
