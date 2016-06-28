/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.ui;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import org.jboss.logging.Logger;

import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderAdd;
import com.stpl.app.adminconsole.hierarchybuilder.ui.view.HierarchyBuilderLandingView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.DateToStringConverterFactory;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderUI.
 *
 * @author nisanthan
 */
public class HierarchyBuilderUI extends UI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(HierarchyBuilderUI.class);

	/** The navigator. */
	private Navigator navigator;
        
        SessionDTO sessionDTO = new SessionDTO();

	/**
	 * This method will invoked first when HierarchyBuilder is accessed.
	 * 
	 * @param request
	 *            the request
	 */
	@Override
	protected void init(final VaadinRequest request) {
            CommonUIUtil.beforeUnloadCloseUi(this,sessionDTO);
                   setId("HB");
                   addStyleName("bootstrap");
                addStyleName("bootstrap-bb");
                addStyleName("bootstrap-admin");
		try {
			LOGGER.info("init method started ");
			final String userId = request.getRemoteUser();
			final String sessionId = request.getWrappedSession().getId();
                        sessionDTO.setUserId(userId);
                        sessionDTO.setSessionId(sessionId);
                        VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYS_ID, 0);
                        VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory()); 
			navigator = new Navigator(this, this);
                        /**
                         * Registering navigation views
                         */
                        navigator.addView(HierarchyBuilderLandingView.NAME, new HierarchyBuilderLandingView(sessionDTO));
			navigator.addView(HierarchyBuilderAdd.NAME, new HierarchyBuilderAdd(sessionDTO));
			navigator.setErrorView(new HierarchyBuilderLandingView(sessionDTO));
		} catch (SystemException ex) {
			final  String errorMsg=ErrorCodeUtil.getErrorMessage(ex);
			AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001),errorMsg);
			LOGGER.error(ex);
		} catch (PortalException ex) {
			AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
			LOGGER.error(ex);
		}catch (Exception ex) {
			AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
			LOGGER.error(ex);
		}
		LOGGER.info("init method Ended ");
	}

	/**
	 * Getter for navigator
     * @return 
	 */
	public Navigator getNavigator() {
		return navigator;
	}
	
/**
 * Setter for navigator
 */
	public void setNavigator(final Navigator navigator) {
		this.navigator = navigator;
	}
}
