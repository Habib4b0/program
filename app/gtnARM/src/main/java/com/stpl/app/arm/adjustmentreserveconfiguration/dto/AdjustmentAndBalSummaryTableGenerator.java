/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import com.vaadin.v7.ui.AbstractField;
import com.vaadin.v7.ui.Field;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author saranya.sekar
 */
public class AdjustmentAndBalSummaryTableGenerator implements ExtFilterGenerator {

    private ReserveSelection selection;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentAndBalSummaryTableGenerator.class);

    public AdjustmentAndBalSummaryTableGenerator(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField.getValue() != null && !originatingField.getValue().toString().equals(StringUtils.EMPTY)) {
            return new SimpleStringFilter(propertyId, String.valueOf(originatingField.getValue()), false, false);
        }
        return null;

    }

    @Override
    public AbstractField<?> getCustomFilterComponent(Object propertyId) {
        try {
            if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant().equals(propertyId)) {
                CustomTextField text = new CustomTextField();
                text.setVisible(false);
                text.setImmediate(true);
                return text;
            } else {
                CustomTextField text = new CustomTextField();
                text.setImmediate(true);
                return text;
            }

        } catch (Exception e) {
            LOGGER.error("Error in getCustomFilterComponent :", e);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        LOGGER.debug("filterRemoved Method{}", selection);
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        LOGGER.debug("filterAdded Method");
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

    private void writeObject(ObjectOutputStream tabgenout) throws IOException {
        tabgenout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream tabgenout) throws IOException, ClassNotFoundException {
        tabgenout.defaultReadObject();
    }

}
