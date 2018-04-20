package com.stpl.gtn.gtn2o.ws.request.report;

import com.stpl.gtn.gtn2o.ws.report.bean.CustomerHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsReportRequest implements GtnWSRequestData {

	private CustomerHierarchyLookupBean customerHierarchyLookupBean;

	public GtnWsReportRequest() {
		super();
	}

	public CustomerHierarchyLookupBean getCustomerHierarchyLookupBean() {
		return customerHierarchyLookupBean;
	}

	public void setCustomerHierarchyLookupBean(CustomerHierarchyLookupBean customerHierarchyLookupBean) {
		this.customerHierarchyLookupBean = customerHierarchyLookupBean;
	}

}
