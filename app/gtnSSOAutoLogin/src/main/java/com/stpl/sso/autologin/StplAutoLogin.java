package com.stpl.sso.autologin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.stpl.sso.autologin.config.StplConfigReader;
import com.stpl.sso.kerberos.spnego.SpnegoHttpFilter;
import com.stpl.sso.kerberos.spnego.SpnegoPrincipal;
import com.stpl.sso.saml.StplSAMLRequestGenerator;
import com.stpl.sso.saml.StplSAMLResponseParser;

public class StplAutoLogin implements AutoLogin {

	private static final Logger LOGGER = LoggerFactory.getLogger(StplAutoLogin.class);
	private final StplSAMLResponseParser samlResponseParser = new StplSAMLResponseParser();

	@Override
	public String[] handleException(HttpServletRequest request, HttpServletResponse response, Exception arg2)
			throws AutoLoginException {
		LOGGER.error("Error in Hook", arg2);
		return null;
	}

	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
		String emailAddress = null;

		LOGGER.info("Initiating AutoLogin");
		if (SSOConstants.SAML.equals(StplConfigReader.getInstance().getPropertyBean().getSsoType())) {
			emailAddress = doSamlSSO(request, response);
		} else {
			emailAddress = doKerberos(request, response);
		}
		if (emailAddress == null) {
			return null;
		}
		LOGGER.info("Email Address is " + emailAddress);
		String username = getUserName(emailAddress);
		User user = null;
		try {
			user = getLiferayUser(request, response, emailAddress, username);
		} catch (SystemException ex) {
			LOGGER.error("Unable to create User", ex);
		}

		new StplBPMLogin().login(user, response);
		return getUserString(user);

	}

	public String doSamlSSO(HttpServletRequest request, HttpServletResponse response) {
		String emailAddress = "";
		String samlResponseString = request.getParameter("SAMLResponse"); // constants

		String relayState = request.getParameter("RelayState");

		if (samlResponseString == null) {

			String idpUrl = StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getIdpURL();
			String isRequestNeeded = StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean()
					.getIsSAMLRequestNeeded();
			if (isRequestNeeded != null && !isRequestNeeded.isEmpty() && "yes".equalsIgnoreCase(isRequestNeeded)) {
				idpUrl = new StplSAMLRequestGenerator().getAuthNRedirectUrl();
			}

			LOGGER.info("- Redirecting to  Idp Url - " + idpUrl);
			request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT, idpUrl);
			return null;
		}

		if (!StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getRelayState()
				.equals(relayState)) {
			LOGGER.info("- Failed for relayState  - {} ", relayState);
			return null;
		}

		Response samlResponse = samlResponseParser.parseAndGetSamlResponse(samlResponseString);
		emailAddress = samlResponseParser.validateAndGetUser(samlResponse);
		return emailAddress;
	}

	public String doKerberos(HttpServletRequest request, HttpServletResponse response) {
		SpnegoHttpFilter filter = new SpnegoHttpFilter();
		SpnegoPrincipal princ = null;
		try {
			princ = filter.getPrincipal(request, response);
			LOGGER.info("Pricipal -" + princ);
		} catch (IOException | ServletException ex) {
			LOGGER.error("Exception in getting Principal ", ex);
		} catch (Exception e) {
			LOGGER.error("Exception in getting Principal ", e);
		}
		if (princ == null) {
			return "";

		}

		return princ.getName().trim();

	}

	public User getLiferayUser(HttpServletRequest request, HttpServletResponse response, String emailAddress,
			String username) throws SystemException {
		boolean newUser = false;
		User user = null;
		long companyId = PortalUtil.getCompanyId(request);
		try {
			user = UserLocalServiceUtil.getUserByScreenName(companyId, username);
		} catch (PortalException ex) {
			newUser = true;
		}

		if (newUser) {
			user = this.createUser(companyId, request, response, emailAddress, username);
			return user;
		}

		this.updateUser(user, companyId, request, response, emailAddress, username, newUser);

		return user;
	}

	public String[] getUserString(User user) {
		LOGGER.info("-- Inside getUserString -- ");
		LOGGER.info("User " + user.getUserId());
		LOGGER.info("User-Screen  " + user.getPassword());
		return new String[] { String.valueOf(user.getUserId()), user.getPassword(),
				String.valueOf(user.isPasswordEncrypted()) };
	}

	private void updateUser(User user, long companyId, HttpServletRequest request, HttpServletResponse response,
			String emailAddress, String username, boolean newUser) {

		new StplCreateUserService().updateUser(user, username);
		this.bpmLogin(request, response, username);

	}

	private User createUser(long companyId, HttpServletRequest request, HttpServletResponse response,
			String emailAddress, String username) {
		String firstName = username;
		String lastName = firstName;

		if (firstName.indexOf('.') != -1) {
			String[] nameArray = firstName.split("\\.");
			firstName = nameArray[0];
			lastName = nameArray[1];
		} else if (firstName.indexOf('_') != -1) {
			String[] nameArray = firstName.split("_");
			firstName = nameArray[0];
			lastName = nameArray[1];
		}
		try {
			User newUser = new StplCreateUserService().addUser(request, companyId, firstName, lastName, emailAddress,
					username, emailAddress);
			this.bpmLogin(request, response, username);
			return newUser;
		} catch (Exception e) {
			LOGGER.error("Exception in creating User through API", e);
		}
		return null;
	}

	private boolean bpmLogin(HttpServletRequest request, HttpServletResponse response, String userName) {
		boolean loginStatus = false;
		try {
			String sessionId = request.getSession().getId();
			response.addCookie(createCookie("bpm_session_id", sessionId, true));
			loginStatus = new StplBPMUserCreation().createBPMUser(userName, sessionId);
		} catch (Exception ex) {
		}
		LOGGER.info("bpmLogin: loginStatus  " + loginStatus);
		return loginStatus;
	}

	private Cookie createCookie(String name, String value, boolean days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(days ? 1 * 24 * 60 * 60 * 1000 : -1);
		cookie.setPath("/");
		return cookie;
	}

	private String getUserName(String emailAddress) {
		String username = emailAddress.substring(0, emailAddress.indexOf("@"));
		for (String iterable_element : StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean()
				.getSpecialCharArray()) {
			username = username.replace(iterable_element, ".");
		}
		return username;
	}

}
