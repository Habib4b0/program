/*
 * 
 */
package com.stpl.app.adminconsole.processscheduler.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Table DTO.
 */
public class OutboundTableDTO extends ExtMapDTO implements Comparator<OutboundTableDTO> {

    /**
     * The group.
     */
    private String group = StringUtils.EMPTY;

    /**
     * The level No.
     */
    private Integer levelNo = 0;

    /**
     * The Level Value.
     */
    private String levelValue = StringUtils.EMPTY;

    private String relationshipTree = StringUtils.EMPTY;
    private String relationshipName = StringUtils.EMPTY;
    private String relationshipDescription = StringUtils.EMPTY;
    private String relationshipType = StringUtils.EMPTY;
    private String hierarchyName = StringUtils.EMPTY;
    private String hierarchyLevelName = StringUtils.EMPTY;
    private String hierarchyVersionNo = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String buildType = StringUtils.EMPTY;

    // For Hierarchy definition
    private String hierarchyDefinitionSystemId = StringUtils.EMPTY;
    private String hierarchyType = StringUtils.EMPTY;
    private String hierarchyCategory = StringUtils.EMPTY;
    private String nooflevels = StringUtils.EMPTY;
    private String version = StringUtils.EMPTY;
    private static final Logger LOGGER = LoggerFactory.getLogger(OutboundTableDTO.class);

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getRelationshipTree() {
        return relationshipTree;
    }

    public void setRelationshipTree(String relationshipTree) {
        this.relationshipTree = relationshipTree;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getRelationshipDescription() {
        return relationshipDescription;
    }

    public void setRelationshipDescription(String relationshipDescription) {
        this.relationshipDescription = relationshipDescription;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getHierarchyLevelName() {
        return hierarchyLevelName;
    }

    public void setHierarchyLevelName(String hierarchyLevelName) {
        this.hierarchyLevelName = hierarchyLevelName;
    }

    public String getHierarchyVersionNo() {
        return hierarchyVersionNo;
    }

    public void setHierarchyVersionNo(String hierarchyVersionNo) {
        this.hierarchyVersionNo = hierarchyVersionNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getHierarchyType() {
        return hierarchyType;
    }

    public void setHierarchyType(String hierarchyType) {
        this.hierarchyType = hierarchyType;
    }

    public String getHierarchyCategory() {
        return hierarchyCategory;
    }

    public void setHierarchyCategory(String hierarchyCategory) {
        this.hierarchyCategory = hierarchyCategory;
    }

    public String getNooflevels() {
        return nooflevels;
    }

    public void setNooflevels(String nooflevels) {
        this.nooflevels = nooflevels;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHierarchyDefinitionSystemId() {
        return hierarchyDefinitionSystemId;
    }

    public void setHierarchyDefinitionSystemId(String hierarchyDefinitionSystemId) {
        this.hierarchyDefinitionSystemId = hierarchyDefinitionSystemId;
    }

    @Override
    public int compare(OutboundTableDTO obj1, OutboundTableDTO obj2) {
        int value = 0;
        try {
            if (obj1 != null && obj2 != null && obj1.getGroup() != null && obj2.getGroup() != null) {

                if (obj1.getGroup().length() == NumericConstants.FOUR) {
                    Integer year1 = Integer.valueOf(obj1.getGroup());
                    Integer year2 = Integer.valueOf(obj2.getGroup());
                    value = year1.compareTo(year2);
                } else if (obj1.getGroup().length() != NumericConstants.FOUR && Character.isDigit(obj1.getGroup().charAt(1)) && Character.isDigit(obj2.getGroup().charAt(1))) {
                    String str1 = obj1.getGroup().substring(NumericConstants.TWO);
                    String str2 = obj2.getGroup().substring(NumericConstants.TWO);
                    value = str1.compareTo(str2);
                } else {
                    Integer year1 = Integer.valueOf(obj1.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    Integer year2 = Integer.valueOf(obj2.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    value = year1.compareTo(year2);

                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        }

        return value;
    }
}
