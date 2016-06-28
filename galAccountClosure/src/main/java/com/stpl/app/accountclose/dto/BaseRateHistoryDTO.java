/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class BaseRateHistoryDTO {

    private String customerName = StringUtils.EMPTY;
    private int currentRate;
    private int suggestedRate;
    private int accrualRate;
    private Date startDate;
    private Date endDate;
    private String alternateContract;
    private String alternateProduct;
    private String methodology;
    private String reasonCode;
    private String notes;
    private String checkRecord;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(int currentRate) {
        this.currentRate = currentRate;
    }

    public int getSuggestedRate() {
        return suggestedRate;
    }

    public void setSuggestedRate(int suggestedRate) {
        this.suggestedRate = suggestedRate;
    }

    public int getAccrualRate() {
        return accrualRate;
    }

    public void setAccrualRate(int accrualRate) {
        this.accrualRate = accrualRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAlternateContract() {
        return alternateContract;
    }

    public void setAlternateContract(String alternateContract) {
        this.alternateContract = alternateContract;
    }

    public String getAlternateProduct() {
        return alternateProduct;
    }

    public void setAlternateProduct(String alternateProduct) {
        this.alternateProduct = alternateProduct;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(String checkRecord) {
        this.checkRecord = checkRecord;
    }

}
