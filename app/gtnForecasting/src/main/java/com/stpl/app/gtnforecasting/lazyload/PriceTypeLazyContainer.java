/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.lazyload;

import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.stpl.app.serviceUtils.ErrorCodeUtil;
import com.stpl.app.serviceUtils.ErrorCodes;
import com.stpl.ifs.util.HelperDTO;
import com.liferay.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collections;
import java.util.List;
import org.jboss.logging.Logger;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;

/**
 *
 * @author pvinoth
 */
public class PriceTypeLazyContainer implements DAO<HelperDTO> {

    private final HelperDTO priceType;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PriceTypeLazyContainer.class);

    /**
     * Method used for get Count.
     */
    public PriceTypeLazyContainer(final HelperDTO priceType) {
        this.priceType = priceType;
    }


    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            return PPAProjectionLogic.getPriceTypeCount(searchCriteria.getFilter(), priceType);
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
                @Override
                        public void buttonClicked(final ButtonId buttonId) { 
                            // Do Nothing     
                        }           
                    }, ButtonId.OK);  
                    msg.getButton(ButtonId.OK).focus();
        } 
        return 0;
    }

    @Override
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            return PPAProjectionLogic.getPriceTypeResults(startIndex, startIndex + offset, searchCriteria.getFilter(), priceType);
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
                @Override
                        public void buttonClicked(final ButtonId buttonId) { 
                            // Do Nothing     
                        }           
                    }, ButtonId.OK);  
                    msg.getButton(ButtonId.OK).focus();
        } 
        return Collections.emptyList();
    }

    
}
