/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;
import com.stpl.app.customtreecontainer.CustomTreeDTO;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author Nandhakumar
 */
public class DiscountProjectionResultsDTO extends CustomTreeDTO{
    
   private String group;
   private Integer levelNo;
   private String hierarchySid;
   private String hierarchylevelId; 
   private String relationshipValue;
   private String hierarchyNo; 
   private String isParent;
   private String empty;
  
     /**
     * The parent node.
     */
    private String parentNode = StringUtils.EMPTY;
    
     /** The Level Value. */
    private String levelValue = StringUtils.EMPTY;
    
      /** The Hierarchy Indicator. */
    private String hierarchyIndicator = StringUtils.EMPTY;
    
     /** The tree level No. */
    private Integer projectionTotal = 0; 
    
     /** The parent. */
    private Integer parent=0;
    
     /** The Product Hierarchy No. */
    private String productHierarchyNo = StringUtils.EMPTY;
    
     /** The Customer Hierarchy No. */
    private String customerHierarchyNo = StringUtils.EMPTY;
    
    private int total= 0;
      /** The level No. */
    private Integer treeLevelNo = 0;
    
      /** The On Expand Total Row. */
    private Integer onExpandTotalRow=0;
    
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

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
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

    

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
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

    public Integer getProjectionTotal() {
        return projectionTotal;
    }

    public void setProjectionTotal(Integer projectionTotal) {
        this.projectionTotal = projectionTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
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
    
}
