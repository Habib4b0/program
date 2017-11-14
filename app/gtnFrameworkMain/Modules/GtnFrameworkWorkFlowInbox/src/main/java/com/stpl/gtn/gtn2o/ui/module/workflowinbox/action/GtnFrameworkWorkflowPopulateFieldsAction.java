package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnFrameworkWorkflowPopulateFieldsAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkWorkflowPopulateFieldsAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		gtnLogger.debug("inside GtnFrameworkWorkflowPopulateFieldsAction");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnWsRecordBean gtnWsRecordBean = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.getValueFromPagedDataTable();
		try {
			String combocomponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("businessProcess")
					.getCaptionFromComboBox();
			String workflowIdPrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWIDPRIVATE));
			String workflowNamePrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAMEPRIVATE));
			String workflowDescPrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCPRIVATE));
			String companyNamePrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMEPRIVATE));
			String businessUnitNamePrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMEPRIVATE));
			String contractId = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTID));
			String contractNo = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO));
			String companyNo = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.COMPANYNO));
			String businessUnitId = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID));
			String businessUnitNo = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO));
			String businessUnitName = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME));
			String contractName = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME));
			String itemNo = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNO));
			String itemName = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNAME));

			String forecastdeductionLevel = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONLEVEL));

			String forecastdeductionValue = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE));

			String contractType = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE));

			String companyID = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.COMPANYID));
			String itemId = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMID));
			String businessUnitIdReturns = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDRETURNS));
			String businessUnitNoReturns = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNORETURNS));
			String businessUnitNameReturns = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMERETURNS));
			String itemNoReturns = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNORETURNS));
			String itemNameReturns = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNAMERETURNS));
			String itemIdReturns = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMIDRETURNS));

			String contractIdArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDARM));
			String contractNoArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNOARM));
			String brandIdArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BRANDIDARM));
			String contractNameArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM));
			String itemNoArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNOARM));
			String itemNameArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ITEMNAMEARM));

			String brandNameArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BRANDNAMEARM));
			Date glDateArm = (Date) gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.GLDATEARM);

			String deductionNoArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNOARM));
			String deductionNameArm = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAMEARM));

			String customerNoArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.COMPANYNO));

			String customerNameArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM));

			Date createdfromPrivate = (Date) gtnWsRecordBean
					.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM_PRIVATE);

			Date createdtoPrivate = (Date) gtnWsRecordBean
					.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CREATEDTO_PRIVATE);

			Date approvedfromPrivate = (Date) gtnWsRecordBean
					.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM_PRIVATE);

			Date approvedtoPrivate = (Date) gtnWsRecordBean
					.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO_PRIVATE);

			String businessunitArm = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARM));

			String workflowStatusArm = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSTATUSPRIVATE));

			String adjustmentType = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE));

			String deductionLevelArm = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM));

			String deductionValueArm = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM));

			String companyARM = String
					.valueOf(gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.COMPANYARM));

			String createdByPrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.CREATEDBYPRIVATE));

			String approvedByPrivate = String.valueOf(
					gtnWsRecordBean.getPropertyValue(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBYPRIVATE));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID, componentId)
					.loadFieldValue(workflowIdPrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAME, componentId)
					.loadFieldValue(workflowNamePrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC, componentId)
					.loadFieldValue(workflowDescPrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDFROM, componentId)
					.loadDateValue(createdfromPrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDTO, componentId)
					.loadDateValue(createdtoPrivate);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDFROM, componentId)
					.loadDateValue(approvedfromPrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO, componentId)
					.loadDateValue(approvedtoPrivate);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTID, componentId)
					.loadFieldValue(contractId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO, componentId)
					.loadFieldValue(contractNo);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNO, componentId)
					.loadFieldValue(companyNo);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME, componentId)
					.loadFieldValue(companyNamePrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID, componentId)
					.loadFieldValue(businessUnitId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO, componentId)
					.loadFieldValue(businessUnitNo);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME, componentId)
					.loadFieldValue(businessUnitName);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME, componentId)
					.loadFieldValue(businessUnitNamePrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME, componentId)
					.loadFieldValue(contractName);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNO, componentId)
					.loadFieldValue(itemNo);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNAME, componentId)
					.loadFieldValue(itemName);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("forecastdeductionLevel", componentId)
					.loadComboBoxComponentValue(getIntValue(forecastdeductionLevel));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("forecastdeductionValue", componentId)
					.loadComboBoxComponentValue(getIntValue(forecastdeductionValue));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("contractType", componentId)
					.loadComboBoxComponentValue(getIntValue(contractType));

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYID, componentId)
					.loadFieldValue(companyID);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMID, componentId)
					.loadFieldValue(itemId);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDRETURNS, componentId)
					.loadFieldValue(businessUnitIdReturns);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNORETURNS, componentId)
					.loadFieldValue(businessUnitNoReturns);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMERETURNS,
							componentId)
					.loadFieldValue(businessUnitNameReturns);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNORETURNS, componentId)
					.loadFieldValue(itemNoReturns);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNAMERETURNS, componentId)
					.loadFieldValue(itemNameReturns);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMIDRETURNS, componentId)
					.loadFieldValue(itemIdReturns);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDARM, componentId)
					.loadFieldValue(contractIdArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNOARM, componentId)
					.loadFieldValue(contractNoArm);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BRANDIDARM, componentId)
					.loadFieldValue(brandIdArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM, componentId)
					.loadFieldValue(contractNameArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNOARM, componentId)
					.loadFieldValue(itemNoArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ITEMNAMEARM, componentId)
					.loadFieldValue(itemNameArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BRANDNAMEARM, componentId)
					.loadFieldValue(brandNameArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.GLDATEARM, componentId)
					.loadDateValue(glDateArm);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNOARM, componentId)
					.loadFieldValue(deductionNoArm);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAMEARM, componentId)
					.loadFieldValue(deductionNameArm);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("businessUnitARM", componentId)
					.loadComboBoxComponentValue(getIntValue(businessunitArm));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("workflowStatusArm", componentId)
					.loadComboBoxComponentValue(getIntValue(workflowStatusArm));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("adjustmentType", componentId)
					.loadComboBoxComponentValue(getIntValue(adjustmentType));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("deductionLevelArm", componentId)
					.loadComboBoxComponentValue(getIntValue(deductionLevelArm));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("deductionValueArm", componentId)
					.loadComboBoxComponentValue(getIntValue(deductionValueArm));

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("customerNoArm", componentId).loadFieldValue(customerNoArm);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent("customerNameArm", componentId)
					.loadFieldValue(customerNameArm);
			if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM) && !companyARM.isEmpty()) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent("companyARM", componentId)
						.loadComboBoxComponentValue(getIntValue(companyARM));
			}
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY, componentId)
					.loadFieldValue(createdByPrivate);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY, componentId)
					.loadFieldValue(approvedByPrivate);

		} catch (

		Exception e) {
			gtnLogger.error(e.getMessage(), e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private Integer getIntValue(String value) {
		if (value != null && !value.isEmpty()) {
			return Integer.parseInt(value);
		}
		return 0;
	}

}
