/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.security.udc.dto;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Asha
 */
public class BrandMasterDTO implements Serializable {
    
    private int brandMasterSid;
    
    private String brandId = StringUtils.EMPTY;
    
    private String brandName = StringUtils.EMPTY;
    
    private String displayBrand = StringUtils.EMPTY;
    
    private String category = StringUtils.EMPTY;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDisplayBrand() {
        return displayBrand;
    }

    public void setDisplayBrand(String displayBrand) {
        this.displayBrand = displayBrand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }
    
    
    
}
