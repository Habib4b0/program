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
    private int gl_companyMasterSid;
    private int bu_companyMasterSid;
    private int rateConfigMasterSid;
    private int adjustmentId;

    public void reset() {
        this.adjustmentType = StringUtils.EMPTY;
        this.gl_companyMasterSid = 0;
        this.bu_companyMasterSid = 0;
        this.rateConfigMasterSid = 0;
        this.adjustmentId=0;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getGl_companyMasterSid() {
        return gl_companyMasterSid;
    }

    public void setGl_companyMasterSid(int gl_companyMasterSid) {
        this.gl_companyMasterSid = gl_companyMasterSid;
    }

    public int getBu_companyMasterSid() {
        return bu_companyMasterSid;
    }

    public void setBu_companyMasterSid(int bu_companyMasterSid) {
        this.bu_companyMasterSid = bu_companyMasterSid;
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
        hash = NumericConstants.FIFTY_THREE * hash + this.gl_companyMasterSid;
        hash = NumericConstants.FIFTY_THREE * hash + this.bu_companyMasterSid;
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
        if (this.gl_companyMasterSid != other.gl_companyMasterSid) {
            return false;
        }
        if (this.bu_companyMasterSid != other.bu_companyMasterSid) {
            return false;
        }
        if (this.rateConfigMasterSid != other.rateConfigMasterSid) {
            return false;
        }
        return true;
    }

    public void setInternal(AdjustmentRateSelection val) {
        this.adjustmentType = val.getAdjustmentType();
        this.bu_companyMasterSid = val.getBu_companyMasterSid();
        this.gl_companyMasterSid = val.getGl_companyMasterSid();
        this.rateConfigMasterSid = val.getRateConfigMasterSid();
    }

    @Override
    public String toString() {
        return "AdjustmentRateSelection{" + "adjustmentType=" + adjustmentType + ", gl_companyMasterSid=" + gl_companyMasterSid + ", bu_companyMasterSid=" + bu_companyMasterSid + ", rateConfigMasterSid=" + rateConfigMasterSid + '}';
    }
    
}
