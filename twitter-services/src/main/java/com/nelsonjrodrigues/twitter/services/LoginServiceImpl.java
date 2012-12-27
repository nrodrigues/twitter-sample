package com.nelsonjrodrigues.twitter.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;
import com.nelsonjrodrigues.twitter.data.model.User;
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
	public ApiToken login(User user) {
		Objects.requireNonNull(user);

		// make sure id's are valid
		userRepository.load(user.getId());

		ApiToken token = new ApiToken();

		repository.save(token);

		return token;
	}

	@Override
	public void check(ApiToken token) {
		Objects.requireNonNull(token);

		// make sure id's are valid
		repository.load(token.getId());
	}
}
