
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;

/**
 *
 * @author abhiram
 */
public class ProjectionSelectionDTO {

    int projectionId;
    int userId;
    int sessionId;
    int levelNo;
    int treeLevelNo;
    int frequencyDivision;
    int historyStartYear;
    int historyStartMonth;
    int historyStartDay;
    int historyStartPeriod;
    int historyEndDay;
    int historyEndPeriod;
    int historyEndMonth;
    int historyEndYear;
    int forecastStartDay;
    int forecastStartPeriod;
    int forecastStartMonth;
    int forecastStartYear;
    int forecastEndDay;
    int forecastEndPeriod;
    int projectionStartDay;
    int projectionStartPeriod;
    int projectionStartMonth;
    int projectionStartYear;
    int projectionEndDay;
    int projectionEndPeriod;
    int currentYear;
    int startYear;
    int endYear;
    int startPeriod;
    int endPeriod;
    int currentPeriod;
    int startMonth;
    int endMonth;
    int startDay;
    int endDay;
    String salesOrUnit;
    String actualsOrProjections;
    String levelValue = StringUtils.EMPTY;
    String parentNode = StringUtils.EMPTY;
    String year;
    String sales;
    boolean isTotal;
    boolean isProjectionTotal;
    private String hierarchyNo;
    private String reHierarchyNo = StringUtils.EMPTY;
    private String productHierarchyNo = "";
    private String deductionHierarchyNo = "";
    private String customerHierarchyNo = "";
    private String hierarchyIndicator = "";
    List<List<String>> discountList = new ArrayList<>();
    List<String> discountNameCFF = new ArrayList<>();
    String view;
    String frequency;
    String projectionOrder;
    String history;
    String projection;
    String pivotView;
    String group;
    int customId;
    int historyNum;
    int projectionNum;
    List<String> levelValueList = new ArrayList<>();
    List<String> parentNodeList = new ArrayList<>();
    Date startDate;
    Date toDates;
    List<String> columns = new ArrayList<>();
    List<String> periodList = new ArrayList<>();
    List<String> discountProgramsList = new ArrayList<>();
    Map<String, String> periodListMap = new HashMap<>();
    boolean isFilter;
    boolean isCustomHierarchy;
    int discountIndex = 0;
    boolean isCount;
    boolean isExandCollapse;
    boolean ppa;
    int total;
    private int filterLevelNo;
    private String filterHierarchyNo = "";
    private int customerLevelNo;
    private int productLevelNo;
    ForecastDTO forecastDTO;
    SessionDTO sessionDTO;
    private Map<String, Integer> projectionDetails = new HashMap<>();
    int historyStartIndex = -1;
    int projectionStartIndex = -1;
    int forecastStartIndex = -1;
    int historyEndIndex = -1;
    int projectionEndIndex = -1;
    int forecastEndIndex = -1;
    private String groupFilter = "";
    private int tpLevel = 0;
    private boolean excelExport;
    private String custRelationshipBuilderSid;
    private String prodRelationshipBuilderSid;
    private String relationshipBuilderSid;
    private List<String> nonFetchableIndex = new ArrayList<>();
    boolean groupCount = false;
    int levelCount = 0;
    List<String> hirarechyNo = new ArrayList<>();
    private String discountName = StringUtils.EMPTY;
    private boolean expandCollapseFlag = false;
    private String ndcType = StringUtils.EMPTY;
    private String methodology = StringUtils.EMPTY;
    private String levelFilterValue = StringUtils.EMPTY;
    private String customerHierSid = StringUtils.EMPTY;
    private String productHierSid = StringUtils.EMPTY;
    private String level = StringUtils.EMPTY;
    private String levelNdc = StringUtils.EMPTY;
    private String levelIndicator = StringUtils.EMPTY;
    private String projectionStartDate;
    private String therapeuticClass;
    private int maxCustLevel;
    private int maxProdLevel;
    private String baseline = StringUtils.EMPTY;
    private boolean levelFilter = false;
    private Map<String, SalesRowDto> itemMap;
    private int lastLevelNo = 0;
    private int totalCustomerLevelNo;
    private String salesAllocationTherapeutic = "%";
    private boolean productNdc = false;
    private String[] expandLevelValueArray = null;
    private String[] expandLevelCaptionArray = null;
    private int levelExpColLevelNo = 0;
    private boolean customerNdc = false;
    private String projectionType = StringUtils.EMPTY;
    private String discountType = StringUtils.EMPTY;
    private int discountTypeId = 0;
    private String calcPeriods = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private boolean isBrand;
    private String productType = StringUtils.EMPTY;
    private String lag = StringUtils.EMPTY;
    private boolean filterDdlb;
    private String levelName = StringUtils.EMPTY;
    private boolean customFlag;
    private Integer customLevelNo = 0;
    private Integer projectionEndYear = 0;
    private Integer projectionEndMonth = 0;
    private Date projectionEndDate;
    private Date forecastEndDate;
    private Integer forecastEndYear = 0;
    private Integer forecastEndMonth = 0;
    private List<String> variableList = new ArrayList<>();
    private String screenName = StringUtils.EMPTY;
    private int rowsPerLevelItem = 0;
    private String buisnessProcessIndicator = StringUtils.EMPTY;
    private List projectedList = new ArrayList();
    private boolean future;
    private boolean isChildTotal;
    //Discount projection related entries
    private int custHierarchySid;
    private int prodHierarchySid;
    private boolean levelFlag;
    private Integer supplementalLevelNo = 0;
    private String supplementalLevelName = StringUtils.EMPTY;
    private String parentSupplementalLevelName = StringUtils.EMPTY;
    private List<Object> supplementalList = new ArrayList<>();
    private Integer ccpDetailsSID = 0;
    private Integer systemID = 0;
    private Integer companyID = 0;
    private Integer contractID = 0;
    private Integer therapeuticID = 0;
    private Integer levelCompanySid = 0;
    private String brandID = StringUtils.EMPTY;
    private List<String> projectionDetailIdList = new ArrayList<>();
    private List<String> parentProjectionDetailIdList = new ArrayList<>();
    private String levelFieldSelection = StringUtils.EMPTY;
    private boolean excelFlag;
    //Discount projection related entries ends here
    //DPR
    private String mandatedOrSupp;
    private int custHierarchySID;
    private int prodHierarchySID;
    private boolean excel = false;
    private String relationshipLevelName = StringUtils.EMPTY;
    private String parentLevelValue;
    private DiscountProjectionResultsDTO mandatedDTO;
    private DiscountProjectionResultsDTO supplementalDTO;
    private List<DiscountProjectionResultsDTO> dprDTOList;
    private String marketTypeValue = StringUtils.EMPTY;
    private String cusFieldName = StringUtils.EMPTY;
    //MM DPR Variables
    private String hierarchySid;
    private String hierarchylevelId;
    private String relationshipValue;
    private String isParent;
    private String empty;
    private Integer parent;
    private boolean checkFlag;
    private boolean suppFlag;
    private boolean nmSuppFlag;
    private boolean manFlag;
    private String manCheck;
    private boolean countFlag;
    private String nextFlag;
    private String currentGroup;
    private String currentCustomer = StringUtils.EMPTY;
    private String currentContract = StringUtils.EMPTY;
    private String currentBrand = StringUtils.EMPTY;
    private String currentLevel = StringUtils.EMPTY;
    private boolean lastFlag;
    private String selectedCust;
    private String filterCustomerDDLB = StringUtils.EMPTY;
    private String filterLevelValue = StringUtils.EMPTY;
    private boolean FilterFlag;
    int start = 0;
    int offset = 0;
    private String discountLevel = StringUtils.EMPTY;
    private Integer onExpandTotalRow = 0;
    private String pivotValue = StringUtils.EMPTY;
    private List<Date> startAndEndDate = new ArrayList<>();
    private String startDateValue = "";
    private String endDateValue = "";
    private String nmSuppLevel = StringUtils.EMPTY;
    private String projTabName = StringUtils.EMPTY;
    private String fromDateDdlb;
    private String toDateDdlb;
    private String calcBased;
    // NM DP
    private List<String> dPVariablesList;
    private List<String> ppaSelectedVariables;
    private List<Object> rightHeaderDoubleColumns = new ArrayList<>();
    private String discountValue = StringUtils.EMPTY;
    private String definedContract = StringUtils.EMPTY;
    Map<Integer, List> headerMapForExcel = new HashMap<>();
    private Map<String, String> pivotHeaderMap = new HashMap<>();
    private List<String> pivotList = new ArrayList<>();

    private Map<Integer,String> levelDdlbMap = new HashMap();

    private String ccpIds=StringUtils.EMPTY;
    private Object[] displayFormat = null;
    private Object conversionFactor = null;
    public List<String> getdPVariablesList() {
        return dPVariablesList;
    }

    public void setdPVariablesList(List<String> dPVariablesList) {
        this.dPVariablesList = dPVariablesList;
    }
    private int ccpCount;

    public String getProjTabName() {
        return projTabName;
    }

    public void setProjTabName(String projTabName) {
        this.projTabName = projTabName;
    }

    public boolean isExcelFlag() {
        return excelFlag;
    }

    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    public void setForecastDTO(ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    public void setExcelFlag(boolean excelFlag) {
        this.excelFlag = excelFlag;
    }

    public Integer getLevelCompanySid() {
        return levelCompanySid;
    }

    public void setLevelCompanySid(Integer levelCompanySid) {
        this.levelCompanySid = levelCompanySid;
    }

    public String getLevelFieldSelection() {
        return levelFieldSelection;
    }

    public void setLevelFieldSelection(String levelFieldSelection) {
        this.levelFieldSelection = levelFieldSelection;
    }

    public List<String> getProjectionDetailIdList() {
        return projectionDetailIdList;
    }

    public void setProjectionDetailIdList(List<String> projectionDetailIdList) {
        this.projectionDetailIdList = projectionDetailIdList;
    }

    public List<String> getParentProjectionDetailIdList() {
        return parentProjectionDetailIdList;
    }

    public void setParentProjectionDetailIdList(List<String> parentProjectionDetailIdList) {
        this.parentProjectionDetailIdList = parentProjectionDetailIdList;
    }

    public Integer getSystemID() {
        return systemID;
    }

    public void setSystemID(Integer systemID) {
        this.systemID = systemID;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public Integer getTherapeuticID() {
        return therapeuticID;
    }

    public void setTherapeuticID(Integer therapeuticID) {
        this.therapeuticID = therapeuticID;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getSupplementalLevelName() {
        return supplementalLevelName;
    }

    public void setSupplementalLevelName(String supplementalLevelName) {
        this.supplementalLevelName = supplementalLevelName;
    }

    public String getParentSupplementalLevelName() {
        return parentSupplementalLevelName;
    }

    public void setParentSupplementalLevelName(String parentSupplementalLevelName) {
        this.parentSupplementalLevelName = parentSupplementalLevelName;
    }

    public List<Object> getSupplementalList() {
        return supplementalList;
    }

    public void setSupplementalList(List<Object> supplementalList) {
        this.supplementalList = supplementalList;
    }

    public Integer getCcpDetailsSID() {
        return ccpDetailsSID;
    }

    public void setCcpDetailsSID(Integer ccpDetailsSID) {
        this.ccpDetailsSID = ccpDetailsSID;
    }

    public Integer getSupplementalLevelNo() {
        return supplementalLevelNo;
    }

    public void setSupplementalLevelNo(Integer supplementalLevelNo) {
        this.supplementalLevelNo = supplementalLevelNo;
    }

    public boolean isLevelFlag() {
        return levelFlag;
    }

    public void setLevelFlag(boolean levelFlag) {
        this.levelFlag = levelFlag;
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

    public List<String> getVariableList() {
        return variableList;
    }

    public void setVariableList(List<String> variableList) {
        this.variableList = variableList;
    }

    public Date getProjectionEndDate() {
        return projectionEndDate;
    }

    public void setProjectionEndDate(Date projectionEndDate) {
        this.projectionEndDate = projectionEndDate;
    }

    public Date getForecastEndDate() {
        return forecastEndDate;
    }

    public void setForecastEndDate(Date forecastEndDate) {
        this.forecastEndDate = forecastEndDate;
    }

    public Integer getForecastEndYear() {
        return forecastEndYear;
    }

    public void setForecastEndYear(Integer forecastEndYear) {
        this.forecastEndYear = forecastEndYear;
    }

    public Integer getForecastEndMonth() {
        return forecastEndMonth;
    }

    public void setForecastEndMonth(Integer forecastEndMonth) {
        this.forecastEndMonth = forecastEndMonth;
    }

    public Integer getProjectionEndMonth() {
        return projectionEndMonth;
    }

    public void setProjectionEndMonth(Integer projectionEndMonth) {
        this.projectionEndMonth = projectionEndMonth;
    }

    public Integer getProjectionEndYear() {
        return projectionEndYear;
    }

    public void setProjectionEndYear(Integer projectionEndYear) {
        this.projectionEndYear = projectionEndYear;
    }

    public Integer getCustomLevelNo() {
        return customLevelNo;
    }

    public void setCustomLevelNo(Integer customLevelNo) {
        this.customLevelNo = customLevelNo;
    }

    public boolean isCustomFlag() {
        return customFlag;
    }

    public void setCustomFlag(boolean customFlag) {
        this.customFlag = customFlag;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getHirarechyNo() {
        return hirarechyNo;
    }

    public void setHirarechyNo(List<String> hirarechyNo) {
        this.hirarechyNo = hirarechyNo;
    }

    public boolean isExcelExport() {
        return excelExport;
    }

    public void setExcelExport(boolean excelExport) {
        this.excelExport = excelExport;
    }

    public int getDiscountIndex() {
        return discountIndex;
    }

    public void setDiscountIndex(int discountIndex) {
        this.discountIndex = discountIndex;
    }

    public boolean isIsExandCollapse() {
        return isExandCollapse;
    }

    public void setIsExandCollapse(boolean isExandCollapse) {
        this.isExandCollapse = isExandCollapse;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
    }

    public boolean isIsCustomHierarchy() {
        return isCustomHierarchy;
    }

    public void setIsCustomHierarchy(boolean isCustomHierarchy) {
        this.isCustomHierarchy = isCustomHierarchy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getTreeLevelNo() {
        return treeLevelNo;
    }

    public void setTreeLevelNo(int treeLevelNo) {
        this.treeLevelNo = treeLevelNo;
    }

    public int getFrequencyDivision() {
        return frequencyDivision;
    }

    public void setFrequencyDivision(int frequencyDivision) {
        this.frequencyDivision = frequencyDivision;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(int startPeriod) {
        this.startPeriod = startPeriod;
    }

    public int getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(int endPeriod) {
        this.endPeriod = endPeriod;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public String getSalesOrUnit() {
        return salesOrUnit;
    }

    public void setSalesOrUnit(String salesOrUnit) {
        this.salesOrUnit = salesOrUnit;
    }

    public String getActualsOrProjections() {
        return actualsOrProjections;
    }

    public void setActualsOrProjections(String actualsOrProjections) {
        this.actualsOrProjections = actualsOrProjections;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public boolean isIsTotal() {
        return isTotal;
    }

    public void setIsTotal(boolean isTotal) {
        this.isTotal = isTotal;
    }

    public boolean isIsProjectionTotal() {
        return isProjectionTotal;
    }

    public void setIsProjectionTotal(boolean isProjectionTotal) {
        this.isProjectionTotal = isProjectionTotal;
    }

    public String getHierarchyNo() {
        return hierarchyNo;
    }

    public void setHierarchyNo(String hierarchyNo) {
        this.hierarchyNo = hierarchyNo;
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

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public List<List<String>> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<List<String>> discountList) {
        this.discountList = discountList;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getProjectionOrder() {
        return projectionOrder;
    }

    public void setProjectionOrder(String projectionOrder) {
        this.projectionOrder = projectionOrder;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public String getPivotView() {
        return pivotView;
    }

    public void setPivotView(String pivotView) {
        this.pivotView = pivotView;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public int getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(int historyNum) {
        this.historyNum = historyNum;
    }

    public int getProjectionNum() {
        return projectionNum;
    }

    public void setProjectionNum(int projectionNum) {
        this.projectionNum = projectionNum;
    }

    public List<String> getLevelValueList() {
        return levelValueList;
    }

    public void setLevelValueList(List<String> levelValueList) {
        this.levelValueList = levelValueList;
    }

    public List<String> getParentNodeList() {
        return parentNodeList;
    }

    public void setParentNodeList(List<String> parentNodeList) {
        this.parentNodeList = parentNodeList;
    }

    public List<String> getDiscountNameList() {
        List<String> discountNames = new ArrayList<>();
        if (discountList != null && discountList.size() > 1) {
            discountNames = discountList.get(1);
        }
        return discountNames;
    }

    public List<String> getDiscountNoList() {
        List<String> discountNos = new ArrayList<>();
        if (discountList != null && discountList.size() > 0) {
            discountNos = discountList.get(0);
        }
        return discountNos;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public boolean hasColumn(String column) {
        if (columns != null && column != null) {
            return columns.contains(column);
        }
        return false;
    }

    public List<String> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<String> periodList) {
        this.periodList = periodList;
    }

    public boolean hasPeriod(String period) {
        if (periodList != null && period != null) {
            return periodList.contains(period);
        }
        return false;
    }

    public Map<String, String> getPeriodListMap() {
        return periodListMap;
    }

    public void setPeriodListMap(Map<String, String> periodListMap) {
        this.periodListMap = periodListMap;
    }

    public void setDiscountProgramsList(List<String> discountProgramsList) {
        this.discountProgramsList = discountProgramsList;
    }

    public List<String> getDiscountProgramsList() {
        return discountProgramsList;
    }

    public boolean isPpa() {
        return ppa;
    }

    public void setPpa(boolean ppa) {
        this.ppa = ppa;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFilterLevelNo() {
        return filterLevelNo;
    }

    public void setFilterLevelNo(int filterLevelNo) {
        this.filterLevelNo = filterLevelNo;
    }

    public String getFilterHierarchyNo() {
        return filterHierarchyNo;
    }

    public void setFilterHierarchyNo(String filterHierarchyNo) {
        this.filterHierarchyNo = filterHierarchyNo;
    }

    public int getCustomerLevelNo() {
        return customerLevelNo;
    }

    public void setCustomerLevelNo(int customerLevelNo) {
        this.customerLevelNo = customerLevelNo;
    }

    public int getProductLevelNo() {
        return productLevelNo;
    }

    public void setProductLevelNo(int productLevelNo) {
        this.productLevelNo = productLevelNo;
    }

    public Map<String, Integer> getProjectionDetails() {
        return projectionDetails;
    }

    public void setProjectionDetails(Map<String, Integer> projectionDetails) {
        this.projectionDetails = projectionDetails;
    }

    public void addProjectionDetails(String field, Integer value) {
        this.projectionDetails.put(field, value);
    }

    public Integer getProjectionDetails(String field) {
        return projectionDetails.get(field);
    }

    public int getHistoryStartDay() {
        return historyStartDay;
    }

    public void setHistoryStartDay(int historyStartDay) {
        this.historyStartDay = historyStartDay;
    }

    public int getHistoryStartPeriod() {
        return historyStartPeriod;
    }

    public void setHistoryStartPeriod(int historyStartPeriod) {
        this.historyStartPeriod = historyStartPeriod;
    }

    public int getHistoryEndDay() {
        return historyEndDay;
    }

    public void setHistoryEndDay(int historyEndDay) {
        this.historyEndDay = historyEndDay;
    }

    public int getHistoryEndPeriod() {
        return historyEndPeriod;
    }

    public void setHistoryEndPeriod(int historyEndPeriod) {
        this.historyEndPeriod = historyEndPeriod;
    }

    public int getForecastStartDay() {
        return forecastStartDay;
    }

    public void setForecastStartDay(int forecastStartDay) {
        this.forecastStartDay = forecastStartDay;
    }

    public int getForecastStartPeriod() {
        return forecastStartPeriod;
    }

    public void setForecastStartPeriod(int forecastStartPeriod) {
        this.forecastStartPeriod = forecastStartPeriod;
    }

    public int getForecastEndDay() {
        return forecastEndDay;
    }

    public void setForecastEndDay(int forecastEndDay) {
        this.forecastEndDay = forecastEndDay;
    }

    public int getForecastEndPeriod() {
        return forecastEndPeriod;
    }

    public void setForecastEndPeriod(int forecastEndPeriod) {
        this.forecastEndPeriod = forecastEndPeriod;
    }

    public int getProjectionStartDay() {
        return projectionStartDay;
    }

    public void setProjectionStartDay(int projectionStartDay) {
        this.projectionStartDay = projectionStartDay;
    }

    public int getProjectionStartPeriod() {
        return projectionStartPeriod;
    }

    public void setProjectionStartPeriod(int projectionStartPeriod) {
        this.projectionStartPeriod = projectionStartPeriod;
    }

    public int getProjectionEndDay() {
        return projectionEndDay;
    }

    public void setProjectionEndDay(int projectionEndDay) {
        this.projectionEndDay = projectionEndDay;
    }

    public int getProjectionEndPeriod() {
        return projectionEndPeriod;
    }

    public void setProjectionEndPeriod(int projectionEndPeriod) {
        this.projectionEndPeriod = projectionEndPeriod;
    }

    public int getHistoryStartYear() {
        return historyStartYear;
    }

    public void setHistoryStartYear(int historyStartYear) {
        this.historyStartYear = historyStartYear;
    }

    public int getHistoryStartMonth() {
        return historyStartMonth;
    }

    public void setHistoryStartMonth(int historyStartMonth) {
        this.historyStartMonth = historyStartMonth;
    }

    public int getHistoryEndMonth() {
        return historyEndMonth;
    }

    public void setHistoryEndMonth(int historyEndMonth) {
        this.historyEndMonth = historyEndMonth;
    }

    public int getHistoryEndYear() {
        return historyEndYear;
    }

    public void setHistoryEndYear(int historyEndYear) {
        this.historyEndYear = historyEndYear;
    }

    public int getForecastStartMonth() {
        return forecastStartMonth;
    }

    public void setForecastStartMonth(int forecastStartMonth) {
        this.forecastStartMonth = forecastStartMonth;
    }

    public int getForecastStartYear() {
        return forecastStartYear;
    }

    public void setForecastStartYear(int forecastStartYear) {
        this.forecastStartYear = forecastStartYear;
    }

    public int getProjectionStartMonth() {
        return projectionStartMonth;
    }

    public void setProjectionStartMonth(int projectionStartMonth) {
        this.projectionStartMonth = projectionStartMonth;
    }

    public int getProjectionStartYear() {
        return projectionStartYear;
    }

    public void setProjectionStartYear(int projectionStartYear) {
        this.projectionStartYear = projectionStartYear;
    }

    public int getHistoryStartIndex() {
        return historyStartIndex;
    }

    public void setHistoryStartIndex(int historyStartIndex) {
        this.historyStartIndex = historyStartIndex;
    }

    public int getProjectionStartIndex() {
        return projectionStartIndex;
    }

    public void setProjectionStartIndex(int projectionStartIndex) {
        this.projectionStartIndex = projectionStartIndex;
    }

    public int getForecastStartIndex() {
        return forecastStartIndex;
    }

    public void setForecastStartIndex(int forecastStartIndex) {
        this.forecastStartIndex = forecastStartIndex;
    }

    public int getHistoryEndIndex() {
        return historyEndIndex;
    }

    public void setHistoryEndIndex(int historyEndIndex) {
        this.historyEndIndex = historyEndIndex;
    }

    public int getProjectionEndIndex() {
        return projectionEndIndex;
    }

    public void setProjectionEndIndex(int projectionEndIndex) {
        this.projectionEndIndex = projectionEndIndex;
    }

    public int getForecastEndIndex() {
        return forecastEndIndex;
    }

    public void setForecastEndIndex(int forecastEndIndex) {
        this.forecastEndIndex = forecastEndIndex;
    }

    public String getGroupFilter() {
        return groupFilter;
    }

    public void setGroupFilter(String groupFilter) {
        this.groupFilter = groupFilter;
    }

    public int getTpLevel() {
        return tpLevel;
    }

    public void setTpLevel(int tpLevel) {
        this.tpLevel = tpLevel;
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

    public String getRelationshipBuilderSid() {
        return relationshipBuilderSid;
    }

    public void setRelationshipBuilderSid(String relationshipBuilderSid) {
        this.relationshipBuilderSid = relationshipBuilderSid;
    }

    public List<String> getNonFetchableIndex() {
        return nonFetchableIndex;
    }

    public void clearNonFetchableIndex() {
        nonFetchableIndex.clear();
    }

    public void setNonFetchableIndex(List<String> nonFetchableIndex) {
        Collections.sort(nonFetchableIndex);
        this.nonFetchableIndex = nonFetchableIndex;
    }

    public boolean hasNonFetchableIndex(String index) {
        if (nonFetchableIndex != null && index != null) {
            return nonFetchableIndex.contains(index);
        }
        return false;
    }

    public boolean isGroupCount() {
        return groupCount;
    }

    public void setGroupCount(boolean groupCount) {
        this.groupCount = groupCount;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public boolean isExpandCollapseFlag() {
        return expandCollapseFlag;
    }

    public void setExpandCollapseFlag(boolean expandCollapseFlag) {
        this.expandCollapseFlag = expandCollapseFlag;
    }

    public String getNdcType() {
        return ndcType;
    }

    public void setNdcType(String ndcType) {
        this.ndcType = ndcType;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getLevelFilterValue() {
        return levelFilterValue;
    }

    public void setLevelFilterValue(String levelFilterValue) {
        this.levelFilterValue = levelFilterValue;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelNdc() {
        return levelNdc;
    }

    public void setLevelNdc(String levelNdc) {
        this.levelNdc = levelNdc;
    }

    public String getLevelIndicator() {
        return levelIndicator;
    }

    public void setLevelIndicator(String levelIndicator) {
        this.levelIndicator = levelIndicator;
    }

    public String getProjectionStartDate() {
        return projectionStartDate;
    }

    public void setProjectionStartDate(String projectionStartDate) {
        this.projectionStartDate = projectionStartDate;
    }

    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public int getMaxCustLevel() {
        return maxCustLevel;
    }

    public void setMaxCustLevel(int maxCustLevel) {
        this.maxCustLevel = maxCustLevel;
    }

    public int getMaxProdLevel() {
        return maxProdLevel;
    }

    public void setMaxProdLevel(int maxProdLevel) {
        this.maxProdLevel = maxProdLevel;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public boolean isLevelFilter() {
        return levelFilter;
    }

    public void setLevelFilter(boolean levelFilter) {
        this.levelFilter = levelFilter;
    }

    public Map<String, SalesRowDto> getItemMap() {
        return itemMap;
    }

    public void setItemMap(Map<String, SalesRowDto> itemMap) {
        this.itemMap = itemMap;
    }

    public int getLastLevelNo() {
        return lastLevelNo;
    }

    public void setLastLevelNo(int lastLevelNo) {
        this.lastLevelNo = lastLevelNo;
    }

    public int getTotalCustomerLevelNo() {
        return totalCustomerLevelNo;
    }

    public void setTotalCustomerLevelNo(int totalCustomerLevelNo) {
        this.totalCustomerLevelNo = totalCustomerLevelNo;
    }

    public String getSalesAllocationTherapeutic() {
        return salesAllocationTherapeutic;
    }

    public void setSalesAllocationTherapeutic(String salesAllocationTherapeutic) {
        this.salesAllocationTherapeutic = salesAllocationTherapeutic;
    }

    public boolean isProductNdc() {
        return productNdc;
    }

    public void setProductNdc(boolean productNdc) {
        this.productNdc = productNdc;
    }

    public String[] getExpandLevelValueArray() {
        return expandLevelValueArray;
    }

    public void setExpandLevelValueArray(String[] expandLevelValueArray) {
        this.expandLevelValueArray = expandLevelValueArray;
    }

    public String[] getExpandLevelCaptionArray() {
        return expandLevelCaptionArray;
    }

    public void setExpandLevelCaptionArray(String[] expandLevelCaptionArray) {
        this.expandLevelCaptionArray = expandLevelCaptionArray;
    }

    public int getLevelExpColLevelNo() {
        return levelExpColLevelNo;
    }

    public void setLevelExpColLevelNo(int levelExpColLevelNo) {
        this.levelExpColLevelNo = levelExpColLevelNo;
    }

    public boolean isCustomerNdc() {
        return customerNdc;
    }

    public void setCustomerNdc(boolean customerNdc) {
        this.customerNdc = customerNdc;
    }

    public String getProjectionType() {
        return projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(int discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    public String getCalcPeriods() {
        return calcPeriods;
    }

    public void setCalcPeriods(String calcPeriods) {
        this.calcPeriods = calcPeriods;
    }

    public boolean isIsBrand() {
        return isBrand;
    }

    public void setIsBrand(boolean isBrand) {
        this.isBrand = isBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLag() {
        return lag;
    }

    public void setLag(String lag) {
        this.lag = lag;
    }

    public boolean isFilterDdlb() {
        return filterDdlb;
    }

    public void setFilterDdlb(boolean filterDdlb) {
        this.filterDdlb = filterDdlb;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getRowsPerLevelItem() {
        return rowsPerLevelItem;
    }

    public void setRowsPerLevelItem(int rowsPerLevelItem) {
        this.rowsPerLevelItem = rowsPerLevelItem;
    }

    public String getBuisnessProcessIndicator() {
        return buisnessProcessIndicator;
    }

    public void setBuisnessProcessIndicator(String buisnessProcessIndicator) {
        this.buisnessProcessIndicator = buisnessProcessIndicator;
    }

    public List getProjectedList() {
        return projectedList;
    }

    public void setProjectedList(List projectedList) {
        this.projectedList = projectedList;
    }

    public Date getToDates() {
        return toDates;
    }

    public void setToDates(Date toDates) {
        this.toDates = toDates;
    }

    public String getMandatedOrSupp() {
        return mandatedOrSupp;
    }

    public void setMandatedOrSupp(String mandatedOrSupp) {
        this.mandatedOrSupp = mandatedOrSupp;
    }

    public int getCustHierarchySID() {
        return custHierarchySID;
    }

    public void setCustHierarchySID(int custHierarchySID) {
        this.custHierarchySID = custHierarchySID;
    }

    public int getProdHierarchySID() {
        return prodHierarchySID;
    }

    public void setProdHierarchySID(int prodHierarchySID) {
        this.prodHierarchySID = prodHierarchySID;
    }

    public boolean isExcel() {
        return excel;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }

    public String getRelationshipLevelName() {
        return relationshipLevelName;
    }

    public void setRelationshipLevelName(String relationshipLevelName) {
        this.relationshipLevelName = relationshipLevelName;
    }

    public String getParentLevelValue() {
        return parentLevelValue;
    }

    public void setParentLevelValue(String parentLevelValue) {
        this.parentLevelValue = parentLevelValue;
    }

    public DiscountProjectionResultsDTO getMandatedDTO() {
        return mandatedDTO;
    }

    public void setMandatedDTO(DiscountProjectionResultsDTO mandatedDTO) {
        this.mandatedDTO = mandatedDTO;
    }

    public DiscountProjectionResultsDTO getSupplementalDTO() {
        return supplementalDTO;
    }

    public void setSupplementalDTO(DiscountProjectionResultsDTO supplementalDTO) {
        this.supplementalDTO = supplementalDTO;
    }

    public List<DiscountProjectionResultsDTO> getDprDTOList() {
        return dprDTOList;
    }

    public void setDprDTOList(List<DiscountProjectionResultsDTO> dprDTOList) {
        this.dprDTOList = dprDTOList;
    }

    public String getMarketTypeValue() {
        return marketTypeValue;
    }

    public void setMarketTypeValue(String marketTypeValue) {
        this.marketTypeValue = marketTypeValue;
    }

    public String getCusFieldName() {
        return cusFieldName;
    }

    public void setCusFieldName(String cusFieldName) {
        this.cusFieldName = cusFieldName;
    }

    public String getHierarchySid() {
        return hierarchySid;
    }

    public void setHierarchySid(String hierarchySid) {
        this.hierarchySid = hierarchySid;
    }

    public String getHierarchylevelId() {
        return hierarchylevelId;
    }

    public void setHierarchylevelId(String hierarchylevelId) {
        this.hierarchylevelId = hierarchylevelId;
    }

    public String getRelationshipValue() {
        return relationshipValue;
    }

    public void setRelationshipValue(String relationshipValue) {
        this.relationshipValue = relationshipValue;
    }

    public String getIsParent() {
        return isParent;
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public boolean isSuppFlag() {
        return suppFlag;
    }

    public void setSuppFlag(boolean suppFlag) {
        this.suppFlag = suppFlag;
    }

    public boolean isNmSuppFlag() {
        return nmSuppFlag;
    }

    public void setNmSuppFlag(boolean nmSuppFlag) {
        this.nmSuppFlag = nmSuppFlag;
    }

    public boolean isManFlag() {
        return manFlag;
    }

    public void setManFlag(boolean manFlag) {
        this.manFlag = manFlag;
    }

    public String getManCheck() {
        return manCheck;
    }

    public void setManCheck(String manCheck) {
        this.manCheck = manCheck;
    }

    public boolean isCountFlag() {
        return countFlag;
    }

    public void setCountFlag(boolean countFlag) {
        this.countFlag = countFlag;
    }

    public String getNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(String nextFlag) {
        this.nextFlag = nextFlag;
    }

    public String getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(String currentGroup) {
        this.currentGroup = currentGroup;
    }

    public String getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(String currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public String getCurrentContract() {
        return currentContract;
    }

    public void setCurrentContract(String currentContract) {
        this.currentContract = currentContract;
    }

    public String getCurrentBrand() {
        return currentBrand;
    }

    public void setCurrentBrand(String currentBrand) {
        this.currentBrand = currentBrand;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isLastFlag() {
        return lastFlag;
    }

    public void setLastFlag(boolean lastFlag) {
        this.lastFlag = lastFlag;
    }

    public String getSelectedCust() {
        return selectedCust;
    }

    public void setSelectedCust(String selectedCust) {
        this.selectedCust = selectedCust;
    }

    public String getFilterCustomerDDLB() {
        return filterCustomerDDLB;
    }

    public void setFilterCustomerDDLB(String filterCustomerDDLB) {
        this.filterCustomerDDLB = filterCustomerDDLB;
    }

    public String getFilterLevelValue() {
        return filterLevelValue;
    }

    public void setFilterLevelValue(String filterLevelValue) {
        this.filterLevelValue = filterLevelValue;
    }

    public boolean isFilterFlag() {
        return FilterFlag;
    }

    public void setFilterFlag(boolean FilterFlag) {
        this.FilterFlag = FilterFlag;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getDiscountLevel() {
        return discountLevel;
    }

    public void setDiscountLevel(String discountLevel) {
        this.discountLevel = discountLevel;
    }

    public Integer getOnExpandTotalRow() {
        return onExpandTotalRow;
    }

    public void setOnExpandTotalRow(Integer onExpandTotalRow) {
        this.onExpandTotalRow = onExpandTotalRow;
    }

    public String getPivotValue() {
        return pivotValue;
    }

    public void setPivotValue(String pivotValue) {
        this.pivotValue = pivotValue;
    }

    public List<Date> getStartAndEndDate() {
        return startAndEndDate;
    }

    public void setStartAndEndDate(List<Date> startAndEndDate) {
        this.startAndEndDate = startAndEndDate;
    }

    public String getStartDateValue() {
        return startDateValue;
    }

    public void setStartDateValue(String startDateValue) {
        this.startDateValue = startDateValue;
    }

    public String getEndDateValue() {
        return endDateValue;
    }

    public void setEndDateValue(String endDateValue) {
        this.endDateValue = endDateValue;
    }

    public String getNmSuppLevel() {
        return nmSuppLevel;
    }

    public void setNmSuppLevel(String nmSuppLevel) {
        this.nmSuppLevel = nmSuppLevel;
    }

    public boolean isFuture() {
        return future;
    }

    public void setFuture(boolean future) {
        this.future = future;
    }

    public boolean isIsChildTotal() {
        return isChildTotal;
    }

    public void setIsChildTotal(boolean isChildTotal) {
        this.isChildTotal = isChildTotal;
    }

    public String getFromDateDdlb() {
        return fromDateDdlb;
    }

    public void setFromDateDdlb(String fromDateDdlb) {
        this.fromDateDdlb = fromDateDdlb;
    }

    public String getToDateDdlb() {
        return toDateDdlb;
    }

    public void setToDateDdlb(String toDateDdlb) {
        this.toDateDdlb = toDateDdlb;
    }

    public String getCalcBased() {
        return calcBased;
    }

    public void setCalcBased(String calcBased) {
        this.calcBased = calcBased;
    }

    public int getCcpCount() {
        return ccpCount;
    }

    public void setCcpCount(int ccpCount) {
        this.ccpCount = ccpCount;
    }

    public List<String> getPpaSelectedVariables() {
        return ppaSelectedVariables;
    }

    public void setPpaSelectedVariables(List<String> ppaSelectedVariables) {
        this.ppaSelectedVariables = ppaSelectedVariables;
    }

    public List<Object> getRightHeaderDoubleColumns() {
        return rightHeaderDoubleColumns;
    }

    public void setRightHeaderDoubleColumns(List<Object> rightHeaderDoubleColumns) {
        this.rightHeaderDoubleColumns = rightHeaderDoubleColumns;
    }

    public String getReHierarchyNo() {
        return reHierarchyNo;
    }

    public void setReHierarchyNo(String reHierarchyNo) {
        this.reHierarchyNo = reHierarchyNo;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getDefinedContract() {
        return definedContract;
    }

    public void setDefinedContract(String definedContract) {
        this.definedContract = definedContract;
    }

    public List<String> getDiscountNameCFF() {
        return discountNameCFF;
    }

    public void setDiscountNameCFF(List<String> discountNameCFF) {
        this.discountNameCFF = discountNameCFF;
    }
    
    public Map<String, String> getPivotHeaderMap() {
        return pivotHeaderMap;
    }

    public void setPivotHeaderMap(Map<String, String> pivotHeaderMap) {
        this.pivotHeaderMap = pivotHeaderMap;
    }
    
    public List<String> getPivotList() {
        return pivotList;
    }

    public void setPivotList(List<String> pivotList) {
        this.pivotList = pivotList;
    }

    public Map<Integer, List> getHeaderMapForExcel() {
        return headerMapForExcel;
    }

    public void setHeaderMapForExcel(Map<Integer, List> headerMapForExcel) {
        this.headerMapForExcel = headerMapForExcel;
    }

    public String getCcpIds() {
        return ccpIds;
    }

    public void setCcpIds(String ccpIds) {
        this.ccpIds = ccpIds;
    }

    public Map<Integer, String> getLevelDdlbMap() {
        return levelDdlbMap;
    }

    public void setLevelDdlbMap(Map<Integer, String> levelDdlbMap) {
        this.levelDdlbMap = levelDdlbMap;
    }

    public String getDeductionHierarchyNo() {
        return deductionHierarchyNo;
    }

    public void setDeductionHierarchyNo(String deductionHierarchyNo) {
        this.deductionHierarchyNo = deductionHierarchyNo;
    }
    
    public Object[] getDisplayFormat() {
        return displayFormat;
    }

    public void setDisplayFormat(Object[] displayFormat) {
        this.displayFormat = new Object[displayFormat.length];
        this.displayFormat = displayFormat;
    }
      public Object getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Object conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

}
