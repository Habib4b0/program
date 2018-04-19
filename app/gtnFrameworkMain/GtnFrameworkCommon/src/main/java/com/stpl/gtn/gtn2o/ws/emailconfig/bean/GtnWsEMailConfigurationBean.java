package com.stpl.gtn.gtn2o.ws.emailconfig.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GtnWsEMailConfigurationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GtnWsEMailConfigurationBean() {
		super();
	}

	private String emailConfigTabSMTP;
	private String emailConfigTabHostName;
	private String emailConfigTabemailAddress;
	private String emailConfigTabPassword;
	private String emailConfigPortNumber;
	private String emailConfigTabTestMailAddress;

	private String emailNotificationTabProcessName;
	private String emailNotificationTabEmailTo;
	private String emailNotificationTabSubject;
	private String emailNotificationTabEmailCc;
	private String emailNotificationTabEmailBody;
	private String emailNotificationTabFailureTo;
	private String emailNotificationTabFailureSubject;
	private String emailNotificationTabFailureEmailCc;
	private String emailNotificationTabFailureEmailBody;
	private List<Object[]> defaultDataLoad;
	private List<Object[]> comboboxOnChangeDataLoad;

	public String getEmailConfigTabSMTP() {
		return emailConfigTabSMTP;
	}

	public void setEmailConfigTabSMTP(String emailConfigTabSMTP) {
		this.emailConfigTabSMTP = emailConfigTabSMTP;
	}

	public String getEmailConfigTabHostName() {
		return emailConfigTabHostName;
	}

	public void setEmailConfigTabHostName(String emailConfigTabHostName) {
		this.emailConfigTabHostName = emailConfigTabHostName;
	}

	public String getEmailConfigTabemailAddress() {
		return emailConfigTabemailAddress;
	}

	public void setEmailConfigTabemailAddress(String emailConfigTabemailAddress) {
		this.emailConfigTabemailAddress = emailConfigTabemailAddress;
	}

	public String getEmailConfigTabPassword() {
		return emailConfigTabPassword;
	}

	public void setEmailConfigTabPassword(String emailConfigTabPassword) {
		this.emailConfigTabPassword = emailConfigTabPassword;
	}

	public String getEmailConfigPortNumber() {
		return emailConfigPortNumber;
	}

	public void setEmailConfigPortNumber(String emailConfigPortNumber) {
		this.emailConfigPortNumber = emailConfigPortNumber;
	}

	public String getEmailConfigTabTestMailAddress() {
		return emailConfigTabTestMailAddress;
	}

	public void setEmailConfigTabTestMailAddress(String emailConfigTabTestMailAddress) {
		this.emailConfigTabTestMailAddress = emailConfigTabTestMailAddress;
	}

	public String getEmailNotificationTabProcessName() {
		return emailNotificationTabProcessName;
	}

	public void setEmailNotificationTabProcessName(String emailNotificationTabProcessName) {
		this.emailNotificationTabProcessName = emailNotificationTabProcessName;
	}

	public String getEmailNotificationTabEmailTo() {
		return emailNotificationTabEmailTo;
	}

	public void setEmailNotificationTabEmailTo(String emailNotificationTabEmailTo) {
		this.emailNotificationTabEmailTo = emailNotificationTabEmailTo;
	}

	public String getEmailNotificationTabSubject() {
		return emailNotificationTabSubject;
	}

	public void setEmailNotificationTabSubject(String emailNotificationTabSubject) {
		this.emailNotificationTabSubject = emailNotificationTabSubject;
	}

	public String getEmailNotificationTabEmailCc() {
		return emailNotificationTabEmailCc;
	}

	public void setEmailNotificationTabEmailCc(String emailNotificationTabEmailCc) {
		this.emailNotificationTabEmailCc = emailNotificationTabEmailCc;
	}

	public String getEmailNotificationTabEmailBody() {
		return emailNotificationTabEmailBody;
	}

	public void setEmailNotificationTabEmailBody(String emailNotificationTabEmailBody) {
		this.emailNotificationTabEmailBody = emailNotificationTabEmailBody;
	}

	public String getEmailNotificationTabFailureTo() {
		return emailNotificationTabFailureTo;
	}

	public void setEmailNotificationTabFailureTo(String emailNotificationTabFailureTo) {
		this.emailNotificationTabFailureTo = emailNotificationTabFailureTo;
	}

	public String getEmailNotificationTabFailureSubject() {
		return emailNotificationTabFailureSubject;
	}

	public void setEmailNotificationTabFailureSubject(String emailNotificationTabFailureSubject) {
		this.emailNotificationTabFailureSubject = emailNotificationTabFailureSubject;
	}

	public String getEmailNotificationTabFailureEmailCc() {
		return emailNotificationTabFailureEmailCc;
	}

	public void setEmailNotificationTabFailureEmailCc(String emailNotificationTabFailureEmailCc) {
		this.emailNotificationTabFailureEmailCc = emailNotificationTabFailureEmailCc;
	}

	public String getEmailNotificationTabFailureEmailBody() {
		return emailNotificationTabFailureEmailBody;
	}

	public void setEmailNotificationTabFailureEmailBody(String emailNotificationTabFailureEmailBody) {
		this.emailNotificationTabFailureEmailBody = emailNotificationTabFailureEmailBody;
	}

	public List<Object[]> getDefaultDataLoad() {
		return defaultDataLoad == null ? null : Collections.unmodifiableList(defaultDataLoad);
	}

	public void setDefaultDataLoad(List<Object[]> defaultDataLoad) {
		this.defaultDataLoad = defaultDataLoad == null ? null : Collections.unmodifiableList(defaultDataLoad);
	}

	public List<Object[]> getComboboxOnChangeDataLoad() {
		return comboboxOnChangeDataLoad == null ? null : Collections.unmodifiableList(comboboxOnChangeDataLoad);
	}

	public void setComboboxOnChangeDataLoad(List<Object[]> comboboxOnChangeDataLoad) {
		this.comboboxOnChangeDataLoad = comboboxOnChangeDataLoad == null ? null
				: Collections.unmodifiableList(comboboxOnChangeDataLoad);
	}


}
