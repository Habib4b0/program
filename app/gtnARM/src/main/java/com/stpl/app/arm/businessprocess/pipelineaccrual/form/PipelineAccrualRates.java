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
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import java.util.Map;

/**
 *
 * @author porchelvi
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
    public boolean checkLeave() {
        return true;
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return deductionLevelDdlb;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Rates");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        ratesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        ratesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));

    }

    @Override
    public void configureFields() {
        customMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValueDdlb, selection.getDataSelectionDTO().getProjectionId());
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateBasisDdlb, CommonConstant.ARM_RATE_BASIS, false);
        rateBasisDdlb.removeItem(helperId.getIdByDesc(CommonConstant.ARM_RATE_BASIS, "Contract Terms"));
        rateBasisDdlb.removeItem(helperId.getIdByDesc(CommonConstant.ARM_RATE_BASIS, "Contract Details"));
        rateBasisDdlb.removeItem(helperId.getIdByDesc(CommonConstant.ARM_RATE_BASIS, "Calculated"));
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateFrequencyDdlb, "PAYMENT_FREQUENCY", false);
        setDefaultValue();
    }

    @Override
    public AbstractBPLogic getRatelogicObject() {
        return new PipelineAccrualRateLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getRatelogicObject();
    }

}
