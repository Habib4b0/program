package com.stpl.app.adminconsole.hierarchybuilder.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class TableFieldLookUpDTO.
 */
public class TableFieldLookUpDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -204743731460739960L;

	/** The unique id. */
	private int uniqueId;

	/** The table name. */
	private String tableName = StringUtils.EMPTY;

	/** The field name. */
	private String fieldName = StringUtils.EMPTY;
        
        	/** The level values. */
	private String levelValues = StringUtils.EMPTY;

	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public int getUniqueId() {
		return uniqueId;
	}

	/**
	 * Sets the unique id.
	 *
	 * @param uniqueId
	 *            the new unique id
	 */
	public void setUniqueId(final int uniqueId) {
		this.uniqueId = uniqueId;
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

    public String getLevelValues() {
        return levelValues;
    }

    public void setLevelValues(String levelValues) {
        this.levelValues = levelValues;
    }

}
