/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.request.cff;

import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsCFFSubmitBean;
import java.io.Serializable;

/**
 *
 * @author Manikanda.Prabu
 */
public class GtnWsCFFSubmitRequest implements Serializable {

    public GtnWsCFFSubmitRequest() {
        super();
    }
    private GtnWsGeneralRequest gtnWsGeneralRequest;
    private GtnWsCFFSubmitBean gtnWsCFFSubmitBean;

    public GtnWsCFFSubmitBean getGtnWsCFFSubmitBean() {
        return gtnWsCFFSubmitBean;
    }

    public void setGtnWsCFFSubmitBean(GtnWsCFFSubmitBean gtnWsCFFSubmitBean) {
        this.gtnWsCFFSubmitBean = gtnWsCFFSubmitBean;
    }

    public GtnWsGeneralRequest getGtnWsGeneralRequest() {
        return gtnWsGeneralRequest;
    }

    public void setGtnWsGeneralRequest(GtnWsGeneralRequest gtnWsGeneralRequest) {
        this.gtnWsGeneralRequest = gtnWsGeneralRequest;
    }
}
