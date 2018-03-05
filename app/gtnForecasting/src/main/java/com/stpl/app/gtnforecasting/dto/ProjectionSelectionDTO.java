/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.dto;

import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author abhiram
 */
public class ProjectionSelectionDTO {

    private int projectionId;
    private int userId;
    private int sessionId;
    private int levelNo;
    private int treeLevelNo;
    private int frequencyDivision;
    private int historyStartYear;
    private int historyStartMonth;
    private int historyStartDay;
    private int historyStartPeriod;
    private int historyEndDay;
    private int historyEndPeriod;
    private int historyEndMonth;
    private int historyEndYear;
    private int forecastStartDay;
    private int forecastStartPeriod;
    private int forecastStartMonth;
    private int forecastStartYear;
    private int forecastEndDay;
    private int forecastEndPeriod;
    private int projectionStartDay;
    private int projectionStartPeriod;
    private int projectionStartMonth;
    private int projectionStartYear;
    private int projectionEndDay;
    private int projectionEndPeriod;
    private int currentYear;
    private int startYear;
    private int endYear;
    private int startPeriod;
    private int endPeriod;
    private int currentPeriod;
    private int startMonth;
    private int endMonth;
    private int startDay;
    private int endDay;
    private String salesOrUnit;
    private String actualsOrProjections;
    private String levelValue = StringUtils.EMPTY;
    private String parentNode = StringUtils.EMPTY;
    private String year;
    private String sales;
    private boolean isTotal;
    private boolean isProjectionTotal;
    private String hierarchyNo;
    private String reHierarchyNo = StringUtils.EMPTY;
    private String productHierarchyNo = StringUtils.EMPTY;
    private String customerHierarchyNo = StringUtils.EMPTY;
    private String deductionHierarchyNo = StringUtils.EMPTY;
    private String hierarchyIndicator = StringUtils.EMPTY;
    private List<List<String>> discountList = new ArrayList<>();
    private String view;
    private String frequency = StringUtils.EMPTY;
    private boolean isFrequencyChanged = Boolean.TRUE;
    private String projectionOrder;
    private String history = StringUtils.EMPTY;
    private boolean isHistroryChanged = Boolean.TRUE;
    private String projection;
    private String pivotView;
    private String group;
    private int customId = 0;
    private int historyNum;
    private int projectionNum;
    private List<String> levelValueList = new ArrayList<>();
    private List<String> parentNodeList = new ArrayList<>();
    private Date startDate;
    private Date toDates;
    private List<String> columns = new ArrayList<>();
    private List<String> periodList = new ArrayList<>();
    private List<String> discountProgramsList = new ArrayList<>();
    private Map<String, String> periodListMap = new HashMap<>();
    private boolean isFilter;
    private boolean isCustomHierarchy;
    private int discountIndex = 0;
    private boolean isCount;
    private boolean isExandCollapse;
    private boolean ppa;
    private int total;
    private int filterLevelNo;
    private String filterHierarchyNo = StringUtils.EMPTY;
    private int customerLevelNo;
    private int productLevelNo;
    private int deductionLevelNo;
    private ForecastDTO forecastDTO;
    private SessionDTO sessionDTO;
    private Map<String, Integer> projectionDetails = new HashMap<>();
    private int historyStartIndex = -1;
    private int projectionStartIndex = -1;
    private int forecastStartIndex = -1;
    private int historyEndIndex = -1;
    private int projectionEndIndex = -1;
    private int forecastEndIndex = -1;
    private String groupFilter = StringUtils.EMPTY;
    private int tpLevel = 0;
    private boolean excelExport;
    private String custRelationshipBuilderSid;
    private String prodRelationshipBuilderSid;
    private String relationshipBuilderSid;
    private List<String> nonFetchableIndex = new ArrayList<>();
    private boolean groupCount = false;
    private int levelCount = 0;
    private List<String> hirarechyNo = new ArrayList<>();
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
    private String salesAllocationTherapeutic = Constant.PERCENT;
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
    private List<String> ccpDetailIdList = new ArrayList<>();
    private List<String> parentCcpDetailIdList = new ArrayList<>();
    private String levelFieldSelection = StringUtils.EMPTY;
    private boolean excelFlag;
    private String mandatedOrSupp;
    private int customerHierarchySID;
    private int productHierarchySID;
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
    private int start = 0;
    private int offset = 0;
    private String discountLevel = StringUtils.EMPTY;
    private Integer onExpandTotalRow = 0;
    private String pivotValue = StringUtils.EMPTY;
    private List<Date> startAndEndDate = new ArrayList<>();
    private String startDateValue = StringUtils.EMPTY;
    private String endDateValue = StringUtils.EMPTY;
    private String nmSuppLevel = StringUtils.EMPTY;
    private String projTabName = StringUtils.EMPTY;
    private String fromDateDdlb;
    private String toDateDdlb;
    private String calcBased;
    // NM DP
    private List<String> dPVariablesList;
    private List<String> ppaSelectedVariables;
    private List<Object> rightHeaderDoubleColumns = new ArrayList<>();
    private String discountValue=StringUtils.EMPTY;
    private String definedContract=StringUtils.EMPTY;
    private String functionality=StringUtils.EMPTY;
    private boolean isSumarry=false;
    private List<DiscountProjectionDTO> alternatePivotList=new ArrayList<>();
    private String variableView=StringUtils.EMPTY;
    private boolean isAlternate=false;
    private String detailsSid=StringUtils.EMPTY;
    private Map<Integer, List> headerMapForExcel = new HashMap<>();

    private boolean returns;
    private List<String> commonColumn = new ArrayList<>();
    private List<String> headerMapColumn=new ArrayList<>();
    private List<String> forecastConfigPeriods = new ArrayList<>();
    private Map<String,List<String>> projectionHeaderMap = new HashMap<>();
    private List<String> projectionHeaderList = new ArrayList<>();
    private List<String> programCodeList = new ArrayList<>();
    private String customhierarchy = StringUtils.EMPTY;
    private String customViewIndicator = StringUtils.EMPTY;
    private String tabName = StringUtils.EMPTY;
    private boolean isGenerate = false;
    private boolean isDiscountFlag=false;
    private boolean isproductFirst=false;
    private boolean isdeductionFirst=false;
    private boolean iscustomerFirst=false;
    private List<String> customerLevelFilter=new ArrayList<>();
    private List<String> productLevelFilter=new ArrayList<>();
    private List<String> deductionLevelFilter=new ArrayList<>();
    private List<String> deductionLevelFilterPv=new ArrayList<>();
    private List<String> deductionLevelCaptions=new ArrayList<>();
    private String selectedDeductionLevelName= StringUtils.EMPTY;
    private String uomCode = StringUtils.EMPTY;
    private Object[] displayFormat = null;
    private Object conversionFactor = null;
    private GtnSmallHashMap multipleVariableCheckMap = new GtnSmallHashMap();
    private boolean isMultipleVariablesUpdated = false;
    private Map<String,String> updateQueryMap = new HashMap<>();
    
    public List<String> getDeductionLevelFilter() {
        return deductionLevelFilter == null ? deductionLevelFilter : new ArrayList<>(deductionLevelFilter);
    }

    public void setDeductionLevelFilter(List<String> deductionLevelFilter) {
        this.deductionLevelFilter = deductionLevelFilter == null ? deductionLevelFilter : new ArrayList<>(deductionLevelFilter);
    }
    public List<String> getDeductionLevelFilterPv() {
        return deductionLevelFilterPv == null ? deductionLevelFilterPv : new ArrayList<>(deductionLevelFilterPv);
    }

    public void setDeductionLevelFilterPv(List<String> deductionLevelFilterPv) {
        this.deductionLevelFilterPv = deductionLevelFilterPv == null ? deductionLevelFilterPv : new ArrayList<>(deductionLevelFilterPv);
    }
    

    public String getUomCode() {
        return uomCode;
    }

    public void setUomCode(String uomCode) {
        this.uomCode = uomCode;
    }

    public List<String> getCustomerLevelFilter() {
        return customerLevelFilter == null ? customerLevelFilter : new ArrayList<>(customerLevelFilter);
    }

    public void setCustomerLevelFilter(List<String> customerLevelFilter) {
        this.customerLevelFilter = customerLevelFilter == null ? customerLevelFilter : new ArrayList<>(customerLevelFilter);
    }

    public List<String> getProductLevelFilter() {
        return productLevelFilter == null ? productLevelFilter : new ArrayList<>(productLevelFilter);
    }

    public void setProductLevelFilter(List<String> productLevelFilter) {
        this.productLevelFilter = productLevelFilter == null ? productLevelFilter : new ArrayList<>(productLevelFilter);
    }
    
    

    public boolean isIsDiscountFlag() {
        return isDiscountFlag;
    }

    public void setIsDiscountFlag(boolean isDiscountFlag) {
        this.isDiscountFlag = isDiscountFlag;
    }
    public List<String> getdPVariablesList() {
        return dPVariablesList == null ? dPVariablesList : new ArrayList<>(dPVariablesList);
    }

    public void setdPVariablesList(List<String> dPVariablesList) {
        this.dPVariablesList = dPVariablesList == null ? dPVariablesList : new ArrayList<>(dPVariablesList);
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

    public List<String> getCcpDetailIdList() {
        return ccpDetailIdList == null ? ccpDetailIdList : new ArrayList<>(ccpDetailIdList);
    }

    public void setCcpDetailIdList(List<String> ccpDetailIdList) {
        this.ccpDetailIdList = ccpDetailIdList == null ? ccpDetailIdList : new ArrayList<>(ccpDetailIdList);
    }

    public List<String> getParentCcpDetailIdList() {
        return parentCcpDetailIdList == null ? parentCcpDetailIdList : new ArrayList<>(parentCcpDetailIdList);
    }

    public void setParentCcpDetailIdList(List<String> parentCcpDetailIdList) {
        this.parentCcpDetailIdList = parentCcpDetailIdList == null ? parentCcpDetailIdList : new ArrayList<>(parentCcpDetailIdList);
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
        return supplementalList == null ? supplementalList : new ArrayList<>(supplementalList);
    }

    public void setSupplementalList(List<Object> supplementalList) {
        this.supplementalList = supplementalList == null ? supplementalList : new ArrayList<>(supplementalList);
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
        return variableList == null ? variableList : new ArrayList<>(variableList);
    }

    public void setVariableList(List<String> variableList) {
        this.variableList = variableList == null ? variableList : new ArrayList<>(variableList);
    }

    public Date getProjectionEndDate() {
        return projectionEndDate == null ? null : (Date) projectionEndDate.clone();
    }

    public void setProjectionEndDate(Date projectionEndDate) {
        this.projectionEndDate = projectionEndDate == null ? null : (Date) projectionEndDate.clone();
    }

    public Date getForecastEndDate() {
        return forecastEndDate == null ? null : (Date) forecastEndDate.clone();
    }

    public void setForecastEndDate(Date forecastEndDate) {
        this.forecastEndDate = forecastEndDate == null ? null : (Date) forecastEndDate.clone();
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
        return hirarechyNo == null ? hirarechyNo : new ArrayList<>(hirarechyNo);
    }

    public void setHirarechyNo(List<String> hirarechyNo) {
        this.hirarechyNo = hirarechyNo == null ? hirarechyNo : new ArrayList<>(hirarechyNo);
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
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
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

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
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
        return discountList == null ? discountList : new ArrayList<>(discountList);
    }

    public void setDiscountList(List<List<String>> discountList) {
        this.discountList = discountList == null ? discountList : new ArrayList<>(discountList);
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

    public boolean isIsFrequencyChanged() {
        return isFrequencyChanged;
    }

    public void setIsFrequencyChanged(String selectedFrequency) {
        if (getFrequency().equals(selectedFrequency)) {
            isFrequencyChanged = Boolean.FALSE;
        } else {
            isFrequencyChanged = Boolean.TRUE;
        }
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

    public boolean isIsHistroryChanged() {
        return isHistroryChanged;
    }

    public void setIsHistroryChanged(String selectedHistory) {
        if (getHistory().equals(selectedHistory)) {
            isHistroryChanged = Boolean.FALSE;
        } else {
            isHistroryChanged = Boolean.TRUE;
        }
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
        return levelValueList == null ? levelValueList : new ArrayList<>(levelValueList);
    }

    public void setLevelValueList(List<String> levelValueList) {
        this.levelValueList = levelValueList == null ? levelValueList : new ArrayList<>(levelValueList);
    }

    public List<String> getParentNodeList() {
        return parentNodeList == null ? parentNodeList : new ArrayList<>(parentNodeList);
    }

    public void setParentNodeList(List<String> parentNodeList) {
        this.parentNodeList = parentNodeList == null ? parentNodeList : new ArrayList<>(parentNodeList);
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
        if (discountList != null && !discountList.isEmpty()) {
            discountNos = discountList.get(0);
        }
        return discountNos;
    }

     public List<String> getDiscountNameNoList() {
        List<String> discountNos = new ArrayList<>();
        if (discountList != null && !discountList.isEmpty()) {
            discountNos = discountList.get(NumericConstants.TWO);
        }
        return discountNos;
    }
   
    public List<String> getColumns() {
        return columns == null ? columns : new ArrayList<>(columns);
    }

    public void setColumns(List<String> columns) {
        this.columns = columns == null ? columns : new ArrayList<>(columns);
    }

    public boolean hasColumn(String column) {
        if (columns != null && column != null) {
            return columns.contains(column);
        }
        return false;
    }

    public List<String> getPeriodList() {
        return periodList == null ? periodList : new ArrayList<>(periodList);
    }

    public void setPeriodList(List<String> periodList) {
        this.periodList = periodList == null ? periodList : new ArrayList<>(periodList);
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
        this.discountProgramsList = discountProgramsList == null ? discountProgramsList : new ArrayList<>(discountProgramsList);
    }

    public List<String> getDiscountProgramsList() {
        return discountProgramsList == null ? discountProgramsList : new ArrayList<>(discountProgramsList);
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

    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    public void setForecastDTO(ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
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
        return nonFetchableIndex == null ? nonFetchableIndex : new ArrayList<>(nonFetchableIndex);
    }

    public void clearNonFetchableIndex() {
        nonFetchableIndex.clear();
    }

    public void setNonFetchableIndex(List<String> nonFetchableIndex) {
        Collections.sort(nonFetchableIndex);
        this.nonFetchableIndex = nonFetchableIndex == null ? nonFetchableIndex : new ArrayList<>(nonFetchableIndex);
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
        return expandLevelValueArray == null ? expandLevelValueArray : expandLevelValueArray.clone();
    }

    public void setExpandLevelValueArray(String[] expandLevelValueArray) {
        this.expandLevelValueArray = expandLevelValueArray == null ? expandLevelValueArray : expandLevelValueArray.clone();
    }

    public String[] getExpandLevelCaptionArray() {
        return expandLevelCaptionArray == null ? expandLevelCaptionArray : expandLevelCaptionArray.clone();
    }

    public void setExpandLevelCaptionArray(String[] expandLevelCaptionArray) {
        this.expandLevelCaptionArray = expandLevelCaptionArray == null ? expandLevelCaptionArray : expandLevelCaptionArray.clone();
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
        return projectedList == null ? projectedList : new ArrayList<>(projectedList);
    }

    public void setProjectedList(List projectedList) {
        this.projectedList = projectedList == null ? projectedList : new ArrayList<>(projectedList);
    }

    public Date getToDates() {
        return toDates == null ? null : (Date) toDates.clone();
    }

    public void setToDates(Date toDates) {
        this.toDates = toDates == null ? null : (Date) toDates.clone();
    }

    public String getMandatedOrSupp() {
        return mandatedOrSupp;
    }

    public void setMandatedOrSupp(String mandatedOrSupp) {
        this.mandatedOrSupp = mandatedOrSupp;
    }

    public int getCustomerHierarchySID() {
        return customerHierarchySID;
    }

    public void setCustomerHierarchySID(int customerHierarchySID) {
        this.customerHierarchySID = customerHierarchySID;
    }

    public int getProductHierarchySID() {
        return productHierarchySID;
    }

    public void setProductHierarchySID(int productHierarchySID) {
        this.productHierarchySID = productHierarchySID;
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
        return dprDTOList == null ? dprDTOList : new ArrayList<>(dprDTOList);
    }

    public void setDprDTOList(List<DiscountProjectionResultsDTO> dprDTOList) {
        this.dprDTOList = dprDTOList == null ? dprDTOList : new ArrayList<>(dprDTOList);
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
        return startAndEndDate == null ? startAndEndDate : new ArrayList<>(startAndEndDate);
    }

    public void setStartAndEndDate(List<Date> startAndEndDate) {
        this.startAndEndDate = startAndEndDate == null ? startAndEndDate : new ArrayList<>(startAndEndDate);
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
        return ppaSelectedVariables == null ? ppaSelectedVariables : new ArrayList<>(ppaSelectedVariables);
    }

    public void setPpaSelectedVariables(List<String> ppaSelectedVariables) {
        this.ppaSelectedVariables = ppaSelectedVariables == null ? ppaSelectedVariables : new ArrayList<>(ppaSelectedVariables);
    }
    public List<Object> getRightHeaderDoubleColumns() {
        return rightHeaderDoubleColumns == null ? rightHeaderDoubleColumns : new ArrayList<>(rightHeaderDoubleColumns);
    }

    public void setRightHeaderDoubleColumns(List<Object> rightHeaderDoubleColumns) {
        this.rightHeaderDoubleColumns = rightHeaderDoubleColumns == null ? rightHeaderDoubleColumns : new ArrayList<>(rightHeaderDoubleColumns);
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

    public String getFunctionality() {
        return functionality;
    }

    public void setFunctionality(String functionality) {
        this.functionality = functionality;
    }

    public boolean isIsSumarry() {
        return isSumarry;
    }

    public void setIsSumarry(boolean isSumarry) {
        this.isSumarry = isSumarry;
    }

    public List<DiscountProjectionDTO> getAlternatePivotList() {
        return alternatePivotList == null ? alternatePivotList : new ArrayList<>(alternatePivotList);
    }

    public void setAlternatePivotList(List<DiscountProjectionDTO> alternatePivotList) {
        this.alternatePivotList = alternatePivotList == null ? alternatePivotList : new ArrayList<>(alternatePivotList);
    }

    public String getVariableView() {
        return variableView;
    }

    public void setVariableView(String variableView) {
        this.variableView = variableView;
    }

    public boolean isIsAlternate() {
        return isAlternate;
    }

    public void setIsAlternate(boolean isAlternate) {
        this.isAlternate = isAlternate;
    }

    public String getDetailsSid() {
        return detailsSid;
    }

    public void setDetailsSid(String detailsSid) {
        this.detailsSid = detailsSid;
    }

    public Map<Integer, List> getHeaderMapForExcel() {
        return headerMapForExcel;
    }

    public void setHeaderMapForExcel(Map<Integer, List> headerMapForExcel) {
        this.headerMapForExcel = headerMapForExcel;
    }
    
    public boolean isReturns() {
        return returns;
    }

    public void setReturns(boolean returns) {
        this.returns = returns;
    }

    public List<String> getCommonColumn() {
        return commonColumn == null ? commonColumn : new ArrayList<>(commonColumn);
    }

    public void setCommonColumn(List<String> commonColumn) {
        this.commonColumn = commonColumn == null ? commonColumn : new ArrayList<>(commonColumn);
    }

    public List<String> getHeaderMapColumn() {
        return headerMapColumn == null ? headerMapColumn : new ArrayList<>(headerMapColumn);
    }

    public void setHeaderMapColumn(List<String> headerMapColumn) {
        this.headerMapColumn = headerMapColumn == null ? headerMapColumn : new ArrayList<>(headerMapColumn);
    }

     public Map<String, List<String>> getProjectionHeaderMap() {
        return projectionHeaderMap;
    }

    public void setProjectionHeaderMap(Map<String, List<String>> projectionHeaderMap) {
        this.projectionHeaderMap = projectionHeaderMap;
    }
    
    public List<String> getForecastConfigPeriods() {
        return forecastConfigPeriods == null ? forecastConfigPeriods : new ArrayList<>(forecastConfigPeriods);
    }

    public void setForecastConfigPeriods(List<String> forecastConfigPeriods) {
        this.forecastConfigPeriods = forecastConfigPeriods == null ? forecastConfigPeriods : new ArrayList<>(forecastConfigPeriods);
    }

    public List<String> getProjectionHeaderList() {
        return projectionHeaderList == null ? projectionHeaderList : new ArrayList<>(projectionHeaderList);
    }

    public void setProjectionHeaderList(List<String> projectionHeaderList) {
        this.projectionHeaderList = projectionHeaderList == null ? projectionHeaderList : new ArrayList<>(projectionHeaderList);
    }

    public List<String> getProgramCodeList() {
        return programCodeList == null ? programCodeList : new ArrayList<>(programCodeList);
    }

    public void setProgramCodeList(List<String> programCodeList) {
        this.programCodeList = programCodeList == null ? programCodeList : new ArrayList<>(programCodeList);
    }

    public String getCustomhierarchy() {
        return customhierarchy;
    }

    public void setCustomhierarchy(String customhierarchy) {
        this.customhierarchy = customhierarchy;
    }

    public String getCustomViewIndicator() {
        return customViewIndicator;
    }

    public void setCustomViewIndicator(String customViewIndicator) {
        this.customViewIndicator = customViewIndicator;
    }
    
    public boolean isIsGenerate() {
        return isGenerate;
    }

    public void setIsGenerate(boolean isGenerate) {
        this.isGenerate = isGenerate;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public boolean getIsproductFirst() {
        return isproductFirst;
    }

    public void setIsproductFirst(boolean isproductFirst) {
        this.isproductFirst = isproductFirst;
    }

    public boolean getIscustomerFirst() {
        return iscustomerFirst;
    }

    public void setIscustomerFirst(boolean iscustomerFirst) {
        this.iscustomerFirst = iscustomerFirst;
    }

    public boolean getIsdeductionFirst() {
        return isdeductionFirst;
    }

    public void setIsdeductionFirst(boolean isdeductionFirst) {
        this.isdeductionFirst = isdeductionFirst;
    }

    public String getDeductionHierarchyNo() {
        return deductionHierarchyNo;
    }

    public void setDeductionHierarchyNo(String deductionHierarchyNo) {
        this.deductionHierarchyNo = deductionHierarchyNo;
    }

    public int getDeductionLevelNo() {
        return deductionLevelNo;
    }

    public void setDeductionLevelNo(int deductionLevelNo) {
        this.deductionLevelNo = deductionLevelNo;
    }

    public List<String> getDeductionLevelCaptions() {
        return deductionLevelCaptions == null ? deductionLevelCaptions : new ArrayList<>(deductionLevelCaptions);
    }

    public void setDeductionLevelCaptions(List<String> deductionLevelCaptions) {
        this.deductionLevelCaptions = deductionLevelCaptions == null ? deductionLevelCaptions : new ArrayList<>(deductionLevelCaptions);
    }

    public String getSelectedDeductionLevelName() {
        return selectedDeductionLevelName;
    }

    public void setSelectedDeductionLevelName(String selectedDeductionLevelName) {
        this.selectedDeductionLevelName = selectedDeductionLevelName;
    }
    public Object[] getDisplayFormat() {
        return displayFormat == null ? displayFormat : displayFormat.clone();
    }

    public void setDisplayFormat(Object[] displayFormat) {
        this.displayFormat = new Object[displayFormat.length];
        this.displayFormat = displayFormat.clone();
    }


    public Object getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Object conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public GtnSmallHashMap getMultipleVariableCheckMap() {
        return multipleVariableCheckMap;
    }

    public void setMultipleVariableCheckMap(GtnSmallHashMap multipleVariableCheckMap) {
        this.multipleVariableCheckMap = multipleVariableCheckMap;
    }

    public boolean isMultipleVariablesUpdated() {
        return isMultipleVariablesUpdated;
    }

    public void setIsMultipleVariablesUpdated(boolean isMultipleVariablesUpdated) {
        this.isMultipleVariablesUpdated = isMultipleVariablesUpdated;
    }

    public Map<String, String> getUpdateQueryMap() {
        return updateQueryMap;
    }

    public void setUpdateQueryMap(Map<String, String> updateQueryMap) {
        this.updateQueryMap = updateQueryMap;
    }

    }
