/*
 * 
 */
package com.stpl.app.galforecasting.nationalassumptions.dto;

import com.stpl.app.customtreecontainer.CustomTreeDTO;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;

/**
 * The Class Table DTO.
 */
public class TableDTO extends CustomTreeDTO implements Comparator<TableDTO>{
 /** The group. */
    private String group = StringUtils.EMPTY;
    
     /** The level No. */
    private Integer levelNo = 0;
    
      /** The tree level No. */
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
    
    /** The Hierarchy Indicator. */
    private String hierarchyIndicator = StringUtils.EMPTY;
    
     /** The On Expand Total Row. */
    private Integer onExpandTotalRow=0;
    
    private Integer itemMasterSid = 0;    
     /** The Hierarchy No. */
    private String baseYearAmp = StringUtils.EMPTY;
    
    /** The Hierarchy Indicator. */
    private String baseYearCpi = StringUtils.EMPTY;
    
      /** The Hierarchy No. */
    private String baseYearAmpNotes = StringUtils.EMPTY;
    
    /** The Hierarchy Indicator. */
    private String baseYearCpiNotes = StringUtils.EMPTY;
    
     private String ndc9 = StringUtils.EMPTY;
     
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

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
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

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getBaseYearAmp() {
        return baseYearAmp;
    }

    public void setBaseYearAmp(String baseYearAmp) {
        this.baseYearAmp = baseYearAmp;
    }

    public String getBaseYearCpi() {
        return baseYearCpi;
    }

    public void setBaseYearCpi(String baseYearCpi) {
        this.baseYearCpi = baseYearCpi;
    }

    public String getBaseYearAmpNotes() {
        return baseYearAmpNotes;
    }

    public void setBaseYearAmpNotes(String baseYearAmpNotes) {
        this.baseYearAmpNotes = baseYearAmpNotes;
    }

    public String getBaseYearCpiNotes() {
        return baseYearCpiNotes;
    }

    public void setBaseYearCpiNotes(String baseYearCpiNotes) {
        this.baseYearCpiNotes = baseYearCpiNotes;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

   @Override
    public int compare(TableDTO obj1, TableDTO obj2) {
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
