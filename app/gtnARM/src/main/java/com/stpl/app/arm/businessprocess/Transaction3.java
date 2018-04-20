/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.AdjustmentDetailInventory;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.AdjustmentSummaryInventory;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.Inventory;
import com.stpl.app.arm.businessprocess.pipelineinventory.form.PipelineInventoryRates;
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
public class Transaction3 extends AbstractTransaction {

    private PipelineInventorySelectionDTO piSelectionDto;
    private Inventory inventory;
    private PipelineInventoryRates rates;
    private AdjustmentSummaryInventory summary;
    private AdjustmentDetailInventory details;
    public static final Logger LOGGER = LoggerFactory.getLogger(Transaction3.class);

    public Transaction3(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.THREE, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            piSelectionDto = new PipelineInventorySelectionDTO();
            piSelectionDto.setDataSelectionDTO(getDataselectionDTO());
            piSelectionDto.setSessionDTO(getSessionDTO());
            inventory = new Inventory(getDataselectionDTO(), piSelectionDto, getDataselectionDTO().getProjectionId());
            rates = new PipelineInventoryRates(piSelectionDto);
            summary = new AdjustmentSummaryInventory(piSelectionDto);
            details = new AdjustmentDetailInventory(piSelectionDto);

            TabSheet.Tab tab1 = getTabSheet().addTab(inventory, "Inventory");
            tab1.setDefaultFocusComponent(inventory.getDefaultFocusComponent());
            TabSheet.Tab tab2 = getTabSheet().addTab(rates, "Rates");
            tab2.setDefaultFocusComponent(rates.getDefaultFocusComponent());
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
        return NumericConstants.THREE;
    }

    @Override
    public boolean isGenerated() {
        return inventory.isGenerated() && rates.isGenerated() && summary.isGenerated() && details.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransaction3();
    }

    @Override
    public PipelineInventorySelectionDTO getSelection() {
        return piSelectionDto;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.savePipelineInventorySelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
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
        return "ARM_Txt_3";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        inventory.configurePermission(userId, stplSecurity);
        rates.configurePermission(userId, stplSecurity);
        summary.configurePermission(userId, stplSecurity);
        details.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_INVENTORY_RATE";
    }

    @Override
    public String ratesInput() {
        return ",\n"
                + "  MAX(B.RATE) AS RATE";
    }
}
