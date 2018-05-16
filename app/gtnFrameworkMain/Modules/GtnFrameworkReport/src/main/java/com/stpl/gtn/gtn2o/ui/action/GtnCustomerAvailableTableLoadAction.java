package com.stpl.gtn.gtn2o.ui.action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.vaadin.ui.AbstractComponent;

public class GtnCustomerAvailableTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Date forecastEligibleDate = null;
		String hierarchyComponentId = "reportLandingScreen_customerHierarchy";
		String relationshipComponentId = "reportLandingScreen_customerSelectionRelationship";
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(hierarchyComponentId, componentId).getComponentData().getCustomData();

		String relationshipVersionNo = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerRelationshipVersion").getValueFromComponent());
		String hierarchyVersionNo = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerRelationshipVersion").getCaptionFromComboBox();

		String relationshipBuilderSid = String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(relationshipComponentId, componentId).getValueFromComponent());
		Integer hierarchyDefSid = (Integer) recordBean.getPropertyValueByIndex(recordBean.getProperties().size() - 1);

		Integer selectedLevelNo = (Integer) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerSelectionLevel").getValueFromComponent();
		String selectedLevel = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerSelectionLevel").getCaptionFromComboBox();
		LocalDate date = (LocalDate) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("reportLandingScreen_customerSelectionForecastEligibilityDate").getFieldValue();
		if (date != null) {
			forecastEligibleDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		if (!selectedLevel.equals("")) {
			String query = getLevelValueMapQuery(relationshipBuilderSid, hierarchyDefSid, hierarchyVersionNo,
					relationshipVersionNo);
			Map<String, String> levelValueMap = getLevelValueMap(query);
			List<GtnReportHierarchyLevelBean> hierarchyLevels = getHierarchyLevelDefinition(hierarchyDefSid,
					hierarchyVersionNo);
			GtnReportHierarchyLevelBean selectedHierarchyLevelBean = hierarchyLevels.get(selectedLevelNo - 1);
			String customerLevelQuery = loadAvailableCustomerLevelQuery(selectedHierarchyLevelBean,
					Integer.parseInt(relationshipBuilderSid), relationshipVersionNo, forecastEligibleDate);
			List<Object> queryParameters = new ArrayList<>();
			queryParameters.add(customerLevelQuery);
			queryParameters.add(levelValueMap);
			queryParameters.add(selectedHierarchyLevelBean);
			queryParameters.add(hierarchyLevels);
			queryParameters.add(relationshipVersionNo);
			queryParameters.add(hierarchyVersionNo);
			queryParameters.add(selectedLevelNo);
			queryParameters.add(forecastEligibleDate);
			queryParameters.add(Boolean.FALSE);

			AbstractComponent dualListBoxComponent = GtnUIFrameworkGlobalUI
					.getVaadinComponent("reportLandingScreen_customerDualListBox");
			GtnUIFrameworkComponentData dualListBoxComponentData = (GtnUIFrameworkComponentData) dualListBoxComponent
					.getData();
			GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxComponentData
					.getCustomData();
			dualListBoxBean.setGtnDualListBoxqueryParameters(queryParameters);
			dualListBoxComponentData.setCustomData(dualListBoxBean);
		}
	}

	private String getLevelValueMapQuery(String relationshipBuilderSid, Integer hierarchyDefSid,
			String hierarchyVersionNo, String relationshipVersionNo) {

		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(Integer.parseInt(relationshipBuilderSid));
		inputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse relationResponse = client.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_LEVELVALUE_MAP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsForecastResponse foreCastResponse = relationResponse.getGtnWsForecastResponse();
		GtnForecastHierarchyInputBean outputBean = foreCastResponse.getInputBean();
		return outputBean.getHieraryQuery();

	}

	private Map<String, String> getLevelValueMap(String query) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHieraryQuery(query);
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTDATASELECTION_LOAD_LEVELVALUE_MAP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getTempTableMap();
	}

	private List<GtnReportHierarchyLevelBean> getHierarchyLevelDefinition(Integer hierarchyDefSid,
			String hierarchyVersionNo) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setHierarchyDefinitionSid(hierarchyDefSid);
		inputBean.setHierarchyVersionNo(Integer.parseInt(hierarchyVersionNo));
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_REPORTCUSTOMER_HIERARCHYLEVEL_VALUES,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getLevelList();
	}

	private String loadAvailableCustomerLevelQuery(GtnReportHierarchyLevelBean selectedHierarchyLevelBean,
			int relationshipSid, String relationshipVersionNo, Date forecastEligibleDate) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setRelationShipBuilderSid(relationshipSid);
		inputBean.setRelationVersionNo(Integer.parseInt(relationshipVersionNo));
		inputBean.setForecastEligibleDate(forecastEligibleDate);
		inputBean.setHierarchyDefinitionSid(selectedHierarchyLevelBean.getHierarchyDefSid());
		inputBean
				.setHierarchyLevelDefinitionSid(Integer.parseInt(selectedHierarchyLevelBean.getHierarchyLevelDefSid()));
		inputBean.setHierarchyVersionNo(selectedHierarchyLevelBean.getHierarchyVersionNo());
		inputBean.setLevelNo(selectedHierarchyLevelBean.getLevelNo());
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_DATASELCTION_EDIT_SERVICE
						+ GtnWebServiceUrlConstants.GTN_DATASELECTION_LOAD_CUSTOMER_LEVEL,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsForecastResponse().getInputBean().getHieraryQuery();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
