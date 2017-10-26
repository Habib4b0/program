/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineRates;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PipelineAccrualRateLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.SalesVariables.ST_ARM_PIPELINE_RATE;
import com.stpl.app.security.permission.model.AppPermission;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class PipelineAccrualRates extends AbstractPipelineRates {

    public PipelineAccrualRates(AbstractSelectionDTO selection) {
        super(selection);

    }

    @Override
    public boolean saveAssets() {
        return true;
    }

    @Override
    public AbstractRatesSearchResults getResultsObject(AbstractBPLogic logic, AbstractSelectionDTO selectionDto) {
        selectionDto.setModuleName("Pipeline Accrual");
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            selectionDto.setTableName("ARM_PIPELINE_RATE");
        } else {
            selectionDto.setTableName(selectionDto.getSessionDTO().getCurrentTableNames().get(ST_ARM_PIPELINE_RATE.toString()));
        }
        return new AccrualRatesSearchResults(logic, selectionDto);
    }

    @Override
    public AbstractBPLogic getRatelogicObject() {
        return new PipelineAccrualRateLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getRatelogicObject();
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return deductionLevelDdlb;
    }

    @Override
    public boolean checkLeave() {
        return true;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Rates");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        ratesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        ratesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
    }

}
