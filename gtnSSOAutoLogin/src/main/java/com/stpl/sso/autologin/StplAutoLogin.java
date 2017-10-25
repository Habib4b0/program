package com.stpl.sso.autologin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.core.Response;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.security.auth.AutoLogin;
import com.stpl.portal.security.auth.AutoLoginException;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.portal.util.PortalUtil;
import com.stpl.sso.autologin.config.StplConfigReader;
import com.stpl.sso.kerberos.spnego.SpnegoHttpFilter;
import com.stpl.sso.kerberos.spnego.SpnegoPrincipal;
import com.stpl.sso.saml.StplSAMLResponseParser;

public class StplAutoLogin implements AutoLogin {

	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(StplAutoLogin.class);
	private final StplSAMLResponseParser samlResponseParser = new StplSAMLResponseParser();

	@Override
	public String[] handleException(HttpServletRequest request, HttpServletResponse response, Exception arg2)
			throws AutoLoginException {
		return null;
	}

	@Override
	public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
		String emailAddress = null;
		if (SSOConstants.SAML.equals(StplConfigReader.getInstance().getPropertyBean().getSsoType())) {
			emailAddress = doSamlSSO(request, response);
		} else {
			emailAddress = doKerberos(request, response);
		}
		if (emailAddress == null) {
			return null;
		}
		String username = emailAddress.substring(0, emailAddress.indexOf("@"));
		User user = null;
		try {
			user = getLiferayUser(request, response, emailAddress, username);
		} catch (SystemException ex) {
			LOGGER.error(ex);
		}
		return getUserString(user);

	}

	public String doSamlSSO(HttpServletRequest request, HttpServletResponse response) {
		String emailAddress = "";
		String samlResponseString = request.getParameter("SAMLResponse"); // constants
		String relayState = request.getParameter("RelayState");

		if (samlResponseString == null) {
			request.setAttribute(AutoLogin.AUTO_LOGIN_REDIRECT,
					StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getIdpURL());
			return null;
		}

		if (!StplConfigReader.getInstance().getPropertyBean().getSamlPropertyBean().getRelayState()
				.equals(relayState)) {
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
			LOGGER.error(ex);
		} catch (Exception e){
			LOGGER.error(e);
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
		try {
			User newUser = new StplCreateUserService().addUser(request, companyId, username, "", emailAddress, username,
					emailAddress);
			this.bpmLogin(request, response, username);
			return newUser;
		} catch (Exception e) {
			LOGGER.error(e);
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

}
