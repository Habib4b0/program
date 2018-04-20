/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author karthikeyans
 */
public class AdjustmentRateSelection implements Serializable {

    private String adjustmentType = StringUtils.EMPTY;
    private int glcompanyMasterSid;
    private int bucompanyMasterSid;
    private int rateConfigMasterSid;
    private int adjustmentId;

    public AdjustmentRateSelection() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public void reset() {
        this.adjustmentType = StringUtils.EMPTY;
        this.glcompanyMasterSid = 0;
        this.bucompanyMasterSid = 0;
        this.rateConfigMasterSid = 0;
        this.adjustmentId = 0;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getGlcompanyMasterSid() {
        return glcompanyMasterSid;
    }

    public void setGlcompanyMasterSid(int glcompanyMasterSid) {
        this.glcompanyMasterSid = glcompanyMasterSid;
    }

    public int getBucompanyMasterSid() {
        return bucompanyMasterSid;
    }

    public void setBucompanyMasterSid(int bucompanyMasterSid) {
        this.bucompanyMasterSid = bucompanyMasterSid;
    }

    public int getRateConfigMasterSid() {
        return rateConfigMasterSid;
    }

    public void setRateConfigMasterSid(int rateConfigMasterSid) {
        this.rateConfigMasterSid = rateConfigMasterSid;
    }

    public int getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(int adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    @Override
    public int hashCode() {
        int hash = NumericConstants.THREE;
        hash = NumericConstants.FIFTY_THREE * hash + Objects.hashCode(this.adjustmentType);
        hash = NumericConstants.FIFTY_THREE * hash + this.glcompanyMasterSid;
        hash = NumericConstants.FIFTY_THREE * hash + this.bucompanyMasterSid;
        hash = NumericConstants.FIFTY_THREE * hash + this.rateConfigMasterSid;
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
        final AdjustmentRateSelection other = (AdjustmentRateSelection) obj;
        if (!Objects.equals(this.adjustmentType, other.adjustmentType)) {
            return false;
        }
        if (this.glcompanyMasterSid != other.glcompanyMasterSid) {
            return false;
        }
        if (this.bucompanyMasterSid != other.bucompanyMasterSid) {
            return false;
        }
        if (this.rateConfigMasterSid != other.rateConfigMasterSid) {
            return false;
        }
        return true;
    }

    public void setInternal(AdjustmentRateSelection val) {
        this.adjustmentType = val.getAdjustmentType();
        this.bucompanyMasterSid = val.getBucompanyMasterSid();
        this.glcompanyMasterSid = val.getGlcompanyMasterSid();
        this.rateConfigMasterSid = val.getRateConfigMasterSid();
    }

    @Override
    public String toString() {
        return "AdjustmentRateSelection{" + "adjustmentType=" + adjustmentType + ", glcompanyMasterSid=" + glcompanyMasterSid + ", bucompanyMasterSid=" + bucompanyMasterSid + ", rateConfigMasterSid=" + rateConfigMasterSid + '}';
    }

}
