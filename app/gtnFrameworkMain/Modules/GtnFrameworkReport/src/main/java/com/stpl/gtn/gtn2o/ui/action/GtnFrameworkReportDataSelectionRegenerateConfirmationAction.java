package com.stpl.gtn.gtn2o.ui.action;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkReportDataSelectionRegenerateConfirmationAction implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            String s= gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString();
            String st=s;
		callRegenerate((GtnWsReportDataSelectionBean)gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void callRegenerate(GtnWsReportDataSelectionBean dataSelectionBean)
			throws GtnFrameworkValidationFailedException {
		GtnForecastHierarchyInputBean inputBean = createInputBeanForCCPInsert(
				dataSelectionBean.getSelectedCustomerHierarchyList(),
				dataSelectionBean.getSelectedProductHierarchyList(), dataSelectionBean);
		insertToCCp(inputBean, dataSelectionBean);
	}

	private GtnForecastHierarchyInputBean createInputBeanForCCPInsert(
			List<GtnWsRecordBean> selectedCustomerHierarchyList, List<GtnWsRecordBean> selectedProductHierarchyList,
			GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnForecastHierarchyInputBean inputBean = new GtnForecastHierarchyInputBean();
		inputBean.setSelectedCustomerList(relationBeanList(selectedCustomerHierarchyList, dataSelectionBean, false));
		inputBean.setSelectedProductList(relationBeanList(selectedProductHierarchyList, dataSelectionBean, true));
		inputBean.setSelectedCustomerRelationShipBuilderVersionNo(dataSelectionBean.getCustomerRelationshipVersionNo());
		inputBean.setSelectedProductRelationShipBuilderVersionNo(dataSelectionBean.getProductRelationshipVersionNo());
		inputBean.setSelectedCustomerHierarcySid((int) dataSelectionBean.getCustomerHierarchySid());
		inputBean.setSelectedProductHierarcySid((int) dataSelectionBean.getProductHierarchySid());
		inputBean.setSelectedProductRelationShipBuilderSid(dataSelectionBean.getProductRelationshipBuilderSid());
		inputBean.setSelectedCustomerHierarchyVersionNo(dataSelectionBean.getCustomerHierarchyVersionNo());
		inputBean.setSelectedProductHierarchyVersionNo(dataSelectionBean.getProductHierarchyVersionNo());
		inputBean.setForecastEligibleDate(dataSelectionBean.getForecastEligibleDate());
		inputBean.setForecastInsert(true);
		return inputBean;
	}

	private List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList(
			List<GtnWsRecordBean> selectedCustomerHierarchyList, GtnWsReportDataSelectionBean dataSelectionBean,
			boolean isProduct) {
		List<GtnFrameworkRelationshipLevelDefintionBean> relationBeanList = new ArrayList<>(
				selectedCustomerHierarchyList.size());
		for (GtnWsRecordBean recordBean : selectedCustomerHierarchyList) {

			GtnFrameworkRelationshipLevelDefintionBean forecast = new GtnFrameworkRelationshipLevelDefintionBean();
			forecast.setLevelName(recordBean.getStringProperty("levelName"));
			forecast.setLevelNo(recordBean.getIntegerProperty("levelNo"));
			forecast.setRelationshipLevelSid(recordBean.getIntegerProperty("relationshipLevelSid"));
			forecast.setRelationShipLevelValue(recordBean.getIntegerProperty("relationshipLevelValues"));
			forecast.setTableName(recordBean.getStringProperty("tableName"));
			forecast.setFieldName(recordBean.getStringProperty("fieldName"));
			forecast.setHierarchyNo(recordBean.getStringProperty("hierarchyNo"));
			forecast.setHierarchyDefinitionSid(recordBean.getIntegerProperty("hierarchyDefSid"));
			forecast.setHierarchyLevelDefinitionSid(recordBean.getIntegerProperty("hierarchyLevelDefSid"));
			forecast.setLevelValueReference(recordBean.getStringProperty("levelValuReference"));
			forecast.setRelationshipBuilderSid(recordBean.getIntegerProperty("relationshipBuilderSid"));
			forecast.setRelationshipVersionNo(!isProduct ? dataSelectionBean.getCustomerRelationshipVersionNo()
					: dataSelectionBean.getProductRelationshipVersionNo());
			forecast.setHierarchyVersionNo(!isProduct ? dataSelectionBean.getCustomerHierarchyVersionNo()
					: dataSelectionBean.getProductHierarchyVersionNo());
			forecast.setHierarchyCategory(recordBean.getStringProperty("hierarchyCategory"));
			relationBeanList.add(forecast);

		}
		return relationBeanList;
	}

	private void insertToCCp(GtnForecastHierarchyInputBean inputBean, GtnWsReportDataSelectionBean dataSelectionBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		GtnWsReportBean reportBeanRequest = new GtnWsReportBean();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		reportRequest.setReportBean(reportBeanRequest);
		reportBeanRequest.setDataSelectionBean(dataSelectionBean);
		forecastRequest.setInputBean(inputBean);
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		request.setGtnWsReportRequest(reportRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_WS_DATA_SELECTION_REGENERATE_SERVICE, "report", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
