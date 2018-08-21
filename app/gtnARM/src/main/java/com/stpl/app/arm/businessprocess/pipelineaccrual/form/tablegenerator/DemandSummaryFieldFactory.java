/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author Nimisha.Rakesh
 */
public class DemandSummaryFieldFactory extends SummaryFieldFactory {

    private AbstractSelectionDTO selection;
    private String tableName;

    public DemandSummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire, String tableName) {
        super(logic, selection, isFieldRequire);
        this.selection = selection;
        this.tableName = tableName;
    }

    @Override
    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        int singleVisibleColumn = getSingleVisibleColumn(uiContext, propertyId);
        String doubleVisibleHeader = getDoubleVisibleHeader(uiContext, propertyId);

        if (singleVisibleColumn == (dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) || ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            Double value = 0.0;
            boolean isEmptied = false;
            try {

                isEmptied = checkIsEmptyString(val);

                if (checkNotNullAndNotEqualToZero(val)) {
                    value = convertToDouble(val);
                }
                dto.setCalculateFlag(true);
            } catch (NumberFormatException e) {
                if (!isEmptied) {
                    return;
                }
                LOGGER.debug("User is supposed to give Double value " + e.getMessage());
            }
            String period = getPeriod(doubleVisibleHeader);
            submitToService(dto, isEmptied, period, value);
        }
    }

    private void submitToService(AdjustmentDTO dto, boolean isEmptied, String period, Double value) {
        List input = new ArrayList();
        input.add(selection.getProjectionMasterSid());
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        input.add(dto.getBranditemmasterSid());
        input.add(isEmptied ? "NULL" : value.toString());
        input.add(selection.getSummarydemandfrequency().substring(0, 1));
        input.add(period);
        input.add(tableName);
        input.add(tableName);
        service.submit(new UpdateOverride(input));
    }

    private Double convertToDouble(Object val) {
        return Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
    }

    private boolean checkNotNullAndNotEqualToZero(Object val) {
        return val != null && !"0".equals(val.toString().replace("$", StringUtils.EMPTY));
    }

    private boolean checkIsEmptyString(Object val) {
        return StringUtils.EMPTY.equals(val);
    }

    private int getSingleVisibleColumn(Component uiContext, Object propertyId) {
        if (uiContext instanceof ExtPagedTable) {
            ExtPagedTable table = (ExtPagedTable) uiContext;
            return ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())
                    ? 0 : Integer.valueOf(((String[]) (table.getDoubleHeaderForSingleHeader(propertyId.toString())).split("\\~"))[0]);
        } else {
            ExtCustomTable table = (ExtCustomTable) uiContext;
            return ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())
                    ? 0 : Integer.valueOf(((String[]) (table.getDoubleHeaderForSingleHeader(propertyId.toString())).split("\\~"))[0]);
        }
    }

    private String getDoubleVisibleHeader(Component uiContext, Object propertyId) {
        if (uiContext instanceof ExtPagedTable) {
            ExtPagedTable table = (ExtPagedTable) uiContext;
            return table.getDoubleHeaderColumnHeader(table.getDoubleHeaderForSingleHeader(propertyId.toString()));
        } else {
            ExtCustomTable table = (ExtCustomTable) uiContext;
            return table.getDoubleHeaderColumnHeader(table.getDoubleHeaderForSingleHeader(propertyId.toString()));
        }
    }

    private String getPeriod(String doubleVisibleHeader) {
        if ("M".equals(selection.getSummarydemandfrequency().substring(0, 1))) {
            String monthPeriod = ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview()) ? doubleVisibleHeader : selection.getSummarydemandfromDate();
            return String.valueOf(getLogic().getPeriodSid(monthPeriod));
        } else if (!ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            return selection.getSummarydemandfromDate();
        } else {
            return doubleVisibleHeader;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
