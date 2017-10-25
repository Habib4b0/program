/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.ifp.ui.lazyload;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.dto.TempItemDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
import com.stpl.portal.kernel.exception.SystemException;
import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;
import de.steinwedel.messagebox.MessageBoxListener;
import java.util.ArrayList;
import java.util.List;
import org.vaadin.addons.lazycontainer.BeanDAO;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;
import org.vaadin.addons.lazycontainer.OrderByColumn;


/**
 * 
 * @author Harlin
 */
public class TempSelectedContainer implements BeanDAO<TempItemDTO> {
    
    SessionDTO sessionDTO;
    private final IFPLogic ifpLogic;
    
    public TempSelectedContainer(final SessionDTO sessionDTO){
    this.sessionDTO=sessionDTO;
    ifpLogic = new IFPLogic(this.sessionDTO);
    }
    
    public int count(BeanSearchCriteria sc) {
        try {
            return ifpLogic.tempTableCount(sc);
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

    public List<TempItemDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {
            return ifpLogic.tempTableResults(i,i+i1,list, sc);
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
         return new ArrayList<TempItemDTO>();
    }
}