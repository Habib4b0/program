package com.stpl.gtn.gtn2o.ws.entity.transaction;
// Generated Feb 6, 2018 3:06:35 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * StagReturnRateForecast generated by hbm2java
 */
public class StagReturnRateForecast  implements java.io.Serializable {


     private int stagReturnRateForecastSid;
     private BigDecimal returnRateForecastInterfaceId;
     private String itemId;
     private String itemNo;
     private String itemName;
     private String forecastYear;
     private String forecastMonth;
     private Double rate;
     private String forecastName;
     private String forecastVer;
     private String modifiedBy;
     private Date modifiedDate;
     private String createdBy;
     private Date createdDate;
     private String addChgDelIndicator;
     private String batchId;
     private String source;

    public StagReturnRateForecast() {
    }

	
    public StagReturnRateForecast(int stagReturnRateForecastSid, BigDecimal returnRateForecastInterfaceId, String itemId, Double rate, String addChgDelIndicator, String batchId) {
        this.stagReturnRateForecastSid = stagReturnRateForecastSid;
        this.returnRateForecastInterfaceId = returnRateForecastInterfaceId;
        this.itemId = itemId;
        this.rate = rate;
        this.addChgDelIndicator = addChgDelIndicator;
        this.batchId = batchId;
    }
    public StagReturnRateForecast(int stagReturnRateForecastSid, BigDecimal returnRateForecastInterfaceId, String itemId, String itemNo, String itemName, String forecastYear, String forecastMonth, Double rate, String forecastName, String forecastVer, String modifiedBy, Date modifiedDate, String createdBy, Date createdDate, String addChgDelIndicator, String batchId, String source) {
       this.stagReturnRateForecastSid = stagReturnRateForecastSid;
       this.returnRateForecastInterfaceId = returnRateForecastInterfaceId;
       this.itemId = itemId;
       this.itemNo = itemNo;
       this.itemName = itemName;
       this.forecastYear = forecastYear;
       this.forecastMonth = forecastMonth;
       this.rate = rate;
       this.forecastName = forecastName;
       this.forecastVer = forecastVer;
       this.modifiedBy = modifiedBy;
       this.modifiedDate = modifiedDate;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
       this.addChgDelIndicator = addChgDelIndicator;
       this.batchId = batchId;
       this.source = source;
    }
   
    public int getStagReturnRateForecastSid() {
        return this.stagReturnRateForecastSid;
    }
    
    public void setStagReturnRateForecastSid(int stagReturnRateForecastSid) {
        this.stagReturnRateForecastSid = stagReturnRateForecastSid;
    }
    public BigDecimal getReturnRateForecastInterfaceId() {
        return this.returnRateForecastInterfaceId;
    }
    
    public void setReturnRateForecastInterfaceId(BigDecimal returnRateForecastInterfaceId) {
        this.returnRateForecastInterfaceId = returnRateForecastInterfaceId;
    }
    public String getItemId() {
        return this.itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getItemNo() {
        return this.itemNo;
    }
    
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }
    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
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
    public Double getRate() {
        return this.rate;
    }
    
    public void setRate(Double rate) {
        this.rate = rate;
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




}


