/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class DeductionDetailsDTO implements Serializable {

    private String forecastFromDate  = StringUtils.EMPTY ;
    private String forecastToDate  = StringUtils.EMPTY ;
    private String detailsFromDate = StringUtils.EMPTY;
    private String detailsToDate = StringUtils.EMPTY;
    private String frequency = StringUtils.EMPTY;
    private String userId = StringUtils.EMPTY;
    private String sessionId = StringUtils.EMPTY;
    private Integer levelNo =0;
    private String dataView = StringUtils.EMPTY;
    private String listView = StringUtils.EMPTY;
    private String filterDdlb = StringUtils.EMPTY;
    private boolean generated = Boolean.FALSE;

    public String getFilterDdlb() {
        return filterDdlb;
    }

    public void setFilterDdlb(String filterDdlb) {
        this.filterDdlb = filterDdlb;
    }
    
    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getForecastFromDate() {
        return forecastFromDate;
    }

    public void setForecastFromDate(String forecastFromDate) {
        this.forecastFromDate = forecastFromDate;
    }

    public String getForecastToDate() {
        return forecastToDate;
    }

    public void setForecastToDate(String forecastToDate) {
        this.forecastToDate = forecastToDate;
    }

     public String getDetailsFromDate() {
        return detailsFromDate;
    }

    public void setDetailsFromDate(String detailsFromDate) {
        this.detailsFromDate = detailsFromDate;
    }

    public String getDetailsToDate() {
        return detailsToDate;
    }

    public void setDetailsToDate(String detailsToDate) {
        this.detailsToDate = detailsToDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDataView() {
        return dataView;
    }

    public void setDataView(String dataView) {
        this.dataView = dataView;
    }

    public String getListView() {
        return listView;
    }

    public void setListView(String listView) {
        this.listView = listView;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    }
