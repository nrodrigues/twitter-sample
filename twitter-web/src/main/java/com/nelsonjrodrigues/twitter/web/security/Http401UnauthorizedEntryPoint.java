package com.nelsonjrodrigues.twitter.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * <p>
 * In the pre-authenticated authentication case (unlike CAS, for example) the
 * user will already have been identified through some external mechanism and a
 * secure context established by the time the security-enforcement filter is
 * invoked.
 * <p>
 * Therefore this class isn't actually responsible for the commencement of
 * authentication, as it is in the case of other providers. It will be called if
 * the user is rejected by the AbstractPreAuthenticatedProcessingFilter,
 * resulting in a null authentication.
 * <p>
 * The <code>commence</code> method will always return an
 * <code>HttpServletResponse.SC_UNAUTHORIZED</code> (401 error).
 * 
 * @see org.springframework.security.web.access.ExceptionTranslationFilter
 * 
 * @author nrodrigues
 * @author Luke Taylor
 * @author Ruud Senden
 * @since 2.0
 */
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {
	private static final Log logger = LogFactory.getLog(Http401UnauthorizedEntryPoint.class);

	/**
	 * Always returns a 403 error code to the client.
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException,
			ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Pre-authenticated entry point called. Rejecting access");
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}

}
