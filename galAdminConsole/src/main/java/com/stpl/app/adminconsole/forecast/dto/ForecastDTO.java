/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.forecast.dto;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ForecastDTO.
 *
 * @author mohamed
 */
public class ForecastDTO implements Serializable {

    /**
     * The serial ID
     */
    private static final long serialVersionUID = -1744865098431640649L;
    /**
     * The business process.
     */
    private HelperDTO businessProcess = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The forecast year.
     */
    private String forecastYear = ConstantsUtils.EMPTY;
    /**
     * The fromDate.
     */
    private String fromDate = ConstantsUtils.EMPTY;
    /**
     * The toDate.
     */
    private String toDate = ConstantsUtils.EMPTY;
    /**
     * The historicInterval.
     */
    private HelperDTO historicalInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The historicPeriod String.
     */
    private String historicalPeriod = ConstantsUtils.EMPTY;
    /**
     * The futureInterval.
     */
    private HelperDTO futureInterval = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    /**
     * The historicPeriod String.
     */
    private String futurePeriod = ConstantsUtils.EMPTY;
    /**
     * The History value
     */
    private String historicalValue = ConstantsUtils.EMPTY;
    /**
     * The History value
     */
    private String futureValue = ConstantsUtils.EMPTY;
    /**
     * Mode
     */
    private String mode = ConstantsUtils.EMPTY;
    /**
     *  version
     */
    private int versionNo = 0;
    /**
     * Process Type
     */
    private String processType = ConstantsUtils.EMPTY;
    
    /**
     * from Period
     */
   private Date fromPeriod ;
   /**
    * to Period
    */
   private Date toPeriod ;
   
   /**
     *Active Flag
     */
    private String activeFlag = ConstantsUtils.EMPTY;
    
     /**
     * createdBy
     */
    private String createdBy = ConstantsUtils.EMPTY;

    private Date fromDateSearch;
    
    private Date toDateSearch;
    
    private HelperDTO historicalIntervalFrequency = new HelperDTO(0, ConstantsUtils.SELECT_ONE);
    private String historicalIntervalValue = ConstantsUtils.EMPTY;
    private String historicalPeriodValue = ConstantsUtils.EMPTY;
    private String historicalDataIntervalFrom;
    private String historicalDataIntervalTo;
    private String historicalTimePeriodTo;
    private String historicalTimePeriodFrom;
    public String getProcessType() {
        return processType;
    }

    public void setProcessType(final String processType) {
        this.processType = processType;
    }
    
    /**
     * Gets the business process.
     *
     * @return the business process
     */
    public HelperDTO getBusinessProcess() {
        return businessProcess;
    }

    /**
     * Sets the business process.
     *
     * @param businessProcess the new business process
     */
    public void setBusinessProcess(final HelperDTO businessProcess) {
        this.businessProcess = businessProcess;
    }

    /**
     * Gets the forecast year.
     *
     * @return the forecast year
     */
    public String getForecastYear() {
        return forecastYear;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public HelperDTO getHistoricalInterval() {
        return historicalInterval;
    }

    public void setHistoricalInterval(HelperDTO historicalInterval) {
        this.historicalInterval = historicalInterval;
    }

 
    public HelperDTO getFutureInterval() {
        return futureInterval;
    }

    public void setFutureInterval(HelperDTO futureInterval) {
        this.futureInterval = futureInterval;
    }

    public String getFuturePeriod() {
        return futurePeriod;
    }

    public void setFuturePeriod(String futurePeriod) {
        this.futurePeriod = futurePeriod;
    }

    /**
     * Sets the forecast year.
     *
     * @param forecastYear the new forecast year
     */
    public void setForecastYear(final String forecastYear) {
        this.forecastYear = forecastYear;
    }

    public String getHistoricalValue() {
        return historicalValue;
    }

    public void setHistoricalValue(String historicalValue) {
        this.historicalValue = historicalValue;
    }

    public String getFutureValue() {
        return futureValue;
    }

    public void setFutureValue(String futureValue) {
        this.futureValue = futureValue;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public Date getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(Date fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public Date getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(Date toPeriod) {
        this.toPeriod = toPeriod;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getFromDateSearch() {
        return fromDateSearch;
    }

    public void setFromDateSearch(Date fromDateSearch) {
        this.fromDateSearch = fromDateSearch;
    }

    public Date getToDateSearch() {
        return toDateSearch;
    }

    public void setToDateSearch(Date toDateSearch) {
        this.toDateSearch = toDateSearch;
    }
    
    public HelperDTO getHistoricalIntervalFrequency() {
        return historicalIntervalFrequency;
    }

    public void setHistoricalIntervalFrequency(HelperDTO historicalIntervalFrequency) {
        this.historicalIntervalFrequency = historicalIntervalFrequency;
    }

    public String getHistoricalIntervalValue() {
        return historicalIntervalValue;
    }

    public void setHistoricalIntervalValue(String historicalIntervalValue) {
        this.historicalIntervalValue = historicalIntervalValue;
    }

    public String getHistoricalPeriodValue() {
        return historicalPeriodValue;
    }

    public void setHistoricalPeriodValue(String historicalPeriodValue) {
        this.historicalPeriodValue = historicalPeriodValue;
    }

    public String getHistoricalDataIntervalFrom() {
        return historicalDataIntervalFrom;
    }

    public void setHistoricalDataIntervalFrom(String historicalDataIntervalFrom) {
        this.historicalDataIntervalFrom = historicalDataIntervalFrom;
    }

    public String getHistoricalDataIntervalTo() {
        return historicalDataIntervalTo;
    }

    public void setHistoricalDataIntervalTo(String historicalDataIntervalTo) {
        this.historicalDataIntervalTo = historicalDataIntervalTo;
    }

    public String getHistoricalTimePeriodTo() {
        return historicalTimePeriodTo;
    }

    public void setHistoricalTimePeriodTo(String historicalTimePeriodTo) {
        this.historicalTimePeriodTo = historicalTimePeriodTo;
    }

    public String getHistoricalTimePeriodFrom() {
        return historicalTimePeriodFrom;
    }

    public void setHistoricalTimePeriodFrom(String historicalTimePeriodFrom) {
        this.historicalTimePeriodFrom = historicalTimePeriodFrom;
    }
}
