package com.stpl.gtn.gtn2o.ui.framework.action.duallistbox.v8;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkV8DualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import java.util.Locale;

public class GtnUIFrameWorkV8DualListBoxLoadRightTableBulkAction implements GtnUIFrameWorkAction {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkComponentData dualListBoxData = (GtnUIFrameworkComponentData) actionParametersList.get(0);
		GtnFrameworkV8DualListBoxBean dualListBoxBean = (GtnFrameworkV8DualListBoxBean) dualListBoxData.getCustomData();
		loadRightTableBulk(dualListBoxBean);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public void loadRightTableBulk(GtnFrameworkV8DualListBoxBean dualListBoxBean) {
		GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig = dualListBoxBean.getDualListBoxConfig();
		GtnUIFrameworkHierarchyTreeBuilder treeBuilder = dualListBoxBean.getTreeBuilder();
		Map<String, String> levelValueMap = (Map<String, String>) dualListBoxBean.getGtnDualListBoxqueryParameters()
				.get(1);
		boolean isProduct = (boolean) dualListBoxBean.getGtnDualListBoxqueryParameters().get(8);
		TreeGrid<GtnWsRecordBean> rightTable = dualListBoxBean.getRightTable();
		Grid<GtnWsRecordBean> availableGrid = dualListBoxBean.getLeftTable();
		Component component = availableGrid.getHeaderRow(1).getCell("levelValue").getComponent();
		HorizontalLayout horizontalLayout = (HorizontalLayout) component;
		TextField textField = (TextField) horizontalLayout.getComponent(0);
		String filterText = textField.getValue();
		ListDataProvider<GtnWsRecordBean> availableDataProvider = (ListDataProvider<GtnWsRecordBean>) availableGrid
				.getDataProvider();
		List<GtnWsRecordBean> gtnWsRecordBeanList = new ArrayList<>();
		List<GtnWsRecordBean> availableRecords = (List<GtnWsRecordBean>) availableDataProvider.getItems();
		for (GtnWsRecordBean recordBean : availableRecords) {
			if (recordBean.getPropertyValue("levelValue").toString().toLowerCase()
					.contains(filterText.toLowerCase(Locale.ENGLISH))) {
				gtnWsRecordBeanList.add(recordBean);
			}
		}

		GtnUIFrameworkWebserviceResponse response = callWebService(dualListBoxConfig.getMoveAllDataURL(),
				createRightTableRequest(dualListBoxBean.getGtnDualListBoxqueryParameters(), dualListBoxConfig,
						isProduct, gtnWsRecordBeanList),
				dualListBoxConfig);

		List<GtnWsRecordBean> selectedGridList = new ArrayList<>();

		for (GtnUIFrameworkDataRow data : response.getGtnSerachResponse().getResultSet().getDataTable()) {
			GtnWsRecordBean selectedRecordBean = new GtnWsRecordBean();
			selectedRecordBean.setProperties(data.getColList());
			selectedRecordBean.addProperties(levelValueMap.get(data.getColumnVAlue(8)));
			selectedRecordBean.setRecordHeader(dualListBoxConfig.getRightRecordHeader());
			selectedGridList.add(selectedRecordBean);
		}

		treeBuilder.buildTree(selectedGridList);
		treeBuilder.loadRightTreeTable(rightTable, dualListBoxConfig.getLoadingLevel());
		rightTable.getDataProvider().refreshAll();
		rightTable.markAsDirty();
	}

	private GtnWsReportRequest createReportRequest(final List<Object> queryParameters, boolean isProduct,
			List<GtnWsRecordBean> recordBeanList) {
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setHierarchyInputBean((GtnReportHierarchyLevelBean) queryParameters.get(2));
		reportRequest.setHierarchyLevelList((List<GtnReportHierarchyLevelBean>) queryParameters.get(3));
		if (!isProduct) {
			reportRequest.setForecastEligibleDate((Date) queryParameters.get(7));
		}
		reportRequest.setRecordBean(recordBeanList);
		return reportRequest;
	}

	private GtnWsSearchRequest createSearchRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setQueryInputList(queryParameters);
		searchRequest.setSearchColumnNameList(new ArrayList<>(dualListBoxConfig.getRecordHeader()));
		return searchRequest;
	}

	private GtnWsGeneralRequest createGeneralRequest() {
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		return generalRequest;
	}

	private GtnUIFrameworkWebserviceRequest createRightTableRequest(final List<Object> queryParameters,
			GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig, boolean isProduct,
			List<GtnWsRecordBean> recordBeanList) {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsSearchRequest(createSearchRequest(queryParameters, dualListBoxConfig));
		request.setGtnWsGeneralRequest(createGeneralRequest());
		request.setGtnWsReportRequest(createReportRequest(queryParameters, isProduct, recordBeanList));
		return request;
	}

	private GtnUIFrameworkWebserviceResponse callWebService(final String webServiceUrl,
			final GtnUIFrameworkWebserviceRequest request, GtnUIFrameworkV8DualListBoxConfig dualListBoxConfig) {
		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		return client.callGtnWebServiceUrl(webServiceUrl, dualListBoxConfig.getModuleType(), request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

}
