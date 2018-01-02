/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 *
 * @author Abishek.Ram
 */
public class StplCreateUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StplCreateUserService.class);

	public StplCreateUserService() {
	}

	public User addUser(HttpServletRequest request, long companyid, String firstName, String lastName, String email,
			String password, String screenName) throws Exception {
		LOGGER.info("Creating user -" + screenName);
		 getLastNameFromName(firstName,lastName);
		try {

			long[] emptyLong = {};
			long id = UserLocalServiceUtil.getDefaultUserId(companyid);
			ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

			User user = UserLocalServiceUtil.addUser(id, companyid, true, password, password, true, screenName, email,
					0, null, Locale.ENGLISH, firstName, "", lastName, 0, 0, true, 5, 19, 1994, "", emptyLong, emptyLong,
					emptyLong, emptyLong, true, serviceContext);

			this.updateUser(user, password);
			return user;

		} catch (PortalException | SystemException e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	private void  getLastNameFromName(String firstName,String lastName) {
		
	}

	public void updateUser(User user, String password) {
		user = updatePassword(user, password);
		user.setAgreedToTermsOfUse(true);
		user.setReminderQueryQuestion("testQuestion");
		user.setReminderQueryAnswer("testAnswer");
		user.setLanguageId("en_US");
		try {
			UserLocalServiceUtil.updateUser(user);
		} catch (SystemException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private User updatePassword(User user, String password) {
		try {
			user = UserLocalServiceUtil.updatePassword(user.getUserId(), password, password, false);
			return user;
		} catch (UserPasswordException e) {
			LOGGER.info(" Password updated already ");
			return user;
		} catch (PortalException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (SystemException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return user;
	}
}
