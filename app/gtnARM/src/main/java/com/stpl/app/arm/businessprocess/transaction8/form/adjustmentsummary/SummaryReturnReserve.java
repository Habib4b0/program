/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.adjustmentsummary;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummary;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractPipelineSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.transaction8.dto.RRSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.navigator.ViewChangeListener;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Sathya.Seelan
 */
public class SummaryReturnReserve extends AbstractPipelineSummary {

    public SummaryReturnReserve(RRSelectionDTO selection) {
        super(new RRSummaryLogic(), selection);
        init();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        logger.debug("Inside Enter method of SummaryReturnReserve Class");

    }

    @Override
    protected AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        return new SummaryReturnReserveResults((RRSummaryLogic) logic, (RRSelectionDTO) selectionDto);
    }

    @Override
    protected void configureSummary() {
        variableHeader = ARMUtils.getReturnReserveSummaryHeaders();
        variableHeaderDeduction = ARMUtils.getReturnReserveSummaryDeductionHeaders();
        variableVisibleColumnsDeduction = ARMUtils.getReturnReserveSummaryDeductionColumns();
        variableVisibleColumns = ARMUtils.getReturnReserveSummaryColumns();
    }

    @Override
    public void configureWorkFlow() {
        if (selectionDto.getSessionDTO().isWorkFlow()) {
            loadDetails();
            generateButtonClick(null);
            if (ARMUtils.VIEW_SMALL.equals(selectionDto.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
        }
    }

    @Override
    public void loadDetails() {
        StringBuilder variablesBuilder = new StringBuilder();
        variablesBuilder.append(VariableConstants.SUMMARY_DEDUCTION_VALUES_FIELD).append(ARMUtils.COMMA_CHAR)
                .append(VariableConstants.SUMMARY_VARIABLES_FIELD).append(ARMUtils.COMMA_CHAR)
                .append(VariableConstants.SUMMARY_DEDUCTION_LEVEL_FIELD).append(ARMUtils.COMMA_CHAR)
                .append(VariableConstants.SUMMARY_GL_DATE_FIELD);
        List<Object[]> list = CommonLogic.loadReturnReserve(selectionDto.getProjectionMasterSid(), variablesBuilder.toString());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (VariableConstants.SUMMARY_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                deductionLevelDdlb.setValue(selectionDto.getSummarydeductionLevel());
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(deductionCustomMenuItem, str3);
                }
            } else if (VariableConstants.SUMMARY_VARIABLES.equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }

            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(selectionDto, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    logger.error("Error in loadDetails :", ex);

                }

            }
        }

    }

    @Override
    public void defaultFocus() {
        deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc("DEDUCTION_LEVELS", "Deduction"));
    }

}
