package twitterStats;

import java.text.MessageFormat;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.UniformInterfaceException;

import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UniversityHost {
	//private final String BASE_URI ="http://localhost:8080/DemoRestwebservicetest/rest/demo/";
	private final static String PASSCODE = "ks4686";
	private final static String BASE_URI = "https://campus.cs.le.ac.uk/tyche/CO7214Rest2/rest/soa/";
	private final static String DESCRIPTION = "https://campus.cs.le.ac.uk/tyche/CO7214Rest2/rest/application.wadl";
	
	/*
	private final static String GETUSERNAME_URI = "getUserName/{passCode}";
	private final static String SUBMITNUMBEROFRETWEETS_URI = "submitNumberOfRetweets/{passCode}/{userName}/{nRTweets}";
	private final static String SUBMITMOSTACTIVEFOLLOWER_URI = "submitMostActiveFollower/{passCode}/{userName}/{ActiveFollower}";
	private final static String SUBMITNUMBEROFFOLLOWERS_URI = "submitNumberOfFollowers/{passCode}/{userName}/{nFollowers}";
	private final static String SUBMITNUMBEROFTWEETS_URI = "submitNumberOfTweets/{passCode}/{userName}/{nTweets}";
	private final static String TEST_URI = "hello";
	*/
	
	private final static String GETUSERNAME_URI = "getUserName";
	private final static String SUBMITNUMBEROFRETWEETS_URI = "submitNumberOfRetweets";
	private final static String SUBMITMOSTACTIVEFOLLOWER_URI = "submitMostActiveFollower";
	private final static String SUBMITNUMBEROFFOLLOWERS_URI = "submitNumberOfFollowers";
	private final static String SUBMITNUMBEROFTWEETS_URI = "submitNumberOfTweets";
	private final static String TEST_URI = "hello";
	
	public UniversityHost(){
		
	}
	
	public String getUserName(String passCode){
		Client client = ClientBuilder.newClient();
		System.out.println(BASE_URI+MessageFormat.format(GETUSERNAME_URI + "/{0}",  passCode));
		WebTarget target =client.target(BASE_URI+MessageFormat.format(GETUSERNAME_URI + "/{0}/",  passCode));
			
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	public String submitNumberOfFollowers(String passCode, String userName, int nFollowers){
		System.out.println("Submit Number of Followers...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFFOLLOWERS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nFollowers)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	public String submitNumberOfTweets(String passCode, String userName, int nTweets){
		System.out.println("Submit Number of Tweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFTWEETS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nTweets)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	public String submitNumberOfRetweets(String passCode, String userName, int nRTweets){
		System.out.println("Submit Number of Retweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITNUMBEROFRETWEETS_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, new Integer(nRTweets)}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}
	
	public String submitMostActiveFollower(String passCode, String userName, String ActiveFollower){
		System.out.println("Submit Number of Retweets...");
		Client client = ClientBuilder.newClient();		
		WebTarget target =client.target(BASE_URI+MessageFormat.format(SUBMITMOSTACTIVEFOLLOWER_URI + "/{0}/{1}/{2}/",  new Object[] {passCode, userName, ActiveFollower}));
		return target.request(MediaType.APPLICATION_JSON_TYPE).get(String.class);
	}

	public static String getSum(String a, String b) throws UniformInterfaceException{
	
		Client client = ClientBuilder.newClient();
		
		WebTarget target =client.target(BASE_URI+MessageFormat.format("sum/{0}/{1}", new Object[] {a,b}));
			
		return target.request(MediaType.TEXT_PLAIN).get(String.class);
		
	}

	public static String getHello() throws UniformInterfaceException{     
	
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(BASE_URI + MessageFormat.format(TEST_URI, new Object[] {}));
		
		return target.request(MediaType.TEXT_PLAIN).get(String.class);
	
	}           
}





