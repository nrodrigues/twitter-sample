package com.nelsonjrodrigues.twitter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.User;
import com.nelsonjrodrigues.twitter.repositories.FollowerRepository;
import com.nelsonjrodrigues.twitter.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FollowerRepository followerRepository;

	@Override
	public List<User> followers(String id) {
		Assert.hasText(id);

		User user = userRepository.load(id);

		if (user == null) {
			throw new ResourceNotFoundException();
		}

		return followerRepository.findFollowers(id);
	}

	@Override
	public List<User> following(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void follow(String follower, String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unfollow(String follower, String id) {
		// TODO Auto-generated method stub

	}

}
