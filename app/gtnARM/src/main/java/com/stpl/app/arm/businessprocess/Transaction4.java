/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.demandpayment.dto.DPSelectionDTO;
import com.stpl.app.arm.businessprocess.demandpayment.form.AdjustmentDetailsPayment;
import com.stpl.app.arm.businessprocess.demandpayment.form.AdjustmentSummaryDemandPayment;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.TabSheet;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abhiram.Giri
 */
public class Transaction4 extends AbstractTransaction {

    private DPSelectionDTO dpSelectionDto;
    private AdjustmentSummaryDemandPayment summary;
    private AdjustmentDetailsPayment details;
    public static final Logger LOGGER = LoggerFactory.getLogger(Transaction4.class);

    public Transaction4(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.FOUR, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            dpSelectionDto = new DPSelectionDTO();
            dpSelectionDto.setDataSelectionDTO(getDataselectionDTO());
            dpSelectionDto.setSessionDTO(getSessionDTO());
            summary = new AdjustmentSummaryDemandPayment(dpSelectionDto);
            details = new AdjustmentDetailsPayment(dpSelectionDto);

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
        return NumericConstants.FOUR;
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
    public DPSelectionDTO getSelection() {
        return dpSelectionDto;
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
        return "ARM_Txt_4";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        summary.configurePermission(userId, stplSecurity);
        details.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_DEMAND_RECON_SUMMARY";
    }

    @Override
    public String ratesInput() {
        return StringUtils.EMPTY;
    }

}
