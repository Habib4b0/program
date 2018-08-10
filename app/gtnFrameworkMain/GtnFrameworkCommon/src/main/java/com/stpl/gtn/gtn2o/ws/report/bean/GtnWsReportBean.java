package com.stpl.gtn.gtn2o.ws.report.bean;

public class GtnWsReportBean {

        public GtnWsReportBean() {
            super();
        }

	private GtnWsReportDataSelectionBean dataSelectionBean;
	private GtnWsReportCustomViewBean customViewBean;
	private GtnReportHierarchyLookupBean hierarchyLookupBean;

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

	public GtnReportHierarchyLookupBean getHierarchyLookupBean() {
		return hierarchyLookupBean;
	}

	public void setHierarchyLookupBean(GtnReportHierarchyLookupBean hierarchyLookupBean) {
		this.hierarchyLookupBean = hierarchyLookupBean;
	}

}
