package com.stpl.gtn.gtn2o.ws.request.report;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.report.bean.CustomerHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;

public class GtnWsReportRequest implements GtnWSRequestData {

	private CustomerHierarchyLookupBean customerHierarchyLookupBean;
	private List<Object[]> resultList;
	private String query;
	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

	public GtnWsReportRequest() {
		super();
	}

	public CustomerHierarchyLookupBean getCustomerHierarchyLookupBean() {
		return customerHierarchyLookupBean;
	}

	public void setCustomerHierarchyLookupBean(CustomerHierarchyLookupBean customerHierarchyLookupBean) {
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

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
