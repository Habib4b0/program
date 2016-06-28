/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.sessionutils;

import com.stpl.app.galforecasting.dto.ForecastDTO;
import com.stpl.app.galforecasting.utils.Constant;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author soundarrajan
 */
public class SessionDTO implements Serializable {

    private int projectionId = 0;
    private int workflowId;
    private String sessionId;
    private String projectionName;
    private String action;
    private String workflowFlag;
    private String editOperation;
    private String userId;
    private String userName;
    private String workflowStatus;
    private String workflowUserType;
    private boolean saveFlag = false;
    private boolean submitFlag;
    private boolean cidtIndicator;
    private boolean salesIndicator;
    private boolean discountIndicator;
    private boolean ppaIndicator;
    private boolean projectionSaveFlag;
    private Date fromDate;
    private Date toDate;
    private String fromPeriod;
    private String toPeriod;
    private String customerLevelNumber;
    private String productLevelNumber;
    private int lowerMostProductLevelNo;
    private int lowerMostCustomerLevelNo;
    private int lowerMostCustomLevelNo;
    private Boolean assumptionSales;
    private Boolean assumptionDiscount;
    private Boolean assumptionPPA;
    private Map<String, Integer> projectionPeriod = new HashMap<String, Integer>();
    private Map<String, List> FrequencyAndQuater = new HashMap<String, List>();
    private Map<String, Date> HistoryAndStartDate = new HashMap<String, Date>();
    private ForecastDTO forecastDTO;
    private Date currentDate;
    private Map<String, Map<String, Integer>> historyEndDetails = new HashMap<String, Map<String, Integer>>();
    private Map<String, Integer> projectionDetails = new HashMap<String, Integer>();
    private boolean fromDateChanged = false;
    private String custRelationshipBuilderSid = StringUtils.EMPTY;
    private String prodRelationshipBuilderSid = StringUtils.EMPTY;
    private Map<String, String> customerDescription = new HashMap<String, String>();
    private Map<String, String> productDescription = new HashMap<String, String>();
    private int customerHierarchyId;
    private int productHierarchyId;
    private int customerRelationId;
    private int productRelationId;
    private String marketTypeValue = StringUtils.EMPTY;
    private String discount = StringUtils.EMPTY;
    private int discountTypeId = 0;
    private int custHierarchySid;
    private int prodHierarchySid;
    private long processId;
    private String marketType = StringUtils.EMPTY;
    private String modifiedDate;
    private int projectionDetailsSid;
    private String cusFieldName = StringUtils.EMPTY;
    private String customerHierSid = StringUtils.EMPTY;
    private String productHierSid = StringUtils.EMPTY;
    private int totalCustomerLevelNo;
    private String totalDiscountCount = StringUtils.EMPTY;
    //Added for persisting freq value in session for calc and adj prc
    private String frequency = StringUtils.EMPTY;
    private Map<String, String> returnsDetailsMap = new HashMap<String, String>();
    private String detailsSID = StringUtils.EMPTY;
    private int noOfApproval;
    private int approvalLevel;
   private String companyIds=StringUtils.EMPTY;
   private String contractIds=StringUtils.EMPTY;
   private String ccpDetailsId=StringUtils.EMPTY;
   private String forecastName = StringUtils.EMPTY;
   private String ccps = StringUtils.EMPTY;
   private String startDate=StringUtils.EMPTY;
   private String endDate=StringUtils.EMPTY;
   private String deductionLevel=StringUtils.EMPTY;
   private String deductionValue=StringUtils.EMPTY;
   private String actualccp=StringUtils.EMPTY;
   private String description = StringUtils.EMPTY;
   private boolean isFileNotChanged=false;
   private boolean isNewFileCalculationNeeded = false;
   private String selCustomer = StringUtils.EMPTY;
   private String altFromPeriod;
   private String altToPeriod;
     public String getAltFromPeriod() {
        return altFromPeriod;
    }

    public void setAltFromPeriod(String altFromPeriod) {
        this.altFromPeriod = altFromPeriod;
    }

    public String getAltToPeriod() {
        return altToPeriod;
    }

    public void setAltToPeriod(String altToPeriod) {
        this.altToPeriod = altToPeriod;
    }
    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }
   
    public String getCusFieldName() {
        return cusFieldName;
    }

    public void setCusFieldName(String cusFieldName) {
        this.cusFieldName = cusFieldName;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getProjectionDetailsSid() {
        return projectionDetailsSid;
    }

    public void setProjectionDetailsSid(int projectionDetailsSid) {
        this.projectionDetailsSid = projectionDetailsSid;
    }

    public int getCustHierarchySid() {
        return custHierarchySid;
    }

    public void setCustHierarchySid(int custHierarchySid) {
        this.custHierarchySid = custHierarchySid;
    }

    public int getProdHierarchySid() {
        return prodHierarchySid;
    }

    public void setProdHierarchySid(int prodHierarchySid) {
        this.prodHierarchySid = prodHierarchySid;
    }

    public long getProcessId() {
        return processId;
    }

    public void setProcessId(long processId) {
        this.processId = processId;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public Map<String, String> getCustomerDescription() {
        return customerDescription;
    }

    public void setCustomerDescription(Map<String, String> customerDescription) {
        this.customerDescription = customerDescription;
    }

    public Map<String, String> getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Map<String, String> productDescription) {
        this.productDescription = productDescription;
    }

    public String getLevelValueDiscription(String hierarchyNo, String indicator) {
        String retVal;

        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(indicator)) {
            if (customerDescription == null) {
                retVal = StringUtils.EMPTY;
            } else {
                retVal = customerDescription.get(hierarchyNo) == null ? StringUtils.EMPTY : customerDescription.get(hierarchyNo);
            }
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(indicator)) {
            if (productDescription == null) {
                retVal = StringUtils.EMPTY;
            } else {
                retVal = productDescription.get(hierarchyNo) == null ? StringUtils.EMPTY : productDescription.get(hierarchyNo);
            }
        } else {
            retVal = StringUtils.EMPTY;
        }
        return retVal;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    public void setForecastDTO(ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    public Boolean getAssumptionSales() {
        return assumptionSales;
    }

    public void setAssumptionSales(Boolean assumptionSales) {
        this.assumptionSales = assumptionSales;
    }

    public Boolean getAssumptionDiscount() {
        return assumptionDiscount;
    }

    public void setAssumptionDiscount(Boolean assumptionDiscount) {
        this.assumptionDiscount = assumptionDiscount;
    }

    public Boolean getAssumptionPPA() {
        return assumptionPPA;
    }

    public void setAssumptionPPA(Boolean assumptionPPA) {
        this.assumptionPPA = assumptionPPA;
    }

    public Map<String, Integer> getProjectionPeriod() {
        return projectionPeriod;
    }

    public void setProjectionPeriod(Map<String, Integer> projectionPeriod) {
        this.projectionPeriod = projectionPeriod;
    }

    public void addProjectionPeriod(String frequency, Integer period) {
        this.projectionPeriod.put(frequency, period);
    }

    public Integer getProjectionPeriod(String frequency) {
        return projectionPeriod.get(frequency);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Map<String, List> getFrequencyAndQuater() {
        return FrequencyAndQuater;
    }

    public void setFrequencyAndQuater(Map<String, List> FrequencyAndQuater) {
        this.FrequencyAndQuater = FrequencyAndQuater;
    }

    public void addFrequencyAndQuater(String frequency, List HistoryCount) {
        this.FrequencyAndQuater.put(frequency, HistoryCount);
    }

    public Map<String, Date> getHistoryAndStartDate() {
        return HistoryAndStartDate;
    }

    public void setHistoryAndStartDate(Map<String, Date> HistoryAndStartDate) {
        this.HistoryAndStartDate = HistoryAndStartDate;
    }
    private boolean hasTradingPartner;

    public void addHistoryAndStartDate(String history, Date startDate) {
        this.HistoryAndStartDate.put(history, startDate);
    }

    public Date getHistoryAndStartDateValue(String history) {
        return HistoryAndStartDate.get(history);
    }

    public List getFrequencyAndQuaterValue(String frequency) {
        return FrequencyAndQuater.get(frequency);
    }
    private String sessionDate;
    private String vaadinSessionId;

    private String mode = StringUtils.EMPTY;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEditOperation() {
        return editOperation;
    }

    public void setEditOperation(String editOperation) {
        this.editOperation = editOperation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getWorkflowFlag() {
        return workflowFlag;
    }

    public void setWorkflowFlag(String workflowFlag) {
        this.workflowFlag = workflowFlag;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getWorkflowUserType() {
        return workflowUserType;
    }

    public void setWorkflowUserType(String workflowUserType) {
        this.workflowUserType = workflowUserType;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public boolean isSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(boolean saveFlag) {
        this.saveFlag = saveFlag;
    }

    public boolean isSubmitFlag() {
        return submitFlag;
    }

    public void setSubmitFlag(boolean submitFlag) {
        this.submitFlag = submitFlag;
    }

    public boolean isCidtIndicator() {
        return cidtIndicator;
    }

    public void setCidtIndicator(boolean cidtIndicator) {
        this.cidtIndicator = cidtIndicator;
    }

    public boolean isSalesIndicator() {
        return salesIndicator;
    }

    public void setSalesIndicator(boolean salesIndicator) {
        this.salesIndicator = salesIndicator;
    }

    public boolean isDiscountIndicator() {
        return discountIndicator;
    }

    public void setDiscountIndicator(boolean discountIndicator) {
        this.discountIndicator = discountIndicator;
    }

    public boolean isPpaIndicator() {
        return ppaIndicator;
    }

    public void setPpaIndicator(boolean ppaIndicator) {
        this.ppaIndicator = ppaIndicator;
    }

    public boolean isProjectionSaveFlag() {
        return projectionSaveFlag;
    }

    public void setProjectionSaveFlag(boolean projectionSaveFlag) {
        this.projectionSaveFlag = projectionSaveFlag;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getVaadinSessionId() {
        return vaadinSessionId;
    }

    public void setVaadinSessionId(String vaadinSessionId) {
        this.vaadinSessionId = vaadinSessionId;
    }

    public boolean isHasTradingPartner() {
        return hasTradingPartner;
    }

    public void setHasTradingPartner(boolean hasTradingPartner) {
        this.hasTradingPartner = hasTradingPartner;
    }

    public String getCustomerLevelNumber() {
        return customerLevelNumber;
    }

    public void setCustomerLevelNumber(String customerLevelNumber) {
        this.customerLevelNumber = customerLevelNumber;
    }

    public String getProductLevelNumber() {
        return productLevelNumber;
    }

    public void setProductLevelNumber(String productLevelNumber) {
        this.productLevelNumber = productLevelNumber;
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

    public Map<String, Map<String, Integer>> getHistoryEndDetails() {
        return historyEndDetails;
    }

    public void setHistoryEndDetails(Map<String, Map<String, Integer>> historyEndDetails) {
        this.historyEndDetails = historyEndDetails;
    }

    public void addHistoryEndDetails(String frequency, Map<String, Integer> value) {
        this.historyEndDetails.put(frequency, value);
    }

    public Map<String, Integer> getHistoryEndDetails(String frequency) {
        return historyEndDetails.get(frequency);
    }

    public Map<String, Integer> getProjectionDetails() {
        return projectionDetails;
    }

    public void setProjectionDetails(Map<String, Integer> projectionDetails) {
        this.projectionDetails = projectionDetails;
    }

    public int getLowerMostProductLevelNo() {
        return lowerMostProductLevelNo;
    }

    public void setLowerMostProductLevelNo(int lowerMostProductLevelNo) {
        this.lowerMostProductLevelNo = lowerMostProductLevelNo;
    }

    public int getLowerMostCustomerLevelNo() {
        return lowerMostCustomerLevelNo;
    }

    public void setLowerMostCustomerLevelNo(int lowerMostCustomerLevelNo) {
        this.lowerMostCustomerLevelNo = lowerMostCustomerLevelNo;
    }

    public int getLowerMostCustomLevelNo() {
        return lowerMostCustomLevelNo;
    }

    public void setLowerMostCustomLevelNo(int lowerMostCustomLevelNo) {
        this.lowerMostCustomLevelNo = lowerMostCustomLevelNo;
    }

    public void addProjectionDetails(String field, Integer value) {
        this.projectionDetails.put(field, value);
    }

    public Integer getProjectionDetails(String field) {
        return projectionDetails.get(field);
    }

    public boolean isFromDateChanged() {
        return fromDateChanged;
    }

    public void setFromDateChanged(boolean fromDateChanged) {
        this.fromDateChanged = fromDateChanged;
    }

    public String getCustRelationshipBuilderSid() {
        return custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        this.custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public String getProdRelationshipBuilderSid() {
        return prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public int getCustomerHierarchyId() {
        return customerHierarchyId;
    }

    public void setCustomerHierarchyId(int customerHierarchyId) {
        this.customerHierarchyId = customerHierarchyId;
    }

    public int getProductHierarchyId() {
        return productHierarchyId;
    }

    public void setProductHierarchyId(int productHierarchyId) {
        this.productHierarchyId = productHierarchyId;
    }

    public int getCustomerRelationId() {
        return customerRelationId;
    }

    public void setCustomerRelationId(int customerRelationId) {
        this.customerRelationId = customerRelationId;
    }

    public int getProductRelationId() {
        return productRelationId;
    }

    public void setProductRelationId(int productRelationId) {
        this.productRelationId = productRelationId;
    }

    public String getMarketTypeValue() {
        return marketTypeValue;
    }

    public void setMarketTypeValue(String marketTypeValue) {
        this.marketTypeValue = marketTypeValue;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(int discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    public String getCustomerHierSid() {
        return customerHierSid;
    }

    public void setCustomerHierSid(String customerHierSid) {
        this.customerHierSid = customerHierSid;
    }

    public String getProductHierSid() {
        return productHierSid;
    }

    public void setProductHierSid(String productHierSid) {
        this.productHierSid = productHierSid;
    }

    public int getTotalCustomerLevelNo() {
        return totalCustomerLevelNo;
    }

    public void setTotalCustomerLevelNo(int totalCustomerLevelNo) {
        this.totalCustomerLevelNo = totalCustomerLevelNo;
    }

    public String getTotalDiscountCount() {
        return totalDiscountCount;
    }

    public void setTotalDiscountCount(String totalDiscountCount) {
        this.totalDiscountCount = totalDiscountCount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Map<String, String> getReturnsDetailsMap() {
        return returnsDetailsMap;
    }

    public void setReturnsDetailsMap(Map<String, String> returnsDetailsMap) {
        this.returnsDetailsMap = returnsDetailsMap;
    }

    public String getDetailsSID() {
        return detailsSID;
    }

    public void setDetailsSID(String detailsSID) {
        this.detailsSID = detailsSID;
    }

    public int getNoOfApproval() {
        return noOfApproval;
    }

    public void setNoOfApproval(int noOfApproval) {
        this.noOfApproval = noOfApproval;
    }

    public int getApprovalLevel() {
        return approvalLevel;
    }

    public void setApprovalLevel(int approvalLevel) {
        this.approvalLevel = approvalLevel;
    }
    
    public String getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(String companyIds) {
        this.companyIds = companyIds;
    }

    public String getContractIds() {
        return contractIds;
    }

    public void setContractIds(String contractIds) {
        this.contractIds = contractIds;
    }

    public String getCcpDetailsId() {
        return ccpDetailsId;
    }

    public void setCcpDetailsId(String ccpDetailsId) {
        this.ccpDetailsId = ccpDetailsId;
    }

    public String getCcps() {
        return ccps;
    }

    public void setCcps(String ccps) {
        this.ccps = ccps;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDeductionLevel() {
        return deductionLevel;
    }

    public void setDeductionLevel(String deductionLevel) {
        this.deductionLevel = deductionLevel;
    }

    public String getDeductionValue() {
        return deductionValue;
    }

    public void setDeductionValue(String deductionValue) {
        this.deductionValue = deductionValue;
    }
    public String getActualccp() {
        return actualccp;
    }

    public void setActualccp(String actualccp) {
        this.actualccp = actualccp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isFileNotChanged() {
        return isFileNotChanged;
    }

    public void setIsFileNotChanged(boolean isFileChanged) {
        this.isFileNotChanged = isFileChanged;
    }

    public boolean isNewFileCalculationNeeded() {
        return isNewFileCalculationNeeded;
    }

    public void setIsNewFileCalculationNeeded(boolean isNewFileCalculationNeeded) {
        this.isNewFileCalculationNeeded = isNewFileCalculationNeeded;
    }   

    public String getSelCustomer() {
        return selCustomer;
    }

    public void setSelCustomer(String selCustomer) {
        this.selCustomer = selCustomer;
    }    
    
}
