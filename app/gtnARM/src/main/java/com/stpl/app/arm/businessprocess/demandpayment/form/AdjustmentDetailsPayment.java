/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandpayment.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.demandpayment.logic.DPDetailsLogic;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.asi.ui.custommenubar.CustomMenuBar;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentDetailsPayment extends AbstractAdjustmentDetails {

    private boolean creditFlag;
    private AbstractSelectionDTO paymentsSelection;

    public AdjustmentDetailsPayment(AbstractSelectionDTO selectionDto) {
        super(new DPDetailsLogic(), selectionDto);
        this.paymentsSelection = selectionDto;
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        paymentsSelection.setDetailLevel(level.getValue().toString());
        paymentsSelection.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        paymentsSelection.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        paymentsSelection.setDetailamountFilter(!amtFilter.isEmpty() ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);

        paymentsSelection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) :  new ArrayList());
        creditFlag = logic.cerditDebitEqualCheck(paymentsSelection);
    }

    @Override
    protected void generateBtn() {
        setSelection();
        if (logic.generateButtonCheck(paymentsSelection) && !creditFlag) {
            super.generateBtn();
            tableLogic.loadSetData(Boolean.TRUE);
        } else if (creditFlag && isGenerateFlag()) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageMsgHeader003(), ARMMessages.getGenerateMessageMsgId006());
        } else if (isGenerateFlag()) {
            AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
        }
    }

    @Override
    protected void loadReserveAccount() {
        List<List> paymentsList = logic.getReserveAccountDetails(paymentsSelection, GlobalConstants.getReserveDetail().equals(level.getValue().toString()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(paymentsList.get(0).toArray(), paymentsList.get(0).size(), String[].class),
                Arrays.copyOf(paymentsList.get(1).toArray(), paymentsList.get(1).size(), String[].class));
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
        List paymentsList = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            if (paymentsList.contains(object.getMenuItem().getWindow())) {
                object.setChecked(Boolean.TRUE);
            }
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return null;
    }

    @Override
    protected void columnAlignmentChanges() {
        return;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> paymentsFunctionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction4", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", paymentsFunctionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", paymentsFunctionHM));

    }

}
