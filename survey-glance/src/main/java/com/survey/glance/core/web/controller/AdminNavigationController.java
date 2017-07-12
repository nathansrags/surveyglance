package com.survey.glance.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.survey.glance.core.web.email.IMailMessageBuilderService;

/**
 * @author Administrator
 * @Description Class to navigate admin pages
 *
 */
@Controller
public class AdminNavigationController extends AbstractController{
	
	
	@Autowired
	@Qualifier("mailMessageBuilderService")
	private IMailMessageBuilderService mailMessageBuilderService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		//mailMessageBuilderService.sendEmailTemplate();
		return "admin";
	}
}
