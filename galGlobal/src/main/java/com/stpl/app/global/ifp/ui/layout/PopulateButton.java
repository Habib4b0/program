package com.stpl.app.global.ifp.ui.layout;


import java.util.Map;

import org.jboss.logging.Logger;

import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.StplHorizontalLayout;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.FunctionNameUtil;
import com.stpl.app.util.UISecurityUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;

// TODO: Auto-generated Javadoc
/**
 * The Class PopulateButton.
 */
public class PopulateButton extends StplHorizontalLayout {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PopulateButton.class);
    /**
     * The binder.
     */
    private ErrorfulFieldGroup binder;


    /**
     * Instantiates a new populate button.
     *
     * @param binder the binder
     */
    public PopulateButton(final ErrorfulFieldGroup binder) throws PortalException, SystemException {
        super();
        this.binder = binder;
        init();
    }

    /**
     * Initial method when the Constructor get calls.
     */
    private void init() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
        LOGGER.info("getBusinessFunctionPermission method ");
        final Map<String, AppPermission> functionIfpHM = stplSecurity.getBusinessFunctionPermission(userId, UISecurityUtil.ITEM_FAMILY_PLAN);
        this.setSpacing(true);
        if (functionIfpHM.get(FunctionNameUtil.IDENTIFIER_ATTACH) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.IDENTIFIER_ATTACH)).isFunctionFlag()) {
            populateButton();
        }
        if (functionIfpHM.get(FunctionNameUtil.RESET) != null && ((AppPermission) functionIfpHM.get(FunctionNameUtil.RESET)).isFunctionFlag()) {
            resetButton();
        }
    }

    
   

	/**
	 * Populate button.
	 *
	 * @throws Exception the exception
	 */
    private void populateButton() {
      
        // Commit button
        final Button btnSearch = new Button(ConstantsUtils.POPULATE);
        btnSearch.setWidth(ConstantsUtils.BTN_WIDTH);
        btnSearch.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                LOGGER.info("Entering Populate operation");
                try {
                    binder.commit();
                    btnSearchLogic();
                    final Notification notif = new Notification(ConstantsUtils.SEARCH_COMPLETED,
                            Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                    notif.show(Page.getCurrent());
                } catch (FieldGroup.CommitException ex) {
                    LOGGER.error(ex);
                } catch (Exception exception) {
                    final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1002), new MessageBoxListener() {  
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
                LOGGER.info("Ending Populate operation");
            }
        });
        this.addComponent(btnSearch);
       
    }

    /**
     * Reset button.
     *
     * @throws Exception the exception
     */
    private void resetButton() {

     

        final Button btnReset = new Button(ConstantsUtils.RESET);

        btnReset.setWidth(ConstantsUtils.BTN_WIDTH);
        btnReset.addClickListener(new ClickListener() {
            /**
             * Called when a Button has been clicked.
             */ @SuppressWarnings("PMD")
            public void buttonClick(final ClickEvent event) {
                try{
                LOGGER.info("Entering Reset operation");
                btnResetLogic();
                LOGGER.info("Ending Reset operation");
               } catch (Exception exception) {
            final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1006), new MessageBoxListener() {  
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
        });
        this.addComponent(btnReset);
        
    }

    /**
     * Btn search logic.
     */
    protected void btnSearchLogic() {
        //EMPTY METHOD
    }

    /**
     * Btn reset logic.
     */
    protected void btnResetLogic() {
        //EMPTY METHOD
    }


	/**
	 * Gets the binder.
	 *
	 * @return the binder
	 */
	public ErrorfulFieldGroup getBinder() {
		return binder;
	}

	/**
	 * Sets the binder.
	 *
	 * @param binder the new binder
	 */
	public void setBinder(final ErrorfulFieldGroup binder) {
		this.binder = binder;
	}


}
