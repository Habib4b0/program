/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.forecast.ui;

import com.stpl.app.adminconsole.forecast.ui.view.ForecastSearchView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ForecastUI.
 *
 * @author mohamed
 */
public class ForecastUI extends UI implements VaadinPortletSession.PortletListener{

    /**
     * The navigator.
     */
    public Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ForecastUI.class);

    /**
     * (non-Javadoc)
     *
     * @see com.vaadin.ui.UI#getNavigator()
     */
    public Navigator getNavigator() {
        return navigator;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.vaadin.ui.UI#setNavigator(com.vaadin.navigator.Navigator)
     */
    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * (non-Javadoc).
     *
     * @param request the request
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(final VaadinRequest request) {
        try {
            LOGGER.debug("init method started");
            
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            addStyleName("bootstrap-admin");
            addStyleName("bootstrap-adminConsole");
            final String userId = request.getRemoteUser();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("forecastconfiguration");
            navigator = new Navigator(this, this);
            navigator.addView(ForecastSearchView.NAME, new ForecastSearchView());
              if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {
                VaadinPortletSession portletsession = (VaadinPortletSession) VaadinSession.getCurrent();
                portletsession.addPortletListener(this);
            }
            LOGGER.debug("init method ended");
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
            LOGGER.error(ex);
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(ex);
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
            LOGGER.error(ex);
        }

         UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
              
                String cause = "The Exception occured because of: ";
                for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                    if (t.getCause() == null) 
                    {
                        cause += t.getClass().getName();
                    }
                }
                LOGGER.error(cause);
               
            }
        });
        
    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
       getUI().getNavigator().navigateTo(ForecastSearchView.NAME);
    }

    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
        return;
    }

    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
        return;
    }

    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
        return;
    }
}
