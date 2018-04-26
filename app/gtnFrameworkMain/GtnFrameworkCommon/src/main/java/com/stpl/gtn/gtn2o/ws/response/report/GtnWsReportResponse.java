package com.stpl.gtn.gtn2o.ws.response.report;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnWsReportResponse implements GtnWSRequestData {

	private GtnReportHierarchyLookupBean customerHierarchyLookupBean;
	private List<Object[]> resultList;
	private GtnWsReportDataSelectionBean dataSelectionBean;
	private GtnWsReportCustomViewBean customViewBean;

	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

	public GtnWsReportResponse() {
		super();
	}

	public GtnReportHierarchyLookupBean getCustomerHierarchyLookupBean() {
		return customerHierarchyLookupBean;
	}

	public void setCustomerHierarchyLookupBean(GtnReportHierarchyLookupBean customerHierarchyLookupBean) {
		this.customerHierarchyLookupBean = customerHierarchyLookupBean;

	}

	public GtnWsReportDataSelectionBean getDataSelectionBean() {
		return dataSelectionBean;
	}

	public void setDataSelectionBean(GtnWsReportDataSelectionBean dataSelectionBean) {
		this.dataSelectionBean = dataSelectionBean;
	}

	public GtnWsReportCustomViewBean getCustomViewBean() {
		return customViewBean;
	}

	public void setCustomViewBean(GtnWsReportCustomViewBean customViewBean) {
		this.customViewBean = customViewBean;
	}

	private GtnWsReportBean reportBean = new GtnWsReportBean();

	public GtnWsReportBean getReportBean() {
		return reportBean;
	}

	public void setReportBean(GtnWsReportBean reportBean) {
		this.reportBean = reportBean;
	}
}
