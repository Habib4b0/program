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
import org.jboss.logging.Logger;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummaryDemandPayment extends AbstractDemandSummarySelection {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(AdjustmentSummaryDemandPayment.class);

    public AdjustmentSummaryDemandPayment(DPSelectionDTO selection) throws InvocationTargetException {
        super(selection, new DPSummaryLogic());
        configureWorkFlow();
    }

    @Override
    protected void configureSummary() {
        view.select(ARMConstants.getMultiplePeriod());
        selectionDTO.setSummary_demand_view("Multiple Period");
        variableHeader = new String[]{VariableConstants.DemandPaymentSummaryVariables.DemandAccrual.toString(), VariableConstants.DemandPaymentSummaryVariables.DemandAccrualReforecast.toString(),
            VariableConstants.DemandPaymentSummaryVariables.DemandPaymentRecon.toString(),
            VariableConstants.DemandPaymentSummaryVariables.TotalDemandAccrual.toString(), VariableConstants.DemandPaymentSummaryVariables.ActualPayments.toString(),
            VariableConstants.DemandPaymentSummaryVariables.PaymentRatio.toString(), VariableConstants.DemandPaymentSummaryVariables.Variance.toString()};
        variableHeader_deduction = VariableConstants.DemandPaymentSummaryVariables.names();
        variableVisibleColumns_deduction = VariableConstants.VISIBLE_COLUMN_DEMAND_PAYMENT_DEDUCTION;
        variableVisibleColumns = VariableConstants.VISIBLE_COLUMN_DEMAND_PAYMENT;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @Override
    protected AbstractDemandSummaryResults getResults(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDTO) {
        return new AdjustmentSummarySearchResultsDemandPayment((DPSummaryLogic) logic, (DPSelectionDTO) selectionDTO);
    }

    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return adjustmentResults.getResultBeanContainer();
    }

    @Override
    protected void customizeResetButtonLogic() {
        view.select("Multiple Period");
    }

    @Override
    protected String[] getTableColumns() {
        return VariableConstants.VISIBLE_COLUMN_DEMAND_PAYMENT_DEDUCTION;
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
            Object[] obj = (Object[]) list.get(i);
            if ("summary_deductionValues".equals(String.valueOf(obj[0]))) {
                deductionLevelDdlb.setValue(selectionDTO.getSummary_deductionLevel());
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(getDeductionCustomMenuItem(), str3);
                }
            } else if ("summary_variables".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }
            } else if (!"detail_variables".equals(String.valueOf(obj[0])) && !"detail_reserveAcount".equals(String.valueOf(obj[0]))
                    && !"sales_variables".equals(String.valueOf(obj[0]))
                    && !"rate_DeductionValue".equals(String.valueOf(obj[0])) && !VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selectionDTO, String.valueOf(obj[0]), obj[1]);
                } catch (IllegalAccessException ex) {
                    LOGGER.error(ex);
                } catch (InvocationTargetException ex) {
                    LOGGER.error(ex);
                } catch (Exception ex) {
                    LOGGER.error(ex);
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
            view.setValue(selectionDTO.getSummary_demand_view());
            frequencyDdlb.select(Integer.valueOf(selectionDTO.getSummary_demand_frequency()));
            selectionDTO.setSummary_demand_frequency(String.valueOf(frequencyDdlb.getItemCaption(frequencyDdlb.getValue())));
            fromDate.setValue(selectionDTO.getSummary_demand_fromDate());
            toDate.setValue(selectionDTO.getSummary_demand_toDate());
            LOGGER.debug("selectionDTO.getSummary_glDate()!!!!!!!!" + selectionDTO.getSummary_glDate());
            glImpactDate.removeValueChangeListener(glListener);
            defaultWorkFlowDate = dateFormat.parse(selectionDTO.getSummary_glDate());
            resetWorkFlowDate = defaultWorkFlowDate;
            glImpactDate.setValue(resetWorkFlowDate);
            glImpactDate.addValueChangeListener(glWorkflowListener);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return frequencyDdlb;
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) throws Exception {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction4", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        adjustmentResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        adjustmentResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        adjustmentResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        adjustmentResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));
    }

}
