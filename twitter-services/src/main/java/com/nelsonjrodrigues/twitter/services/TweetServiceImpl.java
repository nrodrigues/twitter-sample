package com.nelsonjrodrigues.twitter.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.TweetRepository;
import com.nelsonjrodrigues.twitter.repositories.UserRepository;

@Service
@Transactional
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Tweet tweet(String userId, String content) {
		Assert.hasText(userId);
		Assert.hasText(content);

		// make sure id's are valid
		User user = userRepository.load(userId);

		Tweet tweet = new Tweet();

		tweet.setAuthorId(user.getId());
		tweet.setCreationDate(new Date());
		tweet.setContent(content);

		tweetRepository.save(tweet);

		return tweet;
	}

	@Override
	public List<Tweet> timeline(String userId) {
		Assert.hasText(userId);

		// make sure id's are valid
		User user = userRepository.load(userId);

		return tweetRepository.findTweetsByUser(user.getId());
	}

}
