/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkNewTreeTableConfig {

	private GtnUIFrameWorkActionConfig childDetectActionConfig = null;

	private List<GtnUIFrameWorkActionConfig> expandActionConfigList = new ArrayList<>();

	private List<GtnUIFrameWorkActionConfig> collapseActionConfigList = new ArrayList<>();

	public GtnUIFrameWorkActionConfig getChildDetectActionConfig() {
		return childDetectActionConfig;
	}

	public void setChildDetectActionConfig(GtnUIFrameWorkActionConfig childDetectActionConfig) {
		this.childDetectActionConfig = childDetectActionConfig;
	}

	public List<GtnUIFrameWorkActionConfig> getExpandActionConfigList() {
		return expandActionConfigList == null ? expandActionConfigList
				: Collections.unmodifiableList(expandActionConfigList);
	}

	public void setExpandActionConfigList(List<GtnUIFrameWorkActionConfig> expandActionConfigList) {
		this.expandActionConfigList = new ArrayList<>(expandActionConfigList);
	}

	public List<GtnUIFrameWorkActionConfig> getCollapseActionConfigList() {
		return collapseActionConfigList == null ? collapseActionConfigList
				: Collections.unmodifiableList(collapseActionConfigList);
	}

	public void setCollapseActionConfigList(List<GtnUIFrameWorkActionConfig> collapseActionConfigList) {
		this.collapseActionConfigList = new ArrayList<>(collapseActionConfigList);
	}

	public void addExpandActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.expandActionConfigList == null) {
			this.expandActionConfigList = new ArrayList<>();
		}
		this.expandActionConfigList.add(actionConfig);
	}

	public void addCollapseActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.collapseActionConfigList == null) {
			this.collapseActionConfigList = new ArrayList<>();
		}
		this.collapseActionConfigList.add(actionConfig);
	}
}
