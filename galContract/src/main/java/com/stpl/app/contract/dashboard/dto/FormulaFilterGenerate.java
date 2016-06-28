/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public class FormulaFilterGenerate implements ExtFilterGenerator {

    private final CommonUtil commonUtil = CommonUtil.getInstance();    
    
    private static final Logger LOGGER = Logger.getLogger(FormulaFilterGenerate.class);
    
    IfpLogic rebateLogic = new IfpLogic();
    
    public FormulaFilterGenerate() {
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {        
        return null;
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        try
        {
            if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            } else if (originatingField instanceof ComboBox) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
                } else {
                    return null;
                }
            }
   
        }
        catch(Exception e){
             LOGGER.error(e);      
        }
        return null;
    }
    
    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        try {
            final ComboBox comboBox = new ComboBox();
           Map<Integer, String> userMap = StplSecurity.getUserName();
            switch (propertyId.toString()) {
                    case "formulaType":
                    commonUtil.loadComboBox(comboBox,  ConstantsUtils.REBATE_PLAN_FORMULA_TYPE, true);
                    return comboBox;
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
