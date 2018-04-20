package com.stpl.app.arm.businessprocess;

import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.PipelineAccrualSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.AdjustmentDetailAccural;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.AdjustmentSummaryPipelineAccrual;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.PipelineAccrualRates;
import com.stpl.app.arm.businessprocess.pipelineaccrual.form.Sales;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Abhiram
 */
public class Transaction1 extends AbstractTransaction {

    private PipelineAccrualSelectionDTO paSelectionDto;
    private Sales sales;
    private PipelineAccrualRates rates;
    private AdjustmentSummaryPipelineAccrual summary;
    private AdjustmentDetailAccural details;
    public static final Logger LOGGER = LoggerFactory.getLogger(Transaction1.class);

    public Transaction1(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, 1, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        try {
            paSelectionDto = new PipelineAccrualSelectionDTO();
            paSelectionDto.setDataSelectionDTO(getDataselectionDTO());
            paSelectionDto.setSessionDTO(getSessionDTO());
            paSelectionDto.setProjectionMasterSid(getDataselectionDTO().getProjectionId());
            paSelectionDto.setModuleName(ARMConstants.getPipelineAccrual());
            sales = new Sales(getDataselectionDTO(), paSelectionDto);
            rates = new PipelineAccrualRates(paSelectionDto);
            summary = new AdjustmentSummaryPipelineAccrual(paSelectionDto);
            details = new AdjustmentDetailAccural(paSelectionDto);
            Tab tab1 = getTabSheet().addTab(sales, "Sales");
            tab1.setDefaultFocusComponent(sales.getDefaultFocusComponent());
            Tab tab2 = getTabSheet().addTab(rates, "Rates");
            tab2.setDefaultFocusComponent(rates.getDefaultFocusComponent());
            Tab tab3 = getTabSheet().addTab(summary, "Adjustment Summary");
            tab3.setDefaultFocusComponent(summary.getDefaultFocusComponent());
            Tab tab4 = getTabSheet().addTab(details, "Adjustment Detail");
            tab4.setDefaultFocusComponent(details.getDefaultFocusComponent());
            Tab tab5 = getTabSheet().addTab(getNotes(), "Additional Information");
            tab5.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
        } catch (Exception e) {
            LOGGER.error("Error in initializeTabs" + e);
        }
    }

    @Override
    public int getTransactionNo() {
        return 1;
    }

    @Override
    public boolean isGenerated() {
        return sales.isGenerated() && rates.isGenerated() && summary.isGenerated() && details.isGenerated();
    }

    @Override
    public String submitErrorMessage() {
        return ARMMessages.getSubmitErrorTransaction1();
    }

    @Override
    public PipelineAccrualSelectionDTO getSelection() {
        return paSelectionDto;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.savePipeAccrualSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
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
        return "ARM_Txt_1";
    }

    @Override
    public void configurePermission() {
        String userId = String.valueOf(getSessionDTO().getUserId());
        sales.configurePermission(userId, stplSecurity);
        rates.configurePermission(userId, stplSecurity);
        summary.configurePermission(userId, stplSecurity);
        details.configurePermission(userId, stplSecurity);
    }

    @Override
    public String getTableName() {
        return "ARM_PIPELINE_RATE";
    }

    @Override
    public String ratesInput() {
        return ",\n"
                + "  MAX(B.RATE) AS RATE";
    }

}
