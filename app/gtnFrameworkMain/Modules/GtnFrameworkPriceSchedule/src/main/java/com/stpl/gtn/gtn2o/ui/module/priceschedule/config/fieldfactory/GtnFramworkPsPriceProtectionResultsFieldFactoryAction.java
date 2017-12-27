package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory;

import java.util.ArrayList;
import java.util.Date;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsCheckAllUpdateBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsCheckAllUpdateRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

public class GtnFramworkPsPriceProtectionResultsFieldFactoryAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinFieldFactoryComponentData(componentId);
		GtnUIFrameworkActionParameter actionParam = componentData.getActionParameter();
		if (GtnFrameworkPriceProtectionValueChangeManager.isValueChangeAllowed()) {
			String propertyId = actionParam.getPropertyId();
			if (propertyId.equals(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12])) {
				propertyId = getFieldId(propertyId, actionParam.getItemId(), actionParam.getCurrentValue());
			}
			updateField(propertyId, actionParam.getCurrentValue(), Boolean.FALSE,
					actionParam.getItemId().getPropertyValue("systemId").toString(), componentId,
					actionParam.getTableComponentId(), actionParam.getItemId());
		}
		if (GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId())) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParam.getTableComponentId())
					.getLogicFromPagedDataTable().handleCheckBoxOnItem(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
							Boolean.parseBoolean(String.valueOf(actionParam.getCurrentValue())));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void updateField(String column, Object value, boolean checkAll, String systemId, String componentId,
			String tableId, GtnWsRecordBean gtnWsRecordBean) throws GtnFrameworkValidationFailedException {

		Object localVarable = value;
		GtnUIFrameworkWebserviceRequest updateRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		if ((column.equals("psPPStartDate")) && localVarable == null) {
			localVarable = "NULL";
		}

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId").toString());
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsCheckAllUpdateBean psUpdateBean = new GtnWsCheckAllUpdateBean();
		psUpdateBean.setPropertyId(column);

		if (localVarable instanceof Boolean) {
			localVarable = (Boolean) localVarable ? 1 : 0;
		} else if (GtnFrameworkPSConstants.getPriceProtectionCustomTextFieldProperties().contains(column)
				&& GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData(componentId).getCustomData() != null) {
			GtnWsRecordBean dto = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinFieldFactoryComponentData(componentId).getCustomData();
			localVarable = dto.getPropertyValueByIndex(dto.getProperties().size() - 1);
		} else if (GtnFrameworkPSConstants.getPriceProtectionCustomTextFieldProperties().contains(column)
				&& GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData(componentId).getCustomData() == null) {
			return;
		} else if (GtnFrameworkPSConstants.getPriceProtectionDdlbFieldPropertiesArray().contains(column)
				&& localVarable == null) {
			localVarable = 0;
		}
		psUpdateBean.setValue(localVarable);
		psUpdateBean.setMasterSid(Integer.valueOf(systemId));
		psUpdateBean.setCheckAll(checkAll);

		GtnWsCheckAllUpdateRequest gtnWsPSUpdateRequest = new GtnWsCheckAllUpdateRequest();
		gtnWsPSUpdateRequest.setUpdateBean(psUpdateBean);
		updateRequest.setGtnWsCheckAllUpdateRequest(gtnWsPSUpdateRequest);
		updateRequest.setGtnWsGeneralRequest(generalWSRequest);

		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_PRICE_PROTECTION_TAB_UPDATE_SERVICE,
				updateRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (column.equals(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[11])) {
			GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
			String depandingValue = baseComponent.getCaptionFromComboBox();
			gtnWsRecordBean.getProperties().set(11, depandingValue);
			Object newValue = getFieldValue(gtnWsRecordBean);
			gtnWsRecordBean.addProperties(GtnFrameworkCommonConstants.PS_BASE_PRICE_ENTRY, newValue);
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId);

			tableBaseComponent.setTableColumnValue(gtnWsRecordBean,
					GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[12], null);
			tableBaseComponent.setTableRefresh(true);

		}
	
		boolean gtnFrameworkPPPriceToltanceValueChangeManager = GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager
				.isValueChangeAllowed();
		if ((gtnFrameworkPPPriceToltanceValueChangeManager)
				&& ((column.equals(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[20]))|| 
				(column.equals(GtnFrameworkPSConstants.getPriceProtectionEditableList().toArray()[21])))) {
			GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager.setValueChangeAllowed(false);
			refreshTable();
			GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager.setValueChangeAllowed(true);
		}
	}

	private void refreshTable() throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("psPriceProtectionTabResultDataTable");
		GtnUIFrameworkPagedTableLogic tableLogic = componentData.getCurrentPageTableLogic();
		tableLogic.startSearchProcess(new ArrayList<String>(), true);
		GtnFrameworkPPPriceTolranceFieldFactoryValueChangeManager.setValueChangeAllowed(true);
	}

	private Object getFieldValue(GtnWsRecordBean psPriceProtectionBean) {
		String psPriceTypeValue = psPriceProtectionBean.getStringPropertyByIndex(11);
		if (psPriceTypeValue.startsWith("P")) {
			return psPriceProtectionBean.getPropertyValueByIndex(36);
		}
		if (psPriceTypeValue.startsWith("D")) {
			Object value = psPriceProtectionBean.getPropertyValueByIndex(35);
			if (value != null && Long.class.isAssignableFrom(value.getClass())) {
				value = new Date((Long) value);
			}
			return value;
		}
		if (psPriceTypeValue.startsWith("M")) {
			return psPriceProtectionBean.getStringPropertyByIndex(12).trim();
		}
		return "";
	}

	private String getFieldId(String currentPropId, GtnWsRecordBean bean, Object value) {
		String depandingValue = bean.getStringPropertyByIndex(11);
		if (depandingValue.startsWith("P")) {
			bean.getProperties().set(36, value);
			return currentPropId.replace("Entry", "Ddlb");
		}
		if (depandingValue.startsWith("D")) {
			bean.getProperties().set(35, value);
			return currentPropId.replace("Entry", "Date");
		}
		if (depandingValue.startsWith("M")) {
			bean.getProperties().set(12, value);
			return currentPropId;
		}
		return "";
	}
}
