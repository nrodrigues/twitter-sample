package com.nelsonjrodrigues.twitter.repositories;

import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nelsonjrodrigues.twitter.data.model.Follower;
import com.nelsonjrodrigues.twitter.data.model.User;

public class FollowerRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowerRepository repository;

	User followerUser = null;
	User followedUser = null;

	@Before
	public void setup() {
		followerUser = new User();
		followerUser.setUsername("follower");
		userRepository.save(followerUser);

		followedUser = new User();
		followedUser.setUsername("followed");
		userRepository.save(followedUser);
	}

	@After
	public void teardown() {
		deleteFromTables("Followers", "Users");
	}

	@Test
	public void testAddFollower() {
		Follower follower = new Follower();

		follower.setFollowerId(followedUser.getId());
		follower.setUserId(followedUser.getId());

		repository.save(follower);

		int rowsInTable = countRowsInTable("Followers");

		Assert.assertEquals(1, rowsInTable);

		Follower retrievedFollower = simpleJdbcTemplate.queryForObject("select * from Followers where id = ?", new BeanPropertyRowMapper<>(
				Follower.class), follower.getId());

		Assert.assertEquals(follower, retrievedFollower);

	}

	@Test
	public void testRetrieveFollower() {
		String id = UUID.randomUUID().toString();

		simpleJdbcTemplate.update("insert into Followers values (?, ?, ?)", id, followerUser.getId(), followedUser.getId());

		Follower retrievedFollower = repository.load(id);

		Assert.assertNotNull(retrievedFollower);
		Assert.assertEquals(id, retrievedFollower.getId());
		Assert.assertEquals(followerUser.getId(), retrievedFollower.getFollowerId());
		Assert.assertEquals(followedUser.getId(), retrievedFollower.getUserId());

	}

	@Test
	public void testFindFollowers() {
		String id = UUID.randomUUID().toString();

		simpleJdbcTemplate.update("insert into Followers values (?, ?, ?)", id, followerUser.getId(), followedUser.getId());

		List<User> followers = repository.findFollowers(followedUser.getId());

		Assert.assertNotNull(followers);
		Assert.assertEquals(1, followers.size());
		Assert.assertEquals(followerUser, followers.get(0));

	}

}
