package com.stpl.gtn.gtn2o.ui.framework.engine.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

public class GtnUIFrameworkViewConfig {

	private String viewName = null;
	private String viewId = null;
	private boolean isDefaultView = false;

	/*
	 * Set this flag as true whenever a particular view should be loaded
	 * multiple times on user action
	 */
	private boolean replicable = false;

	/*
	 * Set this flag as true when a view need to be added to navigator or else
	 * set this flag as false
	 */
	private boolean addToNavigator = true;

	private boolean isResetAllowed = true;

	private List<GtnUIFrameWorkActionConfig> viewActionList = null;

	private List<GtnUIFrameworkComponentConfig> gtnComponentList = null;

	private List<String> componentStyle = new ArrayList<>();

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public boolean isDefaultView() {
		return isDefaultView;
	}

	public void setDefaultView(boolean isDefaultView) {
		this.isDefaultView = isDefaultView;
	}

	public List<GtnUIFrameworkComponentConfig> getGtnComponentList() {
		return gtnComponentList == null ? gtnComponentList : Collections.unmodifiableList(gtnComponentList);
	}

	public void setGtnComponentList(List<GtnUIFrameworkComponentConfig> gtnComponentList) {
		this.gtnComponentList = Collections.unmodifiableList(gtnComponentList);
	}

	public boolean isReplicable() {
		return replicable;
	}

	public void setReplicable(boolean replicable) {
		this.replicable = replicable;
	}

	public boolean isAddToNavigator() {
		return addToNavigator;
	}

	public void setAddToNavigator(boolean addToNavigator) {
		this.addToNavigator = addToNavigator;
	}

	public List<GtnUIFrameWorkActionConfig> getViewActionList() {
		return viewActionList == null ? viewActionList : Collections.unmodifiableList(viewActionList);
	}

	public void setViewActionList(List<GtnUIFrameWorkActionConfig> viewActionList) {
		this.viewActionList = new ArrayList<>(viewActionList);
	}

	public void addViewAction(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		if (this.viewActionList == null) {
			this.viewActionList = new ArrayList<>();
		}
		this.viewActionList.add(gtnUIFrameWorkActionConfig);
	}

	public boolean isResetAllowed() {
		return isResetAllowed;
	}

	public void setResetAllowed(boolean isResetAllowed) {
		this.isResetAllowed = isResetAllowed;
	}

	public List<String> getComponentStyle() {
		return componentStyle == null ? componentStyle : Collections.unmodifiableList(componentStyle);
	}

	public void setComponentStyle(List<String> componentStyle) {
		this.componentStyle = new ArrayList<>(componentStyle);
	}

	public void addComponentStyle(String componentStyle) {
		if (componentStyle == null) {
			return;
		}
		this.componentStyle.add(componentStyle);
	}
}