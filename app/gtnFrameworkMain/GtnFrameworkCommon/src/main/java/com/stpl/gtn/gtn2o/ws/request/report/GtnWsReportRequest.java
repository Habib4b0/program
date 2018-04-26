package com.stpl.gtn.gtn2o.ws.request.report;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnWsReportRequest {

	private GtnWsReportBean reportBean = new GtnWsReportBean();

	private GtnReportHierarchyLookupBean customerHierarchyLookupBean;
	private GtnReportHierarchyLevelBean hierarchyInputBean;
	private GtnWsReportDashboardBean gtnWsReportDashboardBean;
	private GtnWsReportDataSelectionBean dataSelectionBean;
	private GtnWsReportCustomViewBean customViewBean;

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
