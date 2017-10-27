/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.compliancededuction.ui;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.compliancededuction.ui.view.CDRView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinPortletSession;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletMode;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class ComplianceDeductionUI extends UI implements VaadinPortletSession.PortletListener {

    /**
     * The Navigator.
     */
    public Navigator navigator;
    @AutoGenerated
    PortletMode customMode;
    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ComplianceDeductionUI.class);
    SessionDTO sessionDTO = new SessionDTO();

    /**
     * For Initialization.
     *
     * @param request the request
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(VaadinRequest request) {
        LOGGER.debug("Enters init() P1: VaadinRequest ");
        try {
            addStyleName(ConstantsUtils.BOOTSTRAP);
            addStyleName(ConstantsUtils.BOOTSTRAP_BB);
            final String userId = request.getRemoteUser();
            final Date tempDate = new Date();
            final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat fmtID = new SimpleDateFormat("MMddyyyyhhmmssms");
            sessionDTO.setSessionDate(fmt.format(tempDate));
            sessionDTO.setUiSessionId(fmtID.format(tempDate));
            sessionDTO.setUserId(userId);
            StplSecurity.getUserName();
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("compliancededuction");
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionDTO.getUiSessionId());
            LOGGER.debug("USER_ID: "+userId);
            LOGGER.debug("SESSION_ID: "+sessionDTO.getUiSessionId());
            navigator = new Navigator(this, this);
            navigator.addView(AbstractSearchView.NAME, new AbstractSearchView(ConstantUtil.COMPLIANCE_DEDUCTION_RULES, sessionDTO));
            navigator.addView(CDRView.NAME, new CDRView(sessionDTO));
            customMode = new PortletMode("config");
            // Configure the error handler for the UI
            UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
                @Override
                /**
                 * Error event handling.
                 */
                @SuppressWarnings("PMD")
                public void error(final com.vaadin.server.ErrorEvent event) {
                    // Find the final cause

                    final StringBuilder cause = new StringBuilder("The Exception occured because of------>");

                    for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                        if (t.getCause() == null) // We're at final cause
                        {
                            cause.append(t.getClass().getName());
                           LOGGER.error(t);
                        }
                    }
                    LOGGER.error(cause);
                }
            });
        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    @Override
    public void handleRenderRequest(RenderRequest request, RenderResponse response, UI uI) {
        throw new UnsupportedOperationException(ConstantUtil.NOT_SUPPORTED_YET); 
    }

    @Override
    public void handleActionRequest(ActionRequest request, ActionResponse response, UI uI) {
        throw new UnsupportedOperationException(ConstantUtil.NOT_SUPPORTED_YET); 
    }

    @Override
    public void handleEventRequest(EventRequest request, EventResponse response, UI uI) {
        throw new UnsupportedOperationException(ConstantUtil.NOT_SUPPORTED_YET);
    }

    @Override
    public void handleResourceRequest(ResourceRequest request, ResourceResponse response, UI uI) {
        throw new UnsupportedOperationException(ConstantUtil.NOT_SUPPORTED_YET); 
    }
}
