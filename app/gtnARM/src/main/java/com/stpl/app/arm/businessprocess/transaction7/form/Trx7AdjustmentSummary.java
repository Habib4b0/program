/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.transaction7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7PASummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class Trx7AdjustmentSummary extends AbstractPipelineSummary {

    /**
     *
     * @param selectionDto
     */
    public Trx7AdjustmentSummary(AbstractSelectionDTO selectionDto) {
        super(new Trx7PASummaryLogic(), selectionDto);
        init();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.debug("Inside Enter Method Of Trx7AdjustmentSummary Class");

    }

    @Override
    protected AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        return new Trx7AdjustmentSummarySearchResultsPipelineAccrual((Trx7PASummaryLogic) logic, (Trx7SelectionDTO) selectionDto);
    }

    @Override
    protected void configureSummary() {
        variableHeader = new String[]{
            VariableConstants.Trx7SummaryVariables.CURRENTBALANCE.toString(),
            VariableConstants.Trx7SummaryVariables.CALCULATEDADJUSTMENT.toString(),
            VariableConstants.Trx7SummaryVariables.ADJUSTEMNTRATIO.toString(),
            VariableConstants.Trx7SummaryVariables.VARIANCE.toString()
        };
        variableHeaderDeduction = VariableConstants.Trx7SummaryVariables.names();
        variableVisibleColumnsDeduction = VariableConstants.getVariableVisibleColumnTrx7Deduction();
        variableVisibleColumns = VariableConstants.getVariableVisibleColumnTrx7();
    }

    @Override
    protected void loadVariablesDdlb() {

        CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);

    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction7", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        summaryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        summaryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        summaryResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        summaryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));
    }

}
