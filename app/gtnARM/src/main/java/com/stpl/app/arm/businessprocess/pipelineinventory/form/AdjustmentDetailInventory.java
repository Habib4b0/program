/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PIDetailsLogic;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentDetailInventory extends AbstractAdjustmentDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentDetailInventory.class);
    private boolean creditFlag;
    private AbstractSelectionDTO inventorySelection;

    public AdjustmentDetailInventory(AbstractSelectionDTO selectionDto) {
        super(new PIDetailsLogic(), selectionDto);
        this.inventorySelection = selectionDto;
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        inventorySelection.setDetailLevel(level.getValue().toString());
        inventorySelection.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        inventorySelection.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        inventorySelection.setDetailamountFilter(!amtFilter.isEmpty() ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);

        inventorySelection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : null);
        creditFlag = logic.cerditDebitEqualCheck(inventorySelection);
    }

    @Override
    protected void generateBtn() {
        setSelection();
        if (logic.generateButtonCheck(inventorySelection) && !creditFlag) {
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
        List<List> inventoryList = logic.getReserveAccountDetails(inventorySelection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(inventoryList.get(0).toArray(), inventoryList.get(0).size(), String[].class),
                Arrays.copyOf(inventoryList.get(1).toArray(), inventoryList.get(1).size(), String[].class));
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
        List inventoryList = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem menuItem : customMenuItem.getChildren()) {
            if (inventoryList.contains(menuItem.getMenuItem().getWindow())) {
                menuItem.setChecked(true);
            }
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return level;
    }

    @Override
    protected void columnAlignmentChanges() {
        LOGGER.debug("Inside columnAlignmentChanges  Method");

    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction3", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));

    }

    @Override
    public boolean equals(Object invAdjDetobj) {
        return super.equals(invAdjDetobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
