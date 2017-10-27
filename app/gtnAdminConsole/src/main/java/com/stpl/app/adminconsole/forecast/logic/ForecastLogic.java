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
import com.stpl.ifs.ui.util.NumericConstants;
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
import com.stpl.app.adminconsole.util.SysDataSourceConnection;
import java.sql.Connection;


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
                        forecastConfig.setFromDate(convertToPeriod(CommonUtils.HISTORY, forecastDTO.getHistoricalInterval().getDescription(), Integer.valueOf(forecastDTO.getHistoricalValue())));
                        forecastConfig.setProjHistFreq(forecastDTO.getHistoricalIntervalFrequency() == null ? 0 : forecastDTO.getHistoricalIntervalFrequency().getId());
                        forecastConfig.setProjHistValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getHistoricalIntervalValue()) ? forecastDTO.getHistoricalIntervalValue() : "0"));
                        if (forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED)) {
                            forecastConfig.setToDate(convertToPeriod("Projection", forecastDTO.getFutureInterval().getDescription(), Integer.valueOf(forecastDTO.getFutureValue())));
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                            final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                            cal.setTime(gtsDate);
                            cal.set(Calendar.MONTH, NumericConstants.ELEVEN);
                            cal.set(Calendar.DAY_OF_MONTH, NumericConstants.THIRTY_ONE);
                            Date toDate = cal.getTime();
                            forecastConfig.setToDate(gtsDate);
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
                            cal.set(Calendar.MONTH, NumericConstants.ELEVEN);
                            cal.set(Calendar.DAY_OF_MONTH, NumericConstants.THIRTY_ONE);
                            Date toDate = cal.getTime();
                            forecastConfig.setToDate(gtsDate);
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
                    forecastConfig.setHistValue(Integer.valueOf(forecastDTO.getHistoricalValue()));
                    forecastConfig.setProjFreq(forecastDTO.getFutureInterval() == null ? 0 : forecastDTO.getFutureInterval().getId());
                    forecastConfig.setProjValue(Integer.valueOf(forecastDTO.getFutureValue().equals(ConstantsUtils.EMPTY) ? "0" : forecastDTO.getFutureValue()));
                    forecastConfig.setFromDate(convertToPeriod(CommonUtils.HISTORY, forecastDTO.getHistoricalInterval().getDescription(), Integer.valueOf(forecastDTO.getHistoricalValue())));
                    forecastConfig.setProjHistFreq(forecastDTO.getHistoricalIntervalFrequency() == null ? 0 : forecastDTO.getHistoricalIntervalFrequency().getId());
                    forecastConfig.setProjHistValue(Integer.valueOf(StringUtils.isNotBlank(forecastDTO.getHistoricalIntervalValue()) ? forecastDTO.getHistoricalIntervalValue() : "0"));
                    if (forecastDTO.getProcessType().equalsIgnoreCase(ConstantsUtils.DEFINED)) {
                        forecastConfig.setToDate(convertToPeriod("Projection", forecastDTO.getFutureInterval().getDescription(), Integer.valueOf(forecastDTO.getFutureValue())));
                    } else {
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
                        final Date gtsDate = new CommonUtils().getCurrentGTSToDate(ConstantsUtils.EX_FACTORY_SALES);
                        cal.setTime(gtsDate);
                        cal.set(Calendar.MONTH, NumericConstants.ELEVEN);
                        cal.set(Calendar.DAY_OF_MONTH, NumericConstants.THIRTY_ONE);
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
            LOGGER.debug(e);
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
        final List<ForecastDTO> forecastList = new ArrayList<>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        try {
            final Map<String, String> userInfoMap = CommonUtil.getCreatedByUser();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] obj = (Object[]) resultList.get(i);
                final ForecastDTO forecastResults = new ForecastDTO();
                forecastResults.setBusinessProcess(obj[0] != null ? idHelperDTOMap.get(obj[0]) : new HelperDTO(0, ConstantsUtils.SELECT_ONE));
                forecastResults.setProcessType(String.valueOf(obj[1]));
                forecastResults.setMode(String.valueOf(obj[NumericConstants.TWO]));
                forecastResults.setFromDate(formatDate(convertNullToEmpty(obj[NumericConstants.THREE])));
                forecastResults.setToDate(formatDate(convertNullToEmpty(obj[NumericConstants.FOUR])));
                forecastResults.setFromDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.THREE]))));
                forecastResults.setToDateSearch(parsetDate(convertNullToEmpty(String.valueOf(obj[NumericConstants.FOUR]))));
                forecastResults.setVersionNo(Integer.valueOf(String.valueOf(obj[NumericConstants.NINE])));
                forecastResults.setHistoricalInterval((obj[NumericConstants.FIVE] != null && !obj[NumericConstants.FIVE].equals(0)) ? idHelperDTOMap.get(obj[NumericConstants.FIVE]) : new HelperDTO(0, StringUtils.EMPTY));

                forecastResults.setHistoricalValue(String.valueOf(obj[NumericConstants.SIX]));

                forecastResults.setFutureInterval((obj[NumericConstants.SEVEN] != null && !obj[NumericConstants.SEVEN].equals(0)) ? idHelperDTOMap.get(obj[NumericConstants.SEVEN]) : new HelperDTO(0, StringUtils.EMPTY));

                forecastResults.setFutureValue(String.valueOf(obj[NumericConstants.EIGHT]));
                forecastResults.setFromPeriod((Date) obj[NumericConstants.TEN] != null ? (Date) obj[NumericConstants.TEN] : null);
                forecastResults.setToPeriod((Date) obj[NumericConstants.ELEVEN] != null ? (Date) obj[NumericConstants.ELEVEN] : null);
                forecastResults.setActiveFlag(String.valueOf(obj[NumericConstants.THIRTEEN]));
                final String createdBy = userInfoMap.get(String.valueOf(obj[NumericConstants.TWELVE]));
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
        if (flag.equals(CommonUtils.HISTORY)) {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                calculateFromDate(cal, interval, 1, 0, frequency);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                calculateFromDate(cal, interval, NumericConstants.TWO, 0, frequency);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                calculateFromDate(cal, interval, NumericConstants.FOUR, 1, frequency);
            } else {
                calculateFromDate(cal, interval, NumericConstants.TWELVE, 1, frequency);
            }
        } else {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                calculateToDate(cal, interval, 1, 0);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                calculateToDate(cal, interval, NumericConstants.TWO, 0);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                calculateToDate(cal, interval, NumericConstants.FOUR, 0);
            } else {
                calculateToDate(cal, interval, NumericConstants.TWELVE, 1);
            }
        }
        return getDateReturnsDate(cal);
    }

    /**
     * Convert into month
     */
    public Calendar convertPeriod(final String flag, final String frequency, final int interval) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (flag.equalsIgnoreCase(CommonUtils.HISTORY)) {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * NumericConstants.TWELVE;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * NumericConstants.SIX;
                cal.add(Calendar.MONTH, -month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * NumericConstants.THREE;
                cal.add(Calendar.MONTH, -month);
            } else {
                cal.add(Calendar.MONTH, -interval);
            }
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
            cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
            cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
            cal.set(Calendar.HOUR, cal.getActualMinimum(Calendar.HOUR));
        } else {
            if (frequency.equalsIgnoreCase(ConstantsUtils.ANNUAL)) {
                int month = interval * NumericConstants.TWELVE;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.SEMI_ANNUAL)) {
                int month = interval * NumericConstants.SIX;
                cal.add(Calendar.MONTH, month);
            } else if (frequency.equalsIgnoreCase(ConstantsUtils.QUARTER)) {
                int month = interval * NumericConstants.THREE;
                cal.add(Calendar.MONTH, month);
            } else {
                cal.add(Calendar.MONTH, interval);
            }
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
            cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
            cal.set(Calendar.HOUR, cal.getActualMaximum(Calendar.HOUR));
        }

        return cal;
    }

    public static String getDate(Calendar cal) {
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("M-dd-yy");
        String dateString = format1.format(date);
        return dateString;
    }

    public static Date getDateReturnsDate(Calendar cal) {
        Date date = cal.getTime();
        LOGGER.debug("Final To Period date---------------" + date);
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
            date = parseDate.parse(value);
        }

        return date;
    }

    /**
     * Get GTS file to date
     *
     * @return list of date
     */
    public List<Date> getGTSFileToDate() {
        List<Date> dates = new ArrayList<>();
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
                cal.add(Calendar.MONTH, histValue * NumericConstants.THREE);
            } else if ("SemiAnnual".equalsIgnoreCase(frequency)) {
                cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, histValue / NumericConstants.TWO);
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
    public String formatDate(String stringDate) throws java.text.ParseException {
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
        Connection connection = null; 
        try {			
            connection = SysDataSourceConnection.getConnection();
            query = query.replace("?", connection.getCatalog()); 
            String filterQuery = "";
            String finalQuery = "";
            HashMap<String, String> detailsColumnForFilter = new HashMap<>();
            detailsColumnForFilter.put("businessProcess", "BUSINESS_PROCESS_TYPE");
            detailsColumnForFilter.put("processType", "PROCESS_TYPE_1");
            detailsColumnForFilter.put("mode", "MODE");
            detailsColumnForFilter.put("fromDateSearch", "FROM_DATE");
            detailsColumnForFilter.put("toDateSearch", "TO_DATE");
            detailsColumnForFilter.put("historicalInterval", "HIST_FREQ");
            detailsColumnForFilter.put("historicalValue", "HIST_VALUE");
            detailsColumnForFilter.put("futureInterval", "PROJ_FREQ");
            detailsColumnForFilter.put("futureValue", "PROJ_VALUE");
            detailsColumnForFilter.put("versionNo", "VERSION_NO");
            detailsColumnForFilter.put("fromPeriod", "ACTIVE_START_DATE");
            detailsColumnForFilter.put("toPeriod", "ACTIVE_END_DATE");
            detailsColumnForFilter.put("createdBy", "USR.lastName");
            detailsColumnForFilter.put("activeFlag", "Active_Inactive");
            
            HashMap<String, String> detailsColumnForSort = new HashMap<>();
            detailsColumnForSort.put("businessProcess", "HT.DESCRIPTION");
            detailsColumnForSort.put("processType", "PROCESS_TYPE_1");
            detailsColumnForSort.put("mode", "MODE");
            detailsColumnForSort.put("fromDateSearch", "FROM_DATE");
            detailsColumnForSort.put("toDateSearch", "TO_DATE");
            detailsColumnForSort.put("historicalInterval", "HTF.DESCRIPTION");
            detailsColumnForSort.put("historicalValue", "HIST_VALUE");
            detailsColumnForSort.put("futureInterval", "HTP.DESCRIPTION");
            detailsColumnForSort.put("futureValue", "PROJ_VALUE");
            detailsColumnForSort.put("versionNo", "VERSION_NO");
            detailsColumnForSort.put("fromPeriod", "ACTIVE_START_DATE");
            detailsColumnForSort.put("toPeriod", "ACTIVE_END_DATE");
            detailsColumnForSort.put("createdBy", "USR.lastName");
            detailsColumnForSort.put("activeFlag", "Active_Inactive");


            if (filterSet != null) {
                filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(filterSet, detailsColumnForFilter).toString();
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
                        orderByColumn = detailsColumnForSort.get(columnName);

                        if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                            sortOrder = false;
                        } else {
                            sortOrder = true;
                        }
                    }
                }
                if (orderByColumn == null || StringUtils.EMPTY.equals(orderByColumn)) {
                    order = order + " ORDER BY HT.DESCRIPTION DESC ";
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
                countQuery = countQuery.replace("?", connection.getCatalog());

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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
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
            date = format.parse(tempDate);
        }

        return date;
    }

    private void calculateFromDate(Calendar cal, int interval, int periodOffset, int monthOffset, final String frequency) {
        int periodConstant = NumericConstants.TWELVE / periodOffset;
        int monthDifference = interval * periodConstant;
        int currentMonth = cal.get(Calendar.MONTH);
        int period = (currentMonth / periodConstant) + 1;
        int firstMonth = ((periodOffset == NumericConstants.TWELVE ? period : period - 1) * periodConstant) - monthOffset;
        if(ConstantsUtils.QUARTER.equals(frequency))
        {
        cal.set(Calendar.MONTH, firstMonth + monthOffset);
        }
        else
        {
           cal.set(Calendar.MONTH, firstMonth);  
        }
        cal.add(Calendar.MONTH, -monthDifference);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
    }

    private void calculateToDate(Calendar cal, int interval, int periodOffset, int monthOffset) {
        int periodConstant = NumericConstants.TWELVE / periodOffset;
        int monthDifference = interval * periodConstant;
        int currentMonth = cal.get(Calendar.MONTH);
        int period = (currentMonth / periodConstant) + 1;
        int lastMonth = ((period * periodConstant) - 1) - monthOffset;
        cal.set(Calendar.MONTH, lastMonth);
        cal.add(Calendar.MONTH, +monthDifference);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
    }
}
