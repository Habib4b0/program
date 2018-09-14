/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;

import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.ComboBox;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author karthikeyans
 */
public class CommonUtils {

    public static final String DASH = "0";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class.getName());
    private static final CustomComparator sorter = new CustomComparator();
    /**
     * UserMap - Contains User System ID and User Name
     */
    private static Map<Integer, String> userMap = new ConcurrentHashMap<>();

    public static ComboBox loadComboBoxWithInteger(final CustomComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);

            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            Collections.sort(list, sorter);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? GlobalConstants.getSelectOne() : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return select;
    }

    public static ComboBox loadComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? GlobalConstants.getSelectOne() : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return select;
    }

    public static ComboBox loadFilterComboBoxWithIntegerForComboBox(final ComboBox select, String listName) {
        try {
            select.setImmediate(true);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, ConstantsUtils.SHOW_ALL);
            select.setNullSelectionAllowed(true);
            select.setNullSelectionItemId(NumericConstants.ZERO);
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return select;
    }

    /**
     * This method is to load transaction name of adjustment config value in
     * helper list map
     */
    public static void loadTransactionNameForCurrentSession(ComboBox comboBox, String tempTableName, Boolean isFilter) {
        List inputList = new ArrayList<>();
        inputList.add(tempTableName);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setNullSelectionItemId(0);
        comboBox.addItem(0);
        comboBox.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
        comboBox.setNullSelectionItemId(0);
        comboBox.select(0);
        comboBox.setImmediate(true);
        List<Object[]> resultsList = QueryUtils.getItemData(inputList, "Load_Transaction_names_From_Config_Details", null);
        for (int i = 0; i < resultsList.size(); i++) {
            Object[] helper = resultsList.get(i);
            comboBox.addItem(helper[0] != null ? (Integer) helper[0] : 0);
            comboBox.setItemCaption(helper[0] != null ? (Integer) helper[0] : 0, String.valueOf(helper[NumericConstants.ONE]));
        }
    }

    /**
     * This method is to load transaction name of adjustment config value in
     * helper list map
     */
    public static void loadTransactionNameForCurrentSessionFromAccount(ComboBox accountComboBox, String tempTableName, Boolean isFilter, Object accountValue) {
        List inputList = new ArrayList<>();
        inputList.add(tempTableName);
        String accouontFilterValue = StringUtils.EMPTY;
        if (accountValue != null) {
            accouontFilterValue = " WHERE ACCOUNT = '" + accountValue + ARMUtils.SINGLE_QUOTES;
        }
        inputList.add(accouontFilterValue);
        accountComboBox.removeAllItems();
        accountComboBox.setImmediate(true);
        accountComboBox.setNullSelectionAllowed(true);
        accountComboBox.setNullSelectionItemId(0);
        accountComboBox.addItem(0);
        accountComboBox.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
        accountComboBox.setNullSelectionItemId(0);
        accountComboBox.select(0);
        accountComboBox.setImmediate(true);
        List<Object[]> resultsList = QueryUtils.getItemData(inputList, "Load_Transaction_names_From_Config_Details_ForAccounts", null);
        for (int i = 0; i < resultsList.size(); i++) {
            Object[] helper = resultsList.get(i);
            accountComboBox.addItem(helper[0] != null ? (Integer) helper[0] : 0);
            accountComboBox.setItemCaption(helper[0] != null ? (Integer) helper[0] : 0, String.valueOf(helper[NumericConstants.ONE]));
        }
    }

    public static ComboBox loadComboBoxWithMethodologyId(final ComboBox comboBox, String tempTableName, Boolean isFilter) {
        try {
            List inputList = new ArrayList<>();
            inputList.add(tempTableName);
            comboBox.setImmediate(true);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(0);
            comboBox.addItem(0);
            comboBox.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            comboBox.setNullSelectionItemId(0);
            List<Object[]> resultsList = QueryUtils.getItemData(inputList, "loadAccount_Filter_BasedOn_Methodology", null);
            comboBox.select(0);
            comboBox.setImmediate(true);
            for (int i = 0; i < resultsList.size(); i++) {
                Object[] helper = resultsList.get(i);
                comboBox.addItem(helper[0] != null ? (Integer) helper[0] : 0);
                comboBox.setItemCaption(helper[0] != null ? (Integer) helper[0] : 0, String.valueOf(helper[NumericConstants.ONE]));
            }
        } catch (Exception e) {
            LOGGER.error("Error in loadComboBoxWithMethodologyId :", e);
        }
        return comboBox;
    }

    public static ComboBox loadDistinctAccount(final ComboBox comboBox, String tempTableName, Boolean isFilter) {
        try {
            List inputList = new ArrayList<>();
            inputList.add(tempTableName);
            String item = isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne();
            comboBox.setImmediate(true);
            comboBox.setNullSelectionAllowed(true);
            comboBox.setNullSelectionItemId(item);
            comboBox.addItem(item);
            comboBox.setNullSelectionItemId(item);
            comboBox.select(item);
            comboBox.setImmediate(true);
            List<Object[]> resultsList = QueryUtils.getItemData(inputList, "loadDistinctAccounts", null);
            for (int i = 0; i < resultsList.size(); i++) {
                Object[] helper = resultsList.get(i);
                comboBox.addItem(helper[1] != null ? String.valueOf(helper[NumericConstants.ONE]) : StringUtils.EMPTY);
            }
        } catch (Exception e) {
            LOGGER.error("Error in loadDistinctAccount :", e);
        }
        return comboBox;
    }

    public static ComboBox loadUdcComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            List input = new ArrayList<>();
            input.add(listName);
            List<Object[]> list = QueryUtils.getItemData(input, "Load_udc", null);
            List<HelperDTO> resultList = new ArrayList<>();
            if (!HelperListUtil.getInstance().getListNameMap().containsKey(listName)) {
                for (Object[] str : list) {
                    if (!str[1].equals(String.valueOf(GlobalConstants.getSelectOne()))) {
                        String description;
                        HelperDTO dto = new HelperDTO();
                        dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
                        description = str[1] == null ? ARMUtils.ZERO_STRING : String.valueOf(str[1]);
                        dto.setDescription(description);
                        resultList.add(dto);
                    }
                }
                HelperListUtil.getInstance().getListNameMap().put(listName, resultList);
            }
            for (int i = 0; i < HelperListUtil.getInstance().getListNameMap().get(listName).size(); i++) {
                HelperDTO helper = HelperListUtil.getInstance().getListNameMap().get(listName).get(i);
                select.addItem(helper.getId());
                select.setItemCaption(helper.getId(), helper.getDescription());
            }

            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? GlobalConstants.getSelectOne() : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return select;
    }

    static class CustomComparator implements Comparator<HelperDTO> {

        @Override
        public int compare(HelperDTO o1, HelperDTO o2) {
            return Integer.valueOf(o1.getId()).compareTo(o2.getId());
        }
    }

    public static CustomComboBox loadRatePeriodComboBox(final CustomComboBox select, int frequecy, String listName) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);

            select.setItemCaption(0, GlobalConstants.getSelectOne());
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            Collections.sort(list, sorter);
            if (frequecy > 1) {
                list = spilitFrequencyWise(list, frequecy);
            }
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.setDescription((String) (select.getValue() == DASH ? GlobalConstants.getSelectOne() : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return select;
    }

    private static List<HelperDTO> spilitFrequencyWise(List<HelperDTO> list, int frequecy) {
        List<Integer> ids = findMath(list);
        int min = Collections.min(ids);
        int max = Collections.max(ids);
        if (frequecy != NumericConstants.TWO) {
            min /= frequecy;
            max /= frequecy;
        } else { // Added for GAL-5682
            min = -NumericConstants.SIX;
            max = 0;
        }
        int minIndex = ids.indexOf(min);
        int maxIndex = ids.indexOf(max) + 1;
        return list.subList(minIndex, maxIndex);
    }

    private static List<Integer> findMath(List<HelperDTO> sd) {
        List<Integer> ids = new ArrayList<>();
        for (HelperDTO helperDTO : sd) {
            String desc = helperDTO.getDescription();
            int month = 0;
            if (desc.contains("+")) {
                month = Integer.valueOf(desc.substring(desc.indexOf('+') + NumericConstants.TWO));
            } else if (desc.contains("-")) {
                month = -Integer.valueOf(desc.substring(desc.indexOf('-') + NumericConstants.TWO));
            }
            ids.add(month);
        }
        return ids;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || ARMUtils.NULL.equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    public static void successNotification(final String message) {
        try {
            final Notification notif = new Notification(message,
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName("mystyle");
            notif.setDelayMsec(NumericConstants.THOUSAND);
            notif.show(Page.getCurrent());
        } catch (Exception e) {

            LOGGER.error("Error in successNotification :", e);
        }
    }

    /**
     * <h1>Before Unload function</h1>
     * <p>
     * Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is
     * unloaded from the browser , so that the data will not retained when the
     * user navigates back to same UI. <br>
     *
     * Comments about Code : Action performed after the listener is fix for the
     * Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to
     * Sesison DTO, on refresh the Id gets flushed and Screen collapse. The
     * Actions performed after the Unload listener is to maintain the required
     * ID
     *
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final CommonUI uI) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                if (uI.isExcelFlag()) { // Fix to avoid blank page issue while excel export
                    uI.setExcelFlag(Boolean.FALSE);
                } else {
                    uI.close();
                }
            }
        });

    }

    public static final CustomMenuBar.CustomMenuItem loadSummaryDeductionsDdlb(final ComboBox helperComboBox, final CustomMenuBar menuBar, final int projectionId) {
        loadComboBoxWithIntegerForComboBox(helperComboBox, "DEDUCTION_LEVELS", false);
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);
        final CustomMenuBar.CustomMenuItem customMenuItemDed = menuBar.addItem("  - Select Value -  ", null);
        helperComboBox.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (Integer.parseInt(String.valueOf(helperComboBox.getValue())) != 0) {
                    int id = (int) helperComboBox.getValue();
                    String description = helperComboBox.getItemCaption(id);
                    if (description != null) {
                        List<Object[]> list = loadDeductionsValues(projectionId, description);
                        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
                        customMenuItemDed.removeChildren();
                        for (int i = 0; i < list.size(); i++) {
                            String header = String.valueOf(list.get(i)[1]);
                            String column = header;
                            if (header.contains("~")) {
                                column = header.split("~")[0].trim();
                            }
                            header = header.replace("~", "-");
                            MenuItemDTO dto = new MenuItemDTO(column, header);
                            dto.setId(Integer.valueOf(list.get(i)[0].toString()));
                            customItem[i] = customMenuItemDed.addItem(dto, null);
                            customItem[i].setCheckable(true);
                            customItem[i].setItemClickable(true);
                            customItem[i].setItemClickNotClosable(true);
                        }
                    }
                } else {
                    customMenuItemDed.removeChildren();
                }
            }
        });
        return customMenuItemDed;
    }

    private static List<Object[]> loadDeductionsValues(int projectionId, String deductionLevel) {
        List<Object[]> valuesDes = new ArrayList<>();
        boolean isDetection = deductionLevel.equals(ARMConstants.getDeduction());
        Map<Integer, String> descriptionMap = HelperListUtil.getInstance().getIdDescMap();
        Map<String, String> summaryLevelMap = ARMUtils.loadDedutionMap();
        String deductionColumn = summaryLevelMap.get(deductionLevel);
        List<Object[]> deductionsValues;
        if (isDetection) {
            deductionsValues = QueryUtils.getItemData(new ArrayList<>(Arrays.asList(projectionId, "A.RS_MODEL_SID,A.RS_ID+' ~ '+ A.RS_NAME", "order by A.RS_ID+' ~ '+ A.RS_NAME")), "loadDeductionValue", null);
        } else {
            deductionsValues = QueryUtils.getItemData(new ArrayList<>(Arrays.asList(projectionId, deductionColumn, "")), "loadDeductionValue", null);
        }

        return customizeDeductions(deductionsValues, isDetection, descriptionMap, valuesDes);
    }

    public static final CustomMenuBar.CustomMenuItem loadAdjustmentTypeDdlb(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);
        List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), "LoadAdjustmentType", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = list.get(i);
            String column = helper[1].toString();
            String header = helper[NumericConstants.TWO].toString();
            MenuItemDTO menudto = new MenuItemDTO(column, header);
            menudto.setId((int) helper[0]);
            customItem[i] = customMenuItemDed.addItem(menudto, null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
        }
        return customMenuItemDed;
    }

    public static List<String[]> getCheckedValues(final CustomMenuBar.CustomMenuItem customMenuItem) {
        List<String[]> result = new ArrayList<>();
        if (customMenuItem != null && customMenuItem.getSize() > 0) {

            List<CustomMenuBar.CustomMenuItem> items = customMenuItem.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    result.add(new String[]{(String) customMenuItem1.getMenuItem().getWindow(), customMenuItem1.getMenuItem().getCaption(), String.valueOf(customMenuItem1.getMenuItem().getId())});
                }
            }
        }
        return result;
    }

    public static List<List> getSelectedVariables(CustomMenuBar.CustomMenuItem customMenuItem, Boolean isPropertyRequired) {
        List<List> list = new ArrayList<>(NumericConstants.THREE);
        List<Object> column = new ArrayList();
        List<Object> columnWithNoIndex = new ArrayList();
        List<String> header = new ArrayList();
        List<Integer> ids = new ArrayList();
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            int i = 0;
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.isChecked()) {
                    column.add(isPropertyRequired ? object.getMenuItem().getWindow() + "." + i : object.getMenuItem().getWindow());
                    columnWithNoIndex.add(object.getMenuItem().getWindow());
                    header.add(object.getMenuItem().getCaption());
                    ids.add(object.getMenuItem().getId());
                    i++;
                }
            }
            list.add(column);
            list.add(header);
            list.add(ids);
            list.add(columnWithNoIndex);
        }
        return list;
    }

    /**
     *
     * @param customMenuItem
     * @return
     */
    public static List<List> getSelectedVariablesforRates(CustomMenuBar.CustomMenuItem customMenuItem) {
        List<List> list = new ArrayList<>();
        List<Object> column = new ArrayList();
        List<String> header = new ArrayList();
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            int i = 0;
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.isChecked()) {
                    column.add(object.getMenuItem().getCaption().trim() + "." + i);
                    header.add(object.getMenuItem().getCaption());
                    i++;
                }
            }
            list.add(column);
            list.add(header);
        }
        return list;
    }

    public static void loadCustomMenu(CustomMenuBar.CustomMenuItem customMenuItem, String[] variableHeader, String[] variableProperty) {
        customMenuItem.removeChildren();
        if (variableHeader.length != 0 && variableProperty.length != 0) {
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[variableHeader.length];
            for (int i = 0; i < variableHeader.length; i++) {
                MenuItemDTO dto = new MenuItemDTO(variableProperty[i].trim(), variableHeader[i].trim());
                customItem[i] = customMenuItem.addItem(dto, null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }
        }
    }

    public static void loadCustomMenu(CustomMenuBar.CustomMenuItem customMenuItem, List value) {
        if (value != null && !value.isEmpty()) {
            customMenuItem.removeChildren();
            CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[value.size()];
            for (int i = 0; i < value.size(); i++) {
                customItem[i] = customMenuItem.addItem(value.get(i).toString(), null);
                customItem[i].setCheckable(true);
                customItem[i].setItemClickable(true);
                customItem[i].setItemClickNotClosable(true);
            }
        }
    }

    /**
     * returns periods interms of given frequency with current, current -1 and
     * current +3 periods
     *
     * @param frequency
     * @param startPeriod
     * @param endPeriod
     * @return
     */
    public static List<String> getPeriodsByFrequency(String frequency, String startPeriod, String endPeriod) {
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        if (StringUtils.isNotBlank(frequency)) {
            String freq = frequency.trim().toUpperCase(Locale.ENGLISH);
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            Calendar calendar = Calendar.getInstance();
            String[] startDate = startPeriod.split(" ");
            calendar.set(Calendar.MONTH, CommonUtils.getMonthNo(startDate[0]) - 1);
            calendar.set(Calendar.YEAR, Integer.valueOf(startDate[1]));
            calendar.add(Calendar.MONTH, -NumericConstants.TWELVE);
            String year;
            int month;
            String quarter;
            String semi;
            int count = getPeriodCount(freq, startPeriod, endPeriod) + NumericConstants.TWELVE;
            int countSix = count % NumericConstants.SIX == 0 ? 0 : 1;
            int countTwelve = count % NumericConstants.TWELVE == 0 ? 0 : 1;
            int iterationCountValue = freq.startsWith("S") ? (count / NumericConstants.SIX + (countSix)) : (count / NumericConstants.TWELVE + (countTwelve));
            int countThree = count % NumericConstants.THREE == 0 ? 0 : 1;
            int iterationCountWithQ = freq.startsWith("Q") ? (count / NumericConstants.THREE + (countThree))
                    : iterationCountValue;
            int iterationCount = freq.startsWith("M") ? count : iterationCountWithQ;
            int incrementWithS = freq.startsWith("S") ? NumericConstants.SIX : NumericConstants.TWELVE;
            int incrementWithQ = freq.startsWith("Q") ? NumericConstants.THREE : incrementWithS;
            int increment = freq.startsWith("M") ? 1
                    : incrementWithQ;
            for (int i = 0; i < iterationCount; i++) {
                year = String.valueOf(calendar.get(Calendar.YEAR));
                month = calendar.get(Calendar.MONTH);
                quarter = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.THREE + 1);
                semi = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.SIX + 1);
                String periodWithS = freq.startsWith("S") ? "S" + semi + " " + year : year;
                String periodWithQ = freq.startsWith("Q") ? "Q" + quarter + " " + year
                        : periodWithS;
                String period = freq.startsWith("M") ? months[month] + " " + year : periodWithQ;
                calendar.add(Calendar.MONTH, increment);
                periodList.add(period);
            }
        }

        return periodList;
    }

    /**
     * For getting the total months based on frequency
     *
     * Impl for the CR GAL-1135,GAL-540 and GAL-541
     *
     * @param frequency
     * @param startPeriod
     * @param endPeriod
     * @return
     */
    static int getPeriodCount(String frequency, String startPeriod, String endPeriod) {
        int freqWithS = frequency.startsWith("S") ? NumericConstants.SIX : 1;
        int freq = frequency.startsWith("Q") ? NumericConstants.THREE : freqWithS;
        int count = 0;
        String[] startArr = startPeriod.split(" ");
        String[] endArr = endPeriod.split(" ");
        boolean freqFlag = frequency.startsWith("Q") || frequency.startsWith("S") ? Boolean.TRUE : Boolean.FALSE;

        //end variable is to find the starting month based on the Quarter and Semi-Annual
        int end = CommonUtils.getMonthNo(endArr[0]) - 1;
        if (freqFlag) {
            end = ((((end / freq) + 1) - 1) * freq) + 1;
        }

        //End Period
        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.MONTH, end);
        endCal.set(Calendar.YEAR, Integer.valueOf(endArr[1]));

        //Start variable is to find the starting month based on the Quarter and Semi-Annual
        int start = CommonUtils.getMonthNo(startArr[0]) - 1;
        if (freqFlag) {
            start = ((((start / freq) + 1) - 1) * freq) + 1;
        }

        //Start Period
        Calendar startCal = Calendar.getInstance();
        startCal.set(Calendar.MONTH, start);
        startCal.set(Calendar.YEAR, Integer.valueOf(startArr[1]));

        while (startCal.before(endCal) || startCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)) {
            count++;
            startCal.add(Calendar.MONTH, 1);
        }
        return count;
    }

    /**
     * returns periods interms of given frequency with FromDate and ToDate
     * periods
     *
     * @param frequency
     * @return
     */
    public static List<String> getToAndFromByFrequency(String frequency, Date fromDate, Date toDate) {
        Calendar fromDateCal = Calendar.getInstance();
        fromDateCal.setTime(fromDate);
        Calendar toDateCal = Calendar.getInstance();
        toDateCal.setTime(toDate);
        List<String> periodList = new ArrayList<>();
        periodList.add(GlobalConstants.getSelectOne());
        if (StringUtils.isNotBlank(frequency) && !"-Select One-".equalsIgnoreCase(frequency) && !"null".equals(frequency)) {
            int diffYear = toDateCal.get(Calendar.YEAR) - fromDateCal.get(Calendar.YEAR);
            int diffMonth = diffYear * NumericConstants.TWELVE + toDateCal.get(Calendar.MONTH) - fromDateCal.get(Calendar.MONTH);
            String freq = frequency.trim().toUpperCase(Locale.ENGLISH);
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            int iterationCountWithS = freq.startsWith("S") ? calculateFrequency(diffMonth, NumericConstants.SIX) : diffYear;
            int iterationCountWithQ = freq.startsWith("Q") ? calculateFrequency(diffMonth, NumericConstants.THREE)
                    : iterationCountWithS;
            int iterationCount = freq.startsWith("M") ? diffMonth : iterationCountWithQ;
            int incrementWithS = freq.startsWith("S") ? NumericConstants.SIX : NumericConstants.TWELVE;
            int incrementWithQ = freq.startsWith("Q") ? NumericConstants.THREE
                    : incrementWithS;
            int increment = freq.startsWith("M") ? 1 : incrementWithQ;
            getPeriodList(iterationCount, fromDateCal, freq, months, increment, periodList);
        }
        return periodList;
    }

    private static void getPeriodList(int iterationCount, Calendar fromDateCal, String freq, String[] months, int increment, List<String> periodList) {
        String year;
        String quarter;
        String semi;
        int month;
        for (int i = 0; i <= iterationCount; i++) {
            year = String.valueOf(fromDateCal.get(Calendar.YEAR));
            month = fromDateCal.get(Calendar.MONTH);
            quarter = String.valueOf(fromDateCal.get(Calendar.MONTH) / NumericConstants.THREE + 1);
            semi = String.valueOf(fromDateCal.get(Calendar.MONTH) / NumericConstants.SIX + 1);
            String startWithS = freq.startsWith("S") ? "S" + semi + " " + year : year;
            String startWithQ = freq.startsWith("Q") ? "Q" + quarter + " " + year : startWithS;
            String period = freq.startsWith("M") ? months[month] + " " + year : startWithQ;
            fromDateCal.add(Calendar.MONTH, increment);
            periodList.add(period);
        }
    }

    /**
     * To uncheck the menu items that is checked
     *
     * @param customMenuItem
     */
    public static void unCheckMenuBarItem(CustomMenuBar.CustomMenuItem customMenuItem) {
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.isChecked()) {
                    object.setChecked(false);
                }
            }
        }
    }

    /**
     * To check all the menu items
     *
     * @param customMenuItem
     */
    public static void checkAllMenuBarItem(CustomMenuBar.CustomMenuItem customMenuItem) {
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                object.setChecked(true);
            }
        }
    }

    public static Map<Integer, String> getUserName() {
        LOGGER.debug("Enters getUserName method");
        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
            List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
            for (User user : userList) {
                String formattedUN = user.getLastName() + ", " + user.getFirstName();
                userMap.put((int) user.getUserId(), formattedUN);
            }
        } catch (Exception ex) {
            LOGGER.error("Error in getUserName :", ex);
        }
        LOGGER.debug("End of getUserName method");
        return userMap;
    }

    /**
     * Get User Name By User Id
     *
     * @param userIdString
     * @return
     */
    public static String getUserNameById(String userIdString) {
        String userName = StringUtils.EMPTY;
        int userId = Integer.parseInt(StringUtils.isBlank(userIdString) ? DASH : userIdString);
        if (userId != 0) {
            userName = userMap.get(userId);
        }
        return userName;
    }

    /**
     * Get User Name By User Id
     *
     * @param userIdString
     * @return
     */
    public static String getDeductionValue() {
        SQlUtil.getQuery("LOAD_DEDUCTION_VALUE");

        return "";
    }

    /**
     * returns monthNo Jan--1..........Dec-12
     *
     * @param mon
     * @return
     */
    public static int getMonthNo(String mon) {
        int num = 0;
        try {
            Date dat = new SimpleDateFormat("MMM").parse(mon);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dat);
            num = cal.get(Calendar.MONTH) + 1;
        } catch (Exception e) {
            LOGGER.error("Error in getMonthNo :", e);
        }
        return num;
    }

    public void saveUploadedInformation(List<NotesDTO> availableUploadedInformation, String moduleName, int moduleSystemId) {
        try {
            if (availableUploadedInformation != null && !availableUploadedInformation.isEmpty()) {

                for (NotesDTO uploadDetails : availableUploadedInformation) {
                    MasterDataFiles masterDataFiles;
                    if (uploadDetails.getDocDetailsId() == 0) {
                        masterDataFiles = MasterDataFilesLocalServiceUtil.createMasterDataFiles(0);
                        masterDataFiles.setMasterTableName(moduleName);
                        masterDataFiles.setMasterTableSid(moduleSystemId);
                        masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
                        masterDataFiles.setCreatedBy(uploadDetails.getUserId());
                        masterDataFiles.setCreatedDate(new Date());
                        MasterDataFilesLocalServiceUtil.addMasterDataFiles(masterDataFiles);
                    } else {
                        masterDataFiles = MasterDataFilesLocalServiceUtil.getMasterDataFiles(uploadDetails.getDocDetailsId());
                        masterDataFiles.setMasterTableName(moduleName);
                        masterDataFiles.setMasterTableSid(moduleSystemId);
                        masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
                        masterDataFiles.setCreatedBy(uploadDetails.getUserId());
                        masterDataFiles.setCreatedDate(new Date());
                        MasterDataFilesLocalServiceUtil.updateMasterDataFiles(masterDataFiles);
                    }
                }

            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static void checkMenuBarItem(CustomMenuBar.CustomMenuItem customMenuItem, String obj) {
        if (customMenuItem != null && customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.getMenuItem().getWindow().toString().trim().equalsIgnoreCase(obj.trim())) {
                    object.setChecked(true);
                }
            }
        }
    }

    public static void checkMenuBarItemCaption(CustomMenuBar.CustomMenuItem customMenuItem, String obj) {
        if (customMenuItem != null && customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.getMenuItem().getCaption().trim().equalsIgnoreCase(obj.trim())) {
                    object.setChecked(true);
                }
            }
        }
    }

    /**
     * To uncheck the menu items that is checked
     *
     * @param customMenuItem
     */
    public static void checkMenuBarAllItem(CustomMenuBar.CustomMenuItem customMenuItem) {
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                object.setChecked(true);
            }
        }
    }

    /**
     * To convert Collection<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param isDoubleQuote
     * @param toRemoveSpace
     * @param toRemoveSpaceAfterComma
     * @return
     */
    public static String collectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean isDoubleQuote, boolean toRemoveSpace, boolean toRemoveSpaceAfterComma) {

        String framedString = StringUtils.EMPTY;
        if (collectionOfString != null && !collectionOfString.isEmpty()) {

            if (toAddQuote) {
                String quote = String.valueOf(ARMUtils.SINGLE_QUOTES);
                if (isDoubleQuote) {
                    quote = "\"";
                }
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", quote).replace("]", quote).replace(", ", quote + ", " + quote);
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY);
            }

            if (toRemoveSpace) {
                framedString = framedString.replace(" ", StringUtils.EMPTY);
            } else if (toRemoveSpaceAfterComma) {
                framedString = framedString.replace(", ", ",");
            }
        }
        return framedString;
    }

    public static ComboBox loadComboBoxWithIntegerForComboBoxForDemandSummary(final ComboBox demandSummarySelect, String listName, boolean isFilter) {
        try {
            demandSummarySelect.setImmediate(true);
            demandSummarySelect.setNullSelectionAllowed(false);
            demandSummarySelect.setData(listName);
            demandSummarySelect.addItem(0);
            demandSummarySelect.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    demandSummarySelect.addItem(helperDTO.getId());
                    demandSummarySelect.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            demandSummarySelect.select(0);
            demandSummarySelect.markAsDirty();
            demandSummarySelect.setDescription((String) (demandSummarySelect.getValue() == DASH ? GlobalConstants.getSelectOne() : demandSummarySelect.getItemCaption(demandSummarySelect.getValue())));
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return demandSummarySelect;
    }

    public static String getFormatedValue(String format, Object value) {
        DecimalFormat decimalformat = new DecimalFormat(format);
        return getFormatedValue(decimalformat, value);
    }

    public static String getFormatedValue(DecimalFormat decimalformat, Object value) {
        return decimalformat.format(value);
    }

    public static final CustomMenuBar.CustomMenuItem loadStatusMenuBar(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed) {

        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.FOUR); // GAL-7390

        List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), "LoadStatus", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = list.get(i);
            String description = helper[1].toString();
            MenuItemDTO menudto = new MenuItemDTO(description);
            menudto.setId((int) helper[0]);
            customItem[i] = customMenuItemDed.addItem(menudto, null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
        }
        return customMenuItemDed;
    }

    /**
     * Loading values based on file end date
     *
     * @param select
     * @param priceList
     * @param count
     * @return
     */
    public static CustomComboBox loadPriceComboBox(final CustomComboBox select, List priceList, int count) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.addItem(GlobalConstants.getSelectOne());

            priceList.clear();

            List<Object[]> list = QueryUtils.getItemData(Collections.emptyList(), "getMonthYear", null);
            if (list != null && !list.isEmpty()) {
                Object[] object = list.get(0);

                //Start date - Current date
                Calendar startPeriod = Calendar.getInstance();
                startPeriod.add(Calendar.MONTH, count);

                //End date - Form file
                Calendar endPeriod = Calendar.getInstance();
                endPeriod.set(Calendar.MONTH, Integer.valueOf(String.valueOf(object[1])) - 1);
                endPeriod.set(Calendar.YEAR, Integer.valueOf(String.valueOf(object[NumericConstants.TWO])));

                while (startPeriod.before(endPeriod) || startPeriod.get(Calendar.MONTH) == endPeriod.get(Calendar.MONTH)) {
                    priceList.add("CURRENT" + getCurrentString(count));
                    startPeriod.add(Calendar.MONTH, 1);
                    count++;
                }
            }
            select.addItems(priceList);
            select.select(GlobalConstants.getSelectOne());

        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down : Price with :", e);
        }
        return select;
    }

    private static String getCurrentString(int index) {
        String result = StringUtils.EMPTY;
        if (index < 0) {
            result = " - " + String.valueOf(index).replace("-", StringUtils.EMPTY);
        } else if (index > 0) {
            result = " + " + String.valueOf(index).replace("+", StringUtils.EMPTY);
        }
        return result;
    }

    public static List<String> getSelectedVariables(CustomMenuBar.CustomMenuItem customMenuItem) {
        List<String> list = new ArrayList();
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                if (object.isChecked()) {
                    list.add(object.getMenuItem().getCaption());
                }
            }
        }
        return list;
    }

    /**
     * The logic is written based on the comments given in the ticket GALUAT-540
     * and GALUAT-541.
     *
     * Our current system allows Current-3 years of historical period so for an
     * example if our current period is Nov 2016 , Current-3 years is Nov 2013
     * if the user select Jun 2013 which did not fall in historical period range
     * so this method will return Nov 2013 (i.e Current-3 years) period based on
     * the comment specified.
     *
     * If the user select Oct 2015 which is in the period range so the method
     * retuns Oct 2015
     *
     * @param freq
     * @param period
     * @return
     */
    public static String getPeriodValue(char freq, String period) {
        int count = 0;
        boolean flag = false;
        String finalPeriod = null;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] months = dateFormatSymbols.getShortMonths();
        String[] histPeriodArr = period.split(" ");
        //cal variable is used for getting current Calendar instance
        Calendar cal = Calendar.getInstance();
        //history variable is used for setting the date based on user selection
        Calendar history = Calendar.getInstance();
        if ('A' == freq) {
            history.set(Calendar.YEAR, Integer.valueOf(histPeriodArr[0]));
        } else {
            history.set(Calendar.MONTH, CommonUtils.getMonthNo(histPeriodArr[0]) - 1);
            history.set(Calendar.YEAR, Integer.valueOf(histPeriodArr[1]));
        }

        /**
         * We will decrement cal value by month until history period is before
         * cal
         */
        while (history.before(cal)) {
            count++;
            cal.add(Calendar.MONTH, -1);
            if (count == NumericConstants.THIRTY_SIX) {
                flag = true;
                break;
            }
        }
        if (flag) {
            finalPeriod = checkPeriodValue(freq, cal, months);

        } else {
            finalPeriod = period;
        }
        return finalPeriod;
    }

    public static ComboBox loadComboBoxWithIntegerRateBasis(final CustomComboBox rateBasisSelect, String listName, boolean isFilter, String adjustmentType) {

        try {
            rateBasisSelect.removeAllItems();
            rateBasisSelect.setImmediate(true);
            rateBasisSelect.setNullSelectionAllowed(false);
            rateBasisSelect.setData(listName);
            rateBasisSelect.addItem(0);

            rateBasisSelect.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : GlobalConstants.getSelectOne());
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            Collections.sort(list, sorter);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    if (getCondition(adjustmentType, helperDTO)) {
                        rateBasisSelect.addItem(helperDTO.getId());
                        rateBasisSelect.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                    }
                }

                rateBasisSelect.select(0);
                rateBasisSelect.markAsDirty();
                rateBasisSelect.setDescription((String) (rateBasisSelect.getValue() == DASH ? GlobalConstants.getSelectOne() : rateBasisSelect.getItemCaption(rateBasisSelect.getValue())));
            }
        } catch (Exception e) {
            LOGGER.error(CommonConstant.ERROR_WHILE_LOADING_DROP_DOWN + listName + CommonConstant.WITH, e);
        }
        return rateBasisSelect;
    }

    private static boolean getCondition(String adjustmentType, HelperDTO helperDTO) {
        return ((ARMConstants.getPipelineAccrual().equals(adjustmentType) || ARMConstants.getTransaction6().equals(adjustmentType) || ARMConstants.getTransaction7().equals(adjustmentType))
                && !VariableConstants.CONTRACT_TERMS.equals(helperDTO.getDescription()) && !VariableConstants.CALCULATED_RETURNS.equals(helperDTO.getDescription()) && !VariableConstants.CONTRACT_DETAILS.equals(helperDTO.getDescription()))
                || (ARMConstants.getTransaction8().equals(adjustmentType) && !VariableConstants.CONTRACT_TERMS.equals(helperDTO.getDescription()))
                || (ARMConstants.getPipelineInventoryTrueUp().equals(adjustmentType) && !VariableConstants.CALCULATED_RETURNS.equals(helperDTO.getDescription()) && !VariableConstants.CONTRACT_DETAILS.equals(helperDTO.getDescription()));
    }

    public static void loadCustomMenubarAccount(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed, final int adjustmentType, ReserveSelection selection) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TWO);
        List inputList = new ArrayList<>();
        inputList.add(selection.getTempTableName());
        inputList.add(adjustmentType);
        List<Object[]> list = QueryUtils.getItemData(inputList, "loadAccount_BasedOn_AdjustmentType", null);
        inputList.clear();
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = list.get(i);
            String column = helper[NumericConstants.ONE].toString();
            String header = helper[NumericConstants.ONE].toString();
            MenuItemDTO menudto = new MenuItemDTO(column, header);
            menudto.setId((int) helper[0]);
            customItem[i] = customMenuItemDed.addItem(menudto, null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
        }
    }

    private static String checkPeriodValue(char freq, Calendar cal, String[] months) {
        String year;
        int month;
        String quarter;
        String semi;
        year = String.valueOf(cal.get(Calendar.YEAR));
        month = cal.get(Calendar.MONTH);
        quarter = String.valueOf(month / NumericConstants.THREE + 1);
        semi = String.valueOf(month / NumericConstants.SIX + 1);
        String finalPeriod;
        switch (freq) {
            case 'M':
                finalPeriod = months[month] + " " + year;
                break;
            case 'Q':
                finalPeriod = "Q" + quarter + " " + year;
                break;
            case 'S':
                finalPeriod = "S" + semi + " " + year;
                break;
            default:
                finalPeriod = year;

        }
        return finalPeriod;
    }

    public static final CustomMenuBar.CustomMenuItem loadSummaryDeductionsDdlbForTrx6(final ComboBox deductionComboBox, final CustomMenuBar deductionValueMenuBar, final int projectionId) {
        loadComboBoxWithIntegerForComboBox(deductionComboBox, "DEDUCTION_LEVELS", false);
        deductionValueMenuBar.setScrollable(true);
        deductionValueMenuBar.setPageLength(NumericConstants.TEN);
        final CustomMenuBar.CustomMenuItem customMenuItemDed = deductionValueMenuBar.addItem("  - Select Value -  ", null);
        deductionComboBox.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (Integer.parseInt(String.valueOf(deductionComboBox.getValue())) != 0) {
                    int id = (int) deductionComboBox.getValue();
                    String description = deductionComboBox.getItemCaption(id);
                    if (description != null) {
                        List<Object[]> resultList = loadDeductionsValuesForTrx6(projectionId, description);
                        CustomMenuBar.CustomMenuItem[] dedcutionCustomItem = new CustomMenuBar.CustomMenuItem[resultList.size()];
                        customMenuItemDed.removeChildren();
                        for (int i = 0; i < resultList.size(); i++) {
                            String dedcutionHeader = String.valueOf(resultList.get(i)[1]);
                            String column = dedcutionHeader;
                            if (dedcutionHeader.contains("~")) {
                                column = dedcutionHeader.split("~")[0].trim();
                            }
                            dedcutionHeader = dedcutionHeader.replace("~", "-");
                            MenuItemDTO dto = new MenuItemDTO(column, dedcutionHeader);
                            dto.setId(Integer.valueOf(resultList.get(i)[0].toString()));
                            dedcutionCustomItem[i] = customMenuItemDed.addItem(dto, null);
                            dedcutionCustomItem[i].setCheckable(true);
                            dedcutionCustomItem[i].setItemClickable(true);
                            dedcutionCustomItem[i].setItemClickNotClosable(true);
                        }
                    }
                } else {
                    customMenuItemDed.removeChildren();
                }
            }
        });
        return customMenuItemDed;
    }

    private static List<Object[]> loadDeductionsValuesForTrx6(int projectionId, String deductionLevel) {
        List<Object[]> valuesDes = new ArrayList<>();
        boolean isDetection = deductionLevel.equals(ARMConstants.getDeduction());
        Map<Integer, String> descriptionMap = HelperListUtil.getInstance().getIdDescMap();
        Map<String, String> summaryLevelMap = ARMUtils.loadDedutionMapForInflation();
        String deductionColumn = summaryLevelMap.get(deductionLevel);
        List<Object[]> deductionsValues;
        if (isDetection) {
            deductionsValues = QueryUtils.getItemData(new ArrayList<>(Arrays.asList(projectionId, "A.RS_MODEL_SID,A.RS_ID+' ~ '+ A.RS_NAME", StringUtils.EMPTY, "order by A.RS_ID+' ~ '+ A.RS_NAME")), "loadDeductionValueForInflation", null);
        } else {
            String[] input = deductionColumn.split(",");
            deductionsValues = QueryUtils.getItemData(new ArrayList<>(Arrays.asList(projectionId, deductionColumn, "LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = " + input[0], "order by HT.DESCRIPTION")), "loadDeductionValueForInflation", null);

        }

        return customizeDeductions(deductionsValues, isDetection, descriptionMap, valuesDes);
    }

    private static List<Object[]> customizeDeductions(List<Object[]> deductionsValues, boolean isDetection, Map<Integer, String> descriptionMap, List<Object[]> valuesDes) {
        for (int i = 0; i < deductionsValues.size(); i++) {
            Object[] arr = new Object[NumericConstants.TWO];
            Object[] obj = deductionsValues.get(i);

            String id;
            if (obj[0] != null && !"".equalsIgnoreCase(String.valueOf(obj[0]))) {
                id = obj[0] == null || (int) obj[0] == 0 ? "0" : String.valueOf(obj[0]);
                if (!"0".equals(id)) {
                    arr[0] = id;
                    String value = (obj[1] == null && obj.length < NumericConstants.TWO) ? "0" : String.valueOf(obj[1]);
                    arr[1] = isDetection ? value : descriptionMap.get(Integer.valueOf(id));
                    valuesDes.add(arr);
                }
            }
        }
        return valuesDes;
    }

    private static int calculateFrequency(int diffMonth, int dividend) {
        int result = diffMonth / dividend;
        int remainder = diffMonth % dividend;
        if (remainder > 0) {
            result += 1;
        }
        return result;
    }

    public static String convertStringToDate(String date) {
        try {
            Date dateField = ARMUtils.getInstance().getDateFormatter().parse(date);
            return ARMUtils.getInstance().getmmddyyyy().format(dateField);
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public static Map<Integer, String> getUserMap() {
        return userMap != null ? new HashMap<>(userMap) : null;
    }

    public static void setUserMap(Map<Integer, String> userMap) {
        CommonUtils.userMap = userMap != null ? new HashMap<>(userMap) : null;
    }

}
