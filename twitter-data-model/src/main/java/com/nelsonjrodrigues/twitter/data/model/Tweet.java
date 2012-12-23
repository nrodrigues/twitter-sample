package com.nelsonjrodrigues.twitter.data.model;

import java.util.Date;
import java.util.Objects;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public class Tweet extends BaseDomain {

	private User author;

	private Date date;

	private String content;

	public Tweet() {

	}

	Tweet(User author, Date date, String content) {
		Objects.requireNonNull(author);
		Objects.requireNonNull(date);
		Objects.requireNonNull(content);
	}

	public User getAuthor() {
		return author;
	}

	public Date getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

}
