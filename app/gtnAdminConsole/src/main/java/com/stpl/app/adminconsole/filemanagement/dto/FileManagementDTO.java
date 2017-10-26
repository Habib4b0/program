/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.filemanagement.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class FileManagementDTO.
 *
 * @author elangovan
 */
public class FileManagementDTO implements Serializable {

    private static final long serialVersionUID = 5671099792235762634L;

    private final String DEFAULT = StringUtils.EMPTY;

    private String effectiveDate = DEFAULT;

    private String currentFile = DEFAULT;

    private String forecastName = DEFAULT;

    private String forecastDate = DEFAULT;

    private String forecastVersion = DEFAULT;

    private String createdDate = DEFAULT;

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
        return DEFAULT;
    }
}
