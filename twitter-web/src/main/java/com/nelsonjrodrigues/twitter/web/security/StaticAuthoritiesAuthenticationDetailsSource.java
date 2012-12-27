package com.nelsonjrodrigues.twitter.web.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

public class StaticAuthoritiesAuthenticationDetailsSource implements AuthenticationDetailsSource {

	@Override
	public Object buildDetails(Object context) {
		if (context instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) context;

			PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails details = new PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(
					httpServletRequest);

			details.setGrantedAuthorities(Collections.<GrantedAuthority> singletonList(new GrantedAuthorityImpl("ROLE_REST_API")));

			return details;
		}
		return null;
	}

}
