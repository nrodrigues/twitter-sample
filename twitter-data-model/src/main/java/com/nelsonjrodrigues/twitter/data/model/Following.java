package com.nelsonjrodrigues.twitter.data.model;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public class Following extends BaseDomain {

	private User who;

	public User getWho() {
		return who;
	}

	public List<Tweet> getTweets() {
		return who.getTweets();
	}

}
