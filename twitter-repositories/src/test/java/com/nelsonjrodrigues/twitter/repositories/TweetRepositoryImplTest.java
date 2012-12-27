package com.nelsonjrodrigues.twitter.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nelsonjrodrigues.twitter.data.model.Follower;
import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.data.model.User;

public class TweetRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowerRepository followerRepository;

	@Autowired
	private TweetRepository repository;

	User user = null;
	User followed = null;

	@Before
	public void setup() {
		user = new User();
		user.setUsername("test");
		userRepository.save(user);

		followed = new User();
		followed.setUsername("test2");
		userRepository.save(followed);

		Follower follower = new Follower();
		follower.setFollowerId(user.getId());
		follower.setUserId(followed.getId());
		followerRepository.save(follower);
	}

	@After
	public void teardown() {
		deleteFromTables("Tweets", "Followers", "Users");
	}

	@Test
	public void testAddTweet() {
		Tweet tweet = new Tweet();

		tweet.setAuthorId(user.getId());
		tweet.setCreationDate(new Date());
		tweet.setContent("Hello world!");

		repository.save(tweet);

		int rowsInTable = countRowsInTable("Tweets");

		Assert.assertEquals(1, rowsInTable);

		Tweet retrievedTweet = simpleJdbcTemplate.queryForObject("select * from Tweets where id = ?", new BeanPropertyRowMapper<>(Tweet.class),
				tweet.getId());

		Assert.assertEquals(tweet, retrievedTweet);

	}

	@Test
	public void testRetrieveTweet() {
		String id = UUID.randomUUID().toString();
		String content = "test";

		simpleJdbcTemplate.update("insert into Tweets values (?, ?, ?, ?)", id, user.getId(), new Date(), content);

		Tweet retrievedTweet = repository.load(id);

		Assert.assertNotNull(retrievedTweet);
		Assert.assertEquals(id, retrievedTweet.getId());
		Assert.assertEquals(content, retrievedTweet.getContent());

	}

	@Test
	public void testFindTweetsByUser() {
		String id = UUID.randomUUID().toString();
		String content = "test";

		simpleJdbcTemplate.update("insert into Tweets values (?, ?, ?, ?)", id, user.getId(), new Date(), content);

		String id2 = UUID.randomUUID().toString();
		String content2 = "test";

		simpleJdbcTemplate.update("insert into Tweets values (?, ?, ?, ?)", id2, followed.getId(), new Date(), content2);

		List<Tweet> timeline = repository.findTweetsByUserAndSearchTerms(user.getId(), null);

		Assert.assertNotNull(timeline);
		Assert.assertEquals(2, timeline.size());

		Assert.assertEquals(id2, timeline.get(0).getId());
		Assert.assertEquals(id, timeline.get(1).getId());
	}

}
