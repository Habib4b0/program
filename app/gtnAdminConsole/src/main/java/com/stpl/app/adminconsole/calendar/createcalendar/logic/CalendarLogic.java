/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.createcalendar.logic;

import com.stpl.app.adminconsole.calendar.createcalendar.logic.dto.CalendarDetailsDTO;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author SYSBIZ\abishekram.r
 */
public class CalendarLogic {

    static int id;
    private final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CalendarLogic.class);
    final static CommonDAO baseRateDAO = new CommonDAOImpl();
    Map<String, String> userMap;

    public CalendarLogic() {
        try {
            this.userMap = CommonUtil.getCreatedByUser();
        } catch (SystemException ex) {
            Logger.getLogger(CalendarLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * For Getting Calendar From Master
     *
     * @param props
     * @return
     */
    public List getcalendar(final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, Map props, final Set<Container.Filter> filterSet, boolean iscount) {
        StringBuilder query = new StringBuilder("select * from CALENDAR_CONFIG_MASTER");
        getParameterisedQuery(query, props);
        String finalQuery = getFinalquery(query.toString(), getFilterquery(filterSet), getOrderquery(startIndex, endIndex, sortByColumns, getDBMap()), iscount);
        LOGGER.debug("Calendar Search Query: " + finalQuery);
        List<Object[]> list = (List<Object[]>) baseRateDAO.executeSelectQuery(finalQuery, null, null);
        return converTListtoDTO(list);
    }

    private String getOrderquery(final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, Map dBmap) {
        boolean sortOrder = false;
        dBmap.put("createdBy", "CREATED_BY");
        dBmap.put("modifiedBy", "MODIFIED_BY");
        String columnName = null;
        String order = StringUtils.EMPTY;
        String orderByColumn = null;
        if (sortByColumns != null) {
            for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn sortByColumn = (SortByColumn) iterator.next();
                columnName = sortByColumn.getName();
                orderByColumn = (String) dBmap.get(columnName);
                sortOrder = sortByColumn.getType() != SortByColumn.Type.ASC;
            }
        }
        if (orderByColumn != null) {
            order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
        }
        if (order.isEmpty()) {
            order = order + " ORDER BY CALENDAR_NAME  ASC";
        }
        order = order + " " + "OFFSET ";
        order = order + startIndex;
        order = order + " ROWS FETCH NEXT " + endIndex;
        order = order + " ROWS ONLY;";

        return order;
    }

    private String getFilterquery(final Set<Container.Filter> filterSet) {
        return AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, getDBMap()).toString() + " " + getUserFilterQuery(filterSet);
    }

    private String getUserFilterQuery(final Set<Container.Filter> filterSet) {
        String userQuery = StringUtils.EMPTY;
        String createdQuery = StringUtils.EMPTY;
        String modifiedQuery = StringUtils.EMPTY;
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    
                    if (!stringFilter.getFilterString().isEmpty()) {
                        if (stringFilter.getPropertyId().equals("createdBy")) {
                            createdQuery = genereateUserQuery("CREATED_BY", stringFilter.getFilterString());
                        } else if (stringFilter.getPropertyId().equals("modifiedBy")) {
                            modifiedQuery = genereateUserQuery("MODIFIED_BY", stringFilter.getFilterString());
                        }
                    }
                    
                }

            }
        }

        if (!createdQuery.isEmpty()) {
            userQuery += "AND " + createdQuery;
        }
        if (!modifiedQuery.isEmpty()) {
            userQuery += "AND " + modifiedQuery;
        }

        return userQuery;

    }

    private String genereateUserQuery(String columnName, String fiterString) {

        String query = StringUtils.EMPTY;
        for (String key : userMap.keySet()) {
            if ((userMap.get(key).toLowerCase()).contains((fiterString.trim()).toLowerCase())) {
                if (query.isEmpty()) {
                    query = columnName + " IN (" + key;
                } else {
                    query = query + "," + key;
                }

            }
        }
        if (!query.isEmpty()) {
            query = query + ")";
        } else {
            query = columnName + " IN ('" + fiterString + "')";
        }
        return query;

    }

    private String getFinalquery(String searchquery, String filterQuery, String orderQuery, boolean isCount) {
        if (searchquery.contains("where")) {
            filterQuery = filterQuery.replace("where", "and");
        }
        String finalString = searchquery + " " + filterQuery;
        if (!isCount) {
            finalString += orderQuery;
        }

        return finalString;
    }

    private Map getDBMap() {
        Map idDBrelationMap = new HashMap<>();
        idDBrelationMap.put("calendarName", "CALENDAR_NAME");
        idDBrelationMap.put("calendarDescription", "CALENDAR_DESCRIPTION");
        idDBrelationMap.put("calendarYear", "CALENDAR_YEAR");
        idDBrelationMap.put("createdDate", "CREATED_DATE");
        idDBrelationMap.put("modifiedDate", "MODIFIED_DATE");
        return idDBrelationMap;
    }

    private void getParameterisedQuery(StringBuilder query, Map props) {
        boolean isFirst = true;
        if (!props.isEmpty()) {
            for (Object entry : props.entrySet()) {
                Map.Entry entryKey = (Map.Entry) entry;
                if (isFirst) {
                    query.append(" where ").append(entryKey.getKey()).append(" like ").append("'").append(entryKey.getValue()).append("'");
                    isFirst = false;
                } else {
                    query.append(" and ").append(entryKey.getKey()).append(" like ").append("'").append(entryKey.getValue()).append("'");
                }
            }
        }
    }

    private List converTListtoDTO(List<Object[]> list) {
        List<CalendarDetailsDTO> searchResults = new ArrayList<>();
        List<Object[]> changedList = convertNullObjectsToEmptyString(list);
        for (Object[] objects : changedList) {
            CalendarDetailsDTO temp = new CalendarDetailsDTO();
            temp.setId(Long.valueOf(String.valueOf(objects[0])));
            temp.setCalendarName(String.valueOf(objects[1]));
            temp.setCalendarDescription(String.valueOf(objects[NumericConstants.TWO]));
            temp.setCalendarYear(Integer.valueOf(String.valueOf(objects[NumericConstants.THREE])));
            temp.setCountry(String.valueOf(objects[NumericConstants.FOUR]));
            temp.setCreatedDate(String.valueOf(objects[NumericConstants.FIVE]).isEmpty() ? 0L : ((java.sql.Timestamp) objects[NumericConstants.FIVE]).getTime());
            temp.setCreatedBy(userMap.get(String.valueOf(objects[NumericConstants.SIX])));
            temp.setModifiedDate(String.valueOf(objects[NumericConstants.SEVEN]).isEmpty() ? 0L : ((java.sql.Timestamp) objects[NumericConstants.SEVEN]).getTime());
            temp.setModifiedBy(userMap.containsKey(String.valueOf(objects[NumericConstants.EIGHT])) ? userMap.get(String.valueOf(objects[NumericConstants.EIGHT])) : "");
            temp.setDefaultHolidays(Boolean.valueOf(String.valueOf(objects[NumericConstants.NINE])));
            searchResults.add(temp);
        }
        LOGGER.debug("Total Calendar for Search: " + searchResults.size());
        return searchResults;
    }

    private List<Object[]> convertNullObjectsToEmptyString(List<Object[]> list) {
        for (Object[] objects : list) {
            for (int i = 0; i < objects.length; i++) {
                objects[i] = objects[i] == null ? StringUtils.EMPTY : String.valueOf(objects[i]).equals("null") ? StringUtils.EMPTY : objects[i];
            }
        }
        return list;
    }

    /**
     * For Saving calendar
     *
     * @param cddto
     * @param session
     */
    public int saveCalendar(CalendarDetailsDTO cddto, SessionDTO session) {
        int masterId;
        if (cddto.getId() == 0l) {
            masterId = insertNewIntoCalendarMaster(cddto, session);
        } else {
            masterId = ((Long) cddto.getId()).intValue();
            updateMaster(cddto, session);
            deleteOldCalendarDetails(cddto);
        }
        insertIntoCalendarConfig(masterId, cddto.getHolidayDays());
        return masterId;
    }

    private int insertNewIntoCalendarMaster(CalendarDetailsDTO cddto, SessionDTO session) {
        String defaultHolidays = cddto.isDefaultHolidays()== true? "'1'" : "'0'";
        String insertQuery = "INSERT INTO CALENDAR_CONFIG_MASTER(CALENDAR_NAME,CALENDAR_DESCRIPTION,CALENDAR_YEAR,COUNTRY,CREATED_DATE,CREATED_BY,MODIFIED_DATE,MODIFIED_BY,DEFAULT_HOLIDAYS) VALUES('" + cddto.getCalendarName() + "','" + cddto.getCalendarDescription() + "'," + cddto.getCalendarYear() + ",'" + cddto.getCountry() + "','" + dbDateString(new Date()) + "'," + session.getUserId() + ",null,'',"+ defaultHolidays+")SELECT SCOPE_IDENTITY();";
        LOGGER.debug("Calendar master Insert Query: " + insertQuery);
        List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(insertQuery);
        return ((BigDecimal) (list.get(0))).intValue();
    }

    private String dbDateString(Date date) {
        return new Timestamp(date.getTime()).toString();
    }

    private void insertIntoCalendarConfig(int masterId, Map holidayDays) {
        StringBuilder insertQuery = new StringBuilder("INSERT INTO CALENDAR_CONFIG_DETAILS(CALENDAR_CONFIG_MASTER_SID,HOLIDAYS_PERIOD_DATE)VALUES");
        List<Date> dates = (List<Date>) holidayDays.get("newHolidays");
        for (int i = 0; i < dates.size(); i++) {
            Date date = dates.get(i);
            insertQuery.append("(").append(masterId).append(",'").append(dbDateString(date)).append("')");
            if (i != (dates.size() - 1)) {
                insertQuery.append(",");
            }
        }
        LOGGER.debug("Calendar Details Insert Query: " + insertQuery.toString());
        baseRateDAO.executeBulkUpdateQuery(insertQuery.toString(), null, null);
    }

    /**
     * Get Holidays Based on Master
     *
     * @param Id
     * @return
     */
    public List getHolidaysForCalendar(long Id) {
        LOGGER.debug("Getting Holidays for Calendar with ID - " + Id);
        String selectQuery = "select HOLIDAYS_PERIOD_DATE  from CALENDAR_CONFIG_DETAILS where CALENDAR_CONFIG_MASTER_SID =" + Id;
        LOGGER.debug("Calendar Details Select Query - " + selectQuery);
        return (List<Object>) baseRateDAO.executeSelectQuery(selectQuery, null, null);
    }

    private void updateMaster(CalendarDetailsDTO cddto, SessionDTO session) {
        String defaultHolidays = cddto.isDefaultHolidays()== true? "1" : "0";
        String updateQuery = "Update CALENDAR_CONFIG_MASTER set CALENDAR_NAME='" + cddto.getCalendarName() + "',CALENDAR_DESCRIPTION='" + cddto.getCalendarDescription() + "',CALENDAR_YEAR=" + cddto.getCalendarYear() + ",COUNTRY='" + cddto.getCountry() + "',MODIFIED_DATE='" + dbDateString(new Date()) + "',MODIFIED_BY=" + session.getUserId() + ", DEFAULT_HOLIDAYS='"+ defaultHolidays+"' WHERE CALENDAR_CONFIG_MASTER_SID=" + cddto.getId() + "";
        baseRateDAO.executeBulkUpdateQuery(updateQuery, null, null);
    }

    /**
     * Delete calendar
     *
     * @param cddto
     */
    public void deleteRecord(CalendarDetailsDTO cddto) {
        deleteOldCalendarDetails(cddto);
        deleteCalendarMaster(cddto);
    }

    /**
     *
     * @param cddto
     * @return
     */
    public boolean validateBeforeDelete(CalendarDetailsDTO cddto) {
        return isPresentinAutoCLOSE(cddto) || isPresentinCLOSE(cddto);
    }

    public boolean isPresentinAutoCLOSE(CalendarDetailsDTO cddto) {
        String checkIncompanyMaster = "select count(*) from COMPANY_FINANCIAL_CLOSE_AUTO where  CALENDAR  =" + cddto.getId();
        List<Object> count = (List<Object>) baseRateDAO.executeSelectQuery(checkIncompanyMaster, null, null);
        return ((Integer) count.get(0)) > 0;
    }

    public boolean isPresentinCLOSE(CalendarDetailsDTO cddto) {
        String checkIncompanyMaster = "select count(*) from COMPANY_FINANCIAL_CLOSE where CALENDAR  = " + cddto.getId();
        List<Object> count = (List<Object>) baseRateDAO.executeSelectQuery(checkIncompanyMaster, null, null);
        return ((Integer) count.get(0)) > 0;
    }

    private void deleteOldCalendarDetails(CalendarDetailsDTO cddto) {
        String deleteQuery = "Delete from CALENDAR_CONFIG_DETAILS where CALENDAR_CONFIG_MASTER_SID=" + cddto.getId();
        baseRateDAO.executeBulkUpdateQuery(deleteQuery, null, null);
    }

    private void deleteCalendarMaster(CalendarDetailsDTO cddto) {
        String deleteQuery = "Delete from CALENDAR_CONFIG_MASTER where CALENDAR_CONFIG_MASTER_SID =" + cddto.getId();
        baseRateDAO.executeBulkUpdateQuery(deleteQuery, null, null);
    }

    /**
     * Load Country from UDC
     *
     * @return
     */
    public List<Object> loadCountry() {
        String selectQuery = "select DESCRIPTION from HELPER_TABLE where LIST_NAME like '%Country%'";
        return (List<Object>) baseRateDAO.executeSelectQuery(selectQuery, null, null);
    }

}
