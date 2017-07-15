package com.survey.glance.core.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.survey.glance.core.web.domain.User;
import com.survey.glance.core.web.domain.UserProfile;
import com.survey.glance.core.web.domain.UserProfileType;
import com.survey.glance.core.web.forms.RegisterFormTO;
import com.survey.glance.core.web.forms.validator.RegisterFormValidator;
import com.survey.glance.core.web.service.IUserProfileService;
import com.survey.glance.core.web.service.IUserService;
import com.survey.glance.core.web.transformer.IGenericTransformer;

/**
 * @author Administrator
 *
 */
@Controller
public class RegistrationController extends AbstractController {

	@Autowired
	@Qualifier("registerFormValidator")
	private RegisterFormValidator registerFormValidator;

	@Autowired
	@Qualifier("pUserTransformerImpl")
	private IGenericTransformer<User, RegisterFormTO> userTransformerImpl;

	@Autowired
	@Qualifier("userServiceImpl")
	private IUserService userServiceImpl;

	@Autowired
	@Qualifier("userProfileServiceImpl")
	private IUserProfileService userProfileServiceImpl;

	/**
	 * @param regForm
	 * @param result
	 * @return
	 */
	@RequestMapping(value = { "/registerNewUser" }, method = RequestMethod.POST)
	public String registerNewUser(
			@ModelAttribute("registerForm") RegisterFormTO regForm,
			final BindingResult result, ModelMap model) {
		registerFormValidator.validate(regForm, result);
		if (result.hasErrors()) {
			System.out.println("In error");
			return "registration";
		}
		final User user = userTransformerImpl.transformTOToEntity(regForm);
		if (null == userServiceImpl.find(user)) {
			final UserProfile userProfile = userProfileServiceImpl
					.findByType(UserProfileType.USER.getUserProfileType());
			user.getUserProfiles().add(userProfile);
			userServiceImpl.createUser(user);
		} else {
			return "registration";
		}
		return "redirect:/login?success";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = { "/registrationView" }, method = RequestMethod.GET)
	public ModelAndView registrationView() {
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("registerForm", new RegisterFormTO());
		return mv;
	}

}
