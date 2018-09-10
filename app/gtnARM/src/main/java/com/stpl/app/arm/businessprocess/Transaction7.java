
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.transaction7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction7.form.Trx7AdjustmentDetail;
import com.stpl.app.arm.businessprocess.transaction7.form.Trx7AdjustmentSummary;
import com.stpl.app.arm.businessprocess.transaction7.form.Trx7RatesTab;
import com.stpl.app.arm.businessprocess.transaction7.form.Trx7SalesTab;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mahesh.James
 */
public class Transaction7 extends AbstractTransaction {

    private Trx7SalesTab trx7SalesTab;
    private Trx7RatesTab trx7RatesTab;

    private Trx7SelectionDTO trx7SelectionDTO;

    private Trx7AdjustmentSummary distributionSummary;
    private Trx7AdjustmentDetail distributionDetails;
    public static final Logger TXN7_LOGGER=  LoggerFactory.getLogger(Transaction7.class);

    public Transaction7(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.SEVEN, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            trx7SelectionDTO = new Trx7SelectionDTO();
            trx7SelectionDTO.setDataSelectionDTO(getDataselectionDTO());
            trx7SelectionDTO.setSessionDTO(getSessionDTO());
            trx7SelectionDTO.setProjectionMasterSid(getDataselectionDTO().getProjectionId());
            trx7SelectionDTO.setModuleName(ARMConstants.getTransaction7());

            trx7SalesTab = new Trx7SalesTab(getDataselectionDTO(), trx7SelectionDTO);
            trx7RatesTab = new Trx7RatesTab(trx7SelectionDTO);
            distributionSummary = new Trx7AdjustmentSummary(trx7SelectionDTO);
            distributionDetails = new Trx7AdjustmentDetail(trx7SelectionDTO);
            Tab tab1 = getTabSheet().addTab(trx7SalesTab, "Sales");
            tab1.setDefaultFocusComponent(trx7SalesTab.getDefaultFocusComponent());
            Tab tab2 = getTabSheet().addTab(trx7RatesTab, "Rates");
            tab2.setDefaultFocusComponent(trx7RatesTab.getDefaultFocusComponent());
            Tab tab3 = getTabSheet().addTab(distributionSummary, "Adjustment Summary");
            tab3.setDefaultFocusComponent(distributionSummary.getDefaultFocusComponent());
            Tab tab4 = getTabSheet().addTab(distributionDetails, "Adjustment Detail");
            tab4.setDefaultFocusComponent(distributionDetails.getDefaultFocusComponent());
            Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
            tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
        } catch (Exception e) {
            TXN7_LOGGER.error("Error in initializeTabs", e);
        }
    }

    @Override
    public int getTransactionNo() {
        return NumericConstants.SEVEN;
    }

    @Override
    public boolean isGenerated() {
        return trx7SalesTab.isGenerated() && trx7RatesTab.isGenerated() && distributionSummary.isGenerated() && distributionDetails.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransaction7();
    }

    @Override
    public Trx7SelectionDTO getSelection() {
        return trx7SelectionDTO;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.savePipeAccrualSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
        return distributionSummary.saveAssets();
    }

    @Override
    public String getGtnQuery() {
        TXN7_LOGGER.debug("Transaction 7 Gtn Outbound Insert Query");
        return "Pipeline_Adjustment_details_Insert_GTN";
    }

    @Override
    public String getReserveQuery() {
        TXN7_LOGGER.debug("Transaction 7 Reserve Outbound Insert Query");
        return "Pipeline_Adjustment_details_Insert_Reserve";
    }

    @Override
    public String getTransactionName() {
        return "ARM_Txt_7";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        trx7SalesTab.configurePermission(userId, stplSecurity);
        trx7RatesTab.configurePermission(userId, stplSecurity);
        distributionSummary.configurePermission(userId, stplSecurity);
        distributionDetails.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_DISTRIBUTION_FEES_RATE";
    }

    @Override
    public String ratesInput() {
        return ",\n"
                + "  MAX(B.RATE) AS RATE";
    }

}
