package com.nelsonjrodrigues.twitter.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public class User extends BaseDomain {

	private String username;

	private List<Tweet> tweets = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Assert.hasText(username);

		this.username = username;
	}

	public List<Tweet> getTweets() {
		return Collections.unmodifiableList(tweets);
	}

}
