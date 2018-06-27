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
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkArmAdjustmentTypeDdlbAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkArmAdjustmentTypeDdlbAction.class);
	
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		/*
		 * No Implementation Needed
		 */
	}
	
	private GtnWebServiceSearchCriteria createInstanceGtnWebServiceSearchCriteria(String fieldId, String filterValue1, String expression) {
		GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria=new GtnWebServiceSearchCriteria();
		gtnWebServiceSearchCriteria.setFieldId(fieldId);
		gtnWebServiceSearchCriteria.setFilterValue1(filterValue1);
		gtnWebServiceSearchCriteria.setExpression(expression);
		
		return gtnWebServiceSearchCriteria;
	}
	
	private void addDeductionLevelSearchComponent(String deductionLevelArm, String deductionValueArm, List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList) {
		additioanlSearchCriteriaList.add(
				createInstanceGtnWebServiceSearchCriteria(
				armDeductionLevelCoulmnDetail(deductionLevelArm),deductionValueArm, GtnFrameworkWorkflowInboxClassConstants.EQUAL));
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
		StringBuilder listString=new StringBuilder();
		
		for(String[] menuItem: resultList) {
			listString.append(menuItem[2]);
			listString.append(",");
		}
		
		if(!listString.toString().isEmpty()) {
			listString.deleteCharAt(listString.lastIndexOf(","));
		
			//List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList=new ArrayList<>();
			GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria=new GtnWebServiceSearchCriteria();
			gtnWebServiceSearchCriteria.setFieldId(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE);
			gtnWebServiceSearchCriteria.setFilterValue1("("+listString.toString()+")");
			gtnWebServiceSearchCriteria.setExpression("IN");
			additioanlSearchCriteriaList.add(gtnWebServiceSearchCriteria);
			
			/*GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSEARCHRESULTTABLE)
				.getCurrentPageTableLogic().setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);*/
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
			
			if(!resultList.isEmpty()) {
				armAdditionalAdjustmentType(resultList, additioanlSearchCriteriaList);
			}
			
			if(!deductionLevelArm.isEmpty() && !deductionValueArm.isEmpty()) {
				addDeductionLevelSearchComponent(deductionLevelArm, deductionValueArm, additioanlSearchCriteriaList);
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error("Inside GtnFrameworkArmAdjustmentTypeDdlbAction -->armAdditionalSearchComponent", e);
		}
	}
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("Inside GtnFrameworkArmAdjustmentTypeDdlbAction --> doAction --> Start");
		String businessProcess=GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESS_PROCESS).getCaptionFromComboBox();
	
		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList=new ArrayList<>();
		
		if(businessProcess.equalsIgnoreCase(GtnFrameworkWorkflowInboxClassConstants.ARM)) {
			armAdditionalSearchComponent(additioanlSearchCriteriaList);
		}
		
		if(!additioanlSearchCriteriaList.isEmpty()) {
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
