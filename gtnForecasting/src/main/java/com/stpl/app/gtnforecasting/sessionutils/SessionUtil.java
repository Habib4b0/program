/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.sessionutils;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author soundarrajan
 */
public class SessionUtil {

    public static int count = 1;

    public static SessionDTO createSession() {
        SessionDTO session = new SessionDTO();
        session.setVaadinSessionId(String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.SESSION_ID)));
        session.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(Constant.USER_ID)));
        session.setAction(StringUtils.EMPTY);
        session.setEditOperation("N");
        session.setUserName(String.valueOf(VaadinSession.getCurrent().getAttribute("userName")));
        final Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat(Constant.DATE_FORMAT);
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssSSS");
        session.setSessionDate(fmt.format(tempDate));
        session.setSessionId(fmtID.format(tempDate));
        session.setCurrentDate(tempDate);

        return session;
    }

    public static String processProjectionId(String projectionId) {
        return projectionId.replaceAll(",", StringUtils.EMPTY);
    }
}
