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
import org.jboss.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class AdjustmentDetailDemand extends AbstractAdjustmentDetails {

    private static final Logger LOGGER = Logger.getLogger(AdjustmentDetailDemand.class);

    public AdjustmentDetailDemand(AbstractSelectionDTO selectionDto) {
        super(new DADetailsLogic(), selectionDto);
        init();
        configureWorkFlow();
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
        try {
            setSelection();
            if (logic.generateButtonCheck(selection)) {
                super.generateBtn();
                tableLogic.loadSetData(Boolean.TRUE);
            } else if (isGenerateFlag()) {
                AbstractNotificationUtils.getErrorNotification(WorkflowMessages.getCW_SubmitMandoryValidationHeader(), ARMMessages.getGenerateMessageMsgId_004());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        CommonUtils.checkMenuBarAllItem(reserveMenuItem);
    }

    @Override
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
        return level;
    }

    private void configureFieldsOnViewMode() {
        reset.setEnabled(false);
    }

    public void loadDetails() {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selection.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if ("detail_Level".equals(String.valueOf(obj[0]))) {
                level.setValue(String.valueOf(obj[1]));
            } else if ("detail_variables".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                }
                CommonUtils.checkMenuBarItem(customMenuItem, str3);
                selection.setDetail_variables(Arrays.asList(str2));
            } else if ("detail_reserveAcount".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                }
                CommonUtils.checkMenuBarItem(reserveMenuItem, str3);
                selection.setDetail_reserveAcount(Arrays.asList(str2));
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
                selection.setDetail_amountFilter(Arrays.asList(str2));

            } else if (!"sales_variables".equals(String.valueOf(obj[0])) && !"summary_deductionValues".equals(String.valueOf(obj[0]))
                    && !"summary_variables".equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selection, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }

            }
        }

        String frequency = HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selection.getSummary_demand_frequency()));
        selection.setSummary_demand_frequency(frequency);
    }

    private void configureWorkFlow() {
        if (selection.getSessionDTO().isWorkFlow()) {
            loadDetails();
            generateBtn();
            tableLogic.loadSetData(Boolean.TRUE);
            if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
        }
    }

    @Override
    protected void columnAlignmentChanges() {
    }

    public boolean isDetailsGenerated() {
        return resultsContainer.size() > 0;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction2", "Adjustment Details");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
    }
}
