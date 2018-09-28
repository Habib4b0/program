
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.BalanceSummaryReportDataSelectionTab;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthikeyan.S
 */
public abstract class AbstractSelectionDTO implements SelectionDTO, Serializable {

    /*----------------------------------Variables used in Summary----------------------------------------------------*/
    private int summaryfrequency = 0;
    private List<String[]> summaryvariables;
    private List<String[]> summarydeductionVariables;
    private List<String> summarycolumnList;
    private List<String> inventorycolumnList;
    private int summarydeductionLevel = 0;
    private String summarydeductionLevelDes = StringUtils.EMPTY;
    private String summaryDeductionView = StringUtils.EMPTY;
    private String summarymonth = StringUtils.EMPTY;
    private String summarydeductionValues = StringUtils.EMPTY;
    private String summarydemandview = StringUtils.EMPTY;
    private String summarydemandtoDate = StringUtils.EMPTY;
    private String summarydemandfromDate = StringUtils.EMPTY;
    private String summarydemandfrequency = StringUtils.EMPTY;
    private Map<Integer, String> summarydemandLevel;
    private int summarydemandMultipleLevel;
    private int summarylevelFilterNo;
    private String summarylevelFilterValue;
    private String summaryviewType = StringUtils.EMPTY;
    private int projectionMasterSid = 0;
    private int companyMasterSid = 0;

    private transient DataSelectionDTO dataSelectionDTO;

    private transient SessionDTO sessionDTO;

    protected transient List<String> selectedAdjustmentType;

    protected String detailLevel = StringUtils.EMPTY;

    protected transient List<String> detailreserveAcount;

    private List<String> detailamountFilter;

    protected transient List<String> detailvariables;

    private List<String> savedetailvariables;

    private boolean excelDetials;

    protected String summaryglDate = StringUtils.EMPTY;

    private transient Map<String, Object> procedureInputs = new HashMap<>();
    private String fromDate;
    private String toDate;
    private String fromDateFilter;
    private String toDateFilter;
    private String dataSelectionFromDate;
    private String dataSelectionToDate;
    private int summaryPeriods;
    private transient List<Object> periodSidList = new ArrayList<>();

    private int summaryvalueSid = 0;
    private String deductionLevelValue = StringUtils.EMPTY;
    private String saleslevelFilterValue = StringUtils.EMPTY;
    private String dateType = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;

    private List<String[]> salesVariables;
    private String[] variableVisibleColumns;
    private String inventoryDetailsDdlb = StringUtils.EMPTY;
    private String baseLinePrice = StringUtils.EMPTY;
    private String adjustedPrice = StringUtils.EMPTY;
    private String inventoryreserveDate = StringUtils.EMPTY;
    private String inventoryDetails = StringUtils.EMPTY;
    private List<String> excelVisibleColumn;
    private boolean isMultiple;
    private List<String> selectedAdjustmentTypeValues = new ArrayList<>();
    private List<String> customerGroupList = new ArrayList<>();
    private List<String> customerList = new ArrayList<>();

    /*----------------------------------RATES----------------------------------------------------*/
    private int rateDeductionLevel = 0;

    private String rateDeductionLevelName = StringUtils.EMPTY;

    private String rateDeductionValue = StringUtils.EMPTY;

    private String rateDeductionView = StringUtils.EMPTY;

    private int rateBasisValue = 0;

    private String rateBasisName = StringUtils.EMPTY;

    private int rateFrequencyValue = 0;

    private String rateFrequencyName = StringUtils.EMPTY;

    private String ratePeriodValue = StringUtils.EMPTY;

    private transient List<List> rateColumnList = new ArrayList<>();

    private String rateLevelName = StringUtils.EMPTY;

    private String moduleName = StringUtils.EMPTY;

    private String tableName = StringUtils.EMPTY;

    private int rateslevelFilterNo = 0;

    private int ratesOverrideFlag = 0;
    // -----------------------------Hierarchy------------------------
    private Map<Integer, String> saleshierarchy;
    private Map<Integer, String> rateshierarchy;
    private Map<Integer, String> summeryhierarchy;

    /*----------------------------------RATES----------------------------------------------------*/
    private int levelNo;

    private String status;

    private List<String[]> summaryfrequencyList;

    private String summaryFrequencyName = StringUtils.EMPTY;

    private String summaryStatusID = StringUtils.EMPTY;

    private String rateBasis = StringUtils.EMPTY;

    private String rateFrequency = StringUtils.EMPTY;

    private String ratePeriod = StringUtils.EMPTY;

    private String inventoryOptionGroup = StringUtils.EMPTY;

    private transient Object[] excelHierarchy;

    private TreeMap<String, Integer> masterSids;

    private Integer userId;

    private Integer sessionId;

    private boolean totalFlag;

    private boolean isWorkFlow = false;

    private Set<String> customerGroupSidSet = new HashSet<>();

    private boolean reloadFlag = false;

    private List<String> returnsdatavariables;

    private List<String> returnsdataSelectedvariables;

    private transient List<List> returnReserveDataVariables;

    private Date originalSaleLimiter;

    private String originalSaleLimiterVal = StringUtils.EMPTY;

    private int removeClosedBatches;

    private int excludeBasedOnLoeDate;

    private boolean cancelOverride;

    private Map<Integer, String> returnReserveDataHierarchy;

    private String returnReserveDataLevelName = StringUtils.EMPTY;

    private String returnReserveDataDeductionView = StringUtils.EMPTY;

    private String returnReserveDeductionValue = StringUtils.EMPTY;

    private int returnReserveDatalevelFilterNo = 0;

    private transient BalanceSummaryReportDataSelectionTab balanceSummaryDataSelectionTab;

    private boolean submittedFlag;

    public boolean isSubmittedFlag() {
        return submittedFlag;
    }

    public void setSubmittedFlag(boolean submittedFlag) {
        this.submittedFlag = submittedFlag;
    }

    public int getSummaryfrequency() {
        return summaryfrequency;
    }

    public void setSummaryfrequency(int summaryfrequency) {
        this.summaryfrequency = summaryfrequency;
    }

    @Override
    public List<String[]> getSummaryvariables() {
        return CommonLogic.getInstance().getArrayListCloned(summaryvariables);
    }

    @Override
    public void setSummaryvariables(List<String[]> summaryvariables) {
        this.summaryvariables = CommonLogic.getInstance().getArrayListCloned(summaryvariables);
    }

    @Override
    public List<String[]> getSummarydeductionVariables() {
        return CommonLogic.getInstance().getArrayListCloned(summarydeductionVariables);
    }

    public void setSummarydeductionVariables(List<String[]> summarydeductionVariables) {
        this.summarydeductionVariables = CommonLogic.getInstance().getArrayListCloned(summarydeductionVariables);
    }

    @Override
    public List<String> getSummarycolumnList() {
        return CommonLogic.getInstance().getArrayListCloned(summarycolumnList);
    }

    public void setSummarycolumnList(List<String> summarycolumnList) {
        this.summarycolumnList = CommonLogic.getInstance().getArrayListCloned(summarycolumnList);
    }

    @Override
    public int getSummarydeductionLevel() {
        return summarydeductionLevel;
    }

    public void setSummarydeductionLevel(int summarydeductionLevel) {
        this.summarydeductionLevel = summarydeductionLevel;
    }

    @Override
    public String getSummarydeductionLevelDes() {
        return summarydeductionLevelDes;
    }

    public void setSummarydeductionLevelDes(String summarydeductionLevelDes) {
        this.summarydeductionLevelDes = summarydeductionLevelDes;
    }

    @Override
    public String getSummarymonth() {
        return summarymonth;
    }

    public void setSummarymonth(String summarymonth) {
        this.summarymonth = summarymonth;
    }

    @Override
    public int getProjectionMasterSid() {
        return projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        this.projectionMasterSid = projectionMasterSid;
    }

    @Override
    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    @Override
    public String getSummarydeductionValues() {
        return summarydeductionValues;
    }

    public void setSummarydeductionValues(String summarydeductionValues) {
        this.summarydeductionValues = summarydeductionValues;
    }

    @Override
    public DataSelectionDTO getDataSelectionDTO() {
        return dataSelectionDTO;
    }

    public void setDataSelectionDTO(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    @Override
    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    @Override
    public List<String> getSelectedAdjustmentType() {
        return selectedAdjustmentType;
    }

    public void setSelectedAdjustmentType(List<String> selectedAdjustmentType) {
        this.selectedAdjustmentType = selectedAdjustmentType;
    }

    @Override
    public String getDetailLevel() {
        return detailLevel;
    }

    public void setDetailLevel(String detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public List<String> getDetailreserveAcount() {
        return detailreserveAcount;
    }

    public void setDetailreserveAcount(List<String> detailreserveAcount) {
        this.detailreserveAcount = detailreserveAcount;
    }

    @Override
    public List<String> getDetailvariables() {
        return detailvariables;
    }

    public void setDetailvariables(List<String> detailvariables) {
        this.detailvariables = detailvariables;
    }

    @Override
    public String getSummaryglDate() {
        return summaryglDate;
    }

    public void setSummaryglDate(String summaryglDate) {
        this.summaryglDate = summaryglDate;
    }

    @Override
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String getDeductionLevelValue() {
        return deductionLevelValue;
    }

    public void setDeductionLevelValue(String deductionLevelValue) {
        this.deductionLevelValue = deductionLevelValue;
    }

    @Override
    public String getSaleslevelFilterValue() {
        return saleslevelFilterValue;
    }

    public void setSaleslevelFilterValue(String saleslevelFilterValue) {
        this.saleslevelFilterValue = saleslevelFilterValue;
    }

    @Override
    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    @Override
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int getRateDeductionLevel() {
        return rateDeductionLevel;
    }

    public void setRateDeductionLevel(int rateDeductionLevel) {
        this.rateDeductionLevel = rateDeductionLevel;
    }

    @Override
    public String getRateDeductionLevelName() {
        return rateDeductionLevelName;
    }

    public void setRateDeductionLevelName(String rateDeductionLevelName) {
        this.rateDeductionLevelName = rateDeductionLevelName;
    }

    @Override
    public String getRateDeductionValue() {
        return rateDeductionValue;
    }

    public void setRateDeductionValue(String rateDeductionValue) {
        this.rateDeductionValue = rateDeductionValue;
    }

    @Override
    public String getRateDeductionView() {
        return rateDeductionView;
    }

    public void setRateDeductionView(String rateDeductionView) {
        this.rateDeductionView = rateDeductionView;
    }

    @Override
    public int getRateBasisValue() {
        return rateBasisValue;
    }

    public void setRateBasisValue(int rateBasisValue) {
        this.rateBasisValue = rateBasisValue;
    }

    @Override
    public String getRateBasisName() {
        return rateBasisName;
    }

    public void setRateBasisName(String rateBasisName) {
        this.rateBasisName = rateBasisName;
    }

    @Override
    public int getRateFrequencyValue() {
        return rateFrequencyValue;
    }

    public void setRateFrequencyValue(int rateFrequencyValue) {
        this.rateFrequencyValue = rateFrequencyValue;
    }

    @Override
    public String getRateFrequencyName() {
        return rateFrequencyName;
    }

    public void setRateFrequencyName(String rateFrequencyName) {
        this.rateFrequencyName = rateFrequencyName;
    }

    @Override
    public String getRatePeriodValue() {
        return ratePeriodValue;
    }

    public void setRatePeriodValue(String ratePeriodValue) {
        this.ratePeriodValue = ratePeriodValue;
    }

    @Override
    public List<List> getRateColumnList() {
        return CommonLogic.getInstance().getArrayListCloned(rateColumnList);
    }

    public void setRateRateColumnList(List<List> rateRateColumnList) {
        this.rateColumnList = CommonLogic.getInstance().getArrayListCloned(rateRateColumnList);
    }

    @Override
    public String getRateLevelName() {
        return rateLevelName;
    }

    public void setRateLevelName(String rateLevelName) {
        this.rateLevelName = rateLevelName;
    }

    /**
     * Generate button Validation
     *
     * @return
     */
    @Override
    public boolean isRateGenerateAllowed() {
        return this.getRateDeductionLevel() != 0 && StringUtils.isNotBlank(this.getRateDeductionValue())
                && this.getRateBasisValue() != 0 && this.getRateFrequencyValue() != 0
                && StringUtils.isNotBlank(this.getRatePeriodValue())
                && !GlobalConstants.getSelectOne().equals(this.getRatePeriodValue());
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public List<String[]> getSummaryfrequencyList() {
        return CommonLogic.getInstance().getArrayListCloned(summaryfrequencyList);
    }

    public void setSummaryfrequencyList(List<String[]> summaryfrequencyList) {
        this.summaryfrequencyList = CommonLogic.getInstance().getArrayListCloned(summaryfrequencyList);
    }

    @Override
    public String getSummarydemandview() {
        return summarydemandview;
    }

    public void setSummarydemandview(String summarydemandview) {
        this.summarydemandview = summarydemandview;
    }

    @Override
    public String getSummarydemandtoDate() {
        return summarydemandtoDate;
    }

    public void setSummarydemandtoDate(String summarydemandtoDate) {
        this.summarydemandtoDate = summarydemandtoDate;
    }

    @Override
    public String getSummarydemandfromDate() {
        return summarydemandfromDate;
    }

    public void setSummarydemandfromDate(String summarydemandfromDate) {
        this.summarydemandfromDate = summarydemandfromDate;
    }

    @Override
    public String getSummarydemandfrequency() {
        return summarydemandfrequency;
    }

    public void setSummarydemandfrequency(String summarydemandfrequency) {
        this.summarydemandfrequency = summarydemandfrequency;
    }

    @Override
    public Object getProcedureInputs(String feild) {
        return procedureInputs.get(feild);
    }

    @Override
    public void setProcedureInputs(String feild, Object value) {
        this.procedureInputs.put(feild, value);
    }

    @Override
    public boolean isFieldInput(String feild) {
        for (Map.Entry<String, Object> me : procedureInputs.entrySet()) {
            return me.getKey().equals(feild);
        }
        return getConstant();
    }

    private boolean getConstant() {
        return true;
    }

    @Override
    public List<String[]> getSalesVariables() {
        return CommonLogic.getInstance().getArrayListCloned(salesVariables);
    }

    public void setSalesVariables(List<String[]> salesVariables) {
        this.salesVariables = CommonLogic.getInstance().getArrayListCloned(salesVariables);
    }

    @Override
    public String[] getVariableVisibleColumns() {
        return CommonLogic.getInstance().getStringArrayCloned(variableVisibleColumns);
    }

    public void setVariableVisibleColumns(String[] variableVisibleColumns) {
        this.variableVisibleColumns = CommonLogic.getInstance().getStringArrayCloned(variableVisibleColumns);
    }

    @Override
    public String getInventoryreserveDate() {
        return inventoryreserveDate;
    }

    public void setInventoryreserveDate(String inventoryreserveDate) {
        this.inventoryreserveDate = inventoryreserveDate;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getRateBasis() {
        return rateBasis;
    }

    @Override
    public void setRateBasis(String rateBasis) {
        this.rateBasis = rateBasis;
    }

    @Override
    public String getRateFrequency() {
        return rateFrequency;
    }

    @Override
    public void setRateFrequency(String rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    @Override
    public String getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(String ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    @Override
    public Map<Integer, String> getSummarydemandLevel() {
        return summarydemandLevel;
    }

    @Override
    public void setSummarydemandLevel(Map<Integer, String> summarydemandLevel) {
        this.summarydemandLevel = summarydemandLevel;
    }

    @Override
    public int getSummarydemandMultipleLevel() {
        return summarydemandMultipleLevel;
    }

    @Override
    public void setSummarydemandMultipleLevel(int summarydemandMultipleLevel) {
        this.summarydemandMultipleLevel = summarydemandMultipleLevel;

    }

    @Override
    public String getInventoryDetails() {
        return inventoryDetails;
    }

    public void setInventoryDetails(String inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }

    @Override
    public String getSummaryviewType() {
        return summaryviewType;
    }

    public void setSummaryviewType(String summaryviewType) {
        this.summaryviewType = summaryviewType;
    }

    @Override
    public int getSummarylevelFilterNo() {
        return summarylevelFilterNo;
    }

    public void setSummarylevelFilterNo(int summarylevelFilterNo) {
        this.summarylevelFilterNo = summarylevelFilterNo;
    }

    @Override
    public String getSummarylevelFilterValue() {
        return summarylevelFilterValue;
    }

    public void setSummarylevelFilterValue(String summarylevelFilterValue) {
        this.summarylevelFilterValue = summarylevelFilterValue;
    }

    @Override
    public int getRateslevelFilterNo() {
        return rateslevelFilterNo;
    }

    public void setRateslevelFilterNo(int rateslevelFilterNo) {
        this.rateslevelFilterNo = rateslevelFilterNo;
    }

    @Override
    public TreeMap<String, Integer> getMasterSids() {
        return masterSids;
    }

    public void setMasterSids(TreeMap<String, Integer> masterSids) {
        this.masterSids = masterSids;
    }

    public String getInventoryOptionGroup() {
        return inventoryOptionGroup;
    }

    public void setInventoryOptionGroup(String inventoryOptionGroup) {
        this.inventoryOptionGroup = inventoryOptionGroup;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Integer getSessionId() {
        return sessionId;
    }

    @Override
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    // -----------------------------Hierarchy------------------------
    @Override
    public Map<Integer, String> getSaleshierarchy() {
        return saleshierarchy;
    }

    public void setSaleshierarchy(Map<Integer, String> saleshierarchy) {
        this.saleshierarchy = saleshierarchy;
    }

    @Override
    public Map<Integer, String> getRateshierarchy() {
        return rateshierarchy;
    }

    public void setRateshierarchy(Map<Integer, String> rateshierarchy) {
        this.rateshierarchy = rateshierarchy;
    }

    @Override
    public Map<Integer, String> getSummeryhierarchy() {
        return summeryhierarchy;
    }

    public void setSummeryhierarchy(Map<Integer, String> summeryhierarchy) {
        this.summeryhierarchy = summeryhierarchy;
    }

    public List<String> getExcelVisibleColumn() {
        return CommonLogic.getInstance().getArrayListCloned(excelVisibleColumn);
    }

    public void setExcelVisibleColumn(List<String> excelVisibleColumn) {
        this.excelVisibleColumn = CommonLogic.getInstance().getArrayListCloned(excelVisibleColumn);
    }

    public Object[] getExcelHierarchy() {
        return CommonLogic.getInstance().getObjectArrayCloned(excelHierarchy);
    }

    public void setExcelHierarchy(Object[] excelHierarchy) {
        this.excelHierarchy = CommonLogic.getInstance().getObjectArrayCloned(excelHierarchy);
    }

    public boolean isIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public String getSummaryFrequencyName() {
        return summaryFrequencyName;
    }

    public void setSummaryFrequencyName(String summaryFrequencyName) {
        this.summaryFrequencyName = summaryFrequencyName;
    }

    public String getSummaryStatusID() {
        return summaryStatusID;
    }

    public void setSummaryStatusID(String summaryStatusID) {
        this.summaryStatusID = summaryStatusID;
    }

    @Override
    public List<String> getSelectedAdjustmentTypeValues() {
        return CommonLogic.getInstance().getArrayListCloned(selectedAdjustmentTypeValues);
    }

    public void setSelectedAdjustmentTypeValues(List<String> selectedAdjustmentTypeValues) {
        this.selectedAdjustmentTypeValues = CommonLogic.getInstance().getArrayListCloned(selectedAdjustmentTypeValues);
    }

    @Override
    public int getSummaryvalueSid() {
        return summaryvalueSid;
    }

    public void setSummaryvalueSid(int summaryvalue) {
        this.summaryvalueSid = summaryvalue;
    }

    public List<String> getCustomerGroupList() {
        return CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    public void setCustomerGroupList(List<String> customerGroupList) {
        this.customerGroupList = CommonLogic.getInstance().getArrayListCloned(customerGroupList);
    }

    public List<String> getCustomerList() {
        return CommonLogic.getInstance().getArrayListCloned(customerList);
    }

    public void setCustomerList(List<String> customerList) {
        this.customerList = CommonLogic.getInstance().getArrayListCloned(customerList);
    }

    public boolean isTotalFlag() {
        return totalFlag;
    }

    public void setTotalFlag(boolean totalFlag) {
        this.totalFlag = totalFlag;
    }

    public String getInventoryDetailsDdlb() {
        return inventoryDetailsDdlb;
    }

    public void setInventoryDetailsDdlb(String inventoryDetailsDdlb) {
        this.inventoryDetailsDdlb = inventoryDetailsDdlb;
    }

    public String getBaseLinePrice() {
        return baseLinePrice;
    }

    public void setBaseLinePrice(String baseLinePrice) {
        this.baseLinePrice = baseLinePrice;
    }

    public String getAdjustedPrice() {
        return adjustedPrice;
    }

    public void setAdjustedPrice(String adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }

    public boolean isIsWorkFlow() {
        return isWorkFlow;
    }

    public void setIsWorkFlow(boolean isWorkFlow) {
        this.isWorkFlow = isWorkFlow;
    }

    @Override
    public List<String> getDetailamountFilter() {
        return detailamountFilter != null ? new ArrayList<>(detailamountFilter) : detailamountFilter;
    }

    public void setDetailamountFilter(List<String> detailamountFilter) {
        this.detailamountFilter = detailamountFilter != null ? new ArrayList<>(detailamountFilter) : detailamountFilter;
    }

    @Override
    public List<String> getInventorycolumnList() {
        return CommonLogic.getInstance().getArrayListCloned(inventorycolumnList);
    }

    public void setInventorycolumnList(List<String> inventorycolumnList) {
        this.inventorycolumnList = CommonLogic.getInstance().getArrayListCloned(inventorycolumnList);
    }

    public List<String> getSavedetailvariables() {
        return new ArrayList<>(savedetailvariables);
    }

    public void setSavedetailvariables(List<String> savedetailvariables) {
        this.savedetailvariables = savedetailvariables == null ? null : new ArrayList<>(savedetailvariables);
    }

    public Set<String> getCustomerGroupSidSet() {
        return new HashSet<>(customerGroupSidSet);
    }

    public void addCustomerGroupSidSet(String value) {
        customerGroupSidSet.add(value);
    }

    public void removeCustomerGroupSidSet(String value) {
        customerGroupSidSet.remove(value);
    }

    public void clearCustomerGroupSidSet() {
        customerGroupSidSet.clear();
    }

    public boolean isReloadFlag() {
        return reloadFlag;
    }

    public void setReloadFlag(boolean reloadFlag) {
        this.reloadFlag = reloadFlag;
    }

    public BalanceSummaryReportDataSelectionTab getBalanceSummaryDataSelectionTab() {
        return balanceSummaryDataSelectionTab;
    }

    public void setBalanceSummaryDataSelectionTab(BalanceSummaryReportDataSelectionTab balanceSummaryDataSelectionTab) {
        this.balanceSummaryDataSelectionTab = balanceSummaryDataSelectionTab;
    }

    @Override
    public List<String> getReturnsdatavariables() {
        return new ArrayList<>(returnsdatavariables);
    }

    public void setReturnsdatavariables(List<String> returnsdatavariables) {
        this.returnsdatavariables = new ArrayList<>(returnsdatavariables);
    }

    public List<List> getReturnReserveDataVariables() {
        return CommonLogic.getInstance().getArrayListCloned(returnReserveDataVariables);
    }

    public void setReturnReserveDataVariables(List<List> returnReserveDataVariables) {
        this.returnReserveDataVariables = CommonLogic.getInstance().getArrayListCloned(returnReserveDataVariables);
    }

    public Date getOriginalSaleLimiter() {
        return CommonLogic.getInstance().getDates(originalSaleLimiter);
    }

    public void setOriginalSaleLimiter(Date originalSaleLimiter) {
        this.originalSaleLimiter = CommonLogic.getInstance().getDates(originalSaleLimiter);
    }

    public int getRemoveClosedBatches() {
        return removeClosedBatches;
    }

    public void setRemoveClosedBatches(int removeClosedBatches) {
        this.removeClosedBatches = removeClosedBatches;
    }

    public int getExcludeBasedOnLoeDate() {
        return excludeBasedOnLoeDate;
    }

    public void setExcludeBasedOnLoeDate(int excludeBasedOnLoeDate) {
        this.excludeBasedOnLoeDate = excludeBasedOnLoeDate;
    }

    public boolean isReturnsReserveDataGenerated() {
        return returnReserveDataVariables != null && !returnReserveDataVariables.get(0).isEmpty();
    }

    public Map<Integer, String> getReturnReserveDataHierarchy() {
        return returnReserveDataHierarchy;
    }

    public void setReturnReserveDataHierarchy(Map<Integer, String> returnReserveDataHierarchy) {
        this.returnReserveDataHierarchy = returnReserveDataHierarchy;
    }

    public String getReturnReserveDataLevelName() {
        return returnReserveDataLevelName;
    }

    public void setReturnReserveDataLevelName(String returnReserveDataLevelName) {
        this.returnReserveDataLevelName = returnReserveDataLevelName;
    }

    public String getReturnReserveDataDeductionView() {
        return returnReserveDataDeductionView;
    }

    public void setReturnReserveDataDeductionView(String returnReserveDataDeductionView) {
        this.returnReserveDataDeductionView = returnReserveDataDeductionView;
    }

    public String getOriginalSaleLimiterVal() {
        return originalSaleLimiterVal;
    }

    public void setOriginalSaleLimiterVal(String originalSaleLimiterVal) {
        this.originalSaleLimiterVal = originalSaleLimiterVal;
    }

    @Override
    public boolean isCancelOverride() {
        return cancelOverride;
    }

    public void setCancelOverride(boolean cancelOverride) {
        this.cancelOverride = cancelOverride;
    }

    public String getReturnReserveDeductionValue() {
        return returnReserveDeductionValue;
    }

    public void setReturnReserveDeductionValue(String returnReserveDeductionValue) {
        this.returnReserveDeductionValue = returnReserveDeductionValue;
    }

    public int getReturnReserveDatalevelFilterNo() {
        return returnReserveDatalevelFilterNo;
    }

    public void setReturnReserveDatalevelFilterNo(int returnReserveDatalevelFilterNo) {
        this.returnReserveDatalevelFilterNo = returnReserveDatalevelFilterNo;
    }

    @Override
    public List<String> getReturnsdataSelectedvariables() {
        return CommonLogic.getInstance().getArrayListCloned(returnsdataSelectedvariables);
    }

    public void setReturnsdataSelectedvariables(List<String> returnsdataSelectedvariables) {
        this.returnsdataSelectedvariables = CommonLogic.getInstance().getArrayListCloned(returnsdataSelectedvariables);
    }

    public String getFromDateFilter() {
        return fromDateFilter;
    }

    public void setFromDateFilter(String fromDateFilter) {
        this.fromDateFilter = fromDateFilter;
    }

    public String getToDateFilter() {
        return toDateFilter;
    }

    public void setToDateFilter(String toDateFilter) {
        this.toDateFilter = toDateFilter;
    }

    public String getDataSelectionFromDate() {
        return dataSelectionFromDate;
    }

    public void setDataSelectionFromDate(String dataSelectionFromDate) {
        this.dataSelectionFromDate = dataSelectionFromDate;
    }

    public String getDataSelectionToDate() {
        return dataSelectionToDate;
    }

    public void setDataSelectionToDate(String dataSelectionToDate) {
        this.dataSelectionToDate = dataSelectionToDate;
    }

    public String getSummaryDeductionView() {
        return summaryDeductionView;
    }

    public void setSummaryDeductionView(String summaryDeductionView) {
        this.summaryDeductionView = summaryDeductionView;
    }

    @Override
    public int getRatesOverrideFlag() {
        return ratesOverrideFlag;
    }

    public void setRatesOverrideFlag(int ratesOverrideFlag) {
        this.ratesOverrideFlag = ratesOverrideFlag;
    }

    public int getSummaryPeriods() {
        return summaryPeriods;
    }

    public void setSummaryPeriods(int summaryPeriods) {
        this.summaryPeriods = summaryPeriods;
    }

    public List<Object> getPeriodSidList() {
        return new ArrayList(periodSidList);
    }

    public void setPeriodSidList(List<Object> periodSidList) {
        this.periodSidList = CommonLogic.getInstance().getArrayListCloned(periodSidList);
    }

    @Override
    public boolean isExcelDetials() {
        return excelDetials;
    }

    public void setExcelDetials(boolean excelDetials) {
        this.excelDetials = excelDetials;
    }
}
