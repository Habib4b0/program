/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.abstractsearch.util.ConstantUtil;
import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
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
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
      if (originatingField instanceof ComboBox) {
          if(originatingField.getValue()!=null){
              if (ConstantsUtils.CREATEDBY.equals(propertyId) || ConstantsUtils.MODIFIEDBY.equals(propertyId)) {
                   return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
              } else {
                  return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
              }
          }
          else{
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
            if (ConstantUtil.RULE_TYPE.equals(propertyId)) {
                final ComboBox ruleType = new ComboBox();
                final HelperDTO defaultValue = new HelperDTO(0, ConstantsUtils.SHOW_ALL);
                ruleType.setValidationVisible(true);
                ruleType.setImmediate(true);
                ruleType.setNullSelectionAllowed(true);
                ruleType.setNullSelectionItemId(defaultValue);
                ruleType.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);

                List<HelperDTO> helperList = new ArrayList<>();
                helperList.add(defaultValue);
                BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
                helperList.add(rtDto);

                resultContainer.addAll(helperList);
                ruleType.setContainerDataSource(resultContainer);
                ruleType.select(defaultValue);
                ruleType.markAsDirty();
                ruleType.setDescription((String) (ruleType.getValue() == null ? ConstantsUtils.SELECT_ONE : ruleType.getValue()));
                ruleType.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                            ruleType.select(defaultValue);
                        }
                        ruleType.setDescription((String) (ruleType.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) ruleType.getValue()).getDescription()));
                    }
                });
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
           
              
        if (ConstantsUtils.CREATEDBY.equals(propertyId) || ConstantsUtils.MODIFIEDBY.equals(propertyId)) {
            Map<Integer, String> userMap = StplSecurity.userMap;
            ComboBox comboBox = new ComboBox();
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