/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.dto;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author manikandaprabu.g
 */
public class PPAHelperDTO implements Serializable, Comparable<PPAHelperDTO> {

    private String itemName = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private int itemMasterSysId = 0;

    public PPAHelperDTO(int itemMasterSysId, String itemNo, String itemName) {
        this.itemMasterSysId = itemMasterSysId;
        this.itemName = itemName;
        this.itemNo = itemNo;
    }

    public PPAHelperDTO() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public int getItemMasterSysId() {
        return itemMasterSysId;
    }

    public void setItemMasterSysId(int itemMasterSysId) {
        this.itemMasterSysId = itemMasterSysId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.itemName);
        hash = 79 * hash + Objects.hashCode(this.itemNo);
        hash = 79 * hash + this.itemMasterSysId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PPAHelperDTO other = (PPAHelperDTO) obj;
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemNo, other.itemNo)) {
            return false;
        }
        if (this.itemMasterSysId != other.itemMasterSysId) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(PPAHelperDTO o) {
        return this.itemNo.compareTo(o.getItemNo()) + this.itemName.compareTo(o.getItemName());
    }

}
