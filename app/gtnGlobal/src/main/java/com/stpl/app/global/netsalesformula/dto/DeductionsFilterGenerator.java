/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.netsalesformula.utils.UIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author karthikraja.k
 */
public class DeductionsFilterGenerator implements ExtFilterGenerator 
{
     CommonUtil commonUtil = CommonUtil.getInstance();
    
    private static final Logger LOGGER = Logger.getLogger(com.stpl.app.global.rebateschedule.dto.RSFilterGenerate.class);
    
    public DeductionsFilterGenerator() {
        //constructor
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {        
          return null;
    }
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        
        if (originatingField instanceof ComboBox) {   
            if(propertyId.toString().equals("indicator") && originatingField.getValue() != null)
            {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);  
            }
            else if (originatingField.getValue() != null) {                                   
                    return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);                
            }
        }

        return null;
    }
    
    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {

        try {
            switch (propertyId.toString()) {
                case "deductionTypeTable":
                case "deductionType":
                    ComboBox deductionType = new ComboBox();
                    commonUtil.loadComboBox(deductionType, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_TYPE, true);
                    return deductionType;
                case "deductionSubTypeTable":
                case "deductionSubType":
                    ComboBox deductionSubType = new ComboBox();
                    commonUtil.loadComboBox(deductionSubType, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_SUB_TYPE, true);
                    return deductionSubType;
                case "marketTypeTable":
                case "marketType" :
                     ComboBox marketType = new ComboBox();
                    commonUtil.loadComboBox(marketType, com.stpl.app.global.abstractsearch.util.UIUtils.CONTRACT_TYPE, true);
                    return marketType;
                case "deductionCategoryTable":
                case "deductionCategory" :
                     ComboBox deductionCategory = new ComboBox();
                    commonUtil.loadComboBox(deductionCategory, com.stpl.app.global.abstractsearch.util.UIUtils.DEDUCTION_CATEGORY, true);
                    return deductionCategory;
                case "indicator":
                    ComboBox indicator = new ComboBox();
                    UIUtils utils = new UIUtils();
                    utils.loadIndicator(indicator, true);
                    return indicator;
                default:
                    return null;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);          
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