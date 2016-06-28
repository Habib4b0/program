package com.stpl.app.global.rebateschedule.ui;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.rebateschedule.ui.view.RebateScheduleAddView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.DateToStringConverterFactory;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.jboss.logging.Logger;

/**
 * Class for implementing UI for RebateSchedule .
 */
@SuppressWarnings("serial")
public class RebateScheduleUI extends UI {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(RebateScheduleUI.class);
    SessionDTO sessionDTO = new SessionDTO();

    /**
     * To initialize the UI.
     *
     * @param request
     */
    Navigator navigator;

    /**
     * Method that allows switching of views in the application baseed on the
     * vaadin request.
     */
    @Override
    protected void init(final VaadinRequest request) {
        try {
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");

            CommonUIUtils.beforeUnloadCloseUi(this,sessionDTO,ConstantUtil.REBATE_SCHEDULE_MASTER);

            final String userId = request.getRemoteUser();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
            VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());

            StplSecurity.getUserName();
            HelperListUtil helperListUtil = HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("rebateschedule");
            navigator = new Navigator(this, this);
            navigator.addView(AbstractSearchView.NAME, new AbstractSearchView(ConstantUtil.REBATE_SCHEDULE_MASTER,sessionDTO));
            RebateScheduleAddView rebateScheduleAddView=new RebateScheduleAddView(sessionDTO);
            navigator.addView(RebateScheduleAddView.NAME, rebateScheduleAddView);
            
            navigator.setErrorView(new AbstractSearchView(ConstantUtil.REBATE_SCHEDULE_MASTER));
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
                        }
                        LOGGER.error(t);
                    } 
                    LOGGER.error(cause);
                }
            });
        } catch (SystemException sysException) {
            final String errorMsg = ErrorCodeUtil.getErrorMessage(sysException);
            LOGGER.error(errorMsg);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), errorMsg, new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing              
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (PortalException portException) {
            LOGGER.error(portException);
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing          
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
        } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1010), new MessageBoxListener() {
                /**
                 * The method is triggered when a button of the message box is
                 * pressed .
                 *
                 * @param buttonId The buttonId of the pressed button.
                 */
                @SuppressWarnings("PMD")
                public void buttonClicked(final ButtonId buttonId) {
                    // Do Nothing   
                }
            }, ButtonId.OK);
            msg.getButton(ButtonId.OK).focus();
            LOGGER.error(exception);

        }
    }
}
