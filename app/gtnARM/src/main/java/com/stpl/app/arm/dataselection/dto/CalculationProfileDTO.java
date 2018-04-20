/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class CalculationProfileDTO implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 613970373488326921L;

    private String systemId = StringUtils.EMPTY;
    private String adjustmentType = StringUtils.EMPTY;
    private int accountType = 0;
    private boolean include;
    private int indicator = 0;

    private String calculationProfileId = "0";
    private String profileName = StringUtils.EMPTY;
    private String profileDesc = StringUtils.EMPTY;
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;

    private String query = StringUtils.EMPTY;
    private String updateViewQuery = StringUtils.EMPTY;
    private boolean addUpdateFlag;

    public CalculationProfileDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getIndicator() {
        return indicator;
    }

    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCalculationProfileId() {
        return calculationProfileId;
    }

    public void setCalculationProfileId(String calculationProfileId) {
        this.calculationProfileId = calculationProfileId;
    }

    public String getUpdateViewQuery() {
        return updateViewQuery;
    }

    public void setUpdateViewQuery(String updateViewQuery) {
        this.updateViewQuery = updateViewQuery;
    }

    public boolean isAddUpdateFlag() {
        return addUpdateFlag;
    }

    public void setAddUpdateFlag(boolean addUpdateFlag) {
        this.addUpdateFlag = addUpdateFlag;
    }

}
