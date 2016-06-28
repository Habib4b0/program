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
 * @author Harlin
 */
public class ItemQualifierNameContainer implements DAO<HelperDTO> {

    private boolean editListFlag;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ItemQualifierNameContainer.class);

    /**
     * Method used for get Count.
     */
    public ItemQualifierNameContainer(final boolean editListFlag){
        this.editListFlag=editListFlag;
    }
    public int count(final SearchCriteria searchCriteria) {
        try {
            if(editListFlag){
                return ItemSearchLogic.getLazyItemQualifierNameCount(searchCriteria.getFilter(),editListFlag)+2;
            }
            else
                return ItemSearchLogic.getLazyItemQualifierNameCount(searchCriteria.getFilter(),editListFlag)+1;
        } catch (SystemException ex) {
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
                }
        return 0;
    }

    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
         try {
            return ItemSearchLogic.getLazyItemQualifierNameResults(startIndex, startIndex + offset, searchCriteria.getFilter(),editListFlag);
        } catch (SystemException ex) {
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
                }
         return null;
    }
    
}
