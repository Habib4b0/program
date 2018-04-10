package com.stpl.gtn.gtn2o.ws.entity.user;
// Generated Feb 22, 2017 8:16:57 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private long userId;
	private long mvccVersion;
	private String uuid;
	private Long companyId;
	private Date createDate;
	private Date modifiedDate;
	private Boolean defaultUser;
	private Long contactId;
	private String password;
	private Boolean passwordEncrypted;
	private Boolean passwordReset;
	private Date passwordModifiedDate;
	private String digest;
	private String reminderQueryQuestion;
	private String reminderQueryAnswer;
	private Integer graceLoginCount;
	private String screenName;
	private String emailAddress;
	private Long facebookId;
	private String googleUserId;
	private Long ldapServerId;
	private String openId;
	private Long portraitId;
	private String languageId;
	private String timeZoneId;
	private String greeting;
	private String comments;
	private String firstName;
	private String middleName;
	private String lastName;
	private String jobTitle;
	private Date loginDate;
	private String loginIp;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Date lastFailedLoginDate;
	private Integer failedLoginAttempts;
	private Boolean lockout;
	private Date lockoutDate;
	private Boolean agreedToTermsOfUse;
	private Boolean emailAddressVerified;
	private Integer status;

	public User() {
	}

	public User(long userId, long mvccVersion) {
		this.userId = userId;
		this.mvccVersion = mvccVersion;
	}

	public User(long userId, long mvccVersion, String uuid, Long companyId, Date createDate, Date modifiedDate,
			Boolean defaultUser, Long contactId, String password, Boolean passwordEncrypted,
			Boolean passwordReset, Date passwordModifiedDate, String digest, String reminderQueryQuestion,
			String reminderQueryAnswer, Integer graceLoginCount, String screenName,
			String emailAddress, Long facebookId, String googleUserId, Long ldapServerId,
			String openId, Long portraitId, String languageId, String timeZoneId,
			String greeting, String comments, String firstName, String middleName,
			String lastName, String jobTitle, Date loginDate, String loginIp, Date lastLoginDate,
			String lastLoginIp, Date lastFailedLoginDate, Integer failedLoginAttempts, Boolean lockout,
			Date lockoutDate, Boolean agreedToTermsOfUse, Boolean emailAddressVerified, Integer status) {
		this.userId = userId;
		this.mvccVersion = mvccVersion;
		this.uuid = uuid;
		this.companyId = companyId;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.defaultUser = defaultUser;
		this.contactId = contactId;
		this.password = password;
		this.passwordEncrypted = passwordEncrypted;
		this.passwordReset = passwordReset;
		this.passwordModifiedDate = passwordModifiedDate;
		this.digest = digest;
		this.reminderQueryQuestion = reminderQueryQuestion;
		this.reminderQueryAnswer = reminderQueryAnswer;
		this.graceLoginCount = graceLoginCount;
		this.screenName = screenName;
		this.emailAddress = emailAddress;
		this.facebookId = facebookId;
		this.googleUserId = googleUserId;
		this.ldapServerId = ldapServerId;
		this.openId = openId;
		this.portraitId = portraitId;
		this.languageId = languageId;
		this.timeZoneId = timeZoneId;
		this.greeting = greeting;
		this.comments = comments;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.loginDate = loginDate;
		this.loginIp = loginIp;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.lastFailedLoginDate = lastFailedLoginDate;
		this.failedLoginAttempts = failedLoginAttempts;
		this.lockout = lockout;
		this.lockoutDate = lockoutDate;
		this.agreedToTermsOfUse = agreedToTermsOfUse;
		this.emailAddressVerified = emailAddressVerified;
		this.status = status;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMvccVersion() {
		return this.mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getDefaultUser() {
		return this.defaultUser;
	}

	public void setDefaultUser(Boolean defaultUser) {
		this.defaultUser = defaultUser;
	}

	public Long getContactId() {
		return this.contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getPasswordEncrypted() {
		return this.passwordEncrypted;
	}

	public void setPasswordEncrypted(Boolean passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public Boolean getPasswordReset() {
		return this.passwordReset;
	}

	public void setPasswordReset(Boolean passwordReset) {
		this.passwordReset = passwordReset;
	}

	public Date getPasswordModifiedDate() {
		return this.passwordModifiedDate;
	}

	public void setPasswordModifiedDate(Date passwordModifiedDate) {
		this.passwordModifiedDate = passwordModifiedDate;
	}

	public String getDigest() {
		return this.digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getReminderQueryQuestion() {
		return this.reminderQueryQuestion;
	}

	public void setReminderQueryQuestion(String reminderQueryQuestion) {
		this.reminderQueryQuestion = reminderQueryQuestion;
	}

	public String getReminderQueryAnswer() {
		return this.reminderQueryAnswer;
	}

	public void setReminderQueryAnswer(String reminderQueryAnswer) {
		this.reminderQueryAnswer = reminderQueryAnswer;
	}

	public Integer getGraceLoginCount() {
		return this.graceLoginCount;
	}

	public void setGraceLoginCount(Integer graceLoginCount) {
		this.graceLoginCount = graceLoginCount;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(Long facebookId) {
		this.facebookId = facebookId;
	}

	public String getGoogleUserId() {
		return this.googleUserId;
	}

	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
	}

	public Long getLdapServerId() {
		return this.ldapServerId;
	}

	public void setLdapServerId(Long ldapServerId) {
		this.ldapServerId = ldapServerId;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getPortraitId() {
		return this.portraitId;
	}

	public void setPortraitId(Long portraitId) {
		this.portraitId = portraitId;
	}

	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getTimeZoneId() {
		return this.timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getGreeting() {
		return this.greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastFailedLoginDate() {
		return this.lastFailedLoginDate;
	}

	public void setLastFailedLoginDate(Date lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}

	public Integer getFailedLoginAttempts() {
		return this.failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public Boolean getLockout() {
		return this.lockout;
	}

	public void setLockout(Boolean lockout) {
		this.lockout = lockout;
	}

	public Date getLockoutDate() {
		return this.lockoutDate;
	}

	public void setLockoutDate(Date lockoutDate) {
		this.lockoutDate = lockoutDate;
	}

	public Boolean getAgreedToTermsOfUse() {
		return this.agreedToTermsOfUse;
	}

	public void setAgreedToTermsOfUse(Boolean agreedToTermsOfUse) {
		this.agreedToTermsOfUse = agreedToTermsOfUse;
	}

	public Boolean getEmailAddressVerified() {
		return this.emailAddressVerified;
	}

	public void setEmailAddressVerified(Boolean emailAddressVerified) {
		this.emailAddressVerified = emailAddressVerified;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
