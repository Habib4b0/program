
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.dataSelection.dto.ForecastDTO;
import com.stpl.app.cff.ui.dataSelection.logic.DataSelectionLogic;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_LEVEL_CONTRACT;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_LEVEL_CUSTOMER;
import static com.stpl.app.cff.util.Constants.IndicatorConstants.INDICATOR_LEVEL_NDC;
import static com.stpl.app.cff.util.Constants.NULL;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItem;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.TreeTable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtFilterTable;

/**
 *
 * @author soundarrajan.l
 */
public class DataSelectionUtil {

    private static final Map<String, String> userMap = new HashMap<>();
    private static final Map<String, String> userIdMap = new HashMap<>();
    private static final Map<String, String> discountMap = new HashMap<>();

    public static Date calculateHistory(final String frequency, final int histValue) {

        Calendar cal = Calendar.getInstance();
        Calendar cal1;
        Date date = null;
        String obtainedDate = StringUtils.EMPTY;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if ("Month".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -histValue);
            } else if ("Quarter".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -(histValue * NumericConstants.THREE));  // Quarter * 3
            } else if ("SemiAnnual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -(histValue / NumericConstants.TWO));   // Semi annual / 2
            } else if ("Annual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -histValue);
            }
            obtainedDate = getDate(cal);
            date = format.parse(obtainedDate);
            cal1 = Calendar.getInstance();
            cal1.setTime(date);

        } catch (ParseException ex) {
        }
        return date;
    }

    public static Date calculateProjection(final String frequency, final int histValue) {
        Calendar cal = Calendar.getInstance();
        Calendar cal1;
        Date date = null;
        String obtainedDate = StringUtils.EMPTY;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if ("Month".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, histValue);
            } else if ("Quarter".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, histValue * NumericConstants.THREE); // Quarter * 3
            } else if ("SemiAnnual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, histValue / NumericConstants.TWO); // Semi annual / 2
            } else if ("Annual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, histValue);
            }
            obtainedDate = getDate(cal);
            date = format.parse(obtainedDate);
            cal1 = Calendar.getInstance();
            cal1.setTime(date);

        } catch (ParseException ex) {
        }
        return date;
    }

    public static String getDate(Calendar cal) {
        return (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
    }

    public static String getItemSidForGroup(List<Leveldto> innerProdLevels, List<String> items) {
        StringBuilder itemList = new StringBuilder(StringUtils.EMPTY);
        if (items != null && !items.isEmpty()) {
            items = new ArrayList<>(new LinkedHashSet<String>(items));
            for (int loop = 0, limit = items.size(); loop < limit; loop++) {
                itemList.append('\'');
                itemList.append(items.get(loop));
                itemList.append('\'');
                if (loop != (limit - 1)) {
                    itemList.append(", ");
                }
            }
        }
        String indicatorTable = "table";
        String indicatorColumn = "column";
        StringBuilder query = new StringBuilder(" ");
        query.append(" SELECT distinct im.");
        query.append(UiUtils.generateHqlField("ITEM_MASTER_SID", indicatorColumn));
        query.append(" FROM ");
        query.append(UiUtils.generateHqlField(StringConstantsUtil.ITEM_MASTER, indicatorTable));
        query.append(" im ");

        query.append(" WHERE ");

        Map<String, Boolean> fieldDuplicationCheck = new HashMap<>();
        Leveldto ddo;
        boolean orFlag = false;
        for (int loop = 0, limit = innerProdLevels.size(); loop < limit; loop++) {
            ddo = innerProdLevels.get(loop);

                if (ddo.getTableName().equalsIgnoreCase(StringConstantsUtil.ITEM_MASTER) && (fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)) == null
                        || !fieldDuplicationCheck.get(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn)))) {
                    if (orFlag) {
                        query.append(" or ");
                    }
                    query.append(" im.");
                    orFlag = true;
                    fieldDuplicationCheck.put(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn), true);
                    query.append(UiUtils.generateHqlField(ddo.getFieldName(), indicatorColumn));
                    query.append(" in (");

                    query.append(itemList.toString());

                    query.append(')');
                }


        }

        return query.toString();
    }

    public static void removeItemsRecursively(final Object item, final TreeTable selectedTable, final ExtFilterTable availableTable,
            final ExtTreeContainer<Leveldto> selectedContainer, final BeanItemContainer<Leveldto> availableContainer, final int currentLevel) {
        if (selectedTable.hasChildren(item)) {
            Collection<?> children = selectedTable.getChildren(item);
            if (children != null && children.size() > 0) {
                BeanItemContainer<Leveldto> tempBean = new BeanItemContainer<>(Leveldto.class);
                LinkedList<Object> children2 = new LinkedList<>();
                for (Iterator<?> i = children.iterator(); i.hasNext();) {
                    children2.add((Object) i.next());
                }
                for (Iterator<Object> i = children2.iterator(); i.hasNext();) {
                    Object child = i.next();
                    Leveldto beanItem = getBeanFromId(child);
                    tempBean.addBean(beanItem);
                    removeItemsRecursively(child, selectedTable, availableTable, selectedContainer, availableContainer, currentLevel);
                    if ("NDC".equalsIgnoreCase(getBeanFromId(child).getLevel())) {
                    }
                    selectedTable.removeItem(child);
                    selectedContainer.removeItemRecursively(child);
                }
            }
        }
    }

    /**
     * Gets the bean from id.
     *
     * @param obj the id
     * @return the bean from id
     */
    public static Leveldto getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof Leveldto) {
            targetItem = new BeanItem<>((Leveldto) obj);
        }

        return (Leveldto) targetItem.getBean();
    }

    public static List<Integer> getSelectedRelationshipLevelSids(List<Leveldto> itemIds) {
        List<Integer> selectedRelationshipLevelSids = null;
        if (itemIds != null && !itemIds.isEmpty()) {
            Leveldto dto;
            selectedRelationshipLevelSids = new ArrayList<>();
            for (Object item : itemIds) {
                dto = DataSelectionUtil.getBeanFromId(item);
                selectedRelationshipLevelSids.add(dto.getRelationshipLevelSid());
            }
        }
        return selectedRelationshipLevelSids;
    }

    public static List<String> storeUncommonValues(List<String> list1, List<String> list2) {
        // Prepare a union
        List<String> union = new ArrayList<>(list1);
        union.addAll(list2);
        // Prepare an intersection
        List<String> intersection = new ArrayList<>(list1);
        intersection.retainAll(list2);
        // Subtract the intersection from the union
        union.removeAll(intersection);
        return union;
    }

    public static List<String> getTimePeriodList(Date start, Date end) {
        SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
        List<String> quartersList = new ArrayList<>();
        int startQuarter = getQuarter(start);
        int endQuarter = getQuarter(end);
        int startYear = Integer.parseInt(getYear.format(start));
        int limit = NumericConstants.FOUR;
        int endYear = Integer.parseInt(getYear.format(end));
        while (startYear <= endYear) {
            if (startYear == endYear) {
                limit = endQuarter;
            }
            while (startQuarter <= limit) {
                quartersList.add("Q" + startQuarter + " - " + startYear);
                startQuarter++;
            }
            startYear++;
            startQuarter = 1;
        }
        return quartersList;
    }

    private static int getQuarter(Date end) {
        SimpleDateFormat getMonth = new SimpleDateFormat("MM");
        return Integer.parseInt(getMonth.format(end)) % NumericConstants.THREE == 0 ? (Integer.parseInt(getMonth.format(end)) / NumericConstants.THREE) : (Integer.parseInt(getMonth.format(end)) / NumericConstants.THREE) + 1;
    }

    public static List<String> getEndLevelHierNo(List<Leveldto> levels) {
        List<String> hierNos = new ArrayList<>();
        for (Leveldto level : levels) {
            hierNos.add(String.valueOf(level.getHierarchyNo()));
        }
        return hierNos;
    }

    public static String getDateFromQuarter(String quarter) {
        String slash = "/";
        String dd = "01";
        String MM = "01";
        String date = "";
        String[] split = quarter.split(" - ");
        String splitQuarter = split[0];
        int quarterValue = UiUtils.parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (quarterValue == 1) {
            MM = "01";
        } else if (quarterValue == NumericConstants.TWO) {
            MM = "04";
        } else if (quarterValue == NumericConstants.THREE) {
            MM = "07";
        } else if (quarterValue == NumericConstants.FOUR) {
            MM = "10";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static String getTimePeriod(Date date) {
        String timePeriod = StringUtils.EMPTY;
        if (date != null) {
            SimpleDateFormat getYear = new SimpleDateFormat("yyyy");
            int quarter = getQuarter(date);
            int year = Integer.parseInt(getYear.format(date));
            timePeriod = "Q" + quarter + " - " + year;
        }
        return timePeriod;
    }

    public static void setHistoryLimit(ForecastDTO forecastDTO) {
        Date tempDate = new Date();
        tempDate.setMonth(tempDate.getMonth() - 1);
        forecastDTO.setHistoryEndYear(tempDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        forecastDTO.setHistoryEndMonth(tempDate.getMonth() + 1);
        forecastDTO.setHistoryEndDate(tempDate);
        Date tempStartDate = new Date();
        tempStartDate.setYear(tempStartDate.getYear() - NumericConstants.THREE);
        tempStartDate.setMonth(0);
        forecastDTO.setHistoryStartYear(tempStartDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        forecastDTO.setHistoryStartMonth(tempStartDate.getMonth());
        forecastDTO.setHistoryStartDate(tempStartDate);

    }

    public static ForecastDTO getForecastDTO(DataSelectionDTO dataSelectionDTO, SessionDTO session) {
        ForecastDTO forecastDTO;
        if (session.getForecastDTO() == null) {
            forecastDTO = new ForecastDTO();
        } else {
            forecastDTO = session.getForecastDTO();
        }
        DataSelectionLogic logic = new DataSelectionLogic();
        logic.setForcastFileDate(dataSelectionDTO);
        Date tempDate = new Date();
        tempDate.setMonth(dataSelectionDTO.getFileEndMonth() - 1);
        tempDate.setYear(dataSelectionDTO.getFileEndYear() - NumericConstants.ONE_NINE_ZERO_ZERO);
        if (tempDate.before(dataSelectionDTO.getToDate())) {
            forecastDTO.setProjectionEndYear(tempDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setProjectionEndMonth(tempDate.getMonth() + 1);
            forecastDTO.setProjectionEndDate(tempDate);
            forecastDTO.setForecastEndYear(dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setForecastEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
            forecastDTO.setForecastEndDate(dataSelectionDTO.getToDate());
        } else {
            forecastDTO.setProjectionEndYear(dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setProjectionEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
            forecastDTO.setProjectionEndDate(dataSelectionDTO.getToDate());
            forecastDTO.setForecastEndYear(dataSelectionDTO.getToDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setForecastEndMonth(dataSelectionDTO.getToDate().getMonth() + 1);
            forecastDTO.setForecastEndDate(dataSelectionDTO.getToDate());
        }

        DataSelectionUtil.setHistoryLimit(forecastDTO);
        Date tempForecastDate = new Date();
        forecastDTO.setForecastStartYear(tempForecastDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
        forecastDTO.setForecastStartMonth(tempForecastDate.getMonth() + 1);
        forecastDTO.setForecastStartDate(tempForecastDate);
        if (dataSelectionDTO.getFromDate().before(forecastDTO.getHistoryStartDate())) {
            forecastDTO.setProjectionStartYear(forecastDTO.getHistoryStartDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setProjectionStartMonth(forecastDTO.getHistoryStartDate().getMonth()+1);
            forecastDTO.setProjectionStartDate(forecastDTO.getHistoryStartDate());
        } else {
            forecastDTO.setProjectionStartYear(dataSelectionDTO.getFromDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO);
            forecastDTO.setProjectionStartMonth(dataSelectionDTO.getFromDate().getMonth()+1);
            forecastDTO.setProjectionStartDate(dataSelectionDTO.getFromDate());
        }

        // Added to handle the case of file date is greater than the forecast date
        if (forecastDTO.getProjectionEndDate().after(forecastDTO.getForecastEndDate())) {
            forecastDTO.setProjectionEndYear(forecastDTO.getForecastEndYear());
            forecastDTO.setProjectionEndMonth(forecastDTO.getForecastEndMonth());
            forecastDTO.setProjectionEndDate(forecastDTO.getForecastEndDate());
        }
        session.setForecastDTO(forecastDTO);
        return forecastDTO;
    }

    public static List<String> getRemovedLevelsRsid(List<Leveldto> originalList, List<Leveldto> newList) {
        List<String> endLevel = null;
        List<String> oldRsids = new ArrayList<>();
        List<String> newRsds = new ArrayList<>();
        for (Leveldto level : originalList) {
            oldRsids.add(String.valueOf(level.getRelationshipLevelSid()));
        }
        for (Leveldto level : newList) {
            newRsds.add(String.valueOf(level.getRelationshipLevelSid()));
        }
        endLevel = new ArrayList<>(oldRsids);
        endLevel.removeAll(newRsds);
        return endLevel;
    }

    public static List<String> getRemovedLevelsHno(List<Leveldto> originalList, List<Leveldto> newList) {
        List<String> endLevel = null;
        List<String> oldRsids = new ArrayList<>();
        List<String> newRsds = new ArrayList<>();
        for (Leveldto level : originalList) {
            oldRsids.add(String.valueOf(level.getHierarchyNo()));
        }
        for (Leveldto level : newList) {
            newRsds.add(String.valueOf(level.getHierarchyNo()));
        }
        endLevel = new ArrayList<>(oldRsids);
        endLevel.removeAll(newRsds);
        return endLevel;
    }

    public static List<String> getNewLevelRsid(List<Leveldto> originalList, List<Leveldto> newList) {
        List<String> addLevel = null;
        List<String> oldHno = new ArrayList<>();
        List<String> newHno = new ArrayList<>();
        for (Leveldto level : originalList) {
            oldHno.add(String.valueOf(level.getRelationshipLevelSid()));
        }
        for (Leveldto level : newList) {
            newHno.add(String.valueOf(level.getRelationshipLevelSid()));
        }
        addLevel = new ArrayList<>(newHno);
        addLevel.removeAll(oldHno);
        return addLevel;
    }

    public static List<String> getNewLevelHnos(List<Leveldto> originalList, List<Leveldto> newList) {
        List<String> addLevel = null;
        List<String> oldHno = new ArrayList<>();
        List<String> newHno = new ArrayList<>();
        for (Leveldto level : originalList) {
            oldHno.add(level.getHierarchyNo());
        }
        for (Leveldto level : newList) {
            newHno.add(level.getHierarchyNo());
        }
        addLevel = new ArrayList<>(newHno);
        addLevel.removeAll(oldHno);
        return addLevel;
    }

    public static List<String> getTempTableList() {
        List<String> tempTables = new ArrayList<>();
        tempTables.add("ST_NM_SALES_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_SALES");
        tempTables.add("ST_NM_SALES_PROJECTION_MASTER");
        tempTables.add("ST_NM_DISCOUNT_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_DISCOUNT");
        tempTables.add("ST_NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("ST_NM_PPA_PROJECTION");
        tempTables.add("ST_NM_ACTUAL_PPA");
        tempTables.add("ST_NM_PPA_PROJECTION_MASTER");
        tempTables.add("ST_NM_ASSUMPTIONS");
        tempTables.add("NM_SALES_PROJECTION");
        tempTables.add("NM_ACTUAL_SALES");
        tempTables.add("NM_SALES_PROJECTION_MASTER");
        tempTables.add("NM_DISCOUNT_PROJECTION");
        tempTables.add("NM_ACTUAL_DISCOUNT");
        tempTables.add("NM_DISCOUNT_PROJ_MASTER");
        tempTables.add("NM_PPA_PROJECTION");
        tempTables.add("NM_ACTUAL_PPA");
        tempTables.add("NM_PPA_PROJECTION_MASTER");
        tempTables.add("NM_ASSUMPTIONS");
        tempTables.add("PROJECTION_DETAILS");
        return tempTables;
    }

    public static String getLastDateFromQuarter(String quarter) {
        String slash = "/";
        String dd = "30";
        String MM = "01";
        String date = "";
        String[] split = quarter.split(" - ");
        String splitQuarter = split[0];
        int quarterValue = UiUtils.parseStringToInteger(splitQuarter);
        String yyyy = split[1];
        if (quarterValue == 1) {
            MM = "03";
            dd = "31";
        } else if (quarterValue == NumericConstants.TWO) {
            MM = "06";
            dd = "30";
        } else if (quarterValue == NumericConstants.THREE) {
            MM = "09";
            dd = "30";
        } else if (quarterValue == NumericConstants.FOUR) {
            MM = "12";
            dd = "31";
        }

        date = MM + slash + dd + slash + yyyy;
        return date;
    }

    public static void mapUsers() {
        userMap.clear();
    }

    public static String filterUser(String filter) {
        List<String> keys = new ArrayList<>();
        String userIds;
        if (userMap != null) {
            for (Map.Entry<String, String> entry : userMap.entrySet()) {
                if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
                    keys.add(String.valueOf(entry.getKey()));
                }
            }
        }
        if (!keys.isEmpty()) {
            userIds = UiUtils.stringListToString(keys);
        } else {
            userIds = "0";
        }
        return userIds;
    }

    public static String getUserName(String userId) {
        String userName = StringUtils.EMPTY;
        if (userMap != null) {
            userName = userMap.get(userId);
        }
        return userName;
    }

    public static List<Leveldto> getFSValue(final String relationshipLevelValue, final String fieldName) {
        List<Leveldto> list = new ArrayList<>();
        DataSelectionLogic logic = new DataSelectionLogic();
        List<Object> listValue = logic.getFSValue(relationshipLevelValue, fieldName);
        Leveldto dto = new Leveldto();
        for (int i = 0; i < listValue.size(); i++) {
            Object[] obj = (Object[]) listValue.get(i);
            dto.setForm(String.valueOf(obj[0]));
            dto.setStrength(String.valueOf(obj[1]));
            list.add(dto);
        }
        return list;
    }

    public static String getCcpWithCC(final List<Leveldto> ccList) {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("SELECT DISTINCT CCP.itemMasterSid FROM CcpDetails CCP ");
        List<String> companyMasterValues = new ArrayList<>();
        List<String> contractMasterValues = new ArrayList<>();
        Leveldto ddo;
        for (int loop = 0, limit = ccList.size(); loop < limit; loop++) {
            ddo = ccList.get(loop);
            if (ddo.getTableName().equalsIgnoreCase("CONTRACT_MASTER")) {
                contractMasterValues.add(ddo.getRelationshipLevelValue());
            } else if (ddo.getTableName().equalsIgnoreCase("COMPANY_MASTER")) {
                companyMasterValues.add(ddo.getRelationshipLevelValue());
            }
        }
        if (!companyMasterValues.isEmpty() || !contractMasterValues.isEmpty()) {
            String and = StringUtils.EMPTY;
            query.append(" WHERE ");
            if (!companyMasterValues.isEmpty()) {
                query.append(" CCP.companyMasterSid IN (");
                query.append(UiUtils.stringListToString(companyMasterValues));
                query.append(')');
                and = " AND ";
            }
            if (!contractMasterValues.isEmpty()) {
                query.append(and);
                query.append(" CCP.contractMasterSid IN (");
                query.append(UiUtils.stringListToString(contractMasterValues));
                query.append(')');
            }
        }
        return query.toString();
    }

    public static List<String> getItemSidFromHierarchy(List<Leveldto> innerProdLevels) {
        List<String> items = new ArrayList<>();
        if (innerProdLevels != null) {
            for (Leveldto leveldto : innerProdLevels) {
                if (leveldto.isFromItem()) {
                    items.add(leveldto.getRelationshipLevelValue());
                }
            }
        }
        return items;
    }

    public static List<String> getCustomerSidFromHierarchy(List<Leveldto> innerCustLevels) {
        List<String> customers = new ArrayList<>();
        if (innerCustLevels != null) {
            for (Leveldto leveldto : innerCustLevels) {
                if (leveldto.isFromCompany()) {
                    customers.add(leveldto.getRelationshipLevelValue());
                }
            }
        }
        return customers;
    }

    public static List<String> getCompanySidFromHierarchy(List<Leveldto> innerProdLevels, String screenName) {
        List<String> companies = new ArrayList<>();
        if (innerProdLevels != null) {
            for (Leveldto leveldto : innerProdLevels) {
                if ((screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_CHANNELS)) && leveldto.isFromCompany() || ("Company".equalsIgnoreCase(leveldto.getLevel()) || "GL Comp".contains(leveldto.getLevel()) || "GL Company".contains(leveldto.getLevel()))) {
                    companies.add(leveldto.getRelationshipLevelValue());
                } else if (leveldto.isFromCompany()) {
                    companies.add(leveldto.getRelationshipLevelValue());
                }
            }
        }
        return companies;
    }

    public static void mapUserIds() {
        userIdMap.clear();
    }

    public static String filterUserId(String filter) {
        List<String> keys = new ArrayList<>();
        String userIds;
        if (userIdMap != null) {
            for (Map.Entry<String, String> entry : userIdMap.entrySet()) {
                if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
                    keys.add(String.valueOf(entry.getKey()));
                }
            }
        }
        if (!keys.isEmpty()) {
            userIds = UiUtils.stringListToString(keys);
        } else {
            userIds = "0";
        }
        return userIds;
    }

    public static String getUserId(String userId) {
        String userName = StringUtils.EMPTY;
        if (userIdMap != null) {
            userName = userIdMap.get(userId);
        }
        return userName;
    }

    public static String identifyLevel(Leveldto levelDto) {
        String level = StringUtils.EMPTY;
        if (!StringUtils.isBlank(levelDto.getLevel()) && !NULL.equalsIgnoreCase(levelDto.getLevel())) {
            if ("COMPANY_MASTER".equalsIgnoreCase(levelDto.getTableName())
                    && ("Customer".equalsIgnoreCase(levelDto.getLevel())
                    || "Company".equalsIgnoreCase(levelDto.getLevel())
                    || "Trading Partner".equalsIgnoreCase(levelDto.getLevel()))) {
                level = INDICATOR_LEVEL_CUSTOMER.getConstant();
            } else if ("CONTRACT_MASTER".equalsIgnoreCase(levelDto.getTableName())
                    && levelDto.getLevel().contains("Contract")) {
                level = INDICATOR_LEVEL_CONTRACT.getConstant();
            } else if (StringConstantsUtil.ITEM_MASTER.equalsIgnoreCase(levelDto.getTableName())
                    && (levelDto.getLevel().contains("NDC")
                    || "Item".equalsIgnoreCase(levelDto.getLevel()))) {
                level = INDICATOR_LEVEL_NDC.getConstant();
            }
        }
        return level;
    }

    public static void mapDiscounts() {
        discountMap.clear();
    }

    public static String filterDiscount(String filter) {
        List<String> keys = new ArrayList<>();
        String discountIds;
        if (discountMap != null) {
            for (Map.Entry<String, String> entry : discountMap.entrySet()) {
                if ((String.valueOf(entry.getValue()).toLowerCase().trim()).contains(filter.toLowerCase().trim())) {
                    keys.add(String.valueOf(entry.getKey()));
                }
            }
        }
        if (!keys.isEmpty()) {
            discountIds = UiUtils.stringListToString(keys);
        } else {
            discountIds = "0";
        }
        return discountIds;
    }

    public static String getDiscountName(String discountId) {
        String userName = StringUtils.EMPTY;
        if (discountMap != null) {
            userName = discountMap.get(discountId);
        }
        return userName;
    }
        }
