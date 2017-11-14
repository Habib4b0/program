/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.contractdashboard.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardSessionBean {

	public GtnWsContractDashboardSessionBean() {
		super();
	}

	private GtnWsContractDashboardProcessBean processBean;
	private GtnWsContractWorkflowBean workflowBean;
	private List<String> contractInfoFieldList;
	private List<String> companiesFieldList;
	private List<String> itemsFieldList;
	private List<String> pricingFieldList;
	private List<String> rebateFieldList;
	private List<String> rebatePopulateFieldList;
	private String companiesLevelId;
	private String itemsLevelId;
	private String pricingLevelId;
	private String rebateLevelId;
	private String rebateCalculationTypeId;
	private String aliasTableId;
	private String companyAdditionRightTableId;
	private String itemAdditionRightTableId;
	private String companiesTableId;
	private String itemsTableId;
	private String pricingDetailsTableId;
	private String priceProtectionTableId;
	private String rebateDetailsTableId;
	private String rebateSelectedRuleTableId;
	private boolean companyAddTableLoad;
	private boolean itemAddTableLoad;
	private boolean companiesTableLoad;
	private boolean itemsTableLoad;
	private boolean pricingDetailsTableLoad;
	private boolean priceProtectionTableLoad;
	private boolean rebateDetailsTableLoad;
	private boolean companiesLoaded;
	private boolean itemsLoaded;
	private boolean companyAddLoaded;
	private boolean itemAddLoaded;
	private boolean pricingLoaded;
	private boolean rebateLoaded;
	private boolean contractLoaded;
	private boolean aliasLoaded;
	private boolean notesLoaded;
	private boolean rebateDetailsColumnChange;
	private boolean success;
	private boolean viewMode = false;
	private boolean needOperation = true;
	private GtnWsRecordBean contractInfoBean;
	private GtnWsRecordBean companyInfoBean;
	private GtnWsRecordBean itemInfoBean;
	private GtnWsRecordBean priceInfoBean;
	private GtnWsRecordBean rebateInfoBean;
	private List<GtnWsRecordBean> contractAliasRecordList;
	private List<GtnWsRecordBean> notesTabRecordList;
	private String errorDisplayId = "errorDisplay";
	private Boolean hasPermission;
	private long persistanceId;
	private String focusedId;

	public GtnWsContractDashboardProcessBean getProcessBean() {
		return processBean;
	}

	public void setProcessBean(GtnWsContractDashboardProcessBean processBean) {
		this.processBean = processBean;
	}

	public List<String> getContractInfoFieldList() {
		return contractInfoFieldList != null ? Collections.unmodifiableList(contractInfoFieldList)
				: contractInfoFieldList;
	}

	public void setContractInfoFieldList(List<String> contractInfoFieldList) {
		this.contractInfoFieldList = contractInfoFieldList != null ? Collections.unmodifiableList(contractInfoFieldList)
				: contractInfoFieldList;
	}

	public List<String> getCompaniesFieldList() {
		return companiesFieldList != null ? new ArrayList<>(companiesFieldList) : companiesFieldList;
	}

	public void setCompaniesFieldList(List<String> companiesFieldList) {
		this.companiesFieldList = companiesFieldList != null ? new ArrayList<>(companiesFieldList) : companiesFieldList;
	}

	public List<String> getItemsFieldList() {
		return itemsFieldList != null ? new ArrayList<>(itemsFieldList) : itemsFieldList;
	}

	public void setItemsFieldList(List<String> itemsFieldList) {
		this.itemsFieldList = itemsFieldList != null ? new ArrayList<>(itemsFieldList) : itemsFieldList;
	}

	public List<String> getPricingFieldList() {
		return pricingFieldList != null ? new ArrayList<>(pricingFieldList) : pricingFieldList;
	}

	public void setPricingFieldList(List<String> pricingFieldList) {
		this.pricingFieldList = pricingFieldList != null ? new ArrayList<>(pricingFieldList) : pricingFieldList;
	}

	public List<String> getRebateFieldList() {
		return rebateFieldList != null ? new ArrayList<>(rebateFieldList) : rebateFieldList;
	}

	public void setRebateFieldList(List<String> rebateFieldList) {
		this.rebateFieldList = rebateFieldList != null ? new ArrayList<>(rebateFieldList) : rebateFieldList;
	}

	public String getAliasTableId() {
		return aliasTableId;
	}

	public void setAliasTableId(String aliasTableId) {
		this.aliasTableId = aliasTableId;
	}

	public String getCompanyAdditionRightTableId() {
		return companyAdditionRightTableId;
	}

	public void setCompanyAdditionRightTableId(String companyAdditionRightTableId) {
		this.companyAdditionRightTableId = companyAdditionRightTableId;
	}

	public String getItemAdditionRightTableId() {
		return itemAdditionRightTableId;
	}

	public void setItemAdditionRightTableId(String itemAdditionRightTableId) {
		this.itemAdditionRightTableId = itemAdditionRightTableId;
	}

	public String getCompaniesTableId() {
		return companiesTableId;
	}

	public void setCompaniesTableId(String companiesTableId) {
		this.companiesTableId = companiesTableId;
	}

	public String getItemsTableId() {
		return itemsTableId;
	}

	public void setItemsTableId(String itemsTableId) {
		this.itemsTableId = itemsTableId;
	}

	public String getPricingDetailsTableId() {
		return pricingDetailsTableId;
	}

	public void setPricingDetailsTableId(String pricingDetailsTableId) {
		this.pricingDetailsTableId = pricingDetailsTableId;
	}

	public String getPriceProtectionTableId() {
		return priceProtectionTableId;
	}

	public void setPriceProtectionTableId(String priceProtectionTableId) {
		this.priceProtectionTableId = priceProtectionTableId;
	}

	public String getRebateDetailsTableId() {
		return rebateDetailsTableId;
	}

	public void setRebateDetailsTableId(String rebateDetailsTableId) {
		this.rebateDetailsTableId = rebateDetailsTableId;
	}

	public boolean isItemsTableLoad() {
		return itemsTableLoad;
	}

	public void setItemsTableLoad(boolean itemsTableLoad) {
		this.itemsTableLoad = itemsTableLoad;
	}

	public boolean isPricingDetailsTableLoad() {
		return pricingDetailsTableLoad;
	}

	public void setPricingDetailsTableLoad(boolean pricingDetailsTableLoad) {
		this.pricingDetailsTableLoad = pricingDetailsTableLoad;
	}

	public boolean isPriceProtectionTableLoad() {
		return priceProtectionTableLoad;
	}

	public void setPriceProtectionTableLoad(boolean priceProtectionTableLoad) {
		this.priceProtectionTableLoad = priceProtectionTableLoad;
	}

	public boolean isRebateDetailsTableLoad() {
		return rebateDetailsTableLoad;
	}

	public void setRebateDetailsTableLoad(boolean rebateDetailsTableLoad) {
		this.rebateDetailsTableLoad = rebateDetailsTableLoad;
	}

	public boolean isCompaniesTableLoad() {
		return companiesTableLoad;
	}

	public void setCompaniesTableLoad(boolean companiesTableLoad) {
		this.companiesTableLoad = companiesTableLoad;
	}

	public boolean isViewMode() {
		return viewMode;
	}

	public void setViewMode(boolean viewMode) {
		this.viewMode = viewMode;
	}

	public boolean isCompaniesLoaded() {
		return companiesLoaded;
	}

	public void setCompaniesLoaded(boolean companiesLoaded) {
		this.companiesLoaded = companiesLoaded;
	}

	public boolean isItemsLoaded() {
		return itemsLoaded;
	}

	public void setItemsLoaded(boolean itemsLoaded) {
		this.itemsLoaded = itemsLoaded;
	}

	public boolean isCompanyAddLoaded() {
		return companyAddLoaded;
	}

	public void setCompanyAddLoaded(boolean companyAddLoaded) {
		this.companyAddLoaded = companyAddLoaded;
	}

	public boolean isItemAddLoaded() {
		return itemAddLoaded;
	}

	public void setItemAddLoaded(boolean itemAddLoaded) {
		this.itemAddLoaded = itemAddLoaded;
	}

	public boolean isPricingLoaded() {
		return pricingLoaded;
	}

	public void setPricingLoaded(boolean pricingLoaded) {
		this.pricingLoaded = pricingLoaded;
	}

	public boolean isRebateLoaded() {
		return rebateLoaded;
	}

	public void setRebateLoaded(boolean rebateLoaded) {
		this.rebateLoaded = rebateLoaded;
	}

	public String getRebateSelectedRuleTableId() {
		return rebateSelectedRuleTableId;
	}

	public void setRebateSelectedRuleTableId(String rebateSelectedRuleTableId) {
		this.rebateSelectedRuleTableId = rebateSelectedRuleTableId;
	}

	public boolean isContractLoaded() {
		return contractLoaded;
	}

	public void setContractLoaded(boolean contractLoaded) {
		this.contractLoaded = contractLoaded;
	}

	public boolean isAliasLoaded() {
		return aliasLoaded;
	}

	public void setAliasLoaded(boolean aliasLoaded) {
		this.aliasLoaded = aliasLoaded;
	}

	public boolean isRebateDetailsColumnChange() {
		return rebateDetailsColumnChange;
	}

	public void setRebateDetailsColumnChange(boolean rebateDetailsColumnChange) {
		this.rebateDetailsColumnChange = rebateDetailsColumnChange;
	}

	public GtnWsRecordBean getContractInfoBean() {
		return contractInfoBean;
	}

	public void setContractInfoBean(GtnWsRecordBean contractInfoBean) {
		this.contractInfoBean = contractInfoBean;
	}

	public boolean isNotesLoaded() {
		return notesLoaded;
	}

	public void setNotesLoaded(boolean notesLoaded) {
		this.notesLoaded = notesLoaded;
	}

	public GtnWsRecordBean getItemInfoBean() {
		return itemInfoBean;
	}

	public void setItemInfoBean(GtnWsRecordBean itemInfoBean) {
		this.itemInfoBean = itemInfoBean;
	}

	public GtnWsRecordBean getPriceInfoBean() {
		return priceInfoBean;
	}

	public void setPriceInfoBean(GtnWsRecordBean priceInfoBean) {
		this.priceInfoBean = priceInfoBean;
	}

	public GtnWsRecordBean getCompanyInfoBean() {
		return companyInfoBean;
	}

	public void setCompanyInfoBean(GtnWsRecordBean companyInfoBean) {
		this.companyInfoBean = companyInfoBean;
	}

	public GtnWsRecordBean getRebateInfoBean() {
		return rebateInfoBean;
	}

	public void setRebateInfoBean(GtnWsRecordBean rebateInfoBean) {
		this.rebateInfoBean = rebateInfoBean;
	}

	public List<GtnWsRecordBean> getContractAliasRecordList() {
		return contractAliasRecordList != null ? new ArrayList<>(contractAliasRecordList) : contractAliasRecordList;
	}

	public void setContractAliasRecordList(List<GtnWsRecordBean> contractAliasRecordList) {
		this.contractAliasRecordList = contractAliasRecordList != null ? new ArrayList<>(contractAliasRecordList)
				: contractAliasRecordList;
	}

	public boolean isCompanyAddTableLoad() {
		return companyAddTableLoad;
	}

	public void setCompanyAddTableLoad(boolean companyAddTableLoad) {
		this.companyAddTableLoad = companyAddTableLoad;
	}

	public boolean isItemAddTableLoad() {
		return itemAddTableLoad;
	}

	public void setItemAddTableLoad(boolean itemAddTableLoad) {
		this.itemAddTableLoad = itemAddTableLoad;
	}

	public String getCompaniesLevelId() {
		return companiesLevelId;
	}

	public void setCompaniesLevelId(String companiesLevelId) {
		this.companiesLevelId = companiesLevelId;
	}

	public String getItemsLevelId() {
		return itemsLevelId;
	}

	public void setItemsLevelId(String itemsLevelId) {
		this.itemsLevelId = itemsLevelId;
	}

	public String getPricingLevelId() {
		return pricingLevelId;
	}

	public void setPricingLevelId(String pricingLevelId) {
		this.pricingLevelId = pricingLevelId;
	}

	public String getRebateLevelId() {
		return rebateLevelId;
	}

	public void setRebateLevelId(String rebateLevelId) {
		this.rebateLevelId = rebateLevelId;
	}

	public String getRebateCalculationTypeId() {
		return rebateCalculationTypeId;
	}

	public void setRebateCalculationTypeId(String rebateCalculationTypeId) {
		this.rebateCalculationTypeId = rebateCalculationTypeId;
	}

	public List<String> getRebatePopulateFieldList() {
		return rebatePopulateFieldList != null ? new ArrayList<>(rebatePopulateFieldList) : rebatePopulateFieldList;
	}

	public void setRebatePopulateFieldList(List<String> rebatePopulateFieldList) {
		this.rebatePopulateFieldList = rebatePopulateFieldList != null ? new ArrayList<>(rebatePopulateFieldList)
				: rebatePopulateFieldList;
	}

	public boolean needOperation() {
		return needOperation;
	}

	public void setNeedOperation(boolean needOperation) {
		this.needOperation = needOperation;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorDisplayId() {
		return errorDisplayId;
	}

	public void setErrorDisplayId(String errorDisplayId) {
		this.errorDisplayId = errorDisplayId;
	}

	public List<GtnWsRecordBean> getNotesTabRecordList() {
		return notesTabRecordList != null ? Collections.unmodifiableList(notesTabRecordList) : notesTabRecordList;
	}

	public void setNotesTabRecordList(List<GtnWsRecordBean> notesTabRecordList) {
		this.notesTabRecordList = notesTabRecordList != null ? Collections.unmodifiableList(notesTabRecordList)
				: notesTabRecordList;
	}

	public GtnWsContractWorkflowBean getWorkflowBean() {
		return workflowBean;
	}

	public void setWorkflowBean(GtnWsContractWorkflowBean workflowBean) {
		this.workflowBean = workflowBean;
	}

	public Boolean getHasPermission() {
		return hasPermission;
	}

	public void setHasPermission(Boolean hasPermission) {
		this.hasPermission = hasPermission;
	}

	public long getPersistanceId() {
		return persistanceId;
	}

	public void setPersistanceId(long persistanceId) {
		this.persistanceId = persistanceId;
	}

	public String getFocusedId() {
		return focusedId;
	}

	public void setFocusedId(String focusedId) {
		this.focusedId = focusedId;
	}

}
