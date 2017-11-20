/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.sessionutils;

import com.stpl.app.gtnforecasting.dataassumptions.logic.DataAssumptionsLogic;
import com.stpl.app.gtnforecasting.dto.ForecastDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
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
    private boolean generateFlag = false;
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
    private GtnSmallHashMap projectionPeriod = new GtnSmallHashMap();
    private Map<String, List> FrequencyAndQuater = new HashMap<>();
    private Map<String, Date> HistoryAndStartDate = new HashMap<>();
    private ForecastDTO forecastDTO;
    private Date currentDate;
    private Map<String, Map<String, Integer>> historyEndDetails = new HashMap<>();
    private Map<String, Integer> projectionDetails = new HashMap<>();
    private boolean fromDateChanged = false;
    private String custRelationshipBuilderSid = StringUtils.EMPTY;
    private String prodRelationshipBuilderSid = StringUtils.EMPTY;
    private String dedRelationshipBuilderSid = StringUtils.EMPTY;
    private Map<String, String> customerDescription = new HashMap<>();
    private Map<String, String> productDescription = new HashMap<>();
    private Map<String, List> hierarchyLevelDetails = new HashMap<>();
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
    private String companyIds = StringUtils.EMPTY;
    private String contractIds = StringUtils.EMPTY;
    private String ccpDetailsId = StringUtils.EMPTY;
    private String forecastName = StringUtils.EMPTY;
    private String ccps = StringUtils.EMPTY;
    private String startDate = StringUtils.EMPTY;
    private String endDate = StringUtils.EMPTY;
    private String deductionLevel = StringUtils.EMPTY;
    private String deductionValue = StringUtils.EMPTY;
    private String actualccp = StringUtils.EMPTY;
    private String description = StringUtils.EMPTY;
    private boolean isFileNotChanged = false;
    private boolean isNewFileCalculationNeeded = false;
    private String selCustomer = StringUtils.EMPTY;
    private String altFromPeriod;
    private String altToPeriod;
    private int tradingPartner = 0;
    private List<Leveldto> productHierarchyList;
    private List<Leveldto> customerHierarchyList;
    private Map<Integer, List<Leveldto>> customHierarchyMap = new HashMap<>();
    private Map<Integer, List<CustomViewDetails>> customDetailMap = new HashMap<>();
    private String screenName = StringUtils.EMPTY;
    private int customId;
    private Set<String> salesgroupSet;
    boolean sprRefreshReqd = false;
    boolean isProgramCategory = true;
    private Set<String> discountgroupSet;
    private List<List<String>> discountRSlist = new ArrayList<>();
    boolean discountRSlistUpdated = false;
    private List<List<String>> discountlist = new ArrayList<>();
    private List<CustomViewMaster> customerViewList = new ArrayList<>();
    private List<Thread> threadList = new ArrayList<>();
    private Map<String, Future[]> futureList = new HashMap<>();
    private int numberOfThreads;
    private boolean isPPAUpdated = false;
    private GtnSmallHashMap currentTableNames = new GtnSmallHashMap();    
    private boolean dprRefreshReqd = false;
    private List<Object[]> busineesUnit = new ArrayList<>();
    private boolean prRefreshReqd = false;
    private int maximumCustomerLevel;
    private int maximumProductLevel;
    List<String> selectedCustomerRelationSid;
    List<String> selectedProductRelationSid;
    private String currentActiveFile = null;
    private String fileName = StringUtils.EMPTY;
    private String fileType = StringUtils.EMPTY;
    private int fileStatus ;
    private boolean newFileAtivated = false;
    private boolean isSalesCalculated = false;
    private boolean isDiscountCalculated = false;
    private int ccpDetailsSid ;
    private String salesInclusion = StringUtils.EMPTY;
    private String deductionInclusion = StringUtils.EMPTY;
    private String discountUom = StringUtils.EMPTY;
    private boolean isSPCalculationDoneAgain = false;
    private Map<String,Object[]> latestProjectionFileDetails = new HashMap<>();
    private String fileNameUsedInSales = StringUtils.EMPTY;
    private DataAssumptionsLogic dataAssumptionLogic;
    private boolean actualAdjustment = false;
    private String actualAdjustmentPeriods = StringUtils.EMPTY;
    private boolean isDeductionCustom = false;
    List<String> selectedRsForCustom;
    private int selectedDeductionLevelNo;
    private int selectedDeductionLevelNoPv;
    private int customerLastLevelNo;
    private int productLastLevelNo;
    private String tabNameCaption = StringUtils.EMPTY;
    private String tabName;
    
    private int customerHierarchyVersion = 0;
    private int productHierarchyVersion = 0;
    private int customerRelationVersion = 0;
    private int productRelationVersion = 0;

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

    /**
     *
     * @param customerLevelDetails
     */
    public void setCustomerLevelDetails(Map<String, List> customerLevelDetails) {
        this.hierarchyLevelDetails.putAll(customerLevelDetails);
    }

    /**
     *
     * @param productLevelDetails
     */
    public void setProductLevelDetails(Map<String, List> productLevelDetails) {
        this.hierarchyLevelDetails.putAll(productLevelDetails);
    }
    
     public void setDeductionLevelDetails(Map<String, List> deductionLevelDetails) {
        this.hierarchyLevelDetails.putAll(deductionLevelDetails);
    }

    public void setHierarchyLevelDetails(Map<String, List> hierarchyLevelDetails) {
        this.hierarchyLevelDetails = hierarchyLevelDetails;
    }
    public Map<String, List> getHierarchyLevelDetails() {
        return Collections.unmodifiableMap(hierarchyLevelDetails);
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

    public GtnSmallHashMap getProjectionPeriod() {
        return projectionPeriod;
    }

    public void setProjectionPeriod(GtnSmallHashMap projectionPeriod) {
        this.projectionPeriod = projectionPeriod;
    }

    public void addProjectionPeriod(String frequency, Integer period) {
        this.projectionPeriod.put(frequency, period);
    }

    public int getProjectionPeriod(String frequency) {
        return projectionPeriod.getInt(frequency);
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

    public boolean isGenerateFlag() {
        return generateFlag;
    }

    public void setGenerateFlag(boolean generateFlag) {
        this.generateFlag = generateFlag;
    }

    public int getTradingPartner() {
        return tradingPartner;
    }

    public void setTradingPartner(int tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    public List<Leveldto> getProductHierarchyList() {
        return productHierarchyList;
    }

    public void setProductHierarchyList(List<Leveldto> productHierarchyList) {
        this.productHierarchyList = productHierarchyList;
    }

    public List<Leveldto> getCustomerHierarchyList() {
        return customerHierarchyList;
    }

    public void setCustomerHierarchyList(List<Leveldto> customerHierarchyList) {
        this.customerHierarchyList = customerHierarchyList;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public Map<Integer, List<CustomViewDetails>> getCustomDetailMap() {
        return customDetailMap;
    }

    public void setCustomDetailMap(Map<Integer, List<CustomViewDetails>> customDetailMap) {
        this.customDetailMap = customDetailMap;
    }

    public Map<Integer, List<Leveldto>> getCustomHierarchyMap() {
        return customHierarchyMap;
    }

    public void setCustomHierarchyMap(Map<Integer, List<Leveldto>> customHierarchyMap) {
        this.customHierarchyMap = customHierarchyMap;
    }

    public Set<String> getSalesgroupSet() {
        return salesgroupSet;
    }

    public void setSalesgroupSet(Set<String> salesgroupSet) {
        this.salesgroupSet = salesgroupSet;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<List<String>> getDiscountRSlist() {
        return discountRSlist;
    }

    public void setDiscountRSlist(List<List<String>> discountRSlist) {
        this.discountRSlist = discountRSlist;
    }

    public Set<String> getDiscountgroupSet() {
        return discountgroupSet;
    }

    public void setDiscountgroupSet(Set<String> discountgroupSet) {
        this.discountgroupSet = discountgroupSet;
    }

    public boolean isSprRefreshReqd() {
        return sprRefreshReqd;
    }

    public void setSprRefreshReqd(boolean sprRefreshReqd) {
        this.sprRefreshReqd = sprRefreshReqd;
    }

    public boolean isIsProgramCategory() {
        return isProgramCategory;
    }

    public void setIsProgramCategory(boolean isProgramCategory) {
        this.isProgramCategory = isProgramCategory;
    }

    public boolean isDiscountRSlistUpdated() {
        return discountRSlistUpdated;
    }

    public void setDiscountRSlistUpdated(boolean discountRSlistUpdated) {
        this.discountRSlistUpdated = discountRSlistUpdated;
    }

    public List<CustomViewMaster> getCustomerViewList() {
        return customerViewList;
    }

    public void setCustomerViewList(List<CustomViewMaster> customerViewList) {
        this.customerViewList = customerViewList;
    }

    public Thread getThread(final int index) {

        if (index < threadList.size() && threadList.get(index) != null) {
            return threadList.get(index);
        } else {
            return null;
        }
    }

    public void addThreadInThreadList(Thread thread) {
        threadList.add(thread);
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public boolean isIsPPAUpdated() {
        return isPPAUpdated;
    }

    public void setIsPPAUpdated(boolean isPPAUpdated) {
        this.isPPAUpdated = isPPAUpdated;
    }

    public List<List<String>> getDiscountlist() {
        return discountlist;
    }

    public void setDiscountlist(List<List<String>> discountlist) {
        this.discountlist = discountlist;
}
    
    public GtnSmallHashMap getCurrentTableNames() {
        return currentTableNames;
    }

    public void addCurrentTableNames(final String key, final String value) {
        currentTableNames.put(key, value);
    }

    public boolean isDprRefreshReqd() {
        return dprRefreshReqd;
    }

    public void setDprRefreshReqd(boolean dprRefreshReqd) {
        this.dprRefreshReqd = dprRefreshReqd;
    }

    public List<Object[]> getBusineesUnit() {
        return busineesUnit;
    }

    public void setBusineesUnit(List<Object[]> busineesUnit) {
        this.busineesUnit = busineesUnit;
    }

    public boolean isPrRefreshReqd() {
        return prRefreshReqd;
    }

    public void setPrRefreshReqd(boolean prRefreshReqd) {
        this.prRefreshReqd = prRefreshReqd;
    }

    public Future[] getFutureValue(String key) {
        if (futureList.containsKey(key)) {
            return futureList.get(key);
        } else {
            return null;
        }
    }

    public Future getFutureValue(String key, int index) {
        if (futureList.containsKey(key)) {
            return (futureList.get(key))[index];
        } else {
            return null;
        }
    }

    public void addFutureMap(String key, Future[] value) {
        this.futureList.put(key, value);
    }

    public Map<String, Future[]> returnFutureMap() {
        return futureList;
    }

    public int getMaximumCustomerLevel() {
        return maximumCustomerLevel;
    }

    public void setMaximumCustomerLevel(int maximumCustomerLevel) {
        this.maximumCustomerLevel = maximumCustomerLevel;
    }

    public int getMaximumProductLevel() {
        return maximumProductLevel;
    }

    public void setMaximumProductLevel(int maximumProductLevel) {
        this.maximumProductLevel = maximumProductLevel;
    }

    public void resetCurrentTableNames() {
        currentTableNames = new GtnSmallHashMap();
    }

    public List<String> getSelectedCustomerRelationSid() {
        return selectedCustomerRelationSid;
    }

    public void setSelectedCustomerRelationSid(List<String> selectedCustomerRelationSid) {
        this.selectedCustomerRelationSid = selectedCustomerRelationSid;
    }

    public List<String> getSelectedProductRelationSid() {
        return selectedProductRelationSid;
    }

    public void setSelectedProductRelationSid(List<String> selectedProductRelationSid) {
        this.selectedProductRelationSid = selectedProductRelationSid;
    }

   public int getCcpDetailsSid() {
        return ccpDetailsSid;
}

    public void setCcpDetailsSid(int ccpDetailsSid) {
        this.ccpDetailsSid = ccpDetailsSid;
    }
    public String getCurrentActiveFile() {
        return currentActiveFile;
    }

    public void setCurrentActiveFile(String currentActiveFile) {
        this.currentActiveFile = currentActiveFile;
    }

    public boolean isNewFileAtivated() {
        return newFileAtivated;
    }

    public void setNewFileAtivated(boolean newFileAtivated) {
        this.newFileAtivated = newFileAtivated;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public boolean isIsSalesCalculated() {
        return isSalesCalculated;
    }

    public void setIsSalesCalculated(boolean isSalesCalculated) {
        this.isSalesCalculated = isSalesCalculated;
    }

    public boolean isIsSPCalculationDoneAgain() {
        return isSPCalculationDoneAgain;
    }

    public void setIsSPCalculationDoneAgain(boolean isSPCalculationDoneAgain) {
        this.isSPCalculationDoneAgain = isSPCalculationDoneAgain;
    }

    public boolean isIsDiscountCalculated() {
        return isDiscountCalculated;
    }

    public void setIsDiscountCalculated(boolean isDiscountCalculated) {
        this.isDiscountCalculated = isDiscountCalculated;
    }

    public Map<String, Object[]> getLatestProjectionFileDetails() {
        return latestProjectionFileDetails;
    }

    public void setLatestProjectionFileDetails(Map<String, Object[]> latestProjectionFileDetails) {
        this.latestProjectionFileDetails = latestProjectionFileDetails;
    }

    public String getFileNameUsedInSales() {
        return fileNameUsedInSales;
    }

    public void setFileNameUsedInSales(String fileNameUsedInSales) {
        this.fileNameUsedInSales = fileNameUsedInSales;
    }

    public DataAssumptionsLogic getDataAssumptionLogic() {
        return dataAssumptionLogic;
    }

    public void setDataAssumptionLogic(DataAssumptionsLogic dataAssumptionLogic) {
        this.dataAssumptionLogic = dataAssumptionLogic;
    }
    
    public boolean getDiscountCanBeCalculated(Object[] obj){
        return dataAssumptionLogic.discountCanbeCalculated(obj);
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }
    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }
    
    public boolean isActualAdjustment() {
        return actualAdjustment;
    }

    public void setActualAdjustment(boolean actualAdjustment) {
        this.actualAdjustment = actualAdjustment;
    }

    public String getActualAdjustmentPeriods() {
        return actualAdjustmentPeriods;
    }

    public void setActualAdjustmentPeriods(String actualAdjustmentPeriods) {
        this.actualAdjustmentPeriods = actualAdjustmentPeriods;
    }

    public String getDedRelationshipBuilderSid() {
        return dedRelationshipBuilderSid;
    }

    public void setDedRelationshipBuilderSid(String dedRelationshipBuilderSid) {
        this.dedRelationshipBuilderSid = dedRelationshipBuilderSid;
    }

    public boolean isIsDeductionCustom() {
        return isDeductionCustom;
    }

    public void setIsDeductionCustom(boolean isDeductionCustom) {
        this.isDeductionCustom = isDeductionCustom;
    }

    public List<String> getSelectedRsForCustom() {
        return selectedRsForCustom;
    }

    public void setSelectedRsForCustom(List<String> selectedRsForCustom) {
        this.selectedRsForCustom = selectedRsForCustom;
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
    
    public int getSelectedDeductionLevelNoPv() {
        return selectedDeductionLevelNoPv;
    }

    public void setSelectedDeductionLevelNoPv(int selectedDeductionLevelNoPv) {
        this.selectedDeductionLevelNoPv = selectedDeductionLevelNoPv;
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

    public String getTabNameCaption() {
        return tabNameCaption;
    }

    public void setTabNameCaption(String tabNameCaption) {
        this.tabNameCaption = tabNameCaption;
    }
    
    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
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