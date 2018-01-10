/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.dto;

import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.Field;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinodhini
 */
public class OutboundFilterGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutboundFilterGenerator.class);

    private final CommonUtils commonUtil = CommonUtils.getInstance();

    public OutboundFilterGenerator() {
//        Empty Constructor
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (propertyId.toString().equals("createdBy")) {
            if (originatingField.getValue() != null) {
                return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
            } else {
                return null;
            }
        }
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {

                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO) originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {

            if ("hierarchyCategory".equals(propertyId)) {
                ComboBox hierarchyCategory = new ComboBox();
                commonUtil.loadComboBox(hierarchyCategory, CommonUIUtil.HIERARCHY_CATEGORY, true);
                return hierarchyCategory;
            }
            if ("hierarchyTypeDto".equals(propertyId) || "relationshipType".equals(propertyId)) {
                ComboBox hierarchyType = new ComboBox();
                commonUtil.loadComboBox(hierarchyType, CommonUIUtil.RELATIONSHIP_TYPE, true);
                return hierarchyType;
            }
            if ("createdBy".equals(propertyId)) {
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
