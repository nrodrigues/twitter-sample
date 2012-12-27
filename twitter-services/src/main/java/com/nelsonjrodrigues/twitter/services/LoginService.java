package com.nelsonjrodrigues.twitter.services;

import org.springframework.dao.EmptyResultDataAccessException;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;
import com.nelsonjrodrigues.twitter.data.model.User;

/**
 * Login interface
 * 
 * @author nrodrigues
 * 
 */
public interface LoginService {

	/**
	 * Login a given user
	 * 
	 * @param user
	 * @return an api token valid for further requests
	 */
	ApiToken login(User user);

	/**
	 * Check if the token is valid
	 * 
	 * @param token
	 * @throws EmptyResultDataAccessException
	 *             if token is invalid
	 */
	void check(ApiToken token);

}
