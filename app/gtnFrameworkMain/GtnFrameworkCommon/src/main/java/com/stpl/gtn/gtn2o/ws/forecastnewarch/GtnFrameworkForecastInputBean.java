package com.stpl.gtn.gtn2o.ws.forecastnewarch;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnFrameworkForecastInputBean {

	private String projectionName;
	private String projectionDescription;
	private int company;
	private int businessUnit;
	private String privateViewName;
	private String publicViewName;
	private String customerHierarchyName;
	private String productHierarchyName;
	private String customerGroupName;
	private String productGroupName;
	private int customerRelationSid;
	private int productRelationSid;
	private int customerRelationVersionNo;
	private int productRelationVersionNo;
	private int customerHierarchyLevel;
	private int productHierarchyLevel;
	private int customerRelationLevel;
	private int productRelationLevel;
	private Date forecastEligibleDate;
	private int customerHierarchySid;
	private int productHierarchySid;
	private int customerHierarchyVersion;
	private int productHierarchyVersion;
	private String fromPeriod;
	private String toPeriod;
	private GtnWsRecordBean customerHierarchyBean;
	private GtnWsRecordBean productHierarchyBean;
	private List<GtnWsRecordBean> selectedCustomerList;
	private List<GtnWsRecordBean> selectedProductList;
	private String userId;
	private String sessionId;
	private String uniqueId;
	private String createdBy;
	private Date createdDate;
	private Date modifiedDate;
	private String modifiedBy;
	private int customViewMasterSid;
	private int projectionMasterSid;
	private int customerGroupSid;
	private int productGroupSid;
	private int discountType;
	private Date fromDate;
	private Date toDate;
	private int deductionRelationVersion;
	private String companyName;
	private String businessUnitName;
	private String deductionLevel;

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

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(int businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getPrivateViewName() {
		return privateViewName;
	}

	public void setPrivateViewName(String privateViewName) {
		this.privateViewName = privateViewName;
	}

	public String getPublicViewName() {
		return publicViewName;
	}

	public void setPublicViewName(String publicViewName) {
		this.publicViewName = publicViewName;
	}

	public String getCustomerHierarchyName() {
		return customerHierarchyName;
	}

	public void setCustomerHierarchyName(String customerHierarchyName) {
		this.customerHierarchyName = customerHierarchyName;
	}

	public String getProductHierarchyName() {
		return productHierarchyName;
	}

	public void setProductHierarchyName(String productHierarchyName) {
		this.productHierarchyName = productHierarchyName;
	}

	public String getCustomerGroupName() {
		return customerGroupName;
	}

	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}

	public String getProductGroupName() {
		return productGroupName;
	}

	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}

	public int getCustomerRelationSid() {
		return customerRelationSid;
	}

	public void setCustomerRelationSid(int customerRelationSid) {
		this.customerRelationSid = customerRelationSid;
	}

	public int getProductRelationSid() {
		return productRelationSid;
	}

	public void setProductRelationSid(int productRelationSid) {
		this.productRelationSid = productRelationSid;
	}

	public int getCustomerRelationVersionNo() {
		return customerRelationVersionNo;
	}

	public void setCustomerRelationVersionNo(int customerRelationVersionNo) {
		this.customerRelationVersionNo = customerRelationVersionNo;
	}

	public int getProductRelationVersionNo() {
		return productRelationVersionNo;
	}

	public void setProductRelationVersionNo(int productRelationVersionNo) {
		this.productRelationVersionNo = productRelationVersionNo;
	}

	public int getCustomerHierarchyLevel() {
		return customerHierarchyLevel;
	}

	public void setCustomerHierarchyLevel(int customerHierarchyLevel) {
		this.customerHierarchyLevel = customerHierarchyLevel;
	}

	public int getProductHierarchyLevel() {
		return productHierarchyLevel;
	}

	public void setProductHierarchyLevel(int productHierarchyLevel) {
		this.productHierarchyLevel = productHierarchyLevel;
	}

	public int getCustomerRelationLevel() {
		return customerRelationLevel;
	}

	public void setCustomerRelationLevel(int customerRelationLevel) {
		this.customerRelationLevel = customerRelationLevel;
	}

	public int getProductRelationLevel() {
		return productRelationLevel;
	}

	public void setProductRelationLevel(int productRelationLevel) {
		this.productRelationLevel = productRelationLevel;
	}

	public Date getForecastEligibleDate() {
		return forecastEligibleDate;
	}

	public void setForecastEligibleDate(Date forecastEligibleDate) {
		this.forecastEligibleDate = forecastEligibleDate;
	}

	public int getCustomerHierarchySid() {
		return customerHierarchySid;
	}

	public void setCustomerHierarchySid(int customerHierarchySid) {
		this.customerHierarchySid = customerHierarchySid;
	}

	public int getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(int productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public int getCustomerHierarchyVersion() {
		return customerHierarchyVersion;
	}

	public void setCustomerHierarchyVersion(int customerHierarchyVersion) {
		this.customerHierarchyVersion = customerHierarchyVersion;
	}

	public int getProductHierarchyVersion() {
		return productHierarchyVersion;
	}

	public void setProductHierarchyVersion(int productHierarchyVersion) {
		this.productHierarchyVersion = productHierarchyVersion;
	}

	public GtnWsRecordBean getCustomerHierarchyBean() {
		return customerHierarchyBean;
	}

	public void setCustomerHierarchyBean(GtnWsRecordBean customerHierarchyBean) {
		this.customerHierarchyBean = customerHierarchyBean;
	}

	public GtnWsRecordBean getProductHierarchyBean() {
		return productHierarchyBean;
	}

	public void setProductHierarchyBean(GtnWsRecordBean productHierarchyBean) {
		this.productHierarchyBean = productHierarchyBean;
	}

	public List<GtnWsRecordBean> getSelectedCustomerList() {
		return selectedCustomerList;
	}

	public String getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(String fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public String getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(String toPeriod) {
		this.toPeriod = toPeriod;
	}

	public void setSelectedCustomerList(List<GtnWsRecordBean> selectedCustomerList) {
		this.selectedCustomerList = selectedCustomerList;
	}

	public List<GtnWsRecordBean> getSelectedProductList() {
		return selectedProductList;
	}

	public void setSelectedProductList(List<GtnWsRecordBean> selectedProductList) {
		this.selectedProductList = selectedProductList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getCustomViewMasterSid() {
		return customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		this.customViewMasterSid = customViewMasterSid;
	}

	public int getProjectionMasterSid() {
		return projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		this.projectionMasterSid = projectionMasterSid;
	}

	public int getCustomerGroupSid() {
		return customerGroupSid;
	}

	public void setCustomerGroupSid(int customerGroupSid) {
		this.customerGroupSid = customerGroupSid;
	}

	public int getProductGroupSid() {
		return productGroupSid;
	}

	public void setProductGroupSid(int productGroupSid) {
		this.productGroupSid = productGroupSid;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getDeductionRelationVersion() {
		return deductionRelationVersion;
	}

	public void setDeductionRelationVersion(int deductionRelationVersion) {
		this.deductionRelationVersion = deductionRelationVersion;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getDeductionLevel() {
		return deductionLevel;
	}

	public void setDeductionLevel(String deductionLevel) {
		this.deductionLevel = deductionLevel;
	}

}
