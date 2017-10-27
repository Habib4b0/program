/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.dto;

import com.stpl.app.contract.common.util.CommonUtil;
import com.stpl.app.contract.contractheader.util.CommonUtils;
import com.stpl.app.contract.contractheader.util.UIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;

public class PsParentFilterGenerator implements ExtFilterGenerator {

    CommonUtils commonsUtil = new CommonUtils();

    /**
     * The common util.
     */
    private CommonUtil commonUtil = CommonUtil.getInstance();

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {

        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {

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

        if ("priceScheduleType".equalsIgnoreCase(String.valueOf(propertyId))) {
            ComboBox priceScheduleType = new ComboBox();
            
            commonUtil.loadComboBox(priceScheduleType, UIUtils.PS_TYPE, true);
            return priceScheduleType;
        }
        if ("priceScheduleStatus".equalsIgnoreCase(String.valueOf(propertyId))) {
            ComboBox priceScheduleStatus = new ComboBox();

            commonUtil.loadComboBox(priceScheduleStatus, UIUtils.STATUS, true);
            return priceScheduleStatus;

        }

        if ("priceScheduleCategory".equalsIgnoreCase(String.valueOf(propertyId))) {
            ComboBox priceScheduleCategory = new ComboBox();

            commonUtil.loadComboBox(priceScheduleCategory, UIUtils.PS_CATEGORY, true);
            return priceScheduleCategory;
        }
        
        if ("priceScheduleDesignation".equalsIgnoreCase(String.valueOf(propertyId))) {
            ComboBox priceScheduleDesignation = new ComboBox();

            commonUtil.loadComboBox(priceScheduleDesignation, UIUtils.PS_DESIGNATION, true);
            return priceScheduleDesignation;
        }
        if ("tradeClass".equalsIgnoreCase(String.valueOf(propertyId))) {
            ComboBox tradeClass = new ComboBox();

            commonUtil.loadComboBox(tradeClass, UIUtils.PS_TRADE_CLASS, true);
            return tradeClass;
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
