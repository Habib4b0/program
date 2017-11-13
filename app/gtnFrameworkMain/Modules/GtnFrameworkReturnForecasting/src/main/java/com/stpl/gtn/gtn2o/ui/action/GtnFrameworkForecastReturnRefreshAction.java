/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.ui.AbstractComponent;

/**
 *
 * @author Sibi.Chakaravarthy
 */
public class GtnFrameworkForecastReturnRefreshAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastReturnRefreshAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		gtnLogger.info("inside GtnFrameworkForecastReturnRefreshAction");

		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();
		AbstractComponent abstractComponent = GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(actionParametersList.get(1)), componentId);
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) abstractComponent.getData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		refreshTable(tableLogic,
				getHierarchyNoBasedOnAction(componentData, tableLogic, (Boolean) actionParametersList.get(2)));
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	/**
	 * Hierarchy No. used in this method is the hierarchy no. internally used in
	 * the table logic.
	 *
	 * @param componentData
	 * @param tableLogic
	 * @param isCalculate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Set<String> getHierarchyNoBasedOnAction(GtnUIFrameworkComponentData componentData,
			GtnUIFrameworkPagedTreeTableLogic tableLogic, boolean isCalculate) {
		Set<String> refreshRecordSet;
		if (isCalculate) {
			refreshRecordSet = (Set<String>) componentData.getCustomDataList().get(1);
		} else {
			refreshRecordSet = (Set<String>) componentData.getCustomDataList().get(2);
		}
		return configureParentAndChildNodeForRefresh(tableLogic, refreshRecordSet, isCalculate);
	}

	/**
	 * Hierarchy No. used in this method is the hierarchy no. internally used in
	 * the table logic.
	 *
	 * @param tableLogic
	 * @param editedRecordTableHierarchyNoSet
	 * @return
	 */
	private Set<String> configureParentAndChildNodeForRefresh(GtnUIFrameworkPagedTreeTableLogic tableLogic,
			Set<String> editedRecordTableHierarchyNoSet, boolean isCalculate) {
		final Set<String> finalTableHierarchyNoSet = new HashSet<>();
		for (String tableHierarchyNo : editedRecordTableHierarchyNoSet) {
			finalTableHierarchyNoSet.addAll(tableLogic.getAllParentLevels(tableHierarchyNo));
			finalTableHierarchyNoSet.addAll(tableLogic.getAllChildLevels(tableHierarchyNo));
			finalTableHierarchyNoSet.add(tableHierarchyNo);
		}
		if (!isCalculate) {
			editedRecordTableHierarchyNoSet.clear();
		}
		return finalTableHierarchyNoSet;
	}

	private void refreshTable(GtnUIFrameworkPagedTreeTableLogic tableLogic, Set<String> hierarchyNo) {
		tableLogic.forRefresh(hierarchyNo);
		tableLogic.setCurrentPage(tableLogic.getCurrentPage());
	}

}
