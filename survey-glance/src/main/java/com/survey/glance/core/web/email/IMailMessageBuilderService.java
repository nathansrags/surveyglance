package com.survey.glance.core.web.email;

import java.util.Map;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Administrator
 *
 */
public interface IMailMessageBuilderService extends MailSender {

	
	/**
	 * 
	 */
	void sendMail();

	
	/**
	 * @param msg
	 * @param hTemplateVariables
	 */
	void send(final SimpleMailMessage msg,
			final Map<String, Object> hTemplateVariables);
	
	
	void sendEmailTemplate();

}
