/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.ui;

import com.stpl.app.gcm.common.HelperListUtil;
import com.stpl.app.gcm.globalchange.ui.view.GlobalChangeUIMainView;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author alok.v
 */
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet") 
@Component(service = UI.class, property = {
    "com.liferay.portlet.display-category=Forecast and Planning",
    "javax.portlet.name=GlobalChangeManagement",
    "javax.portlet.display-name=Global Change Management",
    "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
@JavaScript("js/WorkflowInboxListener.js")
public class GlobalChangeUI extends UI {

    private Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalChangeUI.class);

    @Override
    protected void init(VaadinRequest request) {
        addStyleName("bootstrap bootstrap-ui bootstrap-forecast bootstrap-nm");
        navigator = new Navigator(this, this);

        final String userId = request.getRemoteUser();
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
        VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
        CommonUtils.beforeUnloadCloseUi(this);
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("gcm");
        helperListUtil.loadValuesWithListName("workflowinbox");
        try {
            navigator.addView(GlobalChangeUIMainView.NAME, new GlobalChangeUIMainView());
            navigator.setErrorView(new GlobalChangeUIMainView());
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }

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

    }
