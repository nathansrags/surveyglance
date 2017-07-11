package com.survey.glance.core.web.controller;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.survey.glance.core.web.domain.User;

/**
 * @author Administrator
 *
 */
@Controller
public class AppInitiateController extends AbstractController {

	private static Logger LOG = LoggerFactory
			.getLogger(AppInitiateController.class);

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
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@Async
	@RequestMapping(value = "/googleOAuth", method = RequestMethod.POST)
	public String googleAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		final String authCode = null;
		final String REDIRECT_URI = "/welcome";
		// (Receive authCode via HTTPS POST)
		if (request.getHeader("X-Requested-With") == null) {
			// Without the `X-Requested-With` header, this request could be
			// forged. Aborts.
			request.getParameter("code");
		}

		// Set path to the Web application client_secret_*.json file you
		// downloaded from the
		// Google API Console:
		// https://console.developers.google.com/apis/credentials
		// You can also find your Web application client ID and client secret
		// from the
		// console and specify them directly when you create the
		// GoogleAuthorizationCodeTokenRequest
		// object.
		String CLIENT_SECRET_FILE = "/path/to/client_secret.json";

		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
				JacksonFactory.getDefaultInstance(), new FileReader(
						CLIENT_SECRET_FILE));
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
				new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
				"https://www.googleapis.com/oauth2/v4/token", clientSecrets
						.getDetails().getClientId(), clientSecrets.getDetails()
						.getClientSecret(), authCode, REDIRECT_URI) // Specify
																	// the same
																	// redirect
																	// URI that
																	// you use
																	// with your
																	// web
																	// app. If
																	// you don't
																	// have a
																	// web
																	// version
																	// of your
																	// app, you
																	// can
																	// specify
																	// an empty
																	// string.
				.execute();

		String accessToken = tokenResponse.getAccessToken();

		// Use access token to call API
		GoogleCredential credential = new GoogleCredential()
				.setAccessToken(accessToken);
		Drive drive = new Drive.Builder(new NetHttpTransport(),
				JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName("Auth Code Exchange Demo").build();
		// File file =
		drive.files().get("appfolder").execute();

		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject(); // Use this value as a key to
												// identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");
		return "/welcome";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	/*
	 * @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	 * public String saveUser(@Valid User user, BindingResult result, ModelMap
	 * model) {
	 * 
	 * if (result.hasErrors()) { return "registration"; }
	 * 
	 * 
	 * Preferred way to achieve uniqueness of field [sso] should be implementing
	 * custom @Unique annotation and applying it on field [sso] of Model class
	 * [User].
	 * 
	 * Below mentioned peace of code [if block] is to demonstrate that you can
	 * fill custom errors outside the validation framework as well while still
	 * using internationalized messages.
	 * 
	 * 
	 * if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
	 * FieldError ssoError =new
	 * FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId",
	 * new String[]{user.getSsoId()}, Locale.getDefault()));
	 * result.addError(ssoError); return "registration"; }
	 * 
	 * userService.saveUser(user);
	 * 
	 * model.addAttribute("success", "User " + user.getFirstName() + " "+
	 * user.getLastName() + " registered successfully");
	 * model.addAttribute("loggedinuser", getPrincipal()); //return "success";
	 * return "registrationsuccess"; }
	 */

}