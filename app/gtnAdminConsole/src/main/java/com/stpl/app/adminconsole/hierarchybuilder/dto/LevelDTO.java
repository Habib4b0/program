package com.stpl.app.adminconsole.hierarchybuilder.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * The Class LevelDTO.
 */
public class LevelDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -603988571435101382L;

	/** The level value system id. */
	private int levelValueSystemId;

	/** The level system id. */
	private int levelSystemId;

	/** The level values. */
	private String levelValues = StringUtils.EMPTY;

	/** The created date. */
	private Date createdDate;

	/** The created by. */
	private int createdBy ;

	/** The modified date. */
	private Date modifiedDate;

	/** The modified by. */
	private int modifiedBy ;

	/**
	 * Gets the level value system id.
	 *
	 * @return the level value system id
	 */
	public int getLevelValueSystemId() {
		return levelValueSystemId;
	}

	/**
	 * Sets the level value system id.
	 *
	 * @param levelValueSystemId
	 *            the new level value system id
	 */
	public void setLevelValueSystemId(final int levelValueSystemId) {
		this.levelValueSystemId = levelValueSystemId;
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
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(final int createdBy) {
		this.createdBy = createdBy;
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

}
