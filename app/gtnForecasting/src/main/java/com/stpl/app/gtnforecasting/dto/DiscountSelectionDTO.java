/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.dto;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DiscountSelectionDTO.
 *
 * @author sriram
 */
public class DiscountSelectionDTO {
    
    /** The check Record */
    private boolean checkRecord;
    
    /** The discount name. */
    private String discountName = StringUtils.EMPTY;
    
     /** The discount name for Projection Results. */
    private String discountNamePr = StringUtils.EMPTY;
    
    /** The discount no. */
    private String discountNo = StringUtils.EMPTY;
    private String rsId = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private   List<List<String>> discountRSlist =new ArrayList<>();

    public DiscountSelectionDTO() {
        super();
    }

    /**
     * Checks if is check record.
     *
     * @return true, if checks if is check record
     */
    public Boolean isCheckRecord() {
        return checkRecord;
    }

    /**
     * Sets the check record.
     *
     * @param checkRecord the check record
     */
    public void setCheckRecord(final Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    /**
     * Checks if is check record.
     *
     * @return true, if checks if is check record
     */
    public Boolean getCheckRecord() {
        return checkRecord;
    }
    
    /**
     * Gets the discount name.
     *
     * @return the discount name
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * Sets the discount name.
     *
     * @param discountName the discount name
     */
    public void setDiscountName(final String discountName) {
        this.discountName = discountName;
    }

    /**
     * Gets the discount no.
     *
     * @return the discount no
     */
    public String getDiscountNo() {
        return discountNo;
    }

    /**
     * Sets the discount no.
     *
     * @param discountNo the discount no
     */

    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
    }

    public String getDiscountNamePr() {
        return discountNamePr;
    }

    public void setDiscountNamePr(String discountNamePr) {
        this.discountNamePr = discountNamePr;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public List<List<String>> getDiscountRSlist() {
        return discountRSlist == null ? discountRSlist : new ArrayList<>(discountRSlist);
    }

    public void setDiscountRSlist(List<List<String>> discountRSlist) {
        this.discountRSlist = discountRSlist == null ? discountRSlist : new ArrayList<>(discountRSlist);
    }

   
        
}
