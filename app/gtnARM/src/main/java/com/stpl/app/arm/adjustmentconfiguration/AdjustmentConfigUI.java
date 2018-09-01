/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration;

import com.stpl.app.arm.adjustmentconfiguration.form.AdjustmentConfigForm;
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

@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet")
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=ARM",
    "javax.portlet.name=AdjustmentConfiguration",
    "javax.portlet.display-name=Adjustment Configuration",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class AdjustmentConfigUI extends UI {

    /**
     * The Logger for Adjustment Rate UI the logger usually logs into a file
     * (this can be configured through jboss logging )
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentConfigUI.class);

    /**
     * The Session DTO is POJO class used in UI class as binder, Which holds
     * UserId and session Id though out the UI Object(views, forms and sub
     * classes)
     */
    private SessionDTO sessionDTO = new SessionDTO();

    /**
     * The method is used to adding styles, navigator initialization and view
     * navigation Performing the initialization in a constructor is not
     * suggested as the state of the UI is not properly set up when the
     * constructor is invoked.
     *
     * @param request
     */
    public AdjustmentConfigUI() {

        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    protected void init(VaadinRequest request) {
        try {
            LOGGER.debug("Enters inside the Adjustment Config UI");
            CommonLogic.beforeUnloadCloseUi(this);
            addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
            final String userId = request.getRemoteUser();
            if (userId != null) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
                final String sessionId = request.getWrappedSession().getId();
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
                sessionDTO.setUserId(Integer.valueOf(userId));
                HelperListUtil.getInstance().loadValuesWithListName("ADJUSTMENT_CONFIGURATION");
                LOGGER.info("USER_ID :{}", userId);
                LOGGER.info("SESSION_ID :{}", sessionId);
                Navigator navigator = new Navigator(this, this);
                navigator.addView(AdjustmentConfigForm.getName(), new AdjustmentConfigForm(sessionDTO));
            }
            LOGGER.debug("Exits the Adjustment Reserve UI");
        } catch (Exception ex) {
            LOGGER.error("Error While Creating AdjustmentRateUI ", ex);
        }
    }

    @Override
    public boolean equals(Object adjConfigobj) {
        return super.equals(adjConfigobj);
    }

    @Override
    public int hashCode() {
        LOGGER.debug("Inside hash Code of Adjustment Config");
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream adjConfigOut) throws IOException {
        adjConfigOut.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjuConfigIn) throws IOException, ClassNotFoundException {
        adjuConfigIn.defaultReadObject();
    }
}
