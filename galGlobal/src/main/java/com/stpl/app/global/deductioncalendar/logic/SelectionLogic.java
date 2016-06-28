/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic;

import com.stpl.app.global.common.util.HelperListUtil;
import com.stpl.app.global.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.global.deductioncalendar.ui.util.HeaderUtils;
import com.stpl.app.model.HelperTable;
import com.stpl.app.util.Constants;
import static com.stpl.app.util.GeneralCommonUtils.ZERO;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.data.Container;
import com.vaadin.data.util.filter.Between;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.SimpleStringFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.DeductionDetailsDTO;
import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.item.util.CommonUtils;
import com.stpl.app.service.RsModelLocalServiceUtil;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.app.util.HelperUtils;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.util.BeanItem;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class SelectionLogic {

    private final HelperListUtil helperListUtil = HelperListUtil.getInstance();

    static HashMap<String, String> criteria = new HashMap<String, String>();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SelectionLogic.class);
    /**
     * The format double.
     */
    private static final DecimalFormat FORMATDECIMAL = new DecimalFormat("###,##0.000000");

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
            final Set<Container.Filter> filterSet, final List<SortByColumn> columns, final String availableOrselected) throws ParseException, com.stpl.portal.kernel.search.ParseException {

        final Object object;

        Map<String, Object> parameters = new HashMap<String, Object>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String columnName = StringUtils.EMPTY;
        String dbColumnName = StringUtils.EMPTY;
        StringBuilder queryString = new StringBuilder();
        if (availableOrselected.equals("available")) {
            queryString = new StringBuilder(CustomSQLUtil.get(isCount ? "available-customer-search-count" : "available-customer-search-results"));

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

            queryString.append("WHERE  CM.INBOUND_STATUS <> 'D' ");

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

            if (selectionDTO.getTradeClass() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getTradeClass()))) {
                try {
                    String tradeClassValue = String.valueOf(selectionDTO.getTradeClass());
                    if (tradeClassValue != null && !tradeClassValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("COMPANY_TRADE_CLASS", tradeClassValue);
                        if (helperId != 0) {
                            queryString.append(" AND HT_CTS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            if (selectionDTO.getCustomerType() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getCustomerType()))) {
                try {
                    String customerTypeValue = String.valueOf(selectionDTO.getCustomerType());
                    if (customerTypeValue != null && !customerTypeValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("COMPANY_TYPE", customerTypeValue);
                        queryString.append(" AND HT_CT.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            if (selectionDTO.getCustomerStatus() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getCustomerStatus()))) {
                try {
                    String customerStatusValue = String.valueOf(selectionDTO.getCustomerStatus());
                    if (customerStatusValue != null && !customerStatusValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("STATUS", customerStatusValue);
                        queryString.append(" AND HT_CS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        } else if (availableOrselected.equals("selected")) {
            queryString = new StringBuilder(CustomSQLUtil.get(isCount ? "selected-customer-search-count" : "selected-customer-search-results"));
            queryString.append("WHERE  CM.INBOUND_STATUS <> 'D' ");

            queryString.append(" AND ST.USER_ID = ").append(selectionDTO.getUserId()).append(" AND ST.SESSION_ID = '").append(selectionDTO.getSessionId()).append("'");
        }

        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if ("customerType".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("COMPANY_TYPE", filterValue);
                                parameters.put("customerType", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                    if ("customerStatus".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("STATUS", filterValue);
                                parameters.put("customerStatus", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                    if ("tradeClass".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("COMPANY_TRADE_CLASS", filterValue);
                                parameters.put("tradeClass", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
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

        if ((parameters.get("organisationKey") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("organisationKey"))))) {
            queryString.append(" AND CM.ORGANIZATION_KEY LIKE '%").append(String.valueOf(parameters.get("organisationKey"))).append("%'");
        }
        if ((parameters.get("customerId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerId"))))) {
            queryString.append(" AND CM.COMPANY_ID LIKE '%").append(String.valueOf(parameters.get("customerId"))).append("%'");
        }
        if ((parameters.get("customerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerNo"))))) {
            queryString.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("customerNo"))).append("%'");
        }
        if ((parameters.get("customerName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerName"))))) {
            queryString.append(" AND CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get("customerName"))).append("%'");
        }
        if ((parameters.get("customerType") != null && !StringUtils.isBlank(String.valueOf(parameters.get("customerType"))))) {
            queryString.append(" AND CM.COMPANY_TYPE = ").append(String.valueOf(parameters.get("customerType"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("customerStatus") != null && !StringUtils.isBlank(String.valueOf(parameters.get("customerStatus"))))) {
            queryString.append(" AND CM.COMPANY_STATUS = ").append(String.valueOf(parameters.get("customerStatus"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("tradeClass") != null && !StringUtils.isBlank(String.valueOf(parameters.get("tradeClass"))))) {
            queryString.append(" AND CTS.COMPANY_TRADE_CLASS = ").append(String.valueOf(parameters.get("tradeClass"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("lives") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("lives"))))) {
            queryString.append(" AND CM.LIVES LIKE '%").append(String.valueOf(parameters.get("lives"))).append("%'");
        }
        if ((parameters.get("udc1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc1"))))) {
            queryString.append(" AND UDC.UDC1 LIKE '%").append(String.valueOf(parameters.get("udc1"))).append("%'");
        }
        if ((parameters.get("udc2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc2"))))) {
            queryString.append(" AND UDC.UDC2 LIKE '%").append(String.valueOf(parameters.get("udc2"))).append("%'");
        }
        if ((parameters.get("udc3") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc3"))))) {
            queryString.append(" AND UDC.UDC3 LIKE '%").append(String.valueOf(parameters.get("udc3"))).append("%'");
        }
        if ((parameters.get("udc4") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc4"))))) {
            queryString.append(" AND UDC.UDC4 LIKE '%").append(String.valueOf(parameters.get("udc4"))).append("%'");
        }
        if ((parameters.get("udc5") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc5"))))) {
            queryString.append(" AND UDC.UDC5 LIKE '%").append(String.valueOf(parameters.get("udc5"))).append("%'");
        }
        if ((parameters.get("udc6") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc6"))))) {
            queryString.append(" AND UDC.UDC6 LIKE '%").append(String.valueOf(parameters.get("udc6"))).append("%'");
        }
        if ((parameters.get("customerGroup") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerGroup"))))) {
            queryString.append(" AND CM.COMPANY_GROUP LIKE '%").append(String.valueOf(parameters.get("customerGroup"))).append("%'");
        }
        if ((parameters.get("financialSystem") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("financialSystem"))))) {
            queryString.append(" AND CM.FINANCIAL_SYSTEM LIKE '%").append(String.valueOf(parameters.get("financialSystem"))).append("%'");
        }
        if ((parameters.get("address1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("address1"))))) {
            queryString.append(" AND CM.ADDRESS1 LIKE '%").append(String.valueOf(parameters.get("address1"))).append("%'");
        }
        if ((parameters.get("address2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("address2"))))) {
            queryString.append(" AND CM.ADDRESS2 LIKE '%").append(String.valueOf(parameters.get("address2"))).append("%'");
        }
        if ((parameters.get("city") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("city"))))) {
            queryString.append(" AND CM.CITY LIKE '%").append(String.valueOf(parameters.get("city"))).append("%'");
        }
        if ((parameters.get("state") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("state"))))) {
            queryString.append(" AND CM.STATE LIKE '%").append(String.valueOf(parameters.get("state"))).append("%'");
        }
        if ((parameters.get("zipCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("zipCode"))))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get("zipCode"))).append("%'");
        }
        if ((parameters.get("country") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("country"))))) {
            queryString.append(" AND CM.COUNTRY LIKE '%").append(String.valueOf(parameters.get("country"))).append("%'");
        }
        if ((parameters.get("regionCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("regionCode"))))) {
            queryString.append(" AND CM.REGION_CODE LIKE '%").append(String.valueOf(parameters.get("regionCode"))).append("%'");
        }
        if ((parameters.get("zipCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("zipCode"))))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get("zipCode"))).append("%'");
        }
        if ((parameters.get("parentCustomerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentCustomerNo"))))) {
            queryString.append(" AND CM1.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("parentCustomerNo"))).append("%'");
        }
        if ((parameters.get("priorParentCustomerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentCustomerNo"))))) {
            queryString.append(" AND CM2.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("priorParentCustomerNo"))).append("%'");
        }
        if ((parameters.get("tradeClassStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassStartDatefrom"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE >='").append(String.valueOf(parameters.get("tradeClassStartDatefrom"))).append("'");
        }
        if ((parameters.get("tradeClassStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassStartDateto"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE <='").append(String.valueOf(parameters.get("tradeClassStartDateto"))).append("'");
        }
        if ((parameters.get("tradeClassEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassEndDatefrom"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE >='").append(String.valueOf(parameters.get("tradeClassEndDatefrom"))).append("'");
        }
        if ((parameters.get("tradeClassEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassEndDateto"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE <='").append(String.valueOf(parameters.get("tradeClassEndDateto"))).append("'");
        }

        if ((parameters.get("customerStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerStartDatefrom"))))) {
            queryString.append(" AND CM.COMPANY_START_DATE >='").append(String.valueOf(parameters.get("customerStartDatefrom"))).append("'");
        }
        if ((parameters.get("customerStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerStartDateto"))))) {
            queryString.append(" AND CM.COMPANY_START_DATE <='").append(String.valueOf(parameters.get("customerStartDateto"))).append("'");
        }
        if ((parameters.get("customerEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerEndDatefrom"))))) {
            queryString.append(" AND CM.COMPANY_END_DATE >='").append(String.valueOf(parameters.get("customerEndDatefrom"))).append("'");
        }
        if ((parameters.get("customerEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerEndDateto"))))) {
            queryString.append(" AND CM.COMPANY_END_DATE <='").append(String.valueOf(parameters.get("customerEndDateto"))).append("'");
        }

        if ((parameters.get("parentStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentStartDatefrom"))))) {
            queryString.append(" AND CPD.PARENT_START_DATE >='").append(String.valueOf(parameters.get("parentStartDatefrom"))).append("'");
        }
        if ((parameters.get("parentStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentStartDateto"))))) {
            queryString.append(" AND CPD.PARENT_START_DATE <='").append(String.valueOf(parameters.get("parentStartDateto"))).append("'");
        }

        if ((parameters.get("parentEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEndDatefrom"))))) {
            queryString.append(" AND CPD.PARENT_END_DATE >='").append(String.valueOf(parameters.get("parentEndDatefrom"))).append("'");
        }
        if ((parameters.get("parentEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEndDateto"))))) {
            queryString.append(" AND CPD.PARENT_END_DATE LIKE <='").append(String.valueOf(parameters.get("parentEndDateto"))).append("'");
        }

        if ((parameters.get("priorParentStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentStartDatefrom"))))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE >='").append(String.valueOf(parameters.get("priorParentStartDatefrom"))).append("'");
        }
        if ((parameters.get("priorParentStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentStartDateto"))))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE LIKE <='").append(String.valueOf(parameters.get("priorParentStartDateto"))).append("'");
        }

        dbColumnName = "COMPANY_ID";
        String orderBy = "ASC";
        HeaderUtils.loadColumnNames();
        if (columns != null) {
            for (final Iterator<SortByColumn> iterator = columns.iterator(); iterator.hasNext();) {
                final SortByColumn orderByColumn = (SortByColumn) iterator.next();
                columnName = orderByColumn.getName();
                if (columnName.equals("customerNo")) {
                    dbColumnName = "CM.COMPANY_NO";
                } else if (columnName.equals("parentCustomerNo")) {
                    dbColumnName = "CM1.COMPANY_NO";
                } else if (columnName.equals("priorParentCustomerNo")) {
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
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryString.toString(), this, this);

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
    private List<SelectionDTO> customizeCustomerList(final List<Object[]> list) throws ParseException, com.stpl.portal.kernel.search.ParseException {
        final List<SelectionDTO> resultList = new ArrayList<>();
        Map<Integer, HelperDTO> idHelperDTOMap = helperListUtil.getIdHelperDTOMap();
        for (Object[] object : list) {

            SelectionDTO selectionDTO = new SelectionDTO();
            if (object[0] != null) {
                if (idHelperDTOMap.get(object[0]) != null && !object[0].toString().equals(ConstantsUtils.ZERO)) {
                    selectionDTO.setOrganisationKey(idHelperDTOMap.get(object[0]));
                }

            }
            selectionDTO.setCustomerId(String.valueOf(object[1]));
            selectionDTO.setCustomerNo(String.valueOf(object[2]));
            selectionDTO.setCustomerName(String.valueOf(object[3]));
            selectionDTO.setTradeClass(idHelperDTOMap.get(object[4]));
            if (object[5] != null) {
                selectionDTO.setTradeClassStartDate((Date) object[5]);
            }
            if (object[6] != null) {
                selectionDTO.setTradeClassEndDate((Date) object[6]);
            }
            selectionDTO.setCustomerType(idHelperDTOMap.get(object[7]));
            selectionDTO.setCustomerStatus(idHelperDTOMap.get(object[8]));
            selectionDTO.setLives(String.valueOf(object[9]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[9]));
            if (object[10] != null) {
                selectionDTO.setCustomerStartDate((Date) object[10]);
            }
            if (object[11] != null) {
                selectionDTO.setCustomerEndDate((Date) object[11]);
            }
            if (object[12] != null && !object[12].toString().equals(ConstantsUtils.ZERO)) {

                selectionDTO.setUdc1(idHelperDTOMap.get(object[12]));
            }

            if (object[13] != null && !object[13].toString().equals(ConstantsUtils.ZERO)) {

                selectionDTO.setUdc2(idHelperDTOMap.get(object[13]));
            }
            if (object[14] != null && !object[14].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc3(idHelperDTOMap.get(object[14]));
            }
            if (object[15] != null && !object[15].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc4(idHelperDTOMap.get(object[15]));
            }
            if (object[16] != null && !object[16].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc5(idHelperDTOMap.get(object[16]));
            }
            if (object[17] != null && !object[17].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setUdc6(idHelperDTOMap.get(object[17]));
            }
            if (object[18] != null && !object[18].toString().equals(ConstantsUtils.ZERO)) {
                selectionDTO.setCustomerGroup(idHelperDTOMap.get(object[18]));
            }

            selectionDTO.setFinancialSystem(String.valueOf(object[19]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[19]));
            selectionDTO.setAddress1(String.valueOf(object[20]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[20]));
            selectionDTO.setAddress2(String.valueOf(object[21]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[21]));
            selectionDTO.setCity(String.valueOf(object[22]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[22]));
            selectionDTO.setState(String.valueOf(object[23]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[23]));
            selectionDTO.setZipCode(String.valueOf(object[24]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[24]));
            selectionDTO.setCountry(String.valueOf(object[25]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[25]));
            selectionDTO.setRegionCode(String.valueOf(object[26]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[26]));
            selectionDTO.setParentCustomerNo(String.valueOf(object[27]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[27]));
            if (object[28] != null) {
                selectionDTO.setParentStartDate((Date) object[28]);
            }
            if (object[29] != null) {
                selectionDTO.setParentEndDate((Date) object[29]);
            }
            if (object[30] != null) {
                selectionDTO.setPriorParentStartDate((Date) object[30]);
            }
            selectionDTO.setPriorParentCustomerNo(String.valueOf(object[31]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : String.valueOf(object[31]));
            selectionDTO.setCompanyMasterSid(String.valueOf(object[32]).equals(ConstantsUtils.NULL) ? 0 : Integer.valueOf(String.valueOf(object[32])));
            selectionDTO.setParentCompanyMasterSid(String.valueOf(object[33]).equals(ConstantsUtils.NULL) ? 0 : Integer.valueOf(String.valueOf(object[33])));
            resultList.add(selectionDTO);
        }
        return resultList;
    }

    public void resetCompanyAndItem(final SessionDTO sessionDTO, final Boolean isItem) {

        final StringBuilder queryString = new StringBuilder("DELETE FROM ST_SELECTION_TABLE WHERE USER_ID='");
        queryString.append(sessionDTO.getUserId()).
                append("' AND SESSION_ID='").append(sessionDTO.getUiSessionId()).append("' AND SELECTION_TYPE='");
        queryString.append(isItem ? "I" : "C").append("';");
        Object obj = RsModelLocalServiceUtil.executeUpdateQuery(queryString.toString(), this, this);
    }

    public void moveCustomersAndSaveToTempTable(final SelectionDTO selDTO, final SessionDTO sessionDTO) throws SystemException, PortalException {
        if (selDTO != null) {
            
            String selectedComapnyIds = String.valueOf(selDTO.getCompanyMasterSid());
            
            String queryString = CustomSQLUtil.get("moveAndMergeToTempTable");
            queryString = queryString.replace("?COMPANY_FECTH", getQuery(true,selDTO,null));
            queryString = queryString.replace("?UID", sessionDTO.getUserId());
            queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");    
            queryString = queryString.replace("?COMPANY_ITEM_SIDS", selectedComapnyIds);
            RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);            
        }
    }

    public void deleteCustomersFromTempTable(final String selDTO, final SessionDTO sessionDTO) throws SystemException, PortalException {
        if (StringUtils.isNotBlank(selDTO)) {
            String query= CustomSQLUtil.get("cp-delete-company")
                    .replace("?CMSID", selDTO)
                    .replace("?UID", "'"+sessionDTO.getUserId()+"'")
                    .replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
            RsModelLocalServiceUtil.executeUpdateQuery(query, this, this);
        }
    }

    public void addAllCustomersAndSaveToTempTable(final SessionDTO sessionDTO,final SelectionDTO selDTO,Set<Container.Filter> filter) throws SystemException, PortalException {
        
        String queryString = CustomSQLUtil.get("moveAndMergeToTempTable");
        queryString = queryString.replace("?COMPANY_FECTH", getQuery(false,selDTO,filter));    
        queryString = queryString.replace("?UID", sessionDTO.getUserId());
        queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
        RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);
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
    public void addAllItemsAndSaveToTempTable(final SessionDTO sessionDTO,final ErrorfulFieldGroup searchItemForm, boolean isAddAll, List<Integer> selectedItemSID,Set<Container.Filter> filter) throws SystemException, PortalException {
        
        String queryString = CustomSQLUtil.get("moveAndMergeItemsToTempTable");
        queryString = queryString.replace("?ITEM_FECTH", isAddAll? buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, null, filter, true) : buildSearchQuery(null, true, 0, 0, StringUtils.join(selectedItemSID, ","), null, null, true));    
        queryString = queryString.replace("?UID", sessionDTO.getUserId());
        queryString = queryString.replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
        RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);
    }

    public void deleteAllCustomersFromTempTable(final SessionDTO sessionDTO) throws SystemException, PortalException {
        String query= CustomSQLUtil.get("cp-deleteall-company")
                    .replace("?UID", "'"+sessionDTO.getUserId()+"'")
                    .replace("?SID", "'"+sessionDTO.getUiSessionId()+"'");
            RsModelLocalServiceUtil.executeUpdateQuery(query, this, this);
    }

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

    public void saveToTempDeductionDetails(SessionDTO sessionDTO, DeductionDetailsDTO detailsDto) {
        String queryString = CustomSQLUtil.get("saveToTempDeductionDetails")
        .replace("@USERID", sessionDTO.getUserId())
        .replace("@SESSIONID", sessionDTO.getUiSessionId())
        .replace("@STARTPERIOD", detailsDto.getForecastFromDate())
        .replace("@ENDPERIOD", detailsDto.getForecastToDate());
        RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);
    }

    public List<SelectionDTO> getAvailableTableResult(ErrorfulFieldGroup searchItemForm, int start, int end, List<SortByColumn> columns, final Set<Container.Filter> filterSet)/*,  BeanSearchCriteria criteria)*/ throws SystemException, PortalException, Exception {
        List<SelectionDTO> selectionDTO;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchItemForm, false, start, end, StringUtils.EMPTY, columns, filterSet, false);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        selectionDTO = getCustomizedSearchFormFromObject(list);
        return selectionDTO;
    }

    public int getAvailableTableCount(ErrorfulFieldGroup searchItemForm, List<SortByColumn> columns, final Set<Container.Filter> filterSet)/*, List<OrderByColumn> orderByColumns, BeanSearchCriteria criteria)*/ throws SystemException, PortalException, Exception {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, columns, filterSet, false);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null && !list.isEmpty()) {
            count += list.size();
        }
        return count;
    }

    public List<SelectionDTO> getSelectedTableResult(SessionDTO sessionDTO, int start, int end, List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, PortalException, Exception {
        List<SelectionDTO> selectionDTO;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildAvailableQuery(sessionDTO, false, columns, filterSet);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);

        StringBuilder queryBuilder1 = new StringBuilder();
        queryBuilder1 = buildSearchQuery(null, false, start, end, StringUtils.join(list, ','), columns, filterSet, false);
        final List list1 = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder1.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        selectionDTO = getCustomizedSearchFormFromObject(list1);
        return selectionDTO;
    }

    public int getSelectedTableCount(SessionDTO sessionDTO, List<SortByColumn> columns, final Set<Container.Filter> filterSet) throws SystemException, PortalException, Exception {
        int count = 0;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildAvailableQuery(sessionDTO, true, columns, filterSet);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            count += Integer.valueOf(String.valueOf(ob));
        }
        return count;
    }

    private StringBuilder buildAvailableQuery(SessionDTO sessionDTO, boolean isCount, List<SortByColumn> columns, final Set<Container.Filter> filterSet) {
        StringBuilder queryBuilder = new StringBuilder();
        String query;
        if(isCount){
            query = "DISTINCT COUNT(SD.ITEM_MASTER_SID) FROM ST_DEDUCTION_CALENDAR_ITEM SD JOIN ITEM_MASTER IM ON SD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                + "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n"
                + "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER' \n"
                + "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID";
        }else{
            query = "DISTINCT SD.ITEM_MASTER_SID FROM ST_DEDUCTION_CALENDAR_ITEM SD JOIN ITEM_MASTER IM ON SD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID\n"
                + "LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n"
                + "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER' \n"
                + "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID";
            
        }
        queryBuilder.append(" SELECT " + query + " WHERE ");
        queryBuilder.append("  USER_ID='").append(sessionDTO.getUserId()).
                append("' AND SESSION_ID='").append(sessionDTO.getUiSessionId()).append("' ");
        
        String filterQuery = StringUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("itemId", "IM.item_Id");
        detailsColumn.put("item", "IM.item_no");
        detailsColumn.put("itemName", "IM.item_Name");
        detailsColumn.put("itemDesc", "IM.item_Desc");
        detailsColumn.put("itemType", "IM.item_Type");
        detailsColumn.put("itemStatus", "IM.item_Status");
        detailsColumn.put("itemCode", "IM.item_Code");
        detailsColumn.put("packageSizeCode", "IM.package_Size_Code");
        detailsColumn.put("itemStartDate", "IM.item_Start_Date");
        detailsColumn.put("itemEndDate", "IM.item_End_Date");
        detailsColumn.put("labelerCode", "IM.labeler_Code");
        detailsColumn.put("form", "IM.form");
        detailsColumn.put("strength", "IM.strength");
        detailsColumn.put("primaryUOM", "IM.primary_UOM");
        detailsColumn.put("secondaryUOM", "IM.secondary_UOM");
        detailsColumn.put("itemClass", "IM.item_Class");
        detailsColumn.put("pediatricExclusiveStartDate", "IM.pediatric_Exclusive_Start_Date");
        detailsColumn.put("pediatricExclusiveEndDate", "IM.pediatric_Exclusive_End_Date");
        detailsColumn.put("clottingFactorStartDate", "IM.clotting_Factor_Start_Date");
        detailsColumn.put("clottingFactorEndDate", "IM.clotting_Factor_End_Date");
        detailsColumn.put("psNDC9", "IM.NDC9");
        detailsColumn.put("psNDC8", "IM.NDC8");
        detailsColumn.put("therapeuticClass", "IM.therapeutic_Class");
        detailsColumn.put("packageSizeIntroDate", "IM.package_Size_Intro_Date");
        detailsColumn.put("manufacturerID", "CM.COMPANY_ID");
        detailsColumn.put("manufacturerNO", "CM.COMPANY_NO");
        detailsColumn.put("manufacturerName", "CM.COMPANY_NAME");
        detailsColumn.put("productOrganizationKey", "IM.organization_Key");
        detailsColumn.put("acquisitionDate", "IM.acquisition_Date");
        detailsColumn.put("authorizedGeneric", "IM.authorized_Generic");
        detailsColumn.put("authorizedGenericStartDate", "IM.authorized_Generic_Start_Date");
        detailsColumn.put("authorizedGenericEndDate", "IM.authorized_Generic_End_Date");
        detailsColumn.put("firstSaleDate", "IM.first_Sale_Date");
        detailsColumn.put("itemTypeIndicator", "IM.ITEM_TYPE_INDICATION");
        detailsColumn.put("marketTerminationDate", "IM.market_Termination_Date");
        detailsColumn.put("newFormulationIndicator", "IM.new_Formulation_Indicator");
        detailsColumn.put("newFormulation", "IM.new_Formulation");
        detailsColumn.put("newFormulationStartDate", "IM.new_Formulation_Start_Date");
        detailsColumn.put("newFormulationEndDate", "IM.new_Formulation_End_Date");
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
        detailsColumn.put("discontinuationDate", "IM.discontinuation_Date");
        detailsColumn.put("lastLotExpirationDate", "IM.last_Lot_Expiration_Date");
        detailsColumn.put("baselineAMP", "IM.baseline_AMP");
        detailsColumn.put("baseYearCPI", "IM.BASE_CPI_PERIOD");
        detailsColumn.put("psUDC1", "UDCS.UDC1");
        detailsColumn.put("psUDC2", "UDCS.UDC2");
        detailsColumn.put("psUDC3", "UDCS.UDC3");
        detailsColumn.put("psUDC4", "UDCS.UDC4");
        detailsColumn.put("psUDC5", "UDCS.UDC5");
        detailsColumn.put("psUDC6", "UDCS.UDC6");
        detailsColumn.put("displayBrand", "BM.DISPLAY_BRAND");
        detailsColumn.put("brand", "BM.BRAND_NAME");
        detailsColumn.put("itemClass", "IM.ITEM_CLASS"); 
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";

                    if (!filterString.contains("Show all")) {
                        filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    }

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String filterString = formatter.format(stringFilter.getStartValue());
                    String filterString1 = formatter.format(stringFilter.getEndValue());
                    if ("itemStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.item_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.item_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("itemEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.item_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.item_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("packageSizeIntroDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.package_Size_Intro_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.package_Size_Intro_Date <= '" + filterString1 + "' ";
                    }
                    if ("acquisitionDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.acquisition_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.acquisition_Date <= '" + filterString1 + "' ";
                    }
                    if ("pediatricExclusiveStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("pediatricExclusiveEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("clottingFactorStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.clotting_Factor_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.clotting_Factor_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("clottingFactorEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.clotting_Factor_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.clotting_Factor_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("authorizedGenericStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.authorized_Generic_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.authorized_Generic_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("authorizedGenericEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.authorized_Generic_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.authorized_Generic_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("firstSaleDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.first_Sale_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.first_Sale_Date <= '" + filterString1 + "' ";
                    }
                    if ("newFormulationEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.new_Formulation_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.new_Formulation_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("discontinuationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.discontinuation_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.discontinuation_Date <= '" + filterString1 + "' ";
                    }
                    if ("lastLotExpirationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.last_Lot_Expiration_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.last_Lot_Expiration_Date <= '" + filterString1 + "' ";
                    }
                    if ("marketTerminationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.market_Termination_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.market_Termination_Date <= '" + filterString1 + "' ";
                    }
                    if ("newFormulationStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.new_Formulation_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.new_Formulation_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("baseYearCPI".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.BASE_CPI_PERIOD >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.BASE_CPI_PERIOD <= '" + filterString1 + "' ";
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
                              "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID" : CustomSQLUtil.get("DeductionCalendarItemSelectionSearch");
        }else{
            query = isCount ? "DISTINCT ITEM_MASTER_SID FROM ITEM_MASTER IM LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID=IM.MANUFACTURER_ID \n" +
                                "LEFT join UDCS UDCS ON UDCS.MASTER_SID=IM.ITEM_MASTER_SID AND UDCS.MASTER_TYPE='ITEM_MASTER'  \n" +
                                "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID " : CustomSQLUtil.get("DeductionCalendarItemSelectionSearch");
        }
        queryBuilder.append(" SELECT " + query + " WHERE IM.INBOUND_STATUS <> 'D' ");
        if (criteria.isEmpty()) {
            loadCriteriaInMap();
        }
        if (searchFields != null) {
            SelectionDTO selectionDTO = getBeanFromId(searchFields.getItemDataSource());
            Set<String> keys = criteria.keySet();
            for (String fields : keys) {
                if ("itemTypeDdlb".equals(fields)) {
                    if (selectionDTO.getItemTypeDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getItemTypeDdlb().getDescription().toString()) && !selectionDTO.getItemTypeDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = '").append(selectionDTO.getItemTypeDdlb().getId()).append("'");
                    }
                }
                if ("brandDdlb".equals(fields)) {
                    if (selectionDTO.getBrandDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getBrandDdlb().getDescription().toString()) && !selectionDTO.getBrandDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = '").append(selectionDTO.getBrandDdlb().getId()).append("'");
                    }
                }
                if ("formDdlb".equals(fields)) {
                    if (selectionDTO.getFormDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getFormDdlb().getDescription().toString()) && !selectionDTO.getFormDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = '").append(selectionDTO.getFormDdlb().getId()).append("'");
                    }
                }
                if ("strengthDdlb".equals(fields)) {
                    if (selectionDTO.getStrengthDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getStrengthDdlb().getDescription().toString()) && !selectionDTO.getStrengthDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = '").append(selectionDTO.getStrengthDdlb().getId()).append("'");
                    }
                }
                if ("therapeuticclassDdlb".equals(fields)) {
                    if (selectionDTO.getTherapeuticclassDdlb() != null && !ConstantUtil.SELECT_ONE.equals(selectionDTO.getTherapeuticclassDdlb().getDescription().toString()) && !selectionDTO.getTherapeuticclassDdlb().getDescription().toString().trim().isEmpty()) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" = '").append(selectionDTO.getTherapeuticclassDdlb().getId()).append("'");
                    }
                }
                if ("itemDesc".equals(fields)) {
                    if (selectionDTO.getItemDesc() != null && !selectionDTO.getItemDesc().trim().isEmpty() &&!"*".equals(selectionDTO.getItemDesc().trim())) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" LIKE '").append(selectionDTO.getItemDesc().toString().trim().replace("*", "%")).append("'");
                    }
                }
                if ("item".equals(fields)) {
                    if (selectionDTO.getItem() != null && !selectionDTO.getItem().toString().trim().isEmpty() &&!"*".equals(selectionDTO.getItem().trim())) {
                        queryBuilder.append(" AND ").append(criteria.get(fields)).append(" LIKE '").append(selectionDTO.getItem().toString().trim().replace("*", "%")).append("'");
                    }
                }
            }
        } else {
            queryBuilder.append(" AND ").append("ITEM_MASTER_SID in (").append(itemSystemId).append(")");
        }
        String filterQuery = StringUtils.EMPTY;
        String finalQuery = StringUtils.EMPTY;
        HashMap<String, String> detailsColumn = new HashMap<String, String>();
        detailsColumn.put("itemId", "IM.item_Id");
        detailsColumn.put("item", "IM.item_no");
        detailsColumn.put("itemName", "IM.item_Name");
        detailsColumn.put("itemDesc", "IM.item_Desc");
        detailsColumn.put("itemType", "IM.item_Type");
        detailsColumn.put("itemStatus", "IM.item_Status");
        detailsColumn.put("itemCode", "IM.item_Code");
        detailsColumn.put("packageSizeCode", "IM.package_Size_Code");
        detailsColumn.put("itemStartDate", "IM.item_Start_Date");
        detailsColumn.put("itemEndDate", "IM.item_End_Date");
        detailsColumn.put("labelerCode", "IM.labeler_Code");
        detailsColumn.put("form", "IM.form");
        detailsColumn.put("strength", "IM.strength");
        detailsColumn.put("primaryUOM", "IM.primary_UOM");
        detailsColumn.put("secondaryUOM", "IM.secondary_UOM");
        detailsColumn.put("itemClass", "IM.item_Class");
        detailsColumn.put("pediatricExclusiveStartDate", "IM.pediatric_Exclusive_Start_Date");
        detailsColumn.put("pediatricExclusiveEndDate", "IM.pediatric_Exclusive_End_Date");
        detailsColumn.put("clottingFactorStartDate", "IM.clotting_Factor_Start_Date");
        detailsColumn.put("clottingFactorEndDate", "IM.clotting_Factor_End_Date");
        detailsColumn.put("psNDC9", "IM.NDC9");
        detailsColumn.put("psNDC8", "IM.NDC8");
        detailsColumn.put("therapeuticClass", "IM.therapeutic_Class");
        detailsColumn.put("packageSizeIntroDate", "IM.package_Size_Intro_Date");
        detailsColumn.put("manufacturerID", "CM.COMPANY_ID");
        detailsColumn.put("manufacturerNO", "CM.COMPANY_NO");
        detailsColumn.put("manufacturerName", "CM.COMPANY_NAME");
        detailsColumn.put("productOrganizationKey", "IM.organization_Key");
        detailsColumn.put("acquisitionDate", "IM.acquisition_Date");
        detailsColumn.put("authorizedGeneric", "IM.authorized_Generic");
        detailsColumn.put("authorizedGenericStartDate", "IM.authorized_Generic_Start_Date");
        detailsColumn.put("authorizedGenericEndDate", "IM.authorized_Generic_End_Date");
        detailsColumn.put("firstSaleDate", "IM.first_Sale_Date");
        detailsColumn.put("itemTypeIndicator", "IM.ITEM_TYPE_INDICATION");
        detailsColumn.put("marketTerminationDate", "IM.market_Termination_Date");
        detailsColumn.put("newFormulationIndicator", "IM.new_Formulation_Indicator");
        detailsColumn.put("newFormulation", "IM.new_Formulation");
        detailsColumn.put("newFormulationStartDate", "IM.new_Formulation_Start_Date");
        detailsColumn.put("newFormulationEndDate", "IM.new_Formulation_End_Date");
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
        detailsColumn.put("discontinuationDate", "IM.discontinuation_Date");
        detailsColumn.put("lastLotExpirationDate", "IM.last_Lot_Expiration_Date");
        detailsColumn.put("baselineAMP", "IM.baseline_AMP");
        detailsColumn.put("baseYearCPI", "IM.BASE_CPI_PERIOD");
        detailsColumn.put("psUDC1", "UDCS.UDC1");
        detailsColumn.put("psUDC2", "UDCS.UDC2");
        detailsColumn.put("psUDC3", "UDCS.UDC3");
        detailsColumn.put("psUDC4", "UDCS.UDC4");
        detailsColumn.put("psUDC5", "UDCS.UDC5");
        detailsColumn.put("psUDC6", "UDCS.UDC6");
        detailsColumn.put("displayBrand", "BM.DISPLAY_BRAND");
        detailsColumn.put("brand", "BM.BRAND_NAME");
        detailsColumn.put("itemClass", "IM.ITEM_CLASS"); 
        if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = "%" + stringFilter.getFilterString() + "%";

                    if (!filterString.contains("Show all")) {
                        filterQuery = filterQuery + " AND " + detailsColumn.get(String.valueOf(stringFilter.getPropertyId())) + " like '" + filterString + "'";
                    }

                } else if (filter instanceof Between) {
                    Between stringFilter = (Between) filter;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String filterString = formatter.format(stringFilter.getStartValue());
                    String filterString1 = formatter.format(stringFilter.getEndValue());
                    if ("itemStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.item_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.item_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("itemEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.item_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.item_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("packageSizeIntroDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.package_Size_Intro_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.package_Size_Intro_Date <= '" + filterString1 + "' ";
                    }
                    if ("acquisitionDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.acquisition_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.acquisition_Date <= '" + filterString1 + "' ";
                    }
                    if ("pediatricExclusiveStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("pediatricExclusiveEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.pediatric_Exclusive_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("clottingFactorStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.clotting_Factor_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.clotting_Factor_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("clottingFactorEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.clotting_Factor_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.clotting_Factor_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("authorizedGenericStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.authorized_Generic_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.authorized_Generic_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("authorizedGenericEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.authorized_Generic_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.authorized_Generic_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("firstSaleDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.first_Sale_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.first_Sale_Date <= '" + filterString1 + "' ";
                    }
                    if ("newFormulationEndDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.new_Formulation_End_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.new_Formulation_End_Date <= '" + filterString1 + "' ";
                    }
                    if ("discontinuationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.discontinuation_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.discontinuation_Date <= '" + filterString1 + "' ";
                    }
                    if ("lastLotExpirationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.last_Lot_Expiration_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.last_Lot_Expiration_Date <= '" + filterString1 + "' ";
                    }
                    if ("marketTerminationDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.market_Termination_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.market_Termination_Date <= '" + filterString1 + "' ";
                    }
                    if ("newFormulationStartDate".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.new_Formulation_Start_Date >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.new_Formulation_Start_Date <= '" + filterString1 + "' ";
                    }
                    if ("baseYearCPI".equals(stringFilter.getPropertyId())) {
                        filterQuery = filterQuery + " AND IM.BASE_CPI_PERIOD >= '" + filterString + "' ";
                        filterQuery = filterQuery + " AND IM.BASE_CPI_PERIOD <= '" + filterString1 + "' ";
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
        criteria.put("itemDesc", "IM.ITEM_DESC");
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
            final List list) throws PortalException, SystemException, Exception {

        LOGGER.info("Entering getCustomizedSearchFormFromObject p1: " + ((list == null) ? list : list.size()) + " p2: ");
        final List<SelectionDTO> searchItemList = new ArrayList<SelectionDTO>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final SelectionDTO searchItemForm = new SelectionDTO();
                final Object[] obj = (Object[]) list.get(i);
                searchItemForm.setItemSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setSystemID(HelperUtils.getString(obj[0]));
                searchItemForm.setItemId(HelperUtils.getString(obj[1]));
                searchItemForm.setItem(HelperUtils.getString(obj[2]));
                searchItemForm.setItemName(HelperUtils.getString(obj[3]));
                searchItemForm.setItemDesc(HelperUtils.getString(obj[4]));

                searchItemForm.setItemType(CommonUtils.getDescription(HelperUtils.getInteger(obj[5])));
                searchItemForm.setItemStatus(CommonUtils.getDescription(HelperUtils.getInteger(obj[6])));

                searchItemForm.setRecordLockStatus(Boolean.parseBoolean(HelperUtils.getString(obj[7])));
                searchItemForm.setItemCode(HelperUtils.getString(obj[8]));
                searchItemForm.setPackageSizeCode(HelperUtils.getString(obj[9]));

                if (obj[12] == null) {
                    searchItemForm.setPsUP("0.000000");
                } else {
                    searchItemForm.setPsUP(FORMATDECIMAL.format(Double.valueOf(HelperUtils.getString(obj[10]))));
                }
                if (obj[11] != null) {
                    searchItemForm.setItemStartDate((Date) obj[11]);
                }
                if (obj[12] != null) {
                    searchItemForm.setItemEndDate((Date) obj[12]);
                }
                
                searchItemForm.setLabelerCode(HelperUtils.getString(obj[13]));
                searchItemForm.setForm(obj[14] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[14]));
                searchItemForm.setStrength(obj[15] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[15]));
                searchItemForm.setPrimaryUOM(obj[16] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[16]));
                searchItemForm.setSecondaryUOM(obj[17] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[17]));
                searchItemForm.setItemClass(CommonUtils.getDescription(HelperUtils.getInteger(obj[18])));

                if (obj[19] != null) {
                    searchItemForm.setPediatricExclusiveStartDate((Date) obj[19]);
                }
                if (obj[20] != null) {
                    searchItemForm.setPediatricExclusiveEndDate((Date) obj[20]);
                }

                searchItemForm.setClottingFactorStartDate(String.valueOf(obj[21]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : parseDateLogic(obj[21]));
                searchItemForm.setClottingFactorEndDate(String.valueOf(obj[22]).equals(ConstantsUtils.NULL) ? StringUtils.EMPTY : parseDateLogic(obj[22]));
                searchItemForm.setBrand(HelperUtils.getString(obj[23]));
                searchItemForm.setPsNDC8(HelperUtils.getString(obj[24]));
                searchItemForm.setPsNDC9(HelperUtils.getString(obj[25]));
                searchItemForm.setTherapeuticClass(obj[26] != null && !StringUtils.isEmpty(obj[26].toString()) && !ConstantsUtils.ZERO.equals(obj[26]) && !ConstantsUtils.SELECT_ONE.equals(obj[26])
                        ? CommonUtils.getDescription((Integer) obj[26]) : StringUtils.EMPTY);

                                if (obj[27] != null) {
                    searchItemForm.setPackageSizeIntroDate((Date) obj[27]);
                }

                searchItemForm.setManufacturerID(HelperUtils.getString(obj[28]));
                searchItemForm.setManufacturerNO(HelperUtils.getString(obj[29]));
                searchItemForm.setManufacturerName(HelperUtils.getString(obj[30]));
                searchItemForm.setProductOrganizationKey(HelperUtils.getString(obj[31]));

                if (obj[32] != null) {
                    searchItemForm.setAcquisitionDate((Date) obj[32]);
                }
                
                searchItemForm.setAuthorizedGeneric(HelperUtils.getString(obj[33]));
               
                if (obj[34] != null) {
                    searchItemForm.setAuthorizedGenericStartDate((Date) obj[34]);
                }
                if (obj[35] != null) {
                    searchItemForm.setAuthorizedGenericEndDate((Date) obj[35]);
                }
                if (obj[36] != null) {
                    searchItemForm.setFirstSaleDate((Date) obj[36]);
                }

                searchItemForm.setItemTypeIndicator(HelperUtils.getString(obj[37]));
                
                if (obj[38] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[38]);
                }
                searchItemForm.setNewFormulationIndicator(HelperUtils.getString(obj[39]));
                searchItemForm.setNewFormulation(HelperUtils.getString(obj[40]));
                
                if (obj[41] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[41]);
                }
                if (obj[42] != null) {
                    searchItemForm.setMarketTerminationDate((Date) obj[42]);
                }
                
                searchItemForm.setPediatricExclusiveIndicator(HelperUtils.getString(obj[43]));
                searchItemForm.setClottingFactorIndicator(HelperUtils.getString(obj[44]));
                searchItemForm.setShelfLife(HelperUtils.getString(obj[45]));
                searchItemForm.setShelfLifeType(HelperUtils.getString(obj[46]));
                searchItemForm.setDualPricingIndicator(HelperUtils.getString(obj[47]));
                searchItemForm.setItemFamilyID(HelperUtils.getString(obj[48]));
                searchItemForm.setAcquiredAMP(HelperUtils.getString(obj[49]));
                searchItemForm.setAcquiredBAMP(HelperUtils.getString(obj[50]));
                searchItemForm.setPsOBRABAMP(HelperUtils.getString(obj[51]));
                searchItemForm.setPsDRA(HelperUtils.getString(obj[52]));
                searchItemForm.setDosesperUnit(HelperUtils.getString(obj[53]));
                
                if (obj[54] != null) {
                    searchItemForm.setDiscontinuationDate((Date) obj[54]);
                }
                if (obj[55] != null) {
                    searchItemForm.setLastLotExpirationDate((Date) obj[55]);
                }
                if (obj[57] != null) {
                    searchItemForm.setBaseYearCPI((Date) obj[57]);
                }
                
                searchItemForm.setBaselineAMP(HelperUtils.getString(obj[56]));
                searchItemForm.setPsUDC1(obj[58] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[58]));
                searchItemForm.setPsUDC2(obj[59] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[59]));
                searchItemForm.setPsUDC3(obj[60] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[60]));
                searchItemForm.setPsUDC4(obj[61] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[61]));
                searchItemForm.setPsUDC5(obj[62] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[62]));
                searchItemForm.setPsUDC6(obj[63] == null ? StringUtils.EMPTY : CommonUtils.getDescription((Integer) obj[63]));
                searchItemForm.setDisplayBrand(HelperUtils.getString(obj[64]));
                searchItemForm.setInnovatorCode(HelperUtils.getString(obj[65]));
                searchItemList.add(searchItemForm);

            }
        }

        LOGGER.info("returns searchItemList size " + searchItemList.size());
        return searchItemList;
    }

    public void moveItemAndSaveToTempTable(final String selDTO, final SessionDTO sessionDTO) throws SystemException, PortalException {
        if (selDTO != null) {

            final StringBuilder queryString = new StringBuilder(CustomSQLUtil.get("moveAndSaveToTempTable"));
            queryString.append("SELECT ").append(selDTO).append(" ,'I',1,'A','").append(sessionDTO.getUserId()).
                    append("','").append(sessionDTO.getUiSessionId()).append("' ;");
            Object obj = RsModelLocalServiceUtil.executeUpdateQuery(queryString.toString(), this, this);
        }
    }

    public void removeItem(final String selDTO, final SessionDTO sessionDTO, Boolean isRemoveAll) throws SystemException, PortalException {
        if (!isRemoveAll && StringUtils.isNotBlank(selDTO)) {

            String queryString = CustomSQLUtil.get("cp-delete-item");
            queryString=queryString.replace("?UID","'"+sessionDTO.getUserId()+"'")
                    .replace("?SID","'"+sessionDTO.getUiSessionId()+"'")
                    .replace("?IMSID",selDTO);
            RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);
        }else if (isRemoveAll) {
            String queryString = CustomSQLUtil.get("cp-deleteall-item");
            queryString=queryString.replace("?UID","'"+sessionDTO.getUserId()+"'")
                    .replace("?SID","'"+sessionDTO.getUiSessionId()+"'");
            RsModelLocalServiceUtil.executeUpdateQuery(queryString, this, this);
        }
    }

    public Boolean moveItemDuplicateCheck(final String selDTO, final SessionDTO sessionDTO) throws SystemException, PortalException {
        if (selDTO != null) {

            final StringBuilder queryString = new StringBuilder("SELECT COUNT(COMPANY_ITEM_SID) FROM ST_SELECTION_TABLE WHERE ");
            queryString.append("COMPANY_ITEM_SID ='").append(selDTO)
                    .append("' AND SELECTION_TYPE='I' AND USER_ID='").append(sessionDTO.getUserId()).
                    append("' AND SESSION_ID='").append(sessionDTO.getUiSessionId()).append("' ;");

            List obj = (List) RsModelLocalServiceUtil.executeSelectQuery(queryString.toString(), this, this);
            if (((Integer) obj.get(0)) > 0) {
                return true;
            }
        }
        return false;
    }

    public List addToTempTable(ErrorfulFieldGroup searchItemForm)/*, List<OrderByColumn> orderByColumns, BeanSearchCriteria criteria)*/ throws SystemException, PortalException, Exception {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder = buildSearchQuery(searchItemForm, true, 0, 0, StringUtils.EMPTY, null, null, false);
        final List list = (List) RsModelLocalServiceUtil.executeSelectQuery(queryBuilder.toString(), StringUtils.EMPTY, StringUtils.EMPTY);
        return list;
    }

    public void deleteDedutionCalendar(SessionDTO sessionDTO, int deducationCalendarSid) {
        final StringBuilder queryString = new StringBuilder();
        queryString.append("UPDATE DEDUCTION_CALENDAR_MASTER SET INBOUND_STATUS = 'D' WHERE DEDUCTION_CALENDAR_MASTER_SID = ")
                .append(deducationCalendarSid).append(";");
        Object obj = RsModelLocalServiceUtil.executeUpdateQuery(queryString.toString(), this, this);
    }

    public String parseDateLogic(Object object) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String date = formatter.format(object);
        return date;
    }
    public String getQuery(boolean isAdd, SelectionDTO selDTO, Set<Container.Filter> filterSet){
        String query = StringUtils.EMPTY;
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
        List idList = (List) RsModelLocalServiceUtil.executeSelectQuery(query, null, null);
        
        String selectedCompanySids = StringUtils.EMPTY;
        if(!idList.isEmpty()){
            selectedCompanySids = CommonUtils.CollectionToString(idList, false);
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

            if (selectionDTO.getTradeClass() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getTradeClass()))) {
                try {
                    String tradeClassValue = String.valueOf(selectionDTO.getTradeClass());
                    if (tradeClassValue != null && !tradeClassValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("COMPANY_TRADE_CLASS", tradeClassValue);
                        if (helperId != 0) {
                            queryString.append(" AND HT_CTS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                        }
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            if (selectionDTO.getCustomerType() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getCustomerType()))) {
                try {
                    String customerTypeValue = String.valueOf(selectionDTO.getCustomerType());
                    if (customerTypeValue != null && !customerTypeValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("COMPANY_TYPE", customerTypeValue);
                        queryString.append(" AND HT_CT.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            if (selectionDTO.getCustomerStatus() != null && !"-Select One-".equals(String.valueOf(selectionDTO.getCustomerStatus()))) {
                try {
                    String customerStatusValue = String.valueOf(selectionDTO.getCustomerStatus());
                    if (customerStatusValue != null && !customerStatusValue.equals(ConstantsUtils.NULL)) {
                        int helperId = getHelperCode("STATUS", customerStatusValue);
                        queryString.append(" AND HT_CS.HELPER_TABLE_SID = ").append(helperId).append(StringUtils.EMPTY);
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        return getFilterQuery(queryString,filterSet);
    }

    private String getFilterQuery(StringBuilder queryString, Set<Container.Filter> filterSet) {
       DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
         Map<String, Object> parameters = new HashMap<String, Object>();
          if (filterSet != null) {
            for (Container.Filter filter : filterSet) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if ("customerType".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("COMPANY_TYPE", filterValue);
                                parameters.put("customerType", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                    if ("customerStatus".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("STATUS", filterValue);
                                parameters.put("customerStatus", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }
                    if ("tradeClass".equals(stringFilter.getPropertyId())) {
                        if (filterValue != null && !filterValue.equals(ConstantsUtils.NULL)) {
                            try {
                                int helperId = getHelperCode("COMPANY_TRADE_CLASS", filterValue);
                                parameters.put("tradeClass", helperId);
                            } catch (PortalException ex) {
                                LOGGER.error(ex);
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
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

        if ((parameters.get("organisationKey") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("organisationKey"))))) {
            queryString.append(" AND CM.ORGANIZATION_KEY LIKE '%").append(String.valueOf(parameters.get("organisationKey"))).append("%'");
        }
        if ((parameters.get("customerId") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerId"))))) {
            queryString.append(" AND CM.COMPANY_ID LIKE '%").append(String.valueOf(parameters.get("customerId"))).append("%'");
        }
        if ((parameters.get("customerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerNo"))))) {
            queryString.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("customerNo"))).append("%'");
        }
        if ((parameters.get("customerName") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerName"))))) {
            queryString.append(" AND CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get("customerName"))).append("%'");
        }
        if ((parameters.get("customerType") != null && !StringUtils.isBlank(String.valueOf(parameters.get("customerType"))))) {
            queryString.append(" AND CM.COMPANY_TYPE = ").append(String.valueOf(parameters.get("customerType"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("customerStatus") != null && !StringUtils.isBlank(String.valueOf(parameters.get("customerStatus"))))) {
            queryString.append(" AND CM.COMPANY_STATUS = ").append(String.valueOf(parameters.get("customerStatus"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("tradeClass") != null && !StringUtils.isBlank(String.valueOf(parameters.get("tradeClass"))))) {
            queryString.append(" AND CTS.COMPANY_TRADE_CLASS = ").append(String.valueOf(parameters.get("tradeClass"))).append(StringUtils.EMPTY);
        }
        if ((parameters.get("lives") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("lives"))))) {
            queryString.append(" AND CM.LIVES LIKE '%").append(String.valueOf(parameters.get("lives"))).append("%'");
        }
        if ((parameters.get("udc1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc1"))))) {
            queryString.append(" AND UDC.UDC1 LIKE '%").append(String.valueOf(parameters.get("udc1"))).append("%'");
        }
        if ((parameters.get("udc2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc2"))))) {
            queryString.append(" AND UDC.UDC2 LIKE '%").append(String.valueOf(parameters.get("udc2"))).append("%'");
        }
        if ((parameters.get("udc3") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc3"))))) {
            queryString.append(" AND UDC.UDC3 LIKE '%").append(String.valueOf(parameters.get("udc3"))).append("%'");
        }
        if ((parameters.get("udc4") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc4"))))) {
            queryString.append(" AND UDC.UDC4 LIKE '%").append(String.valueOf(parameters.get("udc4"))).append("%'");
        }
        if ((parameters.get("udc5") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc5"))))) {
            queryString.append(" AND UDC.UDC5 LIKE '%").append(String.valueOf(parameters.get("udc5"))).append("%'");
        }
        if ((parameters.get("udc6") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("udc6"))))) {
            queryString.append(" AND UDC.UDC6 LIKE '%").append(String.valueOf(parameters.get("udc6"))).append("%'");
        }
        if ((parameters.get("customerGroup") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerGroup"))))) {
            queryString.append(" AND CM.COMPANY_GROUP LIKE '%").append(String.valueOf(parameters.get("customerGroup"))).append("%'");
        }
        if ((parameters.get("financialSystem") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("financialSystem"))))) {
            queryString.append(" AND CM.FINANCIAL_SYSTEM LIKE '%").append(String.valueOf(parameters.get("financialSystem"))).append("%'");
        }
        if ((parameters.get("address1") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("address1"))))) {
            queryString.append(" AND CM.ADDRESS1 LIKE '%").append(String.valueOf(parameters.get("address1"))).append("%'");
        }
        if ((parameters.get("address2") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("address2"))))) {
            queryString.append(" AND CM.ADDRESS2 LIKE '%").append(String.valueOf(parameters.get("address2"))).append("%'");
        }
        if ((parameters.get("city") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("city"))))) {
            queryString.append(" AND CM.CITY LIKE '%").append(String.valueOf(parameters.get("city"))).append("%'");
        }
        if ((parameters.get("state") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("state"))))) {
            queryString.append(" AND CM.STATE LIKE '%").append(String.valueOf(parameters.get("state"))).append("%'");
        }
        if ((parameters.get("zipCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("zipCode"))))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get("zipCode"))).append("%'");
        }
        if ((parameters.get("country") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("country"))))) {
            queryString.append(" AND CM.COUNTRY LIKE '%").append(String.valueOf(parameters.get("country"))).append("%'");
        }
        if ((parameters.get("regionCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("regionCode"))))) {
            queryString.append(" AND CM.REGION_CODE LIKE '%").append(String.valueOf(parameters.get("regionCode"))).append("%'");
        }
        if ((parameters.get("zipCode") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("zipCode"))))) {
            queryString.append(" AND CM.ZIP_CODE LIKE '%").append(String.valueOf(parameters.get("zipCode"))).append("%'");
        }
        if ((parameters.get("parentCustomerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentCustomerNo"))))) {
            queryString.append(" AND CM1.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("parentCustomerNo"))).append("%'");
        }
        if ((parameters.get("priorParentCustomerNo") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentCustomerNo"))))) {
            queryString.append(" AND CM2.COMPANY_NO LIKE '%").append(String.valueOf(parameters.get("priorParentCustomerNo"))).append("%'");
        }
        if ((parameters.get("tradeClassStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassStartDatefrom"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE >='").append(String.valueOf(parameters.get("tradeClassStartDatefrom"))).append("'");
        }
        if ((parameters.get("tradeClassStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassStartDateto"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_START_DATE <='").append(String.valueOf(parameters.get("tradeClassStartDateto"))).append("'");
        }
        if ((parameters.get("tradeClassEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassEndDatefrom"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE >='").append(String.valueOf(parameters.get("tradeClassEndDatefrom"))).append("'");
        }
        if ((parameters.get("tradeClassEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("tradeClassEndDateto"))))) {
            queryString.append(" AND CTS.TRADE_CLASS_END_DATE <='").append(String.valueOf(parameters.get("tradeClassEndDateto"))).append("'");
        }

        if ((parameters.get("customerStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerStartDatefrom"))))) {
            queryString.append(" AND CM.COMPANY_START_DATE >='").append(String.valueOf(parameters.get("customerStartDatefrom"))).append("'");
        }
        if ((parameters.get("customerStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerStartDateto"))))) {
            queryString.append(" AND CM.COMPANY_START_DATE <='").append(String.valueOf(parameters.get("customerStartDateto"))).append("'");
        }
        if ((parameters.get("customerEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerEndDatefrom"))))) {
            queryString.append(" AND CM.COMPANY_END_DATE >='").append(String.valueOf(parameters.get("customerEndDatefrom"))).append("'");
        }
        if ((parameters.get("customerEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("customerEndDateto"))))) {
            queryString.append(" AND CM.COMPANY_END_DATE <='").append(String.valueOf(parameters.get("customerEndDateto"))).append("'");
        }

        if ((parameters.get("parentStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentStartDatefrom"))))) {
            queryString.append(" AND CPD.PARENT_START_DATE >='").append(String.valueOf(parameters.get("parentStartDatefrom"))).append("'");
        }
        if ((parameters.get("parentStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentStartDateto"))))) {
            queryString.append(" AND CPD.PARENT_START_DATE <='").append(String.valueOf(parameters.get("parentStartDateto"))).append("'");
        }

        if ((parameters.get("parentEndDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEndDatefrom"))))) {
            queryString.append(" AND CPD.PARENT_END_DATE >='").append(String.valueOf(parameters.get("parentEndDatefrom"))).append("'");
        }
        if ((parameters.get("parentEndDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("parentEndDateto"))))) {
            queryString.append(" AND CPD.PARENT_END_DATE LIKE <='").append(String.valueOf(parameters.get("parentEndDateto"))).append("'");
        }

        if ((parameters.get("priorParentStartDatefrom") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentStartDatefrom"))))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE >='").append(String.valueOf(parameters.get("priorParentStartDatefrom"))).append("'");
        }
        if ((parameters.get("priorParentStartDateto") != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get("priorParentStartDateto"))))) {
            queryString.append(" AND CPD.PRIOR_PARENT_START_DATE LIKE <='").append(String.valueOf(parameters.get("priorParentStartDateto"))).append("'");
        }
      return queryString.toString();
    }
}
