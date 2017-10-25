/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.index.logic;

import com.stpl.app.gcm.common.AppDataUtils;
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
import static com.stpl.app.gcm.tp.ui.form.CustomerSelection.getUserName;
import com.stpl.app.gcm.util.CommonUtils;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.util.HelperDTO;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.stpl.app.gcm.util.Constants.IndicatorConstants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
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
    Map<String, String> componentMap = new HashMap<String, String>();
    Map<String, String> ifpMap = new HashMap<String, String>();
    Map<String, String> psMap = new HashMap<String, String>();
     Map<Integer, String> users = new HashMap<Integer, String>();
        String userid= "";

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
        comboBox.setPageLength(NumericConstants.SEVEN);
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
         users = getUserName();
                for (Map.Entry<Integer, String> entry : users.entrySet()) {
                    if (entry.getValue().contains("ETL")) {
                        userid = entry.getKey().toString();
                    }
                }
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
            input.add("LIKE '%'");
        } else {
            if (binderDto.getPlaceHolder_DTO() != null && binderDto.getPlaceHolder_DTO().getId() != NumericConstants.ELEVEN) {
                String placehold = String.valueOf(binderDto.getPlaceHolder_DTO().getId());
                if(placehold.contains("0"))
                {
                    input.add("! = '"+userid+"'");
                }
                else
                {
                     input.add("= '"+userid+"'");
                }
            } else {
                input.add("LIKE '%'");
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
                if (!filterMap.get("placeHolder").equals("NumericConstants.ELEVEN")) {
                    String placehold = String.valueOf(filterMap.get("placeHolder"));
                    if (placehold.contains("0")) {
                        input.add("! = '" + userid + "'");
                    } else {
                        input.add("= '" + userid + "'");
                    }
                }
            } else {
                input.add("LIKE '%'");
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
            addList(input, NumericConstants.FOURTEEN);
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


    public int configureLevelCount(SelectionDTO projSelDTO) {
        List input = new ArrayList();
        Map<Integer, Map<String, String>> idMainMap = new HashMap<Integer, Map<String, String>>(projSelDTO.getIdMainMap());
        Map<String, String> idMap = idMainMap.get(projSelDTO.getLevelNo());
        if (projSelDTO.getLevelNo() == 1) {
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == NumericConstants.TWO) {
            input.add(idMap.get("Contract"));
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == NumericConstants.THREE) {
            input.add(idMap.get("Company"));
            input.add(idMap.get("Contract"));
            input.add(projSelDTO.getProjectionId());
        } else if (projSelDTO.getLevelNo() == NumericConstants.FOUR) {
            input.add(idMap.get("Company"));
            input.add(idMap.get("Contract"));
            if (projSelDTO.getSummaryScreen().equals("SalesSummary")) {
                input.add(projSelDTO.getProjectionId());
                input.add(idMap.get("brand"));
            } else {
                input.add(idMap.get("brand"));
                input.add(projSelDTO.getProjectionId());
            }
        } else if (projSelDTO.getLevelNo() == NumericConstants.FIVE) {
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
            inputList = getComponentLookUpSelection(binderDto, filters);
        } else {
            inputList = getComponentSearchSelection(binderDto, selection);
        }
        List<Object[]> list = ItemQueries.getItemData(inputList, selection.getComponentCount(), null);
        return getCount(list);
    }

    public List<ComponentLookUpDTO> getComponentResults(final ComponentLookUpDTO binderDto, final SelectionDTO selection, Set<Container.Filter> filters) {
        List<Object[]> list = ItemQueries.getItemData(getComponentLookUpSelection(binderDto, filters), selection.getComponentLoad(), null);
        List<ComponentLookUpDTO> finalResult = new ArrayList<ComponentLookUpDTO>();
        for (Object[] str : list) {
            ComponentLookUpDTO dto = new ComponentLookUpDTO();
            dto.setComponentId(str[0] == null ? StringUtils.EMPTY : String.valueOf(str[0]));
            dto.setComponentNo(str[1] == null ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setComponentName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setComponentStatus(str[NumericConstants.THREE] == null || str[NumericConstants.THREE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setComponentType(str[NumericConstants.FOUR] == null || str[NumericConstants.FOUR].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setMasterSid(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
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
            dto.setComponentName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setComponentType(str[NumericConstants.THREE] == null || str[NumericConstants.THREE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setCategory(str[NumericConstants.FOUR] == null || str[NumericConstants.FOUR].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setDesignation(str[NumericConstants.FIVE] == null || str[NumericConstants.FIVE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setPlanId(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setPlanName(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setComponentStatus(str[NumericConstants.EIGHT] == null || str[NumericConstants.EIGHT].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setTradeClass(str[NumericConstants.NINE] == null || str[NumericConstants.NINE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINE]));
            dto.setStartDate(str[NumericConstants.TEN] == null ? new Date() : (Date) (str[NumericConstants.TEN]));
            dto.setEndDate(str[NumericConstants.ELEVEN] == null ? new Date() : (Date) (str[NumericConstants.ELEVEN]));
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
            dto.setComponentName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setComponentType(str[NumericConstants.THREE] == null || str[NumericConstants.THREE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setCategory(str[NumericConstants.FOUR] == null || str[NumericConstants.FOUR].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setDesignation(str[NumericConstants.FIVE] == null || str[NumericConstants.FIVE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setPlanId(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setPlanName(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setComponentStatus(str[NumericConstants.EIGHT] == null || str[NumericConstants.EIGHT].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setStartDate(str[NumericConstants.NINE] == null ? new Date() : (Date) (str[NumericConstants.NINE]));
            dto.setEndDate(str[NumericConstants.TEN] == null ? new Date() : (Date) (str[NumericConstants.TEN]));
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
            dto.setComponentName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setCategory(str[NumericConstants.THREE] == null || str[NumericConstants.THREE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setComponentType(str[NumericConstants.FOUR] == null || str[NumericConstants.FOUR].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setDesignation(str[NumericConstants.FIVE] == null || str[NumericConstants.FIVE].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setParentPsId(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setParentPsName(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setComponentStatus(str[NumericConstants.EIGHT] == null || str[NumericConstants.EIGHT].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setStartDate(str[NumericConstants.NINE] == null ? new Date() : (Date) (str[NumericConstants.NINE]));
            dto.setEndDate(str[NumericConstants.TEN] == null ? new Date() : (Date) (str[NumericConstants.TEN]));
            dto.setTradeClass(str[NumericConstants.ELEVEN] == null || str[NumericConstants.ELEVEN].equals(IndicatorConstants.SELECT_ONE.getConstant()) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private List getComponentLookUpSelection(ComponentLookUpDTO binderDto, Set<Container.Filter> filters) {
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
                    addList(input, NumericConstants.FIVE);
                }
            }
        } else {
            addList(input, NumericConstants.FIVE);
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
        input.add(NumericConstants.TWELVE);
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
            int priceType = obj[NumericConstants.TWO] == null ? 0 : (Integer) obj[NumericConstants.TWO];
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
            dto.setSystemId(str[0].toString());
            dto.setCompany(str[1] == null || str[1].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[1]));
            dto.setItemId(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
            dto.setItemNo(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
            dto.setItemName(str[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
            dto.setItemDesc(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
            dto.setTherapeuticClass(str[NumericConstants.SIX] == null || str[NumericConstants.SIX].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
            dto.setBrand(str[NumericConstants.SEVEN] == null || str[NumericConstants.SEVEN].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
            dto.setForm(str[NumericConstants.EIGHT] == null || str[NumericConstants.EIGHT].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT]));
            dto.setStrength(str[NumericConstants.NINE] == null || str[NumericConstants.NINE].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.NINE]));
            dto.setPlaceHolder((Boolean) str[NumericConstants.TEN] == false ? String.valueOf("No") : String.valueOf("Yes"));
            dto.setNdc9(str[NumericConstants.ELEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ELEVEN]));
            dto.setItemCategory(str[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : str[NumericConstants.TWELVE].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWELVE]));
            dto.setItemType(str[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : str[NumericConstants.THIRTEEN].equals(Constants.SELECT_ONE) ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTEEN]));
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
            forecastingType = String.valueOf(obj[NumericConstants.THREE]);
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
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
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
