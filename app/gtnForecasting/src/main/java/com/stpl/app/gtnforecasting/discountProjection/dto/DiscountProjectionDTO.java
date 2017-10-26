/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountProjection.dto;

import com.stpl.app.gtnforecasting.utils.Constant;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author Vinodhini
 */
public class DiscountProjectionDTO extends ExtMapDTO {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The product.
     */
    private String product = StringUtils.EMPTY;
    /**
     * The product name.
     */
    private String productName = StringUtils.EMPTY;
    /**
     * The product description.
     */
    private String productDescription = StringUtils.EMPTY;

    /**
     * The customer.
     */
    private String customer = StringUtils.EMPTY;

    /**
     * The actuals.
     */
    private String actuals = StringUtils.EMPTY;

    /**
     * The projections.
     */
    private String projections = StringUtils.EMPTY;

    private Integer parent = 0;

    private Integer supplementalLevelNo = 0;

    private Integer levelNo = 0;

    private String hierarchyNo = StringUtils.EMPTY;

    private String levelName = StringUtils.EMPTY;

    private String hierarchyIndicator = StringUtils.EMPTY;

    private Integer onExpandTotalRow = 0;

    private Integer systemID = 0;

    private Integer ccpDetailsSID = 0;

    private String actualdiscount = StringUtils.EMPTY;

    private List<String> ccpDetailIds = new ArrayList<String>();

    private Integer companyID = 0;

    private Integer contractID = 0;

    private Integer therapeuticID = 0;

    private String brandID = StringUtils.EMPTY;

    private Integer ndcID = 0;

    private Integer projectionDetailsSid = 0;

    private Integer year = 0;

    private Integer period = 0;

    private String propertyName = StringUtils.EMPTY;

    private String methodologyCommentary = StringUtils.EMPTY;

    private String parity = StringUtils.EMPTY;

    private String access = StringUtils.EMPTY;

    private String accessCommentary = StringUtils.EMPTY;

    private String parityCommentary = StringUtils.EMPTY;

//    private List<String> ccpDetailIdList = new ArrayList<String>();

    private List<String> parentCcpDetailIdList = new ArrayList<String>();

    private String companyIdForNdcLevel = StringUtils.EMPTY;

    private String itemIdForNdcLevel = StringUtils.EMPTY;

    private Integer startYear = 0;
    private Integer endYear = 0;
    private Integer startPeriod = 0;
    private Integer endPeriod = 0;

    /**
     * The level.
     */
    private String level = StringUtils.EMPTY;
    private String levelValue = StringUtils.EMPTY;
    /**
     * The customer hierarchy number
     */
    private String customerHierarchyNo = StringUtils.EMPTY;

    /**
     * The product hierarchy Number
     */
    private String productHierarchyNo = StringUtils.EMPTY;

    /**
     * The tree level No
     */
    private Integer treeLevelNo = 0;

    private Integer relationLevelSid = new Integer(Constant.DASH);

    private String propertyId = StringUtils.EMPTY;

    private Object value = StringUtils.EMPTY;

    private Integer uncheckCount = 0;

    private Integer ccpCount = 0;

    /**
     * Gets the product.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the product.
     *
     * @param product the new product
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name.
     *
     * @param productName the new product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the product description.
     *
     * @return the product description
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the product description.
     *
     * @param productDescription the new product description
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    /**
     * Gets the actuals.
     *
     * @return the actuals
     */
    public String getActuals() {
        return actuals;
    }

    /**
     * Sets the actuals.
     *
     * @param actuals the new actuals
     */
    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    /**
     * Gets the projections.
     *
     * @return the projections
     */
    public String getProjections() {
        return projections;
    }

    /**
     * Sets the projections.
     *
     * @param projections the new projections
     */
    public void setProjections(String projections) {
        this.projections = projections;
    }

    private String group = StringUtils.EMPTY;
    private String methodology = StringUtils.EMPTY;

    private String methodologyQ4 = StringUtils.EMPTY;
    private String contractPriceQ4 = StringUtils.EMPTY;
    private String methodologyQ1 = StringUtils.EMPTY;
    private String contractPriceQ1 = StringUtils.EMPTY;

    private String contractPrice = StringUtils.EMPTY;
    private String discount1 = StringUtils.EMPTY;
    private String discount2 = StringUtils.EMPTY;
    private String contractEndDate = StringUtils.EMPTY;
    private String levelDetails = StringUtils.EMPTY;

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return the methodology
     */
    public String getMethodology() {
        return methodology;
    }

    /**
     * @param methodology the methodology to set
     */
    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    /**
     * @return the methodologyQ4
     */
    public String getMethodologyQ4() {
        return methodologyQ4;
    }

    /**
     * @param methodologyQ4 the methodologyQ4 to set
     */
    public void setMethodologyQ4(String methodologyQ4) {
        this.methodologyQ4 = methodologyQ4;
    }

    /**
     * @return the contractPriceQ4
     */
    public String getContractPriceQ4() {
        return contractPriceQ4;
    }

    /**
     * @param contractPriceQ4 the contractPriceQ4 to set
     */
    public void setContractPriceQ4(String contractPriceQ4) {
        this.contractPriceQ4 = contractPriceQ4;
    }

    /**
     * @return the methodologyQ1
     */
    public String getMethodologyQ1() {
        return methodologyQ1;
    }

    /**
     * @param methodologyQ1 the methodologyQ1 to set
     */
    public void setMethodologyQ1(String methodologyQ1) {
        this.methodologyQ1 = methodologyQ1;
    }

    /**
     * @return the contractPriceQ1
     */
    public String getContractPriceQ1() {
        return contractPriceQ1;
    }

    /**
     * @param contractPriceQ1 the contractPriceQ1 to set
     */
    public void setContractPriceQ1(String contractPriceQ1) {
        this.contractPriceQ1 = contractPriceQ1;
    }

    /**
     * @return the contractPrice
     */
    public String getContractPrice() {
        return contractPrice;
    }

    /**
     * @param contractPrice the contractPrice to set
     */
    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * @return the discount1
     */
    public String getDiscount1() {
        return discount1;
    }

    /**
     * @param discount1 the discount1 to set
     */
    public void setDiscount1(String discount1) {
        this.discount1 = discount1;
    }

    /**
     * @return the discount2
     */
    public String getDiscount2() {
        return discount2;
    }

    /**
     * @param discount2 the discount2 to set
     */
    public void setDiscount2(String discount2) {
        this.discount2 = discount2;
    }

    /**
     * @return the contractEndDate
     */
    public String getContractEndDate() {
        return contractEndDate;
    }

    /**
     * @param contractEndDate the contractEndDate to set
     */
    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getSupplementalLevelNo() {
        return supplementalLevelNo;
    }

    public void setSupplementalLevelNo(Integer supplementalLevelNo) {
        this.supplementalLevelNo = supplementalLevelNo;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public Integer getSystemID() {
        return systemID;
    }

    public void setSystemID(Integer systemID) {
        this.systemID = systemID;
    }

    public Integer getCcpDetailsSID() {
        return ccpDetailsSID;
    }

    public void setCcpDetailsSID(Integer ccpDetailsSID) {
        this.ccpDetailsSID = ccpDetailsSID;
    }

    public String getActualdiscount() {
        return actualdiscount;
    }

    public void setActualdiscount(String actualdiscount) {
        this.actualdiscount = actualdiscount;
    }

    public List<String> getCcpDetailIds() {
        return ccpDetailIds;
    }

    public void setCcpDetailIds(List<String> ccpDetailIds) {
        this.ccpDetailIds = ccpDetailIds;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public Integer getTherapeuticID() {
        return therapeuticID;
    }

    public void setTherapeuticID(Integer therapeuticID) {
        this.therapeuticID = therapeuticID;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public Integer getNdcID() {
        return ndcID;
    }

    public void setNdcID(Integer ndcID) {
        this.ndcID = ndcID;
    }

    public Integer getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(Integer projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getMethodologyCommentary() {
        return methodologyCommentary;
    }

    public void setMethodologyCommentary(String methodologyCommentary) {
        this.methodologyCommentary = methodologyCommentary;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getAccessCommentary() {
        return accessCommentary;
    }

    public void setAccessCommentary(String accessCommentary) {
        this.accessCommentary = accessCommentary;
    }

    public String getParityCommentary() {
        return parityCommentary;
    }

    public void setParityCommentary(String parityCommentary) {
        this.parityCommentary = parityCommentary;
    }

//    public List<String> getCcpDetailIdList() {
//        return ccpDetailIdList;
//    }
//
//    public void setCcpDetailIdList(List<String> ccpDetailIdList) {
//        this.ccpDetailIdList = ccpDetailIdList;
//    }

    public List<String> getParentCcpDetailIdList() {
        return parentCcpDetailIdList;
    }

    public void setParentCcpDetailIdList(List<String> parentCcpDetailIdList) {
        this.parentCcpDetailIdList = parentCcpDetailIdList;
    }

    public String getLevelDetails() {
        return levelDetails;
    }

    public void setLevelDetails(String levelDetails) {
        this.levelDetails = levelDetails;
    }

    public String getCompanyIdForNdcLevel() {
        return companyIdForNdcLevel;
    }

    public void setCompanyIdForNdcLevel(String companyIdForNdcLevel) {
        this.companyIdForNdcLevel = companyIdForNdcLevel;
    }

    public String getItemIdForNdcLevel() {
        return itemIdForNdcLevel;
    }

    public void setItemIdForNdcLevel(String itemIdForNdcLevel) {
        this.itemIdForNdcLevel = itemIdForNdcLevel;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Integer startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Integer getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Integer endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public Integer getRelationLevelSid() {
        return relationLevelSid;
    }

    public void setRelationLevelSid(Integer relationLevelSid) {
        this.relationLevelSid = relationLevelSid;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Integer getUncheckCount() {
        return uncheckCount;
    }

    public void setUncheckCount(Integer uncheckCount) {
        this.uncheckCount = uncheckCount;
    }

    public Integer getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
    }

}
