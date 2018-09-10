/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.demandreforecast.dto.DRSelectionDTO;
import com.stpl.app.arm.businessprocess.demandreforecast.form.AdjustmentDetailReforecast;
import com.stpl.app.arm.businessprocess.demandreforecast.form.AdjustmentSummaryDemandReforecast;
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
public class Transaction5 extends AbstractTransaction {

    private DRSelectionDTO dRSelectionDTO;
    private AdjustmentSummaryDemandReforecast reforecastsummary;
    private AdjustmentDetailReforecast reforecastDetails;
    public static final Logger TXN5_LOGGER = LoggerFactory.getLogger(Transaction5.class);

    public Transaction5(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.FIVE, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            dRSelectionDTO = new DRSelectionDTO();

            dRSelectionDTO.setDataSelectionDTO(getDataselectionDTO());
            dRSelectionDTO.setSessionDTO(getSessionDTO());
            reforecastsummary = new AdjustmentSummaryDemandReforecast(dRSelectionDTO);
            reforecastDetails = new AdjustmentDetailReforecast(dRSelectionDTO);
            TabSheet.Tab tab3 = getTabSheet().addTab(reforecastsummary, "Adjustment Summary");
            tab3.setDefaultFocusComponent(reforecastsummary.getDefaultFocusComponent());
            TabSheet.Tab tab4 = getTabSheet().addTab(reforecastDetails, "Adjustment Detail");
            tab4.setDefaultFocusComponent(reforecastDetails.getDefaultFocusComponent());
            TabSheet.Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
            tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
        } catch (Exception e) {
            TXN5_LOGGER.error("Error in initializeTabs", e);
        }
    }

    @Override
    public int getTransactionNo() {
        return NumericConstants.FIVE;
    }

    @Override
    public boolean isGenerated() {
        return reforecastsummary.isGenerated() && reforecastDetails.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransactionDemand();
    }

    @Override
    public DRSelectionDTO getSelection() {
        return dRSelectionDTO;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.saveDemandPaymentsReforcastSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
        return reforecastsummary.saveAssets();
    }

    @Override
    public String getReserveQuery() {
        TXN5_LOGGER.debug("Transaction 5 gtn Outbound Insert Query");
        return "Demand_Adjustment_details_Insert_Reserve";
    }

    @Override
    public String getGtnQuery() {
        TXN5_LOGGER.debug("Transaction 5 Reserve Outbound Insert Query");
        return "Demand_Adjustment_details_Insert_GTN";
    }

    @Override
    public String getTransactionName() {
        return "ARM_Txt_5";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        reforecastsummary.configurePermission(userId, stplSecurity);
        reforecastDetails.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_DEMAND_RF_TRUE_UP_SUMMARY";
    }

    @Override
    public String ratesInput() {
        return StringUtils.EMPTY;
    }
}
