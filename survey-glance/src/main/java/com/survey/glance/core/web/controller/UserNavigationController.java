package com.survey.glance.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Administrator
 * @Description Class to navigate admin pages
 */
@Controller
public class UserNavigationController extends AbstractController {
	
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
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String goToProfile(final ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "profile";
	}
}
