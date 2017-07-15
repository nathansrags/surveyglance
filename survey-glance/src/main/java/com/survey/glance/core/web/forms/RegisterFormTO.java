package com.survey.glance.core.web.forms;

/**
 * @author Administrator
 *
 */
public class RegisterFormTO {

	private String regusername;
	private String regemail;
	private String regpassword;
	private String confirmreppassword;
	private String agreement;

	public String getRegusername() {
		return regusername;
	}

	public void setRegusername(final String regusername) {
		this.regusername = regusername;
	}

	public String getRegemail() {
		return regemail;
	}

	public void setRegemail(final String regemail) {
		this.regemail = regemail;
	}

	public String getRegpassword() {
		return regpassword;
	}

	public void setRegpassword(final String regpassword) {
		this.regpassword = regpassword;
	}

	public String getConfirmreppassword() {
		return confirmreppassword;
	}

	public void setConfirmreppassword(String confirmreppassword) {
		this.confirmreppassword = confirmreppassword;
	}

	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

}
