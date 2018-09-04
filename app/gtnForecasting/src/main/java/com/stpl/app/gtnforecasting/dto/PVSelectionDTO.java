/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
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
    private String discountLevelName;
    private String fromDate;
    private String currentProjectionName;
    private List<Integer> projIdList = new ArrayList<>();
    private Map<Integer, String> projectionMap = new HashMap<>();
    private String variableCategory;
    private String variables;
    private CustomTableHeaderDTO rightHeader = new CustomTableHeaderDTO();
    private CustomTableHeaderDTO rightHeaderPeriod = new CustomTableHeaderDTO();
    private List<String> discountNames = new ArrayList<>();
    private SessionDTO session = new SessionDTO();
    private Map<String, Object> selection = new HashMap<>();
    private int neededRecord;
    private int mayBeAdded;
    private boolean isFlag;
    private boolean discountFlag = false;
    private boolean valueFlag;
    protected String productHierarchyNo;
    protected String customerHierarchyNo;
    protected String deductionHierarchyNo;
    protected String currentOrPrior;
    protected boolean islevelFiler;
    protected boolean isChildFlag;
    protected boolean isPrior = false;
    protected int currentProjId;
    protected String screenName;
    private int currentProjectionID;
    protected int customCount = 0;
    private List<String> programCodeNameList = new ArrayList<>();
    private String toDate;
    private boolean isCustomerDdlb;
    private String mandatedView;

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
    private boolean isLevel;
    private boolean isNetSales;
    private String varIndicator = StringUtils.EMPTY;
    private Map<String, Object> headerMap = new HashMap<>();
    private String graphHeader = StringUtils.EMPTY;
    private boolean rpu = false;
    private String discountGroupName = StringUtils.EMPTY;

    private Integer groupParent = 0;

    private String salesInclusion = StringUtils.EMPTY;
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

    private boolean netSalesExFactory;

    private boolean discountPerExFactory;

    private boolean netExFactorySales;

    private boolean netExFactorySalesPerExFactory;

    private int levelNo = 0;

    private Map<Integer, List<Object>> yearwiseColumns = new HashMap<>();
    private String comparisonBasis;
    private Map<String, Integer> discountNameMap = new HashMap<>();
    private boolean conversionNeeded = false;

    public PVSelectionDTO() {
        super();
    }

    @Override
    public int getLevelNo() {
        return levelNo;
    }

    @Override
    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getProjectionNo() {
        return projectionNo;
    }

    public void setProjectionNo(int projectionNo) {
        this.projectionNo = projectionNo;
    }

    @Override
    public String getLevel() {
        return level;
    }

    @Override
    public void setLevel(String level) {
        this.level = level;
    }

    public String getProjectionPeriodOrder() {
        return projectionPeriodOrder;
    }

    public void setProjectionPeriodOrder(String projectionPeriodOrder) {
        this.projectionPeriodOrder = projectionPeriodOrder;
    }

    @Override
    public String getDiscountLevel() {
        return discountLevel;
    }

    @Override
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
        return projIdList == null ? projIdList : new ArrayList<>(projIdList);
    }

    public void setProjIdList(List<Integer> projIdList) {
        this.projIdList = projIdList == null ? projIdList : new ArrayList<>(projIdList);
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

    @Override
    public Integer getParent() {
        return parent;
    }

    @Override
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
        return periodHeaderList == null ? periodHeaderList : new ArrayList<>(periodHeaderList);
    }

    public void setPeriodHeaderList(List<String> periodHeaderList) {
        this.periodHeaderList = periodHeaderList == null ? periodHeaderList : new ArrayList<>(periodHeaderList);
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

    public Map<String, Object> getSelection() {
        return selection;
    }

    public void setSelection(Map<String, Object> selection) {
        this.selection = selection;
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
        return discountNames == null ? discountNames : new ArrayList<>(discountNames);
    }

    public void setDiscountNames(List<String> discountNames) {
        this.discountNames = discountNames == null ? discountNames : new ArrayList<>(discountNames);
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

    @Override
    public String getScreenName() {
        return screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<String> getPivotList() {
        return pivotList == null ? pivotList : new ArrayList<>(pivotList);
    }

    public void setPivotList(List<String> pivotList) {
        this.pivotList = pivotList == null ? pivotList : new ArrayList<>(pivotList);
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
        return programCodeNameList == null ? programCodeNameList : new ArrayList<>(programCodeNameList);
    }

    public void setProgramCodeNameList(List<String> programCodeNameList) {
        this.programCodeNameList = programCodeNameList == null ? programCodeNameList : new ArrayList<>(programCodeNameList);
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
        return rpu;
    }

    public void setRPU(boolean rPU) {
        this.rpu = rPU;
    }

    public String getDiscountGroupName() {
        return discountGroupName;
    }

    public void setDiscountGroupName(String discountGroupName) {
        this.discountGroupName = discountGroupName;
    }

    public Map<Integer, List<Object>> getYearwiseColumns() {
        return yearwiseColumns;
    }

    public void setYearwiseColumns(Map<Integer, List<Object>> yearwiseColumns) {
        this.yearwiseColumns = yearwiseColumns;
    }

    public Integer getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(Integer groupParent) {
        this.groupParent = groupParent;
    }

    public String getMandatedView() {
        return mandatedView;
    }

    public void setMandatedView(String mandatedView) {
        this.mandatedView = mandatedView;
    }

    public boolean isNetSalesExFactory() {
        return netSalesExFactory;
    }

    public void setNetSalesExFactory(boolean netSalesExFactory) {
        this.netSalesExFactory = netSalesExFactory;
    }

    public String getComparisonBasis() {
        return comparisonBasis;
    }

    public void setComparisonBasis(String comparisonBasis) {
        this.comparisonBasis = comparisonBasis;
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

    @Override
    public String getDeductionHierarchyNo() {
        return deductionHierarchyNo;
    }

    @Override
    public void setDeductionHierarchyNo(String deductionHierarchyNo) {
        this.deductionHierarchyNo = deductionHierarchyNo;
    }

    public String getSalesInclusion() {
        return salesInclusion;
    }

    public void setSalesInclusion(String salesInclusion) {
        this.salesInclusion = salesInclusion;
    }

    public String getDiscountLevelName() {
        return discountLevelName;
    }

    public void setDiscountLevelName(String discountLevelName) {
        this.discountLevelName = discountLevelName;
    }
    
       public Map<String, Integer> getDiscountNameMap() {
        return discountNameMap;
    }

    public void setDiscountNameMap(Map<String, Integer> discountNameMap) {
        this.discountNameMap = discountNameMap;
    }
    
    public void addDiscountNameMap(String field, Integer value) {
        this.discountNameMap.put(field, value);
    }

    public boolean isConversionNeeded() {
        return conversionNeeded;
    }

    public void setConversionNeeded(boolean conversionNeeded) {
        this.conversionNeeded = conversionNeeded;
    }
    
}
