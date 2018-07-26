package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkAdditionalSearchCriteriaAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAdditionalSearchCriteriaAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        /*
		 * No Implementation Needed
         */
    }

    private GtnWebServiceSearchCriteria createInstanceGtnWebServiceSearchCriteria(String fieldId, String filterValue1, String expression) {
        GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();
        gtnWebServiceSearchCriteria.setFieldId(fieldId);
        gtnWebServiceSearchCriteria.setFilterValue1(filterValue1);
        gtnWebServiceSearchCriteria.setExpression(expression);

        return gtnWebServiceSearchCriteria;
    }

    private void addDeductionLevelSearchComponent(String deductionLevelArm, String deductionValueArm, List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
        additioanlSearchCriteriaList.add(
                createInstanceGtnWebServiceSearchCriteria(
                        armDeductionLevelCoulmnDetail(deductionLevelArm), deductionValueArm, GtnFrameworkWorkflowInboxClassConstants.EQUAL));
    }

    private String armDeductionLevelCoulmnDetail(String key) {
        Map<String, String> fieldToColumnDetailsMap = new HashMap<>();
        fieldToColumnDetailsMap.put("Deduction Category", "deductionCategory");
        fieldToColumnDetailsMap.put("Deduction Program", "deductionProgram");
        fieldToColumnDetailsMap.put("Deduction Type", "deductionType");
        fieldToColumnDetailsMap.put("Deduction Category 2", "deductionCategory2");
        fieldToColumnDetailsMap.put("Deduction Category 3", "deductionCategory3");
        fieldToColumnDetailsMap.put("Deduction Category 4", "deductionCategory4");
        fieldToColumnDetailsMap.put("Deduction Category 5", "deductionCategory5");
        fieldToColumnDetailsMap.put("Deduction Category 6", "deductionCategory6");
        fieldToColumnDetailsMap.put("Deduction", "deduction");
        return fieldToColumnDetailsMap.get(key);
    }

    private void armAdditionalAdjustmentType(List<String[]> resultList, List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
        StringBuilder listString = new StringBuilder();

        for (String[] menuItem : resultList) {
            listString.append(menuItem[2]);
            listString.append(',');
        }

        if (!listString.toString().isEmpty()) {
            listString.deleteCharAt(listString.lastIndexOf(","));

            GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria
                    = createInstanceGtnWebServiceSearchCriteria(
                            GtnFrameworkWorkflowInboxClassConstants.SELECTED_ADJUSTMENTTYPE,
                            "(" + listString.toString() + ")",
                            "IN");
            additioanlSearchCriteriaList.add(gtnWebServiceSearchCriteria);

        }
    }

    @SuppressWarnings("unchecked")
    private void armAdditionalSearchComponent(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
        List<String[]> resultList;
        String deductionLevelArm;
        String deductionValueArm;

        try {
            resultList = (List<String[]>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE).getValueFromComponent();
            deductionLevelArm = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM).getCaptionFromComboBox();
            deductionValueArm = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM).getIntegerFromField().toString();

            if (!resultList.isEmpty()) {
                armAdditionalAdjustmentType(resultList, additioanlSearchCriteriaList);
            }

            if (!deductionLevelArm.isEmpty() && !deductionValueArm.equals("0")) {
                addDeductionLevelSearchComponent(deductionLevelArm, deductionValueArm, additioanlSearchCriteriaList);
            }
        } catch (GtnFrameworkValidationFailedException e) {
            gtnLogger.error("Inside GtnFrameworkArmAdjustmentTypeDdlbAction -->armAdditionalSearchComponent", e);
        }
    }

    private void approvedBySearchComponent(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) throws GtnFrameworkValidationFailedException {
        if ("".equals(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY).getObjectFromField())) {
            return;
        }

        GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP)
                .getComponentData().getCustomData();

        if (gtnWsRecordBean==null || gtnWsRecordBean.getPropertyValue("fullName")==null || !String.valueOf(gtnWsRecordBean.getPropertyValue("fullName"))
                .equalsIgnoreCase(String.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY).getObjectFromField()))) {
            return;
        }

        GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = createInstanceGtnWebServiceSearchCriteria(
                GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY,
                gtnWsRecordBean.getStringProperty("userId"),
                "EQUALS");
        additioanlSearchCriteriaList.add(gtnWebServiceSearchCriteria);
    }

    private void createdBySearchComponent(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) throws GtnFrameworkValidationFailedException {

        if ("".equals(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY).getObjectFromField())) {
            return;
        }

        GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY + GtnFrameworkWorkflowInboxClassConstants.SEARCHPOPUP)
                .getComponentData().getCustomData();

        
        if (gtnWsRecordBean==null || gtnWsRecordBean.getPropertyValue("fullName")==null || !String.valueOf(gtnWsRecordBean.getPropertyValue("fullName"))
                .equalsIgnoreCase(String.valueOf(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY).getObjectFromField()))) {
            return;
        }

        GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = createInstanceGtnWebServiceSearchCriteria(
                GtnFrameworkWorkflowInboxClassConstants.CREATEDBY,
                gtnWsRecordBean.getStringProperty("userId"),
                "EQUALS");
        additioanlSearchCriteriaList.add(gtnWebServiceSearchCriteria);
    }

    private void createdByAndSearchBySearchComponent(List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
        try {
            createdBySearchComponent(additioanlSearchCriteriaList);
            approvedBySearchComponent(additioanlSearchCriteriaList);
        } catch (GtnFrameworkValidationFailedException ex) {
            gtnLogger.error("Error in GtnFrameworkAdditionalSearchCriteriaAction -->createdByAndSearchBySearchComponent ");
        }
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        gtnLogger.debug("Inside GtnFrameworkArmAdjustmentTypeDdlbAction --> doAction --> Start");
        String businessProcess = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS).getCaptionFromComboBox();

        List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

        if (businessProcess.equalsIgnoreCase(GtnFrameworkWorkflowInboxClassConstants.ARM)) {
            armAdditionalSearchComponent(additioanlSearchCriteriaList);
            createdByAndSearchBySearchComponent(additioanlSearchCriteriaList);
        } else if (businessProcess.equalsIgnoreCase(GtnFrameworkCommonStringConstants.ACCRUAL_RATE_PROJECTION)) {
            createdByAndSearchBySearchComponent(additioanlSearchCriteriaList);
        }

        if (!additioanlSearchCriteriaList.isEmpty()) {
            GtnUIFrameworkGlobalUI
                    .getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE)
                    .getCurrentPageTableLogic().setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
        }
        gtnLogger.debug("Inside GtnFrameworkArmAdjustmentTypeDdlbAction --> doAction --> End");
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
