/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration;

import com.stpl.app.arm.adjustmentrateconfiguration.ui.view.AdjustmentRateView;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jboss.logging.Logger;

/**
 * The UI Class For Adjustment Rate Configuration which is configured in
 * portlet.xml for UI class trigger and which brings up to screen to portlet.
 *
 * @author
 */
public class AdjustmentRateUI extends UI {

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentRateUI.class);
    /**
     * The Navigator class manages a collection of views that implement the View
     * interface. The views can be either registered beforehand or acquired from
     * a view provider. When registering, the views must have a name identifier
     * and be added to a navigator with addView(). You can register new views at
     * any point. Once registered, you can navigate to them with navigateTo().
     */
    private Navigator navigator;
    /**
     * The Session DTO is POJO class used in UI class as binder, Which holds
     * UserId and session Id though out the UI Object(views, forms and sub
     * classes)
     */
    SessionDTO sessionDTO;

    public static boolean EXCEL_CLOSE = false;

    /**
     * The method is used to adding styles, navigator initialization and view
     * navigation Performing the initialization in a constructor is not
     * suggested as the state of the UI is not properly set up when the
     * constructor is invoked.
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        try {
            LOGGER.debug("Enters inside the Adjustment Rate UI");
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
            final String userId = request.getRemoteUser();
            if (userId != null) {
                CommonLogic.beforeUnloadCloseUi(this);
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
                final String sessionId = request.getWrappedSession().getId();
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
                sessionDTO = new SessionDTO();
                LOGGER.info("USER_ID :" + userId);
                LOGGER.info("SESSION_ID :" + sessionId);
                navigator = new Navigator(this, this);
                navigator.addView(AdjustmentRateView.NAME, new AdjustmentRateView(sessionDTO));
                ExecutorService service = Executors.newSingleThreadExecutor();
                service.submit(new HelperListLoadJob("ADJUSTMENT_RATE_CONFIGURATION"));
            }
            LOGGER.debug("Exits the Adjustment Reserve UI");
        } catch (Exception ex) {
            LOGGER.error("Error While Creating AdjustmentRateUI " + ex);
        }
    }

    class HelperListLoadJob implements Runnable {

        private final String moduleName;

        public HelperListLoadJob(String moduleName) {
            this.moduleName = moduleName;
        }

        @Override
        public void run() {
            HelperListUtil.getInstance().loadValuesWithListName(moduleName);
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
