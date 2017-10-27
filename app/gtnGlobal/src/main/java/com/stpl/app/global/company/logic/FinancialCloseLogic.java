
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.logic;

import com.stpl.app.global.abstractsearch.logic.FilterLogic;
import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.util.QueryUtils;
import com.stpl.app.global.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.util.ConstantsUtils;
import static com.stpl.app.util.GeneralCommonUtils.ZERO;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.SysDataSourceConnection;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author STPLROOT
 */
public class FinancialCloseLogic {

    public static final SimpleDateFormat DBDate = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DB_Date = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");

    private static final Logger LOGGER = Logger.getLogger(FinancialCloseLogic.class);

    /**
     * Method to return count from db only for count Query.
     *
     * @param list
     * @return 0 or no .of count from db as count
     */
    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    /**
     * get status from
     */
    public String getStatus(final int year, final int period, final int companyMasterSid) {
        List input = new ArrayList();
        input.add(year);
        input.add(period);
        input.add(companyMasterSid);
        List<Object[]> list = QueryUtils.getAppData(input, "getStatus", null);
        String status = StringUtils.EMPTY;
        if (!list.isEmpty()) {
            Object[] obj = list.get(0);
            status = obj[1] != null ? (String) obj[1] : StringUtils.EMPTY;
        }
        return status;
    }

    /**
     * Get Months from java
     */
    public List getMonths() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        int num = 0;
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
        }
        List<String> list = new ArrayList<>();
        list.add(StringUtils.EMPTY);
        list.addAll(Arrays.asList(months));
        list.remove(list.size() - 1);
        return list;
    }

    /**
     * Get financialCloseCount
     *
     * Return no of count available in the table
     */
    public int getFinancialCloseCount(final FinancialCloseDTO binderDto, final BeanItemContainer<FinancialCloseDTO> resultsContainer) {
        int count = 0;
        try (Connection con = SysDataSourceConnection.getConnection()) {
            if (binderDto.isFirstTimeEdit()) {
                List inputList = new ArrayList();
                String filterQuery = StringUtils.EMPTY;
                if (binderDto.getFilters() != null) {
                    filterQuery = FilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getQueryFilterMap(false)).toString();
                    filterQuery = filterQuery.replace("where", ConstantsUtils.AND);
                }
                String sortQuery = StringUtils.EMPTY;
                if (binderDto.getSortedColumns() != null && !binderDto.getSortedColumns().isEmpty()) {
                    String customSort = FilterLogic.getInstance().orderByQueryGenerator(binderDto.getSortedColumns(), getQueryFilterMap(true)).toString();
                    sortQuery = customSort.replace("ORDER BY", "");
                } else {
                    sortQuery = "cfc.CREATED_DATE desc ";
                }
                inputList.add(con.getCatalog());
                inputList.add(binderDto.getCompanyMasterSid());
                inputList.add(filterQuery);
                inputList.add(sortQuery);

                List<FinancialCloseDTO> list = getCustomizedFinancialClose(QueryUtils.getAppData(inputList, "getFinanicalCloseData", null));
                binderDto.setInitialRecordList(list);
                for (FinancialCloseDTO financialCloseDTO : list) {
                    resultsContainer.addItem(financialCloseDTO);
                }
                binderDto.setFirstTimeEdit(false);
            }
            if (binderDto.isReset()) {
                resultsContainer.removeAllItems();
                binderDto.setReset(false);
                if (binderDto.getInitialRecordList() != null) {
                    for (FinancialCloseDTO financialCloseDTO : binderDto.getInitialRecordList()) {
                        resultsContainer.addItem(financialCloseDTO);
                    }
                }
            } else if (binderDto.isGenerate()) {
                count += 1;
            }
            FilterLogic.getInstance().filterQueryForContainer(binderDto.getFilters(), resultsContainer);
            count += resultsContainer.size();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    /**
     * Get financialCloseCount
     *
     * Return no of count available in the table
     */
    public List getFinancialCloseData(final FinancialCloseDTO binderDto, final BeanItemContainer<FinancialCloseDTO> resultsContainer) {
        List<FinancialCloseDTO> list = new ArrayList<>();
        FilterLogic.getInstance().orderByQueryForContainer(binderDto.getSortedColumns(), resultsContainer);
        if (binderDto.isGenerate()) {
            resultsContainer.addItemAt(0, getFinancialRecord(binderDto));
            binderDto.setGenerate(false);
        }
        list.addAll(resultsContainer.getItemIds(binderDto.getStart(), binderDto.getEnd()));
        return list;
    }

    private static FinancialCloseDTO getFinancialRecord(FinancialCloseDTO binderDto) {
        FinancialCloseDTO dto = new FinancialCloseDTO();
        try {
            Map<Integer, String> userMap = StplSecurity.getUserName();
            dto.setMode(binderDto.getModeDdlb() != null && !binderDto.getModeDdlb().getDescription().equals(ConstantsUtils.SELECT_ONE) ? binderDto.getModeDdlb().getDescription() : StringUtils.EMPTY);
            dto.setModeDdlb(binderDto.getModeDdlb());
            dto.setCalendar(binderDto.getCalendar() != null && !ConstantsUtils.NULL.equals(binderDto.getCalendar()) ? binderDto.getCalendar() : StringUtils.EMPTY); //For GAL-9301
            dto.setCalenderDdlb(binderDto.getCalenderDdlb());
            dto.setStatus(binderDto.getStatusDdlb() != null && !binderDto.getStatusDdlb().getDescription().equals(ConstantsUtils.SELECT_ONE) ? binderDto.getStatusDdlb().getDescription() : StringUtils.EMPTY);
            dto.setStatusDdlb(binderDto.getStatusDdlb());
            dto.setDateTime(new Date());
            dto.setCreatedDate(new Date());
            dto.setCreatedBy(userMap.get(Integer.valueOf(binderDto.getCreatedBy())));
            dto.setCreatedByDdlb(new HelperDTO(Integer.valueOf(binderDto.getCreatedBy()), userMap.get(Integer.valueOf(binderDto.getCreatedBy()))));
            dto.setYear(binderDto.getYear());
            dto.setMonth(binderDto.getMonth());
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return dto;
    }

    public static void setAutomaticFinancialRecord(FinancialCloseDTO binderDto) {
        FinancialCloseDTO dto = getFinancialRecord(binderDto);
        List addModeList = new ArrayList<>();
        dto.setMinuteDdlb(binderDto.getMinuteDdlb());
        dto.setHourDdlb(binderDto.getHourDdlb());
        dto.setBusinessDayDdlb(binderDto.getBusinessDayDdlb());
        addModeList.add(dto);
        binderDto.setAddMode_Container_MainList(addModeList);
    }

    /**
     * Get customized list from Query
     *
     * @param list
     * @return DTO list which to be added in container
     */
    private List<FinancialCloseDTO> getCustomizedFinancialClose(final List<Object[]> list) throws SystemException {
        final List<FinancialCloseDTO> fiancialList = new ArrayList<>();
        Map<Integer, String> userMap = StplSecurity.getUserName();
        for (Object[] obj : list) {
            FinancialCloseDTO dto = new FinancialCloseDTO();
            HelperDTO helperDto = new HelperDTO();
            helperDto.setId(obj[1] != null && !String.valueOf(obj[1]).equals("0") ? (Integer) obj[1] : 0);
            helperDto.setDescription(obj[NumericConstants.TWO] != null && !String.valueOf(obj[NumericConstants.TWO]).equals(StringUtils.EMPTY) ? (String) obj[NumericConstants.TWO] : StringUtils.EMPTY);
            dto.setMode(helperDto.getDescription());
            dto.setModeDdlb(helperDto);
            dto.setCalenderDdlb(obj[NumericConstants.THREE] != null && !String.valueOf(obj[NumericConstants.THREE]).equals("0") ? (Integer) obj[NumericConstants.THREE] : 0);
            dto.setCalendar(obj[NumericConstants.FOUR] != null && !String.valueOf(obj[NumericConstants.FOUR]).equals(StringUtils.EMPTY) ? (String) obj[NumericConstants.FOUR] : StringUtils.EMPTY);
            helperDto = new HelperDTO();
            helperDto.setId(obj[NumericConstants.FIVE] != null && !String.valueOf(obj[NumericConstants.FIVE]).equals("0") ? (Integer) obj[NumericConstants.FIVE] : 0);
            helperDto.setDescription(obj[NumericConstants.SIX] != null && !String.valueOf(obj[NumericConstants.SIX]).equals(StringUtils.EMPTY) ? (String) obj[NumericConstants.SIX] : StringUtils.EMPTY);
            dto.setStatus(helperDto.getDescription());
            dto.setStatusDdlb(helperDto);
            dto.setYear(obj[NumericConstants.SEVEN] != null && !String.valueOf(obj[NumericConstants.SEVEN]).equals(StringUtils.EMPTY) ? String.valueOf(obj[NumericConstants.SEVEN]) : StringUtils.EMPTY);
            dto.setMonth(obj[NumericConstants.EIGHT] != null && !String.valueOf(obj[NumericConstants.EIGHT]).equals(StringUtils.EMPTY) ? String.valueOf(obj[NumericConstants.EIGHT]) : StringUtils.EMPTY);
            dto.setDateTime(obj[NumericConstants.NINE] != null ? (Date) obj[NumericConstants.NINE] : null);
            dto.setCreatedDate(obj[NumericConstants.TEN] != null ? (Date) obj[NumericConstants.TEN] : null);
            helperDto = new HelperDTO();
            helperDto.setId(obj[NumericConstants.ELEVEN] != null && !String.valueOf(obj[NumericConstants.ELEVEN]).equals("0") ? Integer.valueOf(String.valueOf(obj[NumericConstants.ELEVEN])) : 0);
            helperDto.setDescription(obj[NumericConstants.ELEVEN] != null && !String.valueOf(obj[NumericConstants.ELEVEN]).equals(StringUtils.EMPTY) ? userMap.get(Integer.valueOf(String.valueOf(obj[NumericConstants.ELEVEN]))) : StringUtils.EMPTY);
            dto.setCreatedBy(helperDto.getDescription());
            dto.setCreatedByDdlb(helperDto);
            dto.setHourDdlb(obj[NumericConstants.TWELVE] != null ? String.valueOf(obj[NumericConstants.TWELVE]) : StringUtils.EMPTY);
            helperDto = new HelperDTO();
            helperDto.setId(obj[NumericConstants.THIRTEEN] != null && !String.valueOf(obj[NumericConstants.THIRTEEN]).equals("0") ? Integer.valueOf(String.valueOf(obj[NumericConstants.THIRTEEN])) : 0);
            helperDto.setDescription(obj[NumericConstants.FOURTEEN] != null && !String.valueOf(obj[NumericConstants.FOURTEEN]).equals(StringUtils.EMPTY) ? String.valueOf(obj[NumericConstants.FOURTEEN]) : ConstantsUtils.SELECT_ONE);
            dto.setMinuteDdlb(helperDto);
            dto.setBusinessDayDdlb(obj[NumericConstants.FIFTEEN] != null ? String.valueOf(obj[NumericConstants.FIFTEEN]) : StringUtils.EMPTY);
            fiancialList.add(dto);
        }
        return fiancialList;
    }

    /**
     * Query map is a input for Filter implementations
     *
     * @return
     */
    private Map<String, String> getQueryFilterMap(final boolean isSort) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("modeDdlb", "HT_MODE.DESCRIPTION");
        queryMap.put("calendar", "CCG.CALENDAR_NAME");
        queryMap.put("statusDdlb", "HT_STATUS.DESCRIPTION");
        queryMap.put("dateTime", "cfc.STATUS_PERIOD_DATE");
        queryMap.put("createdDate", "cfc.CREATED_DATE");
        queryMap.put("createdByDdlb", "cfc.CREATED_BY");
        queryMap.put("year", "PD.\"YEAR\"");
        queryMap.put("month", "PD.\"MONTH\"");
        if (isSort) {
            queryMap.put("sid", "CFC.CREATED_DATE");
        }
        return queryMap;
    }

    /**
     * This is the main method to save/update status and other values when we
     * choose manual mode - open / close
     *
     * @param binderDto
     */
    public void saveManualStatus(final FinancialCloseDTO binderDto) {
        List input = setOpenCloseInput(binderDto);
        QueryUtils.updateAppData(input, "setManualOpenClose");
    }

    /**
     * This is the main method to save/update status and other values when we
     * choose manual mode - open / close
     *
     * @param binderDto
     */
    public void saveManualStatus(final FinancialCloseDTO binderDto, final BeanItemContainer container) {
        List<FinancialCloseDTO> containerDto = new ArrayList<>();
        container.sort(new Object[]{"dateTime"}, new boolean[]{true});
        containerDto.addAll(container.getItemIds());
        for (FinancialCloseDTO financialCloseDTO : containerDto) {
            if (binderDto.getInitialRecordList() != null && binderDto.getInitialRecordList().contains(financialCloseDTO)) {
                continue;
            }
            financialCloseDTO.setCompanyMasterSid(binderDto.getCompanyMasterSid());
            List input = setOpenCloseInputDto(financialCloseDTO);
            QueryUtils.updateAppData(input, "setManualOpenClose");
        }
    }

    /**
     * Get Helper Sid from Desciption and list name
     *
     * @param listName
     * @param description
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        final ItemDAO DAO = new ItemSearchLogicDAOImpl();
        int code = 0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = DAO.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.valueOf(result.get(ZERO).toString());
        }
        return code;
    }

    /**
     * Method to set inputs in a list as a input param for Executing query
     *
     * @param dto
     * @return List
     */
    private List setOpenCloseInput(final FinancialCloseDTO dto) {
        final List list = new ArrayList();
        list.add(dto.getCompanyMasterSid());
        list.add(dto.getMode() != null && !dto.getMode().equals(StringUtils.EMPTY) ? dto.getMode() : StringUtils.EMPTY);
        list.add(dto.getCalenderDdlb() != null && !dto.getCalenderDdlb().equals("0") ? dto.getCalenderDdlb() : StringUtils.EMPTY);
        list.add(dto.getStatus() != null && !dto.getStatus().equals("0") ? dto.getStatus() : StringUtils.EMPTY);
        list.add(new Timestamp(new Date().getTime()));
        list.add(dto.getCreatedBy() != null && !dto.getCreatedBy().equals(StringUtils.EMPTY) ? dto.getCreatedBy() : StringUtils.EMPTY);
        list.add(new Timestamp(new Date().getTime()));
        list.add(dto.getYear());
        list.add(dto.getMonth());
        return list;
    }

    /* /**
     * Method to set inputs in a list as a input param for Executing query
     *
     * @param dto
     * @return List
     */
    private List setOpenCloseInputDto(final FinancialCloseDTO dto) {
        final List list = new ArrayList();
        list.add(dto.getCompanyMasterSid());
        list.add(dto.getModeDdlb() != null && dto.getModeDdlb().getId() != 0 ? dto.getModeDdlb().getId() : 0);
        list.add(dto.getCalenderDdlb() != null && dto.getCalenderDdlb() != 0 ? dto.getCalenderDdlb() : 0);
        list.add(dto.getStatusDdlb() != null && dto.getStatusDdlb().getId() != 0 ? dto.getStatusDdlb().getId() : 0);
        list.add(new Timestamp(dto.getDateTime().getTime()));
        list.add(dto.getCreatedByDdlb() != null && dto.getCreatedByDdlb().getId() != 0 ? dto.getCreatedByDdlb().getId() : 0);
        list.add(new Timestamp(dto.getCreatedDate().getTime()));
        list.add(dto.getYear());
        list.add(dto.getMonth());
        return list;
    }

    /* /**
     * Method to set inputs in a list as a input param for Executing query
     *
     * @param dto
     * @return List
     */
    private List setAutoCloseInput(final FinancialCloseDTO dto) {
        final List list = new ArrayList();
        list.add(dto.getCompanyMasterSid());
        list.add(dto.getBusinessDayDdlb() != null && !dto.getBusinessDayDdlb().equals(StringUtils.EMPTY) ? dto.getBusinessDayDdlb() : StringUtils.EMPTY);
        list.add(dto.getHourDdlb() != null && !dto.getHourDdlb().equals(StringUtils.EMPTY) ? dto.getHourDdlb() : StringUtils.EMPTY);
        list.add(dto.getMinuteDdlb() != null && dto.getMinuteDdlb().getId() != 0 ? dto.getMinuteDdlb().getId() : 0);
        list.add(dto.getCalenderDdlb());
        return list;
    }

    /**
     * This is the main method to save/update status and other values when we
     * choose manual mode - open / close
     *
     * @param binderDto
     */
    public void saveAutoMode(final FinancialCloseDTO binderDto, final List<FinancialCloseDTO> addModeList) {
        for (FinancialCloseDTO financialCloseDTO : addModeList) {
            financialCloseDTO.setCompanyMasterSid(binderDto.getCompanyMasterSid());
            List input = setAutoCloseInput(financialCloseDTO);
            QueryUtils.updateAppData(input, "setCompanyAuto");
        }
    }

    /**
     * This is the method to call for finding the day and months of scheduling.
     *
     * @param businessDay
     * @param holiday
     * @param form
     * @return
     */
    public static Calendar findScheduleDate(int businessDay, List<Object> holiday, SimpleDateFormat form, int checkMonth) {
        Calendar cal = null;
        if (businessDay > 0) {
            cal = Calendar.getInstance();
            cal.set(Calendar.DATE, 1);
            cal = findNextScheduleDate(cal, businessDay, holiday, form, 0, true, 0, checkMonth);
        }
        return cal;
    }

    /**
     * This is the method to calculate the day when the scheduler is to be done
     * in every month based on business day input.
     *
     * @param cal
     * @param businessDay
     * @param holiday
     * @param form
     * @param noOfDay
     * @return
     */
    public static Calendar findNextScheduleDate(Calendar cal, int businessDay, List<Object> holiday, SimpleDateFormat form, int noOfDay, boolean start, int monthChange, int checkMonth) {
        if (noOfDay == businessDay && cal.getTime().before(Calendar.getInstance().getTime())) {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
            cal.set(Calendar.DATE, 1);
            noOfDay = 0;
            monthChange++;
        }
        if (monthChange < checkMonth) {
            if (noOfDay < businessDay) {
                int day = cal.get(Calendar.DATE);
                boolean isHoliday = holiday.contains(day);
                if (!isHoliday) {
                    noOfDay++;
                }
                int oldMonth = cal.get(Calendar.MONTH);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
                int newMonth = cal.get(Calendar.MONTH);
                if (oldMonth != newMonth) {
                    cal.set(Calendar.DATE, 1);
                    noOfDay = 0;
                    monthChange++;
                }
                cal = findNextScheduleDate(cal, businessDay, holiday, form, noOfDay, false, monthChange, checkMonth);
            } else if (!start) {
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
            }
        } else {
            cal = null;
        }
        return cal;
    }

    public static List<Object> holidaysForCurrentMonth(Integer masterSID, int month) {
        List input = new ArrayList();
        input.add(masterSID);
        input.add(month);
        List<Object> dbData = QueryUtils.getAppData(input, "getHolidaysForMonth", null);
        List<Object> list = new ArrayList<>();
        for (Object sqldate : dbData) {
            list.add(((java.sql.Timestamp) sqldate).getDate());
        }
        return list;
    }

    /**
     * This is the method to delete the Data from Company FC Auto when the user
     * swaps the Mode from Automatic to Manual in edit mode
     *
     * @param companyMasterSid
     */
    public void swapAutoToManualUpdate(int companyMasterSid) {
        List list = new ArrayList<>();
        list.add(companyMasterSid);
        QueryUtils.updateAppData(list, "swapAutoToManualUpdate");
    }

    /**
     * This is the method to retrive DDLb values on Edit from Database
     *
     * @return
     */
    public List<FinancialCloseDTO> getDdlbValuesOnEdit(final int companyMasterSid) throws Exception {
        List list = new ArrayList();
        List<FinancialCloseDTO> resultList = new ArrayList<>();
        try (Connection connection = SysDataSourceConnection.getConnection()) {
            list.add(connection.getCatalog());
            list.add(companyMasterSid);
            resultList = getCustomizedFinancialClose(QueryUtils.getAppData(list, "getDdlbOnEdit", null));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    /**
     * Loading calendar DDLB from Calendar Configuration
     *
     * @param ddlb
     */
    public void loadCalendarDDLB(final ComboBox ddlb) {
        List<Object[]> calendarList = QueryUtils.executeSelectQuery(SQLUtil.getQuery("getCalendarDDLB"));
        ddlb.addItem(ConstantsUtils.SELECT_ONE);
        for (Object[] objects : calendarList) {
            ddlb.addItem(objects[0]);
            ddlb.setItemCaption(objects[0], String.valueOf(objects[1]));
        }
        ddlb.setNullSelectionAllowed(true);
        ddlb.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
    }

    /**
     *
     * @param busineesDay
     * @param calendarsid
     * @return
     */
    public Set checkIsthereBusinessdayForMonths(Integer busineesDay, Integer calendarsid) {
        List input = new ArrayList();
        input.add(calendarsid);
        Set awaySet = new HashSet();
        List<Object[]> hodidayList = QueryUtils.getAppData(input, "getHolidaysMonthWise", null);
        Calendar cal = Calendar.getInstance();
        for (Object[] objects : hodidayList) {
            cal.set(Calendar.MONTH, (Integer) objects[0]);
            if (cal.getActualMaximum(Calendar.DAY_OF_MONTH) >= busineesDay + (Integer) objects[1]) {
            } else {
                awaySet.add((Integer) objects[0]);
            }
        }
        return awaySet;
    }

    /**
     *
     * @param companyMasterSid
     * @return
     * @throws Exception
     */
    public List<Object[]> getDdlbValues(final int companyMasterSid) {
        List list = new ArrayList();
        list.add(companyMasterSid);
        List<Object[]> resultList = QueryUtils.getAppData(list, "getDdlbValues", null);
        return resultList;
    }
}
