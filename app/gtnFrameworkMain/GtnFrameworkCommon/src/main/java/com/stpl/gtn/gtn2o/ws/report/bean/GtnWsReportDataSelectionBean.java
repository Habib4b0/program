package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnWsReportDataSelectionBean {

	public GtnWsReportDataSelectionBean() {
		super();
	}

	private long customerHierarchySid;
	private long productHierarchySid;
	private int customerHierarchyForecastLevel;
	private int productHierarchyForecastLevel;
	private String name;
	private int productRelationshipBuilderSid;
	private int customerRelationshipBuilderSid;
	private int customerHierarchyVersionNo;
	private int productHierarchyVersionNo;
	private int customerRelationshipVersionNo;
	private int productRelationshipVersionNo;
	private Date forecastEligibleDate;
	private String userId;
	private String sessionId;
	private String uniqueId;

	private Integer companyReport;
	private Integer businessUnitReport;
	private Integer fromPeriodReport;
	private Integer toPeriod;
	private GtnWsRecordBean customerHierarchyRecordBean;
	private List<GtnWsRecordBean> availableCustomerHierarchyList;
	private List<GtnWsRecordBean> availableProductHierarchyList;
	private GtnWsRecordBean productHierarchyRecordBean;
	private List<GtnWsRecordBean> selectedCustomerHierarchyList;
	private List<GtnWsRecordBean> selectedProductHierarchyList;
	private List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList;
	private Date fromPeriod;
	private Date toPeriodReport;
	private int frequency;
	private String frequencyName;
	private int reportDataSource;

	private String viewName;
	private String viewType;
	private Integer viewId;
	private Map<String, String> sessionTableMap = null;

	private List variableBreakdownHeaderLoadList;
    private List<GtnReportVariableBreakdownLookupBean> variableBreakdownSaveList;
    
    private List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownSaveList;

    

    public void setComparisonBreakdownSaveList(List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownSaveList) {
        this.comparisonBreakdownSaveList = comparisonBreakdownSaveList;
    }

    public List<GtnReportComparisonBreakdownLookupBean> getComparisonBreakdownSaveList() {
        return comparisonBreakdownSaveList;
    }

    
    public List<GtnReportVariableBreakdownLookupBean> getVariableBreakdownSaveList() {
        return variableBreakdownSaveList;
    }
    
    public void setVariableBreakdownSaveList(List<GtnReportVariableBreakdownLookupBean> variableBreakdownSaveList) {
        this.variableBreakdownSaveList = variableBreakdownSaveList;
    }
    
	public List getVariableBreakdownHeaderLoadList() {
		return variableBreakdownHeaderLoadList;
	}

	public void setVariableBreakdownHeaderLoadList(List variableBreakdownHeaderLoadList) {
		this.variableBreakdownHeaderLoadList = variableBreakdownHeaderLoadList;
	}

	private int customViewMasterSid;

	public int getProductRelationshipBuilderSid() {
		return productRelationshipBuilderSid;
	}

	public void setProductRelationshipBuilderSid(int productRelationshipBuilderSid) {
		this.productRelationshipBuilderSid = productRelationshipBuilderSid;
	}

	public int getCustomerRelationshipBuilderSid() {
		return customerRelationshipBuilderSid;
	}

	public void setCustomerRelationshipBuilderSid(int customerRelationshipBuilderSid) {
		this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
	}

	public int getCustomerHierarchyVersionNo() {
		return customerHierarchyVersionNo;
	}

	public void setCustomerHierarchyVersionNo(int customerHierarchyVersionNo) {
		this.customerHierarchyVersionNo = customerHierarchyVersionNo;
	}

	public int getProductHierarchyVersionNo() {
		return productHierarchyVersionNo;
	}

	public void setProductHierarchyVersionNo(int productHierarchyVersionNo) {
		this.productHierarchyVersionNo = productHierarchyVersionNo;
	}

	public int getCustomerRelationshipVersionNo() {
		return customerRelationshipVersionNo;
	}

	public void setCustomerRelationshipVersionNo(int customerRelationshipVersionNo) {
		this.customerRelationshipVersionNo = customerRelationshipVersionNo;
	}

	public int getProductRelationshipVersionNo() {
		return productRelationshipVersionNo;
	}

	public void setProductRelationshipVersionNo(int productRelationshipVersionNo) {
		this.productRelationshipVersionNo = productRelationshipVersionNo;
	}

	public Date getForecastEligibleDate() {
		return this.forecastEligibleDate;
	}

	public void setForecastEligibleDate(Date forecastEligibleDate) {
		this.forecastEligibleDate = forecastEligibleDate;
	}

	public long getCustomerHierarchySid() {
		return customerHierarchySid;
	}

	public void setCustomerHierarchySid(long customerHierarchySid) {
		this.customerHierarchySid = customerHierarchySid;
	}

	public long getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(long productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public int getCustomerHierarchyForecastLevel() {
		return customerHierarchyForecastLevel;
	}

	public void setCustomerHierarchyForecastLevel(int customerHierarchyForecastLevel) {
		this.customerHierarchyForecastLevel = customerHierarchyForecastLevel;
	}

	public int getProductHierarchyForecastLevel() {
		return productHierarchyForecastLevel;
	}

	public void setProductHierarchyForecastLevel(int productHierarchyForecastLevel) {
		this.productHierarchyForecastLevel = productHierarchyForecastLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getTableNameWithUniqueId(String tableName) {
		return tableName + "_" + uniqueId;
	}

	public String getTableNameWithUserIdAndSession(String tableName) {
		return tableName + "_" + userId + "_" + sessionId;
	}

	public Integer getCompanyReport() {
		return companyReport;
	}

	public void setCompanyReport(Integer companyReport) {
		this.companyReport = companyReport;
	}

	public Integer getBusinessUnitReport() {
		return businessUnitReport;
	}

	public void setBusinessUnitReport(Integer businessUnitReport) {
		this.businessUnitReport = businessUnitReport;
	}

	public Integer getFromPeriodReport() {
		return fromPeriodReport;
	}

	public void setFromPeriodReport(Integer fromPeriodReport) {
		this.fromPeriodReport = fromPeriodReport;
	}

	public GtnWsRecordBean getCustomerHierarchyRecordBean() {
		return customerHierarchyRecordBean;
	}

	public void setCustomerHierarchyRecordBean(GtnWsRecordBean customerHierarchyRecordBean) {
		this.customerHierarchyRecordBean = customerHierarchyRecordBean;
	}

	public List<GtnWsRecordBean> getSelectedCustomerHierarchyList() {
		return selectedCustomerHierarchyList;
	}

	public void setSelectedCustomerHierarchyList(List<GtnWsRecordBean> selectedCustomerHierarchyList) {
		this.selectedCustomerHierarchyList = selectedCustomerHierarchyList;
	}

	public List<GtnWsRecordBean> getSelectedProductHierarchyList() {
		return selectedProductHierarchyList;
	}

	public void setSelectedProductHierarchyList(List<GtnWsRecordBean> selectedProductHierarchyList) {
		this.selectedProductHierarchyList = selectedProductHierarchyList;
	}

	public GtnWsRecordBean getProductHierarchyRecordBean() {
		return productHierarchyRecordBean;
	}

	public void setProductHierarchyRecordBean(GtnWsRecordBean productHierarchyRecordBean) {
		this.productHierarchyRecordBean = productHierarchyRecordBean;
	}

	public Integer getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(Integer toPeriod) {
		this.toPeriod = toPeriod;
	}

	public List<GtnWsRecordBean> getAvailableCustomerHierarchyList() {
		return availableCustomerHierarchyList;
	}

	public void setAvailableCustomerHierarchyList(List<GtnWsRecordBean> availableCustomerHierarchyList) {
		this.availableCustomerHierarchyList = availableCustomerHierarchyList;
	}

	public List<GtnWsRecordBean> getAvailableProductHierarchyList() {
		return availableProductHierarchyList;
	}

	public void setAvailableProductHierarchyList(List<GtnWsRecordBean> availableProductHierarchyList) {
		this.availableProductHierarchyList = availableProductHierarchyList;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Date getFromPeriod() {
		return this.fromPeriod;
	}

	public void setFromPeriod(Date fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public Date getToPeriodReport() {
		return this.toPeriodReport;
	}

	public void setToPeriodReport(Date toPeriodReport) {
		this.toPeriodReport = toPeriodReport;
	}

	public Integer getViewId() {
		return viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
	}

	public List<GtnReportComparisonProjectionBean> getComparisonProjectionBeanList() {
		return comparisonProjectionBeanList;
	}

	public void setComparisonProjectionBeanList(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		this.comparisonProjectionBeanList = comparisonProjectionBeanList;
	}

	public int getCustomViewMasterSid() {
		return customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		this.customViewMasterSid = customViewMasterSid;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public int getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(int reportDataSource) {
		this.reportDataSource = reportDataSource;
	}

	public Map<String, String> getSessionTableMap() {
		return sessionTableMap;
	}

	public void setSessionTableMap(Map<String, String> sessionTableMap) {
		this.sessionTableMap = sessionTableMap;
	}

	public void putSessionTableMap(String key, String value) {
		if (this.sessionTableMap == null) {
			this.sessionTableMap = new HashMap<>();
		}
		this.sessionTableMap.put(key, value);
	}

	public String getSessionTable(String key) {
		return this.sessionTableMap.get(key);
	}

}
