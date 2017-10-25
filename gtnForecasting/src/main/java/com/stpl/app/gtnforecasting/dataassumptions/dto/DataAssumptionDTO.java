/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dataassumptions.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionDTO {

    private String activeFileName = StringUtils.EMPTY;
    private String activeFileCompany = StringUtils.EMPTY;
    private String activeFileBusinessUnit = StringUtils.EMPTY;
    private String activeFiletype = StringUtils.EMPTY;
    private String activeFileVersion = StringUtils.EMPTY;
    private Date activeFileFromDate = null;
    private Date activeFileFromPeriod = null;
    private Date activeFileToPeriod = null;
    private String activeFileFromDateString = "";
    private String activeFileFromPeriodString = "";
    private String activeFileToPeriodString = "";

    public String getActiveFileName() {
        return activeFileName;
    }

    public void setActiveFileName(String activeFileName) {
        this.activeFileName = activeFileName;
    }

    public String getActiveFileCompany() {
        return activeFileCompany;
    }

    public void setActiveFileCompany(String activeFileCompany) {
        this.activeFileCompany = activeFileCompany;
    }

    public String getActiveFileBusinessUnit() {
        return activeFileBusinessUnit;
    }

    public void setActiveFileBusinessUnit(String activeFileBusinessUnit) {
        this.activeFileBusinessUnit = activeFileBusinessUnit;
    }

    public String getActiveFiletype() {
        return activeFiletype;
    }

    public void setActiveFiletype(String activeFiletype) {
        this.activeFiletype = activeFiletype;
    }

    public String getActiveFileVersion() {
        return activeFileVersion;
    }

    public void setActiveFileVersion(String activeFileVersion) {
        this.activeFileVersion = activeFileVersion;
    }

    public Date getActiveFileFromDate() {
        return activeFileFromDate;
    }

    public void setActiveFileFromDate(Date activeFileFromDate) {
        this.activeFileFromDate = activeFileFromDate;
    }

    public Date getActiveFileFromPeriod() {
        return activeFileFromPeriod;
    }

    public void setActiveFileFromPeriod(Date activeFileFromPeriod) {
        this.activeFileFromPeriod = activeFileFromPeriod;
    }

    public Date getActiveFileToPeriod() {
        return activeFileToPeriod;
    }

    public void setActiveFileToPeriod(Date activeFileToPeriod) {
        this.activeFileToPeriod = activeFileToPeriod;
    }

    public String getActiveFileFromDateString() {
        return activeFileFromDateString;
    }

    public void setActiveFileFromDateString(String activeFileFromDateString) {
        this.activeFileFromDateString = activeFileFromDateString;
    }

    public String getActiveFileFromPeriodString() {
        return activeFileFromPeriodString;
    }

    public void setActiveFileFromPeriodString(String activeFileFromPeriodString) {
        this.activeFileFromPeriodString = activeFileFromPeriodString;
    }

    public String getActiveFileToPeriodString() {
        return activeFileToPeriodString;
    }

    public void setActiveFileToPeriodString(String activeFileToPeriodString) {
        this.activeFileToPeriodString = activeFileToPeriodString;
    }
}
