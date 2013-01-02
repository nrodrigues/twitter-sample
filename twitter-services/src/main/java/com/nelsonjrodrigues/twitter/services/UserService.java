package com.nelsonjrodrigues.twitter.services;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.User;

/**
 * Manager interface to the User and Follower repositories
 * 
 * @author nrodrigues
 * 
 */
public interface UserService {

	/**
	 * Retrieves the list of users following a given user
	 * 
	 * @param username
	 *            the user username to search followers for
	 * @return the list
	 */
	List<User> followers(String username);

	/**
	 * Retrieves the list of users a given user is following
	 * 
	 * @param username
	 *            the user username to search
	 * @return
	 */
	List<User> following(String username);

	/**
	 * Start following a user
	 * 
	 * @param follower
	 *            the follower user username
	 * @param username
	 *            the followed user username
	 */
	void follow(String follower, String username);

	/**
	 * Stop following a user
	 * 
	 * @param follower
	 *            the follower username
	 * @param username
	 *            the followed username
	 */
	void unfollow(String follower, String username);

}
