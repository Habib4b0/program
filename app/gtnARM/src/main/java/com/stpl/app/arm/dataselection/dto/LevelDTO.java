/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class LevelDTO implements Comparable<LevelDTO> {

    private String level;
    private String levelValueReference;
    private String tableName;
    private String fieldName;
    private Integer levelNo = NumericConstants.ZERO;
    private int relationshipLevelSid;
    private String ndc;
    private String form;
    private String strength;
    private String displayValue;
    private String actualValue;
    private String rsModelSID;
    private String rsName;
    private String rsId;
    private String relationshipLevelValue;
    private String rsCategory;
    private String rsType;
    private String rsProg;
    private String udc2;
    private String udc3;
    private String udc4;
    private String udc5;
    private String udc6;
    private String parentNode;
    private String hierarchyNo;
    private String relationShipBuilderId;
    private String hierarchyLevelDefnId;
    private int hierarchyId;
    private String hierarchyType = StringUtils.EMPTY;
    private int hierarchyVersionNo;
    private int relationShipVersionNo;

    public LevelDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public LevelDTO(LevelDTO levelDto) {
        this.level = levelDto.getLevel();
        this.levelValueReference = levelDto.getLevelValueReference();
        this.tableName = levelDto.getTableName();
        this.fieldName = levelDto.getFieldName();
        this.relationshipLevelSid = levelDto.getRelationshipLevelSid();
        this.ndc = levelDto.getNdc();
        this.form = levelDto.getForm();
        this.strength = levelDto.getStrength();
        this.displayValue = levelDto.getDisplayValue();
        this.actualValue = levelDto.getActualValue();
        this.rsModelSID = levelDto.getRsModelSID();
        this.rsName = levelDto.getRsName();
        this.rsId = levelDto.getRsId();
        this.relationshipLevelValue = levelDto.getRelationshipLevelValue();
        this.rsCategory = levelDto.getRsCategory();
        this.rsType = levelDto.getRsType();
        this.rsProg = levelDto.getRsProg();
        this.udc2 = levelDto.getUdc2();
        this.udc3 = levelDto.getUdc3();
        this.udc4 = levelDto.getUdc4();
        this.udc5 = levelDto.getUdc5();
        this.udc6 = levelDto.getUdc6();
        this.parentNode = levelDto.getParentNode();
        this.hierarchyNo = levelDto.getHierarchyNo();
        this.relationShipBuilderId = levelDto.getRelationShipBuilderId();
        this.hierarchyLevelDefnId = levelDto.getHierarchyLevelDefnId();
        this.hierarchyId = levelDto.getHierarchyId();
        this.hierarchyVersionNo = levelDto.getHierarchyVersionNo();
        this.relationShipVersionNo = levelDto.getRelationShipVersionNo();
    }

    @Override
    public int compareTo(LevelDTO obj) {
        return this.levelNo.compareTo(obj.levelNo);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelValueReference() {
        return levelValueReference;
    }

    public void setLevelValueReference(String levelValueReference) {
        this.levelValueReference = levelValueReference;
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

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
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

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public int getRelationshipLevelSid() {
        return relationshipLevelSid;
    }

    public void setRelationshipLevelSid(int relationshipLevelSid) {
        this.relationshipLevelSid = relationshipLevelSid;
    }

    public String getRelationshipLevelValue() {
        return relationshipLevelValue;
    }

    public void setRelationshipLevelValue(String relationshipLevelValue) {
        this.relationshipLevelValue = relationshipLevelValue;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
    }

    public String getRelationShipBuilderId() {
        return relationShipBuilderId;
    }

    public void setRelationShipBuilderId(String relationShipBuilderId) {
        this.relationShipBuilderId = relationShipBuilderId;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getRsModelSID() {
        return rsModelSID;
    }

    public void setRsModelSID(String rsModelSID) {
        this.rsModelSID = rsModelSID;
    }

    public String getRsCategory() {
        return rsCategory;
    }

    public void setRsCategory(String rsCategory) {
        this.rsCategory = rsCategory;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getRsProg() {
        return rsProg;
    }

    public void setRsProg(String rsProg) {
        this.rsProg = rsProg;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public int getRelationShipVersionNo() {
        return relationShipVersionNo;
    }

    public void setRelationShipVersionNo(int relationShipVersionNo) {
        this.relationShipVersionNo = relationShipVersionNo;
    }

    public String getHierarchyLevelDefnId() {
        return hierarchyLevelDefnId;
    }

    public void setHierarchyLevelDefnId(String hierarchyLevelDefnId) {
        this.hierarchyLevelDefnId = hierarchyLevelDefnId;
    }

    public int getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(int hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getHierarchyType() {
        return hierarchyType;
    }

    public void setHierarchyType(String hierarchyType) {
        this.hierarchyType = hierarchyType;
    }

    public int getHierarchyVersionNo() {
        return hierarchyVersionNo;
    }

    public void setHierarchyVersionNo(int hierarchyVersionNo) {
        this.hierarchyVersionNo = hierarchyVersionNo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object levelObj) {
        return super.equals(levelObj);
    }

}
