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
public class SearchComponentFilterGenerator implements ExtFilterGenerator{
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SearchComponentFilterGenerator.class);
private final ContractHeaderLogic contractHL = new ContractHeaderLogic();
    CommonUtils commonsUtil = new CommonUtils();
    String componentType;
    
    public SearchComponentFilterGenerator(String componentType){
       this.componentType=componentType; 
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
          
        
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      
        if (originatingField instanceof ComboBox) {
            
            if(originatingField.getValue()!=null)
            {
                return new SimpleStringFilter(propertyId,String.valueOf(originatingField.getValue()),false,false);
            }else{
                return null;  
            }
        } 
        
       
        
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if ("memberType".equals(propertyId)) {
           ComboBox memberType = new ComboBox();
                try {
                    if( ContractUtils.CONTRACT_DETAILS_COMPONENT.equals(componentType)){
                        memberType=commonsUtil.getNativeSelect(memberType, contractHL.getDropDownList("CONTRACT_TYPE"));
                    }else if(ContractUtils.CFP_COMPONENT.equals(componentType)){
                        memberType=commonsUtil.getNativeSelect(memberType, contractHL.getDropDownList("CFP_TYPE"));
                    }else if(ContractUtils.IFP_COMPONENT.equals(componentType)){
                        memberType=commonsUtil.getNativeSelect(memberType, contractHL.getDropDownList("IFP_TYPE"));
                    }else if(ContractUtils.PS_COMPONENT.equals(componentType)){
                        memberType=commonsUtil.getNativeSelect(memberType, contractHL.getDropDownList("PS_TYPE"));
                    }else if(ContractUtils.RS_COMPONENT.equals(componentType)){
                        memberType=commonsUtil.getNativeSelect(memberType, contractHL.getDropDownList("RS_TYPE"));
                    }
                } catch (SystemException ex) {
                    LOGGER.error(ex);
                }
            memberType.setImmediate(true);
            memberType.setNullSelectionAllowed(true);
            memberType.setNullSelectionItemId(Constants.ZEROSTRING);
            memberType.setPageLength(7);
            return memberType;
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