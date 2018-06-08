/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.arm;

import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import java.io.Serializable;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnWsArmRequest implements Serializable {

    private GtnARMHierarchyInputBean inputBean;

    public GtnWsArmRequest() {
        super();
    }
    
    public GtnARMHierarchyInputBean getInputBean() {
        return inputBean;
    }

    public void setInputBean(GtnARMHierarchyInputBean inputBean) {
        this.inputBean = inputBean;
    }

}
