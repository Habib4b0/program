/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import static com.stpl.app.utils.CommonUtils.userMap;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class ViewFilterGenerator implements ExtFilterGenerator {

    private static final Logger LOGGER = Logger.getLogger(ViewFilterGenerator.class);

    public ViewFilterGenerator() {
        LOGGER.debug("inside VireFiltergenerator Constructor");

    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if ((originatingField instanceof ComboBox) && (originatingField.getValue() != null)) {
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        if (originatingField instanceof NumberFilterPopup) {
            NumberFilterPopup popup = (NumberFilterPopup) originatingField;
            if (StringUtils.isNotBlank(popup.getValue().getEqualsValue())) {
                return new Compare.Equal(propertyId, popup.getValue().getEqualsValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue()) && StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new And(new Compare.Less(propertyId, popup.getValue().getLessThanValue()), new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue()));
            } else if (StringUtils.isNotBlank(popup.getValue().getLessThanValue())) {
                return new Compare.Less(propertyId, popup.getValue().getLessThanValue());
            } else if (StringUtils.isNotBlank(popup.getValue().getGreaterThanValue())) {
                return new Compare.Greater(propertyId, popup.getValue().getGreaterThanValue());
            }
        }
        return null;
    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            CommonUtils.getUserName();
            if ("createdByString".equals(propertyId) || "createdBy".equals(propertyId) || "modifiedBy".equals(propertyId)) {
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

            if ("deductionLevels".equals(propertyId)) {
                CustomComboBox itemStatus = new CustomComboBox();
                CommonUtils.loadComboBoxWithInteger(itemStatus, "DEDUCTION_LEVELS", true);
                return itemStatus;
            }
        } catch (Exception e) {
            LOGGER.error("Error in getCustomFilterComponent :"+e);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        LOGGER.debug("Inside filterRemoved Method");
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        LOGGER.debug("Inside filterAdded Method");
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
