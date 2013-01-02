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
	public Tweet tweet(String username, String content) {
		Assert.hasText(username);
		Assert.hasText(content);

		// make sure id's are valid
		User user = userRepository.findByUsername(username);

		Tweet tweet = new Tweet();

		tweet.setAuthorId(user.getId());
		tweet.setCreationDate(new Date());
		tweet.setContent(content);

		tweetRepository.save(tweet);

		return tweet;
	}

	@Override
	public List<Tweet> timeline(String username, String searchTerms) {
		Assert.hasText(username);

		// make sure id's are valid
		User user = userRepository.findByUsername(username);

		return tweetRepository.findTweetsByUserAndSearchTerms(user.getId(), searchTerms);
	}

}
