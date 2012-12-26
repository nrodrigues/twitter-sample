package com.nelsonjrodrigues.twitter.repositories;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.Follower;
import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepository;

public interface FollowerRepository extends BaseRepository<Follower> {

	List<User> findFollowers(String followedUserId);

}
