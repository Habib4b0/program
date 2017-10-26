/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form.tablegenerator;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nimisha.Rakesh
 */
public class Trx7DemandSummaryFieldFactory extends Trx7SummaryFieldFactory {

    AbstractSelectionDTO selection;

    public Trx7DemandSummaryFieldFactory(AbstractSummaryLogic logic, AbstractSelectionDTO selection, Boolean isFieldRequire) {
        super(logic, selection, isFieldRequire);
        this.selection = selection;
    }

    @Override
    protected void valueChangeLogic(AdjustmentDTO dto, Object val, Object propertyId, Component uiContext) {
        ExtCustomTable table = (ExtCustomTable) uiContext;
        String doubleVisibleHeader = table.getDoubleHeaderColumnHeader(table.getDoubleHeaderForSingleHeader(propertyId.toString()));
        Double value = null;
        try {
            if (val != null && !val.toString().replace("$", StringUtils.EMPTY).equals("0")) {
                value = Double.valueOf(val.toString().trim().replaceAll("[^\\-\\d.]", StringUtils.EMPTY));
            }
            dto.setCalculateFlag(true);
        } catch (NumberFormatException e) {
            LOGGER.debug("User is supposed to give Double value " + e.getMessage());
            return;
        }
        String period = StringUtils.EMPTY;
        if (selection.getSummary_demand_frequency().substring(0, 1).equals("M")) {
            period = String.valueOf(getLogic().getPeriodSid(doubleVisibleHeader));
        } else if (!ARMConstants.getMultiplePeriod().equals(selection.getSummary_demand_view())) {
            period = selection.getSummary_demand_fromDate();
        } else {
            period = doubleVisibleHeader;
        }
        List input = new ArrayList();
        input.add(selection.getProjectionMasterSid());
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CONTRACT.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.CUSTOMER.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.BRAND.toString()));
        input.add(dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()) == null ? "%" : dto.getMasterIds().get(ARMUtils.levelVariablesVarables.DEDUCTION.toString()));
        input.add(dto.getBrand_item_masterSid());
        input.add(value);
        input.add(dto.getUserId());
        input.add(dto.getSessionId());
        input.add(selection.getSummary_demand_frequency().substring(0, 1));
        input.add(period);

        service.submit(new UpdateOverride(input));
    }

}
