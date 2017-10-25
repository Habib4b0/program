/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dto;

import org.asi.container.ExtMapDTO;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vigneshkanna
 */
public class DiscountDTO extends ExtMapDTO implements Comparator<DiscountDTO> {

    private String levelValue = StringUtils.EMPTY;
    private Integer parent = 0;
    private Integer levelNo = 0;

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public int compare(DiscountDTO o1, DiscountDTO o2) {
        throw new UnsupportedOperationException("Not supported yet."); 
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

}
