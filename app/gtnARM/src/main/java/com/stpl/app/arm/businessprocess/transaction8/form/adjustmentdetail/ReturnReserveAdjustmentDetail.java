/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction8.form.adjustmentdetail;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.transaction8.logic.RRDetailsLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.ifs.util.constants.WorkflowMessages;
import java.util.Arrays;
import java.util.List;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class ReturnReserveAdjustmentDetail extends AbstractAdjustmentDetails {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReturnReserveAdjustmentDetail.class);

    public ReturnReserveAdjustmentDetail(AbstractSelectionDTO selectionDto) {
        super(new RRDetailsLogic(), selectionDto);
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        selection.setDetailLevel(level.getValue().toString());
        selection.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        selection.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        selection.setDetailamountFilter(!amtFilter.isEmpty() ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);
        selection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : null);
    }

    @Override
    protected void generateBtn() {
        try {
            setSelection();
            if (logic.generateButtonCheck(selection)) {
                super.generateBtn();
                tableLogic.loadSetData(Boolean.TRUE);
            } else if (isGenerateFlag()) {
                AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
            }
        } catch (Exception ex) {
            LOGGER.error("Error in generateBtn :" , ex);
        }
    }

    @Override
    protected void loadReserveAccount() {
        List<List> list = logic.getReserveAccountDetails(selection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        if (list != null && !list.isEmpty()) {
            CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(list.get(0).toArray(), list.get(0).size(), String[].class),
                    Arrays.copyOf(list.get(1).toArray(), list.get(1).size(), String[].class));
            CommonUtils.checkAllMenuBarItem(reserveMenuItem);
        }
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
        List list = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            if (list.contains(object.getMenuItem().getWindow())) {
                object.setChecked(Boolean.TRUE);
            }
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return level;
    }

    @Override
    protected void columnAlignmentChanges() {
        for (Object obj : resultsTable.getVisibleColumns()) {
            if (obj.toString().contains("deductionAmount") || obj.toString().contains("deductionRate")) {
                resultsTable.setColumnAlignment(obj, ExtCustomTable.Align.LEFT);
            }
        }
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        LOGGER.debug("inside configurePermission Method{}", userId + stplSecurity);

    }

}
