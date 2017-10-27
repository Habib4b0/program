/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.stpl.app.arm.businessprocess.demandaccrual.dto.DASelectionDTO;
import com.stpl.app.arm.businessprocess.demandaccrual.form.AdjustmentDetailDemand;
import com.stpl.app.arm.businessprocess.demandaccrual.form.AdjustmentSummaryDemandAccrual;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.TabSheet;
import org.jboss.logging.Logger;

/**
 *
 * @author Abhiram.Giri
 */
public class Transaction2 extends AbstractTransaction {

    DASelectionDTO daSelectionDto;
    AdjustmentSummaryDemandAccrual summary;
    AdjustmentDetailDemand details;
    public static final Logger LOGGER = Logger.getLogger(Transaction2.class);

    public Transaction2(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.TWO, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            daSelectionDto = new DASelectionDTO();
            daSelectionDto.setDataSelectionDTO(getDataselectionDTO());
            daSelectionDto.setSessionDTO(getSessionDTO());
            summary = new AdjustmentSummaryDemandAccrual(daSelectionDto);
            details = new AdjustmentDetailDemand(daSelectionDto);
            TabSheet.Tab tab3 = getTabSheet().addTab(summary, "Adjustment Summary");
            tab3.setDefaultFocusComponent(summary.getDefaultFocusComponent());
            TabSheet.Tab tab4 = getTabSheet().addTab(details, "Adjustment Detail");
            tab4.setDefaultFocusComponent(details.getDefaultFocusComponent());
            TabSheet.Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
            tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());

        } catch (Exception e) {
            LOGGER.error("Error in initializeTabs"+e);
        }
    }

    @Override
    public int getTransactionNo() {
        return NumericConstants.TWO;
    }

    @Override
    public boolean isGenerated() {
        return summary.isGenerated() && details.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransactionDemand();
    }

    @Override
    public DASelectionDTO getSelection() {
        return daSelectionDto;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.saveDemandPaymentsReforcastSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
        return summary.saveAssets();
    }

    @Override
    public String getGtnQuery() {
        return "Demand_Adjustment_details_Insert_GTN";
    }

    @Override
    public String getReserveQuery() {
        return "Demand_Adjustment_details_Insert_Reserve";
    }

    @Override
    public String getTransactionName() {
        return "ARM_Txt_2";
    }

    @Override
    public void configurePermission() throws Exception {
        String userId = String.valueOf(getSessionDTO().getUserId());
        summary.configurePermission(userId, stplSecurity);
        details.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_DEMAND_ADJ_SUMMARY";
    }
}
