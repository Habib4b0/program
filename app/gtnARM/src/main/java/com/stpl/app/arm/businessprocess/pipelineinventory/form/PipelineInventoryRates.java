/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineRates;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryRatelogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import java.util.Map;

/**
 *
 * @author porchelvi.g
 */
public class PipelineInventoryRates extends AbstractPipelineRates {

    public PipelineInventoryRates(AbstractSelectionDTO selection) {
        super(selection);

    }

    @Override
    public boolean saveAssets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractRatesSearchResults getResultsObject(AbstractBPLogic logic, AbstractSelectionDTO selectionDto) {
        selectionDto.setModuleName("Pipeline Inventory");
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            selectionDto.setTableName("ARM_INVENTORY_RATE");
        } else {
            selectionDto.setTableName(selectionDto.getSessionDTO().getCurrentTableNames().get("ST_ARM_INVENTORY_RATE"));
        }
        return new InventoryRatesSearchResults(logic, selectionDto);
    }

    @Override
    public AbstractBPLogic getRatelogicObject() {
        return new PipelineInventoryRatelogic();
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
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction3", "Rates");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        ratesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        ratesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
    }
    
    @Override
    public void configureFields() {
        customMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValueDdlb, selection.getDataSelectionDTO().getProjectionId());
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateBasisDdlb, "ARM_RATE_BASIS", false);
        rateBasisDdlb.removeItem(helperId.getIdByDesc(CommonConstant.ARM_RATE_BASIS, "Contract Details"));
        rateBasisDdlb.removeItem(helperId.getIdByDesc(CommonConstant.ARM_RATE_BASIS, "Calculated"));
        CommonUtils.loadComboBoxWithIntegerForComboBox(rateFrequencyDdlb, "PAYMENT_FREQUENCY", false);
        setDefaultValue();

    }
}
