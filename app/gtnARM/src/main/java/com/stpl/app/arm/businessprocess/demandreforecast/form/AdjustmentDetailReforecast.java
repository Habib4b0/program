
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandreforecast.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractAdjustmentDetails;
import com.stpl.app.arm.businessprocess.demandreforecast.logic.DRDetailsLogic;
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
import org.apache.commons.beanutils.BeanUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentDetailReforecast extends AbstractAdjustmentDetails {

    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentDetailReforecast.class);

    private boolean creditFlag;
    
    private final AbstractSelectionDTO reforecastSelection;

    public AdjustmentDetailReforecast(AbstractSelectionDTO selectionDto) {
        super(new DRDetailsLogic(), selectionDto);
        this.reforecastSelection = selectionDto;
        init();
    }

    /**
     * To set the values to the DTO This method will be called before generate
     */
    public void setSelection() {
        reforecastSelection.setDetailLevel(level.getValue().toString());
        reforecastSelection.setDetailvariables(Arrays.asList(variableValue));
        List<List> account = CommonUtils.getSelectedVariables(reserveMenuItem, Boolean.FALSE);
        reforecastSelection.setDetailreserveAcount(!account.isEmpty() ? account.get(0) : null);
        List<String> amtFilter = CommonUtils.getSelectedVariables(amountFilterItem);
        reforecastSelection.setDetailamountFilter(!amtFilter.isEmpty() ? amtFilter : null);
        List<List> selectedVariable = CommonUtils.getSelectedVariables(customMenuItem, Boolean.FALSE);

        reforecastSelection.setSavedetailvariables(!selectedVariable.isEmpty() ? selectedVariable.get(0) : new ArrayList());
        creditFlag = logic.cerditDebitEqualCheck(reforecastSelection);
    }

    @Override
    protected void generateBtn() {
        setSelection();
        if (logic.generateButtonCheck(reforecastSelection) && !creditFlag) {
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
        List<List> list = logic.getReserveAccountDetails(reforecastSelection, level.getValue().toString().equals(GlobalConstants.getReserveDetail()));
        CommonUtils.loadCustomMenu(reserveMenuItem, Arrays.copyOf(list.get(0).toArray(), list.get(0).size(), String[].class),
                Arrays.copyOf(list.get(1).toArray(), list.get(1).size(), String[].class));
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
        List reforecastList = Arrays.asList(level.getValue().toString().equals(GlobalConstants.getReserveDetail())
                ? VariableConstants.getAdjustmentDemandPipelineReserveVariableDefaultSelection()
                : VariableConstants.getAdjustmentDemandPipelineGtnVariableDefaultSelection());
        for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
            if (reforecastList.contains(object.getMenuItem().getWindow())) {
                object.setChecked(true);
            }
        }
    }

    @Override
    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(reforecastSelection.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if ("detailLevel".equals(String.valueOf(obj[0]))) {
                level.setValue(String.valueOf(obj[1]));
            } else if ("detailvariables".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
                reforecastSelection.setDetailvariables(Arrays.asList(str2));
            } else if ("detailreserveAcount".equals(String.valueOf(obj[0]))) {
                reserveMenuItem.removeChildren(); // Reserve account custom menubar variables are loaded wrongly. 
                loadReserveAccount(); // GAL-8014
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(reserveMenuItem, str3);
                }
                reforecastSelection.setDetailreserveAcount(Arrays.asList(str2));
            } else if (VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                amountFilterItem.removeChildren();
                loadAmountFilter();
                CommonUtils.unCheckMenuBarItem(amountFilterItem);
                String reforecaststr1 = (String) obj[1];
                String[] reforecaststr2 = reforecaststr1.split(",");
                String str3 = null;
                for (String strings : reforecaststr2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItemCaption(amountFilterItem, str3);
                }
                reforecastSelection.setDetailamountFilter(Arrays.asList(reforecaststr2));

            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(reforecastSelection, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error("Error in loadDetails :", ex);
                }

            }
        }

    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return null;
    }

    @Override
    protected void columnAlignmentChanges() {
        LOGGER.debug("inside columnAlignmentChanges Method");
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction5", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));

    }
}
