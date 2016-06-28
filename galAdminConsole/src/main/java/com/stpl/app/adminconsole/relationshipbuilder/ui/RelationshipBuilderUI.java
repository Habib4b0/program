/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.relationshipbuilder.ui.view.RelationshipBuilderLandingView;
import com.stpl.app.adminconsole.relationshipbuilder.ui.view.RelationshipBuilderView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Class RelationshipBuilderUI.
 *
 * @author nisanthan
 */
public class RelationshipBuilderUI extends UI {

    private static final Logger LOGGER = Logger.getLogger(RelationshipBuilderUI.class);

    SessionDTO sessionDTO = new SessionDTO();

    /**
     * (non-Javadoc).
     *
     * @param request the request
     * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
     */
    @Override
    protected void init(final VaadinRequest request) {

        CommonUIUtil.beforeUnloadCloseUi(this, sessionDTO);
        setId("RB");
        addStyleName("bootstrap");
        addStyleName("bootstrap-bb");
        addStyleName("bootstrap-admin");
        addStyleName("bootstrap-adminConsole");
        addStyleName("admin-reponsive");
        Navigator navigator;

        LOGGER.info("init method started");

        try {
            final String userId = request.getRemoteUser();
            final String sessionId = request.getWrappedSession().getId();
            sessionDTO.setSessionId(sessionId);
            sessionDTO.setUserId(userId);
            sessionDTO.setSystemId(0);
            sessionDTO.setSelectedHierarchySessionId(0);
            sessionDTO.setFromViewPage(ConstantsUtils.EMPTY);
            sessionDTO.setVersionNo(1);
            navigator = new Navigator(this, this);

            navigator.addView(RelationshipBuilderLandingView.NAME, new RelationshipBuilderLandingView(sessionDTO));
            navigator.addView(RelationshipBuilderView.NAME, new RelationshipBuilderView(sessionDTO));
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
        LOGGER.info("init method Ended");
    }
}
