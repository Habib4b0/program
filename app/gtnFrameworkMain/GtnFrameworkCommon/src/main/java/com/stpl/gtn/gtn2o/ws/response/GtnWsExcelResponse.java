/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.response;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsExcelHeaderBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import java.util.ArrayList;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWsExcelResponse {

	public GtnWsExcelResponse() {
		super();
	}

	private List<GtnWsRecordBean> resultBeanList;

	private GtnWsExcelHeaderBean excelHeaderBean;

	public List<GtnWsRecordBean> getResultBeanList() {
		return resultBeanList != null ? new ArrayList<>(resultBeanList) : resultBeanList;
	}

	public void setResultBeanList(List<GtnWsRecordBean> resultBeanList) {
		this.resultBeanList = resultBeanList != null ? new ArrayList<>(resultBeanList) : resultBeanList;
	}

	public GtnWsExcelHeaderBean getExcelHeaderBean() {
		return excelHeaderBean;
	}

	public void setExcelHeaderBean(GtnWsExcelHeaderBean excelHeaderBean) {
		this.excelHeaderBean = excelHeaderBean;
	}

}
