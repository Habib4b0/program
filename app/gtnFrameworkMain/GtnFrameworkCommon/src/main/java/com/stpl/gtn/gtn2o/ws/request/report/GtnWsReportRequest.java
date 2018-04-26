package com.stpl.gtn.gtn2o.ws.request.report;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnWsReportRequest {

	private List<Object[]> resultList;
	private String query;
	private GtnWsReportBean reportBean = new GtnWsReportBean();

	private GtnReportHierarchyLookupBean customerHierarchyLookupBean;
	private GtnReportHierarchyLevelBean hierarchyInputBean;
	private GtnWsReportDashboardBean gtnWsReportDashboardBean;
	private GtnWsReportDataSelectionBean dataSelectionBean;
	private GtnWsReportCustomViewBean customViewBean;

	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

	public GtnWsReportBean getReportBean() {
		return reportBean;
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setReportBean(GtnWsReportBean reportBean) {
		this.reportBean = reportBean;
	}

	public GtnWsReportDashboardBean getGtnWsReportDashboardBean() {
		return gtnWsReportDashboardBean;
	}

	public void setGtnWsReportDashboardBean(GtnWsReportDashboardBean gtnWsReportDashboardBean) {
		this.gtnWsReportDashboardBean = gtnWsReportDashboardBean;
	}

	public GtnReportHierarchyLevelBean getHierarchyInputBean() {
		return hierarchyInputBean;
	}

	public void setHierarchyInputBean(GtnReportHierarchyLevelBean hierarchyInputBean) {
		this.hierarchyInputBean = hierarchyInputBean;
	}

}
