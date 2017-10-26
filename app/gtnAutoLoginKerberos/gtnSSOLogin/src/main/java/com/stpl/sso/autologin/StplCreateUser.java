/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.sso.autologin;

import com.stpl.portal.UserPasswordException;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.stpl.portal.model.User;
import com.stpl.portal.service.ServiceContext;
import com.stpl.portal.service.ServiceContextFactory;
import com.stpl.portal.service.UserLocalServiceUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author Abishek.Ram
 */
public class StplCreateUser {

    private static final Logger LOGGER = Logger.getLogger(StplCreateUser.class);

    public StplCreateUser() {
    }

    public User addUser(HttpServletRequest request, long companyid, String firstName, String lastName, String email,
            String password, String screenName) throws Exception {
        LOGGER.info("Creating user -" + screenName);
        try {

            long[] emptyLong = {};
            long id = UserLocalServiceUtil.getDefaultUserId(companyid);
            ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

            User user = UserLocalServiceUtil.addUser(id, companyid, false, password, password, true, screenName,
                    email, 0, null, Locale.ENGLISH, firstName, "", lastName, 0, 0, true, 5, 19, 1994, "", emptyLong,
                    emptyLong, emptyLong, emptyLong, true, serviceContext);

            this.updateUser(user, password);
            return user;

        } catch (PortalException | SystemException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
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

    public User updatePassword(User user, String password) {
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
