/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic;

import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.deductioncalendar.ui.util.HeaderUtils;
import com.stpl.app.util.Constants;
import static com.stpl.app.util.GeneralCommonUtils.ZERO;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CommonUtil;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.vaadin.v7.data.util.BeanItem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manasa
 */
public class SelectionLogic {

    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

    private static final HashMap<String, String> criteria = new HashMap<String, String>();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectionLogic.class);
    /**
     * The format double.
     */
    private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("###,##0.000000");
    
    public static final CommonDao DAO = CommonDaoImpl.getInstance();
    
    /**
     *
     * @param selectionDTO
     * @param start
     * @param offset
     * @param isCount
     * @param filterSet
     * @param columns
     * @return
     */
    public Object getCustomerSearchResult(final SelectionDTO selectionDTO, final int start, final int offset, final boolean isCount,
            final Set<Container.Filter> filterSet, final List<SortByColumn> columns, final String availableOrselected) {

        final Object object;

        Map<String, Object> parameters = new HashMap<>();

        DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT);

        String columnName;
        String dbColumnName;
        StringBuilder queryString = new StringBuilder();
        if (availableOrselected.equals("available")) {
            queryString = new StringBuilder(SQLUtil.getQuery(isCount ? "available-customer-search-count" : "available-customer-search-results"));

            if (StringUtils.isNotBlank(selectionDTO.getState()) && !Constants.NULL.equals(selectionDTO.getState())) {
                String state = selectionDTO.getState();
                state = CommonUtil.buildSearchCriteria(state);
                queryString.append(" AND CM.\"STATE\" LIKE '").append(state).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getCity()) && !Constants.NULL.equals(selectionDTO.getCity())) {
                String city = selectionDTO.getCity();
                city = CommonUtil.buildSearchCriteria(city);
                queryString.append(" AND CM.CITY LIKE '").append(city).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getZipCode()) && !Constants.NULL.equals(selectionDTO.getZipCode())) {
                String zipCode = selectionDTO.getZipCode();
                zipCode = CommonUtil.buildSearchCriteria(zipCode);
                queryString.append(" AND CM.ZIP_CODE LIKE '").append(zipCode).append("' ");
            }

            queryString.append("WHERE  CM.INBOUND_STATUS <> 'D' ");

            if (StringUtils.isNotBlank(selectionDTO.getCustomerNo()) && !Constants.NULL.equals(selectionDTO.getCustomerNo())) {
                String customerNo = selectionDTO.getCustomerNo();
                customerNo = CommonUtil.buildSearchCriteria(customerNo);
                queryString.append(" AND CM.COMPANY_NO LIKE '").append(customerNo).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getCustomerName()) && !Constants.NULL.equals(selectionDTO.getCustomerName())) {
                String customerName = selectionDTO.getCustomerName();
                customerName = CommonUtil.buildSearchCriteria(customerName);
                queryString.append(" AND CM.COMPANY_NAME LIKE '").append(customerName).append("' ");
            }

            if (selectionDTO.getTradeClass() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getTradeClass()))) {
                try {
                    String tradeClassValue = String.valueOf(selectionDTO.getTradeClass());
                    if (tradeClassValue != null && !tradeClassValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(ConstantsUtils.COMPANY_TRADE_CLASS_LIST, tradeClassValue);
                        if (helperId != 0) {
                            queryString.append(" AND HT_CTS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                        }
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            if (selectionDTO.getCustomerType() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getCustomerType()))) {
                try {
                    String customerTypeValue = String.valueOf(selectionDTO.getCustomerType());
                    if (customerTypeValue != null && !customerTypeValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(CommonUIUtils.COMPANY_TYPE, customerTypeValue);
                        queryString.append(" AND HT_CT.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            if (selectionDTO.getCustomerStatus() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getCustomerStatus()))) {
                try {
                    String customerStatusValue = String.valueOf(selectionDTO.getCustomerStatus());
                    if (customerStatusValue != null && !customerStatusValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(CommonUIUtils.STATUS, customerStatusValue);
                        queryString.append(" AND HT_CS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        } else if (availableOrselected.equals("selected")) {
            queryString = new StringBuilder(SQLUtil.getQuery(isCount ? "selected-customer-search-count" : "selected-customer-search-results"));
            queryString.append("WHERE  CM.INBOUND_STATUS <> 'D' ");

            queryString.append(" AND ST.USER_ID = ").append(selectionDTO.getUserId()).append(" AND ST.SESSION_ID = '").append(selectionDTO.getSessionId()).append("'");
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if (ConstantsUtils.CUSTOMER_TYPE.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.COMPANY_TYPE, filterValue);
                                parameters.put(ConstantsUtils.CUSTOMER_TYPE, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.CUSTOMER_STATUS.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.STATUS, filterValue);
                                parameters.put(ConstantsUtils.CUSTOMER_STATUS, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(ConstantsUtils.COMPANY_TRADE_CLASS_LIST, filterValue);
                                parameters.put(ConstantsUtils.TRADE_CLASS, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC1.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC1, filterValue);
                                parameters.put(ConstantsUtils.UDC1, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC2.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC2, filterValue);
                                parameters.put(ConstantsUtils.UDC2, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC3.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC3, filterValue);
                                parameters.put(ConstantsUtils.UDC3, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC4.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC4, filterValue);
                                parameters.put(ConstantsUtils.UDC4, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC5.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC5, filterValue);
                                parameters.put(ConstantsUtils.UDC5, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.UDC6.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.UDC6, filterValue);
                                parameters.put(ConstantsUtils.UDC6, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.ORGANISATION_KEY.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.ORGANIZATION_KEY, filterValue);
                                parameters.put(ConstantsUtils.ORGANISATION_KEY, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.CUSTOMER_GROUP.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.COMPANY_GROUP, filterValue);
                                parameters.put(ConstantsUtils.CUSTOMER_GROUP, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    
                    Compare.Operation operation = stringFilter.getOperation();
                    if (stringFilter.getValue() instanceof Date) {
                        String filterString = dateFormat.format(stringFilter.getValue());
                        if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                         parameters.put(stringFilter.getPropertyId().toString() +"from",filterString);
                        } else {
                         parameters.put(stringFilter.getPropertyId().toString()+"to", filterString);
                        }
                    }
                    
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }
        }

        if (parameters.get(ConstantsUtils.ORGANISATION_KEY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ORGANISATION_KEY)))) {
            queryString.append(" AND CM.ORGANIZATION_KEY =").append(String.valueOf(parameters.get(ConstantsUtils.ORGANISATION_KEY)));
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_ID)))) {
            queryString.append(" AND CM.COMPANY_ID LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_ID))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NO)))) {
            queryString.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NO))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NAME)))) {
            queryString.append(" AND CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NAME))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_TYPE) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_TYPE))) && Integer.parseInt(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_TYPE))) != 0) {
            queryString.append(" AND CM.COMPANY_TYPE = ").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_TYPE))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_STATUS))) && Integer.parseInt(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_STATUS))) != 0) {
            queryString.append(" AND CM.COMPANY_STATUS = ").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_STATUS))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.TRADE_CLASS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.TRADE_CLASS))) && Integer.parseInt(String.valueOf(parameters.get(ConstantsUtils.TRADE_CLASS))) != 0) {
            queryString.append(" AND CTS.COMPANY_TRADE_CLASS = ").append(String.valueOf(parameters.get(ConstantsUtils.TRADE_CLASS))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.LIVES) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.LIVES)))) {
            queryString.append(" AND CM.LIVES LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.LIVES))).append("%'");
        }
        if (parameters.get("udc1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc1")))) {
            queryString.append(" AND UDC.UDC1 =").append(String.valueOf(parameters.get("udc1")));
        }
        if (parameters.get("udc2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc2")))) {
            queryString.append(" AND UDC.UDC2 =").append(String.valueOf(parameters.get("udc2")));
        }
        if (parameters.get("udc3") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc3")))) {
            queryString.append(" AND UDC.UDC3 =").append(String.valueOf(parameters.get("udc3")));
        }
        if (parameters.get("udc4") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc4")))) {
            queryString.append(" AND UDC.UDC4 =").append(String.valueOf(parameters.get("udc4")));
        }
        if (parameters.get("udc5") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc5")))) {
            queryString.append(" AND UDC.UDC5 =").append(String.valueOf(parameters.get("udc5")));
        }
        if (parameters.get("udc6") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc6")))) {
            queryString.append(" AND UDC.UDC6 =").append(String.valueOf(parameters.get("udc6")));
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_GROUP) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_GROUP)))) {
            queryString.append(" AND CM.COMPANY_GROUP LIKE =").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_GROUP)));
        }
        if (parameters.get(ConstantsUtils.FINANCIAL_SYSTEM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FINANCIAL_SYSTEM)))) {
            queryString.append(" AND CM.FINANCIAL_SYSTEM LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.FINANCIAL_SYSTEM))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ADDRESS1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ADDRESS1)))) {
            queryString.append(" AND CM.ADDRESS1 LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ADDRESS1))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ADDRESS2) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ADDRESS2)))) {
            queryString.append(" AND CM.ADDRESS2 LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ADDRESS2))).append("%'");
        }
        if (parameters.get("city") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("city")))) {
            queryString.append(" AND CM.CITY LIKE '%").append(String.valueOf(parameters.get("city"))).append("%'");
        }
        if (parameters.get(ConstantsUtils.STATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STATE)))) {
            queryString.append(" AND CM.STATE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.STATE))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ZIPCODE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ZIPCODE)))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ZIPCODE))).append("%'");
        }
        if (parameters.get(ConstantsUtils.COUNTRY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.COUNTRY)))) {
            queryString.append(" AND CM.COUNTRY LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.COUNTRY))).append("%'");
        }
        if (parameters.get(ConstantsUtils.REGION_CODE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.REGION_CODE)))) {
            queryString.append(" AND CM.REGION_CODE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.REGION_CODE))).append("%'");
        }
       
        if (parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO)))) {
            queryString.append(" AND CM1.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO))).append("%'");
        }
        if (parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER)))) {
            queryString.append(" AND CM2.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER))).append("%'");
        }
        if (parameters.get(ConstantsUtils.TC_SD_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_SD_FROM)))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.TC_SD_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_SD_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_SD_TO)))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.TC_SD_TO))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_ED_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_ED_FROM)))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.TC_ED_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_ED_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_ED_TO)))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.TC_ED_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM)))) {
            queryString.append(" AND CM.COMPANY_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO)))) {
            queryString.append(" AND CM.COMPANY_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM)))) {
            queryString.append(" AND CM.COMPANY_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO)))) {
            queryString.append(" AND CM.COMPANY_END_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_FROM)))) {
            queryString.append(" AND CPD.PARENT_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_TO)))) {
            queryString.append(" AND CPD.PARENT_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM)))) {
            queryString.append(" AND CPD.PARENT_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PARENT_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_TO)))) {
            queryString.append(" AND CPD.PARENT_END_DATE LIKE <='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM)))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO)))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE LIKE <='").append(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO))).append("'");
        }

        dbColumnName = "COMPANY_ID";
        String orderBy = "ASC";
        HeaderUtils.loadColumnNames();
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                columnName = orderByColumn.getName();
                if (columnName.equals(ConstantsUtils.CUSTOMER_NO)) {
                    dbColumnName = ConstantsUtils.COMP_COMPANY_NO;
                } else if (columnName.equals(ConstantsUtils.PARENT_CUSTOMER_NO)) {
                    dbColumnName = "CM1.COMPANY_NO";
                } else if (columnName.equals(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER)) {
                    dbColumnName = "CM2.COMPANY_NO";
                } else {
                    dbColumnName = HeaderUtils.getDbColumnNames(columnName);
                }
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    orderBy = "ASC";
                } else {
                    orderBy = "DESC";
                }
            }
        }

        if (!isCount) {
            queryString.append(" ORDER BY ").append(dbColumnName).append(" ").append(orderBy).append(" OFFSET ").append(start).append(" ROWS FETCH NEXT ")
                    .append(offset).append(" ROWS ONLY");
        }
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());

        if (isCount) {
            object = (Integer) list.get(0);
        } else {
            object = (List) customizeCustomerList(list);
        }
        return object;
    }

    /**
     * Customizes the result list by setting that in the list of selectionDTO.
     *
     * @param list
     * @return
     */
    private List<SelectionDTO> customizeCustomerList(final List<Object[]> list) {
        final List<SelectionDTO> resultList = new ArrayList<>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        for (Object[] object : list) {

            SelectionDTO selectionDTO = new SelectionDTO();
            if (object[0] != null && idHelperDTOMap.get(object[0]) != null && !object[0].toString().equals(ConstantsUtils.ZERO)) {
                    selectionDTO.setOrganisationKey(idHelperDTOMap.get(object[0]));
            }
            selectionDTO.setCustomerId(String.valueOf(object[1]));
            selectionDTO.setCustomerNo(String.valueOf(object[NumericConstants.TWO]));
            selectionDTO.setCustomerName(String.valueOf(object[NumericConstants.THREE]));
            selectionDTO.setTradeClass(idHelperDTOMap.get(object[NumericConstants.FOUR]));
            if (object[NumericConstants.FIVE] != null) {
                selectionDTO.setTradeClassStartDate((Date) object[NumericConstants.FIVE]);
            }
            if (object[NumericConstants.SIX] != null) {
                selectionDTO.setTradeClassEndDate((Date) object[NumericConstants.SIX]);
            }
            selectionDTO.setCustomerType(idHelperDTOMap.get(object[NumericConstants.SEVEN]));
            selectionDTO.setCustomerStatus(idHelperDTOMap.get(object[NumericConstants.EIGHT]));
            selectionDTO.setLives(String.valueOf(object[NumericConstants.NINE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.NINE]));
            if (object[NumericConstants.TEN] != null) {
                selectionDTO.setCustomerStartDate((Date) object[NumericConstants.TEN]);
            }
            if (object[NumericConstants.ELEVEN] != null) {
                selectionDTO.setCustomerEndDate((Date) object[NumericConstants.ELEVEN]);
            }
            if (object[NumericConstants.TWELVE] != null && !object[NumericConstants.TWELVE].toString().equals(ConstantsUtils.ZERO)) {

                selectionDTO.setUdc1(idHelperDTOMap.get(object[NumericConstants.TWELVE]));
            }

            if (object[NumericConstants.THIRTEEN] != null && !object[NumericConstants.THIRTEEN].toString().equals(ConstantsUtils.ZERO)) {

                selectionDTO.setUdc2(idHelperDTOMap.get(object[NumericConstants.THIRTEEN]));
            }
            if (object[NumericConstants.FOURTEEN] != null && !object[NumericConstants.FOURTEEN].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc3(idHelperDTOMap.get(object[NumericConstants.FOURTEEN]));
            }
            if (object[NumericConstants.FIFTEEN] != null && !object[NumericConstants.FIFTEEN].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc4(idHelperDTOMap.get(object[NumericConstants.FIFTEEN]));
            }
            if (object[NumericConstants.SIXTEEN] != null && !object[NumericConstants.SIXTEEN].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc5(idHelperDTOMap.get(object[NumericConstants.SIXTEEN]));
            }
            if (object[NumericConstants.SEVENTEEN] != null && !object[NumericConstants.SEVENTEEN].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc6(idHelperDTOMap.get(object[NumericConstants.SEVENTEEN]));
            }
            if (object[NumericConstants.EIGHTEEN] != null && !object[NumericConstants.EIGHTEEN].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setCustomerGroup(idHelperDTOMap.get(object[NumericConstants.EIGHTEEN]));
            }

            selectionDTO.setFinancialSystem(String.valueOf(object[NumericConstants.NINETEEN]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.NINETEEN]));
            selectionDTO.setAddress1(String.valueOf(object[NumericConstants.TWENTY]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY]));
            selectionDTO.setAddress2(String.valueOf(object[NumericConstants.TWENTY_ONE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_ONE]));
            selectionDTO.setCity(String.valueOf(object[NumericConstants.TWENTY_TWO]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_TWO]));
            selectionDTO.setState(String.valueOf(object[NumericConstants.TWENTY_THREE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_THREE]));
            selectionDTO.setZipCode(String.valueOf(object[NumericConstants.TWENTY_FOUR]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_FOUR]));
            selectionDTO.setCountry(String.valueOf(object[NumericConstants.TWENTY_FIVE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_FIVE]));
            selectionDTO.setRegionCode(String.valueOf(object[NumericConstants.TWENTY_SIX]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_SIX]));
            selectionDTO.setParentCustomerNo(String.valueOf(object[NumericConstants.TWENTY_SEVEN]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.TWENTY_SEVEN]));
            if (object[NumericConstants.TWENTY_EIGHT] != null) {
                selectionDTO.setParentStartDate((Date) object[NumericConstants.TWENTY_EIGHT]);
            }
            if (object[NumericConstants.TWENTY_NINE] != null) {
                selectionDTO.setParentEndDate((Date) object[NumericConstants.TWENTY_NINE]);
            }
            if (object[NumericConstants.THIRTY] != null) {
                selectionDTO.setPriorParentStartDate((Date) object[NumericConstants.THIRTY]);
            }
            selectionDTO.setPriorParentCustomerNo(String.valueOf(object[NumericConstants.THIRTY_ONE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[NumericConstants.THIRTY_ONE]));
            selectionDTO.setCompanyMasterSid(String.valueOf(object[NumericConstants.THIRTY_TWO]).equals(ConstantsUtils.NULL) ? 0 : Integer.parseInt(String.valueOf(object[NumericConstants.THIRTY_TWO])));
            selectionDTO.setParentCompanyMasterSid(String.valueOf(object[NumericConstants.THIRTY_THREE]).equals(ConstantsUtils.NULL) ? 0 : Integer.parseInt(String.valueOf(object[NumericConstants.THIRTY_THREE])));
            resultList.add(selectionDTO);
        }
        return resultList;
    }

    public void resetCompanyAndItem(final SessionDTO sessionDTO, final Boolean isItem) {

        final StringBuilder queryString = new StringBuilder("DELETE FROM ST_SELECTION_TABLE WHERE USER_ID='");
        queryString.append(sessionDTO.getUserId()).
                append(ConstantsUtils.QUOTE_SESSION_ID).append(sessionDTO.getUiSessionId()).append("' AND SELECTION_TYPE='");
        queryString.append(isItem ? "I" : "C").append("';");
        HelperTableLocalServiceUtil.executeUpdateQuery(queryString.toString());
    }

    public void moveCustomersAndSaveToTempTable(final SelectionDTO selDTO, final SessionDTO sessionDTO) {
        if (selDTO != null) {
            
            String selectedComapnyIds = String.valueOf(selDTO.getCompanyMasterSid());
            
            String queryString = SQLUtil.getQuery("moveAndMergeToTempTable");
            queryString = queryString.replace("?COMPANY_FECTH", getQuery(true,selDTO,null));
            queryString = queryString.replace("?UID", sessionDTO.getUserId());
            queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");    
            queryString = queryString.replace("?COMPANY_ITEM_SIDS", selectedComapnyIds);
            HelperTableLocalServiceUtil.executeUpdateQuery(queryString);            
        }
    }

    public void deleteCustomersFromTempTable(final String selDTO, final SessionDTO sessionDTO) {
        if (StringUtils.isNotBlank(selDTO)) {
            String query= SQLUtil.getQuery("cp-delete-company")
                    .replace("?CMSID", selDTO)
                    .replace("?UID", "'"+sessionDTO.getUserId()+"'")
                    .replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
        }
    }

    public void addAllCustomersAndSaveToTempTable(final SessionDTO sessionDTO,final SelectionDTO selDTO,Set<Container.Filter> filter) {
        
        String queryString = SQLUtil.getQuery("moveAndMergeToTempTable");
        queryString = queryString.replace("?COMPANY_FECTH", getQuery(false,selDTO,filter));    
        queryString = queryString.replace("?UID", sessionDTO.getUserId());
        queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
        HelperTableLocalServiceUtil.executeUpdateQuery(queryString);
    }
    
    /**
     * 
     * @param sessionDTO
     * @param searchItemForm
     * @param isAddAll
     * @param selectedItemSID
     * @param filter
     * @throws SystemException
     * @throws PortalException 
     */
    public void addAllItemsAndSaveToTempTable(final SessionDTO sessionDTO,final ErrorfulFieldGroup searchItemForm, boolean isAddAll, List<Integer> selectedItemSID,Set<Container.Filter> filter) {
        
        String queryString = SQLUtil.getQuery("moveAndMergeItemsToTempTable");
        queryString = queryString.replace("?ITEM_FECTH", isAddAll? buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, null, filter, true) : buildSearchQuery(null, true, 0, 0, StringUtils.join(selectedItemSID, ","), null, null, true));    
        queryString = queryString.replace("?UID", sessionDTO.getUserId());
        queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
        HelperTableLocalServiceUtil.executeUpdateQuery(queryString);
    }

    public void deleteAllCustomersFromTempTable(final SessionDTO sessionDTO) {
        String query= SQLUtil.getQuery("cp-deleteall-company")
                    .replace("?UID", "'"+sessionDTO.getUserId()+"'")
                    .replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(query,sessionDTO.getCurrentTableNames()));
    }

    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        int code = 0;
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = DAO.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.parseInt(result.get(ZERO).toString());
        }
        return code;
    }

    public void saveToTempDeductionDetails(SessionDTO sessionDTO, DeductionDetailsDTO detailsDto) {
        String queryString = SQLUtil.getQuery("saveToTempDeductionDetails")
        .replace("@USERID", sessionDTO.getUserId())
        .replace("@SESSIONID", sessionDTO.getUiSessionId())
        .replace("@STARTPERIOD", detailsDto.getForecastFromDate())
        .replace("@ENDPERIOD", detailsDto.getForecastToDate());
        HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(queryString,sessionDTO.getCurrentTableNames()));
    }

    public List<SelectionDTO> getAvailableTableResult(ErrorfulFieldGroup searchItemForm, int start, int end, List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<SelectionDTO> selectionDTO;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchItemForm, false, start, end, StringUtils.EMPTY, columns, filterSet, false);
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        return getCustomizedSearchFormFromObject(list);
    }

    public int getAvailableTableCount(ErrorfulFieldGroup searchItemForm, List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, columns, filterSet, false);
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        if (list != null && !list.isEmpty()) {
            count += list.size();
        }
        return count;
    }

    public List<SelectionDTO> getSelectedTableResult(SessionDTO sessionDTO, int start, int end, List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        List<SelectionDTO> selectionDTO;
        StringBuilder queryBuilder;
        queryBuilder = buildAvailableQuery(sessionDTO, false, filterSet);
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());

        StringBuilder queryBuilder1;
        queryBuilder1 = buildSearchQuery(null, false, start, end, StringUtils.join(list, ','), columns, filterSet, false);
        final List list1 = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder1.toString());
        selectionDTO = getCustomizedSearchFormFromObject(list1);
        return selectionDTO;
    }

    public int getSelectedTableCount(SessionDTO sessionDTO, final Set<Container.Filter> filterSet)  {
        int count = 0;
        StringBuilder queryBuilder;
        queryBuilder = buildAvailableQuery(sessionDTO, true, filterSet);
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count += Integer.parseInt(String.valueOf(ob));
        }
        return count;
    }

    private StringBuilder buildAvailableQuery(SessionDTO sessionDTO, boolean isCount, final Set<Container.Filter> filterSet) {
        StringBuilder queryBuilder = new StringBuilder();
        String query;
        if(isCount){
            query = "DISTINCT COUNT(SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_ITEM SD JOIN ITEM_MASTER IM ON SD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                + "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n"
                + "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER' \n"
                + ConstantsUtils.LEFT_JOIN_BRAND_MASTER_ON_BM_SID;
        }else{
            query = "DISTINCT SD.ITEM_MASTER_SID FROM ST_DEDUCTION_CALENDAR_ITEM SD JOIN ITEM_MASTER IM ON SD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                + "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n"
                + "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER' \n"
                + ConstantsUtils.LEFT_JOIN_BRAND_MASTER_ON_BM_SID;
            
        }
        queryBuilder.append(" SELECT " + query + " WHERE ");
        queryBuilder.append("  USER_ID='").append(sessionDTO.getUserId()).
                append(ConstantsUtils.QUOTE_SESSION_ID).append(sessionDTO.getUiSessionId()).append("' ");
        
        String filterQuery = StringUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<>();
        detailsColumn.put("itemId", "IM.item_Id");
        detailsColumn.put("item", "IM.item_no");
        detailsColumn.put("itemName", "IM.item_Name");
        detailsColumn.put(ConstantsUtils.ITEM_DESC, "IM.item_Desc");
        detailsColumn.put("itemType", "IM.item_Type");
        detailsColumn.put("itemStatus", "IM.item_Status");
        detailsColumn.put("itemCode", "IM.item_Code");
        detailsColumn.put("packageSizeCode", "IM.package_Size_Code");
        detailsColumn.put(ConstantsUtils.ITEM_START_DATE, "IM.item_Start_Date");
        detailsColumn.put(ConstantsUtils.ITEM_END_DATE, "IM.item_End_Date");
        detailsColumn.put("labelerCode", "IM.labeler_Code");
        detailsColumn.put("form", "IM.form");
        detailsColumn.put("strength", "IM.strength");
        detailsColumn.put("primaryUOM", "IM.primary_UOM");
        detailsColumn.put("secondaryUOM", "IM.secondary_UOM");
        detailsColumn.put(ConstantsUtils.ITEM_CLASS, "IM.item_Class");
        detailsColumn.put(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, "IM.pediatric_Exclusive_Start_Date");
        detailsColumn.put(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, "IM.pediatric_Exclusive_End_Date");
        detailsColumn.put(ConstantsUtils.CLOTTING_FACTOR_START_DATE, "IM.clotting_Factor_Start_Date");
        detailsColumn.put(ConstantsUtils.CLOTTING_FACTOR_END_DATE, "IM.clotting_Factor_End_Date");
        detailsColumn.put("psNDC9", "IM.NDC9");
        detailsColumn.put("psNDC8", "IM.NDC8");
        detailsColumn.put("therapeuticClass", "IM.therapeutic_Class");
        detailsColumn.put(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, "IM.package_Size_Intro_Date");
        detailsColumn.put("manufacturerID", "CM.COMPANY_ID");
        detailsColumn.put("manufacturerNO", ConstantsUtils.COMP_COMPANY_NO);
        detailsColumn.put("manufacturerName", "CM.COMPANY_NAME");
        detailsColumn.put("productOrganizationKey", "IM.organization_Key");
        detailsColumn.put(ConstantsUtils.ACQUISISTION_DATE, "IM.acquisition_Date");
        detailsColumn.put("authorizedGeneric", "IM.authorized_Generic");
        detailsColumn.put(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, "IM.authorized_Generic_Start_Date");
        detailsColumn.put(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, "IM.authorized_Generic_End_Date");
        detailsColumn.put(ConstantsUtils.FIRST_SALE_DATE, "IM.first_Sale_Date");
        detailsColumn.put("itemTypeIndicator", "IM.ITEM_TYPE_INDICATION");
        detailsColumn.put(ConstantsUtils.MARKER_TERMINATION_DATE, "IM.market_Termination_Date");
        detailsColumn.put("newFormulationIndicator", "IM.new_Formulation_Indicator");
        detailsColumn.put("newFormulation", "IM.new_Formulation");
        detailsColumn.put(ConstantsUtils.NEW_FORMULATION_START_DATE, "IM.new_Formulation_Start_Date");
        detailsColumn.put(ConstantsUtils.NEW_FORMULATION_END_DATE, "IM.new_Formulation_End_Date");
        detailsColumn.put("pediatricExclusiveIndicator", "IM.pediatric_Exclusive_Indicator");
        detailsColumn.put("clottingFactorIndicator", "IM.clotting_Factor_Indicator");
        detailsColumn.put("shelfLife", "IM.shelf_Life");
        detailsColumn.put("dualPricingIndicator", "IM.dual_Pricing_Indicator");
        detailsColumn.put("itemFamilyID", "IM.item_Family_ID");
        detailsColumn.put("acquiredAMP", "IM.acquired_AMP");
        detailsColumn.put("acquiredBAMP", "IM.acquired_BAMP");
        detailsColumn.put("psOBRABAMP", "IM.OBRA_BAMP");
        detailsColumn.put("psDRA", "IM.DRA");
        detailsColumn.put("dosesperUnit", "IM.doses_per_Unit");
        detailsColumn.put(ConstantsUtils.DISCOUNT_DATE, "IM.discontinuation_Date");
        detailsColumn.put(ConstantsUtils.LAST_LOT_EXP_DATE, "IM.last_Lot_Expiration_Date");
        detailsColumn.put("baselineAMP", "IM.baseline_AMP");
        detailsColumn.put(ConstantsUtils.BASE_YEAR_CPI, "IM.BASE_CPI_PERIOD");
        detailsColumn.put("psUDC1", "UDCS.UDC1");
        detailsColumn.put("psUDC2", "UDCS.UDC2");
        detailsColumn.put("psUDC3", "UDCS.UDC3");
        detailsColumn.put("psUDC4", "UDCS.UDC4");
        detailsColumn.put("psUDC5", "UDCS.UDC5");
        detailsColumn.put("psUDC6", "UDCS.UDC6");
        detailsColumn.put("displayBrand", "BM.DISPLAY_BRAND");
        detailsColumn.put("brand", "BM.BRAND_NAME");
        detailsColumn.put(ConstantsUtils.ITEM_CLASS, "IM.ITEM_CLASS"); 
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());

                    if (!filterString.contains(ConstantsUtils.SHOW_ALL)) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.AND). append(detailsColumn.get(String.valueOf(stringFilter.getPropertyId()))). append( " like '").append(filterString).append("'").toString();
                    }

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String filterString = formatter.format(stringFilter.getStartValue());
                    String filterString1 = formatter.format(stringFilter.getEndValue());
                    if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.ITEM_MASTER_START_DATE_TO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_FROM ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_TO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_FROM ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.PACKAGE_SIZE_INTRO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.IM_PACKAGE_SIZE_INTRO_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    Date value = (Date) stringFilter.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_TO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_TO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.PACKAGE_SIZE_INTRO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD ).append( value ).append( "' ").toString();
                        }
                    } else {
                        if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_FROM ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_FROM ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.IM_PACKAGE_SIZE_INTRO_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                    }
                }
            }
        }
        queryBuilder.append(filterQuery).append(" ;");
        return queryBuilder;
    }

    private StringBuilder buildSearchQuery(ErrorfulFieldGroup searchFields, boolean isCount, final int start, final int end, final String itemSystemId, final List<SortByColumn> sortByColumns, final Set<Container.Filter> filterSet, final boolean isSelected) {
        StringBuilder queryBuilder = new StringBuilder();
        String query;
        if (isSelected) {
            query = isCount ? "ITEM_MASTER_SID, ?UID, ?SID FROM ITEM_MASTER IM LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n" +
                              "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER'  \n" +
                              ConstantsUtils.LEFT_JOIN_BRAND_MASTER_ON_BM_SID : SQLUtil.getQuery("DeductionCalendarItemSelectionSearch");
        }else{
            query = isCount ? "DISTINCT ITEM_MASTER_SID FROM ITEM_MASTER IM LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n" +
                                "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER'  \n" +
                                "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID " : SQLUtil.getQuery("DeductionCalendarItemSelectionSearch");
        }
        queryBuilder.append(" SELECT " + query + " WHERE IM.INBOUND_STATUS <> 'D' ");
        if (criteria.isEmpty()) {
            loadCriteriaInMap();
        }
        if (searchFields != null) {
            SelectionDTO selectionDTO = getBeanFromId(searchFields.getItemDataSource());
            Set<String> keys = criteria.keySet();
            for (String fields : keys) {
                if ("itemTypeDdlb".equals(fields) && selectionDTO.getItemTypeDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getItemTypeDdlb().getDescription().toString()) && !selectionDTO.getItemTypeDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(selectionDTO.getItemTypeDdlb().getId()).append("'");
                }
                if ("brandDdlb".equals(fields) && selectionDTO.getBrandDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getBrandDdlb().getDescription().toString()) && !selectionDTO.getBrandDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(selectionDTO.getBrandDdlb().getId()).append("'");
                }
                if ("formDdlb".equals(fields) && selectionDTO.getFormDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getFormDdlb().getDescription().toString()) && !selectionDTO.getFormDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(selectionDTO.getFormDdlb().getId()).append("'");
                }
                if ("strengthDdlb".equals(fields) && selectionDTO.getStrengthDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getStrengthDdlb().getDescription().toString()) && !selectionDTO.getStrengthDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(selectionDTO.getStrengthDdlb().getId()).append("'");
                }
                if ("therapeuticclassDdlb".equals(fields) && selectionDTO.getTherapeuticclassDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getTherapeuticclassDdlb().getDescription().toString()) && !selectionDTO.getTherapeuticclassDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" = '").append(selectionDTO.getTherapeuticclassDdlb().getId()).append("'");
                }
                if (ConstantsUtils.ITEM_DESC.equals(fields) && selectionDTO.getItemDesc() != null && !selectionDTO.getItemDesc().trim().isEmpty() &&!"*".equals(selectionDTO.getItemDesc().trim())) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" LIKE '").append(CommonUtil.buildSearchCriteria(selectionDTO.getItemDesc().trim())).append("'");
                }
                if ("item".equals(fields) && selectionDTO.getItem() != null && !selectionDTO.getItem().toString().trim().isEmpty() &&!"*".equals(selectionDTO.getItem().trim())) {
                        queryBuilder.append(ConstantsUtils.AND).append(criteria.get(fields)).append(" LIKE '").append(CommonUtil.buildSearchCriteria(selectionDTO.getItem().trim())).append("'");
                }
            }
        } else {
            queryBuilder.append(ConstantsUtils.AND).append("ITEM_MASTER_SID in (").append(itemSystemId).append(")");
        }
        String filterQuery = StringUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<>();
        detailsColumn.put("itemId", "IM.item_Id");
        detailsColumn.put("item", "IM.item_no");
        detailsColumn.put("itemName", "IM.item_Name");
        detailsColumn.put(ConstantsUtils.ITEM_DESC, "IM.item_Desc");
        detailsColumn.put("itemType", "IM.item_Type");
        detailsColumn.put("itemStatus", "IM.item_Status");
        detailsColumn.put("itemCode", "IM.item_Code");
        detailsColumn.put("packageSizeCode", "IM.package_Size_Code");
        detailsColumn.put(ConstantsUtils.ITEM_START_DATE, "IM.item_Start_Date");
        detailsColumn.put(ConstantsUtils.ITEM_END_DATE, "IM.item_End_Date");
        detailsColumn.put("labelerCode", "IM.labeler_Code");
        detailsColumn.put("form", "IM.form");
        detailsColumn.put("strength", "IM.strength");
        detailsColumn.put("primaryUOM", "IM.primary_UOM");
        detailsColumn.put("secondaryUOM", "IM.secondary_UOM");
        detailsColumn.put(ConstantsUtils.ITEM_CLASS, "IM.item_Class");
        detailsColumn.put(ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE, "IM.pediatric_Exclusive_Start_Date");
        detailsColumn.put(ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE, "IM.pediatric_Exclusive_End_Date");
        detailsColumn.put(ConstantsUtils.CLOTTING_FACTOR_START_DATE, "IM.clotting_Factor_Start_Date");
        detailsColumn.put(ConstantsUtils.CLOTTING_FACTOR_END_DATE, "IM.clotting_Factor_End_Date");
        detailsColumn.put("psNDC9", "IM.NDC9");
        detailsColumn.put("psNDC8", "IM.NDC8");
        detailsColumn.put("therapeuticClass", "IM.therapeutic_Class");
        detailsColumn.put(ConstantsUtils.PACKAGE_SIZE_INTRO_DATE, "IM.package_Size_Intro_Date");
        detailsColumn.put("manufacturerID", "CM.COMPANY_ID");
        detailsColumn.put("manufacturerNO", ConstantsUtils.COMP_COMPANY_NO);
        detailsColumn.put("manufacturerName", "CM.COMPANY_NAME");
        detailsColumn.put("productOrganizationKey", "IM.organization_Key");
        detailsColumn.put(ConstantsUtils.ACQUISISTION_DATE, "IM.acquisition_Date");
        detailsColumn.put("authorizedGeneric", "IM.authorized_Generic");
        detailsColumn.put(ConstantsUtils.AUTHORIZED_GENERIC_START_DATE, "IM.authorized_Generic_Start_Date");
        detailsColumn.put(ConstantsUtils.AUTHORIZED_GENERIC_END_DATE, "IM.authorized_Generic_End_Date");
        detailsColumn.put(ConstantsUtils.FIRST_SALE_DATE, "IM.first_Sale_Date");
        detailsColumn.put("itemTypeIndicator", "IM.ITEM_TYPE_INDICATION");
        detailsColumn.put(ConstantsUtils.MARKER_TERMINATION_DATE, "IM.market_Termination_Date");
        detailsColumn.put("newFormulationIndicator", "IM.new_Formulation_Indicator");
        detailsColumn.put("newFormulation", "IM.new_Formulation");
        detailsColumn.put(ConstantsUtils.NEW_FORMULATION_START_DATE, "IM.new_Formulation_Start_Date");
        detailsColumn.put(ConstantsUtils.NEW_FORMULATION_END_DATE, "IM.new_Formulation_End_Date");
        detailsColumn.put("pediatricExclusiveIndicator", "IM.pediatric_Exclusive_Indicator");
        detailsColumn.put("clottingFactorIndicator", "IM.clotting_Factor_Indicator");
        detailsColumn.put("shelfLife", "IM.shelf_Life");
        detailsColumn.put("dualPricingIndicator", "IM.dual_Pricing_Indicator");
        detailsColumn.put("itemFamilyID", "IM.item_Family_ID");
        detailsColumn.put("acquiredAMP", "IM.acquired_AMP");
        detailsColumn.put("acquiredBAMP", "IM.acquired_BAMP");
        detailsColumn.put("psOBRABAMP", "IM.OBRA_BAMP");
        detailsColumn.put("psDRA", "IM.DRA");
        detailsColumn.put("dosesperUnit", "IM.doses_per_Unit");
        detailsColumn.put(ConstantsUtils.DISCOUNT_DATE, "IM.discontinuation_Date");
        detailsColumn.put(ConstantsUtils.LAST_LOT_EXP_DATE, "IM.last_Lot_Expiration_Date");
        detailsColumn.put("baselineAMP", "IM.baseline_AMP");
        detailsColumn.put(ConstantsUtils.BASE_YEAR_CPI, "IM.BASE_CPI_PERIOD");
        detailsColumn.put("psUDC1", "UDCS.UDC1");
        detailsColumn.put("psUDC2", "UDCS.UDC2");
        detailsColumn.put("psUDC3", "UDCS.UDC3");
        detailsColumn.put("psUDC4", "UDCS.UDC4");
        detailsColumn.put("psUDC5", "UDCS.UDC5");
        detailsColumn.put("psUDC6", "UDCS.UDC6");
        detailsColumn.put("displayBrand", "BM.DISPLAY_BRAND");
        detailsColumn.put("brand", "BM.BRAND_NAME");
        detailsColumn.put(ConstantsUtils.ITEM_CLASS, "IM.ITEM_CLASS"); 
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = CommonUtil.buildFilterCriteria(stringFilter.getFilterString());

                    if (!filterString.contains(ConstantsUtils.SHOW_ALL)) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.AND ).append( detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) ).append( " like '" ).append( filterString ).append( "'").toString();
                    }

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String filterString = formatter.format(stringFilter.getStartValue());
                    String filterString1 = formatter.format(stringFilter.getEndValue());
                    if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_TO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_FROM ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_TO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_FROM ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.PACKAGE_SIZE_INTRO ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.IM_PACKAGE_SIZE_INTRO_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                    if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD ).append( filterString ).append( "' ").toString();
                        filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD_LESSER_THAN ).append( filterString1 ).append( "' ").toString();
                    }
                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    Compare.Operation operation = stringFilter.getOperation();
                    Date value = (Date) stringFilter.getValue();
                    if (Compare.Operation.GREATER_OR_EQUAL.toString().equals(operation.name())) {
                        if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_TO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_TO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.PACKAGE_SIZE_INTRO ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD ).append( value ).append( "' ").toString();
                        }
                    } else {
                        if (ConstantsUtils.ITEM_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_START_DATE_FROM ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ITEM_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.ITEM_MASTER_END_DATE_FROM ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PACKAGE_SIZE_INTRO_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append(ConstantsUtils.IM_PACKAGE_SIZE_INTRO_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.ACQUISISTION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_ACQUISITION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.PEDIATRIC_EXCLUSIVE_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_PEDIATRIC_EXCLUSIVE_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.CLOTTING_FACTOR_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_CLOTTING_FACTOR_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.AUTHORIZED_GENERIC_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_AUTHORIZED_GENERIC_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.FIRST_SALE_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_FIRST_SALE_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_END_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_END_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.DISCOUNT_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_DISCONTINUATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.LAST_LOT_EXP_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_LAST_LOT_EXPIRATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.MARKER_TERMINATION_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_MARKET_TERMINATION_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.NEW_FORMULATION_START_DATE.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_NEW_FORMULATION_START_DATE_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                        if (ConstantsUtils.BASE_YEAR_CPI.equals(stringFilter.getPropertyId())) {
                            filterQuery = new StringBuilder(filterQuery).append( ConstantsUtils.IM_BASE_CPI_PERIOD_LESSER_THAN ).append( value ).append( "' ").toString();
                        }
                    }
                }
            }
        }
        String order = StringUtils.EMPTY;
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
                order = order + " ORDER BY  IM.ITEM_NO ASC";
            } else {
                order = order + " ORDER BY " + orderByColumn + ((!sortOrder) ? " ASC " : " DESC ");
            }
            order = order + " " + "OFFSET ";
            order = order + start;
            order = order + " ROWS FETCH NEXT " + end;
            order = order + " ROWS ONLY;";
        }
        if (isCount) {
            queryBuilder.append(filterQuery);
        } else {
            queryBuilder.append(filterQuery + order);
        }

        queryBuilder = new StringBuilder(queryBuilder.toString().replace("WHERE AND", " WHERE "));
        return queryBuilder;
    }

    public SelectionDTO getBeanFromId(Object obj) {

        BeanItem<?> targetItem = null;
        if (obj instanceof BeanItem<?>) {
            targetItem = (BeanItem<?>) obj;
        } else if (obj instanceof SelectionDTO) {
            targetItem = new BeanItem<>(
                    (SelectionDTO) obj);
        }
        return (SelectionDTO) targetItem.getBean();
    }

    private void loadCriteriaInMap() {
        criteria.clear();
        criteria.put("item", "IM.ITEM_NO");
        criteria.put(ConstantsUtils.ITEM_DESC, "IM.ITEM_DESC");
        criteria.put("itemTypeDdlb", "IM.ITEM_TYPE");
        criteria.put("brandDdlb", "IM.BRAND_MASTER_SID");
        criteria.put("formDdlb", "IM.FORM");
        criteria.put("strengthDdlb", "IM.STRENGTH");
        criteria.put("therapeuticclassDdlb", "IM.THERAPEUTIC_CLASS");
    }

    /**
     * Gets the customized search form from object.
     *
     * @param list the list
     * @param identifierType the identifier type
     * @return the customized search form from object
     */
    public List<SelectionDTO> getCustomizedSearchFormFromObject(
            final List list) {

        LOGGER.debug("Entering getCustomizedSearchFormFromObject p1: " + ((list == null) ? list : list.size()) + " p2: ");
        final List<SelectionDTO> searchItemList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SelectionDTO searchItemForm = new SelectionDTO();
                final Object[] obj = (Object[]) list.get(i);
                searchItemForm.setItemSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setItemId(HelperUtils.getString(obj[1]));
                searchItemForm.setItem(HelperUtils.getString(obj[NumericConstants.TWO]));
                searchItemForm.setItemName(HelperUtils.getString(obj[NumericConstants.THREE]));
                searchItemForm.setItemDesc(HelperUtils.getString(obj[NumericConstants.FOUR]));

                searchItemForm.setItemType(CommonUIUtils.getDescription(HelperUtils.getInteger(obj[NumericConstants.FIVE])));
                searchItemForm.setItemStatus(CommonUIUtils.getDescription(HelperUtils.getInteger(obj[NumericConstants.SIX])));

                searchItemForm.setRecordLockStatus(Boolean.parseBoolean(HelperUtils.getString(obj[NumericConstants.SEVEN])));
                searchItemForm.setItemCode(HelperUtils.getString(obj[NumericConstants.EIGHT]));
                searchItemForm.setPackageSizeCode(HelperUtils.getString(obj[NumericConstants.NINE]));

                if (obj[NumericConstants.TWELVE] == null) {
                    searchItemForm.setPsUP("0.000000");
                } else {
                    searchItemForm.setPsUP(obj[NumericConstants.TEN] == null ? StringUtils.EMPTY : FORMATDECIMAL.format(Double.valueOf(HelperUtils.getString(obj[NumericConstants.TEN]))));
                }
                if (obj[NumericConstants.ELEVEN] != null) {
                    searchItemForm.setItemStartDate((Date) obj[NumericConstants.ELEVEN]);
                }
                if (obj[NumericConstants.TWELVE] != null) {
                    searchItemForm.setItemEndDate((Date) obj[NumericConstants.TWELVE]);
                }
                
                searchItemForm.setLabelerCode(HelperUtils.getString(obj[NumericConstants.THIRTEEN]));
                searchItemForm.setForm(obj[NumericConstants.FOURTEEN] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.FOURTEEN]));
                searchItemForm.setStrength(obj[NumericConstants.FIFTEEN] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.FIFTEEN]));
                searchItemForm.setPrimaryUOM(obj[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SIXTEEN]));
                searchItemForm.setSecondaryUOM(obj[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SEVENTEEN]));
                searchItemForm.setItemClass(CommonUIUtils.getDescription(HelperUtils.getInteger(obj[NumericConstants.EIGHTEEN])));

                if (obj[NumericConstants.NINETEEN] != null) {
                    searchItemForm.setPediatricExclusiveStartDate((Date) obj[NumericConstants.NINETEEN]);
                }
                if (obj[NumericConstants.TWENTY] != null) {
                    searchItemForm.setPediatricExclusiveEndDate((Date) obj[NumericConstants.TWENTY]);
                }

                searchItemForm.setClottingFactorStartDate(String.valueOf(obj[NumericConstants.TWENTY_ONE]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : parseDateLogic(obj[NumericConstants.TWENTY_ONE]));
                searchItemForm.setClottingFactorEndDate(String.valueOf(obj[NumericConstants.TWENTY_TWO]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : parseDateLogic(obj[NumericConstants.TWENTY_TWO]));
                searchItemForm.setBrand(HelperUtils.getString(obj[NumericConstants.TWENTY_THREE]));
                searchItemForm.setPsNDC9(HelperUtils.getString(obj[NumericConstants.TWENTY_FOUR]));
                searchItemForm.setPsNDC8(HelperUtils.getString(obj[NumericConstants.TWENTY_FIVE]));
                searchItemForm.setTherapeuticClass(obj[NumericConstants.TWENTY_SIX] != null && !StringUtils.isEmpty(obj[NumericConstants.TWENTY_SIX].toString()) && !ConstantsUtils.ZERO.equals(obj[NumericConstants.TWENTY_SIX]) && !ConstantsUtils.SELECT_ONE.equals(obj[NumericConstants.TWENTY_SIX])
                        ? CommonUIUtils.getDescription((Integer) obj[NumericConstants.TWENTY_SIX]) : StringUtils.EMPTY);

                                if (obj[NumericConstants.TWENTY_SEVEN] != null) {
                    searchItemForm.setPackageSizeIntroDate((Date) obj[NumericConstants.TWENTY_SEVEN]);
                }

                searchItemForm.setManufacturerID(HelperUtils.getString(obj[NumericConstants.TWENTY_EIGHT]));
                searchItemForm.setManufacturerNO(HelperUtils.getString(obj[NumericConstants.TWENTY_NINE]));
                searchItemForm.setManufacturerName(HelperUtils.getString(obj[NumericConstants.THIRTY]));
                searchItemForm.setProductOrganizationKey(HelperUtils.getString(obj[NumericConstants.THIRTY_ONE]));

                if (obj[NumericConstants.THIRTY_TWO] != null) {
                    searchItemForm.setAcquisitionDate((Date) obj[NumericConstants.THIRTY_TWO]);
                }
                
                searchItemForm.setAuthorizedGeneric(HelperUtils.getString(obj[NumericConstants.THIRTY_THREE]));
               
                if (obj[NumericConstants.THIRTY_FOUR] != null) {
                    searchItemForm.setAuthorizedGenericStartDate((Date) obj[NumericConstants.THIRTY_FOUR]);
                }
                if (obj[NumericConstants.THIRTY_FIVE] != null) {
                    searchItemForm.setAuthorizedGenericEndDate((Date) obj[NumericConstants.THIRTY_FIVE]);
                }
                if (obj[NumericConstants.THIRTY_SIX] != null) {
                    searchItemForm.setFirstSaleDate((Date) obj[NumericConstants.THIRTY_SIX]);
                }

                searchItemForm.setItemTypeIndicator(HelperUtils.getString(obj[NumericConstants.THIRTY_SEVEN]));
                
                if (obj[NumericConstants.THIRTY_EIGHT] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[NumericConstants.THIRTY_EIGHT]);
                }
                searchItemForm.setNewFormulationIndicator(HelperUtils.getString(obj[NumericConstants.THIRTY_NINE]));
                searchItemForm.setNewFormulation(HelperUtils.getString(obj[NumericConstants.FORTY]));
                
                if (obj[NumericConstants.FORTY_ONE] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[NumericConstants.FORTY_ONE]);
                }
                if (obj[NumericConstants.FORTY_TWO] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[NumericConstants.FORTY_TWO]);
                }
                
                searchItemForm.setPediatricExclusiveIndicator(HelperUtils.getString(obj[NumericConstants.FORTY_THREE]));
                searchItemForm.setClottingFactorIndicator(HelperUtils.getString(obj[NumericConstants.FORTY_FOUR]));
                searchItemForm.setShelfLife(HelperUtils.getString(obj[NumericConstants.FORTY_FIVE]));
                searchItemForm.setShelfLifeType(HelperUtils.getString(obj[NumericConstants.FORTY_SIX]));
                searchItemForm.setDualPricingIndicator(HelperUtils.getString(obj[NumericConstants.FORTY_SEVEN]));
                searchItemForm.setItemFamilyID(HelperUtils.getString(obj[NumericConstants.FORTY_EIGHT]));
                searchItemForm.setAcquiredAMP(HelperUtils.getString(obj[NumericConstants.FORTY_NINE]));
                searchItemForm.setAcquiredBAMP(HelperUtils.getString(obj[NumericConstants.FIFTY]));
                searchItemForm.setPsOBRABAMP(HelperUtils.getString(obj[NumericConstants.FIFTY_ONE]));
                searchItemForm.setPsDRA(HelperUtils.getString(obj[NumericConstants.FIFTY_TWO]));
                searchItemForm.setDosesperUnit(HelperUtils.getString(obj[NumericConstants.FIFTY_THREE]));
                
                if (obj[NumericConstants.FIFTY_FOUR] != null) {
                    searchItemForm.setDiscontinuationDate((Date) obj[NumericConstants.FIFTY_FOUR]);
                }
                if (obj[NumericConstants.FIFTY_FIVE] != null) {
                    searchItemForm.setLastLotExpirationDate((Date) obj[NumericConstants.FIFTY_FIVE]);
                }
                if (obj[NumericConstants.FIFTY_SEVEN] != null) {
                    searchItemForm.setBaseYearCPI((Date) obj[NumericConstants.FIFTY_SEVEN]);
                }
                
                searchItemForm.setBaselineAMP(HelperUtils.getString(obj[NumericConstants.FIFTY_SIX]));
                searchItemForm.setPsUDC1(obj[NumericConstants.FIFTY_EIGHT] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.FIFTY_EIGHT]));
                searchItemForm.setPsUDC2(obj[NumericConstants.FIFTY_NINE] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.FIFTY_EIGHT]));
                searchItemForm.setPsUDC3(obj[NumericConstants.SIXTY] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SIXTY]));
                searchItemForm.setPsUDC4(obj[NumericConstants.SIXTY_ONE] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SIXTY_ONE]));
                searchItemForm.setPsUDC5(obj[NumericConstants.SIXTY_TWO] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SIXTY_TWO]));
                searchItemForm.setPsUDC6(obj[NumericConstants.SIXTY_THREE] == null ? StringUtils.EMPTY : CommonUIUtils.getDescription((Integer) obj[NumericConstants.SIXTY_THREE]));
                searchItemForm.setDisplayBrand(HelperUtils.getString(obj[NumericConstants.SIXTY_FOUR]));
                searchItemForm.setInnovatorCode(HelperUtils.getString(obj[NumericConstants.SIXTY_FIVE]));
                searchItemList.add(searchItemForm);

            }
        }

        LOGGER.debug("returns searchItemList size " + searchItemList.size());
        return searchItemList;
    }

    public void moveItemAndSaveToTempTable(final String selDTO, final SessionDTO sessionDTO) {
        if (selDTO != null) {

            final StringBuilder queryString = new StringBuilder(SQLUtil.getQuery("moveAndSaveToTempTable"));
            queryString.append("SELECT ").append(selDTO).append(" ,'I',1,'A','").append(sessionDTO.getUserId()).
                    append("','").append(sessionDTO.getUiSessionId()).append("' ;");
            HelperTableLocalServiceUtil.executeUpdateQuery(queryString.toString());
        }
    }

    public void removeItem(final String selDTO, final SessionDTO sessionDTO, Boolean isRemoveAll) {
        if (!isRemoveAll && StringUtils.isNotBlank(selDTO)) {

            String queryString = SQLUtil.getQuery("cp-delete-item");
            queryString=queryString.replace("?UID","'"+sessionDTO.getUserId()+"'")
                    .replace("?SID","'"+sessionDTO.getUiSessionId()+"'")
                    .replace("?IMSID",selDTO);
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(queryString,sessionDTO.getCurrentTableNames()));
        }else if (isRemoveAll) {
            String queryString = SQLUtil.getQuery("cp-deleteall-item");
            queryString=queryString.replace("?UID","'"+sessionDTO.getUserId()+"'")
                    .replace("?SID","'"+sessionDTO.getUiSessionId()+"'");
            HelperTableLocalServiceUtil.executeUpdateQuery(QueryUtil.replaceTableNames(queryString,sessionDTO.getCurrentTableNames()));
        }
    }

    public Boolean moveItemDuplicateCheck(final String selDTO, final SessionDTO sessionDTO) {
        if (selDTO != null) {

            final StringBuilder queryString = new StringBuilder("SELECT COUNT(COMPANY_ITEM_SID) FROM ST_SELECTION_TABLE WHERE ");
            queryString.append("COMPANY_ITEM_SID ='").append(selDTO)
                    .append("' AND SELECTION_TYPE='I' AND USER_ID='").append(sessionDTO.getUserId()).
                    append(ConstantsUtils.QUOTE_SESSION_ID).append(sessionDTO.getUiSessionId()).append("' ;");

            List obj = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryString.toString());
            if (((Integer) obj.get(0)) > 0) {
                return true;
            }
        }
        return false;
    }

    public List addToTempTable(ErrorfulFieldGroup searchItemForm) {
        StringBuilder queryBuilder;
        queryBuilder = buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, null, null, false);
        final List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryBuilder.toString());
        return list;
    }

    public void deleteDedutionCalendar(int deducationCalendarSid) {
        final StringBuilder queryString = new StringBuilder();
        queryString.append("UPDATE DEDUCTION_CALENDAR_MASTER SET INBOUND_STATUS = 'D' WHERE DEDUCTION_CALENDAR_MASTER_SID = ")
                .append(deducationCalendarSid).append(";");
         HelperTableLocalServiceUtil.executeUpdateQuery(queryString.toString());
    }

    public String parseDateLogic(Object object) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(object);
    }
    public String getQuery(boolean isAdd, SelectionDTO selDTO, Set<Container.Filter> filterSet){
        String query;
        if(isAdd){
            query = "SELECT COMPANY_MASTER_SID,?UID,?SID from COMPANY_MASTER cm WHERE COMPANY_MASTER_SID IN (?COMPANY_ITEM_SIDS) AND INBOUND_STATUS <> 'D'";
        } else {
            query = getCompanySearchQuery(selDTO,filterSet);
        }      
        return query;
    }
    
    public String getSelectedCompanyIds(SessionDTO sessionDTO){
        final String query = "SELECT COMPANY_ITEM_SID FROM ST_SELECTION_TABLE WHERE SELECTION_TYPE = 'C' AND USER_ID = "
                + " "+sessionDTO.getUserId()+" AND SESSION_ID = '"+sessionDTO.getUiSessionId()+"';";
        List idList = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
        
        String selectedCompanySids = StringUtils.EMPTY;
        if(!idList.isEmpty()){
            selectedCompanySids = CommonUIUtils.collectionToString(idList, false);
        } 
        return selectedCompanySids;
    }
    
    public String getCompanySearchQuery(SelectionDTO selectionDTO, Set<Container.Filter> filterSet){
        StringBuilder queryString = new StringBuilder(" SELECT DISTINCT CM.COMPANY_MASTER_SID,?UID,?SID FROM COMPANY_MASTER CM \n" +
                                " LEFT JOIN COMPANY_TRADE_CLASS CTS\n" +
                                "                      ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n" +
                                "                    LEFT JOIN HELPER_TABLE HT_CTS\n" +
                                "                           ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n" +
                                "                    LEFT JOIN HELPER_TABLE HT_CT\n" +
                                "                           ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE\n" +
                                "                    LEFT JOIN HELPER_TABLE HT_CS\n" +
                                "                           ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS ");
        if (StringUtils.isNotBlank(selectionDTO.getState()) && !Constants.NULL.equals(selectionDTO.getState())) {
                String state = selectionDTO.getState();
                state = state.replace('*', '%');
                queryString.append(" AND CM.\"STATE\" LIKE '").append(state).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getCity()) && !Constants.NULL.equals(selectionDTO.getCity())) {
                String city = selectionDTO.getCity();
                city = city.replace('*', '%');
                queryString.append(" AND CM.CITY LIKE '").append(city).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getZipCode()) && !Constants.NULL.equals(selectionDTO.getZipCode())) {
                String zipCode = selectionDTO.getZipCode();
                zipCode = zipCode.replace('*', '%');
                queryString.append(" AND CM.ZIP_CODE LIKE '").append(zipCode).append("' ");
            }

            queryString.append(" WHERE  CM.INBOUND_STATUS <> 'D' ");

            if (StringUtils.isNotBlank(selectionDTO.getCustomerNo()) && !Constants.NULL.equals(selectionDTO.getCustomerNo())) {
                String customerNo = selectionDTO.getCustomerNo();
                customerNo = customerNo.replace('*', '%');
                queryString.append(" AND CM.COMPANY_NO LIKE '").append(customerNo).append("' ");
            }

            if (StringUtils.isNotBlank(selectionDTO.getCustomerName()) && !Constants.NULL.equals(selectionDTO.getCustomerName())) {
                String customerName = selectionDTO.getCustomerName();
                customerName = customerName.replace('*', '%');
                queryString.append(" AND CM.COMPANY_NAME LIKE '").append(customerName).append("' ");
            }

            if (selectionDTO.getTradeClass() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getTradeClass()))) {
                try {
                    String tradeClassValue = String.valueOf(selectionDTO.getTradeClass());
                    if (tradeClassValue != null && !tradeClassValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(ConstantsUtils.COMPANY_TRADE_CLASS_LIST, tradeClassValue);
                        if (helperId != 0) {
                            queryString.append(" AND HT_CTS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                        }
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            if (selectionDTO.getCustomerType() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getCustomerType()))) {
                try {
                    String customerTypeValue = String.valueOf(selectionDTO.getCustomerType());
                    if (customerTypeValue != null && !customerTypeValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(CommonUIUtils.COMPANY_TYPE, customerTypeValue);
                        queryString.append(" AND HT_CT.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

            if (selectionDTO.getCustomerStatus() != null && !Constants.SELECT_ONE.equals(String.valueOf(selectionDTO.getCustomerStatus()))) {
                try {
                    String customerStatusValue = String.valueOf(selectionDTO.getCustomerStatus());
                    if (customerStatusValue != null && !customerStatusValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode(CommonUIUtils.STATUS, customerStatusValue);
                        queryString.append(" AND HT_CS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (PortalException | SystemException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }
        return getFilterQuery(queryString,filterSet);
    }

    private String getFilterQuery(StringBuilder queryString, Set<Container.Filter> filterSet) {
       DateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.YMD_FORMAT);
         Map<String, Object> parameters = new HashMap<>();
          if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString().replace(GlobalConstants.getPercent(), GlobalConstants.getPercentForEscape());
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if (ConstantsUtils.CUSTOMER_TYPE.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.COMPANY_TYPE, filterValue);
                                parameters.put(ConstantsUtils.CUSTOMER_TYPE, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.CUSTOMER_STATUS.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(CommonUIUtils.STATUS, filterValue);
                                parameters.put(ConstantsUtils.CUSTOMER_STATUS, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }
                    if (ConstantsUtils.TRADE_CLASS.equals(stringFilter.getPropertyId()) && filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode(ConstantsUtils.COMPANY_TRADE_CLASS_LIST, filterValue);
                                parameters.put(ConstantsUtils.TRADE_CLASS, helperId);
                            } catch (PortalException | SystemException ex) {
                                LOGGER.error(ex.getMessage());
                            }
                    }

                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormat.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormat.format(endValue)));

                }
            }
        }  

        if (parameters.get(ConstantsUtils.ORGANISATION_KEY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ORGANISATION_KEY)))) {
            queryString.append(" AND CM.ORGANIZATION_KEY LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ORGANISATION_KEY))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_ID) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_ID)))) {
            queryString.append(" AND CM.COMPANY_ID LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_ID))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NO)))) {
            queryString.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NO))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_NAME) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NAME)))) {
            queryString.append(" AND CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_NAME))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_TYPE) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_TYPE)))) {
            queryString.append(" AND CM.COMPANY_TYPE = ").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_TYPE))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_STATUS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_STATUS)))) {
            queryString.append(" AND CM.COMPANY_STATUS = ").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_STATUS))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.TRADE_CLASS) != null && !StringUtils.isBlank(String.valueOf(parameters.get(ConstantsUtils.TRADE_CLASS)))) {
            queryString.append(" AND CTS.COMPANY_TRADE_CLASS = ").append(String.valueOf(parameters.get(ConstantsUtils.TRADE_CLASS))).append(StringUtils.EMPTY);
        }
        if (parameters.get(ConstantsUtils.LIVES) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.LIVES)))) {
            queryString.append(" AND CM.LIVES LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.LIVES))).append("%'");
        }
        if (parameters.get("udc1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc1")))) {
            queryString.append(" AND UDC.UDC1 LIKE '%").append(String.valueOf(parameters.get("udc1"))).append("%'");
        }
        if (parameters.get("udc2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc2")))) {
            queryString.append(" AND UDC.UDC2 LIKE '%").append(String.valueOf(parameters.get("udc2"))).append("%'");
        }
        if (parameters.get("udc3") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc3")))) {
            queryString.append(" AND UDC.UDC3 LIKE '%").append(String.valueOf(parameters.get("udc3"))).append("%'");
        }
        if (parameters.get("udc4") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc4")))) {
            queryString.append(" AND UDC.UDC4 LIKE '%").append(String.valueOf(parameters.get("udc4"))).append("%'");
        }
        if (parameters.get("udc5") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc5")))) {
            queryString.append(" AND UDC.UDC5 LIKE '%").append(String.valueOf(parameters.get("udc5"))).append("%'");
        }
        if (parameters.get("udc6") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc6")))) {
            queryString.append(" AND UDC.UDC6 LIKE '%").append(String.valueOf(parameters.get("udc6"))).append("%'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_GROUP) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_GROUP)))) {
            queryString.append(" AND CM.COMPANY_GROUP LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_GROUP))).append("%'");
        }
        if (parameters.get(ConstantsUtils.FINANCIAL_SYSTEM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.FINANCIAL_SYSTEM)))) {
            queryString.append(" AND CM.FINANCIAL_SYSTEM LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.FINANCIAL_SYSTEM))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ADDRESS1) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ADDRESS1)))) {
            queryString.append(" AND CM.ADDRESS1 LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ADDRESS1))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ADDRESS2) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ADDRESS2)))) {
            queryString.append(" AND CM.ADDRESS2 LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ADDRESS2))).append("%'");
        }
        if (parameters.get("city") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("city")))) {
            queryString.append(" AND CM.CITY LIKE '%").append(String.valueOf(parameters.get("city"))).append("%'");
        }
        if (parameters.get(ConstantsUtils.STATE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.STATE)))) {
            queryString.append(" AND CM.STATE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.STATE))).append("%'");
        }
        if (parameters.get(ConstantsUtils.ZIPCODE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.ZIPCODE)))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.ZIPCODE))).append("%'");
        }
        if (parameters.get(ConstantsUtils.COUNTRY) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.COUNTRY)))) {
            queryString.append(" AND CM.COUNTRY LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.COUNTRY))).append("%'");
        }
        if (parameters.get(ConstantsUtils.REGION_CODE) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.REGION_CODE)))) {
            queryString.append(" AND CM.REGION_CODE LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.REGION_CODE))).append("%'");
        }
       
        if (parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO)))) {
            queryString.append(" AND CM1.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_CUSTOMER_NO))).append("%'");
        }
        if (parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER)))) {
            queryString.append(" AND CM2.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_CUSTOMER_NUMBER))).append("%'");
        }
        if (parameters.get(ConstantsUtils.TC_SD_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_SD_FROM)))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.TC_SD_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_SD_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_SD_TO)))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.TC_SD_TO))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_ED_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_ED_FROM)))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.TC_ED_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.TC_ED_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.TC_ED_TO)))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.TC_ED_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM)))) {
            queryString.append(" AND CM.COMPANY_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO)))) {
            queryString.append(" AND CM.COMPANY_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_START_DATE_TO))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM)))) {
            queryString.append(" AND CM.COMPANY_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO)))) {
            queryString.append(" AND CM.COMPANY_END_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.CUSTOMER_END_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_START_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_FROM)))) {
            queryString.append(" AND CPD.PARENT_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_TO)))) {
            queryString.append(" AND CPD.PARENT_START_DATE <='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_START_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM)))) {
            queryString.append(" AND CPD.PARENT_END_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PARENT_END_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_TO)))) {
            queryString.append(" AND CPD.PARENT_END_DATE LIKE <='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_TO))).append("'");
        }

        if (parameters.get(ConstantsUtils.PARENT_END_DATE_FROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM)))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE >='").append(String.valueOf(parameters.get(ConstantsUtils.PARENT_END_DATE_FROM))).append("'");
        }
        if (parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO)))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE LIKE <='").append(String.valueOf(parameters.get(ConstantsUtils.PRIOR_PARENT_START_DATE_TO))).append("'");
        }
      return queryString.toString();
    }
}
