/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;

/**
 *
 * @author STPL
 */
public class DataSelectionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSelectionBean() {
		super();
	}

	private String projectionName;

	private String projectionDescription;

	private String publicViewName;

	private String privateViewName;

	private int glCompanyId;

	private int businessUnitId;

	private String productHierarchyName;

	@JsonDeserialize(as = Object.class)
	private Serializable productHierarchyId;

	@JsonDeserialize(as = Object.class)
	private Serializable relationshipBuilderId;

	@JsonDeserialize(as = Object.class)
	private Serializable forecastLevelId;

	private int innerProductLevelId;

	private int fromDate;

	private int toDate;

	private String productGroupName;

	private int productGroupId;

	private List<String> selectedProducts;

	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public String getProjectionDescription() {
		return projectionDescription;
	}

	public void setProjectionDescription(String projectionDescription) {
		this.projectionDescription = projectionDescription;
	}

	public String getPublicViewName() {
		return publicViewName;
	}

	public void setPublicViewName(String publicViewName) {
		this.publicViewName = publicViewName;
	}

	public String getPrivateViewName() {
		return privateViewName;
	}

	public void setPrivateViewName(String privateViewName) {
		this.privateViewName = privateViewName;
	}

	public int getGlCompanyId() {
		return glCompanyId;
	}

	public void setGlCompanyId(int glCompanyId) {
		this.glCompanyId = glCompanyId;
	}

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getProductHierarchyName() {
		return productHierarchyName;
	}

	public void setProductHierarchyName(String productHierarchyName) {
		this.productHierarchyName = productHierarchyName;
	}

	public Object getProductHierarchyId() {
		return productHierarchyId;
	}

	public void setProductHierarchyId(Object productHierarchyId) {
		this.productHierarchyId = (Serializable) productHierarchyId;
	}

	public Object getRelationshipBuilderId() {
		return relationshipBuilderId;
	}

	public void setRelationshipBuilderId(Object relationshipBuilderId) {
		this.relationshipBuilderId = (Serializable) relationshipBuilderId;
	}

	public Object getForecastLevelId() {
		return forecastLevelId;
	}

	public void setForecastLevelId(Object forecastLevelId) {
		this.forecastLevelId = (Serializable) forecastLevelId;
	}

	public int getInnerProductLevelId() {
		return innerProductLevelId;
	}

	public void setInnerProductLevelId(int innerProductLevelId) {
		this.innerProductLevelId = innerProductLevelId;
	}

	public int getFromDate() {
		return fromDate;
	}

	public void setFromDate(int fromDate) {
		this.fromDate = fromDate;
	}

	public int getToDate() {
		return toDate;
	}

	public void setToDate(int toDate) {
		this.toDate = toDate;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public int getProductGroupId() {
		return productGroupId;
	}

	public void setProductGroupId(int productGroupId) {
		this.productGroupId = productGroupId;
	}

	public List<String> getSelectedProducts() {
		return selectedProducts != null ? new ArrayList<>(selectedProducts) : selectedProducts;
	}

	public void setSelectedProducts(List<String> selectedProducts) {
		this.selectedProducts = selectedProducts != null ? new ArrayList<>(selectedProducts) : selectedProducts;
	}

}
