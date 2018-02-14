package com.stpl.app.gtnforecasting.dto;

import com.stpl.ifs.ui.util.NumericConstants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectionVarianceDTO extends ExtMapDTO implements Comparator<ProjectionVarianceDTO> {

    /**
     * The group.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectionVarianceDTO.class);
    private String group = StringUtils.EMPTY;
    private Integer levelNo = 0;
    private String parentNode = StringUtils.EMPTY;
    private Integer treeLevelNo = 0;
    private Integer parent = 0;
    private String discoutName;
    private String discountIndicator;
    private List<String> periodHeaderList;
    private String view = StringUtils.EMPTY;
    private String hierarchyNo = StringUtils.EMPTY;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private String level = StringUtils.EMPTY;
    private String levelValue = StringUtils.EMPTY;
    private String productHierarchyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;
    private String deductionHierarchyNo = StringUtils.EMPTY;
    private Integer customId = 0;
    private String relationshipLevelName = StringUtils.EMPTY;
    private Integer onExpandTotalRow = 0;
    private Integer groupParent = 0;
    private String parentHierarchyNo = StringUtils.EMPTY;
    private String dfLevelNumber = StringUtils.EMPTY;
    private String dfLevelName = StringUtils.EMPTY;

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

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public String getRelationshipLevelName() {
        return relationshipLevelName;
    }

    public void setRelationshipLevelName(String relationshipLevelName) {
        this.relationshipLevelName = relationshipLevelName;
    }

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

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getDiscoutName() {
        return discoutName;
    }

    public void setDiscoutName(String discoutName) {
        this.discoutName = discoutName;
    }

    public String getDiscountIndicator() {
        return discountIndicator;
    }

    public void setDiscountIndicator(String discountIndicator) {
        this.discountIndicator = discountIndicator;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public List<String> getPeriodHeaderList() {
        return periodHeaderList == null ? periodHeaderList : new ArrayList<>(periodHeaderList);
    }

    public void setPeriodHeaderList(List<String> periodHeaderList) {
        this.periodHeaderList = periodHeaderList == null ? periodHeaderList : new ArrayList<>(periodHeaderList);
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
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

    public Integer getCustomId() {
        return customId;
    }

    public void setCustomId(Integer customId) {
        this.customId = customId;
    }

    public Integer getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(Integer groupParent) {
        this.groupParent = groupParent;
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
    
    @Override
    public int compare(ProjectionVarianceDTO obj1, ProjectionVarianceDTO obj2) {
        int value = 0;
        try {
            if (obj1 != null && obj2 != null && obj1.getGroup() != null && obj2.getGroup() != null) {

                if (obj1.getGroup().length() == NumericConstants.FOUR) {
                    Integer year1 = Integer.valueOf(obj1.getGroup());
                    Integer year2 = Integer.valueOf(obj2.getGroup());
                    value = year1.compareTo(year2);
                } else if (obj1.getGroup().length() != NumericConstants.FOUR && Character.isDigit(obj1.getGroup().charAt(1)) && Character.isDigit(obj2.getGroup().charAt(1))) {
                    String str1 = obj1.getGroup().substring(NumericConstants.TWO);
                    String str2 = obj2.getGroup().substring(NumericConstants.TWO);
                    value = str1.compareTo(str2);
                } else {
                    Integer year1 = Integer.valueOf(obj1.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    Integer year2 = Integer.valueOf(obj2.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    value = year1.compareTo(year2);

                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return value;
    }
}
