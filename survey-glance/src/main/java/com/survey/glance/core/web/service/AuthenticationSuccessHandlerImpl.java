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

public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler {

	protected Logger logger = LoggerFactory
			.getLogger(AuthenticationSuccessHandlerImpl.class);

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to "
					+ targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;
		boolean isDBA = false;
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				isAdmin = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ROLE_DBA")) {
				isAdmin = true;
				break;
			}
		}

		if (isUser) {
			return "/welcome";
		} else if (isAdmin) {
			return "/admin";
		} else if (isDBA) {
			return "/dba";
		} else {
			throw new IllegalStateException();
		}
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
