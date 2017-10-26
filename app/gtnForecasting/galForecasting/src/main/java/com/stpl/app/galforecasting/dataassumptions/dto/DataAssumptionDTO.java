/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dataassumptions.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rohit.Vignesh
 */
public class DataAssumptionDTO {

    private String activeFileName = StringUtils.EMPTY;
    private String activeFileCompany = StringUtils.EMPTY;
    private String activeFileBussinessUnit = StringUtils.EMPTY;
    private String activeFiletype = StringUtils.EMPTY;
    private String activeFileVersion = StringUtils.EMPTY;
    private String activeFileFromDate = StringUtils.EMPTY;

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

    public String getActiveFileBussinessUnit() {
        return activeFileBussinessUnit;
    }

    public void setActiveFileBussinessUnit(String activeFileBussinessUnit) {
        this.activeFileBussinessUnit = activeFileBussinessUnit;
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

    public String getActiveFileFromDate() {
        return activeFileFromDate;
    }

    public void setActiveFileFromDate(String activeFileFromDate) {
        this.activeFileFromDate = activeFileFromDate;
    }

}
