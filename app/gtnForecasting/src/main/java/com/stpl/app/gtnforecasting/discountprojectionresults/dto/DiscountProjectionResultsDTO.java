/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.dto;

import com.stpl.app.gtnforecasting.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author pvinoth
 */
public class DiscountProjectionResultsDTO extends ExtMapDTO {

    /**
     * The Constant serialVersionUID.
     */
    private String level = StringUtils.EMPTY;
    private String group = StringUtils.EMPTY;
    private String customer = StringUtils.EMPTY;
    private String contract = StringUtils.EMPTY;
    private String product = StringUtils.EMPTY;
    private Integer levelNo = new Integer(Constant.DASH);
    private String levelValue = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String history = StringUtils.EMPTY;
    private String salesOrUnits = StringUtils.EMPTY;
    private String actuasOrProj = StringUtils.EMPTY;
    private String projectionPeriodOrder = StringUtils.EMPTY;
    private String pivotView = StringUtils.EMPTY;
    private String screenName = StringUtils.EMPTY;
    private String hierarchyNo = StringUtils.EMPTY;
    private Integer parent = 0;
    private String parentNode = StringUtils.EMPTY;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private Integer projectionTotal = 0;
    private Integer treeLevelNo = 0;
    private Integer onExpandTotalRow = 0;
    private String productHierarchyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;
    private Integer customLevelNo = 0;
    private String parentLevelName = StringUtils.EMPTY;
    protected DiscountProjectionResultsDTO manDTO;
    protected DiscountProjectionResultsDTO suppDTO;
    private List<DiscountProjectionResultsDTO> dprDTOList;
    private String relationshipLevelName = StringUtils.EMPTY;
    private int total = 0;
    private String hierarchySid;
    private String hierarchylevelId;
    private String relationshipValue;
    private String isParent;
    private String empty;
    private String mmTotal = StringUtils.EMPTY;
    private String treeParent = StringUtils.EMPTY;
    private String supplementalLevelName = StringUtils.EMPTY;
    private String currLevelName = StringUtils.EMPTY;
    private String currentLevel = StringUtils.EMPTY;
    private String currentBrand = StringUtils.EMPTY;
    private String currentCustomer = StringUtils.EMPTY;
    private String currentContract = StringUtils.EMPTY;
    private String discountLevel = StringUtils.EMPTY;
    private String pivotValue = StringUtils.EMPTY;
    private String nextFlag = StringUtils.EMPTY;
    private String nmSuppLevel = StringUtils.EMPTY;
    private Integer addedDiscountCount = 0;
    private Integer totalDiscountCount = 0;
    private String parentHierarchyNo = StringUtils.EMPTY;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSalesOrUnits() {
        return salesOrUnits;
    }

    public void setSalesOrUnits(String salesOrUnits) {
        this.salesOrUnits = salesOrUnits;
    }

    public String getActuasOrProj() {
        return actuasOrProj;
    }

    public void setActuasOrProj(String actuasOrProj) {
        this.actuasOrProj = actuasOrProj;
    }

    public String getProjectionPeriodOrder() {
        return projectionPeriodOrder;
    }

    public void setProjectionPeriodOrder(String projectionPeriodOrder) {
        this.projectionPeriodOrder = projectionPeriodOrder;
    }

    public String getPivotView() {
        return pivotView;
    }

    public void setPivotView(String pivotView) {
        this.pivotView = pivotView;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public Integer getProjectionTotal() {
        return projectionTotal;
    }

    public void setProjectionTotal(Integer projectionTotal) {
        this.projectionTotal = projectionTotal;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public Integer getCustomLevelNo() {
        return customLevelNo;
    }

    public void setCustomLevelNo(Integer customLevelNo) {
        this.customLevelNo = customLevelNo;
    }

    public String getParentLevelName() {
        return parentLevelName;
    }

    public void setParentLevelName(String parentLevelName) {
        this.parentLevelName = parentLevelName;
    }

    public DiscountProjectionResultsDTO getManDTO() {
        return manDTO;
    }

    public void setManDTO(DiscountProjectionResultsDTO manDTO) {
        this.manDTO = manDTO;
    }

    public DiscountProjectionResultsDTO getSuppDTO() {
        return suppDTO;
    }

    public void setSuppDTO(DiscountProjectionResultsDTO suppDTO) {
        this.suppDTO = suppDTO;
    }

    public List<DiscountProjectionResultsDTO> getDprDTOList() {
        return dprDTOList == null ? dprDTOList : new ArrayList<>(dprDTOList);
    }

    public void setDprDTOList(List<DiscountProjectionResultsDTO> dprDTOList) {
        this.dprDTOList = dprDTOList == null ? dprDTOList : new ArrayList<>(dprDTOList);
    }

    public String getRelationshipLevelName() {
        return relationshipLevelName;
    }

    public void setRelationshipLevelName(String relationshipLevelName) {
        this.relationshipLevelName = relationshipLevelName;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getHierarchySid() {
        return hierarchySid;
    }

    public void setHierarchySid(String hierarchySid) {
        this.hierarchySid = hierarchySid;
    }

    public String getHierarchylevelId() {
        return hierarchylevelId;
    }

    public void setHierarchylevelId(String hierarchylevelId) {
        this.hierarchylevelId = hierarchylevelId;
    }

    public String getRelationshipValue() {
        return relationshipValue;
    }

    public void setRelationshipValue(String relationshipValue) {
        this.relationshipValue = relationshipValue;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getTreeParent() {
        return treeParent;
    }

    public void setTreeParent(String treeParent) {
        this.treeParent = treeParent;
    }

    public String getSupplementalLevelName() {
        return supplementalLevelName;
    }

    public void setSupplementalLevelName(String supplementalLevelName) {
        this.supplementalLevelName = supplementalLevelName;
    }

    public String getCurrLevelName() {
        return currLevelName;
    }

    public void setCurrLevelName(String currLevelName) {
        this.currLevelName = currLevelName;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getCurrentBrand() {
        return currentBrand;
    }

    public void setCurrentBrand(String currentBrand) {
        this.currentBrand = currentBrand;
    }

    public String getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(String currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public String getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(String currentContract) {
        this.currentContract = currentContract;
    }

    public String getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(String discountLevel) {
        this.discountLevel = discountLevel;
    }

    public String getPivotValue() {
        return pivotValue;
    }

    public void setPivotValue(String pivotValue) {
        this.pivotValue = pivotValue;
    }

    public String getNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(String nextFlag) {
        this.nextFlag = nextFlag;
    }

    public String getNmSuppLevel() {
        return nmSuppLevel;
    }

    public void setNmSuppLevel(String nmSuppLevel) {
        this.nmSuppLevel = nmSuppLevel;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getMmTotal() {
        return mmTotal;
    }

    public void setMmTotal(String mmTotal) {
        this.mmTotal = mmTotal;
    }

    public Integer getAddedDiscountCount() {
        return addedDiscountCount;
}

    public void setAddedDiscountCount(Integer addedDiscountCount) {
        this.addedDiscountCount = addedDiscountCount;
    }

    public Integer getTotalDiscountCount() {
        return totalDiscountCount;
    }

    public void setTotalDiscountCount(Integer totalDiscountCount) {
        this.totalDiscountCount = totalDiscountCount;
    }

    public String getParentHierarchyNo() {
        return parentHierarchyNo;
    }

    public void setParentHierarchyNo(String parentHierarchyNo) {
        this.parentHierarchyNo = parentHierarchyNo;
    }
    
}
