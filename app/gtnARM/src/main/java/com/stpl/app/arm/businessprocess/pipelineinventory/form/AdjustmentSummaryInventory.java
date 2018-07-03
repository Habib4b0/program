/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.PipelineInventorySelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PISummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.navigator.ViewChangeListener;
import java.util.Map;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentSummaryInventory extends AbstractPipelineSummary {

    public AdjustmentSummaryInventory(PipelineInventorySelectionDTO selectionDto) {
        super(new PISummaryLogic(), selectionDto);
        init();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.debug("Inside Enter Method Of AdjustmentSummaryInventory Class");

    }

    @Override
    protected AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        return new AdjustmentSummarySearchResultsInventory((PISummaryLogic) logic, (PipelineInventorySelectionDTO) selectionDto);
    }

    @Override
    protected void configureSummary() {
        variableVisibleColumns = VariableConstants.getVariableVisibleColumnPi();
        variableHeader = new String[]{VariableConstants.PISummaryVariables.CPIPELINEACCRUAL.toString(),
            VariableConstants.PISummaryVariables.PPIPELINEACCRUAL.toString(),
            VariableConstants.PISummaryVariables.PIPELINERATIO.toString(),
            VariableConstants.PISummaryVariables.VARIANCE.toString()
//            VariableConstants.PISummaryVariables.Adjustment.toString()
        };
        variableHeaderDeduction = VariableConstants.PISummaryDeductionVariables.names();
        variableVisibleColumnsDeduction = VariableConstants.getVariableVisibleColumnDedutionPi();

    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction3", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        summaryResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        summaryResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        summaryResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        summaryResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }

}
