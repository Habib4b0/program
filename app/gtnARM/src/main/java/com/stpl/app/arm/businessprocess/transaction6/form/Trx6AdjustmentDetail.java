/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6DetailsLogic;
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

/**
 *
 * @author Srithar
 *
 */
public class Trx6AdjustmentDetail extends AbstractAdjustmentDetails {

    private boolean creditFlag;
    private boolean tr6GenerateFlag;
    private AbstractSelectionDTO tr6SelectionDto;

    public Trx6AdjustmentDetail(AbstractSelectionDTO selectionDto) {
        super(new Trx6DetailsLogic(), selectionDto);
        this.tr6SelectionDto = selectionDto;
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        tr6SelectionDto.setDetailLevel(level.getValue().toString());
        tr6SelectionDto.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        tr6SelectionDto.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        tr6SelectionDto.setDetailamountFilter(!amtFilter.isEmpty() ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);

        tr6SelectionDto.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : null);
        if (account.get(2).isEmpty() || amtFilter.isEmpty() || tr6SelectionDto.getSavedetailvariables().isEmpty()) {
            tr6GenerateFlag = true;
        } else {
            tr6GenerateFlag = false;
        }
        creditFlag = logic.cerditDebitEqualCheck(tr6SelectionDto);
    }

    @Override
    protected void generateBtn() {
        setSelection();
        if (tr6GenerateFlag) {
            AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
        } else if (logic.generateButtonCheck(tr6SelectionDto) && !creditFlag) {
            super.generateBtn();
            tableLogic.loadSetData(Boolean.TRUE);
        } else if (creditFlag && isGenerateFlag()) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageMsgHeader003(), ARMMessages.getGenerateMessageMsgId006());
        }
    }

    @Override
    protected void loadReserveAccount() {
        List<List> tr6ReserveList = logic.getReserveAccountDetails(tr6SelectionDto, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(tr6ReserveList.get(0).toArray(), tr6ReserveList.get(0).size(), String[].class),
                Arrays.copyOf(tr6ReserveList.get(1).toArray(), tr6ReserveList.get(1).size(), String[].class));
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
        List tr6DefaultList = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            if (tr6DefaultList.contains(object.getMenuItem().getWindow())) {
                object.setChecked(true);
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
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction6", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));

    }

}
