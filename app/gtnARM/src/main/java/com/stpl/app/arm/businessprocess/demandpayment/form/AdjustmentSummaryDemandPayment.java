/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandpayment.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummarySelection;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.demandpayment.dto.DPSelectionDTO;
import com.stpl.app.arm.businessprocess.demandpayment.logic.DPSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.vaadin.navigator.ViewChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummaryDemandPayment extends AbstractDemandSummarySelection {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummaryDemandPayment.class);

    public AdjustmentSummaryDemandPayment(DPSelectionDTO selection) throws InvocationTargetException {
        super(selection, new DPSummaryLogic());
        configureWorkFlow();
    }

    @Override
    protected void configureSummary() {
        view.select(ARMConstants.getMultiplePeriod());
        selectionDTO.setSummarydemandview("Multiple Period");
        variableHeader = new String[]{VariableConstants.DemandPaymentSummaryVariables.DEMANDACCRUAL.toString(), VariableConstants.DemandPaymentSummaryVariables.DEMANDACCRUALREFORECAST.toString(),
            VariableConstants.DemandPaymentSummaryVariables.DEMANDACCRUALRECON.toString(),
            VariableConstants.DemandPaymentSummaryVariables.TOTALDEMANDACCRUAL.toString(), VariableConstants.DemandPaymentSummaryVariables.ACTUALPAYMENTS.toString(),
            VariableConstants.DemandPaymentSummaryVariables.PAYMENTRATIO.toString(), VariableConstants.DemandPaymentSummaryVariables.VARIANCE.toString()};
        variableHeaderDeduction = VariableConstants.DemandPaymentSummaryVariables.names();
        variableVisibleColumnsDeduction = VariableConstants.getVisibleColumnDemandPaymentDeduction();
        variableVisibleColumns = VariableConstants.getVisibleColumnDemandPayment();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method of AdjustmentSummaryDemandPayment Class");

    }

    @Override
    protected AbstractDemandSummaryResults getResults(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDTO) {
        return new AdjustmentSummarySearchResultsDemandPayment((DPSummaryLogic) logic, (DPSelectionDTO) selectionDTO);
    }

    @Override
    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return adjustmentResults.getResultBeanContainerVal();
    }

    @Override
    protected void customizeResetButtonLogic() {
        view.select("Multiple Period");
    }

    @Override
    protected String[] getTableColumns() {
        return VariableConstants.getVisibleColumnDemandPaymentDeduction();
    }

    public void configureWorkFlow() throws InvocationTargetException {
        if (selectionDTO.getSessionDTO().isWorkFlow()) {
            loadDetails();
            loadSelection();
            if (ARMUtils.VIEW_SMALL.equals(selectionDTO.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            generateButtonLogic();
        }
    }

    public void loadDetails() throws InvocationTargetException {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selectionDTO.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (VariableConstants.SUMMARY_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                deductionLevelDdlb.setValue(selectionDTO.getSummarydeductionLevel());
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(getDeductionCustomMenuItem(), str3);
                }
            } else if (VariableConstants.SUMMARY_VARIABLES.equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(selectionDTO, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error("Error in loadDetails :", ex);
                }

            }
        }
    }

    public void configureFieldsOnViewMode() {
        glImpactDate.setEnabled(false);
        reset.setEnabled(false);
    }

    private void loadSelection() {
        try {
            view.setValue(selectionDTO.getSummarydemandview());
            frequencyDdlb.select(Integer.valueOf(selectionDTO.getSummarydemandfrequency()));
            selectionDTO.setSummarydemandfrequency(String.valueOf(frequencyDdlb.getItemCaption(frequencyDdlb.getValue())));
            fromDate.setValue(selectionDTO.getSummarydemandfromDate());
            toDate.setValue(selectionDTO.getSummarydemandtoDate());
            LOGGER.debug("selectionDTO.getSummary_glDate()!!!!!!!!{}", selectionDTO.getSummaryglDate());
            glImpactDate.removeValueChangeListener(glListener);
            defaultWorkFlowDate = dateFormat.parse(selectionDTO.getSummaryglDate());
            resetWorkFlowDate = defaultWorkFlowDate;
            glImpactDate.setValue(resetWorkFlowDate);
            glImpactDate.addValueChangeListener(gldemandWorkflowListener);
        } catch (Exception ex) {
            LOGGER.error("Error in loadSelection :", ex);
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return frequencyDdlb;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction4", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        adjustmentResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        adjustmentResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        adjustmentResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        adjustmentResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }

}
