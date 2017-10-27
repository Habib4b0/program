/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import com.stpl.ifs.util.CustomTableHeaderDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.hameed
 */
public class PVSelectionDTO extends ProjectionSelectionDTO {

    private int projectionNo;
    private String level;
    private String projectionPeriodOrder;
    private String discountLevel;
    private String fromDate;
    private String currentProjectionName;
    public List<Integer> projIdList = new ArrayList<>();
    private Map<Integer, String> projectionMap = new HashMap<>();
    private String variableCategory;
    private String variables;
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeaderPeriod = new CustomTableHeaderDTO();
    private List<String> discountNames = new ArrayList<>();
    private SessionDTO session = new SessionDTO();
    private Map<String, String> pivotHeaderMap = new HashMap<>();
    private int neededRecord;
    private int mayBeAdded;
    private boolean isFlag;
    private boolean discountFlag = false;
    private boolean valueFlag;
    public String productHierarchyNo;
    public String customerHierarchyNo;
    public String currentOrPrior;
    public boolean islevelFiler;
    public boolean isChildFlag;
    public boolean isPrior = false;
    public int currentProjId;
    public String screenName;
    private int currentProjectionID;
    int customCount = 0;
    private List<String> programCodeNameList = new ArrayList<>();
    private String toDate;
    private boolean isCustomerDdlb;
    private String pivotStartDate;

    private int customerRelationId;

    private String columnName = StringUtils.EMPTY;

    private int productRelationId;
    private List<String> pivotList = new ArrayList<>();
    /**
     * From Projection Variance DTO
     *
     */
    private Integer parent = 0;
    private String discoutName;
    private String discountIndicator;
    private List<String> periodHeaderList = new ArrayList<>();
    private List<String> pivotHeaderList = new ArrayList<>();
    private boolean isLevel;
    private boolean isNetSales;
    private String varIndicator = StringUtils.EMPTY;
    private Map<String, Object> headerMap = new HashMap<>();
    private String graphHeader = StringUtils.EMPTY;
    private boolean RPU = false;
    private String discountGroupName = StringUtils.EMPTY;
    private int excelFilterLevelNo=0;
    private boolean netExFactorySales;
    
    private boolean netExFactorySalesPerExFactory;
    /**
     * Combination variables
     */
    /**
     * The col current.
     */
    private boolean colValue;
    /**
     * The col variance.
     */
    private boolean colVariance;
    /**
     * The col percentage.
     */
    private boolean colPercentage;
    /**
     * The Var gts.
     */
    private boolean varGTS;

    /**
     * The Var gts.
     */
    private boolean varExFacSales;
    /**
     * The Var gts.
     */
    private boolean varDemandSales;
    /**
     * The Var gts.
     */
    private boolean varInvSales;
    /**
     * The Var gts.
     */
    private boolean varPerExFacSales;
    /**
     * The Var gts.
     */
    private boolean varPerDemandSales;
    /**
     * The Var gts.
     */
    private boolean varPerInvSales;

    /**
     * The Var contractsales.
     */
    private boolean varContractsales;
    /**
     * The Var contract units.
     */
    private boolean varContractUnits;
    /**
     * The var percentage.
     */
    private boolean varPercentage;
    /**
     * The var dis amount.
     */
    private boolean varDisAmount;
    /**
     * The var dis rate.
     */
    private boolean varDisRate;
    /**
     * The var net sales.
     */
    private boolean varNetSales;

    private boolean varRPU;

    private boolean varCOGC;

    private boolean varNetProfit;

    private int levelNo = 0;

    private boolean varExFacCustomer;
    private boolean varAdjDemand;
    private boolean varIwDetails;
    private boolean varPerExFacCustomer;
    private boolean varPerAdjDemand;
    private boolean varPerIwDetails;
    private String comparisonBasis;
    private boolean netSalesExFactory;
    private boolean discountPerExFactory;

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getProjectionNo() {
        return projectionNo;
    }

    public void setProjectionNo(int projectionNo) {
        this.projectionNo = projectionNo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProjectionPeriodOrder() {
        return projectionPeriodOrder;
    }

    public void setProjectionPeriodOrder(String projectionPeriodOrder) {
        this.projectionPeriodOrder = projectionPeriodOrder;
    }

    public String getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(String discountLevel) {
        this.discountLevel = discountLevel;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getCurrentProjectionName() {
        return currentProjectionName;
    }

    public void setCurrentProjectionName(String currentProjectionName) {
        this.currentProjectionName = currentProjectionName;
    }

    public List<Integer> getProjIdList() {
        Collections.sort(projIdList);
        return projIdList;
    }

    public void setProjIdList(List<Integer> projIdList) {
        this.projIdList = projIdList;
    }

    public Map<Integer, String> getProjectionMap() {
        return projectionMap;
    }

    public void setProjectionMap(Map<Integer, String> projectionMap) {
        this.projectionMap = projectionMap;
    }

    public String getVariableCategory() {
        return variableCategory;
    }

    public void setVariableCategory(String variableCategory) {
        this.variableCategory = variableCategory;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public CustomTableHeaderDTO getRightHeader() {
        return rightHeader;
    }

    public void setRightHeader(CustomTableHeaderDTO rightHeader) {
        this.rightHeader = rightHeader;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getDiscoutName() {
        return discoutName;
    }

    public void setDiscoutName(String discoutName) {
        this.discoutName = discoutName;
    }

    public String getDiscountIndicator() {
        return discountIndicator;
    }

    public void setDiscountIndicator(String discountIndicator) {
        this.discountIndicator = discountIndicator;
    }

    public List<String> getPeriodHeaderList() {
        return periodHeaderList;
    }

    public void setPeriodHeaderList(List<String> periodHeaderList) {
        this.periodHeaderList = periodHeaderList;
    }

    public boolean isColValue() {
        return colValue;
    }

    public void setColValue(boolean colValue) {
        this.colValue = colValue;
    }

    public boolean isColVariance() {
        return colVariance;
    }

    public void setColVariance(boolean colVariance) {
        this.colVariance = colVariance;
    }

    public boolean isColPercentage() {
        return colPercentage;
    }

    public void setColPercentage(boolean colPercentage) {
        this.colPercentage = colPercentage;
    }

    public boolean isVarGTS() {
        return varGTS;
    }

    public void setVarGTS(boolean varGTS) {
        this.varGTS = varGTS;
    }

    public boolean isVarContractsales() {
        return varContractsales;
    }

    public void setVarContractsales(boolean varContractsales) {
        this.varContractsales = varContractsales;
    }

    public boolean isVarContractUnits() {
        return varContractUnits;
    }

    public void setVarContractUnits(boolean varContractUnits) {
        this.varContractUnits = varContractUnits;
    }

    public boolean isVarPercentage() {
        return varPercentage;
    }

    public void setVarPercentage(boolean varPercentage) {
        this.varPercentage = varPercentage;
    }

    public boolean isVarDisAmount() {
        return varDisAmount;
    }

    public void setVarDisAmount(boolean varDisAmount) {
        this.varDisAmount = varDisAmount;
    }

    public boolean isVarDisRate() {
        return varDisRate;
    }

    public void setVarDisRate(boolean varDisRate) {
        this.varDisRate = varDisRate;
    }

    public boolean isVarNetSales() {
        return varNetSales;
    }

    public void setVarNetSales(boolean varNetSales) {
        this.varNetSales = varNetSales;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public CustomTableHeaderDTO getRightHeaderPeriod() {
        return rightHeaderPeriod;
    }

    public void setRightHeaderPeriod(CustomTableHeaderDTO rightHeaderPeriod) {
        this.rightHeaderPeriod = rightHeaderPeriod;
    }

    public int getNeededRecord() {
        return neededRecord;
    }

    public void setNeededRecord(int neededRecord) {
        this.neededRecord = neededRecord;
    }

    public int getMayBeAdded() {
        return mayBeAdded;
    }

    public void setMayBeAdded(int mayBeAdded) {
        this.mayBeAdded = mayBeAdded;
    }

    public boolean isIsFlag() {
        return isFlag;
    }

    public void setIsFlag(boolean isFlag) {
        this.isFlag = isFlag;
    }

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
    }

    public boolean isValueFlag() {
        return valueFlag;
    }

    public void setValueFlag(boolean valueFlag) {
        this.valueFlag = valueFlag;
    }

    public String getCurrentOrPrior() {
        return currentOrPrior;
    }

    public void setCurrentOrPrior(String currentOrPrior) {
        this.currentOrPrior = currentOrPrior;
    }

    public boolean isIslevelFiler() {
        return islevelFiler;
    }

    public void setIslevelFiler(boolean islevelFiler) {
        this.islevelFiler = islevelFiler;
    }

    public boolean isIsChildFlag() {
        return isChildFlag;
    }

    public void setIsChildFlag(boolean isChildFlag) {
        this.isChildFlag = isChildFlag;
    }

    public boolean isIsLevel() {
        return isLevel;
    }

    public void setIsLevel(boolean isLevel) {
        this.isLevel = isLevel;
    }

    public boolean isIsNetSales() {
        return isNetSales;
    }

    public void setIsNetSales(boolean isNetSales) {
        this.isNetSales = isNetSales;
    }

    public List<String> getDiscountNames() {
        return discountNames;
    }

    public void setDiscountNames(List<String> discountNames) {
        this.discountNames = discountNames;
    }

    public boolean isIsPrior() {
        return isPrior;
    }

    public void setIsPrior(boolean isPrior) {
        this.isPrior = isPrior;
    }

    public int getCurrentProjId() {
        return currentProjId;
    }

    public void setCurrentProjId(int currentProjId) {
        this.currentProjId = currentProjId;
    }

    public String getVarIndicator() {
        return varIndicator;
    }

    public void setVarIndicator(String varIndicator) {
        this.varIndicator = varIndicator;
    }

    public Map<String, Object> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, Object> headerMap) {
        this.headerMap = headerMap;
    }

    public String getGraphHeader() {
        return graphHeader;
    }

    public void setGraphHeader(String graphHeader) {
        this.graphHeader = graphHeader;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<String> getPivotList() {
        return pivotList;
    }

    public void setPivotList(List<String> pivotList) {
        this.pivotList = pivotList;
    }

    public boolean isIsCustomerDdlb() {
        return isCustomerDdlb;
    }

    public void setIsCustomerDdlb(boolean isCustomerDdlb) {
        this.isCustomerDdlb = isCustomerDdlb;
    }

    public int getCurrentProjectionID() {
        return currentProjectionID;
    }

    public void setCurrentProjectionID(int currentProjectionID) {
        this.currentProjectionID = currentProjectionID;
    }

    public int getCustomCount() {
        return customCount;
    }

    public void setCustomCount(int customCount) {
        this.customCount = customCount;
    }

    public List<String> getProgramCodeNameList() {
        return programCodeNameList;
    }

    public void setProgramCodeNameList(List<String> programCodeNameList) {
        this.programCodeNameList = programCodeNameList;
    }

    public int getCustomerRelationId() {
        return customerRelationId;
    }

    public void setCustomerRelationId(int customerRelationId) {
        this.customerRelationId = customerRelationId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getProductRelationId() {
        return productRelationId;
    }

    public void setProductRelationId(int productRelationId) {
        this.productRelationId = productRelationId;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean isVarExFacSales() {
        return varExFacSales;
    }

    public void setVarExFacSales(boolean varExFacSales) {
        this.varExFacSales = varExFacSales;
    }

    public boolean isVarDemandSales() {
        return varDemandSales;
    }

    public void setVarDemandSales(boolean varDemandSales) {
        this.varDemandSales = varDemandSales;
    }

    public boolean isVarInvSales() {
        return varInvSales;
    }

    public void setVarInvSales(boolean varInvSales) {
        this.varInvSales = varInvSales;
    }

    public boolean isVarPerExFacSales() {
        return varPerExFacSales;
    }

    public void setVarPerExFacSales(boolean varPerExFacSales) {
        this.varPerExFacSales = varPerExFacSales;
    }

    public boolean isVarPerDemandSales() {
        return varPerDemandSales;
    }

    public void setVarPerDemandSales(boolean varPerDemandSales) {
        this.varPerDemandSales = varPerDemandSales;
    }

    public boolean isVarPerInvSales() {
        return varPerInvSales;
    }

    public void setVarPerInvSales(boolean varPerInvSales) {
        this.varPerInvSales = varPerInvSales;
    }

    public boolean isVarRPU() {
        return varRPU;
    }

    public void setVarRPU(boolean varRPU) {
        this.varRPU = varRPU;
    }

    public boolean isVarCOGC() {
        return varCOGC;
    }

    public void setVarCOGC(boolean varCOGC) {
        this.varCOGC = varCOGC;
    }

    public boolean isVarNetProfit() {
        return varNetProfit;
    }

    public void setVarNetProfit(boolean varNetProfit) {
        this.varNetProfit = varNetProfit;
    }

    public boolean isRPU() {
        return RPU;
    }

    public void setRPU(boolean RPU) {
        this.RPU = RPU;
    }

    public String getDiscountGroupName() {
        return discountGroupName;
    }

    public void setDiscountGroupName(String discountGroupName) {
        this.discountGroupName = discountGroupName;
    }

    public String getProductHierarchyNo() {
        return productHierarchyNo;
    }

    public void setProductHierarchyNo(String productHierarchyNo) {
        this.productHierarchyNo = productHierarchyNo;
    }

    public String getCustomerHierarchyNo() {
        return customerHierarchyNo;
    }

    public void setCustomerHierarchyNo(String customerHierarchyNo) {
        this.customerHierarchyNo = customerHierarchyNo;
    }

    public boolean isVarExFacCustomer() {
        return varExFacCustomer;
    }

    public void setVarExFacCustomer(boolean varExFacCustomer) {
        this.varExFacCustomer = varExFacCustomer;
    }

    public boolean isVarAdjDemand() {
        return varAdjDemand;
    }

    public void setVarAdjDemand(boolean varAdjDemand) {
        this.varAdjDemand = varAdjDemand;
    }

    public boolean isVarIwDetails() {
        return varIwDetails;
    }

    public void setVarIwDetails(boolean varIwDetails) {
        this.varIwDetails = varIwDetails;
    }

    public boolean isVarPerExFacCustomer() {
        return varPerExFacCustomer;
    }

    public void setVarPerExFacCustomer(boolean varPerExFacCustomer) {
        this.varPerExFacCustomer = varPerExFacCustomer;
    }

    public boolean isVarPerAdjDemand() {
        return varPerAdjDemand;
    }

    public void setVarPerAdjDemand(boolean varPerAdjDemand) {
        this.varPerAdjDemand = varPerAdjDemand;
    }

    public boolean isVarPerIwDetails() {
        return varPerIwDetails;
    }

    public void setVarPerIwDetails(boolean varPerIwDetails) {
        this.varPerIwDetails = varPerIwDetails;
    }

    public Map<String, String> getPivotHeaderMap() {
        return pivotHeaderMap;
    }

    public void setPivotHeaderMap(Map<String, String> pivotHeaderMap) {
        this.pivotHeaderMap = pivotHeaderMap;
    }

    public List<String> getPivotHeaderList() {
        return pivotHeaderList;
    }

    public void setPivotHeaderList(List<String> pivotHeaderList) {
        this.pivotHeaderList = pivotHeaderList;
    }

    public String getPivotStartDate() {
        return pivotStartDate;
    }

    public void setPivotStartDate(String pivotStartDate) {
        this.pivotStartDate = pivotStartDate;
    }

    public int getExcelFilterLevelNo() {
        return excelFilterLevelNo;
    }

    public void setExcelFilterLevelNo(int excelFilterLevelNo) {
        this.excelFilterLevelNo = excelFilterLevelNo;
    }

    public String getComparisonBasis() {
        return comparisonBasis;
    }

    public void setComparisonBasis(String comparisonBasis) {
        this.comparisonBasis = comparisonBasis;
    }

    public boolean isNetSalesExFactory() {
        return netSalesExFactory;
    }

    public void setNetSalesExFactory(boolean netSalesExFactory) {
        this.netSalesExFactory = netSalesExFactory;
    }

    public boolean isDiscountPerExFactory() {
        return discountPerExFactory;
    }

    public void setDiscountPerExFactory(boolean discountPerExFactory) {
        this.discountPerExFactory = discountPerExFactory;
    }

    public boolean isNetExFactorySales() {
        return netExFactorySales;
    }

    public void setNetExFactorySales(boolean netExFactorySales) {
        this.netExFactorySales = netExFactorySales;
    }

    public boolean isNetExFactorySalesPerExFactory() {
        return netExFactorySalesPerExFactory;
    }

    public void setNetExFactorySalesPerExFactory(boolean netExFactorySalesPerExFactory) {
        this.netExFactorySalesPerExFactory = netExFactorySalesPerExFactory;
    }

    }
