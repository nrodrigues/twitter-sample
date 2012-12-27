package com.nelsonjrodrigues.twitter.data.model;

import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

@XmlRootElement
public class Tweet extends BaseDomain {

	private String authorId;

	private Date creationDate;

	private String content;

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		Assert.hasText(authorId);

		this.authorId = authorId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		Objects.requireNonNull(creationDate);

		this.creationDate = creationDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		Assert.hasText(content);

		this.content = content;
	}

}
