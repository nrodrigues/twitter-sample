package com.nelsonjrodrigues.twitter.repositories;

import java.util.Collections;
import java.util.Objects;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepositoryImpl;

@Repository
@Transactional(readOnly = true)
public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet> implements TweetRepository {

	@Override
	@Transactional
	public void save(Tweet tweet) {
		Objects.requireNonNull(tweet);

		String sql = "insert into Tweets (id, authorId, creationDate, content) values (:id, :authorId, :creationDate, :content)";

		jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tweet));
	}

	@Override
	public Tweet load(String id) {
		Assert.hasText(id);

		String sql = "select * from Tweets where id = :id";

		return jdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), rowMapper);
	}

}
