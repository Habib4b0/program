package com.stpl.app.global.item.ui;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.abstractsearch.view.AbstractSearchView;
import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.item.ui.view.ItemAddView;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.ui.DateToStringConverterFactory;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import org.jboss.logging.Logger;

/**
 * The Class ItemUI.
 */
@SuppressWarnings("serial")
public class ItemUI extends UI {
	/**
	 * The Navigator.
	 */
	@AutoGenerated
	public Navigator navigator;
        SessionDTO sessionDTO=new SessionDTO();
	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(ItemUI.class);
	/**
	 * For Initialization.
	 *
	 * @param request
	 *            the request
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(final VaadinRequest request) {
		LOGGER.info("Enters init() P1: VaadinRequest ");
		try {
                        CommonUIUtils.beforeUnloadCloseUi(this,sessionDTO,ConstantUtil.ITEM_MASTER);
                        addStyleName(ConstantsUtils.BOOTSTRAP);
                        addStyleName(ConstantsUtils.BOOTSTRAP_BB);
			final String userId = request.getRemoteUser();
			final String sessionId = request.getWrappedSession().getId();
			VaadinSession.getCurrent().setAttribute(ConstantsUtils.SESSION_ID, sessionId);
			VaadinSession.getCurrent().setAttribute(ConstantsUtils.USER_ID, userId);
			VaadinSession.getCurrent().setAttribute(ConstantsUtils.LOCK_STATUS, ConstantsUtils.NO_INDICATOR);
			VaadinSession.getCurrent().setAttribute(ConstantsUtils.SAVE_NOTI, ConstantsUtils.NO_INDICATOR);
			VaadinSession.getCurrent().setConverterFactory(new DateToStringConverterFactory());
                        StplSecurity.getUserName();
                        HelperListUtil helperListUtil=HelperListUtil.getInstance();
                        helperListUtil.loadValuesWithListName("itemmaster");
                        LOGGER.info("USER_ID: "+userId);
                        LOGGER.info("SESSION_ID: "+sessionId);
			navigator = new Navigator(this, this);
			navigator.addView(AbstractSearchView.NAME,new AbstractSearchView(ConstantUtil.ITEM_MASTER,sessionDTO));
                        ItemAddView itemAddView=new ItemAddView(sessionDTO);
			navigator.addView(ItemAddView.NAME, itemAddView);
			navigator.setErrorView(new ItemAddView(sessionDTO));
		}catch (SystemException se) {
                   
                    LOGGER.error(se);
                    final String errorMsg = ErrorCodeUtil.getErrorMessage(se);
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
                }
            /**
             * The method is triggered when a button of the message box is
             * pressed .
             *
             * @param buttonId The buttonId of the pressed button.
             */
            // Do Nothing
             catch (Exception e) {
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