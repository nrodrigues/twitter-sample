package com.nelsonjrodrigues.twitter.services;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.Tweet;

/**
 * Tweet Manager Service
 * 
 * @author nrodrigues
 * 
 */
public interface TweetService {

	/**
	 * Create a new tweet on the users timeline
	 * 
	 * @param userId
	 * @param content
	 * @return the new tweet
	 */
	Tweet tweet(String userId, String content);

	/**
	 * Get the user timeline, ie user's own tweets and followed user's tweets,
	 * ordered by creation date
	 * 
	 * @param userId
	 * @param searchTerms
	 * @return the users timeline
	 */
	List<Tweet> timeline(String userId, String searchTerms);

}
