/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author soundarrajan.l
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
