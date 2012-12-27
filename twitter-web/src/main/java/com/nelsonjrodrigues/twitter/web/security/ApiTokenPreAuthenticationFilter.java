package com.nelsonjrodrigues.twitter.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.StringUtils;

import com.nelsonjrodrigues.twitter.repositories.ApiTokenRepository;

public class ApiTokenPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	@Autowired
	private ApiTokenRepository repository;

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return "N/A";
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		String token = request.getParameter("token");
		if (!StringUtils.hasText(token)) {
			return null;
		}

		return repository.load(token);
	}

}
