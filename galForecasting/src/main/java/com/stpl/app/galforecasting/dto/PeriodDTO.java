/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.dto;

import com.stpl.app.galforecasting.utils.Constant;
import org.apache.commons.lang.StringUtils;



/**
 *
 * @author maheshj
 */
public class PeriodDTO {
    Integer projectionId=0;
    Integer rowId=new Integer(0);
    Integer ccpId=new Integer(0);
    String level=StringUtils.EMPTY;
    String LevelName=StringUtils.EMPTY;
    Integer levelNo=new Integer(0);
    String key=StringUtils.EMPTY;
    String LevelValues=StringUtils.EMPTY;
    String HierarchyLevel=StringUtils.EMPTY;
    Integer actualsOrProj=new Integer(0);
  
    String accountGrowth="0.0";
    String productGrowth="0.0";
    String actualSales="0.0";
    String actualUnits="0.0";
    String actualPrpjectionSales="0.0";
    String actualProjectionUnits="0.0";
    String projSales="0.0";
    String projUnits="0.0";
    String period=StringUtils.EMPTY; 
    String year=StringUtils.EMPTY;
    String semiYear=StringUtils.EMPTY;
    String quator=StringUtils.EMPTY;
    Integer month=new Integer(Constant.DASH);
    String sales="0.0";
    String units="0.0";
    Integer salesId=new Integer(Constant.DASH);
    String customer=StringUtils.EMPTY;
    String product=StringUtils.EMPTY;
    String contract=StringUtils.EMPTY;
    Integer rowCount=new Integer(Constant.DASH);
    String BaseLine=StringUtils.EMPTY;
    String userGroup=StringUtils.EMPTY;
    String methodologyUsed=StringUtils.EMPTY;
    String hierarchyNo=Constant.PERCENT;
   
     Integer checkUncheckRec=new Integer(0);
    Double lives=0.0;

    Integer relationshipLevelSid=new Integer(0);
    Integer uncheckCount=new Integer(Constant.DASH);
    Integer ccpCount=new Integer(Constant.DASH);
    
    public Integer getActualsOrProj() {
        return actualsOrProj;
    }

    public void setActualsOrProj(Integer actualsOrProj) {
        this.actualsOrProj = actualsOrProj;
    }

    public Integer getCheckUncheckRec() {
        return checkUncheckRec;
    }

    public void setCheckUncheckRec(Integer checkUncheckRec) {
        this.checkUncheckRec = checkUncheckRec;
    }

    public String getHierarchyLevel() {
        return HierarchyLevel;
    }

    public void setHierarchyLevel(String HierarchyLevel) {
        this.HierarchyLevel = HierarchyLevel;
    }

    public Double getLives() {
        return lives;
    }

    public void setLives(Double lives) {
        this.lives = lives;
    }
    
    

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public Integer getRelationshipLevelSid() {
        return relationshipLevelSid;
    }

    public void setRelationshipLevelSid(Integer relationshipLevelSid) {
        this.relationshipLevelSid = relationshipLevelSid;
    }

    
    
    public String getBaseLine() {
        return BaseLine;
    }

    public void setBaseLine(String BaseLine) {
        this.BaseLine = BaseLine;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getMethodologyUsed() {
        return methodologyUsed;
    }

    public void setMethodologyUsed(String methodologyUsed) {
        this.methodologyUsed = methodologyUsed;
    }

    public Integer getRowCount() {
        return rowCount;
    }
  public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String LevelName) {
        this.LevelName = LevelName;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }


    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public String getCustomer() {
        return customer;
    }

    public String getLevelValues() {
        return LevelValues;
    }

    public void setLevelValues(String LevelValues) {
        this.LevelValues = LevelValues;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Integer projectionId) {
        this.projectionId = projectionId;
    }

    public Integer getCcpId() {
        return ccpId;
    }

    public void setCcpId(Integer ccpId) {
        this.ccpId = ccpId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemiYear() {
        return semiYear;
    }

    public void setSemiYear(String semiYear) {
        this.semiYear = semiYear;
    }

    public String getQuator() {
        return quator;
    }

    public void setQuator(String quator) {
        this.quator = quator;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getAccountGrowth() {
        return accountGrowth;
    }

    public void setAccountGrowth(String accountGrowth) {
        this.accountGrowth = accountGrowth;
    }

    public String getProductGrowth() {
        return productGrowth;
    }

    public void setProductGrowth(String productGrowth) {
        this.productGrowth = productGrowth;
    }

    public String getActualSales() {
        return actualSales;
    }

    public void setActualSales(String actualSales) {
        this.actualSales = actualSales;
    }

    public String getActualUnits() {
        return actualUnits;
    }

    public void setActualUnits(String actualUnits) {
        this.actualUnits = actualUnits;
    }

    public String getActualPrpjectionSales() {
        return actualPrpjectionSales;
    }

    public void setActualPrpjectionSales(String actualPrpjectionSales) {
        this.actualPrpjectionSales = actualPrpjectionSales;
    }

    public String getActualProjectionUnits() {
        return actualProjectionUnits;
    }

    public void setActualProjectionUnits(String actualProjectionUnits) {
        this.actualProjectionUnits = actualProjectionUnits;
    }

    public String getProjSales() {
        return projSales;
    }

    public void setProjSales(String projSales) {
        this.projSales = projSales;
    }

    public String getProjUnits() {
        return projUnits;
    }

    public void setProjUnits(String projUnits) {
        this.projUnits = projUnits;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
       public Integer getUncheckC0unt() {
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
