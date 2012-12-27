package com.nelsonjrodrigues.twitter.services;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;

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
	 * @param userId
	 * @return an api token valid for further requests
	 */
	ApiToken login(String userId);

}
