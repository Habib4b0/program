/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.view.BalanceSummaryReportDataSelectionView;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryReportUI extends CommonUI {

    private Navigator navigator;
    private SessionDTO sessionDTO;
    private static final Logger LOGGER = Logger.getLogger(BalanceSummaryReportUI.class);

    @Override
    protected void init(VaadinRequest request) {
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
            java.util.logging.Logger.getLogger(BalanceSummaryReportUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOGGER.info("USER_ID :" + userId);
        LOGGER.debug("SESSION_ID :" + sessionId);
        navigator = new Navigator(this, this);
        HelperListUtil.getInstance().loadValuesWithListName("DATA_SELECTION");
        navigator.addView(BalanceSummaryReportDataSelectionView.NAME, new BalanceSummaryReportDataSelectionView(sessionDTO, "Balance Summary Report"));
    }

}
