package com.stpl.gtn.gtn2o.ws.forecastnewarch;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

public class GtnFrameworkForecastDataSelectionBean {

	public GtnFrameworkForecastDataSelectionBean() {
		super();
	}

	private Integer customerHierarchySid;
	private Integer productHierarchySid;
	private Integer customerHierarchyLevel;
	private Integer productHierarchyLevel;
	private Integer customerHierarchyInnerLevel;
	private Integer productHierarchyInnerLevel;
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
	private String projectionName;
	private String projectionDescription;
	
	

	private Integer company;
	private Integer businessUnit;
	private Integer fromPeriodForecast;
	private Integer toPeriod;
	private GtnWsRecordBean customerHierarchyRecordBean;
	private List<GtnWsRecordBean> availableCustomerHierarchyList;
	private List<GtnWsRecordBean> availableProductHierarchyList;
	private GtnWsRecordBean productHierarchyRecordBean;
	private List<GtnWsRecordBean> selectedCustomerHierarchyList;
	private List<GtnWsRecordBean> customerDualListBox;
	private List<GtnWsRecordBean> selectedProductHierarchyList;

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

	private List<Object> variablesList;
	private Integer customView;
	private boolean dataRefreshDone = false;

	private String privateViewName;
	private String publicViewName;
	private List<Object> customDataList;
	
	public String getProjectionName() {
		return projectionName;
	}

	public void setProjectionName(String projectionName) {
		this.projectionName = projectionName;
	}

	public String getProjectionDescription() {
		return projectionDescription;
	}

	public void setProjectionDescription(String projectionDescription) {
		this.projectionDescription = projectionDescription;
	}

	public String getFromOrToForDataSelection() {
		return fromOrToForDataSelection;
	}

	public void setFromOrToForDataSelection(String fromOrToForDataSelection) {
		this.fromOrToForDataSelection = fromOrToForDataSelection;
	}

	

	private Integer customViewMasterSid;

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

	public Integer getCustomerHierarchyLevel() {
		return customerHierarchyLevel;
	}

	public void setCustomerHierarchyLevel(Integer customerHierarchyLevel) {
		this.customerHierarchyLevel = customerHierarchyLevel;
	}

	public Integer getProductHierarchyLevel() {
		return productHierarchyLevel;
	}

	public void setProductHierarchyLevel(Integer productHierarchyLevel) {
		this.productHierarchyLevel = productHierarchyLevel;
	}
	public Integer getProductHierarchyInnerLevel() {
		return productHierarchyInnerLevel;
	}
	public Integer getCustomerHierarchyInnerLevel() {
		return customerHierarchyInnerLevel;
	}

	public void setCustomerHierarchyInnerLevel(Integer customerHierarchyInnerLevel) {
		this.customerHierarchyInnerLevel = customerHierarchyInnerLevel;
	}
	public void setProductHierarchyInnerLevel(Integer productHierarchyInnerLevel) {
		this.productHierarchyInnerLevel = productHierarchyInnerLevel;
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

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public Integer getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(Integer businessUnit) {
		this.businessUnit = businessUnit;
	}

	public Integer getFromPeriodForecast() {
		return fromPeriodForecast;
	}

	public void setFromPeriodForecast(Integer fromPeriodForecast) {
		this.fromPeriodForecast = fromPeriodForecast;
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

	public List<GtnWsRecordBean> getCustomerDualListBox() {
		return customerDualListBox;
	}

	public void setCustomerDualListBox(List<GtnWsRecordBean> customerDualListBox) {
		this.customerDualListBox = customerDualListBox;
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

	public GtnFrameworkForecastDataSelectionBean DataSelectionBeanCopy() {
		GtnFrameworkForecastDataSelectionBean DataSelectionCopyBean = new GtnFrameworkForecastDataSelectionBean();
		DataSelectionCopyBean.setCustomerHierarchySid(this.customerHierarchySid);
		DataSelectionCopyBean.setProductHierarchySid(this.productHierarchySid);
		DataSelectionCopyBean.setCustomerHierarchyLevel(this.customerHierarchyLevel);
		DataSelectionCopyBean.setProductHierarchyLevel(this.productHierarchyLevel);
		DataSelectionCopyBean.setName(name);
		DataSelectionCopyBean.setProductRelationshipBuilderSid(this.productRelationshipBuilderSid);
		DataSelectionCopyBean.setCustomerRelationshipBuilderSid(this.customerRelationshipBuilderSid);
		DataSelectionCopyBean.setCustomerRelationshipBuilderSid(customerRelationshipBuilderSid);
		DataSelectionCopyBean.setCustomerHierarchyVersionNo(this.customerHierarchyVersionNo);
		DataSelectionCopyBean.setProductHierarchyVersionNo(this.productHierarchyVersionNo);
		DataSelectionCopyBean.setCustomerRelationshipVersionNo(this.customerRelationshipVersionNo);
		DataSelectionCopyBean.setProductRelationshipVersionNo(this.productRelationshipVersionNo);
		DataSelectionCopyBean.setForecastEligibleDate(this.forecastEligibleDate);
		DataSelectionCopyBean.setUserId(this.userId);
		DataSelectionCopyBean.setSessionId(this.sessionId);
		DataSelectionCopyBean.setUniqueId(this.uniqueId);
		DataSelectionCopyBean.setCompany(this.company);
		DataSelectionCopyBean.setBusinessUnit(this.businessUnit);
		DataSelectionCopyBean.setFromPeriodForecast(this.fromPeriodForecast);
		DataSelectionCopyBean.setToPeriod(this.toPeriod);
		DataSelectionCopyBean.setCustomerHierarchyRecordBean(this.customerHierarchyRecordBean);
		DataSelectionCopyBean.setAvailableCustomerHierarchyList(this.availableCustomerHierarchyList);
		DataSelectionCopyBean.setAvailableProductHierarchyList(this.availableProductHierarchyList);
		DataSelectionCopyBean.setProductHierarchyRecordBean(this.productHierarchyRecordBean);
		DataSelectionCopyBean.setSelectedCustomerHierarchyList(this.selectedCustomerHierarchyList);
		DataSelectionCopyBean.setSelectedProductHierarchyList(this.selectedProductHierarchyList);

		DataSelectionCopyBean.setFromPeriod(this.fromPeriod);
		DataSelectionCopyBean.setToPeriodReport(this.toPeriodReport);
		DataSelectionCopyBean.setFrequency(this.frequency);
		DataSelectionCopyBean.setFrequencyName(this.frequencyName);
		DataSelectionCopyBean.setReportDataSource(this.reportDataSource);
		DataSelectionCopyBean.setViewName(this.viewName);
		DataSelectionCopyBean.setViewType(this.viewType);
		DataSelectionCopyBean.setViewId(this.viewId);
		DataSelectionCopyBean.setFromOrToForDataSelection(this.fromOrToForDataSelection);
		DataSelectionCopyBean.setSessionTableMap(this.sessionTableMap);
		DataSelectionCopyBean.setVariableBreakdownHeaderLoadList(this.variableBreakdownHeaderLoadList);
		DataSelectionCopyBean.setVariablesList(this.variablesList);
		DataSelectionCopyBean.setCustomView(this.customView);
		DataSelectionCopyBean.setDataRefreshDone(this.dataRefreshDone);
		DataSelectionCopyBean.setPrivateViewName(this.privateViewName);
		DataSelectionCopyBean.setPublicViewName(this.publicViewName);
		DataSelectionCopyBean.setCustomDataList(this.customDataList);
		return DataSelectionCopyBean;
	}

}
