package com.nelsonjrodrigues.twitter.data.model;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public class Follower extends BaseDomain {

	private String followerId;
	private String userId;

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
