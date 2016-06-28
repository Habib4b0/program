/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.ui.lazyload;

import com.stpl.app.global.priceschedule.dto.PSIFPDTO;
import com.stpl.app.global.priceschedule.logic.PSLogic;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.List;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author karthikeyans
 */
public class AvailableIFPContainer  implements BeanDAO<PSIFPDTO> {

    public boolean editListFlag;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AvailableIFPContainer.class);
    final String searchField;
    final String value;
    final Integer []systemIdList;
    
    /**
     * Method used for get Count.
     */
    public AvailableIFPContainer(final String searchField,final String value,final Integer [] list) {
        this.searchField = searchField;
        this.value = value;
        this.systemIdList = list;
        
    }

    public int count(final BeanSearchCriteria searchCriteria) {
        try {
            LOGGER.info("Entering AvailableCompanyAddition Count method :");
            return PSLogic.getLazyAvailableIFPCount( searchField , value,systemIdList,searchCriteria);
        } catch (PortalException ex) {
            LOGGER.error(ex);
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
    public List<PSIFPDTO> find(final BeanSearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> orderByColumns) {
        try {
            LOGGER.info("Entering AvailableCompanyAddition find method :");
            return PSLogic.getLazyAvailableIFPResults(startIndex, startIndex + offset, searchField , value,systemIdList,orderByColumns,searchCriteria);
        } catch (PortalException ex) {
            LOGGER.error(ex);
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
            LOGGER.error(ex);
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

