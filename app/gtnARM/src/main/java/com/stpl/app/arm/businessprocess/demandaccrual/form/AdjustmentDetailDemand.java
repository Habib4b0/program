/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.demandaccrual.logic.DADetailsLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
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
import org.apache.commons.beanutils.BeanUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentDetailDemand extends AbstractAdjustmentDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentDetailDemand.class);
    private boolean creditFlag;

    public AdjustmentDetailDemand(AbstractSelectionDTO selectionDto) {
        super(new DADetailsLogic(), selectionDto);
        init();
        configuresWorkFlow();
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
        creditFlag = logic.cerditDebitEqualCheck(selection);
    }

    @Override
    protected void generateBtn() {
        try {
            setSelection();
            if (logic.generateButtonCheck(selection) && !creditFlag) {
                super.generateBtn();
                tableLogic.loadSetData(Boolean.TRUE);
            } else if (creditFlag && isGenerateFlag() && !selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
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
        List<List> list = logic.getReserveAccountDetails(selection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(list.get(0).toArray(), list.get(0).size(), String[].class),
                Arrays.copyOf(list.get(1).toArray(), list.get(1).size(), String[].class));
        CommonUtils.checkMenuBarAllItem(reserveMenuItem);
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
                object.setChecked(true);
            }
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return level;
    }

    private void configureFieldOnViewMode() {
        reset.setEnabled(false);
    }

    @Override
    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if ("detailLevel".equals(String.valueOf(obj[0]))) {
                level.setValue(String.valueOf(obj[1]));
            } else if ("detailvariables".equals(String.valueOf(obj[0]))) {
                getDetailVariables(obj);
            } else if ("detailreserveAcount".equals(String.valueOf(obj[0]))) {
                getDetailReserveAccount(obj);
            } else if (VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                amountFilterItem.removeChildren();
                loadAmountFilter();
                CommonUtils.unCheckMenuBarItem(amountFilterItem);
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItemCaption(amountFilterItem, str3);
                }
                selection.setDetailamountFilter(Arrays.asList(str2));

            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error("Error in loadDetails :", ex);
                }

            }
        }

        String frequency = HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selection.getSummarydemandfrequency()));
        selection.setSummarydemandfrequency(frequency);
    }

    private void getDetailReserveAccount(Object[] obj) {
        String str1 = (String) obj[1];
        String[] str2 = str1.split(",");
        String str3 = null;
        for (String strings : str2) {
            str3 = strings;
        }
        CommonUtils.checkMenuBarItem(reserveMenuItem, str3);
        selection.setDetailreserveAcount(Arrays.asList(str2));
    }

    private void getDetailVariables(Object[] obj) {
        String str1 = (String) obj[1];
        String[] str2 = str1.split(",");
        String str3 = null;
        for (String strings : str2) {
            str3 = strings;
        }
        CommonUtils.checkMenuBarItem(customMenuItem, str3);
        selection.setDetailvariables(Arrays.asList(str2));
    }

    private void configuresWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            loadDetails();
            generateBtn();
            tableLogic.loadSetData(Boolean.TRUE);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldOnViewMode();
            }
        }
    }

    @Override
    protected void columnAlignmentChanges() {
        LOGGER.debug("Inside columnAlignmentChanges Method");
    }

    public boolean isDetailsGenerated() {
        return resultsContainer.size() > 0;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM;
        functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction2", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));

    }

    @Override
    public boolean equals(Object demandAdjDetobj) {
        return super.equals(demandAdjDetobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
