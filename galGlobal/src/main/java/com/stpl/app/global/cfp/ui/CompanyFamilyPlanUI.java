package com.stpl.app.global.cfp.ui;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.cfp.ui.view.CFPAddView;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.DateToStringConverterFactory;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.jboss.logging.Logger;
import com.vaadin.annotations.AutoGenerated;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import javax.portlet.PortletMode;

/**
 * Company Family Plan Screen will be attached to UI
 *
 * @author manikanta
 *
 */
@SuppressWarnings("serial")
public class CompanyFamilyPlanUI extends UI {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyFamilyPlanUI.class);
    @AutoGenerated
    SessionDTO sessionDTO=new SessionDTO();
    Navigator navigator;
    PortletMode customMode;

    /**
     * To set attributes(sessionId & userId) in the session and navigates the
     * view
     *
     * @param request
     */
    @Override
    protected void init(final VaadinRequest request) {
        LOGGER.info("Entering CompanyFamilyPlanUI init");
        try {
            CommonUIUtils.beforeUnloadCloseUi(this,sessionDTO,ConstantUtil.COMPANY_FAMILY_PLAN);
            
            addStyleName("bootstrap");
            addStyleName("bootstrap-bb");
            final String userId = request.getRemoteUser();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
            sessionDTO.setIsSave("N");
            final String sessionId = request.getWrappedSession().getId();
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
            VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
            StplSecurity.getUserName();
            navigator = new Navigator(this, this);
            HelperListUtil helperListUtil=HelperListUtil.getInstance();
            helperListUtil.loadValuesWithListName("companyFamilyPlan");
            AbstractSearchView abstractSearchView=new AbstractSearchView(ConstantUtil.COMPANY_FAMILY_PLAN,sessionDTO);
            navigator.addView(AbstractSearchView.NAME, abstractSearchView);
            CFPAddView cfpAddView=new CFPAddView(sessionDTO);
            navigator.addView(CFPAddView.NAME, cfpAddView);
                        customMode = new PortletMode("config");

            navigator.setErrorView(new AbstractSearchView(ConstantUtil.COMPANY_FAMILY_PLAN));
        } catch (SystemException ex) {
            LOGGER.error(ex);
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        } catch (PortalException pe) {
            LOGGER.error(pe);
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
        } catch (Exception e) {
            LOGGER.error(e);
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
        }
    }
}
