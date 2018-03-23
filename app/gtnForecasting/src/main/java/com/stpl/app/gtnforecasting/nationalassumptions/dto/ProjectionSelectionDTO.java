/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.dto;

import static com.stpl.app.gtnforecasting.nationalassumptions.util.Constants.CommonConstants.SELECT_ONE;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 * Projection Selection DTO.
 *
 * @author Vinodhini
 */
public class ProjectionSelectionDTO {

    private int projectionId;
    private int userId;
    private int sessionId;
    private int levelNo;
    private int treeLevelNo;
    private int frequencyDivision;
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
    private String actualsOrProjections;
    private String levelValue;
    private String parentNode;
    private String year;
    private String sales;
    private boolean isTotal;
    private boolean isProjectionTotal;
    private String hierarchyNo;
    private String hierarchyIndicator;
    private String view;
    private String frequency;
    private String projectionOrder;
    private String history;
    private String projection;
    private String pivotView;
    private String group;
    private int customId;
    private int historyNum;
    private int projectionNum;
    private List<String> levelValueList=new ArrayList<>();
    private List<String> parentNodeList=new ArrayList<>();
    private Date startDate;
    private Date toDate;
    private int ndcLevelNo;
    private List<String> columns=new ArrayList<>();
    private List<String> periodList=new ArrayList<>();
    private List<String> discountProgramsList=new ArrayList<>();
    private Map<String,String> periodListMap=new HashMap<>();
    private boolean isFilter;
    private boolean isCustomHierarchy;
    private List<String> priceTypeList = new ArrayList<>();
    private String variables;
    private int brandMasterId=0;
    private HelperDTO therapeuticSid = new HelperDTO(0, SELECT_ONE.getConstant());
    private HelperDTO brandSid = new HelperDTO(0, SELECT_ONE.getConstant());
    private boolean customFlag;
    private int customLevelNo;
    private String viewName=StringUtils.EMPTY;
    private HelperDTO ndcSid = new HelperDTO(0, SELECT_ONE.getConstant());
    private List<String> pivotList=new ArrayList<>();
    private String ndcParent;
    private Map<String,String[]> notesMap=new HashMap<>();
    private Map<String,String[]> secondRowNotesMap=new HashMap<>();
    private Map<String,String[]> thirdRowNotesMap=new HashMap<>();
    private Map<String,String[]> fourthRowNotesMap=new HashMap<>();
    private int histProjYear;
    private int histProjMonth;
    private int histProjPeriod;
    private int pageStart;
    private int pageOffSet;
    private boolean adjust=false;
    private String ndc9=StringUtils.EMPTY;
    private HelperDTO brand = new HelperDTO(0, SELECT_ONE.getConstant());
    private String ndc9LevelNo=StringUtils.EMPTY;
    private String medicaidSelectedNdc=StringUtils.EMPTY;
    private HelperDTO ndcWSdto = new HelperDTO(0, SELECT_ONE.getConstant());
    private HelperDTO brandWSdto = new HelperDTO(0, SELECT_ONE.getConstant());
    // for paged Table
    private List<String> nonFetchableIndex = new ArrayList<>();
    private boolean excel=false;
    private boolean brandSeclected=false;
    private String newFormulation = StringUtils.EMPTY;
    private Map<String,String> loadUraPriceActualMap = new HashMap<>();
    private Map<String,String> loadUraPriceMap = new HashMap<>();
    private Map<String,String> loadPhsPriceActualMap = new HashMap<>();
    private Map<String,String> loadPhsPriceMap = new HashMap<>();
     
      private String newFormulationItemId = StringUtils.EMPTY;
       
     private String baeselineAmp = StringUtils.EMPTY;
     
     private String baeselineCpi = StringUtils.EMPTY;
     
     private String priceType = StringUtils.EMPTY;
     
      private String ndc9Value = StringUtils.EMPTY;

    public ProjectionSelectionDTO() {
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

    public Date getToDate() {
        return toDate == null ? null : (Date) toDate.clone();
    }

    public void setToDate(Date endDate) {
        this.toDate = endDate == null ? null : (Date) endDate.clone();
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

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
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
   
    public List<String> getColumns() {
        return columns == null ? columns : new ArrayList<>(columns);
    }

    public void setColumns(List<String> columns) {
        this.columns = columns == null ? columns : new ArrayList<>(columns);
    }

    public boolean hasColumn(String column) {
        if (columns != null&&column!=null) {
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

    public Map<String,String> getPeriodListMap() {
        return periodListMap;
    }

    public void setPeriodListMap(Map<String,String> periodListMap) {
        this.periodListMap = periodListMap;
    }

    public void setDiscountProgramsList(List<String> discountProgramsList) {
        this.discountProgramsList = discountProgramsList == null ? discountProgramsList : new ArrayList<>(discountProgramsList);
    }
    
    public List<String> getDiscountProgramsList() {
        return discountProgramsList == null ? discountProgramsList : new ArrayList<>(discountProgramsList);
    }

    public List<String> getPriceTypeList() {
        return priceTypeList == null ? priceTypeList : new ArrayList<>(priceTypeList);
    }

    public void setPriceTypeList(List<String> priceTypeList) {
        this.priceTypeList = priceTypeList == null ? priceTypeList : new ArrayList<>(priceTypeList);
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public int getBrandMasterId() {
        return brandMasterId;
    }

    public void setBrandMasterId(int brandMasterId) {
        this.brandMasterId = brandMasterId;
    }

    public HelperDTO getTherapeuticSid() {
        return therapeuticSid;
    }

    public void setTherapeuticSid(HelperDTO therapeuticSid) {
        this.therapeuticSid = therapeuticSid;
    }

    public HelperDTO getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(HelperDTO brandSid) {
        this.brandSid = brandSid;
    }

    public boolean isCustomFlag() {
        return customFlag;
    }

    public void setCustomFlag(boolean customFlag) {
        this.customFlag = customFlag;
    }

    public int getCustomLevelNo() {
        return customLevelNo;
    }

    public void setCustomLevelNo(int customLevelNo) {
        this.customLevelNo = customLevelNo;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public HelperDTO getNdcSid() {
        return ndcSid;
    }

    public void setNdcSid(HelperDTO ndcSid) {
        this.ndcSid = ndcSid;
    }

    public List<String> getPivotList() {
        return pivotList == null ? pivotList : new ArrayList<>(pivotList);
    }

    public void setPivotList(List<String> pivotList) {
        this.pivotList = pivotList == null ? pivotList : new ArrayList<>(pivotList);
    }

    public int getNdcLevelNo() {
        return ndcLevelNo;
    }

    public void setNdcLevelNo(int ndcLevelNo) {
        this.ndcLevelNo = ndcLevelNo;
    }

    public String getNdcParent() {
        return ndcParent;
    }

    public void setNdcParent(String ndcParent) {
        this.ndcParent = ndcParent;
    }
    
    public Map<String, String[]> getNotesMap() {
        return notesMap;
    }

    public void setNotesMap(Map<String, String[]> notesMap) {
        this.notesMap = notesMap;
    }      

    public Map<String, String[]> getSecondRowNotesMap() {
        return secondRowNotesMap;
    }

    public void setSecondRowNotesMap(Map<String, String[]> secondRowNotesMap) {
        this.secondRowNotesMap = secondRowNotesMap;
    }

    public Map<String, String[]> getThirdRowNotesMap() {
        return thirdRowNotesMap;
    }

    public void setThirdRowNotesMap(Map<String, String[]> thirdRowNotesMap) {
        this.thirdRowNotesMap = thirdRowNotesMap;
    }
    
    public int getHistProjYear() {
        return histProjYear;
    }

    public void setHistProjYear(int histProjYear) {
        this.histProjYear = histProjYear;
    }

    public int getHistProjPeriod() {
        return histProjPeriod;
    }

    public void setHistProjPeriod(int histProjPeriod) {
        this.histProjPeriod = histProjPeriod;
    }

    public int getHistProjMonth() {
        return histProjMonth;
    }

    public void setHistProjMonth(int histProjMonth) {
        this.histProjMonth = histProjMonth;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageOffSet() {
        return pageOffSet;
    }

    public void setPageOffSet(int pageOffSet) {
        this.pageOffSet = pageOffSet;
    }

    public boolean isAdjust() {
        return adjust;
    }

    public void setAdjust(boolean adjust) {
        this.adjust = adjust;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public String getNdc9LevelNo() {
        return ndc9LevelNo;
    }

    public void setNdc9LevelNo(String ndc9LevelNo) {
        this.ndc9LevelNo = ndc9LevelNo;
    }

    public String getMedicaidSelectedNdc() {
        return medicaidSelectedNdc;
    }

    public void setMedicaidSelectedNdc(String medicaidSelectedNdc) {
        this.medicaidSelectedNdc = medicaidSelectedNdc;
    }

    public HelperDTO getNdcWSdto() {
        return ndcWSdto;
    }

    public void setNdcWSdto(HelperDTO ndcWSdto) {
        this.ndcWSdto = ndcWSdto;
    }

    public HelperDTO getBrandWSdto() {
        return brandWSdto;
    }

    public void setBrandWSdto(HelperDTO brandWSdto) {
        this.brandWSdto = brandWSdto;
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

    public boolean isExcel() {
        return excel;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }
   public HelperDTO getBrand() {
        return brand;
    }

    public void setBrand(HelperDTO brand) {
        this.brand = brand;
    }

    public boolean isBrandSeclected() {
        return brandSeclected;
    }

    public void setBrandSeclected(boolean brandSeclected) {
        this.brandSeclected = brandSeclected;
    }

    public String getNewFormulation() {
        return newFormulation;
    }

    public void setNewFormulation(String newFormulation) {
        this.newFormulation = newFormulation;
    }

    public String getBaeselineAmp() {
        return baeselineAmp;
    }

    public void setBaeselineAmp(String baeselineAmp) {
        this.baeselineAmp = baeselineAmp;
    }

    public String getBaeselineCpi() {
        return baeselineCpi;
    }

    public void setBaeselineCpi(String baeselineCpi) {
        this.baeselineCpi = baeselineCpi;
    }

    public String getNewFormulationItemId() {
        return newFormulationItemId;
    }

    public void setNewFormulationItemId(String newFormulationItemId) {
        this.newFormulationItemId = newFormulationItemId;
    }
      public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public Map<String, String> getLoadPriceActualMap() {
        return loadUraPriceActualMap;
    }

    public void setLoadPriceActualMap(Map<String, String> loadUraPriceActualMap) {
        this.loadUraPriceActualMap = loadUraPriceActualMap;
    }

    public Map<String, String> getLoadUraPriceMap() {
        return loadUraPriceMap;
    }

    public void setLoadUraPriceMap(Map<String, String> loadUraPriceMap) {
        this.loadUraPriceMap = loadUraPriceMap;
    }

    public Map<String, String> getLoadPhsPriceActualMap() {
        return loadPhsPriceActualMap;
    }

    public void setLoadPhsPriceActualMap(Map<String, String> loadPhsPriceActualMap) {
        this.loadPhsPriceActualMap = loadPhsPriceActualMap;
    }

    public Map<String, String> getLoadPhsPriceMap() {
        return loadPhsPriceMap;
    }

    public void setLoadPhsPriceMap(Map<String, String> loadPhsPriceMap) {
        this.loadPhsPriceMap = loadPhsPriceMap;
    }

    public Map<String,String[]> getFourthRowNotesMap() {
        return fourthRowNotesMap;
    }

    public void setFourthRowNotesMap(Map<String,String[]> fourthRowNotesMap) {
        this.fourthRowNotesMap = fourthRowNotesMap;
    }

    public String getNdc9Value() {
        return ndc9Value;
    }

    public void setNdc9Value(String ndc9Value) {
        this.ndc9Value = ndc9Value;
    }
    
  }