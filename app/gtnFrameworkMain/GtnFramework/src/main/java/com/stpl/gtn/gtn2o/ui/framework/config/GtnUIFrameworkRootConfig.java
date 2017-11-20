package com.stpl.gtn.gtn2o.ui.framework.config;

import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;

public class GtnUIFrameworkRootConfig {

	private List<GtnUIFrameworkViewConfig> gtnViewConfigList = null;

	public List<GtnUIFrameworkViewConfig> getGtnViewConfigList() {
		return gtnViewConfigList == null ? gtnViewConfigList : Collections.unmodifiableList(gtnViewConfigList);
	}

	public void setGtnViewConfigList(List<GtnUIFrameworkViewConfig> gtnViewConfigList) {
		this.gtnViewConfigList = Collections.unmodifiableList(gtnViewConfigList);
	}
}