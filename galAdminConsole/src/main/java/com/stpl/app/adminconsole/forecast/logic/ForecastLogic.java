/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.forecast.logic;

import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.ForecastLogicDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.dao.impl.ForecastLogicDAOImpl;
import com.stpl.app.adminconsole.forecast.dto.ForecastDTO;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.ForecastingMaster;
import com.stpl.app.service.ForecastingMasterLocalServiceUtil;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.service.ForecastConfigLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Container;
import com.vaadin.server.VaadinSession;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 * The Class ForecastLogic.
 *
 * @author mohamed
 */
public class ForecastLogic {

    private static ForecastLogicDAO forecastLogicDAO = new ForecastLogicDAOImpl();
    private static CommonDAO commonDAO = new CommonDAOImpl();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ForecastLogic.class);

    /**
     * save the entered forecasting year for forecasting projection.
     *
     * @param forecastDTO the forecast dto
     * @return
     */
    public String saveForecastYears(final ForecastDTO forecastDTO) {
        try {
            final int userId = Integer.valueOf((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            ForecastConfig forecastConfig = null;
            DateFormat df = new SimpleDateFormat("M-dd-yy");
            final DynamicQuery query = DynamicQueryFactoryUtil.forClass(ForecastConfig.class);
            query.add(RestrictionsFactoryUtil.eq("businessProcessType", forecastDTO.getBusinessProcess().getId()));
            query.addOrder(OrderFactoryUtil.asc(ConstantsUtils.VERSION_NO));

            List<ForecastConfig> versionList = forecastLogicDAO.getForecastVersion(query);
            if (!versionList.isEmpty()) {
                commonDAO.executeBulkUpdateQuery(updateActiveEndDate(String.valueOf(forecastDTO.getBusinessProcess().getId())), null, null);
                for (ForecastConfig forecastList : versionList) {
                    forecastConfig = ForecastConfigLocalServiceUtil.createForecastConfig(0);
                    forecastConfig.setVersionNo(forecastList.getVersionNo() + 1);
                    forecastConfig.setBusinessProcessType(forecastDTO.getBusinessProcess().getId());
                    forecastConfig.setProcessType(!forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED));
                    forecastConfig.setActiveStartDate(new Date());
                    if (forecastDTO.getMode().equalsIgnoreCase(ConstantsUtils.INTERVAL)) {
                        forecastConfig.setProcessMode(true);
                        forecastConfig.setHistFreq(forecastDTO.getHistoricalInterval().getId());
                        forecastConfig.setHistValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getHistoricalValue()) ? forecastDTO.getHistoricalValue() : "0"));
                        forecastConfig.setProjFreq(forecastDTO.getFutureInterval() == null ? 0 : forecastDTO.getFutureInterval().getId());
                        forecastConfig.setProjValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getFutureValue()) ? forecastDTO.getFutureValue() : "0"));
                        forecastConfig.setFromDate(convertToPeriod("History", forecastDTO.getHistoricalInterval().getDescription(), Integer.valueOf((forecastDTO.getHistoricalValue()))));
                        forecastConfig.setProjHistFreq(forecastDTO.getHistoricalIntervalFrequency() == null ? 0 : forecastDTO.getHistoricalIntervalFrequency().getId());
                        forecastConfig.setProjHistValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getHistoricalIntervalValue()) ? forecastDTO.getHistoricalIntervalValue() : "0"));
                        if (forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED)) {
                            forecastConfig.setToDate(convertToPeriod("Projection", forecastDTO.getFutureInterval().getDescription(), Integer.valueOf((forecastDTO.getFutureValue()))));
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                            final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                            cal.setTime(gtsDate);
                            cal.set(Calendar.MONTH, 11);
                            cal.set(Calendar.DAY_OF_MONTH, 31);
                            Date toDate = cal.getTime();
                            forecastConfig.setToDate(toDate);
                        }

                    } else {
                        forecastConfig.setProcessMode(false);
                        if (forecastDTO.getHistoricalPeriod() != null) {
                            forecastConfig.setFromDate(convertStringToDate(forecastDTO.getHistoricalPeriod()));
                        }
                        if (forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED) && forecastDTO.getFuturePeriod() != null) {
                            forecastConfig.setToDate(convertStringToDate(forecastDTO.getFuturePeriod()));
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeZone(TimeZone.getTimeZone("GMT"));

                            final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                            cal.setTime(gtsDate);
                            cal.set(Calendar.MONTH, 11);
                            cal.set(Calendar.DAY_OF_MONTH, 31);
                            Date toDate = cal.getTime();
                            forecastConfig.setToDate(toDate);
                        }
                    }
                    forecastConfig.setCreatedDate(new Date());
                    forecastConfig.setModifiedDate(new Date());

                    forecastConfig.setCreatedBy(userId);
                    forecastConfig.setModifiedBy(userId);
                }
                forecastLogicDAO.saveForecastYears(forecastConfig);

            } else {
                forecastConfig = ForecastConfigLocalServiceUtil.createForecastConfig(0);
                forecastConfig.setVersionNo(1);
                forecastConfig.setBusinessProcessType(forecastDTO.getBusinessProcess().getId());
                forecastConfig.setProcessType(!forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED));
                forecastConfig.setActiveStartDate(new Date());
                if (forecastDTO.getMode().equalsIgnoreCase(ConstantsUtils.INTERVAL)) {
                    forecastConfig.setProcessMode(true);
                    forecastConfig.setHistFreq(forecastDTO.getHistoricalInterval().getId());
                    forecastConfig.setHistValue(Integer.valueOf((forecastDTO.getHistoricalValue())));
                    forecastConfig.setProjFreq(forecastDTO.getFutureInterval().getId());
                    forecastConfig.setProjValue(Integer.valueOf(forecastDTO.getFutureValue().equals(ConstantsUtils.EMPTY) ? "0" : forecastDTO.getFutureValue()));
                    forecastConfig.setFromDate(convertToPeriod("History", forecastDTO.getHistoricalInterval().getDescription(), Integer.valueOf((forecastDTO.getHistoricalValue()))));
                    forecastConfig.setProjHistFreq(forecastDTO.getHistoricalIntervalFrequency() == null ? 0 : forecastDTO.getHistoricalIntervalFrequency().getId());
                    forecastConfig.setProjHistValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getHistoricalIntervalValue()) ? forecastDTO.getHistoricalIntervalValue() : "0"));
                    if (forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED)) {
                        forecastConfig.setToDate(convertToPeriod("Projection", forecastDTO.getFutureInterval().getDescription(), Integer.valueOf((forecastDTO.getFutureValue()))));
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                        final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                        cal.setTime(gtsDate);
                        cal.set(Calendar.MONTH, 11);
                        cal.set(Calendar.DAY_OF_MONTH, 31);
                        Date toDate = cal.getTime();
                        forecastConfig.setToDate(toDate);
                    }
                } else {
                    forecastConfig.setProcessMode(false);
                    if (forecastDTO.getHistoricalPeriod() != null) {
                        forecastConfig.setFromDate(convertStringToDate(forecastDTO.getHistoricalPeriod()));
                    }
                    if (forecastDTO.getFuturePeriod() != null) {
                        forecastConfig.setToDate(convertStringToDate(forecastDTO.getFuturePeriod()));
                    }
                }
                forecastConfig.setCreatedDate(new Date());
                forecastConfig.setModifiedDate(new Date());
                forecastConfig.setCreatedBy(userId);
                forecastConfig.setModifiedBy(userId);
                forecastLogicDAO.saveForecastYears(forecastConfig);
            }

            return ConstantsUtils.SUCCESS;
        } catch (Exception e) {

            LOGGER.info(e);
        }
        return "fail";
    }

    /**
     * Gets the customized results.
     *
     * @param resultList the result list
     * @return the customized results
     */
    private List<ForecastDTO> getCustomizedresults(final List resultList) {
        final List<ForecastDTO> forecastList = new ArrayList<ForecastDTO>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        try {
            final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[]) resultList.get(i);
                final ForecastDTO forecastResults = new ForecastDTO();
                forecastResults.setBusinessProcess(obj[0] != null ? idHelperDTOMap.get(obj[0]) : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                forecastResults.setProcessType(String.valueOf(obj[1]));
                forecastResults.setMode(String.valueOf(obj[2]));
                forecastResults.setFromDate(formatDate(convertNullToEmpty(obj[3])));
                forecastResults.setToDate(formatDate(convertNullToEmpty(obj[4])));
                forecastResults.setFromDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[3]))));
                forecastResults.setToDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[4]))));
                forecastResults.setVersionNo(Integer.valueOf(String.valueOf(obj[9])));
                forecastResults.setHistoricalInterval(obj[5] != null ? idHelperDTOMap.get(obj[5]) : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                forecastResults.setHistoricalValue(String.valueOf(obj[6]));

                forecastResults.setFutureInterval(obj[7] != null ? idHelperDTOMap.get(obj[7]) : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                forecastResults.setFutureValue(String.valueOf(obj[8]));
                forecastResults.setFromPeriod((Date) obj[10] != null ? (Date) obj[10] : null);
                forecastResults.setToPeriod((Date) obj[11] != null ? (Date) obj[11] : null);
                forecastResults.setActiveFlag(String.valueOf(obj[13]));
                final String createdBy = userInfoMap.get(String.valueOf(obj[12]));
                if (StringUtils.isNotBlank(createdBy) && !createdBy.equals("null")) {
                    forecastResults.setCreatedBy(createdBy);
                }

                forecastList.add(forecastResults);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return forecastList;
    }

    /**
     * Convert into month
     */
    public Date convertToPeriod(final String flag, final String frequency, final int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (flag.equalsIgnoreCase("History")) {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * 12;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 12;
                if (rem == 0) {
                    rem = 11;
                } else {
                    rem = rem - 1;
                }
                month = month + rem;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * 6;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 6;
                if (rem == 0) {
                    rem = 5;
                } else {
                    rem = rem - 1;
                }
                month = month + rem;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * 3;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 3;
                if (rem == 0) {
                    rem = 2;
                } else {
                    rem = rem - 1;
                }
                month = month + rem;
                cal.add(Calendar.MONTH, -month);

            } else {
                cal.add(Calendar.MONTH, -interval);
            }
            cal1.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            cal1.set(Calendar.MONTH, 00);
            cal1.set(Calendar.DAY_OF_MONTH, 01);
        } else {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * 12;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 12;
                int requirdMonth = 0;
                if (rem != 0) {
                    requirdMonth = 12 - rem;
                }
                month = month + requirdMonth;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * 6;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 6;
                int requirdMonth = 0;
                if (rem != 0) {
                    requirdMonth = 6 - rem;
                }
                month = month + requirdMonth;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * 3;
                int month1 = cal.get(Calendar.MONTH);
                month1 = month1 + 1;
                int rem = month1 % 3;
                int requirdMonth = 0;
                if (rem != 0) {
                    requirdMonth = 3 - rem;
                }
                month = month + requirdMonth;
                cal.add(Calendar.MONTH, month);
            } else {
                cal.add(Calendar.MONTH, interval);
            }
            cal1.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            cal1.set(Calendar.MONTH, 11);
            cal1.set(Calendar.DAY_OF_MONTH, 31);
        }

        return getDateReturnsDate(cal1);
    }

    /**
     * Convert into month
     */
    public Calendar convertPeriod(final String flag, final String frequency, final int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (flag.equalsIgnoreCase("History")) {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * 12;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * 6;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * 3;
                cal.add(Calendar.MONTH, -month);
            } else {
                cal.add(Calendar.MONTH, -interval);
            }
            cal1.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            cal1.set(Calendar.MONTH, 00);
            cal1.set(Calendar.DAY_OF_MONTH, 01);
        } else {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * 12;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * 6;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * 3;
                cal.add(Calendar.MONTH, month);
            } else {
                cal.add(Calendar.MONTH, interval);
            }
            cal1.set(Calendar.YEAR, cal.get(Calendar.YEAR));
            cal1.set(Calendar.MONTH, 11);
            cal1.set(Calendar.DAY_OF_MONTH, 31);
        }

        return cal1;
    }

    public static String getDate(Calendar cal) {
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("M-dd-yy");
        String dateString = format1.format(date);
        return dateString;
    }

    public static Date getDateReturnsDate(Calendar cal) {
        Date date = cal.getTime();
        LOGGER.info("Final To Period date---------------" + date);
        return date;
    }

    /**
     * Logic for String to Date
     *
     * @param aMask
     * @param strDate
     * @return Date
     * @throws ParseException
     * @throws java.text.ParseException
     */
    private static Date convertStringToDate(String value) throws java.text.ParseException {
        Date date = null;
        SimpleDateFormat parseDate = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            date = (parseDate.parse(value));
        }

        return date;
    }

    /**
     * Get GTS file to date
     *
     * @return list of date
     */
    public List<Date> getGTSFileToDate() {
        List<Date> dates = new ArrayList<Date>();
        final DynamicQuery query = DynamicQueryFactoryUtil.forClass(ForecastingMaster.class);
        final ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property("forecastDate"));
        query.setProjection(ProjectionFactoryUtil.distinct(projList));
        query.addOrder(OrderFactoryUtil.desc("forecastDate"));
        try {
            dates = ForecastingMasterLocalServiceUtil.dynamicQuery(query);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return dates;
    }

    /**
     * calculate date based on given interval input future
     *
     * @param frequency
     * @param histValue
     * @return Date
     */
    public Date calculateProjection(final String frequency, final int histValue) {
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
                cal.add(Calendar.MONTH, (histValue * 3));
            } else if ("SemiAnnual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, (histValue / 2));
            } else if ("Annual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, histValue);
            }
            obtainedDate = getObtainedDate(cal);
            date = format.parse(obtainedDate);
            cal1 = Calendar.getInstance();
            cal1.setTime(date);

        } catch (java.text.ParseException ex) {
            LOGGER.error(ex);
        }
        return date;
    }

    /**
     * Return date from calendar
     *
     * @param cal
     * @return date
     */
    public static String getObtainedDate(Calendar cal) {
        return (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
    }

    /**
     * Change string date format
     *
     * @param stringDate
     * @return
     * @throws SystemException
     * @throws Exception
     */
    public String formatDate(String stringDate) throws SystemException, Exception {
        Date date = null;
        SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
        Format formatter = new SimpleDateFormat("MM/dd/yyyy");
        String detinationDate = "";
        if (stringDate != null && !StringUtils.EMPTY.equals(stringDate.trim()) && !"null".equals(stringDate)) {
            date = sdfSource.parse(stringDate);
        }
        if (date != null) {
            detinationDate = formatter.format(date);
        }
        return detinationDate;
    }

    /**
     * Update end date
     */
    public String updateActiveEndDate(String businessType) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String activeEndDate = df.format(date);
        Map<String, String> input = new HashMap<>();
        input.put("?activeEndDate", activeEndDate);
        input.put("?businessType", businessType);
        String sqlQuery = CommonUtil.replacedQuery(input, "updateForecastConfigEndDate");
        return sqlQuery;
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public Object searchForecast(final int startIndex, final int endIndex, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, boolean isCount) {
        String query = CustomSQLUtil.get("getForecastConfigurationSearchResults");
        Object forecastObj = null;
        try {
            String filterQuery = "";
            String finalQuery = "";
            HashMap<String, String> detailsColumn = new HashMap<String, String>();
            detailsColumn.put("businessProcess", "BUSINESS_PROCESS_TYPE");
            detailsColumn.put("processType", "PROCESS_TYPE_1");
            detailsColumn.put("mode", "MODE");
            detailsColumn.put("fromDateSearch", "FROM_DATE");
            detailsColumn.put("toDateSearch", "TO_DATE");
            detailsColumn.put("historicalInterval", "HIST_FREQ");
            detailsColumn.put("historicalValue", "HIST_VALUE");
            detailsColumn.put("futureInterval", "PROJ_FREQ");
            detailsColumn.put("futureValue", "PROJ_VALUE");
            detailsColumn.put("versionNo", "VERSION_NO");
            detailsColumn.put("fromPeriod", "ACTIVE_START_DATE");
            detailsColumn.put("toPeriod", "ACTIVE_END_DATE");
            detailsColumn.put("createdBy", "CREATED_BY");
            detailsColumn.put("activeFlag", "Active_Inactive");

            if (filterSet != null) {
                filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, detailsColumn).toString();
            }

            String order = "";
            if (!isCount) {

                boolean sortOrder = false;
                String columnName = null;
                String orderByColumn = null;
                if (sortByColumns != null) {
                    for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                        final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                        columnName = sortByColumn.getName();
                        orderByColumn = detailsColumn.get(columnName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrder = false;
                        } else {
                            sortOrder = true;
                        }
                    }
                }
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    order = order + " ORDER BY VERSION_NO DESC ";
                } else {
                    order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
                }

                order = order + " " + "OFFSET ";
                order = order + startIndex;
                order = order + " ROWS FETCH NEXT " + endIndex;
                order = order + " ROWS ONLY;";
                finalQuery = query + filterQuery + order;
                List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
                if (resultList != null && resultList.size() > 0) {
                    forecastObj = getCustomizedresults(resultList);
                }
            } else {
                String countQuery = CustomSQLUtil.get("getForecastConfigurationSearchCount");
                finalQuery = countQuery + filterQuery;
                List resultList = ForecastingMasterLocalServiceUtil.getFileSearchResults(finalQuery);
                if (resultList != null && resultList.size() > 0) {
                    forecastObj = resultList.get(0);
                } else {
                    forecastObj = 0;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return forecastObj;
    }

    public static String convertNullToEmpty(Object value) {
        String returnValue;
        if (value == null || "null".equals(String.valueOf(value))) {
            returnValue = StringUtils.EMPTY;
        } else {
            returnValue = String.valueOf(value);
        }
        return returnValue;
    }

    private static Date parsetDate(String value) throws java.text.ParseException {
        Date date = null;
        String tempDate;
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        if (value != null && !StringUtils.EMPTY.equals(value) && !"null".equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
        }

        return date;
    }
}
