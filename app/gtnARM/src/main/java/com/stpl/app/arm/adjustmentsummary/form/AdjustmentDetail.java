/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.form;

import com.stpl.app.arm.adjustmentsummary.logic.ASDetailsLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.ifs.util.constants.WorkflowMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.asi.ui.custommenubar.CustomMenuBar;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentDetail extends AbstractAdjustmentDetails {

    AbstractSelectionDTO selectionDto;
    SessionDTO sessionDTO;

    public AdjustmentDetail(AbstractSelectionDTO selectionDto, SessionDTO sessionDTO) throws PortalException, SystemException {
        super(new ASDetailsLogic(), selectionDto);
        this.selectionDto = selectionDto;
        this.sessionDTO = sessionDTO;
        init();
        configureSecurity();

        /**
         * For adjustment summary they didn't insist about this change So making
         * Amount filter field visible false
         */
        amountFilter.setVisible(Boolean.FALSE);
        amountFilterLabel.setVisible(Boolean.FALSE);
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        selection.setDetailLevel(level.getValue().toString());
        selection.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        selection.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        selection.setStatus(selectionDto.getStatus());
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);
        selection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : null);
    }

    @Override
    protected void generateBtn() {
        setSelection();
        if (logic.generateButtonCheck(selection)) {
            super.generateBtn();
            tableLogic.loadSetData(Boolean.TRUE);
        } else if (isGenerateFlag()) {
            AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
        }
    }

    @Override
    protected void resetBtn() {
        super.resetBtn();
    }

    @Override
    protected void loadReserveAccount() {
        List<List> list = logic.getReserveAccountDetails(selection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        if (!list.isEmpty()) {
            CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(list.get(0).toArray(), list.get(0).size(), String[].class),
                    Arrays.copyOf(list.get(1).toArray(), list.get(1).size(), String[].class));
            CommonUtils.checkAllMenuBarItem(reserveMenuItem);
        }
    }

    @Override
    protected void loadVariable() {
        variableHeader = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? ARMUtils.getAdjustmentDemandSummaryReserveVariableCombobox() : ARMUtils.getAdjustmentDemandSummaryGtnVariableCombobox();
        variableValue = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? VariableConstants.getAdjustmentDemandSummaryReserveVariable() : VariableConstants.getAdjustmentDemandSummaryGtnVariable();
    }

    /**
     * This method is used for selecting the variables in the Variable menu bar
     */
    @Override
    protected void variableDefaultSelection() {
        List list = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandSummaryReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandSummaryGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            object.setChecked(Boolean.FALSE);
            if (list.contains(object.getMenuItem().getWindow())) {
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

    private void configureSecurity() throws PortalException, SystemException {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Adjustment Summary" + "," + "Landing screen");
        if (functionHM.get("reset") != null && !(functionHM.get("reset")).isFunctionFlag()) {
            getReset().setVisible(false);
        } else {
            getReset().setVisible(true);
        }
        if (functionHM.get("generate") != null && !(functionHM.get("generate")).isFunctionFlag()) {
            getGenerate().setVisible(false);
        } else {
            getGenerate().setVisible(true);
        }
        if (functionHM.get("export") != null && !(functionHM.get("export")).isFunctionFlag()) {
            getExport().setVisible(false);
        } else {
            getExport().setVisible(true);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
