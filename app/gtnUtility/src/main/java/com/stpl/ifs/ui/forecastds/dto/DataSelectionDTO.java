/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.dto;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DataSelectionDTO implements Serializable {

	/**
	 * The projection id.
	 */
	private int projectionId;

	private boolean saveFlag;
	/**
	 * The company.
	 */

	private String selectedCompanyName = StringUtils.EMPTY;

	/**
	 * Projection version number
	 */
	private String projectionVersion = StringUtils.EMPTY;

	/**
	 * The private view.
	 */
	private String privateView = StringUtils.EMPTY;

	/**
	 * The public view.
	 */
	private String publicView = StringUtils.EMPTY;

	/**
	 * The projection name.
	 */
	private String projectionName = StringUtils.EMPTY;

	/**
	 * The description.
	 */
	private String description = StringUtils.EMPTY;

	/**
	 * The customer hierarchy.
	 */
	private String customerHierarchy = StringUtils.EMPTY;

	/**
	 * The customer level.
	 */
	private String customerGroup = StringUtils.EMPTY;

	/**
	 * The product hierarchy.
	 */
	private String productHierarchy = StringUtils.EMPTY;

	/**
	 * The product level.
	 */
	private String productGroup = StringUtils.EMPTY;

	/**
	 * The created by.
	 */
	private String createdBy = StringUtils.EMPTY;
	private String modifiedBy = StringUtils.EMPTY;

	/**
	 * The created date.
	 */
	private String createdDate = StringUtils.EMPTY;

	/**
	 * The last modified date.
	 */
	private String lastModifiedDate = StringUtils.EMPTY;

	/**
	 * The workflow status.
	 */
	private String workflowStatus = StringUtils.EMPTY;

	/**
	 * The market type.
	 */
	private String marketType = StringUtils.EMPTY;

	/**
	 * The brand.
	 */
	private boolean brand;

	/**
	 * The contract holder.
	 */
	private String contractHolder = StringUtils.EMPTY;

	/**
	 * The ndc no.
	 */
	private String ndcNo = StringUtils.EMPTY;

	/**
	 * The ndc name.
	 */
	private String ndcName = StringUtils.EMPTY;

	/**
	 * The contract.
	 */
	private String contract = StringUtils.EMPTY;

	/**
	 * The created date from.
	 */
	private String createdDateFrom = StringUtils.EMPTY;

	/**
	 * The created date to.
	 */
	private String createdDateTo = StringUtils.EMPTY;

	/**
	 * Customer hierarchy version number
	 */
	private int customerHierVersionNo;

	/**
	 * Product hierarchy version number
	 */
	private int productHierVersionNo;

	/**
	 * The from period
	 */
	private Date fromDate;

	private String fromPeriod;

	/**
	 * The to period
	 */
	private Date toDate;

	private String toPeriod;

	/**
	 * Customer hierarchy level number
	 */
	private String customerHierarchyLevel = StringUtils.EMPTY;

	private String brandType = StringUtils.EMPTY;

	private String customerHierSid = StringUtils.EMPTY;

	private String companySid = StringUtils.EMPTY;

	private String prodHierSid = StringUtils.EMPTY;

	private String customerGrpSid = StringUtils.EMPTY;

	private String prodGrpSid = StringUtils.EMPTY;
        private String frequency = StringUtils.EMPTY;
	/**
	 * product hierarchy level number
	 */
	private String productHierarchyLevel = StringUtils.EMPTY;
	private String companyName = StringUtils.EMPTY;

	List<String> selectedCustomerRelationSid;
	List<String> selectedProductRelationSid;
	private String productHierarchyInnerLevel = StringUtils.EMPTY;
	private String customerHierarchyInnerLevel = StringUtils.EMPTY;

	private String custRelationshipBuilderSid;
	private String prodRelationshipBuilderSid;

	private Date createdDateSearch;
	private Date modifiedDateSearch;
	private Integer fileEndMonth;
	private Integer fileEndYear;
	private String discount = StringUtils.EMPTY;
	private int discountSid = 0;
	private String modulName = StringUtils.EMPTY;
	private String createdById = StringUtils.EMPTY;
	public Map<String, String> customerDescription = new HashMap<String, String>();
	public Map<String, String> productDescription = new HashMap<String, String>();
	public String ViewType = "";
	public String deductionLevel = StringUtils.EMPTY;
	public String deductionValue = StringUtils.EMPTY;
	public String deductionType = StringUtils.EMPTY;
	public Integer deductionValueId;
	private Integer businessUnitSystemId;
	private String businessUnitName;
        private String selectedCustomerLevelNo;


	private int customerRelationShipVersionNo;
	private int productRelationShipVersionNo;
	private int deductionRelationShipVersionNo;
	private int customRelationShipSid;
	private int customDeductionRelationShipSid;
        private Date forecastEligibleDate;
        private int dataSelectionDeductionLevelSid;
        private int customViewMasterSid;

        private GtnWsRecordBean dsCustomerHierarchyBean;

        private List<GtnWsRecordBean> dsCustomerSelectedTableBean;

        private List<GtnWsRecordBean> dsProductSelectedTableBean;

        private String CustomerOrProduct = "";

        private GtnFrameworkForecastInputBean inputBean;

    public GtnFrameworkForecastInputBean getInputBean() {
        return inputBean;
    }

    public void setInputBean(GtnFrameworkForecastInputBean inputBean) {
        this.inputBean = inputBean;
    }
    public String getCustomerOrProduct() {
        return CustomerOrProduct;
    }

    public void setCustomerOrProduct(String CustomerOrProduct) {
        this.CustomerOrProduct = CustomerOrProduct;
    }
    public List<GtnWsRecordBean> getDsCustomerSelectedTableBean() {
        return dsCustomerSelectedTableBean;
    }

    public void setDsCustomerSelectedTableBean(List<GtnWsRecordBean> dsCustomerSelectedTableBean) {
        this.dsCustomerSelectedTableBean = dsCustomerSelectedTableBean;
    }
        
    public GtnWsRecordBean getDsCustomerHierarchyBean() {
        return dsCustomerHierarchyBean;
    }

    public void setDsCustomerHierarchyBean(GtnWsRecordBean dsCustomerHierarchyBean) {
        this.dsCustomerHierarchyBean = dsCustomerHierarchyBean;
    }

    public GtnWsRecordBean getDsProductHierarchyBean() {
        return dsProductHierarchyBean;
    }

    public void setDsProductHierarchyBean(GtnWsRecordBean dsProductHierarchyBean) {
        this.dsProductHierarchyBean = dsProductHierarchyBean;
    }
    
    
    public List<GtnWsRecordBean> getDsProductSelectedTableBean() {
        return dsProductSelectedTableBean;
    }

    public void setDsProductSelectedTableBean(List<GtnWsRecordBean> dsProductSelectedTableBean) {
        this.dsProductSelectedTableBean = dsProductSelectedTableBean;
    }
        
        private GtnWsRecordBean dsProductHierarchyBean;
        
	public Integer getFileEndMonth() {
		return fileEndMonth;
	}

	public void setFileEndMonth(Integer fileEndMonth) {
		this.fileEndMonth = fileEndMonth;
	}

	public Integer getFileEndYear() {
		return fileEndYear;
	}

	public void setFileEndYear(Integer fileEndYear) {
		this.fileEndYear = fileEndYear;
	}

	/**
	 * Gets the private view.
	 *
	 * @return the private view
	 */
	public String getPrivateView() {
		return privateView;
	}

	/**
	 * Sets the private view.
	 *
	 * @param privateView
	 *            the private view
	 */
	public void setPrivateView(final String privateView) {
		this.privateView = privateView;
	}

	/**
	 * Gets the public view.
	 *
	 * @return the public view
	 */
	public String getPublicView() {
		return publicView;
	}

	/**
	 * Sets the public view.
	 *
	 * @param publicView
	 *            the public view
	 */
	public void setPublicView(final String publicView) {
		this.publicView = publicView;
	}

	/**
	 * Gets the projection name.
	 *
	 * @return the projection name
	 */
	public String getProjectionName() {
		return projectionName;
	}

	/**
	 * Sets the projection name.
	 *
	 * @param projectionName
	 *            the projection name
	 */
	public void setProjectionName(final String projectionName) {
		this.projectionName = projectionName;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Gets the customer hierarchy.
	 *
	 * @return the customer hierarchy
	 */
	public String getCustomerHierarchy() {
		return customerHierarchy;
	}

	/**
	 * Sets the customer hierarchy.
	 *
	 * @param customerHierarchy
	 *            the customer hierarchy
	 */
	public void setCustomerHierarchy(final String customerHierarchy) {
		this.customerHierarchy = customerHierarchy;
	}

	/**
	 * Gets the customer Group.
	 *
	 * @return the customer Group
	 */
	public String getCustomerGroup() {
		return customerGroup;
	}

	/**
	 * Sets the customer Group.
	 *
	 * @param customerGroup
	 *            the customer Group
	 */
	public void setCustomerGroup(final String customerGroup) {
		this.customerGroup = customerGroup;
	}

	/**
	 * Gets the product hierarchy.
	 *
	 * @return the product hierarchy
	 */
	public String getProductHierarchy() {
		return productHierarchy;
	}

	/**
	 * Sets the product hierarchy.
	 *
	 * @param productHierarchy
	 *            the product hierarchy
	 */
	public void setProductHierarchy(final String productHierarchy) {
		this.productHierarchy = productHierarchy;
	}

	/**
	 * Gets the product Group.
	 *
	 * @return the product Group
	 */
	public String getProductGroup() {
		return productGroup;
	}

	/**
	 * Sets the product Group.
	 *
	 * @param productGroup
	 *            the product Group
	 */
	public void setProductGroup(final String productGroup) {
		this.productGroup = productGroup;
	}

	public int getCustomerRelationShipVersionNo() {
		return customerRelationShipVersionNo;
	}

	public void setCustomerRelationShipVersionNo(int customerRelationShipVersionNo) {
		this.customerRelationShipVersionNo = customerRelationShipVersionNo;
	}

	public int getProductRelationShipVersionNo() {
		return productRelationShipVersionNo;
	}

	public void setProductRelationShipVersionNo(int productRelationShipVersionNo) {
		this.productRelationShipVersionNo = productRelationShipVersionNo;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the created by
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the created date
	 */
	public void setCreatedDate(final String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the last modified date.
	 *
	 * @return the last modified date
	 */
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * Sets the last modified date.
	 *
	 * @param lastModifiedDate
	 *            the last modified date
	 */
	public void setLastModifiedDate(final String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Gets the workflow status.
	 *
	 * @return the workflow status
	 */
	public String getWorkflowStatus() {
		return workflowStatus;
	}

	/**
	 * Sets the workflow status.
	 *
	 * @param workflowStatus
	 *            the workflow status
	 */
	public void setWorkflowStatus(final String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	/**
	 * Gets the market type.
	 *
	 * @return the market type
	 */
	public String getMarketType() {
		return marketType;
	}

	/**
	 * Sets the market type.
	 *
	 * @param marketType
	 *            the market type
	 */
	public void setMarketType(final String marketType) {
		this.marketType = marketType;
	}

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public boolean getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand
	 *            the brand
	 */
	public void setBrand(final boolean brand) {
		this.brand = brand;
	}

	/**
	 * Gets the contract holder.
	 *
	 * @return the contract holder
	 */
	public String getContractHolder() {
		return contractHolder;
	}

	/**
	 * Sets the contract holder.
	 *
	 * @param contractHolder
	 *            the contract holder
	 */
	public void setContractHolder(final String contractHolder) {
		this.contractHolder = contractHolder;
	}

	/**
	 * Gets the ndc no.
	 *
	 * @return the ndc no
	 */
	public String getNdcNo() {
		return ndcNo;
	}

	/**
	 * Sets the ndc no.
	 *
	 * @param ndcNo
	 *            the ndc no
	 */
	public void setNdcNo(final String ndcNo) {
		this.ndcNo = ndcNo;
	}

	/**
	 * Gets the ndc name.
	 *
	 * @return the ndc name
	 */
	public String getNdcName() {
		return ndcName;
	}

	/**
	 * Sets the ndc name.
	 *
	 * @param ndcName
	 *            the ndc name
	 */
	public void setNdcName(final String ndcName) {
		this.ndcName = ndcName;
	}

	/**
	 * Gets the contract.
	 *
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}

	/**
	 * Sets the contract.
	 *
	 * @param contract
	 *            the contract
	 */
	public void setContract(final String contract) {
		this.contract = contract;
	}

	/**
	 * Gets the created date from.
	 *
	 * @return the created date from
	 */
	public String getCreatedDateFrom() {
		return createdDateFrom;
	}

	/**
	 * Sets the created date from.
	 *
	 * @param createdDateFrom
	 *            the created date from
	 */
	public void setCreatedDateFrom(final String createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	/**
	 * Gets the created date to.
	 *
	 * @return the created date to
	 */
	public String getCreatedDateTo() {
		return createdDateTo;
	}

	/**
	 * Sets the created date to.
	 *
	 * @param createdDateTo
	 *            the created date to
	 */
	public void setCreatedDateTo(final String createdDateTo) {
		this.createdDateTo = createdDateTo;
	}

	/**
	 * Gets the projection id.
	 *
	 * @return the projection id
	 */
	public int getProjectionId() {
		return projectionId;
	}

	/**
	 * Sets the projection id.
	 *
	 * @param projectionId
	 *            the new projection id
	 */
	public void setProjectionId(int projectionId) {
		this.projectionId = projectionId;
	}

	/**
	 * Gets the projection version number.
	 *
	 * @return the projection version number
	 */
	public String getProjectionVersion() {
		return projectionVersion;
	}

	/**
	 * Sets the projection version number.
	 *
	 * @param projectionId
	 *            the new version number
	 */
	public void setProjectionVersion(String projectionVersion) {
		this.projectionVersion = projectionVersion;
	}

	/**
	 * Gets the customer hierarchy level number.
	 *
	 * @return the customer hierarchy level number
	 */
	public String getCustomerHierarchyLevel() {
		return customerHierarchyLevel;
	}

	/**
	 * Sets customer hierarchy level number.
	 *
	 * @param customerHierarchyLevel
	 *            the customer hierarchy level number
	 */
	public void setCustomerHierarchyLevel(String customerHierarchyLevel) {
		this.customerHierarchyLevel = customerHierarchyLevel;
	}

	/**
	 * Gets the product hierarchy level number.
	 *
	 * @return the product hierarchy level number
	 */
	public String getProductHierarchyLevel() {
		return productHierarchyLevel;
	}

	/**
	 * Sets the product hierarchy level number.
	 *
	 * @param productHierarchyLevel
	 *            the product hierarchy level number
	 */
	public void setProductHierarchyLevel(String productHierarchyLevel) {
		this.productHierarchyLevel = productHierarchyLevel;
	}

	/**
	 * Gets Customer hierarchy version number
	 *
	 * @return Customer hierarchy version number
	 */
	public int getCustomerHierVersionNo() {
		return customerHierVersionNo;
	}

	/**
	 * Sets Customer hierarchy version number
	 *
	 * @param customerHierVersionNo
	 *            Customer hierarchy version number
	 */
	public void setCustomerHierVersionNo(int customerHierVersionNo) {
		this.customerHierVersionNo = customerHierVersionNo;
	}

	/**
	 * Gets Product hierarchy version number
	 *
	 * @return Product hierarchy version number
	 */
	public int getProductHierVersionNo() {
		return productHierVersionNo;
	}

	/**
	 * Gets Product hierarchy version number
	 *
	 * @return Product hierarchy version number
	 */
	public void setProductHierVersionNo(int productHierVersionNo) {
		this.productHierVersionNo = productHierVersionNo;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public String getCustomerHierSid() {
		return customerHierSid;
	}

	public void setCustomerHierSid(String customerHierSid) {
		this.customerHierSid = customerHierSid;
	}

	public String getCompanySid() {
		return companySid;
	}

	public void setCompanySid(String companySid) {
		this.companySid = companySid;
	}

	public String getProdHierSid() {
		return prodHierSid;
	}

	public void setProdHierSid(String prodHierSid) {
		this.prodHierSid = prodHierSid;
	}

	public String getCustomerGrpSid() {
		return customerGrpSid;
	}

	public void setCustomerGrpSid(String customerGrpSid) {
		this.customerGrpSid = customerGrpSid;
	}

	public String getProdGrpSid() {
		return prodGrpSid;
	}

	public void setProdGrpSid(String prodGrpSid) {
		this.prodGrpSid = prodGrpSid;
	}

	public String getSelectedCompanyName() {
		return selectedCompanyName;
	}

	public void setSelectedCompanyName(String selectedCompanyName) {
		this.selectedCompanyName = selectedCompanyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public boolean isSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(boolean saveFlag) {
		this.saveFlag = saveFlag;
	}

	public void clear() {
		projectionId = 0;
		saveFlag = false;
		selectedCompanyName = StringUtils.EMPTY;
		projectionVersion = StringUtils.EMPTY;
		privateView = StringUtils.EMPTY;
		publicView = StringUtils.EMPTY;
		projectionName = StringUtils.EMPTY;
		description = StringUtils.EMPTY;
		customerHierarchy = StringUtils.EMPTY;
		customerGroup = StringUtils.EMPTY;
		productHierarchy = StringUtils.EMPTY;
		productGroup = StringUtils.EMPTY;
		createdBy = StringUtils.EMPTY;
		modifiedBy = StringUtils.EMPTY;
		createdDate = StringUtils.EMPTY;
		lastModifiedDate = StringUtils.EMPTY;
		workflowStatus = StringUtils.EMPTY;
		marketType = StringUtils.EMPTY;
		brand = true;
		contractHolder = StringUtils.EMPTY;
		ndcNo = StringUtils.EMPTY;
		ndcName = StringUtils.EMPTY;
		contract = StringUtils.EMPTY;
		createdDateFrom = StringUtils.EMPTY;
		createdDateTo = StringUtils.EMPTY;
		customerHierVersionNo = 0;
		productHierVersionNo = 0;
		customerHierarchyLevel = StringUtils.EMPTY;
		brandType = StringUtils.EMPTY;
		customerHierSid = StringUtils.EMPTY;
		companySid = StringUtils.EMPTY;
		prodHierSid = StringUtils.EMPTY;
		customerGrpSid = StringUtils.EMPTY;
		prodGrpSid = StringUtils.EMPTY;
		productHierarchyLevel = StringUtils.EMPTY;
		companyName = StringUtils.EMPTY;
	}

	public List<String> getSelectedCustomerRelationSid() {
		return selectedCustomerRelationSid;
	}

	public void setSelectedCustomerRelationSid(List<String> selectedCustomerRelationSid) {
		this.selectedCustomerRelationSid = selectedCustomerRelationSid;
	}

	public List<String> getSelectedProductRelationSid() {
		return selectedProductRelationSid;
	}

	public void setSelectedProductRelationSid(List<String> selectedProductRelationSid) {
		this.selectedProductRelationSid = selectedProductRelationSid;
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

	public String getProductHierarchyInnerLevel() {
		return productHierarchyInnerLevel;
	}

	public void setProductHierarchyInnerLevel(String productHierarchyInnerLevel) {
		this.productHierarchyInnerLevel = productHierarchyInnerLevel;
	}

	public String getCustomerHierarchyInnerLevel() {
		return customerHierarchyInnerLevel;
	}

	public void setCustomerHierarchyInnerLevel(String customerHierarchyInnerLevel) {
		this.customerHierarchyInnerLevel = customerHierarchyInnerLevel;
	}

	public String getCustRelationshipBuilderSid() {
		return custRelationshipBuilderSid;
	}

	public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
		this.custRelationshipBuilderSid = custRelationshipBuilderSid;
	}

	public String getProdRelationshipBuilderSid() {
		return prodRelationshipBuilderSid;
	}

	public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
		this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
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

	public Date getCreatedDateSearch() {
		return createdDateSearch;
	}

	public void setCreatedDateSearch(Date createdDateSearch) {
		this.createdDateSearch = createdDateSearch;
	}

	public Date getModifiedDateSearch() {
		return modifiedDateSearch;
	}

	public void setModifiedDateSearch(Date modifiedDateSearch) {
		this.modifiedDateSearch = modifiedDateSearch;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getDiscountSid() {
		return discountSid;
	}

	public void setDiscountSid(int discountSid) {
		this.discountSid = discountSid;
	}

	public String getModulName() {
		return modulName;
	}

	public void setModulName(String modulName) {
		this.modulName = modulName;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public Map<String, String> getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(Map<String, String> customerDescription) {
		this.customerDescription = customerDescription;
	}

	public Map<String, String> getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(Map<String, String> productDescription) {
		this.productDescription = productDescription;
	}

	public String getViewType() {
		return ViewType;
	}

	public void setViewType(String ViewType) {
		this.ViewType = ViewType;
	}

	public String getDeductionLevel() {
		return deductionLevel;
	}

	public void setDeductionLevel(String deductionLevel) {
		this.deductionLevel = deductionLevel;
	}

	public String getDeductionValue() {
		return deductionValue;
	}

	public void setDeductionValue(String deductionValue) {
		this.deductionValue = deductionValue;
	}

	public Integer getDeductionValueId() {
		return deductionValueId;
	}

	public void setDeductionValueId(Integer deductionValueId) {
		this.deductionValueId = deductionValueId;
	}

	public Integer getBusinessUnitSystemId() {
		return businessUnitSystemId;
	}

	public void setBusinessUnitSystemId(Integer businessUnitSystemId) {
		this.businessUnitSystemId = businessUnitSystemId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitSystemName) {
		this.businessUnitName = businessUnitSystemName;
	}

	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}
        public Date getForecastEligibleDate() {
            return forecastEligibleDate;
        }

        public void setForecastEligibleDate(Date forecastEligibleDate) {
            this.forecastEligibleDate = forecastEligibleDate;
        }

        public String getSelectedCustomerLevelNo() {
            return selectedCustomerLevelNo;
        }

        public void setSelectedCustomerLevelNo(String selectedCustomerLevelNo) {
            this.selectedCustomerLevelNo = selectedCustomerLevelNo;
        }

       public int getDeductionRelationShipVersionNo() {
        return deductionRelationShipVersionNo;
       }

       public void setDeductionRelationShipVersionNo(int deductionRelationShipVersionNo) {
        this.deductionRelationShipVersionNo = deductionRelationShipVersionNo;
       }

        public String getFrequency() {
        return frequency;
        }

        public void setFrequency(String frequency) {
        this.frequency = frequency;
        }

    public int getCustomRelationShipSid() {
        return customRelationShipSid;
    }

    public void setCustomRelationShipSid(int customRelationShipSid) {
        this.customRelationShipSid = customRelationShipSid;
    }

    public int getCustomDeductionRelationShipSid() {
        return customDeductionRelationShipSid;
    }

    public void setCustomDeductionRelationShipSid(int customDeductionRelationShipSid) {
        this.customDeductionRelationShipSid = customDeductionRelationShipSid;
    }

    public int getDataSelectionDeductionLevelSid() {
        return dataSelectionDeductionLevelSid;
    }

    public void setDataSelectionDeductionLevelSid(int dataSelectionDeductionLevelSid) {
        this.dataSelectionDeductionLevelSid = dataSelectionDeductionLevelSid;
    }
    public int getCustomViewMasterSid() {
        return customViewMasterSid;
    }

    public void setCustomViewMasterSid(int customViewMasterSid) {
        this.customViewMasterSid = customViewMasterSid;
    }
}
