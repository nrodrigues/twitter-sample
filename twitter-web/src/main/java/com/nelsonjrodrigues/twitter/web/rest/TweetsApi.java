package com.nelsonjrodrigues.twitter.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nelsonjrodrigues.twitter.data.model.Tweet;
import com.nelsonjrodrigues.twitter.services.TweetService;

@Controller
@RequestMapping("/tweets")
public class TweetsApi {

	@Autowired
	private TweetService tweetService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Tweet tweet(@PathVariable("id") String user, @RequestBody String content) {
		Assert.hasText(user);
		Assert.hasText(content);

		return tweetService.tweet(user, content);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<Tweet> timeline(@PathVariable("id") String user) {
		Assert.hasText(user);

		return tweetService.timeline(user);
	}

}
