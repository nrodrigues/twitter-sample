package com.nelsonjrodrigues.twitter.repositories;

import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.data.model.User;

public class TweetRepositoryImplTest extends AbstractRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TweetRepository repository;

	User user = null;

	@Before
	public void setup() {
		user = new User();
		user.setUsername("test");
		userRepository.save(user);
	}

	@After
	public void teardown() {
		deleteFromTables("Tweets", "Users");
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

}
