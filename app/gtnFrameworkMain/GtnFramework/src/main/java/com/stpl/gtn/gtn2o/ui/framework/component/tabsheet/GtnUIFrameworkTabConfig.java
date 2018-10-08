package com.stpl.gtn.gtn2o.ui.framework.component.tabsheet;

import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnUIFrameworkTabConfig {

	public GtnUIFrameworkTabConfig() {
		super();
	}

	private String componentId = null;
	private String tabCaption = null;
	private GtnUIFrameworkTabSheetLoadType tabloadingType = GtnUIFrameworkTabSheetLoadType.EARLY_LOAD;
	private List<GtnUIFrameworkComponentConfig> tabLayoutComponentConfigList = null;
	private boolean isDefaultTab = false;
	private int tabIndex;

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getTabCaption() {
		return tabCaption;
	}

	public void setTabCaption(String tabCaption) {
		this.tabCaption = tabCaption;
	}

	public GtnUIFrameworkTabSheetLoadType getTabloadingType() {
		return tabloadingType;
	}

	public void setTabloadingType(GtnUIFrameworkTabSheetLoadType tabloadingType) {
		this.tabloadingType = tabloadingType;
	}

	public List<GtnUIFrameworkComponentConfig> getTabLayoutComponentConfigList() {
		return tabLayoutComponentConfigList == null ? tabLayoutComponentConfigList
				: Collections.unmodifiableList(tabLayoutComponentConfigList);
	}

	public void setTabLayoutComponentConfigList(List<GtnUIFrameworkComponentConfig> tabLayoutComponentConfigList) {
		this.tabLayoutComponentConfigList = Collections.unmodifiableList(tabLayoutComponentConfigList);

	}

	public boolean isDefaultTab() {
		return isDefaultTab;
	}

	public void setDefaultTab(boolean isDefaultTab) {
		this.isDefaultTab = isDefaultTab;
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

}
