/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration;

import com.stpl.app.arm.accountconfiguration.view.AccountConfigView;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 *
 * @author Srithar.Raju
 */
public class AccountConfigUI extends UI {

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = Logger.getLogger(AccountConfigUI.class);
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
    SessionDTO sessionDTO = new SessionDTO();

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
            LOGGER.info("Enters inside the AccountConfigUI");
            CommonLogic.beforeUnloadCloseUi(this);
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
            final String userId = request.getRemoteUser();
            if (userId != null) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
                final String sessionId = request.getWrappedSession().getId();
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
                HelperListUtil.getInstance().loadValuesWithListName("ADJUSTMENT_CONFIGURATION");
                sessionDTO = new SessionDTO();
                sessionDTO.setUserId(Integer.valueOf(userId));
                navigator = new Navigator(this, this);
                navigator.addView(AccountConfigView.NAME, new AccountConfigView(sessionDTO));
            }
            LOGGER.info("Exits the AccountConfigUI UI");
        } catch (Exception ex) {
            LOGGER.error("Error While Creating AccountConfigUI " + ex);
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
