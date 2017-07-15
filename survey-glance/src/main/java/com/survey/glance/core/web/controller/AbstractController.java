package com.survey.glance.core.web.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class AbstractController {
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	/**
	 * @return
	 */
	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	
	/**
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getPrincipalAuthorities() {
		Collection<? extends GrantedAuthority> authorities = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			authorities = ((UserDetails) principal).getAuthorities();
		}
		return authorities;
	}
	
	/**
	 * @param authentication
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public void determineTargetUrl(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) {
		try {
			authenticationSuccessHandler.onAuthenticationSuccess(request, response,
					authentication);
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
