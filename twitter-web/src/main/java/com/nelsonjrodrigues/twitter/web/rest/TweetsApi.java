package com.nelsonjrodrigues.twitter.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.services.TweetService;

@Controller
@RequestMapping("/tweets")
public class TweetsApi {

	@Autowired
	private TweetService tweetService;

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/{username}", method = RequestMethod.POST)
	public Tweet tweet(@PathVariable("username") String username, @RequestBody String content) {
		Assert.hasText(username);
		Assert.hasText(content);

		return tweetService.tweet(username, content);
	}

	@ResponseBody
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public List<Tweet> timeline(@PathVariable("username") String username, @RequestParam(value = "search", required = false) String searchTerms) {
		Assert.hasText(username);

		return tweetService.timeline(username, searchTerms);
	}

}
