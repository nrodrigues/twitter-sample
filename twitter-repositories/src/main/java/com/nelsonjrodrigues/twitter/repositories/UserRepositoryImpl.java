package com.nelsonjrodrigues.twitter.repositories;

import java.util.Collections;
import java.util.Objects;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepositoryImpl;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

	@Override
	protected String getTableName() {
		return "Users";
	}

	@Override
	@Transactional
	public void save(User user) {
		Objects.requireNonNull(user);

		String sql = "insert into Users values (:id, :username)";

		jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
	}

	@Override
	public User findByUsername(String username) {
		Assert.hasText(username);

		String sql = "select * from Users where username = :username";

		return jdbcTemplate.queryForObject(sql, Collections.singletonMap("username", username), rowMapper);
	}

}
