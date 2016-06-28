/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author santanukumar
 */
public class LiabilitySummaryDto {
    private String year = StringUtils.EMPTY;
    private String actuals= StringUtils.EMPTY;;
    private String accruals= StringUtils.EMPTY;;
    private String remainingEstimate= StringUtils.EMPTY;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActuals() {
        return actuals;
    }

    public void setActuals(String actuals) {
        this.actuals = actuals;
    }

    public String getAccruals() {
        return accruals;
    }

    public void setAccruals(String accruals) {
        this.accruals = accruals;
    }

    public String getRemainingEstimate() {
        return remainingEstimate;
    }

    public void setRemainingEstimate(String remainingEstimate) {
        this.remainingEstimate = remainingEstimate;
    }
}
