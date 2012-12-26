package com.nelsonjrodrigues.twitter.repositories.base;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public abstract class BaseRepositoryImpl<Domain extends BaseDomain> implements BaseRepository<Domain> {

	protected RowMapper<Domain> rowMapper;
	protected NamedParameterJdbcOperations jdbcTemplate;

	private Class<Domain> entityClass;

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

}
