/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction_7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineRates;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractRatesSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractBPLogic;
import com.stpl.app.arm.businessprocess.transaction_7.logic.Trx7PipelineAccrualRateLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author porchelvi.g
 */
public class Trx7RatesTab extends AbstractPipelineRates {

    public Trx7RatesTab(AbstractSelectionDTO selection) {
        super(selection);

    }

    @Override
    public boolean saveAssets() {
        return true;
    }

    @Override
    public AbstractRatesSearchResults getResultsObject(AbstractBPLogic logic, AbstractSelectionDTO selectionDto) {
        selectionDto.setModuleName(ARMConstants.getTransaction7());
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);

        if (isView) {
            selectionDto.setTableName("ARM_DISTRIBUTION_FEES_RATE");
        } else {
            selectionDto.setTableName(selectionDto.getSessionDTO().getCurrentTableNames().get("ST_ARM_DISTRIBUTION_FEES_RATE"));
        }

        return new Trx7AccrualRatesSearchResults(logic, selectionDto);
    }

    @Override
    public AbstractBPLogic getRatelogicObject() {
        return new Trx7PipelineAccrualRateLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getRatelogicObject();
    }

    @Override
    public void configureFields() {
        customMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValueDdlb, selection.getDataSelectionDTO().getProjectionId());

        rateFrequencyDdlbLabel.setVisible(false);
        rateFrequencyDdlb.setVisible(false);
        ratePeriodDdlbLabel.setVisible(false);
        ratePeriodDdlb.setVisible(false);
        rateBasisDdlb.addItem("Contract Details");
        rateBasisDdlb.select("Contract Details");
        for (Object item : deductionLevelDdlb.getItemIds()) {
            if ("Deduction".equals(deductionLevelDdlb.getItemCaption(item))) {
                deductionLevelDdlb.select(item);
                break;
            }

        }
        rateBasisDdlb.setEnabled(false);
        deductionLevelDdlb.setEnabled(false);
    }

    @Override
    protected void setSelection() {
        selection.setRate_DeductionLevel((Integer) deductionLevelDdlb.getValue());
        selection.setRate_DeductionLevelName(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()));
        selection.setRate_RateColumnList(CommonUtils.getSelectedVariables(customMenuItem, true));
        List<String> listSize = new ArrayList(selection.getRate_ColumnList().get(0));
        String deductionValues = StringUtils.EMPTY;
        if (!listSize.isEmpty()) {
            for (int i = 0; i < listSize.size(); i++) {
                String value = listSize.get(i);
                if (value.contains(".")) {
                    value = value.substring(0, value.lastIndexOf("."));
                }
                listSize.set(i, value.replace(" ", StringUtils.EMPTY).trim());
                if (i != listSize.size() - 1) {
                    deductionValues += "'" + value + "',";
                } else {
                    deductionValues += "'" + value + "'";
                }
            }
        }
        selection.setRate_DeductionValue(deductionValues);

        selection.setRate_BasisName("Contract Details");

    }

    @Override
    public void setDefaultValue() {
        CommonUtils.unCheckMenuBarItem(customMenuItem);
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction7", "Rates");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        ratesResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        ratesResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
    }

}
