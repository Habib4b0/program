/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.netsalesformula.dto;

import com.stpl.app.global.abstractsearch.util.UIUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class NsfFilterGenerator implements ExtFilterGenerator {

        
    private static Logger LOGGER = Logger.getLogger(NsfFilterGenerator.class);
      
    private final CommonUtil commonUtil = CommonUtil.getInstance();
    HelperDTO rtDto;
    
    public NsfFilterGenerator(HelperDTO rtDto) {
        this.rtDto = rtDto;
    }

    public NsfFilterGenerator() {
//        Empty Constructor
    }
        
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
             
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (propertyId.toString().equals("nsfcreatedBy") || propertyId.toString().equals("nsfmodifiedBy")) {
                if (originatingField.getValue() != null) {
                    return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                }
                 else {
                    return null;
                }
            }
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null)
          {
            return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);    
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
        try {

            if ("availableCheck".equals(propertyId)) {
                CheckBox availableCheck = new CheckBox();
                return availableCheck;
            }
            
            if ("netSalesFormulaType".equals(propertyId)) {
                ComboBox netSalesFormulaType = new ComboBox();
                commonUtil.loadComboBox(netSalesFormulaType, UIUtils.NS_FORMULA_TYPE, true);
                return netSalesFormulaType;
            }
            if ("nepFormulaType".equals(propertyId)) {
                ComboBox netSalesFormulaType = new ComboBox();
                commonUtil.loadComboBox(netSalesFormulaType, UIUtils.NS_FORMULA_TYPE, true);
                return netSalesFormulaType;
            }
            if ("ruleType".equals(propertyId)) {
                ComboBox ruleType = new ComboBox();
                commonUtil.loadComboBox(ruleType, UIUtils.RULE_TYPE, true);
                return ruleType;
            }
            if ("ruleCategoryString".equals(propertyId)) {
                ComboBox ruleCategory = new ComboBox();
                commonUtil.loadComboBox(ruleCategory, UIUtils.RULE_CATEGORY, true);
                return ruleCategory;
            }
            
             if ("marketType".equals(propertyId)) {
                ComboBox marketType = new ComboBox();
                commonUtil.loadComboBox(marketType, UIUtils.CONTRACT_TYPE, true);
                return marketType;
            }
            if ("createdDate".equals(propertyId)) {
                final DateField createdDate = new DateField();
                createdDate.setDescription(ConstantsUtils.DATE_DES);
                createdDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                createdDate.setImmediate(true);
                createdDate.setEnabled(false);
                return createdDate;
            }
            if ("nsfcreatedBy".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();
                Map<Integer, String> userMap = StplSecurity.getUserName();
                comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
            }
            if ("nsfmodifiedBy".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();
                Map<Integer, String> userMap = StplSecurity.getUserName();
                comboBox.addItem(0);
                    comboBox.setItemCaption(0, ConstantsUtils.SHOW_ALL);
                    for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
                        comboBox.addItem(entry.getKey());
                        comboBox.setItemCaption(entry.getKey(), entry.getValue());
                    }
                    comboBox.setNullSelectionAllowed(true);
                    comboBox.setNullSelectionItemId(0);
                    return comboBox;
            }
            
            if ("modifiedDate".equals(propertyId)) {
                final DateField modifiedDate = new DateField();
                modifiedDate.setDescription(ConstantsUtils.DATE_DES);
                modifiedDate.setDateFormat(ConstantsUtils.DATE_FORMAT);
                modifiedDate.setImmediate(true);
                modifiedDate.setEnabled(false);
                return modifiedDate;
            }
            if ("lineType".equals(propertyId)) {
                ComboBox lineType = new ComboBox();
                commonUtil.loadComboBox(lineType, UIUtils.LINE_TYPE, true);
                return lineType;
            }
            if ("association".equals(propertyId)) {
                ComboBox association = new ComboBox();
                commonUtil.loadComboBox(association, UIUtils.ITEM_GROUP_MS_ASSOCIATION, true);
                return association;
            }
            if ("keyword".equals(propertyId)) {
                ComboBox keyword = new ComboBox();
                commonUtil.loadComboBox(keyword, UIUtils.KEYWORD, true);
                return keyword;
            }
            if ("operator".equals(propertyId)) {
                ComboBox operator = new ComboBox();
                commonUtil.loadComboBox(operator, UIUtils.OPERATOR, true);
                return operator;
            }
            if ("comparison".equals(propertyId)) {
                ComboBox comparison = new ComboBox();
                commonUtil.loadComboBox(comparison, UIUtils.COMPARISON, true);
                return comparison;
            }
            if ("logicalOperator".equals(propertyId)) {
                ComboBox logicalOperator = new ComboBox();
                commonUtil.loadComboBox(logicalOperator, UIUtils.LOGICAL_OPERATOR, true);
                return logicalOperator;
            }
            
        } catch (Exception ex) {
            LOGGER.error(ex);;
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