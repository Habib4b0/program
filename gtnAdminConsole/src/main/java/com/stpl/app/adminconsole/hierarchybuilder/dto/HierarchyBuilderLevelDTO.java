/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.hierarchybuilder.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class HierarchyBuilderLevelDTO.
 *
 * @author nisanthan
 */
public class HierarchyBuilderLevelDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2596578263508911123L;

	/** The level system id. */
	private int levelSystemId;

	/** The level no. */
	private int levelNo;

	/** The level name. */
	private String levelName = StringUtils.EMPTY;

	/** The level value reference. */
	private String userDefinedOrLinked = StringUtils.EMPTY;

	/** The hierarchy definition system id. */
	private int hierarchyDefinitionSystemId;

	/** The table name. */
	private String tableName = StringUtils.EMPTY;

	/** The field name. */
	private String fieldName = StringUtils.EMPTY;

	/** The level values. */
	private String levelValues = StringUtils.EMPTY;

	/** The recor lock status. */
	private String recorLockStatus = StringUtils.EMPTY;

	/** The created date. */
	private Date createdDate;

	/** The modified date. */
	private Date modifiedDate;

	/** The created by. */
	private String createdBy ;

	/** The modified by. */
	private String modifiedBy ;
	/**  The Version Number. */
	private int versionNo;
	/** The level list. */
	private List<LevelDTO> levelList = new ArrayList<LevelDTO>();
	
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
	 * Gets the level system id.
	 *
	 * @return the level system id
	 */
	public int getLevelSystemId() {
		return levelSystemId;
	}

	/**
	 * Sets the level system id.
	 *
	 * @param levelSystemId
	 *            the new level system id
	 */
	public void setLevelSystemId(final int levelSystemId) {
		this.levelSystemId = levelSystemId;
	}

	/**
	 * Gets the level no.
	 *
	 * @return the level no
	 */
	public int getLevelNo() {
		return levelNo;
	}

	/**
	 * Sets the level no.
	 *
	 * @param levelNo
	 *            the new level no
	 */
	public void setLevelNo(final int levelNo) {
		this.levelNo = levelNo;
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
	 * Gets the level value reference.
	 *
	 * @return the level value reference
	 */
	public String getUserDefinedOrLinked() {
		return userDefinedOrLinked;
	}

	/**
	 * Sets the level value reference.
	 *
	 * @param userDefinedOrLinked
	 *            the new level value reference
	 */
	public void setUserDefinedOrLinked(final String userDefinedOrLinked) {
		this.userDefinedOrLinked = userDefinedOrLinked;
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
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the table name.
	 *
	 * @param tableName
	 *            the new table name
	 */
	public void setTableName(final String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName
	 *            the new field name
	 */
	public void setFieldName(final String fieldName) {
		this.fieldName = fieldName;
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
	 * Gets the level list.
	 *
	 * @return the level list
	 */
	public List<LevelDTO> getLevelList() {
		return levelList;
	}

	/**
	 * Sets the level list.
	 *
	 * @param levelList
	 *            the new level list
	 */
	public void setLevelList(final List<LevelDTO> levelList) {
		this.levelList = levelList;
	}

    /**
     *
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     *
     * @param modifiedBy
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
        
        
        
}
