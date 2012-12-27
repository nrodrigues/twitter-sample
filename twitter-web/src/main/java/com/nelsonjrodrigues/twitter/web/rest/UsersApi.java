package com.nelsonjrodrigues.twitter.web.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.UserRepository;
import com.nelsonjrodrigues.twitter.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		Objects.requireNonNull(user);

		userRepository.save(user);

		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User retrieve(@PathVariable("id") String id) {
		return userRepository.load(id);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/followers", method = RequestMethod.GET)
	public List<User> followers(@PathVariable("id") String id) {
		Assert.hasText(id);

		return userService.followers(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/{followed}/followers", method = RequestMethod.POST)
	public void beFollowed(@RequestBody User follower, @PathVariable("followed") String followed) {
		Objects.requireNonNull(follower);
		Assert.hasText(followed);

		userService.follow(follower.getId(), followed);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{followed}/followers", method = RequestMethod.DELETE)
	public void shakeOffFollower(@RequestBody User follower, @PathVariable("followed") String followed) {
		Objects.requireNonNull(follower);
		Assert.hasText(followed);

		userService.unfollow(follower.getId(), followed);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}/following", method = RequestMethod.GET)
	public List<User> following(@PathVariable("id") String id) {
		Assert.hasText(id);

		return userService.following(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/{id}/following", method = RequestMethod.POST)
	public void follow(@PathVariable("id") String follower, @RequestBody User followed) {
		Assert.hasText(follower);
		Objects.requireNonNull(followed);

		userService.follow(follower, followed.getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}/following", method = RequestMethod.DELETE)
	public void unfollow(@PathVariable("id") String follower, @RequestBody User followed) {
		Assert.hasText(follower);
		Objects.requireNonNull(followed);

		userService.unfollow(follower, followed.getId());
	}

}
