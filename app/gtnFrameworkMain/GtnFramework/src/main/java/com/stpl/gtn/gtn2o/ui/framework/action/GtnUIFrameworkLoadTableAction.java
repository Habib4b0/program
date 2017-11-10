package com.stpl.gtn.gtn2o.ui.framework.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Field;

public class GtnUIFrameworkLoadTableAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<GtnWsRecordBean> records = new ArrayList<>();
		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent((String) params.get(0), componentId);
		GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
		GtnUIFrameworkComponentConfig componentConfig = componentData.getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig tableConfig = componentConfig.getGtnPagedTableConfig();

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = getWSRequest(tableConfig, gtnUIFrameWorkActionConfig,
				componentConfig.getSourceViewId());
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(tableConfig.getResultSetUrl(),
				serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		List<Object> recordHeader = Arrays.asList(tableConfig.getTableColumnMappingId());
		for (GtnUIFrameworkDataRow record : response.getGtnSerachResponse().getResultSet().getDataTable()) {
			GtnWsRecordBean dto = new GtnWsRecordBean();
			dto.setRecordHeader(recordHeader);
			dto.setProperties(record.getColList());
			records.add(dto);
		}
		baseComponent.resetTable();
		baseComponent.addItemsToGrid(records);
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

	private List<GtnWebServiceSearchCriteria> getCurrentSearchCriteria(List<String> vaadinFieldValues,
			List<String> vaadinFieldDescriptionList, String sourceViewId) throws GtnFrameworkValidationFailedException {
		List<GtnWebServiceSearchCriteria> currentSearchCriteria = new ArrayList<>();
		getFieldValues(vaadinFieldValues, sourceViewId, currentSearchCriteria);
		if (vaadinFieldDescriptionList != null) {
			for (String description : vaadinFieldDescriptionList) {
				GtnWebServiceSearchCriteria dataTableSearchCriteria = new GtnWebServiceSearchCriteria();
				GtnUIFrameworkBaseComponent dataTableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(description,
						sourceViewId);
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = dataTableBaseComponent.getComponentConfig();

				String currentValue = dataTableBaseComponent.getCaptionFromComboBox();
				if (currentValue != null && !"".equals(currentValue)) {

					if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
						dataTableSearchCriteria.setFieldId(description);
					} else {
						dataTableSearchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
					}

					dataTableSearchCriteria.setFilterValue1(currentValue);
					setExpressionType(gtnUIFrameworkComponentConfig, dataTableSearchCriteria, currentValue);
					currentSearchCriteria.add(dataTableSearchCriteria);
				}
			}
		}
		return currentSearchCriteria;
	}

	private void getFieldValues(List<String> vaadinFieldValues, String sourceViewId,
			List<GtnWebServiceSearchCriteria> currentSearchCriteria) {
		if (vaadinFieldValues != null) {
			for (String componentId : vaadinFieldValues) {
				GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
				Field<?> vaadinField = (Field<?>) GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(componentId, sourceViewId).getComponent();
				AbstractComponent vaadinAbstractComponent = (AbstractComponent) vaadinField;
				GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig = ((GtnUIFrameworkComponentData) vaadinAbstractComponent
						.getData()).getCurrentComponentConfig();
				if (vaadinField.isEnabled()) {
					String currentValue = getString(vaadinField.getValue());
					if (isNullOrEmpty(currentValue)) {
						setFieldIdToCriteria(gtnUIFrameworkComponentConfig, searchCriteria, componentId);
						searchCriteria.setFilterValue1(getFilterValue(currentValue));
						setExpressionType(gtnUIFrameworkComponentConfig, searchCriteria, currentValue);
						currentSearchCriteria.add(searchCriteria);

					}
				}
			}
		}
	}

	private GtnUIFrameworkWebserviceRequest getWSRequest(GtnUIFrameworkPagedTableConfig tableConfig,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String sourceViewId)
			throws GtnFrameworkValidationFailedException {

		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setExtraParameter(tableConfig.getExtraColumn());

		request.setSearchModuleName(tableConfig.getModuleName());
		request.setSearchQueryName(tableConfig.getQueryName());

		request.setSearchConfigLodaderType(tableConfig.getSearchQueryConfigLoaderType());

		List<Object> requestRecordHeader = new ArrayList<>(Arrays.asList(tableConfig.getTableColumnMappingId()));
		if (tableConfig.getExtraColumn() != null && tableConfig.getExtraColumnDataType() == null) {
			requestRecordHeader.addAll(Arrays.asList(tableConfig.getExtraColumn()));
		}

		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalWSRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		serviceRequest.setGtnWsGeneralRequest(generalWSRequest);
		request.setSearchColumnNameList(requestRecordHeader);
		request.setGtnWebServiceSearchCriteriaList(getCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues(),
				gtnUIFrameWorkActionConfig.getFieldDescription(), sourceViewId));
		request.setGtnWebServiceOrderByCriteriaList(new ArrayList<GtnWebServiceOrderByCriteria>());
		serviceRequest.setGtnWsSearchRequest(request);

		return serviceRequest;
	}

	private void setExpressionType(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig,
			GtnWebServiceSearchCriteria searchCriteria, String currentValue) {
		if (gtnUIFrameworkComponentConfig.getExpressionType() == null) {
			searchCriteria.setExpression(getExpression(currentValue));
		} else {
			searchCriteria.setExpression(gtnUIFrameworkComponentConfig.getExpressionType());
		}
	}

	private String getString(Object value) {
		if (isNull(value)) {
			return "";
		} else if (value instanceof Date) {
			java.sql.Date date = new java.sql.Date(((Date) value).getTime());
			return String.valueOf(date);
		}
		return String.valueOf(value);
	}

	private boolean isNull(Object value) {
		return value == null;
	}

	private boolean isNullOrEmpty(String currentValue) {
		return currentValue != null && !"".equals(currentValue);
	}

	private void setFieldIdToCriteria(GtnUIFrameworkComponentConfig gtnUIFrameworkComponentConfig,
			GtnWebServiceSearchCriteria searchCriteria, String componentId) {
		if (gtnUIFrameworkComponentConfig.getComponentWsFieldId() == null) {
			searchCriteria.setFieldId(componentId);
		} else {
			searchCriteria.setFieldId(gtnUIFrameworkComponentConfig.getComponentWsFieldId());
		}
	}

	private String getFilterValue(String currentValue) {
		return currentValue.contains("'") ? currentValue.replaceAll("'", "''") : currentValue;
	}

	private String getExpression(String currentValue) {
		return currentValue.contains("*") ? "LIKE" : GtnFrameworkCommonConstants.PROPERTY_EQUALS;
	}

}
