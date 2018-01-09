/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.dto;

import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import com.vaadin.v7.ui.TextField;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sooriya.lakshmanan
 */
public class FilterGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterGenerator.class);
    
    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox || originatingField instanceof TextField) {

            if (originatingField.getValue() != null) {
                String value = originatingField.getValue().toString();
                if (Constant.FORMULA_TYPE.equals(propertyId) || Constant.DEDUCTION_TYPE.equals(propertyId) || Constant.DEDUCTION_SUB_TYPE.equals(propertyId) || Constant.DEDUCTION_CATEGORY.equals(propertyId)) {
                    value = String.valueOf(((HelperDTO) originatingField.getValue()).getId());
                }
                return new SimpleStringFilter(propertyId, value, false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        if (Constant.FORMULA_TYPE.equals(propertyId)) {
            try {
                ComboBox formulaType = new ComboBox();
                CommonUtil.getInstance().loadComboBox(formulaType, "NS_FORMULA_TYPE", false);
                return formulaType;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (Constant.DEDUCTION_TYPE.equals(propertyId)) {
            try {
                ComboBox deductionType = new ComboBox();
                CommonUtil.getInstance().loadComboBox(deductionType, Constant.RS_TYPE, false);
                return deductionType;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (Constant.DEDUCTION_SUB_TYPE.equals(propertyId)) {
            try {
                ComboBox deductionSubType = new ComboBox();
                CommonUtil.getInstance().loadComboBox(deductionSubType, "REBATE_PROGRAM_TYPE", false);
                return deductionSubType;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        } else if (Constant.DEDUCTION_CATEGORY.equals(propertyId)) {
            try {
                ComboBox deductionCategory = new ComboBox();
                CommonUtil.getInstance().loadComboBox(deductionCategory, "RS_CATEGORY", false);
                return deductionCategory;
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
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
