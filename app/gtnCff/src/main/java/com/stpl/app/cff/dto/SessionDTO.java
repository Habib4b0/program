/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Manasa
 */
public class SessionDTO implements Cloneable {

    private int projectionId;
    private int workflowId;
    private String sessionId;
    private String projectionName;
    private String action;
    private String workflowFlag;
    private String editOperation;
    private String userId;
    private String userName;
    private String workflowStatus;
    private String sessionDate;
    private ForecastDTO forecastDTO;
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
    private Map<String, Integer> projectionPeriod = new HashMap<>();
    private Map<String, List> FrequencyAndQuater = new HashMap<>();
    private Map<String, Date> HistoryAndStartDate = new HashMap<>();
    private Date currentDate;
    private Map<String, Map<String, Integer>> historyEndDetails = new HashMap<>();
    private Map<String, Integer> projectionDetails = new HashMap<>();
    private boolean fromDateChanged = false;
    private String custRelationshipBuilderSid = StringUtils.EMPTY;
    private String prodRelationshipBuilderSid = StringUtils.EMPTY;
    private String dedRelationshipBuilderSid = StringUtils.EMPTY;
    private Map<String, String> customerDescription = new HashMap<>();
    private Map<String, String> productDescription = new HashMap<>();
    private Map<String, String> deductionDescription = new HashMap<>();
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
    private Map<String, String> returnsDetailsMap = new HashMap<>();
    private String detailsSID = StringUtils.EMPTY;
    private int noOfApproval;
    private int approvalLevel;
    private int companySystemId;
    private String contractIds = StringUtils.EMPTY;
    private String ccpDetailsId = StringUtils.EMPTY;
    private String forecastName = StringUtils.EMPTY;
    private boolean hasTradingPartner;
    private boolean isGenerated = false;
    private final GtnSmallHashMap currentTableNames = new GtnSmallHashMap();
    private String screenName = StringUtils.EMPTY;
    private final Map<String, List> hierarchyLevelDetails = new HashMap<>();
    private Map<Integer, List<Leveldto>> customHierarchyMap = new HashMap<>();
    private int customId;
    private Map<Integer, List<CffCustomViewDetails>> customDetailMap = new HashMap<>();
    private Future future;
    private boolean isDeductionCustom = false;
    private String salesInclusion = StringUtils.EMPTY;
    private String deductionInclusion = StringUtils.EMPTY;
    private String discountUom = StringUtils.EMPTY;
    private int selectedDeductionLevelNo;
    private int customerLastLevelNo;
    private int productLastLevelNo;
    private int customerHierarchyVersion = 0;
    private int productHierarchyVersion = 0;
    private int customerRelationVersion = 0;
    private int productRelationVersion = 0;
    public boolean isHasTradingPartner() {
        return hasTradingPartner;
    }

    public void setHasTradingPartner(boolean hasTradingPartner) {
        this.hasTradingPartner = hasTradingPartner;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
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

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getWorkflowFlag() {
        return workflowFlag;
    }

    public void setWorkflowFlag(String workflowFlag) {
        this.workflowFlag = workflowFlag;
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

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    public void setForecastDTO(ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    public String getWorkflowUserType() {
        return workflowUserType;
    }

    public void setWorkflowUserType(String workflowUserType) {
        this.workflowUserType = workflowUserType;
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

    public Map<String, List> getFrequencyAndQuater() {
        return FrequencyAndQuater;
    }

    public void setFrequencyAndQuater(Map<String, List> FrequencyAndQuater) {
        this.FrequencyAndQuater = FrequencyAndQuater;
    }

    public void addFrequencyAndQuater(String frequency, List HistoryCount) {
        this.FrequencyAndQuater.put(frequency, HistoryCount);
    }

    public List getFrequencyAndQuaterValue(String frequency) {
        return FrequencyAndQuater.get(frequency);
    }

    public Map<String, Date> getHistoryAndStartDate() {
        return HistoryAndStartDate;
    }

    public void setHistoryAndStartDate(Map<String, Date> HistoryAndStartDate) {
        this.HistoryAndStartDate = HistoryAndStartDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
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

    public String getCusFieldName() {
        return cusFieldName;
    }

    public void setCusFieldName(String cusFieldName) {
        this.cusFieldName = cusFieldName;
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

    public int getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(int companySystemId) {
        this.companySystemId = companySystemId;
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

    public String getForecastName() {
        return forecastName;
    }

    public void setForecastName(String forecastName) {
        this.forecastName = forecastName;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void addProjectionPeriod(String frequency, Integer period) {
        this.projectionPeriod.put(frequency, period);
    }

    public Integer getProjectionPeriod(String frequency) {
        return projectionPeriod.get(frequency);
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public Map<String, String> getDeductionDescription() {
        return deductionDescription;
    }

    public void setDeductionDescription(Map<String, String> deductionDescription) {
        this.deductionDescription = deductionDescription;
    }
    
    

    @Override
    public SessionDTO clone() throws CloneNotSupportedException {
        SessionDTO sessiondto = (SessionDTO) super.clone();
        return sessiondto;
    }
    

    public String getLevelValueDiscription(String hierarchyNo, String indicator) {
        String retVal;
        if ("C".equals(indicator)) {
            if (customerDescription == null) {
                retVal = StringUtils.EMPTY;
            } else {
                retVal = customerDescription.get(hierarchyNo) == null ? StringUtils.EMPTY : customerDescription.get(hierarchyNo);
            }
        } else if ("P".equals(indicator)) {
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
 /**
     *
     * @param customerLevelDetails
     */
    public void setCustomerLevelDetails(Map<String, List> customerLevelDetails) {
        if (customerLevelDetails != null && !customerLevelDetails.isEmpty()) {
            this.customerDescription.clear();
            for (String key : customerLevelDetails.keySet()) {
                this.customerDescription.put(key, customerLevelDetails.get(key).get(0).toString());
            }
        }
        this.hierarchyLevelDetails.putAll(customerLevelDetails);
    }

    /**
     *
     * @param productLevelDetails
     */
    public void setProductLevelDetails(Map<String, List> productLevelDetails) {
        if (productLevelDetails != null && !productLevelDetails.isEmpty()) {
            this.productDescription.clear();
            for (String key : productLevelDetails.keySet()) {
                this.productDescription.put(key, productLevelDetails.get(key).get(0).toString());
            }
        }
        this.hierarchyLevelDetails.putAll(productLevelDetails);
    }
    
     /**
     *
     * @param deductionLevelDetails
     */
	public void setDeductionLevelDetails(Map<String, List> deductionLevelDetails) {
		if (deductionLevelDetails != null && !deductionLevelDetails.isEmpty()) {
			this.productDescription.clear();
			for (Entry<String, List> iterable_element : deductionLevelDetails.entrySet()) {
				this.productDescription.put(iterable_element.getKey(), iterable_element.getValue().get(0).toString());
			}
		}
		this.hierarchyLevelDetails.putAll(deductionLevelDetails);
	}
    public Map<String, List> getHierarchyLevelDetails() {
        return Collections.unmodifiableMap(hierarchyLevelDetails);
    }
    public boolean isIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public GtnSmallHashMap getCurrentTableNames() {
        return currentTableNames;
    }

    public void addCurrentTableNames(final String key, final String value) {
        currentTableNames.put(key, value);
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Map<Integer, List<Leveldto>> getCustomHierarchyMap() {
        return customHierarchyMap;
    }

    public void setCustomHierarchyMap(Map<Integer, List<Leveldto>> customHierarchyMap) {
        this.customHierarchyMap = customHierarchyMap;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public Map<Integer, List<CffCustomViewDetails>> getCustomDetailMap() {
        return customDetailMap;
    }

    public void setCustomDetailMap(Map<Integer, List<CffCustomViewDetails>> customDetailMap) {
        this.customDetailMap = customDetailMap;
    }

    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public boolean isIsDeductionCustom() {
        return isDeductionCustom;
    }

    public void setIsDeductionCustom(boolean isDeductionCustom) {
        this.isDeductionCustom = isDeductionCustom;
    }

    public String getDedRelationshipBuilderSid() {
        return dedRelationshipBuilderSid;
    }

    public void setDedRelationshipBuilderSid(String dedRelationshipBuilderSid) {
        this.dedRelationshipBuilderSid = dedRelationshipBuilderSid;
    }

    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getDiscountUom() {
        return discountUom;
    }

    public void setDiscountUom(String discountUom) {
        this.discountUom = discountUom;
    }

    public int getSelectedDeductionLevelNo() {
        return selectedDeductionLevelNo;
    }

    public void setSelectedDeductionLevelNo(int selectedDeductionLevelNo) {
        this.selectedDeductionLevelNo = selectedDeductionLevelNo;
    }

    public int getCustomerLastLevelNo() {
        return customerLastLevelNo;
    }

    public void setCustomerLastLevelNo(int customerLastLevelNo) {
        this.customerLastLevelNo = customerLastLevelNo;
    }

    public int getProductLastLevelNo() {
        return productLastLevelNo;
    }

    public void setProductLastLevelNo(int productLastLevelNo) {
        this.productLastLevelNo = productLastLevelNo;
    }

	public int getCustomerHierarchyVersion() {
		return customerHierarchyVersion;
	}

	public void setCustomerHierarchyVersion(int customerHierarchyVersion) {
		this.customerHierarchyVersion = customerHierarchyVersion;
	}

	public int getProductHierarchyVersion() {
		return productHierarchyVersion;
	}

	public void setProductHierarchyVersion(int productHierarchyVersion) {
		this.productHierarchyVersion = productHierarchyVersion;
	}

	public int getCustomerRelationVersion() {
		return customerRelationVersion;
	}

	public void setCustomerRelationVersion(int customerRelationVersion) {
		this.customerRelationVersion = customerRelationVersion;
	}

	public int getProductRelationVersion() {
		return productRelationVersion;
	}

	public void setProductRelationVersion(int productRelationVersion) {
		this.productRelationVersion = productRelationVersion;
	}
        
}
