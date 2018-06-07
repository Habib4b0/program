package com.stpl.gtn.gtn2o.ws.entity.customviewforecast;
// Generated May 16, 2018 7:58:59 PM by Hibernate Tools 4.3.1



/**
 * CustViewDetails generated by hbm2java
 */
public class CustViewDetails  implements java.io.Serializable {


     private int customViewDetailsSid;
     private Integer customViewMasterSid;
     private Integer hierarchyId;
     private Character hierarchyIndicator;
     private Integer levelNo;
     private String levelName;
     private int   variableCount;

    public CustViewDetails() {
    }

	
    public CustViewDetails(int customViewDetailsSid) {
        this.customViewDetailsSid = customViewDetailsSid;
    }

    public CustViewDetails(int customViewDetailsSid, Integer customViewMasterSid, Integer hierarchyId, Character hierarchyIndicator, Integer levelNo, String levelName, int variableCount) {
        this.customViewDetailsSid = customViewDetailsSid;
        this.customViewMasterSid = customViewMasterSid;
        this.hierarchyId = hierarchyId;
        this.hierarchyIndicator = hierarchyIndicator;
        this.levelNo = levelNo;
        this.levelName = levelName;
        this.variableCount = variableCount;
    }
   
   
    public int getCustomViewDetailsSid() {
        return this.customViewDetailsSid;
    }
    
    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        this.customViewDetailsSid = customViewDetailsSid;
    }
    public Integer getCustomViewMasterSid() {
        return this.customViewMasterSid;
    }
    
    public void setCustomViewMasterSid(Integer customViewMasterSid) {
        this.customViewMasterSid = customViewMasterSid;
    }
    public Integer getHierarchyId() {
        return this.hierarchyId;
    }
    
    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }
    public Character getHierarchyIndicator() {
        return this.hierarchyIndicator;
    }
    
    public void setHierarchyIndicator(Character hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }
    public Integer getLevelNo() {
        return this.levelNo;
    }
    
    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getVariableCount() {
        return variableCount;
    }

    public void setVariableCount(int variableCount) {
        this.variableCount = variableCount;
    }




}


