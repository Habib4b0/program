package com.stpl.gtn.gtn2o.ws.forecast.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportHierarchyLevelBean;

/**
 * @author Srithar.Raju
 *
 */
public class GtnForecastHierarchyInputBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int relationShipBuilderSid;
	private int hierarchyDefinitionSid;
	private int hierarchyLevelDefinitionSid;
	private int relationVersionNo;
	private int hierarchyVersionNo;
	private String deductionLevel;
	private String deductionValue;
	private String fieldName;
	private String tableName;
	private String hierarchyType;
	private String hieraryQuery;
	private Date forecastEligibleDate;
	private List<String> groupFilterCompenies;
	private int levelNo;
	private int lowestLevelNo;
	private int projectionId;
	private boolean isNdc;
	private int selectedCustomerRelationShipBuilderSid;
	private int selectedCustomerRelationShipBuilderVersionNo;
	private int selectedCustomerHierarcySid;
	private int selectedCustomerHierarchyVersionNo;
	private int selectedProductRelationShipBuilderSid;
	private int selectedProductRelationShipBuilderVersionNo;
	private int selectedProductHierarcySid;
	private int selectedProductHierarchyVersionNo;
	private String businessUnitValue;
	private String hierarchyIndicator;
	private Map<String, String> tempTableMap;
	private List<GtnReportHierarchyLevelBean> levelList;

	private List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerList;
	private List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductList;

	private GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto;

	private boolean isCff;
	private boolean forecastInsert;
        private List<Object[]> resultList;
        private List<Object> inputList;

    public List<Object> getInputList() {
        return inputList != null ? new ArrayList<>(inputList) : inputList;
    }

    public void setInputList(List<Object> inputList) {
        this.inputList = inputList != null ? new ArrayList<>(inputList)
				: inputList;
    }
        
    public List<Object[]> getResultList() {
        return resultList != null ? new ArrayList<>(resultList) : resultList;
    }

    public void setResultList(List<Object[]> resultList) {
        this.resultList = resultList != null ? new ArrayList<>(resultList)
				: resultList;
    }

	public GtnForecastHierarchyInputBean() {
		super();
	}

	public String getHieraryQuery() {
		return hieraryQuery;
	}

	public void setHieraryQuery(String hieraryQuery) {
		this.hieraryQuery = hieraryQuery;
	}

	public int getRelationShipBuilderSid() {
		return relationShipBuilderSid;
	}

	public void setRelationShipBuilderSid(int relationShipBuilderSid) {
		this.relationShipBuilderSid = relationShipBuilderSid;
	}

	public int getHierarchyDefinitionSid() {
		return hierarchyDefinitionSid;
	}

	public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
		this.hierarchyDefinitionSid = hierarchyDefinitionSid;
	}

	public int getHierarchyLevelDefinitionSid() {
		return hierarchyLevelDefinitionSid;
	}

	public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
		this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
	}

	public int getRelationVersionNo() {
		return relationVersionNo;
	}

	public void setRelationVersionNo(int relationVersionNo) {
		this.relationVersionNo = relationVersionNo;
	}

	public int getHierarchyVersionNo() {
		return hierarchyVersionNo;
	}

	public void setHierarchyVersionNo(int hierarchyVersionNo) {
		this.hierarchyVersionNo = hierarchyVersionNo;
	}

	public String getDeductionLevel() {
		if (deductionLevel == null)
			return "";
		return deductionLevel;
	}

	public void setDeductionLevel(String deductionLevel) {
		this.deductionLevel = deductionLevel;
	}

	public String getDeductionValue() {
		if (deductionValue == null)
			return "";
		return deductionValue;
	}

	public void setDeductionValue(String deductionValue) {
		this.deductionValue = deductionValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public boolean isCff() {
		return isCff;
	}

	public void setCff(boolean isCff) {
		this.isCff = isCff;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getHierarchyType() {
		return hierarchyType;
	}

	public void setHierarchyType(String hierarchyType) {
		this.hierarchyType = hierarchyType;
	}

	public Date getForecastEligibleDate() {
		return forecastEligibleDate == null ? null : (Date) forecastEligibleDate.clone();
	}

	public void setForecastEligibleDate(Date forecastEligibleDate) {
		this.forecastEligibleDate = forecastEligibleDate == null ? null : (Date) forecastEligibleDate.clone();
	}

	public List<String> getGroupFilterCompenies() {
		return groupFilterCompenies != null ? new ArrayList<>(groupFilterCompenies) : groupFilterCompenies;
	}

	public void setGroupFilterCompenies(List<String> groupFilterCompenies) {
		this.groupFilterCompenies = groupFilterCompenies != null ? new ArrayList<>(groupFilterCompenies)
				: groupFilterCompenies;
	}

	public int getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}

	public boolean isNdc() {
		return isNdc;
	}

	public void setNdc(boolean isNdc) {
		this.isNdc = isNdc;
	}

	public int getSelectedCustomerRelationShipBuilderSid() {
		return selectedCustomerRelationShipBuilderSid;
	}

	public Map<String, String> getTempTableMap() {
		return tempTableMap;
	}

	public void setTempTableMap(Map<String, String> tempTableMap) {
		this.tempTableMap = tempTableMap;
	}

	public void setSelectedCustomerRelationShipBuilderSid(int selectedCustomerRelationShipBuilderSid) {
		this.selectedCustomerRelationShipBuilderSid = selectedCustomerRelationShipBuilderSid;
	}

	public int getSelectedCustomerRelationShipBuilderVersionNo() {
		return selectedCustomerRelationShipBuilderVersionNo;
	}

	public void setSelectedCustomerRelationShipBuilderVersionNo(int selectedCustomerRelationShipBuilderVersionNo) {
		this.selectedCustomerRelationShipBuilderVersionNo = selectedCustomerRelationShipBuilderVersionNo;
	}

	public String getBusinessUnitValue() {
		return businessUnitValue;
	}

	public void setBusinessUnitValue(String businessUnitValue) {
		this.businessUnitValue = businessUnitValue;
	}

	public int getSelectedCustomerHierarcySid() {
		return selectedCustomerHierarcySid;
	}

	public void setSelectedCustomerHierarcySid(int selectedCustomerHierarcySid) {
		this.selectedCustomerHierarcySid = selectedCustomerHierarcySid;
	}

	public int getSelectedCustomerHierarchyVersionNo() {
		return selectedCustomerHierarchyVersionNo;
	}

	public void setSelectedCustomerHierarchyVersionNo(int selectedCustomerHierarchyVersionNo) {
		this.selectedCustomerHierarchyVersionNo = selectedCustomerHierarchyVersionNo;
	}

	public List<GtnFrameworkRelationshipLevelDefintionBean> getSelectedCustomerList() {
		return selectedCustomerList != null ? new ArrayList<>(selectedCustomerList) : selectedCustomerList;
	}

	public void setSelectedCustomerList(List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerList) {
		this.selectedCustomerList = selectedCustomerList != null ? new ArrayList<>(selectedCustomerList)
				: selectedCustomerList;
	}

	public List<GtnFrameworkRelationshipLevelDefintionBean> getSelectedProductList() {
		return selectedProductList != null ? new ArrayList<>(selectedProductList) : selectedProductList;
	}

	public void setSelectedProductList(List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductList) {
		this.selectedProductList = selectedProductList != null ? new ArrayList<>(selectedProductList)
				: selectedProductList;
	}

	public int getSelectedProductRelationShipBuilderSid() {
		return selectedProductRelationShipBuilderSid;
	}

	public void setSelectedProductRelationShipBuilderSid(int selectedProductRelationShipBuilderSid) {
		this.selectedProductRelationShipBuilderSid = selectedProductRelationShipBuilderSid;
	}

	public int getSelectedProductRelationShipBuilderVersionNo() {
		return selectedProductRelationShipBuilderVersionNo;
	}

	public void setSelectedProductRelationShipBuilderVersionNo(int selectedProductRelationShipBuilderVersionNo) {
		this.selectedProductRelationShipBuilderVersionNo = selectedProductRelationShipBuilderVersionNo;
	}

	public int getSelectedProductHierarcySid() {
		return selectedProductHierarcySid;
	}

	public void setSelectedProductHierarcySid(int selectedProductHierarcySid) {
		this.selectedProductHierarcySid = selectedProductHierarcySid;
	}

	public int getSelectedProductHierarchyVersionNo() {
		return selectedProductHierarchyVersionNo;
	}

	public void setSelectedProductHierarchyVersionNo(int selectedProductHierarchyVersionNo) {
		this.selectedProductHierarchyVersionNo = selectedProductHierarchyVersionNo;
	}

	public int getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(int projectionId) {
		this.projectionId = projectionId;
	}

	public GtnFrameworkRelationshipLevelDefintionBean getSelectedHierarchyLevelDto() {
		return selectedHierarchyLevelDto;
	}

	public void setSelectedHierarchyLevelDto(GtnFrameworkRelationshipLevelDefintionBean selectedHierarchyLevelDto) {
		this.selectedHierarchyLevelDto = selectedHierarchyLevelDto;
	}

	public String getHierarchyIndicator() {
		return hierarchyIndicator;
	}

	public void setHierarchyIndicator(String hierarchyIndicator) {
		this.hierarchyIndicator = hierarchyIndicator;
	}

	public int getLowestLevelNo() {
		return lowestLevelNo;
	}

	public void setLowestLevelNo(int lowestLevelNo) {
		this.lowestLevelNo = lowestLevelNo;
	}

	public List<GtnReportHierarchyLevelBean> getLevelList() {
		return levelList == null ? levelList : new ArrayList<>(levelList);
	}

	public void setLevelList(List<GtnReportHierarchyLevelBean> levelList) {
		this.levelList = levelList == null ? levelList : new ArrayList<>(levelList);
	}

	public boolean isForecastInsert() {
		return forecastInsert;
	}

	public void setForecastInsert(boolean forecastInsert) {
		this.forecastInsert = forecastInsert;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException {
		stream.defaultWriteObject();
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
		stream.defaultReadObject();
	}
}
