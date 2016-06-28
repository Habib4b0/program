/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author santanukumar
 */
public class SessionUtil {

    public static final int count = 1;

    public static SessionDTO createSession() {
        SessionDTO session = new SessionDTO();
        session.setVaadinSessionId(String.valueOf(VaadinSession.getCurrent().getAttribute("sessionId")));
        session.setUserId(String.valueOf(VaadinSession.getCurrent().getAttribute("userId")));
        session.setAction(StringUtils.EMPTY);
        session.setEditOperation("N");
        final Date tempDate = new Date();
        final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        session.setSessionDate(fmt.format(tempDate));
        session.setSessionId(fmtID.format(tempDate));
        session.setCurrentDate(tempDate);

        return session;
    }

    public static String processProjectionId(String projectionId) {
        return projectionId.replaceAll(",", StringUtils.EMPTY);
    }
    
    /**
     * 
     * @param uI  UI Object
     * @param sessionDTO SessionDTO Object
     */
    
    public static final void beforeUnloadCloseUi(final UI uI, final SessionDTO sessionDTO) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {

            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                VaadinSession.getCurrent().setAttribute("systemId", sessionDTO.getSystemId());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.MODE, sessionDTO.getMode());
                if (!"true".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.EXCEL_CLOSE))) {
                    uI.close();
                } else {
                    VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, StringUtils.EMPTY);
                }
            }
        });
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE) != null && !StringUtils.EMPTY.equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE))) {
            sessionDTO.setMode((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.MODE));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.MODE, StringUtils.EMPTY);
        }
        if (VaadinSession.getCurrent().getAttribute("systemId") != null && !StringUtils.EMPTY.equals(VaadinSession.getCurrent().getAttribute("systemId"))) {
            sessionDTO.setSystemId((Integer) VaadinSession.getCurrent().getAttribute("systemId"));
            VaadinSession.getCurrent().setAttribute("systemId", StringUtils.EMPTY);
        }

    }
}
