/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.PipelineAccrualSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PASummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class AdjustmentSummaryPipelineAccrual extends AbstractPipelineSummary {

    /**
     *
     * @param selectionDto
     */
    public AdjustmentSummaryPipelineAccrual(AbstractSelectionDTO selectionDto) {
        super(new PASummaryLogic(), selectionDto);
        init();

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.debug("Inside Enter Method Of AdjustmentSummaryPipelineAccrual Class");

    }

    @Override
    protected AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        return new AdjustmentSummarySearchResultsPipelineAccrual((PASummaryLogic) logic, (PipelineAccrualSelectionDTO) selectionDto);
    }

    @Override
    protected void configureSummary() {
        variableHeader = new String[]{VariableConstants.PipelineSummaryVariables.CPIPELINEACRUAL.toString(), VariableConstants.PipelineSummaryVariables.PPIPELINEACRUAL.toString(),
            VariableConstants.PipelineSummaryVariables.VARIANCE.toString()};
        variableHeaderDeduction = VariableConstants.PipelineSummaryVariables.names();
        variableVisibleColumnsDeduction = VariableConstants.getVariableVisibleColumnDedution();
        variableVisibleColumns = VariableConstants.getVariableVisibleColumn();
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        summaryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        summaryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        summaryResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        summaryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }

}
