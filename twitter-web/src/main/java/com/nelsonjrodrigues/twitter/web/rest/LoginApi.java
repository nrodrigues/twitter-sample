package com.nelsonjrodrigues.twitter.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nelsonjrodrigues.twitter.data.model.ApiToken;
import com.nelsonjrodrigues.twitter.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginApi {

	@Autowired
	private LoginService service;

	@ResponseBody
	@RequestMapping("/{id}")
	public ApiToken login(@PathVariable("id") String userId) {
		Assert.hasText(userId);

		return service.login(userId);
	}

}
