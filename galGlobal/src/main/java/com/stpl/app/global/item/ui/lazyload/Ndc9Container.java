/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.item.ui.lazyload;

import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.DAO;
import org.vaadin.addons.lazycontainer.OrderByColumn;
import org.vaadin.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author Asha
 */
public class Ndc9Container implements DAO<HelperDTO> {
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Ndc9Container.class);

    /**
     * Method used for get Count.
     */
    public Ndc9Container(){
        //Empty Constructor.
    }
   
    
    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.info("Entering Ndc8 Count method :");
            return ItemSearchLogic.getLazyNdc9Count(searchCriteria.getFilter());
        } catch (PortalException ex) {
            LOGGER.error(ex);;
        	final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {     
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
        } catch (Exception ex) {
            LOGGER.error(ex);;
        	final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        return 0;
    }

    /**
     * Method used for get the results.
     */
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.info("Entering Ndc8 find method :");
            return ItemSearchLogic.getLazyNdc9Results(startIndex, startIndex + offset, searchCriteria.getFilter());
        
        } catch (PortalException ex) {
            LOGGER.error(ex);;
        	final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {     
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
        } catch (SystemException ex) {
            LOGGER.error(ex);;
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
        return null;
    }
    
}
