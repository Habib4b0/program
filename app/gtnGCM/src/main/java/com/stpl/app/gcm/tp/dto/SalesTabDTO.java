/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import org.asi.container.ExtMapDTO;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Lokeshwari
 */
public class SalesTabDTO extends ExtMapDTO implements Comparator<SalesTabDTO> {
    
    private static final Logger LOGGER = Logger.getLogger(SalesTabDTO.class);

    private String levelValue = StringUtils.EMPTY;
    private Integer parent;
    private String parentLevel = StringUtils.EMPTY;
    private Integer contractMasterSid;
    private Integer companyMasterSid;
    private Integer brandMasterSid;
    private Integer itemMasterSid;

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(String parentLevel) {
        this.parentLevel = parentLevel;
    }

    public Integer getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(Integer contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public Integer getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(Integer companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Integer getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(Integer brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public int compare(SalesTabDTO obj1, SalesTabDTO obj2) {
        int value = 0;
        try {
            if (obj1 != null && obj2 != null && obj1.getLevelValue() != null && obj2.getLevelValue() != null) {

                if (obj1.getLevelValue().length() == NumericConstants.FOUR) {
                    Integer year1 = Integer.valueOf(obj1.getLevelValue());
                    Integer year2 = Integer.valueOf(obj2.getLevelValue());
                    value = year1.compareTo(year2);
                } else if (obj1.getLevelValue().length() != NumericConstants.FOUR && Character.isDigit(obj1.getLevelValue().charAt(1)) && Character.isDigit(obj2.getLevelValue().charAt(1))) {
                    String str1 = obj1.getLevelValue().substring(NumericConstants.TWO);
                    String str2 = obj2.getLevelValue().substring(NumericConstants.TWO);
                    value = str1.compareTo(str2);
                } else {
                    Integer year1 = Integer.valueOf(obj1.getLevelValue().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    Integer year2 = Integer.valueOf(obj2.getLevelValue().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    value = year1.compareTo(year2);

                }
            }
        } catch (Exception e) {
             LOGGER.error(e);
        }

        return value;
    }

}
