package com.nelsonjrodrigues.twitter.repositories.base;

import com.nelsonjrodrigues.twitter.data.model.base.BaseDomain;

public interface BaseRepository<Domain extends BaseDomain> {

	void save(Domain d);

	Domain load(String id);

}
