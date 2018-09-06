/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.form.tablegenerator;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nimisha.Rakesh
 */
public class Trx7DemandSummaryFieldFactory extends Trx7SummaryFieldFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trx7DemandSummaryFieldFactory.class);
    private AbstractSelectionDTO selection;

    public Trx7DemandSummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire) {
        super(logic, selection, isFieldRequire);
        this.selection = selection;
    }

    @Override
    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        ExtPagedTable table = (ExtPagedTable) uiContext;
        String doubleVisibleHeader = table.getDoubleHeaderColumnHeader(table.getDoubleHeaderForSingleHeader(propertyId.toString()));
        Double value = null;
        try {
            if (val != null && !"0".equals(val.toString().replace("$", StringUtils.EMPTY))) {
                value = Double.valueOf(val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
            }
            dto.setCalculateFlag(true);
        } catch (NumberFormatException e) {
            LOGGER.debug("User is supposed to give Double value {}", e.getMessage());
            return;
        }
        String period;
        if ("M".equals(selection.getSummarydemandfrequency().substring(0, 1))) {
            period = String.valueOf(getLogic().getPeriodSid(doubleVisibleHeader));
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
        input.add(value);
        input.add(dto.getUserId());
        input.add(dto.getSessionId());
        input.add(selection.getSummarydemandfrequency().substring(0, 1));
        input.add(period);

        service.submit(new Tr7SummaryUpdateOverride(input));
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
