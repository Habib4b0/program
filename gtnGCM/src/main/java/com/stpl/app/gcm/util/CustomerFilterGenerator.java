/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class CustomerFilterGenerator implements ExtFilterGenerator {
    
    private static Logger LOGGER = Logger.getLogger(CustomerFilterGenerator.class);
    
    CommonUtil commonMsg = CommonUtil.getInstance();

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
            if ("companyStatus".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "STATUS", true);
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }else if ("status".equals(propertyId) || "statusDescription".equals(propertyId) || "priceProtectionStatus".equals(propertyId)) {
                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "STATUS", true);
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            }else if ("tradeClass".equals(propertyId)) {

                comboBox = new ComboBox();
                comboBox.setImmediate(true);
                commonMsg.loadComboBox(comboBox, "COMPANY_TRADE_CLASS", true);
                comboBox.select(0);
                comboBox.setNullSelectionAllowed(true);
                comboBox.setNullSelectionItemId(ConstantsUtils.SHOW_ALL);
                comboBox.select(ConstantsUtils.ZERO_INT);
                return comboBox;

            } else if ("priceType".equals(propertyId) || "priceProtectionPriceType".equals(propertyId)) {
                try {
                    final ComboBox priceType = new ComboBox();
                    commonMsg.loadComboBox(priceType, "PS_TYPE", true);
                    priceType.select(0);

                    return priceType;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("priceToleranceType".equals(propertyId)) {
                try {
                    final ComboBox tolerance = new ComboBox();
                    commonMsg.loadComboBox(tolerance, "PRICE_TOLERANCE_TYPE", true);
                    tolerance.select(0);

                    return tolerance;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("priceToleranceInterval".equals(propertyId)) {
                try {
                    final ComboBox toleranceInterval = new ComboBox();
                    commonMsg.loadComboBox(toleranceInterval, "PRICE_TOLERANCE_INTERVAL", true);
                    toleranceInterval.select(0);

                    return toleranceInterval;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("priceToleranceFrequency".equals(propertyId)) {
                try {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "PRICE_TOLERANCE_FREQUENCY", true);
                    frequency.select(0);
                    return frequency;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("contractType".equals(propertyId)) {
                try {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "CONTRACT_TYPE", true);
                    frequency.select(0);
                    return frequency;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("resetType".equals(propertyId) || "resetInterval".equals(propertyId) || "resetFrequency".equals(propertyId)) {
                try {
                    ComboBox resetType = new ComboBox();
                    commonMsg.loadComboBox(resetType, "resetType".equals(propertyId) ? "RESET_TYPE" : 
                                        "resetInterval".equals(propertyId) ? "PRICE_TOLERANCE_INTERVAL" : "PRICE_TOLERANCE_FREQUENCY", true);
                    return resetType;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("resetEligible".equals(propertyId)) {
                try {
                    ComboBox resetEligible = new ComboBox();
                    commonMsg.loadYesNoDDLB(resetEligible, true);
                    resetEligible.setDebugId("testing");
                    return resetEligible;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("formulaType".equals(propertyId)) {
                try {
                    final ComboBox frequency = new ComboBox();
                    commonMsg.loadComboBox(frequency, "FORMULA_TYPE", true);
                    frequency.select(0);
                    return frequency;
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } 
        } catch (Exception ex) {
            LOGGER.error(ex);
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
