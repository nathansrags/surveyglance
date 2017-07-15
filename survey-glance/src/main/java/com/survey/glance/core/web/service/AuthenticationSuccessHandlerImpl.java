package com.survey.glance.core.web.service;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.survey.glance.core.web.common.util.Constants;

/**
 * Class used to redirect from login after successful spring authentication
 * based on the user role
 * 
 * @author Administrator
 *
 */
public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	protected Logger logger = LoggerFactory
			.getLogger(AuthenticationSuccessHandlerImpl.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.authentication.AuthenticationSuccessHandler
	 * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	/**
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 */
	protected void handle(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		final String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * @param authentication
	 * @return
	 */
	protected String determineTargetUrl(final Authentication authentication) {
		String targetUrl = Constants.NavigationConstants.SLASH_LOGIN;
		if (authentication != null) {
			final Collection<? extends GrantedAuthority> authorities = authentication
					.getAuthorities();
			for (final GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals(
						Constants.ROLE_CONSTANTS.ROLE_USER)) {
					targetUrl = Constants.NavigationConstants.SLASH_VISITOR_HOME;
					break;
				} else if (grantedAuthority.getAuthority().equals(
						Constants.ROLE_CONSTANTS.ROLE_ADMIN)) {
					targetUrl = Constants.NavigationConstants.SLASH_ADMIN_HOME;
					break;
				} else if (grantedAuthority.getAuthority().equals(
						Constants.ROLE_CONSTANTS.ROLE_DBA)) {
					targetUrl = Constants.NavigationConstants.SLASH_DBA_HOME;
					break;
				}
			}
		}
		return targetUrl;
	}

	/**
	 * @param request
	 */
	protected void clearAuthenticationAttributes(
			final HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	/**
	 * @param redirectStrategy
	 */
	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	/**
	 * @return
	 */
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}
