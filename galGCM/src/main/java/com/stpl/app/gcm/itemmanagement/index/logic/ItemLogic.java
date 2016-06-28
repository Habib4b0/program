/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.logic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractFilter;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.DdlbCriteria;
import com.stpl.app.gcm.itemmanagement.itemabstract.lazyload.LoadDdlbDAO;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import static com.stpl.app.gcm.itemmanagement.remove.logic.RemoveItemLogic.getProjectionQuery;

import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.ComboBox;
import java.util.Date;
import org.vaadin.addons.lazycontainer.LazyContainer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Srithar
 */
public class ItemLogic {

    HelperDTO ddlbDefaultValue = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    HelperDTO ddlbShowAllValue = new HelperDTO(0, Constants.SHOW_ALL);
    private static final DecimalFormat AMOUNT_SALES = new DecimalFormat("$#,##0");
    private static final DecimalFormat AMOUNT_UNITS = new DecimalFormat("#,##0");
    private static final DecimalFormat RATE_PER = new DecimalFormat("####.00");
    Map<String, String> componentMap = new HashMap<String, String>();
    Map<String, String> ifpMap = new HashMap<String, String>();
    Map<String, String> psMap = new HashMap<String, String>();

    public void setCfpSearch() {
        componentMap = new HashMap<String, String>();
        componentMap.put("componentId", "CM.CFP_ID");
        componentMap.put("componentNo", " CM.CFP_NO");
        componentMap.put("componentName", "CM.CFP_NAME");
        componentMap.put("componentType", "TYP.DESCRIPTION");
        componentMap.put("category", "CAT.DESCRIPTION");
        componentMap.put("designation", "CM.CFP_DESIGNATION");
        componentMap.put("componentStatus", "ST.HELPER_TABLE_SID");
        componentMap.put("tradeClass", "TC.DESCRIPTION");
        componentMap.put(Constants.START_DATE, "CM.CFP_START_DATE");
        componentMap.put(Constants.END_DATE, "CM.CFP_END_DATE");

    }

    public void setIfpSearch() {
        ifpMap = new HashMap<String, String>();
        ifpMap.put("componentId", "CM.IFP_ID");
        ifpMap.put("componentNo", "CM.IFP_NO");
        ifpMap.put("componentName", "CM.IFP_NAME");
        ifpMap.put("componentType", "TYP.DESCRIPTION");
        ifpMap.put("category", "CAT.DESCRIPTION");
        ifpMap.put("designation", "CM.IFP_DESIGNATION");
        ifpMap.put("componentStatus", "ST.HELPER_TABLE_SID");
        ifpMap.put(Constants.START_DATE, "CM.IFP_START_DATE");
        ifpMap.put(Constants.END_DATE, "CM.IFP_START_DATE");

    }

    public void setPsSearch() {
        psMap = new HashMap<String, String>();
        psMap.put("componentId", "PS.PS_ID");
        psMap.put("componentNo", "PS.PS_NO");
        psMap.put("componentName", "PS.PS_NAME");
        psMap.put("componentType", "TYP.DESCRIPTION");
        psMap.put("category", "CAT.DESCRIPTION");
        psMap.put("designation", "desig.HELPER_TABLE_SID");
        psMap.put("parentPsId", "PS.PARENT_PS_ID");
        psMap.put("PS.PARENT_PS_NAME", "ST.HELPER_TABLE_SID");
        psMap.put("componentStatus", "ST.HELPER_TABLE_SID");
        psMap.put("tradeClass", "TC.DESCRIPTION");
        psMap.put(Constants.START_DATE, "PS.PS_START_DATE");
        psMap.put(Constants.END_DATE, "PS.PS_END_DATE ");

    }

    public void LazyLoadDdlb(final ComboBox comboBox, String countFlag, String findFlag, boolean isFilter) {
        final List inputList = new ArrayList();
        inputList.add(countFlag);
        inputList.add(findFlag);
        LazyContainer containerData = new LazyContainer(HelperDTO.class, new LoadDdlbDAO(inputList, true), new DdlbCriteria());
        comboBox.setPageLength(7);
        comboBox.setContainerDataSource(containerData);
        if (isFilter) {
            comboBox.setNullSelectionItemId(ddlbShowAllValue);
        } else {
            comboBox.setNullSelectionItemId(ddlbDefaultValue);
        }
        comboBox.setNullSelectionAllowed(true);
        comboBox.setImmediate(true);
        comboBox.setItemCaptionPropertyId("description");
        containerData.setMinFilterLength(0);
    }

    public int getDdlbCount(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public List<HelperDTO> getDdlbList(String QueryName, final List<String> input) {
        List<Object[]> list = ItemQueries.getItemData(input, QueryName, null);
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        if (Integer.valueOf(String.valueOf(input.get(1))) == 0) {
            HelperDTO defaultValue = new HelperDTO(0, IndicatorConstants.SELECT_ONE.getConstant());
            resultList.add(defaultValue);
        }
        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? Constants.ZEROSTRING : String.valueOf(str[1]));
            resultList.add(dto);
        }
        return resultList;
    }

    public int getSearchCount(ItemIndexDto binderDto, SelectionDTO selection) {
        String queryName;
        queryName = "Load Search Results Count";
        List<Object[]> list = ItemQueries.getItemData(getSearchSelection(binderDto, selection), queryName, null);
        return getCount(list);
    }

    public List getSearchSelection(ItemIndexDto binderDto, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        if (binderDto.getIdentifierType_DTO() != null) {
            input.add(binderDto.getIdentifierType_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getIdentifier() != null && !binderDto.getIdentifier().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getIdentifier()));
        } else {
            input.add("%");
        }
        if (binderDto.getItemId() != null && !binderDto.getItemId().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getItemId()));
        } else {
            input.add("%");
        }
        if (binderDto.getItemName() != null && !binderDto.getItemName().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getItemName()));
        } else {
            input.add("%");
        }
        if (binderDto.getTherapeuticClass() != null && !Constants.SELECT_ONE.equals(binderDto.getTherapeuticClass())) {
            input.add(binderDto.getTherapeuticClass());
        } else {
            input.add("%");
        }
        if (binderDto.getForm_DTO() != null) {
            input.add(binderDto.getForm_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getItemNo() != null && !binderDto.getItemNo().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getItemNo()));
        } else {
            input.add("%");
        }
        if (binderDto.getItemDesc() != null && !binderDto.getItemDesc().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getItemDesc()));
        } else {
            input.add("%");
        }
        if (binderDto.getBrand_DTO() != null) {
            input.add(binderDto.getBrand_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getStrength_DTO() != null) {
            input.add(binderDto.getStrength_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getNdc9() != null && !binderDto.getNdc9().isEmpty()) {
            input.add(CommonUtils.getDBinput(binderDto.getNdc9()));
        } else {
            input.add("%");
        }
        if (binderDto.getItemCategory() != null && !Constants.SELECT_ONE.equals(binderDto.getItemCategory().trim()) && !StringUtils.EMPTY.equals(binderDto.getItemCategory().trim())) {
            input.add(binderDto.getItemCategory());
        } else {
            input.add("%");
        }
        if (binderDto.getItemType() != null && !Constants.SELECT_ONE.equals(binderDto.getItemType().trim()) && !StringUtils.EMPTY.equals(binderDto.getItemType().trim())) {
            input.add(binderDto.getItemType());
        } else {
            input.add("%");
        }
        if ("No".equals(binderDto.getPlaceHolderValue())) {

            input.add(Constants.ZEROSTRING);
        } else {

            if (binderDto.getPlaceHolder_DTO() != null && binderDto.getPlaceHolder_DTO().getId() != 11) {
                input.add(binderDto.getPlaceHolder_DTO().getId());
            } else {
                input.add("%");
            }
        }

        if (binderDto.getCompany_DTO() != null) {
            input.add(binderDto.getCompany_DTO().getId());
        } else {
            input.add("%");
        }

        if (!selection.getFilters().isEmpty()) {
            Map<String, String> filterMap = getFilterMap(selection.getFilters());

            if (filterMap.get("systemId") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("systemId")));
            } else {
                input.add("%");
            }
            if (filterMap.get("company") != null) {
                input.add(filterMap.get("company"));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemId") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("itemId")));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemNo") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("itemNo")));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemName") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("itemName")));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemDesc") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("itemDesc")));
            } else {
                input.add("%");
            }
            if (filterMap.get("therapeuticClass") != null) {
                input.add(filterMap.get("therapeuticClass"));
            } else {
                input.add("%");
            }
            if (filterMap.get("brand") != null) {
                input.add(filterMap.get("brand"));
            } else {
                input.add("%");
            }
            if (filterMap.get("form") != null) {
                input.add(filterMap.get("form"));
            } else {
                input.add("%");
            }
            if (filterMap.get("strength") != null) {
                input.add(filterMap.get("strength"));
            } else {
                input.add("%");
            }
            if (filterMap.get("placeHolder") != null) {
                if (!filterMap.get("placeHolder").equals("11")) {
                    input.add(filterMap.get("placeHolder"));
                } else {
                    input.add("%");
                }
            } else {
                input.add("%");
            }
            if (filterMap.get("ndc9") != null) {
                input.add(CommonUtils.getDBinputForFilter(filterMap.get("ndc9")));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemCategory") != null) {
                input.add(filterMap.get("itemCategory"));
            } else {
                input.add("%");
            }
            if (filterMap.get("itemType") != null) {
                input.add(filterMap.get("itemType"));
            } else {
                input.add("%");
            }

        } else {
            addList(input, 14);
        }

        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }

        return input;
    }

    public Object getItemIds(List<ItemIndexDto> itemList) {

        List itemIdList = new ArrayList();
        for (ItemIndexDto dto : itemList) {
            itemIdList.add(dto.getSystemId());
        }
        return CommonUtils.getListToString(itemIdList);
    }

    public int getDetailsCount(SelectionDTO selection) {
        String query;
        if (selection.isIsAddMode()) {
            query = "Load item Results Add count";
        } else {
            query = "Load item Count";
        }
        List<Object[]> list = ItemQueries.getItemData(getResultsInput(selection), query, null);
        return getCount(list);
    }

    private int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    private List getDetailsInput(int start, int offset, SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getInternalSessionid());
        input.add(start);
        input.add(offset);
        return input;
    }

    private void printList(List<Object[]> list) {
        for (int i = 0; i < list.size(); i++) {
            Object[] str = list.get(i);
            for (Object obj : str) {
            }
        }
    }

    public int configureLevelCount(SelectionDTO projSelDTO) {
        List input = new ArrayList();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>(projSelDTO.getIdMainMap());
        Map<String, String> idMap = idMainMap.get(projSelDTO.getLevelNo());
        if (projSelDTO.getLevelNo() == 1) {
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == 2) {
            input.add(idMap.get("Contract"));
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == 3) {
            input.add(idMap.get("Company"));
            input.add(idMap.get("Contract"));
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == 4) {
            input.add(idMap.get("Company"));
            input.add(idMap.get("Contract"));
            if (projSelDTO.getSummaryScreen().equals("SalesSummary")) {
                input.add(projSelDTO.getProjectionId());
                input.add(idMap.get("brand"));
            } else {
                input.add(idMap.get("brand"));
                input.add(projSelDTO.getProjectionId());
            }
        } else if (projSelDTO.getLevelNo() == 5) {
            input.add(idMap.get("Company"));
            input.add(idMap.get("Contract"));
            input.add(idMap.get("brand"));
            input.add(projSelDTO.getProjectionId());

        }
        List<Object[]> list = ItemQueries.getItemData(input, projSelDTO.getCountFlag(), null);
        Object obj = list.get(0);
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     * Null
     *
     * @param stringValue
     * @return String
     */
    public static String stringIsNull(Object value) {
        String stringValue = StringUtils.EMPTY + value;
        if (stringValue.contains(Constants.NULL)) {
            stringValue = Constants.ZEROSTRING;
            return stringValue;

        }
        return stringValue;
    }

    /**
     * For Format
     *
     * @param FORMAT
     * @param value
     * @return String
     */
    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constants.NULL)) {
            value = Constants.ZEROSTRING;
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public Boolean removeItemsLogic(SelectionDTO selection) {
        return ItemQueries.itemUpdate(getResultsInput(selection), "updateItemEndDate");
    }

    public int getComponentCount(final ComponentLookUpDTO binderDto, final SelectionDTO selection, Set<Container.Filter> filters) {
        List inputList = new ArrayList();
        if (selection.getComponentScreen().equalsIgnoreCase("Component Lookup")) {
            inputList = getComponentLookUpSelection(binderDto, selection, filters);
        } else {
            inputList = getComponentSearchSelection(binderDto, selection);
        }
        List<Object[]> list = ItemQueries.getItemData(inputList, selection.getComponentCount(), null);
        return getCount(list);
    }

    public List<ComponentLookUpDTO> getComponentResults(final ComponentLookUpDTO binderDto, final SelectionDTO selection, Set<Container.Filter> filters) {
        List<Object[]> list = ItemQueries.getItemData(getComponentLookUpSelection(binderDto, selection, filters), selection.getComponentLoad(), null);
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponentNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponentName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setComponentStatus(str[3] == null || str[3].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setComponentType(str[4] == null || str[4].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setMasterSid(str[5] == null ? StringUtils.EMPTY : String.valueOf(str[5]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentLookUpDTO> getComponentSearchResults(final ComponentLookUpDTO binderDto, final SelectionDTO selection) {
        List<Object[]> list = ItemQueries.getItemData(getComponentSearchSelection(binderDto, selection), selection.getComponentLoad(), null);
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        if (selection.getComponent().equalsIgnoreCase(Constants.CFP)) {
            finalResult = getCustomizedCFP(list);
        } else if (selection.getComponent().equals(Constants.IFP)) {
            finalResult = getCustomizedIFP(list);
        } else if (selection.getComponent().equals(Constants.PS)) {
            finalResult = getCustomizedPS(list);
        }
        return finalResult;
    }

    public List<ComponentLookUpDTO> getCustomizedCFP(final List<Object[]> list) {
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponentNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponentName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setComponentType(str[3] == null || str[3].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setCategory(str[4] == null || str[4].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setDesignation(str[5] == null || str[5].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setPlanId(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setPlanName(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setComponentStatus(str[8] == null || str[8].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setTradeClass(str[9] == null || str[9].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setStartDate(str[10] == null ? new Date() : (Date) (str[10]));
            dto.setEndDate(str[11] == null ? new Date() : (Date) (str[11]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentLookUpDTO> getCustomizedIFP(final List<Object[]> list) {
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponentNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponentName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setComponentType(str[3] == null || str[3].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setCategory(str[4] == null || str[4].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setDesignation(str[5] == null || str[5].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setPlanId(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setPlanName(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setComponentStatus(str[8] == null || str[8].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setStartDate(str[9] == null ? new Date() : (Date) (str[9]));
            dto.setEndDate(str[10] == null ? new Date() : (Date) (str[10]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    public List<ComponentLookUpDTO> getCustomizedPS(final List<Object[]> list) {
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponentNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponentName(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setCategory(str[3] == null || str[3].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setComponentType(str[4] == null || str[4].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setDesignation(str[5] == null || str[5].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setParentPsId(str[6] == null ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setParentPsName(str[7] == null ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setComponentStatus(str[8] == null || str[8].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setStartDate(str[9] == null ? new Date() : (Date) (str[9]));
            dto.setEndDate(str[10] == null ? new Date() : (Date) (str[10]));
            dto.setTradeClass(str[11] == null || str[11].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[11]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private List getComponentLookUpSelection(ComponentLookUpDTO binderDto, SelectionDTO selection, Set<Container.Filter> filters) {
        List<Object> input = new ArrayList();
        if (!binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (!binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (!binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getComponentType_DTO() != null) {
            input.add(binderDto.getComponentType_DTO().getId());
        } else {
            input.add("%");
        }
        if (!filters.isEmpty()) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    if (stringFilter.getPropertyId().equals("componentId")) {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        input.add(filterString);
                    } else {
                        input.add("%");
                    }
                    if (stringFilter.getPropertyId().equals("componentNo")) {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        input.add(filterString);
                    } else {
                        input.add("%");
                    }
                    if (stringFilter.getPropertyId().equals("componentName")) {
                        String filterString = "%" + stringFilter.getFilterString() + "%";
                        input.add(filterString);
                    } else {
                        input.add("%");
                    }
                    if (stringFilter.getPropertyId().equals("componentStatus")) {
                        String filterString = stringFilter.getFilterString();
                        input.add(filterString);
                    } else {
                        input.add("%");
                    }
                    if (stringFilter.getPropertyId().equals("componentType")) {
                        String filterString = stringFilter.getFilterString();
                        input.add(filterString);
                    } else {
                        input.add("%");
                    }
                } else {
                    addList(input, 5);
                }
            }
        } else {
            addList(input, 5);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    private List getComponentSearchSelection(ComponentLookUpDTO binderDto, SelectionDTO selection) {
        List<Object> input = new ArrayList();
        String startDate = StringUtils.EMPTY;
        String endDate = StringUtils.EMPTY;
        if (selection.getComponent().equals(Constants.CFP)) {
            startDate = "CM.CFP_START_DATE";
            endDate = "CM.CFP_END_DATE";
        } else if (selection.getComponent().equals(Constants.IFP)) {
            startDate = "CM.IFP_START_DATE";
            endDate = "CM.IFP_END_DATE";
        } else if (selection.getComponent().equals(Constants.PS)) {
            startDate = "PS.CFP_START_DATE";
            endDate = "PS.CFP_END_DATE";
        }
        if (!binderDto.getComponentId().isEmpty()) {
            input.add(binderDto.getComponentId().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (!binderDto.getComponentName().isEmpty()) {
            input.add(binderDto.getComponentName().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (!binderDto.getCategory().isEmpty()) {
            input.add(binderDto.getCategory().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getStartDate() != null) {
            input.add(" AND ( " + startDate + " >= '" + CommonUtils.DBDate.format(binderDto.getStartDate()) + "')");
        } else {
            input.add(" ");
        }

        if (!binderDto.getComponentNo().isEmpty()) {
            input.add(binderDto.getComponentNo().replace("*", "%"));
        } else {
            input.add("%");
        }

        if (!binderDto.getComponentType().isEmpty()) {
            input.add(binderDto.getComponentType().replace("*", "%"));
        } else {
            input.add("%");
        }
        if (binderDto.getComponentStatus_DTO() != null) {
            input.add(binderDto.getComponentStatus_DTO().getId());
        } else {
            input.add("%");
        }
        if (binderDto.getEndDate() != null) {
            input.add(" AND ( " + endDate + " <= '" + CommonUtils.DBDate.format(binderDto.getEndDate()) + "')");
        } else {
            input.add(" ");
        }
        StringBuilder sql = AbstractFilter.getInstance().lookUpSearchfilterQueryGenerator(selection.getComponent(), selection.getFilters());
        if (sql != null) {
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (binderDto.isIsCount()) {
            input.add(binderDto.getStartIndex());
            input.add(binderDto.getEndIndex());
        }
        return input;
    }

    public static List getResultsInput(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getInternalSessionid());
        return input;
    }

    public int getContractSummaryCount(SelectionDTO selection) {

        List<Object[]> list = ItemQueries.getItemData(getResultsInput(selection), "Contract Summary Count", null);
        return getCount(list);
    }

    public static void insertItemToTable(String sid, boolean isAdd, SelectionDTO selection, String constant) {
        List input = new ArrayList();
        input.add(sid);
        input.add(selection.getInternalSessionid());
        input.add(Constants.ITEM_MANAGEMENT);
        input.add(12);
        input.add(constant);
        input.add(selection.getSessionId());
        if (isAdd) {
            ItemQueries.itemUpdate(input, "inserting itemIndex table");
        } else {
            ItemQueries.itemUpdate(input, "deleting itemIndex table");
        }

    }

    public List<Integer> isMandatoryCheck(SelectionDTO selection) {
        final List input = new ArrayList();
        input.add(selection.getSessionId());
        input.add(selection.getInternalSessionid());
        final List<Integer> finalList = new ArrayList<Integer>();
        List<Object[]> list = ItemQueries.getItemData(input, "contractTempValidations", null);
        if (!list.isEmpty()) {
            Object[] obj = list.get(0);
            int startDate = obj[0] == null ? 0 : (Integer) obj[0];
            int cpStartDateCount = obj[1] == null ? 0 : (Integer) obj[1];
            int priceType = obj[2] == null ? 0 : (Integer) obj[2];
            finalList.add(startDate);
            finalList.add(cpStartDateCount);
            finalList.add(priceType);
        }
        return new ArrayList<Integer>();
    }

    private void addList(List input, Integer size) {
        for (int i = 0; i < size; i++) {
            input.add("%");
        }
    }

    public List<ItemIndexDto> getSearchResults(ItemIndexDto binderDto, SelectionDTO selection, List<ItemIndexDto> selecteditemList) {
        String queryName;
        queryName = "Load Search Results";
        List<Object[]> list = ItemQueries.getItemData(getSearchSelection(binderDto, selection), queryName, null);
        List<ItemIndexDto> finalResult = new ArrayList<ItemIndexDto>();
        for (Object[] str : list) {
            ItemIndexDto dto = new ItemIndexDto();
            dto.setSystemId((str[0].toString()));
            dto.setCompany(str[1] == null || str[1].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setItemId(str[2] == null ? StringUtils.EMPTY : String.valueOf(str[2]));
            dto.setItemNo(str[3] == null ? StringUtils.EMPTY : String.valueOf(str[3]));
            dto.setItemName(str[4] == null ? StringUtils.EMPTY : String.valueOf(str[4]));
            dto.setItemDesc(str[5] == null ? StringUtils.EMPTY : String.valueOf(str[5]));
            dto.setTherapeuticClass(str[6] == null || str[6].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[6]));
            dto.setBrand(str[7] == null || str[7].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[7]));
            dto.setForm(str[8] == null || str[8].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[8]));
            dto.setStrength(str[9] == null || str[9].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[9]));
            dto.setPlaceHolder((Boolean) str[10] == false ? String.valueOf("No") : String.valueOf("Yes"));
            dto.setNdc9(str[11] == null ? StringUtils.EMPTY : String.valueOf(str[11]));
            dto.setItemCategory(str[12] == null ? StringUtils.EMPTY : str[12].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[12]));
            dto.setItemType(str[13] == null ? StringUtils.EMPTY : str[13].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[13]));
            for (ItemIndexDto item : selecteditemList) {
                if (item.getSystemId().equals(dto.getSystemId())) {
                    dto.setCheckRecord(Boolean.TRUE);
                    break;
                } else {
                    dto.setCheckRecord(Boolean.FALSE);
                }
            }
            finalResult.add(dto);
        }
        return finalResult;

    }

    public static Map getFilterMap(Set<Container.Filter> filters) {
        Map filterMap = new HashMap();
        for (Container.Filter filter : filters) {
            if (filter instanceof SimpleStringFilter) {
                SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                String filterString = "%" + stringFilter.getFilterString() + "%";
                filterMap.put(stringFilter.getPropertyId(), filterString);
            }
        }
        return filterMap;
    }

    public static List getIdAndForecastingType(TabSelectionDTO SelectionDTO, SelectionDTO selection) {
        int projectionID = 0;
        String forecastingType = StringUtils.EMPTY;
        final List projIdList = new ArrayList();
        List<Object> list = getProjectionQuery(SelectionDTO, selection);
        Object[] obj = null;
        if (list != null && !list.isEmpty()) {
            for (Object list1 : list) {
                obj = (Object[]) list1;
            }
            projectionID = Integer.parseInt(String.valueOf(obj[0]));
            forecastingType = String.valueOf(obj[3]);
        }

        projIdList.add(projectionID);
        SelectionDTO.setSummaryProjectionId(projectionID);
        SelectionDTO.setForeCastingType(forecastingType);
        SelectionDTO.setOperation(selection.getOperation());
        SelectionDTO.setSessionID(selection.getSessionId());
        projIdList.add(forecastingType);
        return projIdList;
    }

    /**
     * Reset the editable fields on reset
     *
     * @param selection
     */
    public static void resetContractDetails(SelectionDTO selection) {
        List input = new ArrayList();
        input.add(selection.getSessionId());
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) //        input.add(selection.getInternalSessionid());
        {
            if (selection.getTransferScreenName().equals(ConstantsUtil.CURRENT_COONTRACT)) {
                input.add(ConstantsUtil.CURRENT_COONTRACT);
            } else {
                input.add(ConstantsUtil.TRANSFER_CONTRACT);
            }
        } else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            if (selection.getTransferScreenName().equals(ConstantsUtil.CURRENT_COONTRACT)) {
                input.add(ConstantsUtil.CURRENT_COONTRACT);
            } else {
                input.add(ConstantsUtil.TRANSFER_CONTRACT);
            }
        } else {
            input.add(selection.getButtonMode());
        }
        ItemQueries.itemUpdate(input, "resetContractDetails");
    }
}
