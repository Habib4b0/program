/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.contractheader.logic.ContractHeaderLogic;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.dashboard.util.ContractUtils;
import com.stpl.app.contract.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
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
public class DetailComponentFilterGenerator implements ExtFilterGenerator{
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchComponentFilterGenerator.class);
    
    private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    CommonUtils commonsUtil = new CommonUtils();
    String componentType;
    
    public DetailComponentFilterGenerator(String componentType){
       this.componentType=componentType; 
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
          
        
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      
        if (originatingField instanceof ComboBox) {
            
            if(originatingField.getValue()!=null){
                return new SimpleStringFilter(propertyId,String.valueOf(originatingField.getValue()),false,false);
            }else{
                return null;  
            }
        } 
        
       
        
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if ("type".equals(propertyId) && ContractUtils.CFP_COMPONENT.equals(componentType)) {
           ComboBox type = new ComboBox();
                try {
                    type=commonsUtil.getNativeSelect(type, contractHL.getDropDownList("COMP_TYPE"));
                    
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            type.setImmediate(true);
            type.setNullSelectionAllowed(true);
            type.setNullSelectionItemId(Constants.ZEROSTRING);
            type.setPageLength(NumericConstants.SEVEN);
            return type;
        }
         
        if ("status".equals(propertyId)) {
           ComboBox status = new ComboBox();
                try {
                    if(ContractUtils.CFP_COMPONENT.equals(componentType)){
                        status=commonsUtil.getNativeSelect(status, contractHL.getDropDownList("STATUS"));
                    }else if(ContractUtils.IFP_COMPONENT.equals(componentType)){
                        status=commonsUtil.getNativeSelect(status, contractHL.getDropDownList("STATUS"));
                    }else if(ContractUtils.PS_COMPONENT.equals(componentType)){
                        status=commonsUtil.getNativeSelect(status, contractHL.getDropDownList("STATUS"));
                    }else if(ContractUtils.RS_COMPONENT.equals(componentType)){
                        status=commonsUtil.getNativeSelect(status, contractHL.getDropDownList("STATUS"));
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            status.setImmediate(true);
            status.setNullSelectionAllowed(true);
            status.setNullSelectionItemId(Constants.ZEROSTRING);
            status.setPageLength(NumericConstants.SEVEN);
            return status;
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