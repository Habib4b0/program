package com.stpl.gtn.gtn2o.ws.response.report;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsReportResponse implements GtnWSRequestData {

	private static final long serialVersionUID = -135232026568151871L;
	private GtnWsReportBean reportBean = new GtnWsReportBean();
	private List<Object[]> resultList;
	private List<GtnWsRecordBean> recordBeanResultList;

	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

	public GtnWsReportResponse() {
		super();
	}

	public GtnWsReportBean getReportBean() {
		return reportBean;
	}

	public void setReportBean(GtnWsReportBean reportBean) {
		this.reportBean = reportBean;
	}

	public List<GtnWsRecordBean> getRecordBeanResultList() {
		return recordBeanResultList;
	}

	public void setRecordBeanResultList(List<GtnWsRecordBean> recordBeanResultList) {
		this.recordBeanResultList = recordBeanResultList;
	}

}
