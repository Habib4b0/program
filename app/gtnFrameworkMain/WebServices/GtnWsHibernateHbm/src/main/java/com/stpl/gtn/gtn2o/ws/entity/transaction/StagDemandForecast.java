package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated Jun 2, 2017 4:52:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * StagDemandForecast generated by hbm2java
 */
public class StagDemandForecast  implements java.io.Serializable {


     private int stagDemandForecastSid;
     private String demandForecastInterfaceId;
     private String forecastType;
     private String forecastYear;
     private String forecastMonth;
     private String itemId;
     private String itemIdentifierCodeQualifier;
     private String itemIdentifier;
     private String brandId;
     private String segment;
     private String marketSizeUnits;
     private String marketShareRatio;
     private String marketShareUnits;
     private String uncapturedUnits;
     private String uncapturedUnitsRatio;
     private String totalDemandUnits;
     private String totalDemandAmount;
     private String inventoryUnitChange;
     private String grossUnits;
     private String grossPrice;
     private String grossAmount;
     private String netSalesPrice;
     private String netSalesAmount;
     private String createdBy;
     private Date createdDate;
     private String modifiedBy;
     private Date modifiedDate;
     private String addChgDelIndicator;
     private String batchId;
     private String source;
     private String forecastName;
     private String forecastVer;
     private String country;
     private String organizationKey;

    public StagDemandForecast() {
    }

	
    public StagDemandForecast(int stagDemandForecastSid, String demandForecastInterfaceId, String forecastType, String forecastYear, String forecastMonth, String itemId, String marketSizeUnits, String marketShareRatio, String marketShareUnits, String uncapturedUnits, String totalDemandUnits, String inventoryUnitChange, String grossUnits, String grossPrice, String grossAmount, String netSalesPrice, String netSalesAmount, String addChgDelIndicator, String batchId, String source, String forecastName, String forecastVer, String country, String organizationKey) {
        this.stagDemandForecastSid = stagDemandForecastSid;
        this.demandForecastInterfaceId = demandForecastInterfaceId;
        this.forecastType = forecastType;
        this.forecastYear = forecastYear;
        this.forecastMonth = forecastMonth;
        this.itemId = itemId;
        this.marketSizeUnits = marketSizeUnits;
        this.marketShareRatio = marketShareRatio;
        this.marketShareUnits = marketShareUnits;
        this.uncapturedUnits = uncapturedUnits;
        this.totalDemandUnits = totalDemandUnits;
        this.inventoryUnitChange = inventoryUnitChange;
        this.grossUnits = grossUnits;
        this.grossPrice = grossPrice;
        this.grossAmount = grossAmount;
        this.netSalesPrice = netSalesPrice;
        this.netSalesAmount = netSalesAmount;
        this.addChgDelIndicator = addChgDelIndicator;
        this.batchId = batchId;
        this.source = source;
        this.forecastName = forecastName;
        this.forecastVer = forecastVer;
        this.country = country;
        this.organizationKey = organizationKey;
    }
    public StagDemandForecast(int stagDemandForecastSid, String demandForecastInterfaceId, String forecastType, String forecastYear, String forecastMonth, String itemId, String itemIdentifierCodeQualifier, String itemIdentifier, String brandId, String segment, String marketSizeUnits, String marketShareRatio, String marketShareUnits, String uncapturedUnits, String uncapturedUnitsRatio, String totalDemandUnits, String totalDemandAmount, String inventoryUnitChange, String grossUnits, String grossPrice, String grossAmount, String netSalesPrice, String netSalesAmount, String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String addChgDelIndicator, String batchId, String source, String forecastName, String forecastVer, String country, String organizationKey) {
       this.stagDemandForecastSid = stagDemandForecastSid;
       this.demandForecastInterfaceId = demandForecastInterfaceId;
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
       this.uncapturedUnits = uncapturedUnits;
       this.uncapturedUnitsRatio = uncapturedUnitsRatio;
       this.totalDemandUnits = totalDemandUnits;
       this.totalDemandAmount = totalDemandAmount;
       this.inventoryUnitChange = inventoryUnitChange;
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
       this.forecastName = forecastName;
       this.forecastVer = forecastVer;
       this.country = country;
       this.organizationKey = organizationKey;
    }
   
    public int getStagDemandForecastSid() {
        return this.stagDemandForecastSid;
    }
    
    public void setStagDemandForecastSid(int stagDemandForecastSid) {
        this.stagDemandForecastSid = stagDemandForecastSid;
    }
    public String getDemandForecastInterfaceId() {
        return this.demandForecastInterfaceId;
    }
    
    public void setDemandForecastInterfaceId(String demandForecastInterfaceId) {
        this.demandForecastInterfaceId = demandForecastInterfaceId;
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
    public String getMarketSizeUnits() {
        return this.marketSizeUnits;
    }
    
    public void setMarketSizeUnits(String marketSizeUnits) {
        this.marketSizeUnits = marketSizeUnits;
    }
    public String getMarketShareRatio() {
        return this.marketShareRatio;
    }
    
    public void setMarketShareRatio(String marketShareRatio) {
        this.marketShareRatio = marketShareRatio;
    }
    public String getMarketShareUnits() {
        return this.marketShareUnits;
    }
    
    public void setMarketShareUnits(String marketShareUnits) {
        this.marketShareUnits = marketShareUnits;
    }
    public String getUncapturedUnits() {
        return this.uncapturedUnits;
    }
    
    public void setUncapturedUnits(String uncapturedUnits) {
        this.uncapturedUnits = uncapturedUnits;
    }
    public String getUncapturedUnitsRatio() {
        return this.uncapturedUnitsRatio;
    }
    
    public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
        this.uncapturedUnitsRatio = uncapturedUnitsRatio;
    }
    public String getTotalDemandUnits() {
        return this.totalDemandUnits;
    }
    
    public void setTotalDemandUnits(String totalDemandUnits) {
        this.totalDemandUnits = totalDemandUnits;
    }
    public String getTotalDemandAmount() {
        return this.totalDemandAmount;
    }
    
    public void setTotalDemandAmount(String totalDemandAmount) {
        this.totalDemandAmount = totalDemandAmount;
    }
    public String getInventoryUnitChange() {
        return this.inventoryUnitChange;
    }
    
    public void setInventoryUnitChange(String inventoryUnitChange) {
        this.inventoryUnitChange = inventoryUnitChange;
    }
    public String getGrossUnits() {
        return this.grossUnits;
    }
    
    public void setGrossUnits(String grossUnits) {
        this.grossUnits = grossUnits;
    }
    public String getGrossPrice() {
        return this.grossPrice;
    }
    
    public void setGrossPrice(String grossPrice) {
        this.grossPrice = grossPrice;
    }
    public String getGrossAmount() {
        return this.grossAmount;
    }
    
    public void setGrossAmount(String grossAmount) {
        this.grossAmount = grossAmount;
    }
    public String getNetSalesPrice() {
        return this.netSalesPrice;
    }
    
    public void setNetSalesPrice(String netSalesPrice) {
        this.netSalesPrice = netSalesPrice;
    }
    public String getNetSalesAmount() {
        return this.netSalesAmount;
    }
    
    public void setNetSalesAmount(String netSalesAmount) {
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
    public String getForecastName() {
        return this.forecastName;
    }
    
    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }
    public String getForecastVer() {
        return this.forecastVer;
    }
    
    public void setForecastVer(String forecastVer) {
        this.forecastVer = forecastVer;
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


