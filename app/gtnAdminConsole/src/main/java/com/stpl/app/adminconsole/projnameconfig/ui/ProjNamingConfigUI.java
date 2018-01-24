/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.projnameconfig.ui;

import com.stpl.app.adminconsole.projnameconfig.ui.view.ProjNameConfigAddView;
import com.stpl.app.adminconsole.projnameconfig.ui.view.ProjNameConfigMainView;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.ErrorCodeUtil;
import com.stpl.app.adminconsole.util.ErrorCodes;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ProjNamingConfigUI.
 *
 * @author santanukumar
 */
public class ProjNamingConfigUI extends UI {

	public ProjNamingConfigUI(){
		super();
	}
    /**
     * The navigator.
     */
	private Navigator navigator;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjNamingConfigUI.class);

    /**
     * (non-Javadoc).
     *
     * @return the navigator
     * @see com.vaadin.ui.UI#getNavigator()
     */
    public Navigator getNavigator() {
        return navigator;
    }

    /**
     * (non-Javadoc).
     *
     * @param navigator the new navigator
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
            final String userId = request.getRemoteUser();

            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            navigator = new Navigator(this, this);
            navigator.addView(ProjNameConfigMainView.NAME, new ProjNameConfigMainView());
            navigator.addView(ProjNameConfigAddView.NAME, new ProjNameConfigAddView());
            LOGGER.info("init method ended");
        } catch (SystemException ex) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
           LOGGER.error(ex.getMessage());
        } catch (PortalException ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
           LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_4011));
           LOGGER.error(ex.getMessage());
        }

    }

}
