package com.stpl.gtn.gtn2o.ws.request.report;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.bean.HierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsReportRequest implements GtnWSRequestData {

	private HierarchyLookupBean customerHierarchyLookupBean;
    private GtnWsReportDashboardBean gtnWsReportDashboardBean;

    public GtnWsReportRequest() {
        super();
    }

	public HierarchyLookupBean getCustomerHierarchyLookupBean() {
		return customerHierarchyLookupBean;
	}

	public void setCustomerHierarchyLookupBean(HierarchyLookupBean customerHierarchyLookupBean) {
		this.customerHierarchyLookupBean = customerHierarchyLookupBean;
    }

    private GtnWsReportDataSelectionBean dataSelectionBean;
    private GtnWsReportCustomViewBean customViewBean;

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

    public GtnWsReportDashboardBean getGtnWsReportDashboardBean() {
        return gtnWsReportDashboardBean;
    }

    public void setGtnWsReportDashboardBean(GtnWsReportDashboardBean gtnWsReportDashboardBean) {
        this.gtnWsReportDashboardBean = gtnWsReportDashboardBean;
    }

}
