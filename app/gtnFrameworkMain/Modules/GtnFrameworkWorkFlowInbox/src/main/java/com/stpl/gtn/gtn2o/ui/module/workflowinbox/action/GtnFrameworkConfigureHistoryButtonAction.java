package com.stpl.gtn.gtn2o.ui.module.workflowinbox.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxTableConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkConfigureHistoryButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		Object[] visibleColumnHistory = null;
		String[] visibleHeaderHistory = null;
		Class<?>[] dataTypeHistory = null;
		List<GtnUIFrameworkComponentConfig> componentList;

		String combocomponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("businessProcess")
				.getCaptionFromComboBox();

		if (combocomponent.equals(GtnFrameworkCommonStringConstants.ARM)) {
			componentList = new ArrayList<>();
			dataTypeHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryArmSearchTableColumnsDataType();
			visibleHeaderHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryArmSearchTableHeaders();
			visibleColumnHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryArmSearchTableColumns();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNOLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNO)
					.setVisible(true);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMELAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME)
					.setVisible(true);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNOLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO)
					.setVisible(true);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMELAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME)
					.setVisible(true);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOW_DESC)
					.setVisible(true);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONELAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONE)
					.setVisible(true);

			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.ATTACHMENTRESULT_PANEL)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYIDLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYID_HISTORY)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID)
					.setVisible(false);
		} else {

			componentList = new ArrayList<>();
			dataTypeHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryNotArmSearchTableColumnsDataType();
			visibleHeaderHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryNotArmSearchTableHeaders();
			visibleColumnHistory = GtnFrameworkWorkflowInboxTableConstants
					.getGtnWorkflowInboxHistoryNotArmSearchTableColumns();

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYIDLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYID_HISTORY)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDLAYOUT)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID)
					.setVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNOLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMELAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNOLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMELAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCLAYOUT)
					.setVisible(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCONELAYOUT)
					.setVisible(false);
		}

		GtnUIFrameworkComponentConfig searchResultConfig = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(GtnFrameworkWorkflowInboxClassConstants.HISTORYSEARCHRESULT_TABLE)
				.getCurrentComponentConfig();
		GtnUIFrameworkPagedTableConfig searchResults = searchResultConfig.getGtnPagedTableConfig();
		searchResults.setTableColumnDataType(dataTypeHistory);
		searchResults.setTableVisibleHeader(visibleHeaderHistory);
		searchResults.setTableColumnMappingId(visibleColumnHistory);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		componentList.add(searchResultConfig);
		GtnUIFrameworkGlobalUI.addChildComponent(GtnFrameworkWorkflowInboxClassConstants.HISTORYDETAILSRESULT_LAYOUT,
				componentList);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
