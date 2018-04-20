/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.view.BalanceSummaryReportDataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportUI extends CommonUI {

    private static final Logger LOGGERBALSUMMARYUI = LoggerFactory.getLogger(BalanceSummaryReportUI.class);
    private static boolean excelClose = false;

    public static boolean getExcelClose() {
        return excelClose;
    }

    @Override
    protected void init(VaadinRequest request) {
        Navigator navigator;
        SessionDTO sessionDTO;
        CommonLogic.beforeUnloadCloseUiBSR(this);
        final String userId = request.getRemoteUser();
        if (userId == null) {
            return;
        }
        HelperListUtil.getInstance().loadValuesWithListName("BALANCE_SUMMARY");
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        final String sessionId = new SimpleDateFormat("hhmmssms").format(new Date());
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        sessionDTO = new SessionDTO();
        sessionDTO.setUserId(Integer.valueOf(userId));
        sessionDTO.setSessionId(Integer.valueOf(sessionId));
        try {
            StplSecurity.getUserName();
        } catch (SystemException ex) {
            LOGGER.error("Error in StplSecurity" + ex);
        }
        LOGGERBALSUMMARYUI.info("USER_ID :" + userId);
        LOGGERBALSUMMARYUI.debug("SESSION_ID :" + sessionId);
        navigator = new Navigator(this, this);
        HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        navigator.addView(BalanceSummaryReportDataSelectionView.NAME, new BalanceSummaryReportDataSelectionView(sessionDTO, "Balance Summary Report"));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static void setExcelClose(boolean excelClose) {
        BalanceSummaryReportUI.excelClose = excelClose;
    }
}
