/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.form.Trx6AdjustmentDetail;
import com.stpl.app.arm.businessprocess.transaction6.form.Trx6AdjustmentSummary;
import com.stpl.app.arm.businessprocess.transaction6.form.Trx6Inventory;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.TabSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abhiram.Giri
 */
public class Transaction6 extends AbstractTransaction {

    private Trx6SelectionDTO trx6Selection;
    private Trx6Inventory inventory;
    private Trx6AdjustmentSummary summary;
    private Trx6AdjustmentDetail details;
    public static final Logger LOGGER = LoggerFactory.getLogger(Transaction6.class);

    public Transaction6(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.SIX, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            trx6Selection = new Trx6SelectionDTO();
            trx6Selection.setDataSelectionDTO(getDataselectionDTO());
            trx6Selection.setSessionDTO(getSessionDTO());
            inventory = new Trx6Inventory(trx6Selection, getDataselectionDTO().getProjectionId());
            summary = new Trx6AdjustmentSummary(trx6Selection);
            details = new Trx6AdjustmentDetail(trx6Selection);

            TabSheet.Tab tab1 = getTabSheet().addTab(inventory, "Inventory");
            tab1.setDefaultFocusComponent(inventory.getDefaultFocusComponent());
            TabSheet.Tab tab3 = getTabSheet().addTab(summary, "Adjustment Summary");
            tab3.setDefaultFocusComponent(summary.getDefaultFocusComponent());
            TabSheet.Tab tab4 = getTabSheet().addTab(details, "Adjustment Detail");
            tab4.setDefaultFocusComponent(details.getDefaultFocusComponent());
            TabSheet.Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
            tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
        } catch (Exception e) {
            LOGGER.error("Error in initializeTabs" + e);
        }
    }

    @Override
    public int getTransactionNo() {
        return NumericConstants.SIX;
    }

    @Override
    public boolean isGenerated() {
        return inventory.isGenerated() && summary.isGenerated() && details.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransaction6();
    }

    @Override
    public Trx6SelectionDTO getSelection() {
        return trx6Selection;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.saveInflationAdjustmentSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
        return summary.saveAssets();
    }

    @Override
    public String getGtnQuery() {
        return "Pipeline_Adjustment_details_Insert_GTN";
    }

    @Override
    public String getReserveQuery() {
        return "Pipeline_Adjustment_details_Insert_Reserve";
    }

    @Override
    public String getTransactionName() {
        return "ARM_Txt_6";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        inventory.configurePermission(userId, stplSecurity);
        summary.configurePermission(userId, stplSecurity);
        details.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_INFLATION_INVENTORY_ADJ";
    }

    @Override
    public String ratesInput() {
        return ",\n"
                + "  MAX(B.ADJUSTMENT_RATIO) AS RATE";
    }
}
