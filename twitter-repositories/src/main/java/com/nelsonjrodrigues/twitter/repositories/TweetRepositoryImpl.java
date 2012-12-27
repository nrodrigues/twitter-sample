package com.nelsonjrodrigues.twitter.repositories;

import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	public List<Tweet> findTweetsByUserAndSearchTerms(String userId, String searchTerms) {
		String searchPredicate = "";
		if (StringUtils.hasText(searchTerms)) {
			searchTerms = "%" + searchTerms + "%";
			searchPredicate = "and t.content like :searchTerms";
		}

		String sql = String.format("select t.* from Tweets t left outer join Followers f on (t.authorId <> :userId and t.authorId = f.userId) "
				+ "where (f.followerId = :userId or (f.id is null and t.authorId = :userId)) %s order by t.creationDate desc", searchPredicate);

		SqlParameterSource params = new MapSqlParameterSource("userId", userId).addValue("searchTerms", searchTerms);

		return jdbcTemplate.query(sql, params, rowMapper);
	}

}
