package twitterStats;

import java.text.MessageFormat;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.UniformInterfaceException;

import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.DefaultClientConfig;

import twitter4j.TwitterException;

/**
 * 
 * @author Kyle Spindler
 * @version 1.0
 * Date : 17/03/2017
 * Description : Integration into the University's API where it will verify outgoing twitter stats
 *               
 */
public class UniversityHost {

	//University URI	
	private final static String BASE_URI = "https://campus.cs.le.ac.uk/tyche/CO7214Rest2/rest/soa/";
	
	//University route endpoints
	private final static String GETUSERNAME_URI = "getUserName";
	private final static String SUBMITNUMBEROFRETWEETS_URI = "submitNumberOfRetweets";
	private final static String SUBMITMOSTACTIVEFOLLOWER_URI = "submitMostActiveFollower";
	private final static String SUBMITNUMBEROFFOLLOWERS_URI = "submitNumberOfFollowers";
	private final static String SUBMITNUMBEROFTWEETS_URI = "submitNumberOfTweets";
	private final static String TEST_URI = "hello";
	
	public UniversityHost(){
		
	}
	
	/**
	 * This method gets a random user's screen name from the  University's API
	 * @param  University unique passcode
	 * @return Success response from University API
	 */
	public String getUserName(String passCode){
		System.out.println("Getting a user name from server");
		Client client = ClientBuilder.newClient();
		System.out.println(BASE_URI+MessageFormat.format(GETUSERNAME_URI + "/{0}",  passCode));
		WebTarget target =client.target(BASE_URI+MessageFormat.format(GETUSERNAME_URI + "/{0}/",  passCode));			
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	/**
	 * This method submits the number of followers for a certain user to the University's API
	 * for verification
	 * @param  University unique passcode
	 * @param  The user we are comparing
	 * @param  The number of followers for a certain user
	 * @return Success response from University API
	 */
	public String submitNumberOfFollowers(String passCode, String userName, int nFollowers){
		System.out.println("Submit Number of Followers...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFFOLLOWERS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nFollowers)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	/**
	 * This method submits the number of tweets for a certain user to the University's API
	 * for verification
	 * @param  University unique passcode
	 * @param  The user we are comparing
	 * @param  The number of tweets for a certain user
	 * @return Success response from University API
	 */
	public String submitNumberOfTweets(String passCode, String userName, int nTweets){
		System.out.println("Submit Number of Tweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFTWEETS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nTweets)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	/**
	 * This method submits the number of retweets for a certain user to the University's API
	 * for verification
	 * @param  University unique passcode
	 * @param  The user we are comparing
	 * @param  The number of retweets for a certain user
	 * @return Success response from University API
	 */
	public String submitNumberOfRetweets(String passCode, String userName, int nRTweets){
		System.out.println("Submit Number of Retweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFRETWEETS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nRTweets)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	/**
	 * This method submits the most active follower for a certain user to the University's API
	 * for verification
	 * @param  University unique passcode
	 * @param  The user we are comparing
	 * @param  The most active user's screen name
	 * @return Success response from University API
	 */
	public String submitMostActiveFollower(String passCode, String userName, String ActiveFollower){
		System.out.println("Submit Number of Retweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITMOSTACTIVEFOLLOWER_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, ActiveFollower}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}         
}





