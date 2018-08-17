package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.customview.constants.GtnWsCustomViewConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkReportOptionsViewOptionsAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkReportOptionsViewOptionsAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Empty 
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		// Getting level information from custom view
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest customViewRequest = new GtnWsCustomViewRequest();
		String customViewSelectedItem = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("dataSelectionTab_displaySelectionTabCustomView", componentId)
				.getV8StringFromField();
		if (!"".equals(customViewSelectedItem) && !"0".equals(customViewSelectedItem)) {
			customViewRequest.setCvSysId(Integer.parseInt(customViewSelectedItem));
			request.setGtnWsCustomViewRequest(customViewRequest);
			GtnUIFrameworkWebserviceResponse generalResponse = new GtnUIFrameworkWebServiceClient()
					.callGtnWebServiceUrl(
							GtnWsCustomViewConstants.GTN_CUSTOM_VIEW_SERVICE
									+ GtnWsCustomViewConstants.CUSTOM_VIEW_LEVEL_DATA,
							request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = generalResponse
					.getGtnUIFrameworkWebserviceComboBoxResponse();

			List<String> levelList = comboBoxResponse.getItemValueList();
			int levelListSize = levelList.size();
			int lastLevelNumber = levelListSize;

			List<Object> actionParameterList = (gtnUIFrameWorkActionConfig.getActionParameterList());
			String viewOption = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId)
					.getCaptionFromV8ComboBox();

			if ("2".equals(viewOption)) {
				expandTreeGrid(actionParameterList, componentId, lastLevelNumber);
			}

			if ("1".equals(viewOption)) {
				collapseTreeGrid(actionParameterList, componentId, lastLevelNumber);
			}
		}
	}

	// Method to make the tree grid fully expanded if view options is set to fully expanded
	private void expandTreeGrid(List<Object> actionParameterList, String componentId, int levelNo) {

		logger.info("================Inside Expand Tree Grid============");
		GtnUIFrameworkComponentData tableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) actionParameterList.get(1), componentId);
		PagedTreeGrid grid = (PagedTreeGrid) tableComponentData.getCustomData();
		grid.expandAll(levelNo);
	}

	// Method to make the tree grid collapsed if view options is set to standard
	private void collapseTreeGrid(List<Object> actionParams, String sourceComponentId, int levelNumber) {

		logger.info("================Inside Collapse Tree Grid============");
		GtnUIFrameworkComponentData tableComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) actionParams.get(1), sourceComponentId);
		PagedTreeGrid pagedTreeGrid = (PagedTreeGrid) tableComponentData.getCustomData();
		pagedTreeGrid.collapseAll(levelNumber);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
