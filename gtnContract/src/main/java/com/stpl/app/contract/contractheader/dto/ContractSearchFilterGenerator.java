/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.contractheader.dto;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author shrihariharan
 */
public class ContractSearchFilterGenerator implements ExtFilterGenerator{
private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    CommonUtils commonsUtil = new CommonUtils();
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractSearchFilterGenerator.class);

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
            
          if(originatingField.getValue()!=null){
             
           return new SimpleStringFilter(propertyId,String.valueOf(originatingField.getValue()),false,false);
          }
          else{
             return null;  
          }
        } 
        
       
        
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        if ("contractStatus".equals(propertyId)) {
           ComboBox contractStatus = new ComboBox();
            try {
                commonsUtil.getNativeSelect(contractStatus, contractHL.getDropDownList(UIUtils.STATUS));
            } catch (Exception ex) {
              LOGGER.error(ex);
            }
            contractStatus.setImmediate(true);
            contractStatus.setNullSelectionAllowed(true);
            contractStatus.setNullSelectionItemId(Constants.ZEROSTRING);
            contractStatus.setPageLength(NumericConstants.SEVEN);
            return contractStatus;
        }
        if ("contractType".equals(propertyId)) {
           ComboBox contractType = new ComboBox();
           
            try {
                commonsUtil.getNativeSelect(contractType, contractHL.getDropDownList(UIUtils.CONTRACT_TYPE));
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            contractType.setImmediate(true);
            contractType.setNullSelectionAllowed(true);
            contractType.setNullSelectionItemId(Constants.ZEROSTRING);
            contractType.setPageLength(NumericConstants.SEVEN);
            return contractType;
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
