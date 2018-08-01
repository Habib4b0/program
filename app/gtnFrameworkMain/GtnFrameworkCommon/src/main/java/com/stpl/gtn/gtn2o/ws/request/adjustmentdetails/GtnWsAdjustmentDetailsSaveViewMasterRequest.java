/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.adjustmentdetails;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsAdjustmentDetailsSaveViewMasterRequest {

    private Integer armViewMasterSid;
    private String viewName;
    private String viewType;
    
    public Integer getArmViewMasterSid() {
        return armViewMasterSid;
    }

    public void setArmViewMasterSid(Integer armViewMasterSid) {
        this.armViewMasterSid = armViewMasterSid;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

}
