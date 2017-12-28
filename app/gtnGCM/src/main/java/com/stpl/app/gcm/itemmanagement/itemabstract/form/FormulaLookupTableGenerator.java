/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form;

import com.stpl.app.gcm.common.CommonUtil;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rohitvignesh.s
 */
public class FormulaLookupTableGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormulaLookupTableGenerator.class);

    public FormulaLookupTableGenerator() {
       LOGGER.debug("FormulaLookupTableGenerator");
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        try {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            if ("netSalesformulaType".equals(propertyId)) {
                final ComboBox comboBox = new ComboBox();
                CommonUtil.loadComboBoxForGCM(comboBox, Constants.NS_FORMULA_TYPE_LISTNAME, true);
                return comboBox;
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
