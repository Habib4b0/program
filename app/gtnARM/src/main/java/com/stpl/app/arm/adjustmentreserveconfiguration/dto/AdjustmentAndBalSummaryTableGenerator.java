/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.utils.CommonUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;

import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterGenerator;
import org.jboss.logging.Logger;

/**
 *
 * @author saranya.sekar
 */
public class AdjustmentAndBalSummaryTableGenerator implements ExtFilterGenerator {

    ReserveSelection selection;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(AdjustmentAndBalSummaryTableGenerator.class);

    public AdjustmentAndBalSummaryTableGenerator(ReserveSelection selection) {
        this.selection = selection;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Object value) {
        return null;
    }

    @Override
    public Container.Filter generateFilter(Object propertyId, Field<?> originatingField) {
        if (originatingField instanceof ComboBox) {
            if (originatingField.getValue() != null && ArrayUtils.contains(ARMUtils.getArmAdjSummaryConfCustomMenubarHeaders(), propertyId.toString())) {
                ComboBox combo = (ComboBox) originatingField;
                return new SimpleStringFilter(propertyId, String.valueOf(combo.getValue()), false, false);
            }
            return null;

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
            }
            if (ArrayUtils.contains(ARMUtils.getArmAdjSummaryConfComboHeaders(), propertyId.toString()) || ArrayUtils.contains(ARMUtils.getBalSummaryConfComboHeader(), propertyId.toString())) {
                final ComboBox combo = new ComboBox();
                CommonUtils.loadTransactionNameForCurrentSession(combo, selection.getTempTableName(), Boolean.TRUE);
                return combo;
            } else if (ArrayUtils.contains(ARMUtils.getArmAdjSummaryConfCustomMenubarHeaders(), propertyId.toString())) {
                final ComboBox comboForAccount = new ComboBox();
                CommonUtils.loadDistinctAccount(comboForAccount, selection.getTempTableName(), Boolean.TRUE);
                return comboForAccount;

            } else if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BAL_SUMMARY_ACCOUNT.getConstant().equals(propertyId.toString())) {
                final ComboBox combo = new ComboBox();
                CommonUtils.loadDistinctAccount(combo, selection.getTempTableName(), Boolean.TRUE);
                return combo;
            }

        } catch (Exception e) {
            LOGGER.error("Error in getCustomFilterComponent :"+e);
        }
        return null;
    }

    @Override
    public void filterRemoved(Object propertyId) {
        LOGGER.debug("filterRemoved Method");
    }

    @Override
    public void filterAdded(Object propertyId, Class<? extends Container.Filter> filterType, Object value) {
        LOGGER.debug("filterAdded Method");
    }

    @Override
    public Container.Filter filterGeneratorFailed(Exception reason, Object propertyId, Object value) {
        return null;
    }

}
