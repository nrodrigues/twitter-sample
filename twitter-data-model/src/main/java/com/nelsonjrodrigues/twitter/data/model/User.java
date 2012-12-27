package com.nelsonjrodrigues.twitter.data.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

@XmlRootElement
public class User extends BaseDomain {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Assert.hasText(username);

		this.username = username;
	}

}
