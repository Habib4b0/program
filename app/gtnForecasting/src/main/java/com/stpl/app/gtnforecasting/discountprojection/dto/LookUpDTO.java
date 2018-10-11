package com.stpl.app.gtnforecasting.discountprojection.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;


/**
 * The Class LookUpDTO.
 */
public class LookUpDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The hierarchy name.
     */
    private String hierarchyName;

    /**
     * The highest level.
     */
    private String highestLevel;

    /**
     * The lowest level.
     */
    private String lowestLevel;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The modified date.
     */
    private String modifiedDate;

    /**
     * The customer group name.
     */
    private String customerGroupName;

    /**
     * The customer group.
     */
    private String customerGroup;

    /**
     * The segment.
     */
    private String segment;

    /**
     * The segment group.
     */
    private String segmentGroup;

    /**
     * The product group name.
     */
    private String productGroupName;

    /**
     * The product group.
     */
    private String productGroup;

    /**
     * The company.
     */
    private String company;

    /**
     * The contract.
     */
    private String contract;

    /**
     * The brand.
     */
    private String brand;

    /**
     * The ndc.
     */
    private String ndc;

    /**
     * The ndc description.
     */
    private String ndcDescription;

    /**
     * The projection name.
     */
    private String projectionName;

    /**
     * The description.
     */
    private String description;

    /**
     * The market type.
     */
    private String marketType;

    /**
     * The customer.
     */
    private String customer;

    /**
     * The contract comparison.
     */
    private String contractComparison;

    /**
     * The brand comparison.
     */
    private String brandComparison;

    private int itemMasterSid;

    private int contractMasterSid;

    private String contractName = StringUtils.EMPTY;

    /**
     * The brand.
     */
    private String brandName = StringUtils.EMPTY;

    /**
     * The itemNo.
     */
    private String itemNo = StringUtils.EMPTY;

    private String itemDesc = StringUtils.EMPTY;

    private String startPeriod = StringUtils.EMPTY;

    private String endPeriod = StringUtils.EMPTY;

    private String methodology = StringUtils.EMPTY;

    private String discount1 = StringUtils.EMPTY;

    private String discount2 = StringUtils.EMPTY;

    private int projectionDetailsSid;

    private String workFlowStatus;

    private String ndcName;

    private String fromDate;

    private String toDate;

    private String projectionId;

    private String createdBy;

    private List<LookUpDTO> selected;

    private Map<Integer, String> projectionMap;
    private String quarterValue;
    private String yearValue;
    private int ccpDetailsSid;

    public LookUpDTO() {
        super();
    }

    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchyName
     */
    public String getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Sets the hierarchy name.
     *
     * @param hierarchyName the hierarchyName to set
     */
    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    /**
     * Gets the highest level.
     *
     * @return the highestLevel
     */
    public String getHighestLevel() {
        return highestLevel;
    }

    /**
     * Sets the highest level.
     *
     * @param highestLevel the highestLevel to set
     */
    public void setHighestLevel(String highestLevel) {
        this.highestLevel = highestLevel;
    }

    /**
     * Gets the lowest level.
     *
     * @return the lowestLevel
     */
    public String getLowestLevel() {
        return lowestLevel;
    }

    /**
     * Sets the lowest level.
     *
     * @param lowestLevel the lowestLevel to set
     */
    public void setLowestLevel(String lowestLevel) {
        this.lowestLevel = lowestLevel;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate == null ? null : (Date) createdDate.clone();
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
    }

    /**
     * Gets the modified date.
     *
     * @return the modifiedDate
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the customer group name.
     *
     * @return the customerGroupName
     */
    public String getCustomerGroupName() {
        return customerGroupName;
    }

    /**
     * Sets the customer group name.
     *
     * @param customerGroupName the customerGroupName to set
     */
    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    /**
     * Gets the customer group.
     *
     * @return the customerGroup
     */
    public String getCustomerGroup() {
        return customerGroup;
    }

    /**
     * Sets the customer group.
     *
     * @param customerGroup the customerGroup to set
     */
    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    /**
     * Gets the segment.
     *
     * @return the segment
     */
    public String getSegment() {
        return segment;
    }

    /**
     * Sets the segment.
     *
     * @param segment the segment to set
     */
    public void setSegment(String segment) {
        this.segment = segment;
    }

    /**
     * Gets the segment group.
     *
     * @return the segmentGroup
     */
    public String getSegmentGroup() {
        return segmentGroup;
    }

    /**
     * Sets the segment group.
     *
     * @param segmentGroup the segmentGroup to set
     */
    public void setSegmentGroup(String segmentGroup) {
        this.segmentGroup = segmentGroup;
    }

    /**
     * Gets the product group name.
     *
     * @return the productGroupName
     */
    public String getProductGroupName() {
        return productGroupName;
    }

    /**
     * Sets the product group name.
     *
     * @param productGroupName the productGroupName to set
     */
    public void setProductGroupName(String productGroupName) {
        this.productGroupName = productGroupName;
    }

    /**
     * Gets the product group.
     *
     * @return the productGroup
     */
    public String getProductGroup() {
        return productGroup;
    }

    /**
     * Sets the product group.
     *
     * @param productGroup the productGroup to set
     */
    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    /**
     * Gets the company.
     *
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the company.
     *
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
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
     * @param contract the contract to set
     */
    public void setContract(String contract) {
        this.contract = contract;
    }

    /**
     * Gets the brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand.
     *
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets the ndc.
     *
     * @return the ndc
     */
    public String getNdc() {
        return ndc;
    }

    /**
     * Sets the ndc.
     *
     * @param ndc the ndc to set
     */
    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    /**
     * Gets the ndc description.
     *
     * @return the ndcDescription
     */
    public String getNdcDescription() {
        return ndcDescription;
    }

    /**
     * Sets the ndc description.
     *
     * @param ndcDescription the ndcDescription to set
     */
    public void setNdcDescription(String ndcDescription) {
        this.ndcDescription = ndcDescription;
    }

    /**
     * Gets the projection name.
     *
     * @return the projectionName
     */
    public String getProjectionName() {
        return projectionName;
    }

    /**
     * Sets the projection name.
     *
     * @param projectionName the projectionName to set
     */
    public void setProjectionName(String projectionName) {
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
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the market type.
     *
     * @return the marketType
     */
    public String getMarketType() {
        return marketType;
    }

    /**
     * Sets the market type.
     *
     * @param marketType the marketType to set
     */
    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * Sets the customer.
     *
     * @param customer the customer to set
     */
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * @return the contractComparison
     */
    public String getContractComparison() {
        return contractComparison;
    }

    /**
     * @param contractComparison the contractComparison to set
     */
    public void setContractComparison(String contractComparison) {
        this.contractComparison = contractComparison;
    }

    /**
     * @return the brandComparison
     */
    public String getBrandComparison() {
        return brandComparison;
    }

    /**
     * @param brandComparison the brandComparison to set
     */
    public void setBrandComparison(String brandComparison) {
        this.brandComparison = brandComparison;
    }

    public String getWorkFlowStatus() {
        return workFlowStatus;
    }

    public void setWorkFlowStatus(String workFlowStatus) {
        this.workFlowStatus = workFlowStatus;
    }

    public String getNdcName() {
        return ndcName;
    }

    public void setNdcName(String ndcName) {
        this.ndcName = ndcName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<LookUpDTO> getSelected() {
        return selected == null ? selected : new ArrayList<>(selected);
    }

    public void setSelected(List selected) {
        this.selected = selected == null ? selected : new ArrayList<>(selected);
    }

    public Map<Integer, String> getProjectionMap() {
        return projectionMap;
    }

    public void setProjectionMap(Map<Integer, String> projectionMap) {
        this.projectionMap = projectionMap;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public int getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getDiscount1() {
        return discount1;
    }

    public void setDiscount1(String discount1) {
        this.discount1 = discount1;
    }

    public String getDiscount2() {
        return discount2;
    }

    public void setDiscount2(String discount2) {
        this.discount2 = discount2;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public String getQuarterValue() {
        return quarterValue;
    }

    public void setQuarterValue(String quarterValue) {
        this.quarterValue = quarterValue;
    }

    public String getYearValue() {
        return yearValue;
    }

    public void setYearValue(String yearValue) {
        this.yearValue = yearValue;
    }

    public int getCcpDetailsSid() {
        return ccpDetailsSid;
    }

    public void setCcpDetailsSid(int ccpDetailsSid) {
        this.ccpDetailsSid = ccpDetailsSid;
    }

}
