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
	 * @param id
	 *            the user id to search followers for
	 * @return the list
	 */
	List<User> followers(String id);

	/**
	 * Retrieves the list of users a given user is following
	 * 
	 * @param id
	 *            the user id to search
	 * @return
	 */
	List<User> following(String id);

	/**
	 * Start following a user
	 * 
	 * @param follower
	 *            the follower user id
	 * @param id
	 *            the followed user id
	 */
	void follow(String follower, String id);

	/**
	 * Stop following a user
	 * 
	 * @param follower
	 *            the follower user id
	 * @param id
	 *            the followed user id
	 */
	void unfollow(String follower, String id);

}
