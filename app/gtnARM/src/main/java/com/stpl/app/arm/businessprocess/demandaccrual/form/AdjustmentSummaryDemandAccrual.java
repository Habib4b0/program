/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummarySelection;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.demandaccrual.dto.DASelectionDTO;
import com.stpl.app.arm.businessprocess.demandaccrual.logic.DASummaryLogic;
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
 * @author porchelvi.g
 */
public class AdjustmentSummaryDemandAccrual extends AbstractDemandSummarySelection {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummaryDemandAccrual.class);
    private final DASelectionDTO demandSummarySelectionDTO;

    public AdjustmentSummaryDemandAccrual(DASelectionDTO selection) throws InvocationTargetException {
        super(selection, new DASummaryLogic());
        this.demandSummarySelectionDTO = selection;
        super.init();
        configureWorkFlow();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method of AdjustmentSummaryDemandAccrual Class");
    }

    @Override
    public void configureSummary() {
        view.select(ARMConstants.getSinglePeriod());
        toDate.setEnabled(false);
        variableHeader = new String[]{VariableConstants.DemandSummaryVariables.DEMANDACCRUAL.toString(), VariableConstants.DemandSummaryVariables.DEMANDACCRUALREFORECAST.toString(),
            VariableConstants.DemandSummaryVariables.TOTALDEMANDACCRUAL.toString(),
            VariableConstants.DemandSummaryVariables.PROJECTEDTOTALDEMANDACCRUAL.toString(), VariableConstants.DemandSummaryVariables.DEMANDACCRUALRATIO.toString(),
            VariableConstants.DemandSummaryVariables.VARIANCE.toString()};
        variableHeaderDeduction = VariableConstants.DemandSummaryVariables.names();
        variableVisibleColumnsDeduction = VariableConstants.getVisibleColumnDemandAccrualDeduction();
        variableVisibleColumns = VariableConstants.getVisibleColumnDemandAccrual();
    }

    @Override
    protected AbstractDemandSummaryResults getResults(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDTO) {
        return new AdjustmentSummarySearchResultsDemandAccrual((DASummaryLogic) logic, (DASelectionDTO) selectionDTO);
    }

    @Override
    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return adjustmentResults.getResultBeanContainerVal();
    }

    @Override
    protected void customizeResetButtonLogic() {
        LOGGER.debug("Inside customizeResetButtonLogic  Method ");
    }

    @Override
    protected String[] getTableColumns() {
        return VariableConstants.getVisibleColumnDemandAccrualDeduction();
    }

    private void configureWorkFlow() throws InvocationTargetException {
        if (demandSummarySelectionDTO.getSessionDTO().isWorkFlow()) {
            loadSummaryDemandDetails();
            loadsumaryDemandSelection();
            if (ARMUtils.VIEW_SMALL.equals(demandSummarySelectionDTO.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
            generateButtonLogic();
        }
    }

    public void loadSummaryDemandDetails() throws InvocationTargetException {
        List<Object[]> list = CommonLogic.loadPipelineAccrual(demandSummarySelectionDTO.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (VariableConstants.SUMMARY_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                deductionLevelDdlb.setValue(demandSummarySelectionDTO.getSummarydeductionLevel());
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
                    BeanUtils.setProperty(demandSummarySelectionDTO, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error("Error in loadDetails :", ex);
                }

            }
        }
    }

    public void configureFieldsOnViewMode() {
        boolean readonlymode = false;
        frequencyDdlb.setEnabled(readonlymode);
        glImpactDate.setEnabled(readonlymode);
        reset.setEnabled(readonlymode);
        view.setEnabled(readonlymode);
    }

    private void loadsumaryDemandSelection() {
        try {
            view.setValue(demandSummarySelectionDTO.getSummarydemandview());
            frequencyDdlb.select(Integer.valueOf(demandSummarySelectionDTO.getSummarydemandfrequency()));
            demandSummarySelectionDTO.setSummarydemandfrequency((descriptionMap.get((Integer) frequencyDdlb.getValue())).getDescription());
            fromDate.setValue(demandSummarySelectionDTO.getSummarydemandfromDate());
            toDate.setValue(demandSummarySelectionDTO.getSummarydemandtoDate());
            LOGGER.debug("selectionDTO.getSummary_glDate(){}", demandSummarySelectionDTO.getSummaryglDate());
            glImpactDate.removeValueChangeListener(glListener);
            defaultWorkFlowDate = dateFormat.parse(demandSummarySelectionDTO.getSummaryglDate());
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
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction2", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        adjustmentResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        adjustmentResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        adjustmentResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        adjustmentResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }

    @Override
    public boolean equals(Object accrualAdjDetobj) {
        return super.equals(accrualAdjDetobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
