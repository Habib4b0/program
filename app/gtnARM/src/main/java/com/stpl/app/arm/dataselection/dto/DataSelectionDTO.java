/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.dto;

import com.stpl.app.arm.common.CommonLogic;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;

/**
 *
 * @author sathyaseelan.v
 */
public class DataSelectionDTO {

    private String projectionName;
    private String projectionDescription;
    private String forecastingType;
    private String customerHierarchyName;
    private String productHierarchyName;
    private boolean brandType;
    private boolean saveFlag;
    private int customerHierarchySid;
    private int productHierarchySid;
    private int createdBy;
    private int customerHierarchyVersionNo;
    private int productHierarchyVersionNo;
    private String customerHierarchyLevel;
    private String productHierarchyLevel;
    private String companyGroupSid;
    private String itemGroupSid;

    private int companyMasterSid;
    private Date fromDate;
    private Date toDate;
    private Date createdDate;
    private int custRelationshipBuilderSid;
    private int prodRelationshipBuilderSid;
    private String discountType;
    private String adjustmentType;
    private String viewType;
    private int projectionId;
    private int adjustmentId;
    private int bucompanyMasterSid;
    private int defaultCompanyMasterSid;
    private int deductionLevel;
    private List<Integer> rsContractSidList = new ArrayList<>();
    private List<LevelDTO> customerList = new ArrayList<>();
    private List<LevelDTO> productList = new ArrayList<>();
    private List<String> customerEndLevelList = new ArrayList<>();
    private List<String> productEndLevelList = new ArrayList<>();

    private ExtTreeContainer<LevelDTO> selectedCustomerContainer = new ExtTreeContainer<>(LevelDTO.class);

    private ExtTreeContainer<LevelDTO> selectedProductContainer = new ExtTreeContainer<>(LevelDTO.class);

    private ExtTreeContainer<DeductionLevelDTO> selectedDeductionContainer = new ExtTreeContainer<>(DeductionLevelDTO.class);
    private boolean addUpdateFlag = false;
    private String viewId = StringUtils.EMPTY;
    private String viewName = StringUtils.EMPTY;
    private int viewCreatedBy = 0;
    private String fromPeriod = StringUtils.EMPTY;
    private String toPeriod = StringUtils.EMPTY;
    private String fromPeriodMonth = StringUtils.EMPTY;

    private String customerRelationship = StringUtils.EMPTY;
    private String productRelationship = StringUtils.EMPTY;
    private String createdDateString = StringUtils.EMPTY;
    private String modifiedDate = StringUtils.EMPTY;
    private String createdByString = StringUtils.EMPTY;
    private String deductionLevels = StringUtils.EMPTY;
    private String selectedAdjType = StringUtils.EMPTY;
    private String adjustmentCaption = StringUtils.EMPTY;
    private boolean viewFlag;
    private List newClosedSummaryglList = new ArrayList();
    private Object summaryType;
    private String screenname = StringUtils.EMPTY;
    private Date endDate;
    private Date defaultToDate;
    private boolean checkFlag;
    private Date startDate;
    private boolean alterFlag = true;
    private int customerRelationshipVersionNo;
    private int productRelationshipVersionNo;

    public DataSelectionDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
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

    public String getForecastingType() {
        return forecastingType;
    }

    public void setForecastingType(String forecastingType) {
        this.forecastingType = forecastingType;
    }

    public String getCustomerHierarchyName() {
        return customerHierarchyName;
    }

    public void setCustomerHierarchyName(String customerHierarchyName) {
        this.customerHierarchyName = customerHierarchyName;
    }

    public String getProductHierarchyName() {
        return productHierarchyName;
    }

    public void setProductHierarchyName(String productHierarchyName) {
        this.productHierarchyName = productHierarchyName;
    }

    public boolean getBrandType() {
        return brandType;
    }

    public void setBrandType(boolean brandType) {
        this.brandType = brandType;
    }

    public boolean getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public int getCustomerHierarchySid() {
        return customerHierarchySid;
    }

    public void setCustomerHierarchySid(int customerHierarchySid) {
        this.customerHierarchySid = customerHierarchySid;
    }

    public int getProductHierarchySid() {
        return productHierarchySid;
    }

    public void setProductHierarchySid(int productHierarchySid) {
        this.productHierarchySid = productHierarchySid;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

    public String getCustomerHierarchyLevel() {
        return customerHierarchyLevel;
    }

    public void setCustomerHierarchyLevel(String customerHierarchyLevel) {
        this.customerHierarchyLevel = customerHierarchyLevel;
    }

    public String getProductHierarchyLevel() {
        return productHierarchyLevel;
    }

    public void setProductHierarchyLevel(String productHierarchyLevel) {
        this.productHierarchyLevel = productHierarchyLevel;
    }

    public String getCompanyGroupSid() {
        return companyGroupSid;
    }

    public void setCompanyGroupSid(String companyGroupSid) {
        this.companyGroupSid = companyGroupSid;
    }

    public String getItemGroupSid() {
        return itemGroupSid;
    }

    public void setItemGroupSid(String itemGroupSid) {
        this.itemGroupSid = itemGroupSid;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public Date getFromDate() {
        return CommonLogic.getInstance().getDates(fromDate);
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = CommonLogic.getInstance().getDates(fromDate);
    }

    public Date getToDate() {
        return CommonLogic.getInstance().getDates(toDate);
    }

    public void setToDate(Date toDate) {
        this.toDate = CommonLogic.getInstance().getDates(toDate);
    }

    public Date getCreatedDate() {
        return CommonLogic.getInstance().getDates(createdDate);
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = CommonLogic.getInstance().getDates(createdDate);
    }

    public int getCustRelationshipBuilderSid() {
        return custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(int custRelationshipBuilderSid) {
        this.custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public int getProdRelationshipBuilderSid() {
        return prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(int prodRelationshipBuilderSid) {
        this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public int getBucompanyMasterSid() {
        return bucompanyMasterSid;
    }

    public void setBucompanyMasterSid(int bucompanyMasterSid) {
        this.bucompanyMasterSid = bucompanyMasterSid;
    }

    public int getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(int deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public List<Integer> getRsContractSidList() {
        return CommonLogic.getInstance().getArrayListCloned(rsContractSidList);
    }

    public void setRsContractSidList(List<Integer> rsContractSidList) {
        this.rsContractSidList = CommonLogic.getInstance().getArrayListCloned(rsContractSidList);
    }

    public List<LevelDTO> getCustomerList() {
        return CommonLogic.getInstance().getArrayListCloned(customerList);
    }

    public void setCustomerList(List<LevelDTO> customerList) {
        this.customerList = CommonLogic.getInstance().getArrayListCloned(customerList);
    }

    public List<LevelDTO> getProductList() {
        return CommonLogic.getInstance().getArrayListCloned(productList);
    }

    public void setProductList(List<LevelDTO> productList) {
        this.productList = CommonLogic.getInstance().getArrayListCloned(productList);
    }

    public List<String> getCustomerEndLevelList() {
        return CommonLogic.getInstance().getArrayListCloned(customerEndLevelList);
    }

    public void setCustomerEndLevelList(List<String> customerEndLevelList) {
        this.customerEndLevelList = CommonLogic.getInstance().getArrayListCloned(customerEndLevelList);
    }

    public List<String> getProductEndLevelList() {
        return CommonLogic.getInstance().getArrayListCloned(productEndLevelList);
    }

    public void setProductEndLevelList(List<String> productEndLevelList) {
        this.productEndLevelList = CommonLogic.getInstance().getArrayListCloned(productEndLevelList);
    }

    public ExtTreeContainer<LevelDTO> getSelectedCustomerContainer() {
        return selectedCustomerContainer;
    }

    public void setSelectedCustomerContainer(ExtTreeContainer<LevelDTO> selectedCustomerContainer) {
        this.selectedCustomerContainer = selectedCustomerContainer;
    }

    public ExtTreeContainer<LevelDTO> getSelectedProductContainer() {
        return selectedProductContainer;
    }

    public void setSelectedProductContainer(ExtTreeContainer<LevelDTO> selectedProductContainer) {
        this.selectedProductContainer = selectedProductContainer;
    }

    public ExtTreeContainer<DeductionLevelDTO> getSelectedDeductionContainer() {
        return selectedDeductionContainer;
    }

    public void setSelectedDeductionContainer(ExtTreeContainer<DeductionLevelDTO> selectedDeductionContainer) {
        this.selectedDeductionContainer = selectedDeductionContainer;
    }

    public boolean isAddUpdateFlag() {
        return addUpdateFlag;
    }

    public void setAddUpdateFlag(boolean addUpdateFlag) {
        this.addUpdateFlag = addUpdateFlag;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public int getViewCreatedBy() {
        return viewCreatedBy;
    }

    public void setViewCreatedBy(int viewCreatedBy) {
        this.viewCreatedBy = viewCreatedBy;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(String fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public String getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(String toPeriod) {
        this.toPeriod = toPeriod;
    }

    public String getFromPeriodMonth() {
        return fromPeriodMonth;
    }

    public void setFromPeriodMonth(String fromPeriodMonth) {
        this.fromPeriodMonth = fromPeriodMonth;
    }

    public String getCustomerRelationship() {
        return customerRelationship;
    }

    public void setCustomerRelationship(String customerRelationship) {
        this.customerRelationship = customerRelationship;
    }

    public String getProductRelationship() {
        return productRelationship;
    }

    public void setProductRelationship(String productRelationship) {
        this.productRelationship = productRelationship;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedByString() {
        return createdByString;
    }

    public void setCreatedByString(String createdByString) {
        this.createdByString = createdByString;
    }

    public String getDeductionLevels() {
        return deductionLevels;
    }

    public void setDeductionLevels(String deductionLevels) {
        this.deductionLevels = deductionLevels;
    }

    public int getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(int adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getSelectedAdjType() {
        return selectedAdjType;
    }

    public void setSelectedAdjType(String selectedAdjType) {
        this.selectedAdjType = selectedAdjType;
    }

    public boolean isViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getAdjustmentCaption() {
        return adjustmentCaption;
    }

    public void setAdjustmentCaption(String adjustmentCaption) {
        this.adjustmentCaption = adjustmentCaption;
    }

    public List getNewClosedSummaryglList() {
        return CommonLogic.getInstance().getArrayListCloned(newClosedSummaryglList);
    }

    public void setNewClosedSummaryglList(List newClosedSummaryglList) {
        this.newClosedSummaryglList = CommonLogic.getInstance().getArrayListCloned(newClosedSummaryglList);
    }

    public Object getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(Object summaryType) {
        this.summaryType = summaryType;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public int getDefaultCompanyMasterSid() {
        return defaultCompanyMasterSid;
    }

    public void setDefaultCompanyMasterSid(int defaultCompanyMasterSid) {
        this.defaultCompanyMasterSid = defaultCompanyMasterSid;
    }

    public Date getEndDate() {
        return CommonLogic.getInstance().getDates(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = CommonLogic.getInstance().getDates(endDate);
    }

    public Date getDefaultToDate() {
        return CommonLogic.getInstance().getDates(defaultToDate);
    }

    public void setDefaultToDate(Date defaultToDate) {
        this.defaultToDate = CommonLogic.getInstance().getDates(defaultToDate);
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Date getStartDate() {
        return CommonLogic.getInstance().getDates(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = CommonLogic.getInstance().getDates(startDate);
    }

    public boolean isAlterFlag() {
        return alterFlag;
    }

    public void setAlterFlag(boolean alterFlag) {
        this.alterFlag = alterFlag;
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

}
