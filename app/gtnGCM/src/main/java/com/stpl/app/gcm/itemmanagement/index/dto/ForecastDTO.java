/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.dto;

import java.util.Date;

public class ForecastDTO {

    private Integer projectionStartYear;
    private Integer projectionStartMonth;
    private Integer projectionEndYear;
    private Integer projectionEndMonth;
    private Integer forecastStartYear;
    private Integer forecastStartMonth;
    private Integer forecastEndYear;
    private Integer forecastEndMonth;
    private Integer historyStartYear;
    private Integer historyStartMonth;
    private Integer historyEndYear;
    private Integer historyEndMonth;
    private Date forecastStartDate;
    private Date forecastEndDate;
    private Date projectionStartDate;
    private Date projectionEndDate;
    private Date historyStartDate;
    private Date historyEndDate;

    public ForecastDTO() {
    }

    public Date getForecastStartDate() {
        return forecastStartDate == null ? null : (Date) forecastStartDate.clone();
    }

    public void setForecastStartDate(Date forecastStartDate) {
        this.forecastStartDate = forecastStartDate == null ? null : (Date) forecastStartDate.clone();
    }

    public Date getForecastEndDate() {
        return forecastEndDate == null ? null : (Date) forecastEndDate.clone();
    }

    public void setForecastEndDate(Date forecastEndDate) {
        this.forecastEndDate = forecastEndDate == null ? null : (Date) forecastEndDate.clone();
    }

    public Date getProjectionStartDate() {
        return projectionStartDate == null ? null : (Date) projectionStartDate.clone();
    }

    public void setProjectionStartDate(Date projectionStartDate) {
        this.projectionStartDate = projectionStartDate == null ? null : (Date) projectionStartDate.clone();
    }

    public Date getProjectionEndDate() {
        return projectionEndDate == null ? null : (Date) projectionEndDate.clone();
    }

    public void setProjectionEndDate(Date projectionEndDate) {
        this.projectionEndDate = projectionEndDate == null ? null : (Date) projectionEndDate.clone();
    }

    public Date getHistoryStartDate() {
        return historyStartDate == null ? null : (Date) historyStartDate.clone();
    }

    public void setHistoryStartDate(Date historyStartDate) {
        this.historyStartDate = historyStartDate == null ? null : (Date) historyStartDate.clone();
    }

    public Date getHistoryEndDate() {
        return historyEndDate == null ? null : (Date) historyEndDate.clone();
    }

    public void setHistoryEndDate(Date historyEndDate) {
        this.historyEndDate = historyEndDate == null ? null : (Date) historyEndDate.clone();
    }

    public Integer getProjectionStartYear() {
        return projectionStartYear;
    }

    public void setProjectionStartYear(Integer projectionStartYear) {

        this.projectionStartYear = projectionStartYear;
    }

    public Integer getProjectionStartMonth() {
        return projectionStartMonth;
    }

    public void setProjectionStartMonth(Integer projectionStartMonth) {
        this.projectionStartMonth = projectionStartMonth;
    }

    public Integer getProjectionEndYear() {
        return projectionEndYear;
    }

    public void setProjectionEndYear(Integer projectionEndYear) {
        this.projectionEndYear = projectionEndYear;
    }

    public Integer getProjectionEndMonth() {
        return projectionEndMonth;
    }

    public void setProjectionEndMonth(Integer projectionEndMonth) {
        this.projectionEndMonth = projectionEndMonth;
    }

    public Integer getForecastStartYear() {
        return forecastStartYear;
    }

    public void setForecastStartYear(Integer forecastStartYear) {
        this.forecastStartYear = forecastStartYear;
    }

    public Integer getForecastStartMonth() {
        return forecastStartMonth;
    }

    public void setForecastStartMonth(Integer forecastStartMonth) {
        this.forecastStartMonth = forecastStartMonth;
    }

    public Integer getForecastEndYear() {
        return forecastEndYear;
    }

    public void setForecastEndYear(Integer forecastEndYear) {
        this.forecastEndYear = forecastEndYear;
    }

    public Integer getForecastEndMonth() {
        return forecastEndMonth;
    }

    public void setForecastEndMonth(Integer forecastEndMonth) {
        this.forecastEndMonth = forecastEndMonth;
    }

    public Integer getHistoryStartYear() {
        return historyStartYear;
    }

    public void setHistoryStartYear(Integer historyStartYear) {
        this.historyStartYear = historyStartYear;
    }

    public Integer getHistoryStartMonth() {
        return historyStartMonth;
    }

    public void setHistoryStartMonth(Integer historyStartMonth) {
        this.historyStartMonth = historyStartMonth;
    }

    public Integer getHistoryEndYear() {
        return historyEndYear;
    }

    public void setHistoryEndYear(Integer historyEndYear) {
        this.historyEndYear = historyEndYear;
    }

    public Integer getHistoryEndMonth() {
        return historyEndMonth;
    }

    public void setHistoryEndMonth(Integer historyEndMonth) {
        this.historyEndMonth = historyEndMonth;
    }
}
