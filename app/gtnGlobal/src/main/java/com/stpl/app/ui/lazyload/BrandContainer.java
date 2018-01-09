/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.ui.lazyload;

import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.app.util.HelperDTO;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.Collections;
import java.util.List;
import org.asi.ui.addons.lazycontainer.DAO;
import org.asi.ui.addons.lazycontainer.OrderByColumn;
import org.asi.ui.addons.lazycontainer.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha
 */

public class BrandContainer implements DAO<HelperDTO> {
    
    private HelperDTO brand;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandContainer.class);
    private boolean isFilter=false;

    /**
     * Method used for get Count.
     */
    public BrandContainer(boolean isFilter){
        this.isFilter=isFilter;
    }
     /**
     * Method used for get Count.
     */
    public BrandContainer(final HelperDTO brand){
        this.brand=brand;
    }
    
    @Override
    public int count(final SearchCriteria searchCriteria) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO Count method :");
            return DeductionCalendarLogic.getLazyBrandCount(searchCriteria.getFilter())+1;
        } catch (PortalException ex) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {   
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
                LOGGER.error(ex.getMessage());
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
                @Override
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
    @Override
    public List<HelperDTO> find(final SearchCriteria searchCriteria, final int startIndex, final int offset, final List<OrderByColumn> list) {
        try {
            LOGGER.debug("Entering CompanyQualifierNameDAO find method :");
            return DeductionCalendarLogic.getLazyBrandResults(startIndex, startIndex + offset, searchCriteria.getFilter(),brand, isFilter);
        } catch (PortalException ex) {
                final MessageBox msg = MessageBox.showPlain(Icon.ERROR, ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1001), ErrorCodeUtil.getEC(ErrorCodes.ERROR_CODE_1004), new MessageBoxListener() {   
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
                LOGGER.error(ex.getMessage());
        } catch (SystemException ex) {
        	LOGGER.error(ex.getMessage());
            final String errorMsg = ErrorCodeUtil.getErrorMessage(ex);
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
