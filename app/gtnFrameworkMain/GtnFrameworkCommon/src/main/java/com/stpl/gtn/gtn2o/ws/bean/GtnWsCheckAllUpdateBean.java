/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.bean;

import java.util.Map;

/**
 *
 * @author Mahesh.James
 */
public class GtnWsCheckAllUpdateBean {

	public GtnWsCheckAllUpdateBean() {
		super();
	}

	private boolean checkAll;
	private String propertyId;
	private Object value;
	private int masterSid;
	private Map<String, String> propertyValueMap;
	private String propertyId1;
	private Object value1;

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public int getMasterSid() {
		return masterSid;
	}

	public void setMasterSid(int masterSid) {
		this.masterSid = masterSid;
	}

	public Map<String, String> getPropertyValueMap() {
		return propertyValueMap;
	}

	public void setPropertyValueMap(Map<String, String> propertyValueMap) {
		this.propertyValueMap = propertyValueMap;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue1() {
		return value1;
	}

	public void setValue1(Object value1) {
		this.value1 = value1;
	}

	public String getPropertyId1() {
		return propertyId1;
	}

	public void setPropertyId1(String propertyId1) {
		this.propertyId1 = propertyId1;
	}

}