package com.nelsonjrodrigues.twitter.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User {

	private String username;

	private List<Tweet> tweets = new ArrayList<>();

	private List<Following> following = new ArrayList<>();

	private List<Follower> followers = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public List<Tweet> getTweets() {
		return Collections.unmodifiableList(tweets);
	}

	public Tweet tweet(String content) {
		Tweet tweet = new Tweet(this, new Date(), content);

		tweets.add(tweet);

		return tweet;
	}

}
