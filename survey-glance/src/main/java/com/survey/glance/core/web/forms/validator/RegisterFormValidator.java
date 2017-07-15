package com.survey.glance.core.web.forms.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.survey.glance.core.web.forms.RegisterFormTO;

@Component("registerFormValidator")
public class RegisterFormValidator implements Validator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		final RegisterFormTO regForm = (RegisterFormTO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "regemail",
				"required.regemail", "Email is required.");
		
		// email validation in spring
		if (!(regForm.getRegemail() != null && regForm.getRegemail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(regForm.getRegemail());
			if (!matcher.matches()) {
				errors.rejectValue("regemail", "regemail.incorrect",
						"Enter a correct email");
			}
		}
		
		ValidationUtils.rejectIfEmpty(errors, "regusername", "required.regusername",
				"Username is required.");
		ValidationUtils.rejectIfEmpty(errors, "regpassword", "required.regpassword",
				"Password is required.");
		ValidationUtils.rejectIfEmpty(errors, "confirmreppassword", "required.confirmreppassword",
				"Confirm password is required.");
		
		if (regForm.getAgreement()!=null) {
			errors.rejectValue("agreement", "required.agreement", "User Agreement must be accepted");
		}
		
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agreement",
				"required.agreement", "User Agreement must be accepted.");*/
		// checkbox validation
		/*if (regForm.getAgreement().length() <= 0) {
			errors.rejectValue("interest", "noselect.interest",
					"Select atleast one interest");
		}*/
		
		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id",
				"Id is required.");*/

		// input string conatains numeric values only
		/*if (regForm.getId() != null) {
			pattern = Pattern.compile(ID_PATTERN);
			matcher = pattern.matcher(regForm.getId().toString());
			if (!matcher.matches()) {
				errors.rejectValue("id", "id.incorrect",
						"Enter a numeric value");
			}

			// input string can not exceed that a limit
			if (regForm.getId().toString().length() > 5) {
				errors.rejectValue("id", "id.exceed",
						"Id should not contain more than 5 digits");
			}
		}*/

		

		// input string conatains characters only
		/*if (!(regForm.getRegusername() != null && regForm.getRegusername().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(regForm.getRegusername());
			if (!matcher.matches()) {
				errors.rejectValue("regusername", "regusername.containNonChar",
						"Enter a valid name");
			}
		}*/

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
				"required.phone", "Phone is required.");*/

		/*// phone number validation
		if (!(regForm.getPhone() != null && regForm.getPhone().isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(regForm.getPhone());
			if (!matcher.matches()) {
				errors.rejectValue("phone", "phone.incorrect",
						"Enter a correct phone number");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Password is required.");*/

		// password matching validation
		if (regForm.getRegpassword() != null && regForm.getConfirmreppassword() != null && 
				!regForm.getRegpassword().equals(regForm.getConfirmreppassword())) {
			errors.rejectValue("confirmreppassword", "password.mismatch",
					"Password does not match");
		}

		// drop down list validation
		/*if (regForm.getCity().equals("select")) {
			errors.rejectValue("city", "city.select", "select a city");
		}

		// text area validation
		ValidationUtils.rejectIfEmpty(errors, "overview", "required.overview",
				"Overview is required.");*/

		

	}

}
