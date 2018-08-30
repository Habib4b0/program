/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.fileSelection.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

   
/**
 * The Class FileManagementDTO.
 *
 * @author elangovan
 */
public class FileManagementDTO implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 5671099792235762634L;

	/** The default. */

	/** The effective date. */
	private String effectiveDate = StringUtils.EMPTY;

	/** The current file. */
	private String currentFile = StringUtils.EMPTY;

	/** The forecast name. */
	private String forecastName = StringUtils.EMPTY;

	/** The forecast date. */
	private String forecastDate = StringUtils.EMPTY;

	/** The forecast version. */
	private String forecastVersion = StringUtils.EMPTY;

	/** The created date. */
	private String createdDate = StringUtils.EMPTY;

	/**
	 * Gets the effective date.
	 *
	 * @return the effective date
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effective date.
	 *
	 * @param effectiveDate
	 *            the new effective date
	 */
	public void setEffectiveDate(final String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Gets the current file.
	 *
	 * @return the current file
	 */
	public String getCurrentFile() {
		return currentFile;
	}

	/**
	 * Sets the current file.
	 *
	 * @param currentFile
	 *            the new current file
	 */
	public void setCurrentFile(final String currentFile) {
		this.currentFile = currentFile;
	}

	/**
	 * Gets the forecast name.
	 *
	 * @return the forecast name
	 */
	public String getForecastName() {
		return forecastName;
	}

	/**
	 * Sets the forecast name.
	 *
	 * @param forecastName
	 *            the new forecast name
	 */
	public void setForecastName(final String forecastName) {
		this.forecastName = forecastName;
	}

	/**
	 * Gets the forecast date.
	 *
	 * @return the forecast date
	 */
	public String getForecastDate() {
		return forecastDate;
	}

	/**
	 * Sets the forecast date.
	 *
	 * @param forecastDate
	 *            the new forecast date
	 */
	public void setForecastDate(final String forecastDate) {
		this.forecastDate = forecastDate;
	}

	/**
	 * Gets the forecast version.
	 *
	 * @return the forecast version
	 */
	public String getForecastVersion() {
		return forecastVersion;
	}

	/**
	 * Sets the forecast version.
	 *
	 * @param forecastVersion
	 *            the new forecast version
	 */
	public void setForecastVersion(final String forecastVersion) {
		this.forecastVersion = forecastVersion;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(final String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the default.
	 *
	 * @return the default
	 */
	public String getDEFAULT() {
		return StringUtils.EMPTY;
	}
}
