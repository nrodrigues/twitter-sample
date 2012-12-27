package com.nelsonjrodrigues.twitter.repositories.base;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public abstract class BaseRepositoryImpl<Domain extends BaseDomain> implements BaseRepository<Domain> {

	protected RowMapper<Domain> rowMapper;
	protected NamedParameterJdbcOperations jdbcTemplate;

	private Class<Domain> entityClass;

	protected abstract String getTableName();

	@Inject
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	void initialize() {
		Objects.requireNonNull(jdbcTemplate);

		entityClass = (Class<Domain>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseRepository.class);

		rowMapper = new BeanPropertyRowMapper<>(entityClass);
	}

	@Override
	public List<Domain> findAll() {
		String sql = String.format("select * from %s", getTableName());

		return jdbcTemplate.query(sql, Collections.<String, Object> emptyMap(), rowMapper);
	}

	public Domain load(String id) {
		Assert.hasText(id);

		String sql = String.format("select * from %s where id = :id", getTableName());

		return jdbcTemplate.queryForObject(sql, Collections.singletonMap("id", id), rowMapper);
	}

	public void delete(Domain d) {
		Objects.requireNonNull(d);

		String sql = String.format("delete from %s where id = :id", getTableName());

		jdbcTemplate.update(sql, Collections.singletonMap("id", d.getId()));
	}
}
