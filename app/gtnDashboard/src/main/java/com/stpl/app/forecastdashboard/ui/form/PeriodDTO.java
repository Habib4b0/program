/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.forecastdashboard.ui.form;

import java.math.BigDecimal;



/**
 *
 * @author maheshj
 */
public class PeriodDTO {
    Integer projectionId=0;
    Integer rowId=new Integer(0);
    Integer ccpId=new Integer(0);
    String level="";
    String LevelName="";
    Integer levelNo=new Integer(0);
    String key="";
    String LevelValues="";
    String HierarchyLevel="";
    Integer actualsOrProj=new Integer(0);
  
    String accountGrowth="0.0";
    String productGrowth="0.0";
    String actualSales="0.0";
    String actualDiscounts="0.0";
    String actualUnits="0.0";
    String actualPrpjectionSales="0.0";
    String actualProjectionUnits="0.0";
    String projSales="0.0";
    String projDiscount="0.0";
    String projUnits="0.0";
    String period=""; 
    String year="";
    String semiYear="";
    String quator="";
    Integer month=new Integer("0");
    String sales="0.0";
    String units="0.0";
    Integer salesId=new Integer("0");
    String customer="";
    String product="";
    String contract="";
    Integer rowCount=new Integer("0");
    String BaseLine="";
    String userGroup="";
    String methodologyUsed="";
    String hierarchyNo = "%";

    String discountPerUnit = "";
    String discountPer = "";
    String unitVolume = "";
    String totDiscountAmount = "";
    String actualTotalUnits = "0.0";
    String actualExcludedUnits = "0.0";
    String actualNetUnits = "0.0";
    String actualPrice = "0.0";
    String projectedTotalUnits="0.0";
    String projectedExcludedUnits = "0.0";
    String projectedNetUnits = "0.0";
    String projectedPrice = "0.0";
   
     Integer checkUncheckRec=new Integer(0);
    Double lives=0.0;
    
    Integer relationshipLevelSid=new Integer(0);

    public String getDiscountPerUnit() {
        return discountPerUnit;
    }

    public void setDiscountPerUnit(String discountPerUnit) {
        this.discountPerUnit = discountPerUnit;
    }

    public String getDiscountPer() {
        return discountPer;
    }

    public void setDiscountPer(String discountPer) {
        this.discountPer = discountPer;
    }

    public String getUnitVolume() {
        return unitVolume;
    }

    public void setUnitVolume(String unitVolume) {
        this.unitVolume = unitVolume;
    }

    public String getTotDiscountAmount() {
        return totDiscountAmount;
    }

    public void setTotDiscountAmount(String totDiscountAmount) {
        this.totDiscountAmount = totDiscountAmount;
    }
    
    

    public String getActualDiscounts() {
        return actualDiscounts;
    }

    public void setActualDiscounts(String actualDiscounts) {
        this.actualDiscounts = actualDiscounts;
    }

    public String getProjDiscount() {
        return projDiscount;
    }

    public void setProjDiscount(String projDiscount) {
        this.projDiscount = projDiscount;
    }

    
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

    public String getActualTotalUnits() {
        return actualTotalUnits;
    }

    public void setActualTotalUnits(String actualTotalUnits) {
        this.actualTotalUnits = actualTotalUnits;
    }

    public String getActualExcludedUnits() {
        return actualExcludedUnits;
    }

    public void setActualExcludedUnits(String actualExcludedUnits) {
        this.actualExcludedUnits = actualExcludedUnits;
    }

    public String getActualNetUnits() {
        return actualNetUnits;
    }

    public void setActualNetUnits(String actualNetUnits) {
        this.actualNetUnits = actualNetUnits;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getProjectedTotalUnits() {
        return projectedTotalUnits;
    }

    public void setProjectedTotalUnits(String projectedTotalUnits) {
        this.projectedTotalUnits = projectedTotalUnits;
    }

    public String getProjectedExcludedUnits() {
        return projectedExcludedUnits;
    }

    public void setProjectedExcludedUnits(String projectedExcludedUnits) {
        this.projectedExcludedUnits = projectedExcludedUnits;
    }

    public String getProjectedNetUnits() {
        return projectedNetUnits;
    }

    public void setProjectedNetUnits(String projectedNetUnits) {
        this.projectedNetUnits = projectedNetUnits;
    }

    public String getProjectedPrice() {
        return projectedPrice;
    }

    public void setProjectedPrice(String projectedPrice) {
        this.projectedPrice = projectedPrice;
    }

   
    
}
