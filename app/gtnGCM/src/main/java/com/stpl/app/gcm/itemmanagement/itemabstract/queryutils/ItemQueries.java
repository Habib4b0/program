/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.queryutils;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.copycontract.dao.ContractHeaderDAO;
import com.stpl.app.gcm.copycontract.impl.ContractHeaderLogicDAOImpl;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.gcm.transfercontract.util.Constant;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.Between;
import com.vaadin.v7.data.util.filter.Compare;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class ItemQueries {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueries.class);
    
    private final static CommonDao ITEMDAO = CommonImpl.getInstance();
    public static final String COMPANY_STATUS = "companyStatus";
    public static final String TRADE_CLASS_PROPERTY = "tradeClass";
    public static final String START_DATEFROM = "startDatefrom";
    public static final String START_DATETO = "startDateto";
    public static final String END_DATEFROM = "endDatefrom";
    public static final String END_DATETO = "endDateto";
    public static final String ATTACHED_DATETO = "attachedDateto";
    public static final String ATTACHED_DATE = "attachedDate";
    
    public static final String STATUS_DESCRIPTION = "statusDescription";
    public static final String CONTRACT_TYPE = "contractType";
    
    public static List getGroupList() {
        return new ArrayList();
    }
    
    public static final String MAX_INCREMENTAL_CHANGE = "maxIncrementalChange";
    public static final String ATTACHED_DATEFROM = "attachedDatefrom";
    public static final String COMP_END_DATETO = "compEndDateto";
    public static final String COMP_END_DATEFROM = "compEndDatefrom";
    public static final String COMP_START_DATETO = "compStartDateto";
    public static final String COMP_START_DATEFROM = "compStartDatefrom";
    public static final String RS_NAME = "rSName";
    public static final String PS_NAME = "pSName";
    public static final String CONT_END_DATETO = "contEndDateto";
    public static final String CONT_END_DATEFROM = "contEndDatefrom";
    public static final String CONT_START_DATETO = "contStartDateto";
    public static final String CONT_START_DATEFROM = "contStartDatefrom";
    public static final String SPACE_AND = " AND ";
    
    
    public static List getAppData(List input, String queryName, String quaryName2) {
        List list = new ArrayList();
        StringBuilder sql;
        LOGGER.debug("queryName - - >> {}" , queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(queryName));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.debug("sql ==================== {}" , sql);
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }

        LOGGER.debug("End getAppData");
        return list;
    }
    
    public static List getItemData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                if (queryName.equals("CFP Component Search For Table") || queryName.contains("IFP Component Search For Table")
                        || queryName.contains("PS Component Search For Table") || queryName.contains("RS Component Search For Table")) {
                    sql.append(" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list =  ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static Boolean itemUpdate(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sqlQuery = new StringBuilder();
        try {
            sqlQuery = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sqlQuery.replace(sqlQuery.indexOf("?"), sqlQuery.indexOf("?") + 1, String.valueOf(temp));
            }

            Integer countItemUpdate = (Integer) ITEMDAO.executeUpdate(sqlQuery.toString());
            if (countItemUpdate > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
       StringBuilder sqlBuilder = new StringBuilder();
        try {
            sqlBuilder = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sqlBuilder.replace(sqlBuilder.indexOf("?"), sqlBuilder.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return sqlBuilder.toString();
    }

    public static List getDataByDTO(String queryName, Object dto) {
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
        List list = new ArrayList();
        try {
            String query = getQuery(sql, dto);
            list =  ITEMDAO.executeSelect(query);

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return list;
    }

    public static boolean updateDataByDTO(String queryName, Object dto) {
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
        try {
            String query = getQuery(sql, dto);
            Integer count = (Integer) ITEMDAO.executeUpdate(query);
            if (count > 0) {
                return BooleanConstant.getTrueFlag();
            } else {
                return BooleanConstant.getFalseFlag();
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return false;
    }

    private static String getQuery(StringBuilder query, Object myDTO) throws NoSuchFieldException,  IllegalAccessException {
        while (true) {
            int index = query.indexOf("${");
            if (index != -1) {
                String variable = query.substring(index + NumericConstants.TWO, query.indexOf("}"));
                Object value = getFieldValue(myDTO, variable);
                query.replace(index, query.indexOf("}") + 1, String.valueOf(value));
                continue;
            }
            break;
        }
        return query.toString();
    }

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException,  IllegalAccessException {
        Field field = myDTO.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        return field.get(myDTO);
    }

    public static List getItemData(List input, String queryName, String quaryName2, Set<Container.Filter> filters) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {

            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName.contains("CFP Component Search")) {
                    sql.append(getCFPFilterQuery(filters));
                }
                if (queryName.contains("IFP Component Search")) {
                    sql.append(getIFPFilterQuery(filters));
                }
                if (queryName.contains("PS Component Search")) {
                    sql.append(getPSFilterQuery(filters));
                }
                if (queryName.contains("RS Component Search")) {
                    sql.append(getRSFilterQuery(filters));
                }
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                if (queryName.equals("CFP Component Search For Table")) {
                    sql.append(" ORDER BY CM.COMPANY_NO OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("IFP Component Search For Table")) {
                    sql.append(" ORDER BY IFP.IFP_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("PS Component Search For Table")) {
                    sql.append(" ORDER BY PS.PS_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("RS Component Search For Table")) {
                    sql.append(" ORDER BY RS.RS_CONTRACT_SID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");
                }
                if (queryName.equals("get Submitted Records")) {
                    sql.append(getSubmittedRecordsFilterQuery(filters));
                    sql.append(" ORDER  BY CFP_CON.CFP_CONTRACT_SID, RS_CON.RS_CONTRACT_SID, IFP_CON.IFP_CONTRACT_SID, PS_CON.PS_CONTRACT_SID ;");
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    private static StringBuilder getCFPFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Map<String, Object> cfpParameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    cfpParameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    cfpParameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    dateCondition(stringFilter, cfpParameters, filterValue);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;
                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    cfpParameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormatter.format(startValue)));

                    cfpParameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormatter.format(endValue)));

                }
            }
            if (cfpParameters.get(Constants.COMPANY_NO) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(Constants.COMPANY_NO)))) {
                query.append(" AND CM.COMPANY_NO LIKE '%").append(String.valueOf(cfpParameters.get(Constants.COMPANY_NO))).append("%'");
            }
            if (cfpParameters.get(Constants.COMPANY_NAME) != null && !StringUtils.isBlank(String.valueOf(cfpParameters.get(COMPANY_STATUS)))) {
                String companyName = cfpParameters.get(Constants.COMPANY_NAME).toString();
                query.append(" AND CM.COMPANY_NAME like '%").append(companyName).append("%' ");
            }
            if (cfpParameters.get(COMPANY_STATUS) != null && !String.valueOf(cfpParameters.get(COMPANY_STATUS)).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_CS.DESCRIPTION = '").append(String.valueOf(cfpParameters.get(COMPANY_STATUS))).append('\'');
            }
            if (cfpParameters.get(Constants.STATUS_S) != null && !String.valueOf(cfpParameters.get(Constants.STATUS_S)).equals(Constants.ZEROSTRING)) {
                query.append(" AND  HT.DESCRIPTION = '").append(String.valueOf(cfpParameters.get(Constants.STATUS_S))).append('\'');
            }
            if (cfpParameters.get(TRADE_CLASS_PROPERTY) != null && !String.valueOf(cfpParameters.get(TRADE_CLASS_PROPERTY)).equals(Constants.ZEROSTRING)) {
                query.append(" AND HT_CFP.HELPER_TABLE_SID = ").append(String.valueOf(cfpParameters.get(TRADE_CLASS_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (cfpParameters.get(START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(START_DATEFROM)))) {
                query.append(" AND CFP.CFP_START_DATE >='").append(String.valueOf(cfpParameters.get(START_DATEFROM))).append('\'');
            }
            if (cfpParameters.get(START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(START_DATETO)))) {
                query.append(" AND CFP.CFP_START_DATE <='").append(String.valueOf(cfpParameters.get(START_DATETO))).append('\'');
            }
            if (cfpParameters.get(END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(END_DATEFROM)))) {
                query.append(" AND CFP.CFP_END_DATE >='").append(String.valueOf(cfpParameters.get(END_DATEFROM))).append('\'');
            }
            if (cfpParameters.get(END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(END_DATETO)))) {
                query.append(" AND CFP.CFP_END_DATE <='").append(String.valueOf(cfpParameters.get(END_DATETO))).append('\'');
            }
            if (cfpParameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(ATTACHED_DATEFROM)))) {
                query.append(" AND CFP.CFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(cfpParameters.get(ATTACHED_DATEFROM))).append('\'');
            }
            if (cfpParameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(cfpParameters.get(ATTACHED_DATETO)))) {
                query.append(" AND CFP.CFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(cfpParameters.get(ATTACHED_DATETO))).append('\'');
            }
        }
        return query;
    }

    private static StringBuilder getIFPFilterQuery(Set<Container.Filter> filters) {
        StringBuilder ifpQuery = new StringBuilder();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Map<String, Object> ifpParameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    ifpParameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    ifpParameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    dateCondition(stringFilter, ifpParameters, filterValue);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    ifpParameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dateFormatter.format(startValue)));

                    ifpParameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dateFormatter.format(endValue)));

                }
            }
            if (ifpParameters.get(Constants.ITEM_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(Constants.ITEM_NO_PROPERTY)))) {
                ifpQuery.append(" AND IM.ITEM_NO  LIKE '%").append(String.valueOf(ifpParameters.get(Constants.ITEM_NO_PROPERTY))).append("%'");
            }
            if (ifpParameters.get(Constants.ITEM_NAME_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(ifpParameters.get(Constants.ITEM_NAME_PROPERTY)))) {
                String companyName = ifpParameters.get(Constants.ITEM_NAME_PROPERTY).toString();
                ifpQuery.append(" AND IM.ITEM_NAME  like '%").append(companyName).append("%' ");
            }
            if (ifpParameters.get(Constants.BRAND_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(ifpParameters.get(Constants.BRAND_PROPERTY)))) {
                ifpQuery.append(" AND  B.BRAND_NAME like '%").append(String.valueOf(ifpParameters.get(Constants.BRAND_PROPERTY))).append("%'");
            }
            if (ifpParameters.get(Constants.STATUS_S) != null && !String.valueOf(ifpParameters.get(Constants.STATUS_S)).equals(Constants.ZEROSTRING)) {
                ifpQuery.append(" AND  HT.DESCRIPTION = '").append(String.valueOf(ifpParameters.get(Constants.STATUS_S))).append('\'');
            }
            if (ifpParameters.get(START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(START_DATEFROM)))) {
                ifpQuery.append(" AND IFP.IFP_START_DATE >='").append(String.valueOf(ifpParameters.get(START_DATEFROM))).append('\'');
            }
            if (ifpParameters.get(START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(START_DATETO)))) {
                ifpQuery.append(" AND IFP.IFP_START_DATE <='").append(String.valueOf(ifpParameters.get(START_DATETO))).append('\'');
            }
            if (ifpParameters.get(END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(END_DATEFROM)))) {
                ifpQuery.append(" AND IFP.IFP_END_DATE >='").append(String.valueOf(ifpParameters.get(END_DATEFROM))).append('\'');
            }
            if (ifpParameters.get(END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(END_DATETO)))) {
                ifpQuery.append(" AND IFP.IFP_END_DATE <='").append(String.valueOf(ifpParameters.get(END_DATETO))).append('\'');
            }
            if (ifpParameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(ATTACHED_DATEFROM)))) {
                ifpQuery.append(" AND IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(ifpParameters.get(ATTACHED_DATEFROM))).append('\'');
            }
            if (ifpParameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(ifpParameters.get(ATTACHED_DATETO)))) {
                ifpQuery.append(" AND IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(ifpParameters.get(ATTACHED_DATETO))).append('\'');
            }
        }
        return ifpQuery;
    }

    private static StringBuilder getPSFilterQuery(Set<Container.Filter> filters) {
        StringBuilder psQuery = new StringBuilder();
        Map<String, Object> psParameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    psParameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    psParameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    if (Constants.PRICE_TYPE_PROPERTY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.PRICE_TYPE_PROPERTY, filterValue);
                    }
                    if (Constants.PRICE_PROTECTION_PRICE_TYPE_PROPERTY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.PRICE_PROTECTION_PRICE_TYPE_PROPERTY, filterValue);
                    }
                    if (Constants.PRICE_TOLERANCE_INTERVAL.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.PRICE_TOLERANCE_INTERVAL, filterValue);
                    }
                    if (StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY, filterValue);
                    }
                    if (Constants.PRICE_TOLERANCE_TYPE_PROPERTY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.PRICE_TOLERANCE_TYPE_PROPERTY, filterValue);
                    }
                    if (Constants.RESET_TYPE_PROPERTY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.RESET_TYPE_PROPERTY, filterValue);
                    }
                    if (Constants.RESET_INTERVAL_PROPERTY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(Constants.RESET_INTERVAL_PROPERTY, filterValue);
                    }
                    if (StringConstantsUtil.RESET_FREQUENCY.equals(stringFilter.getPropertyId())) {
                        psParameters.put(StringConstantsUtil.RESET_FREQUENCY, filterValue);
                    }
                    dateCondition(stringFilter, psParameters, filterValue);
                            
                } 
            }

            if (psParameters.get(Constants.ITEM_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(Constants.ITEM_NO_PROPERTY)))) {
                psQuery.append(" AND IM.ITEM_NO LIKE '%").append(String.valueOf(psParameters.get(Constants.ITEM_NO_PROPERTY))).append("%'");
            }
            if (psParameters.get(Constants.ITEM_NAME_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(psParameters.get(Constants.ITEM_NAME_PROPERTY)))) {
                psQuery.append(" AND IM.ITEM_NAME like '%").append(String.valueOf(psParameters.get(Constants.ITEM_NAME_PROPERTY))).append("%'");
            }
            if (psParameters.get(Constants.BRAND_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.BRAND_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND B.BRAND_NAME like '%").append(String.valueOf(psParameters.get(Constants.BRAND_PROPERTY))).append("%'");
            }
            if (psParameters.get(Constants.STATUS_S) != null && !String.valueOf(psParameters.get(Constants.STATUS_S)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT.DESCRIPTION = '").append(String.valueOf(psParameters.get(Constants.STATUS_S))).append('\'');
            }
            if (psParameters.get(START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(START_DATEFROM)))) {
                psQuery.append(" AND PS.PS_START_DATE >='").append(String.valueOf(psParameters.get(START_DATEFROM))).append('\'');
            }
            if (psParameters.get(START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(START_DATETO)))) {
                psQuery.append(" AND PS.PS_START_DATE <='").append(String.valueOf(psParameters.get(START_DATETO))).append('\'');
            }
            if (psParameters.get(END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(END_DATEFROM)))) {
                psQuery.append(" AND PS.PS_END_DATE >='").append(String.valueOf(psParameters.get(END_DATEFROM))).append('\'');
            }
            if (psParameters.get(END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(END_DATETO)))) {
                psQuery.append(" AND PS.PS_END_DATE <='").append(String.valueOf(psParameters.get(END_DATETO))).append('\'');
            }
            if (psParameters.get(Constants.PRICE_TYPE_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.PRICE_TYPE_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_PTP.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.PRICE_TYPE_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(Constants.PRICE_PROTECTION_STATUS_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.PRICE_PROTECTION_STATUS_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND PSD.PRICE_PROTECTION_STATUS = ").append(String.valueOf(psParameters.get(Constants.PRICE_PROTECTION_STATUS_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATEFROM)))) {
                psQuery.append(" AND PSD.PRICE_PROTECTION_START_DATE >='").append(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATEFROM))).append('\'');
            }
            if (psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATETO)))) {
                psQuery.append(" AND PSD.PRICE_PROTECTION_START_DATE <='").append(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_START_DATETO))).append('\'');
            }
            if (psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATEFROM)))) {
                psQuery.append(" AND PSD.PRICE_PROTECTION_END_DATE >='").append(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATEFROM))).append('\'');
            }
            if (psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATETO)))) {
                psQuery.append(" AND PSD.PRICE_PROTECTION_END_DATE <='").append(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_PROTECTION_END_DATETO))).append('\'');
            }
            if (psParameters.get(Constants.PRICE_PROTECTION_PRICE_TYPE_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.PRICE_PROTECTION_PRICE_TYPE_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_PTY.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.PRICE_PROTECTION_PRICE_TYPE_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(Constants.PRICE_TOLERANCE_INTERVAL) != null && !String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_INTERVAL)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_PTI.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_INTERVAL))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY) != null && !String.valueOf(psParameters.get(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_PTF.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(StringConstantsUtil.PRICE_TOLERANCE_FREQUENCY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(Constants.PRICE_TOLERANCE_TYPE_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_TYPE_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_PTT.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_TYPE_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(MAX_INCREMENTAL_CHANGE) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(MAX_INCREMENTAL_CHANGE)))) {
                psQuery.append(" AND PSD.MAX_INCREMENTAL_CHANGE LIKE '%").append(String.valueOf(psParameters.get(MAX_INCREMENTAL_CHANGE))).append("%'");
            }
            if (psParameters.get(Constants.PRICE_TOLERANCE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_PROPERTY)))) {
                psQuery.append(" AND PSD.PRICE_TOLERANCE LIKE '%").append(String.valueOf(psParameters.get(Constants.PRICE_TOLERANCE_PROPERTY))).append("%'");
            }
            if (psParameters.get(Constants.RESET_ELIGIBLE_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(Constants.RESET_ELIGIBLE_PROPERTY)))) {
                psQuery.append(" AND PSD.RESET_ELIGIBLE LIKE '%").append(String.valueOf(psParameters.get(Constants.RESET_ELIGIBLE_PROPERTY))).append("%'");
            }
            if (psParameters.get(Constants.RESET_TYPE_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.RESET_TYPE_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_RT.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.RESET_TYPE_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(StringConstantsUtil.RESET_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.RESET_DATEFROM)))) {
                psQuery.append(" AND PSD.RESET_DATE >='").append(String.valueOf(psParameters.get(StringConstantsUtil.RESET_DATEFROM))).append('\'');
            }
            if (psParameters.get(StringConstantsUtil.RESET_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(StringConstantsUtil.RESET_DATETO)))) {
                psQuery.append(" AND PSD.RESET_DATE <='").append(String.valueOf(psParameters.get(StringConstantsUtil.RESET_DATETO))).append('\'');
            }
            if (psParameters.get(Constants.RESET_INTERVAL_PROPERTY) != null && !String.valueOf(psParameters.get(Constants.RESET_INTERVAL_PROPERTY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_RI.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(Constants.RESET_INTERVAL_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(StringConstantsUtil.RESET_FREQUENCY) != null && !String.valueOf(psParameters.get(StringConstantsUtil.RESET_FREQUENCY)).equals(Constants.ZEROSTRING)) {
                psQuery.append(" AND HT_RF.HELPER_TABLE_SID = ").append(String.valueOf(psParameters.get(StringConstantsUtil.RESET_FREQUENCY))).append(StringUtils.EMPTY);
            }
            if (psParameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(ATTACHED_DATEFROM)))) {
                psQuery.append(" AND PS.PS_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(psParameters.get(ATTACHED_DATEFROM))).append('\'');
            }
            if (psParameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(psParameters.get(ATTACHED_DATETO)))) {
                psQuery.append(" AND PS.PS_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(psParameters.get(ATTACHED_DATETO))).append('\'');
            }
        }
        return psQuery;
    }

    private static StringBuilder getRSFilterQuery(Set<Container.Filter> filters) {
        StringBuilder rsQuery = new StringBuilder();
        final SimpleDateFormat df = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Map<String, Object> rsParameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    rsParameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    rsParameters.put(stringFilter.getPropertyId().toString(), filterValue);
                    if (Constants.FORMULA_TYPE_PROPERTY.equals(stringFilter.getPropertyId())) {
                        rsParameters.put(Constants.FORMULA_TYPE_PROPERTY, filterValue);
                    }
                    dateCondition(stringFilter, rsParameters, filterValue);
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    rsParameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(df.format(startValue)));

                    rsParameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(df.format(endValue)));
                    
                }
            }

            if (rsParameters.get(Constants.ITEM_NO_PROPERTY) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(Constants.ITEM_NO_PROPERTY)))) {
                rsQuery.append(" AND IM.ITEM_NO LIKE '%").append(String.valueOf(rsParameters.get(Constants.ITEM_NO_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.ITEM_NAME_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.ITEM_NAME_PROPERTY)))) {
                rsQuery.append(" AND IM.ITEM_NAME like '%").append(String.valueOf(rsParameters.get(Constants.ITEM_NAME_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.BRAND_PROPERTY) != null && !String.valueOf(rsParameters.get(Constants.BRAND_PROPERTY)).equals(Constants.ZEROSTRING)) {
                rsQuery.append(" AND B.BRAND_NAME like '%").append(String.valueOf(rsParameters.get(Constants.BRAND_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.STATUS_S) != null && !String.valueOf(rsParameters.get(Constants.STATUS_S)).equals(Constants.ZEROSTRING)) {
                rsQuery.append(" AND HT.DESCRIPTION = '").append(String.valueOf(rsParameters.get(Constants.STATUS_S))).append('\'');
            }
            if (rsParameters.get(START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(START_DATEFROM)))) {
                rsQuery.append(" AND RSD.ITEM_REBATE_START_DATE >='").append(String.valueOf(rsParameters.get(START_DATEFROM))).append('\'');
            }
            if (rsParameters.get(START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(START_DATETO)))) {
                rsQuery.append(" AND RSD.ITEM_REBATE_START_DATE <='").append(String.valueOf(rsParameters.get(START_DATETO))).append('\'');
            }
            if (rsParameters.get(END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(END_DATEFROM)))) {
                rsQuery.append(" AND RSD.ITEM_REBATE_END_DATE >='").append(String.valueOf(rsParameters.get(END_DATEFROM))).append('\'');
            }
            if (rsParameters.get(END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(END_DATETO)))) {
                rsQuery.append(" AND RSD.ITEM_REBATE_END_DATE <='").append(String.valueOf(rsParameters.get(END_DATETO))).append('\'');
            }
            if (rsParameters.get(Constants.FORMULA_TYPE_PROPERTY) != null && !String.valueOf(rsParameters.get(Constants.FORMULA_TYPE_PROPERTY)).equals(Constants.ZEROSTRING)) {
                rsQuery.append(" AND RSD.FORMULA_TYPE = ").append(String.valueOf(rsParameters.get(Constants.FORMULA_TYPE_PROPERTY))).append(StringUtils.EMPTY);
            }
            if (rsParameters.get(Constants.FORMULA_ID_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.FORMULA_ID_PROPERTY)))) {
                rsQuery.append(" AND FDM.FORMULA_ID like '%").append(String.valueOf(rsParameters.get(Constants.FORMULA_ID_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.FORMULA_NAME_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.FORMULA_NAME_PROPERTY)))) {
                rsQuery.append(" AND FM.FORMULA_NAME like '%").append(String.valueOf(rsParameters.get(Constants.FORMULA_NAME_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.REBATE_PLAN_ID_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.REBATE_PLAN_ID_PROPERTY)))) {
                rsQuery.append(" AND RP.REBATE_PLAN_ID like '%").append(String.valueOf(rsParameters.get(Constants.REBATE_PLAN_ID_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.REBATE_PLAN_NAME_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.REBATE_PLAN_NAME_PROPERTY)))) {
                rsQuery.append(" AND RP.REBATE_PLAN_NAME like '%").append(String.valueOf(rsParameters.get(Constants.REBATE_PLAN_NAME_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.REBATE_AMOUNT_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.REBATE_AMOUNT_PROPERTY)))) {
                rsQuery.append(" AND RSD.REBATE_AMOUNT like '%").append(String.valueOf(rsParameters.get(Constants.REBATE_AMOUNT_PROPERTY))).append("%'");
            }
            if (rsParameters.get(Constants.BUNDLE_NO_PROPERTY) != null && !StringUtils.isBlank(String.valueOf(rsParameters.get(Constants.BUNDLE_NO_PROPERTY)))) {
                rsQuery.append(" AND RSD.BUNDLE_NO like '%").append(String.valueOf(rsParameters.get(Constants.BUNDLE_NO_PROPERTY))).append("%'");
            }
            if (rsParameters.get(ATTACHED_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(ATTACHED_DATEFROM)))) {
                rsQuery.append(" AND RSD.RS_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(rsParameters.get(ATTACHED_DATEFROM))).append('\'');
            }
            if (rsParameters.get(ATTACHED_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(ATTACHED_DATETO)))) {
                rsQuery.append(" AND RSD.RS_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(rsParameters.get(ATTACHED_DATETO))).append('\'');
            }
            if (rsParameters.get(ATTACHED_DATE) != null && !StringUtils.EMPTY.equals(String.valueOf(rsParameters.get(ATTACHED_DATE)))) {
                rsQuery.append(" AND RSD.RS_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(rsParameters.get(ATTACHED_DATE))).append('\'');
            }
        }
        return rsQuery;
    }

    private static StringBuilder getSubmittedRecordsFilterQuery(Set<Container.Filter> filters) {
        StringBuilder query = new StringBuilder();
         final SimpleDateFormat dff = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);
        Map<String, Object> parameters = new HashMap<>();
        if (filters != null) {
            for (Container.Filter filter : filters) {
                if (filter instanceof SimpleStringFilter) {
                    SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                    String filterString = stringFilter.getFilterString();
                    parameters.put(String.valueOf(stringFilter.getPropertyId()), filterString);

                } else if (filter instanceof Compare) {
                    Compare stringFilter = (Compare) filter;
                    String filterValue = String.valueOf(stringFilter.getValue());
                    if (filterValue != null && STATUS_DESCRIPTION.equals(stringFilter.getPropertyId()) && !filterValue.equals(Constants.NULL)) {
                        try {
                            int helperId = getHelperCode("STATUS", filterValue);
                            parameters.put(Constants.STATUS_S, helperId);
                        } catch (SystemException ex) {
                            LOGGER.error("",ex);
                        }
                    }
                    if (filterValue != null && CONTRACT_TYPE.equals(stringFilter.getPropertyId()) && !filterValue.equals(Constants.NULL)) {
                        try {
                            int helperId = getHelperCode("CONTRACT_TYPE", filterValue);
                            parameters.put(CONTRACT_TYPE, helperId);
                        } catch (SystemException ex) {
                            LOGGER.error("",ex);
                        }
                    }
                } else if (filter instanceof Between) {
                    Between betweenFilter = (Between) filter;

                    Date startValue = (Date) betweenFilter.getStartValue();
                    Date endValue = (Date) betweenFilter.getEndValue();

                    parameters.put(betweenFilter.getPropertyId() + "from", String.valueOf(dff.format(startValue)));

                    parameters.put(betweenFilter.getPropertyId() + "to", String.valueOf(dff.format(endValue)));

                }
            }
            String andOperator = StringUtils.EMPTY;
            query.append(" where ");
            if (parameters.get(Constants.CONTRACT_HOLDER) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(Constants.CONTRACT_HOLDER)))) {
                query.append(" CM.COMPANY_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.CONTRACT_HOLDER))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(Constants.CONTRACT_NO) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CONTRACT_NO)))) {
                query.append(andOperator ).append( " CON.CONTRACT_NO LIKE '%").append(String.valueOf(parameters.get(Constants.CONTRACT_NO))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(Constants.CONTRACT_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CONTRACT_NAME)))) {
                query.append(andOperator ).append( " CON.CONTRACT_NAME LIKE  '%").append(String.valueOf(parameters.get(Constants.CONTRACT_NAME))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(CONTRACT_TYPE) != null && !String.valueOf(parameters.get(CONTRACT_TYPE)).equals(Constants.ZEROSTRING)) {
                query.append(andOperator ).append( " HEL.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get(CONTRACT_TYPE))).append(StringUtils.EMPTY);
                andOperator = SPACE_AND;
            }
            if (parameters.get(STATUS_DESCRIPTION) != null && !String.valueOf(parameters.get(STATUS_DESCRIPTION)).equals(Constants.ZEROSTRING)) {
                query.append(andOperator ).append( " HEL_TAB1.HELPER_TABLE_SID = ").append(String.valueOf(parameters.get(STATUS_DESCRIPTION))).append(StringUtils.EMPTY);
                andOperator = SPACE_AND;
            }
            if (parameters.get(CONT_START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CONT_START_DATEFROM)))) {
                query.append(andOperator ).append( " CON.START_DATE >='").append(String.valueOf(parameters.get(CONT_START_DATEFROM))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(CONT_START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CONT_START_DATETO)))) {
                query.append(andOperator ).append( " CON.START_DATE <='").append(String.valueOf(parameters.get(CONT_START_DATETO))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(CONT_END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CONT_END_DATEFROM)))) {
                query.append(andOperator ).append( " CON.END_DATE >='").append(String.valueOf(parameters.get(CONT_END_DATEFROM))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(CONT_END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(CONT_END_DATETO)))) {
                query.append(andOperator ).append( " CON.END_DATE <='").append(String.valueOf(parameters.get(CONT_END_DATETO))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(Constants.CFP_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.CFP_NAME)))) {
                query.append(andOperator ).append( " CON.CONTRACT_NAME LIKE  '%").append(String.valueOf(parameters.get(Constants.CFP_NAME))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(Constants.IFPNAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(Constants.IFPNAME)))) {
                query.append(andOperator ).append( "  CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(Constants.IFPNAME))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(PS_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(PS_NAME)))) {
                query.append(andOperator ).append( " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(PS_NAME))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(RS_NAME) != null && !StringUtils.isBlank(String.valueOf(parameters.get(RS_NAME)))) {
                query.append(andOperator ).append( " CON.CONTRACT_NAME LIKE '%").append(String.valueOf(parameters.get(RS_NAME))).append("%'");
                andOperator = SPACE_AND;
            }
            if (parameters.get(COMP_START_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_START_DATEFROM)))) {
                query.append(andOperator ).append( " IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get(COMP_START_DATEFROM))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(COMP_START_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_START_DATETO)))) {
                query.append(andOperator ).append( " IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get(COMP_START_DATETO))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(COMP_END_DATEFROM) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_END_DATEFROM)))) {
                query.append(andOperator ).append( " IFP.IFP_CONTRACT_ATTACHED_DATE >='").append(String.valueOf(parameters.get(COMP_END_DATEFROM))).append('\'');
                andOperator = SPACE_AND;
            }
            if (parameters.get(COMP_END_DATETO) != null && !StringUtils.EMPTY.equals(String.valueOf(parameters.get(COMP_END_DATETO)))) {
                query.append(andOperator ).append( " IFP.IFP_CONTRACT_ATTACHED_DATE <='").append(String.valueOf(parameters.get(COMP_END_DATETO))).append('\'');
            }
        }
        return query;
    }

    public static int getHelperCode(String listName, String description) {
        ContractHeaderDAO dao = new ContractHeaderLogicDAOImpl();
        int code = 0;
        final DynamicQuery dynamicQuery = HelperTableLocalServiceUtil.dynamicQuery();
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result = dao.getHelperTableList(dynamicQuery);
        if (result != null && !result.isEmpty()) {
            code = Integer.parseInt(result.get(0).toString());
        }
        return code;
    }
    public static boolean updateDataByDTOforRemoveDiscount(String queryName, RemoveDiscountDto dto) {
        StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
        try {
            String query = getQuery(sql, dto);
            query = query.replace("@CHECK_RECORD", (dto.getCheckRecord() ? 1 : 0) + StringUtils.EMPTY);
            Integer count = (Integer) ITEMDAO.executeUpdate(query);
            if (count > 0) {
                return BooleanConstant.getTrueFlag();
            } else {
                return BooleanConstant.getFalseFlag();
            }

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            LOGGER.error("",ex);
        }
        return false;
    }

    public static List<Object[]> execute(String query) {
      return (List<Object[]>) ITEMDAO.executeSelect(query);
    }
     public static void dateCondition(Compare stringFilter, Map<String, Object> parameters, String filterValue) {
        if (Constants.START_DATE.equals(stringFilter.getPropertyId())) {
            parameters.put(stringFilter.getPropertyId() + Constants.FROM, filterValue);
        }
        if (Constant.END_DATE.equals(stringFilter.getPropertyId())) {
            parameters.put(stringFilter.getPropertyId() + Constants.TO, filterValue);
        }
        if (Constants.ATTACHED_DATE_PROPERTY.equals(stringFilter.getPropertyId())) {
            parameters.put(String.valueOf(stringFilter.getPropertyId()), filterValue);
        }
    }
}
