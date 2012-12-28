package com.nelsonjrodrigues.twitter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;
import com.nelsonjrodrigues.twitter.repositories.ApiTokenRepository;
import com.nelsonjrodrigues.twitter.repositories.UserRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private ApiTokenRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ApiToken login(String userId) {
		Assert.hasText(userId);

		// make sure id's are valid
		userRepository.findByUsername(userId);

		ApiToken token = new ApiToken();

		repository.save(token);

		return token;
	}

}
