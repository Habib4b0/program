/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.priceschedule.dto;

import com.stpl.app.global.cfp.util.CommonUtils;
import com.stpl.app.global.common.util.CommonUtil;
import com.stpl.app.global.priceschedule.util.UIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.numberfilter.NumberFilterPopup;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author Shrihariharan
 */
public class IFPFilterGenerator implements ExtFilterGenerator {

    CommonUtils commonsUtil = new CommonUtils();
    CommonUtil commonUtil = CommonUtil.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IFPFilterGenerator.class.getName());

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {

        if ("startDate".equals(propertyId)) {
            /* Create an 'equals' filter for the ID field */
            if (value != null && value instanceof String) {
                try {
                    return new Compare.Equal(propertyId,
                            Integer.parseInt((String) value));
                } catch (NumberFormatException ignored) {
                    // If no integer was entered, just generate default filter
                }
            }
        }
        // For other properties, use the default filter
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null) {                
                return new SimpleStringFilter(propertyId, String.valueOf(((HelperDTO)originatingField.getValue()).getId()), false, false);
            } else {
                return null;
            }
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
        final PSIFPDTO psDTO = new PSIFPDTO();

        if ("itemFamilyPlanStatus".equals(propertyId)) {
            try {
                ComboBox itemFamilyPlanStatus = new ComboBox();
                commonUtil.loadComboBox(itemFamilyPlanStatus, UIUtils.STATUS, true);
                itemFamilyPlanStatus.setDebugId("testing");
                return itemFamilyPlanStatus;
            } catch (Exception ex) {
                LOGGER.error(ex);;
            }
        }
        if ("ifpType".equals(propertyId)) {
            try {
                ComboBox ifpType = new ComboBox();
                commonUtil.loadComboBox(ifpType, "IFP_TYPE", true);
                ifpType.setDebugId("testing");
                return ifpType;
            } catch (Exception ex) {
                LOGGER.error(ex);;
            }
        }
        if ("ifpCategory".equals(propertyId)) {
            try {
                ComboBox ifpCategory = new ComboBox();
                commonUtil.loadComboBox(ifpCategory, "IFP_CATEGORY", true);
                ifpCategory.setDebugId("testing");
                return ifpCategory;
            } catch (Exception ex) {
                LOGGER.error(ex);;
            }
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
