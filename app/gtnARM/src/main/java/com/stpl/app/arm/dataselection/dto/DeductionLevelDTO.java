/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sathyaseelan.v
 */
public class DeductionLevelDTO {

    private int levelSid;
    private String levelValue;
    private String levelName;
    private Set rsContractSids;

    public DeductionLevelDTO(int levelSid, String levelValue, String levelName) {
        this.levelSid = levelSid;
        this.levelValue = levelValue;
        this.levelName = levelName;
    }

    public DeductionLevelDTO() {
    }

    public int getLevelSid() {
        return levelSid;
    }

    public void setLevelSid(int levelSid) {
        this.levelSid = levelSid;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Set getRsContractSids() {
        return rsContractSids == null ? null : new HashSet<>(rsContractSids);
    }

    public void setRsContractSids(Set rsContractSids) {
        this.rsContractSids = (rsContractSids == null ? null : new HashSet<>(rsContractSids));
    }

    @Override
    public String toString() {
        return "DeductionLevelDTO{" + "levelValue=" + levelValue + ", levelName=" + levelName + '}';
    }

}
