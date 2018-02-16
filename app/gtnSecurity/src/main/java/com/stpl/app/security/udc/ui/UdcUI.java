package com.stpl.app.security.udc.ui;

import com.stpl.app.security.common.SessionDTO;
import com.stpl.app.security.udc.ui.view.UdcView;
import com.stpl.app.util.ConstantsUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.util.Collection;
import java.util.Iterator;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Theme("stpl")
@Widgetset("com.stpl.widgetset.vaadin.widgetset.AppWidgetSet") 
@Component(service = UI.class, property = {
        "com.liferay.portlet.display-category=Security",
        "javax.portlet.name=UDC",
        "javax.portlet.display-name=UDC",
        "com.vaadin.osgi.liferay.portlet-ui=true"}, scope = ServiceScope.PROTOTYPE)
public class UdcUI extends UI implements VaadinPortletSession.PortletListener {

    @AutoGenerated
    private Navigator navigator;
    private PortletMode customMode;
    private final SessionDTO sessionDTO = new SessionDTO();
    private static final Logger LOGGER = LoggerFactory.getLogger(UdcUI.class.getName());
    public UdcUI(){
    	super();
    }
    @Override
    protected void init(VaadinRequest request) {
        setId("UDC");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        navigator = new Navigator(this, this);
        final String userId = request.getRemoteUser();
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
        final String sessionId = request.getWrappedSession().getId();
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
        sessionDTO.setUserId(userId);
        sessionDTO.setSessionId(sessionId);
        LOGGER.info("USER_ID: {}.", userId);
        LOGGER.info("SESSION_ID: {}. ", sessionId);
        navigator.addView(UdcView.NAME, new UdcView(sessionDTO));
        setCustomMode(new PortletMode("config"));
        if (VaadinSession.getCurrent() instanceof VaadinPortletSession) {

            VaadinPortletSession portletsession
                    = (VaadinPortletSession) VaadinSession.getCurrent();
            // Add a custom listener to handle action and
            // render requests.
            portletsession.addPortletListener(this);
        }
        setComponentError(new UserError(""));
    }

    @Override
    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        VaadinSession.getCurrent().getNextUIid();
        Collection<UI> uiList = VaadinSession.getCurrent().getUIs();
        for (Iterator<UI> iterator = uiList.iterator(); iterator.hasNext();) {

            UI ui2 = (UI) iterator.next();
            if (ui2.getId().equals("UDC")) {
                ui2.close();
            }
        }
        getUI().getNavigator().navigateTo(UdcView.NAME);
    }

    @Override
    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
        return;
    }

    @Override
    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
        return;
    }

    @Override
    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
        return;
    }

	public PortletMode getCustomMode() {
		return customMode;
	}

	public void setCustomMode(PortletMode customMode) {
		this.customMode = customMode;
	}
}
