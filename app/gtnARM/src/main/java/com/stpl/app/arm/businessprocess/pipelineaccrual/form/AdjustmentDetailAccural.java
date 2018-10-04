/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.PADetailsLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.ifs.util.constants.WorkflowMessages;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentDetailAccural extends AbstractAdjustmentDetails {

    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentDetailAccural.class);
    private boolean creditFlag;

    public AdjustmentDetailAccural(AbstractSelectionDTO selectionDto) {
        super(new PADetailsLogic(), selectionDto);
        init();

    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        selection.setDetailLevel(level.getValue().toString());
        selection.setDetailvariables(Arrays.asList(variableValue));
        List<List> paAccount = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        selection.setDetailreserveAcount(!paAccount.isEmpty() ? paAccount.get(0) : null);
        List<String> paAmtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        selection.setDetailamountFilter(!paAmtFilter.isEmpty() ? paAmtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);

        selection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : null);
        creditFlag = logic.cerditDebitEqualCheck(selection);
    }

    @Override
    protected void generateBtn() {
        try {
            setSelection();
            boolean paCondition1 = logic.generateButtonCheck(selection) && !creditFlag;
            boolean paCondition2 = creditFlag && isGenerateFlag();
            if (paCondition1) {
                super.generateBtn();
                tableLogic.loadSetData(Boolean.TRUE);
            } else if (paCondition2) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageMsgHeader003(), ARMMessages.getGenerateMessageMsgId006());
            } else if (isGenerateFlag()) {
                AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
            }
        } catch (Exception ex) {
            LOGGER.error("Error in generateBtn :", ex);
        }
    }

    @Override
    protected void loadReserveAccount() {
        List<List> paList = logic.getReserveAccountDetails(selection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(paList.get(0).toArray(), paList.get(0).size(), String[].class),
                Arrays.copyOf(paList.get(1).toArray(), paList.get(1).size(), String[].class));
        CommonUtils.checkAllMenuBarItem(reserveMenuItem);
    }

    @Override
    protected void loadVariable() {
        variableHeader = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? ARMUtils.getAdjustmentDemandPipelineReserveVariableCombobox() : ARMUtils.getAdjustmentDemandPipelineGtnVariableCombobox();
        variableValue = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? VariableConstants.getAdjustmentDemandPipelineReserveVariable() : VariableConstants.getAdjustmentDemandPipelineGtnVariable();
    }

    /**
     * This method is used for selecting the variables in the Variable menu bar
     */
    @Override
    protected void variableDefaultSelection() {
        List paList = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            if (paList.contains(object.getMenuItem().getWindow())) {
                object.setChecked(true);
            }
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return level;
    }

    @Override
    protected void columnAlignmentChanges() {
        for (Object paobj : resultsTable.getVisibleColumns()) {
            if (paobj.toString().contains("deductionAmount") || paobj.toString().contains("deductionRate")) {
                resultsTable.setColumnAlignment(paobj, ExtCustomTable.Align.LEFT);
            }
        }
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> paFunctionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction1", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", paFunctionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", paFunctionHM));

    }

    @Override
    public boolean equals(Object padAdjDetobj) {
        return super.equals(padAdjDetobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
