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
        ExtPagedTable table = (ExtPagedTable) uiContext;
        int singleVisibleColumn = ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())
                ? 0 : Integer.valueOf(((String[]) (table.getDoubleHeaderForSingleHeader(propertyId.toString())).split("\\~"))[0]);
        if (singleVisibleColumn == (dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) || ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
            String doubleVisibleHeader = table.getDoubleHeaderColumnHeader(table.getDoubleHeaderForSingleHeader(propertyId.toString()));
            Double value = 0.0;
            boolean isEmptied = false;
            try {
                if (StringUtils.EMPTY.equals(val)) {
                    isEmptied = true;
                }
                if (val != null && !"0".equals(val.toString().replace("$", StringUtils.EMPTY))) {
                    value = Double.valueOf(val == null ? "0" : val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
                }
                dto.setCalculateFlag(true);
            } catch (NumberFormatException e) {
                if (!isEmptied) {
                    return;
                }
                LOGGER.debug("User is supposed to give Double value " + e.getMessage());
            }
            String period;
            if ("M".equals(selection.getSummarydemandfrequency().substring(0, 1))) {
                String monthPeriod = ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview()) ? doubleVisibleHeader : selection.getSummarydemandfromDate();
                period = String.valueOf(getLogic().getPeriodSid(monthPeriod));
            } else if (!ARMConstants.getMultiplePeriod().equals(selection.getSummarydemandview())) {
                period = selection.getSummarydemandfromDate();
            } else {
                period = doubleVisibleHeader;
            }
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
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
