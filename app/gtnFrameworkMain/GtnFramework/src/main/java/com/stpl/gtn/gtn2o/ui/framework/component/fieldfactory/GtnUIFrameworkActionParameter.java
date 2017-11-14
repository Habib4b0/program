/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 *
 * @author Harlin.Mani
 */
public class GtnUIFrameworkActionParameter {

	private GtnWsRecordBean itemId;
	private String propertyId;
	private Object oldValue;
	private Object currentValue;
	private Object actionOutput;
	private Object actionInput;
	private Object lastParent;
	private Map<String, Object> loadBulkMap;
	private List<Object> recordHeader;
	private int levelNo;
	private GtnUIFrameworkComponentConfig vaadinComponentConfig;
	private String vaadinComponentId;
	private String componentIdPrefix;
	private String tableComponentId;

	public GtnUIFrameworkActionParameter() {
		super();
	}

	public GtnWsRecordBean getItemId() {
		return itemId;
	}

	public void setItemId(GtnWsRecordBean itemId) {
		this.itemId = itemId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}

	public Object getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Object currentValue) {
		this.currentValue = currentValue;
	}

	public Object getActionOutput() {
		return actionOutput;
	}

	public void setActionOutput(Object actionOutput) {
		this.actionOutput = actionOutput;
	}

	public Object getActionInput() {
		return actionInput;
	}

	public void setActionInput(Object actionInput) {
		this.actionInput = actionInput;
	}

	public Object getLastParent() {
		return lastParent;
	}

	public void setLastParent(Object lastParent) {
		this.lastParent = lastParent;
	}

	public Map<String, Object> getLoadBulkMap() {
		return loadBulkMap;
	}

	public void setLoadBulkMap(Map<String, Object> loadBulkMap) {
		this.loadBulkMap = loadBulkMap;
	}

	public List<Object> getRecordHeader() {
		return recordHeader == null ? recordHeader : Collections.unmodifiableList(recordHeader);
	}

	public void setRecordHeader(List<Object> recordHeader) {
		this.recordHeader = new ArrayList<>(recordHeader);
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public GtnUIFrameworkComponentConfig getVaadinComponentConfig() {
		return vaadinComponentConfig;
	}

	public void setVaadinComponentConfig(GtnUIFrameworkComponentConfig vaadinComponentConfig) {
		this.vaadinComponentConfig = vaadinComponentConfig;
	}

	public String getVaadinComponentId() {
		return vaadinComponentId;
	}

	public void setVaadinComponentId(String vaadinComponentId) {
		this.vaadinComponentId = vaadinComponentId;
	}

	public String getComponentIdPrefix() {
		return componentIdPrefix;
	}

	public void setComponentIdPrefix(String componentIdPrefix) {
		this.componentIdPrefix = componentIdPrefix;
	}

	public String getTableComponentId() {
		return tableComponentId;
	}

	public void setTableComponentId(String tableComponentId) {
		this.tableComponentId = tableComponentId;
	}

}