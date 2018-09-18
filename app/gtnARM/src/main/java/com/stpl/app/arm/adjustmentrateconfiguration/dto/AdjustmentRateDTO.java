/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class AdjustmentRateDTO implements Serializable {

    private int rateConfigDetailsSid;
    private int inventoryCustomer;
    private String inventoryCalculation = StringUtils.EMPTY;
    private String exclusionDetails = StringUtils.EMPTY;
    private String price;
    private String adjustedPrice;
    private String baselinePrice;
    private int inflationFactor;
    private int reserveDate;
    private int rateBasis;
    private Integer rateFrequency = 0;
    private Integer ratePeriod = 0;
    private String month = StringUtils.EMPTY;
    private int dateType;
    private Boolean checkRecord = Boolean.FALSE;
    private int inventoryDetails = 0;
    private int viewMasterSid;
    private final Map<String, Object> components = new HashMap<>();
    private String invenCust = StringUtils.EMPTY;

    // excel  
    private Integer excelrateConfigDetailsSid;
    private String excelinventoryCustomer = StringUtils.EMPTY;
    private String excelinventoryCalculation = StringUtils.EMPTY;
    private String excelexclusionDetails = StringUtils.EMPTY;
    private String excelprice = StringUtils.EMPTY;
    private String excelreserveDate = StringUtils.EMPTY;
    private String excelrateBasis = StringUtils.EMPTY;
    private String excelrateFrequency = StringUtils.EMPTY;
    private String excelratePeriod = StringUtils.EMPTY;
    private String excelmonth = StringUtils.EMPTY;
    private String exceldateType = StringUtils.EMPTY;
    private Boolean excelcheckRecord;
    private String excelinventoryDetails = StringUtils.EMPTY;
    private String exceladjustmentPrice = StringUtils.EMPTY;
    private String excelbaseLinePrice = StringUtils.EMPTY;
    private Integer excelviewMasterSid;

    public AdjustmentRateDTO(List<Integer> parameterSet, String month, String price, String exclusionDetails, Integer ratePeriod) {
        this.rateConfigDetailsSid = parameterSet.get(0);
        this.month = month;
        this.dateType = parameterSet.get(1);
        this.price = price;
        this.exclusionDetails = exclusionDetails;
        this.rateBasis = parameterSet.get(2);
        this.rateFrequency = parameterSet.get(3);
        this.ratePeriod = ratePeriod;
        this.viewMasterSid = parameterSet.get(4);
    }

    public AdjustmentRateDTO(List<Integer> parameterSet, String month, String inventoryCalculation, String price) {
        this.rateConfigDetailsSid = parameterSet.get(0);
        this.month = month;
        this.inventoryCustomer = parameterSet.get(5);
        this.inventoryCalculation = inventoryCalculation;
        this.price = price;
        this.reserveDate = parameterSet.get(6);
        this.rateBasis = parameterSet.get(2);
        this.rateFrequency = parameterSet.get(3);
        this.ratePeriod = parameterSet.get(7);
        this.inventoryDetails = parameterSet.get(8);
        this.viewMasterSid = parameterSet.get(4);
    }
//"inventoryDetails", "baselinePrice", "adjustedPrice"

    public AdjustmentRateDTO(List<Integer> parameterSet, String month, String inventoryCalculation, String price, String baseLinePrice, String adjustedPrice, String exclusionDetails) {
        this.rateConfigDetailsSid = parameterSet.get(0);
        this.month = month;
        this.inventoryCustomer = 0;
        this.inventoryCalculation = inventoryCalculation;
        this.price = price;
        this.reserveDate = 0;
        this.rateBasis = parameterSet.get(2);
        this.rateFrequency = parameterSet.get(3);
        this.ratePeriod = parameterSet.get(7);
        this.inventoryDetails = parameterSet.get(8);
        this.viewMasterSid = parameterSet.get(4);
        this.adjustedPrice = adjustedPrice;
        this.baselinePrice = baseLinePrice;
        this.exclusionDetails = exclusionDetails;
        this.dateType = parameterSet.get(1);
    }

    public AdjustmentRateDTO() {
    }

    public boolean validateAccrual() {
        if (this.dateType == 0) {
            return false;
        }
        if (this.rateBasis == 0) {
            return false;
        }
        if (this.rateFrequency == 0) {
            return false;
        }
        if (this.ratePeriod == 0) {
            return false;
        }
        return true;
    }

    public boolean validateInventory() {
        if (this.inventoryCustomer == 0) {
            return false;
        }
        if (this.rateBasis == 0) {
            return false;
        }
        if (this.rateFrequency == 0) {
            return false;
        }
        if (this.ratePeriod == 0) {
            return false;
        }
        return true;
    }

    public int getRateConfigDetailsSid() {
        return rateConfigDetailsSid;
    }

    public void setRateConfigDetailsSid(int rateConfigDetailsSid) {
        this.rateConfigDetailsSid = rateConfigDetailsSid;
    }

    public int getInventoryCustomer() {
        return inventoryCustomer;
    }

    public void setInventoryCustomer(int rateInventoryCustomer) {
        this.inventoryCustomer = rateInventoryCustomer;
    }

    public String getInventoryCalculation() {
        return inventoryCalculation;
    }

    public void setInventoryCalculation(String rateInventoryCalculation) {
        this.inventoryCalculation = rateInventoryCalculation;
    }

    public String getExclusionDetails() {
        return exclusionDetails;
    }

    public void setExclusionDetails(String rateExclusionDetails) {
        this.exclusionDetails = rateExclusionDetails;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String ratePrice) {
        this.price = ratePrice;
    }

    public int getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(int reserveDate) {
        this.reserveDate = reserveDate;
    }

    public int getRateBasis() {
        return rateBasis;
    }

    public void setRateBasis(int rateBasis) {
        this.rateBasis = rateBasis;
    }

    public int getRateFrequency() {
        return rateFrequency;
    }

    public void setRateFrequency(int rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    public Integer getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(Integer ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getDateType() {
        return dateType;
    }

    public void setDateType(int dateType) {
        this.dateType = dateType;
    }

    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public Object getComponent(String name) {
        return components.get(name);
    }

    public void setComponent(String name, Object component) {
        this.components.put(name, component);
    }

    public int getInventoryDetails() {
        return inventoryDetails;
    }

    public void setInventoryDetails(int inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }

    public int getViewMasterSid() {
        return viewMasterSid;
    }

    public void setViewMasterSid(int viewMasterSid) {
        this.viewMasterSid = viewMasterSid;
    }

    public String getInvenCust() {
        return invenCust;
    }

    public void setInvenCust(String invenCust) {
        this.invenCust = invenCust;
    }

    public String getAdjustedPrice() {
        return adjustedPrice;
    }

    public void setAdjustedPrice(String adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }

    public String getBaselinePrice() {
        return baselinePrice;
    }

    public void setBaselinePrice(String baselinePrice) {
        this.baselinePrice = baselinePrice;
    }

    public int getInflationFactor() {
        return inflationFactor;
    }

    public void setInflationFactor(int inflationFactor) {
        this.inflationFactor = inflationFactor;
    }

    public Integer getexcelrateConfigDetailsSid() {
        return excelrateConfigDetailsSid;
    }

    public void setexcelrateConfigDetailsSid(Integer excelrateConfigDetailsSid) {
        this.excelrateConfigDetailsSid = excelrateConfigDetailsSid;
    }

    public String getexcelinventoryCustomer() {
        return excelinventoryCustomer;
    }

    public void setexcelinventoryCustomer(String excelinventoryCustomer) {
        this.excelinventoryCustomer = excelinventoryCustomer;
    }

    public String getexcelinventoryCalculation() {
        return excelinventoryCalculation;
    }

    public void setexcelinventoryCalculation(String excelinventoryCalculation) {
        this.excelinventoryCalculation = excelinventoryCalculation;
    }

    public String getexcelexclusionDetails() {
        return excelexclusionDetails;
    }

    public void setexcelexclusionDetails(String excelexclusionDetails) {
        this.excelexclusionDetails = excelexclusionDetails;
    }

    public String getexcelprice() {
        return excelprice;
    }

    public void setexcelprice(String excelprice) {
        this.excelprice = excelprice;
    }

    public String getexcelreserveDate() {
        return excelreserveDate;
    }

    public void setexcelreserveDate(String excelreserveDate) {
        this.excelreserveDate = excelreserveDate;
    }

    public String getexcelrateBasis() {
        return excelrateBasis;
    }

    public void setexcelrateBasis(String excelrateBasis) {
        this.excelrateBasis = excelrateBasis;
    }

    public String getexcelrateFrequency() {
        return excelrateFrequency;
    }

    public void setexcelrateFrequency(String excelrateFrequency) {
        this.excelrateFrequency = excelrateFrequency;
    }

    public String getexcelratePeriod() {
        return excelratePeriod;
    }

    public void setexcelratePeriod(String excelratePeriod) {
        this.excelratePeriod = excelratePeriod;
    }

    public String getexcelmonth() {
        return excelmonth;
    }

    public void setexcelmonth(String excelmonth) {
        this.excelmonth = excelmonth;
    }

    public String getexceldateType() {
        return exceldateType;
    }

    public void setexceldateType(String exceldateType) {
        this.exceldateType = exceldateType;
    }

    public Boolean getexcelcheckRecord() {
        return excelcheckRecord;
    }

    public void setexcelcheckRecord(Boolean excelcheckRecord) {
        this.excelcheckRecord = excelcheckRecord;
    }

    public String getexcelinventoryDetails() {
        return excelinventoryDetails;
    }

    public void setexcelinventoryDetails(String excelinventoryDetails) {
        this.excelinventoryDetails = excelinventoryDetails;
    }

    public String getexceladjustmentPrice() {
        return exceladjustmentPrice;
    }

    public void setexceladjustmentPrice(String exceladjustmentPrice) {
        this.exceladjustmentPrice = exceladjustmentPrice;
    }

    public String getexcelbaseLinePrice() {
        return excelbaseLinePrice;
    }

    public void setexcelbaseLinePrice(String excelbaseLinePrice) {
        this.excelbaseLinePrice = excelbaseLinePrice;
    }

    public Integer getexcelviewMasterSid() {
        return excelviewMasterSid;
    }

    public void setexcelviewMasterSid(Integer excelviewMasterSid) {
        this.excelviewMasterSid = excelviewMasterSid;
    }

    private void writeObject(ObjectOutputStream adjRateDtoout) throws IOException {
        adjRateDtoout.defaultWriteObject();
    }

    private void readObject(ObjectInputStream adjRateDtoout) throws IOException, ClassNotFoundException {
        adjRateDtoout.defaultReadObject();
    }
}
