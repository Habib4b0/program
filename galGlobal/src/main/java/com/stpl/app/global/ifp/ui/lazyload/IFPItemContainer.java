/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.ifp.ui.lazyload;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.ifp.logic.IFPLogic;
import com.stpl.app.global.item.dto.ItemMasterDTO;
import com.stpl.app.util.ErrorCodeUtil;
import com.stpl.app.util.ErrorCodes;
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
public class IFPItemContainer implements BeanDAO<ItemMasterDTO> {
    
    private String searchField;
    private String value;
    private SessionDTO sessionDTO;

    public IFPItemContainer(String searchField, String value, SessionDTO sessionDTO){
        this.searchField=searchField;
        this.value=value;
        this.sessionDTO=sessionDTO;
    }
    public int count(BeanSearchCriteria sc) {
            return IFPLogic.getItemForIFPCount(searchField, value, sc, sessionDTO);
        
    }

    public List<ItemMasterDTO> find(BeanSearchCriteria sc, int i, int i1, List<OrderByColumn> list) {
        try {
            return IFPLogic.getItemForIFPResults(i,i1, searchField, value, list, sc, sessionDTO);
        }catch (Exception ex) {
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
        }
         return new ArrayList<ItemMasterDTO>();
    }
    
}
