package com.stpl.gtn.gtn2o.ws.response.report;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportComparisonBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportVariableBreakdownLookupBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWSRequestData;

public class GtnWsReportResponse implements GtnWSRequestData {

	private static final long serialVersionUID = -135232026568151871L;
	private GtnWsReportBean reportBean = new GtnWsReportBean();
	private List<GtnWsRecordBean> recordBeanResultList;

	private GtnReportHierarchyLookupBean customerHierarchyLookupBean;
	private List<Object> resultList;
	private GtnWsReportDataSelectionBean dataSelectionBean;

	private GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean;

	private GtnReportComparisonBreakdownLookupBean comparisonBreakdownLookupBean;

	public GtnReportComparisonBreakdownLookupBean getComparisonBreakdownLookupBean() {
		return comparisonBreakdownLookupBean;
	}

	public void setComparisonBreakdownLookupBean(GtnReportComparisonBreakdownLookupBean comparisonBreakdownLookupBean) {
		this.comparisonBreakdownLookupBean = comparisonBreakdownLookupBean;
	}

	public GtnReportVariableBreakdownLookupBean getVariableBreakdownLookupBean() {
		return variableBreakdownLookupBean;
	}

	public void setVariableBreakdownLookupBean(GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean) {
		this.variableBreakdownLookupBean = variableBreakdownLookupBean;
	}

	public List<Object> getResultList() {
		return resultList == null ? resultList : new ArrayList<>(resultList);
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList == null ? resultList : new ArrayList<>(resultList);
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
		return recordBeanResultList == null ? recordBeanResultList : new ArrayList<>(recordBeanResultList);
	}

	public void setRecordBeanResultList(List<GtnWsRecordBean> recordBeanResultList) {
		this.recordBeanResultList = recordBeanResultList == null ? recordBeanResultList
				: new ArrayList<>(recordBeanResultList);
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

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}

}
