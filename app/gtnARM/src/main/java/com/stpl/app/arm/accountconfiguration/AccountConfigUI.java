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
import com.stpl.app.utils.ConstantsUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=ARM",
    "javax.portlet.name=AccountConfiguration",
    "javax.portlet.display-name=Account Configuration",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class AccountConfigUI extends UI {

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger ACCOUNT_CONFIG_LOGGER = LoggerFactory.getLogger(AccountConfigUI.class);

    /**
     * The method is used to adding styles, navigator initialization and view
     * navigation Performing the initialization in a constructor is not
     * suggested as the state of the UI is not properly set up when the
     * constructor is invoked.
     *
     * @param request
     */
    public AccountConfigUI() {
        super();
    }

    @Override
    protected void init(VaadinRequest request) {
        try {
            ACCOUNT_CONFIG_LOGGER.info("Enters inside the AccountConfigUI");
            CommonLogic.beforeUnloadCloseUi(this);
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
            final String userId = request.getRemoteUser();
            if (userId != null) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
                final String sessionId = request.getWrappedSession().getId();
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
                HelperListUtil.getInstance().loadValuesWithListName("ADJUSTMENT_CONFIGURATION");
                SessionDTO sessionDTO = new SessionDTO();
                sessionDTO.setUserId(Integer.valueOf(userId));
                Navigator navigator = new Navigator(this, this);
                navigator.addView(AccountConfigView.NAME, new AccountConfigView(sessionDTO));
            }
            ACCOUNT_CONFIG_LOGGER.info("Exits the AccountConfigUI UI");
        } catch (Exception ex) {
            ACCOUNT_CONFIG_LOGGER.error("Error While Creating AccountConfigUI ", ex);
        }
    }

    @Override
    public boolean equals(Object accUiobj) {
        ACCOUNT_CONFIG_LOGGER.debug("Enters the AccountConfig Equals");
        return super.equals(accUiobj);
    }

    @Override
    public int hashCode() {
        ACCOUNT_CONFIG_LOGGER.debug("Enters the AccountConfig Hashcode");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream accUiout) throws IOException {
        ACCOUNT_CONFIG_LOGGER.debug("Enters the AccountConfig WriteObject");
        accUiout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream accUiin) throws IOException, ClassNotFoundException {
        ACCOUNT_CONFIG_LOGGER.debug("Enters the AccountConfig ReadObject");
        accUiin.defaultReadObject();
    }
}
