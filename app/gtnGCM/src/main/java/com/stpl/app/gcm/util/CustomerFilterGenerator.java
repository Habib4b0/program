/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.util.ConstantsUtils;
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
public class CustomerFilterGenerator implements ExtFilterGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFilterGenerator.class);
    
    private final CommonUtil commonMsg = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null) {
              
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
            if ("companyStatus".equals(propertyId) ||"status".equals(propertyId) || "statusDescription".equals(propertyId) || "priceProtectionStatus".equals(propertyId)) {

                comboBox = new ComboBox();
                commonMsg.loadComboBox(comboBox, "STATUS", true);
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }else if ("tradeClass".equals(propertyId)) {

                comboBox = new ComboBox();
                commonMsg.loadComboBox(comboBox, "COMPANY_TRADE_CLASS", true);
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            } else if ("priceType".equals(propertyId) || "priceProtectionPriceType".equals(propertyId)) {
                    final ComboBox priceType = new ComboBox();
                    commonMsg.loadComboBox(priceType, "PS_TYPE", true);
                    priceType.select(0);

                    return priceType;
            } else if ("priceToleranceType".equals(propertyId)) {
                    final ComboBox tolerance = new ComboBox();
                    commonMsg.loadComboBox(tolerance, "PRICE_TOLERANCE_TYPE", true);
                    tolerance.select(0);

                    return tolerance;
            } else if ("priceToleranceInterval".equals(propertyId)) {
                    final ComboBox toleranceInterval = new ComboBox();
                    commonMsg.loadComboBox(toleranceInterval, "PRICE_TOLERANCE_INTERVAL", true);
                    toleranceInterval.select(0);

                    return toleranceInterval;
            } else if ("priceToleranceFrequency".equals(propertyId)) {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "PRICE_TOLERANCE_FREQUENCY", true);
                    frequency.select(0);
                    return frequency;
            } else if ("contractType".equals(propertyId)) {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "CONTRACT_TYPE", true);
                    frequency.select(0);
                    return frequency;
               
            } else if ("resetType".equals(propertyId) || "resetInterval".equals(propertyId) || "resetFrequency".equals(propertyId)) {
                    ComboBox resetType = new ComboBox();
                    commonMsg.loadComboBox(resetType, "resetType".equals(propertyId) ? "RESET_TYPE" : 
                                        "resetInterval".equals(propertyId) ? "PRICE_TOLERANCE_INTERVAL" : "PRICE_TOLERANCE_FREQUENCY", true);
                    return resetType;
            } else if ("resetEligible".equals(propertyId)) {
                    ComboBox resetEligible = new ComboBox();
                    commonMsg.loadYesNoDDLB(resetEligible, true);
                    resetEligible.setDebugId("testing");
                    return resetEligible;
               
            } else if ("formulaType".equals(propertyId)) {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "FORMULA_TYPE", true);
                    frequency.select(0);
                    return frequency;
            } 
        } catch (Exception ex) {
            LOGGER.error("",ex);
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
