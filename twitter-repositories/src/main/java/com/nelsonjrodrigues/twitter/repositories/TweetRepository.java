package com.nelsonjrodrigues.twitter.repositories;

import java.util.List;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.repositories.base.BaseRepository;

public interface TweetRepository extends BaseRepository<Tweet> {

	List<Tweet> findTweetsByUserAndSearchTerms(String userId, String searchTerms);

}
