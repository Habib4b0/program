/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.globalchange.dto;

import com.stpl.app.gcm.itemmanagement.index.dto.ForecastDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemComponentDetailsDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemContractSelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.form.ItemManagementLookup;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Srithar
 */
public class SelectionDTO implements Cloneable {

    private Integer company;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private Integer therapeuticClass;
    private Integer form;
    private Integer identifierType;
    private String itemDesc = StringUtils.EMPTY;
    private Integer brand;
    private Integer strength;
    private String identifier = StringUtils.EMPTY;
    private Integer placeHolder;
    private String ndc9 = StringUtils.EMPTY;
    private Integer itemCategory;
    private Integer itemType;
    private boolean isAddMode;
    private boolean isEditMode;
    private boolean isDeleteMode;
    private boolean isIFP;
    private String buttonMode = StringUtils.EMPTY;
    private String componentScreen = StringUtils.EMPTY;
    private Set<ItemContractSelectionDTO> contractList = new HashSet<>();
    private Integer projectionId = 0;
    private ForecastDTO forecastDTO;
    private Map<String, List> frequencyAndQuater = new HashMap<>();
    private Map<String, Map<String, Integer>> historyEndDetails = new HashMap<>();
    private Integer levelNo = 0;
    private String tableName = StringUtils.EMPTY;
    private Integer itemMasterSid = 0;
    private String forecastingType = StringUtils.EMPTY;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private String countFlag = StringUtils.EMPTY;
    private Map<Integer, Map<String, String>> idMainMap = new HashMap<>();
    private String comapnyMasterSid = StringUtils.EMPTY;
    private String contractMasterSid = StringUtils.EMPTY;
    private String brandMasterSid = StringUtils.EMPTY;
    private String itemSid = StringUtils.EMPTY;
    private String summaryScreen = StringUtils.EMPTY;
    private SessionDTO sessionDTO = new SessionDTO();
    private String forecastTableName = StringUtils.EMPTY;
    private String forecastSales = StringUtils.EMPTY;
    private String forecastUnits = StringUtils.EMPTY;
    private String forecastRate = StringUtils.EMPTY;
    private String forecastAmount = StringUtils.EMPTY;
    private Set<ItemComponentDetailsDTO> selectedItemList = new HashSet<>();
    private Integer sessionId = 0;
    private Date sessionDate;
    private Integer userId;
    private String internalSessionid = StringUtils.EMPTY;
    private boolean reset;
    private String componentCount = StringUtils.EMPTY;
    private String componentLoad = StringUtils.EMPTY;
    private String component = StringUtils.EMPTY;
    private String countQueryName = StringUtils.EMPTY;
    private String dataQueryName = StringUtils.EMPTY;
    private String subModuleName = StringUtils.EMPTY;
    private Integer contractSid = 0;
    private Integer companySid = 0;
    private Integer cfpContractSid = 0;
    private Integer ifpConteractSid = 0;
    private Integer psContractSid = 0;
    private Integer rsContractSid = 0;
    private Integer componentSid = 0;
    private ItemManagementLookup lookup;
    private boolean isSubmitted;
    private Set<Container.Filter> filters;
    private String operation = StringUtils.EMPTY;
    private List<ItemIndexDto> transferItemList = new ArrayList<>();
    private  List<ItemIndexDto> itemList = new ArrayList<>();
    private String windowName = StringUtils.EMPTY;
    private boolean isTransfer;
    private Integer summaryProjectionId = 0;
    private TabSelectionDTO tabSelection;
    private List transterTabItemIds = new ArrayList();
    private List transterItemIds = new ArrayList();
    private boolean isContractUpdate = false;
    private boolean isMassPopulate = true;
    private boolean isItemAddTab=false;
    private String transferScreenName ;
    private List<Thread> threadList = new ArrayList();

    public SelectionDTO() {
        super();
    }
    
    
    
    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public List getTransterTabItemIds() {
        return transterTabItemIds == null ? transterTabItemIds : new ArrayList<>(transterTabItemIds);
    }

    public void setTransterTabItemIds(List transterTabItemIds) {
        this.transterTabItemIds = transterTabItemIds == null ? transterTabItemIds : new ArrayList<>(transterTabItemIds);
    }
    
    public TabSelectionDTO getTabSelection() {
        return tabSelection;
    }

    public void setTabSelection(TabSelectionDTO tabSelection) {
        this.tabSelection = tabSelection;
    }

    public Integer getSummaryProjectionId() {
        return summaryProjectionId;
    }

    public void setSummaryProjectionId(Integer summaryProjectionId) {
        this.summaryProjectionId = summaryProjectionId;
    }

    public boolean isIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public ItemManagementLookup getLookup() {
        return lookup;
    }

    public void setLookup(ItemManagementLookup lookup) {
        this.lookup = lookup;
    }

    public String getButtonMode() {
        return buttonMode;
    }

    public void setButtonMode(String buttonMode) {
        this.buttonMode = buttonMode;
    }

    public String getInternalSessionid() {
        return internalSessionid;
    }

    public void setInternalSessionid(String internalSessionId) {
        this.internalSessionid = internalSessionId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Date getSessionDate() {
        return sessionDate == null ? null : (Date) sessionDate.clone();
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate == null ? null : (Date) sessionDate.clone();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isIsAddMode() {
        return isAddMode;
    }

    public void setIsAddMode(boolean isAddMode) {
        this.isAddMode = isAddMode;
    }

    public boolean isIsEditMode() {
        return isEditMode;
    }

    public void setIsEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;

    }

    public boolean isIsDeleteMode() {
        return isDeleteMode;
    }

    public void setIsDeleteMode(boolean isDeleteMode) {
        this.isDeleteMode = isDeleteMode;

    }

    public boolean isIsIFP() {
        return isIFP;
    }

    public void setIsIFP(boolean isIFP) {
        this.isIFP = isIFP;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getTherapeuticClass() {
        return therapeuticClass;
    }

    public void setTherapeuticClass(Integer therapeuticClass) {
        this.therapeuticClass = therapeuticClass;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Integer getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(Integer identifierType) {
        this.identifierType = identifierType;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(Integer placeHolder) {
        this.placeHolder = placeHolder;
    }

    public String getNdc9() {
        return ndc9;
    }

    public void setNdc9(String ndc9) {
        this.ndc9 = ndc9;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Set<ItemContractSelectionDTO> getContractList() {
        return contractList == null ? contractList : Collections.unmodifiableSet(contractList);
    }

    public void setContractList(Set<ItemContractSelectionDTO> contractList) {
        this.contractList = contractList == null ? contractList : Collections.unmodifiableSet(contractList);
    }

    public Integer getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Integer projectionId) {
        this.projectionId = projectionId;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(Integer itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getForecastingType() {
        return forecastingType;
    }

    public void setForecastingType(String forecastingType) {
        this.forecastingType = forecastingType;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public String getCountFlag() {
        return countFlag;
    }

    public void setCountFlag(String countFlag) {
        this.countFlag = countFlag;
    }

    public Map<Integer, Map<String, String>> getIdMainMap() {
        return idMainMap;
    }

    public void setIdMainMap(Map<Integer, Map<String, String>> idMainMap) {
        this.idMainMap = idMainMap;
    }

    public String getComapnyMasterSid() {
        return comapnyMasterSid;
    }

    public void setComapnyMasterSid(String comapnyMasterSid) {
        this.comapnyMasterSid = comapnyMasterSid;
    }

    public String getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(String contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public String getItemSid() {
        return itemSid;
    }

    public void setItemSid(String itemSid) {
        this.itemSid = itemSid;
    }

    public String getSummaryScreen() {
        return summaryScreen;
    }

    public void setSummaryScreen(String summaryScreen) {
        this.summaryScreen = summaryScreen;
    }

    public com.stpl.app.gcm.sessionutils.SessionDTO getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(com.stpl.app.gcm.sessionutils.SessionDTO sessionDTO) {
        this.sessionDTO = sessionDTO;
    }

    public String getForecastTableName() {
        return forecastTableName;
    }

    public void setForecastTableName(String forecastTableName) {
        this.forecastTableName = forecastTableName;
    }

    public String getForecastSales() {
        return forecastSales;
    }

    public void setForecastSales(String forecastSales) {
        this.forecastSales = forecastSales;
    }

    public String getForecastUnits() {
        return forecastUnits;
    }

    public void setForecastUnits(String forecastUnits) {
        this.forecastUnits = forecastUnits;
    }

    public String getForecastRate() {
        return forecastRate;
    }

    public void setForecastRate(String forecastRate) {
        this.forecastRate = forecastRate;
    }

    public String getForecastAmount() {
        return forecastAmount;
    }

    public void setForecastAmount(String forecastAmount) {
        this.forecastAmount = forecastAmount;
    }

    public Set<ItemComponentDetailsDTO> getSelectedItemList() {
        return selectedItemList == null ? selectedItemList : Collections.unmodifiableSet(selectedItemList);
    }

    public void setSelectedItemList(Set<ItemComponentDetailsDTO> selectedItemList) {
        this.selectedItemList = selectedItemList == null ? selectedItemList : Collections.unmodifiableSet(selectedItemList); 
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public String getComponentCount() {
        return componentCount;
    }

    public void setComponentCount(String componentCount) {
        this.componentCount = componentCount;
    }

    public String getComponentLoad() {
        return componentLoad;
    }

    public void setComponentLoad(String componentLoad) {
        this.componentLoad = componentLoad;
    }

    public String getComponentScreen() {
        return componentScreen;
    }

    public void setComponentScreen(String componentScreen) {
        this.componentScreen = componentScreen;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getCountQueryName() {
        return countQueryName;
    }

    public void setCountQueryName(String countQueryName) {
        this.countQueryName = countQueryName;
    }

    public String getDataQueryName() {
        return dataQueryName;
    }

    public void setDataQueryName(String dataQueryName) {
        this.dataQueryName = dataQueryName;
    }

    public Integer getContractSid() {
        return contractSid;
    }

    public void setContractSid(Integer contractSid) {
        this.contractSid = contractSid;
    }

    public Integer getCompanySid() {
        return companySid;
    }

    public void setCompanySid(Integer companySid) {
        this.companySid = companySid;
    }

    public Integer getCfpContractSid() {
        return cfpContractSid;
    }

    public void setCfpContractSid(Integer cfpContractSid) {
        this.cfpContractSid = cfpContractSid;
    }

    public Integer getIfpConteractSid() {
        return ifpConteractSid;
    }

    public void setIfpConteractSid(Integer ifpConteractSid) {
        this.ifpConteractSid = ifpConteractSid;
    }

    public Integer getPsContractSid() {
        return psContractSid;
    }

    public void setPsContractSid(Integer psContractSid) {
        this.psContractSid = psContractSid;
    }

    public Integer getRsContractSid() {
        return rsContractSid;
    }

    public void setRsContractSid(Integer rsContractSid) {
        this.rsContractSid = rsContractSid;
    }

    public Integer getComponentSid() {
        return componentSid;
    }

    public void setComponentSid(Integer componentSid) {
        this.componentSid = componentSid;
    }

    public Map<String, List> getFrequencyAndQuater() {
        return frequencyAndQuater;
    }

    public void setFrequencyAndQuater(Map<String, List> frequencyAndQuater) {
        this.frequencyAndQuater = frequencyAndQuater;
    }

    public List getFrequencyAndQuaterValue(String frequency) {
        return frequencyAndQuater.get(frequency);
    }

    public void addFrequencyAndQuater(String frequency, List historyCount) {
        this.frequencyAndQuater.put(frequency, historyCount);
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

    public ForecastDTO getForecastDTO() {
        return forecastDTO;
    }

    public void setForecastDTO(ForecastDTO forecastDTO) {
        this.forecastDTO = forecastDTO;
    }

    public Set<Container.Filter> getFilters() {
        return filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? filters : Collections.unmodifiableSet(filters);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<ItemIndexDto> getTransferItemList() {
        return transferItemList == null ? transferItemList : Collections.unmodifiableList(transferItemList);
    }

    public void setTransferItemList(List<ItemIndexDto> transferItemList) {
        this.transferItemList = transferItemList == null ? transferItemList : Collections.unmodifiableList(transferItemList);
    }

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }

    public boolean isIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(boolean isTransfer) {
        this.isTransfer = isTransfer;
    }

    public List<ItemIndexDto> getItemList() {
        return itemList == null ? itemList : Collections.unmodifiableList(itemList);
    }

    public void setItemList(List<ItemIndexDto> itemList) {
        this.itemList = itemList == null ? itemList : Collections.unmodifiableList(itemList);
    }

    public boolean isIsContractUpdate() {
        return isContractUpdate;
    }

    public void setIsContractUpdate(boolean isContractUpdate) {
        this.isContractUpdate = isContractUpdate;
    }

    public boolean isIsMassPopulate() {
        return isMassPopulate;
    }

    public void setIsMassPopulate(boolean isMassPopulate) {
        this.isMassPopulate = isMassPopulate;
    }

    public List getTransterItemIds() {
        return transterItemIds == null ? transterItemIds : new ArrayList<>(transterItemIds);
    }

    public void setTransterItemIds(List transterItemIds) {
        this.transterItemIds = transterItemIds == null ? transterItemIds : new ArrayList<>(transterItemIds);
    }

    public List<Thread> getThreadList() {
        return threadList == null ? threadList : new ArrayList<>(threadList);
    }

    public void setThreadList(List<Thread> threadList) {
        this.threadList = threadList == null ? threadList : new ArrayList<>(threadList);
    }
    
     public void addToThreadList(Thread threadList) {
        this.threadList.add(threadList);
    }

    @Override
    public SelectionDTO clone() throws CloneNotSupportedException {
        SelectionDTO selection = (SelectionDTO) super.clone();
        selection.setCompany(company);
        selection.setItemId(itemId);
        selection.setItemNo(itemNo);
        selection.setItemName(itemName);
        selection.setTherapeuticClass(therapeuticClass);
        selection.setForm(form);
        selection.setIdentifierType(identifierType);
        selection.setItemDesc(itemDesc);
        selection.setBrand(brand);
        selection.setStrength(strength);
        selection.setIdentifier(identifier);
        selection.setPlaceHolder(placeHolder);
        selection.setNdc9(ndc9);
        selection.setItemCategory(itemCategory);
        selection.setItemType(itemType);
        selection.setIsAddMode(isAddMode);
        selection.setIsEditMode(isEditMode);
        selection.setIsDeleteMode(isDeleteMode);
        selection.setIsIFP(isIFP);
        selection.setContractList(new HashSet<>(contractList));
        selection.setProjectionId(projectionId);
        selection.setLevelNo(levelNo);
        selection.setTableName(tableName);
        selection.setItemMasterSid(itemMasterSid);
        selection.setForecastingType(forecastingType);
        selection.setStartIndex(startIndex);
        selection.setOffSet(offSet);
        selection.setCountFlag(countFlag);
        selection.setIdMainMap(new HashMap<>(idMainMap));
        selection.setComapnyMasterSid(comapnyMasterSid);
        selection.setContractMasterSid(contractMasterSid);
        selection.setBrandMasterSid(brandMasterSid);
        selection.setItemSid(itemSid);
        selection.setSummaryScreen(summaryScreen);
        selection.setSessionDTO(sessionDTO);
        selection.setForecastTableName(forecastTableName);
        selection.setForecastSales(forecastSales);
        selection.setForecastUnits(forecastUnits);
        selection.setForecastAmount(forecastAmount);
        selection.setForecastRate(forecastRate);
        selection.setSelectedItemList(new HashSet<>(selectedItemList));
        selection.setSessionId(sessionId);
        selection.setUserId(userId);
        selection.setSessionDate(sessionDate);
        selection.setReset(reset);
        selection.setComponentLoad(componentLoad);
        selection.setComponentCount(componentCount);
        selection.setInternalSessionid(internalSessionid);
        selection.setComponentScreen(componentScreen);
        selection.setComponent(component);
        selection.setCountQueryName(countQueryName);
        selection.setDataQueryName(dataQueryName);
        selection.setContractSid(contractSid);
        selection.setCompanySid(companySid);
        selection.setCfpContractSid(cfpContractSid);
        selection.setIfpConteractSid(ifpConteractSid);
        selection.setPsContractSid(psContractSid);
        selection.setRsContractSid(rsContractSid);
        selection.setComponentSid(componentSid);
        selection.setFilters(filters);
        selection.setOperation(operation);
        selection.setTransferItemList(transferItemList);
        selection.setWindowName(windowName);
        selection.setSummaryProjectionId(summaryProjectionId);
        selection.setItemList(itemList);
        selection.setIsContractUpdate(isContractUpdate);
        selection.setIsMassPopulate(isMassPopulate);
        selection.setTransferScreenName(transferScreenName);
        return selection;
    }

    public boolean isIsItemAddTab() {
        return isItemAddTab;
    }

    public void setIsItemAddTab(boolean isItemAddTab) {
        this.isItemAddTab = isItemAddTab;
    }

    public String getTransferScreenName() {
        return transferScreenName;
    }

    public void setTransferScreenName(String transferScreenName) {
        this.transferScreenName = transferScreenName;
    }
    
}