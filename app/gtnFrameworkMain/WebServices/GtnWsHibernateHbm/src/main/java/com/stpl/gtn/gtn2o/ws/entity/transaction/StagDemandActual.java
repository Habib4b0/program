package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated Jun 2, 2017 4:52:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * StagDemandActual generated by hbm2java
 */
public class StagDemandActual  implements java.io.Serializable {


     private int stagDemandActualSid;
     private String   demandActualInterfaceId;
     private String forecastType;
     private String forecastYear;
     private String forecastMonth;
     private String itemId;
     private String itemIdentifierCodeQualifier;
     private String itemIdentifier;
     private String brandId;
     private String segment;
     private String   marketSizeUnits;
     private String marketShareRatio;
     private String   marketShareUnits;
     private String   totalDemandUnits;
     private String   totalDemandAmount;
     private String   grossUnits;
     private String   grossPrice;
     private String   grossAmount;
     private String   netSalesPrice;
     private String   netSalesAmount;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String addChgDelIndicator;
     private String batchId;
     private String source;
     private String country;
     private String organizationKey;

    public StagDemandActual() {
    }

	
    public StagDemandActual(int stagDemandActualSid, String   demandActualInterfaceId, String forecastType, String forecastYear, String forecastMonth, String itemId, String   marketSizeUnits, String   marketShareUnits, String   totalDemandUnits, String   grossUnits, String   grossPrice, String   grossAmount, String   netSalesPrice, String   netSalesAmount, String addChgDelIndicator, String batchId, String source, String country, String organizationKey) {
        this.stagDemandActualSid = stagDemandActualSid;
        this.demandActualInterfaceId = demandActualInterfaceId;
        this.forecastType = forecastType;
        this.forecastYear = forecastYear;
        this.forecastMonth = forecastMonth;
        this.itemId = itemId;
        this.marketSizeUnits = marketSizeUnits;
        this.marketShareUnits = marketShareUnits;
        this.totalDemandUnits = totalDemandUnits;
        this.grossUnits = grossUnits;
        this.grossPrice = grossPrice;
        this.grossAmount = grossAmount;
        this.netSalesPrice = netSalesPrice;
        this.netSalesAmount = netSalesAmount;
        this.addChgDelIndicator = addChgDelIndicator;
        this.batchId = batchId;
        this.source = source;
        this.country = country;
        this.organizationKey = organizationKey;
    }
    public StagDemandActual(int stagDemandActualSid, String   demandActualInterfaceId, String forecastType, String forecastYear, String forecastMonth, String itemId, String itemIdentifierCodeQualifier, String itemIdentifier, String brandId, String segment, String   marketSizeUnits, String marketShareRatio, String   marketShareUnits, String   totalDemandUnits, String   totalDemandAmount, String   grossUnits, String   grossPrice, String   grossAmount, String   netSalesPrice, String   netSalesAmount, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String addChgDelIndicator, String batchId, String source, String country, String organizationKey) {
       this.stagDemandActualSid = stagDemandActualSid;
       this.demandActualInterfaceId = demandActualInterfaceId;
       this.forecastType = forecastType;
       this.forecastYear = forecastYear;
       this.forecastMonth = forecastMonth;
       this.itemId = itemId;
       this.itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
       this.itemIdentifier = itemIdentifier;
       this.brandId = brandId;
       this.segment = segment;
       this.marketSizeUnits = marketSizeUnits;
       this.marketShareRatio = marketShareRatio;
       this.marketShareUnits = marketShareUnits;
       this.totalDemandUnits = totalDemandUnits;
       this.totalDemandAmount = totalDemandAmount;
       this.grossUnits = grossUnits;
       this.grossPrice = grossPrice;
       this.grossAmount = grossAmount;
       this.netSalesPrice = netSalesPrice;
       this.netSalesAmount = netSalesAmount;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.addChgDelIndicator = addChgDelIndicator;
       this.batchId = batchId;
       this.source = source;
       this.country = country;
       this.organizationKey = organizationKey;
    }
   
    public int getStagDemandActualSid() {
        return this.stagDemandActualSid;
    }
    
    public void setStagDemandActualSid(int stagDemandActualSid) {
        this.stagDemandActualSid = stagDemandActualSid;
    }
    public String   getDemandActualInterfaceId() {
        return this.demandActualInterfaceId;
    }
    
    public void setDemandActualInterfaceId(String   demandActualInterfaceId) {
        this.demandActualInterfaceId = demandActualInterfaceId;
    }
    public String getForecastType() {
        return this.forecastType;
    }
    
    public void setForecastType(String forecastType) {
        this.forecastType = forecastType;
    }
    public String getForecastYear() {
        return this.forecastYear;
    }
    
    public void setForecastYear(String forecastYear) {
        this.forecastYear = forecastYear;
    }
    public String getForecastMonth() {
        return this.forecastMonth;
    }
    
    public void setForecastMonth(String forecastMonth) {
        this.forecastMonth = forecastMonth;
    }
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getItemIdentifierCodeQualifier() {
        return this.itemIdentifierCodeQualifier;
    }
    
    public void setItemIdentifierCodeQualifier(String itemIdentifierCodeQualifier) {
        this.itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }
    
    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }
    public String getBrandId() {
        return this.brandId;
    }
    
    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getSegment() {
        return this.segment;
    }
    
    public void setSegment(String segment) {
        this.segment = segment;
    }
    public String   getMarketSizeUnits() {
        return this.marketSizeUnits;
    }
    
    public void setMarketSizeUnits(String   marketSizeUnits) {
        this.marketSizeUnits = marketSizeUnits;
    }
    public String getMarketShareRatio() {
        return this.marketShareRatio;
    }
    
    public void setMarketShareRatio(String marketShareRatio) {
        this.marketShareRatio = marketShareRatio;
    }
    public String   getMarketShareUnits() {
        return this.marketShareUnits;
    }
    
    public void setMarketShareUnits(String   marketShareUnits) {
        this.marketShareUnits = marketShareUnits;
    }
    public String   getTotalDemandUnits() {
        return this.totalDemandUnits;
    }
    
    public void setTotalDemandUnits(String   totalDemandUnits) {
        this.totalDemandUnits = totalDemandUnits;
    }
    public String   getTotalDemandAmount() {
        return this.totalDemandAmount;
    }
    
    public void setTotalDemandAmount(String   totalDemandAmount) {
        this.totalDemandAmount = totalDemandAmount;
    }
    public String   getGrossUnits() {
        return this.grossUnits;
    }
    
    public void setGrossUnits(String   grossUnits) {
        this.grossUnits = grossUnits;
    }
    public String   getGrossPrice() {
        return this.grossPrice;
    }
    
    public void setGrossPrice(String   grossPrice) {
        this.grossPrice = grossPrice;
    }
    public String   getGrossAmount() {
        return this.grossAmount;
    }
    
    public void setGrossAmount(String   grossAmount) {
        this.grossAmount = grossAmount;
    }
    public String   getNetSalesPrice() {
        return this.netSalesPrice;
    }
    
    public void setNetSalesPrice(String   netSalesPrice) {
        this.netSalesPrice = netSalesPrice;
    }
    public String   getNetSalesAmount() {
        return this.netSalesAmount;
    }
    
    public void setNetSalesAmount(String   netSalesAmount) {
        this.netSalesAmount = netSalesAmount;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getModifiedBy() {
        return this.modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getModifiedDate() {
        return this.modifiedDate;
    }
    
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    public String getAddChgDelIndicator() {
        return this.addChgDelIndicator;
    }
    
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        this.addChgDelIndicator = addChgDelIndicator;
    }
    public String getBatchId() {
        return this.batchId;
    }
    
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getOrganizationKey() {
        return this.organizationKey;
    }
    
    public void setOrganizationKey(String organizationKey) {
        this.organizationKey = organizationKey;
    }




}


