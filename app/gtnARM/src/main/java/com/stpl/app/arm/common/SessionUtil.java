/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.common;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ConstantsUtils;

import com.vaadin.server.VaadinSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha.Ravi
 */
public class SessionUtil {

    protected SessionUtil() {

    }

    public static final int COUNT = 1;

    public static SessionDTO createSession() throws ParseException {
        SessionDTO session = new SessionDTO();
        session.setVaadinSessionId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SESSION_ID)));
        session.setUserId((Integer)(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
        session.setAction(StringUtils.EMPTY);
        session.setEditOperation("N");
        session.setUserName(String.valueOf(VaadinSession.getCurrent().getAttribute("userName")));
        final Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat(ARMUtils.DATE_FORMAT);
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        String dateString = fmt.format(tempDate);
        Date date = fmt.parse(dateString);
        session.setSessionDate(date);
        session.setSessionId(Integer.valueOf(fmtID.format(tempDate)));
        session.setCurrentDate(tempDate);

        return session;
    }

    public static String processProjectionId(String projectionId) {
        return projectionId.replaceAll(",", StringUtils.EMPTY);
    }

}
