/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.dto;

import com.stpl.app.customtreecontainer.CustomTreeDTO;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abhiram
 */
public class ProjectionResultsDTO extends CustomTreeDTO implements Comparator<ProjectionResultsDTO>{
    
     /** The group. */
    private String group = StringUtils.EMPTY;
    
     /** The level No. */
    private Integer levelNo = 0;
    
     /** The level No. */
    private Integer treeLevelNo = 0;
        
     /** The tree level No. */
    private Integer projectionTotal = 0;
    
     /** The Level Value. */
    private String levelValue = StringUtils.EMPTY;
    
     /** The parent. */
    private Integer parent=0;
     /**
     * The parent node.
     */
    private String parentNode = StringUtils.EMPTY;
    
     /** The Hierarchy No. */
    private String hierarchyNo = StringUtils.EMPTY;
    
     /** The Product Hierarchy No. */
    private String productHierarchyNo = StringUtils.EMPTY;
    
     /** The Customer Hierarchy No. */
    private String customerHierarchyNo = StringUtils.EMPTY;
    
    /** The Hierarchy Indicator. */
    private String hierarchyIndicator = StringUtils.EMPTY;
    
     /** The On Expand Total Row. */
    private Integer onExpandTotalRow=0;
    
    private Integer childTotal=0;
    
    private Integer customLevelNo = 0;
    
    /** The Hierarchy Indicator. */
    private String levelIndicator = StringUtils.EMPTY;
    
    private String relationshipLevelName = StringUtils.EMPTY;
    
    ProjectionResultsDTO manDTO;
    ProjectionResultsDTO suppDTO;
    
    
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

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
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

    public Integer getProjectionTotal() {
        return projectionTotal;
    }

    public void setProjectionTotal(Integer projectionTotal) {
        this.projectionTotal = projectionTotal;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
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

    public Integer getChildTotal() {
        return childTotal;
    }

    public void setChildTotal(Integer childTotal) {
        this.childTotal = childTotal;
    }

    public Integer getCustomLevelNo() {
        return customLevelNo;
    }

    public void setCustomLevelNo(Integer customLevelNo) {
        this.customLevelNo = customLevelNo;
    }

    public String getLevelIndicator() {
        return levelIndicator;
    }

    public void setLevelIndicator(String levelIndicator) {
        this.levelIndicator = levelIndicator;
    }

    public String getRelationshipLevelName() {
        return relationshipLevelName;
    }

    public void setRelationshipLevelName(String relationshipLevelName) {
        this.relationshipLevelName = relationshipLevelName;
    }

    public ProjectionResultsDTO getManDTO() {
        return manDTO;
    }

    public void setManDTO(ProjectionResultsDTO manDTO) {
        this.manDTO = manDTO;
    }

    public ProjectionResultsDTO getSuppDTO() {
        return suppDTO;
    }

    public void setSuppDTO(ProjectionResultsDTO suppDTO) {
        this.suppDTO = suppDTO;
    }
    
    @Override
    public int compare(ProjectionResultsDTO obj1, ProjectionResultsDTO obj2) {
        int value=0;
        try {
            if (obj1 != null && obj2 != null && obj1.getGroup() != null && obj2.getGroup() != null) {

                 if (obj1.getGroup().length() == 4) {
                    Integer year1 = Integer.valueOf(obj1.getGroup());
                    Integer year2 = Integer.valueOf(obj2.getGroup());
                    value = year1.compareTo(year2);
                } else if (obj1.getGroup().length() != 4 && Character.isDigit(obj1.getGroup().charAt(1)) && Character.isDigit(obj2.getGroup().charAt(1))) {
                    String str1 = obj1.getGroup().substring(2);
                    String str2 = obj2.getGroup().substring(2);
                    value = str1.compareTo(str2);
                }else {
                    Integer year1 = Integer.valueOf(obj1.getGroup().substring(4, 8));
                    Integer year2 = Integer.valueOf(obj2.getGroup().substring(4, 8));
                    value=year1.compareTo(year2);

                }
            }
        } catch (Exception e) {
            
        }
        
        return value;
    }

}
