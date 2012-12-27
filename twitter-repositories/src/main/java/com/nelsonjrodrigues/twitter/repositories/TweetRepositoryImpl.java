package com.nelsonjrodrigues.twitter.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepositoryImpl;

@Repository
@Transactional(readOnly = true)
public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet> implements TweetRepository {

	@Override
	protected String getTableName() {
		return "Tweets";
	}

	@Override
	@Transactional
	public void save(Tweet tweet) {
		Objects.requireNonNull(tweet);

		String sql = "insert into Tweets (id, authorId, creationDate, content) values (:id, :authorId, :creationDate, :content)";

		jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(tweet));
	}

	@Override
	public List<Tweet> findTweetsByUser(String userId) {
		String sql = "select t.* from Tweets t left outer join Followers f on (t.authorId <> :userId and t.authorId = f.userId) "
				+ "where f.followerId = :userId or (f.id is null and t.authorId = :userId) order by t.creationDate desc";

		return jdbcTemplate.query(sql, Collections.singletonMap("userId", userId), rowMapper);
	}

}
