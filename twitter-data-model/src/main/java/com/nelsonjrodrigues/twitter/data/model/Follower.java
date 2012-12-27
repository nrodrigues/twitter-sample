package com.nelsonjrodrigues.twitter.data.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

@XmlRootElement
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
