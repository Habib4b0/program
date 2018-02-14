package com.stpl.app.bpm.dto;

import java.io.Serializable;

public class HierarchyDefinitionRuleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fieldName;
    private Boolean emptyFlag = false;
    private Boolean nullFlag = false;
    private Boolean duplicateFlag = false;
    private Boolean increasingOrder = false;

    public HierarchyDefinitionRuleDTO(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Boolean getEmptyFlag() {
        return emptyFlag;
    }

    public void setEmptyFlag(Boolean emptyFlag) {
        this.emptyFlag = emptyFlag;
    }

    public Boolean getNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(Boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    public Boolean getDuplicateFlag() {
        return duplicateFlag;
    }

    public void setDuplicateFlag(Boolean duplicateFlag) {
        this.duplicateFlag = duplicateFlag;
    }

    public Boolean getIncreasingOrder() {
        return increasingOrder;
    }

    public void setIncreasingOrder(Boolean increasingOrder) {
        this.increasingOrder = increasingOrder;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "fieldName : " + fieldName + "\n duplicateFlag : " + duplicateFlag + " \n nullFlag : " + nullFlag + " \n emptyFlag : " + emptyFlag;
    }

}
