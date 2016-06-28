/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author Shrihariharan
 */
public class RsLookupFilterGenerator  implements ExtFilterGenerator{
private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    CommonUtils commonsUtil = new CommonUtils();
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             if("tradingPartnerName".equals(propertyId)){
                 
                 
         return new SimpleStringFilter(propertyId,String.valueOf(String.valueOf(value)),false,false);
             }
        
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      
        if (originatingField instanceof ComboBox) {
            
          if(originatingField.getValue()!=null)
          {
             
           return new SimpleStringFilter(propertyId,String.valueOf(originatingField.getValue()),false,false);
          }
          else
          {
             return null;  
          }
        } 
        
       
        
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        if ("displayRsStatus".equals(propertyId)) {
           ComboBox rsStatus = new ComboBox();
            try {
                commonsUtil.getNativeSelect(rsStatus, contractHL.getDropDownList("STATUS"));
            
            } catch (SystemException ex) {
                
            }
            rsStatus.setImmediate(true);
            rsStatus.setNullSelectionAllowed(true);
            rsStatus.setNullSelectionItemId(Constants.ZEROSTRING);
            rsStatus.setPageLength(7);
            return rsStatus;
        }
         
       if ("displayRsType".equals(propertyId)) {
           ComboBox rsType = new ComboBox();
            try {
                commonsUtil.getNativeSelect(rsType, contractHL.getDropDownList("RS_TYPE"));
            
            } catch (SystemException ex) {
               
            }
            rsType.setImmediate(true);
            rsType.setNullSelectionAllowed(true);
            rsType.setNullSelectionItemId(Constants.ZEROSTRING);
            rsType.setPageLength(7);
            return rsType;
        }
        
       if ("displayRpType".equals(propertyId)) {
           ComboBox rsProgramType = new ComboBox();
            try {
                commonsUtil.getNativeSelect(rsProgramType, contractHL.getDropDownList("REBATE_PROGRAM_TYPE"));
            
            } catch (SystemException ex) {
               
            }
            rsProgramType.setImmediate(true);
            rsProgramType.setNullSelectionAllowed(true);
            rsProgramType.setNullSelectionItemId(Constants.ZEROSTRING);
            rsProgramType.setPageLength(7);
            return rsProgramType;
        }
              
       
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
    return null;
    }
    
}