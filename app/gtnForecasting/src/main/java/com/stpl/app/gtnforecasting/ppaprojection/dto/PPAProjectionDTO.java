/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtListDTO;

/**
 *
 * @author Jayaram
 */
public class PPAProjectionDTO extends ExtListDTO implements Cloneable {

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
    
    private String defaultColumn = StringUtils.EMPTY;

    private Integer levelNo = 0;
    private String hirarechyNo = StringUtils.EMPTY;
    private String hirarechyName = StringUtils.EMPTY;
    private Integer ccpCount = 0;

    private Integer checkRecordCount = 0;
    private Integer priceProtectionStatus =0;
    private String priceProtectionStatus1=StringUtils.EMPTY;

    private Date priceProtectionStartDate;
    private Date priceProtectionEndDate;
    private List<String> helperList=new ArrayList<>();

    public PPAProjectionDTO() {
        super();
    }
    
    public List<String> getHelperList() {
        return helperList == null ? helperList : new ArrayList<>(helperList);
    }

    public void setHelperList(List<String> helperList) {
        this.helperList = helperList == null ? helperList : new ArrayList<>(helperList);
    }
    
    

    public Integer getCheckRecordCount() {
        return checkRecordCount;
    }

    public void setCheckRecordCount(Integer checkRecordCount) {
        this.checkRecordCount = checkRecordCount;
    }

    public Integer getCCPCount() {
        return ccpCount;
    }

    public void setCCPCount(Integer ccpCount) {
        this.ccpCount = ccpCount;
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

    public Date getPriceProtectionStartDate() {
        return priceProtectionStartDate == null ? null : (Date) priceProtectionStartDate.clone();
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        this.priceProtectionStartDate = priceProtectionStartDate == null ? null : (Date) priceProtectionStartDate.clone();
    }

    public Date getPriceProtectionEndDate() {
        return priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        this.priceProtectionEndDate = priceProtectionEndDate == null ? null : (Date) priceProtectionEndDate.clone();
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
        dto.setPriceProtectionStatus(priceProtectionStatus);
        dto.setPriceProtectionStatus1(priceProtectionStatus1);
        List<Object> list = new ArrayList(getProperties());
        dto.setProperties(list);
        return dto;
    }

    public Integer getPriceProtectionStatus() {
        return priceProtectionStatus;
    }

    public void setPriceProtectionStatus(Integer priceProtectionStatus) {
        this.priceProtectionStatus = priceProtectionStatus;
    }

    public String getDefaultColumn() {
        return defaultColumn;
    }

    public void setDefaultColumn(String defaultColumn) {
        this.defaultColumn = defaultColumn;
    }


    public String getPriceProtectionStatus1() {
        return priceProtectionStatus1;
    }

    public void setPriceProtectionStatus1(String priceProtectionStatus1) {
        this.priceProtectionStatus1 = priceProtectionStatus1;
    }
    }
