/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.dto;

import com.stpl.ifs.ui.util.NumericConstants;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;
import org.jboss.logging.Logger;

/**
 *
 * @author gopinath
 */
/**
 * The Class Table DTO.
 */
public class TableDTO extends ExtMapDTO implements Comparator<TableDTO> {

    /**
     * The group.
     */
    private String group = StringUtils.EMPTY;

    private Integer sid = 0;

    private Integer levelNo = 0;
    /**
     * The parent.
     */
    private Integer parent = 0;

    private Boolean check;

    /**
     * Company sid
     */
    private Integer companySid = 0;
    /**
     * 
     */
    private Integer brandSid = 0;
    /**
     * 
     */
    private Integer itemSid = 0;

    private Integer uncheckCount = 0;

    private Integer ccpCount = 0;
    
    private static final Logger LOGGER = Logger.getLogger(TableDTO.class);

    public TableDTO() {
        super();
    }


    @Override
    public int compare(TableDTO obj1, TableDTO obj2) {
        int value = 0;
        try {
            if (obj1 != null && obj2 != null && obj1.getGroup() != null && obj2.getGroup() != null) {

                if (obj1.getGroup().length() == NumericConstants.FOUR) {
                    Integer year1 = Integer.valueOf(obj1.getGroup());
                    Integer year2 = Integer.valueOf(obj2.getGroup());
                    value = year1.compareTo(year2);
                } else if (obj1.getGroup().length() != NumericConstants.FOUR && Character.isDigit(obj1.getGroup().charAt(1)) && Character.isDigit(obj2.getGroup().charAt(1))) {
                    String str1 = obj1.getGroup().substring(NumericConstants.TWO);
                    String str2 = obj2.getGroup().substring(NumericConstants.TWO);
                    value = str1.compareTo(str2);
                } else {
                    Integer year1 = Integer.valueOf(obj1.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    Integer year2 = Integer.valueOf(obj2.getGroup().substring(NumericConstants.FOUR, NumericConstants.EIGHT));
                    value = year1.compareTo(year2);

                }
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }

        return value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public Integer getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(Integer brandSid) {
        this.brandSid = brandSid;
    }

    public Integer getItemSid() {
        return itemSid;
    }

    public void setItemSid(Integer itemSid) {
        this.itemSid = itemSid;
    }

    public Integer getUncheckCount() {
        return uncheckCount;
    }

    public void setUncheckCount(Integer uncheckCount) {
        this.uncheckCount = uncheckCount;
    }

    public Integer getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
    }

}
