package com.survey.glance.core.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.survey.glance.core.web.service.AuthenticationSuccessHandlerImpl;

/**
 * @author Administrator
 *
 */
@Controller
public class AppInitiateController {

	@Autowired
	@Qualifier("customAuthenticationSuccessHandler")
	private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public void defalutPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model)  throws IOException {
		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		authenticationSuccessHandlerImpl.onAuthenticationSuccess(request,
				response, authentication);

	}

	/**
	 * @param model
	 * @return String
	 */
	/**
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model){
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "login";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(final ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String anonymousUser(final ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "welcome";
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	/**
	 * @return
	 */
	private String getPrincipal() {
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

}