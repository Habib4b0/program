/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui.forecastds.dto;

import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.bean.GtnWsRelationshipBuilderBean;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class HierarchyLookupDTO {

    /**
     * The hierarchy name.
     */
    private String hierarchyName = StringUtils.EMPTY;

    /**
     * The highest level.
     */
    private String highestLevel = StringUtils.EMPTY;

    /**
     * The lowest level.
     */
    private String lowestLevel = StringUtils.EMPTY;

    /**
     * The created date.
     */
    private String createdDate = StringUtils.EMPTY;

    /**
     * The modified date.
     */
    private String modifiedDate = StringUtils.EMPTY;

    /**
     * The levels.
     */
    private List<String> levels;

    /**
     * The level values.
     */
    private List<String> levelValues;

    /**
     * The level no map.
     */
    private Map<Integer, String> levelNoMap;

    private int versionNo;

    /**
     * The hierarchy id.
     */
    private int hierarchyId = 0;

    public String getHierarchySelection() {
        return hierarchySelection;
    }

    public void setHierarchySelection(String hierarchySelection) {
        this.hierarchySelection = hierarchySelection;
    }

    private Date modifiedDateSearch;
    private Date createdDateSearch;

    private String hierarchySelection;
    
    public Map<Integer, List<GtnWsRelationshipBuilderBean>> getRelationshipMap() {
        return relationshipMap;
    }

    public void setRelationshipMap(Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap) {
        this.relationshipMap = relationshipMap;
    }

    public Map<Integer, String> getHierarchyMap() {
        return hierarchyMap;
    }

    public void setHierarchyMap(Map<Integer, String> hierarchyMap) {
        this.hierarchyMap = hierarchyMap;
    }

    private Map<Integer, List<GtnWsRelationshipBuilderBean>> relationshipMap;
    private Map<Integer, String> hierarchyMap; 
    /**
     * Gets the hierarchy name.
     *
     * @return the hierarchy name
     */
    public String getHierarchyName() {
        return hierarchyName;
    }

    /**
     * Gets the hierarchy id.
     *
     * @return the hierarchy id
     */
    public int getHierarchyId() {
        return hierarchyId;
    }

    /**
     * Sets the hierarchy id.
     *
     * @param hierarchyId the new hierarchy id
     */
    public void setHierarchyId(int hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    /**
     * Sets the hierarchy name.
     *
     * @param hierarchyName the new hierarchy name
     */
    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    /**
     * Gets the highest level.
     *
     * @return the highest level
     */
    public String getHighestLevel() {
        return highestLevel;
    }

    /**
     * Sets the highest level.
     *
     * @param highestLevel the highest level
     */
    public void setHighestLevel(final String highestLevel) {
        this.highestLevel = highestLevel;
    }

    /**
     * Gets the lowest level.
     *
     * @return the lowest level
     */
    public String getLowestLevel() {
        return lowestLevel;
    }

    /**
     * Sets the lowest level.
     *
     * @param lowestLevel the lowest level
     */
    public void setLowestLevel(final String lowestLevel) {
        this.lowestLevel = lowestLevel;
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
     * @param createdDate the created date
     */
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the modified date
     */
    public void setModifiedDate(final String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the levels.
     *
     * @return the levels
     */
    public List<String> getLevels() {
        return levels;
    }

    /**
     * Sets the levels.
     *
     * @param levels the new levels
     */
    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    /**
     * Gets the level values.
     *
     * @return the level values
     */
    public List<String> getLevelValues() {
        return levelValues;
    }

    /**
     * Sets the level values.
     *
     * @param levelValues the new level values
     */
    public void setLevelValues(List<String> levelValues) {
        this.levelValues = levelValues;
    }

    /**
     * Gets the level no map.
     *
     * @return the level no map
     */
    public Map<Integer, String> getLevelNoMap() {
        return levelNoMap;
    }

    /**
     * Sets the level no map.
     *
     * @param levelNoMap the level no map
     */
    public void setLevelNoMap(Map<Integer, String> levelNoMap) {
        this.levelNoMap = levelNoMap;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public Date getCreatedDateSearch() {
        return createdDateSearch;
    }

    public void setCreatedDateSearch(Date createdDateSearch) {
        this.createdDateSearch = createdDateSearch;
    }

    public Date getModifiedDateSearch() {
        return modifiedDateSearch;
    }

    public void setModifiedDateSearch(Date modifiedDateSearch) {
        this.modifiedDateSearch = modifiedDateSearch;
    }

}

