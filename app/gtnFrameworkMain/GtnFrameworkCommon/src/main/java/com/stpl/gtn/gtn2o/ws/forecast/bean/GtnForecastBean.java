package com.stpl.gtn.gtn2o.ws.forecast.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnForecastBean implements Serializable {

	public GtnForecastBean() {
		// constructor

	}

	private static final long serialVersionUID = -5362500031533055558L;

	private int viewId;

	private String company;

	private int companyId;

	private int businessUnitId;

	private String productHirerachy;

	private int productRelationship;

	private int productForecastLevel;

	private String productGroup;

	private int productInnerLevel;

	private String viewName;

	private String moduleName;

	private String userId;

	private String forecastSessionId;

	private String level;

	private int fromPeriod;

	private int toPeriod;
	private int parent = 0;

	private String hierarchyNo;

	private String hierarchyType;

	private int historyStartYear;

	private int historyEndYear;
	private int levelNo;

	private int start;

	private int offset;

	private int projectionStartYear;

	private int projectionEndYear;

	private String projectionName;

	private String projectionDescription;
	private int historyStartMonth;

	private int historyEndMonth;

	private String projectionMasterSid;

	private List<String> hierarchyList;

	private int projectionStartMonth;

	private int projectionEndMonth;

	private String history;

	private String projectionPeriodOrder;

	private String forecastType;

	private int expandCollapseLevelNo;

	/**
	 * Start Date which is available in forecast configuration
	 */
	private Date projectionStartDate;

	/**
	 * End Date which is available in forecast configuration
	 */
	private Date projectionEndDate;

	/**
	 * Projection start date, which is editable
	 */
	private Date forecastStartDate;

	/**
	 * Projection end date, which is editable
	 */
	private Date forecastEndDate;

	private Map<String, List<String>> queryParameters;

	private String actualOrProjection;

	private boolean ascending;

	private String selectedHistory;

	private String frequency;

	private List<Object> recordheader;

	private int relationshipBuilderSid;

	private int forecastLevel;

	private int discountType;

	private int brandType;
	private List<Integer> checkedRowSid;

	private List<String> baselinePeriodList;

	private String forecastMethodology;

	private String calculationStartPeriod;

	private String calculationEndPeriod;

	private List<String> checkedHierarchyNumbers;
	private int productHierarchyInnerLevel;

	private int prodRelationshipBuilderSid;

	private int companyMasterSid;

	private int productHierarchySid;

	private int productHierarchyLevel;

	private int productHierarchyVersionNo;

	private int itemGroupSid;

	private String isApproved;

	private List<String> relationshipSidList;

	private int saveFlag;

	private int createdBy;

	private Date createdDate;

	private int modifiedBy;

	private Date modifiedDate;

	private List<Integer> returnsMasterListSize;

	private String selectedHierarchyNo;

	private Map<String, List<String>> rightTableHierarchy;

	private String checkValue;

	private boolean updateFlag;

	private boolean submitFlag;

	private String mode;

	private String viewType;

	private boolean checkedLeftTreeTable;

	private int viewCreatedBy;

	private boolean viewMode;

	private String manualEntryValue;

	private String manualEntryOldValue;

	private int massUpdateLevelNo;

	private int levelFilter;

	private int endLevel;

	private List<String> existingViewName;

	private boolean multipleSaveFalg;

	private boolean massUpdate = false;
	private boolean editMode;

	private boolean viewModeFlag;

	private boolean checkAllFlag;

	private String testFilePath;

	private boolean dataSelectionTabEditFlag;

	private String workflowId;

	private String workflowStatus;

	private String userType;

	private int noOfApprovals;

	private int approvalLevels;

	private boolean workflowFlag;

	private boolean methodologyCalculationFlag;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getForecastSessionId() {
		return forecastSessionId;
	}

	public void setForecastSessionId(String forecastSessionId) {
		this.forecastSessionId = forecastSessionId;
	}

	public Map<String, List<String>> getQueryParameters() {
		return queryParameters;
	}

	public void setQueryParameters(Map<String, List<String>> queryParameters) {
		this.queryParameters = queryParameters;
	}

	public void putQueryParameters(String indicator, List<String> queryParameters) {
		if (this.queryParameters == null) {
			this.queryParameters = new HashMap<>();
		}
		this.queryParameters.put(indicator, queryParameters);
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Date getProjectionStartDate() {
		return projectionStartDate == null ? null : (Date) projectionStartDate.clone();
	}

	public void setProjectionStartDate(Date projectionStartDate) {
		this.projectionStartDate = projectionStartDate == null ? null : (Date) projectionStartDate.clone();
	}

	public Date getProjectionEndDate() {
		return projectionEndDate == null ? null : (Date) projectionEndDate.clone();
	}

	public void setProjectionEndDate(Date projectionEndDate) {
		this.projectionEndDate = projectionEndDate == null ? null : (Date) projectionEndDate.clone();
	}

	public Date getForecastStartDate() {
		return forecastStartDate == null ? null : (Date) forecastStartDate.clone();
	}

	public void setForecastStartDate(Date forecastStartDate) {
		this.forecastStartDate = forecastStartDate == null ? null : (Date) forecastStartDate.clone();
	}

	public Date getForecastEndDate() {
		return forecastEndDate == null ? null : (Date) forecastEndDate.clone();
	}

	public void setForecastEndDate(Date forecastEndDate) {
		this.forecastEndDate = forecastEndDate == null ? null : (Date) forecastEndDate.clone();
	}

	public String getActualOrProjection() {
		return actualOrProjection;
	}

	public void setActualOrProjection(String projection) {
		this.actualOrProjection = projection;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public String getSelectedHistory() {
		return selectedHistory;
	}

	public void setSelectedHistory(String selectedHistory) {
		this.selectedHistory = selectedHistory;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public List<Object> getRecordheader() {
		return recordheader != null ? new ArrayList<>(recordheader) : recordheader;
	}

	public void setRecordheader(List<Object> recordheader) {
		this.recordheader = recordheader != null ? new ArrayList<>(recordheader) : recordheader;
	}

	public int getRelationshipBuilderSid() {
		return relationshipBuilderSid;
	}

	public void setRelationshipBuilderSid(int relationshipBuilderSid) {
		this.relationshipBuilderSid = relationshipBuilderSid;
	}

	public int getForecastLevel() {
		return forecastLevel;
	}

	public void setForecastLevel(int forecastLevel) {
		this.forecastLevel = forecastLevel;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(int businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

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

	public int getFromPeriod() {
		return fromPeriod;
	}

	public void setFromPeriod(int fromPeriod) {
		this.fromPeriod = fromPeriod;
	}

	public int getToPeriod() {
		return toPeriod;
	}

	public void setToPeriod(int toPeriod) {
		this.toPeriod = toPeriod;
	}

	public String getProductHirerachy() {
		return productHirerachy;
	}

	public void setProductHirerachy(String productHirerachy) {
		this.productHirerachy = productHirerachy;
	}

	public int getProductRelationship() {
		return productRelationship;
	}

	public void setProductRelationship(int productRelationship) {
		this.productRelationship = productRelationship;
	}

	public int getProductForecastLevel() {
		return productForecastLevel;
	}

	public void setProductForecastLevel(int productForecastLevel) {
		this.productForecastLevel = productForecastLevel;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public int getProductInnerLevel() {
		return productInnerLevel;
	}

	public void setProductInnerLevel(int productInnerLevel) {
		this.productInnerLevel = productInnerLevel;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public List<Integer> getCheckedRowSid() {
		return checkedRowSid != null ? new ArrayList<>(checkedRowSid) : checkedRowSid;
	}

	public void setCheckedRowSid(List<Integer> checkedRowSid) {
		this.checkedRowSid = checkedRowSid != null ? new ArrayList<>(checkedRowSid) : checkedRowSid;
	}

	public List<String> getBaselinePeriodList() {
		return baselinePeriodList != null ? new ArrayList<>(baselinePeriodList) : baselinePeriodList;
	}

	public void setBaselinePeriodList(List<String> baselinePeriodList) {
		this.baselinePeriodList = baselinePeriodList != null ? new ArrayList<>(baselinePeriodList) : baselinePeriodList;
	}

	public int getHistoryStartYear() {
		return historyStartYear;
	}

	public void setHistoryStartYear(int historyStartYear) {
		this.historyStartYear = historyStartYear;
	}

	public int getHistoryEndYear() {
		return historyEndYear;
	}

	public void setHistoryEndYear(int historyEndYear) {
		this.historyEndYear = historyEndYear;
	}

	public int getProjectionStartYear() {
		return projectionStartYear;
	}

	public void setProjectionStartYear(int projectionStartYear) {
		this.projectionStartYear = projectionStartYear;
	}

	public int getProjectionEndYear() {
		return projectionEndYear;
	}

	public void setProjectionEndYear(int projectionEndYear) {
		this.projectionEndYear = projectionEndYear;
	}

	public int getHistoryStartMonth() {
		return historyStartMonth;
	}

	public void setHistoryStartMonth(int historyStartMonth) {
		this.historyStartMonth = historyStartMonth;
	}

	public int getHistoryEndMonth() {
		return historyEndMonth;
	}

	public void setHistoryEndMonth(int historyEndMonth) {
		this.historyEndMonth = historyEndMonth;
	}

	public int getProjectionStartMonth() {
		return projectionStartMonth;
	}

	public void setProjectionStartMonth(int projectionStartMonth) {
		this.projectionStartMonth = projectionStartMonth;
	}

	public int getProjectionEndMonth() {
		return projectionEndMonth;
	}

	public void setProjectionEndMonth(int projectionEndMonth) {
		this.projectionEndMonth = projectionEndMonth;
	}

	public String getForecastMethodology() {
		return forecastMethodology;
	}

	public void setForecastMethodology(String forecastMethodology) {
		this.forecastMethodology = forecastMethodology;
	}

	public String getCalculationStartPeriod() {
		return calculationStartPeriod;
	}

	public void setCalculationStartPeriod(String calculationStartPeriod) {
		this.calculationStartPeriod = calculationStartPeriod;
	}

	public String getCalculationEndPeriod() {
		return calculationEndPeriod;
	}

	public void setCalculationEndPeriod(String calculationEndPeriod) {
		this.calculationEndPeriod = calculationEndPeriod;
	}

	public List<String> getCheckedHierarchyNumbers() {
		return checkedHierarchyNumbers != null ? new ArrayList<>(checkedHierarchyNumbers) : checkedHierarchyNumbers;
	}

	public void setCheckedHierarchyNumbers(List<String> checkedHierarchyNumbers) {
		this.checkedHierarchyNumbers = checkedHierarchyNumbers != null ? new ArrayList<>(checkedHierarchyNumbers)
				: checkedHierarchyNumbers;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}

	public int getBrandType() {
		return brandType;
	}

	public void setBrandType(int brandType) {
		this.brandType = brandType;
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public int getProductHierarchySid() {
		return productHierarchySid;
	}

	public void setProductHierarchySid(int productHierarchySid) {
		this.productHierarchySid = productHierarchySid;
	}

	public int getProductHierarchyLevel() {
		return productHierarchyLevel;
	}

	public void setProductHierarchyLevel(int productHierarchyLevel) {
		this.productHierarchyLevel = productHierarchyLevel;
	}

	public int getProductHierarchyVersionNo() {
		return productHierarchyVersionNo;
	}

	public void setProductHierarchyVersionNo(int productHierarchyVersionNo) {
		this.productHierarchyVersionNo = productHierarchyVersionNo;
	}

	public int getItemGroupSid() {
		return itemGroupSid;
	}

	public void setItemGroupSid(int itemGroupSid) {
		this.itemGroupSid = itemGroupSid;
	}

	public int getProductHierarchyInnerLevel() {
		return productHierarchyInnerLevel;
	}

	public void setProductHierarchyInnerLevel(int productHierarchyInnerLevel) {
		this.productHierarchyInnerLevel = productHierarchyInnerLevel;
	}

	public int getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(int saveFlag) {
		this.saveFlag = saveFlag;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public int getProdRelationshipBuilderSid() {
		return prodRelationshipBuilderSid;
	}

	public void setProdRelationshipBuilderSid(int prodRelationshipBuilderSid) {
		this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public List<String> getRelationshipSidList() {
		return relationshipSidList != null ? new ArrayList<>(relationshipSidList) : relationshipSidList;
	}

	public void setRelationshipSidList(List<String> relationshipSidList) {
		this.relationshipSidList = relationshipSidList != null ? new ArrayList<>(relationshipSidList)
				: relationshipSidList;
	}

	public List<Integer> getReturnsMasterListSize() {
		return returnsMasterListSize != null ? new ArrayList<>(returnsMasterListSize) : returnsMasterListSize;
	}

	public void setReturnsMasterListSize(List<Integer> returnsMasterListSize) {
		this.returnsMasterListSize = returnsMasterListSize != null ? new ArrayList<>(returnsMasterListSize)
				: returnsMasterListSize;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getProjectionPeriodOrder() {
		return projectionPeriodOrder;
	}

	public void setProjectionPeriodOrder(String projectionPeriodOrder) {
		this.projectionPeriodOrder = projectionPeriodOrder;
	}

	public String getForecastType() {
		return forecastType;
	}

	public void setForecastType(String forecastType) {
		this.forecastType = forecastType;
	}

	public String getProjectionMasterSid() {
		return projectionMasterSid;
	}

	public void setProjectionMasterSid(String projectionMasterSid) {
		this.projectionMasterSid = projectionMasterSid;
	}

	public Map<String, List<String>> getRightTableHierarchy() {
		return rightTableHierarchy;
	}

	public void setRightTableHierarchy(Map<String, List<String>> rightTableHierarchy) {
		this.rightTableHierarchy = rightTableHierarchy;
	}

	public String getSelectedHierarchyNo() {
		return selectedHierarchyNo;
	}

	public void setSelectedHierarchyNo(String selectedHierarchyNo) {
		this.selectedHierarchyNo = selectedHierarchyNo;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isCheckedLeftTreeTable() {
		return checkedLeftTreeTable;
	}

	public void setCheckedLeftTreeTable(boolean checkedLeftTreeTable) {
		this.checkedLeftTreeTable = checkedLeftTreeTable;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public int getViewCreatedBy() {
		return viewCreatedBy;
	}

	public void setViewCreatedBy(int viewCreatedBy) {
		this.viewCreatedBy = viewCreatedBy;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

	public String getManualEntryValue() {
		return manualEntryValue;
	}

	public void setManualEntryValue(String manualEntryValue) {
		this.manualEntryValue = manualEntryValue;
	}

	public int getMassUpdateLevelNo() {
		return massUpdateLevelNo;
	}

	public void setMassUpdateLevelNo(int massUpdateLevel) {
		this.massUpdateLevelNo = massUpdateLevel;
	}

	public List<String> getExistingViewName() {
		return existingViewName != null ? new ArrayList<>(existingViewName) : existingViewName;
	}

	public void setExistingViewName(List<String> existingViewName) {
		this.existingViewName = existingViewName != null ? new ArrayList<>(existingViewName) : existingViewName;
	}

	public String getManualEntryOldValue() {
		return manualEntryOldValue;
	}

	public void setManualEntryOldValue(String manualEntryOldValue) {
		this.manualEntryOldValue = manualEntryOldValue;
	}

	public int getLevelFilter() {
		return levelFilter;
	}

	public void setLevelFilter(int levelFilter) {
		this.levelFilter = levelFilter;
	}

	public int getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(int endLevel) {
		this.endLevel = endLevel;
	}

	public boolean isMultipleSaveFalg() {
		return multipleSaveFalg;
	}

	public void setMultipleSaveFalg(boolean multipleSaveFalg) {
		this.multipleSaveFalg = multipleSaveFalg;
	}

	public boolean isMassUpdate() {
		return massUpdate;
	}

	public void setMassUpdate(boolean massUpdate) {
		this.massUpdate = massUpdate;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isViewModeFlag() {
		return viewModeFlag;
	}

	public void setViewModeFlag(boolean viewModeFlag) {
		this.viewModeFlag = viewModeFlag;
	}

	public List<String> getHierarchyList() {
		return hierarchyList != null ? new ArrayList<>(hierarchyList) : hierarchyList;
	}

	public void setHierarchyList(List<String> hierarchyList) {
		this.hierarchyList = hierarchyList != null ? new ArrayList<>(hierarchyList) : hierarchyList;
	}

	public int getExpandCollapseLevelNo() {
		return expandCollapseLevelNo;
	}

	public void setExpandCollapseLevelNo(int expandCollapseLevelNo) {
		this.expandCollapseLevelNo = expandCollapseLevelNo;
	}

	public boolean isCheckAllFlag() {
		return checkAllFlag;
	}

	public void setCheckAllFlag(boolean checkAllFlag) {
		this.checkAllFlag = checkAllFlag;
	}

	public String getTestFilePath() {
		return testFilePath;
	}

	public void setTestFilePath(String testFilePath) {
		this.testFilePath = testFilePath;
	}

	public boolean isDataSelectionTabEditFlag() {
		return dataSelectionTabEditFlag;
	}

	public void setDataSelectionTabEditFlag(boolean dataSelectionTabEditFlag) {
		this.dataSelectionTabEditFlag = dataSelectionTabEditFlag;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getNoOfApprovals() {
		return noOfApprovals;
	}

	public void setNoOfApprovals(int noOfApprovals) {
		this.noOfApprovals = noOfApprovals;
	}

	public int getApprovalLevels() {
		return approvalLevels;
	}

	public void setApprovalLevels(int approvalLevels) {
		this.approvalLevels = approvalLevels;
	}

	public boolean isWorkflowFlag() {
		return workflowFlag;
	}

	public void setWorkflowFlag(boolean workflowFlag) {
		this.workflowFlag = workflowFlag;
	}

	public boolean isMethodologyCalculationFlag() {
		return methodologyCalculationFlag;
	}

	public void setMethodologyCalculationFlag(boolean methodologyCalculationFlag) {
		this.methodologyCalculationFlag = methodologyCalculationFlag;
	}



}
