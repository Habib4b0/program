package com.stpl.gtn.gtn2o.ui.module.priceschedule.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceProtectionValueChangeManager;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.config.fieldfactory.GtnFrameworkPriceTabValueChangeManager;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnUIFrameWorkAddDataTableAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void doAction(final String compontId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkPagedTableLogic tableLogic = GtnUIFrameworkGlobalUI.getVaadinComponentData("CFPrightResultTable")
				.getCurrentPageTableLogic();
		if (tableLogic.getContainerDataSource().getItemIds() != null
				&& tableLogic.getContainerDataSource().getItemIds().isEmpty()) {
			tableLogic.configureContainer(
					GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable").getValueFromPagedDataTable(),
					tableLogic.getContainerDataSource());
			GtnWsRecordBean gtnWsRecordBean = null;
			gtnWsRecordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI.getVaadinBaseComponent("CFPleftResultTable")
					.getValueFromComponent();
			String ifpId = gtnWsRecordBean.getPropertyValueByIndex(8).toString();
			String psId = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("priceScheduleId1").getStringFromField();

			Map<String, String> inputValueMap = new HashMap<>();
			inputValueMap.put("ifpId", ifpId);
			inputValueMap.put(GtnFrameworkCommonConstants.SESSION_ID,
					GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());
			inputValueMap.put("userId", GtnUIFrameworkGlobalUI.getCurrentUser());
			inputValueMap.put("ps_id", psId);
			loadDataFromService(inputValueMap);
			GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
			GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.FALSE);
			Object mode = GtnUIFrameworkGlobalUI.getSessionProperty("mode");
			if (mode != null && mode != GtnUIFrameworkModeType.VIEW)
				new GtnUIFrameWorkPSLoadAction().loadPriceTabsForView(0, Boolean.TRUE);
			else
				new GtnUIFrameWorkPSLoadAction().loadPriceTabsForView(0, Boolean.FALSE);
			GtnFrameworkPriceTabValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
			GtnFrameworkPriceProtectionValueChangeManager.setValueChangeAllowed(Boolean.TRUE);
		}
	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadDataFromService(Map<String, String> inputValueMap) {

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		List<Object> inputList = new ArrayList<>();
		inputList.add(inputValueMap);
		gtnWsGeneralRequest.setComboBoxWhereclauseParamList(inputList);

		wsclient.callGtnWebServiceUrl(
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_ITEM_ADDITION_SERVICE, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	public void loadPriceTabs() throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkComponentData psPricingTabResultDataTablecomponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE);
		GtnUIFrameworkPagedTableLogic priceTabTableLogic = psPricingTabResultDataTablecomponentData
				.getCurrentPageTableLogic();

		List<GtnWebServiceSearchCriteria> additioanlSearchCriteriaList = new ArrayList<>();

		GtnWebServiceSearchCriteria userIdCriteria = new GtnWebServiceSearchCriteria();
		GtnWebServiceSearchCriteria sessionIdCriteria = new GtnWebServiceSearchCriteria();
		additioanlSearchCriteriaList.add(userIdCriteria);
		additioanlSearchCriteriaList.add(sessionIdCriteria);

		userIdCriteria.setFieldId("userId");
		userIdCriteria.setExpression("EQUALS");
		userIdCriteria.setFilterValue1(GtnUIFrameworkGlobalUI.getCurrentUser());

		sessionIdCriteria.setFieldId(GtnFrameworkCommonConstants.SESSION_ID);
		sessionIdCriteria.setExpression("EQUALS");
		sessionIdCriteria.setFilterValue1(
				GtnUIFrameworkGlobalUI.getSessionProperty(GtnFrameworkCommonConstants.SESSION_ID).toString());

		priceTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
		setTableEnableDisable(Boolean.TRUE);
		boolean isActiveFlag = Boolean.TRUE;
		priceTabTableLogic.startSearchProcess(new ArrayList<String>(), isActiveFlag);

		GtnUIFrameworkComponentData psPriceProtectionTabResultDataTableomponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE);
		GtnUIFrameworkPagedTableLogic priceProtectionTabTableLogic = psPriceProtectionTabResultDataTableomponentData
				.getCurrentPageTableLogic();
		priceProtectionTabTableLogic.setAdditioanlSearchCriteriaList(additioanlSearchCriteriaList);
		priceProtectionTabTableLogic.startSearchProcess(new ArrayList<String>(), isActiveFlag);

	}

	private void setTableEnableDisable(boolean isEditable) throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setReadOnly(!isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setEditable(isEditable);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setReadOnly(!isEditable);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setEditable(isEditable);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICING_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setFilterBarVisible(isEditable);
		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(GtnFrameworkCommonConstants.PS_PRICE_PROTECTION_TAB_RESULT_DATA_TABLE)
				.getExtPagedTable().setFilterBarVisible(isEditable);

	}

}
