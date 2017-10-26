/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6_DetailsLogic;
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
public class Trx6_AdjustmentDetail extends AbstractAdjustmentDetails {

    public Trx6_AdjustmentDetail(AbstractSelectionDTO selectionDto) {
        super(new Trx6_DetailsLogic(), selectionDto);
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        selection.setDetail_Level(level.getValue().toString());
        selection.setDetail_variables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        selection.setDetail_reserveAcount(account.size() > 0 ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        selection.setDetail_amountFilter(amtFilter.size() > 0 ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);
        selection.setSave_detail_variables(selectedVariable.size() > 0 ? selectedVariable.get(0) : null);
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
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(list.get(0).toArray(), list.get(0).size(), String[].class),
                Arrays.copyOf(list.get(1).toArray(), list.get(1).size(), String[].class));
        CommonUtils.CheckAllMenuBarItem(reserveMenuItem);
    }

    protected void loadVariable() {
        variableHeader = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? ARMUtils.ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_COMBOBOX : ARMUtils.ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_COMBOBOX;
        variableValue = level.getValue().toString().equals(GlobalConstants.getReserveDetail()) ? VariableConstants.ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE : VariableConstants.ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE;
    }

    /**
     * This method is used for selecting the variables in the Variable menu bar
     */
    @Override
    protected void variableDefaultSelection() {
        List list = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_DEFAULT_SELECTION
                : VariableConstants.ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_DEFAULT_SELECTION);
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
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
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction6", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
    }

}
