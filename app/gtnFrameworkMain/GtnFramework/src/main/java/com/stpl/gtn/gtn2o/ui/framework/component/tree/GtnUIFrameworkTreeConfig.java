/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkTreeConfig {

	private boolean selectable = false;
	private boolean multiSelect = false;
	private String itemCaptionPropertyId = null;
	private String itemCaptionPropertyIdType = null;
	private List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
	private List<GtnUIFrameWorkActionConfig> expandActionConfigList = new ArrayList<>();
	private List<GtnUIFrameWorkActionConfig> collapseActionConfigList = new ArrayList<>();
	private boolean dragMode = false;
	private GtnUIFrameWorkActionConfig dropHandlerAction = null;

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public String getItemCaptionPropertyId() {
		return itemCaptionPropertyId;
	}

	public void setItemCaptionPropertyId(String itemCaptionPropertyId) {
		this.itemCaptionPropertyId = itemCaptionPropertyId;
	}

	public String getItemCaptionPropertyIdType() {
		return itemCaptionPropertyIdType;
	}

	public void setItemCaptionPropertyIdType(String itemCaptionPropertyIdType) {
		this.itemCaptionPropertyIdType = itemCaptionPropertyIdType;
	}

	public List<GtnUIFrameWorkActionConfig> getItemClickActionConfigList() {
		return itemClickActionConfigList == null ? itemClickActionConfigList
				: Collections.unmodifiableList(itemClickActionConfigList);
	}

	public void setItemClickActionConfigList(List<GtnUIFrameWorkActionConfig> itemClickActionConfigList) {
		this.itemClickActionConfigList = new ArrayList<>(itemClickActionConfigList);
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

	public void addItemClickActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
		if (this.itemClickActionConfigList == null) {
			this.itemClickActionConfigList = new ArrayList<>();
		}
		this.itemClickActionConfigList.add(actionConfig);
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

	public boolean isDragMode() {
		return dragMode;
	}

	public void setDragMode(boolean dragMode) {
		this.dragMode = dragMode;
	}

	public GtnUIFrameWorkActionConfig getDropHandlerAction() {
		return dropHandlerAction;
	}

	public void setDropHandlerAction(GtnUIFrameWorkActionConfig dropHandlerAction) {
		this.dropHandlerAction = dropHandlerAction;
	}

}
