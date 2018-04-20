/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author Gopinath.Mathiyalaga
 */
public class RateExcelDTO extends ExtMapDTO implements Comparator<RateExcelDTO> {

    private Integer rateConfigDetailsSid;
    private String inventoryCustomer = StringUtils.EMPTY;
    private String inventoryCalculation = StringUtils.EMPTY;
    private String exclusionDetails = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;
    private String reserveDate = StringUtils.EMPTY;
    private String rateBasis = StringUtils.EMPTY;
    private String rateFrequency = StringUtils.EMPTY;
    private String ratePeriod = StringUtils.EMPTY;
    private String month = StringUtils.EMPTY;
    private String dateType = StringUtils.EMPTY;
    private Boolean checkRecord;
    private String inventoryDetails = StringUtils.EMPTY;
    private String adjustmentPrice = StringUtils.EMPTY;
    private String baseLinePrice = StringUtils.EMPTY;
    private Integer viewMasterSid;

    public RateExcelDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public Integer getRateConfigDetailsSid() {
        return rateConfigDetailsSid;
    }

    public void setRateConfigDetailsSid(Integer rateConfigDetailsSid) {
        this.rateConfigDetailsSid = rateConfigDetailsSid;
    }

    public String getInventoryCustomer() {
        return inventoryCustomer;
    }

    public void setInventoryCustomer(String inventoryCustomer) {
        this.inventoryCustomer = inventoryCustomer;
    }

    public String getInventoryCalculation() {
        return inventoryCalculation;
    }

    public void setInventoryCalculation(String inventoryCalculation) {
        this.inventoryCalculation = inventoryCalculation;
    }

    public String getExclusionDetails() {
        return exclusionDetails;
    }

    public void setExclusionDetails(String exclusionDetails) {
        this.exclusionDetails = exclusionDetails;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(String reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getRateBasis() {
        return rateBasis;
    }

    public void setRateBasis(String rateBasis) {
        this.rateBasis = rateBasis;
    }

    public String getRateFrequency() {
        return rateFrequency;
    }

    public void setRateFrequency(String rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    public String getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(String ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getInventoryDetails() {
        return inventoryDetails;
    }

    public void setInventoryDetails(String inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }

    public Integer getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(Integer viewMasterSid) {
        this.viewMasterSid = viewMasterSid;
    }

    @Override
    public int compare(RateExcelDTO o1, RateExcelDTO o2) {
        return 0;
    }

    public String getAdjustmentPrice() {
        return adjustmentPrice;
    }

    public void setAdjustmentPrice(String adjustmentPrice) {
        this.adjustmentPrice = adjustmentPrice;
    }

    public String getBaseLinePrice() {
        return baseLinePrice;
    }

    public void setBaseLinePrice(String baseLinePrice) {
        this.baseLinePrice = baseLinePrice;
    }

}
