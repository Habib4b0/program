package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnWsReportDataSelectionBean {

	public GtnWsReportDataSelectionBean() {
		super();
	}

	private Integer customerHierarchySid;
	private Integer productHierarchySid;
	private Integer customerHierarchyForecastLevel;
	private Integer productHierarchyForecastLevel;
	private String name;
	private Integer productRelationshipBuilderSid;
	private Integer customerRelationshipBuilderSid;
	private Integer customerHierarchyVersionNo;
	private Integer productHierarchyVersionNo;
	private Integer customerRelationshipVersionNo;
	private Integer productRelationshipVersionNo;
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
	private Integer frequency;
	private String frequencyName;
	private Integer reportDataSource;

	private String viewName;
	private String viewType;
	private Integer viewId;

	private String fromOrToForDataSelection;

	private Map<String, String> sessionTableMap = null;

	private List variableBreakdownHeaderLoadList;
	private List<GtnReportVariableBreakdownLookupBean> variableBreakdownSaveList;

	private List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownSaveList;
	private List<Object> variablesList;
	private Integer customView;
	private boolean dataRefreshDone = false;

	private String privateViewName;
	private String publicViewName;
	private List<Object> customDataList;

	public String getFromOrToForDataSelection() {
		return fromOrToForDataSelection;
	}

	public void setFromOrToForDataSelection(String fromOrToForDataSelection) {
		this.fromOrToForDataSelection = fromOrToForDataSelection;
	}

	public void setComparisonBreakdownSaveList(
			List<GtnReportComparisonBreakdownLookupBean> comparisonBreakdownSaveList) {
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

	private Integer customViewMasterSid;
	private boolean isCustomViewStructureChanged = false;

	public Integer getProductRelationshipBuilderSid() {
		return productRelationshipBuilderSid;
	}

	public void setProductRelationshipBuilderSid(Integer productRelationshipBuilderSid) {
		this.productRelationshipBuilderSid = productRelationshipBuilderSid;
	}

	public Integer getCustomerRelationshipBuilderSid() {
		return customerRelationshipBuilderSid;
	}

	public void setCustomerRelationshipBuilderSid(Integer customerRelationshipBuilderSid) {
		this.customerRelationshipBuilderSid = customerRelationshipBuilderSid;
	}

	public Integer getCustomerHierarchyVersionNo() {
		return customerHierarchyVersionNo;
	}

	public void setCustomerHierarchyVersionNo(Integer customerHierarchyVersionNo) {
		this.customerHierarchyVersionNo = customerHierarchyVersionNo;
	}

	public Integer getProductHierarchyVersionNo() {
		return productHierarchyVersionNo;
	}

	public void setProductHierarchyVersionNo(Integer productHierarchyVersionNo) {
		this.productHierarchyVersionNo = productHierarchyVersionNo;
	}

	public Integer getCustomerRelationshipVersionNo() {
		return customerRelationshipVersionNo;
	}

	public void setCustomerRelationshipVersionNo(Integer customerRelationshipVersionNo) {
		this.customerRelationshipVersionNo = customerRelationshipVersionNo;
	}

	public Integer getProductRelationshipVersionNo() {
		return productRelationshipVersionNo;
	}

	public void setProductRelationshipVersionNo(Integer productRelationshipVersionNo) {
		this.productRelationshipVersionNo = productRelationshipVersionNo;
	}

	public Date getForecastEligibleDate() {
		return this.forecastEligibleDate;
	}

	public void setForecastEligibleDate(Date forecastEligibleDate) {
		this.forecastEligibleDate = forecastEligibleDate == null ? null : (Date) forecastEligibleDate.clone();
	}

	public Integer getCustomerHierarchySid() {
		return customerHierarchySid;
	}

	public void setCustomerHierarchySid(Integer customerHierarchySid) {
		this.customerHierarchySid = customerHierarchySid;
	}

	public Integer getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(Integer productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public Integer getCustomerHierarchyForecastLevel() {
		return customerHierarchyForecastLevel;
	}

	public void setCustomerHierarchyForecastLevel(Integer customerHierarchyForecastLevel) {
		this.customerHierarchyForecastLevel = customerHierarchyForecastLevel;
	}

	public Integer getProductHierarchyForecastLevel() {
		return productHierarchyForecastLevel;
	}

	public void setProductHierarchyForecastLevel(Integer productHierarchyForecastLevel) {
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

	public Integer getCustomViewMasterSid() {
		return customViewMasterSid;
	}

	public void setCustomViewMasterSid(Integer customViewMasterSid) {
		this.customViewMasterSid = customViewMasterSid;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public Integer getReportDataSource() {
		return reportDataSource;
	}

	public void setReportDataSource(Integer reportDataSource) {
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

	public Integer getCustomView() {
		return customView;
	}

	public void setCustomView(Integer customView) {
		this.customView = customView;
	}

	public List<Object> getVariablesList() {
		return variablesList;
	}

	public void setVariablesList(List<Object> variablesList) {
		this.variablesList = variablesList;
	}

	public boolean isDataRefreshDone() {
		return dataRefreshDone;
	}

	public void setDataRefreshDone(boolean dataRefreshDone) {
		this.dataRefreshDone = dataRefreshDone;
	}

	public String getPrivateViewName() {
		return privateViewName;
	}

	public void setPrivateViewName(String privateViewName) {
		this.privateViewName = privateViewName;
	}

	public String getPublicViewName() {
		return publicViewName;
	}

	public void setPublicViewName(String publicViewName) {
		this.publicViewName = publicViewName;
	}

	public List<Object> getCustomDataList() {
		return customDataList == null ? customDataList : new ArrayList<>(customDataList);
	}

	public void setCustomDataList(List<Object> customDataList) {
		this.customDataList = customDataList != null ? new ArrayList<>(customDataList) : customDataList;
	}

	public boolean isCustomViewStructureChanged() {
		return isCustomViewStructureChanged;
	}

	public void setCustomViewStructureChanged(boolean isCustomViewStructureChanged) {
		this.isCustomViewStructureChanged = isCustomViewStructureChanged;
	}

	public GtnWsReportDataSelectionBean reportDataSelectionBeanCopy() {
		GtnWsReportDataSelectionBean reportDataSelectionCopyBean = new GtnWsReportDataSelectionBean();
		reportDataSelectionCopyBean.setCustomerHierarchySid(this.customerHierarchySid);
		reportDataSelectionCopyBean.setProductHierarchySid(this.productHierarchySid);
		reportDataSelectionCopyBean.setCustomerHierarchyForecastLevel(this.customerHierarchyForecastLevel);
		reportDataSelectionCopyBean.setProductHierarchyForecastLevel(this.productHierarchyForecastLevel);
		reportDataSelectionCopyBean.setName(name);
		reportDataSelectionCopyBean.setProductRelationshipBuilderSid(this.productRelationshipBuilderSid);
		reportDataSelectionCopyBean.setCustomerRelationshipBuilderSid(this.customerRelationshipBuilderSid);
		reportDataSelectionCopyBean.setCustomerRelationshipBuilderSid(customerRelationshipBuilderSid);
		reportDataSelectionCopyBean.setCustomerHierarchyVersionNo(this.customerHierarchyVersionNo);
		reportDataSelectionCopyBean.setProductHierarchyVersionNo(this.productHierarchyVersionNo);
		reportDataSelectionCopyBean.setCustomerRelationshipVersionNo(this.customerRelationshipVersionNo);
		reportDataSelectionCopyBean.setProductRelationshipVersionNo(this.productRelationshipVersionNo);
		reportDataSelectionCopyBean.setForecastEligibleDate(this.forecastEligibleDate);
		reportDataSelectionCopyBean.setUserId(this.userId);
		reportDataSelectionCopyBean.setSessionId(this.sessionId);
		reportDataSelectionCopyBean.setUniqueId(this.uniqueId);
		reportDataSelectionCopyBean.setCompanyReport(this.companyReport);
		reportDataSelectionCopyBean.setBusinessUnitReport(this.businessUnitReport);
		reportDataSelectionCopyBean.setFromPeriodReport(this.fromPeriodReport);
		reportDataSelectionCopyBean.setToPeriod(this.toPeriod);
		reportDataSelectionCopyBean.setCustomerHierarchyRecordBean(this.customerHierarchyRecordBean);
		reportDataSelectionCopyBean.setAvailableCustomerHierarchyList(this.availableCustomerHierarchyList);
		reportDataSelectionCopyBean.setAvailableProductHierarchyList(this.availableProductHierarchyList);
		reportDataSelectionCopyBean.setProductHierarchyRecordBean(this.productHierarchyRecordBean);
		reportDataSelectionCopyBean.setSelectedCustomerHierarchyList(this.selectedCustomerHierarchyList);
		reportDataSelectionCopyBean.setSelectedProductHierarchyList(this.selectedProductHierarchyList);
		reportDataSelectionCopyBean.setComparisonProjectionBeanList(this.comparisonProjectionBeanList);
		reportDataSelectionCopyBean.setFromPeriod(this.fromPeriod);
		reportDataSelectionCopyBean.setToPeriodReport(this.toPeriodReport);
		reportDataSelectionCopyBean.setFrequency(this.frequency);
		reportDataSelectionCopyBean.setFrequencyName(this.frequencyName);
		reportDataSelectionCopyBean.setReportDataSource(this.reportDataSource);
		reportDataSelectionCopyBean.setViewName(this.viewName);
		reportDataSelectionCopyBean.setViewType(this.viewType);
		reportDataSelectionCopyBean.setViewId(this.viewId);
		reportDataSelectionCopyBean.setFromOrToForDataSelection(this.fromOrToForDataSelection);
		reportDataSelectionCopyBean.setSessionTableMap(this.sessionTableMap);
		reportDataSelectionCopyBean.setVariableBreakdownHeaderLoadList(this.variableBreakdownHeaderLoadList);
		reportDataSelectionCopyBean.setVariableBreakdownSaveList(this.variableBreakdownSaveList);
		reportDataSelectionCopyBean.setComparisonBreakdownSaveList(this.comparisonBreakdownSaveList);
		reportDataSelectionCopyBean.setVariablesList(this.variablesList);
		reportDataSelectionCopyBean.setCustomView(this.customView);
		reportDataSelectionCopyBean.setDataRefreshDone(this.dataRefreshDone);
		reportDataSelectionCopyBean.setPrivateViewName(this.privateViewName);
		reportDataSelectionCopyBean.setPublicViewName(this.publicViewName);
		reportDataSelectionCopyBean.setCustomDataList(this.customDataList);
		return reportDataSelectionCopyBean;
	}

}
