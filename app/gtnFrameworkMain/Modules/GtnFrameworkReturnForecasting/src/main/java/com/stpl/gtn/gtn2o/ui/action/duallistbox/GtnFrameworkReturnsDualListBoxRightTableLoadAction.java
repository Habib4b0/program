/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.duallistbox;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
public class GtnFrameworkReturnsDualListBoxRightTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkReturnsDualListBoxRightTableLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		/*
		 * 0 - Custom Action Class Name 1 - Mode 2 - from namespace 3 - to
		 * namespace 4 - forecast level combo box component id 5 - dual list box
		 * component id
		 */

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> list = getInputListToLoadDualListBox(actionParametersList.get(1).toString(), componentId);
		loadDualListBoxRightTable(list, componentId);
		logger.info("Loaded Dual List Box In Returns Data Selection Tab in " + actionParametersList.get(1) + " mode.");
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	private void loadDualListBoxRightTable(List<String> inputList, String sourceComponentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("returnsForecastTabSheet_dualListBoxComp", sourceComponentId);
		baseComponent.loadDualListBoxRightTableData(inputList, sourceComponentId);
	}

	private List<String> getInputListToLoadDualListBox(final String mode, String componentId)
			throws GtnFrameworkValidationFailedException {
		List<String> list = new ArrayList<>();
		if ("generate".equalsIgnoreCase(mode)) {
			list = getInputListOnGenerateMode(componentId);
		} else if ("edit".equalsIgnoreCase(mode) || "view".equalsIgnoreCase(mode)) {
			list = getInputListOnEditOrViewMode(componentId);
		} else if ("workflow".equalsIgnoreCase(mode)) {
			list = getInputListOnWorkflowMode(componentId);
		}
		return list;
	}

	private List<String> getInputListOnGenerateMode(String sourceComponentId)
			throws GtnFrameworkValidationFailedException {
		int forecastLevel = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				"returnsForecastMainScreenDataSelection_forecastLevel", sourceComponentId).getIntegerFromField();
		return getValueFromDualListBox("returnsForecastMainScreenDataSelection_dualListBoxComp", forecastLevel,
				sourceComponentId);
	}

	private List<String> getInputListOnEditOrViewMode(String componentId) throws GtnFrameworkValidationFailedException {
		GtnWsRecordBean currentData = GtnUIFrameworkGlobalUI.getVaadinBaseComponentFromParent(
				"returnsForecastMainScreenDataSelection_dssearchResultTable", componentId).getValueFromDataTable();
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("projectionDetailsTabsheetMainLayout", componentId);
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		return gtnForecastBean.getRightTableHierarchy().get(String.valueOf(currentData.getPropertyValueByIndex(3)));
	}

	private List<String> getInputListOnWorkflowMode(String componentId) throws GtnFrameworkValidationFailedException {
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData("projectionDetailsTabsheetMainLayout", componentId);
		GtnForecastBean gtnForecastBean = (GtnForecastBean) gtnUIFrameworkComponentData.getCustomData();
		return gtnForecastBean.getRightTableHierarchy().get(String.valueOf(gtnForecastBean.getProductForecastLevel()));
	}

	private List<String> getValueFromDualListBox(String componentId, int levelNo, String sourceComponentId) {
		logger.info("insde getValueFromDualListBox  " + componentId);
		GtnUIFrameworkBaseComponent gtnUIFrameworkBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponentFromParent(componentId, sourceComponentId);
		List<GtnWsRecordBean> rightTableBeanList = gtnUIFrameworkBaseComponent.getDualListBoxRightTableData();
		return getHierarchyNoAsList(rightTableBeanList, levelNo);
	}

	private List<String> getHierarchyNoAsList(List<GtnWsRecordBean> beanList, int levelNo) {
		List<String> hierarchyNoList = new ArrayList<>();
		for (GtnWsRecordBean bean : beanList) {
			if (Integer.valueOf(bean.getAdditionalProperties().get(2).toString()).equals(levelNo)) {
				hierarchyNoList.add(bean.getAdditionalProperties().get(1).toString());
			}
		}
		return hierarchyNoList;
	}

}
