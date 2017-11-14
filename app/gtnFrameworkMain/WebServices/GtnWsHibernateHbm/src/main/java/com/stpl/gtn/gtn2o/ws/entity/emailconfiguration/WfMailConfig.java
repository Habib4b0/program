package com.stpl.gtn.gtn2o.ws.entity.emailconfiguration;

import java.util.Date;

public class WfMailConfig implements java.io.Serializable {

	private int wfMailConfigSid;
	private String hostName;
	private String emailAddress;
	private String password;
	private String portNumber;
	private String smtpFlag;
	private char inboundStatus;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private String testMailAddress;

	public WfMailConfig() {
	}

	public WfMailConfig(int wfMailConfigSid, String hostName,
			String emailAddress, String password, char inboundStatus,
			int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
		this.wfMailConfigSid = wfMailConfigSid;
		this.hostName = hostName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.inboundStatus = inboundStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public WfMailConfig(int wfMailConfigSid, String hostName,
			String emailAddress, String password, String portNumber,
			String smtpFlag, char inboundStatus, int createdBy,
			Date createdDate, int modifiedBy, Date modifiedDate,
			String testMailAddress) {
		this.wfMailConfigSid = wfMailConfigSid;
		this.hostName = hostName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.portNumber = portNumber;
		this.smtpFlag = smtpFlag;
		this.inboundStatus = inboundStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.testMailAddress = testMailAddress;
	}

	public int getWfMailConfigSid() {
		return this.wfMailConfigSid;
	}

	public void setWfMailConfigSid(int wfMailConfigSid) {
		this.wfMailConfigSid = wfMailConfigSid;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortNumber() {
		return this.portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public String getSmtpFlag() {
		return this.smtpFlag;
	}

	public void setSmtpFlag(String smtpFlag) {
		this.smtpFlag = smtpFlag;
	}

	public char getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTestMailAddress() {
		return this.testMailAddress;
	}

	public void setTestMailAddress(String testMailAddress) {
		this.testMailAddress = testMailAddress;
	}
}
