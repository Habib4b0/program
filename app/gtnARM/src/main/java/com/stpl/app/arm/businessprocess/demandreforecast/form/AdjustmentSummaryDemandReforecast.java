/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.demandreforecast.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummaryResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractDemandSummarySelection;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.demandreforecast.dto.DRSelectionDTO;
import com.stpl.app.arm.businessprocess.demandreforecast.logic.DRSummaryLogic;
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
public class AdjustmentSummaryDemandReforecast extends AbstractDemandSummarySelection {

    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummaryDemandReforecast.class);

    public AdjustmentSummaryDemandReforecast(DRSelectionDTO selection) throws InvocationTargetException {
        super(selection, new DRSummaryLogic());
        configureWorkFlow();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.debug("Inside Enter Method of AdjustmentSummaryDemandReforecast Class");

    }

    @Override
    public void configureSummary() {
        view.select(ARMConstants.getMultiplePeriod());
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
        return new AdjustmentSummarySearchResultsDemandReforecast((DRSummaryLogic) logic, (DRSelectionDTO) selectionDTO);
    }

    @Override
    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return adjustmentResults.getResultBeanContainerVal();
    }

    @Override
    protected void customizeResetButtonLogic() {
        view.select(ARMConstants.getMultiplePeriod());
    }

    @Override
    protected String[] getTableColumns() {
        return VariableConstants.getVisibleColumnDemandAccrualDeduction();
    }

    public void configureWorkFlow() throws InvocationTargetException {
        if (selectionDTO.getSessionDTO().isWorkFlow()) {
            loadDetails();
            loadSelection();
            generateButtonLogic();
            if (ARMUtils.VIEW_SMALL.equals(selectionDTO.getSessionDTO().getAction())) {
                configureFieldsOnViewMode();
            }
        }
    }

    public void loadDetails() throws InvocationTargetException {
        List<Object[]> reforecastList = CommonLogic.loadPipelineAccrual(selectionDTO.getProjectionMasterSid());
        for (int i = 0; i < reforecastList.size(); i++) {
            Object[] object = reforecastList.get(i);
            if (VariableConstants.SUMMARY_DEDUCTION_VALUE.equals(String.valueOf(object[0]))) {
                deductionLevelDdlb.setValue(selectionDTO.getSummarydeductionLevel());
                String str = (String) object[1];
                String[] refStr2 = str.split(",");
                String str3 = null;
                for (String strings : refStr2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(getDeductionCustomMenuItem(), str3);
                }
            } else if (VariableConstants.SUMMARY_VARIABLES.equals(String.valueOf(object[0]))) {
                String refStr1 = (String) object[1];
                String[] str2 = refStr1.split(",");
                String refStr3 = null;
                for (String strings : str2) {
                    refStr3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, refStr3);
                }
            } else if (!CommonLogic.getInstance().getVariablesList().contains(object[0])) {
                try {
                    BeanUtils.setProperty(selectionDTO, String.valueOf(object[0]), object[1]);
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    LOGGER.error("Error in loadDetails :", ex);
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
            frequencyDdlb.select(Integer.valueOf(selectionDTO.getSummarydemandfrequency()));
            selectionDTO.setSummarydemandfrequency((descriptionMap.get((int) frequencyDdlb.getValue())).getDescription());
            fromDate.setValue(selectionDTO.getSummarydemandfromDate());
            toDate.setValue(selectionDTO.getSummarydemandtoDate());
            glImpactDate.removeValueChangeListener(glListener);
            defaultWorkFlowDate = dateFormat.parse(selectionDTO.getSummaryglDate());
            resetWorkFlowDate = defaultWorkFlowDate;
            glImpactDate.setValue(resetWorkFlowDate);
            glImpactDate.addValueChangeListener(glWorkflowListener);
        } catch (Exception ex) {
            LOGGER.error("Error in loadSelection :", ex);
        }
    }

    public void configurePermission(String userId, StplSecurity stplSecurity) {
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Fixed Dollar Adjustment", "Transaction5", "Adjustment Summary");
        reset.setVisible(CommonLogic.isButtonVisibleAccess("reset", functionHM));
        generate.setVisible(CommonLogic.isButtonVisibleAccess("generate", functionHM));
        adjustmentResults.getExpandbtn().setVisible(CommonLogic.isButtonVisibleAccess("expandbtn", functionHM));
        adjustmentResults.getCollapseBtn().setVisible(CommonLogic.isButtonVisibleAccess("collapseBtn", functionHM));
        adjustmentResults.getCancelOverride().setVisible(CommonLogic.isButtonVisibleAccess("cancelOverride", functionHM));
        adjustmentResults.getCalculateBtn().setVisible(CommonLogic.isButtonVisibleAccess("calculateBtn", functionHM));

    }
}
