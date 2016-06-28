/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.galforecasting.accrualrateprojection.dto;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gopinath
 */
public class AccrualDataSelectionDTO {

    private String deductionType = StringUtils.EMPTY;
    private String deductionValue = StringUtils.EMPTY;
    private String projectionId = StringUtils.EMPTY;
    private String screenName = StringUtils.EMPTY;
    private String actionFlag = StringUtils.EMPTY;
    private Integer deductionValueId ;

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public Integer getDeductionValueId() {
        return deductionValueId;
    }

    public void setDeductionValueId(Integer deductionValueId) {
        this.deductionValueId = deductionValueId;
    }

}
