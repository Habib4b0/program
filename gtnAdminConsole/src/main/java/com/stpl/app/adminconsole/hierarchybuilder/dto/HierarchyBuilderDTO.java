/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.stpl.ifs.util.HelperDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderDTO.
 *
 * @author nisanthan
 */
public class HierarchyBuilderDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4579875072040122602L;

	/** The hierarchy definition system id. */
	private int hierarchyDefinitionSystemId;
        
        private int systemId;

	/** The hierarchy name. */
	private String hierarchyName = StringUtils.EMPTY;

	/** The hierarchy type. */
	private String hierarchyType;

	/** The level values. */
	private String levelValues = StringUtils.EMPTY;

	/** The level name. */
	private String levelName = StringUtils.EMPTY;

	/** The no of levels. */
	private String noOfLevels = StringUtils.EMPTY;

	/** The created date. */
	private Date createdDate;

	/** The created date from. */
	private Date createdDateFrom;

	/** The created date to. */
	private Date createdDateTo;

	/** The modified date. */
	private Date modifiedDate;

	/** The created by. */
	private String createdBy ;

	/** The modified by. */
	private int modifiedBy ;

	/** The recor lock status. */
	private String recorLockStatus = StringUtils.EMPTY;

	/** The hierarchy category. */
	private HelperDTO hierarchyCategory;
	
	/**  The Version Number. */
	private int versionNo;
        /** The hierarchy category. **/
        private String hierarchyCategoryInString= StringUtils.EMPTY;

    public String getHierarchyCategoryInString() {
        return hierarchyCategoryInString;
    }

    public void setHierarchyCategoryInString(final String hierarchyCategoryInString) {
        this.hierarchyCategoryInString = hierarchyCategoryInString;
    }
	
	/**
	 * Gets the version no.
	 *
	 * @return the version no
	 */
	public int getVersionNo() {
		return versionNo;
	}

	/**
	 * Sets the version no.
	 *
	 * @param versionNo the new version no
	 */
	public void setVersionNo(final int versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * Gets the hierarchy definition system id.
	 *
	 * @return the hierarchy definition system id
	 */
	public int getHierarchyDefinitionSystemId() {
		return hierarchyDefinitionSystemId;
	}

	/**
	 * Sets the hierarchy definition system id.
	 *
	 * @param hierarchyDefinitionSystemId
	 *            the new hierarchy definition system id
	 */
	public void setHierarchyDefinitionSystemId(final int hierarchyDefinitionSystemId) {
		this.hierarchyDefinitionSystemId = hierarchyDefinitionSystemId;
	}

	/**
	 * Gets the hierarchy name.
	 *
	 * @return the hierarchy name
	 */
	public String getHierarchyName() {
		return hierarchyName;
	}

	/**
	 * Sets the hierarchy name.
	 *
	 * @param hierarchyName
	 *            the new hierarchy name
	 */
	public void setHierarchyName(final String hierarchyName) {
		this.hierarchyName = hierarchyName;
	}

	/**
	 * Gets the hierarchy type.
	 *
	 * @return the hierarchy type
	 */
	public String getHierarchyType() {
		return hierarchyType;
	}

	/**
	 * Sets the hierarchy type.
	 *
	 * @param hierarchyType
	 *            the new hierarchy type
	 */
	public void setHierarchyType(final String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	/**
	 * Gets the level values.
	 *
	 * @return the level values
	 */
	public String getLevelValues() {
		return levelValues;
	}

	/**
	 * Sets the level values.
	 *
	 * @param levelValues
	 *            the new level values
	 */
	public void setLevelValues(final String levelValues) {
		this.levelValues = levelValues;
	}

	/**
	 * Gets the level name.
	 *
	 * @return the level name
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Sets the level name.
	 *
	 * @param levelName
	 *            the new level name
	 */
	public void setLevelName(final String levelName) {
		this.levelName = levelName;
	}

	/**
	 * Gets the no of levels.
	 *
	 * @return the no of levels
	 */
	public String getNoOfLevels() {
		return noOfLevels;
	}

	/**
	 * Sets the no of levels.
	 *
	 * @param noOfLevels
	 *            the new no of levels
	 */
	public void setNoOfLevels(final String noOfLevels) {
		this.noOfLevels = noOfLevels;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the created date from.
	 *
	 * @return the created date from
	 */
	public Date getCreatedDateFrom() {
		return createdDateFrom;
	}

	/**
	 * Sets the created date from.
	 *
	 * @param createdDateFrom
	 *            the new created date from
	 */
	public void setCreatedDateFrom(final Date createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}

	/**
	 * Gets the created date to.
	 *
	 * @return the created date to
	 */
	public Date getCreatedDateTo() {
		return createdDateTo;
	}

	/**
	 * Sets the created date to.
	 *
	 * @param createdDateTo
	 *            the new created date to
	 */
	public void setCreatedDateTo(final Date createdDateTo) {
		this.createdDateTo = createdDateTo;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modifiedDate
	 *            the new modified date
	 */
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public int getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy
	 *            the new modified by
	 */
	public void setModifiedBy(final int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the recor lock status.
	 *
	 * @return the recor lock status
	 */
	public String getRecorLockStatus() {
		return recorLockStatus;
	}

	/**
	 * Sets the recor lock status.
	 *
	 * @param recorLockStatus
	 *            the new recor lock status
	 */
	public void setRecorLockStatus(final String recorLockStatus) {
		this.recorLockStatus = recorLockStatus;
	}

	/**
	 * Gets the hierarchy category.
	 *
	 * @return the hierarchy category
	 */
	public HelperDTO getHierarchyCategory() {
		return hierarchyCategory;
	}

	/**
	 * Sets the hierarchy category.
	 *
	 * @param hierarchyCategory
	 *            the new hierarchy category
	 */
	public void setHierarchyCategory(final HelperDTO hierarchyCategory) {
		this.hierarchyCategory = hierarchyCategory;
	}

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

}
