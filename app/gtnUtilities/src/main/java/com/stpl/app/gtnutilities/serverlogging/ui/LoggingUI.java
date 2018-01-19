/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.serverlogging.ui;


import com.stpl.app.gtnutilities.serverlogging.form.LoggingSearchView;
import com.stpl.app.gtnutilities.serverlogging.form.ViewLog;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
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
/**
 *
 * @author Karthik.Raja
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class LoggingUI extends UI implements VaadinPortletSession.PortletListener{

    /**
     * The navigator.
     */
	private Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(LoggingUI.class);

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
            LOGGER.info("init method started");
            
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            addStyleName("bootstrap-admin");
            addStyleName("bootstrap-gtnutilities");
            navigator = new Navigator(this, this);
            navigator.addView(LoggingSearchView.NAME, new LoggingSearchView());
            navigator.addView(ViewLog.NAME, new ViewLog());
        }
        catch (Exception ex) {
            LOGGER.debug(ex);
            
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
//                    Notification.show("Download failed because of "+ event.getThrowable().getCause(), Notification.Type.ERROR_MESSAGE);
                LOGGER.debug(cause);
               
            }
        });
        
    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
//       getUI().getNavigator().navigateTo(LoggingView.NAME);
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
