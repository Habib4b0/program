/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.logic;

import com.stpl.app.model.AccrualDetails;
import com.stpl.app.model.AccrualMaster;
import com.stpl.app.model.ActualsMaster;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.CpiIndexMaster;
import com.stpl.app.model.GlBalanceMaster;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.IvldReturns;
import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.service.ActualsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import static com.stpl.app.transactional.common.logic.SearchLogic.getQuery;
import com.stpl.app.util.ConstantUtil;
import com.stpl.portal.kernel.dao.orm.Criterion;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinSession;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;
import org.vaadin.addons.lazycontainer.BeanSearchCriteria;

/**
 *
 * @author sooriya.lakshmanan
 */
public class SearchLogic {

    private static final Logger LOGGER = Logger.getLogger(SearchLogic.class.getName());
    CommonLogic commonLogic = new CommonLogic();
    String listName;
     Date invalidToDate = null;
     Date invalidFromDate = null;

    /**
     * Gets the actuals total count.
     *
     * @return the actuals total count
     */
    public int getActualsTotalCount(String tableName) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering getActualsTotalCount");
           Class cls;
           Method method = null ;
        if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)
                || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName) || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName)
                || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)  || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        } else {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        }
        if(!("IvldFormulaDetails".equals(tableName) || "IvldForecastSales".equals(tableName) || "IvldReturns".equals(tableName))){
         method = cls.getMethod("get" + tableName + "sCount");
        }else if("IvldFormulaDetails".equals(tableName) || "IvldForecastSales".equals(tableName) || "IvldReturns".equals(tableName)){
           method = cls.getMethod("get" + tableName + "esCount");  
        }
        Object ob = method.invoke(cls.newInstance());
        final int temp = Integer.valueOf(ob.toString());
        LOGGER.info(" Ends getActualsTotalCount with the  temp value    ::::  " + temp);
        return temp;
        }
        
    /**
     * Gets the dynamic query search.
     *
     * @param searchForm the search form
     * @return the dynamic query search
     */
    public int getDynamicQuerySearch(final Map<Object, Object> searchValues, final Set<Filter> search, final String tableName) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering getDynamicQuerySearch with");
        int count = 0;
        try {

            String tempValue = StringUtils.EMPTY;
            DynamicQuery dynamicQuery;
            if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW) || tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)
                   || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));
            } else {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
            }
            if (searchValues.containsKey(ConstantUtil.LEVEL)) {
                searchValues.remove(ConstantUtil.LEVEL);
            }
           for(int i=0;i<searchValues.size();i++){
                if(searchValues.containsKey("invalidFromDate_Date")){
                   invalidFromDate = (Date) searchValues.get("invalidFromDate_Date");  
                }else{
                    invalidFromDate=null;
                }
                if(searchValues.containsKey("invalidToDate_Date")){
                     invalidToDate = (Date) searchValues.get("invalidToDate_Date"); 
                }else{
                    invalidToDate=null;
                }
            }
            for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {

                if (entry.getKey().toString().contains("_Date")) {
                    if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY)) || "invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                        if (invalidToDate != null && invalidFromDate != null) {
                            final Date invalidDate = new Date();

                            invalidDate.setTime(((Date) invalidToDate).getTime());
                            invalidDate.setHours(23);
                            invalidDate.setMinutes(59);
                            invalidDate.setSeconds(59);
                            dynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
                        } else if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                            dynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
                        } else if ("invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {

                            dynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get("invalidFromDate_Date")));
                        }
                    } else {
                        final Date forecastsDate = new Date();
                        forecastsDate.setTime(((Date) entry.getValue()).getTime());
                        forecastsDate.setHours(23);
                        forecastsDate.setMinutes(59);
                        forecastsDate.setSeconds(59);
                        dynamicQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                    }
                } else if (entry.getKey().toString().contains("_boolean")) {
                   
                    boolean flag = false;
                    if (1 == Integer.valueOf(String.valueOf(entry.getValue()))) {

                        flag = true;
                    } else if (0 == Integer.valueOf(String.valueOf(entry.getValue()))) {

                        flag = false;
                    }
                    if (ConstantUtil.GL_BALANCE_MASTER.equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag)));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag));
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else if (ConstantUtil.ACCURAL_MASTER.equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag)));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag));
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }

                    } else if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, ConstantUtil.KEY_Y));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, ConstantUtil.KEY_N);
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else {

                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    }
                } else if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName.trim())
                        && (entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_TYPE)
                        || entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_PROGRAM_TYPE)
                        || entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_CATEGORY))) {
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_CATEGORY)) {
                        listName = ConstantUtil.RS_CATEGORY;
                    }
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_TYPE)) {
                        listName = ConstantUtil.RS_TYPE;
                    }
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_PROGRAM_TYPE)) {
                        listName = ConstantUtil.REBATE_PROGRAM_TYPE;
                    }
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
                } else if ("CpiIndexMaster".equals(tableName.trim())
                        && (entry.getKey().toString().trim().equals(ConstantUtil.STATUS))) {
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.STATUS)) {
                        listName = ConstantUtil.STATUS_DUP;
                    }
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.STATUS, tmpValue));
                }  else {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if ("%".equals(tempValue)) {
                    Criterion c1 = RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue);
                    Criterion c2 = RestrictionsFactoryUtil.isNull(entry.getKey().toString());
                    dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                } else {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue));
                }
                }
                }

            if (String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)).equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
                dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RE_PROCESSED_FLAG, ConstantUtil.KEY_N));
            }

            dynamicQuery = getFilterQuery(search, dynamicQuery, tableName);

            LOGGER.info(" Ends getDynamicQuerySearch with dynamicQuery");

            Class cls;
            if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)
                    || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName) || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName)
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) ) {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            } else {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            }
            Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC_COUNT, DynamicQuery.class);
            Object ob = method.invoke(cls.newInstance(), dynamicQuery);
            count = Integer.valueOf(ob.toString());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.info(" Ends hereee...getDynamicQuerySearch..");
        return count;

    }

    public int getDynamicQuerySearch(final Map<Object, Object> searchValues, final String className,
            final String tableName, final Set<Container.Filter> filterSet) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering getDynamicQuerySearch with" + className);
        int count = 0;
        try {

            String tempValue = StringUtils.EMPTY;
            DynamicQuery dynamicQuery;
//        if((tableName.equals("VwCustomerGtsForecast")) || (tableName.equals("IvldCustomerGtsForecast")) || tableName.equals("VwAdjustDemandForecastAct") || tableName.equals("VwIvldAdjDemandForeActual") ){
//            dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));     
//        }else{
            dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(className));
            //}
            if (searchValues.containsKey("level")) {
                searchValues.remove("level");
            }
            for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {

                if (entry.getKey().toString().contains("_Date")) {

                    final Date forecastsDate = new Date();
                    forecastsDate.setTime(((Date) entry.getValue()).getTime());
                    forecastsDate.setHours(23);
                    forecastsDate.setMinutes(59);
                    forecastsDate.setSeconds(59);
                    dynamicQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                } else if (entry.getKey().toString().contains("_boolean")) {

                    boolean flag = false;
                    if (1 == Integer.valueOf(entry.getValue().toString())) {

                        flag = true;
                    } else if (0 == Integer.valueOf(entry.getValue().toString())) {

                        flag = false;
                    }
                    if ("GlBalanceMaster".equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag)));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag));
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else if ("AccrualMaster".equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag)));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag));
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }

                    } else if ("AccrualDetails".equals(tableName)) {
                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, "Y"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, "N");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else {

                        if (flag) {
                            dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    }
                } else {

                    tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue));
                }
            }

            if (String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)).equalsIgnoreCase("Invalidrecordcount")) {
                dynamicQuery.add(RestrictionsFactoryUtil.eq("reprocessedFlag", "N"));
            }

            dynamicQuery = getFilterQuery(filterSet, dynamicQuery, tableName);

            LOGGER.info(" Ends getDynamicQuerySearch with dynamicQuery");

            Class cls;
            if ((tableName.equals("VwCustomerGtsForecast")) || (tableName.equals("IvldCustomerGtsForecast"))
                    || tableName.equals("VwAdjustDemandForecastAct") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName)
                    || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)) {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            } else {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            }

            Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC_COUNT, DynamicQuery.class);
            Object ob = method.invoke(cls.newInstance(), dynamicQuery);
            count = Integer.valueOf(ob.toString());
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info(" Ends hereee...getDynamicQuerySearch..");
        return count;

    }

    private DynamicQuery getFilterQuery(final BeanSearchCriteria criteria, final DynamicQuery forecastQuery) {
        if (criteria != null && criteria.getFilters() != null) {
            for (Container.Filter filter : criteria.getFilters()) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    if (ConstantUtil.IS_ACTIVE.equals(stringFilter.getPropertyId())) {
                        if (ConstantUtil.YES.equalsIgnoreCase(stringFilter.getFilterString())) {
                            forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), "true"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, "false");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }

                    }
                    if (ConstantUtil.OUT_LINER.equals(stringFilter.getPropertyId())) {

                        if ("true".equals(stringFilter.getFilterString())) {
                            forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), "true"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.OUT_LINER, "false");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.OUT_LINER);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else if (ConstantUtil.POSTING_INDICATOR.equals(stringFilter.getPropertyId())) {
                        if (ConstantUtil.YES.equals(stringFilter.getFilterString())) {
                            forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), "true"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, "false");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else {
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), filterString));
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                    forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Date) {
                        Date filterString = (Date) stringFilter.getValue();
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                            forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), filterString));
                        }
                    }
                    if (stringFilter.getValue() instanceof Integer) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId())));
                            } else {
                                int value = (Integer) stringFilter.getValue();
                                forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            int value = (Integer) stringFilter.getValue();
                            if (value < 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            int value = (Integer) stringFilter.getValue();
                            if (value > 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                    }
                    if (stringFilter.getValue() instanceof Double) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Double) stringFilter.getValue()) == 0.0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                String value = String.valueOf(stringFilter.getValue());

                                if (value.contains(".")) {
                                    String[] tempValues = value.split("[.]");
                                    if (tempValues.length > 1 && tempValues[1].length() <= 3) {
                                        Double[] arrValue = getGreaterLesserVal(value);
                                        if (tempValues[1].length() == 3) {
                                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        } else {
                                            forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        }
                                        forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), arrValue[1]));
                                    } else {
                                        Double douValue = (Double) stringFilter.getValue();
                                        forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), douValue));
                                    }

                                } else {
                                    Double douValue = (Double) stringFilter.getValue();
                                    forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), douValue));
                                }
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            Double value = (Double) stringFilter.getValue();
                            if (value < 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            Double value = (Double) stringFilter.getValue();
                            if (value > 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = "";
                        if (filter1 instanceof Compare.Less) {
                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(propertyId), less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(propertyId), greater.getValue()));
                        }
                    }
                }
            }
        }
        return forecastQuery;
    }

    private DynamicQuery getFilterQuery(final Set<Container.Filter> filterSet, final DynamicQuery forecastQuery, final String tableName) {
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                                        if ("isActive".equals(stringFilter.getPropertyId())) {
                        
                        if ("Yes".equals(stringFilter.getFilterString())) {
                          
                            forecastQuery.add(RestrictionsFactoryUtil.eq(stringFilter.getPropertyId().toString(), String.valueOf(true)));
                        } else {
                         
                            Criterion c1 = RestrictionsFactoryUtil.eq(stringFilter.getPropertyId().toString(), String.valueOf(false));
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }

                    }
                 else  if ("isOutliner".equals(stringFilter.getPropertyId())) {

                        if ("true".equals(stringFilter.getFilterString())) {
                            forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), "true"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.OUT_LINER, "false");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.OUT_LINER);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else if (ConstantUtil.POSTING_INDICATOR.equals(stringFilter.getPropertyId())) {
                        if ("Yes".equals(stringFilter.getFilterString())) {
                            forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), "true"));
                        } else {
                            Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, "false");
                            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                            forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                        }
                    } else if ("deductionCategory".equals(stringFilter.getPropertyId())) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.RS_CATEGORY);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionType".equals(stringFilter.getPropertyId())) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.RS_TYPE);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionProgramType".equals(stringFilter.getPropertyId())) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.REBATE_PROGRAM_TYPE);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("status".equals(stringFilter.getPropertyId())) {
                        if ("AuditMasterInbound".equals(tableName)) {
                            forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), filterString));
                        } else {
                            String tmpValue = getHelperValues(filterString, ConstantUtil.STATUS);
                            forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                        }
                    } else {
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), filterString));
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                    forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), filterString1));
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Date) {
                        Date filterString = (Date) stringFilter.getValue();
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                            forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), filterString));
                        }
                    }
                    if (stringFilter.getValue() instanceof Integer) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Integer) stringFilter.getValue()) == 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId())));
                            } else {
                                int value = (Integer) stringFilter.getValue();
                                forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            int value = (Integer) stringFilter.getValue();
                            if (value < 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            int value = (Integer) stringFilter.getValue();
                            if (value > 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                    }
                    if (stringFilter.getValue() instanceof Double) {

                        if (stringFilter.getOperation().equals(stringFilter.getOperation().EQUAL)) {
                            if (((Double) stringFilter.getValue()) == 0.0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                String value = String.valueOf(stringFilter.getValue());

                                if (value.contains(".")) {
                                    String[] tempValues = value.split("[.]");
                                    if (tempValues.length > 1 && tempValues[1].length() <= 3) {
                                        Double[] arrValue = getGreaterLesserVal(value);
                                        if (tempValues[1].length() == 3) {
                                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        } else {
                                            forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        }
                                        forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), arrValue[1]));
                                    } else {
                                        Double douValue = (Double) stringFilter.getValue();
                                        forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), douValue));
                                    }

                                } else {
                                    Double douValue = (Double) stringFilter.getValue();
                                    forecastQuery.add(RestrictionsFactoryUtil.eq(String.valueOf(stringFilter.getPropertyId()), douValue));
                                }
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER)) {
                            Double value = (Double) stringFilter.getValue();
                            if (value < 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS)) {
                            Double value = (Double) stringFilter.getValue();
                            if (value > 0) {
                                forecastQuery.add(RestrictionsFactoryUtil.or(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), stringFilter.getValue()), RestrictionsFactoryUtil.isNull(String.valueOf(stringFilter.getPropertyId()))));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), value));
                            }
                        }
                    }

                } else if (filter instanceof And) {
                    And stringFilter = (And) filter;
                    Collection<Container.Filter> value = stringFilter.getFilters();
                    for (Container.Filter filter1 : value) {
                        Object propertyId = "";
                        if (filter1 instanceof Compare.Less) {
                            Compare.Less less = (Compare.Less) filter1;
                            propertyId = less.getPropertyId();
                            forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(propertyId), less.getValue()));
                        }
                        if (filter1 instanceof Compare.Greater) {
                            Compare.Greater greater = (Compare.Greater) filter1;
                            propertyId = greater.getPropertyId();
                            forecastQuery.add(RestrictionsFactoryUtil.gt(String.valueOf(propertyId), greater.getValue()));
                        }
                    }
                }
            }
        }
        return forecastQuery;
    }

    public Double[] getGreaterLesserVal(String value) {
        String[] tempValues = value.split("[.]");
        String s = "9995";
        DecimalFormat f = new DecimalFormat("##.0000");
        int length = tempValues[1].length();
        Double adjustValue;
        Double minValue;
        Double maxValue;
        if (length >= 3) {
            adjustValue = 1 / Math.pow(10, (length) + 1) * 5;
            minValue = Double.valueOf(f.format(Double.parseDouble(value) - adjustValue));
            maxValue = Double.valueOf(f.format(Double.parseDouble(value) + adjustValue));
        } else {
            adjustValue = 1 / Math.pow(10, (length));
            minValue = Double.valueOf(f.format(Double.parseDouble(value) - adjustValue));
            if (minValue.toString().split("[.]")[1].length() == length - 1) {
                minValue = Double.valueOf(minValue + "0" + s.substring(length));
            } else {
                minValue = Double.valueOf(minValue + s.substring(length));
            }
            maxValue = Double.valueOf(value + s.substring(length));
        }
        Double[] result = {maxValue, minValue};
        return result;
    }

    /**
     * Search forecast sales.
     *
     * @param searchForm the search form
     * @param start the start
     * @param end the end
     * @param orderByColumns the order by columns
     * @return the list< search forecast dt o>
     */
    @SuppressWarnings("unchecked")
    public List<Object> searchFind(final Map<Object, Object> searchValues, final int start, final int end, final List<SortByColumn> orderByColumns,
            final Set<Container.Filter> filterSet, String tableName, final List<DetailsDTO> moduleDetailsValue, boolean excelFlag, Object[] excelVisibleColumnArr, Map SelectedAccrualsIdMap,final DetailsDTO primaryDTO) throws PortalException, SystemException, Exception {
        LOGGER.info("Entering searchForecastSales with start value  :::: " + start + ",  and end value  :::: " + end + "  and orderByColumns size is   :::: "
                + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));
        String tempValue = StringUtils.EMPTY;
        DynamicQuery forecastQuery;
        tableName = tableName.trim();
        if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || (tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW))
                || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)
                || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
            forecastQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));
        } else {
            forecastQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
        }
        if (excelFlag) {
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
            for (Object columnList1 : excelVisibleColumnArr) {
                if (columnList1.toString().equals("glCompanyName") && tableName.equals("AccrualDetails")) {
                    projectonList.add(ProjectionFactoryUtil.property("companyId"));
                } else {
                    projectonList.add(ProjectionFactoryUtil.property(columnList1.toString()));
                }
            }
            forecastQuery.setProjection(projectonList);
        }
        for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
            if (entry.getKey().toString().contains("_Date")) {
                  if("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY)) || "invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))){
                      if(invalidToDate!=null && invalidFromDate!=null){
                          final Date invalidDate = new Date();
                          invalidDate.setTime(((Date) invalidToDate).getTime());
                          invalidDate.setHours(23);
                          invalidDate.setMinutes(59);
                          invalidDate.setSeconds(59);
                          forecastQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
                      } else if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                          forecastQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
                      } else if ("invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                          forecastQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get("invalidFromDate_Date")));
                      }
                  } else {
                      final Date forecastsDate = new Date();
                      forecastsDate.setTime(((Date) entry.getValue()).getTime());
                      forecastsDate.setHours(23);
                      forecastsDate.setMinutes(59);
                      forecastsDate.setSeconds(59);
                forecastQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                  }
            } else if (entry.getKey().toString().contains("_boolean")) {
                boolean flag = false;
                if (1 == Integer.valueOf(entry.getValue().toString())) {
                    flag = true;
                } else if (0 == Integer.valueOf(entry.getValue().toString())) {
                    flag = false;
                }
                if (ConstantUtil.GL_BALANCE_MASTER.equals(tableName)) {
                    
                    if (flag) {
                        forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag)));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, String.valueOf(flag));
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                        forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                    }

                } else if (ConstantUtil.ACCURAL_MASTER.equals(tableName)) {
                    if (flag) {
                        forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag)));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, String.valueOf(flag));
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                        forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                    }
                } else if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName)) {
                    if (flag) {
                        forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, ConstantUtil.KEY_Y));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.POSTING_INDICATOR, ConstantUtil.KEY_N);
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.POSTING_INDICATOR);
                        forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                    }
                } else {
                    if (flag) {
                        forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                        forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                    }
                }
            } else if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName.trim())
                    && (entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_TYPE)
                    || entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_PROGRAM_TYPE)
                    || entry.getKey().toString().trim().equals(ConstantUtil.DEDUCTION_CATEGORY))) {
                if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_CATEGORY)) {
                    listName = ConstantUtil.RS_CATEGORY;
                } else if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_TYPE)) {
                    listName = ConstantUtil.RS_TYPE;
                } else if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.DEDUCTION_PROGRAM_TYPE)) {
                    listName = ConstantUtil.REBATE_PROGRAM_TYPE;
                }
                String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                forecastQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
            } else if ("CpiIndexMaster".equals(tableName.trim())
                    && (entry.getKey().toString().trim().equals(ConstantUtil.STATUS))) {
                if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.STATUS)) {
                    listName = ConstantUtil.STATUS_DUP;
                }
                String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                forecastQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
            }
           else {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if ("%".equals(tempValue)) {
                    Criterion c1 = RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue);
                    Criterion c2 = RestrictionsFactoryUtil.isNull(entry.getKey().toString());
                    forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                } else {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                forecastQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue));
            }
            }
            
            }
        if (String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)).equalsIgnoreCase(ConstantUtil.INVALID_RECORD_COUNT)) {
            forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.RE_PROCESSED_FLAG, ConstantUtil.KEY_N));
        }
        forecastQuery.setLimit(start, end);
        if (orderByColumns == null || orderByColumns.isEmpty()) {
            //Added to avoid duplication in Excel export.
            if (StringUtils.isEmpty(primaryDTO.getInvalidTableName())) {
                
                if ("VwAdjustDemandForecastAct".equals(tableName)) {
                    forecastQuery.addOrder(OrderFactoryUtil.asc("adjustedDemandForecastId"));
                } else {
                    forecastQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getPropertyName()));
                }
            } else {
                forecastQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getInvalidTableName()));
            }
        }
        if (!excelFlag) {
            forecastQuery = getFilterQuery(filterSet, forecastQuery, tableName);            
            for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    forecastQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
                } else {
                    forecastQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
                }
            }
        }
        Class cls;
        if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER))
                || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) ) {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        } else {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        }

        Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC, DynamicQuery.class);
        List<Object> list = (List<Object>) method.invoke(cls.newInstance(), forecastQuery);
        if (!excelFlag) {
            if (ConstantUtil.GL_BALANCE_MASTER.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    GlBalanceMaster ob = (GlBalanceMaster) list.get(i);
                    if ("1".equals(ob.getIsActive())) {
                        ob.setIsActive(ConstantUtil.YES);
                    } else {
                        ob.setIsActive(ConstantUtil.NO);
                    }
                }
            }

            if (ConstantUtil.ACCURAL_MASTER.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    AccrualMaster ob = (AccrualMaster) list.get(i);
                    if ("1".equals(ob.getPostingIndicator())) {
                        ob.setPostingIndicator(ConstantUtil.YES);
                    } else if ("0".equals(ob.getPostingIndicator())) {
                        ob.setPostingIndicator(ConstantUtil.NO);
                    } else {
                        ob.setPostingIndicator(StringUtils.EMPTY);
                    }
                }
            }
            if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    AccrualDetails ob = (AccrualDetails) list.get(i);
                    if (ConstantUtil.KEY_Y.equals(ob.getPostingStatus())) {
                        ob.setPostingStatus(ConstantUtil.YES);
                    } else if (ConstantUtil.KEY_N.equals(ob.getPostingStatus())) {
                        ob.setPostingStatus(ConstantUtil.NO);
                    } else {
                        ob.setPostingStatus(StringUtils.EMPTY);
                    }

                    if (ConstantUtil.KEY_Y.equals(ob.getPostingIndicator())) {
                        ob.setPostingIndicator(ConstantUtil.YES);
                    } else if (ConstantUtil.KEY_N.equals(ob.getPostingIndicator())) {
                        ob.setPostingIndicator(ConstantUtil.NO);
                    } else {
                        ob.setPostingIndicator(StringUtils.EMPTY);
                    }
                    if (SelectedAccrualsIdMap.size() > 0) {
                        if (SelectedAccrualsIdMap.get(ob.getAccrualDetailsSid()) != null) {
                            ob.setRecordLockStatus((Boolean) SelectedAccrualsIdMap.get(ob.getAccrualDetailsSid()));
                        }

                    } else {
                        ob.setRecordLockStatus(false);
                    }

                }
            }
            if (ConstantUtil.DEMANDVIEW_TABLE.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    VwDemandForecastActual ob = (VwDemandForecastActual) list.get(i);
                    if ("1".equals(ob.getIsForecast())) {
                        ob.setIsForecast(ConstantUtil.PROJECTION);
                    } else {
                        ob.setIsForecast(ConstantUtil.ACTUALS);
                    }
                }
            }
            if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    VwCustomerGtsForecast ob = (VwCustomerGtsForecast) list.get(i);
                    ob.setDeductionCategory((!ob.getDeductionCategory().isEmpty() && !"0".equals(ob.getDeductionCategory()))? commonLogic.getDescription(Integer.valueOf(ob.getDeductionCategory())) : StringUtils.EMPTY);
                    ob.setDeductionProgramType((!ob.getDeductionProgramType().isEmpty()&& !"0".equals(ob.getDeductionProgramType()))? commonLogic.getDescription(Integer.valueOf(ob.getDeductionProgramType())) : StringUtils.EMPTY);
                    ob.setDeductionType((!ob.getDeductionType().isEmpty() && !"0".equals(ob.getDeductionType() ))? commonLogic.getDescription(Integer.valueOf(ob.getDeductionType())) : StringUtils.EMPTY);
                    ob.setUdc1((!ob.getUdc1().isEmpty() && !"0".equals(ob.getUdc1())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc1())) : StringUtils.EMPTY);
                    ob.setUdc2((!ob.getUdc2().isEmpty() && !"0".equals(ob.getUdc2())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc2())) : StringUtils.EMPTY);
                    ob.setUdc3((!ob.getUdc3().isEmpty() && !"0".equals(ob.getUdc3()) )? commonLogic.getDescription(Integer.valueOf(ob.getUdc3())) : StringUtils.EMPTY);
                    ob.setUdc4((!ob.getUdc4().isEmpty()&& !"0".equals(ob.getUdc4())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc4())) : StringUtils.EMPTY);
                    ob.setUdc5((!ob.getUdc5().isEmpty() && !"0".equals(ob.getUdc5())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc5())) : StringUtils.EMPTY);
                    ob.setUdc6((!ob.getUdc6().isEmpty() && !"0".equals(ob.getUdc6())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc6())) : StringUtils.EMPTY);

                }
            }
            if ("CpiIndexMaster".equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    CpiIndexMaster ob = (CpiIndexMaster) list.get(i);
                    ob.setStatus(CommonLogic.getDescription(Integer.valueOf(ob.getStatus())));
                }

            }
            if (tableName.contains(ConstantUtil.INVENTORYVIEW_TABLE) || tableName.contains(ConstantUtil.INVALID_INVENTORYVIEW_TABLE)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                    if (tableName.contains(ConstantUtil.INVALID_INVENTORYVIEW_TABLE)) {
                        VwIvldInventoryWdActualProjMas ob = (VwIvldInventoryWdActualProjMas) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty() && ob.getCreatedBy().matches("\\-?\\d+")) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty() && ob.getModifiedBy().matches("\\-?\\d+")) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else {
                        VwInventoryWdActualProjMas ob = (VwInventoryWdActualProjMas) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    }
                }
            }
            if (tableName.contains(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.contains(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                    if (tableName.contains(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)) {
                        IvldCustomerGtsActual ob = (IvldCustomerGtsActual) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty() && ob.getCreatedBy().matches("\\-?\\d+")) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty() && ob.getModifiedBy().matches("\\-?\\d+")) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else {
                        CustomerGtsActual ob = (CustomerGtsActual) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()
                                && ob.getCreatedBy().matches("\\-?\\d+")) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()
                                && ob.getModifiedBy().matches("\\-?\\d+")) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    }
                }
            }
            if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.contains(ConstantUtil.INVALID_GTS_CUSTOMER)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                    if (tableName.contains(ConstantUtil.INVALID_GTS_CUSTOMER)) {
                        IvldCustomerGtsForecast ob = (IvldCustomerGtsForecast) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty() && ob.getCreatedBy().matches("\\-?\\d+")) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty() && ob.getModifiedBy().matches("\\-?\\d+")) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else {
                        VwCustomerGtsForecast ob = (VwCustomerGtsForecast) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()
                                && ob.getCreatedBy().matches("\\-?\\d+")) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()
                                && ob.getModifiedBy().matches("\\-?\\d+")) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    }
                }
            }
            if (tableName.contains("IvldReturns")) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldReturns ob = (IvldReturns) list.get(i);
                        ob.setOrigSaleMonth(df2.format(df.parse(ob.getOrigSaleMonth())));
                        ob.setFirstReturn(df2.format(df.parse(ob.getFirstReturn())));
                        ob.setLastReturn(df2.format(df.parse(ob.getLastReturn())));
                        ob.setMaxExpiredMonth(df2.format(df.parse(ob.getMaxExpiredMonth())));
                        ob.setOrigSaleMonthCutOff(df2.format(df.parse(ob.getOrigSaleMonthCutOff())));
                        ob.setMaxExpiredMonthPlusCutoff(df2.format(df.parse(ob.getMaxExpiredMonthPlusCutoff())));
                        ob.setLoadDate(df2.format(df.parse(ob.getLoadDate())));
                    } catch (ParseException e) {
                        LOGGER.error("Un parseable Date" + e.getCause());
                    }
                }
            }
                }

        LOGGER.info(" Ends searchForecastSales with the  Serach List size    ::::  " + ((list == null) ? list : list.size()));
          return list;
       
    }

    public int getActualSearchResults(Map<Object, Object> searchValues, Set<Filter> bsc, String tableName) throws Exception {
        LOGGER.info("Entering getActualSearchResults with searchForm" + tableName);
        String tempValue = StringUtils.EMPTY;
        final DynamicQuery contractMasterQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);

        final DynamicQuery brandMasterQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);

        final DynamicQuery itemMasterQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);

        DynamicQuery actualsDynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
        String[] str = {ConstantUtil.ACCOUNTNO, ConstantUtil.ACTUALID, ConstantUtil.ACCOUNT_NAME, ConstantUtil.SETTLEMENT_NO, ConstantUtil.ITEMNO, ConstantUtil.PROVISIONID, ConstantUtil.QUANTITY_INCLUSION, "actualIntfid","batchId"};
        List<String> wordList = Arrays.asList(str);
        for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {

            if (wordList.contains(entry.getKey())) {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if ("%".equals(tempValue)) {
                    Criterion c1 = RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue);
                    Criterion c2 = RestrictionsFactoryUtil.isNull(entry.getKey().toString());
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                } else {
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue));
                }
            }

        }

        //Fetching data from Contract Master 
        boolean flag = false;
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NO)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            contractMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.CONTRACT_NO, tempValue));
            flag = true;
        }
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NAME)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            contractMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.CONTRACT_NAME, tempValue));
            flag = true;
        }
        if (flag) {
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.CONTRACTID).in(contractMasterQuery.setProjection(ProjectionFactoryUtil.property(ConstantUtil.CONTRACTID))));
            flag = false;
        }

        //Fetching data from Brand Master
        if (searchValues.containsKey(ConstantUtil.BRAND)) {
            tempValue = searchValues.get(ConstantUtil.BRAND).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            brandMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.BRAND_NAME, tempValue));
            flag = true;
        }
        if (flag) {
            if ("%".equals(tempValue)) {
            Criterion c1 = RestrictionsFactoryUtil.like(ConstantUtil.BRANDID, tempValue);
            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.BRANDID);
             actualsDynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
               }else{
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.BRANDID).in(brandMasterQuery.setProjection(ProjectionFactoryUtil.property(ConstantUtil.BRANDID))));
            }
            flag = false;
        }

        //Fetching data from Item Master
        if (searchValues.containsKey(ConstantUtil.ITEM_NAME)) {
            tempValue = searchValues.get(ConstantUtil.ITEM_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            itemMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.ITEM_NAME, tempValue));
            flag = true;
        }
        if (flag) {
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.ITEMID).in(itemMasterQuery.setProjection(ProjectionFactoryUtil.property(ConstantUtil.ITEMID))));
            flag = false;
        }

        if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        }
        }
        if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        }
        }

        if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        }
        }

        if (searchValues.containsKey(ConstantUtil.AMOUNT_FROM) && searchValues.containsKey(ConstantUtil.AMOUNT_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.AMOUNT, Double.valueOf(searchValues.get(ConstantUtil.AMOUNT_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.AMOUNT_TO).toString())));
        }

        if (searchValues.containsKey(ConstantUtil.SALES_AMOUNT_FROM) && searchValues.containsKey(ConstantUtil.SALES_AMOUNT_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.SALES_AMOUNT, Double.valueOf(searchValues.get(ConstantUtil.SALES_AMOUNT_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.SALES_AMOUNT_TO).toString())));
        }

        if (searchValues.containsKey(ConstantUtil.QUANTITY_FROM) && searchValues.containsKey(ConstantUtil.QUANTITY_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.QUANTITY, Double.valueOf(searchValues.get(ConstantUtil.QUANTITY_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.QUANTITY_TO).toString())));
        }
                if(searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)  || searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)){
                    if(invalidToDate!=null && invalidFromDate!=null){ 
                         final Date invalidDate = new Date();
                          
                    invalidDate.setTime(((Date) invalidToDate).getTime());
                    invalidDate.setHours(23);
                    invalidDate.setMinutes(59);
                    invalidDate.setSeconds(59);
                     actualsDynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));    
                    } else if(searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)){
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate",(Date)searchValues.get("invalidToDate_Date")));
                         }else if(searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)){
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date)searchValues.get("invalidFromDate_Date")));         
                         }
                }
        if ("IvldActualMaster".equals(tableName)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.like("reprocessedFlag", "N"));
        }
        actualsDynamicQuery = getFilterQuery(bsc, actualsDynamicQuery, tableName);
        LOGGER.info(" Ends getActualSearchResults with dynamicQuery");

        int count;
        if (actualsDynamicQuery == null) {
            count = 0;
        } else {
            Class cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC_COUNT, DynamicQuery.class);
            Object ob = method.invoke(cls.newInstance(), actualsDynamicQuery);
            count = Integer.valueOf(ob.toString());
        }
        return count;
    }

    public List<Object> searchFindForActualMaster(Map<Object, Object> searchValues, int startIndex, int i, List<SortByColumn> sortByColumns, Set<Filter> criteria, String tableName, List<DetailsDTO> moduleDetailsValue, boolean excelFlag, Object[] excelVisibleColumnArr,final DetailsDTO primaryDTO) throws Exception {
        String tempValue = StringUtils.EMPTY;
        final DynamicQuery contractMasterQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);

        final DynamicQuery brandMasterQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);

        final DynamicQuery itemMasterQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        for (int j = 0; j < searchValues.size(); j++) {
            if (searchValues.containsKey("invalidFromDate_Date")) {
                invalidFromDate = (Date) searchValues.get("invalidFromDate_Date");
            } else {
                invalidFromDate = null;
            }
            if (searchValues.containsKey("invalidToDate_Date")) {
                invalidToDate = (Date) searchValues.get("invalidToDate_Date");
            } else {
                invalidToDate = null;
            }
        }
        DynamicQuery actualsDynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
        String[] str = {ConstantUtil.ACCOUNTNO, ConstantUtil.ACTUALID, ConstantUtil.ACCOUNT_NAME, ConstantUtil.SETTLEMENT_NO, ConstantUtil.ITEMNO, ConstantUtil.PROVISIONID, ConstantUtil.QUANTITY_INCLUSION, "actualIntfid", "batchId"};
        List<String> wordList = Arrays.asList(str);
        for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
            if (wordList.contains(entry.getKey())) {
                tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if ("%".equals(tempValue)) {
                    Criterion c1 = RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue);
                    Criterion c2 = RestrictionsFactoryUtil.isNull(entry.getKey().toString());
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
                } else {
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tempValue));
                }
            }

        }

        //Fetching data from Contract Master 
        boolean flag = false;
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NO)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            contractMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.CONTRACT_NO, tempValue));
            flag = true;
        }
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NAME)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            contractMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.CONTRACT_NAME, tempValue));
            flag = true;
        }
        if (flag) {
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.CONTRACTID).in(contractMasterQuery.setProjection(ProjectionFactoryUtil.property(ConstantUtil.CONTRACTID))));
            flag = false;
        }

        //Fetching data from Brand Master
        if (searchValues.containsKey(ConstantUtil.BRAND)) {
            tempValue = searchValues.get(ConstantUtil.BRAND).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                    brandMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.BRAND_NAME, tempValue));
            flag = true;
        }
        if (flag) {
               if ("%".equals(tempValue)) {
            Criterion c1 = RestrictionsFactoryUtil.like(ConstantUtil.BRANDID, tempValue);
            Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.BRANDID);
             actualsDynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
               }else{
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.BRANDID).in(brandMasterQuery.setProjection(ProjectionFactoryUtil.property(ConstantUtil.BRANDID))));
               }
            flag = false;
        }

        //Fetching data from Item Master
        if (searchValues.containsKey(ConstantUtil.ITEM_NAME)) {
            tempValue = searchValues.get(ConstantUtil.ITEM_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            itemMasterQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.ITEM_NAME, tempValue));
            flag = true;
        }
        if (flag) {
            actualsDynamicQuery.add(PropertyFactoryUtil.forName(ConstantUtil.ITEMID).in(itemMasterQuery.setProjection(ProjectionFactoryUtil.property("itemId"))));
            flag = false;
        }

        if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        }
        }
        if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        }
        }

        if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        } else {
            if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date")));
            } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        }
        }
        if (searchValues.containsKey(ConstantUtil.AMOUNT_FROM) && searchValues.containsKey(ConstantUtil.AMOUNT_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.AMOUNT, Double.valueOf(searchValues.get(ConstantUtil.AMOUNT_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.AMOUNT_TO).toString())));
        }

        if (searchValues.containsKey(ConstantUtil.SALES_AMOUNT_FROM) && searchValues.containsKey(ConstantUtil.SALES_AMOUNT_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.SALES_AMOUNT, Double.valueOf(searchValues.get(ConstantUtil.SALES_AMOUNT_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.SALES_AMOUNT_TO).toString())));
        }

        if (searchValues.containsKey(ConstantUtil.QUANTITY_FROM) && searchValues.containsKey(ConstantUtil.QUANTITY_TO)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.QUANTITY, Double.valueOf(searchValues.get(ConstantUtil.QUANTITY_FROM).toString()), Double.valueOf(searchValues.get(ConstantUtil.QUANTITY_TO).toString())));
        }
        if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE) || searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
            if (invalidToDate != null && invalidFromDate != null) {
                    final Date invalidDate = new Date();

                    invalidDate.setTime(((Date) invalidToDate).getTime());
                    invalidDate.setHours(23);
                    invalidDate.setMinutes(59);
                    invalidDate.setSeconds(59);
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
                } else if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)) {
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
                } else if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                    actualsDynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get("invalidFromDate_Date")));
                }
                }
        if ("IvldActualMaster".equals(tableName)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.like("reprocessedFlag", "N"));
        }
        if (sortByColumns==null || sortByColumns.isEmpty()) {
            if(StringUtils.isEmpty(primaryDTO.getInvalidTableName())){
              actualsDynamicQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getPropertyName()));
            } else {
              actualsDynamicQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getInvalidTableName()));
            }
        }

        if (!excelFlag) {
            actualsDynamicQuery = getFilterQuery(criteria, actualsDynamicQuery, tableName);
            actualsDynamicQuery.setLimit(startIndex, i);
            LOGGER.info(" Ends getDynamicQuerySearch with dynamicQuery");
                for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    SortByColumn orderByColumn = (SortByColumn) iterator.next();
                    String columnId = orderByColumn.getName();
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        actualsDynamicQuery.addOrder(OrderFactoryUtil.asc(orderByColumn.getName()));
                    } else {
                        actualsDynamicQuery.addOrder(OrderFactoryUtil.desc(orderByColumn.getName()));
                    }
                }
            }
        if (excelFlag) {
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
            for (Object columnList1 : excelVisibleColumnArr) {
                projectonList.add(ProjectionFactoryUtil.property(columnList1.toString()));
            }
            actualsDynamicQuery.setProjection(projectonList);
            actualsDynamicQuery.setLimit(startIndex, i);
        }
        Class cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC, DynamicQuery.class);
        List<Object> list = (List<Object>) method.invoke(cls.newInstance(), actualsDynamicQuery);
        LOGGER.info(" Ends searchForecastSales with the  Serach List size    ::::  " + ((list == null) ? list : list.size()));
        return list;
    }

    public List<Object> searchFindForReturnsMsater(Map<Object, Object> searchValues, int startIndex, int i, final List<SortByColumn> orderByColumns,
            final Set<Container.Filter> filterSet, String tableName, List<DetailsDTO> moduleDetailsValue, boolean excelFlag, Object[] excelVisibleColumnArr) {

        String QuerytableName = "";
        if ("IvldReturns".equals(tableName)) {
            QuerytableName = "IVLD_RETURNS";
        }
        if (tableName.equals("ReturnsMaster")) {
            QuerytableName = "RETURNS_MASTER";
        }
        String query = getInvalidReturnFind(QuerytableName);

        Map<String, String> input = new HashMap<String, String>();
        input.put("?DESCRIPTION?", "%");
        input.put("?EXTENDED_BRAND?", "%");
        input.put("?MAXIMUM?", "%");
        input.put("?PERCENTILE_75TH?", "%");
        input.put("?PERCENTILE_50TH?", "%");
        input.put("?PERCENTILE_25TH?", "%");
        input.put("?UPPER_OB?", "%");
        input.put("?LOWER_OB?", "%");
        input.put("?ORIG_SALE_UNITS?", "%");
        input.put("?ORIG_SALE_DOLLARS?", "%");
        input.put("?CUM_RETURN_UNITS?", "%");

        if (searchValues.size() != 0) {
            query += " WHERE";

            if (searchValues.containsKey("isOutliner")) {
            }
            if (searchValues.containsKey(ConstantUtil.BRAND)) {
                String value = searchValues.get(ConstantUtil.BRAND).toString();

            }
            if (searchValues.containsKey("percentile75th")) {
                String value = String.valueOf(Double.valueOf(searchValues.get("percentile75th").toString()));
                input.put("?PERCENTILE_75TH?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }

            if (searchValues.containsKey("percentile25th")) {
                String value = String.valueOf(Double.valueOf(searchValues.get("percentile25th").toString()));
                input.put("?PERCENTILE_25TH?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }
            if (searchValues.containsKey("percentile50th")) {
                String value = String.valueOf(Double.valueOf(searchValues.get("percentile50th").toString()));
                input.put("?PERCENTILE_50TH?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }
            if (searchValues.containsKey("extendedBrand")) {
                String value = String.valueOf(searchValues.get("extendedBrand").toString());
                input.put("?EXTENDED_BRAND?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }
            if (searchValues.containsKey("description")) {
                String value = searchValues.get("description").toString();
                input.put("?DESCRIPTION?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }

            if (searchValues.containsKey("maximum")) {
                String value = String.valueOf(Double.valueOf(searchValues.get("maximum").toString()));
                input.put("?MAXIMUM?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }
            if (searchValues.containsKey("upperOb")) {
                String value = String.valueOf(Double.valueOf(searchValues.get("upperOb").toString()));
                input.put("?UPPER_OB?", value.replace(String.valueOf(ConstantUtil.CHAR_ASTERISK), String.valueOf(ConstantUtil.CHAR_PERCENT)));

            }
        }
        String finalCountQuery = getQuery(input, query);
        List<Object> salesList1 = HelperTableLocalServiceUtil.executeSelectQuery(finalCountQuery);
        return salesList1;
    }

    public List<String> getQuantityInclusion() {
        List<String> resultList = new ArrayList<String>();
        List<String> quantityInclusion = new ArrayList<String>();
        DynamicQuery actualsMasterQuery = DynamicQueryFactoryUtil.forClass(ActualsMaster.class);
        ProjectionList projList = ProjectionFactoryUtil.projectionList();
        projList.add(ProjectionFactoryUtil.property(ConstantUtil.QUANTITY_INCLUSION));
        actualsMasterQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
        actualsMasterQuery.addOrder(OrderFactoryUtil.asc(ConstantUtil.QUANTITY_INCLUSION));
        try {
            quantityInclusion = (List<String>) ActualsMasterLocalServiceUtil.dynamicQuery(actualsMasterQuery);;
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        resultList.add(ConstantUtil.SELECT_ONE);
        resultList.addAll(quantityInclusion);
        return resultList;
    }

    private String getInvalidReturnCount(String tableName) {
        String query = "        SELECT COUNT(*) CNT FROM " + tableName + " WHERE \n"
                + "               EXTENDED_BRAND like '?EXTENDED_BRAND?'\n"
                + "            AND DESCRIPTION like '?DESCRIPTION?' \n"
                + "            AND MAXIMUM like '?MAXIMUM?' \n"
                + "            AND PERCENTILE_75TH like '?PERCENTILE_75TH?' \n"
                + "            AND PERCENTILE_50TH like '?PERCENTILE_50TH?' \n"
                + "            AND PERCENTILE_25TH like '?PERCENTILE_25TH?'\n"
                + "            AND UPPER_OB like '?UPPER_OB?'\n";
        return query;
    }

    public static String getQuery(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(queryName);
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        return queryString.toString();
    }

    private String getInvalidReturnFind(String QuerytableName) {
        String invalidquery = "";
        if (QuerytableName.contains("IVLD_RETURNS")) {
            invalidquery = ",RETURNS_INTERFACE_ID,IVLD_RETURNS_SID,ERROR_CODE,ERROR_FIELD,REASON_FOR_FAILURE ,REPROCESSED_FLAG";
        } else {
            invalidquery = ",RETURNS_MASTER_SID";
        }
        String query = "select EXTENDED_BRAND,DESCRIPTION,MAXIMUM,"
                + "PERCENTILE_75TH,PERCENTILE_50TH,PERCENTILE_25TH,UPPER_OB,"
                + "LOWER_OB,IS_OUTLINER,ORIG_SALE_MONTH,ORIG_SALE_UNITS,"
                + "ORIG_SALE_DOLLARS,ACTUAL_RETURN_RATE,WEIGHTED_AVERAGE_MONTHS,"
                + "FIRST_RETURN_DATE,LAST_RETURN_DATE,MAX_EXPIRED_MONTH,MAX_EXPIRED_MONTH_PLUS_CUTOFF,"
                + "EXPECTED_RETURN_RATE,EXPECTED_RETURN_UNITS,VALUE_AT_ORIGINAL_ASP,ADJUSTED_VALUE_AT_ORIGINAL_ASP ";

        query = query + invalidquery;
        query = query + "  from " + QuerytableName + " WHERE "
                + "               EXTENDED_BRAND like '?EXTENDED_BRAND?'\n"
                + "            AND DESCRIPTION like '?DESCRIPTION?' \n"
                + "            AND MAXIMUM like '?MAXIMUM?' \n"
                + "            AND PERCENTILE_75TH like '?PERCENTILE_75TH?' \n"
                + "            AND PERCENTILE_50TH like '?PERCENTILE_50TH?' \n"
                + "            AND PERCENTILE_25TH like '?PERCENTILE_25TH?'\n"
                + "            AND UPPER_OB like '?UPPER_OB?'\n";
        return query;
    }

    private String getHelperValues(String helperValue, String listval) {
        String sqlQuery = "SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE DESCRIPTION LIKE '" + helperValue + "' AND LIST_NAME LIKE '" + listval + "'";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return String.valueOf(list.get(0));
    }
   public List excelLogicForInvalidSales(Map<Object, Object> searchValues,int start,int end) {
       String query = "Select SALES_ID,ITEM_ID,ITEM_NO,ACCOUNT_ID,ACCOUNT_NO,CONTRACT_ID,CONTRACT_NO,COMPANY_ID,\n"
               + "INVOICE_NUMBER,INVOICE_DATE,ORGANIZATION_KEY,PARENT_ITEM_ID,PARENT_ITEM_NO,ITEM_UOM,DOC_TYPE,ORDER_NUMBER,PROVISION_ID,\n"
               + " ORDER_RECEIVED_DATE,INVOICE_LINE_NUMBER,ORDER_TYPE,ORDER_SUBTYPE,ANALYSIS_CODE,PRICE_ADJUSTMENT_NAME,RECORD_SEQUENCE,\n"
               + "PRICE,QUANTITY,LOT_NO,ACCOUNT_TYPE,CUSTOMER_SUBTYPE,WHOLESALE_OWNER_ID,ACCOUNT_NAME,IDENTIFIER_CODE_QUALIFIER,CUSTOMER_COMPANY_CODE,\n"
               + "IS_ACTIVE,DIVISION_ID,MARKET_ID,BRAND_ID,REASON_FOR_FAILURE,ERROR_CODE,ERROR_FIELD,BATCH_ID,SALES_INTFID\n"
               + "from  dbo.IVLD_SALES_MASTER where Item_ID like '%'";
       for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
           String tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
           query = query + " AND " + (getInvalidSalesDbColumn(entry.getKey().toString())) + " like'" + tempValue + "'";
       }
       query = query + " ORDER BY  SALES_INTFID  ASC\n"
               + "OFFSET "+start+" ROWS FETCH NEXT 20000 ROWS ONLY ";
       
       List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
       return list;
    }
       public   String getInvalidSalesDbColumn(String s) {
        switch (s) {
            case "salesIntfid":
                return "SALES_INTFID";
            case "salesId":
                return "SALES_ID";
            case "itemId":
                return "ITEM_ID";
            case "itemNo":
                return "ITEM_NO";
            case "accountId":
                return "ACCOUNT_ID";
            case "accountNo":
                return "ACCOUNT_NO";
            case "contractId":
                return "CONTRACT_ID";
            case "batchId":
                return "BATCH_ID";
            case "contractNo":
                return "CONTRACT_NO";
            case "companyId":
                return "COMPANY_ID";
            default:
                return "ITEM_ID";
        }
    }
}
