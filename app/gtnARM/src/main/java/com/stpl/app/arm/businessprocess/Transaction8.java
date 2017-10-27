/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess;

import com.stpl.app.arm.businessprocess.transaction8.dto.RRSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction8.form.adjustmentsummary.SummaryReturnReserve;
import com.stpl.app.arm.businessprocess.transaction8.form.adjustmentdetail.ReturnReserveAdjustmentDetail;
import com.stpl.app.arm.businessprocess.transaction8.form.rates.RatesReturnsReserve;
import com.stpl.app.arm.businessprocess.transaction8.form.returnsdata.ReturnsData;
import com.stpl.app.arm.businessprocess.transaction8.form.returnreservedata.ReturnsReserveData;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.supercode.AbstractTransaction;
import com.stpl.ifs.ui.CustomFieldGroup;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import org.drools.core.util.StringUtils;

/**
 *
 * @author
 */
public class Transaction8 extends AbstractTransaction {

    ReturnsData returnsData;
    ReturnsReserveData returnReserveData;
    RatesReturnsReserve rates;
    RRSummaryLogic logic;
    RRSelectionDTO selection;
    SummaryReturnReserve summary;
    ReturnReserveAdjustmentDetail details;

    public Transaction8(TabSheet tabSheet, CustomFieldGroup binder, String name, DataSelectionDTO dataselectionDTO, SessionDTO sessionDTO) throws SystemException {
        super(tabSheet, binder, NumericConstants.EIGHT, name, dataselectionDTO, sessionDTO);
    }

    @Override
    public int getTransactionNo() {
        return NumericConstants.EIGHT;
    }

    @Override
    public String getTransactionName() {
        return "ARM_Txt_8";
    }

    @Override
    public void configurePermission() throws Exception {
        LOGGER.debug("Inside the Configurepermission Method");

    }

    @Override
    public boolean isGenerated() {
        return rates.isGenerated() && returnReserveData.isGenerated() && summary.isGenerated() && details.isGenerated();
    }

    @Override
    public void initializeTabs() {
        getTabSheet().addTab(getDataSelectionWF(), ARMConstants.getDataSelection());
        selection = new RRSelectionDTO();
        selection.setDataSelectionDTO(getDataselectionDTO());
        selection.setSessionDTO(getSessionDTO());
        selection.setProjectionMasterSid(getDataselectionDTO().getProjectionId());
        selection.setModuleName(ARMConstants.getTransaction8());
        returnsData = new ReturnsData(selection);
        rates = new RatesReturnsReserve(selection);
        returnReserveData = new ReturnsReserveData(selection);
        summary = new SummaryReturnReserve(selection);
        details = new ReturnReserveAdjustmentDetail(selection);
        Tab tab1 =  getTabSheet().addTab(returnsData, "Returns Data");
        tab1.setDefaultFocusComponent(returnsData.getDefaultFocusComponent());
        Tab tab2 = getTabSheet().addTab(rates, "Rates");
        tab2.setDefaultFocusComponent(rates.getDefaultFocusComponent());
        Tab tab3 = getTabSheet().addTab(returnReserveData, "Return Reserve Data");
        tab3.setDefaultFocusComponent(returnReserveData.getDefaultFocusComponent());
        Tab tab4 =getTabSheet().addTab(summary, "Adjustment Summary");
        tab4.setDefaultFocusComponent(summary.getDefaultFocusComponent());
        Tab tab5 =getTabSheet().addTab(details, "Adjustment Detail");
        tab5.setDefaultFocusComponent(details.getDefaultFocusComponent());
        Tab tab6 =getTabSheet().addTab(getNotes(), "Additional Information");
        tab6.setDefaultFocusComponent(getNotes().getDefaultFocusComponent());
    }

    @Override
    public String submitErrorMessage() {
        return StringUtils.EMPTY;
    }

    @Override
    public RRSelectionDTO getSelection() {
        return selection;
    }

    @Override
    public boolean saveAssets() {
        CommonLogic.saveReturnReserveSelection(getDataselectionDTO().getProjectionId(), getName(), getSelection());
        return summary.saveAssets();
    }

    @Override
    public String getGtnQuery() {
        return StringUtils.EMPTY;
    }

    @Override
    public String getReserveQuery() {
        return "Pipeline_Adjustment_details_Insert_Reserve";
    }

    @Override
    public String getTableName() {
        return "ARM_RETURN_RATE";
    }
}
