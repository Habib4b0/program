/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.ui.dataselection.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class RelationshipDdlbDto {
     private String relationshipName = StringUtils.EMPTY;

    private String relationshipBuilderSid = StringUtils.EMPTY;

    public RelationshipDdlbDto(String relationshipBuilderSid, String relationshipName) {
        this.relationshipBuilderSid = relationshipBuilderSid;
        this.relationshipName = relationshipName;
    }

    public RelationshipDdlbDto() {

    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getRelationshipBuilderSid() {
        return relationshipBuilderSid;
    }

    public void setRelationshipBuilderSid(String relationshipBuilderSid) {
        this.relationshipBuilderSid = relationshipBuilderSid;
    }

}
