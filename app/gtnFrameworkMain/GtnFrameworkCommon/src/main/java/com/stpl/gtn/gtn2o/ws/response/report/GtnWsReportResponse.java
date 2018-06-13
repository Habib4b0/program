package com.stpl.gtn.gtn2o.ws.response.report;

import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
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
	private List<?> resultList;
	private GtnWsReportDataSelectionBean dataSelectionBean;

    private GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean;

    public GtnReportVariableBreakdownLookupBean getVariableBreakdownLookupBean() {
        return variableBreakdownLookupBean;
    }

    public void setVariableBreakdownLookupBean(GtnReportVariableBreakdownLookupBean variableBreakdownLookupBean) {
        this.variableBreakdownLookupBean = variableBreakdownLookupBean;
    }
	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
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

}
