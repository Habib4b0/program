/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

/**
 *
 * @author shrihariharan
 */
public class CfpDetailsFilterGenerator implements ExtFilterGenerator{
    CommonUtils commonsUtil = new CommonUtils();
                
    private CommonUtil commonUtil = CommonUtil.getInstance();
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             if("tradingPartnerName".equals(propertyId)){
                 
         return new SimpleStringFilter(propertyId,String.valueOf(((HelperDTO) value).getId()),false,false);
             }
        
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      
        if (originatingField instanceof ComboBox) {
            
          if(originatingField.getValue()!=null){
           return new SimpleStringFilter(propertyId,String.valueOf(((HelperDTO) originatingField.getValue()).getId()),false,false);
          }
          else{
             return null;  
          }
        } 
        
       
        
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        
        if ("companyStatus".equals(propertyId)) {
           ComboBox companyStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(companyStatus, UIUtils.STATUS, true);
            
            } catch (SystemException ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (Exception ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return companyStatus;
        }
         
       if ("companyType".equals(propertyId)) {
           ComboBox companyType = new ComboBox();
            try {
                commonUtil.loadComboBox(companyType, UIUtils.COMP_TYPE, true);
            
            } catch (SystemException ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (Exception ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return companyType;
        }
        
       if ("companyFamilyPlanStatus".equals(propertyId)) {
           ComboBox companyFamilyPlanStatus = new ComboBox();
            try {
                commonUtil.loadComboBox(companyFamilyPlanStatus, UIUtils.STATUS, true);
            
            } catch (SystemException ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (Exception ex) {
                Logger.getLogger(CfpDetailsFilterGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            return companyFamilyPlanStatus;
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