/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.ui.util;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class DeductionCustomerFilerGenerator implements ExtFilterGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DeductionCustomerFilerGenerator.class);
    
    private CommonUtil commonMsg = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null)
          {
              
            if("customerId".equals(propertyId)){
                
              ComboBox box = (ComboBox)originatingField;
              HelperDTO dto = (HelperDTO)box.getValue();
              return new SimpleStringFilter(propertyId,String.valueOf(dto.getId()),false,false);
                
            } else{ 
              
            return new Compare.Equal(propertyId, originatingField.getValue());
            }
          }else{
             return null;  
          }
        } 
    return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            ComboBox comboBox;
            if ("customerStatus".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "STATUS", true);
                return comboBox;

            }else if ("customerType".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "COMPANY_TYPE", true);
                return comboBox;

            }else if ("tradeClass".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "COMPANY_TRADE_CLASS", true);
                return comboBox;

            }else if(ConstantsUtils.UDC1.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC1, true);
                return comboBox;
            }else if(ConstantsUtils.UDC2.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC2, true);
                return comboBox;
            }else if(ConstantsUtils.UDC3.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC3, true);
                return comboBox;
            }else if(ConstantsUtils.UDC4.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC4, true);
                return comboBox;
            }else if(ConstantsUtils.UDC5.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC5, true);
                return comboBox;
            }else if(ConstantsUtils.UDC6.equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.UDC6, true);
                return comboBox;
            }else if("organisationKey".equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.ORGANIZATION_KEY, true);
                return comboBox;
            }else if("customerGroup".equals(propertyId)){
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, CommonUIUtils.COMPANY_GROUP, true);
                return comboBox;
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        } 
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        return;
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        return;
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {        
   return null;
    }

    
}