package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.dataselection.GtnFrameworkWorkflowInboxDataSelectionConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup.GtnUIFrameworkWorkflowApprovedByPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup.GtnUIFrameworkWorkflowHistoryPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup.GtnUIFrameworkWorkflowPublicPrivateViewPopupConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup.GtnUIFrameworkWorkflowSaveProfilePopupConfig;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;

public class GtnFrameworkWorkflowInboxConfig {

	public GtnUIFrameworkRootConfig getGtnWorkflowInboxRootConfig() {

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();

		viewList.add(new GtnFrameworkWorkflowInboxDataSelectionConfig().getSearchView());
		viewList.add(new GtnUIFrameworkWorkflowPublicPrivateViewPopupConfig().getSearchView("private"));
		viewList.add(new GtnUIFrameworkWorkflowPublicPrivateViewPopupConfig().getSearchView("public"));
		viewList.add(new GtnUIFrameworkWorkflowApprovedByPopupConfig()
				.getSearchView(GtnFrameworkWorkflowInboxClassConstants.CREATEDBY));
		viewList.add(new GtnUIFrameworkWorkflowSaveProfilePopupConfig().getSearchView());
		viewList.add(new GtnUIFrameworkWorkflowApprovedByPopupConfig()
				.getSearchView(GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY));
		viewList.add(new GtnUIFrameworkWorkflowHistoryPopupConfig().getSearchView());
		rootConfig.setGtnViewConfigList(viewList);

		return rootConfig;
	}

}
