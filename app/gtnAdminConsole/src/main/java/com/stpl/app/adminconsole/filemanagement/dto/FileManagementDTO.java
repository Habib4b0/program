/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

 
/**
 * The Class FileManagementDTO.
 *
 * @author elangovan
 */
public class FileManagementDTO implements Serializable {

    private static final long serialVersionUID = 5671099792235762634L;

    private String effectiveDate = StringUtils.EMPTY;

    private String currentFile = StringUtils.EMPTY;

    private String forecastName = StringUtils.EMPTY;

    private String forecastDate = StringUtils.EMPTY;

    private String forecastVersion = StringUtils.EMPTY;

    private String createdDate = StringUtils.EMPTY;
    
    private Integer processSid = 0;
    private String processName = StringUtils.EMPTY;
    private String successCC = StringUtils.EMPTY;
    private String successTo = StringUtils.EMPTY;
    private String failCC = StringUtils.EMPTY;
    private String failTo = StringUtils.EMPTY;
    private String successSubject = StringUtils.EMPTY;
    private String failSubject = StringUtils.EMPTY;
    private String successText = StringUtils.EMPTY;
    private String failText = StringUtils.EMPTY;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(final String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(final String currentFile) {
        this.currentFile = currentFile;
    }

    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(final String forecastName) {
        this.forecastName = forecastName;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(final String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public String getForecastVersion() {
        return forecastVersion;
    }

    public void setForecastVersion(final String forecastVersion) {
        this.forecastVersion = forecastVersion;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDEFAULT() {
        return StringUtils.EMPTY;
    }

    public Integer getProcessSid() {
        return processSid;
    }

    public void setProcessSid(Integer processSid) {
        this.processSid = processSid;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getSuccessCC() {
        return successCC;
    }

    public void setSuccessCC(String successCC) {
        this.successCC = successCC;
    }

    public String getSuccessTo() {
        return successTo;
    }

    public void setSuccessTo(String successTo) {
        this.successTo = successTo;
    }

    public String getFailCC() {
        return failCC;
    }

    public void setFailCC(String failCC) {
        this.failCC = failCC;
    }

    public String getFailTo() {
        return failTo;
    }

    public void setFailTo(String failTo) {
        this.failTo = failTo;
    }

    public String getSuccessSubject() {
        return successSubject;
    }

    public void setSuccessSubject(String successSubject) {
        this.successSubject = successSubject;
    }

    public String getFailSubject() {
        return failSubject;
    }

    public void setFailSubject(String failSubject) {
        this.failSubject = failSubject;
    }

    public String getSuccessText() {
        return successText;
    }

    public void setSuccessText(String successText) {
        this.successText = successText;
    }

    public String getFailText() {
        return failText;
    }

    public void setFailText(String failText) {
        this.failText = failText;
    }
    
    
}
