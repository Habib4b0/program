/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.crud;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastReturnsConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;

/**
 *
 * @author STPL
 */
public class GtnFrameworkReturnsDataSelectionFieldsSaveAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI.getVaadinComponentData(
				gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString(), componentId);
		saveValuesInDataSelectionEdit((GtnForecastBean) gtnUIFrameworkComponentData.getCustomData(),
				gtnUIFrameWorkActionConfig.getFieldValues(), componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	/*
	 * 0 "gtnForecastReturnProjectionDetailsPopup", 1
	 * "returnsForecastTabSheet_dsTabSave", 2
	 * "returnsForecastTabSheet_dsTabUpdate", 3
	 * "returnsForecastTabSheet_dsTabProjectionName", 4
	 * "returnsForecastTabSheet_dsTabProjectionDescription", 5
	 * "returnsForecastTabSheet_dsTabFromPeriod", 6
	 * "returnsForecastTabSheet_productHierarchy", 7
	 * "returnsForecastTabSheet_company", 8
	 * "returnsForecastTabSheet_businessUnit", 9
	 * "returnsForecastTabSheet_relationShipCombobox", 10
	 * "returnsForecastTabSheet_forecastLevel", 11
	 * "forecastReturnslevelFilterDdlb", 12 "forecastReturnslevelDdlb", 13
	 * "massUpdateLevel", 14 "returnsForecastTabSheet_productLevelComboBox", 15
	 * "returnsForecastTabSheet_dsTabToPeriod", 16 "forecastReturnsFrequency",
	 * 17 "forecastReturnsHistory", 18 "actualsProjectionOptionGroup", 19
	 * "projectionPeriodOrderOptionGroup", 20
	 * "returnsForecastTabSheet_dualListBoxComp", 21 "resultTable", 22
	 * "projectionDetailsTabsheetMainLayout", 23 "returnsProjectionGenerateBtn"
	 */
	private void saveValuesInDataSelectionEdit(GtnForecastBean gtnForecastBean, List<String> fieldValuesParameterList,
			String sourceComponentId) throws GtnFrameworkValidationFailedException {

		DataSelectionBean dataSelectionBean = new DataSelectionBean();
		dataSelectionBean.setProjectionName(getStringValue(fieldValuesParameterList.get(3), sourceComponentId));
		dataSelectionBean.setProjectionDescription(getStringValue(fieldValuesParameterList.get(4), sourceComponentId));

		dataSelectionBean.setGlCompanyId(getIntegerValue(fieldValuesParameterList.get(7), sourceComponentId));
		dataSelectionBean.setBusinessUnitId(getIntegerValue(fieldValuesParameterList.get(8), sourceComponentId));

		dataSelectionBean.setFromDate(getIntegerValue(fieldValuesParameterList.get(5), sourceComponentId));
		dataSelectionBean.setToDate(getIntegerValue(fieldValuesParameterList.get(15), sourceComponentId));

		dataSelectionBean.setProductHierarchyId((Serializable)getSelectedId(fieldValuesParameterList.get(6), sourceComponentId));
		dataSelectionBean.setProductHierarchyName(getStringValue(fieldValuesParameterList.get(6), sourceComponentId));

		dataSelectionBean.setRelationshipBuilderId(getStringValue(fieldValuesParameterList.get(9), sourceComponentId));
		dataSelectionBean.setForecastLevelId(getStringValue(fieldValuesParameterList.get(10), sourceComponentId));

		dataSelectionBean.setProductGroupName("");
		dataSelectionBean.setInnerProductLevelId(getIntegerValue(fieldValuesParameterList.get(14), sourceComponentId));
		dataSelectionBean
				.setSelectedProducts(getValueFromDualListBox(fieldValuesParameterList.get(20), sourceComponentId));

		callWebServiceToSaveDataSelection(gtnForecastBean, dataSelectionBean);

	}

	private String getStringValue(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getStringFromField();
	}

	private int getIntegerValue(String componentId, String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getIntegerFromField();
	}

	private Object getSelectedId(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId, sourceComponentId).getIdFromField();
	}

	private List<String> getValueFromDualListBox(String componentId, String sourceComponentId) {
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(componentId, sourceComponentId);
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		return getHierarchyNoList(rightTableBeanList);
	}

	private List<String> getHierarchyNoList(List<GtnWsRecordBean> beanList) {
		List<String> hierarchyNoList = new ArrayList<>();
		for (GtnWsRecordBean bean : beanList) {
			hierarchyNoList.add(bean.getAdditionalProperties().get(1).toString());
		}
		return hierarchyNoList;
	}

	private void callWebServiceToSaveDataSelection(GtnForecastBean gtnForecastBean,
			DataSelectionBean dataSelectionBean) {
		GtnWsForecastRequest forecastRequest = new GtnWsForecastRequest();
		forecastRequest.setGtnForecastBean(gtnForecastBean);
		forecastRequest.setDataSelectionBean(dataSelectionBean);
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastRequest(forecastRequest);
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		wsclient.callGtnWebServiceUrl(GtnWsForecastReturnsConstants.GTN_WS_RETURNS_FORECAST_WRITE_DATA_SELECTION_FILE,
				GtnFrameworkCommonStringConstants.FORECAST_MODULE_NAME, request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
