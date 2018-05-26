/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;
import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkDataAssumptionsTabConfig;
import java.util.Arrays;
import java.util.Collection;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.combobox.GtnUIFrameworkComboBoxComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.GtnUIFrameworkHierarchyTreeBuilder;
import com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox.bean.GtnFrameworkV8DualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TreeGrid;
import java.util.ArrayList;
import java.util.List;

public class GtnReportDataAssumptionsTabLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataAssumptionsTabLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
final List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();

                    final TabSheet tabSheet = (TabSheet) getAbstractComponent(String.valueOf(actionParameterList.get(1)),
					componentId);
                   
                    addTab(tabSheet, actionParameterList, componentId);
                    
		} catch (Exception exception) {
			logger.error("Error message", exception);
		}
	}
        
        private void addTab(final TabSheet tabSheet, List<Object> actionParameterList,String sourceComponentId) {
		AbstractComponent currentComponentTab = GtnUIFrameworkGlobalUI
				.getVaadinComponent(String.valueOf(actionParameterList.get(2)), sourceComponentId);
                 for(int i=0;i<10;i++){
                     currentComponentTab.setCaption("2016"+i);
		tabSheet.addTab(currentComponentTab, i);
                 }
	}

        private AbstractComponent getAbstractComponent(String componentId, String sourceComponentId) {
		return GtnUIFrameworkGlobalUI.getVaadinComponent(String.valueOf(componentId), sourceComponentId);
	}
        
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}

