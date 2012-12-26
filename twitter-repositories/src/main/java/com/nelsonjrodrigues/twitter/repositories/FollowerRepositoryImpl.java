package com.nelsonjrodrigues.twitter.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.Follower;
import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepositoryImpl;

@Repository
@Transactional(readOnly = true)
public class FollowerRepositoryImpl extends BaseRepositoryImpl<Follower> implements FollowerRepository {

	private RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

	@Override
	@Transactional
	public void save(Follower follower) {
		Objects.requireNonNull(follower);

		String sql = "insert into Followers (id, followerId, userId) values (:id, :followerId, :userId)";

		jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(follower));
	}

	@Override
	public Follower load(String id) {
		Assert.hasText(id);

		String sql = "select * from Followers where id = :id";

		return jdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), rowMapper);
	}

	@Override
	public List<User> findFollowers(String followedUserId) {
		Assert.hasText(followedUserId);

		String sql = "select u.* from Followers f inner join Users u on f.followerId = u.id where f.userId = :followedUserId";

		return jdbcTemplate.query(sql, Collections.singletonMap("followedUserId", followedUserId), userRowMapper);
	}
}
