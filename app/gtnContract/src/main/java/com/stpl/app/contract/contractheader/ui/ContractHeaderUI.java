package com.stpl.app.contract.contractheader.ui;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.common.util.HelperListUtil;
import com.stpl.app.contract.contractheader.ui.view.ContractHeaderSearchView;
import com.stpl.app.contract.contractheader.ui.view.ContractHeaderView;
import com.stpl.app.contract.util.AbstractNotificationUtils;
import com.stpl.app.contract.util.CommonUIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.contract.util.ErrorCodeUtil;
import com.stpl.app.contract.util.ErrorCodes;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;

/**
 * UI class for the ContractHeader.
 *
 * @author
 */
public class ContractHeaderUI extends UI {

    /**
     * The navigator.
     */
    private Navigator navigator;
    SessionDTO sessionDTO=new SessionDTO();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractHeaderUI.class);

    /**
     * Gets the Navigator.
     */
    public Navigator getNavigator() {
        return navigator;
    }

    /**
     * Sets the Navigator.
     */
    public void setNavigator(final Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Method overridden while extending UI.Initializes this UI class.
     *
     * @param request - VaadinRequest
     */
    @Override
    protected void init(final VaadinRequest request) {
        try {
            LOGGER.info("Entering init method with parameter request");
            CommonUIUtils.beforeUnloadCloseUi(this,sessionDTO);
            final String userId = request.getRemoteUser();
            final String sessionId = request.getWrappedSession().getId();

            addStyleName("bootstrap");
            addStyleName("bootstrap-bb"); 
            VaadinSession.getCurrent().setAttribute(Constants.SESSION_ID, sessionId);
            VaadinSession.getCurrent().setAttribute(Constants.USER_ID, userId);
            LOGGER.info("USER_ID: "+userId);
            LOGGER.info("SESSION_ID: "+sessionId);
            navigator = new Navigator(this, this);
            VaadinSession.getCurrent().setAttribute(Constants.LOCK_STATUS, "N");
            VaadinSession.getCurrent().setAttribute(Constants.SAVE_NOTIF, "N");
            VaadinSession.getCurrent().setAttribute(Constants.ITEM_CLICK_FLAG, false);
            StplSecurity.getUserName();
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("contractheader");
            ContractHeaderSearchView contractHeaderSearchView=new ContractHeaderSearchView(sessionDTO);
            navigator.addView(ContractHeaderSearchView.NAME,  contractHeaderSearchView);
            ContractHeaderView contractHeaderView=new ContractHeaderView(sessionDTO);
            navigator.addView(ContractHeaderView.NAME,  contractHeaderView);
            LOGGER.info(" init method ends");
            
            UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
                /**
                 *
                 * Invoked when an error occurs.
                 */
                @SuppressWarnings("PMD")
                @Override
                public void error(final com.vaadin.server.ErrorEvent event) {
                    final StringBuilder cause = new StringBuilder("The Exception occured because of------>");
                    for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
                        if (t.getCause() == null){
                            cause.append(t.getClass().getName());
                           
                        }
                    }
                    LOGGER.error(event.getThrowable().getMessage());
                    LOGGER.error(cause);
                }
            });
        } catch (SystemException ex) {
           final  String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
            LOGGER.error(errorMsg);
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg);
        }  catch (Exception ex) {
            AbstractNotificationUtils.getErrorNotification(ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_2011));
            LOGGER.error(ex);
        }
    }
}
