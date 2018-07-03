/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.adjustmentdetails;

import java.io.Serializable;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsAdjusmentDetailsRequest implements Serializable {

    private String deductionLevel;

    public String getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(String deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

}
