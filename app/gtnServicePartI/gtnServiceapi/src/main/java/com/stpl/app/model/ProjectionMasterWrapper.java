/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ProjectionMaster}.
 * </p>
 *
 * @author
 * @see ProjectionMaster
 * @generated
 */
@ProviderType
public class ProjectionMasterWrapper implements ProjectionMaster,
	ModelWrapper<ProjectionMaster> {
	public ProjectionMasterWrapper(ProjectionMaster projectionMaster) {
		_projectionMaster = projectionMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ProjectionMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ProjectionMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("productHierarchyLevel", getProductHierarchyLevel());
		attributes.put("saveFlag", getSaveFlag());
		attributes.put("projectionName", getProjectionName());
		attributes.put("toDate", getToDate());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("forecastingType", getForecastingType());
		attributes.put("productHierVersionNo", getProductHierVersionNo());
		attributes.put("customerHierVersionNo", getCustomerHierVersionNo());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("customerHierarchyLevel", getCustomerHierarchyLevel());
		attributes.put("fromDate", getFromDate());
		attributes.put("productHierarchySid", getProductHierarchySid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("customerHierarchySid", getCustomerHierarchySid());
		attributes.put("companyGroupSid", getCompanyGroupSid());
		attributes.put("brandType", getBrandType());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("projectionDescription", getProjectionDescription());
		attributes.put("isApproved", getIsApproved());
		attributes.put("itemGroupSid", getItemGroupSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("customerHierarchyInnerLevel",
			getCustomerHierarchyInnerLevel());
		attributes.put("productHierarchyInnerLevel",
			getProductHierarchyInnerLevel());
		attributes.put("custRelationshipBuilderSid",
			getCustRelationshipBuilderSid());
		attributes.put("prodRelationshipBuilderSid",
			getProdRelationshipBuilderSid());
		attributes.put("discountType", getDiscountType());
		attributes.put("businessUnit", getBusinessUnit());
		attributes.put("deductionHierarchySid", getDeductionHierarchySid());
		attributes.put("dedRelationshipBuilderSid",
			getDedRelationshipBuilderSid());
		attributes.put("projectionCustVersionNo", getProjectionCustVersionNo());
		attributes.put("projectionProdVersionNo", getProjectionProdVersionNo());
		attributes.put("forecastEligibleDate", getForecastEligibleDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer productHierarchyLevel = (Integer)attributes.get(
				"productHierarchyLevel");

		if (productHierarchyLevel != null) {
			setProductHierarchyLevel(productHierarchyLevel);
		}

		Boolean saveFlag = (Boolean)attributes.get("saveFlag");

		if (saveFlag != null) {
			setSaveFlag(saveFlag);
		}

		String projectionName = (String)attributes.get("projectionName");

		if (projectionName != null) {
			setProjectionName(projectionName);
		}

		Date toDate = (Date)attributes.get("toDate");

		if (toDate != null) {
			setToDate(toDate);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		String forecastingType = (String)attributes.get("forecastingType");

		if (forecastingType != null) {
			setForecastingType(forecastingType);
		}

		Integer productHierVersionNo = (Integer)attributes.get(
				"productHierVersionNo");

		if (productHierVersionNo != null) {
			setProductHierVersionNo(productHierVersionNo);
		}

		Integer customerHierVersionNo = (Integer)attributes.get(
				"customerHierVersionNo");

		if (customerHierVersionNo != null) {
			setCustomerHierVersionNo(customerHierVersionNo);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer customerHierarchyLevel = (Integer)attributes.get(
				"customerHierarchyLevel");

		if (customerHierarchyLevel != null) {
			setCustomerHierarchyLevel(customerHierarchyLevel);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		String productHierarchySid = (String)attributes.get(
				"productHierarchySid");

		if (productHierarchySid != null) {
			setProductHierarchySid(productHierarchySid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String customerHierarchySid = (String)attributes.get(
				"customerHierarchySid");

		if (customerHierarchySid != null) {
			setCustomerHierarchySid(customerHierarchySid);
		}

		String companyGroupSid = (String)attributes.get("companyGroupSid");

		if (companyGroupSid != null) {
			setCompanyGroupSid(companyGroupSid);
		}

		Boolean brandType = (Boolean)attributes.get("brandType");

		if (brandType != null) {
			setBrandType(brandType);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String projectionDescription = (String)attributes.get(
				"projectionDescription");

		if (projectionDescription != null) {
			setProjectionDescription(projectionDescription);
		}

		String isApproved = (String)attributes.get("isApproved");

		if (isApproved != null) {
			setIsApproved(isApproved);
		}

		String itemGroupSid = (String)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}

		String companyMasterSid = (String)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		Integer customerHierarchyInnerLevel = (Integer)attributes.get(
				"customerHierarchyInnerLevel");

		if (customerHierarchyInnerLevel != null) {
			setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
		}

		Integer productHierarchyInnerLevel = (Integer)attributes.get(
				"productHierarchyInnerLevel");

		if (productHierarchyInnerLevel != null) {
			setProductHierarchyInnerLevel(productHierarchyInnerLevel);
		}

		String custRelationshipBuilderSid = (String)attributes.get(
				"custRelationshipBuilderSid");

		if (custRelationshipBuilderSid != null) {
			setCustRelationshipBuilderSid(custRelationshipBuilderSid);
		}

		String prodRelationshipBuilderSid = (String)attributes.get(
				"prodRelationshipBuilderSid");

		if (prodRelationshipBuilderSid != null) {
			setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
		}

		Integer discountType = (Integer)attributes.get("discountType");

		if (discountType != null) {
			setDiscountType(discountType);
		}

		Integer businessUnit = (Integer)attributes.get("businessUnit");

		if (businessUnit != null) {
			setBusinessUnit(businessUnit);
		}

		String deductionHierarchySid = (String)attributes.get(
				"deductionHierarchySid");

		if (deductionHierarchySid != null) {
			setDeductionHierarchySid(deductionHierarchySid);
		}

		String dedRelationshipBuilderSid = (String)attributes.get(
				"dedRelationshipBuilderSid");

		if (dedRelationshipBuilderSid != null) {
			setDedRelationshipBuilderSid(dedRelationshipBuilderSid);
		}

		Integer projectionCustVersionNo = (Integer)attributes.get(
				"projectionCustVersionNo");

		if (projectionCustVersionNo != null) {
			setProjectionCustVersionNo(projectionCustVersionNo);
		}

		Integer projectionProdVersionNo = (Integer)attributes.get(
				"projectionProdVersionNo");

		if (projectionProdVersionNo != null) {
			setProjectionProdVersionNo(projectionProdVersionNo);
		}

		Date forecastEligibleDate = (Date)attributes.get("forecastEligibleDate");

		if (forecastEligibleDate != null) {
			setForecastEligibleDate(forecastEligibleDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectionMasterWrapper((ProjectionMaster)_projectionMaster.clone());
	}

	@Override
	public int compareTo(ProjectionMaster projectionMaster) {
		return _projectionMaster.compareTo(projectionMaster);
	}

	/**
	* Returns the brand type of this projection master.
	*
	* @return the brand type of this projection master
	*/
	@Override
	public boolean getBrandType() {
		return _projectionMaster.getBrandType();
	}

	/**
	* Returns the business unit of this projection master.
	*
	* @return the business unit of this projection master
	*/
	@Override
	public int getBusinessUnit() {
		return _projectionMaster.getBusinessUnit();
	}

	/**
	* Returns the company group sid of this projection master.
	*
	* @return the company group sid of this projection master
	*/
	@Override
	public java.lang.String getCompanyGroupSid() {
		return _projectionMaster.getCompanyGroupSid();
	}

	/**
	* Returns the company master sid of this projection master.
	*
	* @return the company master sid of this projection master
	*/
	@Override
	public java.lang.String getCompanyMasterSid() {
		return _projectionMaster.getCompanyMasterSid();
	}

	/**
	* Returns the created by of this projection master.
	*
	* @return the created by of this projection master
	*/
	@Override
	public int getCreatedBy() {
		return _projectionMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this projection master.
	*
	* @return the created date of this projection master
	*/
	@Override
	public Date getCreatedDate() {
		return _projectionMaster.getCreatedDate();
	}

	/**
	* Returns the customer hierarchy inner level of this projection master.
	*
	* @return the customer hierarchy inner level of this projection master
	*/
	@Override
	public int getCustomerHierarchyInnerLevel() {
		return _projectionMaster.getCustomerHierarchyInnerLevel();
	}

	/**
	* Returns the customer hierarchy level of this projection master.
	*
	* @return the customer hierarchy level of this projection master
	*/
	@Override
	public int getCustomerHierarchyLevel() {
		return _projectionMaster.getCustomerHierarchyLevel();
	}

	/**
	* Returns the customer hierarchy sid of this projection master.
	*
	* @return the customer hierarchy sid of this projection master
	*/
	@Override
	public java.lang.String getCustomerHierarchySid() {
		return _projectionMaster.getCustomerHierarchySid();
	}

	/**
	* Returns the customer hier version no of this projection master.
	*
	* @return the customer hier version no of this projection master
	*/
	@Override
	public int getCustomerHierVersionNo() {
		return _projectionMaster.getCustomerHierVersionNo();
	}

	/**
	* Returns the cust relationship builder sid of this projection master.
	*
	* @return the cust relationship builder sid of this projection master
	*/
	@Override
	public java.lang.String getCustRelationshipBuilderSid() {
		return _projectionMaster.getCustRelationshipBuilderSid();
	}

	/**
	* Returns the ded relationship builder sid of this projection master.
	*
	* @return the ded relationship builder sid of this projection master
	*/
	@Override
	public java.lang.String getDedRelationshipBuilderSid() {
		return _projectionMaster.getDedRelationshipBuilderSid();
	}

	/**
	* Returns the deduction hierarchy sid of this projection master.
	*
	* @return the deduction hierarchy sid of this projection master
	*/
	@Override
	public java.lang.String getDeductionHierarchySid() {
		return _projectionMaster.getDeductionHierarchySid();
	}

	/**
	* Returns the discount type of this projection master.
	*
	* @return the discount type of this projection master
	*/
	@Override
	public int getDiscountType() {
		return _projectionMaster.getDiscountType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _projectionMaster.getExpandoBridge();
	}

	/**
	* Returns the forecast eligible date of this projection master.
	*
	* @return the forecast eligible date of this projection master
	*/
	@Override
	public Date getForecastEligibleDate() {
		return _projectionMaster.getForecastEligibleDate();
	}

	/**
	* Returns the forecasting type of this projection master.
	*
	* @return the forecasting type of this projection master
	*/
	@Override
	public java.lang.String getForecastingType() {
		return _projectionMaster.getForecastingType();
	}

	/**
	* Returns the from date of this projection master.
	*
	* @return the from date of this projection master
	*/
	@Override
	public Date getFromDate() {
		return _projectionMaster.getFromDate();
	}

	/**
	* Returns the is approved of this projection master.
	*
	* @return the is approved of this projection master
	*/
	@Override
	public java.lang.String getIsApproved() {
		return _projectionMaster.getIsApproved();
	}

	/**
	* Returns the item group sid of this projection master.
	*
	* @return the item group sid of this projection master
	*/
	@Override
	public java.lang.String getItemGroupSid() {
		return _projectionMaster.getItemGroupSid();
	}

	/**
	* Returns the modified by of this projection master.
	*
	* @return the modified by of this projection master
	*/
	@Override
	public int getModifiedBy() {
		return _projectionMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this projection master.
	*
	* @return the modified date of this projection master
	*/
	@Override
	public Date getModifiedDate() {
		return _projectionMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this projection master.
	*
	* @return the primary key of this projection master
	*/
	@Override
	public int getPrimaryKey() {
		return _projectionMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _projectionMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the prod relationship builder sid of this projection master.
	*
	* @return the prod relationship builder sid of this projection master
	*/
	@Override
	public java.lang.String getProdRelationshipBuilderSid() {
		return _projectionMaster.getProdRelationshipBuilderSid();
	}

	/**
	* Returns the product hierarchy inner level of this projection master.
	*
	* @return the product hierarchy inner level of this projection master
	*/
	@Override
	public int getProductHierarchyInnerLevel() {
		return _projectionMaster.getProductHierarchyInnerLevel();
	}

	/**
	* Returns the product hierarchy level of this projection master.
	*
	* @return the product hierarchy level of this projection master
	*/
	@Override
	public int getProductHierarchyLevel() {
		return _projectionMaster.getProductHierarchyLevel();
	}

	/**
	* Returns the product hierarchy sid of this projection master.
	*
	* @return the product hierarchy sid of this projection master
	*/
	@Override
	public java.lang.String getProductHierarchySid() {
		return _projectionMaster.getProductHierarchySid();
	}

	/**
	* Returns the product hier version no of this projection master.
	*
	* @return the product hier version no of this projection master
	*/
	@Override
	public int getProductHierVersionNo() {
		return _projectionMaster.getProductHierVersionNo();
	}

	/**
	* Returns the projection cust version no of this projection master.
	*
	* @return the projection cust version no of this projection master
	*/
	@Override
	public int getProjectionCustVersionNo() {
		return _projectionMaster.getProjectionCustVersionNo();
	}

	/**
	* Returns the projection description of this projection master.
	*
	* @return the projection description of this projection master
	*/
	@Override
	public java.lang.String getProjectionDescription() {
		return _projectionMaster.getProjectionDescription();
	}

	/**
	* Returns the projection master sid of this projection master.
	*
	* @return the projection master sid of this projection master
	*/
	@Override
	public int getProjectionMasterSid() {
		return _projectionMaster.getProjectionMasterSid();
	}

	/**
	* Returns the projection name of this projection master.
	*
	* @return the projection name of this projection master
	*/
	@Override
	public java.lang.String getProjectionName() {
		return _projectionMaster.getProjectionName();
	}

	/**
	* Returns the projection prod version no of this projection master.
	*
	* @return the projection prod version no of this projection master
	*/
	@Override
	public int getProjectionProdVersionNo() {
		return _projectionMaster.getProjectionProdVersionNo();
	}

	/**
	* Returns the save flag of this projection master.
	*
	* @return the save flag of this projection master
	*/
	@Override
	public boolean getSaveFlag() {
		return _projectionMaster.getSaveFlag();
	}

	/**
	* Returns the to date of this projection master.
	*
	* @return the to date of this projection master
	*/
	@Override
	public Date getToDate() {
		return _projectionMaster.getToDate();
	}

	@Override
	public int hashCode() {
		return _projectionMaster.hashCode();
	}

	/**
	* Returns <code>true</code> if this projection master is brand type.
	*
	* @return <code>true</code> if this projection master is brand type; <code>false</code> otherwise
	*/
	@Override
	public boolean isBrandType() {
		return _projectionMaster.isBrandType();
	}

	@Override
	public boolean isCachedModel() {
		return _projectionMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _projectionMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _projectionMaster.isNew();
	}

	/**
	* Returns <code>true</code> if this projection master is save flag.
	*
	* @return <code>true</code> if this projection master is save flag; <code>false</code> otherwise
	*/
	@Override
	public boolean isSaveFlag() {
		return _projectionMaster.isSaveFlag();
	}

	@Override
	public void persist() {
		_projectionMaster.persist();
	}

	/**
	* Sets whether this projection master is brand type.
	*
	* @param brandType the brand type of this projection master
	*/
	@Override
	public void setBrandType(boolean brandType) {
		_projectionMaster.setBrandType(brandType);
	}

	/**
	* Sets the business unit of this projection master.
	*
	* @param businessUnit the business unit of this projection master
	*/
	@Override
	public void setBusinessUnit(int businessUnit) {
		_projectionMaster.setBusinessUnit(businessUnit);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_projectionMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the company group sid of this projection master.
	*
	* @param companyGroupSid the company group sid of this projection master
	*/
	@Override
	public void setCompanyGroupSid(java.lang.String companyGroupSid) {
		_projectionMaster.setCompanyGroupSid(companyGroupSid);
	}

	/**
	* Sets the company master sid of this projection master.
	*
	* @param companyMasterSid the company master sid of this projection master
	*/
	@Override
	public void setCompanyMasterSid(java.lang.String companyMasterSid) {
		_projectionMaster.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the created by of this projection master.
	*
	* @param createdBy the created by of this projection master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_projectionMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this projection master.
	*
	* @param createdDate the created date of this projection master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_projectionMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the customer hierarchy inner level of this projection master.
	*
	* @param customerHierarchyInnerLevel the customer hierarchy inner level of this projection master
	*/
	@Override
	public void setCustomerHierarchyInnerLevel(int customerHierarchyInnerLevel) {
		_projectionMaster.setCustomerHierarchyInnerLevel(customerHierarchyInnerLevel);
	}

	/**
	* Sets the customer hierarchy level of this projection master.
	*
	* @param customerHierarchyLevel the customer hierarchy level of this projection master
	*/
	@Override
	public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
		_projectionMaster.setCustomerHierarchyLevel(customerHierarchyLevel);
	}

	/**
	* Sets the customer hierarchy sid of this projection master.
	*
	* @param customerHierarchySid the customer hierarchy sid of this projection master
	*/
	@Override
	public void setCustomerHierarchySid(java.lang.String customerHierarchySid) {
		_projectionMaster.setCustomerHierarchySid(customerHierarchySid);
	}

	/**
	* Sets the customer hier version no of this projection master.
	*
	* @param customerHierVersionNo the customer hier version no of this projection master
	*/
	@Override
	public void setCustomerHierVersionNo(int customerHierVersionNo) {
		_projectionMaster.setCustomerHierVersionNo(customerHierVersionNo);
	}

	/**
	* Sets the cust relationship builder sid of this projection master.
	*
	* @param custRelationshipBuilderSid the cust relationship builder sid of this projection master
	*/
	@Override
	public void setCustRelationshipBuilderSid(
		java.lang.String custRelationshipBuilderSid) {
		_projectionMaster.setCustRelationshipBuilderSid(custRelationshipBuilderSid);
	}

	/**
	* Sets the ded relationship builder sid of this projection master.
	*
	* @param dedRelationshipBuilderSid the ded relationship builder sid of this projection master
	*/
	@Override
	public void setDedRelationshipBuilderSid(
		java.lang.String dedRelationshipBuilderSid) {
		_projectionMaster.setDedRelationshipBuilderSid(dedRelationshipBuilderSid);
	}

	/**
	* Sets the deduction hierarchy sid of this projection master.
	*
	* @param deductionHierarchySid the deduction hierarchy sid of this projection master
	*/
	@Override
	public void setDeductionHierarchySid(java.lang.String deductionHierarchySid) {
		_projectionMaster.setDeductionHierarchySid(deductionHierarchySid);
	}

	/**
	* Sets the discount type of this projection master.
	*
	* @param discountType the discount type of this projection master
	*/
	@Override
	public void setDiscountType(int discountType) {
		_projectionMaster.setDiscountType(discountType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_projectionMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_projectionMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_projectionMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast eligible date of this projection master.
	*
	* @param forecastEligibleDate the forecast eligible date of this projection master
	*/
	@Override
	public void setForecastEligibleDate(Date forecastEligibleDate) {
		_projectionMaster.setForecastEligibleDate(forecastEligibleDate);
	}

	/**
	* Sets the forecasting type of this projection master.
	*
	* @param forecastingType the forecasting type of this projection master
	*/
	@Override
	public void setForecastingType(java.lang.String forecastingType) {
		_projectionMaster.setForecastingType(forecastingType);
	}

	/**
	* Sets the from date of this projection master.
	*
	* @param fromDate the from date of this projection master
	*/
	@Override
	public void setFromDate(Date fromDate) {
		_projectionMaster.setFromDate(fromDate);
	}

	/**
	* Sets the is approved of this projection master.
	*
	* @param isApproved the is approved of this projection master
	*/
	@Override
	public void setIsApproved(java.lang.String isApproved) {
		_projectionMaster.setIsApproved(isApproved);
	}

	/**
	* Sets the item group sid of this projection master.
	*
	* @param itemGroupSid the item group sid of this projection master
	*/
	@Override
	public void setItemGroupSid(java.lang.String itemGroupSid) {
		_projectionMaster.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the modified by of this projection master.
	*
	* @param modifiedBy the modified by of this projection master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_projectionMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this projection master.
	*
	* @param modifiedDate the modified date of this projection master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_projectionMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_projectionMaster.setNew(n);
	}

	/**
	* Sets the primary key of this projection master.
	*
	* @param primaryKey the primary key of this projection master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_projectionMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_projectionMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the prod relationship builder sid of this projection master.
	*
	* @param prodRelationshipBuilderSid the prod relationship builder sid of this projection master
	*/
	@Override
	public void setProdRelationshipBuilderSid(
		java.lang.String prodRelationshipBuilderSid) {
		_projectionMaster.setProdRelationshipBuilderSid(prodRelationshipBuilderSid);
	}

	/**
	* Sets the product hierarchy inner level of this projection master.
	*
	* @param productHierarchyInnerLevel the product hierarchy inner level of this projection master
	*/
	@Override
	public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
		_projectionMaster.setProductHierarchyInnerLevel(productHierarchyInnerLevel);
	}

	/**
	* Sets the product hierarchy level of this projection master.
	*
	* @param productHierarchyLevel the product hierarchy level of this projection master
	*/
	@Override
	public void setProductHierarchyLevel(int productHierarchyLevel) {
		_projectionMaster.setProductHierarchyLevel(productHierarchyLevel);
	}

	/**
	* Sets the product hierarchy sid of this projection master.
	*
	* @param productHierarchySid the product hierarchy sid of this projection master
	*/
	@Override
	public void setProductHierarchySid(java.lang.String productHierarchySid) {
		_projectionMaster.setProductHierarchySid(productHierarchySid);
	}

	/**
	* Sets the product hier version no of this projection master.
	*
	* @param productHierVersionNo the product hier version no of this projection master
	*/
	@Override
	public void setProductHierVersionNo(int productHierVersionNo) {
		_projectionMaster.setProductHierVersionNo(productHierVersionNo);
	}

	/**
	* Sets the projection cust version no of this projection master.
	*
	* @param projectionCustVersionNo the projection cust version no of this projection master
	*/
	@Override
	public void setProjectionCustVersionNo(int projectionCustVersionNo) {
		_projectionMaster.setProjectionCustVersionNo(projectionCustVersionNo);
	}

	/**
	* Sets the projection description of this projection master.
	*
	* @param projectionDescription the projection description of this projection master
	*/
	@Override
	public void setProjectionDescription(java.lang.String projectionDescription) {
		_projectionMaster.setProjectionDescription(projectionDescription);
	}

	/**
	* Sets the projection master sid of this projection master.
	*
	* @param projectionMasterSid the projection master sid of this projection master
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMaster.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the projection name of this projection master.
	*
	* @param projectionName the projection name of this projection master
	*/
	@Override
	public void setProjectionName(java.lang.String projectionName) {
		_projectionMaster.setProjectionName(projectionName);
	}

	/**
	* Sets the projection prod version no of this projection master.
	*
	* @param projectionProdVersionNo the projection prod version no of this projection master
	*/
	@Override
	public void setProjectionProdVersionNo(int projectionProdVersionNo) {
		_projectionMaster.setProjectionProdVersionNo(projectionProdVersionNo);
	}

	/**
	* Sets whether this projection master is save flag.
	*
	* @param saveFlag the save flag of this projection master
	*/
	@Override
	public void setSaveFlag(boolean saveFlag) {
		_projectionMaster.setSaveFlag(saveFlag);
	}

	/**
	* Sets the to date of this projection master.
	*
	* @param toDate the to date of this projection master
	*/
	@Override
	public void setToDate(Date toDate) {
		_projectionMaster.setToDate(toDate);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ProjectionMaster> toCacheModel() {
		return _projectionMaster.toCacheModel();
	}

	@Override
	public ProjectionMaster toEscapedModel() {
		return new ProjectionMasterWrapper(_projectionMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _projectionMaster.toString();
	}

	@Override
	public ProjectionMaster toUnescapedModel() {
		return new ProjectionMasterWrapper(_projectionMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _projectionMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectionMasterWrapper)) {
			return false;
		}

		ProjectionMasterWrapper projectionMasterWrapper = (ProjectionMasterWrapper)obj;

		if (Objects.equals(_projectionMaster,
					projectionMasterWrapper._projectionMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ProjectionMaster getWrappedModel() {
		return _projectionMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _projectionMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _projectionMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_projectionMaster.resetOriginalValues();
	}

	private final ProjectionMaster _projectionMaster;
}