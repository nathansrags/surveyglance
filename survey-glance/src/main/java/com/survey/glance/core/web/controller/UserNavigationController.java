package com.survey.glance.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.survey.glance.core.web.common.util.Constants;

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
	@RequestMapping(value = Constants.NavigationConstants.SLASH_VISITOR_HOME, method = RequestMethod.GET)
	public String anonymousUser(final ModelMap model) {
		model.addAttribute("user", getPrincipal());
		model.addAttribute("authorities", getPrincipalAuthorities());
		return Constants.NavigationConstants.VISITOR_HOME;
	}

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = Constants.NavigationConstants.SLASH_PROFILE, method = RequestMethod.GET)
	public String goToProfile(final ModelMap model) {
		model.addAttribute("user", getPrincipal());
		model.addAttribute("authorities", getPrincipalAuthorities());
		return Constants.NavigationConstants.PROFILE;
	}
}
