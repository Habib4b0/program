
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

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha.Ravi
 */
public class DataSelectionSummaryUI extends CommonUI {

    private Navigator navigator;
    private SessionDTO sessionDTO;
    private static final Logger dataSelectionSummaryUILogger = LoggerFactory.getLogger(DataSelectionSummaryUI.class);

    public DataSelectionSummaryUI() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

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
        dataSelectionSummaryUILogger.info("USER_ID :{}", userId);
        dataSelectionSummaryUILogger.info("SESSION_ID :{}", sessionId);
        navigator = new Navigator(this, this);
        navigator.addView(DataSelectionView.NAME, new DataSelectionView(sessionDTO, ARMConstants.getAdjustmentSummary()));
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(new DataSelectionSummaryUI.HelperListLoadJob("DATA_SELECTION"));
        // This method is to load transaction name of adjustment config value in helper list map
        CommonLogic.loadTransactionName();
    }

    static class HelperListLoadJob implements Runnable {

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
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.navigator);
        hash = 43 * hash + Objects.hashCode(this.sessionDTO);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataSelectionSummaryUI other = (DataSelectionSummaryUI) obj;
        if (!Objects.equals(this.navigator, other.navigator)) {
            return false;
        }
        return Objects.equals(this.sessionDTO, other.sessionDTO);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}
