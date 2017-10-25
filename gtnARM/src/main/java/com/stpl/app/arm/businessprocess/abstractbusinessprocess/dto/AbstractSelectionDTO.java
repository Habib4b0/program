
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto;

import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.dataselection.dto.DataSelectionDTO;
import com.stpl.app.arm.dataselection.ui.form.BalanceSummaryReportDataSelectionTab;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.serviceUtils.ConstantsUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Karthikeyan.Subraman
 */
public abstract class AbstractSelectionDTO implements SelectionDTO {

    /*----------------------------------Variables used in Summary----------------------------------------------------*/
    private int summary_frequency = 0;
    private List<String[]> summary_variables;
    private List<String[]> summary_deductionVariables;
    private List<String> summary_columnList;
    private List<String> inventory_columnList;
    private List<String> summary_glList;
    private int summary_deductionLevel = 0;
    private String summary_deductionLevelDes = StringUtils.EMPTY;
    private String summary_month = StringUtils.EMPTY;
    private String summary_deductionValues = StringUtils.EMPTY;
    private String summary_demand_view = StringUtils.EMPTY;
    private String summary_demand_toDate = StringUtils.EMPTY;
    private String summary_demand_fromDate = StringUtils.EMPTY;
    private String summary_demand_frequency = StringUtils.EMPTY;
    private Map<Integer, String> summary_demand_Level;
    private int summary_demand_MultipleLevel;
    private int summary_levelFilterNo;
    private String summary_levelFilterValue;
    private List<Integer> summary_deductionVariableIds;
    private String summary_viewType = StringUtils.EMPTY;
    private int projectionMasterSid = 0;
    private int companyMasterSid = 0;

    private DataSelectionDTO dataSelectionDTO;

    private SessionDTO sessionDTO;

    public List<String> selectedAdjustmentType;

    public String detail_Level = StringUtils.EMPTY;

    public List<String> detail_reserveAcount;
    
    public List<String> detail_amountFilter;

    public List<String> detail_variables;
    
    public List<String> save_detail_variables;

    public String summary_glDate = StringUtils.EMPTY;
    public String summary_fromDate = StringUtils.EMPTY;
    public String summary_toDate = StringUtils.EMPTY;

    private final Map<String, Object> procedureInputs = new HashMap<>();
    String fromDate;
    String toDate;

    private int summary_valueSid = 0;
    private String deductionLevelValue = StringUtils.EMPTY;
    private String sales_levelFilterValue = StringUtils.EMPTY;
    private String dateType = StringUtils.EMPTY;
    private String price = StringUtils.EMPTY;

    List<String[]> sales_variables;
    private String[] variableVisibleColumns;
    public String inventoryDetailsDdlb = StringUtils.EMPTY;
    public String baseLinePrice = StringUtils.EMPTY;
    public String adjustedPrice = StringUtils.EMPTY;
    private String inventory_reserveDate = StringUtils.EMPTY;
    private String inventory_Details = StringUtils.EMPTY;
    private List<String> excelVisibleColumn;
    private boolean isMultiple;
    private List<String> selectedAdjustmentTypeValues = new ArrayList<>();
    private List<String> customerGroupList = new ArrayList<>();
    private List<String> customerList = new ArrayList<>();

    /*----------------------------------RATES----------------------------------------------------*/
    private int rate_DeductionLevel = 0;

    private String rate_DeductionLevelName = StringUtils.EMPTY;

    private String rate_DeductionValue = StringUtils.EMPTY;

    private String rate_DeductionView = StringUtils.EMPTY;

    private int rate_Basis = 0;

    private String rate_BasisName = StringUtils.EMPTY;

    private int rate_Frequency = 0;

    private String rate_FrequencyName = StringUtils.EMPTY;

    private String rate_Period = StringUtils.EMPTY;

    private List<List> rate_ColumnList = new ArrayList<>();

    private String rate_LevelName = StringUtils.EMPTY;

    private String moduleName = StringUtils.EMPTY;

    private String tableName = StringUtils.EMPTY;

    private int rates_levelFilterNo = 0;
    //-----------------------------Hierarchy------------------------
    private Map<Integer, String> sales_hierarchy;
    private Map<Integer, String> rates_hierarchy;
    private Map<Integer, String> summery_hierarchy;

    /*----------------------------------RATES----------------------------------------------------*/
    private int levelNo;

    private String status;

    private List<String[]> summary_frequencyList;

    private String Summary_FrequencyName = StringUtils.EMPTY;

    private String Summary_StatusID = StringUtils.EMPTY;

    private String rateBasis = StringUtils.EMPTY;

    private String rateFrequency = StringUtils.EMPTY;

    private String ratePeriod = StringUtils.EMPTY;

    private String inventoryOptionGroup = StringUtils.EMPTY;

    private Object[] excelHierarchy;

    TreeMap<String, Integer> masterSids;

    private Integer userId;

    private Integer sessionId;
    
    private boolean totalFlag;
    
    boolean isWorkFlow = false;

    public Set<String> customerGroupSidSet = new HashSet<>();
    
    public boolean reloadFlag = false;
    
    public BalanceSummaryReportDataSelectionTab balanceSummaryDataSelectionTab;

    public int getSummary_frequency() {
        return summary_frequency;
    }

    public void setSummary_frequency(int summary_frequency) {
        this.summary_frequency = summary_frequency;
    }

    public List<String[]> getSummary_variables() {
        return summary_variables;
    }

    public void setSummary_variables(List<String[]> summary_variables) {
        this.summary_variables = summary_variables;
    }

    public List<String[]> getSummary_deductionVariables() {
        return summary_deductionVariables;
    }

    public void setSummary_deductionVariables(List<String[]> summary_deductionVariables) {
        this.summary_deductionVariables = summary_deductionVariables;
    }

    public List<String> getSummary_columnList() {
        return summary_columnList;
    }

    public void setSummary_columnList(List<String> summary_columnList) {
        this.summary_columnList = summary_columnList;
    }

    public int getSummary_deductionLevel() {
        return summary_deductionLevel;
    }

    public void setSummary_deductionLevel(int summary_deductionLevel) {
        this.summary_deductionLevel = summary_deductionLevel;
    }

    public String getSummary_deductionLevelDes() {
        return summary_deductionLevelDes;
    }

    public void setSummary_deductionLevelDes(String summary_deductionLevelDes) {
        this.summary_deductionLevelDes = summary_deductionLevelDes;
    }

    public String getSummary_month() {
        return summary_month;
    }

    public void setSummary_month(String summary_month) {
        this.summary_month = summary_month;
    }

    public int getProjectionMasterSid() {
        return projectionMasterSid;
    }

    public void setProjectionMasterSid(int projectionMasterSid) {
        this.projectionMasterSid = projectionMasterSid;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getSummary_deductionValues() {
        return summary_deductionValues;
    }

    public void setSummary_deductionValues(String summary_deductionValues) {
        this.summary_deductionValues = summary_deductionValues;
    }

    public DataSelectionDTO getDataSelectionDTO() {
        return dataSelectionDTO;
    }

    public void setDataSelectionDTO(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public List<String> getSelectedAdjustmentType() {
        return selectedAdjustmentType;
    }

    public void setSelectedAdjustmentType(List<String> selectedAdjustmentType) {
        this.selectedAdjustmentType = selectedAdjustmentType;
    }

    public String getDetail_Level() {
        return detail_Level;
    }

    public void setDetail_Level(String detail_Level) {
        this.detail_Level = detail_Level;
    }

    public List<String> getDetail_reserveAcount() {
        return detail_reserveAcount;
    }

    public void setDetail_reserveAcount(List<String> detail_reserveAcount) {
        this.detail_reserveAcount = detail_reserveAcount;
    }

    public List<String> getDetail_variables() {
        return detail_variables;
    }

    public void setDetail_variables(List<String> detail_variables) {
        this.detail_variables = detail_variables;
    }

    public String getSummary_glDate() {
        return summary_glDate;
    }

    public void setSummary_glDate(String summary_glDate) {
        this.summary_glDate = summary_glDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDeductionLevelValue() {
        return deductionLevelValue;
    }

    public void setDeductionLevelValue(String deductionLevelValue) {
        this.deductionLevelValue = deductionLevelValue;
    }

    @Override
    public String getSales_levelFilterValue() {
        return sales_levelFilterValue;
    }

    public void setSales_levelFilterValue(String sales_levelFilterValue) {
        this.sales_levelFilterValue = sales_levelFilterValue;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRate_DeductionLevel() {
        return rate_DeductionLevel;
    }

    public void setRate_DeductionLevel(int rate_DeductionLevel) {
        this.rate_DeductionLevel = rate_DeductionLevel;
    }

    public String getRate_DeductionLevelName() {
        return rate_DeductionLevelName;
    }

    public void setRate_DeductionLevelName(String rate_DeductionLevelName) {
        this.rate_DeductionLevelName = rate_DeductionLevelName;
    }

    public String getRate_DeductionValue() {
        return rate_DeductionValue;
    }

    public void setRate_DeductionValue(String rate_DeductionValue) {
        this.rate_DeductionValue = rate_DeductionValue;
    }

    public String getRate_DeductionView() {
        return rate_DeductionView;
    }

    public void setRate_DeductionView(String rate_DeductionView) {
        this.rate_DeductionView = rate_DeductionView;
    }

    public int getRate_Basis() {
        return rate_Basis;
    }

    public void setRate_Basis(int rate_Basis) {
        this.rate_Basis = rate_Basis;
    }

    public String getRate_BasisName() {
        return rate_BasisName;
    }

    public void setRate_BasisName(String rate_BasisName) {
        this.rate_BasisName = rate_BasisName;
    }

    public int getRate_Frequency() {
        return rate_Frequency;
    }

    public void setRate_Frequency(int rate_Frequency) {
        this.rate_Frequency = rate_Frequency;
    }

    public String getRate_FrequencyName() {
        return rate_FrequencyName;
    }

    public void setRate_FrequencyName(String rate_FrequencyName) {
        this.rate_FrequencyName = rate_FrequencyName;
    }

    public String getRate_Period() {
        return rate_Period;
    }

    public void setRate_Period(String rate_Period) {
        this.rate_Period = rate_Period;
    }

    public List<List> getRate_ColumnList() {
        return rate_ColumnList;
    }

    public void setRate_RateColumnList(List<List> rate_RateColumnList) {
        this.rate_ColumnList = rate_RateColumnList;
    }

    public String getRate_LevelName() {
        return rate_LevelName;
    }

    public void setRate_LevelName(String rate_LevelName) {
        this.rate_LevelName = rate_LevelName;
    }

    /**
     * Generate button Validation
     *
     * @return
     */
    public boolean isRateGenerateAllowed() {
        return this.getRate_DeductionLevel() != 0 && StringUtils.isNotBlank(this.getRate_DeductionValue())
                && this.getRate_Basis() != 0 && this.getRate_Frequency() != 0 && StringUtils.isNotBlank(this.getRate_Period())
                && !ConstantsUtils.SELECT_ONE.equals(this.getRate_Period());
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String[]> getSummary_frequencyList() {
        return summary_frequencyList;
    }

    public void setSummary_frequencyList(List<String[]> summary_frequencyList) {
        this.summary_frequencyList = summary_frequencyList;
    }

    public String getSummary_demand_view() {
        return summary_demand_view;
    }

    public void setSummary_demand_view(String summary_demand_view) {
        this.summary_demand_view = summary_demand_view;
    }

    public String getSummary_demand_toDate() {
        return summary_demand_toDate;
    }

    public void setSummary_demand_toDate(String summary_demand_toDate) {
        this.summary_demand_toDate = summary_demand_toDate;
    }

    public String getSummary_demand_fromDate() {
        return summary_demand_fromDate;
    }

    public void setSummary_demand_fromDate(String summary_demand_fromDate) {
        this.summary_demand_fromDate = summary_demand_fromDate;
    }

    public String getSummary_demand_frequency() {
        return summary_demand_frequency;
    }

    public void setSummary_demand_frequency(String summary_demand_frequency) {
        this.summary_demand_frequency = summary_demand_frequency;
    }

    public Object getProcedureInputs(String feild) {
        return procedureInputs.get(feild);
    }

    public void setProcedureInputs(String feild, Object value) {
        this.procedureInputs.put(feild, value);
    }

    public boolean isFieldInput(String feild) {
        for (Map.Entry<String, Object> me : procedureInputs.entrySet()) {
            if (me.getKey().equals(feild)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public List<String[]> getSales_variables() {
        return sales_variables;
    }

    public void setSales_variables(List<String[]> sales_variables) {
        this.sales_variables = sales_variables;
    }

    public String[] getVariableVisibleColumns() {
        return variableVisibleColumns;
    }

    public void setVariableVisibleColumns(String[] variableVisibleColumns) {
        this.variableVisibleColumns = variableVisibleColumns;
    }

    public String getInventory_reserveDate() {
        return inventory_reserveDate;
    }

    public void setInventory_reserveDate(String inventory_reserveDate) {
        this.inventory_reserveDate = inventory_reserveDate;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRateBasis() {
        return rateBasis;
    }

    public void setRateBasis(String rateBasis) {
        this.rateBasis = rateBasis;
    }

    public String getRateFrequency() {
        return rateFrequency;
    }

    public void setRateFrequency(String rateFrequency) {
        this.rateFrequency = rateFrequency;
    }

    public String getRatePeriod() {
        return ratePeriod;
    }

    public void setRatePeriod(String ratePeriod) {
        this.ratePeriod = ratePeriod;
    }

    public Map<Integer, String> getSummary_demand_Level() {
        return summary_demand_Level;
    }

    public void setSummary_demand_Level(Map<Integer, String> summary_demand_Level) {
        this.summary_demand_Level = summary_demand_Level;
    }

    public int getSummary_demand_MultipleLevel() {
        return summary_demand_MultipleLevel;
    }

    public void setSummary_demand_MultipleLevel(int summary_demand_MultipleLevel) {
        this.summary_demand_MultipleLevel = summary_demand_MultipleLevel;

    }

    public String getInventory_Details() {
        return inventory_Details;
    }

    public void setInventory_Details(String inventory_Details) {
        this.inventory_Details = inventory_Details;
    }

    @Override
    public String getSummary_viewType() {
        return summary_viewType;
    }

    public void setSummary_viewType(String summary_viewType) {
        this.summary_viewType = summary_viewType;
    }

    public int getSummary_levelFilterNo() {
        return summary_levelFilterNo;
    }

    public void setSummary_levelFilterNo(int summary_levelFilterNo) {
        this.summary_levelFilterNo = summary_levelFilterNo;
    }

    public String getSummary_levelFilterValue() {
        return summary_levelFilterValue;
    }

    public void setSummary_levelFilterValue(String summary_levelFilterValue) {
        this.summary_levelFilterValue = summary_levelFilterValue;
    }

    public int getRates_levelFilterNo() {
        return rates_levelFilterNo;
    }

    public void setRates_levelFilterNo(int rates_levelFilterNo) {
        this.rates_levelFilterNo = rates_levelFilterNo;
    }

    public TreeMap<String, Integer> getMasterSids() {
        return masterSids;
    }

    public void setMasterSids(TreeMap<String, Integer> masterSids) {
        this.masterSids = masterSids;
    }

    public List<String> getSummary_glList() {
        return summary_glList;
    }

    public void setSummary_glList(List<String> summary_glList) {
        this.summary_glList = summary_glList;
    }

    public String getInventoryOptionGroup() {
        return inventoryOptionGroup;
    }

    public void setInventoryOptionGroup(String inventoryOptionGroup) {
        this.inventoryOptionGroup = inventoryOptionGroup;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public List<Integer> getSummary_deductionVariableIds() {
        return summary_deductionVariableIds;
    }

    public void setSummary_deductionVariableIds(List<Integer> summary_deductionVariableIds) {
        this.summary_deductionVariableIds = summary_deductionVariableIds;
    }

    //-----------------------------Hierarchy------------------------
    public Map<Integer, String> getSales_hierarchy() {
        return sales_hierarchy;
    }

    public void setSales_hierarchy(Map<Integer, String> sales_hierarchy) {
        this.sales_hierarchy = sales_hierarchy;
    }

    public Map<Integer, String> getRates_hierarchy() {
        return rates_hierarchy;
    }

    public void setRates_hierarchy(Map<Integer, String> rates_hierarchy) {
        this.rates_hierarchy = rates_hierarchy;
    }

    public Map<Integer, String> getSummery_hierarchy() {
        return summery_hierarchy;
    }

    public void setSummery_hierarchy(Map<Integer, String> summery_hierarchy) {
        this.summery_hierarchy = summery_hierarchy;
    }

    public List<String> getExcelVisibleColumn() {
        return excelVisibleColumn;
    }

    public void setExcelVisibleColumn(List<String> excelVisibleColumn) {
        this.excelVisibleColumn = excelVisibleColumn;
    }

    public Object[] getExcelHierarchy() {
        return excelHierarchy;
    }

    public void setExcelHierarchy(Object[] excelHierarchy) {
        this.excelHierarchy = excelHierarchy;
    }

    public boolean isIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public String getSummary_FrequencyName() {
        return Summary_FrequencyName;
    }

    public void setSummary_FrequencyName(String Summary_FrequencyName) {
        this.Summary_FrequencyName = Summary_FrequencyName;
    }

    public String getSummary_StatusID() {
        return Summary_StatusID;
    }

    public void setSummary_StatusID(String Summary_StatusID) {
        this.Summary_StatusID = Summary_StatusID;
    }

    public List<String> getSelectedAdjustmentTypeValues() {
        return selectedAdjustmentTypeValues;
    }

    public void setSelectedAdjustmentTypeValues(List<String> selectedAdjustmentTypeValues) {
        this.selectedAdjustmentTypeValues = selectedAdjustmentTypeValues;
    }

    @Override
    public int getSummary_valueSid() {
        return summary_valueSid;
    }

    public void setSummary_valueSid(int summary_value) {
        this.summary_valueSid = summary_value;
    }

    public List<String> getCustomerGroupList() {
        return customerGroupList;
    }

    public void setCustomerGroupList(List<String> customerGroupList) {
        this.customerGroupList = customerGroupList;
    }

    public List<String> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<String> customerList) {
        this.customerList = customerList;
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

    public List<String> getDetail_amountFilter() {
        return detail_amountFilter;
    }

    public void setDetail_amountFilter(List<String> detail_amountFilter) {
        this.detail_amountFilter = detail_amountFilter;
    }

    public List<String> getInventory_columnList() {
        return inventory_columnList;
    }

    public void setInventory_columnList(List<String> inventory_columnList) {
        this.inventory_columnList = inventory_columnList;
    }

    public List<String> getSave_detail_variables() {
        return save_detail_variables;
    }

    public void setSave_detail_variables(List<String> save_detail_variables) {
        this.save_detail_variables = save_detail_variables;
    }
    
    public Set<String> getCustomerGroupSidSet() {
        return customerGroupSidSet;
}

    public void addCustomerGroupSidSet(String value) {
        customerGroupSidSet.add(value);
    }
    
     public void removeCustomerGroupSidSet(String value) {
        customerGroupSidSet.remove(value);
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
    
}