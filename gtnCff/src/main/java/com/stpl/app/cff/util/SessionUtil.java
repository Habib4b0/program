/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.fileSelection.Util.ConstantsUtils;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Manasa
 */
public class SessionUtil {

    public static int count = 1;

    public SessionDTO createSession() {
        SessionDTO session = new SessionDTO();
        session.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID)));
        session.setAction("");
        session.setEditOperation("N");
        session.setUserName(String.valueOf(VaadinSession.getCurrent().getAttribute("userName")));
        final Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        session.setSessionDate(fmt.format(tempDate));
        session.setSessionId(fmtID.format(tempDate));
        return session;
    }

    public static String processProjectionId(String projectionId) {
        return projectionId.replaceAll(",", "");
    }
}
