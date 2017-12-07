/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.transaction.bean;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class GtnWSTransactionColumnBean implements Comparable<GtnWSTransactionColumnBean> {

	public GtnWSTransactionColumnBean() {
		super();
	}

	private String columnID = "";
	private String columnName = "";
	private Boolean isSearchCriteria = false;
	private Boolean isResultView = false;
	private String componentType;
	private String listName = "";
	private boolean loadDescription = false;
	private int listViewIndex = 0;
	private boolean viewOnlyColumn = false;
	private boolean mandatory = false;
	private String messageName = "";
	private String messageBody = "";
	private int totalCombination = 0;
	private int combination = 0;
	private boolean viewFlag = true;
	private boolean defaultResultView = false;
	private boolean demandTypeFlag = false;
	private List<String> listValues = null;
	private int viewModeIndex = 0;
	private boolean viewModeIndexFlag = false;
	private int viewModeOrder = 0;
	private boolean staticSearchCriterian = false;
	private String expressionType;
	private String columnMappingId;
	private String customClassName;
	private Boolean isInvalidComponent = false;
	private boolean onlyValidComponent = false;
	private boolean invalidLoadDescription = false;
	private String defaultValue = "";
	private String columnAlignment;
	private String columnToAlign;
	private boolean lengthValidator = false;
	private int minLength;
	private int maxLength;
	private boolean outBoundModule = false;
	private String reprocessingWebServiceURL;
	private Object[] stagingInsertColumns = new Object[0];
	private Object[] stagingUpdateColumns = new Object[0];
	private Object[] stagingUpdateColumnsValues = new Object[0];

	public String getColumnID() {
		return columnID;
	}

	public void setColumnID(String columnID) {
		this.columnID = columnID;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Boolean getIsSearchCriteria() {
		return isSearchCriteria;
	}

	public void setIsSearchCriteria(Boolean isSearchCriteria) {
		this.isSearchCriteria = isSearchCriteria;
	}

	public Boolean getIsResultView() {
		return isResultView;
	}

	public void setIsResultView(Boolean isResultView) {
		this.isResultView = isResultView;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public boolean isLoadDescription() {
		return loadDescription;
	}

	public void setLoadDescription(boolean loadDescription) {
		this.loadDescription = loadDescription;
	}

	public int getListViewIndex() {
		return listViewIndex;
	}

	public void setListViewIndex(int listViewIndex) {
		this.listViewIndex = listViewIndex;
	}

	public boolean isViewOnlyColumn() {
		return viewOnlyColumn;
	}

	public void setViewOnlyColumn(boolean viewOnlyColumn) {
		this.viewOnlyColumn = viewOnlyColumn;
	}

	@Override
	public int compareTo(GtnWSTransactionColumnBean bean) {
		int compareIndex = (bean).getListViewIndex();
		// ascending order
		return compareIndex == 0 ? 1 : this.listViewIndex - compareIndex;

	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public int getTotalCombination() {
		return totalCombination;
	}

	public void setTotalCombination(int totalCombination) {
		this.totalCombination = totalCombination;
	}

	public int getCombination() {
		return combination;
	}

	public void setCombination(int combination) {
		this.combination = combination;
	}

	public boolean isViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	public boolean isDefaultResultView() {
		return defaultResultView;
	}

	public void setDefaultResultView(boolean defaultResultView) {
		this.defaultResultView = defaultResultView;
	}

	public boolean isDemandTypeFlag() {
		return demandTypeFlag;
	}

	public void setDemandTypeFlag(boolean demandTypeFlag) {
		this.demandTypeFlag = demandTypeFlag;
	}

	public int getViewModeIndex() {
		return viewModeIndex;
	}

	public void setViewModeIndex(int viewModeIndex) {
		this.viewModeIndex = viewModeIndex;
	}

	public boolean isViewModeIndexFlag() {
		return viewModeIndexFlag;
	}

	public void setViewModeIndexFlag(boolean viewModeIndexFlag) {
		this.viewModeIndexFlag = viewModeIndexFlag;
	}

	public int getViewModeOrder() {
		return viewModeOrder;
	}

	public void setViewModeOrder(int viewModeOrder) {
		this.viewModeOrder = viewModeOrder;
	}

	public String getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(String expressionType) {
		this.expressionType = expressionType;
	}

	public String getColumnMappingId() {
		return columnMappingId;
	}

	public void setColumnMappingId(String columnMappingId) {
		this.columnMappingId = columnMappingId;
	}

	public boolean isStaticSearchCriterian() {
		return staticSearchCriterian;
	}

	public void setStaticSearchCriterian(boolean isStaticSearchCriterian) {
		this.staticSearchCriterian = isStaticSearchCriterian;
	}

	public List<String> getListValues() {
		return listValues != null ? Collections.unmodifiableList(listValues) : listValues;
	}

	public void setListValues(List<String> listValues) {
		this.listValues = listValues != null ? Collections.unmodifiableList(listValues) : listValues;
	}

	public String getCustomClassName() {
		return customClassName;
	}

	public void setCustomClassName(String customClassName) {
		this.customClassName = customClassName;
	}

	public Boolean getIsInvalidComponent() {
		return isInvalidComponent;
	}

	public void setIsInvalidComponent(Boolean isInvalidComponent) {
		this.isInvalidComponent = isInvalidComponent;
	}

	public boolean isOnlyValidComponent() {
		return onlyValidComponent;
	}

	public void setOnlyValidComponent(boolean onlyValidComponent) {
		this.onlyValidComponent = onlyValidComponent;
	}

	public boolean isInvalidLoadDescription() {
		return invalidLoadDescription;
	}

	public void setInvalidLoadDescription(boolean invalidLoadDescription) {
		this.invalidLoadDescription = invalidLoadDescription;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getColumnAlignment() {
		return columnAlignment;
	}

	public void setColumnAlignment(String columnAlignment) {
		this.columnAlignment = columnAlignment;
	}

	public String getColumnToAlign() {
		return columnToAlign;
	}

	public void setColumnToAlign(String columnToAlign) {
		this.columnToAlign = columnToAlign;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isLengthValidator() {
		return lengthValidator;
	}

	public void setLengthValidator(boolean lengthValidator) {
		this.lengthValidator = lengthValidator;
	}

	public boolean isOutBoundModule() {
		return outBoundModule;
	}

	public void setOutBoundModule(boolean outBoundModule) {
		this.outBoundModule = outBoundModule;
	}

	public String getReprocessingWebServiceURL() {
		return reprocessingWebServiceURL;
	}

	public void setReprocessingWebServiceURL(String reprocessingWebServiceURL) {
		this.reprocessingWebServiceURL = reprocessingWebServiceURL;
	}

	public Object[] getStagingInsertColumns() {
		return stagingInsertColumns.length == 0 ? stagingInsertColumns : stagingInsertColumns.clone();
	}

	public void setStagingInsertColumns(Object[] stagingInsertColumns) {
		this.stagingInsertColumns = stagingInsertColumns.clone();
	}

	public Object[] getStagingUpdateColumns() {
		return stagingUpdateColumns.length == 0 ? stagingUpdateColumns : stagingUpdateColumns.clone();
	}

	public void setStagingUpdateColumns(Object[] stagingUpdateColumns) {
		this.stagingUpdateColumns = stagingUpdateColumns.clone();
	}

	public Object[] getStagingUpdateColumnsValues() {
		return stagingUpdateColumnsValues.length == 0 ? stagingUpdateColumnsValues : stagingUpdateColumnsValues.clone();
	}

	public void setStagingUpdateColumnsValues(Object[] stagingUpdateColumnsValues) {
		this.stagingUpdateColumnsValues = stagingUpdateColumnsValues.clone();
	}

}
