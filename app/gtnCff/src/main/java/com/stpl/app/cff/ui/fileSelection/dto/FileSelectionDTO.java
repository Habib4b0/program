/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class FileSelectionDTO {

    private String fileName = StringUtils.EMPTY;
    private String fileType = StringUtils.EMPTY;
    private String version = StringUtils.EMPTY;
    private String country = StringUtils.EMPTY;
    private String helperfileType=StringUtils.EMPTY;
    private String fileTypeId=StringUtils.EMPTY;

    private Date activeFromDate;
    private Date activeToDate;
    private String fileManagementSid = StringUtils.EMPTY;
    private boolean fileChanged=false;
    private String fileTypeValue = StringUtils.EMPTY;

    public FileSelectionDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getActiveFromDate() {
        return activeFromDate == null ? null : (Date) activeFromDate.clone();
    }

    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate == null ? null : (Date) activeFromDate.clone();
    }

    public Date getActiveToDate() {
        return activeToDate == null ? null : (Date) activeToDate.clone();
    }

    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate == null ? null : (Date) activeToDate.clone();
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHelperfileType() {
        return helperfileType;
    }

    public void setHelperfileType(String helperfileType) {
        this.helperfileType = helperfileType;
    }

    public String getFileManagementSid() {
        return fileManagementSid;
    }

    public void setFileManagementSid(String fileManagementSid) {
        this.fileManagementSid = fileManagementSid;
    }

    public String getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(String fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public boolean isFileChanged() {
        return fileChanged;
    }

    public void setFileChanged(boolean fileChanged) {
        this.fileChanged = fileChanged;
    }

    public String getFileTypeValue() {
        return fileTypeValue;
    }

    public void setFileTypeValue(String fileTypeValue) {
        this.fileTypeValue = fileTypeValue;
    }
}
