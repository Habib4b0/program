/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration;

import com.stpl.app.arm.adjustmentreserveconfiguration.ui.view.AdjustmentReserveView;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jboss.logging.Logger;

/**
 * The UI Class For Adjustment Reserve Configuration which is configured in
 * portlet.xml for UI class trigger and which brings up to screen to portlet.
 *
 * @author
 */
public class AdjustmentReserveUI extends UI {

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
    /**
     * The Logger for Adjustment Reserve UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentReserveUI.class);

    /**
     * It is used to adding styles, navigator initialization and view navigation
     * Performing the initialization in a constructor is not suggested as the
     * state of the UI is not properly set up when the constructor is invoked.
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        try {
            LOGGER.debug("Enters inside the Adjustment Reserve UI");
            CommonLogic.beforeUnloadCloseUi(this);
            final String userId = request.getRemoteUser();
            if (userId == null) {
                return;
            }
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");

            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.submit(new AdjustmentReserveUI.HelperListLoadJob("ADJUSTMENT_RESERVE_CONFIGURATION"));
            // This method is to load transaction name of adjustment config value in helper list map
            CommonLogic.loadTransactionName();
            //THis method is load credit and debit drop down loading to drop down map.
            CommonLogic.loadDebitAndCrditName();
            LOGGER.info("USER_ID :" + userId);
            LOGGER.info("SESSION_ID :" + sessionId);
            sessionDTO = new SessionDTO();
            navigator = new Navigator(this, this);
            navigator.addView(AdjustmentReserveView.NAME, new AdjustmentReserveView(sessionDTO));
            LOGGER.debug("Exits the Adjustment Reserve UI");
        } catch (Exception ex) {
            LOGGER.error("Error While Creating AdjustmentReserveUI " + ex.getMessage());
        }

        //         Configure the error handler for the UI
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                // Find the final cause
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) {

                        cause += t.getClass().getName();
                    }
                }

                LOGGER.error(cause);
                // Do the default error handling (optional)
            }
        });
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
    protected void refresh(VaadinRequest request) {
        CommonLogic.loadTransactionName();

    }

}
