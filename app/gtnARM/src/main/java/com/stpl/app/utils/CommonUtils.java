/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.model.MasterDataFiles;
import com.stpl.app.service.MasterDataFilesLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.NotesDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ComboBox;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.jboss.logging.Logger;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author karthikeyans
 */
public class CommonUtils {

    public static final String DASH = "0";
    private static final String DATASOURCE_CONTEXT = "java:jboss/datasources/jdbc/appDataPool";
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class.getName());
    private static final CustomComparator sorter = new CustomComparator();
    /**
     * UserMap - Contains User System ID and User Name
     */
    public static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();

    public static ComboBox loadComboBoxWithInteger(final CustomComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);

            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down :" + listName + " with :" + e);
        }
        return select;
    }

    public static ComboBox loadComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                for (HelperDTO helperDTO : list) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down :" + listName + " with :" + e.getMessage());
        }
        return select;
    }

    public static ComboBox loadUdcComboBoxWithIntegerForComboBox(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
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
                HelperDTO helper = (HelperDTO) HelperListUtil.getInstance().getListNameMap().get(listName).get(i);
                select.addItem(helper.getId());
                select.setItemCaption(helper.getId(), helper.getDescription());
            }

            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down :" + listName + " with :" + e);
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
            LOGGER.error("Error while loading Drop down :" + listName + " with :" + e);
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
                month = Integer.valueOf(desc.substring(desc.indexOf("+") + NumericConstants.TWO));
            } else if (desc.contains("-")) {
                month = -Integer.valueOf(desc.substring(desc.indexOf("-") + NumericConstants.TWO));
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

            LOGGER.error(e);
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
                if (!uI.isExcelFlag()) {
                    uI.close();
                }
                uI.setExcelFlag(Boolean.FALSE);
            }
        });

    }

    public static final CustomMenuBar.CustomMenuItem loadSummaryDeductionsDdlb(final ComboBox comboBox, final CustomMenuBar menuBar, final int projectionId) {
        loadComboBoxWithIntegerForComboBox(comboBox, "DEDUCTION_LEVELS", false);
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);
        final CustomMenuBar.CustomMenuItem customMenuItemDed = menuBar.addItem("  - Select Value -  ", null);
        comboBox.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (Integer.parseInt(String.valueOf(comboBox.getValue())) != 0) {
                    int id = (int) comboBox.getValue();
                    String description = comboBox.getItemCaption(id);
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

        for (int i = 0; i < deductionsValues.size(); i++) {
            Object[] arr = new Object[NumericConstants.TWO];
            Object[] obj = deductionsValues.get(i);

            String id;
            if (obj[0] != null && !"".equalsIgnoreCase(String.valueOf(obj[0]))) {
                id = obj[0] == null || (int) obj[0] == 0 ? "0" : String.valueOf(obj[0]);
                if (!"0".equals(id)) {
                    arr[0] = id;
                    arr[1] = isDetection ? ((obj[1] == null && obj.length < NumericConstants.TWO) ? "0" : String.valueOf(obj[1])) : descriptionMap.get(Integer.valueOf(id));
                    valuesDes.add(arr);
                }
            }
        }
        return valuesDes;
    }

    public static final CustomMenuBar.CustomMenuItem loadAdjustmentTypeDdlb(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(NumericConstants.TEN);
        List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, "LoadAdjustmentType", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
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
                    result.add(new String[]{(String) customMenuItem1.getMenuItem().getWindow(), customMenuItem1.getMenuItem().getCaption()});
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
        List<List> list = new ArrayList<List>();
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
        periodList.add(ConstantsUtils.SELECT_ONE);
        if (StringUtils.isNotBlank(frequency)) {
            String freq = frequency.trim().toUpperCase();
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
            int iterationCount = freq.startsWith("M") ? count : freq.startsWith("Q") ? (count / NumericConstants.THREE + (count % NumericConstants.THREE == 0 ? 0 : 1))
                    : freq.startsWith("S") ? (count / NumericConstants.SIX + (count % NumericConstants.SIX == 0 ? 0 : 1)) : (count / NumericConstants.TWELVE + (count % NumericConstants.TWELVE == 0 ? 0 : 1));
            int increment = freq.startsWith("M") ? 1 : freq.startsWith("Q") ? NumericConstants.THREE
                    : freq.startsWith("S") ? NumericConstants.SIX : NumericConstants.TWELVE;
            for (int i = 0; i < iterationCount; i++) {
                year = String.valueOf(calendar.get(Calendar.YEAR));
                month = calendar.get(Calendar.MONTH);
                quarter = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.THREE + 1);
                semi = String.valueOf(calendar.get(Calendar.MONTH) / NumericConstants.SIX + 1);
                String period = freq.startsWith("M") ? months[month] + " " + year : freq.startsWith("Q") ? "Q" + quarter + " " + year
                        : freq.startsWith("S") ? "S" + semi + " " + year : year;
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
        int freq = frequency.startsWith("Q") ? NumericConstants.THREE : frequency.startsWith("S") ? NumericConstants.SIX : 1;
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
        periodList.add(ConstantsUtils.SELECT_ONE);
        if (StringUtils.isNotBlank(frequency) && !"-Select One-".equalsIgnoreCase(frequency) && !"null".equals(frequency)) {
            int diffYear = toDateCal.get(Calendar.YEAR) - fromDateCal.get(Calendar.YEAR);
            int diffMonth = diffYear * NumericConstants.TWELVE + toDateCal.get(Calendar.MONTH) - fromDateCal.get(Calendar.MONTH);
            String freq = frequency.trim().toUpperCase();
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
            String[] months = dateFormatSymbols.getShortMonths();
            String year;
            int month;
            String quarter;
            String semi;
            int iterationCount = freq.startsWith("M") ? diffMonth : freq.startsWith("Q") ? diffMonth / NumericConstants.THREE
                    : freq.startsWith("S") ? diffMonth / NumericConstants.SIX : diffYear;
            int increment = freq.startsWith("M") ? 1 : freq.startsWith("Q") ? NumericConstants.THREE
                    : freq.startsWith("S") ? NumericConstants.SIX : NumericConstants.TWELVE;
            for (int i = 0; i <= iterationCount; i++) {
                year = String.valueOf(fromDateCal.get(Calendar.YEAR));
                month = fromDateCal.get(Calendar.MONTH);
                quarter = String.valueOf(fromDateCal.get(Calendar.MONTH) / NumericConstants.THREE + 1);
                semi = String.valueOf(fromDateCal.get(Calendar.MONTH) / NumericConstants.SIX + 1);
                String period = freq.startsWith("M") ? months[month] + " " + year : freq.startsWith("Q") ? "Q" + quarter + " " + year
                        : freq.startsWith("S") ? "S" + semi + " " + year : year;
                fromDateCal.add(Calendar.MONTH, increment);
                periodList.add(period);
            }
        }
        return periodList;
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
                    object.setChecked(Boolean.FALSE);
                }
            }
        }
    }

    /**
     * To check all the menu items
     *
     * @param customMenuItem
     */
    public static void CheckAllMenuBarItem(CustomMenuBar.CustomMenuItem customMenuItem) {
        if (customMenuItem.getChildren() != null && !customMenuItem.getChildren().isEmpty()) {
            for (CustomMenuBar.CustomMenuItem object : customMenuItem.getChildren()) {
                object.setChecked(Boolean.TRUE);
            }
        }
    }

    /**
     * To execute insert/update stored procedures
     *
     * @param procedureName
     * @param orderedArgs
     */
    public static void callInsertProcedure(String procedureName, Object[] orderedArgs) {
        LOGGER.debug("Procedure Name " + procedureName);
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                String procedureToCall = "{call " + procedureName;
                int noOfArgs = orderedArgs.length;
                for (int i = 0; i < noOfArgs; i++) {
                    if (i == 0) {
                        procedureToCall += "(";
                    }
                    procedureToCall += "?,";
                    if (i == noOfArgs - 1) {
                        procedureToCall += ")";
                    }
                }
                procedureToCall = procedureToCall.replace(",)", ")");
                procedureToCall += "}";
                statement = connection.prepareCall(procedureToCall);
                for (int i = 0; i < noOfArgs; i++) {
                    LOGGER.debug(i + " -- " + orderedArgs[i]);
                    statement.setObject(i + 1, orderedArgs[i]);
                }
                statement.executeUpdate();

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
            try {
                connection.close();

            } catch (Exception ex) {
                LOGGER.error(ex);
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
                userMap.put(Long.valueOf(user.getUserId()).intValue(), formattedUN);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
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
            LOGGER.error(e);
        }
        return num;
    }

    public void saveUploadedInformation(List<NotesDTO> availableUploadedInformation, String moduleName, int moduleSystemId) throws SystemException, PortalException {

        if (availableUploadedInformation != null && availableUploadedInformation.size() > 0) {

            for (NotesDTO uploadDetails : availableUploadedInformation) {
                MasterDataFiles masterDataFiles;
                if (uploadDetails.getDocDetailsId() == 0) {
                    masterDataFiles = MasterDataFilesLocalServiceUtil.createMasterDataFiles(0);
                    masterDataFiles.setMasterTableName(moduleName);
                    masterDataFiles.setMasterTableSid(moduleSystemId);
                    masterDataFiles.setFilePath(uploadDetails.getDocumentFullPath());
                    masterDataFiles.setCreatedBy(uploadDetails.getUserId());
                    masterDataFiles.setCreatedDate(new Date());
                    masterDataFiles = MasterDataFilesLocalServiceUtil.addMasterDataFiles(masterDataFiles);
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
                String quote = "'";
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

    public static ComboBox loadComboBoxWithIntegerForComboBoxForDemandSummary(final ComboBox select, String listName, boolean isFilter) {
        try {
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);
            select.setData(listName);
            select.addItem(0);
            select.setItemCaption(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
            List<HelperDTO> list = HelperListUtil.getInstance().getListNameMap().get(listName);
            if (list != null && !list.isEmpty()) {
                Object[] hepler = list.toArray();
                List<HelperDTO> sortlist = new ArrayList<>();
                int n = hepler.length;
                Object temp;
                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < (n - i); j++) {
                        if (((HelperDTO) hepler[j - 1]).getId() > ((HelperDTO) hepler[j]).getId()) {
                            temp = hepler[j - 1];
                            hepler[j - 1] = hepler[j];
                            hepler[j] = temp;
                        }
                    }
                }
                for (Object helperDTO : hepler) {
                    sortlist.add((HelperDTO) helperDTO);
                }
                for (HelperDTO helperDTO : sortlist) {
                    select.addItem(helperDTO.getId());
                    select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
                }
            }
            select.select(0);
            select.markAsDirty();
            select.setDescription((String) (select.getValue() == DASH ? ConstantsUtils.SELECT_ONE : select.getItemCaption(select.getValue())));
        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down :" + listName + " with :" + e);
        }
        return select;
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

        List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, "LoadStatus", null);
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[list.size()];
        customMenuItemDed.removeChildren();
        for (int i = 0; i < list.size(); i++) {
            Object[] helper = (Object[]) list.get(i);
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
     * @return
     */
    public static CustomComboBox loadPriceComboBox(final CustomComboBox select, List priceList) {
        try {
            select.removeAllItems();
            select.setImmediate(true);
            select.setNullSelectionAllowed(false);

            if (priceList.isEmpty()) {
                priceList.add(GlobalConstants.getSelectOne());
                List<Object[]> list = QueryUtils.getItemData(Collections.EMPTY_LIST, "getMonthYear", null);
                if (list != null && !list.isEmpty()) {
                    int count = -NumericConstants.TWELVE;
                    Object[] object = list.get(0);

                    //Start date - Current date
                    Calendar startPeriod = Calendar.getInstance();
                    startPeriod.add(Calendar.MONTH, -NumericConstants.TWELVE);

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
            } else {
                select.addItems(priceList);
            }

        } catch (Exception e) {
            LOGGER.error("Error while loading Drop down : Price with :" + e);
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
        String finalPeriod = StringUtils.EMPTY;
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
            String year;
            int month;
            String quarter;
            String semi;
            year = String.valueOf(cal.get(Calendar.YEAR));
            month = cal.get(Calendar.MONTH);
            quarter = String.valueOf(month / NumericConstants.THREE + 1);
            semi = String.valueOf(month / NumericConstants.SIX + 1);
            finalPeriod = 'M' == freq ? months[month] + " " + year : 'Q' == freq ? "Q" + quarter + " " + year
                    : 'S' == freq ? "S" + semi + " " + year : year;
        } else {
            finalPeriod = period;
        }
        return finalPeriod;
    }
}
