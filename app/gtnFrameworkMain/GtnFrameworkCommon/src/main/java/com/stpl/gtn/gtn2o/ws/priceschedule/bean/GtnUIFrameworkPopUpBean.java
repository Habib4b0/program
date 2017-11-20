/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.priceschedule.bean;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mahesh.James
 */
public class GtnUIFrameworkPopUpBean {

	private String viewName;
	private String propertyId;
	private String sourceTableName;
	private String destinationComponentId;
	private List<String> sourcePropertyIdList;
	private List<String> destinaComponentIdList;
	private String selectButtonId;
	private Integer ruleTypeId;
	private String caption;

	public GtnUIFrameworkPopUpBean(String viewName) {
		this.viewName = viewName;
	}

	public String getSourceTableName() {
		return sourceTableName;
	}

	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}

	public String getDestinationComponentId() {
		return destinationComponentId;
	}

	public void setDestinationComponentId(String destinationComponentId) {
		this.destinationComponentId = destinationComponentId;
	}

	public List<String> getSourcePropertyIdList() {
		return sourcePropertyIdList == null ? sourcePropertyIdList : Collections.unmodifiableList(sourcePropertyIdList);
	}

	public void setSourcePropertyIdList(List<String> sourcePropertyIdList) {
		this.sourcePropertyIdList = sourcePropertyIdList == null ? sourcePropertyIdList
				: Collections.unmodifiableList(sourcePropertyIdList);
	}

	public List<String> getDestinaComponentIdList() {
		return destinaComponentIdList != null ? Collections.unmodifiableList(destinaComponentIdList)
				: destinaComponentIdList;
	}

	public void setDestinaComponentIdList(List<String> destinaComponentIdList) {
		this.destinaComponentIdList = destinaComponentIdList != null
				? Collections.unmodifiableList(destinaComponentIdList)
				: destinaComponentIdList;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getSelectButtonId() {
		return selectButtonId;
	}

	public void setSelectButtonId(String selectButtonId) {
		this.selectButtonId = selectButtonId;
	}

	public Integer getRuleTypeId() {
		return ruleTypeId;
	}

	public void setRuleTypeId(Integer ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}