package com.survey.glance.core.web.email;

import java.io.StringWriter;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.survey.glance.core.web.email.vo.MailMessageVO;

/**
 * @author Administrator
 *
 */
@Service("mailMessageBuilderService")
public class MailMessageBuilderServiceImpl implements
		IMailMessageBuilderService {

	@Autowired
	@Qualifier("mailSender")
	private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("velocityEngine")
	private VelocityEngine velocityEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.survey.glance.core.web.email.IMailMessageBuilderService#buildMessage
	 * (com.survey.glance.core.web.email.vo.MailMessageVO)
	 */
	@Override
	public void sendMail() {
		SimpleMailMessage simpleMailMessage = tranformVoToMailMessage();
		mailSender.send(simpleMailMessage);

	}

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		mailSender.send(simpleMessage);

	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		mailSender.send(simpleMessages);

	}

	/**
	 * 
	 */
	@Override
	public void sendEmailTemplate() {
		final SimpleMailMessage message = tranformVoToMailMessage();
		Template template = velocityEngine.getTemplate(
				"templates/welcomeemailtemplate.vm", "UTF-8");
		// + mailMesssageVO.getTemplateName());
		final VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("firstName", "Gopinathan");
		velocityContext.put("lastName", "Gopal");
		velocityContext.put("location", "Rocking Chennai");
		final StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		message.setText(stringWriter.toString());
		mailSender.send(message);
	}

	@Override
	public void send(final SimpleMailMessage msg,
			final Map<String, Object> hTemplateVariables) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(msg.getTo());
				message.setFrom(msg.getFrom());
				message.setSubject(msg.getSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "templates/welcomemailtemplate.vm",
						hTemplateVariables);

				message.setText(body, true);
			}
		};

		mailSender.send(preparator);

	}

	/**
	 * @return
	 */
	private MailMessageVO builMailMessageVO() {
		final MailMessageVO mailMessageVO = new MailMessageVO();
		mailMessageVO.setFromRecepient("donotreply@suveyglance.com");
		mailMessageVO.setToRecepient("nathaninblog@gmail.com");
		mailMessageVO.setText("Welcome User");
		mailMessageVO
				.setSubject("Welcome Aboard | Survey Glance Private Limited");
		return mailMessageVO;
	}

	/**
	 * @return
	 */
	private SimpleMailMessage tranformVoToMailMessage() {
		final MailMessageVO mailMesssageVO = builMailMessageVO();
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(mailMesssageVO.getFromRecepient());
		simpleMailMessage.setTo(mailMesssageVO.getToRecepient());
		simpleMailMessage.setSubject(mailMesssageVO.getSubject());
		simpleMailMessage.setText(mailMesssageVO.getText());
		return simpleMailMessage;

	}
}
