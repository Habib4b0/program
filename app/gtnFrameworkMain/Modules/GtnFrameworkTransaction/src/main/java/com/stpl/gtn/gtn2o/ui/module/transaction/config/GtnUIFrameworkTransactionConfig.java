package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkTransactionConfig {

	private List<GtnUIFrameworkViewConfig> viewList = new ArrayList<>();

	public GtnUIFrameworkRootConfig getMainTransactionRootConfig(boolean isInvalid) {

		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();

		generateTempleteForTransaction(isInvalid);

		rootConfig.setGtnViewConfigList(viewList);
		return rootConfig;
	}

	private void generateTempleteForTransaction(boolean isInvalid) {
		viewList.add(new GtnFrameworkTransactionSearchTemplateConfig(isInvalid).getSearchView());

		viewList.add(new GtnFrameworkTransactionViewTemplateConfig(isInvalid).getTransactionView());

	}
}
