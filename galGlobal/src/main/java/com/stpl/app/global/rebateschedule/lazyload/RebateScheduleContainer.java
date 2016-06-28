/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.rebateschedule.lazyload;

import com.stpl.app.global.rebateschedule.dto.IFPDetailsDTO;
import com.stpl.app.global.rebateschedule.logic.RebateScheduleLogic;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;

/**
 *
 * @author Harlin
 */
public class RebateScheduleContainer implements BeanDAO<IFPDetailsDTO> {

    private final String ifpName;
    private final Object ifpType;
    private final Date startDate;
    private final Date endDate;
    private final String ifpNumber;
    private final String itemName;
    private final String itemNo;
    private final Object ifpCategory;
    private final Object ifpStatus;

    final RebateScheduleLogic rebateScheduleLogic = new RebateScheduleLogic();
    
    public RebateScheduleContainer(String ifpNumber, String ifpName, Object ifpType, final Date startDate, final Date endDate, String itemName, String itemNo,Object ifpCategory,Object ifpStatus) {
        this.ifpNumber = ifpNumber;
        this.ifpName = ifpName;
        this.ifpType = ifpType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.ifpCategory = ifpCategory;
        this.ifpStatus = ifpStatus;
    }

    public int count(BeanSearchCriteria sc) {
        try {            
            return rebateScheduleLogic.getIfpMastersCount(0,0,ifpNumber, ifpName, ifpType, startDate, endDate, itemName, itemNo,null,sc,"count",String.valueOf(ifpCategory),String.valueOf(ifpStatus));                         
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

    public List<IFPDetailsDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {             
            return rebateScheduleLogic.getIfpMastersResult(i, i1, ifpNumber, ifpName, ifpType, startDate, endDate, itemName, itemNo,list,sc,"list",String.valueOf(ifpCategory),String.valueOf(ifpStatus));
        }catch (SystemException ex) {
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
        return new ArrayList<IFPDetailsDTO>();
    }
}
