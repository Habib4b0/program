/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

public class RsDeductionFilterGenerator implements ExtFilterGenerator {

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {

        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {
                if (propertyId.toString().equals("createdBy") || propertyId.toString().equals("modifiedBy")) {
                    if (originatingField.getValue() != null) {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
                    } else {
                        return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()).replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape()), false, false);
                    }
                }

            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        ComboBox comboBox;
        Map<Integer, String> userMap = StplSecurity.userMap;
        if ("createdBy".equals(propertyId) || "modifiedBy".equals(propertyId)) {
            comboBox = new ComboBox();
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
