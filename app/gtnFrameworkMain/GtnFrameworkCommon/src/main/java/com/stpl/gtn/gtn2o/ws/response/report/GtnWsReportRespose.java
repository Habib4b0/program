/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.response.report;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsReportRespose {

	private GtnReportHierarchyLevelBean hierarchyInputBean;
	List<GtnWsRecordBean> resultList;

	public List<GtnWsRecordBean> getResultList() {
		return Collections.unmodifiableList(resultList);
	}

	public void setResultList(List<GtnWsRecordBean> resultList) {
		this.resultList = resultList;
	}

	public GtnReportHierarchyLevelBean getHierarchyInputBean() {
		return hierarchyInputBean;
	}

	public void setHierarchyInputBean(GtnReportHierarchyLevelBean hierarchyInputBean) {
		this.hierarchyInputBean = hierarchyInputBean;
	}

}
