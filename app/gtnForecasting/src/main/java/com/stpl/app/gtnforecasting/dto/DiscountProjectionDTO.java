package com.stpl.app.gtnforecasting.dto;

import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.GtnSmallHashMap;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

public class DiscountProjectionDTO extends ExtMapDTO {

    /**
     * The level.
     */
    private String level = StringUtils.EMPTY;

    /**
     * The level.
     */
    private String levelValue = StringUtils.EMPTY;

    /**
     * The level No.
     */
    private Integer levelNo = 0;

    /**
     * The relationship level No.
     */
    private String hierarchyNo = StringUtils.EMPTY;

    /**
     * The group.
     */
    private String group = StringUtils.EMPTY;

    /**
     * The customer hierarchy number
     */
    private String customerHierarchyNo = StringUtils.EMPTY;

    /**
     * The product hierarchy Number
     */
    private String productHierarchyNo = StringUtils.EMPTY;

    /**
     * The product hierarchy Number
     */
    private String deductionHierarchyNo = StringUtils.EMPTY;

    private Integer uncheckCount = 0;

    private String levelName = StringUtils.EMPTY;
    private String deductionInclusion = StringUtils.EMPTY;

    private Integer ccpCount = 0;

    private Integer relationLevelSid = new Integer(Constant.DASH);

    /**
     * The tree level No
     */
    private Integer treeLevelNo = 0;

    /**
     * The Parent hierarchy Indicator
     */
    private String hierarchyIndicator = StringUtils.EMPTY;
    private String hierarchyLevel = "empty";
    private List<DiscountProjectionDTO> alternatePivotList;
    private String parentAlternatePivot = StringUtils.EMPTY;
    private String dfLevelNumber = StringUtils.EMPTY ;
    private String dfLevelName = StringUtils.EMPTY;

    public DiscountProjectionDTO() {
    }

    public String getDfLevelNumber() {
        return dfLevelNumber;
    }

    public void setDfLevelNumber(String dfLevelNumber) {
        this.dfLevelNumber = dfLevelNumber;
    }

    public String getDfLevelName() {
        return dfLevelName;
    }

    public void setDfLevelName(String dfLevelName) {
        this.dfLevelName = dfLevelName;
    }
    

    private GtnSmallHashMap ccpCountForDiscount = new GtnSmallHashMap();
    private String parentHierarchyNo = StringUtils.EMPTY;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
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

    public Integer getUncheckCount() {
        return uncheckCount;
    }

    public void setUncheckCount(Integer uncheckCount) {
        this.uncheckCount = uncheckCount;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
    }

    public Integer getRelationLevelSid() {
        return relationLevelSid;
    }

    public void setRelationLevelSid(Integer relationLevelSid) {
        this.relationLevelSid = relationLevelSid;
    }

    public String getHierarchyLevel() {
        return hierarchyLevel;
    }

    public void setHierarchyLevel(String hierarchyLevel) {
        this.hierarchyLevel = hierarchyLevel;
    }

    public List<DiscountProjectionDTO> getAlternatePivotList() {
        return alternatePivotList == null ? alternatePivotList : new ArrayList<>(alternatePivotList);
    }

    public void setAlternatePivotList(List<DiscountProjectionDTO> alternatePivotList) {
        this.alternatePivotList = alternatePivotList == null ? alternatePivotList : new ArrayList<>(alternatePivotList);
    }

    public String getParentAlternatePivot() {
        return parentAlternatePivot;
    }

    public void setParentAlternatePivot(String parentAlternatePivot) {
        this.parentAlternatePivot = parentAlternatePivot;
    }

    public GtnSmallHashMap getCcpCountForDiscount() {
        return ccpCountForDiscount;
    }

    public void setCcpCountForDiscount(GtnSmallHashMap ccpCountForDiscount) {
        this.ccpCountForDiscount = ccpCountForDiscount;
    }

    public String getParentHierarchyNo() {
        return parentHierarchyNo;
    }

    public void setParentHierarchyNo(String parentHierarchyNo) {
        this.parentHierarchyNo = parentHierarchyNo;
    }

    public String getDeductionHierarchyNo() {
        return deductionHierarchyNo;
    }

    public void setDeductionHierarchyNo(String deductionHierarchyNo) {
        this.deductionHierarchyNo = deductionHierarchyNo;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

}
