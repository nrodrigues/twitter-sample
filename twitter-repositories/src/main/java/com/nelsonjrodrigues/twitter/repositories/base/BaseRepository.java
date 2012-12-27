package com.nelsonjrodrigues.twitter.repositories.base;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public interface BaseRepository<Domain extends BaseDomain> {

	List<Domain> findAll();

	void save(Domain d);

	Domain load(String id);

	void delete(Domain d);

}
