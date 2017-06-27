package com.survey.glance.core.web.email.vo;

/**
 * @author Administrator
 *
 */
public class MailMessageVO {

	private String fromRecepient;
	private String toRecepient;
	private String subject;
	private String[] toMultipleRecepients;
	private String message;
	private String bcc;
	private String[] bccs;
	private String text;
	private String templateName;

	/**
	 * @return
	 */
	public String getFromRecepient() {
		return fromRecepient;
	}

	/**
	 * @param fromRecepient
	 */
	public void setFromRecepient(final String fromRecepient) {
		this.fromRecepient = fromRecepient;
	}

	/**
	 * @return
	 */
	public String getToRecepient() {
		return toRecepient;
	}

	/**
	 * @param toRecepient
	 */
	public void setToRecepient(final String toRecepient) {
		this.toRecepient = toRecepient;
	}

	/**
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(final String subject) {
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String[] getToMultipleRecepients() {
		return toMultipleRecepients;
	}

	/**
	 * @param toMultipleRecepients
	 */
	public void setToMultipleRecepients(final String[] toMultipleRecepients) {
		this.toMultipleRecepients = toMultipleRecepients;
	}

	/**
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @return
	 */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 */
	public void setBcc(final String bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return
	 */
	public String[] getBccs() {
		return bccs;
	}

	/**
	 * @param bccs
	 */
	public void setBccs(final String[] bccs) {
		this.bccs = bccs;
	}

	/**
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * @return
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
