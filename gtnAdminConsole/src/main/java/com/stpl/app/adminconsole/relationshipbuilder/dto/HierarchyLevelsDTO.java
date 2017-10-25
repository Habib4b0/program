/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.ifs.ui.util.NumericConstants;


/**
 * The Class HierarchyLevelsDTO.
 *
 * @author vishalakshi
 */

public class HierarchyLevelsDTO implements Cloneable, Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2427289671216471537L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(HierarchyLevelsDTO.class);

	/** The list of levels. */
	private List<HierarchyLevelDefinition> listOfLevels;

	/** The list of level values. */
	private List<HierarchyLevelValues> listOfLevelValues;

	/** The level count. */
	private int levelCount;

	/** The level name. */
	private String levelName = StringUtils.EMPTY;

	/** The level no. */
	private String levelNo = StringUtils.EMPTY;

	/** The level value. */
	private String levelValue = StringUtils.EMPTY;

	/** The hierarchy level system id. */
	private int hierarchyLevelSystemId;

	/** The relationship level system id. */
	private int relationshipLevelSystemId;

	/** The parent node. */
	private String parentNode = StringUtils.EMPTY;
  /** The hierarchy No . */
	private String hierarchyNo = StringUtils.EMPTY;
    
    
    /** The hidden Id . */
	private String hiddenId = StringUtils.EMPTY;
        
        private boolean equality = false;
        
        private String primaryKeyColumn = StringUtils.EMPTY;
	/**
	 * Gets the h.
	 *
	 * @param hierarchyLevelsDTO
	 *            the hierarchy levels dto
	 * @return the h
	 */
	public HierarchyLevelsDTO getH(final HierarchyLevelsDTO hierarchyLevelsDTO) {
		HierarchyLevelsDTO hierarchyLevelsDTO2 = null;
		try {
			hierarchyLevelsDTO2 = (HierarchyLevelsDTO) hierarchyLevelsDTO.clone();
		} catch (CloneNotSupportedException ex) {
			LOGGER.error(ex);
		}
		return hierarchyLevelsDTO2;
	}

	/**
	 * Gets the list of levels.
	 *
	 * @return the list of levels
	 */
	public List<HierarchyLevelDefinition> getListOfLevels() {
		return listOfLevels;
	}

	/**
	 * Sets the list of levels.
	 *
	 * @param listOfLevels
	 *            the new list of levels
	 */
	public void setListOfLevels(final List<HierarchyLevelDefinition> listOfLevels) {
		this.listOfLevels = listOfLevels;
	}

	/**
	 * Gets the list of level values.
	 *
	 * @return the list of level values
	 */
	public List<HierarchyLevelValues> getListOfLevelValues() {
		return listOfLevelValues;
	}

	/**
	 * Sets the list of level values.
	 *
	 * @param listOfLevelValues
	 *            the new list of level values
	 */
	public void setListOfLevelValues(final List<HierarchyLevelValues> listOfLevelValues) {
		this.listOfLevelValues = listOfLevelValues;
	}

	/**
	 * Gets the level count.
	 *
	 * @return the level count
	 */
	public int getLevelCount() {
		return levelCount;
	}

	/**
	 * Sets the level count.
	 *
	 * @param levelCount
	 *            the new level count
	 */
	public void setLevelCount(final int levelCount) {
		this.levelCount = levelCount;
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
	 * Gets the level no.
	 *
	 * @return the level no
	 */
	public String getLevelNo() {
		return levelNo;
	}

	/**
	 * Sets the level no.
	 *
	 * @param levelNo
	 *            the new level no
	 */
	public void setLevelNo(final String levelNo) {
		this.levelNo = levelNo;
	}

	/**
	 * Gets the level value.
	 *
	 * @return the level value
	 */
	public String getLevelValue() {
		return levelValue;
	}

	/**
	 * Sets the level value.
	 *
	 * @param levelValue
	 *            the new level value
	 */
	public void setLevelValue(final String levelValue) {
		this.levelValue = levelValue;
	}

	/**
	 * Gets the hierarchy level system id.
	 *
	 * @return the hierarchy level system id
	 */
	public int getHierarchyLevelSystemId() {
		return hierarchyLevelSystemId;
	}

	/**
	 * Sets the hierarchy level system id.
	 *
	 * @param hierarchyLevelSystemId
	 *            the new hierarchy level system id
	 */
	public void setHierarchyLevelSystemId(final int hierarchyLevelSystemId) {
		this.hierarchyLevelSystemId = hierarchyLevelSystemId;
	}

	/**
	 * Gets the relationship level system id.
	 *
	 * @return the relationship level system id
	 */
	public int getRelationshipLevelSystemId() {
		return relationshipLevelSystemId;
	}

	/**
	 * Sets the relationship level system id.
	 *
	 * @param relationshipLevelSystemId
	 *            the new relationship level system id
	 */
	public void setRelationshipLevelSystemId(final int relationshipLevelSystemId) {
		this.relationshipLevelSystemId = relationshipLevelSystemId;
	}

	/**
	 * Gets the parent node.
	 *
	 * @return the parent node
	 */
	public String getParentNode() {
		return parentNode;
	}

	/**
	 * Sets the parent node.
	 *
	 * @param parentNode
	 *            the new parent node
	 */
	public void setParentNode(final String parentNode) {
		this.parentNode = parentNode;
	}
	
    public String getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
    }

public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

	@Override
	public int hashCode() {
		final int prime = NumericConstants.THIRTY_ONE;
		int result = 1;
		result = prime * result + ((levelNo == null) ? 0 : levelNo.hashCode());
		result = prime * result + ((levelValue == null) ? 0 : levelValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HierarchyLevelsDTO other = (HierarchyLevelsDTO) obj;
                boolean valueToRet=isEquality()||other.isEquality();
		if (levelNo == null) {
			if (other.levelNo != null)
				return false;
		} else if (!levelNo.equals(other.levelNo))
			return false;
		if (levelValue == null) {
			if (other.levelValue != null)
				return false;
		} else if (!levelValue.equals(other.levelValue))
			return false;
                //GALUAT-654
                if (hiddenId == null) {
			if (other.hiddenId != null)
				return false;
		} else if (!hiddenId.equals(other.hiddenId))
			return false;
		return valueToRet;
	}

    public boolean isEquality() {
        return equality;
    }
    
    public void setEquality(boolean equality) {
        this.equality = equality;
}

    public String getPrimaryKeyColumn() {
        return primaryKeyColumn;
    }

    public void setPrimaryKeyColumn(String primaryKeyColumn) {
        this.primaryKeyColumn = primaryKeyColumn;
    }

}
