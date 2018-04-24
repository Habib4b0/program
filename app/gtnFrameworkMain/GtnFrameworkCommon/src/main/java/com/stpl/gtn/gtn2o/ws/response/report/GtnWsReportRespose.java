/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.response.report;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsReportRespose {
    
    
    List<GtnWsRecordBean> resultList;

    public List<GtnWsRecordBean> getResultList() {
        return Collections.unmodifiableList(resultList);
    }

    public void setResultList(List<GtnWsRecordBean> resultList) {
        this.resultList = resultList;
    }
    
}
