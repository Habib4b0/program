/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class LevelDTO {

    private String level = StringUtils.EMPTY;

    private Integer levelNo = new Integer("0");

    private String parentNode = StringUtils.EMPTY;

    private Integer relationshipLevelSid = new Integer("0");

    private String relationshipLevelValue = StringUtils.EMPTY;

    private String tableName = StringUtils.EMPTY;

    private String fieldName = StringUtils.EMPTY;

    private String levelValueReference = StringUtils.EMPTY;

    private boolean fromCompany;

    private boolean fromItem;

    private boolean fromContract;

    private String hierarchyNo = StringUtils.EMPTY;

    private String parentHierarchyNo = StringUtils.EMPTY;

    private Integer treeLevelNo = new Integer("0");

    private Integer hierarchyId = new Integer("0");

    private String hierarchyIndicator = StringUtils.EMPTY;

    private String relationShipBuilderId;

    private String hierarchyLevelDefnId;

    private String ndc;

    private String form;

    private String strength;

    private String relationShipSid;

    private String levelHier;

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public String getLevelValueReference() {
        return levelValueReference;
    }

    public void setLevelValueReference(String levelValueReference) {
        this.levelValueReference = levelValueReference;
    }

    public String getRelationshipLevelValue() {
        return relationshipLevelValue;
    }

    public void setRelationshipLevelValue(String relationshipLevelValue) {
        this.relationshipLevelValue = relationshipLevelValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getRelationshipLevelSid() {
        return relationshipLevelSid;
    }

    public void setRelationshipLevelSid(Integer relationshipLevelSid) {
        this.relationshipLevelSid = relationshipLevelSid;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isFromCompany() {
        return fromCompany;
    }

    public void setFromCompany(boolean fromCompany) {
        this.fromCompany = fromCompany;
    }

    public boolean isFromItem() {
        return fromItem;
    }

    public void setFromItem(boolean fromItem) {
        this.fromItem = fromItem;
    }

    public boolean isFromContract() {
        return fromContract;
    }

    public void setFromContract(boolean fromContract) {
        this.fromContract = fromContract;
    }

    public Integer getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(Integer treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getRelationShipBuilderId() {
        return relationShipBuilderId;
    }

    public void setRelationShipBuilderId(String relationShipBuilderId) {
        this.relationShipBuilderId = relationShipBuilderId;
    }

    public String getHierarchyLevelDefnId() {
        return hierarchyLevelDefnId;
    }

    public void setHierarchyLevelDefnId(String hierarchyLevelDefnId) {
        this.hierarchyLevelDefnId = hierarchyLevelDefnId;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getParentHierarchyNo() {
        return parentHierarchyNo;
    }

    public void setParentHierarchyNo(String parentHierarchyNo) {
        this.parentHierarchyNo = parentHierarchyNo;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getRelationShipSid() {
        return relationShipSid;
    }

    public void setRelationShipSid(String relationShipSid) {
        this.relationShipSid = relationShipSid;
    }

    public String getLevelHier() {
        return levelHier;
    }

    public void setLevelHier(String levelHier) {
        this.levelHier = levelHier;
    }
}
