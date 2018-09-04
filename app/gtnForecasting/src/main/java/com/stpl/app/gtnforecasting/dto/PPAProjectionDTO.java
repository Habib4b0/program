/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtMapDTO;

/**
 *
 * @author srithar
 */
public class PPAProjectionDTO extends ExtMapDTO implements Cloneable {

    /**
     * The levelName.
     */
    private String levelName = StringUtils.EMPTY;

    /**
     * The group.
     */
    private String group = StringUtils.EMPTY;

    /**
     * The actualPriceCap.
     */
    private String actualPriceCap = StringUtils.EMPTY;
    /**
     * The priceBasis.
     */
    private String priceBasis = StringUtils.EMPTY;

    private Integer levelNo = 0;
    private String hirarechyNo = StringUtils.EMPTY;
    private String hirarechyName = StringUtils.EMPTY;
    private Integer cCPCount = 0;

    private Integer checkRecordCount = 0;

    public Integer getCheckRecordCount() {
        return checkRecordCount;
    }

    public void setCheckRecordCount(Integer checkRecordCount) {
        this.checkRecordCount = checkRecordCount;
    }

    public Integer getCCPCount() {
        return cCPCount;
    }

    public void setCCPCount(Integer cCPCount) {
        this.cCPCount = cCPCount;
    }

    public String getHirarechyName() {
        return hirarechyName;
    }

    public void setHirarechyName(String hirarechyName) {
        this.hirarechyName = hirarechyName;
    }

    public String getHirarechyNo() {
        return hirarechyNo;
    }

    public void setHirarechyNo(String hirarechyNo) {
        this.hirarechyNo = hirarechyNo;
    }

    public String getPriceBasis() {
        return priceBasis;
    }

    public void setPriceBasis(String priceBasis) {
        this.priceBasis = priceBasis;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    /**
     * Gets the levelName.
     *
     * @return the levelName
     */
    public String getlevelName() {
        return levelName;
    }

    /**
     * Sets the levelName.
     *
     * @param level the levelName
     */
    public void setLevelName(String level) {
        this.levelName = level;
    }

    /**
     * Gets the group.
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the group.
     *
     * @param group the group
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    /**
     * Gets the actualPriceCap.
     *
     * @return the actualPriceCap
     */
    public String getActualPriceCap() {
        return actualPriceCap;
    }

    /**
     * Sets the actualPriceCap.
     *
     * @param baseline the actualPriceCap
     */
    public void setActualPriceCap(final String actualPrice) {
        this.actualPriceCap = actualPrice;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PPAProjectionDTO dto = new PPAProjectionDTO();
        dto.setActualPriceCap(actualPriceCap);
        dto.setGroup(group);
        dto.setHirarechyName(hirarechyName);
        dto.setHirarechyNo(hirarechyNo);
        dto.setLevelName(levelName);
        dto.setLevelNo(levelNo);
        dto.setPriceBasis(priceBasis);
        Map map = new HashMap(getProperties());
        dto.setProperties(map);
        return dto;
    }
}
