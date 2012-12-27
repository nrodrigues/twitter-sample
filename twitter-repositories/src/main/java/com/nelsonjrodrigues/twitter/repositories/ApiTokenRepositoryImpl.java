package com.nelsonjrodrigues.twitter.repositories;

import java.util.Collections;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepositoryImpl;

@Repository
@Transactional(readOnly = true)
public class ApiTokenRepositoryImpl extends BaseRepositoryImpl<ApiToken> implements ApiTokenRepository {

	@Override
	@Transactional
	public void save(ApiToken token) {
		Objects.requireNonNull(token);

		String sql = "insert into ApiTokens (id) values (:id)";

		jdbcTemplate.update(sql, Collections.singletonMap("id", token.getId()));
	}

	@Override
	protected String getTableName() {
		return "ApiTokens";
	}

}
