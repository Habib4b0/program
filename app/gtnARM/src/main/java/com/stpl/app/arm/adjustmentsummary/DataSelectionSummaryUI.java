
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.view.DataSelectionView;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha.Ravi
 */
public class DataSelectionSummaryUI extends CommonUI {

    private Navigator navigator;
    private SessionDTO sessionDTO;
    private static final Logger dataSelectionSummaryUILogger = Logger.getLogger(DataSelectionSummaryUI.class);

    @Override
    protected void init(VaadinRequest request) {
        final String userId = request.getRemoteUser();
        if (userId == null) {
            return;
        }
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        final String sessionId = new SimpleDateFormat("hhmmssms").format(new Date());
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        sessionDTO = new SessionDTO();
        sessionDTO.setUserId(Integer.valueOf(userId));
        sessionDTO.setSessionId(Integer.valueOf(sessionId));
        CommonUtils.beforeUnloadCloseUi(this);
        CommonUtils.getUserName();
        dataSelectionSummaryUILogger.info("USER_ID :" + userId);
        dataSelectionSummaryUILogger.info("SESSION_ID :" + sessionId);
        navigator = new Navigator(this, this);
        navigator.addView(DataSelectionView.NAME, new DataSelectionView(sessionDTO, ARMConstants.getAdjustmentSummary()));
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new DataSelectionSummaryUI.HelperListLoadJob("DATA_SELECTION"));
        // This method is to load transaction name of adjustment config value in helper list map
        CommonLogic.loadTransactionName();
    }

    class HelperListLoadJob implements Runnable {

        private final String listName;

        public HelperListLoadJob(String listName) {
            this.listName = listName;
        }

        @Override
        public void run() {
            HelperListUtil.getInstance().loadValuesWithListName(listName);
        }

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
