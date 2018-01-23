/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnutilities.charts.ui;


import com.stpl.app.gtnutilities.charts.form.ChartsView;
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
public class ChartsUI extends UI implements VaadinPortletSession.PortletListener{

    /**
     * The navigator.
     */
	private Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ChartsUI.class);

    public ChartsUI(){
    	super();
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
            navigator.addView(ChartsView.NAME, new ChartsView());
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
                LOGGER.debug(cause);
               
            }
        });
        
    }

    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        return;
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
