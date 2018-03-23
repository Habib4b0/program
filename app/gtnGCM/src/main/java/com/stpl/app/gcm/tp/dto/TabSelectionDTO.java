/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.dto;

import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author lokeshwari
 */
public class TabSelectionDTO {

	private String levelValue = StringUtils.EMPTY;
	private String tableName = StringUtils.EMPTY;
	private String tempTableName = StringUtils.EMPTY;
	private String fieldName = StringUtils.EMPTY;
	private String idField = StringUtils.EMPTY;
	private String parentLevel = StringUtils.EMPTY;
	private int companyMasterSid;
	private int contractMasterSid;
	private int sessionID;
	private int brandMasterSid;
	private String companyName = StringUtils.EMPTY;
	private int rebateProgramType;
	private String salesField;
	private String unitField;
	private String rebateField;
	private String amountField;
    private boolean isProjectionTotal;
    private String frequency = StringUtils.EMPTY;
    private String operation = StringUtils.EMPTY;
    private List<ItemIndexDto> itemList;
    private List<String> companyMasterSids = new ArrayList<>();
    private int summaryProjectionId;
    private String foreCastingType = StringUtils.EMPTY;

    public TabSelectionDTO() {
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public int getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(String parentLevel) {
        this.parentLevel = parentLevel;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(int rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public String getSalesField() {
        return salesField;
    }

    public void setSalesField(String salesField) {
        this.salesField = salesField;
    }

    public String getUnitField() {
        return unitField;
    }

    public void setUnitField(String unitField) {
        this.unitField = unitField;
    }

    public String getRebateField() {
        return rebateField;
    }

    public void setRebateField(String rebateField) {
        this.rebateField = rebateField;
    }

    public String getAmountField() {
        return amountField;
    }

    public void setAmountField(String amountField) {
        this.amountField = amountField;
    }

    public boolean isIsProjectionTotal() {
        return isProjectionTotal;
    }

    public void setIsProjectionTotal(boolean isProjectionTotal) {
        this.isProjectionTotal = isProjectionTotal;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<ItemIndexDto> getItemList() {
        return itemList == null ? itemList : new ArrayList<>(itemList);
    }

    public void setItemList(List<ItemIndexDto> itemList) {
        this.itemList = itemList == null ? itemList : new ArrayList<>(itemList);
    }

    public List<String> getCompanyMasterSids() {
        return companyMasterSids == null ? companyMasterSids : new ArrayList<>(companyMasterSids);
    }

    public void setCompanyMasterSids(List<String> companyMasterSids) {
        this.companyMasterSids = companyMasterSids == null ? companyMasterSids : new ArrayList<>(companyMasterSids);
    }

    public String getTempTableName() {
        return tempTableName;
    }

    public void setTempTableName(String tempTableName) {
        this.tempTableName = tempTableName;
    }

    public int getSummaryProjectionId() {
        return summaryProjectionId;
    }

    public void setSummaryProjectionId(int summaryProjectionId) {
        this.summaryProjectionId = summaryProjectionId;
    }

    public String getForeCastingType() {
        return foreCastingType;
    }

    public void setForeCastingType(String foreCastingType) {
        this.foreCastingType = foreCastingType;
    }

}
