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
import com.stpl.app.model.IvldActualMaster;
import com.stpl.app.model.IvldReturns;
import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.VwIvldDemandForecastActual;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.model.IvldCompanyIdentifier;
import com.stpl.app.parttwo.model.IvldCompanyMaster;
import com.stpl.app.parttwo.model.IvldCompanyParent;
import com.stpl.app.parttwo.model.IvldCompanyTradeClass;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.model.IvldItemIdentifier;
import com.stpl.app.parttwo.model.IvldItemMaster;
import com.stpl.app.parttwo.model.IvldItemPricing;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;
import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.model.VwCompanyIdentifier;
import com.stpl.app.parttwo.model.VwCompanyMaster;
import com.stpl.app.parttwo.model.VwCompanyParentDetails;
import com.stpl.app.parttwo.model.VwCompanyTradeClass;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.parttwo.model.VwItemIdentifier;
import com.stpl.app.parttwo.model.VwItemMaster;
import com.stpl.app.parttwo.model.VwItemPricing;
import com.stpl.app.parttwo.model.VwIvldReturnReserve;
import com.stpl.app.parttwo.model.VwReturnReserve;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.ActualsMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.transactional.common.dto.ARPOutboundDTO;
import com.stpl.app.transactional.common.dto.AdjustmentDetailsDTO;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import static com.stpl.app.transactional.common.logic.SearchLogic.getQuery;
import com.stpl.app.transactional.common.util.AbstractFilterLogic;
import com.stpl.app.util.HelperListUtil;
import com.stpl.app.transactional.common.util.QueryUtils;
import com.stpl.app.transactional.common.util.xmlparser.AbstractFilter;
import com.stpl.app.transactional.common.util.xmlparser.QueryAPP;
import com.stpl.app.util.QueryDataUtils;
import com.stpl.app.util.SysDataSourceConnection;
import com.stpl.app.transactional.common.util.xmlparser.SQlUtil;
import com.stpl.app.util.ConnectionUtils;
import com.stpl.app.util.ConstantUtil;
import com.stpl.ifs.ui.util.NumericConstants;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    static HashMap<String, String> pivotQueryFilterMap = new HashMap<>();
    static HashMap<String, String> countQueryFilterMap = new HashMap<>();
    static HashMap<String, String> filterMap = new HashMap<>();
    static Map<String, String> gtnMap = new HashMap<>();
    static Map<String, String> reserveMap = new HashMap<>();
    HelperListUtil helper = HelperListUtil.getInstance();
    DecimalFormat twodecimalformat = new DecimalFormat("#0.00");

    /**
     * Gets the actuals total count.
     *
     * @return the actuals total count
     */
    public int getActualsTotalCount(String tableName) throws PortalException, SystemException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
        LOGGER.debug("Entering getActualsTotalCount");
        Class cls;
        Method method = null;
        if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)
                || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName) || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName)
                || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName)
                || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName)
                || tableName.equals(ConstantUtil.IBID_ITEM_MASTER) || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVID_ITEM_PRICING) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)
                || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        } else {
            cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
        }
        if (!("IvldFormulaDetails".equals(tableName) || "IvldForecastSales".equals(tableName) || "IvldReturns".equals(tableName) || "VwCompanyParentDetails".equals(tableName) || "VwCompanyTradeClass".equals(tableName) || "IvldCompanyTradeClass".equals(tableName))) {
            method = cls.getMethod("get" + tableName + "sCount");
        } else if ("IvldFormulaDetails".equals(tableName) || "IvldForecastSales".equals(tableName) || "IvldReturns".equals(tableName) || "VwCompanyParentDetails".equals(tableName) || "VwCompanyTradeClass".equals(tableName) || "IvldCompanyTradeClass".equals(tableName)) {
            method = cls.getMethod("get" + tableName + "esCount");
        }
        Object ob = method.invoke(cls.newInstance());
        final int temp = Integer.valueOf(ob.toString());
        LOGGER.debug(" Ends getActualsTotalCount with the  temp value    ::::  " + temp);
        return temp;
    }

    /**
     * Gets the dynamic query search.
     *
     * @param searchForm the search form
     * @return the dynamic query search
     */
    public int getDynamicQuerySearch(final Map<Object, Object> searchValues, final Set<Filter> search, final String tableName) throws PortalException, SystemException{
        LOGGER.debug("Entering getDynamicQuerySearch with" + searchValues);
        int count = 0;
        try {

            String tempValue = StringUtils.EMPTY;
            DynamicQuery dynamicQuery;
            if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW) || tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)
                    || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName) || tableName.equals(ConstantUtil.IBID_ITEM_MASTER)
                    || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVID_ITEM_PRICING) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)
                    || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                    || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));
            } else {
                dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
            }
            if (searchValues.containsKey(ConstantUtil.LEVEL)) {
                searchValues.remove(ConstantUtil.LEVEL);
            }
            for (int i = 0; i < searchValues.size(); i++) {
                if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                    invalidFromDate = (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE);
                } else {
                    invalidFromDate = null;
                }
                if (searchValues.containsKey("invalidToDate_Date")) {
                    invalidToDate = (Date) searchValues.get("invalidToDate_Date");
                } else {
                    invalidToDate = null;
                }
            }
            for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
                if (entry.getKey().toString().contains("_Date")) {
                    if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY)) || "invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                        if (invalidToDate != null && invalidFromDate != null) {
                            final Date invalidDate = new Date();

                            invalidDate.setTime(((Date) invalidToDate).getTime());
                            invalidDate.setHours(NumericConstants.TWENTY_THREE);
                            invalidDate.setMinutes(NumericConstants.FIFTY_NINE);
                            invalidDate.setSeconds(NumericConstants.FIFTY_NINE);
                            dynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
                        } else if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                            dynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
                        } else if ("invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {

                            dynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE) || searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                        if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.between("financialForecastCreationDate", searchValues.get(ConstantUtil.CFF_CREATION_FROM_DATE), searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.ge("financialForecastCreationDate", searchValues.get(ConstantUtil.CFF_CREATION_FROM_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.le("financialForecastCreationDate", searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE) || searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
                        if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.between("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE), searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.ge("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
                            dynamicQuery.add(RestrictionsFactoryUtil.le("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY) || searchValues.containsKey(ConstantUtil.REDEMPTION_FROM_DATE) || searchValues.containsKey(ConstantUtil.REDEMPTION_TO_DATE)) {
                        SimpleDateFormat format = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
                        if (searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY) != null) {
                            Date reserveFromDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY)));
                            if (searchValues.get(ConstantUtil.REDEMPTION_TO_DATE) != null) {
                                Date reserveToDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_TO_DATE)));
                                dynamicQuery.add(RestrictionsFactoryUtil.between("primaryKey.redemptionPeriod", reserveFromDate, reserveToDate));
                            } else {
                                dynamicQuery.add(RestrictionsFactoryUtil.gt("primaryKey.redemptionPeriod", reserveFromDate));
                            }
                        } else if (searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE) != null) {
                            Date reserveFromDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE)));
                            if (searchValues.get(ConstantUtil.REDEMPTION_TO_DATE) != null) {
                                Date reserveToDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_TO_DATE)));
                                dynamicQuery.add(RestrictionsFactoryUtil.between("redemptionPeriod", reserveFromDate, reserveToDate));
                            } else {
                                dynamicQuery.add(RestrictionsFactoryUtil.gt("redemptionPeriod", reserveFromDate));
                            }
                        }
                    } else if (entry.getValue() != null) {
                        final Date forecastsDate = new Date();
                        forecastsDate.setTime(((Date) entry.getValue()).getTime());
                        forecastsDate.setHours(NumericConstants.TWENTY_THREE);
                        forecastsDate.setMinutes(NumericConstants.FIFTY_NINE);
                        forecastsDate.setSeconds(NumericConstants.FIFTY_NINE);
                        dynamicQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                    }
                } else if (entry.getKey().toString().contains("_boolean")) {

                    boolean flag = false;
                    if (NumericConstants.ONE == Integer.valueOf(String.valueOf(entry.getValue()))) {

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
                    } else if (flag) {
                        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                        dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
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
                } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName.trim()) && entry.getKey().toString().trim().equals(ConstantUtil.TYPE)) {
                    listName = ConstantUtil.CFF_TYPE;
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
                } else if ("CpiIndexMaster".equals(tableName.trim())
                        && (entry.getKey().toString().trim().equals(ConstantUtil.STATUS))) {
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.STATUS)) {
                        listName = ConstantUtil.STATUS_DUP;
                    }
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.STATUS, tmpValue));
                } else {
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
            if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                dynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey." + ConstantUtil.USERID, String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID))));
                dynamicQuery.add(RestrictionsFactoryUtil.eq("primaryKey." + ConstantUtil.SESSIONID, String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID))));
            }

            dynamicQuery = getFilterQuery(search, dynamicQuery, tableName);

            LOGGER.debug(" Ends getDynamicQuerySearch with dynamicQuery");

            Class cls;
            if ((tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)
                    || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName) || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName)
                    || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName) || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName) || tableName.equals(ConstantUtil.IBID_ITEM_MASTER) || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER)
                    || tableName.equals(ConstantUtil.IVID_ITEM_PRICING) || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                    || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
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
        LOGGER.debug(" Ends hereee...getDynamicQuerySearch..");
        return count;

    }

    public int getDynamicQuerySearch(final Map<Object, Object> searchValues, final String className,
            final String tableName, final Set<Container.Filter> filterSet) throws PortalException, SystemException {
        LOGGER.debug("Entering getDynamicQuerySearch with" + className);
        int count = 0;
        try {

            String tempValue = StringUtils.EMPTY;
            DynamicQuery dynamicQuery;
            dynamicQuery = DynamicQueryFactoryUtil.forClass(Class.forName(className));
            if (searchValues.containsKey("level")) {
                searchValues.remove("level");
            }
            for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {

                if (entry.getKey().toString().contains("_Date")) {

                    final Date forecastsDate = new Date();
                    forecastsDate.setTime(((Date) entry.getValue()).getTime());
                    forecastsDate.setHours(NumericConstants.TWENTY_THREE);
                    forecastsDate.setMinutes(NumericConstants.FIFTY_NINE);
                    forecastsDate.setSeconds(NumericConstants.FIFTY_NINE);
                    dynamicQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                } else if (entry.getKey().toString().contains("_boolean")) {

                    boolean flag = false;
                    if (NumericConstants.ONE == Integer.valueOf(entry.getValue().toString())) {

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
                    } else if (flag) {
                        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                        dynamicQuery.add(RestrictionsFactoryUtil.or(c1, c2));
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

            LOGGER.debug(" Ends getDynamicQuerySearch with dynamicQuery");

            Class cls;
            if ((tableName.equals("VwCustomerGtsForecast")) || (tableName.equals("IvldCustomerGtsForecast"))
                    || tableName.equals("VwAdjustDemandForecastAct") || ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW.equals(tableName)
                    || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                    || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName)
                    || tableName.equals(ConstantUtil.IBID_ITEM_MASTER) || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVID_ITEM_PRICING)
                    || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                    || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
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
        LOGGER.debug(" Ends hereee...getDynamicQuerySearch..");
        return count;

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

                    } else if ("isOutliner".equals(stringFilter.getPropertyId())) {

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
                    } else if ("type".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.CFF_TYPE);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionInclusion".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "LOCKED_STATUS");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_CATEGORY");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionType".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionProgram".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.CFF_TYPE);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory1".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory2".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory3".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory4".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));

                    } else if ("deductionCategory5".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionCategory6".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("contractType".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "CONTRACT_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("adjustmentLevel".equals(stringFilter.getPropertyId())) {
                        String tmpValue = StringUtils.EMPTY;
                        if(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)){
                            tmpValue = getHelperValues(filterString, "ARM_GTN_ADJUSTMENT_LEVEL");
                        } else if(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)){
                            tmpValue = getHelperValues(filterString, "ARM_RES_ADJUSTMENT_LEVEL");
                        }
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("accountCategory".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ACCOUNT_CATEGORY");
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey." +String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("accountType".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_ACCOUNT_TYPE");
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey." +String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ((ConstantUtil.ACCOUNT.equals(stringFilter.getPropertyId()) || "workflowId".equals(stringFilter.getPropertyId())
                            || "deductionId".equals(stringFilter.getPropertyId()) || "itemId".equals(stringFilter.getPropertyId()) || "companyId".equals(stringFilter.getPropertyId())
                            || "contractId".equals(stringFilter.getPropertyId()) || "glCompanyId".equals(stringFilter.getPropertyId())
                            || "businessUnitId".equals(stringFilter.getPropertyId())) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey."+String.valueOf(stringFilter.getPropertyId()), filterString));                        
                    } else if ("adjustmentType".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getAdjustmentTypeValues(filterString, "ST_ADJUSTMENT_RESERVE_DETAIL");
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey."+String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("adjustmentType".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName))) {
                        String tmpValue = getAdjustmentTypeValues(filterString, "ST_ADJUSTMENT_GTN_DETAIL");
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey."+String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    }  else if ("deductionProgram".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, ConstantUtil.REBATE_PROGRAM_TYPE);
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    }else if ("deductionUdc1".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC1");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionUdc2".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC2");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionUdc3".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC3");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionUdc4".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC4");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionUdc5".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC5");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("deductionUdc6".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "RS_UDC6");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc1".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_1");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc2".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_2");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc3".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_3");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc4".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_4");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc5".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_5");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ("udc6".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))) {
                        String tmpValue = getHelperValues(filterString, "ARM_UDC_6");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else if ((ConstantUtil.ACCOUNT.equals(stringFilter.getPropertyId()) || "workflowId".equals(stringFilter.getPropertyId())
                            || "businessUnitId".equals(stringFilter.getPropertyId()) 
                            || "brand".equals(stringFilter.getPropertyId())) && ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey."+String.valueOf(stringFilter.getPropertyId()), filterString));                        
                    }else if("glCompanyName".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)){
                        forecastQuery.add(RestrictionsFactoryUtil.like("primaryKey.businessUnitId", filterString)); 
                    } else if ("currency".equals(stringFilter.getPropertyId()) && ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                        String tmpValue = getHelperValues(filterString, "CURRENCY");
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), tmpValue));
                    } else {
                        forecastQuery.add(RestrictionsFactoryUtil.like(String.valueOf(stringFilter.getPropertyId()), filterString));
                    }
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date filterString = (Date) stringFilter.getStartValue();
                    Date filterString1 = (Date) stringFilter.getEndValue();
                    if("redemptionPeriod".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))){
                        forecastQuery.add(RestrictionsFactoryUtil.ge("primaryKey." +String.valueOf(stringFilter.getPropertyId()), filterString));
                        forecastQuery.add(RestrictionsFactoryUtil.lt("primaryKey." +String.valueOf(stringFilter.getPropertyId()), filterString1));
                    } else {
                    forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                    forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), filterString1));
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    if (stringFilter.getValue() instanceof Date) {
                        Date filterString = (Date) stringFilter.getValue();
                        if (stringFilter.getOperation().equals(stringFilter.getOperation().GREATER_OR_EQUAL)) {
                            if("redemptionPeriod".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))){
                                forecastQuery.add(RestrictionsFactoryUtil.ge("primaryKey."+String.valueOf(stringFilter.getPropertyId()), filterString));
                            } else {
                            forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), filterString));
                            }                            
                        } else if (stringFilter.getOperation().equals(stringFilter.getOperation().LESS_OR_EQUAL)) {
                            if("redemptionPeriod".equals(stringFilter.getPropertyId()) && (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName))){
                                forecastQuery.add(RestrictionsFactoryUtil.le("primaryKey."+String.valueOf(stringFilter.getPropertyId()), filterString));
                            } else {
                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), filterString));
                        }
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
                                    if (tempValues.length > NumericConstants.ONE && tempValues[NumericConstants.ONE].length() <= NumericConstants.THREE) {
                                        Double[] arrValue = getGreaterLesserVal(value);
                                        if (tempValues[NumericConstants.ONE].length() == NumericConstants.THREE) {
                                            forecastQuery.add(RestrictionsFactoryUtil.le(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        } else {
                                            forecastQuery.add(RestrictionsFactoryUtil.lt(String.valueOf(stringFilter.getPropertyId()), arrValue[0]));
                                        }
                                        forecastQuery.add(RestrictionsFactoryUtil.ge(String.valueOf(stringFilter.getPropertyId()), arrValue[NumericConstants.ONE]));
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
        int length = tempValues[NumericConstants.ONE].length();
        Double adjustValue;
        Double minValue;
        Double maxValue;
        if (length >= NumericConstants.THREE) {
            adjustValue = 1 / Math.pow(NumericConstants.TEN, (length) + 1) * NumericConstants.FIVE;
            minValue = Double.valueOf(f.format(Double.parseDouble(value) - adjustValue));
            maxValue = Double.valueOf(f.format(Double.parseDouble(value) + adjustValue));
        } else {
            adjustValue = 1 / Math.pow(NumericConstants.TEN, length);
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
            final Set<Container.Filter> filterSet, String tableName, boolean excelFlag, Object[] excelVisibleColumnArr, Map SelectedAccrualsIdMap, final DetailsDTO primaryDTO) throws PortalException, SystemException{
        LOGGER.debug("Entering searchForecastSales with start value  :::: " + start + ",  and end value  :::: " + end + "  and orderByColumns size is   :::: "
                + ((orderByColumns == null) ? orderByColumns : orderByColumns.size()));
        List<Object> list = null;
        try{
        if (!excelFlag && (tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL))) {
            adjustmentDetailsSearchRestriction(tableName, searchValues);
            }

        
        String tempValue = StringUtils.EMPTY;
        DecimalFormat decimalformat = new DecimalFormat("$#0.00");
        DecimalFormat decimalformatper = new DecimalFormat("#0.00");
        DecimalFormat alphanumeric = new DecimalFormat("#,###");
        List<String> gtnPrimaryKeyList = new ArrayList<>(Arrays.asList(ConstantUtil.ACCOUNT, "adjustmentType", "accountType", "workflowId", "accountCategory", "deductionId", "itemId", "companyId", "contractId","redemptionPeriod","glCompanyId","businessUnitId"));
        List<String> reserveDetailsPrimaryKeyList = new ArrayList<>(Arrays.asList("businessUnitId", ConstantUtil.ACCOUNT, "adjustmentType", "accountType","workflowId", "accountCategory", "redemptionPeriod", "glCompanyName", "brand"));
        if (!ConstantUtil.ARP_OUTBOUND.equals(tableName)) {

            DynamicQuery forecastQuery;
            tableName = tableName.trim();
            if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER)) 
                    || (tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW)) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.equals(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)
                    || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || tableName.equals("IvldCompanyMaster") || tableName.equals("IvldCompanyIdentifier") || tableName.equals("IvldCompanyParent") 
                    || tableName.equals("IvldCompanyTradeClass")|| tableName.equals(ConstantUtil.IBID_ITEM_MASTER) || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVID_ITEM_PRICING) 
                    || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) 
                    || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                    || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                forecastQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH_TWO + tableName));
            } else {
                forecastQuery = DynamicQueryFactoryUtil.forClass(Class.forName(ConstantUtil.TABLE_MODEL_PATH + tableName));
            }
            if (excelFlag) {
                ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
                for (Object columnList1 : excelVisibleColumnArr) {
                    if (!(ConstantUtil.CHECK_RECORD.equals(columnList1.toString()))) {
                        if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                            if (gtnPrimaryKeyList.contains(columnList1.toString())) {
                                projectonList.add(ProjectionFactoryUtil.property("primaryKey." + columnList1.toString()));
                            } else {
                                projectonList.add(ProjectionFactoryUtil.property(columnList1.toString()));
                            }

                        } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                            if (reserveDetailsPrimaryKeyList.contains(columnList1.toString())) {
                                projectonList.add(ProjectionFactoryUtil.property("primaryKey." + columnList1.toString()));
                            } else {
                                projectonList.add(ProjectionFactoryUtil.property(columnList1.toString()));
                            }

                        } else {
                            if (!(ConstantUtil.CHECK_RECORD.equals(columnList1.toString()) && ConstantUtil.ST_CFF_OUTBOUND.equals(tableName))) {
                                if (columnList1.toString().equals("glCompanyName") && tableName.equals("AccrualDetails")) {
                                    projectonList.add(ProjectionFactoryUtil.property("companyId"));
                                } else {
                                    projectonList.add(ProjectionFactoryUtil.property(columnList1.toString()));
                                }

                            }
                        }
                        forecastQuery.setProjection(projectonList);
                    }
                }
            }
            for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
                if (entry.getKey().toString().contains("_Date")) {
                    if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY)) || "invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                        if (invalidToDate != null && invalidFromDate != null) {
                            final Date invalidDate = new Date();
                            invalidDate.setTime(((Date) invalidToDate).getTime());
                            invalidDate.setHours(NumericConstants.TWENTY_THREE);
                            invalidDate.setMinutes(NumericConstants.FIFTY_NINE);
                            invalidDate.setSeconds(NumericConstants.FIFTY_NINE);
                            forecastQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
                        } else if ("invalidToDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                            forecastQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
                        } else if ("invalidFromDate".equals(entry.getKey().toString().replace("_Date", StringUtils.EMPTY))) {
                            forecastQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE) || searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                        if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.between("financialForecastCreationDate", searchValues.get(ConstantUtil.CFF_CREATION_FROM_DATE), searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.ge("financialForecastCreationDate", entry.getValue()));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.le("financialForecastCreationDate", searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE) || searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
                        if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.between("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE), searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.gt("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE)));
                        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
                            forecastQuery.add(RestrictionsFactoryUtil.lt("financialForecastApprovalDate", searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE)));
                        }
                    } else if (searchValues.containsKey(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY) || searchValues.containsKey(ConstantUtil.REDEMPTION_FROM_DATE) || searchValues.containsKey(ConstantUtil.REDEMPTION_TO_DATE)) {
                        SimpleDateFormat format = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
                        if (searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY) != null) {
                            Date reserveFromDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE_PRIMARY_KEY)));
                            if (searchValues.get(ConstantUtil.REDEMPTION_TO_DATE) != null) {
                                Date reserveToDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_TO_DATE)));
                                forecastQuery.add(RestrictionsFactoryUtil.between("primaryKey.redemptionPeriod", reserveFromDate, reserveToDate));
                    } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt("primaryKey.redemptionPeriod", reserveFromDate));
                            }
                        } else if (searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE) != null) {
                            Date reserveFromDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_FROM_DATE)));
                            if (searchValues.get(ConstantUtil.REDEMPTION_TO_DATE) != null) {
                                Date reserveToDate = format.parse(String.valueOf(searchValues.get(ConstantUtil.REDEMPTION_TO_DATE)));
                                forecastQuery.add(RestrictionsFactoryUtil.between("redemptionPeriod", reserveFromDate, reserveToDate));
                            } else {
                                forecastQuery.add(RestrictionsFactoryUtil.gt("redemptionPeriod", reserveFromDate));
                            }
                        }
                    } else {
                        final Date forecastsDate = new Date();
                        forecastsDate.setTime(((Date) entry.getValue()).getTime());
                        forecastsDate.setHours(NumericConstants.TWENTY_THREE);
                        forecastsDate.setMinutes(NumericConstants.FIFTY_NINE);
                        forecastsDate.setSeconds(NumericConstants.FIFTY_NINE);
                        forecastQuery.add(RestrictionsFactoryUtil.between(entry.getKey().toString().replace("_Date", StringUtils.EMPTY), entry.getValue(), forecastsDate));
                    }
                } else if (entry.getKey().toString().contains("_boolean")) {
                    boolean flag = false;
                    if (NumericConstants.ONE == Integer.valueOf(entry.getValue().toString())) {
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
                    } else if (flag) {
                        forecastQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, flag));
                    } else {
                        Criterion c1 = RestrictionsFactoryUtil.eq(ConstantUtil.IS_ACTIVE, false);
                        Criterion c2 = RestrictionsFactoryUtil.isNull(ConstantUtil.IS_ACTIVE);
                        forecastQuery.add(RestrictionsFactoryUtil.or(c1, c2));
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
                } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName.trim()) && entry.getKey().toString().trim().equals(ConstantUtil.TYPE)) {
                    listName = ConstantUtil.CFF_TYPE;
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    forecastQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
                } else if ("CpiIndexMaster".equals(tableName.trim())
                        && (entry.getKey().toString().trim().equals(ConstantUtil.STATUS))) {
                    if (entry.getKey().toString().equalsIgnoreCase(ConstantUtil.STATUS)) {
                        listName = ConstantUtil.STATUS_DUP;
                    }
                    String tmpValue = getHelperValues(entry.getValue().toString(), listName);
                    forecastQuery.add(RestrictionsFactoryUtil.like(entry.getKey().toString(), tmpValue));
                } else {
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
            if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                forecastQuery.add(RestrictionsFactoryUtil.eq("primaryKey." + ConstantUtil.USERID, String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID))));
                forecastQuery.add(RestrictionsFactoryUtil.eq("primaryKey." + ConstantUtil.SESSIONID, String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID))));

            }
            forecastQuery.setLimit(start, end);
            if (orderByColumns == null || orderByColumns.isEmpty()) {
                //Added to avoid duplication in Excel export.
                if (StringUtils.isEmpty(primaryDTO.getInvalidTableName())) {
                    if (ConstantUtil.ADJUSTED_DEMAND_VIEW.equals(tableName)) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("adjustedDemandForecastId"));
                    } else if (ConstantUtil.DEMANDVIEW_TABLE.equals(tableName.trim())) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("demandForecastActualSid"));
                    } else if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName.trim()) && !excelFlag) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("financialForecastId"));
                    } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName.trim())) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("primaryKey." + primaryDTO.getPropertyName()));
                    } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName.trim())) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("primaryKey." + primaryDTO.getPropertyName()));
                    } else if (ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName.trim()) && excelFlag) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("customerGtsForecastSid"));
                    } else if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName.trim())) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc("accrualDetailsSid"));
                    }else {
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
                    String propertyName=orderByColumn.getName();
                    if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName.trim()) && gtnPrimaryKeyList.contains(orderByColumn.getName())) {
                            propertyName="primaryKey." +orderByColumn.getName();
                    }  
                    if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName.trim()) && reserveDetailsPrimaryKeyList.contains(orderByColumn.getName())) {
                            propertyName="primaryKey." +orderByColumn.getName();
                    }  
                    
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        forecastQuery.addOrder(OrderFactoryUtil.asc(propertyName));
                    } else {
                        forecastQuery.addOrder(OrderFactoryUtil.desc(propertyName));
                    }
                }
            }
            Class cls;
            if ((tableName.equals(ConstantUtil.IVLD_ADJUSTED_DEMAND_VIEW)) || (tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) || (tableName.equals(ConstantUtil.INVALID_GTS_CUSTOMER))
                    || tableName.equals(ConstantUtil.ADJUSTED_DEMAND_VIEW) || tableName.equals(ConstantUtil.CUSTOMER_GTS_ACTUAL)
                    || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName) || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName) || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName) || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName) || tableName.equals(ConstantUtil.IBID_ITEM_MASTER) || tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER) || tableName.equals(ConstantUtil.IVID_ITEM_PRICING)
                    || ConstantUtil.ST_CFF_OUTBOUND.equals(tableName) || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName) || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                    || tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL) || tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH_TWO + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            } else {
                cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            }

            Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC, DynamicQuery.class);
            list = (List<Object>) method.invoke(cls.newInstance(), forecastQuery);
        } else {
            list = searchFindForArpOutBound(searchValues, start, end, null, null, true,true);

        }

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
            } else if (ConstantUtil.IVLD_DEMANDVIEW_TABLE.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    VwIvldDemandForecastActual ob = (VwIvldDemandForecastActual) list.get(i);
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
                    ob.setDeductionCategory((!ob.getDeductionCategory().isEmpty() && !"0".equals(ob.getDeductionCategory())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getDeductionCategory())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionProgramType((!ob.getDeductionProgramType().isEmpty() && !"0".equals(ob.getDeductionProgramType())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getDeductionProgramType())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionType((!ob.getDeductionType().isEmpty() && !"0".equals(ob.getDeductionType())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getDeductionType())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc1((!ob.getUdc1().isEmpty() && !"0".equals(ob.getUdc1())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc1())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc2((!ob.getUdc2().isEmpty() && !"0".equals(ob.getUdc2())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc2())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc3((!ob.getUdc3().isEmpty() && !"0".equals(ob.getUdc3())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc3())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc4((!ob.getUdc4().isEmpty() && !"0".equals(ob.getUdc4())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc4())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc5((!ob.getUdc5().isEmpty() && !"0".equals(ob.getUdc5())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc5())).getDescription() : StringUtils.EMPTY);
                    ob.setUdc6((!ob.getUdc6().isEmpty() && !"0".equals(ob.getUdc6())) ? helper.getHelperDTObyID(Integer.valueOf(ob.getUdc6())).getDescription() : StringUtils.EMPTY);

                }
            }
            if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    StCffOutboundMaster ob = (StCffOutboundMaster) list.get(i);
                    ob.setType((!ob.getType().isEmpty() && !"0".equals(ob.getType())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getType())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionInclusion((!ob.getDeductionInclusion().isEmpty() && !"0".equals(ob.getDeductionInclusion())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionInclusion())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory((!ob.getDeductionCategory().isEmpty() && !"0".equals(ob.getDeductionCategory())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionType((!ob.getDeductionType().isEmpty() && !"0".equals(ob.getDeductionType())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionType())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionProgram((!ob.getDeductionProgram().isEmpty() && !"0".equals(ob.getDeductionProgram())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionProgram())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory1((!ob.getDeductionCategory1().isEmpty() && !"0".equals(ob.getDeductionCategory1())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory1())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory2((!ob.getDeductionCategory2().isEmpty() && !"0".equals(ob.getDeductionCategory2())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory2())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory3((!ob.getDeductionCategory3().isEmpty() && !"0".equals(ob.getDeductionCategory3())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory3())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory4((!ob.getDeductionCategory4().isEmpty() && !"0".equals(ob.getDeductionCategory4())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory4())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory5((!ob.getDeductionCategory5().isEmpty() && !"0".equals(ob.getDeductionCategory5())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory5())).getDescription() : StringUtils.EMPTY);
                    ob.setDeductionCategory6((!ob.getDeductionCategory6().isEmpty() && !"0".equals(ob.getDeductionCategory6())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getDeductionCategory6())).getDescription() : StringUtils.EMPTY);
                    ob.setContractType((!ob.getContractType().isEmpty() && !"0".equals(ob.getContractType())) ? helper.getHelperDTObyID(Integer.parseInt(ob.getContractType())).getDescription() : StringUtils.EMPTY);
                    String outboundStatus = ob.getOutboundStatus();
                    if (outboundStatus.equalsIgnoreCase("yes")) {
                        outboundStatus = "Y";
                    }
                    ob.setOutboundStatus((StringUtils.isBlank(outboundStatus)) || outboundStatus == null || "null".equals(outboundStatus) ? "N" : String.valueOf(outboundStatus));
                }
            }
            if (ConstantUtil.RETURN_RESERVE.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    VwReturnReserve ob = (VwReturnReserve) list.get(i);
                    Map<Integer, String> userMap = StplSecurity.userMap;
                    if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()
                            && ob.getModifiedBy().matches("\\-?\\d+")) {
                        ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                    }
                    if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()
                            && ob.getCreatedBy().matches("\\-?\\d+")) {
                        ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                    }
                    if (!"null".equals(ob.getAmount()) && !"".equals(ob.getAmount())) {
                        ob.setAmount(decimalformat.format(Double.valueOf(ob.getAmount())));
                    } else {
                        ob.setAmount("");
                }
                    if (!"null".equals(ob.getUnits()) && !"".equals(ob.getUnits())) {
                        ob.setUnits(decimalformatper.format(Double.valueOf(ob.getUnits())));
                    } else {
                        ob.setUnits("");
            }

                }
            }
            if (ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    VwIvldReturnReserve ob = (VwIvldReturnReserve) list.get(i);
                    Map<Integer, String> userMap = StplSecurity.userMap;
                    if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()
                            && ob.getModifiedBy().matches("\\-?\\d+")) {
                        ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                    }
                    if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()
                            && ob.getCreatedBy().matches("\\-?\\d+")) {
                        ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                    }
                    if (!"null".equals(ob.getAmount()) && !"".equals(ob.getAmount())) {
                        ob.setAmount(decimalformat.format(Double.valueOf(ob.getAmount())));
                    } else {
                        ob.setAmount("");
                }
                    if (!"null".equals(ob.getUnits()) && !"".equals(ob.getUnits())) {
                        if (ob.getUnits().matches("^[1-9]\\d*(\\.\\d+)?$")) {
                            ob.setUnits(decimalformatper.format(Double.valueOf(ob.getUnits())));
            }
                    } else {
                        ob.setUnits("");
                    }
                }
            }

            if (tableName.equals("IvldCompanyIdentifier")) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldCompanyIdentifier ob = (IvldCompanyIdentifier) list.get(i);
                        if (ob.getEndDate() != null && !(ob.getEndDate().equals("null")) && !(ob.getEndDate().equals(""))) {
                            ob.setEndDate(df2.format(parseDate(ob.getEndDate())));
                        }
                        if (ob.getStartDate() != null && !(ob.getStartDate().equals("null")) && !(ob.getStartDate().equals(""))) {
                            ob.setStartDate(df2.format(parseDate(ob.getStartDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals("IvldCompanyParent")) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldCompanyParent ob = (IvldCompanyParent) list.get(i);
                        if (ob.getParentStartDate() != null && !(ob.getParentStartDate().equals("null")) && !(ob.getParentStartDate().equals(""))) {
                            ob.setParentStartDate(df2.format(parseDate(ob.getParentStartDate())));
                        }
                        if (ob.getParentEndDate() != null && !(ob.getParentEndDate().equals("null")) && !(ob.getParentEndDate().equals(""))) {
                            ob.setParentEndDate(df2.format(parseDate(ob.getParentEndDate())));
                        }
                        if (ob.getPriorParentStartDate() != null && !(ob.getPriorParentStartDate().equals("null")) && !(ob.getPriorParentStartDate().equals(""))) {
                            ob.setPriorParentStartDate(df2.format(parseDate(ob.getParentEndDate())));
                        }
                        if (ob.getLastUpdatedDate() != null && !(ob.getLastUpdatedDate().equals("null")) && !(ob.getLastUpdatedDate().equals(""))) {
                            ob.setLastUpdatedDate(df2.format(parseDate(ob.getLastUpdatedDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals("IvldCompanyMaster")) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldCompanyMaster ob = (IvldCompanyMaster) list.get(i);
                        if (ob.getCompanyStartDate() != null && !(ob.getCompanyStartDate().equals("null")) && !(ob.getCompanyStartDate().equals(""))) {
                            ob.setCompanyStartDate(df2.format(parseDate(ob.getCompanyStartDate())));
                        }
                        if (ob.getCompanyEndDate() != null && !(ob.getCompanyEndDate().equals("null")) && !(ob.getCompanyEndDate().equals(""))) {
                            ob.setCompanyEndDate(df2.format(parseDate(ob.getCompanyEndDate())));
                        }
                        if (ob.getLastUpdatedDate() != null && !(ob.getLastUpdatedDate().equals("null")) && !(ob.getLastUpdatedDate().equals(""))) {
                            ob.setLastUpdatedDate(df2.format(parseDate(ob.getLastUpdatedDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals("IvldCompanyTradeClass")) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldCompanyTradeClass ob = (IvldCompanyTradeClass) list.get(i);
                        if (ob.getTradeClassStartDate() != null && !(ob.getTradeClassStartDate().equals("null")) && !(ob.getTradeClassStartDate().equals(""))) {
                            ob.setTradeClassStartDate(df2.format(parseDate(ob.getTradeClassStartDate())));
                        }
                        if (ob.getTradeClassEndDate() != null && !(ob.getTradeClassEndDate().equals("null")) && !(ob.getTradeClassEndDate().equals(""))) {
                            ob.setTradeClassEndDate(df2.format(parseDate(ob.getTradeClassEndDate())));
                        }
                        if (ob.getPriorTradeClassStartDate() != null && !(ob.getPriorTradeClassStartDate().equals("null")) && !(ob.getPriorTradeClassStartDate().equals(""))) {
                            ob.setPriorTradeClassStartDate(df2.format(parseDate(ob.getPriorTradeClassStartDate())));
                        }
                        if (ob.getLastUpdatedDate() != null && !(ob.getLastUpdatedDate().equals("null")) && !(ob.getLastUpdatedDate().equals(""))) {
                            ob.setLastUpdatedDate(df2.format(parseDate(ob.getLastUpdatedDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals(ConstantUtil.IBID_ITEM_MASTER)) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldItemMaster ob = (IvldItemMaster) list.get(i);
                        if (ob.getAcquisitionDate() != null && !(ob.getAcquisitionDate().equals("null")) && !(ob.getAcquisitionDate().equals(""))) {
                            ob.setAcquisitionDate(df2.format(parseDate(ob.getAcquisitionDate())));
                        }
                        if (ob.getAuthorizedGenericEndDate() != null && !(ob.getAuthorizedGenericEndDate().equals("null")) && !(ob.getAuthorizedGenericEndDate().equals(""))) {
                            ob.setAuthorizedGenericEndDate(df2.format(parseDate(ob.getAuthorizedGenericEndDate())));
                        }
                        if (ob.getAuthorizedGenericStartDate() != null && !(ob.getAuthorizedGenericStartDate().equals("null")) && !(ob.getAuthorizedGenericStartDate().equals(""))) {
                            ob.setAuthorizedGenericStartDate(df2.format(parseDate(ob.getAuthorizedGenericStartDate())));
                        }
                        if (ob.getClottingFactorEndDate() != null && !(ob.getClottingFactorEndDate().equals("null")) && !(ob.getClottingFactorEndDate().equals(""))) {
                            ob.setClottingFactorEndDate(df2.format(parseDate(ob.getClottingFactorEndDate())));
                        }
                        if (ob.getClottingFactorStartDate() != null && !(ob.getClottingFactorStartDate().equals("null")) && !(ob.getClottingFactorStartDate().equals(""))) {
                            ob.setClottingFactorStartDate(df2.format(parseDate(ob.getClottingFactorStartDate())));
                        }
                        if (ob.getDiscontinuationDate() != null && !(ob.getDiscontinuationDate().equals("null")) && !(ob.getDiscontinuationDate().equals(""))) {
                            ob.setDiscontinuationDate(df2.format(parseDate(ob.getDiscontinuationDate())));
                        }
                        if (ob.getDivestitureDate() != null && !(ob.getDivestitureDate().equals("null")) && !(ob.getDivestitureDate().equals(""))) {
                            ob.setDivestitureDate(df2.format(parseDate(ob.getDivestitureDate())));
                        }
                        if (ob.getFirstSaleDate() != null && !(ob.getFirstSaleDate().equals("null")) && !(ob.getFirstSaleDate().equals(""))) {
                            ob.setFirstSaleDate(df2.format(parseDate(ob.getFirstSaleDate())));
                        }
                        if (ob.getItemEndDate() != null && !(ob.getItemEndDate().equals("null")) && !(ob.getItemEndDate().equals(""))) {
                            ob.setItemEndDate(df2.format(parseDate(ob.getItemEndDate())));
                        }
                        if (ob.getItemStartDate() != null && !(ob.getItemStartDate().equals("null")) && !(ob.getItemStartDate().equals(""))) {
                            ob.setItemStartDate(df2.format(parseDate(ob.getItemStartDate())));
                        }
                        if (ob.getLastLotExpirationDate() != null && !(ob.getLastLotExpirationDate().equals("null")) && !(ob.getLastLotExpirationDate().equals(""))) {
                            ob.setLastLotExpirationDate(df2.format(parseDate(ob.getLastLotExpirationDate())));
                        }
                        if (ob.getMarketTerminationDate() != null && !(ob.getMarketTerminationDate().equals("null")) && !(ob.getMarketTerminationDate().equals(""))) {
                            ob.setMarketTerminationDate(df2.format(parseDate(ob.getMarketTerminationDate())));
                        }
                        if (ob.getNewFormulationEndDate() != null && !(ob.getNewFormulationEndDate().equals("null")) && !(ob.getNewFormulationEndDate().equals(""))) {
                            ob.setNewFormulationEndDate(df2.format(parseDate(ob.getNewFormulationEndDate())));
                        }
                        if (ob.getNewFormulationStartDate() != null && !(ob.getNewFormulationStartDate().equals("null")) && !(ob.getNewFormulationStartDate().equals(""))) {
                            ob.setNewFormulationStartDate(df2.format(parseDate(ob.getNewFormulationStartDate())));
                        }
                        if (ob.getNonFederalExpirationDate() != null && !(ob.getNonFederalExpirationDate().equals("null")) && !(ob.getNonFederalExpirationDate().equals(""))) {
                            ob.setNonFederalExpirationDate(df2.format(parseDate(ob.getNonFederalExpirationDate())));
                        }
                        if (ob.getPackageSizeIntroDate() != null && !(ob.getPackageSizeIntroDate().equals("null")) && !(ob.getPackageSizeIntroDate().equals(""))) {
                            ob.setPackageSizeIntroDate(df2.format(parseDate(ob.getPackageSizeIntroDate())));
                        }
                        if (ob.getPediatricExclusiveEndDate() != null && !(ob.getPediatricExclusiveEndDate().equals("null")) && !(ob.getPediatricExclusiveEndDate().equals(""))) {
                            ob.setPediatricExclusiveEndDate(df2.format(parseDate(ob.getPediatricExclusiveEndDate())));
                        }
                        if (ob.getPediatricExclusiveStartDate() != null && !(ob.getPediatricExclusiveStartDate().equals("null")) && !(ob.getPediatricExclusiveStartDate().equals(""))) {
                            ob.setPediatricExclusiveStartDate(df2.format(parseDate(ob.getPediatricExclusiveStartDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals(ConstantUtil.IVID_ITEM_IDENTIFIER)) {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                        df.setLenient(false);
                        IvldItemIdentifier ob = (IvldItemIdentifier) list.get(i);
                        if (ob.getEndDate() != null && !(ob.getEndDate().equals("null")) && !(ob.getEndDate().equals(""))) {
                            ob.setEndDate(df2.format(parseDate(ob.getEndDate())));
                        }
                        if (ob.getStartDate() != null && !(ob.getStartDate().equals("null")) && !(ob.getStartDate().equals(""))) {
                            ob.setStartDate(df2.format(parseDate(ob.getStartDate())));
                        }
                    } catch (Exception e) {
                        LOGGER.error("Un parseable Date" + e);
                    }
                }
            }

            if (tableName.equals(ConstantUtil.IVID_ITEM_PRICING)) {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                            DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                            df.setLenient(false);
                            IvldItemPricing ob = (IvldItemPricing) list.get(i);
                            if (ob.getEndDate() != null && !(ob.getEndDate().equals("null")) && !(ob.getEndDate().equals(""))) {
                                ob.setEndDate(df2.format(parseDate(ob.getEndDate())));
                            }
                            if (ob.getStartDate() != null && !(ob.getStartDate().equals("null")) && !(ob.getStartDate().equals(""))) {
                                ob.setStartDate(df2.format(parseDate(ob.getStartDate())));
                            }
                            if (ob.getItemPrice() != null && !(ob.getItemPrice().equals("null")) && !(ob.getItemPrice().equals(""))) {

                                ob.setItemPrice(decimalformat.format(Double.valueOf(ob.getItemPrice())));
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            if (tableName.equals("VwItemPricing")) {
                for (int i = 0; i < list.size(); i++) {
                    DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                    df.setLenient(false);
                    VwItemPricing ob = (VwItemPricing) list.get(i);
                    ob.setItemPrice(decimalformat.format(Double.valueOf(ob.getItemPrice())));
                }
            }

            if (tableName.equals("VwItemMaster")) {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        try {
                            VwItemMaster ob = (VwItemMaster) list.get(i);
                            if (!"null".equals(ob.getAcquiredBamp()) && !"".equals(ob.getAcquiredBamp()) && ob.getAcquiredBamp() != null) {
                                ob.setAcquiredBamp(decimalformat.format(Double.valueOf(ob.getAcquiredBamp())));
                            } else {
                                ob.setAcquiredBamp("");
                            }
                            if (!"null".equals(ob.getAcquiredAmp()) && !"".equals(ob.getAcquiredAmp()) && ob.getAcquiredAmp() != null) {
                                ob.setAcquiredAmp(decimalformat.format(Double.valueOf(ob.getAcquiredAmp())));
                            } else {
                                ob.setAcquiredAmp("");
                            }
                            if (!"null".equals(ob.getObraBamp()) && !"".equals(ob.getObraBamp()) && ob.getObraBamp() != null) {
                                ob.setObraBamp(decimalformat.format(Double.valueOf(ob.getObraBamp())));
                            } else {
                                ob.setObraBamp("");
                            }
                            if (!"null".equals(ob.getBaselineAmp()) && !"".equals(ob.getBaselineAmp()) && ob.getBaselineAmp() != null) {
                                ob.setBaselineAmp(decimalformat.format(Double.valueOf(ob.getBaselineAmp())));
                            } else {
                                ob.setBaselineAmp("");
                            }
                            if (!"null".equals(ob.getBaseCpi()) && !"".equals(ob.getBaseCpi()) && ob.getBaseCpi() != null) {
                                ob.setBaseCpi(decimalformatper.format(Double.valueOf(ob.getBaseCpi())) + "%");
                            } else {
                                ob.setBaseCpi("");
                            }
                            if (!"null".equals(ob.getDra()) && !"".equals(ob.getDra()) && ob.getDra() != null) {
                                ob.setDra(alphanumeric.format(Double.valueOf(ob.getDra())));
                            } else {
                                ob.setDra("");
                            }
                        } catch (Exception e) {
                           LOGGER.error(e);
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
            if ("CpiIndexMaster".equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    CpiIndexMaster ob = (CpiIndexMaster) list.get(i);
                    ob.setStatus(CommonLogic.getDescription(!StringUtils.EMPTY.equalsIgnoreCase(ob.getStatus())?Integer.valueOf(ob.getStatus()):0));
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

            if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName) || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                try {
                    Map<Integer, String> userMap = new ConcurrentHashMap<>();
                    DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                    List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                    for (User user : userList) {
                        userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                    }
                    
                    if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                        
                        for (int i = 0; i < list.size(); i++) {
                            StAdjustmentReserveDetail ob = (StAdjustmentReserveDetail) list.get(i);
                            
                            ob.setUdc1((!ob.getUdc1().isEmpty() && !"0".equals(ob.getUdc1())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc1())) : StringUtils.EMPTY);
                            ob.setUdc2((!ob.getUdc2().isEmpty() && !"0".equals(ob.getUdc2())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc2())) : StringUtils.EMPTY);
                            ob.setUdc3((!ob.getUdc3().isEmpty() && !"0".equals(ob.getUdc3())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc3())) : StringUtils.EMPTY);
                            ob.setUdc4((!ob.getUdc4().isEmpty() && !"0".equals(ob.getUdc4())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc4())) : StringUtils.EMPTY);
                            ob.setUdc5((!ob.getUdc5().isEmpty() && !"0".equals(ob.getUdc5())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc5())) : StringUtils.EMPTY);
                            ob.setUdc6((!ob.getUdc6().isEmpty() && !"0".equals(ob.getUdc6())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc6())) : StringUtils.EMPTY);
                            ob.setCurrency((!ob.getCurrency().isEmpty() && !"0".equals(ob.getCurrency())) ? commonLogic.getDescription(Integer.valueOf(ob.getCurrency())) : StringUtils.EMPTY);
                            ob.setAdjustmentType((!ob.getAdjustmentType().isEmpty() && !"0".equals(ob.getAdjustmentType())) ? com.stpl.app.util.HelperListUtil.getInstance().getDescById(Integer.valueOf(ob.getAdjustmentType()), ConstantUtil.ADJUSTMENT_TYPE) : StringUtils.EMPTY);
                            ob.setAccountCategory((!ob.getAccountCategory().isEmpty() && !"0".equals(ob.getAccountCategory())) ? commonLogic.getDescription(Integer.valueOf(ob.getAccountCategory())) : StringUtils.EMPTY);
                            ob.setAccountType((!ob.getAccountType().isEmpty() && !"0".equals(ob.getAccountType())) ? commonLogic.getDescription(Integer.valueOf(ob.getAccountType())) : StringUtils.EMPTY);
                            ob.setAdjustmentLevel((!ob.getAdjustmentLevel().isEmpty() && !"0".equals(ob.getAdjustmentLevel())) ? commonLogic.getDescription(Integer.valueOf(ob.getAdjustmentLevel())) : StringUtils.EMPTY);
                            ob.setCredit((!ob.getCredit().isEmpty() && !ob.getCredit().equals(ConstantUtil.NULL) && ob.getCredit() != null) ? decimalformat.format(Double.valueOf(ob.getCredit())) : StringUtils.EMPTY);
                            ob.setDebit((!ob.getDebit().isEmpty() && !ob.getDebit().equals(ConstantUtil.NULL) && ob.getDebit() != null) ? decimalformat.format(Double.valueOf(ob.getDebit())) : StringUtils.EMPTY);
                            ob.setWorkflowCreatedBy((!ob.getWorkflowCreatedBy().isEmpty() && !ob.getWorkflowCreatedBy().equals(ConstantUtil.NULL) && ob.getWorkflowCreatedBy() != null && ob.getWorkflowCreatedBy().matches("\\-?\\d+")) ? userMap.get(Integer.valueOf(ob.getWorkflowCreatedBy())) : StringUtils.EMPTY);
                            ob.setWorkflowApprovedBy((!ob.getWorkflowApprovedBy().isEmpty() && !ob.getWorkflowApprovedBy().equals(ConstantUtil.NULL) && ob.getWorkflowApprovedBy() != null && ob.getWorkflowApprovedBy().matches("\\-?\\d+")) ? userMap.get(Integer.valueOf(ob.getWorkflowApprovedBy())) : StringUtils.EMPTY);
                        }
                    } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                        for (int i = 0; i < list.size(); i++) {
                            
                            StAdjustmentGtnDetail ob = (StAdjustmentGtnDetail) list.get(i);
                            
                            ob.setUdc1((!ob.getUdc1().isEmpty() && !"0".equals(ob.getUdc1())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc1())) : StringUtils.EMPTY);
                            ob.setUdc2((!ob.getUdc2().isEmpty() && !"0".equals(ob.getUdc2())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc2())) : StringUtils.EMPTY);
                            ob.setUdc3((!ob.getUdc3().isEmpty() && !"0".equals(ob.getUdc3())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc3())) : StringUtils.EMPTY);
                            ob.setUdc4((!ob.getUdc4().isEmpty() && !"0".equals(ob.getUdc4())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc4())) : StringUtils.EMPTY);
                            ob.setUdc5((!ob.getUdc5().isEmpty() && !"0".equals(ob.getUdc5())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc5())) : StringUtils.EMPTY);
                            ob.setUdc6((!ob.getUdc6().isEmpty() && !"0".equals(ob.getUdc6())) ? commonLogic.getDescription(Integer.valueOf(ob.getUdc6())) : StringUtils.EMPTY);
                            ob.setDeductionUdc1((!ob.getDeductionUdc1().isEmpty() && !"0".equals(ob.getDeductionUdc1())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc1())) : StringUtils.EMPTY);
                            ob.setDeductionUdc2((!ob.getDeductionUdc2().isEmpty() && !"0".equals(ob.getDeductionUdc2())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc2())) : StringUtils.EMPTY);
                            ob.setDeductionUdc3((!ob.getDeductionUdc3().isEmpty() && !"0".equals(ob.getDeductionUdc3())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc3())) : StringUtils.EMPTY);
                            ob.setDeductionUdc4((!ob.getDeductionUdc4().isEmpty() && !"0".equals(ob.getDeductionUdc4())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc4())) : StringUtils.EMPTY);
                            ob.setDeductionUdc5((!ob.getDeductionUdc5().isEmpty() && !"0".equals(ob.getDeductionUdc5())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc5())) : StringUtils.EMPTY);
                            ob.setDeductionUdc6((!ob.getDeductionUdc6().isEmpty() && !"0".equals(ob.getDeductionUdc6())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionUdc6())) : StringUtils.EMPTY);
                            ob.setDeductionAmount((!ob.getDeductionAmount().isEmpty() && !ob.getDeductionAmount().equals(ConstantUtil.NULL) && ob.getDeductionAmount() != null) ? decimalformat.format(Double.valueOf(ob.getDeductionAmount())) : StringUtils.EMPTY);
                            ob.setDeductionRate((!ob.getDeductionRate().isEmpty() && !ob.getDeductionRate().equals(ConstantUtil.NULL) && ob.getDeductionRate() != null) ? decimalformatper.format(Double.valueOf(ob.getDeductionRate())) : StringUtils.EMPTY);
                            ob.setDeductionCategory((!ob.getDeductionCategory().isEmpty() && !"0".equals(ob.getDeductionCategory())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionCategory())) : StringUtils.EMPTY);
                            ob.setDeductionType((!ob.getDeductionType().isEmpty() && !"0".equals(ob.getDeductionType())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionType())) : StringUtils.EMPTY);
                        ob.setAdjustmentType((!ob.getAdjustmentType().isEmpty() && !"0".equals(ob.getAdjustmentType())) ? com.stpl.app.util.HelperListUtil.getInstance().getDescById(Integer.valueOf(ob.getAdjustmentType()),ConstantUtil.ADJUSTMENT_TYPE)  : StringUtils.EMPTY);
                            ob.setAccountCategory((!ob.getAccountCategory().isEmpty() && !"0".equals(ob.getAccountCategory())) ? commonLogic.getDescription(Integer.valueOf(ob.getAccountCategory())) : StringUtils.EMPTY);
                            ob.setAccountType((!ob.getAccountType().isEmpty() && !"0".equals(ob.getAccountType())) ? commonLogic.getDescription(Integer.valueOf(ob.getAccountType())) : StringUtils.EMPTY);
                            ob.setAdjustmentLevel((!ob.getAdjustmentLevel().isEmpty() && !"0".equals(ob.getAdjustmentLevel())) ? commonLogic.getDescription(Integer.valueOf(ob.getAdjustmentLevel())) : StringUtils.EMPTY);
                            ob.setDeductionInclusion((!ob.getDeductionInclusion().isEmpty() && !"0".equals(ob.getDeductionInclusion())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionInclusion())) : StringUtils.EMPTY);
                            ob.setDeductionProgram((!ob.getDeductionProgram().isEmpty() && !"0".equals(ob.getDeductionProgram())) ? commonLogic.getDescription(Integer.valueOf(ob.getDeductionProgram())) : StringUtils.EMPTY);
                            ob.setWorkflowCreatedBy((!ob.getWorkflowCreatedBy().isEmpty() && !ob.getWorkflowCreatedBy().equals(ConstantUtil.NULL) && ob.getWorkflowCreatedBy() != null && ob.getWorkflowCreatedBy().matches("\\-?\\d+")) ? userMap.get(Integer.valueOf(ob.getWorkflowCreatedBy())) : StringUtils.EMPTY);
                            ob.setWorkflowApprovedBy((!ob.getWorkflowApprovedBy().isEmpty() && !ob.getWorkflowApprovedBy().equals(ConstantUtil.NULL) && ob.getWorkflowApprovedBy() != null && ob.getWorkflowApprovedBy().matches("\\-?\\d+")) ? userMap.get(Integer.valueOf(ob.getWorkflowApprovedBy())) : StringUtils.EMPTY);
                            
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.debug(ex);
                }
            }

            if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.contains(ConstantUtil.INVALID_GTS_CUSTOMER)
                    || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName) || ConstantUtil.VW_ITEM_PRICING.equals(tableName) || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName) || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName) || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                    || ConstantUtil.VW_COMPANY_MASTER.equals(tableName) || ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName)) {
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
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_COMPANY_MASTER)) {
                        VwCompanyMaster ob = (VwCompanyMaster) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_COMPANY_IDENTIFIER)) {
                        VwCompanyIdentifier ob = (VwCompanyIdentifier) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_COMPANY_PARENT_DETAILS)) {
                        VwCompanyParentDetails ob = (VwCompanyParentDetails) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_COMPANY_TRADE_CLASS)) {
                        VwCompanyTradeClass ob = (VwCompanyTradeClass) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_ITEM_MASTER)) {
                        VwItemMaster ob = (VwItemMaster) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_ITEM_IDENTIFIER)) {
                        VwItemIdentifier ob = (VwItemIdentifier) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.equalsIgnoreCase(ConstantUtil.VW_ITEM_PRICING)) {
                        VwItemPricing ob = (VwItemPricing) list.get(i);
                        if (!"null".equals(ob.getCreatedBy()) && ob.getCreatedBy() != null && !ob.getCreatedBy().isEmpty()) {
                            ob.setCreatedBy(userMap.get(Integer.valueOf(ob.getCreatedBy())));
                        }
                        if (!"null".equals(ob.getModifiedBy()) && ob.getModifiedBy() != null && !ob.getModifiedBy().isEmpty()) {
                            ob.setModifiedBy(userMap.get(Integer.valueOf(ob.getModifiedBy())));
                        }
                    } else if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) {
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

      
        

    } catch (SystemException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug(" Ends searchForecastSales with the  Serach List size    ::::  " + ((list == null) ? list : list.size()));
        return list;
    }

    public int getActualSearchResults(Map<Object, Object> searchValues, Set<Filter> bsc, String tableName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException  {
        LOGGER.debug("Entering getActualSearchResults with searchForm" + tableName);
        String tempValue = StringUtils.EMPTY;
        final DynamicQuery contractMasterQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);

        final DynamicQuery brandMasterQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);

        final DynamicQuery itemMasterQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);

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
            } else {
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
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        }
        if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        }

        if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
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
                invalidDate.setHours(NumericConstants.TWENTY_THREE);
                invalidDate.setMinutes(NumericConstants.FIFTY_NINE);
                invalidDate.setSeconds(NumericConstants.FIFTY_NINE);
                actualsDynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
            } else if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
            } else if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE)));
            }
        }
        if ("IvldActualMaster".equals(tableName) || tableName.startsWith("Ivld")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.like("reprocessedFlag", "N"));
        }
        actualsDynamicQuery = getFilterQuery(bsc, actualsDynamicQuery, tableName);
        LOGGER.debug(" Ends getActualSearchResults with dynamicQuery");

        int count;
        if (actualsDynamicQuery == null) {
            count = 0;
        } else {
            Class cls = Class.forName(ConstantUtil.TABLE_SERVICE_PATH + tableName + ConstantUtil.TABLE_UTIL_APPEND);
            Method method = cls.getMethod(ConstantUtil.PARAM_DYNAMIC_COUNT, DynamicQuery.class);
            Object ob = null;
            try {
                ob = method.invoke(cls.newInstance(), actualsDynamicQuery);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(SearchLogic.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                java.util.logging.Logger.getLogger(SearchLogic.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                java.util.logging.Logger.getLogger(SearchLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            count = Integer.valueOf(ob.toString());
        }
        return count;
    }

    public List<Object> searchFindForActualMaster(Map<Object, Object> searchValues, int startIndex, int i, List<SortByColumn> sortByColumns, Set<Filter> criteria, String tableName, boolean excelFlag, Object[] excelVisibleColumnArr, final DetailsDTO primaryDTO) throws IllegalAccessException, IllegalArgumentException,
           InvocationTargetException,ClassNotFoundException, NoSuchMethodException, InstantiationException {
        String tempValue = StringUtils.EMPTY;
        final DynamicQuery contractMasterQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class);

        final DynamicQuery brandMasterQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class);

        final DynamicQuery itemMasterQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
        for (int j = 0; j < searchValues.size(); j++) {
            if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                invalidFromDate = (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE);
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
            } else {
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
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_START_DATE, searchValues.get(ConstantUtil.ACCRUAL_START_DATE_TO + "_Date")));
        }
        if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.ACCRUAL_ACTUAL_END_DATE, searchValues.get(ConstantUtil.ACCRUAL_END_DATE_TO + "_Date")));
        }

        if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date") && searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.between(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date"), searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_FROM + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.gt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_FROM + "_Date")));
        } else if (searchValues.containsKey(ConstantUtil.PAYMENT_DATE_TO + "_Date")) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.lt(ConstantUtil.CASHPAID_DATE, searchValues.get(ConstantUtil.PAYMENT_DATE_TO + "_Date")));
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
                invalidDate.setHours(NumericConstants.TWENTY_THREE);
                invalidDate.setMinutes(NumericConstants.FIFTY_NINE);
                invalidDate.setSeconds(NumericConstants.FIFTY_NINE);
                actualsDynamicQuery.add(RestrictionsFactoryUtil.between("intfInsertedDate", invalidFromDate, invalidDate));
            } else if (searchValues.containsKey(ConstantUtil.INVALID_TO_DATE)) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.lt("intfInsertedDate", (Date) searchValues.get("invalidToDate_Date")));
            } else if (searchValues.containsKey(ConstantUtil.INVALID_FROM_DATE)) {
                actualsDynamicQuery.add(RestrictionsFactoryUtil.ge("intfInsertedDate", (Date) searchValues.get(ConstantUtil.INVALID_FROM_DATE)));
            }
        }
        if ("IvldActualMaster".equals(tableName)) {
            actualsDynamicQuery.add(RestrictionsFactoryUtil.like("reprocessedFlag", "N"));
        }
        if (sortByColumns == null || sortByColumns.isEmpty()) {
            if (StringUtils.isEmpty(primaryDTO.getInvalidTableName())) {
                actualsDynamicQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getPropertyName()));
            } else {
                actualsDynamicQuery.addOrder(OrderFactoryUtil.asc(primaryDTO.getInvalidTableName()));
            }
        }

        if (!excelFlag) {
            actualsDynamicQuery = getFilterQuery(criteria, actualsDynamicQuery, tableName);
            actualsDynamicQuery.setLimit(startIndex, i);
            LOGGER.debug(" Ends getDynamicQuerySearch with dynamicQuery");
            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = (SortByColumn) iterator.next();
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
        
        if (!excelFlag) {
            if ("IvldActualMaster".equals(tableName)) {
                for (int j = 0; j < list.size(); j++) {
                    IvldActualMaster ob = (IvldActualMaster) list.get(j);
                    ob.setQuantity(!"null".equals(ob.getQuantity()) && ob.getQuantity() != null && !ob.getQuantity().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getQuantity()))) : StringUtils.EMPTY);
                    ob.setAmount(!"null".equals(ob.getAmount()) && ob.getAmount() != null && !ob.getAmount().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getAmount()))) : StringUtils.EMPTY);
                    ob.setSalesAmount(!"null".equals(ob.getSalesAmount()) && ob.getSalesAmount() != null && !ob.getSalesAmount().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getSalesAmount()))) : StringUtils.EMPTY);
                }
            } else if ("ActualsMaster".equals(tableName)) {
                for (int j = 0; j < list.size(); j++) {
                    ActualsMaster ob = (ActualsMaster) list.get(j);
                    ob.setQuantity(!"null".equals(ob.getQuantity()) && ob.getQuantity() != null && !ob.getQuantity().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getQuantity()))) : StringUtils.EMPTY);
                    ob.setAmount(!"null".equals(ob.getAmount()) && ob.getAmount() != null && !ob.getAmount().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getAmount()))) : StringUtils.EMPTY);
                    ob.setSalesAmount(!"null".equals(ob.getSalesAmount()) && ob.getSalesAmount() != null && !ob.getSalesAmount().isEmpty() ? String.valueOf(twodecimalformat.format(Double.valueOf(ob.getSalesAmount()))) : StringUtils.EMPTY);
                }
            }
        }
        
        
        
        LOGGER.debug(" Ends searchForecastSales with the Search List size    ::::  " + ((list == null) ? list : list.size()));
        return list;
    }

    public List<Object> searchFindForReturnsMsater(Map<Object, Object> searchValues,
            String tableName) {

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
            quantityInclusion = (List<String>) ActualsMasterLocalServiceUtil.dynamicQuery(actualsMasterQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        resultList.add(ConstantUtil.SELECT_ONE);
        resultList.addAll(quantityInclusion);
        return resultList;
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

    public String getHelperValues(String helperValue, String listval) {
        String sqlQuery = "SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE DESCRIPTION LIKE '" + helperValue + "' AND LIST_NAME LIKE '" + listval + "'";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return String.valueOf(list.get(0));
    }

    public String getAdjustmentTypeValues(String filterString, String tableName) {
        String sqlQuery = " SELECT SRD.ADJUSTMENT_TYPE FROM "+tableName+" SRD\n" +
                            " LEFT JOIN ARM_ADJUSTMENT_CONFIG ADJ ON ADJ.ARM_ADJUSTMENT_CONFIG_SID = SRD.ADJUSTMENT_TYPE\n" +
                            " WHERE ADJ.TRANSACTION_NAME LIKE '"+filterString+"'";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return String.valueOf(list.get(0));
    }

    public List excelLogicForInvalidSales(Map<Object, Object> searchValues, final int start, final int end) {
        String query = "SELECT\n"
                + "    SALES_ID,ITEM_ID,ITEM_NO,ACCOUNT_ID,\n"
                + "    ACCOUNT_NO,CONTRACT_ID,CONTRACT_NO,COMPANY_ID,\n"
                + "    INVOICE_NUMBER,INVOICE_DATE,ORGANIZATION_KEY,PARENT_ITEM_ID,\n"
                + "    ITEM_UOM,DOC_TYPE,ORDER_NUMBER,PROVISION_ID,ORDER_RECEIVED_DATE,\n"
                + "    INVOICE_LINE_NUMBER,ORDER_TYPE,ORDER_SUBTYPE,ANALYSIS_CODE,\n"
                + "    PRICE_ADJUSTMENT_NAME,RECORD_SEQUENCE,PRICE,\n"
                + "    LOT_NO,CUSTOMER_SUBTYPE,WHOLESALE_OWNER_ID,ACCOUNT_NAME,\n"
                + "    IDENTIFIER_CODE_QUALIFIER,CUSTOMER_COMPANY_CODE,IS_ACTIVE,DIVISION_ID,\n"
                + "    MARKET_ID,BRAND_ID,REASON_FOR_FAILURE,ERROR_CODE,\n"
                + "    ERROR_FIELD,IVLD_SALES_MASTER_SID,REPROCESSED_FLAG,BATCH_ID,\n"
                + "	ACCOUNT_TYPE\n"
                + " FROM  dbo.IVLD_SALES_MASTER WHERE REPROCESSED_FLAG = 'N' ";
        for (Map.Entry<Object, Object> entry : searchValues.entrySet()) {
            String tempValue = entry.getValue().toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query = query + " AND " + (getInvalidSalesDbColumn(entry.getKey().toString())) + " like'" + tempValue + "'";
        }
        query = query + " ORDER BY  SALES_INTFID  ASC\n"
                + "OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY ";

        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list;
    }

    public String getInvalidSalesDbColumn(String s) {
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

    
    public String addSearchConditionsforARP(Map<Object, Object> searchValues, boolean countFlag) {
        String tempValue = StringUtils.EMPTY;
        tempValue = tempValue.replace("*", "%");
        String filterQuery = "";

        if (searchValues != null) {

            if (searchValues.containsKey(ConstantUtil.ARP_NAME)) {
                if(!countFlag){
                filterQuery += " INNER JOIN PROJECTION_MASTER P ON P.PROJECTION_MASTER_SID = ARP.PROJECTION_MASTER_SID";
            }
                tempValue = searchValues.get(ConstantUtil.ARP_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND p.PROJECTION_NAME like '" + tempValue + "'";
            }

            if ((searchValues.containsKey(ConstantUtil.COMPANY_NO) || searchValues.containsKey(ConstantUtil.COMPANY_NAME)) && !countFlag) {
                filterQuery += " INNER JOIN COMPANY_MASTER CM ON ARP.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID";
            }

            if (searchValues.containsKey(ConstantUtil.COMPANY_NO)) {
                tempValue = searchValues.get(ConstantUtil.COMPANY_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND CM.COMPANY_NO like '" + tempValue + "'";
            }
            if (searchValues.containsKey(ConstantUtil.COMPANY_NAME)) {
                tempValue = searchValues.get(ConstantUtil.COMPANY_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND CM.COMPANY_NAME like '" + tempValue + "'";
            }

            if ((searchValues.containsKey(ConstantUtil.ITEMNO) || searchValues.containsKey(ConstantUtil.ITEMID) || searchValues.containsKey(ConstantUtil.ITEM_NAME)) && !countFlag) {
                filterQuery += " INNER JOIN ITEM_MASTER IM ON ARP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID";
            }

            if (searchValues.containsKey(ConstantUtil.ITEMNO)) {
                tempValue = searchValues.get(ConstantUtil.ITEMNO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND IM.ITEM_NO like '" + tempValue + "'";
            }
            if (searchValues.containsKey(ConstantUtil.ITEMID)) {
                tempValue = searchValues.get(ConstantUtil.ITEMID).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND IM.ITEM_ID like '" + tempValue + "'";
            }
            if (searchValues.containsKey(ConstantUtil.ITEM_NAME)) {
                tempValue = searchValues.get(ConstantUtil.ITEM_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND IM.ITEM_NAME like '" + tempValue + "'";
            }

            if ((searchValues.containsKey(ConstantUtil.BRAND_ID) || searchValues.containsKey(ConstantUtil.BRAND_NAME)) && !countFlag) {
                filterQuery += " INNER JOIN BRAND_MASTER BM ON ARP.BRAND_MASTER_SID = BM.BRAND_MASTER_SID";
            }

            if (searchValues.containsKey(ConstantUtil.BRAND_ID)) {
                tempValue = searchValues.get(ConstantUtil.BRAND_ID).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND BM.BRAND_ID like '" + tempValue + "'";
            }
            if (searchValues.containsKey(ConstantUtil.BRAND_NAME)) {
                tempValue = searchValues.get(ConstantUtil.BRAND_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND BM.BRAND_NAME like '" + tempValue + "'";
            }
        filterQuery += " Where ARP_WORKFLOW_ID like '%'";
            if (searchValues.containsKey(ConstantUtil.ARP_ID)) {                
                tempValue = searchValues.get(ConstantUtil.ARP_ID).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND ARP_WORKFLOW_ID like '" + tempValue + "'";
            }
            if (searchValues.containsKey(ConstantUtil.CATEGORY)) {
                tempValue = searchValues.get(ConstantUtil.CATEGORY).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if(!"*".equals(searchValues.get(ConstantUtil.CATEGORY).toString())){
                filterQuery += " AND RS_CATEGORY like '" + tempValue + "'";
                }
            }
            if (searchValues.containsKey(ConstantUtil.TYPE)) {
                tempValue = searchValues.get(ConstantUtil.TYPE).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if(!"*".equals(searchValues.get(ConstantUtil.TYPE).toString())){
                filterQuery += " AND RS_TYPE like '" + tempValue + "'";
                }
            }
            if (searchValues.containsKey(ConstantUtil.PROGRAM)) {
                tempValue = searchValues.get(ConstantUtil.PROGRAM).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                if(!"*".equals(searchValues.get(ConstantUtil.PROGRAM).toString())){
                filterQuery += " AND REBATE_PROGRAM_TYPE like '" + tempValue + "'";
            }
            }
            if (searchValues.containsKey(ConstantUtil.ACCOUNT)) {
                tempValue = searchValues.get(ConstantUtil.ACCOUNT).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += " AND ARP.ACCOUNT like '" + tempValue + "'";
            }

            if (searchValues.containsKey(ConstantUtil.ACCOUNT_TYPE)) {
                tempValue = searchValues.get(ConstantUtil.ACCOUNT_TYPE).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
                filterQuery += "  AND ARP.ACCOUNT_TYPE like '" + tempValue + "'";
           
            }

            if (searchValues.containsKey(ConstantUtil.ARP_CREATION_DATE) && searchValues.get(ConstantUtil.ARP_CREATION_DATE) != null && !searchValues.get(ConstantUtil.ARP_CREATION_DATE).equals("null")) {
                    tempValue = searchValues.get(ConstantUtil.ARP_CREATION_DATE).toString();
                        filterQuery += " AND ARP.ARP_CREATION_DATE   >= '" + tempValue + "'";
            }
            filterQuery = filterQuery.replaceFirst("WHERE AND", "WHERE");
            if (filterQuery.endsWith("REBATE_PROGRAM_TYPE=ht1.HELPER_TABLE_SID WHERE")) {
                filterQuery = filterQuery.replace("REBATE_PROGRAM_TYPE=ht1.HELPER_TABLE_SID WHERE", "REBATE_PROGRAM_TYPE=ht1.HELPER_TABLE_SID ");
            }

        }
        return filterQuery;
    }
    
    public List<Object[]> searchArpOutbound(Map<Object, Object> searchValues, boolean countFlag, final Set<Container.Filter> filterSet) throws ParseException {
        String filterCondition=StringUtils.EMPTY;
        StringBuilder queryBuilder = new StringBuilder(addSearchConditionsforARP(searchValues, countFlag));
        String filterQuery = String.valueOf(getConditionForARPOutbound(filterSet, queryBuilder, "count"));
        if (filterSet!=null && filterSet.size()!=0)
         filterCondition = String.valueOf(getFilterForARPOutbound(filterSet));
        filterQuery = filterQuery.replaceAll("''", "'");
        
        String accountType = "null";
        if (searchValues != null && searchValues.containsKey(ConstantUtil.ACCOUNT)) {
            accountType = searchValues.get(ConstantUtil.ACCOUNT).toString();
        }
        if (searchValues != null && searchValues.containsKey(ConstantUtil.ACCOUNT_TYPE)) {
            accountType = searchValues.get(ConstantUtil.ACCOUNT_TYPE).toString();
        }

        if (!accountType.equals("null")) {
            if (accountType.contains("Sales Dollars")) {
                accountType = "'EX-FACTORY SALES DOLLARS'";
            } else if (accountType.contains("Sales Units")) {
                accountType = "'EX-FACTORY SALES UNITS'";
            } else if (accountType.contains("Accrual Dollars")){
                accountType = "'Accrual Dollars'";
            }else if(accountType.contains("Accrual Rate")){
                 accountType = "'Accrual Rate'";
            }else if(accountType.contains("Deduction")){
                 accountType = "'Accrual Rate'";
            }
        }

        String countQuery = SQlUtil.getQuery("arp-outbound-count-query");
        countQuery = countQuery.replace("[?SEARCH_RESTRICTION]", filterQuery);
        countQuery = countQuery.replace("[?FILTER_RESTRICTION]", filterCondition);
        countQuery = countQuery.replace("[?ACCOUNT_TYPE]", accountType);
        List<Object[]> list = HelperTableLocalServiceUtil.executeSelectQuery(countQuery);

        return list;
    }

    void loadCountFilterMap() {
        if (countQueryFilterMap.isEmpty()) {
            countQueryFilterMap.put("check_Record", "CHECK_RECORD");
            countQueryFilterMap.put("arp_Id", "ARP_WORKFLOW_ID");
            countQueryFilterMap.put("arp_Name", "P.PROJECTION_NAME");
            countQueryFilterMap.put("company_Id", "CM.COMPANY_ID");
            countQueryFilterMap.put("company_No", "CM.COMPANY_NO");
            countQueryFilterMap.put("company_Name", "CM.COMPANY_NAME");
            countQueryFilterMap.put("item_No", "IM.ITEM_NO");
            countQueryFilterMap.put("item_Id", "IM.ITEM_ID");
            countQueryFilterMap.put("item_Name", "IM.ITEM_NAME");
            countQueryFilterMap.put("brand_Id", "BM.BRAND_ID");
            countQueryFilterMap.put("brand_Name", "BM.BRAND_NAME");
            countQueryFilterMap.put(ConstantUtil.ACCOUNT, "ARP.ACCOUNT");
            countQueryFilterMap.put("account_Type", "ARP.ACCOUNT_TYPE");
            countQueryFilterMap.put("category", "RS_CATEGORY");
            countQueryFilterMap.put("type", "RS_TYPE");
            countQueryFilterMap.put("program", "REBATE_PROGRAM_TYPE");
            countQueryFilterMap.put("arp_Creation_Date", "ARP.ARP_CREATION_DATE");
            countQueryFilterMap.put("arp_Approval_Date", "AC.APPROVED_DATE");
            countQueryFilterMap.put("outbound_Status", "ARP.OUTBOUND_STATUS");
            countQueryFilterMap.put("original_Batch_ID", "ARP.ORIGINAL_BATCH_ID");

            countQueryFilterMap.put("current_Year_Jan", "ARP.JAN");
            countQueryFilterMap.put("current_Year_Feb", "ARP.FEB");
            countQueryFilterMap.put("current_Year_Mar", "ARP.MAR");
            countQueryFilterMap.put("current_Year_Apr", "ARP.APR");
            countQueryFilterMap.put("current_Year_May", "ARP.MAY");
            countQueryFilterMap.put("current_Year_June", "ARP.JUN");
            countQueryFilterMap.put("current_Year_July", "ARP.JUL");
            countQueryFilterMap.put("current_Year_Aug", "ARP.AUG");
            countQueryFilterMap.put("current_Year_Sep", "ARP.SEP");
            countQueryFilterMap.put("current_Year_Oct", "ARP.OCT");
            countQueryFilterMap.put("current_Year_Nov", "ARP.NOV");
            countQueryFilterMap.put("current_Year_Dec", "ARP.DEC");

            countQueryFilterMap.put("current_Year_1_Jan", "ARP.JAN");
            countQueryFilterMap.put("current_Year_1_Feb", "ARP.FEB");
            countQueryFilterMap.put("current_Year_1_Mar", "ARP.MAR");
            countQueryFilterMap.put("current_Year_1_Apr", "ARP.APR");
            countQueryFilterMap.put("current_Year_1_May", "ARP.MAY");
            countQueryFilterMap.put("current_Year_1_June", "ARP.JUN");
            countQueryFilterMap.put("current_Year_1_July", "ARP.JUL");
            countQueryFilterMap.put("current_Year_1_Aug", "ARP.AUG");
            countQueryFilterMap.put("current_Year_1_Sep", "ARP.SEP");
            countQueryFilterMap.put("current_Year_1_Oct", "ARP.OCT");
            countQueryFilterMap.put("current_Year_1_Nov", "ARP.NOV");
            countQueryFilterMap.put("current_Year_1_Dec", "ARP.DEC");

            countQueryFilterMap.put("current_Year_2_Jan", "ARP.JAN");
            countQueryFilterMap.put("current_Year_2_Feb", "ARP.FEB");
            countQueryFilterMap.put("current_Year_2_Mar", "ARP.MAR");
            countQueryFilterMap.put("current_Year_2_Apr", "ARP.APR");
            countQueryFilterMap.put("current_Year_2_May", "ARP.MAY");
            countQueryFilterMap.put("current_Year_2_June", "ARP.JUN");
            countQueryFilterMap.put("current_Year_2_July", "ARP.JUL");
            countQueryFilterMap.put("current_Year_2_Aug", "ARP.AUG");
            countQueryFilterMap.put("current_Year_2_Sep", "ARP.SEP");
            countQueryFilterMap.put("current_Year_2_Oct", "ARP.OCT");
            countQueryFilterMap.put("current_Year_2_Nov", "ARP.NOV");
            countQueryFilterMap.put("current_Year_2_Dec", "ARP.DEC");
            countQueryFilterMap.put("uom", "ARP.UOM");
            countQueryFilterMap.put("account_category", "ARP.ACCOUNT_CATEGORY");
            countQueryFilterMap.put("account_group", "ARP.ACCOUNT_GROUP");
            countQueryFilterMap.put("account_type", "ARP.ACCOUNT_TYPE");
            countQueryFilterMap.put(ConstantUtil.ACCOUNT, "ARP.ACCOUNT");

        }
    }

    public List<Object> searchFindForArpOutBound(Map<Object, Object> searchValues, int start, int end, List<SortByColumn> sortByColumns, Set<Filter> criteria, boolean isExcel,boolean isSearchFirst) throws ParseException {
     
        String query = QueryUtils.getPivotQuery(searchValues, start, end, isExcel);
        if (isSearchFirst) {
            query = query.replace("[?TEMP_INSERT]", SQlUtil.getQuery("arp-outbound-insert-query"));
        } else {
            query = query.replace("[?TEMP_INSERT]", StringUtils.EMPTY);
        }
        
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder conditionQuery = new StringBuilder();
            String filterQuery = getConditionForARPOutbound(criteria, conditionQuery, "search").toString();
            String searhFilter = addSearchConditionsforARP(searchValues, isExcel);
            query = query.replace("[?SEARCH_RESTRICTION]", searhFilter);

            if (!isExcel) {
                StringBuilder orderByQuery = new StringBuilder();
                String orderQuery = getSortingARPOutbound(sortByColumns, start, end, orderByQuery, query).toString();
                query = query.replace("[?SORT_QUERY]", orderQuery);
                query = query.replace("[?FILTER_CONDITION]", filterQuery);
                list = HelperTableLocalServiceUtil.executeSelectQuery(query);
            } else{
                  
                list = ConnectionUtils.getInstance().getBCP_executableQuery(query);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return list;
    }

    public static void updateTempTable(ARPOutboundDTO dto) {
        String session_Id = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        String updateQuery = SQlUtil.getQuery("Accrual Rate".equals(dto.getAccount_Type()) || "Accrual Dollars".equals(dto.getAccount_Type())? "arp-check-record-update-deduction-rate" : "arp-check-record-update");
        updateQuery = updateQuery.replace("[?CHECK_RECORD]", dto.getCheckedValue());
        updateQuery = updateQuery.replace("[?ITEM_MASTER_SID]", dto.getItemMasterSid());
        updateQuery = updateQuery.replace("[?RS_TYPE]", dto.getType());
        updateQuery = updateQuery.replace("[?REBATE_PROGRAM_TYPE]", dto.getProgram());
        updateQuery = updateQuery.replace("[?RS_CATEGORY]", dto.getCategory());
        updateQuery = updateQuery.replace("[?ACCOUNT_TYPE]", dto.getAccount_Type());
        updateQuery = updateQuery.replace("[?SESSION_ID]", session_Id);
        updateQuery = updateQuery.replace("[?USER_ID]", userId);
         updateQuery = updateQuery.replace("[?ARP_WORKFLOW_ID]", dto.getArp_Id());
        if ("Accrual Rate".equals(dto.getAccount_Type()) || "Accrual Dollars".equals(dto.getAccount_Type())) {
            if (StringUtils.isBlank(dto.getAccount_category())) {
                updateQuery += " AND (ACCOUNT_CATEGORY  ='' or ACCOUNT_CATEGORY is null)";
            } else {
                updateQuery += " AND ACCOUNT_CATEGORY  =" + dto.getAccount_category();
            }
            if (StringUtils.isBlank(dto.getAccount_group())) {
                updateQuery += " AND (ACCOUNT_GROUP ='' or ACCOUNT_GROUP is null)";
            } else {
                updateQuery += " AND ACCOUNT_GROUP = " + dto.getAccount_group();
            }
            
            updateQuery += StringUtils.isBlank(dto.getType()) ? " AND (RS_TYPE ='' or RS_TYPE is null) " : " AND RS_TYPE = '" + dto.getType()+"'";
            updateQuery += StringUtils.isBlank(dto.getProgram()) ? " AND (REBATE_PROGRAM_TYPE ='' or REBATE_PROGRAM_TYPE is null) " : " AND REBATE_PROGRAM_TYPE = '" + dto.getProgram()+"'";
            updateQuery += StringUtils.isBlank(dto.getCategory()) ? " AND (RS_CATEGORY ='' or RS_CATEGORY is null) " : " AND RS_CATEGORY = '" + dto.getCategory()+"'";
        }
        HelperTableLocalServiceUtil.executeUpdateQuery(updateQuery);
    }

    void loadsearchFilterMap() {
        if (pivotQueryFilterMap.isEmpty()) {
            pivotQueryFilterMap.put("check_Record", "CHECK_RECORD");
            pivotQueryFilterMap.put("arp_Id", "A.ARP_WORKFLOW_ID");
            pivotQueryFilterMap.put("arp_Name", "projection_name");
            pivotQueryFilterMap.put("company_Id", "CM1.COMPANY_ID");
            pivotQueryFilterMap.put("company_No", "CM1.COMPANY_NO");
            pivotQueryFilterMap.put("company_Name", "CM1.COMPANY_NAME");
            pivotQueryFilterMap.put("item_No", "IM1.ITEM_NO");
            pivotQueryFilterMap.put("item_Id", "IM1.ITEM_ID");
            pivotQueryFilterMap.put("item_Name", "IM1.ITEM_NAME");
            pivotQueryFilterMap.put("brand_Id", "BM1.BRAND_ID");
            pivotQueryFilterMap.put("brand_Name", "BM1.BRAND_NAME");
            pivotQueryFilterMap.put(ConstantUtil.ACCOUNT, "A.ACCOUNT");
            pivotQueryFilterMap.put("account_Type", "A.ACCOUNT_TYPE");
            pivotQueryFilterMap.put("category", "RS_CATEGORY");
            pivotQueryFilterMap.put("type", "RS_TYPE");
            pivotQueryFilterMap.put("program", "REBATE_PROGRAM_TYPE");
            pivotQueryFilterMap.put("arp_Creation_Date", "A.ARP_CREATION_DATE");
            pivotQueryFilterMap.put("arp_Approval_Date", "A.ARP_APPROVAL_DATE");
            pivotQueryFilterMap.put("outbound_Status", "A.OUTBOUND_STATUS");
            pivotQueryFilterMap.put("original_Batch_ID", "A.ORIGINAL_BATCH_ID");

            pivotQueryFilterMap.put("current_Year_Jan", "A.CURRENT_YEAR_M1");
            pivotQueryFilterMap.put("current_Year_Feb", "A.CURRENT_YEAR_M2");
            pivotQueryFilterMap.put("current_Year_Mar", "A.CURRENT_YEAR_M3");
            pivotQueryFilterMap.put("current_Year_Apr", "A.CURRENT_YEAR_M4");
            pivotQueryFilterMap.put("current_Year_May", "A.CURRENT_YEAR_M5");
            pivotQueryFilterMap.put("current_Year_June", "A.CURRENT_YEAR_M6");
            pivotQueryFilterMap.put("current_Year_July", "A.CURRENT_YEAR_M7");
            pivotQueryFilterMap.put("current_Year_Aug", "A.CURRENT_YEAR_M8");
            pivotQueryFilterMap.put("current_Year_Sep", "A.CURRENT_YEAR_M9");
            pivotQueryFilterMap.put("current_Year_Oct", "A.CURRENT_YEAR_M10");
            pivotQueryFilterMap.put("current_Year_Nov", "A.CURRENT_YEAR_M11");
            pivotQueryFilterMap.put("current_Year_Dec", "A.CURRENT_YEAR_M12");

            pivotQueryFilterMap.put("current_Year_1_Jan", "A.CURRENT_YEAR1_M1");
            pivotQueryFilterMap.put("current_Year_1_Feb", "A.CURRENT_YEAR1_M2");
            pivotQueryFilterMap.put("current_Year_1_Mar", "A.CURRENT_YEAR1_M3");
            pivotQueryFilterMap.put("current_Year_1_Apr", "A.CURRENT_YEAR1_M4");
            pivotQueryFilterMap.put("current_Year_1_May", "A.CURRENT_YEAR1_M5");
            pivotQueryFilterMap.put("current_Year_1_June", "A.CURRENT_YEAR1_M6");
            pivotQueryFilterMap.put("current_Year_1_July", "A.CURRENT_YEAR1_M7");
            pivotQueryFilterMap.put("current_Year_1_Aug", "A.CURRENT_YEAR1_M8");
            pivotQueryFilterMap.put("current_Year_1_Sep", "A.CURRENT_YEAR1_M9");
            pivotQueryFilterMap.put("current_Year_1_Oct", "A.CURRENT_YEAR1_M10");
            pivotQueryFilterMap.put("current_Year_1_Nov", "A.CURRENT_YEAR1_M11");
            pivotQueryFilterMap.put("current_Year_1_Dec", "A.CURRENT_YEAR1_M12");

            pivotQueryFilterMap.put("current_Year_2_Jan", "A.CURRENT_YEAR2_M1");
            pivotQueryFilterMap.put("current_Year_2_Feb", "A.CURRENT_YEAR2_M2");
            pivotQueryFilterMap.put("current_Year_2_Mar", "A.CURRENT_YEAR2_M3");
            pivotQueryFilterMap.put("current_Year_2_Apr", "A.CURRENT_YEAR2_M4");
            pivotQueryFilterMap.put("current_Year_2_May", "A.CURRENT_YEAR2_M5");
            pivotQueryFilterMap.put("current_Year_2_June", "A.CURRENT_YEAR2_M6");
            pivotQueryFilterMap.put("current_Year_2_July", "A.CURRENT_YEAR2_M7");
            pivotQueryFilterMap.put("current_Year_2_Aug", "A.CURRENT_YEAR2_M8");
            pivotQueryFilterMap.put("current_Year_2_Sep", "A.CURRENT_YEAR2_M9");
            pivotQueryFilterMap.put("current_Year_2_Oct", "A.CURRENT_YEAR2_M10");
            pivotQueryFilterMap.put("current_Year_2_Nov", "A.CURRENT_YEAR2_M11");
            pivotQueryFilterMap.put("current_Year_2_Dec", "A.CURRENT_YEAR2_M12");

            pivotQueryFilterMap.put("uom", "A.UOM");
            pivotQueryFilterMap.put("account_category", "A.ACCOUNT_CATEGORY");
            pivotQueryFilterMap.put("account_group", "A.ACCOUNT_GROUP");
            pivotQueryFilterMap.put("account_type", "A.ACCOUNT_TYPE");
            pivotQueryFilterMap.put(ConstantUtil.ACCOUNT, "A.ACCOUNT");

        }

    }
  
    private StringBuilder getConditionForARPOutbound(final Set<Container.Filter> filterSet, StringBuilder stringBuilder, final String query) {
        loadsearchFilterMap();
        if (filterSet != null) {
            if ("count".equals(query)) {
                stringBuilder.append(AbstractFilterLogic.getInstance().filterQueryGenerator_ARP(filterSet, countQueryFilterMap, true).toString().replace("where", " AND"));
            } else {
                stringBuilder.append(AbstractFilterLogic.getInstance().filterQueryGenerator_ARP(filterSet, pivotQueryFilterMap, false).toString());
            }

            String filterQuery = stringBuilder.toString();
            if (filterQuery.contains("OUTBOUND_STATUS LIKE ''%n%''")) {
                filterQuery = filterQuery.replace("OUTBOUND_STATUS LIKE ''%n%''", "OUTBOUND_STATUS LIKE ''%0%''");
            } else if (filterQuery.contains("OUTBOUND_STATUS LIKE ''%y%''")) {
                filterQuery = filterQuery.replaceAll("OUTBOUND_STATUS LIKE ''%y%''", "OUTBOUND_STATUS LIKE ''%1%''");
            }
            stringBuilder = new StringBuilder(filterQuery);
        }
        return stringBuilder;
    }
    
    private StringBuilder getSortingARPOutbound(List<SortByColumn> sortByColumns, int start, int end, StringBuilder stringBuilder, final String query) {
        loadCountFilterMap();
        if (sortByColumns != null) {
            StringBuilder orderBy = new StringBuilder();
            if ("count".equals(query)) {
                orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, countQueryFilterMap, "ARP_WORKFLOW_ID, COMPANY_ID, ITEM_ID, ACCOUNT_TYPE ", start, end);
            } else {
                orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, pivotQueryFilterMap, "ARP_WORKFLOW_ID, COMPANY_ID, ITEM_ID, ACCOUNT_TYPE ", start, end);
            }
            stringBuilder.append(orderBy);
        }
        return stringBuilder;
    } 
   
    public void deleteTempTable() {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String query = "DELETE FROM ST_ARP_OUTBOUND WHERE USER_ID='" + userId + "' AND SESSION_ID=" + sessionId;
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public static void checkAll(String updateValue) {

        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String query = "UPDATE  ST_ARP_OUTBOUND SET CHECK_RECORD=" + updateValue + " WHERE USER_ID='" + userId + "' AND SESSION_ID=" + sessionId + "";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);

    }

    public Date parseDate(String d) {
        String[] formats = {
            "dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm", "dd/MM/yy",
            "dd/MM/yyyy", "d/M/yy", "d/M/yyyy", "ddMMyy", "ddMMyyyy", "ddMMMyy", "ddMMMyyyy", "dd-MMM-yyyy", "dMMMyy",
            "dMMMyyyy", "d-MMM-yy", "d-MMM-yyyy", "d-MMMM-yy", "d-MMMM-yyyy", "yyMMdd", "yyyyMMdd", "yy/MM/dd",
            "yyyy/MM/dd", "MMddyy", "MMddyyyy", "MM/dd/yy", "MM/dd/yyyy", "MMM-dd-yy", "MMM-dd-yyyy", "yyyy-MM-dd", "MMM dd yyyy hh:mm aaa"};

        if (d != null) {
            for (String parse : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.setLenient(false);
                    return sdf.parse(d);
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return null;
    }

    /**
     * Method to get the list
     *
     * @param adjustmentDetailsDTO
     * @return
     * @throws Exception
     */
    public List<AdjustmentDetailsDTO> getViewDeatils(AdjustmentDetailsDTO adjustmentDetailsDTO) throws SQLException  {
        List input = getViewInputList(adjustmentDetailsDTO);
        List<Object[]> list = QueryDataUtils.getAppData(input, "getViewData", null);
        List<AdjustmentDetailsDTO> resultList = getCustomizedViewDTO(list);
        return resultList;
    }

    /**
     * getCustomizedViewDTO
     *
     * @param list
     * @return
     * @throws Exception
     */
    public List<AdjustmentDetailsDTO> getCustomizedViewDTO(List<Object[]> list)  {
        List<AdjustmentDetailsDTO> viewList = new ArrayList<>();
        AdjustmentDetailsDTO adjustmentDetailsDTO;
        for (Object[] temp : list) {
            adjustmentDetailsDTO = new AdjustmentDetailsDTO();
            adjustmentDetailsDTO.setViewMasterSid(temp[0] != null ? Integer.valueOf(String.valueOf(temp[0])) : 0);
            adjustmentDetailsDTO.setViewName(temp[NumericConstants.ONE] != null ? String.valueOf(temp[NumericConstants.ONE]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setViewType(temp[NumericConstants.TWO] != null ? String.valueOf(temp[NumericConstants.TWO]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setCreatedDate(temp[NumericConstants.THREE] != null ? (Date) temp[NumericConstants.THREE] : null);
            adjustmentDetailsDTO.setCreatedBy(temp[NumericConstants.FOUR] != null ? String.valueOf(temp[NumericConstants.FOUR]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setModifiedDate(temp[NumericConstants.FIVE] != null ? (Date) temp[NumericConstants.FIVE] : null);
            adjustmentDetailsDTO.setModifiedBy(temp[NumericConstants.SIX] != null ? String.valueOf(temp[NumericConstants.SIX]) : StringUtils.EMPTY);

            adjustmentDetailsDTO.setAdjustmentType(temp[NumericConstants.SEVEN] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.SEVEN])) : 0);
            adjustmentDetailsDTO.setAdjustmentLevel(temp[NumericConstants.EIGHT] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.EIGHT])) : 0);
            adjustmentDetailsDTO.setCompanyMasterSid(temp[NumericConstants.NINE] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.NINE])) : 0);
            adjustmentDetailsDTO.setBuCompanyMasterSid(temp[NumericConstants.TEN] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.TEN])) : 0);
            adjustmentDetailsDTO.setWorkflowId(temp[NumericConstants.ELEVEN] != null ? String.valueOf(temp[NumericConstants.ELEVEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setWorkflowName(temp[NumericConstants.TWELVE] != null ? String.valueOf(temp[NumericConstants.TWELVE]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setCustomerNo(temp[NumericConstants.THIRTEEN] != null ? String.valueOf(temp[NumericConstants.THIRTEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setItemNo(temp[NumericConstants.FOURTEEN] != null ? String.valueOf(temp[NumericConstants.FOURTEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setDeductionLevel(temp[NumericConstants.FIFTEEN] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.FIFTEEN])) : 0);
            adjustmentDetailsDTO.setCreationDateString(temp[NumericConstants.SIXTEEN] != null ? String.valueOf(temp[NumericConstants.SIXTEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setCustomerName(temp[NumericConstants.SEVENTEEN] != null ? String.valueOf(temp[NumericConstants.SEVENTEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setItemName(temp[NumericConstants.EIGHTEEN] != null ? String.valueOf(temp[NumericConstants.EIGHTEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setDeductionValue(temp[NumericConstants.NINETEEN] != null ? String.valueOf(temp[NumericConstants.NINETEEN]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setGlDateString(temp[NumericConstants.TWENTY] != null ? String.valueOf(temp[NumericConstants.TWENTY]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setBatchId(temp[NumericConstants.TWENTY_ONE] != null ? String.valueOf(temp[NumericConstants.TWENTY_ONE]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setBrandName(temp[NumericConstants.TWENTY_TWO] != null ? String.valueOf(temp[NumericConstants.TWENTY_TWO]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setRedemptionPeriod(temp[NumericConstants.TWENTY_THREE] != null ? String.valueOf(temp[NumericConstants.TWENTY_THREE]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setPostingIndicator(temp[NumericConstants.TWENTY_FOUR] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_FOUR])) : 0);
            adjustmentDetailsDTO.setTransactionLevelId(temp[NumericConstants.TWENTY_FIVE] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_FIVE])) : 0);
            adjustmentDetailsDTO.setAccountCategory(temp[NumericConstants.TWENTY_SIX] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_SIX])) : 0);
            adjustmentDetailsDTO.setAccountType(temp[NumericConstants.TWENTY_SEVEN] != null ? Integer.valueOf(String.valueOf(temp[NumericConstants.TWENTY_SEVEN])) : 0);
            adjustmentDetailsDTO.setAccount(temp[NumericConstants.TWENTY_EIGHT] != null ? String.valueOf(temp[NumericConstants.TWENTY_EIGHT]) : StringUtils.EMPTY);
            adjustmentDetailsDTO.setRedemptionPeriodEndDate(temp[NumericConstants.TWENTY_NINE] != null ? String.valueOf(temp[NumericConstants.TWENTY_NINE]) : StringUtils.EMPTY);

            viewList.add(adjustmentDetailsDTO);
        }
        return viewList;
    }

    private Map<String, String> getAdjusmentDetailsQueryMap() {
        Map<String, String> viewMap = new HashMap<>();
        viewMap.put("armViewMasterSid", "ARM_VIEW_MASTER_SID");
        viewMap.put("viewName", "VIEW_NAME");
        viewMap.put("viewType", "VIEW_TYPE");
        viewMap.put("createdBy", "UN.firstName+UN.lastName");
        viewMap.put(ConstantUtil.CREATED_DATE, "CREATED_DATE");
        viewMap.put("modifiedBy", "MB.firstName+MB.lastName");
        viewMap.put("modifiedDate", "MODIFIED_DATE");
        viewMap.put("sid", "ARM_VIEW_MASTER_SID");
        return viewMap;
    }

    public void viewSaveOrUpdate(Boolean isInsert) {

        if (isInsert) {
            QueryAPP.getAppQuery(null, "insert-ViewQuery");
        } else {
            QueryAPP.getAppQuery(null, "update-ViewQuery");
        }
    }

    public boolean isDuplicateView(String viewName) {
        try {
            List input = new ArrayList();
            input.add(viewName.trim());
            int count = getCount(QueryDataUtils.getAppData(input, "duplicateViewCheck", null));
            return count != 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception ex) {
            LOGGER.error("Error in View Name Duplicated" + ex);
            return Boolean.TRUE;
        }
    }

    /**
     * getViewCount
     *
     * @param dto
     * @return
     */
    public int getViewCount(final AdjustmentDetailsDTO dto) throws SQLException  {
        List input = getViewInputList(dto);
        int count = getCount(QueryDataUtils.getAppData(input, "getViewCount", null));
        LOGGER.debug("count in View ---------------  " + count);
        return count;
    }

    /**
     * Method to get the inputs
     *
     * @param dto
     * @return
     */
    private List getViewInputList(final AdjustmentDetailsDTO dto)  {
        List input = new ArrayList();
        try (Connection connection = SysDataSourceConnection.getConnection()) {
            input.add(connection.getCatalog());
            input.add(connection.getCatalog());
            if (dto.getViewType() != null && !StringUtils.EMPTY.equals(dto.getViewType().trim())) {
                input.add(dto.getViewType().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT));
            } else {
                input.add(ConstantUtil.CHAR_PERCENT);
            }
            if (dto.getViewName() != null && !StringUtils.EMPTY.equals(dto.getViewName().trim())) {
                input.add(dto.getViewName().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT));
            } else {
                input.add(ConstantUtil.CHAR_PERCENT);
            }
            if (dto.getFilters() != null && !dto.getFilters().isEmpty()) {
                StringBuilder filterQString = AbstractFilter.getInstance().filterQueryGenerator(dto.getFilters(), getAdjusmentDetailsQueryMap());
                input.add(filterQString.toString().replace("where", " AND "));
            } else {
                input.add(" ");
            }
            if (!dto.isCount()) {
                String sortQuery = StringUtils.EMPTY;
                if (dto.getSortByColumn() != null && !dto.getSortByColumn().isEmpty()) {
                    StringBuilder orderByString = AbstractFilter.getInstance().orderByQueryGenerator(dto.getSortByColumn(), getAdjusmentDetailsQueryMap());
                    sortQuery = orderByString.toString().replace("ORDER BY", "");
                    input.add(sortQuery);
                } else {
                    input.add(" ARM_VIEW_MASTER_SID asc ");
                }
                input.add(dto.getStart());
                input.add(dto.getOffset());
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
        return input;
    }

    /**
     * Method to retuns the count of a List
     *
     * @param list
     * @return
     */
    public static int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    public static Map<Integer, String> getPropertyMap() {
        List<Object[]> list = QueryDataUtils.getAppData(new ArrayList(), "getPropertyNames", null);
        Map<Integer, String> propertyMap = new HashMap<>();
        for (Object[] objects : list) {
            BigDecimal dc = new BigDecimal(String.valueOf(objects[0]));
            propertyMap.put(objects[0] != null ? dc.intValueExact() : 0, objects[1] != null ? String.valueOf(objects[1]) : StringUtils.EMPTY);
        }
        return propertyMap;
    }

    public static String[] getPropertyNames(Map<Integer, String> map)  {
        List<String> stringList = new ArrayList<>();
        for (Map.Entry<Integer, String> set : map.entrySet()) {
            stringList.add(set.getValue());
        }
        String[] stringArray = Arrays.copyOf(stringList.toArray(), stringList.toArray().length, String[].class);
        return stringArray;
    }

    public static String[] getPropertyNames() {
        List<Object[]> list = QueryDataUtils.getAppData(new ArrayList(), "getPropertyNames", null);
        List<String> stringList = new ArrayList<>();
        for (Object[] objects : list) {
            stringList.add(objects[NumericConstants.ONE] != null ? String.valueOf(objects[NumericConstants.ONE]) : StringUtils.EMPTY);
        }
        String[] stringArray = Arrays.copyOf(stringList.toArray(), stringList.toArray().length, String[].class);
        return stringArray;
    }

    public Boolean addOrUpdate(AdjustmentDetailsDTO adjustmentDetailsDTO) throws ParseException  {
        List input = getPropertyInputs(adjustmentDetailsDTO);
        Boolean isAddOrUpdated = QueryDataUtils.updateAppData(input, adjustmentDetailsDTO.isMode() ? "updateView" : "addView");
        return isAddOrUpdated;
    }

    private List getPropertyInputs(AdjustmentDetailsDTO dto) throws ParseException  {
        Map<String, Object> map = dto.getValueMap();
        List input = new ArrayList();
        //this input is for Master Insert input
        if (dto.isMode()) {
            input.add(dto.getViewMasterSid());
        }
        input.add(dto.getViewName());
        input.add(dto.getViewType());
        input.add(dto.getUserId());
        // This is for details insert input
        if (map.get("adjustmentType") != null) {
            input.add(map.get("adjustmentType"));
        } else {
            input.add("0");
        }
        if (map.get("adjustmentLevel") != null) {
            input.add(map.get("adjustmentLevel"));
        } else {
            input.add("0");
        }

        if (map.get("glCompanyName") != null) {
            input.add(map.get("glCompanyName"));
        } else {
            input.add("NULL");
        }

        if (map.get("businessUnitId") != null) {
            input.add(map.get("businessUnitId"));
        } else {
            input.add("NULL");
        }

        if (map.get("workflowId") != null) {
            input.add(map.get("workflowId"));
        } else {
            input.add("NULL");
        }
        if (map.get("workflowName") != null) {
            input.add(map.get("workflowName"));
        } else {
            input.add("NULL");
        }

        if (map.get("companyNo") != null) {
            input.add(map.get("companyNo"));
        } else {
            input.add("NULL");
        }
        if (map.get("itemNo") != null) {
            input.add(map.get("itemNo"));
        } else {
            input.add("NULL");
        }

        if (map.get("deductionLevel") != null) {
            input.add(map.get("deductionLevel"));
        } else {
            input.add("0");
        }

        if (map.get(ConstantUtil.CREATED_DATE) != null) {
            Date createdDate = (Date) map.get(ConstantUtil.CREATED_DATE);
            Timestamp time = new Timestamp(createdDate.getTime());
            input.add("'" + time + "'");
        } else {
            input.add("NULL");
        }

        if (map.get("companyName") != null) {
            input.add(map.get("companyName"));
        } else {
            input.add("NULL");
        }
        if (map.get("itemName") != null) {
            input.add(map.get("itemName"));
        } else {
            input.add("NULL");
        }

        if (map.get("deductionValue") != null) {
            input.add(map.get("deductionValue"));
        } else {
            input.add("NULL");
        }

        if (map.get("glDate") != null) {
            Date glDate = (Date) map.get("glDate");
            Timestamp time = new Timestamp(glDate.getTime());
            input.add("'" + time + "'");
        } else {
            input.add("NULL");
        }

        if (map.get("originalBatchId") != null) {
            input.add(map.get("originalBatchId"));
        } else {
            input.add("NULL");
        }
        if (map.get("brandName") != null) {
            input.add(map.get("brandName"));
        } else {
            input.add("NULL");
        }
        if (map.get("redemptionPeriodToDate") != null) {
            SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
            Date redemptionPeriod = dateF.parse(String.valueOf(map.get("redemptionPeriodToDate")));
            Timestamp time = new Timestamp(redemptionPeriod.getTime());
            input.add("'" + time + "'");
        } else {
            input.add("NULL");
        }
        if (map.get("postingIndicator") != null) {
            input.add(map.get("postingIndicator"));
        } else {
            input.add("0");
        }
        if (map.get("transactionLevel") != null) {
            input.add(map.get("transactionLevel"));
        } else {
            input.add("0");
        }
        if (map.get("accountCategory") != null) {
            input.add(map.get("accountCategory"));
        } else {
            input.add("0");
        }
        if (map.get("accountType") != null) {
            input.add(map.get("accountType"));
        } else {
            input.add("0");
        }
        if (map.get(ConstantUtil.ACCOUNT) != null) {
            input.add(map.get(ConstantUtil.ACCOUNT));
        } else {
            input.add("NULL");
        }
        if (map.get("redemptionPeriodToDate") != null) {
            SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
            Date redemptionPeriod = dateF.parse(String.valueOf(map.get("redemptionPeriodToDate")));
            Timestamp time = new Timestamp(redemptionPeriod.getTime());
            input.add("'" + time + "'");
        } else {
            input.add("NULL");
        }

        return input;
    }

    public Boolean checkETLRecords() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String query = "update ST_ARP_OUTBOUND SET ETL_CHECK_RECORD=1 WHERE USER_ID = '" + userId + "' AND SESSION_ID = " + sessionId + " AND CHECK_RECORD = 1";
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
        return count > 0;
    }

    public Boolean existsQuery() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID_ARP));
        String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_ARP_OUTBOUND WHERE USER_ID = '" + userId + "' AND SESSION_ID = " + sessionId + " AND ETL_CHECK_RECORD = 1";
        List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int c = Integer.valueOf(String.valueOf(count.get(0)));
        return c > 0;
    }

    public Boolean cffExistsQuery() {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = " + userId + " AND SESSION_ID = " + sessionId + " AND ETL_CHECK_RECORD = 1";
        List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int c = Integer.valueOf(String.valueOf(count.get(0)));
        return c > 0;
    }

    public Boolean checkCffETLRecords() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET ETL_CHECK_RECORD=1 WHERE USER_ID='" + userId + "' AND SESSION_ID = '" + sessionId + "' AND CHECK_RECORD = 1";
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
        return count > 0;
    }

    public void deleteTempTableValues() {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
            String deleteQuery = "DELETE FROM ST_CFF_OUTBOUND_MASTER WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
            HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }
    
    public void updateCheckRecord(String check, String cffId, String userId, String periodSid, String sessionId, String deductionId) {
        String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD=" + check + " WHERE CFF_DETAILS_SID=" + cffId + " AND "
                + " USER_ID=" + userId + " AND SESSION_ID=" + sessionId + " AND PERIOD_SID=" + periodSid + " AND RS_MODEL_SID='" + deductionId + "'";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void updateAllCheckRecord(String check, String userId, String sessionId) {
        String query = "UPDATE ST_CFF_OUTBOUND_MASTER SET CHECK_RECORD='" + check + "' WHERE "
                + " USER_ID=" + userId + " AND SESSION_ID=" + sessionId;
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    
    public static List excelSelectQuery(Set<Filter> criteria, List<SortByColumn> sortByColumn,String tableName) {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
            Map<String, String> ccfMap = new HashMap<>();
            loadfilterColumnMap(ccfMap);
            String query = QueryUtils.getExcelQuery();
            query += " WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
            query += getCustomFilterQuery(criteria, sortByColumn, false, ccfMap,tableName);
            List result = new ArrayList();
            result.add(query);
            return result;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_LIST;
        }
    }
    
     
     
     private static String getCustomFilterQuery(final Set<Container.Filter> filterSet, final List<SortByColumn> sortByColumns, boolean isCount, Map<String, String> filterColumnMap,String tableName) {
        String filterQuery = StringUtils.EMPTY;
        final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date startValue = (Date) stringFilter.getStartValue();
                    Date endValue = (Date) stringFilter.getEndValue();
                    if (startValue != null) {
                        filterQuery += " AND " + (filterColumnMap.get(stringFilter.getPropertyId().toString())) + " >= '" + startValue + "' ";
                    }
                    if (endValue != null) {
                        filterQuery += " AND " + (filterColumnMap.get(stringFilter.getPropertyId().toString())) + " <= '" + endValue + "' ";
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = dbDateFormat.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " > '" + filterString + "' ";
                        } else {
                            filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " < '" + filterString + "' ";
                        }
                    } else if (Compare.Operation.GREATER.toString().equals(operation.name())) {
                        filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " > '" + stringFilter.getValue() + "' ";
                    } else if (Compare.Operation.LESS.toString().equals(operation.name())) {
                        filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " < '" + stringFilter.getValue() + "' ";
                    } else if (Compare.Operation.EQUAL.toString().equals(operation.name())) {
                        filterQuery += " AND " + filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " = '" + stringFilter.getValue() + "' ";
                    }
                }
            }
        }
        String finalQuery = StringUtils.EMPTY;
        String order = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                    columnName = sortByColumn.getName();
                    orderByColumn = filterColumnMap.get(columnName);
                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn != null && !StringUtils.EMPTY.equals(orderByColumn)) {
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            } else {
                if(tableName.equals(ConstantUtil.ST_CFF_OUTBOUND)) {
                    order = " ORDER BY FINANCIAL_FORECAST_ID ASC";
                } else if(tableName.equals(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)){
                    order = " ORDER BY CUSTOMER_GTS_FORECAST_SID ASC";
                }                
            }
        }
        if (isCount) {
            finalQuery = filterQuery;
        } else {
            finalQuery = filterQuery + order;
        }
        return finalQuery;
    }
      private static String getARMFilterQuery(final Set<Container.Filter> filterSet, final List<SortByColumn> sortByColumns, boolean isCount, Map<String, String> filterColumnMap, String tableName) {
        String filterQuery = StringUtils.EMPTY;
     
        final SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (filterSet != null) {

            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";
                    filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                    if (("debit".equalsIgnoreCase(String.valueOf(stringFilter.getPropertyId())) || "credit".equalsIgnoreCase(String.valueOf(stringFilter.getPropertyId())))
                            && filterString.contains("$")) {
                        filterString = filterString.replace("$", StringUtils.EMPTY);
                    }
                    filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    Date startValue = (Date) stringFilter.getStartValue();
                    Date endValue = (Date) stringFilter.getEndValue();
                    if (startValue != null) {
                        String filterString = dbDateFormat.format(startValue);
                        filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                        filterQuery += (filterColumnMap.get(stringFilter.getPropertyId().toString())) + " >= '" + filterString + "' ";
                    }
                    if (endValue != null) {
                        String filterString = dbDateFormat.format(endValue);
                        filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                        filterQuery += (filterColumnMap.get(stringFilter.getPropertyId().toString())) + " <= '" + filterString + "' ";
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = dbDateFormat.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                            filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                            filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " >= '" + filterString + "' ";
                        } else {
                            filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                            filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " <= '" + filterString + "' ";
                        }
                    } else if (Compare.Operation.GREATER.toString().equals(operation.name())) {
                        filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                        filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " > '" + stringFilter.getValue() + "' ";
                    } else if (Compare.Operation.LESS.toString().equals(operation.name())) {
                        filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                        filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " < '" + stringFilter.getValue() + "' ";
                    } else if (Compare.Operation.EQUAL.toString().equals(operation.name())) {
                        filterQuery += filterQuery.isEmpty() ? StringUtils.EMPTY : " AND ";
                        filterQuery += filterColumnMap.get(String.valueOf(stringFilter.getPropertyId())) + " = '" + stringFilter.getValue() + "' ";
                    }
                }
            }
        }
        String finalQuery = StringUtils.EMPTY;
        String order = StringUtils.EMPTY;
        if (!isCount) {
            boolean sortOrder = false;
            String columnName = null;
            String orderByColumn = null;
            if (sortByColumns != null) {
                for (final Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn sortByColumn = (SortByColumn) iterator.next();

                    columnName = sortByColumn.getName();
                    orderByColumn = filterColumnMap.get(columnName);
                    if (sortByColumn.getType() == SortByColumn.Type.ASC) {
                        sortOrder = false;
                    } else {
                        sortOrder = true;
                    }
                }
            }
            if (orderByColumn != null && !StringUtils.EMPTY.equals(orderByColumn)) {
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            } else {
                order = ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName) ? " ORDER BY A.ADJUSTMENT_GTN_DETAIL_SID ASC " : " ORDER BY A.ADJUSTMENT_RESERVE_DETAIL_SID ASC ";
            }
        }
        if (isCount) {
            finalQuery = filterQuery;
        } else {
            finalQuery = filterQuery + order;
        }
        return finalQuery;
    }
      
    private static void loadfilterColumnMap(Map<String, String> filterColumnMap) {
        if (filterColumnMap.isEmpty()) {
            filterColumnMap.put("financialForecastId", "FINANCIAL_FORECAST_ID");
            filterColumnMap.put("financialForecastName", "FINANCIAL_FORECAST_NAME");
            filterColumnMap.put("type", "CFT.DESCRIPTION");
            filterColumnMap.put("projectId", "PROJECT_ID");
            filterColumnMap.put("projectionName", "PROJECTION_NAME");
            filterColumnMap.put("year", "YEAR");
            filterColumnMap.put("month", "MONTH");
            filterColumnMap.put("contractId", "CONTRACT_ID");
            filterColumnMap.put("contractNo", "CONTRACT_NO");
            filterColumnMap.put("contractName", "CONTRACT_NAME");
            filterColumnMap.put("contractType", "HT1.DESCRIPTION");
            filterColumnMap.put("contractHolderId", "CONTRACT_HOLDER_ID");
            filterColumnMap.put("contractHolderNo", "CONTRACT_HOLDER_NO");
            filterColumnMap.put("contractHolderName", "CONTRACT_HOLDER_NAME");
            filterColumnMap.put("customerId", "CUSTOMER_ID");
            filterColumnMap.put("customerNo", "CUSTOMER_NO");
            filterColumnMap.put("customerName", "CUSTOMER_NAME");
            filterColumnMap.put("itemId", "ITEM_ID");
            filterColumnMap.put("itemNo", "ITEM_NO");
            filterColumnMap.put("itemName", "ITEM_NAME");
            filterColumnMap.put("salesDollars", "SALES_DOLLARS");
            filterColumnMap.put("salesUnits", "SALES_UNITS");
            filterColumnMap.put("salesInclusion", "SALES_INCLUSION");
            filterColumnMap.put("deductionId", "DEDUCTION_ID");
            filterColumnMap.put("deductionNo", "DEDUCTION_NO");
            filterColumnMap.put("deductionName", "DEDUCTION_NAME");
            filterColumnMap.put("deductionCategory", "HT2.DESCRIPTION");
            filterColumnMap.put("deductionType", "HT3.DESCRIPTION");
            filterColumnMap.put("deductionProgram", "HT4.DESCRIPTION");
            filterColumnMap.put("deductionInclusion", "HT5.DESCRIPTION");
            filterColumnMap.put("deductionCategory1", "HT6.DESCRIPTION");
            filterColumnMap.put("deductionCategory2", "HT7.DESCRIPTION");
            filterColumnMap.put("deductionCategory3", "HT8.DESCRIPTION");
            filterColumnMap.put("deductionCategory4", "HT9.DESCRIPTION");
            filterColumnMap.put("deductionCategory5", "HT10.DESCRIPTION");
            filterColumnMap.put("deductionCategory6", "HT11.DESCRIPTION");
            filterColumnMap.put("deductionDollars", "DEDUCTION_DOLLARS");
            filterColumnMap.put("deductionRate", "DEDUCTION_RATE");
            filterColumnMap.put("deductionPerUnit", "DEDUCTION_PER_UNIT");
            filterColumnMap.put("netSalesDollar", "NET_SALES_DOLLAR");
            filterColumnMap.put("cogsAmount", "COGS_AMOUNT");
            filterColumnMap.put("cogsPerUnit", "COGS_PER_UNIT");
            filterColumnMap.put("netProfitDollars", "NET_PROFIT_DOLLARS");
            filterColumnMap.put("netProfitPerUnit", "NET_PROFIT_PER_UNIT");
            filterColumnMap.put("companyId", "COMPANY_ID");
            filterColumnMap.put("companyNo", "COMPANY_NO");
            filterColumnMap.put("companyName", "COMPANY_NAME");
            filterColumnMap.put("businessUnitId", "BUSINESS_UNIT_ID");
            filterColumnMap.put("businessUnitNo", "BUSINESS_UNIT_NO");
            filterColumnMap.put("businessUnitName", "BUSINESS_UNIT_NAME");
            filterColumnMap.put("financialForecastCreationDate", "FINANCIAL_FORECAST_CREATION_DATE");
            filterColumnMap.put("financialForecastApprovalDate", "FINANCIAL_FORECAST_APPROVAL_DATE");
            filterColumnMap.put("outboundStatus", "OUTBOUND_STATUS");
            filterColumnMap.put("originalBatchId", "ORIGINAL_BATCH_ID");
        }
    }
    
    public List cffSearch(Map<Object, Object> searchValues, Boolean isCount, Set<Filter> filter, List<SortByColumn> sortByColumn, int start, int end, boolean isFirstSearch,String tableName) {
        String query = StringUtils.EMPTY;
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        if (isFirstSearch) {
            query = SQlUtil.getQuery("cff-outbound-insert-query");
            query = query.replace("?USERID", userId);
            query = query.replace("?SESSIONID", sessionId);
            query = query.replace("?SEARCHFILTER", searchValues.isEmpty()?"":CommonLogic.dataForCFFoutbound(searchValues));
        }
        query += SQlUtil.getQuery("cff-outbound-search-query");
        query = isCount ? query.replace("?COUNT", " COUNT(*) ") : query.replace("?COUNT", SQlUtil.getQuery("search-select"));
        Map<String, String> ccfMap = new HashMap<>();
        loadfilterColumnMap(ccfMap);
        query += " WHERE ST.USER_ID = " + userId + " AND ST.SESSION_ID =" + sessionId + " ";
        query += getCustomFilterQuery(filter, sortByColumn, isCount, ccfMap,tableName);
        if (!isCount) {
            query += " OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY ; ";
        }
        List result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return result;
    }
    
    public static List gtnSearch(Map<Object, Object> searchValues, Boolean isCount, Set<Filter> filter, List<SortByColumn> sortByColumn, int start, int end, boolean isFirstSearch,String tableName,boolean isExcel) {
        String query = StringUtils.EMPTY;
        String insertQuery = StringUtils.EMPTY;
        String searchQuery = StringUtils.EMPTY;
        List result=null;
        Connection connection = null;
         try {
        connection=SysDataSourceConnection.getConnection();
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        query = SQlUtil.getQuery("arm_outbound_search_query");
        if(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)){
           insertQuery=SQlUtil.getQuery("temp_insert_gtn") ;
           searchQuery=SQlUtil.getQuery("Search_query_gtn") ;
        }else{
            insertQuery=SQlUtil.getQuery("temp_insert_reserve");
            searchQuery=SQlUtil.getQuery("select_query_reserve");
        }
        query=query.replace("[$SEARCH_QUERY]",  searchQuery);
        query=query.replace("[$TEMP_INSERTION]", isCount && isFirstSearch? insertQuery: " ");
        query=query.replace("[$SCHEMA_NAME]",connection.getCatalog());
        if(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)){
           query=query.replace("[$CHECK_RECORD]" , !isExcel ?",ST.CHECK_RECORD " : StringUtils.EMPTY);
           query=query.replace("[$COMPANY]" , isExcel ?" A.GL_COMPANY_ID, " : StringUtils.EMPTY);
           query=query.replace("[$GL_COMPANY_NAME]" , isExcel ?"QUOTENAME(GL_COMPANY_NAME, CHAR(34)) " : " GL_COMPANY_NAME ");//To get the value with comma in BCP
        }else{
            query=query.replace("[$CHECK_RECORD]" , !isExcel ?",ST.CHECK_RECORD , A.COMPANY " : StringUtils.EMPTY);
            query=query.replace("[$COMPANY]" , isExcel ?" A.COMPANY, " : StringUtils.EMPTY);
        }
        query=CommonLogic.dataForARMoutbound(searchValues,query,userId,sessionId);
        loadReservefilterColumnMap(reserveMap);
        loadGtnfilterColumnMap(gtnMap);
        String filterQuery = getARMFilterQuery(filter, sortByColumn, isCount, ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)?gtnMap :reserveMap,tableName);
        query = query.replace("[$FILTER_QUERY]", filter.size()!=0 ? " WHERE "+filterQuery:filterQuery);
        query = query.replace("[$START_OFFSET]",!isCount && !isExcel ? " OFFSET " + start + " ROWS FETCH NEXT " + end + " ROWS ONLY" :  StringUtils.EMPTY);
        if(!isExcel){
            result = HelperTableLocalServiceUtil.executeSelectQuery(query);
        } else {
            query = ConnectionUtils.getInstance().makeExecutable(query);
            result = Arrays.asList(new String[]{query});
            }
         } catch (Exception e) {
            LOGGER.error("Error :" + e + " While Executing " + query);
            return null;
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return result;
    }
    
    public void deleteGTNTempTableValues() {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
            String deleteQuery = "DELETE FROM ST_ADJUSTMENT_GTN_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
            HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public Boolean checkGTNETLRecords() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "UPDATE ST_ADJUSTMENT_GTN_DETAIL SET ETL_CHECK_RECORD=1 WHERE USER_ID='" + userId + "' AND SESSION_ID = '" + sessionId + "' AND CHECK_RECORD = 1";
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
        return count > 0;
    }

    public void deleteReserveTempTableValues() {
        try {
            final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
            final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
            String deleteQuery = "DELETE FROM ST_ADJUSTMENT_RESERVE_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
            HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public Boolean checkReserveETLRecords() {
        final String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        final String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "UPDATE ST_ADJUSTMENT_RESERVE_DETAIL SET ETL_CHECK_RECORD=1 WHERE USER_ID='" + userId + "' AND SESSION_ID = '" + sessionId + "' AND CHECK_RECORD = 1";
        int count = HelperTableLocalServiceUtil.executeUpdateQueryCount(query);
        LOGGER.debug("checkReserveETLRecords check record----"+query);
        return count > 0;
    }

    public Boolean GtnExistsQuery() {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_ADJUSTMENT_GTN_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID = " + sessionId + " AND ETL_CHECK_RECORD = 1";
        List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int c = Integer.valueOf(String.valueOf(count.get(0)));
        return c > 0;
    }

    public Boolean ReserveExistsQuery() {
        String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.USERID));
        String sessionId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.SESSIONID));
        String query = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM ST_ADJUSTMENT_RESERVE_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID = " + sessionId + " AND ETL_CHECK_RECORD = 1";
        List count = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int c = Integer.valueOf(String.valueOf(count.get(0)));
        return c > 0;
    }
    
    public String getCompanyNameDescription(String id) {
        String query = "SELECT COMPANY_NAME FROM COMPANY_MASTER WHERE COMPANY_MASTER_SID = " + id;
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null || list.isEmpty() ? StringUtils.EMPTY : String.valueOf(list.get(0));
    }

    public String getCompanyIdDescription(String id) {

        String query = "SELECT COMPANY_ID FROM COMPANY_MASTER WHERE COMPANY_MASTER_SID =" + id;
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null || list.isEmpty() ? StringUtils.EMPTY : String.valueOf(list.get(0));
    }

    public void adjustmentDetailsSearchRestriction(String tableName, Map<Object, Object> searchValues) {
        if (searchValues.containsKey("transactionLevel")) {
            searchValues.remove("transactionLevel");
}
        if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {

            if (searchValues.containsKey("businessUnitId")) {
                searchValues.put("division", getCompanyNameDescription(String.valueOf(searchValues.get("businessUnitId"))));
                searchValues.remove("businessUnitId");
            }
            if (searchValues.containsKey("glCompanyName")) {
                searchValues.put("businessUnitId", getCompanyIdDescription(String.valueOf(searchValues.get("glCompanyName"))));
                searchValues.remove("glCompanyName");
            }

        } else if (tableName.equals(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL)) {
            if (searchValues.containsKey("businessUnitId")) {
                searchValues.put("businessUnitId", getCompanyNameDescription(String.valueOf(searchValues.get("businessUnitId"))));
            }
            if (searchValues.containsKey("glCompanyName")) {
                searchValues.put("glCompanyName", getCompanyNameDescription(String.valueOf(searchValues.get("glCompanyName"))));
            }
            if (searchValues.containsKey("deductionLevel") || searchValues.containsKey("deductionValue")) {
                searchValues.remove("deductionLevel");
                searchValues.remove("deductionValue");
            }
        }
    }
    
    void loadFilterMap() {
        if (filterMap.isEmpty()) {
            filterMap.put("current_Year_Jan", ",JAN");
            filterMap.put("current_Year_Feb", ",FEB");
            filterMap.put("current_Year_Mar", ",MAR");
            filterMap.put("current_Year_Apr", ",APR");
            filterMap.put("current_Year_May", ",MAY");
            filterMap.put("current_Year_June", ",JUN");
            filterMap.put("current_Year_July", ",JUL");
            filterMap.put("current_Year_Aug", ",AUG");
            filterMap.put("current_Year_Sep", ",SEP");
            filterMap.put("current_Year_Oct", ",OCT");
            filterMap.put("current_Year_Nov", ",NOV");
            filterMap.put("current_Year_Dec", ",DEC");

            filterMap.put("current_Year_1_Jan", ",JAN");
            filterMap.put("current_Year_1_Feb", ",FEB");
            filterMap.put("current_Year_1_Mar", ",MAR");
            filterMap.put("current_Year_1_Apr", ",APR");
            filterMap.put("current_Year_1_May", ",MAY");
            filterMap.put("current_Year_1_June", ",JUN");
            filterMap.put("current_Year_1_July", ",JUL");
            filterMap.put("current_Year_1_Aug", ",AUG");
            filterMap.put("current_Year_1_Sep", ",SEP");
            filterMap.put("current_Year_1_Oct", ",OCT");
            filterMap.put("current_Year_1_Nov", ",NOV");
            filterMap.put("current_Year_1_Dec", ",DEC");

            filterMap.put("current_Year_2_Jan", ",JAN");
            filterMap.put("current_Year_2_Feb", ",FEB");
            filterMap.put("current_Year_2_Mar", ",MAR");
            filterMap.put("current_Year_2_Apr", ",APR");
            filterMap.put("current_Year_2_May", ",MAY");
            filterMap.put("current_Year_2_June", ",JUN");
            filterMap.put("current_Year_2_July", ",JUL");
            filterMap.put("current_Year_2_Aug", ",AUG");
            filterMap.put("current_Year_2_Sep", ",SEP");
            filterMap.put("current_Year_2_Oct", ",OCT");
            filterMap.put("current_Year_2_Nov", ",NOV");
            filterMap.put("current_Year_2_Dec", ",DEC");
            filterMap.put("arp_Creation_Date", ",ARP_CREATION_DATE");
            filterMap.put(ConstantUtil.ACCOUNT, ",ACCOUNT");
            filterMap.put("account_Type", ",ACCOUNT_TYPE");
            filterMap.put("outbound_Status", ",OUTBOUND_STATUS");
            filterMap.put("original_Batch_ID", ",ORIGINAL_BATCH_ID");
        }
  }
    private String getFilterForARPOutbound(Set<Filter> filterSet) {
        String filterCondition=StringUtils.EMPTY;
        String filterString=StringUtils.EMPTY;
        for (Container.Filter filter : filterSet) {
            if (filter instanceof SimpleStringFilter) {
                filterString = String.valueOf(((SimpleStringFilter) filter).getPropertyId());
            } else if (filter instanceof Between) {
                filterString = String.valueOf(((Between) filter).getPropertyId());
            } else if (filter instanceof Compare) {
                filterString = String.valueOf(((Compare) filter).getPropertyId());
            }
            if (filterMap.containsKey(filterString)) {
                filterCondition += filterMap.get(filterString);
            }
        }
        return filterCondition;    
       
    } 
    public static List excelQueryForGTSForecast(Set<Filter> criteria, List<SortByColumn> sortByColumn, String tableName,Map<Object, Object> searchValues) {
        try (Connection connection = SysDataSourceConnection.getConnection()) {
            Map<String, String> gtsMap = new HashMap<>();
            loadGTSfilterColumnMap(gtsMap);
            String query = QueryUtils.getGTSExcelQuery();
            query = query + getSearchCriteria(searchValues);
            if (searchValues.isEmpty()) {
                query = query.replace("WHERE", "");
            }
            query += getCustomFilterQuery(criteria, sortByColumn, false, gtsMap, tableName);
            List result = new ArrayList();
            query = query.replace("?", connection.getCatalog());
            result.add(query);
            return result;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_LIST;
        }
    }
    private static void loadGTSfilterColumnMap(Map<String, String> filterColumnMap) {
        if (filterColumnMap.isEmpty()) {
            filterColumnMap.put("price", "PRICE");
            filterColumnMap.put("forecastYear", "FORECAST_YEAR");
            filterColumnMap.put("deductionAmount", "DEDUCTION_AMOUNT");
            filterColumnMap.put("deductionId", "DEDUCTION_ID");
            filterColumnMap.put("forecastDate", "FORECAST_DATE");
            filterColumnMap.put("itemId", "ITEM_ID");
            filterColumnMap.put("source", "SOURCE");
            filterColumnMap.put("salesAmount", "SALES_AMOUNT");
            filterColumnMap.put("udc6", "HT9.DESCRIPTION");
            filterColumnMap.put("udc5", "HT8.DESCRIPTION");
            filterColumnMap.put("udc4", "HT7.DESCRIPTION");
            filterColumnMap.put("deductionType", "HT2.DESCRIPTION");
            filterColumnMap.put("udc1", "HT4.DESCRIPTION");
            filterColumnMap.put("units", "UNITS");
            filterColumnMap.put("deductionRate", "DEDUCTION_RATE");
            filterColumnMap.put("udc2", "HT5.DESCRIPTION");
            filterColumnMap.put("udc3", "HT6.DESCRIPTION");
            filterColumnMap.put("country", "COUNTRY");
            filterColumnMap.put("companyId", "COMPANY_ID");
            filterColumnMap.put("forecastValueType", "FORECAST_VALUE_TYPE");
            filterColumnMap.put("deductionCategory", "HT1.DESCRIPTION");
            filterColumnMap.put("adjustmentCode", "ADJUSTMENT_CODE");
            filterColumnMap.put("deductionProgramType", "HT3.DESCRIPTION");
            filterColumnMap.put("salesInclusion", "SALES_INCLUSION");
            filterColumnMap.put("forecastVer", "FORECAST_VER");
            filterColumnMap.put("priceType", "PRICE_TYPE");
            filterColumnMap.put("forecastMonth", "FORECAST_MONTH");
            filterColumnMap.put("deductionInclusion", "DEDUCTION_INCLUSION");
            filterColumnMap.put("segment", "SEGMENT");
            filterColumnMap.put("brand", "BRAND");
            filterColumnMap.put("batchId", "BATCH_ID");
            filterColumnMap.put("forecastName", "FORECAST_NAME");
            filterColumnMap.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
            filterColumnMap.put("createdBy", "CREATED_BY");
            filterColumnMap.put(ConstantUtil.CREATED_DATE, "CREATED_DATE");
            filterColumnMap.put("modifiedBy", "MODIFIED_BY");
            filterColumnMap.put("modifiedDate", "MODIFIED_DATE");
            filterColumnMap.put("customerGtsForecastIntfId", "CUSTOMER_GTS_FORECAST_INTF_ID");
        }
    }
    private static void loadReservefilterColumnMap(Map<String, String> filterColumnMap) {
        if (filterColumnMap.isEmpty()) {
            filterColumnMap.put("adjustmentType", "AAC.TRANSACTION_NAME");
            filterColumnMap.put("glCompanyName", "A.COMPANY");// GAL-8610
            filterColumnMap.put("division", "DIVISION");
            filterColumnMap.put("businessUnitId", "A.BUSINESS_UNIT");// GAL-8610
            filterColumnMap.put(ConstantUtil.ACCOUNT, "A.ACCOUNT");// GAL-8610
            filterColumnMap.put("journalName", "JOURNAL_NAME");
            filterColumnMap.put("journalDescription", "JOURNAL_DESCRIPTION");
            filterColumnMap.put("brand", "A.BRAND");// GAL-8610
            filterColumnMap.put("debit", "DEBIT");
            filterColumnMap.put("credit", "CREDIT");
            filterColumnMap.put("currency", "HT.CURRENCY");
            filterColumnMap.put("accountingDate", "ACCOUNTING_DATE");
            filterColumnMap.put("redemptionPeriod", "A.REDEMPTION_PERIOD");
            filterColumnMap.put("postingIndicator", "POSTING_INDICATOR");
            filterColumnMap.put("costCenter", "COST_CENTER");
            filterColumnMap.put("project", "PROJECT");
            filterColumnMap.put("future1", "FUTURE_1");
            filterColumnMap.put("future2", "FUTURE_2");
            filterColumnMap.put("category", "CATEGORY");
            filterColumnMap.put("balanceType", "BALANCE_TYPE");
            filterColumnMap.put("database", "ARD_DB");
            filterColumnMap.put("dataAccessSet", "DATA_ACCESS_SET");
            filterColumnMap.put("chartOfAccounts", "CHART_OF_ACCOUNTS");
            filterColumnMap.put("ledger", "LEDGER");
            filterColumnMap.put("source", "SOURCE");
            filterColumnMap.put("batchName", "BATCH_NAME");
            filterColumnMap.put("batchId", "BATCH_ID");
            filterColumnMap.put("reverseJournal", "REVERSE_JOURNAL");
            filterColumnMap.put("reversalPeriodDate", "REVERSAL_PERIOD_DATE");
            filterColumnMap.put("lineDescription", "LINE_DESCRIPTION");
            filterColumnMap.put("udc1", "HT.UDC_1");
            filterColumnMap.put("udc2", "HT.UDC_2");
            filterColumnMap.put("udc3", "HT.UDC_3");
            filterColumnMap.put("udc4", "HT.UDC_4");
            filterColumnMap.put("udc5", "HT.UDC_5");
            filterColumnMap.put("udc6", "HT.UDC_6");
            filterColumnMap.put("accountCategory", "HT.ACCOUNT_CATEGORY");
            filterColumnMap.put("accountType", "HT.ACCOUNT_TYPE");
            filterColumnMap.put("adjustmentLevel", "HT.ADJUSTMENT_LEVEL");
            filterColumnMap.put("adjustmentCreatedDate", "ADJUSTMENT_CREATED_DATE");
            filterColumnMap.put("originalBatchId", "ORIGINAL_BATCH_ID");
            filterColumnMap.put("workflowId", "A.WORKFLOW_ID");// GAL-8610
            filterColumnMap.put("workflowName", "WORKFLOW_NAME");
            filterColumnMap.put("workflowCreatedBy", "WORKFLOW_CREATED_BY");
            filterColumnMap.put("workflowCreatedDate", "WORKFLOW_CREATED_DATE");
            filterColumnMap.put("workflowApprovedBy", "WORKFLOW_APPROVED_BY");
            filterColumnMap.put("workflowApprovedDate", "WORKFLOW_APPROVED_DATE");
            filterColumnMap.put("calculationPeriod", "A.CALCULATION_PERIOD");// GAL-8610
}
    }
    private static void loadGtnfilterColumnMap(Map<String, String> filterColumnMap) {
        if (filterColumnMap.isEmpty()) {
            filterColumnMap.put("adjustmentType", "AAC.TRANSACTION_NAME");
             filterColumnMap.put("accountCategory", "HT.ACCOUNT_CATEGORY");
            filterColumnMap.put("accountType", "HT.ACCOUNT_TYPE");
            filterColumnMap.put(ConstantUtil.ACCOUNT, "A.ACCOUNT");// GAL-8610
            filterColumnMap.put("adjustmentLevel", "HT.ADJUSTMENT_LEVEL");
            filterColumnMap.put("businessUnitId", "A.BUSINESS_UNIT_ID");// GAL-8610
            filterColumnMap.put("businessUnitNo", "BUSINESS_UNIT_NO");
            filterColumnMap.put("businessUnitName", "BUSINESS_UNIT_NAME");
            filterColumnMap.put("glCompanyId", "A.GL_COMPANY_ID");// GAL-8610
            filterColumnMap.put("glCompanyNo", "GL_COMPANY_NO");
            filterColumnMap.put("glCompanyName", "GL_COMPANY_NAME");
            filterColumnMap.put("glYear", "GL_YEAR");
            filterColumnMap.put("glMonth", "GL_MONTH");
            filterColumnMap.put("glDate", "GL_DATE");
            filterColumnMap.put("reversalPeriodDate", "REVERSAL_PERIOD_DATE");
            filterColumnMap.put("adjustmentCreatedDate", "ADJUSTMENT_CREATED_DATE");
            filterColumnMap.put("deductionAmount", "DEDUCTION_AMOUNT");
            filterColumnMap.put("deductionRate", "DEDUCTION_RATE");
            filterColumnMap.put("contractId", "A.CONTRACT_ID");// GAL-8610
            filterColumnMap.put("contractNo", "CONTRACT_NO");
            filterColumnMap.put("contractName", "CONTRACT_NAME");
            filterColumnMap.put("companyId", "A.COMPANY_ID");// GAL-8610
            filterColumnMap.put("companyNo", "COMPANY_NO");
            filterColumnMap.put("companyName", "COMPANY_NAME");
            filterColumnMap.put("itemId", "A.ITEM_ID");// GAL-8610
            filterColumnMap.put("itemNo", "ITEM_NO");
            filterColumnMap.put("itemName", "ITEM_NAME");
            filterColumnMap.put("brandId", "BRAND_ID");
            filterColumnMap.put("brandName", "BRAND_NAME");
            filterColumnMap.put("deductionId", "A.DEDUCTION_ID");// GAL-8610
            filterColumnMap.put("deductionNo", "DEDUCTION_NO");
            filterColumnMap.put("deductionName", "DEDUCTION_NAME");
            filterColumnMap.put("deductionCategory", "HT.DEDUCTION_CATEGORY");
            filterColumnMap.put("deductionType", "HT.DEDUCTION_TYPE");
            filterColumnMap.put("deductionProgram", "HT.DEDUCTION_PROGRAM");
            filterColumnMap.put("deductionInclusion", "DEDUCTION_INCLUSION");
            filterColumnMap.put("deductionUdc1", "HT.DEDUCTION_UDC_1");
            filterColumnMap.put("deductionUdc2", "HT.DEDUCTION_UDC_2");
            filterColumnMap.put("deductionUdc3", "HT.DEDUCTION_UDC_3");
            filterColumnMap.put("deductionUdc4", "HT.DEDUCTION_UDC_4");
            filterColumnMap.put("deductionUdc5", "HT.DEDUCTION_UDC_5");
            filterColumnMap.put("deductionUdc6", "HT.DEDUCTION_UDC_6");
            filterColumnMap.put("calculationPeriod", "A.CALCULATION_PERIOD");// GAL-8610
            filterColumnMap.put("originalBatchId", "ORIGINAL_BATCH_ID");
            filterColumnMap.put("workflowId", "A.WORKFLOW_ID");// GAL-8610
            filterColumnMap.put("workflowName", "WORKFLOW_NAME");
            filterColumnMap.put("workflowCreatedBy", "WORKFLOW_CREATED_BY");
            filterColumnMap.put("workflowCreatedDate", "WORKFLOW_CREATED_DATE");
            filterColumnMap.put("workflowApprovedBy", "WORKFLOW_APPROVED_BY");
            filterColumnMap.put("workflowApprovedDate", "WORKFLOW_APPROVED_DATE");
            filterColumnMap.put("costCenter", "COST_CENTER");
            filterColumnMap.put("project", "PROJECT");
            filterColumnMap.put("future1", "FUTURE_1");
            filterColumnMap.put("future2", "FUTURE_2");
            filterColumnMap.put("udc1", "HT.UDC_1");
            filterColumnMap.put("udc2", "HT.UDC_2");
            filterColumnMap.put("udc3", "HT.UDC_3");
            filterColumnMap.put("udc4", "HT.UDC_4");
            filterColumnMap.put("udc5", "HT.UDC_5");
            filterColumnMap.put("udc6", "HT.UDC_6");
            filterColumnMap.put("postingIndicator", "POSTING_INDICATOR");
            filterColumnMap.put("redemptionPeriod", "A.REDEMPTION_PERIOD");//GAL-8610
        }
    }
    public static String getSearchCriteria(Map<Object, Object> criteria) {

        String searchCriteria = StringUtils.EMPTY;
        String percentage = "";
        Map<Object, Object> dbNameMap = new HashMap();
        loadGtsForecastColumnMap(dbNameMap);
        for (Map.Entry<Object, Object> onn : criteria.entrySet()) {
            percentage = String.valueOf(criteria.get(onn.getKey()));
            percentage = percentage.replace("*", "%");
            if (percentage.equals("%")) {
                searchCriteria = searchCriteria + " AND " + dbNameMap.get(onn.getKey()) + " IS NULL OR " + dbNameMap.get(onn.getKey()) + " like '" + percentage + "'";
            } else {
                searchCriteria = searchCriteria + " AND " + dbNameMap.get(onn.getKey()) + " like '" + percentage + "'";
            }
        }
        if(!criteria.isEmpty()){
        searchCriteria = searchCriteria.substring(4, searchCriteria.length());
        }
        return searchCriteria;
    }

    public static void loadGtsForecastColumnMap(Map<Object, Object> dbColumnName) {
        dbColumnName.put(ConstantUtil.COMPANY_ID, "COMPANY_ID");
        dbColumnName.put(ConstantUtil.FORECAST_YEAR, "FORECAST_YEAR");
        dbColumnName.put(ConstantUtil.FORECAST_MONTH, "FORECAST_MONTH");
        dbColumnName.put(ConstantUtil.ITEMID, "ITEM_ID");
        dbColumnName.put(ConstantUtil.DEDUCTION_CATEGORY, "DEDUCTION_CATEGORY");
        dbColumnName.put(ConstantUtil.FORECAST_NAME, "FORECAST_NAME");
        dbColumnName.put(ConstantUtil.DEDUCTION_PROGRAM_TYPE, "DEDUCTION_PROGRAM_TYPE");
        dbColumnName.put(ConstantUtil.FORECAST_VER, "FORECAST_VER");
        dbColumnName.put(ConstantUtil.DEDUCTION_TYPE, "DEDUCTION_TYPE");
        dbColumnName.put(ConstantUtil.BATCH_ID, "BATCH_ID");
        dbColumnName.put(ConstantUtil.COUNTRY, "COUNTRY");
        dbColumnName.put(ConstantUtil.DEDUCTION_ID, "DEDUCTION_ID");
    }
}
