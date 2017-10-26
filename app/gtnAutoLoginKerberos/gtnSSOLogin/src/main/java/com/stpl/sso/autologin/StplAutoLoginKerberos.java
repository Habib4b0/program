package com.stpl.sso.autologin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.security.auth.AutoLogin;
import com.stpl.portal.security.auth.AutoLoginException;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.stpl.portal.util.PortalUtil;

import com.stpl.sso.kerberos.spnego.SpnegoHttpFilter;
import com.stpl.sso.kerberos.spnego.SpnegoPrincipal;
import javax.servlet.http.Cookie;

public class StplAutoLoginKerberos implements AutoLogin {

    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger
            .getLogger(StplAutoLoginKerberos.class);

    @Override
    public String[] handleException(HttpServletRequest request, HttpServletResponse response, Exception arg2)
            throws AutoLoginException {
        return null;
    }

    @Override
    public String[] login(HttpServletRequest request, HttpServletResponse response) throws AutoLoginException {
        LOGGER.info("Inside stpl auto login");
        SpnegoHttpFilter filter = new SpnegoHttpFilter();
        SpnegoPrincipal princ = null;
        try {
            princ = filter.getPrincipal(request, response);
        } catch (IOException | ServletException ex) {
            LOGGER.error(ex);
        }
        long companyId = PortalUtil.getCompanyId(request);
        String screenName = princ.getName().trim();
        LOGGER.info("Inside stpl auto login: ScreenName " + screenName);
        String username = screenName.substring(0, screenName.indexOf("@"));
        User user = null;
        try {
            user = UserLocalServiceUtil.getUserByScreenName(companyId, username);
            new StplCreateUser().updateUser(user, username);
            this.bpmLogin(request, response, username);
        } catch (PortalException ex) {
            this.bpmLogin(request, response, username);
            return createUser(companyId, request, screenName);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            ex.printStackTrace();
        }
        return getUserString(user);

    }

    public String[] getUserString(User user) {
        System.out.println("Inside getUserString ");
        System.out.println("User " + user.getUserId());
        System.out.println("User-Screen  " + user.getPassword());
        return new String[]{String.valueOf(user.getUserId()), user.getPassword(),
            String.valueOf(user.isPasswordEncrypted())};
    }

    private String[] createUser(long companyId, HttpServletRequest request, String screenName) {
        try {
            String username = screenName.substring(0, screenName.indexOf("@"));
            User newUser = new StplCreateUser().addUser(request, companyId, username, "", screenName, username, screenName);
            return getUserString(newUser);
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
            ex.printStackTrace();
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
