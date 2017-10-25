/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6_SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6_SummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Map;

/**
 *
 * @author Srithar
 */
public class Trx6_AdjustmentSummary extends AbstractPipelineSummary {

    public Trx6_AdjustmentSummary(Trx6_SelectionDTO selectionDto) {
        super(new Trx6_SummaryLogic(), selectionDto);
        selectionDto.setTotalFlag(true);
        init();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    protected AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        return new Trx6_AdjustmentSummarySearchResults((Trx6_SummaryLogic) logic, (Trx6_SelectionDTO) selectionDto);
    }

    @Override
    protected void configureSummary() {
        variableVisibleColumns = ARMUtils.VARIABLE_VISIBLE_COLUMN_TRX6;
        variableHeader = new String[]{ARMUtils.Trx6_Inventory_Variables.CURRENT_BALANCE.getHeader(),
            ARMUtils.Trx6_Inventory_Variables.CALCULATED_ADJUSTMENT.getHeader(),
            ARMUtils.Trx6_Inventory_Variables.ADJUSTMENT_RATIO.getHeader(),
            ARMUtils.Trx6_Inventory_Variables.VARIANCE.getHeader()
        };
        variableHeader_deduction = ARMUtils.VARIABLE_SUMMARY_HEADER;
        variableVisibleColumns_deduction = ARMUtils.VARIABLE_VISIBLE_COLUMN_TRX6_DECUTION;

    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction6", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        summaryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        summaryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        summaryResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        summaryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));
    }

}
